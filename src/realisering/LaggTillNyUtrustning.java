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

    //T�m meddelandet vid start
    private void clearMeddelande()
    {
        lblMeddelande.setText("");
    }

    //Skapa en grupp av radioButtons, f�r att hantera till�gandet av utrustning
    //Endast en radioButton ska kunna vara aktiv �t g�ngen
    private void skapaRdoBtnGrupp()
    {
        ButtonGroup G = new ButtonGroup();
        G.add(rdiobtnVapen);
        G.add(rdiobtnTeknik);
        G.add(rdiobtnKom);
        rdiobtnVapen.setSelected(true);
        setEgenskap();
    }
    
    //Anger texten f�r vilken typ av egenskap som ska skrivas in
    //Beror p� vilken radioButton som �r markerad
    private void setEgenskap()
    {
        if(rdiobtnVapen.isSelected())
            txtEgenskap.setText("Kaliber");
        else if(rdiobtnTeknik.isSelected())
            txtEgenskap.setText("Kraftk�lla");
                    else if(rdiobtnKom.isSelected())
            txtEgenskap.setText("�verf�ringsteknik");
    }
    
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
        lblRubrik = new javax.swing.JLabel();
        lblNamn = new javax.swing.JLabel();
        txtTyp = new javax.swing.JLabel();
        txtEgenskap = new javax.swing.JLabel();
        txtValjEgenskap = new javax.swing.JTextField();

        jLabel2.setText("L�gg till utrustning i din samling");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(420, 280));

        btnLaggTill.setText("L�gg till");
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

        lblRubrik.setText("Ange namn och typ av utrustning att l�gga till i f�retagets f�rr�d");

        lblNamn.setText("Namn: ");

        txtTyp.setText("Typ: ");

        txtEgenskap.setText("Egenskap: ");

        txtValjEgenskap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtValjEgenskapMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                                        .addComponent(txtValjEgenskap, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(lblNamn)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtValjUtrustning, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblMeddelande, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(btnLaggTill, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(65, 65, 65))
                    .addComponent(lblRubrik, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRubrik, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //L�gg till det angivna namnet p� utrustning i utrustning-tabellen
    //och l�gg till den nyregistrerade utrustningen i innehar_utrustning-tabellen.
    //Varje utrustning registreras i antingen vapen, teknik eller kommunikation-tabellen
    //Dagens datum l�ggs till f�r varje nyregistrerad utrustning
    private void btnLaggTillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillActionPerformed
        String utrustningNamn = txtValjUtrustning.getText();
        String egenskapVarde = txtValjEgenskap.getText();
        String insertIntoUtrustning = "";
        String nyttUtrustningsId = "";
        String insertIntoVapen = "";
        String insertIntoTeknik = "";
        String insertIntoKom = "";
        
        //Validering f�r att se att b�da textf�lten �r ifyllda
        if((Validering.finnsText(txtValjUtrustning)) && (Validering.finnsText(txtValjEgenskap)))
        {
        try
        {
                //L�gga till i utrustning-tabellen f�rst
                nyttUtrustningsId = mib.getAutoIncrement("utrustning", "utrustnings_id");
                insertIntoUtrustning = "insert into utrustning values " + "(" + nyttUtrustningsId + ", " + "'" + utrustningNamn + "');";
                mib.insert(insertIntoUtrustning);
                
                //Sedan l�gga till i vapen, teknik, eller kommunikation-tabellen
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
                
                lblMeddelande.setText(utrustningNamn + " har lagts till i f�retagets f�rr�d!");
                        }
        
        catch (InfException ettUndantag) {
                    JOptionPane.showMessageDialog(null, "Databasfel!");
                    System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                }   catch (Exception ettUndantag) {
                    JOptionPane.showMessageDialog(null, "N�got gick fel!");
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
    private javax.swing.JRadioButton rdiobtnKom;
    private javax.swing.JRadioButton rdiobtnTeknik;
    private javax.swing.JRadioButton rdiobtnVapen;
    private javax.swing.JLabel txtEgenskap;
    private javax.swing.JLabel txtTyp;
    private javax.swing.JTextField txtValjEgenskap;
    private javax.swing.JTextField txtValjUtrustning;
    // End of variables declaration//GEN-END:variables
}
