package fishswim.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import fishswim.domain.Fish;
import fishswim.domain.GameLogic;
import fishswim.domain.Obstacle;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class FishSwimUI extends Application {

    private GameLogic fishSwim;
//    private Scene gameScene;

    @Override
    public void init() throws Exception {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        mainMenu(primaryStage);
        primaryStage.show();

    }

    public void mainMenu(Stage primaryStage) {
        Pane mainMenuPane = new Pane();
        Button playButton = new Button("Play game");

        mainMenuPane.getChildren().add(playButton);

        playButton.setOnAction(e -> {
            startGame(primaryStage);
        });
        Scene menuScene = new Scene(mainMenuPane, 400, 400);
        primaryStage.setScene(menuScene);

    }

    public void startGame(Stage primaryStage) {
        Fish fish = new Fish(50, 50);
        Obstacle obstacle = new Obstacle();
        fishSwim = new GameLogic(fish, obstacle);

        Pane gamePane = new Pane();
        Text pointsText = new Text(350, 20, "Points: " + fishSwim.getPoints().get());
        gamePane.getChildren().addAll(fishSwim.getObstacle().getObstacles());
        gamePane.getChildren().add(fishSwim.getFish());
        gamePane.getChildren().add(pointsText);
        Scene gameScene = new Scene(gamePane, 400, 400);
        primaryStage.setScene(gameScene);

        new AnimationTimer() {
            long previous = 0;

            @Override
            public void handle(long now) {
                if (now - previous > 1000000000L) {
                    System.out.println("t''");
                    pointsText.setText("Points: " + fishSwim.getPoints().get());
                    if (!fishSwim.continueGame()) {
                        stop();
                        mainMenu(primaryStage);
                    }
                    this.previous = now;

                }
                gameScene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.W) {
                        fishSwim.getFish().swimUp();
                    }
                });
                fishSwim.moveAll();

            }
        }.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
