package client;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * The class is used to keep the Leaderboard List with the users and the scores
 * that will be displayed after the game ends or in the main menu.
 * The class allows leaderboards of variable sizes to be created.
 *</p>
 * To get the leaderboard from the database, the LeaderboardDao class will be used:
 * Leaderboard newLeaderboard = leaderboardDao.getLeaderboard(int size);
 */
public class Leaderboard {

    public List<LeaderboardEntry> leaderboardList;
    public int sizeLimit;

    /**
     * Specify just the size when creating.
     */
    public Leaderboard(int sizeLimit) {
        this.sizeLimit = sizeLimit;
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

        if (this.leaderboardList.size() > newSize) {
            this.leaderboardList.subList(0, newSize);
        }
    }

    /**
     * Get the current size of the leaderboard list.
     * @return the current size of the leaderboard list
     */
    public int getCurrentSize() {
        return this.leaderboardList.size();
    }

    //The hashCode is not needed for our application.
    //We just use the equals method.
    @SuppressWarnings("PMD.OverrideBothEqualsAndHashcode")
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Leaderboard that = (Leaderboard) o;
        return sizeLimit == that.sizeLimit
                && Objects.equals(leaderboardList, that.leaderboardList);
    }

}
