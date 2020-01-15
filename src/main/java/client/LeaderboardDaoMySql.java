package client;

import java.sql.SQLException;

public class LeaderboardDaoMySql extends DatabaseControllerMySql implements LeaderboardDao {

    public LeaderboardDaoMySql(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    /**
     * Get the points of all players in descending order.
     * @return a list with nicknames and points of all players in descending order
     */
    @Override
    public Leaderboard getLeaderboard(int size) {

        Leaderboard leaderboard = new Leaderboard();
        leaderboard.setSizeLimit(size);
        try {

            conn = connectionFactory.createConnection(URL);
            String query = "select nickname, points from user_data"
                    + " order by points desc, nickname asc "
                    + "limit ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, size);

            rs = ps.executeQuery();

            while (rs.next()) {
                LeaderboardEntry leaderboardEntry = new LeaderboardEntry(
                        rs.getString(1), rs.getInt(2));
                leaderboard.addEntry(leaderboardEntry);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            closeConnections();
        }
        return leaderboard;
    }

    @Override
    public int getLeaderboardPosition(int userId) {
        try {

            conn = connectionFactory.createConnection(URL);
            String query = "select rnk "
                    + "from (select user_id,"
                    + "      (select count(distinct points) "
                    + "       from user_data where points >= s.points) as rnk"
                    + "      from user_data s "
                    + "     ) as rank_table"
                    + "where user_id= ?";

            ps = conn.prepareStatement(query);

            ps.setInt(1, userId);

            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            closeConnections();
        }
        return -1;
    }
}
