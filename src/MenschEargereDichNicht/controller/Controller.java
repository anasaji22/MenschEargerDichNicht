package MenschEargereDichNicht.controller;
import MenschEargereDichNicht.model.IModel;
import MenschEargereDichNicht.model.Model;
import MenschEargereDichNicht.model.Pokemon;
import MenschEargereDichNicht.view.IView;

/**
 * Der Controller ist verantwortlich für die Steuerung des Mensch Ärgere Dich Nicht Spiels.
 * Er koordiniert die Interaktionen zwischen dem Model (Spiellogik) und der View (Benutzeroberfläche).
 * Der Controller verwaltet den aktuellen Spielzustand, einschließlich Menü, Spielinitialisierung, Warten auf das Würfeln,
 * Warten auf das Bewegen der Spielfigur und den Gewinnzustand. Zudem handhabt er Benutzereingaben, aktualisiert den Spielzustand
 * und steuert die Darstellung im Spiel.
 * Beispiel von der erzeugung eines kontrolles
 * <p>
 *  {@code Controller controller = new Controller()}
 *  @author Anas Aji
 *  @version 1.0
 */
public class Controller implements IController {
    private IModel model;
    private IView view;
    private Pokemon selectedPokemon;
    private int selectedNumber;
    private GameState state;

    /**
     * Erzeugt ein neues Controller-Objekt mit dem aktuellen Spielzustand "Menü".
     * <p>
     * Beispiel :
     * {@code Controller controller = new Controller()}
     */
    public Controller() {
        this.state = GameState.MENU;
    }

    /**
     * Setzt das Model, das in diesem Controller verwendet werden soll.
     * <p>
     * {@code Controller controller = new Controller()}
     * <p>
     *  {@code Model model = new Model(width, height)}
     * <p>
     * {@code controller.setModel()}
     * @param model Das zu verwendende Model.
     */
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * Setzt die View, die in diesem Controller verwendet werden soll.
     *<p>
     * {@code Controller controller = new Controller()}
     * <p>
     *  {@code Model model = new Model(width, height)}
     * <p>
     * {@code controller.setView();}
     * @param view Die zu verwendende View.
     */
    public void setView(IView view) {
        this.view = view;
    }

    /**
     * Ruft die Zeichenmethoden der View auf, abhängig vom aktuellen Spielzustand.
     * Während des Spiels aktualisiert diese Methode auch die Position aller Figuren.
     *
     * Im "MENU"-Zustand werden die Menüelemente gezeichnet und die Schaltflächen für Spielbuttons und Gewinnbildschirm-Buttons deaktiviert,
     * während die Menü-Schaltflächen aktiviert werden.
     *
     * Im "GAME_INIT"-Zustand werden die Schaltflächen für den Spielgewinn und das Menü deaktiviert, während die Schaltflächen für den
     * Spielzug aktiviert werden. Ein neues Spiel wird initialisiert, der Hintergrund, das Spielbrett und die Spielerfiguren werden gezeichnet.
     * Anschließend wechselt der Zustand zum "WAIT_TO_ROLL_DICE"-Zustand.
     *
     * Im "WAIT_TO_ROLL_DICE"-Zustand wird die Würfel-Schaltfläche entsperrt, das Spielbrett und die Spielerfiguren werden gezeichnet.
     *
     * Im "WAIT_TO_MOVE_FIGURE"-Zustand wird die Würfel-Schaltfläche gesperrt, das Spielbrett, die Spielerfiguren und die möglichen
     * Züge für die ausgewählte Figur werden gezeichnet.
     *
     * Im "WINE"-Zustand wird der Gewinnscreen für den aktuellen Spieler gezeichnet, und die Schaltflächen für den Spielzug und das Menü werden deaktiviert,
     * während die Schaltflächen für den Neustart und den Menüzugang aktiviert werden.
     * <p>
     *  {@code Controller controller = new Controller();}
     *  <p>
     *  {@code controller.nextFrame();}
     *  <p>
     *  wird von view aufgerufen.
     *
     */

