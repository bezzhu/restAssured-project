import lombok.*;


@Getter
@Setter
public class Register{
    @NonNull
    public String email;
    public String password;
}
