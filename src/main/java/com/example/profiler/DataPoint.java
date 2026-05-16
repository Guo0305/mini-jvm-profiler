package com.example.profiler;
import java.time.LocalDateTime;

public class DataPoint {
    private LocalDateTime timestamp;
    private double cpuLoad;
    private long heapMemoryUsed;
    private int threadCount;


    public DataPoint(LocalDateTime timestamp, double cpuLoad, long heapMemoryUsed, int threadCount) {
        this.timestamp = timestamp;
        this.cpuLoad = cpuLoad;
        this.heapMemoryUsed = heapMemoryUsed;
        this.threadCount = threadCount;
    }
    public LocalDateTime getTimestamp(){return timestamp;}

    public double getCpuLoad() {
        return cpuLoad;
    }

    public long getHeapMemoryUsed() {
        return heapMemoryUsed;
    }

    public int getThreadCount() {
        return threadCount;
    }
}
