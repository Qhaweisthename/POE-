package task1;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import static task1.Task1.Task.deleteTaskByName;
import static task1.Task1.Task.displayLongestTaskDuration;
import static task1.Task1.Task.searchTaskByName;
import static task1.Task1.Task.searchTasksByDeveloper;

 public class Task1 {
    private static int numTasks;
    private static int totalHours;
    private static String usernameREG;
    private static String passwordREG;
    public static ArrayList<String> developerList = new ArrayList<>();
    public static ArrayList<String> taskNamesList = new ArrayList<>();
    private static ArrayList<String> taskIDList = new ArrayList<>();
    private static ArrayList<Integer> taskDurationList = new ArrayList<>();
    private static ArrayList<String> taskStatusList = new ArrayList<>();

    public static void main(String[] args) {
        welcomeMSG();
        Register();
        login();
    }

    public static void welcomeMSG() {
        JOptionPane.showMessageDialog(null, "Welcome to Task 1 - login & reg feature\n============================================");
    }

    public static void login() {
        while (true) {
            String username = JOptionPane.showInputDialog("Log into your Account here\nEnter your username:");
            String password = JOptionPane.showInputDialog("Enter your password:");

            if (username.equals(usernameREG) && password.equals(passwordREG)) {
                JOptionPane.showMessageDialog(null, "Login successful!");
                part2();
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password.\n\n" +
                        "***********************************Try again***********************************\n\n" +
                        "(Type <2> to have another attempt )\n" +
                        "Or contact admin by typing '1'\n\n");
                String choice = JOptionPane.showInputDialog(null, "Enter your choice:");
                if (choice.equals("1")) {
                    admin();
                } else {
                    if (choice.equals("2")) {
                        login();
                    }
                }
            }
        }
    }

    public static void Register() {
        JOptionPane.showMessageDialog(null, "You are going to register first then Login after");

        JOptionPane.showMessageDialog(null, "****************** Enter your first name ************************");
        String firstName = JOptionPane.showInputDialog(null);
        JOptionPane.showMessageDialog(null, "****************** Now Enter your Last name ************************");
        String lastName = JOptionPane.showInputDialog(null);
        JOptionPane.showMessageDialog(null, "Nice to meet you " + firstName + " " + lastName + "\n\n");

        JOptionPane.showMessageDialog(null, "Now you must create an account");

        boolean validUsername = false;
        while (!validUsername) {
            JOptionPane.showMessageDialog(null, "****************** Create A Username ************************");
            usernameREG = JOptionPane.showInputDialog(null);
            JOptionPane.showMessageDialog(null, "Your username is " + usernameREG);

            if (usernameREG.length() <= 5 && usernameREG.contains("_")) {
                JOptionPane.showMessageDialog(null, "Username successfully captured!");
                validUsername = true;
            } else {
                JOptionPane.showMessageDialog(null, "Username is not correctly formatted. Please ensure that your username contains an underscore and is no more than 5 characters in length.");
                JOptionPane.showMessageDialog(null, "You must re-enter your username now.");
            }
        }

        boolean validPassword = false;
        while (!validPassword) {
            JOptionPane.showMessageDialog(null, "****************** Create A Password ************************");
            passwordREG = JOptionPane.showInputDialog(null);
            JOptionPane.showMessageDialog(null, "Your password is " + passwordREG);

            if (passwordREG.length() >= 8 && passwordREG.matches(".*[A-Z].*") && passwordREG.matches(".*[0-9].*") && passwordREG.matches(".*[!@#$%^&*()<>,./?';:}{\\]\\[+_-].*")) {
                JOptionPane.showMessageDialog(null, "Password successfully captured!");
                JOptionPane.showMessageDialog(null, "Your Account has been successfully created\n\n");
                validPassword = true;
            } else {
                JOptionPane.showMessageDialog(null, "Password is not correctly formatted. Please ensure that your password contains at least 8 characters, including an uppercase letter, a number, and a special character.");
                JOptionPane.showMessageDialog(null, "You must re-enter your password now.");
            }
        }
    }

   public static void part2() {
        JOptionPane.showMessageDialog(null, "********************** Welcome to EasyKanBan ************************");

        while (true) {
            String choice = JOptionPane.showInputDialog(null, "Enter your choice:\n\n" +
                    "1 - Add a task\n" +
                    "2 - Show reports\n" +
                    "3 - Display Done Task \n" +
                    "4 - Display Longest Task duration\n" +
                    "5 - Search for Task name \n" +
                    "6 - Search developer name\n" +
                    "7 - Delete a task\n" +
                    "0 - Quit");

            if (choice != null) {
                switch (choice) {
                    case "1":
                        addTasks();
                        break;
                    case "2":
                        showReports();
                        break;
                    case "3":
                        displayDoneTasks();
                        break;
                    case "4":
                        displayLongestTaskDuration();
                        break;
                    case "5":
                        String searchTaskName = JOptionPane.showInputDialog("Enter the task name to search:");
                        searchTaskByName(searchTaskName);
                        break;
                    case "6":
                        String searchDeveloper = JOptionPane.showInputDialog("Enter the developer name to search:");
                        searchTasksByDeveloper(searchDeveloper);
                        break;
                    case "7":
                        String deleteTaskName = JOptionPane.showInputDialog("Enter the task name to delete:");
                        deleteTaskByName(deleteTaskName);
                        break;
                    case "0":
                        quit();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                        break;
                }
            }
        }
    }

    public static void addTasks() {
        String numTasksStr = JOptionPane.showInputDialog("Enter the number of tasks you want to add:");
        numTasks = Integer.parseInt(numTasksStr);

        for (int i = 0; i < numTasks; i++) {
            Task task = new Task();

            String taskName = JOptionPane.showInputDialog("Enter task name:");
            task.setTaskName(taskName);

            String taskDescription = JOptionPane.showInputDialog("Enter task description:");
            task.setTaskDescription(taskDescription);

            String developerDetails = JOptionPane.showInputDialog("Enter developer details:");
            task.setDeveloperDetails(developerDetails);

            String durationStr = JOptionPane.showInputDialog("Enter task duration in hours:");
            int duration = Integer.parseInt(durationStr);
            task.setDuration(duration);

            String status = JOptionPane.showInputDialog("Enter task status (To Do, Doing, Done):");
            task.setStatus(status);

            developerList.add(developerDetails);
            taskNamesList.add(taskName);
            taskIDList.add(task.generateTaskID());
            taskDurationList.add(duration);
            taskStatusList.add(status);

            task.printTaskDetails();
            totalHours += duration;
        }
    }

    public static void showReports() {
        StringBuilder report = new StringBuilder("Task Report:\n\n");

        for (int i = 0; i < taskNamesList.size(); i++) {
            report.append("Task ").append(i + 1).append(":\n");
            report.append("Task Name: ").append(taskNamesList.get(i)).append("\n");
            report.append("Developer: ").append(developerList.get(i)).append("\n");
            report.append("Duration: ").append(taskDurationList.get(i)).append(" hours\n");
            report.append("Status: ").append(taskStatusList.get(i)).append("\n");
            report.append("---------------------------------------------\n");
        }

        JOptionPane.showMessageDialog(null, report.toString());
    }
    
    public static void displayDoneTasks() {
    StringBuilder output = new StringBuilder("Done Tasks:\n");
    for (int i = 0; i < taskNamesList.size(); i++) {
        if (taskStatusList.get(i).equalsIgnoreCase("Done")) {
            output.append("Developer: ").append(developerList.get(i))
                    .append(", Task Name: ").append(taskNamesList.get(i))
                    .append(", Duration: ").append(taskDurationList.get(i)).append(" hours")
                    .append("\n");
        }
    }
    JOptionPane.showMessageDialog(null, output.toString());
}

    public static void quit() {
        JOptionPane.showMessageDialog(null, "Thank you for using Task 1 - login & reg feature.\nGoodbye!");
        System.exit(0);
    }

    public static void admin() {
        JOptionPane.showMessageDialog(null, "Contacting admin...");
    }

    public static void resetAccount() {
        usernameREG = null;
        passwordREG = null;
    }

    static class Task {
        private String taskName;
        private String taskDescription;
        private String developerDetails;
        private int duration;

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public void setTaskDescription(String taskDescription) {
            this.taskDescription = taskDescription;
        }

        public void setDeveloperDetails(String developerDetails) {
            this.developerDetails = developerDetails;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public void setStatus(String status) {
            taskStatusList.add(status);
        }

        public void printTaskDetails() {
            JOptionPane.showMessageDialog(null, "Task Details:\n" +
                    "Task Name: " + taskName + "\n" +
                    "Task Description: " + taskDescription + "\n" +
                    "Developer Details: " + developerDetails + "\n" +
                    "Duration: " + duration + " hours\n" +
                    "Status: " + taskStatusList.get(taskStatusList.size() - 1) + "\n");
        }

        public String generateTaskID() {
            int id = taskIDList.size() + 1;
            return "T" + id;
        }
    
    


     
public static void displayLongestTaskDuration() {
    int maxDuration = 0;
    int longestTaskIndex = -1;

    for (int i = 0; i < taskNamesList.size(); i++) {
        if (taskDurationList.get(i) > maxDuration) {
            maxDuration = taskDurationList.get(i);
            longestTaskIndex = i;
        }
    }

    if (longestTaskIndex != -1) {
        String longestTaskName = taskNamesList.get(longestTaskIndex);
        int longestTaskDuration = taskDurationList.get(longestTaskIndex);

        JOptionPane.showMessageDialog(null, "Longest Task Duration:\n" +
                "Task Name: " + longestTaskName + "\n" +
                "Duration: " + longestTaskDuration + " hours");
    } else {
        JOptionPane.showMessageDialog(null, "No tasks found.");
    }
}
 

public static void searchTaskByName(String taskName) {
    StringBuilder output = new StringBuilder("Search Results:\n");
    boolean found = false;

    for (int i = 0; i < taskNamesList.size(); i++) {
        if (taskNamesList.get(i).equalsIgnoreCase(taskName)) {
            output.append("Developer: ").append(developerList.get(i))
                    .append(", Task Name: ").append(taskNamesList.get(i))
                    .append(", Duration: ").append(taskDurationList.get(i)).append(" hours")
                    .append(", Status: ").append(taskStatusList.get(i))
                    .append("\n");
            found = true;
        }
    }

    if (found) {
        JOptionPane.showMessageDialog(null, output.toString());
    } else {
        JOptionPane.showMessageDialog(null, "No tasks found with the given name.");
    }
}
 

public static void searchTasksByDeveloper(String developerName) {
    StringBuilder output = new StringBuilder("Search Results:\n");
    boolean found = false;

    for (int i = 0; i < developerList.size(); i++) {
        if (developerList.get(i).equalsIgnoreCase(developerName)) {
            output.append("Developer: ").append(developerList.get(i))
                    .append(", Task Name: ").append(taskNamesList.get(i))
                    .append(", Duration: ").append(taskDurationList.get(i)).append(" hours")
                    .append(", Status: ").append(taskStatusList.get(i))
                    .append("\n");
            found = true;
        }
    }

    if (found) {
        JOptionPane.showMessageDialog(null, output.toString());
    } else {
        JOptionPane.showMessageDialog(null, "No tasks found for the given developer.");
    }
}

 

public static void deleteTaskByName(String taskName) {
    boolean found = false;

    for (int i = 0; i < taskNamesList.size(); i++) {
        if (taskNamesList.get(i).equalsIgnoreCase(taskName)) {
            taskNamesList.remove(i);
            developerList.remove(i);
            taskDurationList.remove(i);
            taskStatusList.remove(i);
            found = true;
            break; // Exit the loop once the task is deleted
        }
    }

    if (found) {
        JOptionPane.showMessageDialog(null, "Task '" + taskName + "' deleted successfully.");
    } else {
        JOptionPane.showMessageDialog(null, "No tasks found with the given name.");
    }
}}}