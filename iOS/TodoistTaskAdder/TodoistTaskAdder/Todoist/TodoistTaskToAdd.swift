//
//  TodoistTaskToAdd.swift
//  TodoistTaskAdder
//
//  Created by Michael Charland on 2021-12-05.
//

import Foundation

struct TodoistTaskToAdd {
    let projectID: String? = nil
    let projectName: String
    let title: String
    var labelIDs: [String]
    let dueDate: Date? = nil

    enum CodingKeys: String, CodingKey {
        case projectID = "project_id"
        case projectName
        case title
        case labelIDS = "labels"
        case dueDate = "due_date"
    }
}
