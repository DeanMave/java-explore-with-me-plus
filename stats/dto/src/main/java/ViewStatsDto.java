import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewStatsDto {
    @NotBlank
    private String app;
    @NotBlank
    private String uri;
    private Long hits;
}
