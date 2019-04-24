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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

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

    public void hiScoresScene(Stage primStage, int page) {
        List<Player> players = scoresDao.getScores();
        GridPane scoresPane = new GridPane();
        scoresPane.setAlignment(Pos.CENTER);

        GridPane framePane = new GridPane();
        framePane.setAlignment(Pos.TOP_CENTER);
        framePane.add(scoresPane, 1, 1);

        framePane.setVgap(50);
        framePane.setHgap(25);
        framePane.setPadding(new Insets(50));

        int listSize = players.size();
        int howManyToShow = listSize - (page * 10) > 10 ? 10 : listSize % 10;

        for (int i = 0; i < howManyToShow; i++) {
            Player player = players.get((page * 10) + i);
            Text placement = new Text((page * 10 + i + 1) + ". ");
            Text playerName = new Text(player.getName());
            Text playerScore = new Text("\t" + player.getPoints());

            scoresPane.add(placement, 0, i);
            scoresPane.add(playerName, 1, i);
            scoresPane.add(playerScore, 2, i);
        }

        Button backButton = new Button("Back");
        framePane.add(backButton, 0, 0);

        Label pageNumber = new Label("Page " + page + 1);
        pageNumber.setFont(Font.font(20));
        framePane.add(pageNumber, 1, 0);
        GridPane.setHalignment(pageNumber, HPos.CENTER);

        HBox buttons = new HBox();
        Button nextButton = new Button(">");
        Button previousButton = new Button("<");
        buttons.getChildren().addAll(previousButton, nextButton);

        framePane.add(buttons, 2, 0);

        backButton.setOnAction(e -> {
            primStage.setScene(mainMenu);
        });

        Scene hiScoreScene = new Scene(framePane, width, height);
        primStage.setScene(hiScoreScene);

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
            hiScoresScene(primaryStage, 0);
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
                    if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.SPACE) {
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
