import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Race {
    private Map<Integer, Queue> queueMap;
    private ArrayList<Integer> availableQueues;
    private int maxRounds;
    private int timestep;
    private int n;
    private Random random;

    public Race() {

    }

    private void init() {
        queueMap = new HashMap<>(10);
        maxRounds = 10;
        timestep = 1;
        n = 10;
        random = new Random();

        for(int i = 1; i <= 10; i++) {
            queueMap.put(i, new Queue());
        }

        for(int i = 0; i <= 90; i = i + 10) {
            for(int j = 1; j <= 10; j++) {
                queueMap.get(j).addToEnd(new RubberDuck(j + i));
            }
        }

        // initialize and populate array that keeps track of queues that haven't been removed from the queueMap.
        availableQueues = new ArrayList<>();

        for(Map.Entry<Integer, Queue> entry : queueMap.entrySet()) {
            availableQueues.add(entry.getKey());
        }
    }

    public void play() {
        init();

        while(timestep <= maxRounds) {
                printState();

                moveRandomDuckBetween2RandomQueues();
                removeRandomQueue();
                reduceQueueSize(1);

                timestep++;
                n--;
        }
    }

    private void moveRandomDuckBetween2RandomQueues() {
        int queueToMoveDuckFrom = getRandomQueueKey();
        int queueToMoveDuckTo = getRandomQueueKey();

        queueMap.get(queueToMoveDuckTo).addToEnd(queueMap.get(queueToMoveDuckFrom).moveFirst());

        if(queueMap.get(queueToMoveDuckFrom).size() == 0) {
            queueMap.remove(queueToMoveDuckFrom);
        }
    }

    private void printState() {
        System.out.println();
        System.out.println("---- TIME STEP " + timestep + " ----");

        for(Map.Entry<Integer, Queue> entry : queueMap.entrySet()) {
            System.out.println("Queue number " + entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("Queue Map Size: " + queueMap.size());
    }

    /*
        Tjek hvilke queues der findes i queueMap og gem deres Key i en arrayliste
        Brug en Random til at få et tilfældigt tal mellem 0 og arraylist.size - 1
        Brug det tilfældige tal til at fjerne Queue fra queueMap
     */

    private int getRandomQueueKey() {
        int randomIndex = random.nextInt(availableQueues.size());

        return availableQueues.get(randomIndex);
    }

    private void removeRandomQueue() {
        int randomIndex = random.nextInt(availableQueues.size());
        queueMap.remove(availableQueues.get(randomIndex));
        availableQueues.remove(randomIndex);
    }

    private void reduceQueueSize(int amount) {
        for(Map.Entry<Integer, Queue> entry : queueMap.entrySet()) {
            entry.getValue().reduceSize(1);
        }
    }
}
