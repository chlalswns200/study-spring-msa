package mj.board.comment.entity;

import org.junit.jupiter.api.Test;

import java.net.CookieHandler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CommentPathTest {

    @Test
    void createChildCommentTest() {
        // ----- <- 생성
        createChildCommentTest(CommentPath.create(""), null, "00000");

        // 00000
        //       00000 <- 생성
        createChildCommentTest(CommentPath.create("00000"), null, "0000000000");

        // 00000
        // 00001 <- 생성
        createChildCommentTest(CommentPath.create(""), "00000", "00001");

        // 0000z
        //        abcdz
        //              zzzzz
        //                   zzzzzz
        //        abce0 <- 생성
        createChildCommentTest(CommentPath.create("0000z"),"0000zabcdzzzzzzzzzzz","0000zabce0");
    }

    void createChildCommentTest(CommentPath commentPath, String descendantsTopPath, String expectedChildPath) {
        CommentPath childCommentPath = commentPath.createChildCommentPath(descendantsTopPath);
        assertThat(childCommentPath.getPath()).isEqualTo(expectedChildPath);
    }

    @Test
    void createChildPathMaxDepthTest() {
        assertThatThrownBy(() ->
                CommentPath.create("zzzzz".repeat(5)).createChildCommentPath(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createChildCommentPathIfChunkOverflowTest() {
        CommentPath commentPath = CommentPath.create("");

        assertThatThrownBy(() ->
                commentPath.createChildCommentPath("zzzzz"))
                .isInstanceOf(IllegalArgumentException.class);
    }

}