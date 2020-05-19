package realisering;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author lovee
 */
public class LaggTillNyUtrustning extends javax.swing.JFrame {

    private InfDB mib;
    
    /**
     * Creates new form LaggTillUtrustning
     */
    public LaggTillNyUtrustning(InfDB mib) {
        this.mib = mib;
        initComponents();
        clearMeddelande();
        skapaRdoBtnGrupp();
    }

    //Töm meddelandet vid start
    private void clearMeddelande()
    {
        lblMeddelande.setText("");
    }

    //Skapa en grupp av radioButtons, för att hantera tillägandet av utrustning
    //Endast en radioButton ska kunna vara aktiv åt gången
    private void skapaRdoBtnGrupp()
    {
        ButtonGroup G = new ButtonGroup();
        G.add(rdiobtnVapen);
        G.add(rdiobtnTeknik);
        G.add(rdiobtnKom);
        rdiobtnVapen.setSelected(true);
    }
    
    //Anger texten för vilken typ av egenskap som ska skrivas in
    //Beror på vilken radioButton som är markerad
    private void setEgenskap
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        btnLaggTill = new javax.swing.JButton();
        txtValjUtrustning = new javax.swing.JTextField();
        rdiobtnVapen = new javax.swing.JRadioButton();
        rdiobtnTeknik = new javax.swing.JRadioButton();
        rdiobtnKom = new javax.swing.JRadioButton();
        lblMeddelande = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTyp = new javax.swing.JLabel();
        txtEgenskap = new javax.swing.JLabel();
        txtValjUtrustning1 = new javax.swing.JTextField();

        jLabel2.setText("Lägg till utrustning i din samling");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(369, 240));
        setResizable(false);

        btnLaggTill.setText("Lägg till");
        btnLaggTill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillActionPerformed(evt);
            }
        });

        txtValjUtrustning.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtValjUtrustningMouseClicked(evt);
            }
        });

        rdiobtnVapen.setText("Vapen");

        rdiobtnTeknik.setText("Teknik");

        rdiobtnKom.setText("Kommunikation");

        lblMeddelande.setText("Meddelande");

        jLabel3.setText("Ange namn och typ av utrustning att lägga till i företagets förråd");

        jLabel1.setText("Namn: ");

        txtTyp.setText("Typ: ");

        txtEgenskap.setText("Egenskap: ");

        txtValjUtrustning1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtValjUtrustning1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblMeddelande, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtTyp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(rdiobtnVapen)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(rdiobtnTeknik)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(rdiobtnKom))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtEgenskap)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtValjUtrustning1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(13, 13, 13)
                                            .addComponent(jLabel1)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtValjUtrustning, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(btnLaggTill, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValjUtrustning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdiobtnVapen)
                    .addComponent(rdiobtnTeknik)
                    .addComponent(rdiobtnKom)
                    .addComponent(txtTyp))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValjUtrustning1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEgenskap))
                .addGap(18, 18, 18)
                .addComponent(btnLaggTill)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(lblMeddelande)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Lägg till det angivna namnet på utrustning i utrustning-tabellen
    //och lägg till den nyregistrerade utrustningen i innehar_utrustning-tabellen.
    //Varje utrustning registreras i antingen vapen, teknik eller kommunikation-tabellen
    //Dagens datum läggs till för varje nyregistrerad utrustning
    private void btnLaggTillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillActionPerformed
        String utrustningNamn = txtValjUtrustning.getText();
        String insertIntoUtrustning = "";
        //String insertIntoInneharUtrustning = "";
        String nyttUtrustningsId = "";
        
        if(Validering.finnsText(txtValjUtrustning))
        {
        try
        {
                nyttUtrustningsId = mib.getAutoIncrement("utrustning", "utrustnings_id");
                insertIntoUtrustning = "insert into utrustning values " + "(" + nyttUtrustningsId + ", " + "'" + utrustningNamn + "');";
                //insertIntoInneharUtrustning = "insert into innehar_utrustning values " + "(" + Login.getAgentID() +
                                               //", " + nyttUtrustningsId + ", current_timestamp)";
                mib.insert(insertIntoUtrustning);
                //mib.insert(insertIntoInneharUtrustning);
                lblMeddelande.setText(utrustningNamn + " har lagts till i företagets förråd!");
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

    private void txtValjUtrustningMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtValjUtrustningMouseClicked
        txtValjUtrustning.setText("");
    }//GEN-LAST:event_txtValjUtrustningMouseClicked

    private void txtValjUtrustning1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtValjUtrustning1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValjUtrustning1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLaggTill;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblMeddelande;
    private javax.swing.JRadioButton rdiobtnKom;
    private javax.swing.JRadioButton rdiobtnTeknik;
    private javax.swing.JRadioButton rdiobtnVapen;
    private javax.swing.JLabel txtEgenskap;
    private javax.swing.JLabel txtTyp;
    private javax.swing.JTextField txtValjUtrustning;
    private javax.swing.JTextField txtValjUtrustning1;
    // End of variables declaration//GEN-END:variables
}
