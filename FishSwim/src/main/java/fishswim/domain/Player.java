package fishswim.domain;

/**
 * Player object is used when saving data to database or retrieving player data
 * from database
 */
public class Player {

    private String name;
    private int points;

    /**
     * Player constructor
     *
     * @param name Player name
     * @param points Players points
     */
    public Player(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

}
