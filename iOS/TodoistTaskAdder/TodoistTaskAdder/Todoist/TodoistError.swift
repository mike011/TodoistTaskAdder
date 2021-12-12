//
//  TodoistError.swift
//  TodoistTaskAdder
//
//  Created by Michael Charland on 2021-12-05.
//

import Foundation

enum TodoistError: Error {
    case decodeIssue(_ message: String)
    case projectNameNotFound(name: String)
}
