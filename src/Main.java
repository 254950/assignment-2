import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static int nextGuestId = 1;
    private static int nextBookingId = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Guest> guests = new ArrayList<>();
        ArrayList<Booking> bookings = new ArrayList<>();

        rooms.add(new Room(101, 15000));
        rooms.add(new Room(102, 22000));
        rooms.add(new Room(201, 50000));

        while (true) {
            System.out.println("\n1 Add guest | 2 Add room | 3 Book room | 4 Available rooms | 5 Search guest | 6 Sort rooms | 0 Exit");
            System.out.print("Choose: ");
            String c = sc.nextLine().trim();

            try {
                switch (c) {
                    case "1": {
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        Guest g = new Guest(nextGuestId++, name, email);
                        guests.add(g);

                        Person p = g;
                        System.out.println("Added: " + p);
                        break;
                    }

                    case "2": {
                        System.out.print("Room number: ");
                        int number = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Price: ");
                        double price = Double.parseDouble(sc.nextLine().trim());
                        Room r = new Room(number, price);
                        rooms.add(r);
                        System.out.println("Added: " + r);
                        break;
                    }

                    case "3": {
                        System.out.print("Guest ID: ");
                        int gid = Integer.parseInt(sc.nextLine().trim());
                        Optional<Guest> gOpt = guests.stream().filter(x -> x.getId() == gid).findFirst();
                        if (gOpt.isEmpty()) { System.out.println("Guest not found"); break; }

                        System.out.print("Room number: ");
                        int rn = Integer.parseInt(sc.nextLine().trim());
                        Optional<Room> rOpt = rooms.stream().filter(x -> x.getNumber() == rn).findFirst();
                        if (rOpt.isEmpty()) { System.out.println("Room not found"); break; }
                        if (!rOpt.get().isAvailable()) { System.out.println("Room not available"); break; }

                        System.out.print("Nights: ");
                        int nights = Integer.parseInt(sc.nextLine().trim());

                        Room room = rOpt.get();
                        room.setAvailable(false);

                        Booking b = new Booking(nextBookingId++, gOpt.get(), room, nights);
                        bookings.add(b);

                        Payable pay = b;
                        System.out.println("Created: " + b);
                        System.out.println("Total via Payable: " + pay.calculateTotal());
                        break;
                    }

                    case "4": {
                        System.out.println("Available rooms:");
                        for (Room r : rooms) if (r.isAvailable()) System.out.println(r);
                        break;
                    }

                    case "5": {
                        System.out.print("Keyword: ");
                        String k = sc.nextLine().toLowerCase();
                        System.out.println("Guests:");
                        for (Guest g : guests)
                            if (g.getName().toLowerCase().contains(k)) System.out.println(g);
                        break;
                    }

                    case "6": {
                        rooms.sort(Comparator.comparingDouble(Room::getPrice));
                        System.out.println("Rooms sorted by price:");
                        for (Room r : rooms) System.out.println(r);
                        break;
                    }

                    case "0":
                        return;

                    default:
                        System.out.println("Invalid");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
