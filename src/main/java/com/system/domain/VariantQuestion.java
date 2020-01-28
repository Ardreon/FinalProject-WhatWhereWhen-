package com.system.domain;


public class VariantQuestion {

        private final Long question_id;
        private final String question;
        private final String right_answer;
        private final Double right_answer_procent;
        private final String wrong_answer1;
        private final Double wrong_answer1_procent;
        private final String wrong_answer2;
        private final Double wrong_answer2_procent;
        private final String wrong_answer3;
        private final Double wrong_answer3_procent;

        public VariantQuestion(Builder builder) {
        this.question_id = builder.question_id;
        this.question = builder.question;
        this.right_answer = builder.right_answer;
        this.right_answer_procent = builder.right_answer_procent;
        this.wrong_answer1 = builder.wrong_answer1;
        this.wrong_answer1_procent = builder.wrong_answer1_procent;
        this.wrong_answer2 = builder.wrong_answer2;
        this.wrong_answer2_procent = builder.wrong_answer2_procent;
        this.wrong_answer3 = builder.wrong_answer3;
        this.wrong_answer3_procent = builder.wrong_answer3_procent;
    }

    public static Builder builder() {

            return new Builder();
    }

    @Override
    public String toString() {
        return "VariantTypeQuestion{" +
                "question_id=" + question_id +
                ", question='" + question + '\'' +
                ", right_answer='" + right_answer + '\'' +
                ", right_answer_procent='" + right_answer_procent + '\'' +
                ", wrong_answer1='" + wrong_answer1 + '\'' +
                ", wrong_answer1_procent='" + wrong_answer1_procent + '\'' +
                ", wrong_answer2='" + wrong_answer2 + '\'' +
                ", wrong_answer2_procent='" + wrong_answer2_procent + '\'' +
                ", wrong_answer3='" + wrong_answer3 + '\'' +
                ", wrong_answer3_procent='" + wrong_answer3_procent + '\'' +
                '}';
    }

    public Long getQuestion_id() {
        return question_id;
    }

    public String getQuestion() {
        return question;
    }

    public String getRight_answer() {
        return right_answer;
    }

    public Double getRight_answer_procent() {
        return right_answer_procent;
    }

    public String getWrong_answer1() {
        return wrong_answer1;
    }

    public Double getWrong_answer1_procent() {
        return wrong_answer1_procent;
    }

    public String getWrong_answer2() {
        return wrong_answer2;
    }

    public Double getWrong_answer2_procent() {
        return wrong_answer2_procent;
    }

    public String getWrong_answer3() {
        return wrong_answer3;
    }

    public Double getWrong_answer3_procent() {
        return wrong_answer3_procent;
    }

    public static class Builder {
        private Long question_id;
        private String question;
        private String right_answer;
        private Double right_answer_procent;
        private String wrong_answer1;
        private Double wrong_answer1_procent;
        private String wrong_answer2;
        private Double wrong_answer2_procent;
        private String wrong_answer3;
        private Double wrong_answer3_procent;

            private Builder() {
            }

            public Builder withQuestion_id(Long question_id) {
                this.question_id = question_id;
                return this;
            }

            public Builder withRight_answer(String right_answer) {
                this.right_answer = right_answer;
                return this;
            }

            public Builder withRight_answer_procent(Double right_answer_procent) {
                this.right_answer_procent = right_answer_procent;
                return this;
            }

            public Builder withWrong_answer1(String wrong_answer1) {
            this.wrong_answer1 = wrong_answer1;
            return this;
            }

            public Builder withWrong_answer1_procent(Double wrong_answer1_procent) {
            this.wrong_answer1_procent = wrong_answer1_procent;
            return this;
            }

            public Builder withWrong_answer2(String wrong_answer2) {
            this.wrong_answer2 = wrong_answer2;
            return this;
            }

            public Builder withWrong_answer2_procent(Double wrong_answer2_procent) {
            this.wrong_answer2_procent = wrong_answer2_procent;
            return this;
            }

            public Builder withWrong_answer3(String wrong_answer3) {
            this.wrong_answer3 = wrong_answer3;
            return this;
            }

            public Builder withWrong_answer3_procent(Double wrong_answer3_procent) {
            this.wrong_answer3_procent = wrong_answer3_procent;
            return this;
            }

            public VariantQuestion build() {
                return new VariantQuestion(this);
            }
        }
}
