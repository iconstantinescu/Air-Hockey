package client;

import java.util.List;

public class Leaderboard {

    public List<LeaderboardEntry> leaderboardList;
    public int size;

    /**
     * Empty Constructor.
     */
    public Leaderboard() {

    }

    /**
     * Class constructor.
     * @param leaderboardList arrayList with leaderboard entries
     * @param size the maximum allowed size of the leaderboard list
     */
    public Leaderboard(List<LeaderboardEntry> leaderboardList, int size) {
        this.size = size;

        if (leaderboardList.size() <= this.size) {
            this.leaderboardList = leaderboardList;
        } else {
            this.leaderboardList = leaderboardList.subList(0, this.size);
        }
    }

    /**
     * Leaderboard list getter.
     * @return the list with all the leaderboard entries
     */
    public List<LeaderboardEntry> getLeaderboardList() {
        return this.leaderboardList;
    }

    /**
     * Leaderboard list setter.
     * @param leaderboardList the list with all the leaderboard entries
     */
    public void setLeaderboardList(List<LeaderboardEntry> leaderboardList) {

        if (leaderboardList.size() <= this.size) {
            this.leaderboardList = leaderboardList;
        } else {
            this.leaderboardList = leaderboardList.subList(0, this.size);
        }
    }

    /**
     * Add a new entry to the leaderboard.
     * @param newEntry the new entry to be added
     * @return true if added or false if cannot add (max size reached)
     */
    public boolean addEntry(LeaderboardEntry newEntry) {

        if (leaderboardList.size() < this.size) {
            leaderboardList.add(newEntry);
        } else {
            return false;
        }

        return true;
    }

    /**
     * Return the max size of the leaderboard (not current size).
     * @return the maximum leaderboard size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Change the maximum size of the leaderboard.
     * @param newSize the new size
     */
    public void setSize(int newSize) {
        this.size = newSize;
    }

}
