package com.system.entity;

public class GameEntity {
    private final Long gameId;
    private final Integer time;
    private final Integer openQuestionCount;
    private final Integer variantQuestionCount;
    private final PromptTypeEntity promptTypeEntity;
    private final UserEntity userEntity;
    private final OpenQuestionEntity openQuestionEntity;
    private final VariantQuestionEntity variantQuestionEntity;

    private GameEntity(Builder builder) {
        this.gameId = builder.gameId;
        this.time = builder.time;
        this.openQuestionCount = builder.openQuestionCount;
        this.variantQuestionCount = builder.variantQuestionCount;
        this.promptTypeEntity = builder.promptTypeEntity;
        this.userEntity = builder.userEntity;
        this.openQuestionEntity = builder.openQuestionEntity;
        this.variantQuestionEntity = builder.variantQuestionEntity;
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

    public Integer getOpenQuestionCount() {
        return openQuestionCount;
    }

    public Integer getVariantQuestionCount() {
        return variantQuestionCount;
    }

    public PromptTypeEntity getPromptTypeEntity() {
        return promptTypeEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public OpenQuestionEntity getOpenQuestionEntity() {
        return openQuestionEntity;
    }

    public VariantQuestionEntity getVariantQuestionEntity() {
        return variantQuestionEntity;
    }

    public static class Builder {
        private Long gameId;
        private Integer time;
        private Integer openQuestionCount;
        private Integer variantQuestionCount;
        private PromptTypeEntity promptTypeEntity;
        private UserEntity userEntity;
        private OpenQuestionEntity openQuestionEntity;
        private VariantQuestionEntity variantQuestionEntity;

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

        public Builder withOpenQuestionCount(Integer openQuestionCount) {
            this.openQuestionCount = openQuestionCount;
            return this;
        }

        public Builder withVariantQuestionCount(Integer variantQuestionCount) {
            this.variantQuestionCount = variantQuestionCount;
            return this;
        }

        public Builder withPromptTypeEntity(PromptTypeEntity promptTypeEntity) {
            this.promptTypeEntity = promptTypeEntity;
            return this;
        }

        public Builder withUserEntity(UserEntity userEntity) {
            this.userEntity = userEntity;
            return this;
        }

        public Builder withOpenQuestionEntity(OpenQuestionEntity openQuestionEntity) {
            this.openQuestionEntity = openQuestionEntity;
            return this;
        }

        public Builder withVariantQuestionEntity(VariantQuestionEntity variantQuestionEntity) {
            this.variantQuestionEntity = variantQuestionEntity;
            return this;
        }

        public GameEntity build() {
            return new GameEntity(this);
        }
    }
}
