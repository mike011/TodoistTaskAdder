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
        doPocketMagic()
    }

    private func doPocketMagic() {
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
            PocketAPIManager.shared.getItems()
        }

        if (!PocketAPIManager.shared.hasOAuthToken()) {
            showOAuthLoginView()
            return
        } else {
            PocketAPIManager.shared.getItems()
        }
    }

    func showOAuthLoginView() {
        PocketAPIManager.shared.isLoadingOAuthToken = true
        PocketAPIManager.shared.requestToken { result in
            switch result {
            case .success(let code):
                self.handleSuccessfulRequest(code)
            case .failure(let error):
                print(error)
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

    override func viewDidAppear(_ animated: Bool) {
        let x = 3
    }
}

