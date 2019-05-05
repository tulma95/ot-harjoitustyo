package FishSwim;

import fishswim.domain.Fish;
import fishswim.domain.GameLogic;
import fishswim.domain.Obstacle;
import java.util.Random;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameLogicTest {

    private Fish fish;
    private Obstacle obstacle;
    private GameLogic gameLogic;

    @Before
    public void setUp() {
        Random r = new Random(1337L);
        this.obstacle = new Obstacle(4, r);
        this.fish = new Fish(400, 400, 400);
        this.gameLogic = new GameLogic(fish, obstacle);
    }

    @Test
    public void moveAllFishMoves() {
        double fishY = gameLogic.getFish().getCenterY();
        gameLogic.moveAll();
        assertThat(gameLogic.getFish().getCenterY(), is(not(fishY)));
    }

    @Test
    public void moveAllObstacleMoves() {
        double obstacleX = gameLogic.getObstacles()[0].getX();
        gameLogic.moveAll();
        assertThat(gameLogic.getObstacles()[0].getX(), is(not(obstacleX)));
    }

    @Test
    public void continueGameFalseWhenCollision() {
        fish = new Fish(400, 0, 200);
        gameLogic = new GameLogic(fish, obstacle);
        assertThat(gameLogic.continueGame(), is(false));
    }

    @Test
    public void continueGameTrueWhenNotCollision() {
        fish = new Fish(300, 0, 200);
        gameLogic = new GameLogic(fish, obstacle);
        assertThat(gameLogic.continueGame(), is(true));
    }

}
