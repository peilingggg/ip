# Console UI Test Plan

Program command: `java -cp out isa.ui.Isa`
Data file: `data/isa.txt`

Run the tests from the repository root using the `test-ui` skill. Each test
starts with a new in-memory task list.

## Test case: Exit the program

Aim: Verify that `bye` displays the farewell message and exits.

### Inputs
```text
bye
```

### Expected output
```text
Helloo! I'm Isa
How can I help you?
____________________________________________________________
____________________________________________________________
Bye. Hope you have a nice day!
____________________________________________________________
```

## Test case: Save tasks after changes

Aim: Verify that saving added and marked tasks does not change the console output.

### Inputs
```text
todo read book
deadline return book /by June 6th
event project meeting /from Aug 6th 2pm /to 4pm
mark 1
bye
```

### Expected output
```text
Helloo! I'm Isa
How can I help you?
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [T][ ] read book
 Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [D][ ] return book (by: June 6th)
 Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
 Now you have 3 tasks in the list.
____________________________________________________________
____________________________________________________________
 Nice! I've marked this task as done:
   [T][X] read book
____________________________________________________________
____________________________________________________________
Bye. Hope you have a nice day!
____________________________________________________________
```

## Test case: Add and list todos

Aim: Verify that `todo` adds typed tasks and `list` displays them in order.

### Inputs
```text
todo read book
todo join sports club
list
bye
```

### Expected output
```text
Helloo! I'm Isa
How can I help you?
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [T][ ] read book
 Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [T][ ] join sports club
 Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] read book
 2.[T][ ] join sports club
____________________________________________________________
____________________________________________________________
Bye. Hope you have a nice day!
____________________________________________________________
```

## Test case: Mark and unmark a todo

Aim: Verify that `mark` and `unmark` reverse a todo's done status.

### Inputs
```text
todo read book
mark 1
list
unmark 1
list
bye
```

### Expected output
```text
Helloo! I'm Isa
How can I help you?
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [T][ ] read book
 Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
 Nice! I've marked this task as done:
   [T][X] read book
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[T][X] read book
____________________________________________________________
____________________________________________________________
 OK, I've marked this task as not done yet:
   [T][ ] read book
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] read book
____________________________________________________________
____________________________________________________________
Bye. Hope you have a nice day!
____________________________________________________________
```

## Test case: Add deadlines with flexible dates

Aim: Verify that deadline descriptions and arbitrary `/by` values are stored.

### Inputs
```text
deadline return book /by Sunday
deadline do homework /by no idea :-p
list
bye
```

### Expected output
```text
Helloo! I'm Isa
How can I help you?
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [D][ ] return book (by: Sunday)
 Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [D][ ] do homework (by: no idea :-p)
 Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[D][ ] return book (by: Sunday)
 2.[D][ ] do homework (by: no idea :-p)
____________________________________________________________
____________________________________________________________
Bye. Hope you have a nice day!
____________________________________________________________
```

## Test case: Add an event

Aim: Verify that an event stores and displays its `/from` and `/to` values.

### Inputs
```text
event project meeting /from Mon 2pm /to 4pm
list
bye
```

### Expected output
```text
Helloo! I'm Isa
How can I help you?
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [E][ ] project meeting (from: Mon 2pm to: 4pm)
 Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[E][ ] project meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
____________________________________________________________
Bye. Hope you have a nice day!
____________________________________________________________
```

## Test case: Reject empty todos without changing the task list

Aim: Verify that empty todo descriptions are rejected and do not add tasks,
including when the command contains trailing spaces. Valid list operations are
interleaved to check the task list after each invalid input.

### Inputs
```text
todo read book
todo
list
todo    
list
bye
```

### Expected output
```text
Helloo! I'm Isa
How can I help you?
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [T][ ] read book
 Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
 please enter a todo!
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] read book
____________________________________________________________
____________________________________________________________
 please enter a todo!
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] read book
____________________________________________________________
____________________________________________________________
Bye. Hope you have a nice day!
____________________________________________________________
```

## Test case: Reject unknown commands without changing the task list

Aim: Verify that unknown commands and commands that merely begin with a known
command name are rejected. Valid additions and list operations are interleaved
to confirm that rejected commands do not alter existing tasks.

### Inputs
```text
todo first task
blah
list
todolist
list
todo second task
list
bye
```

### Expected output
```text
Helloo! I'm Isa
How can I help you?
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [T][ ] first task
 Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
 i don't understand :((
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] first task
____________________________________________________________
____________________________________________________________
 i don't understand :((
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] first task
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [T][ ] second task
 Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] first task
 2.[T][ ] second task
____________________________________________________________
____________________________________________________________
Bye. Hope you have a nice day!
____________________________________________________________
```

## Test case: Load saved tasks

Aim: Verify that valid todo, deadline, and event records are restored with
their saved completion statuses.

### Initial data
```text
T | 1 | read book
D | 0 | return book | June 6th
E | 0 | project meeting | Aug 6th 2pm | 4pm
```

### Inputs
```text
list
bye
```

### Expected output
```text
Helloo! I'm Isa
How can I help you?
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[T][X] read book
 2.[D][ ] return book (by: June 6th)
 3.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
____________________________________________________________
____________________________________________________________
Bye. Hope you have a nice day!
____________________________________________________________
```

## Test case: Skip malformed saved tasks

Aim: Verify that malformed records produce warnings while valid records still
load and remain usable.

### Initial data
```text
T | 1 | valid task
T | 2 | wrong status
D | 0 | missing date
Z | 0 | unknown type
broken

```

### Inputs
```text
list
bye
```

### Expected output
```text
Helloo! I'm Isa
How can I help you?
____________________________________________________________
 Warning: skipped saved task on line 2: status must be 0 or 1
 Warning: skipped saved task on line 3: expected 4 fields but found 3
 Warning: skipped saved task on line 4: unknown task type 'Z'
 Warning: skipped saved task on line 5: record does not contain a type and status
____________________________________________________________
 Here are the tasks in your list:
 1.[T][X] valid task
____________________________________________________________
____________________________________________________________
Bye. Hope you have a nice day!
____________________________________________________________
```

## Test case: Load escaped task data

Aim: Verify that escaped separators and backslashes are restored correctly.

### Initial data
```text
T | 0 | use \| separator and \\ slash
```

### Inputs
```text
list
bye
```

### Expected output
```text
Helloo! I'm Isa
How can I help you?
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] use | separator and \ slash
____________________________________________________________
____________________________________________________________
Bye. Hope you have a nice day!
____________________________________________________________
```

## Test case: Delete and save a task

Aim: Verify that deleting a task updates the displayed task list and its saved
data.

### Initial data
```text
T | 0 | read book
D | 0 | return book | Friday
```

### Inputs
```text
delete 1
list
bye
```

### Expected output
```text
Helloo! I'm Isa
How can I help you?
____________________________________________________________
____________________________________________________________
 Noted. I've removed this task:
   [T][ ] read book
 Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[D][ ] return book (by: Friday)
____________________________________________________________
____________________________________________________________
Bye. Hope you have a nice day!
____________________________________________________________
```
