package client;

public interface LeaderboardDao {

    Leaderboard getLeaderboard(int size);

    int getLeaderboardPosition(int userId);

}
