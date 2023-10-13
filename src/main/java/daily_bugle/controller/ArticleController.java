package daily_bugle.controller;

import daily_bugle.dto.ArticleArchiveCommand;
import daily_bugle.dto.ArticleCreateCommand;
import daily_bugle.dto.ArticleInfo;
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
        if (contentValidation(command.getContent())) {
            log.info("HTTP request, POST to api/article: " + command.toString());
            ArticleInfo articleInfo = articleService.saveArticle(command);
            return new ResponseEntity<>(articleInfo, HttpStatus.CREATED);
        } else {
            log.error("HTTP request, POST to api/article: " + command.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    private boolean contentValidation(String content){
        boolean result = false;
        if (content.replace(" ", "").length() > 1000) {
            return result;
        } else {
            result = true;
        }
        return result;
    }

    @PutMapping()
    public ResponseEntity<ArticleInfo> archiveArticle(@Valid @RequestBody ArticleArchiveCommand command){
        if (articleService.checkArticleStatus(command.getArticleId())) {
            log.error("HTTP request, PUT to api/article: " + command.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            log.info("HTTP request, PUT to api/article: " + command.toString());
            ArticleInfo articleInfo = articleService.archiveArticle(command.getArticleId());
            return new ResponseEntity<>(articleInfo, HttpStatus.OK);
        }
    }
}
