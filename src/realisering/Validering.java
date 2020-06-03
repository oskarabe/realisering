package realisering;

import java.text.*;
import java.util.*;
import javax.swing.*;

/**
 * Klass för att validera input
 */
public class Validering {

    // Metod som returnerar true ifall texten i parametern har rätt datumformat. Annars felmeddelande om detta.
    static public boolean isDatum(JTextField datum) {
        SimpleDateFormat datumFormat = new SimpleDateFormat("yyyy-MM-dd");
        datumFormat.setLenient(true);
        try {
            Date d = datumFormat.parse(datum.getText());
            System.out.println("Datum: " + d);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Datum måste anges i formatet yyyy-MM-dd (2020-06-01)");
            return false;
        }
        return true;
    }

    // Returnerar true ifall en sträng inte är null eller tom. För att se ifall SQL-frågor (fetchSingle) returnerar något värde.
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
