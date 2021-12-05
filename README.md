TodoistTaskAdder
================
Convert items stored in Pocket to Todoist

# Release

# 1.0 (current - doesn't work)
- In Java from Pocket it reads the items from the xml and emails all the tasks to Todoist
- xml export is not longer supported so this is no long works.

# 1.0 (Work around - doesn't work because of 2fa)
 - Using https://github.com/mike011/TableViewWithREST (iOS App) 
 - Download the tasks from Pocket
 - Print pocket tasks in `TodoistAdderForLists` format

# 1.1 (In progress)
- Create iOS or macOS app pulling in the appropriate parts from https://github.com/mike011/TableViewWithREST
- Be able to download the current tasks from Todoist
- Print pocket tasks in `TodoistAdderForLists` format

# 2.0
- Convert Java task uploader to Swift
- Use Todoist API to upload tasks
- Delete items from Pocket
