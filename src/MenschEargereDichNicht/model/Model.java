package MenschEargereDichNicht.model;

import java.util.*;
import java.util.stream.IntStream;


/**
 * Die Klasse "Model" implementiert das Spielmodell (Spiel Logik) für "Mensch Ärgere Dich Nicht".
 * Sie enthält Informationen über das Spielfeld, die Spieler, die Spielerfarben, die Würfelzahl.
 * <p>
 * Verwendung:
 * - Erstellen Sie eine Instanz von Model, um das Spielmodell zu initialisieren.
 * - Verwenden Sie Methoden, um das Spiel zu steuern:
 * <p>
 * - {@link Model#newGame()}: Startet ein neues Spiel.
 * <p>
 * - {@link Model#canPlayerPlay(int diceNumber)}: Überprüft, ob der Spieler spielen kann.
 * <p>
 * - {@link Model#canMovePokemonToBox(int diceNumber, Box relatedBox, Pokemon pokemon)}: Überprüft, ob der Spieler ein Pokemon zu einer bestimmten Box bewegen kann.
 * <p>
 * - {@link Model#movePokemon(int diceNumber, Box box, Pokemon pokemon)}: Bewegt ein Pokemon basierend auf der gewürfelten Zahl und der Zielbox.
 * <p>
 * - {@link Model#checkWin()}: Überprüft, ob der Spieler nach einer Bewegung gewonnen hat.
 * <p>
 * - {@link Model#getAvailableBox(int diceNumber, Pokemon pokemon)}: Gibt die mögliche Zielbox für eine Pokemon-Bewegung zurück.
 * <p>
 * - {@link Model#getRelatedBox(int posX, int posY)}: Gibt die Box an den gegebenen Koordinaten zurück.
 * <p>
 * - {@link Model#getInitialPositionByPlayerColor(PlayerColor color)}: Gibt die Anfangspositionen für Spielerfarben zurück.
 * <p>
 * - {@link Model#getCurrentPlayer()}: Gibt den aktuellen Spieler zurück.
 * <p>
 * - {@link Model#getNextPlayer()}: Gibt den nächsten Spieler zurück.
 * <p>
 * - {@link Model#getBoxMap()}: Gibt die Map der Boxen zurück, die für das Spiel benötigt werden.
 * <p>
 * - {@link Model#getRealPlayersList()}: Gibt eine Liste der aktuellen Spieler zurück.
 * <p>
 * - {@link Model#setPlayerTurn(PlayerTurn playerTurn)}: Setzt den Spieler, der an der Reihe ist.
 * <p>
 * <p>
 * - Greifen Sie auf Spieler, Pokemons, Spielfelder usw. über entsprechende Methoden zu.
 * <p>
 * <p>
 * Beispiel:
 * <p>
 * <p>
 * - {@code Model gameModel = new Model(WIDTH, HEIGHT);}
 * <p>
 * - {@code gameModel.newGame();}
 * <p>
 * - {@code int diceNumber = gameModel.getCurrentPlayer().rollTheDice();}
 * <p>
 * <p>
 * Wenn es true zurückliefert:
 * <p>
 * <p>
 * {@code
 * Box toMove = gameModel.getAvailableBox(diceNumber, gameModel.getCurrentPlayer().getListOfPokemons().get(0));
 * gameModel.movePokemon(diceNumber, toMove, selectedPokemon);
 * }
 * <p>
 * <p>
 * Bei false:
 * <p>
 * <p>
 * {@code
 * // Wird in der Controller-Klasse der nächste Spieler gesetzt.
 * }
 * <p>
 *
 * @author Anas Aji
 * @version 1.0
 */
