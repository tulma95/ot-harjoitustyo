package fishswim.domain;

import java.util.concurrent.atomic.AtomicInteger;
import javafx.scene.shape.Rectangle;

public class GameLogic {

    private Obstacle obstacle;
    private Fish fish;
    private AtomicInteger points;

    public GameLogic(Fish fish, Obstacle obstacle) {
        this.fish = fish;
        this.obstacle = obstacle;
        this.points = new AtomicInteger();
    }

    /**
     * Method moves all game characters
     */
    public void moveAll() {
        this.fish.move();
        this.obstacle.move();
        if (Math.floor(this.obstacle.getX()) == 32) {
            points.incrementAndGet();
        }

    }

    /**
     * Checks if fish has hit obstacle
     *
     * @return True if fish has not hit obstacle, else false
     */
    public boolean continueGame() {
        return !obstacle.checkCollision(fish);
    }

    public Rectangle[] getObstacles() {
        return new Rectangle[]{obstacle.getLowerObstacle(), obstacle.getUpperObstacle()};
    }

    public Fish getFish() {
        return fish;
    }

    public AtomicInteger getPoints() {
        return points;
    }

}
