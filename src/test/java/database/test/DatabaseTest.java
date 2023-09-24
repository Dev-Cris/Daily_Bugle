package database.test;

import article.Article;
import database.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import users.Author;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {


    private static EntityManagerFactory entityManagerFactory;
    private Database database;
    private Author author;
    private Article article;


    @BeforeEach
    void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("daily_bugle_test");
        database = new Database(entityManagerFactory);
        author = new Author("István");
        article = new Article("Teszt", author, "Tesztelni jó lesz ez is");
    }

    @Test
    void test_registerAuthor() {

        assertThrows(NoResultException.class, () -> database.findAuthorByName(author.getName()));
        database.registerAuthor(author);
        Author author1 = database.findAuthorByName(author.getName());
        assertEquals(author.getId(), author1.getId());
    }

    @Test
    void test_registerArticle() {
        assertNull(article.getId());
        database.registerAuthor(author);

        database.registerArticle(article);
        assertNotNull(article.getId());
        assertEquals(1, article.getId());
    }

    @Test
    void test_updateArticleStatus() {
        database.registerAuthor(author);
        database.registerArticle(article);
        assertFalse(article.isArchived());
        database.updateArticleStatus(article);
        assertTrue(article.isArchived());
    }

//    @Test
//    void test_registerArticle_withoutRegisteredAuthor(){
//        assertThrows(NoResultException.class, () -> new Engine().createArticle(author));
//        database.registerAuthor(author);
//        database.registerArticle(article);
//        assertEquals(1, article.getId());
//    }

}