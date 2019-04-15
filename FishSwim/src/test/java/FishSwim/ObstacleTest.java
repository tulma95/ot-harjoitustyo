package FishSwim;

import fishswim.domain.Obstacle;
import static org.hamcrest.CoreMatchers.not;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class ObstacleTest {

    Obstacle obstacle;

    @Before
    public void setUp() {
        this.obstacle = new Obstacle();
    }

    @Test
    public void obstacleCanMove() {
        double previousX = this.obstacle.getX();
        this.obstacle.move();

        assertThat(previousX, is(not(this.obstacle.getX())));
    }

    @Test
    public void generateObstacleMakeDifferentObstacles() {
        double previous = this.obstacle.getLowerObstacle().getY();
        this.obstacle.generateObstacle();
        double current = this.obstacle.getLowerObstacle().getY();

        assertThat(previous, is(not(current)));
    }

}
