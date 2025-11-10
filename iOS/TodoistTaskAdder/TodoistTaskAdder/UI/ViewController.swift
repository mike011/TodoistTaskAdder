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
        addCustomItems()
    }

    fileprivate func addCustomItems() {
        for (_,task) in CustomTodoistAdder.getTasks().enumerated() {
            addTodoistTask(task)
        }
    }

    fileprivate func addTodoistTask(_ task: TodoistTaskToAdd) {
        TodoistAPIManager.shared.add(task: task) { result in
            switch result {
            case let .success(project):
                print("added \(project.content)")
            case .failure(let error):
                self.present(error)
            }
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

    func present(_ error: Error) {
        let title = String(describing: type(of: error))
        let message = "\(error)"
        let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
        let alertAction = UIAlertAction(title: "OK", style: .default, handler:nil)
        alertController.addAction(alertAction)
        present(alertController, animated: true, completion: nil)
    }
}

