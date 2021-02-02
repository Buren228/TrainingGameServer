package common.messages;

import common.dto.AwardStructure;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import server.domain.UserProfile;

import java.util.Map;

public class FinishGameResponse extends AbstractResponse {

    public AwardStructure award;

    ApplicationContext context = new FileSystemXmlApplicationContext("classpath:beans.xml");
    Map<Integer, Integer> levelsConfig = (Map<Integer, Integer>) context.getBean("levelsConfig");
    Map<Integer, AwardStructure> levelUpAwardConfig = (Map<Integer, AwardStructure>) context.getBean("levelUpAwardConfig");

    public FinishGameResponse(UserProfile userProfile) {
        CheckLvlUp(userProfile);
    }

    public void CheckLvlUp(UserProfile userProfile) {
        if (userProfile.getExperience() >= levelsConfig.get(userProfile.getLevel() + 1)) {
            userProfile.setExperience(userProfile.getExperience() - levelsConfig.get(userProfile.getLevel() + 1));
            userProfile.setLevel(userProfile.getLevel() + 1);

            getAward(userProfile);
        }
    }

    public void getAward(UserProfile userProfile) {
        award = levelUpAwardConfig.get(userProfile.getLevel());
        userProfile.setMoney(userProfile.getMoney() + award.getMoney());
        userProfile.setEnergy(userProfile.getEnergy() + award.getEnergy());
    }

}
