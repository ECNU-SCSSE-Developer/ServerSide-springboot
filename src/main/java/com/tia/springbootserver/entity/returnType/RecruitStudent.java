package com.tia.springbootserver.entity.returnType;

import org.springframework.context.annotation.Configuration;

/**
 * @author Alfred Fu
 * Created on 2018/12/3 2:58 AM
 */
@Configuration
public class RecruitStudent {
    String studentId;
    Integer recruitId;

    public RecruitStudent() {
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(Integer recruitId) {
        this.recruitId = recruitId;
    }

    @Override
    public String toString() {
        return "RecruitStudent{" +
                "studentId='" + studentId + '\'' +
                ", recruitId=" + recruitId +
                '}';
    }
}
