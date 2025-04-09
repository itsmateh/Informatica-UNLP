package P2.EJ9;

public class Node {
    private int sum_value;
    private int diff_value;

    public Node(int sum_value, int diff_value) {
        this.sum_value = sum_value;
        this.diff_value = diff_value;
    }

    public int getSum_value() {
        return sum_value;
    }

    public void setSum_value(int sum_value) {
        this.sum_value = sum_value;
    }

    public int getDiff_value() {
        return diff_value;
    }

    public void setDiff_value(int diff_value) {
        this.diff_value = diff_value;
    }

    @Override
    public String toString() {
        return sum_value + " | " + diff_value;
    }
}
