package mj.board.article.repository;

import lombok.extern.slf4j.Slf4j;
import mj.board.article.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ArticleRepositoryTest {
    @Autowired
    ArticleRepository articleRepository;

    @Test
    void findAllTest() {
        List<Article> articles = articleRepository.findAll(1L,14999L,30L);
        log.info("articles: {}", articles.size());
        for (Article article : articles) {
            log.info("article: {}", article);
        }
    }

    @Test
    void countTest() {
        Long count = articleRepository.count(1L, 1000L);
        log.info("count: {}", count);
    }

}