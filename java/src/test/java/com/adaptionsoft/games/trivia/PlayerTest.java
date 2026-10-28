package com.adaptionsoft.games.trivia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.adaptionsoft.games.refactor_trivia.Player;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Alice");
    }

    @Test
    @DisplayName("Estado inicial deve ser: 0 moedas, casa 0 e fora da prisão")
    void testEstadoInicial() {
        assertEquals("Alice", player.getName());
        assertEquals(0, player.getPlace());
        assertEquals(0, player.getPurse());
        assertFalse(player.isInPenaltyBox());
    }

    @Test
    @DisplayName("Deve incrementar moedas de forma acumulativa")
    void testIncrementoDeMoedas() {
        player.addCoin();
        assertEquals(1, player.getPurse());

        player.addCoin();
        assertEquals(2, player.getPurse());
    }

    @Test
    @DisplayName("Deve alterar a posição do jogador")
    void testAlteracaoDePosicao() {
        player.setPlace(5);
        assertEquals(5, player.getPlace());
    }

    @Test
    @DisplayName("Deve atualizar o status para a caixa de penalidade")
    void testEntradaNaPrisao() {
        player.sendToPenaltyBox();
        assertTrue(player.isInPenaltyBox());
    }
}