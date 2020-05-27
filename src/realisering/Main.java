package realisering;

import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 * Main-klassen som �r starten p� programmet
 * Skapar en connection med databasfilen, utifr�n en relativ s�kv�g
 * Kallar p� en metod f�r att �ppna Login-f�nstret
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
            JOptionPane.showMessageDialog(null, "N�got gick fel!");
            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
        }
        new Login(mib).setVisible(true);
    }

}