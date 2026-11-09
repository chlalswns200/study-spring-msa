package mj.board.articleread.api;

import mj.board.articleread.service.response.ArticleReadResponse;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

public class ArticleReadApiTest {
    RestClient restClient = RestClient.create("http://localhost:9005");

    @Test
    void readTest() {
        ArticleReadResponse read = restClient.get()
                .uri("/v1/articles/{articleId}", 125935728357814272L)
                .retrieve()
                .body(ArticleReadResponse.class);
        System.out.println("read = " + read);

    }
}
