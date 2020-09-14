import java.time.LocalDate;
import java.util.Scanner;

public class Library {

    // the names of books in stock in the library
    private static final String[] booksInStock = new String[] {
            "Dune",
            "Ender's Game",
            "Hitchhiker's Guide to the Galaxy",
            "Do Androids Dream of Electric Sheep?",
            "Frankenstein",
            "The Hunbger Games",
            "Never Let Me Go",
            "Neuromancer",
            "I Robot",
            "Snow Crash"
    };

    // the names of renters of each of the books (indexes line up)
    // null indicates the book is currently at the library
    private static final String[] renters = new String[booksInStock.length];

    // dates that each book was rented
    private static final LocalDate[] dueDates = new LocalDate[booksInStock.length];

    /** returns the renter associated with the book name.
     * If no one is renting the book, null is returned.
     * @param bookName
     * @return
     */
    public static String getRenter(String bookName) {
        for(int i=0; i<booksInStock.length; i++) {
            if(booksInStock[i].equals(bookName))
                return renters[i];
        }
        return null;
    }

    /** determines whether or not a book is stocked by the library.
     *
     * @param bookName
     * @return
     */
    public static boolean bookExists(String bookName) {
        for(int i=0; i<booksInStock.length; i++) {
            if(booksInStock[i].equals(bookName))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {

        var in = new Scanner(System.in);
        while(true) { // run menu indefinitely
            System.out.println("\nWelcome to the CSC 184 Library App!");
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
            System.out.println("");
            switch (choice) {
                case "1": // list inventory
                    for(int i=0; i<booksInStock.length; i++)
                        System.out.println((i+1)+". " + booksInStock[i]);
                    break;
                case "2": // list books on loan
                    var anyRenters = false;
                    for(int i=0; i<booksInStock.length; i++)
                        if(renters[i] != null) {
                            anyRenters = true;
                            System.out.println(booksInStock[i]);
                            System.out.println("rented by: " + renters[i]);
                            System.out.println("due on: " + dueDates[i]);
                            System.out.println("");
                        }
                    if(!anyRenters) System.out.println("No books are on loan. ");
                    break;
                case "3": // rent a book
                    System.out.print("What book will be rented: ");
                    var bookName = in.nextLine();
                    System.out.println("");
                    if(!bookExists(bookName)) { // make sure we stock the book
                        System.out.println("Unknown book");
                        break;
                    }
                    if(getRenter(bookName) != null) { // make sure the book isn't rented
                        System.out.println("This book is currently on loan");
                        break;
                    }
                    System.out.print("Who is renting the book: ");
                    var renter = in.nextLine();
                    for(int i=0; i< booksInStock.length; i++) {
                        if(booksInStock[i].equals(bookName)) {
                            renters[i] = renter;
                            dueDates[i] = LocalDate.now().plusDays(7);
                        }
                    }
                    break;
                case "4": // return a book
                    System.out.print("What book will be returned: ");
                    var returnedBookName = in.nextLine();
                    System.out.println("");
                    if(!bookExists(returnedBookName)) { // make sure we stock the book
                        System.out.println("Unknown book");
                        break;
                    }
                    if(getRenter(returnedBookName) == null) { // make sure the book isn't rented
                        System.out.println("This book is not currently on loan");
                        break;
                    }
                    for(int i=0; i< booksInStock.length; i++) {
                        if(booksInStock[i].equals(returnedBookName)) {
                            renters[i] = null;
                            dueDates[i] = null;
                        }
                    }
                    break;
                case "5": // exit selected
                    System.out.println("Thank you for using the library!");
                    System.exit(0);  // user exit program
                    break;
                default:
                    System.out.println(choice + " is not a valid selection, try again");
                    break;
            }
        }
    }
}

