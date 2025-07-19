package com.timemachine.repository;

import com.timemachine.entity.StateSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateSnapshotRepository extends JpaRepository<StateSnapshot, Long> {
    List<StateSnapshot> findByEntityIdAndEntityTypeOrderByTimestampAsc(String entityId, String entityType);
}
