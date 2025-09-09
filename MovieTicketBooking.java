class MovieTicket {
    String movieName;
    String theatreName;
    int seatNumber;
    double price;

    public MovieTicket() {
        this("Unknown", "Not Assigned", -1, 0.0);
    }

    public MovieTicket(String movieName) {
        this(movieName, "Not Assigned", -1, 200.0);
    }

    public MovieTicket(String movieName, int seatNumber) {
        this(movieName, "PVR", seatNumber, 200.0);
    }

    public MovieTicket(String movieName, String theatreName, int seatNumber, double price) {
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public void printTicket() {
        System.out.println("----- Movie Ticket -----");
        System.out.println("Movie: " + movieName);
        System.out.println("Theatre: " + theatreName);
        System.out.println("Seat Number: " + seatNumber);
        System.out.println("Price: ₹" + price);
        System.out.println("------------------------\n");
    }
}

public class MovieTicketBooking {
    public static void main(String[] args) {
        MovieTicket t1 = new MovieTicket();
        MovieTicket t2 = new MovieTicket("Inception");
        MovieTicket t3 = new MovieTicket("Interstellar", 15);
        MovieTicket t4 = new MovieTicket("Oppenheimer", "IMAX", 22, 500.0);

        t1.printTicket();
        t2.printTicket();
        t3.printTicket();
        t4.printTicket();
    }
}
