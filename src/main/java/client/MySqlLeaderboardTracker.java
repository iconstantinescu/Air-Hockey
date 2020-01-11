package client;

import java.sql.SQLException;
import java.util.*;

public class MySqlLeaderboardTracker extends DatabaseController {

    public MySqlLeaderboardTracker(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    /**
     * Get the points of all players in descending order.
     * @return a list with nicknames and points of all players in descending order
     */
    public List<LeaderboardEntry> getAllScoresSorted() {

        List<LeaderboardEntry> leaderboardList = new ArrayList<>();

        try {

            conn = connectionFactory.createConnection(URL);
            String query = "select nickname, points from user_data" +
                    " order by points desc, nickname asc " +
                    "limit 100";
            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                LeaderboardEntry leaderboardEntry = new LeaderboardEntry(
                        rs.getString(1), rs.getInt(2));
                leaderboardList.add(leaderboardEntry);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            closeConnections();
        }
        return leaderboardList;
    }

    /**
     * Get the top five scores achieved in the game.
     * @return a list with the nicknames and points of the top 5 players
     */
    public List<LeaderboardEntry> getTopFive() {
        List<LeaderboardEntry> topFive = getAllScoresSorted();
        return topFive.subList(0,5);
    }
}
