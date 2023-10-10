package daily_bugle.exceptionHandling;

import lombok.Getter;

@Getter
public class AuthorNotFoundExeption extends RuntimeException{

    private final Long auhtorId;

    public AuthorNotFoundExeption(Long authorId){
        this.auhtorId = authorId;
    }

}
