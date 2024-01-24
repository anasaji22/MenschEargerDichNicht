package MenschEargereDichNicht.model;

/**
 * Die Klasse "Pokemon" repräsentiert eine Pokemon-Figur im Spiel "Mensch Ärgere Dich Nicht".
 * Ein Pokemon hat eine Spielerfarbe, eine aktuelle Spielfeld-Box, eine Box-Nummer, eine ID-Nummer und einen Status (lebendig oder nicht lebendig).
 * <p>
 * {@code Box b = new Box(10,10,20,10, 10, 20,20,20, 10, 1); }
 * <p>
 * {@code Pokemon p = new Pokemon(PlayerColor.BLUE, b, 1); }
 * <p>
 * color ist vom Typ PlayerColor, welcher als ein Enum in der Package definiert ist und die Werte {BLUE, RED, PURPLE, ORANGE} haben kann.
 * <p>
 * Die Variable "box" repräsentiert die aktuelle Spielfeld-Box des Pokemon.
 * <p>
 * Die Variable "idNummer" ist eine eindeutige Identifikationsnummer für das Pokemon.
 * <p>
 * Die Variable "boxNumber" ist die Nummer der Spielfeld-Box, auf der sich das Pokemon befindet.
 * <p>
 * Die Variable "alive" gibt an, ob das Pokemon lebendig ist oder nicht.
 *  @author Anas Aji
 *  @version 1.0
 */
public class Pokemon {
    private PlayerColor color;
    private Box box;
    private int boxNumber, idNummer;
    private boolean alive;

    /**
     * Konstruktor der Klasse "Pokemon" für die Initialisierung eines Pokemon mit der gegebenen Spielerfarbe, ID-Nummer und Box-Nummer.
     * <p>
     * {@code Box b = new Box(10,10,20,10, 10, 20,20,20, 10, 1);}
     * <p>
     * {@code Pokemon p = new Pokemon(PlayerColor.BLUE, b, 1); }
     *
     * @param color     Die Spielerfarbe des neuen Pokemon.
     * @param idNummer  Die eindeutige ID-Nummer des neuen Pokemon.
     * @param boxNumber Die Nummer der Spielfeld-Box, auf der sich das neue Pokemon befindet.
     */
    public Pokemon(PlayerColor color, int idNummer, int boxNumber) {
        this.color = color;
        this.boxNumber = boxNumber;
        this.idNummer = idNummer;
        this.alive = false;
    }

    /**
     * Gibt die Spielerfarbe des Pokemon zurück.
     * <p>
     * {@code Verwendung: pikachu.getColor();}
     *
     * @return Die Spielerfarbe des Pokemon.
     */
    public PlayerColor getColor() {
        return color;
    }

    /**
     * Gibt die aktuelle Spielfeld-Box des Pokemon zurück.
     * <p>
     * Verwendung:
     * <p>
     * {@code currentBox = pikachu.getBox();}
     *
     * @return Die aktuelle Spielfeld-Box des Pokemon.
     */
    public Box getBox() {
        return box;
    }

    /**
     * Setzt die Spielfeld-Box des Pokemon auf die gegebene Box.
     * <p>
     * Verwendung:
     * - {@code Box newBox =  new Box(20,20, 30,20, 20,30 ,30,30, 10, 2);}
     * <p>
     * - {@code p.setBox(newBox);}
     *
     * @param box Die neue Spielfeld-Box für das Pokemon.
     */
    void setBox(Box box) {
        this.box = box;
    }

    /**
     * Gibt die Nummer der Spielfeld-Box des Pokemon zurück.
     * <p>
     * Verwendung:
     * <p>
     * {@code  boxNumber = p.getBoxNumber(); }
     *
     * @return Die Nummer der Spielfeld-Box des Pokemon.
     */
    int getBoxNumber() {
        return boxNumber;
    }

    /**
     * Setzt die Nummer der Spielfeld-Box des Pokemon auf die gegebene Nummer.
     * <p>
     * Verwendung:
     * - {@code pikachu.setBoxNumber(newBox.boxId()); }
     *
     * @param boxNumber Die neue Nummer der Spielfeld-Box für das Pokemon.
     */

    void setBoxNumber(int boxNumber) {
        this.boxNumber = boxNumber;
    }

    /**
     * Überprüft, ob das Pokemon lebendig ist.
     * <p>
     * Verwendung:
     * <p>
     * - {@code isAlive = p.isAlive();}
     *
     * @return True, wenn das Pokemon lebendig ist, ansonsten False.
     */

    public boolean isAlive() {
        return alive;
    }

    /**
     * Setzt den Status des Pokemon auf "lebendig" oder "nicht lebendig".
     * <p>
     * Verwendung:
     * <p>
     * - {@code  p.setAlive(true) };
     *
     * @param alive True, wenn das Pokemon lebendig ist, ansonsten False.
     */


    void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Gibt die eindeutige ID-Nummer des Pokemon zurück.
     * <p>
     * Verwendung:
     * - {@code id = p.getIdNumber();}
     *
     * @return Die eindeutige ID-Nummer des Pokemon.
     */

     int getIdNumber() {
        return idNummer;
    }

    /**
     * Überprüft, ob das Pokemon mit der mausPressed() getroffen wurde.
     * <p>
     * Verwendung:
     * - {@code hit = pikachu.isBoxHit(posX, posY);}
     *
     * @param posX Die X-Position auf dem Spielfeld.
     * @param posY Die Y-Position auf dem Spielfeld.
     * @return True, wenn die Spielfeld-Box des Pokemon getroffen wurde, ansonsten False.
     */
    public boolean isBoxHit(int posX, int posY) {
        return box.isBoxHit(posX, posY);
    }


}