    @Override
    public void nextFrame() {
        switch (state) {
            case MENU:
                view.drawMenu();
                view.setWinScreenButtonsState(false);
                view.setGameButtonsState(false);
                view.setMenuButtonsState(true);
                break;
            case GAME_INIT:
                model.newGame();
                view.drawBackground();
                view.drawBoard(model.getBoxMap());
                view.drawPokemonOfPlayer(model.getRealPlayersList());
                view.drawNamesOfPlayers(model.getRealPlayersList());
                state = GameState.WAIT_TO_ROLL_DICE;
                view.setMenuButtonsState(false);
                view.setWinScreenButtonsState(false);
                view.setGameButtonsState(true);
                break;
            case WAIT_TO_ROLL_DICE:
                lockRollButton(false);
                view.drawBoard(model.getBoxMap());
                view.drawPokemonOfPlayer(model.getRealPlayersList());
                break;
            case WAIT_TO_MOVE_FIGURE:
                lockRollButton(true);
                view.drawBoard(model.getBoxMap());
                view.drawPokemonOfPlayer(model.getRealPlayersList());
                view.drawAvailableMove(model.getAvailableBox(model.getCurrentPlayer().getDiceNumber(), selectedPokemon));
                break;
            case WIN:
                view.drawWinScreen(model.getCurrentPlayer());
                view.setGameButtonsState(false);
                view.setMenuButtonsState(false);
                view.setWinScreenButtonsState(true);
                break;

        }


    }


    private void lockRollButton(boolean enabled) {
        view.getRollButton().setLock(enabled);
    }

    /**
     * Handhabt die Benutzereingabe während des Spiels. Der aktuelle Spieler würfelt und die geworfene Zahl wird angezeigt.
     * Nach einer kurzen Verzögerung wird überprüft, ob der Spieler spielen kann. Falls nicht, wechselt das Spiel in den Zustand "WAIT_TO_ROLL_DICE".
     * Andernfalls wechselt es in den Zustand "WAIT_TO_MOVE_FIGURE", und die Anzeige wird entsprechend aktualisiert.
     * <p>
     *  {@code Controller controller = new Controller();}
     *  <p>
     *  {@code controller.handelUserInput();}
     *
     */
    @Override
    public void handelUserInputGame() {
        selectedNumber = model.getCurrentPlayer().rollTheDice();
        view.drawDice(selectedNumber);

        if (!model.canPlayerPlay(selectedNumber)) {
            state = GameState.WAIT_TO_ROLL_DICE;
            model.setPlayerTurn(model.getNextPlayer());
            view.drawGameState(view.getTextByColor(model.getCurrentPlayer().getColor().name()).getText() + " :: Roll Dice");
        } else {
            state = GameState.WAIT_TO_MOVE_FIGURE;
            view.drawGameState(view.getTextByColor(model.getCurrentPlayer().getColor().name()).getText() + " :: Move Figure");
        }

    }
    /**
     * Handelt die Benutzereingabe, wenn die Maustaste gedrückt wird, während sich das Spiel im Zustand "WAIT_TO_MOVE_FIGURE" befindet.
     * Überprüft, ob die Mausposition auf eine Spielfigur zeigt, und wählt diese Figur aus, wenn sie getroffen wird.
     *
     * @param mouseX Die x-Koordinate der Mausposition.
     * @param mouseY Die y-Koordinate der Mausposition.
     *   <p>
     *  {@code Controller controller = new Controller();}
     *  <p>
     *  {@code controller handelMousePressed(int mouseX, int mouseY);}
     *   <p>
     *   wird von view aufgerufen.
     *
     */
    @Override
    public void handelMousePressed(int mouseX, int mouseY) {
        if (state == GameState.WAIT_TO_MOVE_FIGURE) {
            var player = model.getCurrentPlayer();
            player.getListOfPokemons().forEach(pokemon -> {
                if (pokemon.isBoxHit(mouseX, mouseY)) {
                    selectedPokemon = pokemon;
                }
            });
        }
    }

