import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDao {

    public long insert(Room room) throws SQLException {
        String sql = "INSERT INTO rooms(room_number, type, price_per_night, available) " +
                "VALUES (?,?,?,?) RETURNING id";

        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, room.getRoomNumber());
            ps.setString(2, room.getType());
            ps.setDouble(3, room.getPricePerNight());
            ps.setBoolean(4, room.isAvailable());

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getLong(1);
            }
        }
    }

    public List<Room> findAll() throws SQLException {
        String sql = "SELECT id, room_number, type, price_per_night, available " +
                "FROM rooms ORDER BY id";

        List<Room> list = new ArrayList<>();

        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Room r = new Room(
                        rs.getLong("id"),
                        rs.getString("room_number"),
                        rs.getString("type"),
                        rs.getDouble("price_per_night"),
                        rs.getBoolean("available")
                );
                list.add(r);
            }
        }

        return list;
    }

    public Optional<Room> findById(long id) throws SQLException {
        String sql = "SELECT id, room_number, type, price_per_night, available " +
                "FROM rooms WHERE id = ?";

        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();

                Room r = new Room(
                        rs.getLong("id"),
                        rs.getString("room_number"),
                        rs.getString("type"),
                        rs.getDouble("price_per_night"),
                        rs.getBoolean("available")
                );

                return Optional.of(r);
            }
        }
    }

    public boolean updateAvailability(long id, boolean available) throws SQLException {
        String sql = "UPDATE rooms SET available = ? WHERE id = ?";

        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setBoolean(1, available);
            ps.setLong(2, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean updatePrice(long id, double newPrice) throws SQLException {
        String sql = "UPDATE rooms SET price_per_night = ? WHERE id = ?";

        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDouble(1, newPrice);
            ps.setLong(2, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteById(long id) throws SQLException {
        String sql = "DELETE FROM rooms WHERE id = ?";

        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
