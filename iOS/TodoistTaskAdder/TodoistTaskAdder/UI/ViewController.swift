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

        getPocketIems { items in
            self.present(items)
//            var todoistTasks = [TodoistTaskToAdd]()
//            for item in items {
//                let ttc = TodoistTaskConverter(pocketedItem: item)
//                todoistTasks.append(ttc.getTodoistTask())
//
//                // only add one <- testing
//                break
//            }
//            TodoistAPIManager.shared.add(tasks: todoistTasks) { result in
//                switch result {
//                case .success(let project):
//                    print(project)
//                case .failure(let error):
//                    self.present(error)
//                }
//            }
        }
    }

    private func getPocketIems(completionHandler: @escaping ([PocketedItem]) -> Void) {
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
            self.getItems(completionHandler: completionHandler)
        }

        if (!PocketAPIManager.shared.hasOAuthToken()) {
            showOAuthLoginView()
            return
        } else {
            self.getItems(completionHandler: completionHandler)
        }
    }

    func showOAuthLoginView() {
        PocketAPIManager.shared.isLoadingOAuthToken = true
        PocketAPIManager.shared.requestToken { result in
            switch result {
            case .success(let code):
                self.handleSuccessfulRequest(code)
            case .failure(let error):
                self.present(error)
            }
        }
    }

    fileprivate func getItems(completionHandler: @escaping ([PocketedItem]) -> Void) {
        PocketAPIManager.shared.getItems { result in
            switch result {
            case .success(let items):
                completionHandler(items)
            case .failure(let error):
                self.present(error)
            }
        }
    }

    func handleSuccessfulRequest(_ requestToken: String) {
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

    func present(_ items: [PocketedItem]) {
        addLinksToPasteBaord(items)
        let alertController = UIAlertController(title: "Message", message: items[0].link, preferredStyle: .alert)
        let alertAction = UIAlertAction(title: "OK", style: .default, handler:nil)
        alertController.addAction(alertAction)
        present(alertController, animated: true, completion: nil)
    }

    fileprivate func addLinksToPasteBaord(_ items: [PocketedItem]) {
        var contents = ""
        for item in items {
            contents += TodoistTaskConverter(pocketedItem: item).convert().description + "\n"
        }
        UIPasteboard.general.string = contents
    }

    func present(_ error: Error) {
        let title = String(describing: type(of: error))
        let message = "\(error)"
        let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
        let alertAction = UIAlertAction(title: "OK", style: .default, handler:nil)
        alertController.addAction(alertAction)
        present(alertController, animated: true, completion: nil)
    }
}

