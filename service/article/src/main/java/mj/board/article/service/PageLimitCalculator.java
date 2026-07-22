package mj.board.article.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mj.board.article.entity.Article;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class PageLimitCalculator {

    public static Long calculatePageLimit(Long page, Long pageSize, Long movablePageCount) {
        return (((page - 1) / movablePageCount) + 1) * pageSize * movablePageCount + 1;
    }
}
