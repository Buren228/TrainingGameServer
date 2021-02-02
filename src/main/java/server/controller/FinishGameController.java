package server.controller;

import common.messages.FinishGameRequest;
import common.messages.FinishGameResponse;
import common.messages.StartGameRequest;
import org.springframework.stereotype.Service;
import platform.service.MessageController;
import server.common.GameResult;
import server.common.ProfileState;
import server.domain.UserProfile;

@Service
public class FinishGameController implements MessageController<FinishGameRequest, UserProfile> {

    @Override
    public FinishGameResponse onMessage(FinishGameRequest finishGameRequest, UserProfile userProfile) {

        if (userProfile.getState().equals(ProfileState.IN_GAME)) {
            if (finishGameRequest.result.equals(GameResult.WIN)) {

                userProfile.setState(ProfileState.MAIN_MENU);

                userProfile.setExperience(userProfile.getExperience() + 10);
                userProfile.setMoney(userProfile.getMoney() + 10);
                userProfile.setRating(userProfile.getRating() + 3);

                return new FinishGameResponse(userProfile);
            } else
//            if (finishGameRequest.result.equals(GameResult.DEFEAT)) {
                userProfile.setState(ProfileState.MAIN_MENU);

            userProfile.setExperience(userProfile.getExperience() + 3);
            if (userProfile.getRating() > 0) {
                userProfile.setRating(userProfile.getRating() - 1);
            }

            return new FinishGameResponse(userProfile);
//            } else {
//                var finishGameResponse = new FinishGameResponse();
//                finishGameResponse.errorCode = 2;
//                finishGameResponse.errorMessage = "result is unknown!";
//                return finishGameResponse;
//            }
        } else {
            var finishGameResponse = new FinishGameResponse(userProfile);
            finishGameResponse.errorCode = 1;
            finishGameResponse.errorMessage = "The game isn't over yet!";
            return finishGameResponse;
        }

    }

    @Override
    public Class<FinishGameRequest> messageClass() {
        return FinishGameRequest.class;
    }
}
