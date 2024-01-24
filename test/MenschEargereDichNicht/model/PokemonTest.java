package MenschEargereDichNicht.model;


import MenschEargereDichNicht.model.Box;
import MenschEargereDichNicht.model.PlayerColor;
import MenschEargereDichNicht.model.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PokemonTest {

    private Pokemon pokemon;
    private PlayerColor playerColor = PlayerColor.RED;

    @BeforeEach
    public void setUp() {
        // Initialisierung für jeden Testfall
        pokemon = new Pokemon(playerColor, 1, 10);
    }

    @Test
   public void getColor_shouldReturnCorrectColor() {
           assertEquals(playerColor, pokemon.getColor());
       }

       @Test
       public void getBox_shouldReturnNullInitially() {
           assertNull(pokemon.getBox());
       }

       @Test
       public void setBox_shouldSetBoxCorrectly() {
           Box box = new Box(1, 2, 3, 4, 5, 6, 7, 8, 10.0f, 1);
           pokemon.setBox(box);
           assertEquals(box, pokemon.getBox());
       }

       @Test
       public void getBoxNumber_shouldReturnCorrectBoxNumber() {
           assertEquals(10, pokemon.getBoxNumber());
       }

       @Test
       public void setBoxNumber_shouldSetBoxNumberCorrectly() {
           pokemon.setBoxNumber(5);
           assertEquals(5, pokemon.getBoxNumber());
       }

       @Test
       public void setAlive_shouldSetAliveStatusCorrectly() {
           pokemon.setAlive(true);
           assertTrue(pokemon.isAlive());

           pokemon.setAlive(false);
           assertFalse(pokemon.isAlive());
       }

       @Test
       public void isAlive_shouldReturnCorrectStatus() {
           assertFalse(pokemon.isAlive());
       }

       @Test
       public void getIdNumber_shouldReturnCorrectIdNumber() {
           assertEquals(1, pokemon.getIdNumber());
       }

       @Test
       public void isBoxHit_shouldReturnTrueForHit() {
           pokemon.setBox(new Box(10, 20, 30, 40, 50, 60, 70, 80, 10.0f, 1));
           assertTrue(pokemon.isBoxHit(15, 25));
       }

       @Test
       public void isBoxHit_shouldReturnFalseForMiss() {
           pokemon.setBox(new Box(10, 20, 30, 40, 50, 60, 70, 80, 10.0f, 1));
           assertFalse(pokemon.isBoxHit(5, 5));
       }

}