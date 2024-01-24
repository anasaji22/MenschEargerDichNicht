package MenschEargereDichNicht.controller;
/**
 * Enum für die verschiedenen Spielzustände, die der Controller verwalten kann.
 * Enthält Zustände wie Menü, Spielinitialisierung, Warten auf das Würfeln, Warten auf das Bewegen der Spielfigur und den Gewinnzustand.
 *  <p>
 *  Beispiel :
 *      GameState state;
 *  @author Anas Aji
 *  @version 1.0
 */
 enum GameState {
    MENU, GAME_INIT, WAIT_TO_ROLL_DICE, WAIT_TO_MOVE_FIGURE, WIN,
}
