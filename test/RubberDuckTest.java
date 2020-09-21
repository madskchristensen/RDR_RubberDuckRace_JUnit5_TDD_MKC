import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RubberDuckTest {

    @Test
    void constructNewRubberDuckWithUniqueNumber() {
        RubberDuck rubberDuck1 = new RubberDuck(1);
        RubberDuck rubberDuck2 = new RubberDuck(2);

        assertNotEquals(rubberDuck1.getUniqueNumber(), rubberDuck2.getUniqueNumber());
    }
}
