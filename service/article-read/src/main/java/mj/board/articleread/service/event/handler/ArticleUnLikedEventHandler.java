package mj.board.articleread.service.event.handler;

import lombok.RequiredArgsConstructor;
import mj.board.articleread.repository.ArticleQueryModelRepository;
import mj.board.common.event.Event;
import mj.board.common.event.EventType;
import mj.board.common.event.payload.ArticleLikedEventPayload;
import mj.board.common.event.payload.ArticleUnlikedEventPayload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticleUnLikedEventHandler implements EventHandler<ArticleUnlikedEventPayload> {
    private final ArticleQueryModelRepository articleQueryModelRepository;

    @Override
    public void handle(Event<ArticleUnlikedEventPayload> event) {
        articleQueryModelRepository.read(event.getPayload().getArticleId())
                .ifPresent(articleQueryModel ->{
                    articleQueryModel.updateBy(event.getPayload());
                    articleQueryModelRepository.update(articleQueryModel);
                });
    }

    @Override
    public boolean supports(Event<ArticleUnlikedEventPayload> event) {
        return EventType.ARTICLE_UNLIKED == event.getType();
    }
}
