package server.controller;

import common.messages.TopRequest;
import common.messages.TopResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import platform.service.MessageController;
import server.domain.UserProfile;

import java.util.List;

public class TopController implements MessageController<TopRequest, UserProfile> {

    ApplicationContext context = new FileSystemXmlApplicationContext("classpath:beans.xml");
    List<UserProfile> topPlayersConfig = (List<UserProfile>) context.getBean("topPlayersConfig");

    @Override
    public TopResponse onMessage(TopRequest topRequest, UserProfile userProfile) {
        var topResponse = new TopResponse();
        topResponse.topPlayers = topPlayersConfig;
        return topResponse;
    }

    @Override
    public Class<TopRequest> messageClass() {
        return TopRequest.class;
    }
}
