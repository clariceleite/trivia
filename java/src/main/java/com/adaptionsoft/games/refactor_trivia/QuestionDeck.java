package com.adaptionsoft.games.refactor_trivia;

import java.util.LinkedList;

public class QuestionDeck {
    private static final int QUESTIONS_PER_CATEGORY = 50;

    private final LinkedList<Question> popQuestions = new LinkedList<>();
    private final LinkedList<Question> scienceQuestions = new LinkedList<>();
    private final LinkedList<Question> sportsQuestions = new LinkedList<>();
    private final LinkedList<Question> rockQuestions = new LinkedList<>();

    public QuestionDeck() {
        for (int i = 0; i < QUESTIONS_PER_CATEGORY; i++) {
            popQuestions.addLast(new Question("Pop", "Pop Question " + i));
            scienceQuestions.addLast(new Question("Science", "Science Question " + i));
            sportsQuestions.addLast(new Question("Sports", "Sports Question " + i));
            rockQuestions.addLast(new Question("Rock", "Rock Question " + i));
        }
    }

    public String categoryFor(int place) {
        if (place == 0 || place == 4 || place == 8) return "Pop";
        if (place == 1 || place == 5 || place == 9) return "Science";
        if (place == 2 || place == 6 || place == 10) return "Sports";
        return "Rock";
    }

    public Question nextQuestion(String category) {
        switch (category) {
            case "Pop":     return popQuestions.removeFirst();
            case "Science": return scienceQuestions.removeFirst();
            case "Sports":  return sportsQuestions.removeFirst();
            default:        return rockQuestions.removeFirst();
        }
    }
}