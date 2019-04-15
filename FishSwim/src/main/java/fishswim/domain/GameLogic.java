package fishswim.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class GameLogic {

    private Obstacle obstacle;
    private Fish fish;
    private AtomicInteger points;

    public GameLogic(Fish fish, Obstacle obstacle) {
        this.fish = fish;
        this.obstacle = obstacle;
        this.points = new AtomicInteger();
    }

    public void moveAll() {
        this.fish.move();
        this.obstacle.move();
        if (Math.floor(this.obstacle.getX()) == 32) {
            points.incrementAndGet();
        }

    }

    public boolean continueGame() {
        return !obstacle.checkCollision(fish);
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public Fish getFish() {
        return fish;
    }

    public AtomicInteger getPoints() {
        return points;
    }

}
