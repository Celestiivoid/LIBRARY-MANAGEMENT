import java.util.Scanner;
import java.util.ArrayList;
public class LIBRARYMANAGEMENT {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<String> Bookname = new ArrayList<>();
    static ArrayList<String> Authorname = new ArrayList<>();
    static ArrayList<String> Bookstatus = new ArrayList<>();
    static String adname = "librarian";
    static String adpass = "lib123";
    static String stname = "student";
    static String stpass = "school123";
    static String bookPend = "Pending for approval";
    static String borrowTag = "Borrowed";
    static String availability = "Available";
    public static void main(String[] args) {

        while(!LOGIN()) {
            continue;
        }

        while(true) {
            System.out.println("=====LIBRARY-MANAGEMENT=====");
            System.out.println("[1] Add Book");
            System.out.println("[2] View Books");
            System.out.println("[3] Search Book");
            System.out.println("[4] Borrow Book");
            System.out.println("[5] Return Book");
            System.out.println("[6] Remove Book");
            System.out.println("[7] Approve Return of Books");
            System.out.println("[8] Exit");

            System.out.println("Enter an option: ");
            int option;

            try {
                option = Integer.parseInt(scanner.nextLine());
                if(option < 1 || option >= 9) {
                    System.out.println("Out of range!");
                    continue;
                }
            } catch(NumberFormatException e) {
                System.out.println("Numbers only!");
                continue;
            }

            switch(option) {
                case 1 -> ADD();
                case 2 -> VIEW();
                case 3 -> SEARCH();
                case 4 -> BORROW();
                case 5 -> RETURN();
                case 6 -> REMOVE();
                case 7 -> BOOKAPPROVAL();
                case 8 -> {
                    while(true) {
                        System.out.println("Do you want to exit? ");
                        String exit = scanner.nextLine();

                        if(exit.equalsIgnoreCase("Yes")) {
                            return;
                        } else if(exit.equalsIgnoreCase("No")) {
                            break;
                        } else {
                            System.out.println("Invalid input!");
                            continue;
                        }
                    }
                }
            }
        }
    }
    static boolean LOGIN() {
        System.out.println("=====LIBRARY MANAGEMENT LOGIN=====");
        System.out.println("USERNAME: ");
        String username = scanner.nextLine();

        if(username.isEmpty()) {
            System.out.println("Username field cannot be empty!");
            return false;
        }

        System.out.println("PASSWORD: ");
        String password = scanner.nextLine();

        if(password.isEmpty()) {
            System.out.println("Password field cannot be empty!");
            return false;
        }
        if(username.equals(stname) && password.equals(stpass)) {
            System.out.println("Successfully logged as student!");
            return true;
        }
        if(username.equals(adname) && password.equals(adpass)) {
            System.out.println("Successfully logged as librarian!");
            return true;
        }
        else {
            System.out.println("Invalid credentials!");
            return false;
        }
    }
    static boolean BOAPLOGIN() {
        System.out.println("=====BOOK-APPROVAL-MENU-LOGIN=====");
        System.out.println("USERNAME: ");
        String username = scanner.nextLine();

        if(username.isEmpty()) {
            System.out.println("Username field cannot be empty.");
            return false;
        }

        System.out.println("PASSWORD: ");
        String password = scanner.nextLine();

        if(password.isEmpty()) {
            System.out.println("Password field cannot be empty.");
            return false;
        }

        if(username.equals(adname) && password.equals(adpass)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid credentials!");
            return false;
        }
    }
    static void ADD() {
        while(true) {
            System.out.println("=====ADD-BOOKS=====");
            System.out.println("Book name: ");
            String addBook = scanner.nextLine();

            if(addBook.isEmpty()) {
                System.out.println("Book field cannot be empty!");
                continue;
            }
            System.out.println("Author name: ");
            String authName = scanner.nextLine();

            if(authName.isEmpty()) {
                System.out.println("Author field cannot be empty!");
                continue;
            }

            Bookstatus.add(availability);
            Bookname.add(addBook);
            Authorname.add(authName);

            System.out.println("Successfully added: " + addBook);
            return;
        }
    }
    static void VIEW() {
        System.out.println("=====VIEW-BOOKS-AND-AVAILABILITY=====");

        if(Bookname.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        for(int i = 0; i < Bookname.size() && i < Authorname.size() && i < Bookstatus.size(); i++ ) {
            System.out.println((i + 1) + "." + Bookname.get(i) + " |By: " + Authorname.get(i) + " | " + Bookstatus.get(i));
        }
    }
    static void SEARCH() {
        while(true) {

            System.out.println("=====SEARCH-A-BOOK=====");

            if(Bookname.isEmpty()) {
                System.out.println("No books available to search.");
                return;
            }

            System.out.println("Enter book name or author to search: ");
            String search = scanner.nextLine();

            for(int i = 0; i < Bookname.size() && i < Authorname.size() && i < Bookstatus.size(); i++) {
                if(search.equalsIgnoreCase(Bookname.get(i)) || search.equalsIgnoreCase(Authorname.get(i))) {
                    System.out.println((i + 1) + "." + Bookname.get(i) + " | " + Authorname.get(i) + " | " + Bookstatus.get(i));
                    return;
                }
            }
            System.out.println("Book not found!");
            return;
        }
    }
    static void BORROW() {
        while(true) {
            System.out.println("=====BORROW-BOOKS=====");

            if(Bookname.isEmpty()) {
                System.out.println("No available books to borrow.");
                return;
            }

            for(int i = 0; i < Bookname.size() && i < Authorname.size() && i < Bookstatus.size(); i++) {
                System.out.println((i + 1) + "." + Bookname.get(i) + " | " + Authorname.get(i) + " | " + Bookstatus.get(i));
            }

            System.out.println("Pick a book to borrow: ");
            int borrow;

            try {
                borrow = Integer.parseInt(scanner.nextLine());
            } catch(NumberFormatException e) {
                System.out.println("Numbers only!");
                continue;
            }

            if(borrow < 1 || borrow > Bookname.size() && borrow > Authorname.size() && borrow > Bookstatus.size()) {
                System.out.println("Out of range!");
                continue;
            }
            if(borrowTag.equalsIgnoreCase(Bookstatus.get(borrow - 1))) {
                System.out.println("Book already borrowed!");
                return;

            } else {
                Bookstatus.set(borrow -1,borrowTag);
                System.out.println("Successfully borrowed!");
                return;
            }
            
        }
    }
    static void RETURN() {
        while(true) {
            System.out.println("=====RETURN-BOOK=====");

            if(Bookname.isEmpty()) {
                System.out.println("No books are available.");
                return;
            }

            for(int i = 0; i < Bookname.size() && i < Authorname.size() && i < Bookstatus.size(); i++) {
                System.out.println((i + 1) + "." + Bookname.get(i) + " | " + Authorname.get(i) + " | " + Bookstatus.get(i));
            }

            System.out.println("Pick a book to return: ");
            int bookreturn;

            try {
                bookreturn = Integer.parseInt(scanner.nextLine());
            } catch(NumberFormatException e) {
                System.out.println("Numbers only!");
                continue;
            }
            if(bookreturn < 1 || bookreturn > Bookname.size()) {
                System.out.println("Out of range!");
                continue;
            }
            if(borrowTag.equalsIgnoreCase(Bookstatus.get(bookreturn - 1))) {
                Bookstatus.set(bookreturn -1, bookPend);
                System.out.println("Successfully returned!");
                return;
            } else {
                System.out.println("Book is available to borrow. Please proceed to borrow menu.");
                return;
            }

        }
    }
    static void REMOVE() {
        while(true) {
            System.out.println("=====REMOVE-A-BOOK=====");

            if(Bookname.isEmpty()) {
                System.out.println("No available books to remove.");
                return;
            }
            for(int i = 0; i < Bookname.size() && i < Authorname.size() && i < Bookstatus.size(); i++) {
                System.out.println((i + 1) + "." + Bookname.get(i) + " | " + Authorname.get(i) + " | " + Bookstatus.get(i));
            }

            System.out.println("Enter a book to remove: ");
            int remove;

            try {
                remove = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Numbers only!");
                continue;
            }

            if(remove < 1 || remove > Bookname.size()) {
                System.out.println("Out of range!");
                continue;
            }

            Bookname.remove(remove - 1);
            Authorname.remove(remove - 1);
            Bookstatus.remove(remove - 1);
            System.out.println("Successfully removed!");
            return;
        }
    }
    static void BOOKAPPROVAL() {
        while(!BOAPLOGIN()) {
            System.out.println("Access Denied: exclusively only for librarians!");
            continue;
        }


        while(true) {
            System.out.println("=====RETURN-OF-BOOK-APPROVAL=====");

            for(int i = 0; i < Bookname.size() && i < Authorname.size() && i < Bookstatus.size(); i++) {
                System.out.println((i + 1) + "." + Bookname.get(i) + " | " + Authorname.get(i) + " | " + Bookstatus.get(i));
            }
            System.out.println("Pick a book to approve: ");
            int bookapp;

            try {
                bookapp = Integer.parseInt(scanner.nextLine());
            } catch(NumberFormatException e) {
                System.out.println("Numbers only!");
                continue;
            }

            if(bookapp < 1 || bookapp > Bookname.size()) {
                System.out.println("Out of range!");
                continue;
            }

            if(availability.equalsIgnoreCase(Bookstatus.get(bookapp - 1))) {
                System.out.println("Book is already available for borrowing.");
                return;
            }
            if(borrowTag.equalsIgnoreCase(Bookstatus.get(bookapp - 1))) {
                System.out.println("Book is currently being borrowed.");
                return;
            }
            if(bookPend.equalsIgnoreCase(Bookstatus.get(bookapp - 1))) {
                Bookstatus.set(bookapp - 1, availability);
                System.out.println("Successfully approved!");
                return;
            }
        }
    }
}