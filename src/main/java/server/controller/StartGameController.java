package server.controller;

import common.messages.StartGameRequest;
import common.messages.StartGameResponse;
import org.springframework.stereotype.Service;
import platform.service.MessageController;
import server.common.ProfileState;
import server.domain.UserProfile;

@Service
public class StartGameController implements MessageController<StartGameRequest, UserProfile> {


    @Override
    public StartGameResponse onMessage(StartGameRequest startGameRequest, UserProfile userProfile) {

        if (!(userProfile.getState().equals(ProfileState.IN_GAME))) {

            if (userProfile.getEnergy() >= 5) {
                userProfile.setEnergy(userProfile.getEnergy() - 5);
                userProfile.setState(ProfileState.IN_GAME);
                return new StartGameResponse();
            } else {
                var startGameResponse = new StartGameResponse();
                startGameResponse.errorCode = 1;
                startGameResponse.errorMessage = "No enough energy!";

                return startGameResponse;
            }
        } else {
            var startGameResponse = new StartGameResponse();
            startGameResponse.errorCode = 2;
            startGameResponse.errorMessage = "Game is already started!";

            return startGameResponse;
        }

    }

    @Override
    public Class<StartGameRequest> messageClass() {
        return StartGameRequest.class;
    }
}
