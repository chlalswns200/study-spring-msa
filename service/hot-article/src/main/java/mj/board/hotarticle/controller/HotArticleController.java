package mj.board.hotarticle.controller;

import lombok.RequiredArgsConstructor;
import mj.board.hotarticle.service.HotArticleService;
import mj.board.hotarticle.service.response.HotArticleResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HotArticleController {

    private final HotArticleService hotArticleService;

    @GetMapping("/v1/hot-articles/articles/data/{dataStr}")
    public List<HotArticleResponse> readAll(
            @PathVariable("dataStr") String dataStr
    ) {
        return hotArticleService.readALl(dataStr);
    }
}
