package realisering;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.*;
import javax.swing.*;
import oru.inf.*;

/**
 * Klassen förser användaren, i det här fallet en alien, med ett gränssnitt där
 * de kan se information om sitt område och sin områdeschef
 */
public class HuvudmenyAlien extends javax.swing.JFrame {

                private String alienID;
                private InfDB mib;

                /**
                 * Konstruktor för HuvudmenyAlien
                 */
                public HuvudmenyAlien(InfDB mib) {
                                initComponents();
                                this.mib = mib;
                                alienID = Login.getAlienID();
                                setLabelInloggNamn();
                                setLabelAlienOmrade();
                                setLabelOmradeschef();
                                setListaAlienNamn();

                }

                @SuppressWarnings("unchecked")
                private void initComponents() {//GEN-BEGIN:initComponents

                                btnTillbakaInlogg = new javax.swing.JButton();
                                lblOmradeTillhorighet = new javax.swing.JLabel();
                                lblHuvudmenyAlien = new javax.swing.JLabel();
                                lblinloggNamn = new javax.swing.JLabel();
                                lblAlienOmrade = new javax.swing.JLabel();
                                lblOmradeschef = new javax.swing.JLabel();
                                Tillbaka = new javax.swing.JButton();
                                andraLosenord = new javax.swing.JButton();
                                skickaEpost = new javax.swing.JButton();
                                lblAliens = new javax.swing.JLabel();
                                jScrollPane1 = new javax.swing.JScrollPane();
                                listaAlienNamn = new javax.swing.JList<>();

                                btnTillbakaInlogg.setText("Tillbaka till inlogg");
                                btnTillbakaInlogg.addActionListener(new java.awt.event.ActionListener() {
                                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                btnTillbakaInloggActionPerformed(evt);
                                                }
                                });

                                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                                setPreferredSize(new java.awt.Dimension(600, 300));
                                setResizable(false);

                                lblHuvudmenyAlien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                                lblHuvudmenyAlien.setText("Huvudmeny - Alien");

                                lblinloggNamn.setText("Du är inloggad som: ");

                                lblAlienOmrade.setText("Du tillhör område: ");

                                lblOmradeschef.setText("Din områdeschef är:");
                                lblOmradeschef.setMaximumSize(new java.awt.Dimension(350, 16));
                                lblOmradeschef.setMinimumSize(new java.awt.Dimension(110, 16));
                                lblOmradeschef.setPreferredSize(new java.awt.Dimension(300, 16));

