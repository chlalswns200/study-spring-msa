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

    @PostMapping("/v1/article-like/aritcles/{articleId}/users/{userId}")
    public void like(
            @PathVariable Long articleId,
            @PathVariable Long userId
    ) {
        articleLikeService.like(articleId, userId);
    }

    @DeleteMapping("/v1/article-like/aritcles/{articleId}/users/{userId}")
    public void unlike(
            @PathVariable Long articleId,
            @PathVariable Long userId
    ) {
        articleLikeService.unlike(articleId, userId);
    }

}
