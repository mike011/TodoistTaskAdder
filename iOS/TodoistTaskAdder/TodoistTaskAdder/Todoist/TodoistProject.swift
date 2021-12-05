import Foundation

// This file was generated from JSON Schema using quicktype, do not modify it directly.
// To parse the JSON, add this file to your project and do:
//
//   let project = try? newJSONDecoder().decode(Project.self, from: jsonData)

import Foundation

// MARK: - TodoistProject
struct TodoistProject: Codable {
    let id, color: Int
    let name: String
    let commentCount: Int
    let shared, favorite: Bool
    let syncID: Int
    let inboxProject: Bool?
    let url: String
    let order: Int?

    enum CodingKeys: String, CodingKey {
        case id, color, name
        case commentCount = "comment_count"
        case shared, favorite
        case syncID = "sync_id"
        case inboxProject = "inbox_project"
        case url, order
    }
}
