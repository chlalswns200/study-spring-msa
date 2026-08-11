package mj.board.comment.service.response;

import lombok.Getter;

import java.util.List;

@Getter
public class CommentPageResponse {
    private List<CommentResponse> commentList;
    private Long commentCount;

    public static CommentPageResponse of(List<CommentResponse> commentList, Long commentCount) {
        CommentPageResponse response = new CommentPageResponse();
        response.commentList = commentList;
        response.commentCount = commentCount;
        return response;
    }
}
