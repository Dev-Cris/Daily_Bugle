package daily_bugle.exceptionHandling;

import lombok.Getter;

@Getter
public class AuthorNotFoundException extends RuntimeException{

    private final Long authorId;

    public AuthorNotFoundException(Long authorId){
        this.authorId = authorId;
    }

}
