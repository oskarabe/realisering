package realisering;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Klass f�r att validera input
 */
public class Validering {

    static public boolean finnsText(JTextField tf) {
        if (tf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Textf�ltet �r tomt!");
            tf.requestFocus();
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
