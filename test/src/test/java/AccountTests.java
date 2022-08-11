import Steps.AuthorizationSteps;
import Steps.GenerateTokenSteps;
import Steps.UserSteps;
import org.junit.Test;

public class AccountTests {
    UserSteps userSteps = new UserSteps();
    GenerateTokenSteps generateTokenSteps = new GenerateTokenSteps();
    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    @Test
    public void userTest() {
        userSteps.addUser()
                .CheckThatBooksListEqualsToNull();

        generateTokenSteps.generateToken()
                .ValidateThatStatusIsEqualsToSuccess()
                .ValidateThatResultIsEqualsToUserAuthorizedSuccessfully();

        authorizationSteps.CheckThatUserBecameAuthorized();

    }

}
