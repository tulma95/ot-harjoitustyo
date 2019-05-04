package FishSwim;

import static org.hamcrest.MatcherAssert.assertThat;
import fishswim.domain.Fish;
import static org.hamcrest.Matchers.greaterThan;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.*;

public class FishTest {

    Fish fish;

    @Before
    public void setUp() {
        fish = new Fish(50, 50, 400);
    }

    @Test
    public void fishFallsDown() {
        fish.move();
        assertThat(fish.getCenterY(), is(51.0));
    }

    @Test
    public void fishMaxJumpSpeedWorks() {
        fish.swimUp();
        fish.swimUp();
        assertThat(fish.getySpeed(), is(-7.5));
    }

    @Test
    public void fishGravityWorks() {
        double initialSpeed = fish.getySpeed();
        fish.move();
        assertThat(fish.getySpeed(), greaterThan(initialSpeed));
    }

    @Test
    public void fishCantFallOutsideOfScreen() {
        for (int i = 0; i < 100; i++) {
            fish.move();
        }
        assertThat(Math.floor(fish.getCenterY()), is(385.0));
    }

    @Test
    public void fishCantGoOverTopOfScreen() {
        for (int i = 0; i < 100; i++) {
            fish.swimUp();
            fish.move();
        }
        assertThat(Math.floor(fish.getCenterY()), is(15.0));
    }
}
