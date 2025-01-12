// From: https://app.quicktype.io

// This file was generated from JSON Schema using quicktype, do not modify it directly.
// To parse the JSON, add this file to your project and do:
//
//   let pocketMyList = try? newJSONDecoder().decode(PocketMyList.self, from: jsonData)

import Foundation

// MARK: - PocketMyList
struct PocketMyList: Decodable {
    // let maxActions: Int
    // let cachetype: String
    let status: Int
    let complete, since: Int
    let pockedItems: [String: PockedItemResponse]

    enum CodingKeys: String, CodingKey {
        // case maxActions
       //  case cachetype
        case status
        case complete
        case since
        case pockedItems = "list"
    }
}

// MARK: - PockedItemResponse
struct PockedItemResponse: Decodable {
    let itemID, favorite, status, timeAdded: String
    let timeUpdated, timeRead, timeFavorited: String
    let sortID: Int
//     let tags: Tags
    let topImageURL: String?
    let resolvedID: String
    let givenURL: String
    let givenTitle, resolvedTitle: String
    let resolvedURL: String
    let excerpt, isArticle, isIndex, hasVideo: String
    let hasImage, wordCount: String
    // let lang: Lang
//     let timeToRead, listenDurationEstimate: Int

    enum CodingKeys: String, CodingKey {
        case itemID = "item_id"
        case favorite, status
        case timeAdded = "time_added"
        case timeUpdated = "time_updated"
        case timeRead = "time_read"
        case timeFavorited = "time_favorited"
        case sortID = "sort_id"
//        case tags
        case topImageURL = "top_image_url"
        case resolvedID = "resolved_id"
        case givenURL = "given_url"
        case givenTitle = "given_title"
        case resolvedTitle = "resolved_title"
        case resolvedURL = "resolved_url"
        case excerpt
        case isArticle = "is_article"
        case isIndex = "is_index"
        case hasVideo = "has_video"
        case hasImage = "has_image"
        case wordCount = "word_count"
       //  case lang
//         case timeToRead = "time_to_read"
//        case listenDurationEstimate = "listen_duration_estimate"
    }
}

enum Lang: String, Codable {
    case empty = ""
    case en = "en"
}

// MARK: - Tags
struct Tags: Decodable {
}
