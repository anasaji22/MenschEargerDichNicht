package MenschEargereDichNicht.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTests {
    @Test
    public void testInitPlayers() {
        Model model = new Model(800.0f, 600.0f);
        model.newGame();
        assertNotNull(model.getRealPlayersList());
        assertFalse(model.getRealPlayersList().isEmpty());
        for (RealPlayer realPlayer : model.getRealPlayersList()) {
            assertNotNull(realPlayer);
            assertNotNull(realPlayer.getListOfPokemons());
            assertFalse(realPlayer.getListOfPokemons().isEmpty());
            for (Pokemon pokemon : realPlayer.getListOfPokemons()) {
                assertNotNull(pokemon);
                assertNotNull(pokemon.getBox());
                assertTrue(model.getBoxMap().containsValue(pokemon.getBox()));
            }
        }
    }


    @Test
    public void testGetAvailableMove() {
        // first if
        Model model = new Model(800.0f, 600.0f);
        model.newGame();
        model.setPlayerTurn(PlayerTurn.PLAYER_BLUE);
        RealPlayer currentPlayer = model.getCurrentPlayer();
        Pokemon pokemon = currentPlayer.getListOfPokemons().get(0);
        int expectedBoxNr = model.getCurrentPlayerBoard()[0];
        Box availableBox = model.getAvailableBox(6, pokemon);
        assertEquals(expectedBoxNr, availableBox.boxId());
        // Sekunde if
        int currentBoxNumber = model.getCurrentPlayerBoard()[model.getCurrentPlayerBoard().length - 3];
        expectedBoxNr = model.getCurrentPlayerBoard()[model.getCurrentPlayerBoard().length - 2];
        pokemon.setBox(model.getBoxMap().get(currentBoxNumber));
        pokemon.setBoxNumber(currentBoxNumber);
        pokemon.setAlive(true);
        availableBox = model.getAvailableBox(1, pokemon);
        assertEquals(expectedBoxNr, availableBox.boxId());
        //else
        availableBox = model.getAvailableBox(5, pokemon);
        assertEquals(null, availableBox);
    }

    @Test
    public void testMovePokemon() {
        //Move Blue Pokemon from HomeBox
        Model model = new Model(800.0f, 600.0f);
        model.newGame();
        model.setPlayerTurn(PlayerTurn.PLAYER_BLUE);
        RealPlayer playerBlue = model.getCurrentPlayer();
        Pokemon pokemon = playerBlue.getListOfPokemons().get(0);
        int expectedBoxNr = model.getCurrentPlayerBoard()[0];
        int diceNumber = 6;
        Box avilibalBox = model.getAvailableBox(diceNumber, pokemon);
        model.movePokemon(diceNumber, avilibalBox, pokemon);
        assertEquals(model.getBoxMap().get(expectedBoxNr), pokemon.getBox());
        assertEquals(expectedBoxNr, pokemon.getBox().boxId());
        // Pokemon Red hits other pokemon Blue.
        int otherPokemonBoxNr = model.getCurrentPlayerBoard()[model.getCurrentPlayerBoard().length - 5];
        model.setPlayerTurn(model.getNextPlayer());
        Pokemon pokemon2 = model.getCurrentPlayer().getListOfPokemons().get(0);
                //Move red pokemon to 5 box before the blue pokemon (blue pokemon on home)
        pokemon2.setBoxNumber(otherPokemonBoxNr);
        pokemon2.setBox(model.getBoxMap().get(otherPokemonBoxNr));
        pokemon2.setAlive(true);
        expectedBoxNr = model.getInitialPositionByPlayerColor(pokemon.getColor())[pokemon.getIdNumber()];
        diceNumber = 1;
        avilibalBox = model.getAvailableBox(diceNumber, pokemon2);
        model.movePokemon(diceNumber, avilibalBox, pokemon2);
        assertEquals(model.getBoxMap().get(expectedBoxNr), pokemon.getBox());
        assertEquals(expectedBoxNr, pokemon.getBox().boxId());
        // Pokemon Blue hits the pokemon red
        model.setPlayerTurn(PlayerTurn.PLAYER_BLUE);
        expectedBoxNr = model.getInitialPositionByPlayerColor(pokemon2.getColor())[pokemon2.getIdNumber()];
        diceNumber = 6;
        avilibalBox = model.getAvailableBox(diceNumber, pokemon);
        model.movePokemon(diceNumber, avilibalBox, pokemon);
        assertEquals(model.getBoxMap().get(expectedBoxNr), pokemon2.getBox());
        assertEquals(expectedBoxNr, pokemon2.getBoxNumber());
    }

    @Test
    public void testCanPlayerPlay() {

        Model model = new Model(800.0f, 600.0f);
        model.newGame();
        int[] playerBoard = model.getCurrentPlayerBoard();
        Pokemon pokemon1 = model.getCurrentPlayer().getListOfPokemons().get(0);
        Pokemon pokemon2 = model.getCurrentPlayer().getListOfPokemons().get(1);
        Pokemon pokemon3 = model.getCurrentPlayer().getListOfPokemons().get(2);
        Pokemon pokemon4 = model.getCurrentPlayer().getListOfPokemons().get(3);
        Box avilibalBoxForPokemon1 = model.getBoxMap().get(playerBoard[playerBoard.length - 1]);
        Box avilibalBoxForPokemon2 = model.getBoxMap().get(playerBoard[playerBoard.length - 2]);
        Box avilibalBoxForPokemon3 = model.getBoxMap().get(playerBoard[playerBoard.length - 3]);
        Box avilibalBoxForPokemon4 = model.getBoxMap().get(playerBoard[playerBoard.length - 5]);
        //No one pokemon isAlive and the diceNumber != 6
        assertFalse(model.canPlayerPlay(3));
        //No one pokemon isAlive and the diceNumber = 6
        assertTrue(model.canPlayerPlay(6));
        // All pokemons are alive and the player can't play
        pokemon1.setBox(avilibalBoxForPokemon1);
        pokemon1.setBoxNumber(avilibalBoxForPokemon1.boxId());
        pokemon1.setAlive(true);
        pokemon2.setBox(avilibalBoxForPokemon2);
        pokemon2.setBoxNumber(avilibalBoxForPokemon2.boxId());
        pokemon2.setAlive(true);
        pokemon3.setBox(avilibalBoxForPokemon3);
        pokemon3.setBoxNumber(avilibalBoxForPokemon3.boxId());
        pokemon3.setAlive(true);
        pokemon4.setBox(avilibalBoxForPokemon4);
        pokemon4.setBoxNumber(avilibalBoxForPokemon4.boxId());
        pokemon4.setAlive(true);
        // can't play third if(), because he can't play with other
        assertFalse(model.canPlayerPlay(3));
        // He can't play, because he can't move out the board  "last return false"
        assertFalse(model.canPlayerPlay(6));
        // The third if() the Player can play with other pokemon "set a player in any Box in the mid of his available board"
        avilibalBoxForPokemon1 = model.getBoxMap().get(playerBoard[playerBoard.length / 2]);
        pokemon1.setBox(avilibalBoxForPokemon1);
        pokemon1.setBoxNumber(avilibalBoxForPokemon1.boxId());
        assertTrue(model.canPlayerPlay(6));
    }
    @Test
    public void testCanMovePokemonToBox() {
        Model model = new Model(800.0f, 600.0f);
        model.newGame();
        int[] playerBoard = model.getCurrentPlayerBoard();
        Pokemon pokemon1 = model.getCurrentPlayer().getListOfPokemons().get(0);
        Pokemon pokemon2 = model.getCurrentPlayer().getListOfPokemons().get(1);
        Box avilibalBoxForPokemon1 = model.getBoxMap().get(playerBoard[0]);
        //Player can move Player to the first Box on his PlaySpace
        assertTrue(model.canMovePokemonToBox(6, avilibalBoxForPokemon1, pokemon1));
        //player can't move to the Box, which used by another pokemon.
        pokemon1.setBox(avilibalBoxForPokemon1);
        pokemon1.setBoxNumber(avilibalBoxForPokemon1.boxId());
        pokemon1.setAlive(true);
        assertFalse(model.canMovePokemonToBox(6,avilibalBoxForPokemon1, pokemon2));
        //player can move pokemon to available Box
        avilibalBoxForPokemon1 = model.getBoxMap().get(playerBoard[5]);
        assertTrue(model.canMovePokemonToBox(5, avilibalBoxForPokemon1, pokemon1));
        // Player can't move to invalid Box
        avilibalBoxForPokemon1 = model.getBoxMap().get(playerBoard[10]);
        assertFalse(model.canMovePokemonToBox(3, avilibalBoxForPokemon1, pokemon1));
    }
    @Test
    public void testCheckWine(){

        Model model = new Model(800.0f, 600.0f);
        model.newGame();
        int[] playerBoard = model.getCurrentPlayerBoard();
        Pokemon pokemon1 = model.getCurrentPlayer().getListOfPokemons().get(0);
        Pokemon pokemon2 = model.getCurrentPlayer().getListOfPokemons().get(1);
        Pokemon pokemon3 = model.getCurrentPlayer().getListOfPokemons().get(2);
        Pokemon pokemon4 = model.getCurrentPlayer().getListOfPokemons().get(3);
        Box avilibalBoxForPokemon1 = model.getBoxMap().get(playerBoard[playerBoard.length - 1]);
        Box avilibalBoxForPokemon2 = model.getBoxMap().get(playerBoard[playerBoard.length - 2]);
        Box avilibalBoxForPokemon3 = model.getBoxMap().get(playerBoard[playerBoard.length - 3]);
        Box avilibalBoxForPokemon4 = model.getBoxMap().get(playerBoard[playerBoard.length - 4]);
        Box notWinnBox =  model.getBoxMap().get(playerBoard[playerBoard.length - 5]);
        pokemon1.setBox(avilibalBoxForPokemon1);
        pokemon1.setBoxNumber(avilibalBoxForPokemon1.boxId());
        pokemon1.setAlive(true);
        pokemon2.setBox(avilibalBoxForPokemon2);
        pokemon2.setBoxNumber(avilibalBoxForPokemon2.boxId());
        pokemon2.setAlive(true);
        pokemon3.setBox(avilibalBoxForPokemon3);
        pokemon3.setBoxNumber(avilibalBoxForPokemon3.boxId());
        pokemon3.setAlive(true);
        pokemon4.setBox(avilibalBoxForPokemon4);
        pokemon4.setBoxNumber(avilibalBoxForPokemon4.boxId());
        pokemon4.setAlive(true);
        assertTrue(model.checkWin());
        //not a winn
        pokemon4.setBox(notWinnBox);
        pokemon4.setBoxNumber(notWinnBox.boxId());
        pokemon4.setAlive(true);
        assertFalse(model.checkWin());
    }
    @Test
    public void testGetRelatedBox(){

        Model model = new Model(800.0f, 600.0f);
        model.newGame();
        int[] playerBoard = model.getCurrentPlayerBoard();
        Box expectedBox = model.getBoxMap().get(playerBoard[0]);
        Pokemon pokemon1 = model.getCurrentPlayer().getListOfPokemons().get(0);
        //set a pokemon in the first box
        pokemon1.setBox(expectedBox);
        pokemon1.setBoxNumber(expectedBox.boxId());
        pokemon1.setAlive(true);
        //the coordinate are saved in float variabiles thats way  + 1
        int xPosOfRelatedBox = (int) expectedBox.x1() +1 ;
        int yPosOfRelatedBox = (int)expectedBox.y1()  + 1;
        assertEquals(expectedBox,model.getRelatedBox(xPosOfRelatedBox,yPosOfRelatedBox));

        // related a not available box
        // out the available boxes
        xPosOfRelatedBox = xPosOfRelatedBox + (int) expectedBox.size();
        yPosOfRelatedBox = yPosOfRelatedBox + (int) expectedBox.size();
        expectedBox = null;

        assertEquals(expectedBox, model.getRelatedBox(xPosOfRelatedBox, yPosOfRelatedBox));


    }


}
