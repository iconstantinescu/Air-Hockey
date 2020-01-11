package client;

import java.util.List;

public class Leaderboard {

    public List<LeaderboardEntry> leaderboardList;
    public int size;

    public Leaderboard(){}

    public Leaderboard(List<LeaderboardEntry> leaderboardList, int size) {
        this.size = size;

        if(leaderboardList.size() <= this.size)
            this.leaderboardList = leaderboardList;
        else
            this.leaderboardList = leaderboardList.subList(0, this.size);
    }

    public List<LeaderboardEntry> getLeaderboardList() {
        return this.leaderboardList;
    }

    public void setLeaderboardList(List<LeaderboardEntry> leaderboardList) {

        if(leaderboardList.size() <= this.size)
            this.leaderboardList = leaderboardList;
        else
            this.leaderboardList = leaderboardList.subList(0, this.size);
    }

    public boolean addEntry(LeaderboardEntry newEntry) {

        if(leaderboardList.size() < this.size)
            leaderboardList.add(newEntry);
        else
            return false;

        return true;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int newSize) {
        this.size = newSize;
    }
    
}
