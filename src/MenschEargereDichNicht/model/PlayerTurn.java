package MenschEargereDichNicht.model;

/**
 * Das Enum "PlayerTurn" repräsentiert den Zug eines Spielers im Spiel "Mensch Ärgere Dich Nicht".
 * Jeder Spielerzug wird durch eine der vier Spielerfarben dargestellt. Durch das Enum wird auch entschieden,
 * wer als nächstes an der Reihe ist. Siehe die Methode {@link Model#getNextPlayer()} im Modell. Die Reihenfolge spielt hier eine Rolle
 * ,da die Spieler hier in der richtigen ReihenFolge des Spieles gespeichert werden.
 *
 * Verwendung:
 * - {@code PlayerTurn pT1= PlayerTurn.BLUE} für den blauen Spielerzug
 * - {@code PlayerTurn pT2= PlayerTurn.RED} für den roten Spielerzug
 * - {@code PlayerTurn pT3= PlayerTurn.PURPLE} für den violetten Spielerzug
 * - {@code PlayerTurn pT4= PlayerTurn.ORANGE} für den orangenen Spielerzug
 *   @author Anas Aji
 *   @version 1.0
 */

 enum PlayerTurn {
    PLAYER_BLUE , PLAYER_RED, PLAYER_PURPLE, PLAYER_ORANGE
}
