package daily_bugle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCreateCommand {

    @NotNull(message = "Must not be null!")
    @NotBlank(message = "Must not be blank!")
    private String title;

    @NotNull(message = "Must not be null!")
    @NotBlank(message = "Must not be blank!")
    private String content;

    @NotNull(message = "Must not be null!")
    private Long authorId;
}
