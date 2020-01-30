package com.system.domain;

public class VariantQuestion extends Question {

    private final Long questionId;
    private final String question;
    private final String rightAnswer;
    private final Double rightAnswerProcent;
    private final String wrongAnswer1;
    private final Double wrongAnswer1Procent;
    private final String wrongAnswer2;
    private final Double wrongAnswer2Procent;
    private final String wrongAnswer3;
    private final Double wrongAnswer3Procent;

    public VariantQuestion(Builder builder) {
        this.questionId = builder.questionId;
        this.question = builder.question;
        this.rightAnswer = builder.rightAnswer;
        this.rightAnswerProcent = builder.rightAnswerProcent;
        this.wrongAnswer1 = builder.wrongAnswer1;
        this.wrongAnswer1Procent = builder.wrongAnswer1Procent;
        this.wrongAnswer2 = builder.wrongAnswer2;
        this.wrongAnswer2Procent = builder.wrongAnswer2Procent;
        this.wrongAnswer3 = builder.wrongAnswer3;
        this.wrongAnswer3Procent = builder.wrongAnswer3Procent;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "VariantTypeQuestion{" +
                "questionId=" + questionId +
                ", question='" + question + '\'' +
                ", rightAnswer='" + rightAnswer + '\'' +
                ", rightAnswerProcent='" + rightAnswerProcent + '\'' +
                ", wrongAnswer1='" + wrongAnswer1 + '\'' +
                ", wrongAnswer1Procent='" + wrongAnswer1Procent + '\'' +
                ", wrongAnswer2='" + wrongAnswer2 + '\'' +
                ", wwrongAnswer2Procent='" + wrongAnswer2Procent + '\'' +
                ", wrongAnswer3='" + wrongAnswer3 + '\'' +
                ", wrongAnswer3Procent='" + wrongAnswer3Procent + '\'' +
                '}';
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public Double getRightAnswerProcent() {
        return rightAnswerProcent;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public Double getWrongAnswer1Procent() {
        return wrongAnswer1Procent;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public Double getWrongAnswer2Procent() {
        return wrongAnswer2Procent;
    }

    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    public Double getWrongAnswer3Procent() {
        return wrongAnswer3Procent;
    }

    public static class Builder {
        private Long questionId;
        private String question;
        private String rightAnswer;
        private Double rightAnswerProcent;
        private String wrongAnswer1;
        private Double wrongAnswer1Procent;
        private String wrongAnswer2;
        private Double wrongAnswer2Procent;
        private String wrongAnswer3;
        private Double wrongAnswer3Procent;

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

        public Builder withRightAnswer(String rightAnswer) {
            this.rightAnswer = rightAnswer;
            return this;
        }

        public Builder withRightAnswerProcent(Double rightAnswerProcent) {
            this.rightAnswerProcent = rightAnswerProcent;
            return this;
        }

        public Builder withWrongAnswer1(String wrongAnswer1) {
            this.wrongAnswer1 = wrongAnswer1;
            return this;
        }

        public Builder withWrongAnswer1Procent(Double wrongAnswer1Procent) {
            this.wrongAnswer1Procent = wrongAnswer1Procent;
            return this;
        }

        public Builder withWrongAnswer2(String wrongAnswer2) {
            this.wrongAnswer2 = wrongAnswer2;
            return this;
        }

        public Builder withWrongAnswer2Procent(Double wrongAnswer2Procent) {
            this.wrongAnswer2Procent = wrongAnswer2Procent;
            return this;
        }

        public Builder withWrongAnswer3(String wrongAnswer3) {
            this.wrongAnswer3 = wrongAnswer3;
            return this;
        }

        public Builder withWrongAnswer3Procent(Double wrongAnswer3Procent) {
            this.wrongAnswer3Procent = wrongAnswer3Procent;
            return this;
        }

        public VariantQuestion build() {
            return new VariantQuestion(this);
        }
    }
}
