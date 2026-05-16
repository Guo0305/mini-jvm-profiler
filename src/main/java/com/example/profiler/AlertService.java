package com.example.profiler;

import org.springframework.stereotype.Service;

@Service
public class AlertService {

    // CPU 阈值 80%
    private static final double CPU_THRESHOLD = 0.8;
    // 内存阈值 500 MB
    private static final long MEMORY_THRESHOLD_MB = 500;

    public void checkAndAlert(DataPoint point) {
        if (point.getCpuLoad() > CPU_THRESHOLD) {
            System.err.println("[ALERT] High CPU usage: " + (point.getCpuLoad() * 100) + "% at " + point.getTimestamp());
        }
        if (point.getHeapMemoryUsed() > MEMORY_THRESHOLD_MB) {
            System.err.println("[ALERT] High memory usage: " + point.getHeapMemoryUsed() + " MB at " + point.getTimestamp());
        }
    }
}