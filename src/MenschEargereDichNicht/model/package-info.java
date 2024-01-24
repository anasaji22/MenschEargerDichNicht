/**
 * Das Package "MenschEargereDichNicht.model" enthält die Modell-Klassen für das Spiel "Mensch Ärgere Dich Nicht".
 * Diese Klassen repräsentieren die Spiellogik, das Spielfeld, die Spieler und andere damit verbundene Elemente.
 *  <p>
 * - Model-Klasse{@link MenschEargereDichNicht.model.Model}:
 *   - Implementiert die Spiellogik für "Mensch Ärgere Dich Nicht".
 *   <p>
 *   - Enthält Informationen über das Spielfeld, die Spieler, die Spielerfarben und die Würfelzahl.
 *   <p>
 *   - Der Konstruktor der Klasse initialisiert die notwendigen Variablen und erstellt das Spielfeld und die Spieler.
 *   <p>
 *   - Die Methode newGame() startet ein neues Spiel, indem sie das Spielfeld initialisiert und die Spieler vorbereitet.
 *   <p>
 *
 * - Box-Klasse{@link MenschEargereDichNicht.model.Box}:
 *  <p>
 *   - Repräsentiert eine Spielfeld-Box mit den Koordinaten (x, y) ihrer vier Ecken.
 *   <p>
 *   - Definiert eine eindeutige ID für jede Box.
 *   <p>
 *   - Die Methode isBoxHit(posX, posY) überprüft, ob die angegebenen x- und y-Koordinaten innerhalb der Grenzen der Box liegen.
 *   <p>
 *
 * - RealPlayer-Klasse{@link MenschEargereDichNicht.model.RealPlayer}:
 *   - Repräsentiert einen echten Spieler im Spiel.
 *   <p>
 *   - Jeder echte Spieler hat eine Spielerfarbe (Blau, Rot, Lila oder Orange) und eine Liste von Pokemon.
 *   <p>
 *   - Die Methode movePokemon(diceNumber, box, pokemon) ermöglicht es einem Spieler, ein Pokemon auf dem Spielfeld zu bewegen.
 *   <p>
 *   - Die Methode checkWine() überprüft, ob der Spieler gewonnen hat, indem alle Pokemon in ihrer Endzone sind.
 *
 *   <p>
 *
 * - Pokemon-Klasse{@link MenschEargereDichNicht.model.Pokemon}:
 * <p>
 *   - Repräsentiert ein Pokemon auf dem Spielfeld.
 *   <p>
 *   - Jedes Pokemon hat eine Spielerfarbe, eine eindeutige ID, eine Boxnummer und einen Zustand (lebendig oder tot).
 *   <p>
 *   - Die Methode canMoveToBox(diceNumber, relatedBox) überprüft, ob das Pokemon zu einer bestimmten Box bewegt werden kann.
 *   <p>
 *
 * - PlayerColor-Enum{@link MenschEargereDichNicht.model.PlayerColor}:
 * <p>
 *   - Definiert die verschiedenen Spielerfarben im Spiel.
 *   <p>
 *
 * - PlayerTurn-Enum{@link MenschEargereDichNicht.model.PlayerTurn}:
 * <p>
 *   - Definiert die verschiedenen Spielerzüge im Spiel (Spieler Blau, Rot, Lila oder Orange).
 *   <p>
 *
 * Das Modell-Package enthält somit die Kernkomponenten, die die Spiellogik und die Repräsentation des Spiels bilden,
 * und stellt eine klare Struktur für die Implementierung des "Mensch Ärgere Dich Nicht"-Spiels bereit.
 */
package MenschEargereDichNicht.model;
