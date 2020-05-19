package realisering;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;
/**
 *
 * @author lovee
 * Denna klass anv�nds f�r att se vilka utrustningar som finns registrerade.
 * Klassen ger �ven m�jlighet att ta sig till nya f�nster som kan
 * l�gga till och ta bort utrustning
 */
public class HanteraUtrustning extends javax.swing.JFrame {

    private InfDB mib;
    
    /**
     * Creates new form LaggTillNyUtrustning
     */
    public HanteraUtrustning(InfDB mib) {
        this.mib = mib;
        initComponents();
        checkAdminStatus();
    }

    //Ifall anv�ndaren inte har adminstatus inaktiveras btnTaBort
    private void checkAdminStatus()
    {
        if(!Login.getAdmin())
        {
            btnTaBort.setEnabled(false);
        }
        else
            btnTaBort.setEnabled(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tble = new javax.swing.JTable();
        btnTaBort = new javax.swing.JButton();
        lblUtrustning = new javax.swing.JLabel();
        sokruta = new javax.swing.JTextField();
        btnLaggTill = new javax.swing.JButton();
        btnReg = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tble.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Utrustnings-ID", "Ben�mning", "Innehas av", "Datum"
            }
        ));
        jScrollPane1.setViewportView(tble);

        btnTaBort.setText("Ta bort utrustning");
        btnTaBort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaBortActionPerformed(evt);
            }
        });

        lblUtrustning.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUtrustning.setText("Utrustning");

        sokruta.setText("S�k utrustning...");
        sokruta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sokrutaActionPerformed(evt);
            }
        });

        btnLaggTill.setText("L�gg till ny utrustning");
        btnLaggTill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillActionPerformed(evt);
            }
        });

        btnReg.setText("Registrera utrustning");
        btnReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sokruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnLaggTill)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnReg)
                        .addGap(18, 18, 18)
                        .addComponent(btnTaBort))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(lblUtrustning)))
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
                    .addComponent(btnLaggTill)
                    .addComponent(btnReg)
                    .addComponent(btnTaBort))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaBortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaBortActionPerformed
        new TaBortUtrustning(mib).setVisible(true);
    }//GEN-LAST:event_btnTaBortActionPerformed

    private void sokrutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sokrutaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sokrutaActionPerformed

    private void btnLaggTillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillActionPerformed
        new LaggTillNyUtrustning(mib).setVisible(true);
    }//GEN-LAST:event_btnLaggTillActionPerformed

    private void btnRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegActionPerformed
        new RegUtrustning(mib).setVisible(true);
    }//GEN-LAST:event_btnRegActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLaggTill;
    private javax.swing.JButton btnReg;
    private javax.swing.JButton btnTaBort;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblUtrustning;
    private javax.swing.JTextField sokruta;
    private javax.swing.JTable tble;
    // End of variables declaration//GEN-END:variables
}