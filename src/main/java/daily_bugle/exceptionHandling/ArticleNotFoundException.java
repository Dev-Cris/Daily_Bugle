package daily_bugle.exceptionHandling;

import lombok.Getter;

@Getter
public class ArticleNotFoundException extends RuntimeException{

    private final Long authorId;

    public ArticleNotFoundException(Long authorId){
        this.authorId = authorId;
    }

}
