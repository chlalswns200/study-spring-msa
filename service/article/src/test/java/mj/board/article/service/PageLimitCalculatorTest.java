package mj.board.article.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PageLimitCalculatorTest {

    @Test
    void calculatePageLimitTest() {
        calculatePageLimitTest(1L,30L,10L,301L);
        calculatePageLimitTest(7L,30L,10L,301L);
        calculatePageLimitTest(11L,30L,10L,601L);
    }

    void calculatePageLimitTest(Long page, Long pageSize, Long movablePageCount, Long expected) {
        Long limit = PageLimitCalculator.calculatePageLimit(page, pageSize, movablePageCount);
        assertThat(limit).isEqualTo(expected);
    }

}