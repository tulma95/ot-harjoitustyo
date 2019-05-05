package FishSwim;

import fishswim.domain.Fish;
import fishswim.domain.Obstacle;
import java.util.Random;
import static org.hamcrest.CoreMatchers.not;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class ObstacleTest {

    Obstacle obstacle;

    @Before
    public void setUp() {
        Random r = new Random(1337L);
        this.obstacle = new Obstacle(4, r);
    }

    @Test
    public void fishCanHitUpperObstacle() {
        Fish fish = new Fish(400, 0, 400);
        assertThat(this.obstacle.checkCollision(fish), is(true));
    }

    @Test
    public void fishCanHitLowerObstacle() {
        Fish fish = new Fish(400, 395, 400);
        assertThat(this.obstacle.checkCollision(fish), is(true));
    }

    @Test
    public void fishCanGoThroughGap() {
        Fish fish = new Fish(400, this.obstacle.getLowerObstacle().getY() - 40, 400);
        assertThat(this.obstacle.checkCollision(fish), is(false));
    }

    @Test
    public void obstacleCanMove() {
        double previousX = this.obstacle.getX();
        this.obstacle.move();
        assertThat(previousX, is(not(this.obstacle.getX())));
    }

    @Test
    public void generateNewObstacleWhenOutOfScreen() {
        double previousY = this.obstacle.getLowerObstacle().getY();
        this.obstacle.move();
        while (true) {
            if (this.obstacle.getX() == 400) {
                break;
            }
            this.obstacle.move();
        }
        assertThat(previousY, is(not(this.obstacle.getLowerObstacle().getY())));
    }

    @Test
    public void generateObstacleMakeDifferentObstacles() {
        double previous = this.obstacle.getLowerObstacle().getY();
        this.obstacle.generateObstacle();
        double current = this.obstacle.getLowerObstacle().getY();

        assertThat(previous, is(not(current)));
    }

}
