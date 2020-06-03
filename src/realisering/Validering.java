package realisering;

import javax.swing.*;

/**
 * Klass för att validera input
 */
public class Validering {

    static public boolean finnsIDB(String inText) {
        if (inText == null || inText.length() == 0) {
            JOptionPane.showMessageDialog(null, "Inget resultat i databasen. Kolla stavning.");
            return false;
        } else {
            return true;
        }
    }

    static public boolean finnsText(JTextField text) {
        if (text.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Textfält tomt!");
            return false;
        } else {
            return true;
        }
    }
    /** Metod för att göra input icke-case-sensitive
    static public boolean textEjCaseSensitive(JTextField tf) {
        tf = tf.toLowerCase();
    }
    */
    
}
