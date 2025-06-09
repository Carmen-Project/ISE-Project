import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // read user input
        SeasonDeterminer seasonDeterminer = new SeasonDeterminer();
        // TimeConverter timeConverter = new TimeConverter();
        int choice = 0; // need a variable to store the user's menu selection

        do {
            System.out.println("----------------------------------------------------------------");
            System.out.println("|             Welcome to the World Time & Season App!          |");
            System.out.println("|                                                              |");
            System.out.println("|  You can convert time and check the current season for the   |");
            System.out.println("|  following supported countries:                              |");
            System.out.println("|                                                              |");
            System.out.println("|    Northern Hemisphere: USA, China, Germany                  |");
            System.out.println("|    Southern Hemisphere: Australia, New Zealand, Brazil       |");
            System.out.println("----------------------------------------------------------------\n");
            System.out.println("=== WorldTime & Weather App ===");
            System.out.println("1. Convert Time");
            System.out.println("2. Determine Current Season");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            /*
             * String input = sc.nextLine();
             * if (input.isEmpty())
             * {
             * System.out.println("Input cannot be blank. Please enter 1 , 2 or 3!");
             * continue;
             * }
             */

            try {
                choice = sc.nextInt();
                sc.nextLine(); // clear newline character

                if (choice == 1) {
                    TimeConverter.timeConverter();

                } else if (choice == 2) {
                    System.out.print("\nEnter country: ");
                    String country = sc.nextLine();

                    System.out.print("Current date (yyyy-MM-dd): ");
                    String dateStr = sc.nextLine();

                    try {
                        LocalDate date = LocalDate.parse(dateStr);
                        String hemisphere = seasonDeterminer.getHemisphere(country);
                        System.out.println(country + " is in the " + hemisphere + " Hemisphere.");
                        String season = seasonDeterminer.getSeason(date, hemisphere);
                        System.out.println("Current season in " + country + ": " + season);
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                    }
                } else if (choice == 3) {
                    System.out.println("Goodbye!");
                } else {
                    System.out.println("Please enter only choice 1 , 2 or 3");
                }
            } catch (InputMismatchException e) { // if the user enters something that isn't an integer
                System.out.println("Invalid input. Please enter only number 1 , 2 or 3");
                sc.nextLine(); // clear the invalid input
            }
        } while (choice != 1 && choice != 2 && choice != 3);

        sc.close(); // close the scanner to free up system resources

    }
}
