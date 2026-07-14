package mj.board.article.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mj.board.article.service.request.ArticleCreateRequest;
import mj.board.article.service.response.ArticleResponse;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

public class ArticleApiTest {

    RestClient restClient = RestClient.create("http://localhost:9000");

    @Test
    void createTest() {
        ArticleResponse response = create(new ArticleCreateRequest("hello title", "my content", 1L, 1L));
        System.out.println("response = " + response);
    }

    @Test
    void updateTest() {
        ArticleResponse update = update(new ArticleUpdateRequest("수정제목", "수정내용 content"), 335411627701133312L);
        System.out.println("update = " + update);
    }

    @Test
    void deleteTest() {
        ArticleResponse delete = delete(335414656483549184L);
        System.out.println("delete = " + delete);
    }

    @Test
    void readTest() {
        ArticleResponse read = read(335411627701133312L);
        System.out.println("read = " + read);
    }

    ArticleResponse create(ArticleCreateRequest request) {
        return restClient.post()
                .uri("/v1/articles")
                .body(request)
                .retrieve()
                .body(ArticleResponse.class);
    }

    ArticleResponse update(ArticleUpdateRequest request,Long articleId) {
        return restClient.put()
                .uri("/v1/articles/{articleId}", articleId)
                .body(request)
                .retrieve()
                .body(ArticleResponse.class);
    }

    ArticleResponse delete(Long id) {
        return restClient.delete()
                .uri("/v1/articles/{articleId}", id)
                .retrieve()
                .body(ArticleResponse.class);
    }

    ArticleResponse read(Long id) {
        return restClient.get()
                .uri("/v1/articles/{articleId}",id)
                .retrieve()
                .body(ArticleResponse.class);
    }
    @Getter
    @AllArgsConstructor
    static class ArticleCreateRequest {
        private String title;
        private String content;
        private Long writerId;
        private Long boardId;
    }

    @Getter
    @AllArgsConstructor
    static class ArticleUpdateRequest {
        private String title;
        private String content;
    }

}
