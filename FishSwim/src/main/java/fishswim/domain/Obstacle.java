package fishswim.domain;

import javafx.scene.shape.Rectangle;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 *
 * @author Riku
 */
public class Obstacle implements GameObject {

    private final Rectangle upperObstacle;
    private final Rectangle lowerObstacle;
    private final int gap;
    private double x;
    private final double speed;

    /**
     * Obstacle constructor
     *
     * @param speed -Obstacle moving speed
     */
    public Obstacle(double speed) {
        this.gap = 125;
        this.upperObstacle = new Rectangle();
        this.lowerObstacle = new Rectangle();
        this.generateObstacle();
        this.x = upperObstacle.getX();
        this.speed = speed;
    }

    /**
     * Method makes obstacles look fancier with self made graphics.
     */
    public void setImg() {
        Image hook = new Image("hook.png");
        Image weed = new Image("weed.png");
        this.upperObstacle.setFill(new ImagePattern(hook));
        this.lowerObstacle.setFill(new ImagePattern(weed));
    }

    public Rectangle getUpperObstacle() {
        return upperObstacle;
    }

    public Rectangle getLowerObstacle() {
        return lowerObstacle;
    }

    public double getX() {
        return x;
    }

    /**
     * Method checks if fish character hits upper or lower obstacle
     *
     * @param fish player character
     * @return TRUE if fish collide with obstacle, else false
     */
    public boolean checkCollision(Fish fish) {
        if (upperObstacle.intersects(fish.boundsInLocalProperty().get())) {
            return true;
        } else if (lowerObstacle.intersects(fish.boundsInLocalProperty().get())) {
            return true;
        }
        return false;
    }

    /**
     * Method moves upper and lower obstacle if obstacle goes out of bounds it
     * calls generateObstacle method to create new obstacles.
     *
     * @see Obstacle#generateObstacle()
     */
    @Override
    public void move() {
        this.upperObstacle.setX(this.upperObstacle.getX() - speed);
        this.lowerObstacle.setX(this.lowerObstacle.getX() - speed);
        if (this.upperObstacle.getX() < -50) {
            this.generateObstacle();
        }
        this.x = upperObstacle.getX();
    }

    /**
     * Method generates new obstacles with random y value which makes obstacle
     * gap to change place.
     */
    public void generateObstacle() {
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
