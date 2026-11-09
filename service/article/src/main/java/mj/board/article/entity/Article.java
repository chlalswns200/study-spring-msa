package mj.board.article.entity;

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
        name = "article",
        indexes = @Index(name = "idx_board_id_article_id", columnList = "board_id asc, article_id desc")
)
@Getter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    private Long articleId;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 3000, nullable = false)
    private String content;
    private Long boardId; // shard key
    private Long writerId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static Article create(Long articleId, String title, String content, Long boardId, Long writerId) {
        Article article = new Article();
        article.articleId = articleId;
        article.title = title;
        article.content = content;
        article.boardId = boardId;
        article.writerId = writerId;
        article.createdAt = LocalDateTime.now();
        article.modifiedAt = article.createdAt;
        return article;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        modifiedAt = LocalDateTime.now();
    }
}
