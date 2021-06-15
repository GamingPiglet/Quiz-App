package com.company;

import java.io.Serializable;

public class Question implements Serializable {
    private String question;
    private String rightAnswer;
    private String wrongAnswer1;
    private String wrongAnswer2;
    private String wrongAnswer3;

    // Initializes an instance of the class.
    public Question(String question, String rightAnswer, String wrongAnswer1, String wrongAnswer2,
                    String wrongAnswer3) {
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
    }

    // Almost all of these methods below set or return the value of their corresponding field.
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public void setWrongAnswer1(String wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    public void setWrongAnswer3(String wrongAnswer3) {
        this.wrongAnswer3 = wrongAnswer3;
    }

    // This method instead returns a string that lists the values of the fields.
    @Override
    public String toString() {
        return "Question: " + question +
                "\nRight answer: " + rightAnswer +
                "\nWrong answer 1: " + wrongAnswer1 +
                "\nWrong answer 2: " + wrongAnswer2 +
                "\nWrong answer 3: " + wrongAnswer3 + "\n";
    }
}
