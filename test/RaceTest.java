import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class RaceTest {

    static Map<Integer, Queue> queueMap;

    @BeforeEach
    void initQueueMap() {
        queueMap = new HashMap<>(10);

        for(int i = 1; i < 10; i++) {
            queueMap.put(i, new Queue());
        }

        for(int i = 0; i <= 90; i = i + 10) {
            for(int j = 1; j <= 10; j++) {
                queueMap.get(j).addToEnd(new RubberDuck(j + i));
            }
        }

        assertEquals(1, queueMap.get(1).getFirst().getUniqueNumber());
        assertEquals(10, queueMap.get(10).getFirst().getUniqueNumber());
        assertEquals(91, queueMap.get(1).getEnd().getUniqueNumber());
        assertEquals(100, queueMap.get(10).getEnd().getUniqueNumber());
    }

    @Test
    void addRubberDuckToQueueInQueueMap() {
        for(int i = 0; i <= 10; i++) {
            queueMap.get(i).addToEnd(new RubberDuck(i));
        }

        assertNotNull(queueMap.get(1));
        assertNotNull(queueMap.get(5));
        assertNotNull(queueMap.get(10));
    }

    @Test
    void moveForemostRubberDuckFromQueueToAnother() {
        // Random random = new Random();

        // int queueToMoveDuckFrom = random.nextInt(10);
        // int queueToMoveDuckTo = random.nextInt(10);

        int queueToMoveDuckFrom = 5;
        int queueToMoveDuckTo = 7;

        queueMap.get(queueToMoveDuckTo).addToEnd(queueMap.get(queueToMoveDuckFrom).moveFirst());

        assertNotSame(queueMap.get(queueToMoveDuckFrom).size(),
                queueMap.get(queueToMoveDuckTo).size());
        assertEquals(9, queueMap.get(queueToMoveDuckFrom).size());
        assertEquals(11, queueMap.get(queueToMoveDuckTo).size());
    }
}