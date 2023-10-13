package daily_bugle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleArchiveCommand {

    @NotNull(message = "Must not be null!")
    private Long articleId;

}
