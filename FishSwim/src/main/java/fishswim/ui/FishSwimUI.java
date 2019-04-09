package fishswim.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import fishswim.domain.Fish;
import fishswim.domain.GameLogic;
import fishswim.domain.Obstacle;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class FishSwimUI extends Application {

    private GameLogic fishSwim;
    private Scene gameScene;

    private Fish fish;
    private Obstacle obstacle;

    @Override
    public void init() throws Exception {
        fish = new Fish(50, 50);
        obstacle = new Obstacle();

        fishSwim = new GameLogic(fish, obstacle);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // gameScene
        Pane gamePane = new Pane();
        Text pointsText = new Text(350, 20, "Points: " + fishSwim.getPoints().get());
        gamePane.getChildren().addAll(fishSwim.getObstacle().getObstacles());
        gamePane.getChildren().add(fishSwim.getFish());
        gamePane.getChildren().add(pointsText);

        new AnimationTimer() {
            long previous = 0;

            @Override
            public void handle(long now) {
                if (now - previous < 100000000) {
                    return;
                }

                gameScene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.W) {
                        fishSwim.getFish().swimUp();
                    }
                });
                pointsText.setText("Points: " + fishSwim.getPoints().get());
                fishSwim.moveAll();
                if (!fishSwim.continueGame()) {
                    stop();
                }
            }
        }.start();

        gameScene = new Scene(gamePane, 400, 400);
        primaryStage.setScene(gameScene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
