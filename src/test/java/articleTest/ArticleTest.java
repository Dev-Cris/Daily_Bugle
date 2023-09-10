package articleTest;


import article.Article;
import org.junit.Test;
import users.Author;

import static org.junit.Assert.*;


public class ArticleTest {

    Author cris = new Author("Cris");

    @Test
    void test_newArticle() {
        Article article = new Article("Title1", cris, "Random content");
        assertEquals("Title1", article.getTitle());
        assertEquals("Cris", article.getAuthor().getName());
        assertEquals("Random content", article.getContent());
        assertEquals(14, article.getContent().length());
    }


}
