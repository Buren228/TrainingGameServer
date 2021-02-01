package client;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import server.domain.UserProfile;

import static org.junit.jupiter.api.Assertions.assertSame;
public class UserProfileBeanTest {

@Test
public void userProfileTest(){
    ApplicationContext context = new FileSystemXmlApplicationContext("classpath:beans.xml");
    UserProfile userProfile = (UserProfile) context.getBean("userProfileConfig");
    assertSame(25,userProfile.getEnergy());
    assertSame(100,userProfile.getMoney());
    assertSame(0,userProfile.getRating());
    assertSame(1,userProfile.getLevel());
 }
}
