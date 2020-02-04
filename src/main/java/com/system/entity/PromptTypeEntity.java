package com.system.entity;

public enum PromptTypeEntity {
    PROCENT, HIDE, TEXT, TIME;
    public static final String ERROR_MESSAGE = "Wrong prompt. This prompt doesn't exist";

    public static PromptTypeEntity getPromptTypeById(Integer promptId) {
        switch (promptId) {
            case 1:
                return PROCENT;
            case 2:
                return HIDE;
            case 3:
                return TEXT;
            case 4:
                return TIME;
            default:
                throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    public Integer getId() {
        return this.ordinal();
    }
}
