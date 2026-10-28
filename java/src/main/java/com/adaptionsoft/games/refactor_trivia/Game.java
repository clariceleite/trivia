package com.adaptionsoft.games.refactor_trivia;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static final int BOARD_SIZE = 12;
    private static final int COINS_TO_WIN = 6;

    private final List<Player> players = new ArrayList<>();
    private final QuestionDeck questions = new QuestionDeck();

    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;

    public boolean isPlayable() {
        return howManyPlayers() >= 2;
    }

    public boolean add(String playerName) {
        players.add(new Player(playerName));

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        Player player = players.get(currentPlayer);
        System.out.println(player.getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (player.isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;
                System.out.println(player.getName() + " is getting out of the penalty box");
                moveAndAskQuestion(player, roll);
            } else {
                System.out.println(player.getName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }
        } else {
            moveAndAskQuestion(player, roll);
        }
    }

    private void moveAndAskQuestion(Player player, int roll) {
        player.setPlace((player.getPlace() + roll) % BOARD_SIZE);
        String category = questions.categoryFor(player.getPlace());

        System.out.println(player.getName() + "'s new location is " + player.getPlace());
        System.out.println("The category is " + category);
        System.out.println(questions.nextQuestion(category).getContent());
    }

    public boolean wasCorrectlyAnswered() {
        Player player = players.get(currentPlayer);

        if (player.isInPenaltyBox() && !isGettingOutOfPenaltyBox) {
            nextPlayer();
            return true;
        }

        System.out.println(player.isInPenaltyBox()
                ? "Answer was correct!!!!"
                : "Answer was corrent!!!!");
        player.addCoin();
        System.out.println(player.getName() + " now has " + player.getPurse() + " Gold Coins.");

        boolean notAWinner = !hasWon(player);
        nextPlayer();
        return notAWinner;
    }

    public boolean wrongAnswer() {
        Player player = players.get(currentPlayer);
        System.out.println("Question was incorrectly answered");
        System.out.println(player.getName() + " was sent to the penalty box");
        player.sendToPenaltyBox();

        nextPlayer();
        return true;
    }

    private boolean hasWon(Player player) {
        return player.getPurse() == COINS_TO_WIN;
    }

    private void nextPlayer() {
        currentPlayer = (currentPlayer + 1) % players.size();
    }
}