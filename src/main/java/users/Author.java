package users;

import article.Article;

import java.util.ArrayList;
import java.util.List;

public class Author extends User {

    private List<Article> publishedArticles;

    public Author(String name) {
        super(name);
        publishedArticles = new ArrayList<>();
    }

    public List<Article> getPublishedArticles() {
        return publishedArticles;
    }

    @Override
    public String toString() {
        return getName();
    }
}
