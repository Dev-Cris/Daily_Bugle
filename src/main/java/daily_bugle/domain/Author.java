package daily_bugle.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
@NoArgsConstructor
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id;

    @Column(name = "author_name", unique = true)
    private String name;

    @Column(name = "published_Articles")
    @OneToMany(mappedBy = "author")
    private List<Article> publishedArticles;


}