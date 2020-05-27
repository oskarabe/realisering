package realisering;

import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 * Main-klassen som är starten på programmet
 * Skapar en connection med databasfilen, utifrån en relativ sökväg
 * Kallar på en metod för att öppna Login-fönstret
 */
public class Main {

    private static InfDB mib;

    public static void main(String[] args) {

        try {
            String projDir = System.getProperty("user.dir");
            String dbDir = projDir + ("\\lib\\MIBDB.FDB");
            System.out.println(dbDir);
            mib = new InfDB(dbDir);
        } catch (InfException ettUndantag) {
            JOptionPane.showMessageDialog(null, "Något gick fel!");
            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
        }
        new Login(mib).setVisible(true);
    }

}