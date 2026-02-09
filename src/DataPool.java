import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class DataPool {
    private final List<Room> rooms = new ArrayList<>();
    private final List<Guest> guests = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();

    public List<Room> getRooms() { return rooms; }
    public List<Guest> getGuests() { return guests; }
    public List<Booking> getBookings() { return bookings; }

    public List<Room> filterAvailableRooms() {
        List<Room> result = new ArrayList<>();
        for (Room r : rooms) {
            if (r.isAvailable()) result.add(r);
        }
        return result;
    }

    public Optional<Room> findRoomByNumber(String number) {
        for (Room r : rooms) {
            if (r.getRoomNumber().equalsIgnoreCase(number)) {
                return Optional.of(r);
            }
        }
        return Optional.empty();
    }

    public List<Room> sortRoomsByPriceAsc() {
        List<Room> copy = new ArrayList<>(rooms);
        copy.sort(Comparator.comparingDouble(Room::getPricePerNight));
        return copy;
    }

    public List<Guest> searchGuestsByName(String keyword) {
        String k = keyword.toLowerCase();
        List<Guest> result = new ArrayList<>();
        for (Guest g : guests) {
            if (g.getFullName() != null && g.getFullName().toLowerCase().contains(k)) {
                result.add(g);
            }
        }
        return result;
    }
}