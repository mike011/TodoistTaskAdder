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

    enum CodingKeys: String, CodingKey {
        case projectID = "project_id"
        case projectName
        case title
        case labelIDS = "label_ids"
        case dueDate = "due_date"
    }
}
