package com.timemachine.controller;

import com.timemachine.entity.StateSnapshot;
import com.timemachine.repository.StateSnapshotRepository;
import com.timemachine.service.TimeMachineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/time-machine")
@RequiredArgsConstructor
@Slf4j
public class TimeMachineController {
    private final StateSnapshotRepository stateSnapshotRepository;
    private final TimeMachineService timeMachineService;

    @PostMapping("/snapshot")
    public StateSnapshot createSnapshot(@RequestBody StateSnapshot snapshot) {
        if(snapshot.getTimestamp() == null)
            snapshot.setTimestamp(Instant.now());

        return stateSnapshotRepository.save(snapshot);
    }

    @GetMapping("history")
    public List<StateSnapshot> getHistory(
            @RequestParam String entityId,
            @RequestParam(defaultValue = "all") String entityType
    ) {
        return stateSnapshotRepository.findByEntityIdAndEntityTypeOrderByTimestampAsc(entityId, entityType);
    }

    @GetMapping("diff")
    public void getDiff(
            @RequestParam String entityId,
            @RequestParam String version1,
            @RequestParam String version2
    ) {
        try {
            timeMachineService.getDiff(entityId, version1, version2);
        } catch(Exception e) {
            log.error("Some error occurred, with msg: {}", e.getMessage());
        }
    }

    @GetMapping("restore")
    public void restore(
            @RequestParam String entityId,
            @RequestParam String version1,
            @RequestParam String version2
    ) {
        try {
            timeMachineService.getDiff(entityId, version1, version2);
        } catch(Exception e) {
            log.error("Some error occurred, with msg: {}", e.getMessage());
        }
    }
}
