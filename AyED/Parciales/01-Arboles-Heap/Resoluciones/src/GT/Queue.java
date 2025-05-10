package GT;

import java.util.*;


public class Queue<T> extends Sequence {
    protected ArrayList<T> elementos;
    public Queue(){
        this.elementos = new ArrayList<T>();
    }

    public void enqueue(T data){
        this.elementos.add(data);
    }

    public T dequeue(){
        return this.elementos.remove(0);
    }

    public T head(){
        return this.elementos.get(0);
    }

    @Override
    public boolean isEmpty() {
        return this.elementos.size() == 0;
    }

    @Override
    public int size() {
        return this.elementos.size();
    }

    @Override
    public String toString(){
        String txt=" ";
        for(T e:elementos){
            txt = txt +  e + " | ";
        }
        return txt;
    }
}
