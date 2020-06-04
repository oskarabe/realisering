package realisering;
import java.util.ArrayList;
import oru.inf.InfDB;
import javax.swing.JOptionPane;
import oru.inf.InfException;

/**
 * Denna klass g�r det m�jligt att ta bort utrustning ur databasen
 */
public class TaBortUtrustning extends javax.swing.JFrame {

    private InfDB mib;
    
    /**
     * Konstruktor f�r TaBortUtrustning
     */
    public TaBortUtrustning(InfDB mib) {
        this.mib = mib;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNamnUtrustning = new javax.swing.JLabel();
        txtFldUtrustningNamn = new javax.swing.JTextField();
        btnTaBort = new javax.swing.JButton();
        Tillbaka = new javax.swing.JButton();
        namn = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        id = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblNamnUtrustning.setText("Skriv in namnet och ID-numret p� den utrustning du vill ta bort");

        btnTaBort.setText("Ta bort");
        btnTaBort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaBortActionPerformed(evt);
            }
        });

        Tillbaka.setText("Tillbaka");
        Tillbaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TillbakaActionPerformed(evt);
            }
        });

        namn.setText("Namn:");

        id.setText("ID:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(lblNamnUtrustning))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Tillbaka)
                            .addComponent(btnTaBort, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(namn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFldUtrustningNamn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(id)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblNamnUtrustning)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFldUtrustningNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id))
                .addGap(18, 18, 18)
                .addComponent(btnTaBort)
                .addGap(18, 18, 18)
                .addComponent(Tillbaka)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Knapptryck som g�r att utrustningen med det angivna namnet tas bort fr�n tabellerna:
    //Utrustning, Innehar_utrustning samt antingen Vapen, Teknik eller Kommunikation
    //beroende p� den typ av utrustning som ska tas bort
    private void btnTaBortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaBortActionPerformed
        String namnPaUtrustning = txtFldUtrustningNamn.getText();
        String utrustnings_id = txtId.getText();
        String hittaBenamning = ("select benamning from utrustning where utrustnings_id = " +
                                 utrustnings_id + " and benamning = '" + namnPaUtrustning + "'");
        String benamningFranDB = "";
        String deleteFromUtrustning = ("delete from utrustning where utrustnings_id = " + "(" +
                                        utrustnings_id + ")");
        String deleteFromInneharUtrustning = ("delete from innehar_utrustning where utrustnings_id = " + "(" +
                                        utrustnings_id + ")");
        String deleteFromVapen = ("delete from vapen where utrustnings_id = " + "(" +
                                        utrustnings_id + ")");
        String deleteFromTeknik = ("delete from teknik where utrustnings_id = " + "(" +
                                        utrustnings_id + ")");
        String deleteFromKom = ("delete from kommunikation where utrustnings_id = " + "(" +
                                        utrustnings_id + ")");
        
        //boolean-variabler f�r att se ifall utrustningen �r vapen, teknik eller kommunikation
        boolean isVapen = false;
        boolean isTeknik = false;
        boolean isKom = false;
        
            //Skapa listor f�r ID-v�rdena i tabellerna Vapen, Teknik och Kommunikation
            ArrayList<String> vapenLista = new ArrayList<>();
            ArrayList<String> teknikLista = new ArrayList<>();
            ArrayList<String> komLista = new ArrayList<>();
            
            String hamtaVapen_id = "select utrustnings_id from vapen";
            String hamtaTeknik_id = "select utrustnings_id from teknik";
            String hamtaKom_id = "select utrustnings_id from kommunikation";
            
            try
            {
            vapenLista = mib.fetchColumn(hamtaVapen_id);
            vapenLista = mib.fetchColumn(hamtaTeknik_id);
            vapenLista = mib.fetchColumn(hamtaKom_id);
            //Str�ng-variabel som kollar att v�rde finns i databasen
            benamningFranDB = mib.fetchSingle(hittaBenamning);
            }
            
            catch (InfException ettUndantag) {
                           JOptionPane.showMessageDialog(null, "Databasfel!");
                           System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                      } catch (Exception ettUndantag) {
                           JOptionPane.showMessageDialog(null, "N�got gick fel!");
                           System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                      }
                      
                            //G� igenom listorna f�r att se vilken typ av utrustning den angivna utrustningen �r
                            for(String vapenId : vapenLista)
                            {
                                if (vapenId.equals(utrustnings_id))
                                    isVapen = true;
                            }
                            for(String teknikId : teknikLista)
                            {
                                if (teknikId.equals(utrustnings_id))
                                    isTeknik = true;
                            }
                            for(String komId : komLista)
                            {
                                if (komId.equals(utrustnings_id))
                                    isKom = true;
                            }
                
                //Tar bort utrustning fr�n tabellerna Utrustning, Innehar_utrustning
                //samt Vapen, Teknik eller Kommunikation
                //F�rst validering f�r att textf�ltena inte ska vara tomt och att den angivna utrustningen
                //faktiskt finns i databasen
                 if (Validering.finnsText(txtFldUtrustningNamn) && Validering.finnsText(txtId)
                     && Validering.finnsIDB(benamningFranDB))
                 {
                      try {
                           mib.delete(deleteFromInneharUtrustning);
                           mib.delete(deleteFromUtrustning);
                           
                           if(isVapen)
                               mib.delete(deleteFromVapen);
                           if(isTeknik)
                               mib.delete(deleteFromTeknik);
                           if(isKom)
                               mib.delete(deleteFromKom);
                           
                           JOptionPane.showMessageDialog(null, "Utrustning " + namnPaUtrustning + " har tagits bort!");
                          }

                        catch (InfException ettUndantag) {
                           JOptionPane.showMessageDialog(null, "Databasfel!");
                           System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                      } catch (Exception ettUndantag) {
                           JOptionPane.showMessageDialog(null, "N�got gick fel!");
                           System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                      }
        }
    }//GEN-LAST:event_btnTaBortActionPerformed

    private void TillbakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TillbakaActionPerformed
        dispose();
        new HanteraUtrustning(mib).setVisible(true);
        //St�nger det aktuella f�nstret och tar anv�ndaren tillbaka till f�reg�ende f�nstret
    }//GEN-LAST:event_TillbakaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Tillbaka;
    private javax.swing.JButton btnTaBort;
    private javax.swing.JLabel id;
    private javax.swing.JLabel lblNamnUtrustning;
    private javax.swing.JLabel namn;
    private javax.swing.JTextField txtFldUtrustningNamn;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
}
