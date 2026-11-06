package mj.board.articleread.service.event.handler;

import lombok.RequiredArgsConstructor;
import mj.board.articleread.repository.ArticleQueryModel;
import mj.board.articleread.repository.ArticleQueryModelRepository;
import mj.board.common.event.Event;
import mj.board.common.event.EventType;
import mj.board.common.event.payload.ArticleCreatedEventPayload;
import mj.board.common.event.payload.CommentCreatedEventPayload;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class CommentCreatedEventHandler implements EventHandler<CommentCreatedEventPayload> {
    private final ArticleQueryModelRepository articleQueryModelRepository;

    @Override
    public void handle(Event<CommentCreatedEventPayload> event) {
        articleQueryModelRepository.read(event.getPayload().getArticleId())
                .ifPresent(articleQueryModel ->{
                    articleQueryModel.updateBy(event.getPayload());
                    articleQueryModelRepository.update(articleQueryModel);
                });
    }

    @Override
    public boolean supports(Event<CommentCreatedEventPayload> event) {
        return EventType.COMMENT_CREATED == event.getType();
    }
}
