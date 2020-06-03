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

    // Returnerar true ifall en sträng inte är null eller tom. För att se ifall SQL-frågor (fetchSingle) returnerar något värde med användarinmatning.
    static public boolean finnsIDB(String inText) {
        if (inText == null || inText.length() == 0) {
            JOptionPane.showMessageDialog(null, "Inget resultat i databasen. Kolla stavning.");
            return false;
        } else {
            return true;
        }
    }

    // Returnerar true ifall det finns text i textrutan.
    static public boolean finnsText(JTextField text) {
        if (text.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Textfält tomt!");
            return false;
        } else {
            return true;
        }
    }

    // Returnerar true ifall inmatad text är j eller n.
    static public boolean adminFormat(JTextField t) {
        String s = t.getText();
        if (s.equalsIgnoreCase("j") || s.equalsIgnoreCase("n")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Ange J eller N för att bestämma administratörstatus.");
            return false;
        }
    }

    // Returnerar true ifall inmatad text är kortare än maximalt tillåtet av databasen för namn-kolumnen.
    static public boolean namnLangd(JTextField t) {
        String s = t.getText();
        if (s.length() < 20) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "För långt namn");
            return false;
        }
    }

    // Returnerar true ifall inmatad text är kortare än maximalt tillåtet av databasen för telefon-kolumnen.
    static public boolean telefonLangd(JTextField t) {
        String s = t.getText();
        if (s.length() < 30) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "För långt telefonnummer");
            return false;
        }
    }
    
}
