package com.timemachine.controller;

import com.timemachine.entity.StateSnapshot;
import com.timemachine.repository.StateSnapshotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/time-machine")
@RequiredArgsConstructor
public class TimeMachineController {
    private final StateSnapshotRepository stateSnapshotRepository;

    @PostMapping("/snapshot")
    public StateSnapshot createSnapshot(@RequestBody StateSnapshot snapshot) {
        if(snapshot.getTimestamp() == null)
            snapshot.setTimestamp(Instant.now());

        return stateSnapshotRepository.save(snapshot);
    }

    @GetMapping("history")
    public List<StateSnapshot> getHistory(
            @RequestParam String entityId,
            @RequestParam String entityType
    ) {
        return stateSnapshotRepository.findByEntityIdAndEntityTypeOrderByTimestampAsc(entityId, entityType);
    }
}
