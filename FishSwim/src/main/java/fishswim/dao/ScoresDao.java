package fishswim.dao;

import fishswim.domain.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ScoresDao {

    private String url;

    public ScoresDao() {
        this.url = "jdbc:sqlite:scores.db";
        initDatabase();
    }

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private void initDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS Scores ("
                + "id integer PRIMARY KEY, "
                + "name VARCHAR(20) NOT NULL, "
                + "score INTEGER);";

        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveScores(Player player) {
        String sql = "INSERT INTO Scores(name, score) VALUES(?,?)";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, player.getName());
            pstmt.setInt(2, player.getPoints());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Player> getScores() {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT name, score FROM Scores ORDER BY score DESC";
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                players.add(new Player(rs.getString("name"), rs.getInt("score")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return players;
    }

}
