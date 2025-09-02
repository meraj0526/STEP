import java.util.*;

class Room {
    private String roomNumber;
    private String roomType;
    private double pricePerNight;
    private boolean isAvailable;
    private int maxOccupancy;

    public Room(String roomNumber, String roomType, double pricePerNight, int maxOccupancy) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
        this.maxOccupancy = maxOccupancy;
    }

    // Getters & setters
    public String getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public double getPricePerNight() { return pricePerNight; }
    public boolean isAvailable() { return isAvailable; }
    public int getMaxOccupancy() { return maxOccupancy; }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " | " + roomType + " | $" + pricePerNight +
                " | Max: " + maxOccupancy + " | " + (isAvailable ? "Available" : "Booked");
    }
}

class Guest {
    private String guestId;
    private String guestName;
    private String phoneNumber;
    private String email;
    private List<String> bookingHistory;

    public Guest(String guestId, String guestName, String phoneNumber, String email) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bookingHistory = new ArrayList<>();
    }

    // Getters
    public String getGuestId() { return guestId; }
    public String getGuestName() { return guestName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public List<String> getBookingHistory() { return bookingHistory; }

    public void addBooking(String bookingId) {
        bookingHistory.add(bookingId);
    }

    @Override
    public String toString() {
        return guestId + " | " + guestName + " | " + phoneNumber + " | " + email;
    }
}

class Booking {
    private String bookingId;
    private Guest guest;
    private Room room;
    private String checkInDate;
    private String checkOutDate;
    private double totalAmount;

    private static int totalBookings = 0;
    private static double hotelRevenue = 0.0;
    private static String hotelName = "Grand Palace Hotel";

    public Booking(String bookingId, Guest guest, Room room, String checkInDate, String checkOutDate, double totalAmount) {
        this.bookingId = bookingId;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalAmount = totalAmount;

        totalBookings++;
        hotelRevenue += totalAmount;
    }

    // Getters
    public String getBookingId() { return bookingId; }
    public Guest getGuest() { return guest; }
    public Room getRoom() { return room; }
    public double getTotalAmount() { return totalAmount; }

    public static int getTotalBookings() { return totalBookings; }
    public static double getHotelRevenue() { return hotelRevenue; }
    public static String getHotelName() { return hotelName; }

    @Override
    public String toString() {
        return "Booking " + bookingId + " | Guest: " + guest.getGuestName() +
                " | Room: " + room.getRoomNumber() + " | $" + totalAmount;
    }
}

class ReservationSystem {
    private List<Room> rooms;
    private List<Guest> guests;
    private List<Booking> bookings;

    public ReservationSystem() {
        rooms = new ArrayList<>();
        guests = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    // Room management
    public void addRoom(Room room) { rooms.add(room); }
    public void addGuest(Guest guest) { guests.add(guest); }

    public Room checkAvailability(String roomType) {
        for (Room room : rooms) {
            if (room.getRoomType().equalsIgnoreCase(roomType) && room.isAvailable()) {
                return room;
            }
        }
        return null;
    }

    // Make a reservation
    public Booking makeReservation(String bookingId, Guest guest, String roomType,
                                   String checkInDate, String checkOutDate, int nights) {
        Room room = checkAvailability(roomType);
        if (room == null) {
            System.out.println("No " + roomType + " rooms available.");
            return null;
        }

        double totalAmount = room.getPricePerNight() * nights;
        Booking booking = new Booking(bookingId, guest, room, checkInDate, checkOutDate, totalAmount);
        bookings.add(booking);
        guest.addBooking(bookingId);
        room.setAvailable(false);

        System.out.println("Reservation successful! " + booking);
        return booking;
    }

    // Cancel reservation
    public void cancelReservation(String bookingId) {
        Booking toCancel = null;
        for (Booking b : bookings) {
            if (b.getBookingId().equals(bookingId)) {
                toCancel = b;
                break;
            }
        }
        if (toCancel != null) {
            toCancel.getRoom().setAvailable(true);
            bookings.remove(toCancel);
            System.out.println("Booking " + bookingId + " cancelled successfully.");
        } else {
            System.out.println("Booking not found.");
        }
    }

    // Reports
    public static double getOccupancyRate(List<Room> rooms) {
        int total = rooms.size();
        int booked = 0;
        for (Room r : rooms) {
            if (!r.isAvailable()) booked++;
        }
        return (booked * 100.0) / total;
    }

    public static double getTotalRevenue() {
        return Booking.getHotelRevenue();
    }

    public String getMostPopularRoomType() {
        Map<String, Integer> counts = new HashMap<>();
        for (Booking b : bookings) {
            String type = b.getRoom().getRoomType();
            counts.put(type, counts.getOrDefault(type, 0) + 1);
        }
        return counts.entrySet().stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse("None");
    }

    // Show all bookings
    public void showAllBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            for (Booking b : bookings) {
                System.out.println(b);
            }
        }
    }

    public List<Room> getRooms() { return rooms; }
}

public class Assignment3_Meraj {
    public static void main(String[] args) {
        ReservationSystem system = new ReservationSystem();

        // Add sample rooms
        system.addRoom(new Room("101", "Single", 2000, 1));
        system.addRoom(new Room("102", "Double", 3500, 2));
        system.addRoom(new Room("103", "Suite", 8000, 4));
        system.addRoom(new Room("104", "Double", 3500, 2));
        system.addRoom(new Room("105", "Single", 2000, 1));

        // Add guests
        Guest g1 = new Guest("G001", "Alice", "9876543210", "alice@example.com");
        Guest g2 = new Guest("G002", "Bob", "9876543211", "bob@example.com");
        system.addGuest(g1);
        system.addGuest(g2);

        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n==== Hotel Reservation System ====");
            System.out.println("1. View Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View All Bookings");
            System.out.println("5. Reports");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    for (Room r : system.getRooms()) {
                        System.out.println(r);
                    }
                    break;
                case 2:
                    System.out.print("Enter guest ID (G001/G002): ");
                    String gid = sc.nextLine();
                    Guest guest = gid.equals("G001") ? g1 : g2;

                    System.out.print("Enter room type (Single/Double/Suite): ");
                    String rtype = sc.nextLine();
                    System.out.print("Enter check-in date: ");
                    String in = sc.nextLine();
                    System.out.print("Enter check-out date: ");
                    String out = sc.nextLine();
                    System.out.print("Enter number of nights: ");
                    int nights = sc.nextInt();
                    sc.nextLine();

                    system.makeReservation("B" + (Booking.getTotalBookings() + 1),
                            guest, rtype, in, out, nights);
                    break;
                case 3:
                    System.out.print("Enter booking ID to cancel: ");
                    String bid = sc.nextLine();
                    system.cancelReservation(bid);
                    break;
                case 4:
                    system.showAllBookings();
                    break;
                case 5:
                    System.out.println("Occupancy Rate: " +
                            ReservationSystem.getOccupancyRate(system.getRooms()) + "%");
                    System.out.println("Total Revenue: $" + ReservationSystem.getTotalRevenue());
                    System.out.println("Most Popular Room Type: " + system.getMostPopularRoomType());
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);

        sc.close();
    }
}
