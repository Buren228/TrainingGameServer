package client;

import common.messages.StartGameRequest;
import common.messages.StartGameResponse;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import server.common.ProfileState;
import server.controller.StartGameRequestController;
import server.domain.UserProfile;

import static org.junit.jupiter.api.Assertions.assertSame;

public class WithUserProfileTest {

    ApplicationContext context = new FileSystemXmlApplicationContext("classpath:beans.xml");
     UserProfile userProfile = (UserProfile) context.getBean("userProfileConfig");
     StartGameRequestController sgrc = new StartGameRequestController();

@Test
public void userProfileTest(){

    assertSame(25,userProfile.getEnergy());
    assertSame(100,userProfile.getMoney());
    assertSame(0,userProfile.getRating());
    assertSame(1,userProfile.getLevel());
 }

 @Test
    public void startGameRequestTest(){
    UserProfile userProfile1=userProfile;

     StartGameResponse startGameResponse = sgrc.onMessage(new StartGameRequest(),userProfile1);

     assertSame(20,userProfile1.getEnergy());
     assertSame(ProfileState.IN_GAME,userProfile1.getState());
     assertSame(0,startGameResponse.errorCode);

     startGameResponse = sgrc.onMessage(new StartGameRequest(),userProfile1);
     assertSame(2,startGameResponse.errorCode);

     userProfile1.setEnergy(2);
     userProfile1.setState(ProfileState.MAIN_MENU);
     startGameResponse = sgrc.onMessage(new StartGameRequest(),userProfile1);
     assertSame(1,startGameResponse.errorCode);
 }
}
