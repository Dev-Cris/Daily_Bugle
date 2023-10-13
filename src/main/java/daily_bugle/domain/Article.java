package daily_bugle.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "article")
@AllArgsConstructor
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(name = "article_title")
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
    @UpdateTimestamp
    private LocalDate modificationDate;

    @Column(name = "archived")
    private boolean archived;

    public Article() {
    }

}