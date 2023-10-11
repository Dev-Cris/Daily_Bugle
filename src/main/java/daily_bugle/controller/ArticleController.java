package daily_bugle.controller;

import daily_bugle.dto.ArticleCreateCommand;
import daily_bugle.dto.ArticleInfo;
import daily_bugle.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

//    @PutMapping()
//    public ResponseEntity<ArticleArchivedInfo> archiveArticle(@Valid @RequestBody ArticleUpdateCommand command){
//
//    }

    private boolean contentValidation(String content){
        boolean result = false;
        if (content.replace(" ", "").length() > 1000) {
            return result;
        } else if (!content.isBlank() && !content.isEmpty()) {
            result = true;
        }
        return result;
    }
}
