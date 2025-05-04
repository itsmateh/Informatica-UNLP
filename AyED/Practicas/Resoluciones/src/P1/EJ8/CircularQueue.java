package P1.EJ8;

public class CircularQueue<T> extends Queue<T> {
    public T shift(){
        T elem = dequeue();
        enqueue(elem);
        return elem;
    }
}
