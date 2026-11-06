package mj.board.articleread.service.event.handler;

import lombok.RequiredArgsConstructor;
import mj.board.articleread.repository.ArticleQueryModelRepository;
import mj.board.common.event.Event;
import mj.board.common.event.EventType;
import mj.board.common.event.payload.ArticleLikedEventPayload;
import mj.board.common.event.payload.CommentCreatedEventPayload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticleLikedEventHandler implements EventHandler<ArticleLikedEventPayload> {
    private final ArticleQueryModelRepository articleQueryModelRepository;

    @Override
    public void handle(Event<ArticleLikedEventPayload> event) {
        articleQueryModelRepository.read(event.getPayload().getArticleId())
                .ifPresent(articleQueryModel ->{
                    articleQueryModel.updateBy(event.getPayload());
                    articleQueryModelRepository.update(articleQueryModel);
                });
    }

    @Override
    public boolean supports(Event<ArticleLikedEventPayload> event) {
        return EventType.ARTICLE_LIKED == event.getType();
    }
}
