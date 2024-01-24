import MenschEargereDichNicht.controller.Controller;
import MenschEargereDichNicht.model.Model;
import MenschEargereDichNicht.view.View;
import processing.core.PApplet;
/**
 * Die Hauptklasse für das Mensch-Ärgere-Dich-Nicht-Spiel.
 */
public final class Main {
    /**
     * Der Einstiegspunkt des Programms.
     *
     * @param args Die Kommandozeilenargumente (werden nicht verwendet).
     */
    public static void main(String[] args) {
        final float  GAME_WIDTH = 1000, GAME_HEIGHT = 1000;
        var model = new Model(GAME_WIDTH, GAME_HEIGHT);
        var controller = new Controller();
        var view = new View(GAME_WIDTH,  GAME_HEIGHT);
        controller.setModel(model);
        controller.setView(view);
        view.setController(controller);
        PApplet.runSketch(new String[]{"MenschEargereDichNichtView"}, view);
    }
}
