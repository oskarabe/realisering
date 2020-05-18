package realisering;
import oru.inf.InfDB;
import javax.swing.JOptionPane;
import oru.inf.InfException;

/**
 *
 * @author lovee
 * Denna klass gör det möjligt att ta bort utrustning ur databasen
 * Vet inte hur detta ska lösas dock, eftersom det finns en foreign_key constraint
 * på innehar_utrustning... Får error ifall man försöker köra ett delete statement på utrustning
 */
public class TaBortUtrustning extends javax.swing.JFrame {

    private InfDB mib;
    
    /**
     * Creates new form TaBortUtrustning
     */
    public TaBortUtrustning(InfDB mib) {
        this.mib = mib;
        initComponents();
        clearMeddelande();
    }

    private void clearMeddelande()
    {
        lblMeddelande.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNamnUtrustning = new javax.swing.JLabel();
        txtFldUtrustningNamn = new javax.swing.JTextField();
        btnTaBort = new javax.swing.JButton();
        lblMeddelande = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblNamnUtrustning.setText("Skriv in namnet på den utrustning du vill ta bort");

        btnTaBort.setText("Ta bort");
        btnTaBort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaBortActionPerformed(evt);
            }
        });

        lblMeddelande.setText("Meddelande");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 109, Short.MAX_VALUE)
                .addComponent(lblMeddelande, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(lblNamnUtrustning))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnTaBort, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtFldUtrustningNamn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblNamnUtrustning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFldUtrustningNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTaBort)
                .addGap(16, 16, 16)
                .addComponent(lblMeddelande)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Knapptryck som gör att utrustningen med det angivna namnet tas bort från databasen
    //Måste fixa för vapen, teknik och kommunikation också
    private void btnTaBortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaBortActionPerformed
        String namnPaUtrustning = txtFldUtrustningNamn.getText();
        String hittaUtrustnings_id = ("(select utrustnings_id from utrustning where benamning = " 
                                      + "'" + namnPaUtrustning + "')");
        String deleteFromUtrustning = ("delete from utrustning where utrustnings_id = " +
                                       hittaUtrustnings_id);
        String deleteFromInneharUtrustning = ("delete from innehar_utrustning where utrustnings_id = " +
                                       hittaUtrustnings_id);
        
        //Skulle behöva en if och loop för att se ifall det faktiskt finns en utrustning
        //med det valda namnet
       if (Validering.finnsText(txtFldUtrustningNamn))
        {
            try {
                mib.delete(deleteFromInneharUtrustning);
                mib.delete(deleteFromUtrustning);
                lblMeddelande.setText(namnPaUtrustning + " har tagits bort!");
                }

                catch (InfException ettUndantag) {
                    JOptionPane.showMessageDialog(null, "Databasfel!");
                    System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                }   catch (Exception ettUndantag) {
                    JOptionPane.showMessageDialog(null, "Något gick fel!");
                    System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                }
        }
    }//GEN-LAST:event_btnTaBortActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTaBort;
    private javax.swing.JLabel lblMeddelande;
    private javax.swing.JLabel lblNamnUtrustning;
    private javax.swing.JTextField txtFldUtrustningNamn;
    // End of variables declaration//GEN-END:variables
}
