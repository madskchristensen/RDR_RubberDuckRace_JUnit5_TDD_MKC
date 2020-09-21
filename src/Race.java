import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Race {
    private Map<Integer, Queue> queueMap;
    private int maxRounds;

    public Race() {

    }

    private void init() {
        queueMap = new HashMap<>(10);

        for(int i = 1; i <= 10; i++) {
            queueMap.put(i, new Queue());
        }

        for(int i = 0; i <= 90; i = i + 10) {
            for(int j = 1; j <= 10; j++) {
                queueMap.get(j).addToEnd(new RubberDuck(j + i));
            }
        }

        maxRounds = 10;
    }

    public void play() {
        int n = 10;

        Random random = new Random();

        init();

        for(int timestep = 1; timestep <= maxRounds; timestep++) {
            int queueToMoveDuckFrom = random.nextInt(n - 1) + 1;
            int queueToMoveDuckTo = random.nextInt(n - 1) + 1;

            System.out.println("---- TIME STEP " + timestep + " ----");
            System.out.println("Move Duck From Queue: " + queueToMoveDuckFrom);
            System.out.println("Move Duck To Queue: " + queueToMoveDuckTo);

            for(int i = 1; i <= queueMap.size(); i++) {
                if(queueMap.get(i) == null) {
                    System.out.println("Queue number: " + i + " has been removed!");
                    continue;
                }

                System.out.println("Queue number: " + i + " { " + queueMap.get(i) + " }");
            }
            System.out.println();

            queueMap.get(queueToMoveDuckTo).addToEnd(queueMap.get(queueToMoveDuckFrom).moveFirst());
            queueMap.remove(timestep);

            for(int i = timestep + 1; i <= n; i++) {
                queueMap.get(i).removeOldest();
            }

            n--;
        }
    }
}