public class Model implements IModel {
    protected final int BOX_NUMBERS = 169;
    private final float WIDTH, HEIGHT;
    private final int[] orangeSpaceOnBoard = new int[]{66, 67, 68, 69, 70, 57, 44, 31, 18, 19, 20, 33, 46, 59, 72, 73, 74, 75, 76, 89, 102, 101, 100, 99, 98, 111, 124, 137, 150, 149, 148, 135, 122, 109, 96, 95, 94, 93, 92, 79, 80, 81, 82, 83};
    private final int[] redSpaceOnBoard = new int[]{102, 101, 100, 99, 98, 111, 124, 137, 150, 149, 148, 135, 122, 109, 96, 95, 94, 93, 92, 79, 66, 67, 68, 69, 70, 57, 44, 31, 18, 19, 20, 33, 46, 59, 72, 73, 74, 75, 76, 89, 88, 87, 86, 85};
    private final int[] purpleSpaceOnBoard = new int[]{148, 135, 122, 109, 96, 95, 94, 93, 92, 79, 66, 67, 68, 69, 70, 57, 44, 31, 18, 19, 20, 33, 46, 59, 72, 73, 74, 75, 76, 89, 102, 101, 100, 99, 98, 111, 124, 137, 150, 149, 136, 123, 110, 97};
    private final int[] blueSpaceOnBoard = new int[]{20, 33, 46, 59, 72, 73, 74, 75, 76, 89, 102, 101, 100, 99, 98, 111, 124, 137, 150, 149, 148, 135, 122, 109, 96, 95, 94, 93, 92, 79, 66, 67, 68, 69, 70, 57, 44, 31, 18, 19, 32, 45, 58, 71};
    private final int[] board = new int[]{14, 15, 18, 19, 20, 23, 24, 27, 28, 31, 32, 33, 36, 37, 44, 45, 46, 57, 58, 59, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 79, 80, 81, 82, 83, 85, 86, 87, 88, 89, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 109, 110, 111, 122, 123, 124, 131, 132, 135, 136, 137, 140, 141, 144, 145, 148, 149, 150, 153, 154};
    private final HashMap<Integer, Box> boxMap;
    private final int[] purplePlayerPosOnBoard = new int[]{131, 132, 144, 145};
    private final int[] orangePlayerPosOnBoard = new int[]{14, 15, 27, 28};
    private final int[] bluePlayerPosOnBoard = new int[]{23, 24, 36, 37};
    private final int[] redPlayerPosOnBoard = new int[]{140, 141, 153, 154};
    private final String[] jBord;
    private final ArrayList<RealPlayer> realPlayersList;
    private final RealPlayer realPlayerPurple;
    private final RealPlayer realPlayerRed;
    private final RealPlayer realPlayerBlue;
    private final RealPlayer realPlayerOrange;
    private PlayerTurn playerTurn;

    /**
     * Konstruktor für die Klasse "Model".
     * <p>
     * Initialisiert die benötigten Variablen für das Spiel unabhängig von den Parametern {@code WIDTH} und {@code HEIGHT}.
     *
     * @param WIDTH  Die Breite des Spielfelds.
     * @param HEIGHT Die Höhe des Spielfelds.
     *               <p>
     *               Die Werte von {@code WIDTH} und {@code HEIGHT} werden bei Aufruf der Methode {@link Model#newGame()} bearbeitet,
     *               um die Größen der Boxen und deren Anzeigeorte zu bestimmen.
     *               </p>
     */
    public Model(float WIDTH, float HEIGHT) {
        this.HEIGHT = HEIGHT;
        this.WIDTH = WIDTH;
        realPlayersList = new ArrayList<>();
        this.boxMap = new HashMap<>();
        playerTurn = PlayerTurn.PLAYER_BLUE;
        realPlayerBlue = new RealPlayer(PlayerColor.BLUE);
        realPlayerRed = new RealPlayer(PlayerColor.RED);
        realPlayerPurple = new RealPlayer(PlayerColor.PURPLE);
        realPlayerOrange = new RealPlayer(PlayerColor.ORANGE);
        this.jBord = new String[BOX_NUMBERS];
        for (int i = 0; i < jBord.length; i++) {
            jBord[i] = " . ";
        }
    }

    /*
     * Diese Methode ist verantwortlich für die Initialisierung der Map, die für das Spiel benötigt wird.
     * Hier werden Boxen zur Map hinzugefügt.
     */
    private void initHshMap() {
        for (int i = 0; i < BOX_NUMBERS; i++) {
            addBoxes(i);
        }
        System.out.println(toStringBoard());
    }

