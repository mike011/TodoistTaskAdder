//
//  TodoistLabel.swift
//  TodoistTaskAdder
//
//  Created by Michael Charland on 2021-12-12.
//

import Foundation

// MARK: - TodoistLabel
struct TodoistLabel: Codable {
    let id: String
    let name: String
    let color: String
    let order: Int
}
