package client;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard {

    public List<LeaderboardEntry> leaderboardList;
    public int sizeLimit;

    /**
     * Empty Constructor.
     */
    public Leaderboard() {
        this.sizeLimit = 0;
        leaderboardList = new ArrayList<>();
    }

    /**
     * Class constructor.
     * @param leaderboardList arrayList with leaderboard entries
     * @param size the maximum allowed size of the leaderboard list
     */
    public Leaderboard(List<LeaderboardEntry> leaderboardList, int size) {
        this.sizeLimit = size;

        if (leaderboardList.size() <= this.sizeLimit) {
            this.leaderboardList = leaderboardList;
        } else {
            this.leaderboardList = leaderboardList.subList(0, this.sizeLimit);
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

        if (leaderboardList.size() <= this.sizeLimit) {
            this.leaderboardList = leaderboardList;
        } else {
            this.leaderboardList = leaderboardList.subList(0, this.sizeLimit);
        }
    }

    /**
     * Add a new entry to the leaderboard.
     * @param newEntry the new entry to be added
     * @return true if added or false if cannot add (max size reached)
     */
    public boolean addEntry(LeaderboardEntry newEntry) {

        if (leaderboardList.size() < this.sizeLimit) {
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
    public int getSizeLimit() {
        return this.sizeLimit;
    }

    /**
     * Change the maximum size of the leaderboard.
     * If the new size is smaller than the list size, trim the list.
     * @param newSize the new size
     */
    public void setSizeLimit(int newSize) {
        this.sizeLimit = newSize;

        if(this.leaderboardList.size() > newSize) {
            this.leaderboardList.subList(0, newSize);
        }
    }

    public int getCurrentSize() {
        return this.leaderboardList.size();
    }

}