    /*
     * Diese Methode fügt die Boxen zum Spielfeld hinzu und aktualisiert die Boxen-Map.
     * Die Berechnungen für die Position jeder Box basieren auf der Anordnung der Boxen im Spielfeld.
     *
     * - Der Index i entspricht der Position der Box im Spielfeld.
     * - Die Anordnung erfolgt in Reihen von 13 Boxen pro Zeile.
     * - Die Konstante constantFreeSpace definiert den freien Raum um das Spielfeld.
     *  Überprüfung, ob die aktuelle Boxnummer zu den vordefinierten Positionen gehört:
     *      if (j < board.length && i == board[j]) {
     *
     * Berechnung der Zeile (row) und Spalte (column) der Box:
     * row = i / 13;
     * column = i % 13;
     *
     * Berechnung der X- und Y-Koordinaten für jede Ecke der Box:
     * x1 = constantFreeSpace + (column * ((WIDTH - (constantFreeSpace * 2)) / 13));
     * y1 = constantFreeSpace + (row * ((HEIGHT - (constantFreeSpace * 2)) / 13));
     * x2 = constantFreeSpace + ((column + 1) * ((WIDTH - (constantFreeSpace * 2)) / 13));
     * y2 = constantFreeSpace + (row * ((HEIGHT - (constantFreeSpace * 2)) / 13));
     * x3 = constantFreeSpace + (column * ((WIDTH - (constantFreeSpace * 2)) / 13));
     * y3 = constantFreeSpace + ((row + 1) * ((HEIGHT - (constantFreeSpace * 2)) / 13));
     * x4 = constantFreeSpace + ((column + 1) * ((WIDTH - (constantFreeSpace * 2)) / 13));
     * y4 = constantFreeSpace + ((row + 1) * ((HEIGHT - (constantFreeSpace * 2)) / 13));
     *
     * Die Box wird mit den berechneten Koordinaten zur Boxen-Map hinzugefügt.
     * Die Anzeige im jBord-Array wird ebenfalls aktualisiert.
     */
    private void addBoxes(int i) {
        int constantFreeSpace = 20;
        for (int j = 0; j < BOX_NUMBERS; j++) {
            int row = i / 13;
            int column = i % 13;
            // Die benötigten boxnummern für das Spiel befinden sich in der final Variable board[]
            // deswegen wird hier überprüfft, ob es gleisch ist oder nicht und wenn ja dann wird das Box hizugefügt
            if (j < board.length && i == board[j]) {
                this.boxMap.put(i, new Box(constantFreeSpace + (column * ((WIDTH - (constantFreeSpace * 2)) / 13)), constantFreeSpace + (row * ((HEIGHT - (constantFreeSpace * 2)) / 13)), constantFreeSpace + ((column + 1) * ((WIDTH - (constantFreeSpace * 2)) / 13)), constantFreeSpace + (row * ((HEIGHT - (constantFreeSpace * 2)) / 13)), constantFreeSpace + (column * ((WIDTH - (constantFreeSpace * 2)) / 13)), constantFreeSpace + ((row + 1) * ((HEIGHT - (constantFreeSpace * 2)) / 13)), constantFreeSpace + ((column + 1) * ((WIDTH - (constantFreeSpace * 2)) / 13)), constantFreeSpace + ((row + 1) * ((HEIGHT - (constantFreeSpace * 2)) / 13)), (WIDTH - (constantFreeSpace * 2)) / 13, i));
                jBord[i] = " # ";
            }
        }
    }

    /*
     * Diese private Methode initialisiert die Spieler und ihre Pokemon für das Spiel.
     * Die Methode verwendet vordefinierte Spielerfarben und erstellt für jeden Spieler vier Pokemon.
     * Jedes Pokemon wird an seiner initialen Position auf dem Spielfeld platziert.
     *
     * - Die Farben der Spieler sind in der Liste colors definiert.
     * - Die Spielerfarben werden iteriert, und für jede Farbe werden vier Pokemon erstellt.
     * - Jedes Pokemon erhält eine eindeutige ID-Nummer und wird an seiner initialen Position platziert.
     * - Die Liste der Pokemon jedes Spielers wird aktualisiert.
     * - Die Pokemon werden mit den zugehörigen Boxen in der boxMap verbunden.
     * - Die Spieler werden zur Liste der realen Spieler hinzugefügt.
     * - Die Anzeige im jBord-Array wird aktualisiert.
     */
    private void initPlayers() {
        var colors = List.of(PlayerColor.BLUE, PlayerColor.RED, PlayerColor.PURPLE, PlayerColor.ORANGE);
        for (var color : colors) {
            var pokemons = new ArrayList<Pokemon>();
            for (int idNummer = 0; idNummer < 4; idNummer++) {
                pokemons.add(new Pokemon(color, idNummer, getInitialPositionByPlayerColor(color)[idNummer]));
                jBord[getInitialPositionByPlayerColor(color)[idNummer]] = getStringPlayerByColor(color);
            }
            var realPlayer = getRealPlayerByColor(color);
            realPlayer.setListOfPokemons(pokemons);
            realPlayer.getListOfPokemons().forEach(p -> p.setBox(boxMap.get(p.getBoxNumber())));
            realPlayersList.add(realPlayer);
        }
        System.out.println(toStringBoard());
    }

    /*
     * ist nur für den Anzeige in der jshell nötig.
     *
     */
    private String toStringBoard() {
        String result = "";
        for (int i = 0; i < jBord.length; i++) {
            result = result + jBord[i] + " ";
            if (i % 13 == 12) {
                result = result + "\n"; // new Line
            }
        }
        result = result + "\n" + "Dice number : " + getCurrentPlayer().getDiceNumber() + "  Player Turn : " + playerTurn;
        return result;
    }

    /**
     * Startet ein neues Spiel, indem es die Spielerreihenfolge auf den blauen Spieler setzt
     * und das Spielfeld initialisiert sowie Spieler und ihre Pokemon platziert.
     * <p>
     * Beispiel:
     * <p>
     * {@code Model gameModel = new Model(WIDTH, HEIGHT);}
     * <p>
     * {@code gameModel.newGame()}
     */
    public void newGame() {
        this.playerTurn = PlayerTurn.PLAYER_BLUE;
        initHshMap();
        initPlayers();
    }

