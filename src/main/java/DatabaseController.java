import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseController {

    private static final String URL = "jdbc:postgresql://"
            + "ec2-54-247-92-167.eu-west-1.compute.amazonaws.com/"
            + "d5d8vck9rbk2st"
            + "?sslmode=require"
            + "&user=oogthbenpdpynf"
            + "&password=3d8cc31cbcff76c86090f97607c3dcb3c66e2a0978fc5251c23a7e184488cef0";


    private static void testSetScore(int userId, int points) {
        try {
            Connection connection = DriverManager.getConnection(URL);

            String query = "update user_data set points = points + ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, points);
            preparedStatement.setInt(2, userId);


            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.toString());
        }
    }
}
