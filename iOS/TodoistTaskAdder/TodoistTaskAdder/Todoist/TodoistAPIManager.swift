//
//  TodoistAPIManager.swift
//  TodoistTaskAdder
//
//  Created by Michael Charland on 2021-12-05.
//

import Alamofire
import Foundation

class TodoistAPIManager {
    static let shared = TodoistAPIManager()

    func getProjects(completionHandler: @escaping (Result<[TodoistProject],Error>) -> Void) {
        AF.request(TodoistRouter.projects)
            .validate(statusCode: 200..<300)
            .responseDecodable(of: [TodoistProject].self) { response in
            switch response.result {
            case .success(let projects):
                completionHandler(.success(projects))
            case .failure(let failure):
                completionHandler(.failure(failure))
            }
        }
    }

    func addTask(title: String) {
        AF.request(TodoistRouter.addTask(content: title))
            .validate(statusCode: 200..<300)
            .responseData(completionHandler: { response in
            switch response.result {
            case .success(let projects):
                print("good")
            case .failure(let failure):
                print("bad")
            }
        })
    }
}
