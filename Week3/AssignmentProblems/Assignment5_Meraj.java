import java.util.*;

class Book {
    private String bookId;
    private String title;
    private String author;
    private String isbn;
    private String category;
    private boolean isIssued;
    private String issueDate;
    private String dueDate;
    private int timesIssued;

    public Book(String bookId, String title, String author, String isbn, String category) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.isIssued = false;
        this.issueDate = null;
        this.dueDate = null;
        this.timesIssued = 0;
    }

    // Getters
    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public boolean isIssued() { return isIssued; }
    public String getIssueDate() { return issueDate; }
    public String getDueDate() { return dueDate; }
    public int getTimesIssued() { return timesIssued; }

    // Setters
    public void setIssued(boolean issued) { isIssued = issued; }
    public void setIssueDate(String issueDate) { this.issueDate = issueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
    public void incrementTimesIssued() { timesIssued++; }

    @Override
    public String toString() {
        return "[" + bookId + "] " + title + " by " + author + " | Category: " + category +
                (isIssued ? " (Issued)" : " (Available)");
    }
}

class Member {
    private String memberId;
    private String memberName;
    private String memberType; // Student, Faculty, General
    private List<Book> booksIssued;
    private double totalFines;
    private String membershipDate;

    private static int totalMembers = 0;
    private static String libraryName = "Central Library";
    private static double finePerDay = 2.0;
    private static int maxBooksAllowed = 3;
    private static int totalBooks = 0;

    public Member(String memberId, String memberName, String memberType, String membershipDate) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberType = memberType;
        this.membershipDate = membershipDate;
        this.booksIssued = new ArrayList<>();
        this.totalFines = 0.0;
        totalMembers++;
    }

    // Issue a book
    public void issueBook(Book book, String issueDate, String dueDate) {
        if (booksIssued.size() >= getMaxBooksAllowedForMember()) {
            System.out.println("❌ Max books limit reached for " + memberType + ": " + memberName);
            return;
        }
        if (book.isIssued()) {
            System.out.println("❌ Book already issued: " + book.getTitle());
            return;
        }
        book.setIssued(true);
        book.setIssueDate(issueDate);
        book.setDueDate(dueDate);
        book.incrementTimesIssued();
        booksIssued.add(book);
        System.out.println("✅ " + memberName + " issued: " + book.getTitle());
    }

    // Return a book
    public void returnBook(Book book, String returnDate) {
        if (!booksIssued.contains(book)) {
            System.out.println("❌ Book not issued to " + memberName);
            return;
        }
        double fine = calculateFine(book, returnDate);
        totalFines += fine;
        book.setIssued(false);
        booksIssued.remove(book);
        System.out.println("✅ " + memberName + " returned: " + book.getTitle() +
                (fine > 0 ? " | Fine: $" + fine : " | No fine"));
    }

    // Renew book
    public void renewBook(Book book, String newDueDate) {
        if (!booksIssued.contains(book)) {
            System.out.println("❌ Cannot renew. Book not issued to " + memberName);
            return;
        }
        book.setDueDate(newDueDate);
        System.out.println("🔄 Book renewed: " + book.getTitle() + " | New Due Date: " + newDueDate);
    }

    // Fine calculation
    private double calculateFine(Book book, String returnDate) {
        try {
            String[] dueParts = book.getDueDate().split("-");
            String[] returnParts = returnDate.split("-");

            int dueDay = Integer.parseInt(dueParts[0]);
            int returnDay = Integer.parseInt(returnParts[0]);

            int overdueDays = returnDay - dueDay;
            if (overdueDays > 0) return overdueDays * finePerDay;
        } catch (Exception e) {
            System.out.println("⚠️ Date parsing error");
        }
        return 0.0;
    }

    // Search books in library
    public static void searchBooks(Book[] books, String keyword) {
        System.out.println("\n🔍 Search Results for: " + keyword);
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                b.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                b.getCategory().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(b);
            }
        }
    }

    // Reserve book
    public void reserveBook(Book book) {
        if (book.isIssued()) {
            System.out.println("📌 " + memberName + " reserved the book: " + book.getTitle());
        } else {
            System.out.println("❌ Book is available. No need to reserve: " + book.getTitle());
        }
    }

    // Get max books allowed based on type
    private int getMaxBooksAllowedForMember() {
        switch (memberType.toLowerCase()) {
            case "faculty": return 5;
            case "student": return 3;
            case "general": return 2;
            default: return maxBooksAllowed;
        }
    }

    // Getters
    public double getTotalFines() { return totalFines; }
    public String getMemberName() { return memberName; }
    public String getMemberType() { return memberType; }

    // Static Reports
    public static void generateLibraryReport(Member[] members, Book[] books) {
        System.out.println("\n===== 📊 Library Report =====");
        System.out.println("Library: " + libraryName);
        System.out.println("Total Members: " + totalMembers);
        System.out.println("Total Books: " + books.length);

        System.out.println("\n-- Overdue Books --");
        getOverdueBooks(members, "20-09-2025");

        System.out.println("\n-- Most Popular Books --");
        getMostPopularBooks(books);
    }

    public static void getOverdueBooks(Member[] members, String currentDate) {
        for (Member m : members) {
            for (Book b : m.booksIssued) {
                try {
                    int dueDay = Integer.parseInt(b.getDueDate().split("-")[0]);
                    int today = Integer.parseInt(currentDate.split("-")[0]);
                    if (today > dueDay) {
                        System.out.println(b.getTitle() + " (Issued to: " + m.getMemberName() + ")");
                    }
                } catch (Exception e) {
                    System.out.println("⚠️ Error checking overdue for: " + b.getTitle());
                }
            }
        }
    }

    public static void getMostPopularBooks(Book[] books) {
        Arrays.sort(books, Comparator.comparingInt(Book::getTimesIssued).reversed());
        for (int i = 0; i < Math.min(3, books.length); i++) {
            System.out.println(books[i].getTitle() + " | Times Issued: " + books[i].getTimesIssued());
        }
    }
}

public class Assignment5_Meraj {
    public static void main(String[] args) {
        // Sample Books
        Book b1 = new Book("B001", "Java Programming", "James Gosling", "ISBN001", "Programming");
        Book b2 = new Book("B002", "Data Structures", "Mark Allen", "ISBN002", "Computer Science");
        Book b3 = new Book("B003", "Operating Systems", "Andrew Tanenbaum", "ISBN003", "CS");
        Book[] books = {b1, b2, b3};

        // Members
        Member m1 = new Member("M001", "Alice", "Student", "01-01-2025");
        Member m2 = new Member("M002", "Bob", "Faculty", "05-01-2025");
        Member[] members = {m1, m2};

        // Issue books
        m1.issueBook(b1, "10-09-2025", "15-09-2025");
        m2.issueBook(b2, "12-09-2025", "18-09-2025");

        // Renew and return
        m1.renewBook(b1, "20-09-2025");
        m1.returnBook(b1, "22-09-2025"); // Late return → fine

        // Reserve a book
        m2.reserveBook(b1);

        // Search
        Member.searchBooks(books, "Data");

        // Generate report
        Member.generateLibraryReport(members, books);
    }
}
