package mj.board.article.service;

import kuke.board.common.snowflake.Snowflake;
import lombok.RequiredArgsConstructor;
import mj.board.article.entity.Article;
import mj.board.article.repository.ArticleRepository;
import mj.board.article.service.request.ArticleCreateRequest;
import mj.board.article.service.request.ArticleUpdateRequest;
import mj.board.article.service.response.ArticlePageResponse;
import mj.board.article.service.response.ArticleResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final Snowflake snowflake = new Snowflake();
    private final ArticleRepository articleRepository;

    @Transactional
    public ArticleResponse create(ArticleCreateRequest request) {
        Article savedArticle = articleRepository.save(
                Article.create(snowflake.nextId(), request.getTitle(), request.getContent(), request.getBoardId(), request.getWriterId()));
        return ArticleResponse.from(savedArticle);
    }

    @Transactional
    public ArticleResponse update(Long articleId, ArticleUpdateRequest request) {
        Article findArticle = articleRepository.findById(articleId).orElseThrow();
        findArticle.update(request.getTitle(), request.getContent());
        return ArticleResponse.from(findArticle);
    }

    public ArticleResponse read(Long articleId) {
        Article findArticle = articleRepository.findById(articleId).orElseThrow();
        return ArticleResponse.from(findArticle);
    }

    @Transactional
    public void delete(Long articleId) {
        articleRepository.deleteById(articleId);
    }

    public ArticlePageResponse readAll(Long boardId, Long page, Long pageSize) {
        return ArticlePageResponse.of(
                articleRepository.findAll(boardId, (page - 1) * pageSize, pageSize).stream()
                        .map(ArticleResponse::from)
                        .toList(),
                articleRepository.count(
                        boardId, PageLimitCalculator.calculatePageLimit(page, pageSize, 10L)
                )
        );
    }

    public List<ArticleResponse> readAllInfiniteScroll(Long boardId, Long pageSize, Long lastArticleId) {
        List<Article> articles = lastArticleId == null ? articleRepository.findAllInfiniteScroll(boardId, pageSize)
                : articleRepository.findAllInfiniteScroll(boardId, pageSize, lastArticleId);

        return articles.stream().map(ArticleResponse::from).toList();
    }


}
