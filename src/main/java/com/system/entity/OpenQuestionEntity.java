package com.system.entity;

import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;

public class OpenQuestionEntity {

    private final Long questionId;
    private final String question;
    private final String answer;
    private final String prompt;

    public OpenQuestionEntity(Builder builder) {
        this.questionId = builder.questionId;
        this.question = builder.question;
        this.answer = builder.answer;
        this.prompt = builder.prompt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getPrompt() {
        return prompt;
    }

    public static class Builder {
        private Long questionId;
        private String question;
        private String answer;
        private String prompt;

        private Builder() {
        }

        public Builder withQuestionId(Long questionId) {
            this.questionId = questionId;
            return this;
        }

        public Builder withQuestion(String question) {
            this.question = question;
            return this;
        }

        public Builder withAnswer(String answer) {
            this.answer = answer;
            return this;
        }

        public Builder withPrompt(String prompt) {
            this.prompt = prompt;
            return this;
        }

        public OpenQuestionEntity build() {
            return new OpenQuestionEntity(this);
        }
    }
}
