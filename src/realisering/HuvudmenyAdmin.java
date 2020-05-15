package realisering;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author oskar
 * Klassen är till för att förse administratören med ett gränssnitt där de
 * kan utföra funktioner som vanliga agenter inte har
 */
public class HuvudmenyAdmin extends javax.swing.JFrame {

    private InfDB mib;
    private String agentID;
    
    /**
     * Creates new form HuvudmenyAgent
     */
    public HuvudmenyAdmin(InfDB mib) {
        this.mib = mib;
        initComponents();
        agentID = Login.getAgentID();
        setLblInloggNamn();
        setcBoxOmrade();
    }

    //Anger texten i lblInloggNamn
    private void setLblInloggNamn()
    {
        String hittaNamn = ("select namn from agent where agent_id = " + agentID);
        
        try {
        lblInloggNamn.setText("Du är inloggad som: " + mib.fetchSingle(hittaNamn));
            }
        
        catch (InfException ettUndantag) {
            JOptionPane.showMessageDialog(null, "Databasfel!");
            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
        } catch (Exception ettUndantag) {
            JOptionPane.showMessageDialog(null, "Något gick fel!");
            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
        }
    }
    
    //Anger värdena i cBoxOmrade
    private void setcBoxOmrade()
    {
        String hittaOmraden = "select benamning from omrade";
        ArrayList<String> allaOmraden;
        
        try {   
            allaOmraden = mib.fetchColumn(hittaOmraden);
            for (String omrade : allaOmraden) {
                cBoxOmrade.addItem(omrade);
            }            
            
        } catch (InfException ettUndantag) {
            JOptionPane.showMessageDialog(null, "Databasfel!");
            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
        }
        
        catch (Exception ettUndantag) {
            JOptionPane.showMessageDialog(null, "Något gick fel!");
            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAdministrator = new javax.swing.JLabel();
        lblInloggNamn = new javax.swing.JLabel();
        hanteraAgent = new javax.swing.JButton();
        tillbakaKnapp = new javax.swing.JButton();
        hanteraUtrustning = new javax.swing.JButton();
        cBoxOmrade = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabell = new javax.swing.JTable();
        lblOmrade = new javax.swing.JLabel();
        txtFldsokruta = new javax.swing.JTextField();
        hanteraAlien = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 500));
        setSize(new java.awt.Dimension(800, 500));

        lblAdministrator.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblAdministrator.setText("Administratör");

        lblInloggNamn.setText("Du är inloggad som:");

        hanteraAgent.setText("Hantera agent");
        hanteraAgent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hanteraAgentActionPerformed(evt);
            }
        });

        tillbakaKnapp.setText("Tillbaka");
        tillbakaKnapp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tillbakaKnappActionPerformed(evt);
            }
        });

        hanteraUtrustning.setText("Hantera utrustning");
        hanteraUtrustning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hanteraUtrustningActionPerformed(evt);
            }
        });

        tabell.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabell);

        lblOmrade.setText("Område");

        txtFldsokruta.setText("Sök agent...");

        hanteraAlien.setText("Hantera alien");
        hanteraAlien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hanteraAlienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cBoxOmrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFldsokruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(hanteraUtrustning)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hanteraAlien, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblInloggNamn)
                                    .addComponent(lblOmrade))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 339, Short.MAX_VALUE)
                                .addComponent(tillbakaKnapp, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hanteraAgent, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(39, 39, 39))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblAdministrator)
                .addGap(310, 310, 310))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblAdministrator)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblInloggNamn)
                                .addGap(42, 42, 42))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(hanteraAgent)
                                    .addComponent(tillbakaKnapp))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hanteraAlien)
                            .addComponent(hanteraUtrustning)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblOmrade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFldsokruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cBoxOmrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hanteraAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hanteraAgentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hanteraAgentActionPerformed

    private void tillbakaKnappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tillbakaKnappActionPerformed
        dispose();
    }//GEN-LAST:event_tillbakaKnappActionPerformed

    private void hanteraUtrustningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hanteraUtrustningActionPerformed
        new TaBortUtrustning(mib).setVisible(true);
    }//GEN-LAST:event_hanteraUtrustningActionPerformed

    private void hanteraAlienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hanteraAlienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hanteraAlienActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cBoxOmrade;
    private javax.swing.JButton hanteraAgent;
    private javax.swing.JButton hanteraAlien;
    private javax.swing.JButton hanteraUtrustning;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAdministrator;
    private javax.swing.JLabel lblInloggNamn;
    private javax.swing.JLabel lblOmrade;
    private javax.swing.JTable tabell;
    private javax.swing.JButton tillbakaKnapp;
    private javax.swing.JTextField txtFldsokruta;
    // End of variables declaration//GEN-END:variables
}
