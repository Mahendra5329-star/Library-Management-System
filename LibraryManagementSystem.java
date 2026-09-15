import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Custom Exception for Library-specific errors
class LibraryException extends Exception {
    public LibraryException(String message) {
        super(message);
    }
}

// Book Class representing the book entity
class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true; // New books are available by default
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return isAvailable; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return "Book ID: " + id + " | Title: " + title + " | Author: " + author + " | Status: " + (isAvailable ? "Available" : "Borrowed");
    }
}

// Member Class representing a library user
class Member {
    private int id;
    private String name;

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Member ID: " + id + " | Name: " + name;
    }
}

// Main Library Class handling business logic and UI
public class LibraryManagementSystem {
    private ArrayList<Book> books;
    private ArrayList<Member> members;
    private Scanner scanner;

    public LibraryManagementSystem() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // --- Main Menu ---
    public void start() {
        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Search Book");
            System.out.println("4. Update Book");
            System.out.println("5. Remove Book");
            System.out.println("6. Issue Book");
            System.out.println("7. Return Book");
            System.out.println("8. View All Books");
            System.out.println("9. Search Member");       // <-- NEW
            System.out.println("10. View All Members");    // <-- NEW
            System.out.println("11. Exit");                // <-- Updated Number
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> addBook();
                    case 2 -> addMember();
                    case 3 -> searchBook();
                    case 4 -> updateBook();
                    case 5 -> removeBook();
                    case 6 -> issueBook();
                    case 7 -> returnBook();
                    case 8 -> viewAllBooks();
                    case 9 -> searchMember();       // <-- NEW
                    case 10 -> viewAllMembers();    // <-- NEW
                    case 11 -> {
                        System.out.println("Exiting system. Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please enter a number between 1 and 11.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input buffer
            }
        }
    }

    // --- Features ---

    private void addBook() {
        try {
            System.out.print("Enter Book ID (number): ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            // Check for duplicate ID
            for (Book b : books) {
                if (b.getId() == id) {
                    throw new LibraryException("A book with ID " + id + " already exists.");
                }
            }

            System.out.print("Enter Book Title: ");
            String title = scanner.nextLine();
            System.out.print("Enter Author Name: ");
            String author = scanner.nextLine();

            books.add(new Book(id, title, author));
            System.out.println("Book added successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid ID format. Please enter numbers only.");
            scanner.nextLine();
        } catch (LibraryException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addMember() {
        try {
            System.out.print("Enter Member ID (number): ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Member Name: ");
            String name = scanner.nextLine();

            members.add(new Member(id, name));
            System.out.println("Member added successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid ID format. Please enter numbers only.");
            scanner.nextLine();
        }
    }

    private void searchBook() {
        System.out.print("Enter Book ID or Title to search: ");
        String query = scanner.nextLine().toLowerCase();
        boolean found = false;

        for (Book b : books) {
            if (String.valueOf(b.getId()).equals(query) || b.getTitle().toLowerCase().contains(query)) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found matching your search.");
        }
    }

    private void updateBook() {
        try {
            System.out.print("Enter Book ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Book bookToUpdate = findBookById(id);
            if (bookToUpdate == null) throw new LibraryException("Book not found.");

            System.out.print("Enter new Title (leave blank to keep current): ");
            String newTitle = scanner.nextLine();
            if (!newTitle.trim().isEmpty()) bookToUpdate.setTitle(newTitle);

            System.out.print("Enter new Author (leave blank to keep current): ");
            String newAuthor = scanner.nextLine();
            if (!newAuthor.trim().isEmpty()) bookToUpdate.setAuthor(newAuthor);

            System.out.println("Book updated successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input format.");
            scanner.nextLine();
        } catch (LibraryException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void removeBook() {
        try {
            System.out.print("Enter Book ID to remove: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Book bookToRemove = findBookById(id);
            if (bookToRemove == null) throw new LibraryException("Book not found.");

            books.remove(bookToRemove);
            System.out.println("Book removed successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input format.");
            scanner.nextLine();
        } catch (LibraryException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void issueBook() {
        try {
            System.out.print("Enter Book ID to issue: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Book book = findBookById(id);
            if (book == null) throw new LibraryException("Book not found.");
            if (!book.isAvailable()) throw new LibraryException("Book is currently borrowed.");

            book.setAvailable(false);
            System.out.println("Book issued successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input format.");
            scanner.nextLine();
        } catch (LibraryException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void returnBook() {
        try {
            System.out.print("Enter Book ID to return: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Book book = findBookById(id);
            if (book == null) throw new LibraryException("Book not found.");
            if (book.isAvailable()) throw new LibraryException("This book is already in the library.");

            book.setAvailable(true);
            System.out.println("Book returned successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input format.");
            scanner.nextLine();
        } catch (LibraryException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("\n--- All Books ---");
            for (Book b : books) {
                System.out.println(b);
            }
        }
    }

    // --- NEW MEMBER METHODS ---

    private void searchMember() {
        System.out.print("Enter Member ID or Name to search: ");
        String query = scanner.nextLine().toLowerCase();
        boolean found = false;

        for (Member m : members) {
            // Check if the query matches the ID (converted to string) OR if the name contains the query text
            if (String.valueOf(m.getId()).equals(query) || m.getName().toLowerCase().contains(query)) {
                System.out.println(m);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No members found matching your search.");
        }
    }

    private void viewAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered in the library.");
        } else {
            System.out.println("\n--- All Members ---");
            for (Member m : members) {
                System.out.println(m);
            }
        }
    }

    // --- Helper Methods ---

    // Helper method to find a book by ID
    private Book findBookById(int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    // Main method to run the application
    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();
        library.start();
    }
}