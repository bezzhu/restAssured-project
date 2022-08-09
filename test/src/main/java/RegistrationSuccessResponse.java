import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter
@Setter
public class RegistrationSuccessResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String id ;
    public String token;
}
