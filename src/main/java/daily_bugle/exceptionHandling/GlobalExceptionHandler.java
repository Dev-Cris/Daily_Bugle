package daily_bugle.exceptionHandling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {



//    @ExceptionHandler(AuthorNotFoundException.class)
//    public ResponseEntity<ValidationError> handleAuthorNotFound(AuthorNotFoundException exeption){
//        ValidationError validationError = new ValidationError("authorID", "Author not found with id: "
//                + exeption.getAuhtorId());
//        log.error(validationError.getErrorMessage());
//        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
//    }

}
