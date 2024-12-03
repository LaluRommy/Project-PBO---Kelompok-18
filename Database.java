import java.sql.*;

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
    
}
