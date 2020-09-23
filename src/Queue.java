import java.util.ArrayList;

public class Queue {
    private ArrayList<RubberDuck> internalArrayList;

    public Queue() {
        internalArrayList = new ArrayList<>();
    }

    public void addToEnd(RubberDuck o) {
        internalArrayList.add(o);
    }

    public RubberDuck getEnd() {
        return internalArrayList.get(internalArrayList.size() - 1);
    }

    public boolean removeOldest() {
        if(internalArrayList.size() < 1) {
            throw new NullPointerException("Attempted to remove from empty queue!");

        } else {
            internalArrayList.remove(0);
        }

        return true;
    }

    public RubberDuck getFirst() {
        if(internalArrayList.size() == 0) {
            return null;
        }

        return internalArrayList.get(0);
    }

    public int size() {
        return internalArrayList.size();
    }

    public RubberDuck moveFirst() {
        RubberDuck rubberDuckToReturn = getFirst();
        internalArrayList.remove(rubberDuckToReturn);

        return rubberDuckToReturn;
    }

    public void reduceSize(int amount) {
        if(internalArrayList.size() != 0) {
            for(int i = 0; i < amount; i++) {
                internalArrayList.remove(internalArrayList.size() - 1);
            }
        }
    }

    @Override
    public String toString() {
        String returnString = "";

        for(RubberDuck rubberDuck : internalArrayList) {
            returnString = returnString + rubberDuck.toString() + " ";
        }

        return returnString;
    }
}