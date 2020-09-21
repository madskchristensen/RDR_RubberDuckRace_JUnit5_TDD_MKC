public class RubberDuck {
    private int uniqueNumber;

    public RubberDuck(int i) {
        this.uniqueNumber = i;
    }

    public int getUniqueNumber() {
        return uniqueNumber;
    }

    @Override
    public String toString() {
        return "Rubber Duck[" + uniqueNumber + "]";
    }
}