                                Tillbaka.setText("Tillbaka");
                                Tillbaka.setMaximumSize(new java.awt.Dimension(160, 28));
                                Tillbaka.setPreferredSize(new java.awt.Dimension(160, 28));
                                Tillbaka.addActionListener(new java.awt.event.ActionListener() {
                                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                TillbakaActionPerformed(evt);
                                                }
                                });

                                andraLosenord.setText("Ändra lösenord");
                                andraLosenord.setMaximumSize(new java.awt.Dimension(160, 28));
                                andraLosenord.setPreferredSize(new java.awt.Dimension(160, 28));
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

                                skickaEpost.setText("Skicka e-post till chef");
                                skickaEpost.setMaximumSize(new java.awt.Dimension(160, 28));
                                skickaEpost.setMinimumSize(new java.awt.Dimension(120, 28));
                                skickaEpost.setPreferredSize(new java.awt.Dimension(160, 28));
                                skickaEpost.addMouseListener(new java.awt.event.MouseAdapter() {
                                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                skickaEpostMouseClicked(evt);
                                                }
                                });
                                skickaEpost.addActionListener(new java.awt.event.ActionListener() {
                                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                skickaEpostActionPerformed(evt);
                                                }
                                });

                                lblAliens.setText("Andra aliens i området:");

                                listaAlienNamn.setModel(new javax.swing.AbstractListModel<String>() {
                                                String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
                                                public int getSize() { return strings.length; }
                                                public String getElementAt(int i) { return strings[i]; }
                                });
                                jScrollPane1.setViewportView(listaAlienNamn);

                                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                                getContentPane().setLayout(layout);
                                layout.setHorizontalGroup(
                                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                                                .addComponent(lblHuvudmenyAlien))
                                                                                                                .addComponent(lblAliens, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                                                .addGap(217, 217, 217)
                                                                                                                                                                .addComponent(lblOmradeTillhorighet))
                                                                                                                                                .addComponent(lblAlienOmrade, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(lblinloggNamn, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(lblOmradeschef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                .addGap(0, 22, Short.MAX_VALUE)))
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(skickaEpost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(Tillbaka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(andraLosenord, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addContainerGap())
                                );
                                layout.setVerticalGroup(
                                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGap(14, 14, 14)
                                                                .addComponent(lblHuvudmenyAlien, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(lblinloggNamn)
                                                                                .addComponent(Tillbaka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                                                .addGap(29, 29, 29)
                                                                                                                                                                .addComponent(lblOmradeTillhorighet))
                                                                                                                                                .addComponent(lblAlienOmrade))
                                                                                                                                .addGap(28, 28, 28))
                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                                                                .addComponent(lblOmradeschef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGap(18, 18, 18)))
                                                                                                .addComponent(lblAliens))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                                .addComponent(andraLosenord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(skickaEpost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(9, 9, 9)
                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(28, 28, 28))
                                );

                                pack();
                }//GEN-END:initComponents

                //Anger namnet på alien i label lblinloggNamn
                private void setLabelInloggNamn() {
                                String hittaNamn = ("select namn from alien where alien_id = " + alienID);

                                try {
                                                lblinloggNamn.setText("Du är inloggad som: " + mib.fetchSingle(hittaNamn));
                                } catch (InfException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Databasfel!");
                                                System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                                } catch (Exception ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Något gick fel!");
                                                System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                                }
                }

                //Anger området alien tillhör i label lblAlienOmrade
                private void setLabelAlienOmrade() {
                                String hittaOmrade = ("select omrade.benamning from alien, plats, omrade where "
                                        + "plats = plats_id and finns_i = omrades_id and "
                                        + "alien_id = " + alienID);

                                try {
                                                lblAlienOmrade.setText("Du tillhör område: " + mib.fetchSingle(hittaOmrade));
                                } catch (InfException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Databasfel!");
                                                System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                                } catch (Exception ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Något gick fel!");
                                                System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                                }
                }

                //Anger områdeschefen för aktuell alien i label lblOmradesChef
                private void setLabelOmradeschef() {
                                String hittaOmradeschefNamn = ("select agent.namn from agent, omradeschef "
                                        + "where agent.agent_id = omradeschef.agent_id "
                                        + "and omradeschef.omrade = ");

                                String hittaOmradesId = ("(select omrades_id from alien, plats, omrade "
                                        + "where plats = plats_id and finns_i = omrades_id and alien_id = "
                                        + alienID + ")");

                                String hittaOmradeschefTelefon = ("select agent.telefon from agent, omradeschef "
                                        + "where agent.agent_id = omradeschef.agent_id "
                                        + "and omradeschef.omrade = ");

                                try {
                                                lblOmradeschef.setText("Din områdeschef är: " + mib.fetchSingle(hittaOmradeschefNamn + hittaOmradesId)
                                                        + " (telefon: " + mib.fetchSingle(hittaOmradeschefTelefon + hittaOmradesId) + ")");
                                } catch (InfException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Databasfel!");
                                                System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                                } catch (Exception ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Något gick fel!");
                                                System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                                }
                }

                // Metod för att lista alla alien i samma område som användaren
                private void setListaAlienNamn() {
                                try {
                                                listaAlienNamn.removeAll();
                                                String hittaOmrade = ("(select omrade.omrades_id from alien, plats, omrade where "
                                                        + "plats = plats_id and finns_i = omrades_id and "
                                                        + "alien_id = " + alienID + ")");
                                                Vector<String> alienNamn = new Vector<>();
                                                addNotNull(alienNamn, mib.fetchColumn("SELECT NAMN FROM ALIEN WHERE PLATS = ANY (SELECT PLATS_ID FROM PLATS WHERE FINNS_I = " + hittaOmrade + ")"));
                                                if (alienNamn.isEmpty()) {
                                                                alienNamn.add("Ensam i området.");
                                                }
                                                listaAlienNamn.setListData(alienNamn);

                                } catch (InfException ex) {
                                                ex.printError();
                                }
                }

                // För att undvika problem med SQL-frågor som returnerar null
                public static <E> void addNotNull(List<E> list, Collection<? extends E> c) {
                                if (c != null) {
                                                list.addAll(c);
                                }
                }

    private void btnTillbakaInloggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaInloggActionPerformed
                                //Används ej
    }//GEN-LAST:event_btnTillbakaInloggActionPerformed

    private void TillbakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TillbakaActionPerformed
                                dispose();
                                new Login(mib).setVisible(true);
                                //Återvänder till Login-fönstret
    }//GEN-LAST:event_TillbakaActionPerformed

    private void andraLosenordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_andraLosenordActionPerformed
                                // TODO add your handling code here:
    }//GEN-LAST:event_andraLosenordActionPerformed

    private void andraLosenordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_andraLosenordMouseClicked
                                new AndraLosenord(mib).setVisible(true);
                                dispose();
    }//GEN-LAST:event_andraLosenordMouseClicked

                private void skickaEpostMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_skickaEpostMouseClicked

                                try {
                                                Desktop.getDesktop().browse(new URI("mailto:omradeschef@mib.se"));
                                } catch (URISyntaxException | IOException ex) {
                                }

                }//GEN-LAST:event_skickaEpostMouseClicked

                private void skickaEpostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skickaEpostActionPerformed
                                // TODO add your handling code here:
                }//GEN-LAST:event_skickaEpostActionPerformed

                // Variables declaration - do not modify//GEN-BEGIN:variables
                private javax.swing.JButton Tillbaka;
                private javax.swing.JButton andraLosenord;
                private javax.swing.JButton btnTillbakaInlogg;
                private javax.swing.JScrollPane jScrollPane1;
                private javax.swing.JLabel lblAlienOmrade;
                private javax.swing.JLabel lblAliens;
                private javax.swing.JLabel lblHuvudmenyAlien;
                private javax.swing.JLabel lblOmradeTillhorighet;
                private javax.swing.JLabel lblOmradeschef;
                private javax.swing.JLabel lblinloggNamn;
                private javax.swing.JList<String> listaAlienNamn;
                private javax.swing.JButton skickaEpost;
                // End of variables declaration//GEN-END:variables
}
