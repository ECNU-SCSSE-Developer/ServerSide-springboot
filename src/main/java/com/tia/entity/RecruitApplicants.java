package com.tia.entity;

public class RecruitApplicants {
    private Integer recruitId;

    private String applicantId;

    public Integer getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(Integer recruitId) {
        this.recruitId = recruitId;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId == null ? null : applicantId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recruitId=").append(recruitId);
        sb.append(", applicantId=").append(applicantId);
        sb.append("]");
        return sb.toString();
    }
}