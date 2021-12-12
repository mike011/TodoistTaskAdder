//
//  TodoistTaskToAdd.swift
//  TodoistTaskAdder
//
//  Created by Michael Charland on 2021-12-05.
//

import Foundation

struct TodoistTaskToAdd {
    let projectID: Int? = nil
    let projectName: String
    let title: String
    let labelIDs: [Int]
    let dueDate: Date? = nil
}
