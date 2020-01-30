package com.system.domain;

public enum PromptType {
    PROCENT, HIDE, TEXT, TIME;
    public static final String ERROR_MESSAGE = "Wrong prompt. This prompt doesn't exist";

    public static PromptType getPromptTypeById(Integer promptId) {
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
