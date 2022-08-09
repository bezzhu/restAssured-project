import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;


@Getter
@Setter
public class UsersParam {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({"name" , "job"})
    @JsonProperty("newName")
    public String name;
    public String job;
}
