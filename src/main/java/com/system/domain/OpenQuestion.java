package com.system.domain;

public class OpenQuestion {

    private final Long question_id;
    private final String question;
    private final String answer;
    private final String prompt;

    public OpenQuestion(Long question_id, String question, String answer, String prompt) {
        this.question_id = question_id;
        this.question = question;
        this.answer = answer;
        this.prompt = prompt;
    }

    public Long getQuestion_id() {
        return question_id;
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
