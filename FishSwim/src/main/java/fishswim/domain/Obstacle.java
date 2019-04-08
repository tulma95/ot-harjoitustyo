package fishswim.domain;

import javafx.scene.shape.Rectangle;
import java.util.Random;

public class Obstacle implements GameObject {

    private Rectangle upperObstacle;
    private Rectangle lowerObstacle;
    private int gap;

    public Obstacle() {
        this.gap = 100;
        this.upperObstacle = new Rectangle(400, 0, 50, 150);
        this.lowerObstacle = new Rectangle(400, 250, 50, 150);
    }

    @Override
    public void move() {
        if (this.upperObstacle.getX() < -50) {
            this.upperObstacle.setX(400);
            this.lowerObstacle.setX(400);
        }
        this.upperObstacle.setX(this.upperObstacle.getX() - 1);
        this.lowerObstacle.setX(this.lowerObstacle.getX() - 1);
    }

    public Rectangle getUpperObstacle() {
        return upperObstacle;
    }

    public Rectangle getLowerObstacle() {
        return lowerObstacle;
    }

}
