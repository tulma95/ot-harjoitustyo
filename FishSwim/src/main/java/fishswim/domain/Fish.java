package fishswim.domain;

import javafx.scene.shape.Circle;

public class Fish extends Circle implements GameObject {

    private double ySpeed;
    private double gravity;
    private double acc;
    private final double HEIGHT;

    public Fish(double x, double y) {
        super(x, y, 15);
        this.ySpeed = 1;
        this.gravity = 0.05;
        this.acc = 1;
        this.HEIGHT = 400;
    }

    public Fish(double x, double y, double height) {
        super(x, y, 15);
        this.ySpeed = 1;
        this.gravity = 0.05;
        this.acc = 1;
        this.HEIGHT = height;
    }

    @Override
    public void move() {
        if (this.getCenterY() < this.radiusProperty().get()) {
            this.setCenterY(15);
            this.ySpeed = 0;
            this.acc = 0;
        } else if (this.getCenterY() > HEIGHT - this.radiusProperty().get()) {
            this.setCenterY(385);
            this.ySpeed = 0;
            this.acc = 0;
        } else {
            this.setCenterY((this.getCenterY() + ySpeed));
        }
        this.ySpeed += gravity;

    }

    public void swimUp() {
        this.ySpeed -= gravity * 75;
    }

}
