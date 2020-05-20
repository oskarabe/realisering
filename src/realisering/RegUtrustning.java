package realisering;

import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author lovee
 */
public class RegUtrustning extends javax.swing.JFrame {

    private InfDB mib;
    
    /**
     * Creates new form RegUtrustning
     */
    public RegUtrustning(InfDB mib) {
        initComponents();
        this.mib = mib;
        clearMeddelande();
    }

    //Töm meddelandet vid start
    private void clearMeddelande()
    {
        lblMeddelande.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblRubrik = new javax.swing.JLabel();
        lblNamn = new javax.swing.JLabel();
        txtValjUtrustning = new javax.swing.JTextField();
        btnLaggTill = new javax.swing.JButton();
        lblMeddelande = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblRubrik.setText("Ange namnet på den utrustning du vill registrera i din samling");

        lblNamn.setText("Namn: ");

        btnLaggTill.setText("Lägg till");
        btnLaggTill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillActionPerformed(evt);
            }
        });

        lblMeddelande.setText("Meddelande");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(lblRubrik))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(btnLaggTill, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(lblMeddelande, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(96, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(102, 102, 102)
                    .addComponent(lblNamn)
                    .addGap(18, 18, 18)
                    .addComponent(txtValjUtrustning, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(163, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblRubrik)
                .addGap(77, 77, 77)
                .addComponent(btnLaggTill)
                .addGap(18, 18, 18)
                .addComponent(lblMeddelande)
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(70, 70, 70)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtValjUtrustning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNamn))
                    .addContainerGap(101, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Knapp för att registrera den angivna utrustningen till den nuvarande
    //användarens arsenal
    private void btnLaggTillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillActionPerformed
        String utrustningNamn = txtValjUtrustning.getText();
        String utrustningsId = "";
        String hittaUtrustningsId = "select utrustnings_id from utrustning " +
                                    "where benamning = " + "'" + utrustningNamn + "'";
        String insertIntoInneharUtrustning = "";

        //Validering för att se att textfältet är ifyllt
        //Om det utvärderas till true körs koden för att registrera utrustningen
        if(Validering.finnsText(txtValjUtrustning))
        {
            try
            {
                utrustningsId = mib.fetchSingle(hittaUtrustningsId);
                insertIntoInneharUtrustning = "insert into innehar_utrustning values " + "(" + Login.getAgentID() +
                                               ", " + utrustningsId + ", current_timestamp)";
                mib.insert(insertIntoInneharUtrustning);
                lblMeddelande.setText(utrustningNamn + " har registrerats bland dina utrustningar!");
            }

            catch (InfException ettUndantag) {
                JOptionPane.showMessageDialog(null, "Databasfel!");
                System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
            }   catch (Exception ettUndantag) {
                JOptionPane.showMessageDialog(null, "Något gick fel!");
                System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
            }
        }
    }//GEN-LAST:event_btnLaggTillActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLaggTill;
    private javax.swing.JLabel lblMeddelande;
    private javax.swing.JLabel lblNamn;
    private javax.swing.JLabel lblRubrik;
    private javax.swing.JTextField txtValjUtrustning;
    // End of variables declaration//GEN-END:variables
}
