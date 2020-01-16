package client;

/**
 * This interface is created as part of the DAO design pattern to
 * achieve a clear separation between database logic and game logic.
 *
 * The interface specifies the required methods that need to be implemented
 * by any class that will retrieve the leaderboard object from
 * any type of data storage. Therefore the database choice does not affect the
 * parts of the code where this interface is used.
 * Any leaderboard related operations will be done through the LeaderboardDao.
 *
 * Example usage for MySql implementation:
 * LeaderboardDao leaderboardDao = new LeaderboardDaoMySql().
 */
public interface LeaderboardDao {

    Leaderboard getLeaderboard(int size);

    int getLeaderboardPosition(int userId);

}
