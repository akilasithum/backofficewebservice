package com.back.office.ws.entity;

import java.util.List;

public class UserComments {

    List<UserComment> userComments;

    public List<UserComment> getUserComments() {
        return userComments;
    }

    public void setUserComments(List<UserComment> userComments) {
        this.userComments = userComments;
    }
}
