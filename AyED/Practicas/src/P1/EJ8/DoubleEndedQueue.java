package P1.EJ8;

public class DoubleEndedQueue<T> extends Queue<T>{
    public void enqueueFirst(T elem){
        super.elementos.add(0, elem);
    }
}
