package server.controller;

import common.messages.ChangeNameRequest;
import common.messages.ChangeNameResponse;
import platform.service.MessageController;
import server.domain.UserProfile;

import java.time.LocalDateTime;

public class ChangeNameController implements MessageController<ChangeNameRequest, UserProfile> {
    @Override
    public ChangeNameResponse onMessage(ChangeNameRequest changeNameRequest, UserProfile userProfile) {
        if (LocalDateTime.now().isAfter(userProfile.getChangeNameTimer()))
            return new ChangeNameResponse(userProfile);
        else {
            return new ChangeNameResponse(1, "The name can only be changed once a day!");
        }
    }

    @Override
    public Class<ChangeNameRequest> messageClass() {
        return ChangeNameRequest.class;
    }
}
