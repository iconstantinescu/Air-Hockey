import java.sql.*;

public class DatabaseController {

    private transient Connection conn;
    private transient PreparedStatement ps;
    private transient ResultSet rs;

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


    public void updatePoints(int userId, int points) {

        try {

            conn = DriverManager.getConnection(URL);
            String query = "update user_data set points = points + ? where user_id = ?";
            ps = conn.prepareStatement(query);

            ps.setInt(1, points);
            ps.setInt(2, userId);

            ps.execute();

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            closeConnections();
        }
    }

    public void saveGame(int userId_1, int userId_2, int score_1, int score_2) {

        try {

            conn = DriverManager.getConnection(URL);
            String query = "insert into game (user_id_1, user_id_2, score_user_1, score_user_2, game_timestamp)"
                     + " values (?,?,?,?,?)";
            ps = conn.prepareStatement(query);

            ps.setInt(1, userId_1);
            ps.setInt(2, userId_2);
            ps.setInt(3, score_1);
            ps.setInt(4, score_2);
            ps.setLong(5, System.currentTimeMillis());

            ps.execute();

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            closeConnections();
        }
    }

    public int getPoints(int userId) {

        try {

            conn = DriverManager.getConnection(URL);
            String query = "select points from user_data where user_id = ?";
            ps = conn.prepareStatement(query);

            ps.setInt(1, userId);

            int points = 0;
            rs = ps.executeQuery();
            while (rs.next())
                points += rs.getInt(1);

            return points;

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            closeConnections();
        }
        return 0;
    }

    public int getGamesPlayed(int userId) {

        try {

            conn = DriverManager.getConnection(URL);
            String query = "select count(game_id) from game" +
                    " where user_id_1 = ? or user_id_2 = ?";
            ps = conn.prepareStatement(query);

            ps.setInt(1, userId);
            ps.setInt(2, userId);

            int gamesPlayed = 0;
            rs = ps.executeQuery();
            while (rs.next())
                gamesPlayed += rs.getInt(1);

            return gamesPlayed;

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            closeConnections();
        }
        return 0;
    }
}
