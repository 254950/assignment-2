import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RoomDao roomDao = new RoomDao();
        GuestDao guestDao = new GuestDao();

        DataPool pool = new DataPool();

        while (true) {
            System.out.println("\n=== HOTEL BOOKING SYSTEM ===");
            System.out.println("1) Load data from DB to DataPool");
            System.out.println("2) Add room (DB)");
            System.out.println("3) List rooms (DB)");
            System.out.println("4) Update room availability (DB)");
            System.out.println("5) Delete room (DB)");
            System.out.println("6) Add guest (DB)");
            System.out.println("7) List guests (DB)");
            System.out.println("8) Search guests by name (DataPool)");
            System.out.println("9) Sort rooms by price (DataPool)");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            String choice = sc.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> {
                        pool.getRooms().clear();
                        pool.getGuests().clear();

                        List<Room> rooms = roomDao.findAll();
                        List<Guest> guests = guestDao.findAll();

                        pool.getRooms().addAll(rooms);
                        pool.getGuests().addAll(guests);

                        System.out.println("Loaded: rooms=" + rooms.size() + ", guests=" + guests.size());
                    }

                    case "2" -> {
                        System.out.print("Room number: ");
                        String number = sc.nextLine();
                        System.out.print("Type (Single/Double/Suite): ");
                        String type = sc.nextLine();
                        System.out.print("Price per night: ");
                        double price = Double.parseDouble(sc.nextLine());
                        System.out.print("Available (true/false): ");
                        boolean available = Boolean.parseBoolean(sc.nextLine());

                        Room r = new Room(0, number, type, price, available);
                        long id = roomDao.insert(r);
                        System.out.println("Inserted room with id=" + id);
                    }

                    case "3" -> roomDao.findAll().forEach(System.out::println);

                    case "4" -> {
                        System.out.print("Room id: ");
                        long id = Long.parseLong(sc.nextLine());
                        System.out.print("New available (true/false): ");
                        boolean av = Boolean.parseBoolean(sc.nextLine());
                        System.out.println("Updated: " + roomDao.updateAvailability(id, av));
                    }

                    case "5" -> {
                        System.out.print("Room id: ");
                        long id = Long.parseLong(sc.nextLine());
                        System.out.println("Deleted: " + roomDao.deleteById(id));
                    }

                    case "6" -> {
                        System.out.print("Full name: ");
                        String name = sc.nextLine();
                        System.out.print("Phone: ");
                        String phone = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();

                        Guest g = new Guest(0, name, phone, email);
                        long id = guestDao.insert(g);
                        System.out.println("Inserted guest with id=" + id);
                    }

                    case "7" -> guestDao.findAll().forEach(System.out::println);

                    case "8" -> {
                        System.out.print("Keyword: ");
                        String k = sc.nextLine();
                        pool.searchGuestsByName(k).forEach(System.out::println);
                    }

                    case "9" -> pool.sortRoomsByPriceAsc().forEach(System.out::println);

                    case "0" -> {
                        System.out.println("Bye!");
                        return;
                    }

                    default -> System.out.println("Unknown option.");
                }
            } catch (SQLException e) {
                System.out.println("DB error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}