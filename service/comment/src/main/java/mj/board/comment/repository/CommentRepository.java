package mj.board.comment.repository;

import mj.board.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select count(*) from (" +
            "select comment_id from comment " +
            "where article_id = :articleId and parent_comment_id = :parentCommentId " +
            "limit :limit" +
            ") as sub", nativeQuery = true)
    Long countBy(@Param("articleId") Long articleId,
                 @Param("parentCommentId") Long parentCommentId,
                 @Param(("limit")) Long limit);

}
