package mj.board.view.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mj.board.common.event.EventType;
import mj.board.common.event.payload.ArticleViewEventPayload;
import mj.board.common.outboxmessagerelay.OutboxEventPublisher;
import mj.board.view.entity.ArticleViewCount;
import mj.board.view.repository.ArticleViewCountBackUpRepository;
import mj.board.view.repository.ArticleViewCountRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticleViewCountBackUpProcessor {
    private final OutboxEventPublisher outboxEventPublisher;
    private final ArticleViewCountBackUpRepository articleViewCountBackUpRepository;

    @Transactional
    public void backUp(Long articleId, Long viewCount) {
        int result = articleViewCountBackUpRepository.updateViewCount(articleId, viewCount);
        if (result == 0) {
            articleViewCountBackUpRepository.findById(articleId)
                    .ifPresentOrElse(ignored -> {
                            },
                            () -> articleViewCountBackUpRepository.save(
                                    ArticleViewCount.init(articleId, viewCount)
                            ));
        }
        outboxEventPublisher.publish(
                EventType.ARTICLE_VIEWED,
                ArticleViewEventPayload.builder()
                        .articleId(articleId)
                        .articleViewCount(viewCount)
                        .build()
                , articleId
        );
    }
}
