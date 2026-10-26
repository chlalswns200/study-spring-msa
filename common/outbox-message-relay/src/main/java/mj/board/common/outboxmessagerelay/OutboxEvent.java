package mj.board.common.outboxmessagerelay;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OutboxEvent {
    private Outbox outBox;

    public static OutboxEvent of(Outbox outBox) {
        OutboxEvent outboxEvent = new OutboxEvent();
        outboxEvent.outBox = outBox;
        return outboxEvent;
    }
}
