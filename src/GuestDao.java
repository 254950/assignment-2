import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestDao {

    public long insert(Guest g) throws SQLException {
        String sql = "INSERT INTO guests(full_name, phone, email) VALUES (?,?,?) RETURNING id";
        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, g.getFullName());
            ps.setString(2, g.getPhone());
            ps.setString(3, g.getEmail());
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getLong(1);
            }
        }
    }

    public List<Guest> findAll() throws SQLException {
        String sql = "SELECT id, full_name, phone, email FROM guests ORDER BY id";
        List<Guest> list = new ArrayList<>();
        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Guest(
                        rs.getLong("id"),
                        rs.getString("full_name"),
                        rs.getString("phone"),
                        rs.getString("email")
                ));
            }
        }
        return list;
    }

    public boolean updatePhone(long id, String phone) throws SQLException {
        String sql = "UPDATE guests SET phone = ? WHERE id = ?";
        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, phone);
            ps.setLong(2, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteById(long id) throws SQLException {
        String sql = "DELETE FROM guests WHERE id = ?";
        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}