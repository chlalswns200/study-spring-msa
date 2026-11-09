package mj.board.articleread.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mj.board.articleread.service.ArticleReadService;
import mj.board.common.event.Event;
import mj.board.common.event.EventPayload;
import mj.board.common.event.EventType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ArticleReadEventConsumer {
    private final ArticleReadService articleReadService;

    @KafkaListener(topics = {
            EventType.Topic.MJ_BOARD_ARTICLE,
            EventType.Topic.MJ_BOARD_COMMENT,
            EventType.Topic.MJ_BOARD_LIKE
    })
    public void listen(String message, Acknowledgment ack) {
        log.info("[ArticleReadEventConsumer,listen] message={}", message);
        Event<EventPayload> event = Event.fromJson(message);
        if (event != null) {
            articleReadService.handleEvent(event);
        }
        ack.acknowledge();
    }
}
