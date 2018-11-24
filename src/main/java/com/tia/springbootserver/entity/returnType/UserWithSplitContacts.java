package com.tia.springbootserver.entity.returnType;

import com.tia.springbootserver.entity.User;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alfred Fu
 * Created on 2018/11/24 6:29 PM
 */

@Configuration
public class UserWithSplitContacts extends User {
    private String qq;
    private String email;

    public UserWithSplitContacts() {
    }

    public UserWithSplitContacts(User user) {
        this.setContacts(user.getContacts());
        this.setGrade(user.getGrade());
        this.setMajor(user.getMajor());
        this.setOpenId(user.getOpenId());
        this.setStudentId(user.getStudentId());
        this.setStudentName(user.getStudentName());
        this.setSpecialty(user.getSpecialty());
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
