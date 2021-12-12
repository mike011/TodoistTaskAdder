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

    private init() {}

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

    func add(tasks: [TodoistTaskToAdd], completionHandler: @escaping (Result<TodoistTask,Error>) -> Void) {
        for task in tasks {
            getProjectId(from: task.project) { result in
                switch result {
                case .success(let id):
                    self.addTask(projectID: id, title: task.title, due: Date(), completionHandler: completionHandler)
                case .failure(let failure):
                    completionHandler(.failure(failure))
                }
            }
            break
        }
    }

    private func getProjectId(from project: String, completionHandler: @escaping (Result<Int,Error>) -> Void) {
        //task.projectID
        if projects == nil {
            getProjects {  response in
                switch response {
                case .success(let projects):
                    self.projects = projects
                    if let project = projects.first( where: { $0.name == project } ) {
                        //return project.id
                        completionHandler(.success(project.id))
                    }
                case .failure(let failure):
                    completionHandler(.failure(failure))
                }
            }
        } else {
            if let project = projects!.first( where: { $0.name == project } ) {
                //return project.id
                completionHandler(.success(project.id))
            }
        }

//        return 0
    }


    func addTask(projectName: String, title: String, due: Date?, completionHandler: @escaping (Result<TodoistTask,Error>) -> Void) {
        getProjects { response in
            switch response {
            case .success(let projects):
                if let project = projects.first( where: { $0.name == projectName } ) {
                    self.addTask(project: project, title: title, due: due, completionHandler: completionHandler)
                } else {
                    completionHandler(.failure(TodoistError.projectNameNotFound(name: title)))
                }
            case .failure(let failure):
                completionHandler(.failure(failure))
            }
        }
    }

    func addTask(project: TodoistProject, title: String, due: Date?, completionHandler: @escaping (Result<TodoistTask,Error>) -> Void) {
        addTask(projectID: project.id, title: title, due: due, completionHandler: completionHandler)
    }

    func addTask(projectID: Int, title: String, due: Date?, completionHandler: @escaping (Result<TodoistTask,Error>) -> Void) {
        AF.request(TodoistRouter.addTask(projectID: projectID, content: title, due: due))
            .validate(statusCode: 200..<300)
            .responseDecodable(of: TodoistTask.self) { response in
            switch response.result {
            case .success(let project):
                completionHandler(.success(project))
            case .failure(let failure):
                completionHandler(.failure(failure))
            }
        }
    }
}
