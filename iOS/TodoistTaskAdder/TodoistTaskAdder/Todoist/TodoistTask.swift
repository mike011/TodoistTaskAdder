// This file was generated from JSON Schema using quicktype, do not modify it directly.
// To parse the JSON, add this file to your project and do:
//
//   let todoistTask = try? newJSONDecoder().decode(TodoistTask.self, from: jsonData)

import Foundation

// MARK: - TodoistTask
struct TodoistTask: Codable {
    let id: String
    let projectID: String
    let order: Int
    let content: String
    let todoistTaskDescription: String
    let completed: Bool
    let labelIDS: [String]
    let priority: Int
    let commentCount: Int
    let url: String

    enum CodingKeys: String, CodingKey {
        case id
        case projectID = "project_id"
        case order, content
        case todoistTaskDescription = "description"
        case completed = "is_completed"
        case labelIDS = "labels"
        case priority
        case commentCount = "comment_count"
        case url
    }
}
