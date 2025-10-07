import java.util.ArrayList;
import java.util.Scanner;


class Book {
    private final int id;          // unique, cannot be changed once set
    private String title;
    private String author;
    private boolean isAvailable;

    // Constructor
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true; // default status
    }

    // Getters (Encapsulation)
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return isAvailable; }

    
    public void borrowBook() { isAvailable = false; }
    public void returnBook() { isAvailable = true; }

    @Override
    public String toString() {
        return id + " | " + title + " | " + author + " | " +
               (isAvailable ? "Available" : "Borrowed");
    }
}


class Library {
    private ArrayList<Book> books = new ArrayList<>();

    // Check if a book ID already exists
    private boolean existsId(int id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id)
                return true;
        }
        return false;
    }

    // Add a new book
    public void addBook(Book book) {
        if (existsId(book.getId())) {
            System.out.println("⚠️ Book with this ID already exists!\n");
            return;
        }
        books.add(book);
        System.out.println("✅ Book added successfully!\n");
    }

    // Show all books
    public void showAllBooks() {
        if (books.isEmpty()) {
            System.out.println("📚 No books available in the library.\n");
            return;
        }
        System.out.println("\n------ Library Books ------");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }
        System.out.println("---------------------------\n");
    }

    // Borrow a book
    public void borrowBook(int id) {
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            if (b.getId() == id) {
                if (b.isAvailable()) {
                    b.borrowBook();
                    System.out.println("📖 You borrowed: " + b.getTitle() + "\n");
                } else {
                    System.out.println("⚠️ This book is already borrowed.\n");
                }
                return;
            }
        }
        System.out.println("❌ Book not found!\n");
    }

    // Return a book
    public void returnBook(int id) {
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            if (b.getId() == id) {
                if (!b.isAvailable()) {
                    b.returnBook();
                    System.out.println("🔁 You returned: " + b.getTitle() + "\n");
                } else {
                    System.out.println("⚠️ This book wasn't borrowed.\n");
                }
                return;
            }
        }
        System.out.println("❌ Book not found!\n");
    }
}


public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();

        while (true) {
            System.out.println("========== LIBRARY MENU ==========");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice;
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); // consume leftover newline
            } else {
                System.out.println("❌ Invalid input. Please enter a number.\n");
                sc.nextLine(); // clear invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Book Author: ");
                    String author = sc.nextLine();
                    Book newBook = new Book(id, title, author);
                    lib.addBook(newBook);
                    break;

                case 2:
                    lib.showAllBooks();
                    break;

                case 3:
                    System.out.print("Enter Book ID to borrow: ");
                    int borrowId = sc.nextInt();
                    lib.borrowBook(borrowId);
                    break;

                case 4:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    lib.returnBook(returnId);
                    break;

                case 5:
                    System.out.println("👋 Thank you for using Library Management System!");
                    sc.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice. Try again!\n");
            }
        }
    }
}
