package daily_bugle.service;

import daily_bugle.domain.Article;
import daily_bugle.domain.Author;
import daily_bugle.dto.ArticleCreateCommand;
import daily_bugle.dto.ArticleInfo;
import daily_bugle.repository.ArticleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final AuthorSerivce authorSerivce;
    private final ModelMapper modelMapper;


    public ArticleService(ArticleRepository articleRepository, AuthorSerivce authorSerivce, ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.authorSerivce = authorSerivce;
        this.modelMapper = modelMapper;
    }

    public ArticleInfo saveArticle(ArticleCreateCommand command) {
        Article articleToBeSaved = modelMapper.map(command, Article.class);
        Author authorForThisArticle = authorSerivce.findAuthorById(command.getAuthorId());
        articleToBeSaved.setAuthor(authorForThisArticle);

        articleRepository.save(articleToBeSaved);
        ArticleInfo articleInfo = modelMapper.map(articleToBeSaved, ArticleInfo.class);
        articleInfo.setAuthorName(authorForThisArticle.getAuthorName());

        return articleInfo;
    }

//    public ArticleArchivedInfo updateArticle(ArticleCreateCommand command){
//        Article articleToBeArchived = articleRepository;
//        if (command.getTitle().isBlank() && command.getContent().isBlank()) {
//            articleToBeArchived.archive();
//        }
//
//    }

}
