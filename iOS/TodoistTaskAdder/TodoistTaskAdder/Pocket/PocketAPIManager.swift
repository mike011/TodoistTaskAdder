//
//  PocketAPIManager.swift
//  TableViewWithREST
//
//  Created by Michael Charland on 2021-11-07.
//  Copyright © 2021 charland. All rights reserved.
//

import Alamofire
import Foundation
import Locksmith

class PocketAPIManager {
    static let shared = PocketAPIManager()
    var isLoadingOAuthToken = false
    private var requestToken: String?

    // handler for the OAuth process
    // stored as var since sometimes it requires a round trip to safari which
    // makes it hard to just keep a reference to it
    var oAuthTokenCompletionHandler:((Error?) -> Void)?

    var oAuthToken: String?
    {
        set {
            guard let newValue = newValue else {
                let _ = try? Locksmith.deleteDataForUserAccount(userAccount: "pocket")
                return
            }
            guard let _ = try? Locksmith.updateData(data: ["token": newValue], forUserAccount: "pocket") else {
                let _ = try? Locksmith.deleteDataForUserAccount(userAccount: "pocket")
                return
            }
        }
        get {
            // try to load data from Keychain
            let dictionary = Locksmith.loadDataForUserAccount(userAccount: "pocket")
            return dictionary?["token"] as? String
        }
    }

    func hasOAuthToken() -> Bool {
        if let token = oAuthToken {
            return !token.isEmpty
        }
        return false
    }

    func requestToken(completionHandler: @escaping (Result<String,Error>) -> Void) {
        AF.request(PocketRouter.request)
            .responseData(completionHandler: { response in
                switch response.result {
                case .success(let data):
                    let stringData = String(decoding: data, as: UTF8.self)
                    let code = String(stringData.split(separator: "=")[1])
                    self.requestToken = code
                    completionHandler(.success(code))
                case .failure(let failure):
                    completionHandler(.failure(BackendError.request(error: failure)))
                }
            })
    }

    func convertURLToStartOAuth2Login(_ requestToken: String) -> URL? {
        let redirectURI = "pocket://localhost"
        let path = "https://getpocket.com/auth/authorize?request_token=\(requestToken)&redirect_uri=\(redirectURI)"
        return URL(string: path)
    }
    
    func processOAuthStep1Response() {
        guard let requestToken = requestToken else {
            return
        }
        swapAuthCodeForToken(code: requestToken)
    }

    func swapAuthCodeForToken(code: String) {
        authorize(requestToken: code, completionHandler: { result in
            switch result {
            case .success(let authorizeToken):
                self.isLoadingOAuthToken = false

                self.oAuthToken = authorizeToken

                guard self.hasOAuthToken() else {
                    return
                }
                if self.hasOAuthToken() {
                    self.oAuthTokenCompletionHandler?(nil)
                } else {
                    let error = BackendError.authCouldNot(reason: "Could not obtain an OAuth token")
                    self.oAuthTokenCompletionHandler?(error)
                }
            case .failure(let error):
                self.isLoadingOAuthToken = false
                let errorMessage = error.localizedDescription
                let error = BackendError.authCouldNot(reason: errorMessage)
                self.oAuthTokenCompletionHandler?(error)
            }
        })
    }

    func authorize(requestToken: String, completionHandler: @escaping (Result<String,Error>) -> Void) {
        AF.request(PocketRouter.authorize(code: requestToken))
            .validate(statusCode: 200..<300)
            .responseData(completionHandler: { response in
                switch response.result {
                case .success(let data):
                    let stringData = String(decoding: data, as: UTF8.self)
                    let groups = String(stringData.split(separator: "&")[0])
                    let accessToken = String(groups.split(separator: "=")[1])
                    completionHandler(.success(accessToken))
                case .failure(let failure):
                    completionHandler(.failure(BackendError.request(error: failure)))
                }
            })
    }


    // MARK: get all the items

    func getItems(completionHandler: @escaping (Result<[PocketedItem],Error>) -> Void) {
        PocketAPIManager.shared.getUntaggedItems { result in
            switch result {
            case .success:
//                if items.isEmpty {
                    self.getAll(completionHandler: completionHandler)
//                } else {
//                    var links = [String]()
//                    for item in items {
//                        links.append("\(item.name) with link \(item.link)")
//                    }
//                    completionHandler(.failure(PocketError.notAllTagged(links)))
//                }

            case .failure(let error):
                if let afError = error.asAFError {
                    completionHandler(.failure(PocketError.decodeIssue(afError.localizedDescription)))
                    return
                }
                completionHandler(.failure(PocketError.decodeIssue(error.localizedDescription)))
            }
        }
    }

