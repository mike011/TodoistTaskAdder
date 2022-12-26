//
//  ViewController.swift
//  TodoistTaskAdder
//
//  Created by Michael Charland on 2021-11-16.
//

import SafariServices
import UIKit

class ViewController: UIViewController, SFSafariViewControllerDelegate {

    private var isLoading = false
    private var safariViewController: SFSafariViewController?

    override func viewDidLoad() {
        super.viewDidLoad()

        getTodoistLabels { labels in
            self.getPocketItems { items in
                let errorFound = self.handlePocketItems(items: items, withLabels: labels);
                if errorFound {
                    return
                }
                print("<<< done >>>")
            }
        }
    }

    private func handlePocketItems(items: [PocketedItem], withLabels labels: [TodoistLabel]) -> Bool {
        for item in items {
            if !(handlePocketItem(item: item, withLabels: labels)) {
                return false
            }
            break
        }
        return false
    }

    private func handlePocketItem(item: PocketedItem, withLabels labels: [TodoistLabel]) -> Bool {
        let ttc = TodoistTaskConverter(pocketedItem: item, todoistLabels: labels)
        do {
            let task = try ttc.getTodoistTask()
            TodoistAPIManager.shared.add(task: task) { result in
                switch result {
                case let .success(project):
                    print("added \(project.content)")
//                                PocketAPIManager.shared.archiveItem(itemID: item.id) { result in
//                                    switch result {
//                                    case .success(let archived):
//                                        print("archived \(archived)")
//                                    case .failure(let error):
//                                        self.present(error)
//                                    }
//                                }
                case .failure(let error):
                    self.present(error)
                }
            }
        } catch {
            self.present(error)
            return true
        }
        return false
    }



    private func getTodoistLabels(completionHandler: @escaping ([TodoistLabel]) -> Void) {
        PocketAPIManager.shared.oAuthTokenCompletionHandler = { error in
            guard error == nil else {
                print(error!)
                self.isLoading = false
                self.showOAuthLoginView()
                return
            }
            if let _ = self.safariViewController {
                self.dismiss(animated: false) {}
            }
            self.getLabels(completionHandler: completionHandler)
        }
        if (!PocketAPIManager.shared.hasOAuthToken()) {
            showOAuthLoginView()
            return
        } else {
            getLabels(completionHandler: completionHandler)
        }
    }

    private func getLabels(completionHandler: @escaping ([TodoistLabel]) -> Void) {
        TodoistAPIManager.shared.getLabels { result in
            switch result {
            case .success(let labels):
                completionHandler(labels)
            case .failure(let error):
                self.present(error)
            }
        }
    }

    func showOAuthLoginView() {
        PocketAPIManager.shared.isLoadingOAuthToken = true
        PocketAPIManager.shared.requestToken { result in
            switch result {
            case .success(let code):
                self.showRequestTokenPage(code)
            case .failure(let error):
                self.present(error)
            }
        }
    }

    func showRequestTokenPage(_ requestToken: String) {
        guard let authURL = PocketAPIManager.shared.convertURLToStartOAuth2Login(requestToken) else {
            let error = BackendError.authCouldNot(reason: "Could not obtain an OAuth token")
            PocketAPIManager.shared.oAuthTokenCompletionHandler?(error)
            return
        }
        // Show web page to start OAuth
        safariViewController = SFSafariViewController(url: authURL)
        safariViewController?.delegate = self
        guard let webViewController = self.safariViewController else {
            return
        }
        present(webViewController, animated: true, completion: nil)
    }

    private func getPocketItems(completionHandler: @escaping ([PocketedItem]) -> Void) {
        PocketAPIManager.shared.getItems { result in
            switch result {
            case .success(let items):
                completionHandler(items)
            case .failure(let error):
                self.present(error)
            }
        }
    }


//    func present(_ items: [PocketedItem]) {
//        addLinksToPasteBaord(items)
//        let alertController = UIAlertController(title: "Message", message: items[0].link, preferredStyle: .alert)
//        let alertAction = UIAlertAction(title: "OK", style: .default, handler:nil)
//        alertController.addAction(alertAction)
//        present(alertController, animated: true, completion: nil)
//    }
//
//    fileprivate func addLinksToPasteBaord(_ items: [PocketedItem]) {
//        var contents = ""
//        for item in items {
//            contents += try TodoistTaskConverter(pocketedItem: item).convert().description + "\n"
//        }
//        UIPasteboard.general.string = contents
//    }

    func present(_ error: Error) {
        let title = String(describing: type(of: error))
        let message = "\(error)"
        let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
        let alertAction = UIAlertAction(title: "OK", style: .default, handler:nil)
        alertController.addAction(alertAction)
        present(alertController, animated: true, completion: nil)
    }
}

