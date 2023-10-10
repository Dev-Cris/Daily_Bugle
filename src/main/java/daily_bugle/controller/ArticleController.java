package daily_bugle.controller;

import daily_bugle.dto.ArticleArchivedInfo;
import daily_bugle.dto.ArticleCreateCommand;
import daily_bugle.dto.ArticleInfo;
import daily_bugle.dto.ArticleUpdateCommand;
import daily_bugle.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/article")
@Slf4j
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseEntity<ArticleInfo> save(@Valid @RequestBody ArticleCreateCommand command) {
        log.info("HTTP request, POST to api/article: " + command.toString());
        ArticleInfo articleInfo = articleService.saveArticle(command);
        return new ResponseEntity<>(articleInfo, HttpStatus.CREATED);
    }
    @PutMapping()
    public ResponseEntity<ArticleArchivedInfo> archiveArticle(@Valid @RequestBody ArticleUpdateCommand command){

    }
}
