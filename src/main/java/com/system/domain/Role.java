package com.system.domain;

public enum Role {
    JUDGE,
    EXPERT;

    public static final String ERROR_MESSAGE = "Wrong Role. This Role doesn't exist";

    public static Role getRoleById(Integer roleId) {
        switch (roleId) {
            case 1:
                return JUDGE;
            case 2:
                return EXPERT;
            default:
                throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    public Integer getId() {
        return this.ordinal();
    }
}
