package MenschEargereDichNicht.controller;

/**
 * Das Interface IController definiert die Methoden, die ein Controller implementieren muss, um mit dem Spiel zu interagieren.
 * Dazu gehören Methoden zum Setzen des Models und der View, zum Aktualisieren des Spielzustands und zum Handhaben von Benutzereingaben, die in view passieren.
 *  @author Anas Aji
 *  @version 1.0
 */
public interface IController {
    /**
     * Diese Methode sollte aufgerufen werden, wenn der Controller entscheiden muss, was die View anzeigen soll.
     * Der Controller wird eine der drawX()-Methoden der View aufrufen.
     * Der Controller entscheidet durch den Spielstatus (state), was er zeigen soll.
     */
    void nextFrame();
    /**
     * Methode, die aufgerufen werden sollte, wenn der Benutzer auf das Menü klickt.
     */
    void handelUserInputMenu();

    /**
     * Methode, die aufgerufen werden sollte, wenn der Benutzer im Spiel interagiert.
     */
    void handelUserInputGame();
    /**
     * Methode, die aufgerufen werden sollte, wenn die Maustaste gedrückt wird.
     *
     * @param mouseX Die x-Koordinate der Mausposition.
     * @param mouseY Die y-Koordinate der Mausposition.
     */
    void handelMousePressed(int mouseX, int mouseY);
    /**
     * Methode, die aufgerufen werden sollte, wenn die Maustaste losgelassen wird.
     *
     * @param mouseX Die x-Koordinate der Mausposition beim Loslassen der Maustaste.
     * @param mouseY Die y-Koordinate der Mausposition beim Loslassen der Maustaste.
     */
    void handelMouseReleased(int mouseX, int mouseY);

    /**
     * Methode, die aufgerufen werden sollte, wenn der Benutzer auf dem Gewinnscreen interagiert.
     *
     * @param event Das ausgelöste Ereignis auf dem Gewinnscreen.
     */
    void handelUserInputWineScreen(String event);
}
