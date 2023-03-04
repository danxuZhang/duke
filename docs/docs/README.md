# User Guide

Project Duke is a personal assistant chat-bot that helps a person tracks his various tasks.

Currently, the user can track there todos, deadlines and events using Duke.

* [Features](#Features)
* [Usage](#usage)
  * [`help`](#help---show-help-information)
  * [`list`](#list---list-all-tasks)
  * [`todo`](#todo---add-a-todo-task)
  * [`deadline`](#deadline---add-a-deadline-task)
  * [`event`](#event---add-an-event-task)
  * [`find`](#find---find-tasks-by-keyword)
  * [`mark`](#mark---mark-task--s--as-done)
  * [`unmark`](#unmark---unmark-task--s--as-not-done-yet)
  * [`delete`](#delete---delete-task)
  * [`bye`](#bye---exit-the-application)


## Features 

### Manage and Track Tasks

The user can track all their added tasks including todos, deadlines and events with their complete status and dates.


## Usage

### `help` - Show help information

Display all acceptable commands and their usages.

Example of usage:

`help`

Expected outcome:

All acceptable commands and their usages.

```
     Here are some sample usages: 

	 todo: add a todo task
	 deadline: add a deadline task with by time
	 event: add an event task with start and to time
	 list: Display all tasks in the list with their indices
	 find: find a task by searching for a keyword
	 mark: mark task(s) to be done
	 unmark: unmark task(s)
	 delete: delete a task from the task list
	 help: Show program usage instructions
	 bye: show available commands and their usages
```


### `list` - List all tasks 

List all tasks in the task list.  

Example of usage: 

`list`

Expected outcome:

All tasks in the task list.

```
	 Here are the tasks in your list:

	 - 1. [D][ ] hello (by: Feb 22 2023)
	 - 2. [E][ ] task (from: Feb 26 2023, to: Feb 27 2023)
	 - 3. [T][ ] hello world
	 - 4. [E][X] destroy the world (from: Jan 1 2023, to: Jan 1 2024)
```


### `todo` - Add a todo task

Add a todo task with its description

Example of usage:

`todo watch formula one race`

Expected outcome:

An echo of the added todo task, and current number of tasks in the list.

```
	 Got it. I've added this task:

	 [T][ ] watch formula one race

	 Now you have 1 tasks in the list.
```


### `deadline` - Add a deadline task

Add a deadline task with its description, by date.

Example of usage:

`deadline eat lunch /by 31/07/2023`

Expected outcome:

An echo of the added deadline task, and current number of tasks in the list.

```
	 Got it. I've added this task:

	 [D][ ] eat lunch (by: Jul 31 2023)

	 Now you have 2 tasks in the list.
```


### `event` - Add an event task

Add an event task with its description, from date, to date.

Example of usage:

`event Hokkaido trip /from 01/01/2023 /to 07/01/2023`

Expected outcome:

An echo of the added event task, and the current number of tasks in the list.

```
	 Got it. I've added this task:

	 [E][ ] Hokkaido trip (from: Jan 1 2023, to: Jan 7 2023)

	 Now you have 3 tasks in the list.
```


### `find` - Find tasks by keyword

List all tasks that contains the input keyword.

Example of usage:

`find eat`

Expected outcome:

A list of all tasks that contains the input keyword.

```
	 Here are the matching tasks in your list:

	 - 2. [D][ ] eat lunch (by: Jul 31 2023)
	 - 4. [T][ ] eat dinner
	 - 5. [T][ ] eat breakfast 
```


### `mark` - Mark task(s) as done
Mark one or more tasks by entering their indexes.

Example of usage:

`mark 1 2`

Expected outcome:

Messages that the task has been marked as done.

```
	 Nice! I've marked this task as done:

	 [T][X] watch formula one race

	 Nice! I've marked this task as done:

	 [D][X] eat lunch (by: Jul 31 2023)
```


### `unmark` - Unmark task(s) as not done yet
Unmark one or more tasks by entering their indexes.

Example of usage:

`unmark 1 2`

Expected outcome:

Messages that the task has been marked as done.

```
	 OK, I've marked this task as not done yet:

	 [T][ ] watch formula one race

	 OK, I've marked this task as not done yet:

	 [D][ ] eat lunch (by: Jul 31 2023)
```


### `delete` - Delete task

Delete a task at the given index.

Example of usage:

`delete 2`

Expected outcome:

Messages that the task has been deleted and the current number of tasks in the list. 

```
	 Noted. I've removed this task:

	 [D][ ] eat lunch (by: Jul 31 2023)

	 Now you have 4 tasks in the list.
```


### `bye` - Exit the application

Exit the running application.

Example of usage:

`bye`

Expected outcome:

A goodbye messages.

```
	 Exiting the application as requested... 
```


