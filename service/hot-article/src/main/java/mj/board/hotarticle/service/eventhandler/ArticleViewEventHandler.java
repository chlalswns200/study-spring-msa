package mj.board.hotarticle.service.eventhandler;

import lombok.RequiredArgsConstructor;
import mj.board.common.event.Event;
import mj.board.common.event.EventType;
import mj.board.common.event.payload.ArticleViewEventPayload;
import mj.board.hotarticle.repository.ArticleViewCountRepository;
import mj.board.hotarticle.utils.TimeCalculatorUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticleViewEventHandler implements EventHandler<ArticleViewEventPayload> {
    private final ArticleViewCountRepository articleViewCountRepository;
    @Override
    public void handle(Event<ArticleViewEventPayload> event) {
        ArticleViewEventPayload payload = event.getPayload();
        articleViewCountRepository.createOrUpdate(
                payload.getArticleId(),
                payload.getArticleViewCount(),
                TimeCalculatorUtils.calculateDurationToMidnight()
        );
    }

    @Override
    public boolean supports(Event<ArticleViewEventPayload> event) {
        return EventType.ARTICLE_VIEWED == event.getType();
    }

    @Override
    public Long findArticleId(Event<ArticleViewEventPayload> event) {
        return event.getPayload().getArticleId();
    }
}
