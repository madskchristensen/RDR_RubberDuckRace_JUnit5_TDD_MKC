import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class RaceTest {

    static Map<Integer, Queue> queueMap;

    @BeforeEach
    void initQueueMap() {
        queueMap = new HashMap<>(10);

        for(int i = 1; i <= 10; i++) {
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
    void testQueueMap() {
        Map<Integer, Queue> queueTestMap;
        queueTestMap = new HashMap<>(10);

        for(int i = 1; i <= 10; i++) {
            queueTestMap.put(i, new Queue());
        }

        for(int i = 0; i <= 90; i = i + 10) {
            for(int j = 1; j <= 10; j++) {
                queueTestMap.get(j).addToEnd(new RubberDuck(j + i));
            }
        }

        assertEquals(1, queueTestMap.get(1).getFirst().getUniqueNumber());
        assertEquals(10, queueTestMap.get(10).getFirst().getUniqueNumber());
        assertEquals(91, queueTestMap.get(1).getEnd().getUniqueNumber());
        assertEquals(100, queueTestMap.get(10).getEnd().getUniqueNumber());
        assertEquals(10, queueTestMap.size());
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

    @Test
    void getAvailableKeysFromQueueMapAndSaveInArrayList() {
        ArrayList<Integer> availableQueues = new ArrayList<>();

        for(Map.Entry<Integer, Queue> entry : queueMap.entrySet()) {
            availableQueues.add(entry.getKey());
        }

        assertEquals(10, availableQueues.size());
        assertEquals(1, availableQueues.get(0));
        assertEquals(10, availableQueues.get(9));
    }

    // skal rettes til at matche test med 3 random queues
    @Test
    void removeRandomAndAvailableQueueFromQueueMap() {
        // initialize and populate arraylist of available queues
        ArrayList<Integer> availableQueues = new ArrayList<>();
        Random random = new Random();

        for(Map.Entry<Integer, Queue> entry : queueMap.entrySet()) {
            availableQueues.add(entry.getKey());
        }

        // mulige tal er til at starte med 0-9
        int queueToRemove = random.nextInt(availableQueues.size());

        queueMap.remove(queueToRemove);
        availableQueues.remove(Integer.valueOf(queueToRemove));
        assertNull(queueMap.get(queueToRemove));
    }

    @Test
    void remove3RandomAndAvailableQueueFromQueueMap() {
        // initialize and populate arraylist of available queues
        ArrayList<Integer> availableQueues = new ArrayList<>();
        Random random = new Random();

        for(Map.Entry<Integer, Queue> entry : queueMap.entrySet()) {
            availableQueues.add(entry.getKey());
        }

        int randomIndex = random.nextInt(availableQueues.size());
        int queueKeyToRemove1 = availableQueues.get(randomIndex);
        queueMap.remove(queueKeyToRemove1);
        availableQueues.remove(randomIndex);

        randomIndex = random.nextInt(availableQueues.size());
        int queueKeyToRemove2 = availableQueues.get(randomIndex);
        queueMap.remove(queueKeyToRemove2);
        availableQueues.remove(randomIndex);

        randomIndex = random.nextInt(availableQueues.size());
        int queueKeyToRemove3 = availableQueues.get(randomIndex);
        queueMap.remove(queueKeyToRemove3);
        availableQueues.remove(randomIndex);

        assertAll(
                () -> assertNull(queueMap.get(queueKeyToRemove1)),
                () -> assertNull(queueMap.get(queueKeyToRemove2)),
                () -> assertNull(queueMap.get(queueKeyToRemove3)),
                () -> assertEquals(7, queueMap.size())
        );
    }
}