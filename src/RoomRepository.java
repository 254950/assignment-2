import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RoomRepository {
    private final RoomDao roomDao = new RoomDao();

    public long addRoom(Room room) throws SQLException {
        return roomDao.insert(room);
    }

    public List<Room> getAllRooms() throws SQLException {
        return roomDao.findAll();
    }

    public Optional<Room> getRoomById(long id) throws SQLException {
        return roomDao.findById(id);
    }

    public boolean updateAvailability(long id, boolean available) throws SQLException {
        return roomDao.updateAvailability(id, available);
    }

    public boolean deleteRoom(long id) throws SQLException {
        return roomDao.deleteById(id);
    }
}