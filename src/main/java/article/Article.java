package article;

import org.hibernate.annotations.CreationTimestamp;
import users.Author;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @Column(name = "content", length = 1200)
    private String content;
    @Column(name = "creation_date")
    @CreationTimestamp
    private LocalDate creationDate;
    @Column(name = "modification_date")
    private LocalDate modificationDate;
    @Column(name = "archived")
    private boolean archived;

    public Article() {
    }

    public Article(String title, Author author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.creationDate = LocalDate.now();
        this.archived = false;
    }

    public String getTitle() {
        return title;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Long getId() {
        return id;
    }

    public boolean isArchived() {
        return archived;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n" + "Content: " + content;
    }
}