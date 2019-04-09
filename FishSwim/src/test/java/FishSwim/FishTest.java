package FishSwim;

import static org.hamcrest.MatcherAssert.assertThat;
import fishswim.domain.Fish;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.*;
import org.junit.Assert;

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
