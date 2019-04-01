package fishswim.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import fishswim.domain.Fish;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class FishSwimUI extends Application {

    private Fish fish;
    private Scene gameScene;

    @Override
    public void init() throws Exception {
        fish = new Fish();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // gameScene
        Pane pane = new Pane();
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
