package realisering;

import javax.swing.*;

/**
 * Klass f�r att validera input
 */
public class Validering {

    static public boolean finnsText(JTextField text) {
        if (text.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Textf�lt tomt!");
            text.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    /** Metod f�r att g�ra input icke-case-sensitive
    static public boolean textEjCaseSensitive(JTextField tf) {
        tf = tf.toLowerCase();
    }
    */
    
}
