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

## Test case: Add and list tasks

Aim: Verify that entered task descriptions are stored and listed in order as not done.

### Inputs
```text
read book
return book
list
bye
```

### Expected output
```text
Helloo! I'm Isa
How can I help you?
____________________________________________________________
____________________________________________________________
 added: read book
____________________________________________________________
____________________________________________________________
 added: return book
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[ ] read book
 2.[ ] return book
____________________________________________________________
____________________________________________________________
Bye. Hope you have a nice day!
____________________________________________________________
```

## Test case: Mark and unmark a task

Aim: Verify that `mark` and `unmark` reverse a task's done status.

### Inputs
```text
read book
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
 added: read book
____________________________________________________________
____________________________________________________________
 Nice! I've marked this task as done:
   [X] read book
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[X] read book
____________________________________________________________
____________________________________________________________
 OK, I've marked this task as not done yet:
   [ ] read book
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[ ] read book
____________________________________________________________
____________________________________________________________
Bye. Hope you have a nice day!
____________________________________________________________
```
