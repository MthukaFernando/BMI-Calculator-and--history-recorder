import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BMIMainApp {

    private static Scanner scanner = new Scanner(System.in);
    private static BMICalculator calculator = new BMICalculator();
    private static List<BMIRecord> history = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("🏥 Welcome to BMI Calculator");

        while (true) {
            showMenu();
            int choice = getIntInput("Choose an option: ");

            switch (choice) {
                case 1:
                    calculateBMI();
                    break;
                case 2:
                    showHistory();
                    break;
                case 3:
                    System.out.println("\n✅ Exiting... Stay healthy!");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Invalid menu option. Try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n====== MENU ======");
        System.out.println("1. Calculate BMI");
        System.out.println("2. View BMI History");
        System.out.println("3. Exit");
    }

    private static void calculateBMI() {
        double weight = getDoubleInput("Enter weight (kg): ");
        double heightValue = getDoubleInput("Enter height value: ");
        int unit = getIntInput("Height unit (1 = meters, 2 = centimeters): ");

        if (unit == 2) {
            heightValue = heightValue / 100;
        }

        if (heightValue <= 0 || weight <= 0) {
            System.out.println("❌ Height and weight must be positive values.");
            return;
        }

        Person person = new Person(weight, heightValue);
        double bmi = calculator.calculateBMI(person);
        String category = calculator.getBMICategory(bmi);

        history.add(new BMIRecord(bmi, category));

        System.out.printf("📊 BMI: %.2f\n", bmi);
        System.out.println("🩺 Health Status: " + category);
    }

    private static void showHistory() {
        if (history.isEmpty()) {
            System.out.println("📭 No BMI records found.");
            return;
        }

        System.out.println("\n📜 BMI History:");
        int i = 1;
        for (BMIRecord record : history) {
            System.out.printf(
                "%d. BMI: %.2f | Status: %s\n",
                i++, record.getBmi(), record.getCategory()
            );
        }
    }

    // -------- Exception-Safe Input Methods --------

    private static int getIntInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid integer.");
            }
        }
    }

    private static double getDoubleInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number.");
            }
        }
    }
}
