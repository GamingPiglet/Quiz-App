package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Quiz implements Serializable {
    private String name;
    // The list of questions the quiz has.
    private final ArrayList<Question> questions;

    // Initializes an instance of the class with a name.
    public Quiz(String name) {
        this.name = name;
        this.questions = new ArrayList<>();
    }

    // Adds a question to the list using the parameters.
    public void addQuestion(String question, String rightAnswer, String wrongAnswer1,
                            String wrongAnswer2, String wrongAnswer3) {
        Question addedQuestion = new Question(question, rightAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3);
        questions.add(addedQuestion);
    }

    // An instance of an overloaded method that uses an index as a parameter.
    public boolean removeQuestion(int index) {
        if (findQuestion(index) == null) {
            return false;
        }

        questions.remove(questions.get(index));
        return true;
    }

    // The same overloaded method that uses the question heading as a parameter instead.
    public boolean removeQuestion(String name) {
        Question foundQuestion = findQuestion(name);

        if (foundQuestion == null) {
            return false;
        }

        questions.remove(foundQuestion);
        return true;
    }

    /*
        Another overloaded method again using an index to find a question, along with another int to decide what
        to edit and a string to act as the replacement to a field.
     */
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

    // The same overloaded method earlier, instead using a question heading to find the question.
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

    // Returns true if the answer was correct, and false if it wasn't.
    public boolean checkAnswer(Question question, String answer) {
        return answer.equals(question.getRightAnswer());
    }

    // Internal method used to find questions.
    private Question findQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            return questions.get(index);
        }
        return null;
    }

    // Same method that uses question headings instead.
    private Question findQuestion(String question) {
        for (Question checkedQuestion : questions) {
            if (checkedQuestion.getQuestion().equals(question)) {
                return checkedQuestion;
            }
        }
        return null;
    }

    // The method that finds a question using question headings but can be used anywhere instead.
    public Question queryQuestion(String question) {
        return findQuestion(question);
    }

    // These methods return or set the value of their corresponding field.
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
