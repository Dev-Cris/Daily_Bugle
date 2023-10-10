package daily_bugle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleArchivedInfo {

    private String title;
    private boolean archived;
    private LocalDate modificationDate;

}
