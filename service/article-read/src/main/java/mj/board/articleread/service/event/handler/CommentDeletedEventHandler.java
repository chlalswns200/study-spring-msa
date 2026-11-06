package mj.board.articleread.service.event.handler;

import lombok.RequiredArgsConstructor;
import mj.board.articleread.repository.ArticleQueryModelRepository;
import mj.board.common.event.Event;
import mj.board.common.event.EventType;
import mj.board.common.event.payload.ArticleDeletedEventPayload;
import mj.board.common.event.payload.CommentDeletedEventPayload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentDeletedEventHandler implements EventHandler<CommentDeletedEventPayload> {
    private final ArticleQueryModelRepository articleQueryModelRepository;

    @Override
    public void handle(Event<CommentDeletedEventPayload> event) {
        articleQueryModelRepository.read(event.getPayload().getArticleId())
                .ifPresent(articleQueryModel -> {
                    articleQueryModel.updateBy(event.getPayload());
                    articleQueryModelRepository.update(articleQueryModel);
                });
    }

    @Override
    public boolean supports(Event<CommentDeletedEventPayload> event) {
        return EventType.COMMENT_DELETED == event.getType();
    }
}
