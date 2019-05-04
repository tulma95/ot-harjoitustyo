package fishswim.domain;

import javafx.scene.shape.Circle;

/**
 *
 * @author Riku
 */
public class Fish extends Circle implements GameObject {

    private double ySpeed;
    private double gravity;
    private final double SCREEN_HEIGHT;

    /**
     * Fish constructor sets fish`s starting location and screen height which is
     * used to calculate fish`s moving bounds
     *
     * @param x -Fish starting x coordinate
     * @param y -Fish starting y coordinate
     * @param height -Game screen height
     */
    public Fish(double x, double y, double height) {
        super(x, y, 15);
        this.ySpeed = 1;
        this.gravity = 0.2;
        this.SCREEN_HEIGHT = height;
    }

    /**
     * Method updates fish location according to it's y-axis speed. Also
     * prevents fish from going out of bounds. Speed is increasing
     */
    @Override
    public void move() {
        if (this.getCenterY() < this.radiusProperty().get()) {
            this.setCenterY(15);
            this.ySpeed = 0;
        } else if (this.getCenterY() > SCREEN_HEIGHT - this.radiusProperty().get()) {
            this.setCenterY(385);
            this.ySpeed = 0;
        } else {
            this.setCenterY((this.getCenterY() + ySpeed));
        }
        this.ySpeed += gravity;
    }

    /**
     * Method changes fish y-axis movement direction by changing ySpeed. Fish
     * speed can't go below -7.5
     */
    public void swimUp() {
        this.ySpeed -= gravity * 40;
        if (this.ySpeed < -7.5) {
            this.ySpeed = -7.5;
        }
    }

    public double getySpeed() {
        return ySpeed;
    }

}
