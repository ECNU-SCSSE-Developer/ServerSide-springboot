package com.tia.entity;

public class MatchRecruit {
    private Integer matchId;

    private Integer recruitId;

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(Integer recruitId) {
        this.recruitId = recruitId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", matchId=").append(matchId);
        sb.append(", recruitId=").append(recruitId);
        sb.append("]");
        return sb.toString();
    }
}