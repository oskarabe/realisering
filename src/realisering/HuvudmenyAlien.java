package realisering;

import javax.swing.*;
import oru.inf.*;

/**
 * Klassen f�rser anv�ndaren, i det h�r fallet en alien, med ett gr�nssnitt
 * d�r de kan se information om sitt omr�de och sin omr�deschef
 */
public class HuvudmenyAlien extends javax.swing.JFrame {

    private String alienID;
    private InfDB mib;
    
    /**
     * Konstruktor f�r HuvudmenyAlien
     */
    public HuvudmenyAlien(InfDB mib) {
        initComponents();
        this.mib = mib;
        alienID = Login.getAlienID();
        setLabelInloggNamn();
        setLabelAlienOmrade();
        setLabelOmradeschef();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnTillbakaInlogg = new javax.swing.JButton();
        lblOmradeTillhorighet = new javax.swing.JLabel();
        lblHuvudmenyAlien = new javax.swing.JLabel();
        lblinloggNamn = new javax.swing.JLabel();
        lblAlienOmrade = new javax.swing.JLabel();
        lblOmradeschef = new javax.swing.JLabel();
        Tillbaka = new javax.swing.JButton();
        andraLosenord = new javax.swing.JButton();

        btnTillbakaInlogg.setText("Tillbaka till inlogg");
        btnTillbakaInlogg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaInloggActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(450, 250));
        setResizable(false);

        lblHuvudmenyAlien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblHuvudmenyAlien.setText("Huvudmeny - Alien");

        lblinloggNamn.setText("Du �r inloggad som: ");

        lblAlienOmrade.setText("Du tillh�r omr�de: ");

        lblOmradeschef.setText("Din omr�deschef �r:");

        Tillbaka.setText("Tillbaka");
        Tillbaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TillbakaActionPerformed(evt);
            }
        });

        andraLosenord.setText("�ndra l�senord");
        andraLosenord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                andraLosenordMouseClicked(evt);
            }
        });
        andraLosenord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                andraLosenordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(lblHuvudmenyAlien)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblOmradeschef, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(174, 174, 174))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAlienOmrade, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblOmradeTillhorighet))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(andraLosenord))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblinloggNamn, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(94, 94, 94)
                                .addComponent(Tillbaka, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(65, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblHuvudmenyAlien, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblinloggNamn)
                    .addComponent(Tillbaka))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAlienOmrade)
                    .addComponent(andraLosenord))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblOmradeTillhorighet)
                .addGap(15, 15, 15)
                .addComponent(lblOmradeschef)
                .addGap(63, 63, 63))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Anger namnet p� alien i label lblinloggNamn
    private void setLabelInloggNamn()
    {
        String hittaNamn = ("select namn from alien where alien_id = " + alienID);
        
        try {
        lblinloggNamn.setText("Du �r inloggad som: " + mib.fetchSingle(hittaNamn));
            }
        
        catch (InfException ettUndantag) {
            JOptionPane.showMessageDialog(null, "Databasfel!");
            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
        } catch (Exception ettUndantag) {
            JOptionPane.showMessageDialog(null, "N�got gick fel!");
            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
        }
    }
    
    //Anger omr�det alien tillh�r i label lblAlienOmrade
    private void setLabelAlienOmrade()
    {
        String hittaOmrade = ("select omrade.benamning from alien, plats, omrade where " +
                                "plats = plats_id and finns_i = omrades_id and " +
                                "alien_id = " + alienID);
        
        try {
        lblAlienOmrade.setText("Du tillh�r omr�de: " + mib.fetchSingle(hittaOmrade));
            }
        
        catch (InfException ettUndantag) {
            JOptionPane.showMessageDialog(null, "Databasfel!");
            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
        } catch (Exception ettUndantag) {
            JOptionPane.showMessageDialog(null, "N�got gick fel!");
            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
        }
    }
    
   //Anger omr�deschefen f�r aktuell alien i label lblOmradesChef
   private void setLabelOmradeschef()
    {
        String hittaOmradeschefNamn = ("select agent.namn from agent, omradeschef " +
                                   "where agent.agent_id = omradeschef.agent_id " +
                                    "and omradeschef.omrade = ");
        
        String hittaOmradesId = ("(select omrades_id from alien, plats, omrade " +
                "where plats = plats_id and finns_i = omrades_id and alien_id = " +
                alienID + ")");
        
        String hittaOmradeschefTelefon = ("select agent.telefon from agent, omradeschef " +
                                   "where agent.agent_id = omradeschef.agent_id " +
                                    "and omradeschef.omrade = ");
        
        try {
        lblOmradeschef.setText("Din omr�deschef �r: " + mib.fetchSingle(hittaOmradeschefNamn + hittaOmradesId)
        + " (telefon: " + mib.fetchSingle(hittaOmradeschefTelefon + hittaOmradesId) + ")");
            }
        
        catch (InfException ettUndantag) {
            JOptionPane.showMessageDialog(null, "Databasfel!");
            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
        } catch (Exception ettUndantag) {
            JOptionPane.showMessageDialog(null, "N�got gick fel!");
            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
        }
    }
    
    private void btnTillbakaInloggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaInloggActionPerformed
        //Anv�nds ej
    }//GEN-LAST:event_btnTillbakaInloggActionPerformed

    private void TillbakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TillbakaActionPerformed
        dispose();
        new Login(mib).setVisible(true);
        //�terv�nder till Login-f�nstret
    }//GEN-LAST:event_TillbakaActionPerformed

    private void andraLosenordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_andraLosenordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_andraLosenordActionPerformed

    private void andraLosenordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_andraLosenordMouseClicked
        new AndraLosenord(mib).setVisible(true);
        dispose();
    }//GEN-LAST:event_andraLosenordMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Tillbaka;
    private javax.swing.JButton andraLosenord;
    private javax.swing.JButton btnTillbakaInlogg;
    private javax.swing.JLabel lblAlienOmrade;
    private javax.swing.JLabel lblHuvudmenyAlien;
    private javax.swing.JLabel lblOmradeTillhorighet;
    private javax.swing.JLabel lblOmradeschef;
    private javax.swing.JLabel lblinloggNamn;
    // End of variables declaration//GEN-END:variables
}
