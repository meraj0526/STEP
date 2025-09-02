class Book {
    // Private variables
    private String bookId;
    private String title;
    private String author;
    private boolean isAvailable;

    // Static variables
    private static int totalBooks = 0;
    private static int availableBooks = 0;
    private static int bookCounter = 0;

    // Constructor
    public Book(String title, String author) {
        this.bookId = generateBookId();
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        totalBooks++;
        availableBooks++;
    }

    // Methods
    public void issueBook() {
        if (isAvailable) {
            isAvailable = false;
            availableBooks--;
            System.out.println("Book " + bookId + " issued successfully.");
        } else {
            System.out.println("Book " + bookId + " is already issued.");
        }
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            availableBooks++;
            System.out.println("Book " + bookId + " returned successfully.");
        } else {
            System.out.println("Book " + bookId + " was not issued.");
        }
    }

    public void displayBookInfo() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("-------------------------");
    }

    public String getBookId() {
        return bookId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Static methods
    private static String generateBookId() {
        bookCounter++;
        return String.format("B%03d", bookCounter);
    }

    public static int getTotalBooks() {
        return totalBooks;
    }

    public static int getAvailableBooks() {
        return availableBooks;
    }
}


class Member {
    // Private variables
    private String memberId;
    private String memberName;
    private String[] booksIssued;
    private int bookCount;

    private static int memberCounter = 0;

    // Constructor
    public Member(String memberName) {
        this.memberId = generateMemberId();
        this.memberName = memberName;
        this.booksIssued = new String[5]; // max 5 books
        this.bookCount = 0;
    }

    // Borrow a book
    public void borrowBook(Book book) {
        if (book.isAvailable() && bookCount < booksIssued.length) {
            book.issueBook();
            booksIssued[bookCount] = book.getBookId();
            bookCount++;
            System.out.println(memberName + " borrowed book: " + book.getBookId());
        } else if (!book.isAvailable()) {
            System.out.println("Sorry, book " + book.getBookId() + " is not available.");
        } else {
            System.out.println(memberName + " has reached maximum book limit.");
        }
    }

    // Return a book
    public void returnBook(String bookId, Book[] books) {
        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (booksIssued[i].equals(bookId)) {
                // Find book in library
                for (Book book : books) {
                    if (book.getBookId().equals(bookId)) {
                        book.returnBook();
                        System.out.println(memberName + " returned book: " + bookId);
                        found = true;
                        // Remove from member's record
                        booksIssued[i] = booksIssued[bookCount - 1];
                        booksIssued[bookCount - 1] = null;
                        bookCount--;
                        break;
                    }
                }
            }
        }
        if (!found) {
            System.out.println(memberName + " does not have book " + bookId);
        }
    }

    public void displayMemberInfo() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Member Name: " + memberName);
        System.out.print("Books Issued: ");
        for (int i = 0; i < bookCount; i++) {
            System.out.print(booksIssued[i] + " ");
        }
        if (bookCount == 0) System.out.print("None");
        System.out.println("\n-------------------------");
    }

    // Static method
    private static String generateMemberId() {
        memberCounter++;
        return String.format("M%03d", memberCounter);
    }
}


public class LibraryManagement {
    public static void main(String[] args) {
        // Create books
        Book[] books = new Book[3];
        books[0] = new Book("The Alchemist", "Paulo Coelho");
        books[1] = new Book("1984", "George Orwell");
        books[2] = new Book("Clean Code", "Robert C. Martin");

        // Display books
        System.out.println("=== Library Books ===");
        for (Book b : books) {
            b.displayBookInfo();
        }

        // Create members
        Member[] members = new Member[2];
        members[0] = new Member("Alice");
        members[1] = new Member("Bob");

        // Borrowing books
        System.out.println("=== Borrowing ===");
        members[0].borrowBook(books[0]); // Alice borrows B001
        members[1].borrowBook(books[0]); // Bob tries to borrow B001 (not available)
        members[1].borrowBook(books[1]); // Bob borrows B002

        // Display members
        System.out.println("=== Member Details ===");
        for (Member m : members) {
            m.displayMemberInfo();
        }

        // Returning books
        System.out.println("=== Returning ===");
        members[0].returnBook("B001", books); // Alice returns B001
        members[1].returnBook("B002", books); // Bob returns B002

        // Final status
        System.out.println("=== Final Book Status ===");
        for (Book b : books) {
            b.displayBookInfo();
        }

        System.out.println("Total Books: " + Book.getTotalBooks());
        System.out.println("Available Books: " + Book.getAvailableBooks());
    }
}
