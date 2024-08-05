import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Expense> expenseList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n---- MENU ----");
            System.out.println("1. Add New Transaction");
            System.out.println("2. Print All Transaction");
            System.out.println("3. Delete Transaction by ID");
            System.out.println("4. Sort Transaction by Amount Descending");
            System.out.println("5. Search Transaction by ID or DesCription");
            System.out.println("6. Search Transaction with Amount >= x");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    inputExpense();
                    break;
                case 2:
                    printExpenses();
                    break;
                case 3:
                    removeExpenseByCode();
                    break;
                case 4:
                    sortExpensesByAmount();
                    break;
                case 5:
                    searchExpense();
                    break;
                case 6:
                    searchExpenseByAmount();
                    break;
                case 0:
                    System.out.println("GoodBye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 0);
    }

    public static void inputExpense() {
        System.out.println("\nEnter Transaction information:");

        System.out.print("Enter Transaction description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Amount: ");
        float amount = scanner.nextFloat();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Transaction ID: ");
        String code = scanner.nextLine();
        System.out.print("Enter Date (dd/mm/yyyy): ");
        String date = scanner.nextLine();

        Expense expense = new Expense(description, amount, code, date);
        expenseList.add(expense);
        System.out.println("Transaction Add successfully.");
    }

    public static void printExpenses() {
        if (expenseList.isEmpty()) {
            System.out.println("No Transaction to display.");
        } else {
            System.out.println("\nTransaction Menu:");
            for (Expense expense : expenseList) {
                System.out.println(expense);
            }
        }
    }

    public static void removeExpenseByCode() {
        System.out.print("Enter Transaction ID to delete: ");
        String code = scanner.nextLine();
        boolean removed = expenseList.removeIf(expense -> expense.getCode().equals(code));
        if (removed) {
            System.out.println("Remove successfully " + code);
        } else {
            System.out.println("Transaction not found " + code);
        }
    }

    public static void sortExpensesByAmount() {
        Collections.sort(expenseList, Comparator.comparing(Expense::getAmount).reversed());
        System.out.println("Transaction sorted by amount in descending order.");
        printExpenses();
    }

    public static void searchExpense() {
        System.out.print("Enter Transaction ID to search: ");
        String keyword = scanner.nextLine();
        List<Expense> results = new ArrayList<>();
        for (Expense expense : expenseList) {
            if (expense.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(expense);
            }
        }
        if (results.isEmpty()) {
            System.out.println("No Transaction found with the given ID.");
        } else {
            System.out.println("Result found: ");
            for (Expense expense : results) {
                System.out.println(expense);
            }
        }
    }

    public static void searchExpenseByAmount() {
        System.out.print("Enter minimum amount: ");
        float minAmount = scanner.nextFloat();
        scanner.nextLine(); // Consume newline
        List<Expense> results = new ArrayList<>();
        for (Expense expense : expenseList) {
            if (expense.getAmount() >= minAmount) {
                results.add(expense);
            }
        }
        if (results.isEmpty()) {
            System.out.println("No minimum spend found >= " + minAmount);
        } else {
            System.out.println("Spend a certain amount of money >= " + minAmount + ":");
            for (Expense expense : results) {
                System.out.println(expense);
            }
        }
    }
}

class Expense {
    private String description;
    private float amount;
    private String code;
    private String date;

    public Expense(String description, float amount, String code, String date) {
        this.description = description;
        this.amount = amount;
        this.code = code;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public float getAmount() {
        return amount;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                ", code='" + code + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}