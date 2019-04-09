package fishswim.domain;

import javafx.scene.shape.Rectangle;
import java.util.Random;
import javafx.scene.shape.Shape;

public class Obstacle implements GameObject {

    private Rectangle upperObstacle;
    private Rectangle lowerObstacle;
    private int gap;
    private double x;

    public Obstacle() {
        this.gap = 125;
        this.upperObstacle = new Rectangle();
        this.lowerObstacle = new Rectangle();
        this.generateObstacle();
        this.x = upperObstacle.getX();
    }

    public double getX() {
        return x;
    }

    public boolean checkCollision(Fish fish) {
        if (upperObstacle.intersects(fish.boundsInLocalProperty().get())) {
            return true;
        } else if (lowerObstacle.intersects(fish.boundsInLocalProperty().get())) {
            return true;
        }
        return false;
    }

    @Override
    public void move() {
        if (this.upperObstacle.getX() < -50) {
            this.generateObstacle();
        }
        this.upperObstacle.setX(this.upperObstacle.getX() - 1);
        this.lowerObstacle.setX(this.lowerObstacle.getX() - 1);
        this.x = upperObstacle.getX();
    }

    public Rectangle[] getObstacles() {
        Rectangle[] rects = {this.upperObstacle, this.lowerObstacle};
        return rects;
    }

    private void generateObstacle() {
        Random r = new Random();
        double y = r.nextInt(175) + 50;

        this.upperObstacle.setX(400);
        this.upperObstacle.setY(0);
        this.upperObstacle.setWidth(50);
        this.upperObstacle.setHeight(y);

        this.lowerObstacle.setX(400);
        this.lowerObstacle.setY(y + gap);
        this.lowerObstacle.setWidth(50);
        this.lowerObstacle.setHeight(400 - y - gap);

    }
}
