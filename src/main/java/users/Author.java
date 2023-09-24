package users;

import article.Article;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")

public class Author extends User {

    @Column(name = "published_Articles")
    @OneToMany(mappedBy = "author")
    private List<Article> publishedArticles;

    public Author() {
    }

    public Author(String name) {
        super(name);
        publishedArticles = new ArrayList<>();
    }


    @Override
    public String toString() {
        return getName();
    }
}