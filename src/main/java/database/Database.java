package database;

import article.Article;
import users.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.List;

public class Database {
    private EntityManagerFactory entityManagerFactory;

    public Database(EntityManagerFactory entityManagerFactory) {

        this.entityManagerFactory = entityManagerFactory;
    }

    public void registerAuthor(Author author) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(author);
        System.out.println("Author registered successfully");

        transaction.commit();
        entityManager.close();
    }

    public void registerArticle(Article article) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(article);
        transaction.commit();

        entityManager.close();
    }

    public Author findAuthorByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Author author = entityManager
                .createQuery("select a from Author a where name like '" + name + "'", Author.class)
                .getSingleResult();

        transaction.commit();
        entityManager.close();

        return author;
    }

    public List<Article> getPublishedArticles() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        List<Article> articles = entityManager
                .createQuery("select a from Article a where archived = false ", Article.class)
                .getResultList();
        transaction.commit();

        entityManager.close();
        return articles;
    }

    public List<Article> getArchivedArticles() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        List<Article> articles = entityManager
                .createQuery("select a from Article a where archived = true ", Article.class)
                .getResultList();
        transaction.commit();

        entityManager.close();
        return articles;
    }

    public void updateArticleStatus(Article article) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        article.setArchived(true);
        article.setModificationDate(LocalDate.now());
        entityManager.merge(article);
        System.out.println("Article was archived successfully");
        transaction.commit();

        entityManager.close();
    }

    public List<Article> searchArticles(String input) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        List<Article> result = entityManager
                .createQuery("select a from Article a where title like '" + "%" + input + "%" + "'", Article.class)
                .getResultList();
        transaction.commit();

        entityManager.close();

        return result;
    }

}
