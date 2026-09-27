package mj.board.like.api;

import mj.board.like.repository.ArticleLikeRepository;
import mj.board.like.service.response.ArticleLikeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

public class LikeApiTest {

    RestClient restClient = RestClient.create("http://localhost:9002");

    @Test
    void likeAndUnlikeTest() {
        Long articleId = 9999L;

        like(articleId,1L);
        like(articleId,2L);
        like(articleId,3L);

        ArticleLikeResponse read = read(articleId, 1L);
        ArticleLikeResponse read2 = read(articleId, 2L);
        ArticleLikeResponse read3 = read(articleId, 3L);
        System.out.println("read = " + read);
        System.out.println("read2 = " + read2);
        System.out.println("read3 = " + read3);

    }

    void like(Long articleId, Long userId) {
        restClient.post()
                .uri("/v1/article-like/aritcles/{articleId}/users/{userId}", articleId, userId)
                .retrieve();
    }

    void unlike(Long articleId, Long userId) {
        restClient.delete()
                .uri("/v1/article-like/aritcles/{articleId}/users/{userId}", articleId, userId)
                .retrieve();
    }

    ArticleLikeResponse read(Long articleId, Long userId) {
        return restClient.get()
                .uri("/v1/article-like/aritcles/{articleId}/users/{userId}", articleId, userId)
                .retrieve()
                .body(ArticleLikeResponse.class);

    }
}
