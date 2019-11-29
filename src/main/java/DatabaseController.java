import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseController {

    private transient Connection conn;
    private transient PreparedStatement ps;
    private transient ResultSet rs;
    private static final String driver = "com.mysql.jdbc.Driver";

    private static final String URL = "jdbc:mysql://"
            + "projects-db.ewi.tudelft.nl/"
            + "projects_sem-project-42"
            + "?sslmode=require"
            + "&user=pu_f1X2r36H3wklF"
            + "&password=ZL3Al54DKc66"
            + "&serverTimezone=UCT";

    private void closeConnections() {
        try {
            rs.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            ps.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            conn.close();
        } catch (Exception e) {
            System.out.println(e.toString());

        }
    }

    /**
     * Update the points for a user in the database.
     * @param userId id of the user
     * @param points number of point to be added
     */
    public void updatePoints(int userId, int points) {

        try {

            Class.forName(driver);
            conn = DriverManager.getConnection(URL);
            String query = "update user_data set points = points + ? where user_id = ?";
            ps = conn.prepareStatement(query);

            ps.setInt(1, points);
            ps.setInt(2, userId);

            ps.execute();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.toString());
        } finally {
            closeConnections();
        }
    }

    /**
     * Save the game details in a database after a game ends.
     * @param userId1 id of the first player
     * @param userId2 id of the second player
     * @param score1 score of the first player
     * @param score2 score of the second player
     */
    public void saveGame(int userId1, int userId2, int score1, int score2) {

        try {

            Class.forName(driver);
            conn = DriverManager.getConnection(URL);
            String query = "insert into game (user_id_1, user_id_2, "
                        + "score_user_1, score_user_2, game_timestamp)"
                        + " values (?,?,?,?,?)";
            ps = conn.prepareStatement(query);

            ps.setInt(1, userId1);
            ps.setInt(2, userId2);
            ps.setInt(3, score1);
            ps.setInt(4, score2);
            ps.setLong(5, System.currentTimeMillis());

            ps.execute();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.toString());
        } finally {
            closeConnections();
        }
    }

    /**
     * Get the number of points for a user from database.
     * @param userId id of the user
     * @return number of points the user has
     */
    public int getPoints(int userId) {

        try {

            Class.forName(driver);
            conn = DriverManager.getConnection(URL);
            String query = "select points from user_data where user_id = ?";
            ps = conn.prepareStatement(query);

            ps.setInt(1, userId);

            int points = 0;
            rs = ps.executeQuery();
            while (rs.next()) {
                points += rs.getInt(1);
            }

            return points;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.toString());
        } finally {
            closeConnections();
        }
        return 0;
    }

    /**
     * Get the number of games a user has played.
     * @param userId id of the user
     * @return the number of games a user has played
     */
    public int getGamesPlayed(int userId) {

        try {

            Class.forName(driver);
            conn = DriverManager.getConnection(URL);
            String query = "select count(game_id) from game"
                        + " where user_id_1 = ? or user_id_2 = ?";
            ps = conn.prepareStatement(query);

            ps.setInt(1, userId);
            ps.setInt(2, userId);

            int gamesPlayed = 0;
            rs = ps.executeQuery();
            while (rs.next()) {
                gamesPlayed += rs.getInt(1);
            }

            return gamesPlayed;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.toString());
        } finally {
            closeConnections();
        }
        return 0;
    }
}
