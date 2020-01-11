package client;

import java.sql.SQLException;
import java.util.*;

public class LeaderboardController extends DatabaseController {

    public LeaderboardController(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    /**
     * Get the points of all players in descending order.
     * @return a list with nicknames and points of all players in descending order
     */
    public List<LeaderboardInstance> getAllScoresSorted() {

        List<LeaderboardInstance> leaderboardList = new ArrayList<>();

        try {

            conn = connectionFactory.createConnection(URL);
            String query = "select nickname, points from user_data limit 100";
            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                LeaderboardInstance leaderboardInstance = new LeaderboardInstance();
                leaderboardInstance.setNickname(rs.getString(1));
                leaderboardInstance.setPoints(rs.getInt(2));
                leaderboardList.add(leaderboardInstance);
            }

            Collections.sort(leaderboardList,
                    Comparator.comparing((LeaderboardInstance a) -> a.getNickname().toLowerCase(Locale.ENGLISH)));

            Collections.sort(leaderboardList,
                    (LeaderboardInstance a, LeaderboardInstance b) -> (int)(b.getPoints() - a.getPoints()));

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
    public List<LeaderboardInstance> getTopFive() {
        List<LeaderboardInstance> topFive = getAllScoresSorted();
        return topFive.subList(0,5);
    }
}
