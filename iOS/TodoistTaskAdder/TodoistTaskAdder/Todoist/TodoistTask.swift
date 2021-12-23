// This file was generated from JSON Schema using quicktype, do not modify it directly.
// To parse the JSON, add this file to your project and do:
//
//   let todoistTask = try? newJSONDecoder().decode(TodoistTask.self, from: jsonData)

import Foundation

// MARK: - TodoistTask
struct TodoistTask: Codable {
    let id: Int
    let assigner: Int
    let projectID: Int
    let sectionID: Int
    let order: Int
    let content: String
    let todoistTaskDescription: String
    let completed: Bool
    let labelIDS: [Int]
    let priority: Int
    let commentCount: Int
    let creator: Int
    let created: String
    let url: String

    enum CodingKeys: String, CodingKey {
        case id, assigner
        case projectID = "project_id"
        case sectionID = "section_id"
        case order, content
        case todoistTaskDescription = "description"
        case completed
        case labelIDS = "label_ids"
        case priority
        case commentCount = "comment_count"
        case creator, created, url
    }
}
