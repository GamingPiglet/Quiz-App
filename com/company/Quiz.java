package com.company;

import java.util.ArrayList;

public class Quiz {
    private String name;
    private ArrayList<Question> questions;

    public Quiz(String name) {
        this.name = name;
        this.questions = new ArrayList<Question>();
    }

    public boolean addQuestion(String question, String rightAnswer, String wrongAnswer1,
                               String wrongAnswer2, String wrongAnswer3) {
        if (findQuestion(question) != null) {
            return false;
        }

        Question addedQuestion = new Question(question, rightAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3);
        questions.add(addedQuestion);
        return true;
    }

    public boolean removeQuestion(int index) {
        if (findQuestion(index) == null) {
            return false;
        }

        questions.remove(questions.get(index));
        return true;
    }

    public boolean removeQuestion (String name) {
        Question foundQuestion = findQuestion(name);

        if (foundQuestion == null) {
            return false;
        }

        questions.remove(foundQuestion);
        return true;
    }

    public boolean editQuestion (int choice, int index, String newField) {
        Question editedQuestion = findQuestion(index);
        if (editedQuestion == null) {
            return false;
        }
        switch (choice) {
            case 1:
                editedQuestion.setQuestion(newField);
                return true;
            case 2:
                if (newField.equals(editedQuestion.getWrongAnswer1()) ||
                        newField.equals(editedQuestion.getWrongAnswer2()) ||
                        newField.equals(editedQuestion.getWrongAnswer3())) {
                    return false;
                }
                editedQuestion.setRightAnswer(newField);
                return true;
            case 3:
                if (newField.equals(editedQuestion.getRightAnswer())) {
                    return false;
                }
                editedQuestion.setWrongAnswer1(newField);
                return true;
            case 4:
                if (newField.equals(editedQuestion.getRightAnswer())) {
                    return false;
                }
                editedQuestion.setWrongAnswer2(newField);
                return true;
            case 5:
                if (newField.equals(editedQuestion.getRightAnswer())) {
                    return false;
                }
                editedQuestion.setWrongAnswer3(newField);
                return true;
            default:
                return false;
        }
    }

    public boolean editQuestion (int choice, String question, String newField) {
        Question editedQuestion = findQuestion(question);
        if (editedQuestion == null) {
            return false;
        }
        switch (choice) {
            case 1:
                editedQuestion.setQuestion(newField);
                return true;
            case 2:
                if (newField.equals(editedQuestion.getWrongAnswer1()) ||
                        newField.equals(editedQuestion.getWrongAnswer2()) ||
                        newField.equals(editedQuestion.getWrongAnswer3())) {
                    return false;
                }
                editedQuestion.setRightAnswer(newField);
                return true;
            case 3:
                if (newField.equals(editedQuestion.getRightAnswer())) {
                    return false;
                }
                editedQuestion.setWrongAnswer1(newField);
                return true;
            case 4:
                if (newField.equals(editedQuestion.getRightAnswer())) {
                    return false;
                }
                editedQuestion.setWrongAnswer2(newField);
                return true;
            case 5:
                if (newField.equals(editedQuestion.getRightAnswer())) {
                    return false;
                }
                editedQuestion.setWrongAnswer3(newField);
                return true;
            default:
                return false;
        }
    }

    public boolean checkAnswer(Question question, String answer) {
        question.setAnswered(true);
        return true;
    }

    private Question findQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            return questions.get(index);
        }
        return null;
    }

    private Question findQuestion(String question) {
        for (Question checkedQuestion: questions) {
            if (checkedQuestion.getQuestion().equals(question)) {
                return checkedQuestion;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}