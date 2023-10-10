package daily_bugle.controller;

import daily_bugle.dto.AuthorCreateUpdateCommand;
import daily_bugle.dto.AuthorInfo;
import daily_bugle.service.AuthorSerivce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/author")
@Slf4j
public class AuthorController {

    private final AuthorSerivce authorSerivce;

    public AuthorController(AuthorSerivce authorSerivce) {
        this.authorSerivce = authorSerivce;
    }

    @PostMapping
    public ResponseEntity<AuthorInfo> save(@Valid @RequestBody AuthorCreateUpdateCommand command) {
        log.info("HTTP request, POST to api/author: " + command.toString());
        AuthorInfo authorInfo = authorSerivce.saveAuthor(command);
        return new ResponseEntity<>(authorInfo, HttpStatus.CREATED);
    }

}
