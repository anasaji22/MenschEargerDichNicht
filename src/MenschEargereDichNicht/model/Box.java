package MenschEargereDichNicht.model;


/**
 * Das Record "Box" speichert die Koordinaten (x, y) von vier Ecken einer Spielfeld-Box.
 * Die Box repräsentiert eine gültige Spielfeldposition im Spiel "Mensch Ärgere Dich Nicht".
 *
 * @param x1    Die x-Koordinate der ersten Ecke der Box oben links.
 * @param y1    Die y-Koordinate der ersten Ecke der Box oben links.
 * @param x2    Die x-Koordinate der zweiten Ecke der Box oben rechts.
 * @param y2    Die y-Koordinate der zweiten Ecke der Box oben rechts.
 * @param x3    Die x-Koordinate der dritten Ecke der Box unten links.
 * @param y3    Die y-Koordinate der dritten Ecke der Box unten links.
 * @param x4    Die x-Koordinate der vierten Ecke der Box unten rechtes.
 * @param y4    Die y-Koordinate der vierten Ecke der Box unten rechtes.
 * @param size  Die Größe der Box.
 * @param boxId Die eindeutige ID der Box.
 *
 * erstellung eines Objekt von der Klasse:
 * <P>
 * {@codeBox b = new Box(10,10,20,10, 10, 20,20,20, 10, 50);}
 * <P>
 * Das ist ein box in der bei der Punkt 10,10 Startet, dann ist die zweite ecke von der Box x 20, y 10
 *              <P>
 * Wenn es sich um einen Viereck oder Rechteck handelt, dann gilt es:
 *              <P>
 * x1 = x3, y1 = y2, y3 = y4 sowie:
 *              <P>
 * x1 != x2, x3 != xy, y1 != y3, y1 != y4.
 *  @author Anas Aji
 *  @version 1.0
 */
public record Box(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, float size,
                  int boxId) {
    /**
     * Überprüft, ob die angegebenen x- und y-Koordinaten innerhalb der Grenzen dieser Box liegen.
     *
     * @param posX Die x-Koordinate.
     * @param posY Die y-Koordinate.
     * @return True, wenn die Koordinaten innerhalb der Box liegen, ansonsten False.
     *          <p>
     *          Die Benutzung von der Methode der Klasse isBoxHit()
     *          <p>
     *          {@code b.isBoxHit(15, 15);}  das soll für der Box b 'true' ergeben
     *          <p>
     *          {@code b.isBoxHit(21, 20);}  das soll für der Box b 'false' ergeben
     *          </p>
     *          {@code b.isBoxHit(21, 20);}
     *  @author Anas Aji
     *  @version 1.0
     */
    public boolean isBoxHit(int posX, int posY) {
        return posX >= Math.min(x1(), Math.min(x2(), Math.min(x3(), x4()))) && posX <= Math.max(x1(), Math.max(x2(), Math.max(x3(), x4())))
                && posY >= Math.min(y1(), Math.min(y2(), Math.min(y3(), y4()))) && posY <= Math.max(y1(), Math.max(y2(), Math.max(y3(), y4())));
    }
}

