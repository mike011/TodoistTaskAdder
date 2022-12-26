import Foundation

// This file was generated from JSON Schema using quicktype, do not modify it directly.
// To parse the JSON, add this file to your project and do:
//
//   let project = try? newJSONDecoder().decode(Project.self, from: jsonData)

import Foundation

// MARK: - TodoistProject
struct TodoistProject: Codable {
    let id: String
    let name: String
    let shared: Bool
    let favorite: Bool
    let url: String

    enum CodingKeys: String, CodingKey {
        case id, name
        case shared = "is_shared"
        case favorite = "is_favorite"
        case url
    }
}
