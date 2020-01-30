package com.system.domain;

public class Game {
    private final Long gameId;
    private final Integer time;
    private final Integer playersCount;
    private final PromptType promptTypes;
    private final Integer questionCount;

    private Game(Builder builder) {
        this.gameId = builder.gameId;
        this.time = builder.time;
        this.playersCount = builder.playersCount;
        this.promptTypes = builder.promptTypes;
        this.questionCount = builder.questionCount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getGameId() {
        return gameId;
    }

    public Integer getTime() {
        return time;
    }

    public Integer getPlayersCount() {
        return playersCount;
    }

    public PromptType getPromptTypes() {
        return promptTypes;
    }

    public Integer getQuestionCount() {
        return questionCount;
    }

    public static class Builder {
        private Long gameId;
        private Integer time;
        private Integer playersCount;
        private PromptType promptTypes;
        private Integer questionCount;

        private Builder() {
        }

        public Builder withGameId(Long gameId) {
            this.gameId = gameId;
            return this;
        }

        public Builder withTime(Integer time) {
            this.time = time;
            return this;
        }

        public Builder withPlayersCount(Integer playersCount) {
            this.playersCount = playersCount;
            return this;
        }

        public Builder withPromptTypes(PromptType promptTypes) {
            this.promptTypes = promptTypes;
            return this;
        }

        public Builder withQuestionCount(Integer questionCount) {
            this.questionCount = questionCount;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }
}
