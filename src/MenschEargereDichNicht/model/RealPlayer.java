package MenschEargereDichNicht.model;


import java.util.List;

/**
 * Die Klasse "RealPlayer" repräsentiert einen realen Spieler im Spiel "Mensch Ärgere Dich Nicht".
 * Ein Spieler hat eine Liste von Pokemon-Figuren, eine aktuelle Würfelzahl und eine Spielerfarbe.
 *
 * {@code RealPlayer rP = new RealPlayer(PlayerColor.BLUE);}
 *
 *
 * color ist vom Typ PlayerColor, welcher als ein Enum in der Package definiert ist und die Werte {@code BLUE, RED, PURPLE, ORANGE} haben kann.
 *
 * Die Liste von Pokemon-Figuren wird bei der Erzeugung mit einem Modell von Pokemon-Figuren gefüllt.
 *
 * Die Variable "diceNumber" ändert sich jedes Mal, wenn der RealPlayer würfelt, und wird auf den Wert gesetzt, den die Methode {@link RealPlayer#rollTheDice()} bestimmt.
 *  @author Anas Aji
 *  @version 1.0
 */
public class RealPlayer {
    private List<Pokemon> listOfPokemons;
    private int diceNumber;
    private PlayerColor color;

    /**
     * Konstruktor der Klasse "RealPlayer" für die Initialisierung eines Spielers mit der gegebenen Spielerfarbe.
     *
     * Verwendung:
     *
     * - {@code RealPlayer rP = new RealPlayer(PlayerColor.BLUE);}
     *
     * @param color Die Spielerfarbe des neuen Spielers.
     */

    public RealPlayer(PlayerColor color) {
        this.color = color;
    }


    /**
     * Gibt die Liste von Pokemon-Figuren zurück, die dem Spieler gehören.
     *
     * Verwendung:
     *
     * - {@code rP.getListOfPokemons()}
     *
     * @return Die Liste von Pokemon-Figuren des Spielers.
     */
    public List<Pokemon> getListOfPokemons() {
        return listOfPokemons;
    }

    /**
     * Setzt die Liste von Pokemon-Figuren des Spielers auf die gegebene Liste.
     * Beispiel für die Verwendung:
     *
     * - {@code }List<Pokemon> listOfPokemon = new ArrayList<>();
     *
     * - {@code }Pokemon p = new Pokemon(PlayerColor color, int idNummer, int boxNumber);
     *
     * - {@code listOfPokemon.add(p); }
     *
     * - {@code rP.setListOfPokemons(listOfPokemon)}
     *
     * @param listOfPokemons Die neue Liste von Pokemon-Figuren für den Spieler.
     */

    public void setListOfPokemons(List<Pokemon> listOfPokemons) {
        this.listOfPokemons = listOfPokemons;
    }
    /**
     * Würfelt mit einem sechsseitigen Würfel und gibt die geworfene Zahl zurück.
     *
     * Verwendung:
     *
     * - {@code selectedNumber = rp.rollTheDice();}
     *
     * @return Die geworfene Würfelzahl.
     */
    public int rollTheDice() {
        diceNumber = (int) (Math.random() * 6) + 1;
        return diceNumber;
    }
    /**
     * Gibt die aktuelle Würfelzahl des Spielers zurück.
     *
     * Verwendung:
     *
     * - {@code rP.getDiceNumber();}
     *
     * @return Die aktuelle Würfelzahl des Spielers.
     */
    public int getDiceNumber() {
        return diceNumber;
    }

    /**
     * Gibt die Spielerfarbe des Spielers zurück.
     *
     * Verwendung:
     *
     * - {@code rp.getColor();}
     *
     * @return Die Spielerfarbe des Spielers.
     */
    public PlayerColor getColor() {
        return color;
    }

}
