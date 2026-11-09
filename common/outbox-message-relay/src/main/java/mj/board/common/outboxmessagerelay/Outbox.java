package mj.board.common.outboxmessagerelay;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mj.board.common.event.EventType;

import java.time.LocalDateTime;

@Table(
        name = "outbox",
        indexes = @Index(name = "idx_shard_key_created_at", columnList = "shard_key asc, created_at asc")
)
@Getter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Outbox {
    @Id
    private Long outboxId;
    @Enumerated(EnumType.STRING)
    @Column(length = 100, nullable = false)
    private EventType eventType;
    @Column(length = 5000, nullable = false)
    private String payload;
    @Column(nullable = false)
    private Long shardKey;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public static Outbox create(Long outboxId, EventType eventType, String payload, Long shardKey) {
        Outbox outbox = new Outbox();
        outbox.outboxId = outboxId;
        outbox.eventType = eventType;
        outbox.payload = payload;
        outbox.shardKey = shardKey;
        outbox.createdAt = LocalDateTime.now();
        return outbox;
    }
}
