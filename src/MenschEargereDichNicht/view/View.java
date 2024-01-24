package MenschEargereDichNicht.view;

import MenschEargereDichNicht.controller.IController;
import MenschEargereDichNicht.model.Box;
import MenschEargereDichNicht.model.RealPlayer;
import controlP5.*;
import controlP5.Button;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 * Die Klasse "View" repräsentiert die grafische Benutzeroberfläche (GUI) für das Spiel "Mensch Ärgere Dich Nicht".
 * Sie verwaltet die Darstellung des Spielfelds, der Spieler, der Würfel, sowie der Menü- und Gewinnscreens.
 * Die Klasse ist eine Implementierung der Schnittstelle {@link IView}, um die Interaktionen mit dem Controller zu ermöglichen.
 * <p>
 * Verwendung:
 * - Erstellen Sie eine Instanz von View, um die GUI zu initialisieren.
 * - Setzen Sie den zugehörigen Controller mit {@link View#setController(IController)}.
 * - Verwenden Sie die verschiedenen Methoden, um die GUI-Elemente zu steuern und darzustellen.
 * <p>
 * Methoden:
 * - {@link View#setup()}: Initialisiert die GUI-Elemente wie Buttons, Textfelder und Bilder.
 * <p>
 * - {@link View#draw()}: Zeichnet den aktuellen Zustand der GUI auf dem Bildschirm.
 *  <p>
 * - {@link View#mousePressed()}: Wird aufgerufen, wenn die Maustaste gedrückt wird. Leitet die Mausaktion an den Controller weiter.
 *       <p>
 * - {@link View#mouseReleased()}: Wird aufgerufen, wenn die Maustaste losgelassen wird. Leitet die Mausaktion an den Controller weiter.
 * <p>
 * - {@link View#drawBoard(HashMap)}: Zeichnet das Spielfeld basierend auf der übergebenen Map von Boxen.
 *<p>
 * - {@link View#drawPokemonOfPlayer(ArrayList)}: Zeichnet die Pokémon der Spieler auf dem Spielfeld.
 * <p>
 * - {@link View#drawGameState(String)}: Zeigt den aktuellen Zustand des Spiels an (z.B., welcher Spieler am Zug ist).
 *  <p>
 * - {@link View#drawDice(int)}: Zeigt den gewürfelten Wert auf dem Bildschirm an.
 *  <p>
 * - {@link View#drawMenu()}: Zeigt das Menü auf dem Bildschirm an.
 *          <p>
 * - {@link View#drawWinScreen(RealPlayer)}: Zeigt den Gewinnscreen für einen Spieler an.
 *          <p>
 * - {@link View#getRollButton()}: Gibt den Roll-Button zurück.
 *  <p>
 * - {@link View#setMenuButtonsState(boolean)}: Setzt den Zustand der Menü-Buttons (Sichtbarkeit).
 * <p>
 * - {@link View#setGameButtonsState(boolean)}: Setzt den Zustand der Spiel-Buttons (Sichtbarkeit).
 * <p>
 * - {@link View#setWinScreenButtonsState(boolean)}: Setzt den Zustand der Gewinnscreen-Buttons (Sichtbarkeit).
 * <p>
 * - {@link View#getTextByColor(String)}: Gibt das Textfeld für den Spieler entsprechend seiner Farbe zurück.
 * <p>
 * - {@link View#drawNamesOfPlayers(ArrayList)}: Zeichnet die Namen der Spieler auf dem Spielfeld.
 * <p>
 * - {@link View#drawAvailableMove(Box)}: Zeichnet eine hervorgehobene Fläche für mögliche Züge auf dem Spielfeld.
 *   <p>
 * - {@link View#drawBackground()}: Zeichnet den Hintergrund der GUI.
 * <p>
 * - {@link View#getPImageByColor(String)}: Gibt das Bild für einen Spieler entsprechend seiner Farbe zurück.
 * <p>
 * - {@link View#getDiceByDiceNumber(int)}: Gibt das Bild für einen Würfelwert zurück.
 * <p>
 * - {@link View#getGameStateLabel()}: Gibt das Textfeld für den aktuellen Spielzustand zurück.
 * <p>
 * <p>
 * Beispiel:
 * <p>
 * <p>
 * - {@code View gameView = new View(WIDTH, HEIGHT);}
 * <p>
 * - {@code gameView.setup();}
 * <p>
 * - {@code gameView.setController(controller);}
 * <p>
 * - {@code gameView.draw();}
 * <p>
 * - {@code gameView.mousePressed();}
 * <p>
 * - {@code gameView.drawBoard(boxMap);}
 * <p>
 * - {@code gameView.drawPokemonOfPlayer(realPlayers);}
 * <p>
 * - {@code gameView.drawGameState("RED's Turn");}
 * <p>
 * - {@code gameView.drawDice(4);}
 * <p>
 * - {@code gameView.drawMenu();}
 * <p>
 * - {@code gameView.drawWinScreen(winningPlayer);}
 * <p>
 * - {@code gameView.getRollButton();}
 * <p>
 *
 * @author Anas Aji
 * @version 1.0
 */


public class View extends PApplet implements IView {
    private IController controller;
    protected float width, height;
    private ControlP5 cp5;
    private Button rollButton, newGameButton, playButton, menuButton, infoButton;
    private Textfield gameStateLabel;
    private Textfield playerRedLabel, playerPurpleLabel, playerBlueLabel, playerOrangeLabel;
    private CallbackListener callbackListenerMenu, callbackListenerPlay, callbackListenerNewGame;
    private PFont font;
    private CallbackListener callbackListenerInfo;
    private PImage infoImage,menuButtonImage, newGameImage, menu2Image, playButtonImage, purpleImage, orangeImage, blueImage, redImage, backgroundImage, dice1, dice2, dice3, dice4, dice5, dice6, menuImage;
    /**
     * Erzeugt eine neue Instanz der Klasse "View"mit den angegebenen Breiten- und Höhenabmessungen.
     * Die Mehode initialisiert die Breite und Hhe der View sowie die Größe des Bildschirms.
     *
     * @param width  Die Breitenabmessung der View.
     * @param height Die Höhenabmessung der View.
     */
    public View(float width, float height) {
        this.width = width;
        this.height = height;
        setSize((int) width, (int) height);
    }
    /**
     * Setzt den Controller für die View, um Benuzereingaben zu handhaben.
     *
     * @param controller Der Controller, der die Benutzereingaben für die View handhaben soll.
     */

    public void setController(IController controller) {
        this.controller = controller;
    }
    /**
     * Initialisiert die View, indem alle notwendigen Bilder und Buttons geladen werden.
     * Diese Methode wird einmal am Anfang des Programs aufgerufen.
     * Laden alle benötigten Ressourcen wie Bilder und Schriftarten hier.
     * Initialisiert außerdem die Buttons für das Hauptmenü, das Spiel und den Gewinnbildschirm.
     * <p>
     * wird von den Main Klasse verwendet.
     */

    public void setup() {
        redImage = loadImage("images/Red.png");
        blueImage = loadImage("images/Blue.png");
        orangeImage = loadImage("images/Orange.png");
        purpleImage = loadImage("images/Purple.png");
        backgroundImage = loadImage("images/OIG.jpg");
        menuImage = loadImage("images/Menu.jpg");
        menu2Image = loadImage("images/menu2.png");
        playButtonImage = loadImage("images/playButton.png");
        newGameImage = loadImage("images/newGame.png");
        dice1 = loadImage("images/dice1.png");
        dice2 = loadImage("images/dice2.png");
        dice3 = loadImage("images/dice3.png");
        dice4 = loadImage("images/dice4.png");
        dice5 = loadImage("images/dice5.png");
        dice6 = loadImage("images/dice6.png");
        infoImage = loadImage("images/Info.png");
        menuButtonImage = loadImage("images/menuButton.png");
        font = createFont("FreeSans Bold", 20, true);
        callbackListenerInfo = new CallbackListener() {
            @Override
            public void controlEvent(CallbackEvent callbackEvent) {
                if (callbackEvent.getAction() == ControlP5.ACTION_RELEASE) {
                    String message = "To move a Pokemon, use drag and drop. Place the mouse over the Pokemon, press the left mouse button, and move the mouse to the desired location. \n Note: You can only move the Pokemon to the yellow-highlighted circles.";
                    javax.swing.JOptionPane.showMessageDialog(null, message, "Game Info", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };
        initMenuButtons();
        initGameButtons();
        initWinButtons();

    }
    /**
     * Initialisiert die Buttons für den Gewnnbildschirm im Menü.
     * Hier werden die Buttons für "New Game" und "Menu" erstellt und entsprechende CallbackListener hinzugefügt.
     * Die Bilder für die Buttons werden ebenfalls geladen und angepasst.
     * <p>
     * wird von den setup(){@link View#setup()} verwendet.
     */
    private void initWinButtons() {
        cp5 = new ControlP5(this);
        callbackListenerNewGame = new CallbackListener() {
            @Override
            public void controlEvent(CallbackEvent callbackEvent) {
                if (callbackEvent.getAction() == ControlP5.ACTION_RELEASE) {
                    controller.handelUserInputWineScreen("New Game");
                }
            }
        };
        callbackListenerMenu = new CallbackListener() {
            @Override
            public void controlEvent(CallbackEvent callbackEvent) {
                if (callbackEvent.getAction() == ControlP5.ACTION_RELEASE) {
                    controller.handelUserInputWineScreen("Menu");
                }
            }
        };
        newGameImage.resize(350, 130);
        newGameButton = cp5.addButton("New Game")
                .setPosition(325, 220)
                .setImage(newGameImage)
                .setSize(350, 130)
                .setCaptionLabel("New Game")
                .addCallback(callbackListenerNewGame)
                .setLock(false)
                .setVisible(false)
        ;
        menuButtonImage.resize(350, 130);
        menuButton = cp5.addButton("Menu")
                .setPosition(325, 330)
                .setImage(menuButtonImage)
                .setSize(350, 130)
                .setCaptionLabel("New Game")
                .addCallback(callbackListenerMenu)
                .setLock(false)
                .setVisible(false)
        ;

        infoImage.resize(40,40);
        infoButton = cp5.addButton("Info")
                .setPosition(width - 70, 10)
                .setImage(infoImage)
                .setSize(40, 40)
                .addCallback(callbackListenerInfo)
                .setVisible(false);
        ;

    }
    /**
     * Initialisiert die Buttons für das laufende Spiel.
     * Hier werden die Buttons für "Roll" und "Info" erstellt und entsprechende CallbackListener hinzugefügt.
     * Die Biler für die Buttons werden ebenfalls gelaen und angepasst.
     * Wird von der Methode setup() {@link View#setup()} verwendet.
     */
    private void initGameButtons() {
        cp5 = new ControlP5(this);
        CallbackListener callbackListenerRoll = new CallbackListener() {
            @Override
            public void controlEvent(CallbackEvent callbackEvent) {
                if (callbackEvent.getAction() == ControlP5.ACTION_RELEASE) {
                    if (!rollButton.isLock()) {
                        controller.handelUserInputGame();
                    }
                }


            }
        };
        infoImage.resize(40,40);
        infoButton = cp5.addButton("Info")
                .setPosition(width - 70, 10)
                .setImage(infoImage)
                .setSize(40, 40)
                .addCallback(callbackListenerInfo)
                .setVisible(false);
        ;
        rollButton = cp5.addButton("Roll")
                .setPosition(830, height - 70)
                .setSize(60, 60)
                .setColorBackground(color(92, 62, 62))
                .setColorForeground(color(82, 52, 52))
                .addCallback(callbackListenerRoll)
                .setLock(true)
                .setVisible(false)
        ;
        gameStateLabel = cp5.addTextfield("")
                .setPosition(100, height - 70)
                .setColorForeground(color(255, 255, 255))
                .setFont(createFont("FreeSans Bold", 20))
                .setText(getTextByColor("BLUE").getText() + " :: Roll Dice")
                .setColorBackground(color(92, 62, 62))
                .setColorForeground(color(92, 62, 62))
                .setSize(400, 60)
                .setLock(true)
                .setVisible(false)
        ;
    }

    /**
     * Initialisiert die Buttons für das Hauptmenü.
     * Hier werden die Buttons für "Play" und die Textfelder für die Spieler-Namen erstelt und entsprechende CallbacListener hinzugefügt.
     * Die Bilder für die Buttos werden ebenfalls geladen und angepasst.
     * Wird von der Methode setup() {@link View#setup()} verwendet.
     */
    private void initMenuButtons() {
        cp5 = new ControlP5(this);
        playButtonImage.resize(300, 70);
        int x = 325;
        int y = 220;
        callbackListenerPlay = new CallbackListener() {
            @Override
            public void controlEvent(CallbackEvent callbackEvent) {
                if (callbackEvent.getAction() == ControlP5.ACTION_RELEASE) {
                    controller.handelUserInputMenu();
                }
            }
        };

        playButton = cp5.addButton("Play")
                .setPosition(x, y)
                .setImage(playButtonImage)
                .setSize(300, 70)
                .setCaptionLabel("Play")
                .addCallback(callbackListenerPlay)
                .setLock(false)
                .setVisible(false)
        ;
        playerBlueLabel = cp5.addTextfield("Name of blue Player")
                .setPosition(x + 10, y + 75)
                .setColorBackground(color(92, 62, 62))
                .setColorForeground(color(92, 62, 62))
                .setText("Blue player")
                .setColorCaptionLabel(color(0))
                .setFont(createFont("FreeSans Bold", 15))
                .setSize(300, 40)
                .setVisible(false)
        ;
        playerRedLabel = cp5.addTextfield("Name of red Player")
                .setPosition(x + 10, y + 150)
                .setColorBackground(color(92, 62, 62))
                .setColorForeground(color(92, 62, 62))
                .setColorCaptionLabel(color(0))
                .setText("Red player")
                .setFont(createFont("FreeSans Bold", 15))
                .setSize(300, 30)
                .setVisible(false)
        ;
        playerPurpleLabel = cp5.addTextfield("Name of purple Player")
                .setPosition(x + 10, y + 225)
                .setColorBackground(color(92, 62, 62))
                .setColorForeground(color(92, 62, 62))
                .setColorCaptionLabel(color(0))
                .setText("Purple player")
                .setFont(createFont("FreeSans Bold", 15))
                .setSize(300, 30)
                .setVisible(false)
        ;
        playerOrangeLabel = cp5.addTextfield("Name of orange Player")
                .setPosition(x + 10, y + 300)
                .setColorBackground(color(92, 62, 62))
                .setColorForeground(color(92, 62, 62))
                .setColorCaptionLabel(color(0))
                .setText("Orange player")
                .setFont(createFont("FreeSans Bold", 15))
                .setSize(300, 30)
                .setVisible(false)
        ;
        infoImage.resize(40,40);
        infoButton = cp5.addButton("Info")
                .setPosition(width - 70, 10)
                .setImage(infoImage)
                .setSize(40, 40)
                .addCallback(callbackListenerInfo)
                .setVisible(false);
        ;
    }
    /**
     * Setzt den Sichtbarkeitszstand der Menübuttons basierend auf dem übergebenen Zustand.
     *
     * @param state Der gewünschte Sichtbarkeitszustand (true fur sichtbar, false für unsichtbar).
     *  <p>
     *  wird von der Controller{@link MenschEargereDichNicht.controller.Controller} benutzt.
     */
    @Override
    public void setMenuButtonsState(boolean state) {
        playButton.setVisible(state);
        playerBlueLabel.setVisible(state);
        playerRedLabel.setVisible(state);
        playerPurpleLabel.setVisible(state);
        playerOrangeLabel.setVisible(state);
        infoButton.setVisible(state);

    }
    /**
     * Setzt den Sichtbarkeitszustand der Spielbuttons basierend auf dem übergebenen Zustand.
     *
     * @param state Der gewünschte Sichtbarkeitszustand (true für sichtbar, false für unsichtbar).
     * <p>
     * wird von der Controller{@link MenschEargereDichNicht.controller.Controller} benutzt.
     */
    @Override
    public void setGameButtonsState(boolean state) {
        rollButton.setVisible(state);
        gameStateLabel.setVisible(state);
        infoButton.setVisible(state);
    }
    /**
     * Setzt den Sichtbarkeitszustand der Buttons im Gewinnbildschirm basierend auf dem übergebenen Zustand.
     *
     * @param state Der gewünschte Sichtbarkeitszustand (true für sichtbar, false für unsichtbar).
     * <p>
     * wird von der Controller{@link MenschEargereDichNicht.controller.Controller} benutzt.
     */
    @Override
    public void setWinScreenButtonsState(boolean state) {
        newGameButton.setVisible(state);
        menuButton.setVisible(state);
        infoButton.setVisible(state);
    }
    /**
     * Wird aufgerufen, wenn die Maustaste gedrückt wird. Ruft die Methode {@link IController#handelMousePressed(int, int)} des Controllers auf.
     */

    public void mousePressed() {
        controller.handelMousePressed(mouseX, mouseY);
    }
    /**
     * Wird aufgerufen, wenn die Maustaste losgelassen wird. Ruft die Methode {@link IController#handelMouseReleased(int, int)} des Controllers auf.
     */

    public void mouseReleased() {
        controller.handelMouseReleased(mouseX, mouseY);
    }

    /**
     * Wird aufgerufen, um das nächste Frame zu zeichnen. Ruft die Methode {@link IController#nextFrame()} des Controllers auf.
     */
    public void draw() {
        controller.nextFrame();
    }

    /**
     * Zeichnet das Spielbrett basierend auf der bereitgestellten Map von Boxen.
     * Jede Box wird mit ihrer entsprechenden Farbe gezeichnet.
     *
     * @param map Eine Map, die die Boxen des Spiels enthält.
     *            <p>
     * Wird von der Controller{@link MenschEargereDichNicht.controller.Controller} benutzt.
     *
     */

    public void drawBoard(HashMap<Integer, Box> map) {
        for (Map.Entry<Integer, Box> entry : map.entrySet()) {
            Box b = entry.getValue();
            Integer key = entry.getKey();
            int color = getColorForBox(key);
            fill(color);
            ellipse(b.x1() + (b.size() / 2), b.y1() + (b.size() / 2), (width - 40) / 13, (height - 40) / 13);
        }
    }
    /**
     * Gibt die Farbe für eine Box basierend auf dem Schlüssel zurück.
     *
     * @param key Der Schlüssel der Box.
     * @return Die Farbe der Box.
     */
    private int getColorForBox(int key) {
        if (Arrays.asList(14, 15, 27, 28, 66, 80, 81, 82, 83).contains(key)) {
            return color(255, 165, 0);
        } else if (Arrays.asList(23, 24, 36, 37, 20, 32, 45, 58, 71).contains(key)) {
            return color(135, 206, 235);
        } else if (Arrays.asList(85, 86, 87, 88, 102, 140, 141, 153, 154).contains(key)) {
            return color(220, 20, 60);
        } else if (Arrays.asList(97, 110, 123, 136, 148, 131, 132, 144, 145).contains(key)) {
            return color(147, 112, 219);
        } else {
            return color(255);
        }
    }
    /**
     * Zeichnet den Bildschirm für den Gewinner.
     *
     * @param realPlayer Der Spieler, der gewonnen hat.
     *                              <p>
     * Wird von der Controller{@link MenschEargereDichNicht.controller.Controller} benutzt.
     */
    @Override
    public void drawWinScreen(RealPlayer realPlayer) {
        System.out.println("----draw-Winn----");
        cp5 = new ControlP5(this);
        float x = (width / 2);
        float y = height / 2;
        float w = 90;
        float h = 90;
        float yText = 490;

        textFont(font);
        fill(255);
        image(menuImage, 0, 0, width, height);
        image(menu2Image, 100, 50);
        textSize(30);
        fill(gettextColorByColor(realPlayer.getColor().name()));
//        textAlign(CENTER, CENTER);
        yText = yText + sin((int) (frameCount * 0.10)) * 5;
        text(getTextByColor(realPlayer.getColor().name()).getText() + " Won", 350, yText);

        image(getPImageByColor(realPlayer.getColor().name()), x, y, w, h);
        image(getPImageByColor(realPlayer.getColor().name()), x - 100, y, w, h);
        image(getPImageByColor(realPlayer.getColor().name()), x - 200, y, w, h);
        image(getPImageByColor(realPlayer.getColor().name()), x + 100, y, w, h);
    }
    /**
     * Gibt die Textfarbe für den Win Screen basierend auf der Spielerfarbe zurück.
     *
     * @param color Die Spielerfarbe als String.
     * @return Die entsprechende Textfarbe.
     *     <p>
     * Wird von der Controller{@link MenschEargereDichNicht.controller.Controller} benutzt.
     */
    private int gettextColorByColor(String color) {
        return Map.of(
                "BLUE",color(0, 25, 99),
                "RED",color(182, 22, 14),
                "PURPLE",color(84, 0, 252),
                "ORANGE",color(253, 93, 0)
        ).get(color);
    }
    /**
     * Zeichnet den Hintergrund des Spiels.
     *     <p>
     * Wird von der Controller{@link MenschEargereDichNicht.controller.Controller} benutzt.
     */
    public void drawBackground() {
        image(backgroundImage, 0, 0, width, height);
        fill(color(180, 180, 180));
        textSize(30);
        strokeWeight(4);
        fill(0);
        noStroke();
        text("info", width - 130, 40);
    }

    /**
     * Zeichnet die Pokemon der Spieler auf dem Spielfeld.
     * @param realPlayers Die Liste der RealPlayer, deren Pokemon gezeichnet werden sollen.
     *  <p>
     *  Wird von der Controller{@link MenschEargereDichNicht.controller.Controller} benutzt.
     */
    @Override
    public void drawPokemonOfPlayer(ArrayList<RealPlayer> realPlayers) {
        realPlayers.stream().flatMap(p -> p.getListOfPokemons().stream()).forEach(po ->
                image(getPImageByColor(po.getColor().name()), po.getBox().x1(), po.getBox().y1(), 70, 70)
        );
    }
    /**
     * Gibt das entsprechende PImage für die übergebene Farbe zurück.
     * @param color Die Farbe des gewünschten PImage.
     * @return Das PImage für die angegebene Farbe.
     * <p>
     * Wird von der Controller{@link MenschEargereDichNicht.controller.Controller} benutzt.
     */
    public PImage getPImageByColor(String color) {
        return Map.of(
                "BLUE", blueImage,
                "RED", redImage,
                "PURPLE", purpleImage,
                "ORANGE", orangeImage
        ).get(color);
    }
    /**
     * Aktualisiert den Text des GameState-Labels mit der übergebenen Farbe.
     * @param color Die Farbe, die im GameState-Label angezeigt werden soll.
     *              <p>
     * Wird von der Controller{@link MenschEargereDichNicht.controller.Controller} benutzt.
     */
    @Override
    public void drawGameState(String color) {
        gameStateLabel.setText(color);
    }
    /**
     * Zeigt das Würfelbild entsprechend der übergebenen Augenzahl an.
     * @param diceNumber Die Augenzahl des Würfels.
     * <p>
     * Wird von der Controller{@link MenschEargereDichNicht.controller.Controller} benutzt.
     */
    @Override
    public void drawDice(int diceNumber) {
        image(getDiceByDiceNumber(diceNumber), 750, height - 70, 60, 60);
    }

    private PImage getDiceByDiceNumber(int diceNumber) {
        return Map.of(
                1, dice1,
                2, dice2,
                3, dice3,
                4, dice4,
                5, dice5,
                6, dice6
        ).get(diceNumber);
    }
    /**
     * Zeichnet das Menü auf dem Bildschirm.
     * Wird vom Controller {@link MenschEargereDichNicht.controller.Controller} verwendet.
     */
    @Override
    public void drawMenu() {
        menu2Image.resize(800, 1000);
        image(menuImage, 0, 0, width, height);
        image(menu2Image, 100, 50);
        textSize(30);
        strokeWeight(4);
        fill(0);
        noStroke();
        text("info", width - 130, 40);
    }
    /**
     * Gibt den "Roll" Button zurück.
     * @return Der "Roll" Button.
     * <p>
     * Wird vom Controller {@link MenschEargereDichNicht.controller.Controller} verwendet.
     */
    @Override
    public Button getRollButton() {
        return rollButton;
    }
    /**
     * Zeichnet die Namen der Spieler über ihren Pokemons.
     * @param realPlayers Die Liste der Spieler.
     *  <p>
     * Wird vom Controller {@link MenschEargereDichNicht.controller.Controller} verwendet.
     */
    @Override
    public void drawNamesOfPlayers(ArrayList<RealPlayer> realPlayers) {
        realPlayers.stream().forEach(realPlayer -> {
            float x = realPlayer.getListOfPokemons().get(0).getBox().x1() + 10;
            float y = realPlayer.getListOfPokemons().get(0).getBox().y1() - 15;
            textSize(20);
            text(getTextByColor(realPlayer.getColor().name()).getText(), x, y);
        });

    }
    /**
     * Gibt das Textfeld für den gegebenen Spielerfarbcode zurück.
     * @param color Der Farbcode des Spielers.
     * @return Das entsprechende Textfeld.
     *   <p>
     *  Wird vom Controller {@link MenschEargereDichNicht.controller.Controller} verwendet.
     */
    @Override
    public Textfield getTextByColor(String color) {
        return Map.of(
                "BLUE",playerBlueLabel,
                "RED",playerRedLabel,
                "PURPLE",playerPurpleLabel,
                "ORANGE",playerOrangeLabel
        ).get(color);
    }
    /**
     * Zeichnet einen verfügbaren Zug für die gegebene Box.
     * @param box Die Box, für die ein verfügbarer Zug angezeigt wird.
     *  <p>
     *  Wird vom Controller {@link MenschEargereDichNicht.controller.Controller} verwendet.
     */
    @Override
    public void drawAvailableMove(Box box) {
        if (box != null && mousePressed) {
            fill(226, 234, 0);
            ellipse(box.x1() + (box.size() / 2), box.y1() + (box.size() / 2), (width - 40) / 13, (height - 40) / 13);
        }
    }
    /**
     * Gibt das Textfeld für den Spielstatus zurück.
     * @return Das Textfeld für den Spielstatus.
     * <p>
     * Wird vom Controller {@link MenschEargereDichNicht.controller.Controller} verwendet.
     */

    public Textfield getGameStateLabel() {
        return gameStateLabel;
    }
}
