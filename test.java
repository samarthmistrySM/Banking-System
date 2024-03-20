import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Clear the screen
            clearScreen();

            // Display UI
            System.out.println("Welcome to the Terminal UI");
            System.out.println("1. Option 1");
            System.out.println("2. Option 2");
            System.out.println("3. Option 3");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            // Read user input
            String choice = scanner.nextLine();

            // Process user input
            switch (choice) {
                case "1":
                    displayOption("You selected Option 1");
                    break;
                case "2":
                    displayOption("You selected Option 2");
                    break;
                case "3":
                    displayOption("You selected Option 3");
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    displayOption("Invalid choice. Please try again.");
            }

            // Wait for user to press Enter before continuing
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
        }

        // Close the scanner
        scanner.close();
    }

    // Method to clear the screen
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Method to display an option
    public static void displayOption(String message) {
        clearScreen();
        System.out.println(message);
    }
}
