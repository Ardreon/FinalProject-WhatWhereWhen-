package com.system.entity;

import java.util.Set;

public class UserEntity {
    private final Long id;
    private final String name;
    private final String email;
    private final String password;
    private final Integer score;
    private final Set<RoleEntity> roleEntities;


    private UserEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.score = builder.score;
        this.roleEntities = builder.roleEntities;
    }

    public static Builder builder() {

        return new Builder();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roleEntities +
                ", score=" + score +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getScore() {
        return score;
    }

    public Set<RoleEntity> getRoleEntities() {
        return roleEntities;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String email;
        private String password;
        private Integer score;
        private Set<RoleEntity> roleEntities;

        private Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
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

        public Builder withScore(Integer score) {
            this.score = score;
            return this;
        }

        public Builder withRoles(Set<RoleEntity> roleEntities) {
            this.roleEntities = roleEntities;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(this);
        }
    }
}
