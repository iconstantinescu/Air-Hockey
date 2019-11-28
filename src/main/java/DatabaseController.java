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
            ps.close();

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
}
