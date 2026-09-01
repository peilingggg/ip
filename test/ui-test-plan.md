# Console UI Test Plan

Program command: `java -cp src/main/java Isa`

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
