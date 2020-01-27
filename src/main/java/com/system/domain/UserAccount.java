package com.system.domain;

public class UserAccount {
    private final Integer id;
    private final User user;

    public UserAccount(Integer id, User user) {
        this.id = id;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }


    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }
}
