package com.adaptionsoft.games.trivia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.adaptionsoft.games.refactor_trivia.Question;
import com.adaptionsoft.games.refactor_trivia.QuestionDeck;

class QuestionDeckTest {

    private QuestionDeck deck;

    @BeforeEach
    void setUp() {
        deck = new QuestionDeck();
    }

    @Test
    @DisplayName("Mapeamento das 12 casas do tabuleiro deve ser idêntico ao jogo original")
    void testMapeamentoCategorias() {
        // Regra original: 0,4,8 = Pop | 1,5,9 = Science | 2,6,10 = Sports | 3,7,11 = Rock
        assertEquals("Pop", deck.categoryFor(0));
        assertEquals("Science", deck.categoryFor(1));
        assertEquals("Sports", deck.categoryFor(2));
        assertEquals("Rock", deck.categoryFor(3));

        assertEquals("Pop", deck.categoryFor(4));
        assertEquals("Science", deck.categoryFor(5));
        assertEquals("Sports", deck.categoryFor(6));
        assertEquals("Rock", deck.categoryFor(7));

        assertEquals("Pop", deck.categoryFor(8));
        assertEquals("Science", deck.categoryFor(9));
        assertEquals("Sports", deck.categoryFor(10));
        assertEquals("Rock", deck.categoryFor(11));
    }

    @Test
    @DisplayName("Deve consumir as perguntas em fila na ordem em que foram criadas")
    void testOrdemDasPerguntas() {
        Question q0 = deck.nextQuestion("Pop");
        assertEquals("Pop Question 0", q0.getContent());

        Question q1 = deck.nextQuestion("Pop");
        assertEquals("Pop Question 1", q1.getContent());
    }
}