//
//  PocketError.swift
//  TodoistTaskAdder
//
//  Created by Michael Charland on 2021-11-26.
//

import Foundation

enum PocketError: Error {
    case decodeIssue(_ message: String)
    case notAllTagged(_ links: [String])
    case todoistProjectNotFound(_ pocketProjectName: String)
    case todoistLabelNotFound(_ labelName: String)
}
