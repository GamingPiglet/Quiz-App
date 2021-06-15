package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Quiz implements Serializable {
    private String name;
    private final ArrayList<Question> questions;

    public Quiz(String name) {
        this.name = name;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(String question, String rightAnswer, String wrongAnswer1,
                            String wrongAnswer2, String wrongAnswer3) {
        Question addedQuestion = new Question(question, rightAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3);
        questions.add(addedQuestion);
    }

    public boolean removeQuestion(int index) {
        if (findQuestion(index) == null) {
            return false;
        }

        questions.remove(questions.get(index));
        return true;
    }

    public boolean removeQuestion(String name) {
        Question foundQuestion = findQuestion(name);

        if (foundQuestion == null) {
            return false;
        }

        questions.remove(foundQuestion);
        return true;
    }

    public boolean editQuestion(int choice, int index, String newField) {
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

    public boolean editQuestion(int choice, String question, String newField) {
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
        return answer.equals(question.getRightAnswer());
    }

    private Question findQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            return questions.get(index);
        }
        return null;
    }

    private Question findQuestion(String question) {
        for (Question checkedQuestion : questions) {
            if (checkedQuestion.getQuestion().equals(question)) {
                return checkedQuestion;
            }
        }
        return null;
    }

    public Question queryQuestion(String question) {
        return findQuestion(question);
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
