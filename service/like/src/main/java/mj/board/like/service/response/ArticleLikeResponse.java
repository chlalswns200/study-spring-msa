package mj.board.like.service.response;

import lombok.Getter;
import lombok.ToString;
import mj.board.like.entity.ArticleLike;

import java.time.LocalDateTime;

@Getter
@ToString
public class ArticleLikeResponse {
    private Long articleLikeId;
    private Long articleId;
    private Long userId;
    private LocalDateTime createAt;

    public static ArticleLikeResponse from(ArticleLike articleLike) {
        ArticleLikeResponse response = new ArticleLikeResponse();
        response.articleLikeId = articleLike.getArticleLikeId();
        response.articleId = articleLike.getArticleId();
        response.userId = articleLike.getUserId();
        response.createAt = articleLike.getCreatedAt();
        return response;
    }
}
