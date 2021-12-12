//
//  TodoistRouter.swift
//  TodoistTaskAdder
//
//  Created by Michael Charland on 2021-12-05.
//

import Alamofire
import Foundation

enum TodoistRouter: URLRequestConvertible {
    private static let baseURLString = "https://api.todoist.com/rest/v1/"
    private static let apiKey = TodoistAPIKeys.api

    /// Gets all the projects
    case projects

    // Adds a task to Todoist
    case add(id: Int, task: TodoistTaskToAdd)

    /// Gets all the labels
    case labels

    func asURLRequest() throws -> URLRequest {
        var method: HTTPMethod {
            switch self {
            case .projects:
                return .get
            case .add:
                return .post
            case .labels:
                return .get
            }
        }

        let url: URL = {
            switch self {
            case .projects:
                var url = URL(string: Self.baseURLString)!
                url.appendPathComponent("projects")
                return url
            case .add:
                var url = URL(string: Self.baseURLString)!
                url.appendPathComponent("tasks")
                return url
            case .labels:
                var url = URL(string: Self.baseURLString)!
                url.appendPathComponent("labels")
                return url
            }
        }()

        let params: ([String: Any]?) = {
            switch self {
            case .projects:
                return nil
            case let .add(id, task):
                var params = [String: Any]()
                params["project_id"] = id
                params["content"] = task.title
                if !task.labelIDs.isEmpty {
                    params["label_ids"] = task.labelIDs
                }
//                var dueDictionary = [String:Any]()
//                params["due"] = dueDictionary
//                dueDictionary["string"] = "mon 9pm"
//                dueDictionary["date"] = "2021-12-15"
//                dueDictionary["recuring"] = false
                return params
            case .labels:
                return nil
            }
        }()

        var urlRequest = URLRequest(url: url)
        urlRequest.httpMethod = method.rawValue
        urlRequest.setValue("Bearer \(Self.apiKey)", forHTTPHeaderField: "Authorization")

        let encoding = URLEncoding.default
        return try encoding.encode(urlRequest, with: params)
    }
}

private extension Date {
    func toString() -> String {
        let formatter = ISO8601DateFormatter()
        return formatter.string(from: self)
    }
}
