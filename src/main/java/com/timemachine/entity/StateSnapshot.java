package com.timemachine.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateSnapshot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventId;
    private EventType eventType;
    private Instant timestamp;
    private int version;
    @Column(columnDefinition = "TEXT")
    private String stateJson;

    public enum EventType {
        CREATE,
        UPDATE,
        DELETE,
        FETCH
    }
}
