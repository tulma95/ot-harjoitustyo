package FishSwim;

import static org.hamcrest.Matchers.*;
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
        fish = new Fish(50, 50);
    }

    @Test
    public void fishFallsDown() {
        fish.move();

        assertThat(fish.getCenterY(), is(51.0));
    }
}
