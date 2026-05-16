package com.example.profiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
public class ProfilerController {
    @Autowired
    private RingBufferStorage storage;

    @GetMapping("/api/profiler/all")
    public List<DataPoint> getAll() {
        return storage.getAll();
    }

    @GetMapping("/api/profiler/recent")
    public List<DataPoint> getRecent(@RequestParam(defaultValue = "10") int n) {
        return storage.getRecent(n);
    }

    @GetMapping("/test")
    public String test() {
        return "OK";
    }
}

