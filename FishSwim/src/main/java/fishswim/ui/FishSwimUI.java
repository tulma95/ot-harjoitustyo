package fishswim.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import fishswim.domain.Fish;
import fishswim.domain.Obstacle;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class FishSwimUI extends Application {

    private Fish fish;
    private Obstacle obstacle;
    private Scene gameScene;

    @Override
    public void init() throws Exception {
        fish = new Fish(50, 50);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // gameScene
        Pane pane = new Pane();
        obstacle = new Obstacle();
        pane.getChildren().addAll(obstacle.getObstacles());
        pane.getChildren().add(fish);

        new AnimationTimer() {
            long previous = 0;

            @Override
            public void handle(long now) {
                if (now - previous < 100000000) {
                    return;
                }

                gameScene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.W) {
                        fish.swimUp();
                    }
                });
                obstacle.move();
                fish.move();
            }
        }.start();

        gameScene = new Scene(pane, 400, 400);
        primaryStage.setScene(gameScene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
