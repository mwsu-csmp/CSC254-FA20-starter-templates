import java.util.Scanner;

public class Library {
    public static void main(String[] args) {
        var in = new Scanner(System.in);
        while(true) { // run menu indefinitely
            System.out.println("Welcome to the CSC 184 Library App!");
            System.out.println("");
            System.out.println("Menu: ");
            System.out.println(" 1) List Inventory ");
            System.out.println(" 2) List Books on Loan ");
            System.out.println(" 3) Rent a Book ");
            System.out.println(" 4) Return a Book ");
            System.out.println(" 5) Quit ");
            System.out.println("");
            System.out.print("Please make a selection: ");

            var choice = in.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("SUPER SNAZZY BOOK INVENTORY!!!");
                    break;
                case "2":
                    System.out.println("SUPER SNAZZY BOOK ON LOAN LIST!!!");
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5": // exit selected
                    System.exit(0);  // user exit program
                    break;
                default:
                    System.out.println(choice + " is not a valid selection, try again");
                    break;
            }
        }
    }
}

