package Models.Responses;

import java.util.ArrayList;

public record UserResponse(String userID, String username, ArrayList<String> books) {

}
