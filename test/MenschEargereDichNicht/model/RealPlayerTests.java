package MenschEargereDichNicht.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
public class RealPlayerTests {
    Model model = new Model(1000,1000);

    @Test
    void testGetColor() {
        RealPlayer player = new RealPlayer(PlayerColor.BLUE);
        assertEquals(PlayerColor.BLUE, player.getColor());
    }

    @Test
    void testGetListOfPokemons() {
        Model model = new Model(1000,1000);
        model.newGame();
        assertNotNull(model.getCurrentPlayer().getListOfPokemons());
        assertEquals(4, model.getCurrentPlayer().getListOfPokemons().size());
    }

    @Test
    void testSetListOfPokemons() {
        RealPlayer player = new RealPlayer(PlayerColor.BLUE);
        List<Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(new Pokemon(PlayerColor.BLUE, 2, 23));
        player.setListOfPokemons(pokemonList);
        assertEquals(pokemonList, player.getListOfPokemons());
    }

    @Test
    void testRollTheDice() {
        RealPlayer player = new RealPlayer(PlayerColor.BLUE);
        int diceResult = player.rollTheDice();
        assertTrue(diceResult >= 1 && diceResult <= 6);
    }

    @Test
    void testGetDiceNumber() {
        RealPlayer player = new RealPlayer(PlayerColor.BLUE);
        int diceResult = player.rollTheDice();
        assertEquals(diceResult, player.getDiceNumber());
    }

}


