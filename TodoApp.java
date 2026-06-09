import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoApp {
    private static final String FILE_NAME = "tasks.txt";

    public static void main(String[] args) { 
        Scanner keyboard = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();
        
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

        System.out.println("--- Todo App ---");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add task");
            System.out.println("2. View tasks");
            System.out.println("3. Delete a task"); 
            System.out.println("4. Delete ALL tasks"); 
            System.out.println("5. Save and Exit");
    
            System.out.print("Enter your choice: ");
            String choice = keyboard.nextLine(); 
            
            if (choice.equals("1")) {
                System.out.print("Enter the task: ");
                String task = keyboard.nextLine();
                tasks.add(task);
                System.out.println("Task added.");

            } else if (choice.equals("2")) {
                System.out.println("\nYour tasks:");
                if (tasks.isEmpty()) {
                    System.out.println("Your list is empty.");
                } else {
                    for (int zero = 0; zero < tasks.size(); zero++) {
                        System.out.println((zero + 1) + ". " + tasks.get(zero));
                    }
                }

            } else if (choice.equals("3")) {
                if (tasks.isEmpty()) {
                    System.out.println("Nothing to delete. Your list is empty.");
                } else {
                    System.out.print("Enter the number of the task to delete: ");
                    String input = keyboard.nextLine(); 
                    
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

            } else if (choice.equals("4")) {
                if (tasks.isEmpty()) {
                    System.out.println("Your list is already empty.");
                } else {
                    System.out.print("Are you sure you want to delete ALL tasks? (yes/no): ");
                    String confirm = keyboard.nextLine();
                    if (confirm.equalsIgnoreCase("yes")) {
                        tasks.clear(); 
                        System.out.println("All tasks have been deleted.");
                    } else {
                        System.out.println("Deletion canceled.");
                    }
                }
                
            } else if (choice.equals("5")) {
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

                System.out.println("app closing...");
                break;

            } else {
                System.out.println("Invalid choice. Please try choose again.");
            }
        }
        keyboard.close();
    }
}