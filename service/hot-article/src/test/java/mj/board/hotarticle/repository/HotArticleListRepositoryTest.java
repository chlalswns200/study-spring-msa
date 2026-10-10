package mj.board.hotarticle.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HotArticleListRepositoryTest {
    @Autowired
    HotArticleListRepository hotArticleListRepository;

    @Test
    void addTest() throws InterruptedException {
        // given
        LocalDateTime localDateTime = LocalDateTime.of(2026, 10, 10, 0, 0);
        long limit = 3;

        //when
        hotArticleListRepository.add(1L,localDateTime,2L,limit, Duration.ofSeconds(3));
        hotArticleListRepository.add(2L,localDateTime,4L,limit, Duration.ofSeconds(3));
        hotArticleListRepository.add(3L,localDateTime,1L,limit, Duration.ofSeconds(3));
        hotArticleListRepository.add(4L,localDateTime,5L,limit, Duration.ofSeconds(3));
        hotArticleListRepository.add(5L,localDateTime,1L,limit, Duration.ofSeconds(3));

        //then
        List<Long> articleIds = hotArticleListRepository.readAll("20261010");

        assertThat(articleIds).hasSize(Long.valueOf(limit).intValue());
        assertThat(articleIds.get(0)).isEqualTo(4L);
        assertThat(articleIds.get(1)).isEqualTo(2L);
        assertThat(articleIds.get(2)).isEqualTo(1L);

        TimeUnit.SECONDS.sleep(5);

        assertThat(hotArticleListRepository.readAll("20261010")).isEmpty();
    }
}