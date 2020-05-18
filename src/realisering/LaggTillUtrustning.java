/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realisering;

import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author lovee
 */
public class LaggTillUtrustning extends javax.swing.JFrame {

    private InfDB mib;
    
    /**
     * Creates new form LaggTillUtrustning
     */
    public LaggTillUtrustning(InfDB mib) {
        this.mib = mib;
        initComponents();
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

        btnLaggTill = new javax.swing.JButton();
        txtValjUtrustning = new javax.swing.JTextField();
        rdiobtnVapen = new javax.swing.JRadioButton();
        rdiobtnTeknik = new javax.swing.JRadioButton();
        rdiobtnKom = new javax.swing.JRadioButton();
        lblNamnUtrustning = new javax.swing.JLabel();
        lblMeddelande = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(369, 240));

        btnLaggTill.setText("Lägg till");
        btnLaggTill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillActionPerformed(evt);
            }
        });

        rdiobtnVapen.setText("Vapen");

        rdiobtnTeknik.setText("Teknik");

        rdiobtnKom.setText("Kommunikation");

        lblNamnUtrustning.setText("Skriv in namnet på den utrustning du vill lägga till");

        lblMeddelande.setText("Meddelande");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rdiobtnVapen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdiobtnTeknik)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdiobtnKom))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtValjUtrustning, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLaggTill, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(lblMeddelande, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblNamnUtrustning, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblNamnUtrustning)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValjUtrustning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLaggTill))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdiobtnVapen)
                    .addComponent(rdiobtnTeknik)
                    .addComponent(rdiobtnKom))
                .addGap(18, 18, 18)
                .addComponent(lblMeddelande)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLaggTillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillActionPerformed
        String utrustningNamn = txtValjUtrustning.getText();
        String insertNamn = "";
        
        try
        {
                insertNamn = "insert into utrustning values " + "(" + mib.getAutoIncrement("utrustning", "utrustnings_id") + ", " + "'" + utrustningNamn + "');";
                mib.insert(insertNamn);
                lblMeddelande.setText(utrustningNamn + " har lagts till!");
                        }
        
        catch (InfException ettUndantag) {
                    JOptionPane.showMessageDialog(null, "Databasfel!");
                    System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                }   catch (Exception ettUndantag) {
                    JOptionPane.showMessageDialog(null, "Något gick fel!");
                    System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                }
    }//GEN-LAST:event_btnLaggTillActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLaggTill;
    private javax.swing.JLabel lblMeddelande;
    private javax.swing.JLabel lblNamnUtrustning;
    private javax.swing.JRadioButton rdiobtnKom;
    private javax.swing.JRadioButton rdiobtnTeknik;
    private javax.swing.JRadioButton rdiobtnVapen;
    private javax.swing.JTextField txtValjUtrustning;
    // End of variables declaration//GEN-END:variables
}
