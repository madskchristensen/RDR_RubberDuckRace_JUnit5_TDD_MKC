import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
    static Queue queue;

    @BeforeEach
    void initMockObjects() {
        queue = new Queue();
        queue.addToEnd(new RubberDuck(1));
        queue.addToEnd(new RubberDuck(2));
        queue.addToEnd(new RubberDuck(3));
    }

    @Test
    void addObjectToEndOfQueue() {
        queue.addToEnd(new RubberDuck(4));

        assertEquals(4, queue.getEnd().getUniqueNumber());
    }

    @Test
    void removeOldestObjectInQueue() {
        queue.removeOldest();

        assertEquals(2, queue.getFirst().getUniqueNumber());
    }

    @Test
    void getNumberOfObjectsInQueue() {
        assertEquals(3, queue.size());
    }

    @Test
    void getQueueSizeAfterObjectHasBeenAddedToQueue() {
        queue.addToEnd(new RubberDuck(56));

        assertEquals(4, queue.size());
    }

    @Test
    void getQueueSizeWhenNoObjectsPresent() {
        queue.removeOldest();
        queue.removeOldest();
        queue.removeOldest();

        assertEquals(0, queue.size());
    }

    @Test
    void removeObjectFromQueueWhenSizeIs0() {
        queue.removeOldest();
        queue.removeOldest();
        queue.removeOldest();

        assertThrows(NullPointerException.class, () -> {
            queue.removeOldest();
        }, "Attempted to remove oldest. NullPointerException was thrown. Noice");
    }
}