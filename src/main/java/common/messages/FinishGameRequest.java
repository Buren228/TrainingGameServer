package common.messages;

import server.common.GameResult;

public class FinishGameRequest {

    public GameResult result;

    //   public FinishGameRequest(){}

    public FinishGameRequest(GameResult result) {
        this.result = result;
    }

}
