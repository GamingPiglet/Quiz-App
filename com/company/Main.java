package com.company;

import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Quiz quiz;

    public static void main(String[] args) {

        System.out.println("Welcome to QuizApp, Version 0.1\n");
        printInstructions();
        int choice;
        boolean quit = false;
        while (!quit) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (quiz == null) {
                    switch (choice) {
                        case 0:
                            printInstructions();
                            break;
                        case 1:
                            checkForUpdates();
                            break;
                        case 2:
                            makeNewQuiz();
                            break;
                        case 3:
                            System.out.println("See ya");
                            quit = true;
                            break;
                        default:
                            System.out.println("Invalid input");
                            break;
                    }
                } else {
                    switch (choice) {
                        case 0:
                            printInstructions();
                            break;
                        case 1:
                            checkForUpdates();
                            break;
                        case 2:
                            run();
                            break;
                        case 3:
                            add();
                            break;
                        case 4:
                            remove();
                            break;
                        case 5:
                            edit();
                            break;
                        case 6:
                            deleteQuiz();
                            break;
                        case 7:
                            printQuestions();
                            break;
                        case 8:
                            System.out.println("See ya");
                            quit = true;
                            break;
                        default:
                            System.out.println("Invalid input");
                            break;
                    }
                }
            } else {
                System.out.println("Invalid input");
            }
            scanner.nextLine();
        }
    }

    public static void makeNewQuiz() {
        boolean done = false;
        while (!done) {
            System.out.print("Enter quiz name, or type cancel to cancel: ");
            if (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                if (name.equals("cancel")) {
                    System.out.println("Terminating process...");
                    return;
                }
                quiz = new Quiz(name);
                System.out.println("Quiz made successfully");
                printInstructions();
                done = true;
            } else {
                if (invalidCheck()) {
                    done = true;
                }
            }
        }
    }

    public static void printInstructions() {
        System.out.println("Enter:\n");
        System.out.println("0 - Print instructions\t");
        System.out.println("1 - Check for updates\t");
        if (quiz == null) {
            System.out.println("2 - Make new quiz\t");
            System.out.println("3 - Close program\n");
        } else {
            System.out.println("2 - Run quiz\t");
            System.out.println("3 - Add question\t");
            System.out.println("4 - Remove question\t");
            System.out.println("5 - Edit question\t");
            System.out.println("6 - Delete quiz\t");
            System.out.println("7 - Print questions\t");
            System.out.println("8 - Close program\n");
        }
    }

    public static void checkForUpdates() {
        URL url = new URL()
    }

    public static void run() {
        ArrayList<Question> temp = quiz.getQuestions();
        int score = 0;
        boolean rightIn = false;
        boolean wrong1In = false;
        boolean wrong2In = false;
        boolean wrong3In = false;
        while (temp.size() != 0) {
            Question randQuestion = temp.get(((int) (Math.random() * (quiz.getQuestions().size() + 1))) - 1);
            ArrayList<String> answers = new ArrayList<>();
            while (answers.size() < 3) {
                int random = (int) (Math.random() * (4 + 1));
                switch (random) {
                    case 1:
                        if (!rightIn) {
                            answers.add(randQuestion.getRightAnswer());
                            rightIn = true;
                            break;
                        } else {
                            continue;
                        }
                    case 2:
                        if (!wrong1In) {
                            answers.add(randQuestion.getWrongAnswer1());
                            wrong1In = true;
                            break;
                        } else {
                            continue;
                        }
                    case 3:
                        if (!wrong2In) {
                            answers.add(randQuestion.getWrongAnswer2());
                            wrong2In = true;
                            break;
                        } else {
                            continue;
                        }
                    case 4:
                        if (!wrong3In) {
                            answers.add(randQuestion.getWrongAnswer3());
                            wrong3In = true;
                            break;
                        }
                }
            }
            System.out.println(randQuestion.getQuestion());
            System.out.println("1 - " + answers.get(0));
            System.out.println("2 - " + answers.get(1));
            System.out.println("3 - " + answers.get(2));
            System.out.println("4 - " + answers.get(3));
            System.out.println("Remember, you can cancel this operation by entering cancel.");
            System.out.println();
            if (scanner.hasNextInt()) {
                boolean done = false;
                while (!done) {
                    int choice = scanner.nextInt();
                    choice--;
                    if (choice < 0 || choice >= answers.size()) {
                        System.out.println("Answer out of bounds, please reenter");
                        continue;
                    }
                    if (quiz.checkAnswer(randQuestion, answers.get(choice))) {
                        System.out.println("Answer is correct");
                        score++;
                    } else {
                        System.out.println("Answer is incorrect, correct answer was " +
                                randQuestion.getRightAnswer());
                    }
                    temp.remove(randQuestion);
                    done = true;
                }
            } else if (scanner.hasNextLine()) {
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("cancel")) {
                    System.out.println("Terminating process...");
                } else {
                    if (invalidCheck()) {
                        return;
                    }
                }
            } else {
                if (invalidCheck()) {
                    return;
                }
            }
        }
        System.out.println("Quiz over, your score is: " + score + "/" + quiz.getQuestions().size());
    }

    public static void add() {
        String question = "";
        String rightAnswer = "";
        String wrongAnswer1 = "";
        String wrongAnswer2 = "";
        String wrongAnswer3 = "";
        int i = 0;
        while (i < 5) {
            switch (i) {
                case 0:
                    boolean done = false;
                    System.out.print("Enter question, or type cancel to cancel: ");
                    while(!done) {
                        if (scanner.hasNextLine()) {
                            question = scanner.nextLine();
                            if (question.equalsIgnoreCase("cancel")) {
                                System.out.println("Terminating process...");
                                return;
                            }
                            i++;
                            done = true;
                        } else {
                            if (invalidCheck()) {
                                return;
                            }
                        }
                    }
                    break;
                case 1:
                    System.out.print("Enter right answer, or type cancel to cancel: ");
                    done = false;
                    while(!done) {
                        if (scanner.hasNextLine()) {
                            rightAnswer = scanner.nextLine();
                            if (rightAnswer.equalsIgnoreCase("cancel")) {
                                System.out.println("Terminating process...");
                                return;
                            }
                            i++;
                            done = true;
                        } else {
                            if (invalidCheck()) {
                                return;
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter wrong answer 1, or type cancel to cancel: ");
                    done = false;
                    while(!done) {
                        if (scanner.hasNextLine()) {
                            wrongAnswer1 = scanner.nextLine();
                            if (wrongAnswer1.equalsIgnoreCase("cancel")) {
                                System.out.println("Terminating process...");
                                return;
                            }
                            i++;
                            done = true;
                        } else {
                            if (invalidCheck()) {
                                return;
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter wrong answer 2, or type cancel to cancel: ");
                    done = false;
                    while(!done) {
                        if (scanner.hasNextLine()) {
                            wrongAnswer2 = scanner.nextLine();
                            if (wrongAnswer2.equalsIgnoreCase("cancel")) {
                                System.out.println("Terminating process...");
                                return;
                            }
                            i++;
                            done = true;
                        } else {
                            if (invalidCheck()) {
                                return;
                            }
                        }
                    }
                    break;
                case 4:
                    done = false;
                    System.out.print("Enter wrong answer 3, or type cancel to cancel: ");
                    while(!done) {
                        if (scanner.hasNextLine()) {
                            wrongAnswer3 = scanner.nextLine();
                            if (wrongAnswer3.equalsIgnoreCase("cancel")) {
                                System.out.println("Terminating process...");
                                return;
                            }
                            i++;
                            done = true;
                        } else {
                            if (invalidCheck()) {
                                return;
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("Something went wrong, terminating process...");
                    return;
            }
            if (quiz.addQuestion(question, rightAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3)) {
                System.out.println("Question successfully added");
            } else {
                System.out.println("Error, question already exists");
            }
        }
    }

    public static void remove() {
        System.out.print("Enter question or question index, or type cancel to cancel: ");
        boolean done = false;
        while (!done) {
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice < 0 || choice >= quiz.getQuestions().size()) {
                    if (invalidCheck()) {
                        done = true;
                    }
                }
                if (quiz.removeQuestion(choice)) {
                    System.out.println("Question removed successfully");
                } else {
                    System.out.println("Error, question does not exist");
                    if (invalidCheck()) {
                        done = true;
                    }
                }
            } else if (scanner.hasNextLine()) {
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("cancel")) {
                    System.out.println("Terminating process...");
                    done = true;
                }
                if (quiz.removeQuestion(choice)) {
                    System.out.println("Question removed successfully");
                } else {
                    if (invalidCheck()) {
                        System.out.println("Error, question does not exist");
                        if (invalidCheck()) {
                            done = true;
                        }
                    }
                }
            } else {
                if (invalidCheck()) {
                    done = true;
                }
            }
        }
    }

    public static void edit() {
        System.out.println("Enter:\n");
        System.out.println("1 - Edit question\t");
        System.out.println("2 - Edit right answer\t");
        System.out.println("3 - Edit wrong answer 1\t");
        System.out.println("4 - Edit wrong answer 2\t");
        System.out.println("5 - Edit wrong answer 3\t");
        System.out.println("Cancel - Cancel operation \n");
        boolean done = false;
        while (!done) {
            if (scanner.hasNextInt() || scanner.hasNextLine()) {
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("cancel")) {
                    System.out.println("Terminating process...");
                    done = true;
                }
                int decision = Integer.parseInt(choice);
                if (decision < 1 || decision > 5) {
                    if (invalidCheck()) {
                        done = true;
                    }
                }
                System.out.print("Enter question or question index, or type cancel to cancel operation: ");
                if (scanner.hasNextInt()) {
                    int index = scanner.nextInt();
                    if (index < 0 || index >= quiz.getQuestions().size()) {
                        if (invalidCheck()) {
                            done = true;
                        }
                    }
                    System.out.print("Enter new string: ");
                    if (scanner.hasNextLine()) {
                        String newField = scanner.nextLine();
                        if (newField.equalsIgnoreCase("cancel")) {
                            System.out.println("Terminating process...");
                            done = true;
                        }
                        if (quiz.editQuestion(decision, index, newField)) {
                            System.out.println("Question edited successfully");
                            done = true;
                        } else {
                            System.out.println("New answer is duplicate of another answer");
                            if (invalidCheck()) {
                                done = true;
                            }
                        }
                    } else {
                        if (invalidCheck()) {
                            done = true;
                        }
                    }
                } else if (scanner.hasNextLine()) {
                    String question = scanner.nextLine();
                    if (question.equalsIgnoreCase("cancel")) {
                        System.out.println("Terminating process...");
                        done = true;
                    }
                    System.out.print("Enter new string: ");
                    if (scanner.hasNextLine()) {
                        String newField = scanner.nextLine();
                        if (newField.equalsIgnoreCase("cancel")) {
                            System.out.println("Terminating process...");
                            done = true;
                        }
                        if (quiz.editQuestion(decision, question, newField)) {
                            System.out.println("Question edited successfully");
                            done = true;
                        } else {
                            System.out.println("New answer is duplicate of another answer");
                            if (invalidCheck()) {
                                done = true;
                            }
                        }
                    } else {
                        if (invalidCheck()) {
                            done = true;
                        }
                    }
                }
            } else {
                if (invalidCheck()) {
                    done = true;
                }
            }
        }
    }

    public static void deleteQuiz() {
        System.out.println("Are you sure you want to delete this quiz? (y, n)");
        boolean done = false;
        while (!done) {
            if (scanner.hasNextLine()) {
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    quiz = null;
                    System.out.println("Quiz successfully deleted");
                    printInstructions();
                } else if (choice.equalsIgnoreCase("n")) {
                    System.out.println("Terminating process...");
                } else {
                    if (invalidCheck()) {
                        done = true;
                    }
                }
            }
        }
    }

    public static void printQuestions() {
        int i = 1;
        for (Question printedQuestion: quiz.getQuestions()) {
            System.out.println(i + ". " + printedQuestion.toString());
            i++;
        }
    }

    private static boolean invalidCheck() {
        System.out.println("Invalid input, would you like to cancel this operation? (y, n)");
        while (true) {
            if (scanner.hasNextLine()) {
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    System.out.print("Ok, enter next operation: ");
                    return true;
                } else if (choice.equalsIgnoreCase("n")) {
                    System.out.println("Ok, repeating...");
                    return false;
                } else {
                    System.out.println("Invalid input");
                }
            } else {
                System.out.println("Invalid input");
            }
        }
    }
}
