package realisering;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Klass för att validera input
 */
public class Validering {

    static public boolean finnsText(JTextField tf) {
        if (tf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Textfältet är tomt!");
            tf.requestFocus();
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
