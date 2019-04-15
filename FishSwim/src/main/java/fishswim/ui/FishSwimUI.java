package fishswim.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import fishswim.domain.Fish;
import fishswim.domain.GameLogic;
import fishswim.domain.Obstacle;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FishSwimUI extends Application {

    private GameLogic fishSwim;
//    private Scene gameScene;
    private Scene mainMenu;
    private Scene scoreScene;

    @Override
    public void init() throws Exception {
    }

    public void mainMenu(Stage primaryStage) {
        GridPane mainMenuPane = new GridPane();
        mainMenuPane.setVgap(100);
        mainMenuPane.setAlignment(Pos.TOP_CENTER);

        VBox buttons = new VBox();
        buttons.setPrefWidth(100);
        buttons.setAlignment(Pos.CENTER);

        Button playButton = new Button("Play game");
        playButton.setPrefWidth(100);
        Button scoresButton = new Button("Hi-Scores");
        scoresButton.setPrefWidth(100);
        Button exitButton = new Button("Exit");
        exitButton.setPrefWidth(100);

        buttons.getChildren().addAll(playButton, scoresButton, exitButton);

        Label title = new Label("Fish Swim");
        title.setFont(Font.font(40));
        title.setAlignment(Pos.CENTER);

        mainMenuPane.add(title, 0, 0);
        mainMenuPane.add(buttons, 0, 1);

        exitButton.setOnAction(e -> {
            primaryStage.close();
        });

        playButton.setOnAction(e -> {
            startGame(primaryStage);
        });
        mainMenu = new Scene(mainMenuPane, 400, 400);

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

                if (now - previous < 1000000000 / 100) {
                    return;
                }
                pointsText.setText("Points: " + fishSwim.getPoints().get());
                if (!fishSwim.continueGame()) {
                    stop();
                    primaryStage.setScene(mainMenu);
                }
                gameScene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.W) {
                        fishSwim.getFish().swimUp();
                    }
                });
                fishSwim.moveAll();
                previous = now;
            }
        }.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        mainMenu(primaryStage);
        primaryStage.setScene(mainMenu);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
