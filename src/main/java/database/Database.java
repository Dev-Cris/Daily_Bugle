package database;

import article.Article;
import users.Author;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Database {
    private Set<Author> registeredAuthors;

    public Database() {
        this.registeredAuthors = new HashSet<>();
    }

    public Author searchAuthorByName(String name) {
        for (Author registeredAuthor : registeredAuthors) {
            if (registeredAuthor.getName().equals(name)) {
                return registeredAuthor;
            } else {
                System.out.println("There is no such author registered with this name!");
            }
        }
        return null;
    }

    public void registerAuthor(String name) {
        Author author = new Author(name);
        registeredAuthors.add(author);
    }

    public List<String> getAllArticlesByAuthor(String name) {
        Author author = searchAuthorByName(name);
        List<String> result = new ArrayList<>();
        for (Article publishedArticle : author.getPublishedArticles()) {
            result.add(publishedArticle.getTitle());
        }
        if (!result.isEmpty()) {
            return result;
        } else {
            System.out.println("This author has no published article!");
        }
        return null;
    }

    public List<String> getAllArticles() {
        List<String> result = new ArrayList<>();
        for (Author registeredAuthor : registeredAuthors) {
            for (Article publishedArticle : registeredAuthor.getPublishedArticles()) {
                result.add(publishedArticle.getTitle());
            }
        }
        if (!result.isEmpty()) {
            return result;
        }
        return null;
    }

    public List<String> getAllAuthors() {
        List<String> result = new ArrayList<>();
        for (Author registeredAuthor : registeredAuthors) {
            result.add(registeredAuthor.getName());
        }
        if (!result.isEmpty()) {
            return result;
        } else {
            System.out.println("There are no registered authors!");
        }
        return null;
    }

    public List<String> searchArticles(String title) {
        List<String> allTitles = getAllArticles();
        List<String> result = new ArrayList<>();
        for (String currentTitle : allTitles) {
            if (currentTitle.contains(title)) {
                result.add(currentTitle);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No result!");
        }
        return result;
    }

    public Article searchArticleByTitle(String title) {
        for (Author registeredAuthor : registeredAuthors) {
            for (Article publishedArticle : registeredAuthor.getPublishedArticles()) {
                if (title.equals(publishedArticle.getTitle())) {
                    return publishedArticle;
                }
            }
        }
        return null;
    }

    public Set<Author> getRegisteredAuthors() {
        return registeredAuthors;
    }
}
