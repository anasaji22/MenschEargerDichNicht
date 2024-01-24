package MenschEargereDichNicht.view;

import MenschEargereDichNicht.model.Box;
import MenschEargereDichNicht.model.RealPlayer;
import controlP5.Button;
import controlP5.Textfield;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Schnittstelle für die Darstellung des Spiels.
 *
 * @author Anas Aji
 * @version 1.0
 */
public interface IView {

    /**
     * Zeichnet das Spielbrett.
     *
     * @param map Die Zuordnung von Feld-IDs zu Feldern.
     */
    void drawBoard(HashMap<Integer, Box> map);

    /**
     * Zeichnet den Hintergrund des Spiels.
     */
    void drawBackground();

    /**
     * Zeichnet die Pokemons der Spieler.
     *
     * @param realPlayers Die Liste der Spieler.
     */
    void drawPokemonOfPlayer(ArrayList<RealPlayer> realPlayers);

    /**
     * Zeichnet den Würfel.
     *
     * @param diceNumber Die gewürfelte Augenzahl.
     */
    void drawDice(int diceNumber);

    /**
     * Gibt den "Roll" Button zurück.
     *
     * @return Der "Roll" Button.
     */
    Button getRollButton();

    /**
     * Zeichnet den Spielstatus in der angegebenen Farbe.
     *
     * @param color Die Farbe des Spielers.
     */
    void drawGameState(String color);

    /**
     * Zeichnet den Bildschirm für den Gewinner.
     *
     * @param realPlayer Der Spieler, der gewonnen hat.
     */
    void drawWinScreen(RealPlayer realPlayer);

    /**
     * Zeichnet das Hauptmenü.
     */
    void drawMenu();

    /**
     * Zeichnet einen verfügbaren Zug für die gegebene Box.
     *
     * @param box Die Box, für die ein verfügbarer Zug angezeigt wird.
     */
    void drawAvailableMove(Box box);

    /**
     * Gibt das Textfeld für den gegebenen Spielerfarbcode zurück.
     *
     * @param color Der Farbcode des Spielers.
     * @return Das entsprechende Textfeld.
     */
    Textfield getTextByColor(String color);

    /**
     * Setzt den Zustand der Menübuttons.
     *
     * @param state Der Zustand, auf den die Buttons gesetzt werden sollen.
     */
    void setMenuButtonsState(boolean state);

    /**
     * Setzt den Zustand der Spielbuttons.
     *
     * @param state Der Zustand, auf den die Buttons gesetzt werden sollen.
     */
    void setGameButtonsState(boolean state);

    /**
     * Setzt den Zustand der Gewinnerbildschirm-Buttons.
     *
     * @param state Der Zustand, auf den die Buttons gesetzt werden sollen.
     */
    void setWinScreenButtonsState(boolean state);

    /**
     * Zeichnet die Namen der Spieler über ihren Pokemons.
     *
     * @param realPlayers Die Liste der Spieler.
     */
    void drawNamesOfPlayers(ArrayList<RealPlayer> realPlayers);

    /**
     * Gibt das Textfeld für den Spielstatus zurück.
     *
     * @return Das Textfeld für den Spielstatus.
     */
    Textfield getGameStateLabel();
}
