//
//  TodoistConverter.swift
//  TodoistTaskAdder
//
//  Created by Michael Charland on 2021-12-12.
//

import Foundation

struct TodoistTaskConverter {
    let pocketedItem: PocketedItem
    let todoistLabels: [TodoistLabel]

    func getTodoistTask() throws -> TodoistTaskToAdd {
        let project = try getTodoistProjectName()
        let labels = try getLabels()
        let task = TodoistTaskToAdd(projectName: project, title: pocketedItem.link, labelIDs: labels)
        return task
    }

    func getTodoistProjectName() throws -> String {
        let pocketProjectName = getPocketProjectName()
        if let projectName = lookUpTodoistProject(from: pocketProjectName) {
            return projectName
        }
        print(PocketError.todoistProjectNotFound(pocketProjectName))
        return "Inbox"
    }

    func getPocketProjectName() -> String { 
        return pocketedItem.tags.filter { tag in
            return !tag.contains("@")
        }.first!
    }

    func lookUpTodoistProject(from key: String) -> String? {
        return pocketTodoistProjectMapping[key]
    }

    private var pocketTodoistProjectMapping: [String: String] {
        var pocketToTodoist = [String: String]()
        pocketToTodoist["cycling"] = "Cycling"
        pocketToTodoist["dev"] = "Dev"
        pocketToTodoist["exercise"] = "Exercise"
        pocketToTodoist["ios"] = "ios"
        pocketToTodoist["personal"] = "Personal"
        pocketToTodoist["rouvy"] = "Rouvy"
        pocketToTodoist["runkeeper"] = "Runkeeper"
        pocketToTodoist["running"] = "Running"
        pocketToTodoist["arduino"] = "Arduino"
        pocketToTodoist["race"] = "Signed up for Races"
        pocketToTodoist["races"] = "Signed up for Races"
        pocketToTodoist["racing"] = "Signed up for Races"
        pocketToTodoist["refactoring"] = "Refactoring"
        pocketToTodoist["food"] = "Food"
        pocketToTodoist["family"] = "Family"
        pocketToTodoist["family"] = "Family"
        pocketToTodoist["italy"] = "Italy 2023"
        pocketToTodoist["work"] = "Work"
        pocketToTodoist["Inbox"] = "Inbox"
        return pocketToTodoist
    }

    func getLabels() throws -> [String] {
        let pocketLabels = getPocketLabels()
        let todoistIDs = try lookUpInTodoist(pocketedlabels: pocketLabels)
        return todoistIDs
    }

    func getPocketLabels() -> [String]  {
        return pocketedItem.tags.filter { tag in
            return tag.contains("@")
        }.map { tag in
            return String(tag.dropFirst())
        }
    }

    func lookUpInTodoist(pocketedlabels: [String]) throws -> [String] {
        var ids = [String]()
        for label in pocketedlabels {
            let filteredLabels = todoistLabels.filter({ todoistLabel in
                todoistLabel.name == label
            })
            if filteredLabels.count != 1 {
                throw PocketError.todoistLabelNotFound(label)
            } else {
                ids.append(filteredLabels.first!.name)
            }
        }
        return ids
    }
}