    func getAll(completionHandler: @escaping (Result<[PocketedItem],Error>) -> Void) {
        getAllItems { result in
            switch result {
            case .success(let items):
                completionHandler(.success(items))
            case .failure(let error):
                completionHandler(.failure(BackendError.request(error: error)))
            }
        }
    }

    func getAllItems(completionHandler: @escaping (Result<[PocketedItem],Error>) -> Void) {
        guard let oAuthToken = oAuthToken else {
            return
        }
        AF.request(PocketRouter.get(token: oAuthToken))
                    .responseDecodable(of: PocketMyList.self) { response in
            switch response.result {
            case .success(let response):
                var pis = [PocketedItem]()
                for item in response.pockedItems.values {
                    var tags = [String]()
                    if let parsedTags = item.tags {
                        tags = parsedTags.map{String($0.key)}
                    }
                    if item.givenURL.contains("blog.randonneursontario.ca") {
                        tags.append("cycling")
                        tags.append("@blog")
                    } else if item.givenURL.contains("youtube") {
                        tags.append("Inbox")
                        tags.append("@audio/video")
                    } else if tags.isEmpty {
                        tags.append("Inbox")
                    }
                    let pi = PocketedItem(name: item.resolvedTitle, link: item.givenURL, tags: tags, id: item.itemID)
                    pis.append(pi)
                }
                completionHandler(.success(pis.sorted(by: { first, second in
                    return first.name.compare(second.name) == ComparisonResult.orderedAscending
                })))

            case .failure(let failure):
                completionHandler(.failure(failure))
            }
        }
    }

    func printIt() {
        guard let oAuthToken = oAuthToken else {
            return
        }
        AF.request(PocketRouter.get(token: oAuthToken, tagType: "_untagged_"))
            .responseData(completionHandler: { response in
                switch response.result {
                case .success(let response):
                    let x = String(decoding: response, as: UTF8.self)
                    for xx in x.split(separator: "}") {
                        if xx.contains("tags") {
                            print(xx)
                        }
                    }
                    print(x)
                case .failure(let failure):
                    print(failure)
                }})
    }

    func getUntaggedItems(completionHandler: @escaping (Result<[PocketedItem],Error>) -> Void) {
        guard let oAuthToken = oAuthToken else {
            return
        }
//        printIt()
        AF.request(PocketRouter.get(token: oAuthToken, tagType: "_untagged_"))
                    .responseDecodable(of: UnTaggedPocketMyList.self) { response in
                        switch response.result {
                        case .success(let result):
                            var pis = [PocketedItem]()
                            if let items = result.pockedItems {
                                for item in items {
                                    let pi = PocketedItem(name: item.resolvedTitle,
                                                          link: item.givenURL,
                                                          tags: ["Inbox"],
                                                          id: item.itemID)
                                    pis.append(pi)
                                }
                            }
                            completionHandler(.success(pis))

                        case .failure(_):
                            self.tryOtherFormat(completionHandler: completionHandler)
                        }
                    }
    }

    func tryOtherFormat(completionHandler: @escaping (Result<[PocketedItem],Error>) -> Void) {
        guard let oAuthToken = oAuthToken else {
            return
        }
        AF.request(PocketRouter.get(token: oAuthToken, tagType: "_untagged_"))
                    .responseDecodable(of: PocketMyList.self) { response in
                        switch response.result {
                        case .success(let result):
                            var pis = [PocketedItem]()
                            for item in result.pockedItems.values {
                                let pi = PocketedItem(name: item.resolvedTitle, link: item.givenURL, tags: ["Inbox"], id: item.itemID)
                                pis.append(pi)
                            }
                            completionHandler(.success(pis))

                        case .failure(let failure):
                            completionHandler(.failure(failure))
                        }
                    }
    }

    func archiveItem(itemID: String, completionHandler: @escaping (Result<PocketArchiveResponse,Error>) -> Void) {
        AF.request(PocketRouter.archive(itemID: Int(itemID)!))
            .responseData(completionHandler: { response in
                switch response.result {
                case .success(let data):
                    let stringData = String(decoding: data, as: UTF8.self)
                    print(stringData)
                case .failure(let failure):
                    completionHandler(.failure(BackendError.request(error: failure)))
                }
//            .responseDecodable(of: PocketArchiveResponse.self) { response in
//                switch response.result {
//                case .success(let archivedObject):
//                    completionHandler(.success(archivedObject))
//                case .failure(let failure):
//                    completionHandler(.failure(failure))
//                }
        })
    }

}

struct PocketedItem {
    let name: String
    let link: String
    let tags: [String]
    let id: String
}
