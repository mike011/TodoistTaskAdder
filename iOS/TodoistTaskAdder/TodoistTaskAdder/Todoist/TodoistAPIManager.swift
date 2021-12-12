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

    private var projects: [TodoistProject]?
    private var labels: [TodoistLabel]?

    private init() {}

    // MARK: - Projects

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

    private func getProjectId(from project: String, completionHandler: @escaping (Result<Int,Error>) -> Void) {
        if projects == nil {
            getProjects {  response in
                switch response {
                case .success(let projects):
                    self.projects = projects
                    if let project = projects.first( where: { $0.name == project } ) {
                        completionHandler(.success(project.id))
                    }
                case .failure(let failure):
                    completionHandler(.failure(failure))
                }
            }
        } else {
            if let project = projects!.first( where: { $0.name == project } ) {
                completionHandler(.success(project.id))
            }
        }
    }

    // MARK: - Tasks

    func add(tasks: [TodoistTaskToAdd], completionHandler: @escaping (Result<TodoistTask,Error>) -> Void) {
        for task in tasks {
            self.getProjectId(from: task.projectName) { result in
                switch result {
                case .success(let id):
                    self.addTask(projectID: id, task: task, completionHandler: completionHandler)
                case .failure(let failure):
                    completionHandler(.failure(failure))
                }
            }
            break
        }
    }

    func addTask(projectName: String, title: String, due: Date?, completionHandler: @escaping (Result<TodoistTask,Error>) -> Void) {
        getProjects { response in
            switch response {
            case .success(let projects):
                if let project = projects.first( where: { $0.name == projectName } ) {
                    let task = TodoistTaskToAdd(projectName: project.name, title: title, labelIDs: [Int]())
                    self.addTask(projectID: project.id, task: task, completionHandler: completionHandler)
                } else {
                    completionHandler(.failure(TodoistError.projectNameNotFound(name: title)))
                }
            case .failure(let failure):
                completionHandler(.failure(failure))
            }
        }
    }

    func addTask(projectID: Int, task: TodoistTaskToAdd, completionHandler: @escaping (Result<TodoistTask,Error>) -> Void) {
        AF.request(TodoistRouter.add(id: projectID, task: task))
            .validate()
            .responseDecodable(of: TodoistTask.self) { response in
            switch response.result {
            case .success(let project):
                completionHandler(.success(project))
            case .failure(let failure):
                completionHandler(.failure(failure))
            }
        }
    }

    // MARK: - Labels
    func getLabels(completionHandler: @escaping (Result<[TodoistLabel],Error>) -> Void) {
        if labels == nil {
            AF.request(TodoistRouter.labels)
                .validate()
                .responseDecodable(of: [TodoistLabel].self) { response in
                    switch response.result {
                    case .success(let labels):
                        self.labels = labels
                        completionHandler(.success(labels))
                    case .failure(let failure):
                        completionHandler(.failure(failure))
                    }
                }
        } else {
            completionHandler(.success(labels!))
        }
    }
}
