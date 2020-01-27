package com.system.domain;

import java.util.List;
import java.util.Objects;

import static com.system.utility.CollectionUtility.nullSafeListInitialize;

public class User {
    private final Integer id;
    private final String email;
    private final String password;
    private final Integer accountID;

    private User(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.password = builder.password;
        this.accountID = builder.accountID;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAccountID() {
        return accountID;
    }


    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(accountID, user.accountID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, accountID);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", accountID=" + accountID +
                '}';
    }

    public static class Builder {
        private Integer id;
        private String email;
        private String password;
        private Integer accountID;

        private Builder() {
        }

        public User build() {
            return new User(this);
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withAccountID(Integer accountID) {
            this.accountID = accountID;
            return this;
        }

        }
    }
