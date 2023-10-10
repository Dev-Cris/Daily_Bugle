package daily_bugle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorCreateUpdateCommand {
    @NotNull(message = "Must not be null!")
    @NotBlank(message = "Must not be blank!")
    private String name;
}
