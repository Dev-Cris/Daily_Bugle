package daily_bugle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleInfo {

    private String title;
    private String authorName;
    private String content;
    private LocalDate creationDate;
    private LocalDate modificationDate;
}
