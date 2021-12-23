// This file was generated from JSON Schema using quicktype, do not modify it directly.
// To parse the JSON, add this file to your project and do:
//
//   let PocketArchiveResponse = try? newJSONDecoder().decode(PocketArchiveResponse.self, from: jsonData)

import Foundation

// MARK: - PocketArchiveResponse
struct PocketArchiveResponse: Decodable {
    let actionResults: [Bool]
    let status: Int

    enum CodingKeys: String, CodingKey {
        case actionResults = "action_results"
        case status
    }
}
