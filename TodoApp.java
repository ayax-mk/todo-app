import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoApp {
    public static final String VERSION = "1.1.0";
    private static final String FILE_NAME = "tasks.txt";
    private static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) { 
        ArrayList<String> tasks = new ArrayList<>();
        loadTasksFromFile(tasks);

        System.out.println("--- Todo App ---");

        while (true) {
            printMenu();
            System.out.print("Enter your choice: ");
            String choice = keyboard.nextLine(); 
            
            if (choice.equals("1")) {
                addTask(tasks);
            } else if (choice.equals("2")) {
                viewTasks(tasks);
            } else if (choice.equals("3")) {
                editTask(tasks);
            } else if (choice.equals("4")) {
                deleteTask(tasks);
            } else if (choice.equals("5")) {
                searchTasks(tasks);
            } else if (choice.equals("6")) {
                deleteAllTasks(tasks);
            } else if (choice.equals("7")) {
                saveTasksToFile(tasks);
                System.out.println("app closing...");
                break;
            } else {
                System.out.println("Invalid choice. Please try choose again.");
            }
        }
        keyboard.close();
    }

    private static void printMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1. Add task");
        System.out.println("2. View tasks");
        System.out.println("3. Edit a task"); 
        System.out.println("4. Delete a task"); 
        System.out.println("5. Search tasks"); 
        System.out.println("6. Delete ALL tasks"); 
        System.out.println("7. Save and Exit");
    }

    private static void addTask(ArrayList<String> tasks) {
        System.out.print("Enter the task: ");
        String task = keyboard.nextLine();
        if (task.isEmpty()) {
            System.out.println("Cannot add an empty task.");
            return;
        }
        tasks.add(task);
        System.out.println("Task added.");
    }

    private static void viewTasks(ArrayList<String> tasks) {
        System.out.println("\nYour tasks:");
        if (tasks.isEmpty()) {
            System.out.println("Your list is empty.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void editTask(ArrayList<String> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Nothing to edit. Your list is empty.");
            return;
        }
        
        System.out.print("Enter the number of the task to edit (or press Enter to go back): ");
        String input = keyboard.nextLine(); 
        
        if (input.isEmpty()) {
            System.out.println("Returning to main menu.");
            return;
        }
        
        try {
            int taskNumber = Integer.parseInt(input); 
            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                String oldText = tasks.get(taskNumber - 1);
                System.out.println("Current text: \"" + oldText + "\"");
                
                System.out.println("How do you want to edit?");
                System.out.println("1. Rewrite the whole task");
                System.out.println("2. Replace a specific part (first match only)");
                System.out.print("Choose edit mode (or press Enter to go back): ");
                String mode = keyboard.nextLine();
                
                if (mode.isEmpty()) {
                    System.out.println("Returning to main menu.");
                    return;
                }
                
                if (mode.equals("1")) {
                    System.out.print("Enter the completely new text (or press Enter to cancel): ");
                    String newFullText = keyboard.nextLine();
                    if (!newFullText.isEmpty()) {
                        tasks.set(taskNumber - 1, newFullText);
                        System.out.println("Task updated!");
                    } else {
                        System.out.println("Changes canceled.");
                    }
                } else if (mode.equals("2")) {
                    System.out.print("Enter the exact part you want to change (or press Enter to cancel): ");
                    String target = keyboard.nextLine();
                    
                    if (target.isEmpty()) {
                        System.out.println("Changes canceled.");
                    } else if (!oldText.contains(target)) {
                        System.out.println("Error: That text was not found in the task!");
                    } else {
                        System.out.print("Enter the new text for that part: ");
                        String replacement = keyboard.nextLine();
                        String newText = oldText.replaceFirst(java.util.regex.Pattern.quote(target), replacement);
                        tasks.set(taskNumber - 1, newText);
                        System.out.println("Task updated to: \"" + newText + "\"");
                    }
                } else {
                    System.out.println("Invalid mode selection.");
                }
            } else {
                System.out.println("Invalid task number. Check your list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number, not letters!");
        }
    }

    private static void deleteTask(ArrayList<String> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Nothing to delete. Your list is empty.");
            return;
        }

        System.out.print("Enter the number of the task to delete (or press Enter to go back): ");
        String input = keyboard.nextLine();

        if (input.isEmpty()) {
            System.out.println("Returning to main menu.");
            return;
        }

        try {
            int taskNumber = Integer.parseInt(input);
            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                String removedTask = tasks.remove(taskNumber - 1);
                System.out.println("Deleted: \"" + removedTask + "\"");
            } else {
                System.out.println("Invalid task number. Check your list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number, not letters!");
        }
    }

    private static void searchTasks(ArrayList<String> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Your list is empty. Nothing to search.");
            return;
        }
        System.out.print("Enter keyword to search for (or press Enter to go back): ");
        String keyword = keyboard.nextLine();
        
        if (keyword.isEmpty()) {
            System.out.println("Returning to main menu.");
            return;
        }
        
        System.out.println("\nSearch results:");
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println((i + 1) + ". " + tasks.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks found containing \"" + keyword + "\".");
        }
    }

    private static void deleteAllTasks(ArrayList<String> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Your list is already empty.");
            return;
        }
        System.out.print("Are you sure you want to delete ALL tasks? (yes/no): ");
        String confirm = keyboard.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            tasks.clear(); 
            System.out.println("All tasks have been deleted.");
        } else {
            System.out.println("Deletion canceled.");
        }
    }

    private static void loadTasksFromFile(ArrayList<String> tasks) {
        try {
            File file = new File(FILE_NAME);
            if (file.exists()) {
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    tasks.add(fileScanner.nextLine());
                }
                fileScanner.close();
                System.out.println("SUCCESS: Your old tasks have been loaded from " + FILE_NAME);
            }
        } catch (IOException e) {
            System.out.println("ERROR: Failed to load saved tasks.");
        }
    }

    private static void saveTasksToFile(ArrayList<String> tasks) {
        try {
            FileWriter writer = new FileWriter(FILE_NAME);
            for (String t : tasks) {
                writer.write(t + "\n");
            }
            writer.close();
            System.out.println("Tasks saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
        }
    }
}
