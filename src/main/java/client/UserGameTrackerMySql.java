package client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that contains the methods required to save and retrieve
 * user points and match details to/from the database.
 */
public class UserGameTrackerMySql extends DatabaseControllerMySql implements UserGameTracker {

    /**
     * Constructor of the class.
     * @param connectionFactory the connectionFactory object that facilitates
     *                          the creation of a new database connection
     */
    public UserGameTrackerMySql(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }


    /**
     * Update the user values in the database with the new values.
     * @param user the user object with the new values
     * @return true if update succeeded
     */
    public boolean updateUserStats(User user) {

        try {

            conn = connectionFactory.createConnection(URL);
            String query = "update user_data set nickname=?, points=?, games_won=?, games_lost=?"
                    + " where user_id = ?";
            ps = conn.prepareStatement(query);

            ps.setString(1, user.getNickname());
            ps.setLong(2, user.getPoints());
            ps.setInt(3, user.getNumOfWonGames());
            ps.setInt(4, user.getNumOfLostGames());
            ps.setInt(5, user.getUserID());

            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            closeConnections();
        }

        return false;
    }

    /**
     * Save the game details in a database after a game ends.
     * @param userId1 id of the first player
     * @param userId2 id of the second player
     * @param score1 score of the first player
     * @param score2 score of the second player
     */
    public boolean saveGame(int userId1, int userId2, int score1, int score2) {

        try {

            conn = connectionFactory.createConnection(URL);
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
            return true;

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            closeConnections();
        }
        return false;
    }

    @Override
    public List<GameDetails> getGameHistory(int userId) {

        List<GameDetails> gamesList = new ArrayList<>();

        try {

            conn = connectionFactory.createConnection(URL);

            String query = "select * from game"
                    + " where user_id_1 = ? or user_id_2 = ?";


            ps = conn.prepareStatement(query);

            ps.setInt(1, userId);
            ps.setInt(2, userId);

            rs = ps.executeQuery();

            while (rs.next()) {

                GameDetails game = new GameDetails();
                game.setNickname1(getNicknameById(rs.getInt("user_id_1")));
                game.setScoreUser1(rs.getInt("score_user_1"));
                game.setNickname2(getNicknameById(rs.getInt("user_id_2")));
                game.setScoreUser2(rs.getInt("score_user_2"));
                game.setTimestamp(new Timestamp(rs.getLong("game_timestamp")));

                gamesList.add(game);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            closeConnections();
        }

        return gamesList;
    }


    // We are actually closing the resultSet in all the cases
    // but the PMD does not see this for some reason
    @SuppressWarnings("PMD.CloseResource")
    private String getNicknameById(int userId) {

        try {

            // Nickname might change so we keep the userIDs in the database
            String query = "select nickname from user_data"
                    + " where user_id = ?";


            ps = conn.prepareStatement(query);

            ps.setInt(1, userId);

            ResultSet newRs = ps.executeQuery();

            if (newRs.next()) {
                String nickname = newRs.getString("nickname");
                newRs.close();
                return nickname;
            }

            newRs.close();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return "";
    }

}
