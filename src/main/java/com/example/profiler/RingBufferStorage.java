package com.example.profiler;
import org.springframework.stereotype.Component;
import java.util.LinkedList;
import java.util.List;

@Component
public class RingBufferStorage {
    private final LinkedList<DataPoint> buffer;
    private final int capacity;


    public RingBufferStorage() {
        this.capacity=100;
        this.buffer = new LinkedList<>();
    }
    public synchronized void add(DataPoint point){
        if (buffer.size() >= capacity){
            buffer.removeFirst();
        }
        buffer.add(point);
    }
    public synchronized List<DataPoint> getAll() {
        return new LinkedList<>(buffer);
    }
    public synchronized List<DataPoint> getRecent(int n) {
        if (n >= buffer.size()){
            return getAll();
        }
        return new LinkedList<>(buffer.subList(buffer.size() - n,buffer.size()));
    }

}
