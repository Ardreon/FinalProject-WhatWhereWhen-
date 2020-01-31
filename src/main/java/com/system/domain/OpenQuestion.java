package com.system.domain;

public class OpenQuestion {

    private final Long questionId;
    private final String question;
    private final String answer;
    private final String prompt;

    public OpenQuestion(Long questionId, String question, String answer, String prompt) {
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
        this.prompt = prompt;
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

}
