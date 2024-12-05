import java.sql.*;
import java.util.ArrayList;

public class Database {
    private String url = "jdbc:mysql://localhost:3306/basketdash";
    private String username = "root";
    private String password = "";

    private Connection connect() throws SQLException{
        return DriverManager.getConnection(url, username, password);
    }

    public void addPlayer(Player player) throws SQLException{
        String query = "INSERT INTO player(nama, score) VALUES (?, ?)";
        try(Connection conn = connect();
        PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1, player.getNama());
            statement.setInt(2, player.getScore());
            statement.executeUpdate();
            System.out.println("Data berhasil ditambahkan!!!");
        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    public ArrayList<Player> getPlayers() throws SQLException{
        String query = "SELECT * FROM player";
        ArrayList<Player> players = new ArrayList<>();

        try (Connection conn = connect(); Statement statement = conn.createStatement(); ResultSet rs = statement.executeQuery(query)){
            while (rs.next()) {
                Player getPlayer = new Player(rs.getString("nama"), rs.getInt("score"));
                players.add(getPlayer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return players;
    }
    
}
