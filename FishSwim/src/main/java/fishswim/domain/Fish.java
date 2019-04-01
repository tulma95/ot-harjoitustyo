package fishswim.domain;

import javafx.scene.shape.Circle;

public class Fish extends Circle {

    private double ySpeed;
    private double gravity;
    private double acc;

    public Fish() {
        super(50, 50, 15);
        this.ySpeed = 1;
        this.gravity = 0.05;
        this.acc = 1;
    }

    public void move() {

//        System.out.println("SPEED " + ySpeed);

        if (this.getCenterY() < 15) {
            this.setCenterY(15);
            this.ySpeed = 0;
            this.acc = 0;
        } else if (this.getCenterY() > 385) {
            this.setCenterY(385);
            this.ySpeed = 0;
            this.acc = 0;
        } else {
            this.setCenterY((this.getCenterY()+ ySpeed));
        }
        this.ySpeed += gravity;

    }

    public void swimUp() {
        this.ySpeed -= gravity * 75;
    }

}
