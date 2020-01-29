package com.system.domain;

public class Configuration {
    private final Long configId;
    private final Integer time;
    private final Integer playersCount;
    private final PromptType promptTypes;
    private final Integer questionCount;

    private Configuration(ConfigurationBuilder configurationBuilder) {
        this.configId = configurationBuilder.configId;
        this.time = configurationBuilder.time;
        this.playersCount = configurationBuilder.playersCount;
        this.promptTypes = configurationBuilder.promptTypes;
        this.questionCount = configurationBuilder.questionCount;
    }

    public static ConfigurationBuilder configurationBuilder() {
        return new ConfigurationBuilder();
    }

    public Long getConfigId() {
        return configId;
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

    public static final class ConfigurationBuilder {
        private Long configId;
        private Integer time;
        private Integer playersCount;
        private PromptType promptTypes;
        private Integer questionCount;


        private ConfigurationBuilder() {
        }

        public ConfigurationBuilder withConfigId(Long configId) {
            this.configId = configId;
            return this;
        }

        public ConfigurationBuilder withTime(Integer time) {
            this.time = time;
            return this;
        }

        public ConfigurationBuilder withPlayersCount(Integer playersCount) {
            this.playersCount = playersCount;
            return this;
        }

        public ConfigurationBuilder withPromptTypes(PromptType promptTypes) {
            this.promptTypes = promptTypes;
            return this;
        }

        public ConfigurationBuilder withQuestionCount(Integer questionCount) {
            this.questionCount = questionCount;
            return this;
        }

        public Configuration configuration() {

            return new Configuration(this);
        }
    }
}
