package mj.board.like.controller;

import lombok.RequiredArgsConstructor;
import mj.board.like.service.ArticleLikeService;
import mj.board.like.service.response.ArticleLikeResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ArticleLikeController {
    private final ArticleLikeService articleLikeService;

    @GetMapping("/v1/article-like/aritcles/{articleId}/users/{userId}")
    public ArticleLikeResponse read(
            @PathVariable Long articleId,
            @PathVariable Long userId
    ) {
        return articleLikeService.read(articleId, userId);
    }

    @GetMapping("/v1/article-like/aritcles/{articleId}/count")
    public Long count(
            @PathVariable Long articleId
    ) {
        return articleLikeService.count(articleId);
    }

    @PostMapping("/v1/article-like/aritcles/{articleId}/users/{userId}/pessimistic-lock-1")
    public void likePessimisticLock1(
            @PathVariable Long articleId,
            @PathVariable Long userId
    ) {
        articleLikeService.likePessimisticLock1(articleId, userId);
    }

    @DeleteMapping("/v1/article-like/aritcles/{articleId}/users/{userId}/pessimistic-lock-1")
    public void unlikePessimisticLock1(
            @PathVariable Long articleId,
            @PathVariable Long userId
    ) {
        articleLikeService.unlikePessimisticLock1(articleId, userId);
    }

    @PostMapping("/v1/article-like/aritcles/{articleId}/users/{userId}/pessimistic-lock-2")
    public void likePessimisticLock2(
            @PathVariable Long articleId,
            @PathVariable Long userId
    ) {
        articleLikeService.likePessimisticLock2(articleId, userId);
    }

    @DeleteMapping("/v1/article-like/aritcles/{articleId}/users/{userId}/pessimistic-lock-2")
    public void unlikePessimisticLock2(
            @PathVariable Long articleId,
            @PathVariable Long userId
    ) {
        articleLikeService.unlikePessimisticLock2(articleId, userId);
    }

    @PostMapping("/v1/article-like/aritcles/{articleId}/users/{userId}/optimistic-lock")
    public void likeOptimisticLock(
            @PathVariable Long articleId,
            @PathVariable Long userId
    ) {
        articleLikeService.likeOptimisticLock(articleId, userId);
    }

    @DeleteMapping("/v1/article-like/aritcles/{articleId}/users/{userId}/optimistic-lock")
    public void unlikeOptimisticLock2(
            @PathVariable Long articleId,
            @PathVariable Long userId
    ) {
        articleLikeService.unlikeOptimisticLock(articleId, userId);
    }

}
