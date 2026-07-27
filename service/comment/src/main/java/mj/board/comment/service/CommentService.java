package mj.board.comment.service;

import kuke.board.common.snowflake.Snowflake;
import lombok.RequiredArgsConstructor;
import mj.board.comment.entity.Comment;
import mj.board.comment.repository.CommentRepository;
import mj.board.comment.service.request.CommentCreateRequest;
import mj.board.comment.service.response.CommentResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.function.Predicate.not;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final Snowflake snowflake = new Snowflake();

    @Transactional
    public CommentResponse create(CommentCreateRequest request) {
        Comment parentComment = findParent(request);
        Comment savedComment = commentRepository.save(
                Comment.create(
                        snowflake.nextId(),
                        request.getContent(),
                        parentComment == null ? null : parentComment.getCommentId(),
                        request.getArticleId(),
                        request.getWriterId()
                )
        );
        return CommentResponse.from(savedComment);
    }

    private Comment findParent(CommentCreateRequest request) {
        Long parentCommentId = request.getParentCommentId();
        if (parentCommentId == null) {
            return null;
        }
        return commentRepository.findById(parentCommentId)
                .filter(not(Comment::getDeleted))
                .filter(Comment::isRoot)
                .orElseThrow();
    }

    public CommentResponse read(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow();
        return CommentResponse.from(comment);
    }

    public void delete(Long id) {
        commentRepository.findById(id)
                .filter(not(Comment::getDeleted))
                .ifPresent(comment -> {
                    if (hasChildren(comment)) {
                        comment.delete();
                    } else {
                        delete(comment);
                    }
                });
    }

    private boolean hasChildren(Comment comment) {
        return commentRepository.countBy(comment.getArticleId(), comment.getCommentId(), 2L) == 2L;
    }

    @Transactional
    public void delete(Comment comment) {
        commentRepository.delete(comment);
        if (!comment.isRoot()) {
            commentRepository.findById(comment.getParentCommentId())
                    .filter(Comment::getDeleted)
                    .filter(not(this::hasChildren))
                    .ifPresent(this::delete);
        }
    }


}
