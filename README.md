Advanced Todo Application

Welcome!

**Current Version:** 1.1.0

Features

* **Add Tasks:** Quick and easy creation of new todo items.
* **View Tasks:** Displays your complete numbered list of tasks dynamically.
* **Smart Editing:** Offers two modes — rewrite the entire task or intelligently replace only a specific part (first match) without retyping everything.
* **Keyword Search:** High-performance search system that instantly filters tasks containing your keyword (case-insensitive).
* **Smart Cancellation:** Type empty space and hit Enter at any stage (Search, Edit, Delete) to safely cancel the action and return to the main menu.
* **Flexible Deletion:** Delete a single task by its number or wipe the entire list with a built-in safety check confirmation (`yes`/`no`).
* **Data Persistence:** Automatically saves all your tasks into a local `tasks.txt` file on exit and automatically restores them when you reboot the app.
* **Robust Input Validation:** Advanced custom error handling ensures the application never crashes, even if letters or invalid numbers are typed by mistake.

Clean Architecture & Technologies Used

* **Language:** Java
* **Data Structures:** `ArrayList` for dynamic memory and task storage.
* **Clean Code Concepts:** Code refactoring into modular static methods, following the **DRY (Don't Repeat Yourself)** principle.
* **File I/O:** `FileWriter` and `Scanner` for seamless data storage management.
* **Exception Handling:** Custom `try-catch` blocks and validation methods to prevent system failures.
* **String Manipulation:** Advanced use of `.replaceFirst()` with regex pattern quoting for bug-free partial updates.

How to Run and Use

1. Make sure you have **Java JDK** installed on your machine.
2. Clone or download the `TodoApp.java` file.
3. Open your terminal, navigate to the folder with the file, and compile it:
```bash
   javac TodoApp.java
