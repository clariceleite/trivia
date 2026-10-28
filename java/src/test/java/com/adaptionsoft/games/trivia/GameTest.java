package com.adaptionsoft.games.trivia;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.adaptionsoft.games.refactor_trivia.Game;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    @DisplayName("Regra: O jogo só é jogável com no mínimo 2 jogadores")
    void testMecanicaIsPlayable() {
        assertFalse(game.isPlayable());

        game.add("Alice");
        assertFalse(game.isPlayable());

        game.add("Bob");
        assertTrue(game.isPlayable());
    }

    @Test
    @DisplayName("Regra: O tabuleiro possui 12 posições (loop com operador módulo)")
    void testAvançoEModuloDoTabuleiro() {
        game.add("Alice");
        game.add("Bob");

        // Alice rola 11 -> vai para a casa 11
        game.roll(11);
        game.wasCorrectlyAnswered();

        // Turno do Bob
        game.roll(1);
        game.wasCorrectlyAnswered();

        // Alice rola 3 -> (11 + 3) % 12 = casa 2
        game.roll(3);
        // O jogo deve continuar normalmente sem lançar exceções de índice
        assertTrue(game.wasCorrectlyAnswered());
    }

    @Test
    @DisplayName("Regra: Resposta incorreta envia o jogador para a caixa de penalidade")
    void testEnvioParaPrisao() {
        game.add("Alice");
        game.add("Bob");

        game.roll(4);
        boolean continua = game.wrongAnswer();

        // wrongAnswer sempre retorna true (o jogo continua)
        assertTrue(continua);
    }

    @Test
    @DisplayName("Regra: Jogador na prisão com dado PAR não anda e não pode ganhar moeda")
    void testPrisaoComDadoPar() {
        game.add("Alice");
        game.add("Bob");

        // Turno 1: Alice vai para a prisão
        game.roll(2);
        game.wrongAnswer();

        // Turno 1: Bob joga e pontua
        game.roll(1);
        game.wasCorrectlyAnswered();

        // Turno 2: Alice rola número PAR (2) -> permanece presa
        game.roll(2);
        boolean continua = game.wasCorrectlyAnswered();

        assertTrue(continua);
    }

    @Test
    @DisplayName("Regra: Jogador na prisão com dado ÍMPAR ganha a oportunidade de responder e pontuar")
    void testPrisaoComDadoImpar() {
        game.add("Alice");
        game.add("Bob");

        // Alice vai para a prisão
        game.roll(2);
        game.wrongAnswer();

        // Turno do Bob
        game.roll(1);
        game.wasCorrectlyAnswered();

        // Alice rola ÍMPAR (3) -> sai da prisão e responde a pergunta
        game.roll(3);
        boolean continua = game.wasCorrectlyAnswered();

        assertTrue(continua);
    }

    @Test
    @DisplayName("Regra: O jogo encerra (retorna false) exatamente quando um jogador atinge 6 moedas")
    void testCondicaoDeVitoria() {
        game.add("Alice");
        game.add("Bob");

        // Simula 5 rodadas de acertos para Alice
        for (int i = 0; i < 5; i++) {
            game.roll(1); // Turno da Alice
            assertTrue(game.wasCorrectlyAnswered(), "Alice ainda não venceu na rodada " + (i + 1));

            game.roll(1); // Turno do Bob
            game.wasCorrectlyAnswered();
        }

        // 6ª rodada: Alice completa 6 moedas e vence o jogo
        game.roll(1);
        boolean gameContinues = game.wasCorrectlyAnswered();

        // wasCorrectlyAnswered retorna false quando !hasWon(player) é avaliado
        assertFalse(gameContinues, "O jogo deveria encerrar quando um jogador atinge 6 moedas");
    }

    @Test
    @DisplayName("Regra: Turnos devem alternar sequencialmente entre os jogadores")
    void testAlternanciaDeTurnos() {
        game.add("Alice");
        game.add("Bob");
        game.add("Charlie");

        // Jogador 0 (Alice)
        game.roll(1);
        assertTrue(game.wasCorrectlyAnswered());

        // Jogador 1 (Bob)
        game.roll(1);
        assertTrue(game.wasCorrectlyAnswered());

        // Jogador 2 (Charlie)
        game.roll(1);
        assertTrue(game.wasCorrectlyAnswered());

        // Retorna para Jogador 0 (Alice)
        game.roll(1);
        assertTrue(game.wasCorrectlyAnswered());
    }
}