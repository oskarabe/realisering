package realisering;

import java.text.*;
import java.util.*;
import javax.swing.*;

/**
 * Klass f�r att validera input
 */
public class Validering {

    // Metod som returnerar true ifall texten i parametern har r�tt datumformat. Annars felmeddelande om detta.
    static public boolean isDatum(JTextField datum) {
        SimpleDateFormat datumFormat = new SimpleDateFormat("yyyy-MM-dd");
        datumFormat.setLenient(true);
        try {
            Date d = datumFormat.parse(datum.getText());
            System.out.println("Datum: " + d);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Datum m�ste anges i formatet yyyy-MM-dd (2020-06-01)");
            return false;
        }
        return true;
    }

    // Returnerar true ifall en str�ng inte �r null eller tom. F�r att se ifall SQL-fr�gor (fetchSingle) returnerar n�got v�rde med anv�ndarinmatning.
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
            JOptionPane.showMessageDialog(null, "Textf�lt tomt!");
            return false;
        } else {
            return true;
        }
    }

    // Returnerar true ifall inmatad text �r j eller n.
    static public boolean adminFormat(JTextField t) {
        String s = t.getText();
        if (s.equalsIgnoreCase("j") || s.equalsIgnoreCase("n")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Ange J eller N f�r att best�mma administrat�rstatus.");
            return false;
        }
    }

    // Returnerar true ifall inmatad text �r kortare �n maximalt till�tet av databasen f�r namn-kolumnen.
    static public boolean namnLangd(JTextField t) {
        String s = t.getText();
        if (s.length() < 20) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "F�r l�ngt namn");
            return false;
        }
    }

    // Returnerar true ifall inmatad text �r kortare �n maximalt till�tet av databasen f�r telefon-kolumnen.
    static public boolean telefonLangd(JTextField t) {
        String s = t.getText();
        if (s.length() < 30) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "F�r l�ngt telefonnummer");
            return false;
        }
    }
    
}
