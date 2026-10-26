package mj.board.common.outboxmessagerelay;

import lombok.RequiredArgsConstructor;
import mj.board.common.event.Event;
import mj.board.common.event.EventPayload;
import mj.board.common.event.EventType;
import mj.board.common.snowflake.Snowflake;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutboxEventPublisher {
    private final Snowflake outboxIdSnowflake = new Snowflake();
    private final Snowflake eventIdSnowflake = new Snowflake();
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(EventType type, EventPayload payload, Long shardKey) {
        Outbox outbox = Outbox.create(outboxIdSnowflake.nextId(),
                type,
                Event.of(eventIdSnowflake.nextId(),
                        type,
                        payload).toJson(),
                shardKey % MessageRelayConstants.SHARD_COUNT);
        applicationEventPublisher.publishEvent(OutboxEvent.of(outbox));

    }
}
