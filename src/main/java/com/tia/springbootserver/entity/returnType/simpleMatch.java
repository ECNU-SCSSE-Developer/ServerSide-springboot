package com.tia.springbootserver.entity.returnType;

import org.springframework.context.annotation.Configuration;

@Configuration
public class simpleMatch {

    private Integer matchId;
    private String matchName;

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

}
