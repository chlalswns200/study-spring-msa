package mj.board.common.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mj.board.common.event.payload.*;

@Slf4j
@Getter
@RequiredArgsConstructor
public enum EventType {
    ARTICLE_CREATED(ArticleCreatedEventPayload.class,Topic.MJ_BOARD_ARTICLE),
    ARTICLE_UPDATED(ArticleUpdatedEventPayload.class,Topic.MJ_BOARD_ARTICLE),
    ARTICLE_DELETED(ArticleDeletedEventPayload.class,Topic.MJ_BOARD_ARTICLE),
    COMMENT_CREATED(CommentCreatedEventPayload.class,Topic.MJ_BOARD_COMMENT),
    COMMENT_DELETED(CommentDeletedEventPayload.class,Topic.MJ_BOARD_COMMENT),
    ARTICLE_LIKED(ArticleLikedEventPayload.class,Topic.MJ_BOARD_LIKE),
    ARTICLE_UNLIKED(ArticleUnlikedEventPayload.class,Topic.MJ_BOARD_LIKE),
    ARTICLE_VIEWED(ArticleViewEventPayload.class,Topic.MJ_BOARD_VIEW);

    private final Class<? extends EventPayload> payloadClass;
    private final String topic;

    public static EventType from(String type) {
        try {
            return valueOf(type);
        } catch (Exception e) {
            log.error("[EventType.from] type={}", type, e);
            return null;
        }
    }

    public static class Topic {
        public static final String MJ_BOARD_ARTICLE = "mj-board-article";
        public static final String MJ_BOARD_COMMENT = "mj-board-comment";
        public static final String MJ_BOARD_LIKE = "mj-board-like";
        public static final String MJ_BOARD_VIEW = "mj-board-view";
    }



}
