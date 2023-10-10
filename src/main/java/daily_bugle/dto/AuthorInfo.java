package daily_bugle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorInfo {

    private String name;
    private List<ArticleInfo> publishedArticles;

}
