import java.util.Objects;

public class Booking implements Payable {
    private final int id;
    private final Guest guest;
    private final Room room;
    private int nights;
    private boolean active;

    public Booking(int id, Guest guest, Room room, int nights) {
        this.id = id;
        this.guest = guest;
        this.room = room;
        this.nights = nights;
        this.active = true;
    }

    public int getId() { return id; }
    public Guest getGuest() { return guest; }
    public Room getRoom() { return room; }
    public boolean isActive() { return active; }

    public void cancel() {
        if (!active) return;
        active = false;
        room.setAvailable(true);
    }

    @Override
    public double calculateTotal() {
        return active ? room.getPrice() * nights : 0.0;
    }

    @Override
    public String toString() {
        return "Booking{id=" + id +
                ", guest=" + guest.getName() +
                ", room=" + room.getNumber() +
                ", nights=" + nights +
                ", active=" + active +
                ", total=" + calculateTotal() +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return id == booking.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
interface Payable {
    double calculateTotal();
}