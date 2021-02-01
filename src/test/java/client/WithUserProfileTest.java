package client;

import common.messages.FinishGameRequest;
import common.messages.FinishGameResponse;
import common.messages.StartGameRequest;
import common.messages.StartGameResponse;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import server.common.GameResult;
import server.common.ProfileState;
import server.controller.FinishGameController;
import server.controller.StartGameController;
import server.domain.UserProfile;

import static org.junit.jupiter.api.Assertions.assertSame;

public class WithUserProfileTest {

    ApplicationContext context = new FileSystemXmlApplicationContext("classpath:beans.xml");
    UserProfile userProfile = (UserProfile) context.getBean("userProfileConfig");
    StartGameController sgc = new StartGameController();
    FinishGameController fgc = new FinishGameController();

    @Test
    public void userProfileTest() {

        assertSame(25, userProfile.getEnergy());
        assertSame(100, userProfile.getMoney());
        assertSame(0, userProfile.getRating());
        assertSame(1, userProfile.getLevel());
    }

    @Test
    public void startAndFinishGameRequestTest() {
        UserProfile userProfile1 = userProfile;

        StartGameResponse startGameResponse = sgc.onMessage(new StartGameRequest(), userProfile1);


        assertSame(20, userProfile1.getEnergy());
        assertSame(ProfileState.IN_GAME, userProfile1.getState());
        assertSame(0, startGameResponse.errorCode);

        startGameResponse = sgc.onMessage(new StartGameRequest(), userProfile1);
        assertSame(2, startGameResponse.errorCode);

        FinishGameRequest winReq = new FinishGameRequest(GameResult.WIN);
        FinishGameRequest defeatReq = new FinishGameRequest(GameResult.DEFEAT);
        FinishGameResponse finishGameResponse = fgc.onMessage(winReq, userProfile1);
        assertSame(110, userProfile1.getMoney());
        assertSame(10, userProfile1.getExperience());
        assertSame(3, userProfile1.getRating());
        assertSame(0, finishGameResponse.errorCode);

        userProfile1.setEnergy(2);

        startGameResponse = sgc.onMessage(new StartGameRequest(), userProfile1);
        assertSame(1, startGameResponse.errorCode);

        userProfile1.setEnergy(5);
        userProfile1.setRating(0);
        sgc.onMessage(new StartGameRequest(), userProfile1);
        fgc.onMessage(defeatReq, userProfile1);
        assertSame(13, userProfile1.getExperience());
        assertSame(0, userProfile1.getRating());
    }

}