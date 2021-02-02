package common.messages;

import server.domain.UserProfile;

import java.time.LocalDateTime;

public class ChangeNameResponse extends AbstractResponse {

    public ChangeNameResponse(UserProfile userProfile) {
        userProfile.setChangeNameTimer(LocalDateTime.now().plusDays(1));
    }

    public ChangeNameResponse(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
