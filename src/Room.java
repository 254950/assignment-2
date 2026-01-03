import java.util.Objects;

public class Room {
    private int number;
    private double price;
    private boolean available;

    public Room(int number, double price) {
        this.number = number;
        this.price = price;
        this.available = true;
    }

    public int getNumber() { return number; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "Room{number=" + number + ", price=" + price + ", available=" + available + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return number == room.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}