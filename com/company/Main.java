// Welcome to the source code of the project. Enjoy your stay.

// This line is why the program comes in 2 folders, com and company. I'm too lazy to remove it so it stays.

package com.company;

// All of this stuff imports utilities that aren't loaded by default to make this program work.

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner(System.in); // Handles all user inputs.
    private static Quiz quiz; // Holds the quiz loaded in memory.

    public static void main(String[] args) {

        System.out.println("Welcome to QuizApp, Version 0.1.0.0\n"); // Prints out version number.
        printInstructions();
        int choice; // Stores user input
        boolean quit = false;
        while (!quit) {
            /*
                If you're confused why this query appears before detecting inputs, it checks for the input after
                the query is called.
             */
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt(); // Detects user input, then stores it in the variable.
                /*
                A weird thing with the scanner is it can occasionally store the enter key input instead of the
                actual input if it isn't looking for a string. This is meant to make sure that doesn't happen.
                 */
                scanner.nextLine();
                if (quiz == null) { // If there is no quiz loaded, then it uses the switch statement below.
                    switch (choice) { // Grabs the variable, then decides what to do based on that.
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
                            load();
                            break;
                        case 4:
                            /* I'm not sure why I even put a message here, the window disappears instantly when
                               the program ends so you can't even read it.
                            */
                            System.out.println("See ya");
                            quit = true;
                            break;
                        default:
                            // Ever wanted to be sneaky and put in a number out of bounds? Well too bad.
                            System.out.println("Invalid input");
                            break;
                    }
                } else { // Quiz is loaded, so it uses the switch statement here instead.
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
                            renameQuiz();
                            break;
                        case 7:
                            unloadQuiz();
                            break;
                        case 8:
                            printQuestions();
                            break;
                        case 9:
                            save();
                            break;
                        case 10:
                            System.out.println("See ya");
                            quit = true;
                            break;
                        default:
                            // Same thing as last time, can't put in something out of bounds.
                            System.out.println("Invalid input");
                            break;
                    }
                }
            } else {
                // No, putting in anything other than an integer doesn't work either.
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        }
    }

    public static void printInstructions() { // Prints instructions
        System.out.println("Enter:\n");
        System.out.println("0 - Print instructions\r");
        System.out.println("1 - Check for updates\r");
        if (quiz == null) {
            System.out.println("2 - Make new quiz\r");
            System.out.println("3 - Load quiz\r");
            System.out.println("4 - Close program\n");
        } else {
            System.out.println("2 - Run quiz\r");
            System.out.println("3 - Add question\r");
            System.out.println("4 - Remove question\r");
            System.out.println("5 - Edit question\r");
            System.out.println("6 - Rename quiz\r");
            System.out.println("7 - Unload quiz\r");
            System.out.println("8 - Print questions\r");
            System.out.println("9 - Save quiz\r");
            System.out.println("10 - Close program\n");
        }
    }

    // This is a bit of a nightmare to read. Just warning you.
    public static void checkForUpdates() {
        // Stores version number.
        int w = 1;
        int x = 0;
        int y = 0;
        int z = 0;
        boolean foundUpdate = false; // If an update is found, this becomes true.
        /*
            Try and catch blocks handle exceptions, instead of letting the program crash and burn. All of this
            in the try block is the code that can potentially cause an exception.
         */
        try {
            // A loop that is meant to happen 4 times.
            for (int i = 0; i < 5; i++) {
                /*
                    To check for updates, the program checks if a release with a URL different than the
                    one you got this release from exists, by implementing the version number with the 4
                    variables into the url, and incrementing them 1 by 1.
                */
                switch (i) {
                    case 1:
                        URL url = new URL("https://github.com/GamingPiglet/Quiz-App/releases/tag/v" + w + "." + x
                                + "." + y + "." + (z + 1));
                        HttpURLConnection connect = (HttpURLConnection) url.openConnection();

                        int response = connect.getResponseCode();
                        if (response == HttpURLConnection.HTTP_OK) {
                            foundUpdate = true;
                        }
                        break;
                    case 2:
                        url = new URL("https://github.com/GamingPiglet/Quiz-App/releases/tag/v" + w + "." + x
                                + "." + (y + 1) + "." + z);
                        connect = (HttpURLConnection) url.openConnection();
                        response = connect.getResponseCode();
                        if (response == HttpURLConnection.HTTP_OK) {
                            foundUpdate = true;
                        }
                        break;
                    case 3:
                        url = new URL("https://github.com/GamingPiglet/Quiz-App/releases/tag/v" + w + "." + (x + 1)
                                + "." + y + "." + z);
                        connect = (HttpURLConnection) url.openConnection();
                        response = connect.getResponseCode();
                        if (response == HttpURLConnection.HTTP_OK) {
                            foundUpdate = true;
                        }
                        break;
                    case 4:
                        url = new URL("https://github.com/GamingPiglet/Quiz-App/releases/tag/v" + (w + 1) + "." + x
                                + "." + y + "." + z);
                        connect = (HttpURLConnection) url.openConnection();
                        response = connect.getResponseCode();
                        if (response == HttpURLConnection.HTTP_OK) {
                            foundUpdate = true;
                        }
                        break;
                }
                // If an update is found, immediately break out of the loop.
                if (foundUpdate) {
                    break;
                }
            }
            if (foundUpdate) {
                // Ask the user if they want to download the update now.
                System.out.println("Update found, would you like to download it now? (y, n)");
                boolean done = false;
                /*
                 This while loop is to make sure the user can continue with their operation if they make a
                 mistake. You'll be seeing these a lot.
                */
                while (!done) {
                    if (scanner.hasNextLine()) {
                        String choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("y")) {
                            /*
                                Answered yes? You go straight to the release page. I don't know how to make an
                                auto updater so this is the best I can do.
                             */
                            URI uri = new URI("https://github.com/GamingPiglet/Quiz-App/releases");
                            java.awt.Desktop.getDesktop().browse(uri);
                            System.out.println("Ok, replace the executable with the new download.");
                            done = true;
                        } else if (choice.equalsIgnoreCase("n")) {
                            // No? You get the URL to the page, so you can access it later.
                            System.out.println("Ok, remember to download it at " +
                                    "https://github.com/GamingPiglet/Quiz-App/releases");
                            done = true;
                        } else {
                            if (invalidCheck()) {
                                done = true;
                            }
                        }
                    }
                }
            } else {
                // Self explanatory. Prints a message saying no updates were found.
                System.out.println("No updates found");
            }
        } catch (Exception e) {
            /*
                 The catch block handles the exception, so here it prints information, tells you how to report it,
                 then reprints the instructions for your next input.
             */
            e.printStackTrace();
            System.out.println("Copy this info, and open an issue with it at " +
                    "https://github.com/GamingPiglet/Quiz-App/issues");
            printInstructions();
        }
    }

    public static void makeNewQuiz() {
        boolean done = false;
        while (!done) {
            // Simple. Just grab the quiz name, then make it.
            System.out.print("Enter quiz name, or type cancel to cancel: ");
            if (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                // If you entered cancel, the program cancels the operation.
                if (name.equalsIgnoreCase("cancel")) {
                    System.out.print("Terminating process... Enter next operation: ");
                    return;
                }
                quiz = new Quiz(name);
                System.out.println("Quiz made successfully");
                // Reprints instructions cause you get more bells and whistles.
                printInstructions();
                done = true;
            } else {
                if (invalidCheck()) {
                    done = true;
                }
            }
        }
    }

    // Another nightmare to read. I don't mind if you're actually skimming through all this.
    public static void load() {
        boolean done = false;
        System.out.println("Enter file name, or type cancel to cancel: ");
        while (!done) {
            /*
                Grabs the name of the .sav file in the directory to be loaded. All inputs are converted to
                lowercase to make entering the name easier, though it makes it so all file names have to be
                lowercase.
             */
            if (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                name = name.toLowerCase() + ".sav";
                if (name.equalsIgnoreCase("cancel")) {
                    System.out.print("Terminating process... Enter next operation: ");
                    return;
                }
                File file = new File("./" + name);
                if (file.exists()) {
                    try {
                    /*
                        The file input stream loads the file into memory, and the object input stream loads
                        the information stored within the file as the quiz.
                     */
                        FileInputStream loadingFile = new FileInputStream(name);

                        ObjectInputStream loader = new ObjectInputStream(loadingFile);

                        // Statement that actually loads the quiz.
                        quiz = (Quiz) loader.readObject();

                        // Closes both streams to free up some memory.
                        loader.close();

                        System.out.println("Quiz has successfully been loaded");
                        printInstructions();
                        done = true;
                    } catch (Exception e) {
                        // Same thing as last time. Yeah I'm a little lazy with these comments.
                        e.printStackTrace();
                        System.out.println("Copy this info, and open an issue with it at " +
                                "https://github.com/GamingPiglet/Quiz-App/issues");
                        printInstructions();
                        done = true;
                    }
                } else {
                    System.out.println("File does not exist");
                    done = true;
                }
            } else {
                if (invalidCheck()) {
                    done = true;
                }
            }
        }
    }

    // 2 nightmares to go through. Yay.
    public static void run() {
        if (quiz.getQuestions().size() > 0) {
            /*
                Makes copy of questions array list, as the method check if this list is empty to decide when the
                quiz has ended.
             */
            ArrayList<Question> temp = new ArrayList<>(quiz.getQuestions());
            // Initializes a variable to tally score as quiz is running.
            int score = 0;
            // Booleans to check if an answer has been added yet.
            boolean rightIn = false;
            boolean wrong1In = false;
            boolean wrong2In = false;
            boolean wrong3In = false;
            // Array list that has answers added in a random order for randomization.
            ArrayList<String> answers = new ArrayList<>();
            // Continuously runs the method until questions copy runs out.
            while (temp.size() > 0) {
                // Randomly gets a question from the copy list to randomize question order.
                Question randQuestion = temp.get(((int) (Math.random() * temp.size())));
                // Iterates until all 4 answers are in the list.
                while (answers.size() < 4) {
                    // Randomly decides which answer to add with the switch statement.
                    int random = (int) (Math.random() * 5 + 1);
                    switch (random) {
                        // If an answer is already added, so the boolean is true, skip to the next iteration.
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
                // Resets the variables for the next question.
                rightIn = false;
                wrong1In = false;
                wrong2In = false;
                wrong3In = false;
                // Prints out the question and then the 4 answers.
                System.out.println(randQuestion.getQuestion());
                System.out.println("1 - " + answers.get(0));
                System.out.println("2 - " + answers.get(1));
                System.out.println("3 - " + answers.get(2));
                System.out.println("4 - " + answers.get(3));
                System.out.println("Remember, you can cancel this operation by entering cancel.");
                // Just skips to a new line to make it easier on your eyes.
                System.out.println();
                boolean answered = false;
                while (!answered) {
                    if (scanner.hasNextInt()) {

                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        /*
                            This method uses indexing to get the answer from your input, so it subtracts it by 1
                            since indexes in a list start from 0 instead of 1.
                         */
                        choice--;
                        if (choice < 0 || choice >= answers.size()) {
                            System.out.println("Answer out of bounds, please reenter");
                            continue;
                        }
                        // If you got it correct, it increments your score, otherwise it doesn't.
                        if (quiz.checkAnswer(randQuestion, answers.get(choice))) {
                            System.out.println("Answer is correct");
                            score++;
                        } else {
                            System.out.println("Answer is incorrect, correct answer was " +
                                    randQuestion.getRightAnswer());
                        }
                        /*
                            Makes the boolean true to exit the loop, removes the question to prevent it from
                            being selected again, and clears the answers list for the next question.
                         */
                        answered = true;
                        temp.remove(randQuestion);
                        answers.clear();

                    } else if (scanner.hasNextLine()) {
                        String choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("cancel")) {
                            System.out.print("Terminating process... Enter next operation: ");
                            return;
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
            }
            // Prints out a message to say what your score is.
            System.out.println("Quiz over, your score is: " + score + "/" + quiz.getQuestions().size());
        } else {
            /*
                Makes sure the method doesn't run if there are no questions. A few other methods have this check
                as well.
             */
            System.out.println(quiz.getName() + " has no questions");
        }
    }

    // Another nightmare of a text wall. Yeah there's a lot here.
    public static void add() {
        // Initializes variables to store your answers in to add the question with later.
        String question = "";
        String rightAnswer = "";
        String wrongAnswer1 = "";
        String wrongAnswer2 = "";
        String wrongAnswer3 = "";
        // Variable to make sure the program runs 4 times.
        int i = 0;
        while (i < 5) {
            // Decides what input to get depending on what iteration the loop is on.
            switch (i) {
                case 0:
                    boolean done = false;
                    System.out.print("Enter question, or type cancel to cancel: ");
                    while (!done) {
                        if (scanner.hasNextLine()) {
                            question = scanner.nextLine();
                            if (question.equalsIgnoreCase("cancel")) {
                                System.out.print("Terminating process... Enter next operation: ");
                                return;
                            }
                            // Makes sure there aren't 2 or more questions with the same heading.
                            if (quiz.queryQuestion(question) != null) {
                                System.out.println("Question already exists");
                                if (invalidCheck()) {
                                    return;
                                } else {
                                    continue;
                                }
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
                    while (!done) {
                        if (scanner.hasNextLine()) {
                            rightAnswer = scanner.nextLine();
                            if (rightAnswer.equalsIgnoreCase("cancel")) {
                                System.out.print("Terminating process... Enter next operation: ");
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
                    while (!done) {
                        if (scanner.hasNextLine()) {
                            wrongAnswer1 = scanner.nextLine();
                            if (wrongAnswer1.equalsIgnoreCase("cancel")) {
                                System.out.print("Terminating process... Enter next operation: ");
                                return;
                            } else if (wrongAnswer1.equals(rightAnswer)) {
                                System.out.println("Wrong answer 1 cannot be the same as another answer");
                                if (invalidCheck()) {
                                    return;
                                } else {
                                    continue;
                                }
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
                    while (!done) {
                        if (scanner.hasNextLine()) {
                            wrongAnswer2 = scanner.nextLine();
                            if (wrongAnswer2.equalsIgnoreCase("cancel")) {
                                System.out.print("Terminating process... Enter next operation: ");
                                return;
                            } else if (wrongAnswer2.equals(wrongAnswer1) || wrongAnswer2.equals(rightAnswer)) {
                                System.out.println("Wrong answer 2 cannot be the same as another answer");
                                if (invalidCheck()) {
                                    return;
                                } else {
                                    continue;
                                }
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
                    while (!done) {
                        if (scanner.hasNextLine()) {
                            wrongAnswer3 = scanner.nextLine();
                            if (wrongAnswer3.equalsIgnoreCase("cancel")) {
                                System.out.print("Terminating process... Enter next operation: ");
                                return;
                            } else if (wrongAnswer3.equals(wrongAnswer1) || wrongAnswer3.equals(rightAnswer)
                                    || wrongAnswer3.equals(wrongAnswer2)) {
                                System.out.println("Wrong answer 3 cannot be the same as another answer");
                                if (invalidCheck()) {
                                    return;
                                } else {
                                    continue;
                                }
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

        }
        // Adds the question with the variables earlier, and prints a message confirming it.
        quiz.addQuestion(question, rightAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3);
        System.out.println("Question successfully added");
    }

    public static void remove() {
        if (quiz.getQuestions().size() > 0) {
            System.out.print("Enter question or question index, or type cancel to cancel: ");
            boolean done = false;
            while (!done) {
                // Decides if the input is an index or a question heading.
                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    scanner.nextLine();
                    if (choice < 0 || choice >= quiz.getQuestions().size()) {
                        if (invalidCheck()) {
                            return;
                        }
                    }
                    // If it's an index, it uses it to remove the question here.
                    if (quiz.removeQuestion(choice)) {
                        System.out.println("Question removed successfully, enter next operation: ");
                        done = true;
                    } else {
                        System.out.println("Error, question does not exist");
                        if (invalidCheck()) {
                            done = true;
                        }
                    }
                } else if (scanner.hasNextLine()) {
                    String choice = scanner.nextLine();
                    if (choice.equalsIgnoreCase("cancel")) {
                        System.out.print("Terminating process... Enter next operation: ");
                        return;
                    }
                    // If it's a heading, it uses it to remove the question here.
                    if (quiz.removeQuestion(choice)) {
                        System.out.println("Question removed successfully, enter next operation: ");
                        done = true;
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
        } else {
            System.out.println(quiz.getName() + " has no questions");
        }
    }

    public static void edit() {
        if (quiz.getQuestions().size() > 0) {
            // Prints a selection of choices to decide what should be edited.
            System.out.println("Enter:\n");
            System.out.println("1 - Edit question\r");
            System.out.println("2 - Edit right answer\r");
            System.out.println("3 - Edit wrong answer 1\r");
            System.out.println("4 - Edit wrong answer 2\r");
            System.out.println("5 - Edit wrong answer 3\r");
            System.out.println("Cancel - Cancel operation \n");
            boolean done = false;
            while (!done) {
                // Stores selection here.
                if (scanner.hasNextInt()) {
                    int decision = scanner.nextInt();
                    scanner.nextLine();
                    if (decision < 1 || decision > 5) {
                        if (invalidCheck()) {
                            return;
                        }
                    }
                    System.out.print("Enter question or question index, or type cancel to cancel operation: ");
                    boolean done1 = false;
                    // Initializes variables to store inputs, these values are remnants from testing.
                    int index = -1;
                    String question = "placeholder";
                    while (!done1) {
                        // Checks if user entered index or question heading, like last time.
                        if (scanner.hasNextInt()) {
                            index = scanner.nextInt();
                            scanner.nextLine();
                            // Checks if index is out of bounds.
                            if (index < 0 || index >= quiz.getQuestions().size()) {
                                if (invalidCheck()) {
                                    return;
                                } else {
                                    continue;
                                }
                            }
                        } else if (scanner.hasNextLine()) {
                            question = scanner.nextLine();
                            if (question.equalsIgnoreCase("cancel")) {
                                System.out.print("Terminating process... Enter next operation: ");
                                return;
                                // Checks if a question with the heading exists.
                            } else if (quiz.queryQuestion(question) == null) {
                                System.out.println("Question does not exist");
                                if (invalidCheck()) {
                                    return;
                                } else {
                                    continue;
                                }
                            }
                        }
                        done1 = true;
                    }
                    // Takes input that will replace old field.
                    System.out.print("Enter new string: ");
                    boolean done2 = false;
                    while (!done2) {
                        if (scanner.hasNextLine()) {
                            String newField = scanner.nextLine();
                            if (newField.equalsIgnoreCase("cancel")) {
                                System.out.print("Terminating process... Enter next operation: ");
                                return;
                            }
                            // Prints messages saying whether or not edit was successful.
                            if (index != -1) {
                                if (quiz.editQuestion(decision, index, newField)) {
                                    System.out.println("Question edited successfully");
                                    done2 = true;
                                    done = true;
                                } else {
                                    System.out.println("New answer is duplicate of another answer");
                                }
                            }
                            if (!question.equals("placeholder")) {
                                if (quiz.editQuestion(decision, question, newField)) {
                                    System.out.println("Question edited successfully");
                                    done2 = true;
                                    done = true;
                                } else {
                                    System.out.println("New answer is duplicate of another answer");
                                }
                            }
                        } else {
                            if (invalidCheck()) {
                                done = true;
                            }
                        }
                    }
                } else if (scanner.hasNextLine()) {
                    String choice = scanner.nextLine();
                    if (choice.equalsIgnoreCase("cancel")) {
                        System.out.print("Terminating process... Enter next operation: ");
                        done = true;
                    }
                } else {
                    if (invalidCheck()) {
                        done = true;
                    }
                }
            }
        } else {
            System.out.println(quiz.getName() + " has no questions");
        }
    }

    public static void renameQuiz() {
        System.out.println("Enter new quiz name, or type cancel to cancel: ");
        boolean done = false;
        while (!done) {
            if (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                if (name.equalsIgnoreCase("cancel")) {
                    System.out.print("Terminating process... Enter next operation: ");
                    return;
                } else {
                    // Uses input to set the quiz name, and print a message confirming the change.
                    System.out.println("Quiz name changed from " + quiz.getName() + " to " + name);
                    quiz.setName(name);
                }
                done = true;
            } else {
                if (invalidCheck()) {
                    done = true;
                }
            }
        }
    }

    // Sets the quiz field to null, to unload it from memory.
    public static void unloadQuiz() {
        System.out.println("Are you sure you want to unload this quiz? (y, n)");
        boolean done = false;
        while (!done) {
            if (scanner.hasNextLine()) {
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    quiz = null;
                    System.out.println("Quiz successfully unloaded");
                    printInstructions();
                    done = true;
                } else if (choice.equalsIgnoreCase("n")) {
                    System.out.print("Terminating process... Enter next operation: ");
                    done = true;
                } else {
                    if (invalidCheck()) {
                        done = true;
                    }
                }
            }
        }
    }

    // Prints out the name of the quiz, then iterates through the list to print out every question.
    public static void printQuestions() {
        int i = 1;
        System.out.println("Name: " + quiz.getName());
        if (quiz.getQuestions().size() > 0) {
            for (Question printedQuestion : quiz.getQuestions()) {
                System.out.println(i + ". " + printedQuestion.toString());
                i++;
            }
        } else {
            System.out.println(quiz.getName() + " has no questions");
        }
    }

    public static void save() {
        System.out.println("Are you sure? (y, n)");
        System.out.println("NOTE: If you renamed the quiz, it will save as a new file instead");
        boolean done = false;
        while (!done) {
            if (scanner.hasNextLine()) {
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    try {
                        // Sets up a stream to a file to write the quiz field to. File is made if nonexistent.
                        FileOutputStream file = new FileOutputStream(quiz.getName().toLowerCase() + ".sav");

                        // Sets up a stream to write objects to the file with.
                        ObjectOutputStream savedQuiz = new ObjectOutputStream(file);

                        // Writes the field to the file.
                        savedQuiz.writeObject(quiz);

                        // Closes the streams to free up memory.
                        savedQuiz.close();

                        System.out.println("Quiz saved to file");
                        System.out.println("NOTE: Do not change any of the letters to uppercase, as" +
                                "\nthe program finds the quiz with lowercase letters. You can rename it, but don't" +
                                "\n change any letters to uppercase. You should also make sure the saved quizzes " +
                                "\nstay in the same directory, as the app checks where the .jar is located to" +
                                "\nfind the quizzes. You can make copies to give to your friends, but make sure " +
                                "\nthe original files stay in here.");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Copy this info, and open an issue with it at " +
                                "https://github.com/GamingPiglet/Quiz-App/issues.");
                        printInstructions();
                    }
                    done = true;
                } else if (choice.equalsIgnoreCase("n")) {
                    System.out.print("Terminating process... Enter next operation: ");
                    done = true;
                } else {
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
    }

    /*
        Code almost every method uses to see if user wants to continue with their operation after certain invalid
        inputs.
     */
    private static boolean invalidCheck() {
        System.out.println("Invalid input, would you like to cancel this operation? (y, n)");
        while (true) {
            if (scanner.hasNextLine()) {
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    System.out.print("Ok, enter next operation: ");
                    return true;
                } else if (choice.equalsIgnoreCase("n")) {
                    System.out.println("Ok, reenter input: ");
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
