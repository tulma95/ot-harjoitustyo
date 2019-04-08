package fishswim.domain;

import javafx.scene.shape.Circle;

public class Fish extends Circle implements GameObject {

    private double ySpeed;
    private double gravity;
    private double acc;

    public Fish(double x, double y) {
        super(x, y, 15);
        this.ySpeed = 1;
        this.gravity = 0.05;
        this.acc = 1;
    }

    @Override
    public void move() {
        if (this.getCenterY() < 15) {
            this.setCenterY(15);
            this.ySpeed = 0;
            this.acc = 0;
        } else if (this.getCenterY() > 385) {
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