    /**
     * Überprüft, ob der aktuelle Spieler mit der gewürfelten Zahl spielen kann.
     * <p>
     * Die Methode prüft verschiedene Bedingungen, um festzustellen, ob der Spieler
     * mit der aktuellen Würfelzahl am Zug spielen kann. Die Überlegungen sind wie folgt:
     * <p>
     * 1. Wenn kein lebendiges Pokemon auf dem Spielfeld ist und die gewürfelte Zahl nicht 6 ist,
     * kann der Spieler nicht spielen. In diesem Fall gibt die Methode False zurück.
     * <p>
     * 2. Wenn kein lebendiges Pokemon auf dem Spielfeld ist, der Spieler jedoch eine 6 gewürfelt hat,
     * kann der Spieler spielen. Die Methode gibt True zurück.
     * <p>
     * 3. Wenn mindestens ein lebendiges Pokemon auf dem Spielfeld ist und die gewürfelte Zahl eine
     * ausreichende Bewegung ermöglicht, gibt die Methode True zurück. Es wird false ausgegeben von der
     * Methoden Aufruf {@link Model#canPlayWithOther(int)}, wenn der spieler nicht mit einem ander Pokemon (Spielfigur)
     * spielen kann.
     * <p>
     * Beispiel:
     * <p>
     * {@code Model gameModel = new Model(WIDTH, HEIGHT);}
     * <p>
     * {@code  gameModel.canPlayerPlay()}
     *  <p>
     *  Die Methode wird von den Controller verwendet
     *
     * @param diceNumber Die gewürfelte Zahl.
     * @return True, wenn der Spieler mit der gewürfelten Zahl spielen kann, ansonsten False.
     */
    public boolean canPlayerPlay(int diceNumber) {
//        No pokemon tooMove and the dice Num != 6
        if (getCurrentPlayer().getListOfPokemons().stream().noneMatch(pokemon -> pokemon.isAlive()) && diceNumber != 6) {
            return false;
        }
//      if no pokemon isAlive and dice Num = 6
        if (getCurrentPlayer().getListOfPokemons().stream().anyMatch(p -> !p.isAlive()) && diceNumber == 6) {
            return true;
        }
        if (getCurrentPlayer().getListOfPokemons().stream().anyMatch(pokemon ->
                findIndex(getCurrentPlayerBoard(), pokemon.getBoxNumber()) + diceNumber < getCurrentPlayerBoard().length && pokemon.isAlive())) {
            return canPlayWithOther(diceNumber);
        }
        return false;
    }
    /*
     * Überprüft, ob der aktuelle Spieler mit der gewürfelten Zahl ein anderes Pokemon bewegen kann.
     *
     * Die Methode prüft für jedes Pokemon des aktuellen Spielers, ob es mit der gewürfelten Zahl
     * bewegt werden kann und ob ein anderes Pokemon auf dem Zielfeld steht, das ebenfalls bewegt werden kann.
     * Wenn mindestens ein solches Paar von Pokemon und Zielfeld gefunden wird, gibt die Methode True zurück.
     *
     * diceNumber Die gewürfelte Zahl.
     * True, wenn der Spieler mit der gewürfelten Zahl ein anderes Pokemon bewegen kann, ansonsten False.
     */