    /**
     * Handhabt die Benutzereingabe, wenn die Maustaste losgelassen wird, während sich das Spiel im Zustand "WAIT_TO_MOVE_FIGURE" befindet.
     * Überprüft, ob die ausgewählte Spielfigur auf ein Spielfeld (Box) verschoben werden kann und führt gegebenenfalls die Verschiebung durch.
     * Aktualisiert den Spielzustand basierend auf dem Erfolg der Aktion. Falls ein Spieler gewonnen hat, wechselt das Spiel in den Zustand "WINE".
     * Andernfalls wechselt es in den Zustand "WAIT_TO_ROLL_DICE" und aktualisiert die Anzeige entsprechend.
     *
     * @param mouseX Die x-Koordinate der Mausposition beim Loslassen der Maustaste.
     * @param mouseY Die y-Koordinate der Mausposition beim Loslassen der Maustaste.
     *   <p>
     *  {@code Controller controller = new Controller();}
     *  <p>
     *  {@code controller.handelMouseReleased(int mouseX, int mouseY);}
     *   <p>
     *  wird von view aufgerufen.
     */
    @Override
    public void handelMouseReleased(int mouseX, int mouseY) {
        var box = model.getRelatedBox(mouseX, mouseY);
        //when the player related a pokemon and put it in available box in his board
        if (state == GameState.WAIT_TO_MOVE_FIGURE
                && selectedPokemon != null
                && box != null
        ) {

            if (model.canMovePokemonToBox(selectedNumber, box, selectedPokemon)) {
                model.movePokemon(selectedNumber, box, selectedPokemon);
                if (model.checkWin()) {state = GameState.WIN;}
                else {
                    state = GameState.WAIT_TO_ROLL_DICE;
                    model.setPlayerTurn(model.getNextPlayer());
                    view.drawGameState(view.getTextByColor(model.getCurrentPlayer().getColor().name()).getText() + " :: Roll Dice");
                }
            }
        }
        selectedPokemon = null;
    }

    /**
     * Handhabt die Benutzereingabe auf dem Gewinnscreen, basierend auf dem ausgelösten Ereignis.
     * Aktualisiert den Spielzustand entsprechend dem ausgewählten Ereignis. Bei Auswahl von "New Game" wechselt das Spiel
     * in den Zustand "GAME_INIT" und aktualisiert die Anzeige für den Start des Spiels. Bei Auswahl von "Menu" wechselt
     * das Spiel in den Zustand "MENU".
     *
     * @param event Das ausgelöste Ereignis auf dem Gewinnscreen ("New Game" oder "Menu").
     *               <p>
     * {@code Controller controller = new Controller();}
     * <p>
     * {@code controller.handelUserInputWineScreen(String event);}
     *  <p>
     * wird von view aufgerufen.
     */
    @Override
    public void handelUserInputWineScreen(String event) {
        switch (event) {
            case "New Game":
                state = GameState.GAME_INIT;
                view.getGameStateLabel().setText(view.getTextByColor("Blue").getText() + " :: Roll Dice");
                break;
            case "Menu":
                state = GameState.MENU;
                break;
        }
    }

    /**
     * Handhabt die Benutzereingabe im Menü, wenn das Spiel gestartet werden soll.
     * Aktualisiert den Spielzustand auf "GAME_INIT", um ein neues Spiel zu initialisieren.
     * {@code Controller controller = new Controller();}
     * <p>
     * {@code controller.handelUserInputMenu();}
     *  <p>
     *  wird von view aufgerufen.
     */
    @Override
    public void handelUserInputMenu() {
        state = GameState.GAME_INIT;
    }

}
