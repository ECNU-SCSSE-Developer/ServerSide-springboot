package com.tia.entity;

public class RecruitType {
    private Integer recruitId;

    private String recruitType;

    public Integer getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(Integer recruitId) {
        this.recruitId = recruitId;
    }

    public String getRecruitType() {
        return recruitType;
    }

    public void setRecruitType(String recruitType) {
        this.recruitType = recruitType == null ? null : recruitType.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recruitId=").append(recruitId);
        sb.append(", recruitType=").append(recruitType);
        sb.append("]");
        return sb.toString();
    }
}