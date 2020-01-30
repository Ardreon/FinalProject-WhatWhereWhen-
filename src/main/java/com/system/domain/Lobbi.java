package com.system.domain;

import java.util.List;

public class Lobbi {
    private final Long lobbiId;
    private final Game configId;
    private final List<User> userId;
    private final List<Question> questions;

    public Lobbi(Long lobbiId, Game configId, List<User> userId, List<Question> questions) {
        this.lobbiId = lobbiId;
        this.configId = configId;
        this.userId = userId;
        this.questions = questions;
    }

    public Long getLobbiId() {
        return lobbiId;
    }

    public Game getConfigId() {
        return configId;
    }

    public List<User> getUserId() {
        return userId;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
