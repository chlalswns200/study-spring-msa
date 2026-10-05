package mj.board.comment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name="article_comment_count")
@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleCountComment {
    @Id
    private Long articleId;
    private Long commentCount;

    public static ArticleCountComment init(Long articleId, Long commentCount) {
        ArticleCountComment articleCountComment = new ArticleCountComment();
        articleCountComment.articleId = articleId;
        articleCountComment.commentCount = commentCount;
        return articleCountComment;
    }
}
