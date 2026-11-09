package mj.board.comment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Table(
        name = "comment",
        indexes = @Index(
                name = "idx_article_id_parent_comment_id_comment_id",
                columnList = "article_id asc, parent_comment_id asc, comment_id asc"
        )
)
@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    private Long commentId;
    @Column(length = 3000, nullable = false)
    private String content;
    private Long parentCommentId;
    private Long articleId; // 샤드키
    private Long writerId;
    private Boolean deleted;
    private LocalDateTime createdAt;


    public static Comment create(Long commentId, String content, Long parentCommentId, Long articleId, Long writerId) {
        Comment comment = new Comment();
        comment.commentId = commentId;
        comment.content = content;
        comment.parentCommentId = parentCommentId == null ? commentId : parentCommentId;
        comment.articleId = articleId;
        comment.writerId = writerId;
        comment.deleted = false;
        comment.createdAt = LocalDateTime.now();
        return comment;
    }

    public boolean isRoot() {
        return this.parentCommentId.equals(this.commentId);
    }

    public void delete() {
        this.deleted = true;
    }

}
