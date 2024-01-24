package MenschEargereDichNicht.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Das Interface "IModel" definiert die Schnittstelle für das Modell des Spiels "Mensch Ärgere Dich Nicht".
 * Es enthält Methoden, die von der Model-Klasse implementiert werden müssen, um die Spiellogik zu steuern.
 *
 * @author Anas Aji
 * @version 1.0
 */
public interface IModel {

    /**
     * Startet ein neues Spiel, indem das Spielfeld initialisiert und die Spieler vorbereitet werden.
     */
    void newGame();

    /**
     * Gibt den nächsten Spieler in der Reihenfolge zurück.
     *
     * @return Der nächste Spieler in der Reihenfolge.
     */
    PlayerTurn getNextPlayer();

    /**
     * Gibt die Zuordnung von Box-IDs zu Box-Objekten zurück.
     *
     * @return Eine HashMap, die Box-IDs auf Box-Objekte abbildet.
     */
    HashMap<Integer, Box> getBoxMap();

    /**
     * Gibt die Liste der echten Spieler im Spiel zurück.
     *
     * @return Die Liste der echten Spieler.
     */
    ArrayList<RealPlayer> getRealPlayersList();

    /**
     * Überprüft, ob ein Spieler ein Pokemon zu einer bestimmten Box bewegen kann.
     *
     * @param diceNumber   Die geworfene Würfelzahl.
     * @param relatedBox   Die Zielbox.
     * @param pokemon      Das zu bewegende Pokemon.
     * @return True, wenn das Pokemon zu der Box bewegt werden kann; andernfalls False.
     */
    boolean canMovePokemonToBox(int diceNumber, Box relatedBox, Pokemon pokemon);

    /**
     * Bewegt ein Pokemon zu einer bestimmten Box.
     *
     * @param diceNumber   Die geworfene Würfelzahl.
     * @param box          Die Zielbox.
     * @param pokemon      Das zu bewegende Pokemon.
     */
    void movePokemon(int diceNumber, Box box, Pokemon pokemon);

    /**
     * Gibt den aktuellen Spieler zurück.
     *
     * @return Der aktuelle Spieler.
     */
    RealPlayer getCurrentPlayer();

    /**
     * Gibt das Spielfeld des aktuellen Spielers zurück.
     *
     * @return Ein Array von Box-IDs, die das Spielfeld des aktuellen Spielers repräsentieren.
     */
    int[] getCurrentPlayerBoard();

    /**
     * Gibt die Box zurück, die den angegebenen Bildschirmkoordinaten entspricht.
     *
     * @param posX Die x-Koordinate auf dem Bildschirm.
     * @param posY Die y-Koordinate auf dem Bildschirm.
     * @return Die Box, die den Bildschirmkoordinaten entspricht, oder null, wenn keine Box gefunden wurde.
     */
    Box getRelatedBox(int posX, int posY);

    /**
     * Überprüft, ob der aktuelle Spieler mit der gegebenen Würfelzahl spielen kann.
     *
     * @param diceNumber Die geworfene Würfelzahl.
     * @return True, wenn der Spieler spielen kann; andernfalls False.
     */
    boolean canPlayerPlay(int diceNumber);

    /**
     * Setzt den Spielerzug auf einen bestimmten Spieler.
     *
     * @param playerTurn Der Spielerzug, der gesetzt werden soll.
     */
    void setPlayerTurn(PlayerTurn playerTurn);

    /**
     * Überprüft, ob der aktuelle Spieler gewonnen hat.
     *
     * @return True, wenn der Spieler gewonnen hat; andernfalls False.
     */
    boolean checkWin();

    /**
     * Gibt die verfügbare Box für das angegebene Pokemon und die geworfene Würfelzahl zurück.
     *
     * @param diceNumber Die geworfene Würfelzahl.
     * @param pokemon    Das betreffende Pokemon.
     * @return Die verfügbare Box oder null, wenn keine verfügbar ist.
     */
    Box getAvailableBox(int diceNumber, Pokemon pokemon);
}
