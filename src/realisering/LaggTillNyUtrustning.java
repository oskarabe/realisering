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
        setEgenskap();
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
        setEgenskap();
    }
    
    //Anger texten för vilken typ av egenskap som ska skrivas in
    //Beror på vilken radioButton som är markerad
    private void setEgenskap()
    {
        if(rdiobtnVapen.isSelected())
            txtEgenskap.setText("Kaliber");
        else if(rdiobtnTeknik.isSelected())
            txtEgenskap.setText("Kraftkälla");
                    else if(rdiobtnKom.isSelected())
            txtEgenskap.setText("Överföringsteknik");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        lblRubrik1 = new javax.swing.JLabel();
        btnLaggTill = new javax.swing.JButton();
        txtValjUtrustning = new javax.swing.JTextField();
        rdiobtnVapen = new javax.swing.JRadioButton();
        rdiobtnTeknik = new javax.swing.JRadioButton();
        rdiobtnKom = new javax.swing.JRadioButton();
        lblMeddelande = new javax.swing.JLabel();
        lblRubrik = new javax.swing.JLabel();
        lblNamn = new javax.swing.JLabel();
        txtTyp = new javax.swing.JLabel();
        txtEgenskap = new javax.swing.JLabel();
        txtValjEgenskap = new javax.swing.JTextField();
        lblRubrik2 = new javax.swing.JLabel();

        jLabel2.setText("Lägg till utrustning i din samling");

        lblRubrik1.setText("Ange namn, typ av utrustning och egenskap för utrustningen");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 330));
        setResizable(false);

        btnLaggTill.setText("Lägg till");
        btnLaggTill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillActionPerformed(evt);
            }
        });

        rdiobtnVapen.setText("Vapen");
        rdiobtnVapen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdiobtnVapenActionPerformed(evt);
            }
        });

        rdiobtnTeknik.setText("Teknik");
        rdiobtnTeknik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdiobtnTeknikActionPerformed(evt);
            }
        });

        rdiobtnKom.setText("Kommunikation");
        rdiobtnKom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdiobtnKomActionPerformed(evt);
            }
        });

        lblMeddelande.setText("Meddelande");

        lblRubrik.setText("Ange namn, typ och egenskap för utrustningen");

        lblNamn.setText("Namn: ");

        txtTyp.setText("Typ: ");

        txtEgenskap.setText("Egenskap: ");

        txtValjEgenskap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtValjEgenskapMouseClicked(evt);
            }
        });

        lblRubrik2.setText("Utrustningen läggs sedan till i förrådsregistret");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMeddelande, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblRubrik, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblRubrik2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtTyp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(rdiobtnVapen)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(rdiobtnTeknik)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(rdiobtnKom))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(txtEgenskap)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtValjEgenskap, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(29, 29, 29)
                            .addComponent(lblNamn)
                            .addGap(18, 18, 18)
                            .addComponent(txtValjUtrustning, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(39, 39, 39))
            .addGroup(layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(btnLaggTill, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRubrik, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRubrik2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValjUtrustning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNamn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdiobtnVapen)
                    .addComponent(rdiobtnTeknik)
                    .addComponent(rdiobtnKom)
                    .addComponent(txtTyp))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValjEgenskap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEgenskap))
                .addGap(18, 18, 18)
                .addComponent(btnLaggTill)
                .addGap(18, 18, 18)
                .addComponent(lblMeddelande)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Lägg till det angivna namnet på utrustning i utrustning-tabellen
    //och lägg till den nyregistrerade utrustningen i innehar_utrustning-tabellen.
    //Varje utrustning registreras i antingen vapen, teknik eller kommunikation-tabellen
    //Dagens datum läggs till för varje nyregistrerad utrustning
    private void btnLaggTillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillActionPerformed
        String utrustningNamn = txtValjUtrustning.getText();
        String egenskapVarde = txtValjEgenskap.getText();
        String insertIntoUtrustning = "";
        String nyttUtrustningsId = "";
        String insertIntoVapen = "";
        String insertIntoTeknik = "";
        String insertIntoKom = "";
        
        //Validering för att se att båda textfälten är ifyllda
        if((Validering.finnsText(txtValjUtrustning)) && (Validering.finnsText(txtValjEgenskap)))
        {
        try
        {
                //Lägga till i utrustning-tabellen först
                nyttUtrustningsId = mib.getAutoIncrement("utrustning", "utrustnings_id");
                insertIntoUtrustning = "insert into utrustning values " + "(" + nyttUtrustningsId + ", " + "'" + utrustningNamn + "');";
                mib.insert(insertIntoUtrustning);
                
                //Sedan lägga till i vapen, teknik, eller kommunikation-tabellen
                if(rdiobtnVapen.isSelected())
                {
                    insertIntoVapen = "insert into vapen values " + "(" + nyttUtrustningsId +
                                       ", " + egenskapVarde + ")";
                    mib.insert(insertIntoVapen);
                }
                else if(rdiobtnTeknik.isSelected())
                {
                    insertIntoTeknik = "insert into teknik values " + "(" + nyttUtrustningsId +
                                       ", '" + egenskapVarde + "')";
                    mib.insert(insertIntoTeknik);
                }
                else if(rdiobtnKom.isSelected())
                {
                    insertIntoKom = "insert into kommunikation values " + "(" + nyttUtrustningsId +
                                       ", '" + egenskapVarde + "')";
                    mib.insert(insertIntoKom);
                }
                
                lblMeddelande.setText("Ny utrustning " + utrustningNamn + " har lagts till i förrådsregistret!");
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

    private void txtValjEgenskapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtValjEgenskapMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValjEgenskapMouseClicked

    private void rdiobtnVapenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdiobtnVapenActionPerformed
        setEgenskap();
    }//GEN-LAST:event_rdiobtnVapenActionPerformed

    private void rdiobtnTeknikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdiobtnTeknikActionPerformed
        setEgenskap();
    }//GEN-LAST:event_rdiobtnTeknikActionPerformed

    private void rdiobtnKomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdiobtnKomActionPerformed
        setEgenskap();
    }//GEN-LAST:event_rdiobtnKomActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLaggTill;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblMeddelande;
    private javax.swing.JLabel lblNamn;
    private javax.swing.JLabel lblRubrik;
    private javax.swing.JLabel lblRubrik1;
    private javax.swing.JLabel lblRubrik2;
    private javax.swing.JRadioButton rdiobtnKom;
    private javax.swing.JRadioButton rdiobtnTeknik;
    private javax.swing.JRadioButton rdiobtnVapen;
    private javax.swing.JLabel txtEgenskap;
    private javax.swing.JLabel txtTyp;
    private javax.swing.JTextField txtValjEgenskap;
    private javax.swing.JTextField txtValjUtrustning;
    // End of variables declaration//GEN-END:variables
}
