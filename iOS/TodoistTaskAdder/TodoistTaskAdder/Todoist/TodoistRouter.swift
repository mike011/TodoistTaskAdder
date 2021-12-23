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
            var url = URL(string: Self.baseURLString)!
            switch self {
            case .projects:
                url.appendPathComponent("projects")
            case .add:
                url.appendPathComponent("tasks")
            case .labels:
                url.appendPathComponent("labels")
            }
            return url
        }()

        let contentType: String? = {
            switch self {
            case .add:
                return "application/json"
            default:
                return nil
            }
        }()

        let params: (Parameters?) = {
            switch self {
            case .projects:
                return nil
            case let .add(id, task):
                var params = [String: Any]()
                params["project_id"] = id
                params["content"] = task.title
                params["label_ids"] = task.labelIDs
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
        if let contentType = contentType {
            urlRequest.setValue(contentType, forHTTPHeaderField: "Content-Type")
        }
        urlRequest.setValue("Bearer \(Self.apiKey)", forHTTPHeaderField: "Authorization")

        let encoding = JSONEncoding.default
        let request = try encoding.encode(urlRequest, with: params)
        return request
    }
}

private extension Date {
    func toString() -> String {
        let formatter = ISO8601DateFormatter()
        return formatter.string(from: self)
    }
}
