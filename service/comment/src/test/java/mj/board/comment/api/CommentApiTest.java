package mj.board.comment.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mj.board.comment.service.response.CommentPageResponse;
import mj.board.comment.service.response.CommentResponse;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

public class CommentApiTest {

    RestClient restClient = RestClient.create("http://localhost:9001");


    @Test
    void create() {
        CommentResponse response1 = createComment(new CommentCreateRequest(1L, "my comment1", null, 1L));
        CommentResponse response2 = createComment(new CommentCreateRequest(1L, "my comment2", response1.getCommentId(), 1L));
        CommentResponse response3 = createComment(new CommentCreateRequest(1L, "my comment3", response1.getCommentId(), 1L));


        System.out.println("response1 = " + response1.getCommentId());
        System.out.println("\tresponse1 = " + response1.getCommentId());
        System.out.println("\tresponse1 = " + response1.getCommentId());

//        response1 = 344822215670980608
//        response1 = 344822215670980608
//        response1 = 344822215670980608
    }


    @Test
    void read() {
        CommentResponse response = restClient.get()
                .uri("/v1/comments/{commentId}", 344822215670980608L)
                .retrieve()
                .body(CommentResponse.class);
        System.out.println("response = " + response);
    }

    @Test
    void delete() {
        restClient.delete()
                .uri("/v1/comments/{commentId}", 345052941060603904L)
                .retrieve();
    }

    CommentResponse createComment(CommentCreateRequest request) {
        return restClient.post()
                .uri("/v1/comments")
                .body(request)
                .retrieve()
                .body(CommentResponse.class);
    }

    @Test
    void readAll() {
        CommentPageResponse response = restClient.get()
                .uri("/v1/comments?articleId=1&page=1&pageSize=10")
                .retrieve()
                .body(CommentPageResponse.class);

        System.out.println("response = " + response.getCommentCount());

        for (CommentResponse commentResponse : response.getCommentList()) {
            if (!commentResponse.getCommentId().equals(commentResponse.getParentCommentId())) {
                System.out.print("\t");
            }
            System.out.println("commentResponse.getCommentId() = " + commentResponse.getCommentId());
        }
        /*
        1 번 페이지 수행 결과
        commentResponse.getCommentId() = 345057625497927680
	        commentResponse.getCommentId() = 345057625665699841
        commentResponse.getCommentId() = 345057625497927681
	        commentResponse.getCommentId() = 345057625632145408
        commentResponse.getCommentId() = 345057625497927682
	        commentResponse.getCommentId() = 345057625665699840
        commentResponse.getCommentId() = 345057625497927683
	        commentResponse.getCommentId() = 345057625632145411
        commentResponse.getCommentId() = 345057625497927684
	        commentResponse.getCommentId() = 345057625632145409
        * */
    }

    @Test
    void readInfiniteScroll() {
        List<CommentResponse> response1 = restClient.get()
                .uri("/v1/comments/infinite-scroll?articleId=1&pageSize=5")
                .retrieve()
                .body(new ParameterizedTypeReference<List<CommentResponse>>() {
                });

        System.out.println("first page");

        for (CommentResponse commentResponse : response1) {
            if (!commentResponse.getCommentId().equals(commentResponse.getParentCommentId())) {
                System.out.print("\t");
            }
            System.out.println("commentResponse.getCommentId() = " + commentResponse.getCommentId());
        }

        Long lastParentCommentId = response1.getLast().getParentCommentId();
        Long lastCommentId = response1.getLast().getCommentId();

        List<CommentResponse> response2 = restClient.get()
                .uri("/v1/comments/infinite-scroll?articleId=1&pageSize=5&lastParentCommentId=%s&lastCommentId=%s".formatted(lastParentCommentId, lastCommentId))
                .retrieve()
                .body(new ParameterizedTypeReference<List<CommentResponse>>() {
                });

        System.out.println("second page");
        for (CommentResponse commentResponse : response2) {
            if (!commentResponse.getCommentId().equals(commentResponse.getParentCommentId())) {
                System.out.print("\t");
            }
            System.out.println("commentResponse.getCommentId() = " + commentResponse.getCommentId());
        }
    }

    @Getter
    @AllArgsConstructor
    public static class CommentCreateRequest {
        private Long articleId;
        private String content;
        private Long parentCommentId;
        private Long writerId;
    }
}
