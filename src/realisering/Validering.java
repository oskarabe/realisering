package realisering;

import javax.swing.*;

/**
 * Klass för att validera input
 */
public class Validering {

    static public boolean finnsText(JTextField text) {
        if (text.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Textfält tomt!");
            text.requestFocus();
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
