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

    case projects
    case addTask(content: String)

    func asURLRequest() throws -> URLRequest {
        var method: HTTPMethod {
            switch self {
            case .projects:
                return .get
            case .addTask(_):
                return .post
            }
        }

        let url: URL = {
            switch self {
            case .projects:
                var url = URL(string: Self.baseURLString)!
                url.appendPathComponent("projects")
                return url
            case .addTask:
                var url = URL(string: Self.baseURLString)!
                url.appendPathComponent("tasks")
                return url
            }
        }()

        let params: ([String: Any]?) = {
            switch self {
            case .projects:
                return nil
            case .addTask(let content):
                var params = [String: Any]()
                params["content"] = content
                return params
            }
        }()

        var urlRequest = URLRequest(url: url)
        urlRequest.httpMethod = method.rawValue
        urlRequest.setValue("Bearer \(Self.apiKey)", forHTTPHeaderField: "Authorization")

        let encoding = URLEncoding.default
        return try encoding.encode(urlRequest, with: params)
    }
}
