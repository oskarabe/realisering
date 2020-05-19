package realisering;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author oskar
 */
public class Validering {

    static public boolean finnsText(JTextField tf) {
        if (tf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Textfältet tomt!");
            tf.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    //static public boolean 
    
}
