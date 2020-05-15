package realisering;
import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;
/**
 *
 * @author lovee
 * Denna klass används för att se vilka utrustningar som finns registrerade.
 * Klassen ger även möjlighet att ta sig till nya fönster som kan
 * lägga till och ta bort utrustning
 */
public class RegistreraUtrustning extends javax.swing.JFrame {

    private InfDB mib;
    
    /**
     * Creates new form RegistreraUtrustning
     */
    public RegistreraUtrustning(InfDB mib) {
        this.mib = mib;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tble = new javax.swing.JTable();
        btnTaBort = new javax.swing.JButton();
        btnLaggTill = new javax.swing.JButton();
        txtValjNamn = new javax.swing.JTextField();
        rdiobtnVapen = new javax.swing.JRadioButton();
        rdiobtnTeknik = new javax.swing.JRadioButton();
        rdiobtnKom = new javax.swing.JRadioButton();
        lblUtrustning = new javax.swing.JLabel();
        sokruta = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tble.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "UtrustningsId", "Benämning"
            }
        ));
        jScrollPane1.setViewportView(tble);

        btnTaBort.setText("Ta bort utrustning");
        btnTaBort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaBortActionPerformed(evt);
            }
        });

        btnLaggTill.setText("Lägg till");

        txtValjNamn.setText("Välj namn på ny utrustning...");

        rdiobtnVapen.setText("Vapen");

        rdiobtnTeknik.setText("Teknik");

        rdiobtnKom.setText("Kommunikation");

        lblUtrustning.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUtrustning.setText("Utrustning");

        sokruta.setText("Sök utrustning...");
        sokruta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sokrutaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(lblUtrustning))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(sokruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTaBort)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rdiobtnVapen)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdiobtnTeknik)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdiobtnKom))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtValjNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnLaggTill, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUtrustning, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sokruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaBort)
                    .addComponent(btnLaggTill)
                    .addComponent(txtValjNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdiobtnVapen)
                    .addComponent(rdiobtnTeknik)
                    .addComponent(rdiobtnKom))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaBortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaBortActionPerformed
        new TaBortUtrustning(mib).setVisible(true);
    }//GEN-LAST:event_btnTaBortActionPerformed

    private void sokrutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sokrutaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sokrutaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLaggTill;
    private javax.swing.JButton btnTaBort;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblUtrustning;
    private javax.swing.JRadioButton rdiobtnKom;
    private javax.swing.JRadioButton rdiobtnTeknik;
    private javax.swing.JRadioButton rdiobtnVapen;
    private javax.swing.JTextField sokruta;
    private javax.swing.JTable tble;
    private javax.swing.JTextField txtValjNamn;
    // End of variables declaration//GEN-END:variables
}
