package com.example.profiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.management.*;
import java.lang.management.*;
import java.time.LocalDateTime;
@Service
public class ProfilerService {
    @Autowired
    private RingBufferStorage storage;
    @Autowired
    private AlertService alertService;
    @Scheduled(fixedDelay = 2000)
    public void collect() {
        try {
            OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
            MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
            double cpuLoad = getProcessCpuLoad();
            long headUsedMB =memoryMXBean.getHeapMemoryUsage().getUsed() / (1024*1024);
            int threadCount = threadMXBean.getThreadCount();
            DataPoint point =new DataPoint(LocalDateTime.now(),cpuLoad,headUsedMB,threadCount);
            storage.add(point);
            alertService.checkAndAlert(point);}
        catch (Exception e){
            e.printStackTrace();
        }
        }
        private double getProcessCpuLoad(){
        try{
            com.sun.management.OperatingSystemMXBean osBean =
                    (com.sun.management.OperatingSystemMXBean) ManagementFactory.getMemoryMXBean();
            return osBean.getProcessCpuLoad();
        } catch (Exception e){
            return 0.0;
        }
        }
    }