    private boolean canPlayWithOther(int diceNumber) {
        for (var pokemon : getCurrentPlayer().getListOfPokemons()) {
            var indexOfPokemonBox = findIndex(getCurrentPlayerBoard(), pokemon.getBoxNumber());
            if (indexOfPokemonBox + diceNumber < getCurrentPlayerBoard().length
                    && getCurrentPlayer().getListOfPokemons().stream().anyMatch(p ->
                    canMovePokemonToBox(diceNumber, boxMap.get(getCurrentPlayerBoard()[indexOfPokemonBox + diceNumber]), p))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Überprüft, ob das Pokemon des aktuellen Spielers mit der gewürfelten Zahl zu einer bestimmten Box bewegt werden kann.
     * <p>
     * Die Methode prüft mehrere Bedingungen, um festzustellen, ob das Pokemon auf das Zielfeld (relatedBox) bewegt werden kann:
     * <p>
     * 1. Das Zielfeld darf nicht von einem anderen Pokemon des aktuellen Spielers besetzt sein.
     * <p>
     * 2. Wenn die gewürfelte Zahl 6 ist und das Pokemon nicht lebendig ist, kann es nur von der Startposition bewegt werden.
     * <p>
     * 3. Das Pokemon muss lebendig sein.
     * <p>
     * 4. Das Zielfeld (relatedBox) muss durch die gewürfelte Zahl erreicht werden können.
     * <p>
     * <p>
     * Beispiel:
     * <p>
     * {@code Model  = new Model(WIDTH, HEIGHT);}
     * <p>
     * {@code gameModel.canMovePokemonToBox(selectedNumber, relatedBox, selectedPokemon}
     * <p>
     * Die Methode wird von den Controller verwendet
     *
     * @param diceNumber Die gewürfelte Zahl.
     * @param relatedBox Die Ziel-Box, zu der das Pokemon bewegt werden soll.
     * @param pokemon    Das Pokemon, das bewegt werden soll.
     * @return True, wenn das Pokemon zu der angegebenen Box bewegt werden kann, ansonsten False.
     */

    public boolean canMovePokemonToBox(int diceNumber, Box relatedBox, Pokemon pokemon) {
        if (getCurrentPlayer().getListOfPokemons().stream().anyMatch(p -> p.getBox().equals(relatedBox))) {
            return false;
        }
        if (diceNumber == 6 && !pokemon.isAlive() && relatedBox.boxId() == getCurrentPlayerBoard()[0]) {
            return true;
        }
        var indexOfPokemonBox = findIndex(getCurrentPlayerBoard(), pokemon.getBoxNumber());
        return pokemon.isAlive() && indexOfPokemonBox + diceNumber < getCurrentPlayerBoard().length && getCurrentPlayerBoard()[indexOfPokemonBox + diceNumber] == relatedBox.boxId();
    }

    /**
     * Bewegt das Pokemon des aktuellen Spielers zu einer bestimmten Box basierend auf der gewürfelten Zahl.
     * Die Methode berücksichtigt verschiedene Bedingungen für das Bewegen des Pokemons.
     * Wenn die gewürfelte Zahl 6 ist und das Pokemon nicht lebendig ist, wird es zu der Startposition bewegt.
     * Andernfalls wird das Pokemon um die gewürfelte Zahl auf dem Spielfeld bewegt.
     * Beispiel:
     *
     * - {@code Model gameModel = new Model(WIDTH, HEIGHT);}
     * - {@code gameModel.newGame();}
     * - {@code int diceNumber = gameModel.getCurrentPlayer().rollTheDice();}
     *
     * Wenn es true zurückliefert:
     *
     * {@code
     * Box toMove = gameModel.getAvailableBox(diceNumber, gameModel.{@link Model#getCurrentPlayer()}.{@link RealPlayer#getListOfPokemons()}.get(0));
     * gameModel.movePokemon(diceNumber, toMove, selectedPokemon);
     * }
     *
     * @param diceNumber Die gewürfelte Zahl.
     * @param box        Die Ziel-Box, zu der das Pokemon bewegt werden soll.
     * @param pokemon    Das zu bewegende Pokemon.
     *
     * Der Methodenaufruf startet einen Hintergrund-Thread, um den Angriff auf andere Pokemon zu handeln.
     * Dies erfolgt, um eine reibungslose Animation oder Aktualisierung des Spielbretts während des Angriffs zu ermöglichen.
     * Die Methode blockiert nicht und gibt sofort zurück, nachdem der Thread gestartet wurde.
     * Die Verwendung von Threads hilft, die Benutzeroberfläche während des Angriffs reaktionsfähig zu halten.
     */
    public void movePokemon(int diceNumber, Box box, Pokemon pokemon) {
        Thread thread = new Thread(() -> {
            handelHits(box);
        });
        if (diceNumber == 6 && !pokemon.isAlive()) {
            jBord[pokemon.getBoxNumber()] = " # ";
            pokemon.setBox(box);
            pokemon.setBoxNumber(getCurrentPlayerBoard()[0]);
            jBord[pokemon.getBoxNumber()] = getStringPlayerByColor(pokemon.getColor());
            pokemon.setAlive(true);
            // Überprüfen, ob das Pokemon andere Pokemon nicht von dem gleichen Team schlägt.
            if (pokemonHitsOther(box, getCurrentPlayer())) {
                thread.start();
            }
            System.out.println(toStringBoard());
        } else {
            jBord[pokemon.getBoxNumber()] = " # ";
            pokemon.setBox(box);
            pokemon.setBoxNumber(getCurrentPlayerBoard()[findIndex(getCurrentPlayerBoard(), pokemon.getBoxNumber()) + diceNumber]);
            jBord[pokemon.getBoxNumber()] = getStringPlayerByColor(pokemon.getColor());
            pokemon.setAlive(true);
            if (pokemonHitsOther(box, getCurrentPlayer())) {
                thread.start();
            }
            System.out.println(toStringBoard());
        }
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    * Überprüft, ob das Pokemon eines Spielers eine Box eines anderen Spielers trifft.
    * Die Methode durchsucht die Liste der echten Spieler (RealPlayer) und deren Pokemon,
    * um festzustellen, ob ein Pokemon des aktuellen Spielers die angegebene Box trifft.
    *
    * @param reletedBox  Die Box, die überprüft wird, ob sie von einem Pokemon getroffen wird.
    * @param realPlayer  Der aktuelle Spieler, dessen Pokemon auf Kollision überprüft wird.
    * @return True, wenn das Pokemon eines Spielers eine Pokemon anderen Spielers die angegebene Box trifft, ansonsten False.
    */
    private boolean pokemonHitsOther(Box reletedBox, RealPlayer realPlayer) {
        return realPlayersList.stream().filter(rP -> !realPlayer.equals(rP)).anyMatch(rP -> rP.getListOfPokemons().stream().anyMatch(pokemon -> pokemon.getBox().equals(reletedBox)));
    }
    /*
    * Verarbeitet Kollisionen zwischen Pokemon, wenn ein Spieler ein Feld betritt, das bereits von einem anderen Spieler besetzt ist.
    * Die Methode durchsucht die Liste der echten Spieler (RealPlayer) und deren Pokemon,
    * um festzustellen, ob ein Pokemon eines anderen Spielers durch die aktuelle Aktion des Spielers getroffen wird.
    * Wenn dies der Fall ist, wird die Position des getroffenen Pokemon auf seine Startposition zurückgesetzt,
    * die Box wird aktualisiert, das Spielfeld wird aktualisiert, und das Pokemon wird als "nicht lebendig" markiert.
    *
    * @param box Die Box, die von einem Pokemon eines anderen Spielers besetzt ist.
    */
    private void handelHits(Box box) {
        for (var rP : realPlayersList) {
            if (!getCurrentPlayer().equals(rP)) {
                for (var po : rP.getListOfPokemons()) {
                    if (po.getBox() == box) {
                        po.setBoxNumber(getInitialPositionByPlayerColor(po.getColor())[po.getIdNumber()]);
                        po.setBox(boxMap.get(po.getBoxNumber()));
                        jBord[po.getBoxNumber()] = getStringPlayerByColor(po.getColor());
                        po.setAlive(false);
                    }
                }
            }
        }
    }
    /**
     * Überprüft, ob der aktuelle Spieler gewonnen hat, indem alle Pokemon des Spielers
     * in ihre Endzone bewegt wurden.
     * <p>
     * Die Methode durchläuft die Liste der Pokemon des aktuellen Spielers und überprüft,
     * ob die Boxnummer jedes Pokemon größer ist als die Länge des Spielfelds abzüglich 5.
     * Dies bedeutet, dass sich alle Pokemons in der Endzone befinden.
     *
     * @return True, wenn alle Pokemons des aktuellen Spielers in der Endzone sind; andernfalls False.
     *
     * Beispiel:
     * <p>
     * Angenommen, die Länge des Spielfelds beträgt 40. Wenn die Boxnummer eines Pokemon 36 ist,
     * bedeutet dies, dass sich das Pokemon in der Endzone befindet (40 - 5 = 35). Die Methode gibt True zurück.
     * Wenn mindestens ein Pokemon nicht in der Endzone ist, wird False zurückgegeben.
     * <p>
     *  Die Methode wird von den Controller verwendet
     */

    public boolean checkWin() {
        return getCurrentPlayer().getListOfPokemons().stream().allMatch(p -> findIndex(getCurrentPlayerBoard(), p.getBoxNumber()) > getCurrentPlayerBoard().length - 5);
    }
    /*
    * findet der Index des Pokemones anhand seine Boxnummer.
    */
    private int findIndex(int[] playerBoard, int boxNumber) {
        return IntStream.range(0, playerBoard.length).filter(i -> playerBoard[i] == boxNumber).findFirst().orElse(-1);
    }
    /**
     * Ermittelt die verfügbare Box, zu der ein Pokemon bewegt werden kann, basierend auf der Würfelzahl.
     * <p>
     * Überprüft, ob das Pokemon nicht null ist und trifft bestimmte Bedingungen für den Zug:
     * <p>
     * - Wenn die Würfelzahl 6 ist und das Pokemon nicht lebendig ist und sich kein anderes Pokemon des aktuellen
     *   Spielers bereits in der Startbox befindet, wird die Startbox als verfügbare Box zurückgegeben.
     *   <p>
     * - Wenn das Pokemon lebendig ist und das Ergebnis der Bewegung mit der Würfelzahl gültig ist (innerhalb der Spiellänge),
     *   und keine anderen Pokemon des aktuellen Spielers bereits auf der Zielbox stehen, wird die entsprechende Box zurückgegeben.
     * <p>
     * @param diceNumber Die Würfelzahl für die Bewegung.
     * @param pokemon    Das Pokemon, das bewegt werden soll.
     * @return Die verfügbare Box für die Bewegung oder null, wenn keine verfügbar ist.
     * <p>
     * Beispiel:
     * Angenommen, die Würfelzahl beträgt 2, und das Pokemon steht auf Box 18 (innerhalb der Spiellänge).
     * Wenn die Box 20 frei ist, wird Box 20 als verfügbare Box zurückgegeben.
     * Wenn eine dieser Bedingungen nicht erfüllt ist, wird null zurückgegeben.
     * <p>
     * {@code Model  = new Model(WIDTH, HEIGHT);}
     * <p>
     * {@code gameModel.getAvailableBox(model.getCurrentPlayer().getDiceNumber(), selectedPokemon);}
     * <p>
     * Die Methode wird von den Controller verwendet
     *
     */
    public Box getAvailableBox(int diceNumber, Pokemon pokemon) {

        if (pokemon != null) {
            if (diceNumber == 6 && !pokemon.isAlive() && !getCurrentPlayer().getListOfPokemons().stream().anyMatch(p -> p.getBox().equals(boxMap.get(getCurrentPlayerBoard()[0])))) {
                return boxMap.get(getCurrentPlayerBoard()[0]);
            }
            var indexOfPokemonBox = findIndex(getCurrentPlayerBoard(), pokemon.getBoxNumber());
            if (pokemon.isAlive() && indexOfPokemonBox + diceNumber < getCurrentPlayerBoard().length
                    && !getCurrentPlayer().getListOfPokemons().stream().anyMatch(p ->
                      p.getBox().equals(boxMap.get(getCurrentPlayerBoard()[indexOfPokemonBox + diceNumber])))) {
                return boxMap.get(getCurrentPlayerBoard()[indexOfPokemonBox + diceNumber]);
            }
        }
        return null;
    }
    /**
     * Ermittelt die zugehörige Box basierend auf den angegebenen x- und y-Koordinaten.
     * <p>
     * Durchsucht die Map der Spielfeld-Boxen und überprüft, ob die angegebenen Koordinaten innerhalb
     * der Grenzen einer Box liegen und ob die Box zur Spielfeldkonfiguration gehört.
     * Wenn diese Bedingungen erfüllt sind, wird die entsprechende Box zurückgegeben.
     * <p>
     * @param posX Die x-Koordinate.
     * @param posY Die y-Koordinate.
     * @return Die zugehörige Box zu den angegebenen Koordinaten oder null, wenn keine gefunden wurde.
     * <p>
     * Beispiel:
     * Angenommen, die x-Koordinate ist 25 und die y-Koordinate ist 35.
     * Wenn eine Box in der Spielfeldkonfiguration die Koordinaten 20, 30, 40, 40 hat,
     * wird diese Box als zugehörige Box zurückgegeben. Andernfalls wird null zurückgegeben.
     * <p>
     * {@code Model  = new Model(WIDTH, HEIGHT); }
     * <p>
     * {@code var box = model.getRelatedBox(mouseX, mouseY);}
     * <p>
     * Die Methode wird von den Controller verwendet
     */
    public Box getRelatedBox(int posX, int posY) {
        for (var entry : boxMap.entrySet()) {
            if (entry.getValue().isBoxHit(posX, posY) && Arrays.stream(getCurrentPlayerBoard()).anyMatch(boardItem -> entry.getKey() == boardItem)) {
                return entry.getValue();
            }
        }
        return null;
    }
    /**
     * Gibt die Anfangspositionen der Pokemons auf dem Spielfeld für die angegebene Spielerfarbe zurück.
     *
     * @param color Die Spielerfarbe.
     * @return Ein Array mit den Anfangspositionen für die angegebene Spielerfarbe.
     *
     * Beispiel:
     * <p>
     * Wenn die Spielerfarbe blau ist, gibt die Methode die Anfangspositionen für die blauen Spieler zurück.
     * <p>
     * Die Methode wird nur innerhalb der Model verwendet.
     */
     int[] getInitialPositionByPlayerColor(PlayerColor color) {
        return Map.of(PlayerColor.BLUE, bluePlayerPosOnBoard, PlayerColor.RED, redPlayerPosOnBoard, PlayerColor.PURPLE, purplePlayerPosOnBoard, PlayerColor.ORANGE, orangePlayerPosOnBoard).get(color);
    }
    /*
     * Gibt die Zeichenrepräsentation für die angegebene Spielerfarbe zurück.
     *  <p>
     * @param playerColor Die Spielerfarbe.
     * @return Die Zeichenrepräsentation für die angegebene Spielerfarbe.
     * <p>
     * Beispiel:
     * <p>
     * Wenn die Spielerfarbe rot ist, gibt die Methode " R " zurück.
     * <p>
     *
     */

    private String getStringPlayerByColor(PlayerColor playerColor) {
        return Map.of(PlayerColor.BLUE, " B ", PlayerColor.RED, " R ", PlayerColor.PURPLE, " P ", PlayerColor.ORANGE, " O ").get(playerColor);
    }
    /**
     * Gibt den aktuellen Spieler basierend auf dem aktuellen Zug (playerTurn) zurück.
     *
     * @return Der aktuelle Spieler.
     * <p>
     * Beispiel:
     * Wenn der aktuelle Zug "PLAYER_BLUE" ist, gibt die Methode den blauen Spieler zurück.
     * <p>
     * Die Methode wird in den Model und in den Controller benutzt.
     */
    public RealPlayer getCurrentPlayer() {
        return Map.of(PlayerTurn.PLAYER_BLUE, realPlayerBlue, PlayerTurn.PLAYER_RED, realPlayerRed, PlayerTurn.PLAYER_PURPLE, realPlayerPurple, PlayerTurn.PLAYER_ORANGE, realPlayerOrange).get(playerTurn);
    }

    /**
     * Gibt das Spielfeld (Board) für den aktuellen Spieler zurück.
     *
     * @return Das Spielfeld (Board) für den aktuellen Spieler.
     * <p>
     * Beispiel:
     * <p>
     * Wenn der aktuelle Zug "PLAYER_BLUE" ist, gibt die Methode das Spielfeld für die blauen Spieler zurück.
     * <p>
     * Die Methode wird in dem Model und in den Controller benutzt.
     */
    public int[] getCurrentPlayerBoard() {
        return Map.of(PlayerTurn.PLAYER_BLUE, blueSpaceOnBoard, PlayerTurn.PLAYER_RED, redSpaceOnBoard, PlayerTurn.PLAYER_PURPLE, purpleSpaceOnBoard, PlayerTurn.PLAYER_ORANGE, orangeSpaceOnBoard).get(playerTurn);
    }
    /*
     * Gibt den RealPlayer für die angegebene Spielerfarbe zurück.
     * <p>
     * @param color Die Spielerfarbe.
     * @return Der RealPlayer für die angegebene Spielerfarbe.
     * <p>
     * Beispiel:
     * <p>
     * Wenn die Spielerfarbe lila ist, gibt die Methode den lila RealPlayer zurück.
     * <p>
     */
    private RealPlayer getRealPlayerByColor(PlayerColor color) {
        return Map.of(PlayerColor.BLUE, realPlayerBlue, PlayerColor.RED, realPlayerRed, PlayerColor.PURPLE, realPlayerPurple, PlayerColor.ORANGE, realPlayerOrange).get(color);
    }
    /**
     * Gibt den nächsten Spieler (nach dem aktuellen Zug) zurück.
     *
     * @return Der nächste Spieler.
     *
     * Beispiel:
     * Wenn der aktuelle Zug "PLAYER_BLUE" ist, gibt die Methode den roten Spieler zurück.
     * <p>
     *  Wenn der aktulle Spiele Orange ist, dann wir Blu gegeben, weil hier % verwendet wird.
     *  <p>
     *  Die Methode wird in dem Model und in den Controller benutzt.
     */

    public PlayerTurn getNextPlayer() {
        return PlayerTurn.values()[(playerTurn.ordinal() + 1) % PlayerTurn.values().length];
    }
    /**
     * Gibt die Map der Spielfeld-Boxen zurück.
     *
     * @return Die Map der Spielfeld-Boxen.
     *
     * Beispiel:
     * Die Methode gibt die gesamte Map der Spielfeld-Boxen zurück.
     * <p>
     * Die Methode wird in dem Model und in den Controller benutzt.
     */
    public HashMap<Integer, Box> getBoxMap() {
        return boxMap;
    }
    /**
     * Gibt die Liste der echten Spieler (RealPlayer) zurück.
     *
     * @return Die Liste der echten Spieler.
     *
     * Beispiel:
     * Die Methode gibt die Liste aller echten Spieler im Spiel zurück.
     * <p>
     * Die Methode wird in dem Model und in den Controller benutzt.
     */
    public ArrayList<RealPlayer> getRealPlayersList() {
        return realPlayersList;
    }

    /**
     * Setzt den aktuellen Zug (Spieler).
     *
     * @param playerTurn Der Spieler, der an der Reihe ist.
     *
     * Beispiel:
     * Wenn der nächste Spieler rot ist, wird die Methode "setPlayerTurn(PlayerTurn.PLAYER_RED)" aufgerufen.
     *                   <p>
     * Die Methode wird in dem Model und in den Controller benutzt.
     */
    public void setPlayerTurn(PlayerTurn playerTurn) {
        this.playerTurn = playerTurn;
    }


}

