//
//  TodoistLabel.swift
//  TodoistTaskAdder
//
//  Created by Michael Charland on 2021-12-12.
//

import Foundation

// MARK: - TodoistLabel
struct TodoistLabel: Codable {
    let id: Int
    let name: String
    let order: Int
    let favorite: Bool
}
