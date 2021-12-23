//
//  PocketRouter.swift
//  TableViewWithREST
//
//  Created by Michael Charland on 2021-11-07.
//  Copyright © 2019 charland. All rights reserved.
//

import Alamofire
import Foundation

enum PocketRouter: URLRequestConvertible {
    private static let baseURLString = "https://getpocket.com/v3/"
    private static let consumerKey = PocketOAuthKeys.consumerKey

    case request
    case authorize(code: String)
    case get(token: String, tagType: String? = nil)
    case archive(itemID: Int)

    func asURLRequest() throws -> URLRequest {
        var method: HTTPMethod {
            return .post
        }

        let url: URL = {
            var url = URL(string: Self.baseURLString)!
            switch self {
            case .request:
                url.appendPathComponent("oauth/request")
            case .authorize:
                url.appendPathComponent("oauth/authorize")
            case .get:
                url.appendPathComponent("get")
            case .archive:
                url.appendPathComponent("send")
            }
            return url
        }()

        let params: ([String: Any]?) = {
            switch self {
            case .request:
                return ["consumer_key": Self.consumerKey,
                        "redirect_uri": "pocket://localhost"]
            case .authorize(let code):
                return ["consumer_key": Self.consumerKey,
                        "code": code]
            case .get(let token, let tagType):
                var getParams = ["consumer_key": Self.consumerKey,
                                  "access_token": token,
                                  "detailType": "complete",
                                  "sort": "oldest"]
                if let tagType = tagType {
                    getParams["tag"] = tagType
                }
                return getParams
            case let .archive(itemID):
                return ["action": "archive",
                        "item_id": itemID]
            }
        }()

        let contentType: String? = {
            switch self {
            case .archive:
                return "application/json"
            default:
                return nil
            }
        }()

        var urlRequest = URLRequest(url: url)
        urlRequest.httpMethod = method.rawValue
        if let contentType = contentType {
            urlRequest.setValue(contentType, forHTTPHeaderField: "Content-Type")
        }

        let encoding = JSONEncoding.default
        let uRLRequest = try encoding.encode(urlRequest, with: params)
        return uRLRequest
    }
}
