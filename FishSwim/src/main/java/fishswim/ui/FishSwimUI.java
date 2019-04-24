package fishswim.ui;

import fishswim.dao.ScoresDao;
import javafx.application.Application;
import javafx.stage.Stage;
import fishswim.domain.Fish;
import fishswim.domain.GameLogic;
import fishswim.domain.Obstacle;
import fishswim.domain.Player;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FishSwimUI extends Application {

    private GameLogic fishSwim;
    private Scene mainMenu;
    private Scene endgameScene;
//    private Scene 
    private ScoresDao scoresDao;
    private double height;
    private double width;

    @Override
    public void init() throws Exception {
        this.height = 400;
        this.width = 400;
        this.scoresDao = new ScoresDao();
    }

    public void hiScoresScene(Stage primStage) {
        List<Player> players = scoresDao.getScores();
        int page = 0;
        GridPane pane = new GridPane();
        for (int i = 0; i < 10; i++) {
//            Text playerStats = new Text(STYLESHEET_MODENA)
            pane.add(pane, i, i);
        }
    }

    public void endGameScene(Stage primaryStage, int points) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);

        TextField textfield = new TextField();
        textfield.setPrefWidth(100);

        Label scores = new Label();
        scores.setText("POINTS: " + points);

        Button playAgainButton = new Button("Play again");
        playAgainButton.setPrefWidth(100);
        Button saveScoreButton = new Button("Save score");
        saveScoreButton.setPrefWidth(100);
        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setPrefWidth(100);

        VBox buttons = new VBox();
        buttons.getChildren()
                .addAll(saveScoreButton, playAgainButton, mainMenuButton);

        buttons.setAlignment(Pos.CENTER);

        pane.add(scores, 0, 0);
        pane.add(textfield, 0, 1);
        pane.add(buttons, 0, 2);

        saveScoreButton.setOnAction(e -> {
            Player player = new Player(textfield.getText(),
                    points);
            scoresDao.saveScores(player);
            primaryStage.setScene(mainMenu);
        });

        playAgainButton.setOnAction(e -> {
            startGame(primaryStage);
        });

        mainMenuButton.setOnAction(e -> {
            primaryStage.setScene(mainMenu);
        });

        primaryStage.setScene(new Scene(pane, width, height));
    }

    public void createMainMenu(Stage primaryStage) {
        GridPane mainMenuPane = new GridPane();
        mainMenuPane.setVgap(100);
        mainMenuPane.setAlignment(Pos.TOP_CENTER);

        VBox buttons = new VBox();
        buttons.setPrefWidth(100);
        buttons.setSpacing(5);
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

        scoresButton.setOnAction(e -> {
            scoresDao.getScores();
        });

        mainMenu = new Scene(mainMenuPane, width, height);

    }

    public void startGame(Stage primaryStage) {
        Fish fish = new Fish(50, 50, height);
        Obstacle obstacle = new Obstacle(4);
        obstacle.setImg();
        fishSwim = new GameLogic(fish, obstacle);

        Pane gamePane = new Pane();
        Text pointsText = new Text("Points: " + fishSwim.getPoints().get());
        pointsText.setX((width / 2) - (pointsText.getLayoutBounds().getWidth() / 2));
        pointsText.setY(20);
        gamePane.getChildren().addAll(fishSwim.getObstacle().getObstacles());
        gamePane.getChildren().add(fishSwim.getFish());
        gamePane.getChildren().add(pointsText);
        Scene gameScene = new Scene(gamePane, width, height);
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
                    endGameScene(primaryStage, fishSwim.getPoints().get());
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
        primaryStage.setTitle("Fish Swim");
        createMainMenu(primaryStage);
        primaryStage.setScene(mainMenu);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
