package realisering;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import oru.inf.*;

/**
 *
 * @author oskar
 */
public class HuvudmenyAdmin extends javax.swing.JFrame {

                private InfDB mib;
                private String agentID;
                private String omID;
                private ComboBoxModel lvBox;
                private DefaultTableModel model;
                private String agentLista, omradeSok, namnSok;

                /**
                 * Creates new form HuvudmenyAgent
                 *
                 * @param mib
                 */
                public HuvudmenyAdmin(InfDB mib) {

                                this.mib = mib;
                                agentID = Login.getAgentID();
                                initComponents();
                                setLabelInloggNamn();
                                setOmraden();
                                agentLista = "SELECT AGENT_ID, NAMN, TELEFON, ANSTALLNINGSDATUM, ADMINISTRATOR, OMRADE FROM AGENT";
                                omradeSok = agentLista + " WHERE OMRADE = ";
                                namnSok = agentLista + " WHERE NAMN LIKE '";


                }

                private String valOmrade() {

                                try {
                                               String aktiv = (String) omradeBox.getSelectedItem();
                                                omID = ("SELECT OMRADES_ID FROM OMRADE WHERE BENAMNING = '" + aktiv + "'");
                                                //System.out.println(mib.fetchSingle(omID));

                                                return mib.fetchSingle(omID);


                                } catch (InfException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Databasfel!");
                                                System.out.println("1 Internt felmeddelande" + ettUndantag.getMessage());

                                } catch (NumberFormatException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "N�got gick fel!");
                                                System.out.println("2 Internt felmeddelande" + ettUndantag.getMessage());
                                }
                                float lvR = 3 * Math.round(Math.random());
                                return "" + Math.round(lvR);

                }

                //Anger texten i label lblinloggNamn
                private void setLabelInloggNamn() {
                                String hittaNamn = ("select namn from agent where agent_id = " + agentID);

                                try {
                                                inloggadSom.setText("Du �r inloggad som: " + mib.fetchSingle(hittaNamn));
                                } catch (InfException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Databasfel!");
                                                System.out.println("1,5 - Internt felmeddelande" + ettUndantag.getMessage());
                                } catch (Exception ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "N�got gick fel!");
                                                System.out.println("2 - Internt felmeddelande" + ettUndantag.getMessage());
                                }
                }

                //Anger texten i label lblinloggNamn
                private void setOmraden() {
                                String hittaOmraden = ("SELECT BENAMNING FROM OMRADE;");
                                Vector<String> vc = new Vector<>();
                                try {
                                                vc.addAll(mib.fetchColumn(hittaOmraden));
                                                lvBox = new DefaultComboBoxModel(vc);
                                                omradeBox.getModel().setSelectedItem(vc.firstElement());
                                                omradeBox.setModel(lvBox);

                                } catch (InfException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Databasfel!");
                                                System.out.println("3 Internt felmeddelande" + ettUndantag.getMessage());
                                } catch (HeadlessException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "N�got gick fel!");
                                                System.out.println("Fel --  " + ettUndantag.getMessage() + " -- " + ettUndantag.getLocalizedMessage());
                                }

                }

                private void skrivTabell(String querySQL, Boolean manual) {

                                model = (DefaultTableModel) tabell.getModel();
                                model.getDataVector().removeAllElements();
                                tabell.setAutoCreateRowSorter(true);

                                tabell.setModel(model);
                                model.fireTableDataChanged();

                                Vector<Vector> vV = new Vector<>(30, 10);
                                Vector<String> vK, vT;
                                try {

                                                System.out.println(querySQL);
                                                ArrayList<HashMap<String, String>> kDat;
                                                if (manual) {
                                                                kDat = mib.fetchRows(querySQL);
                                                } else {
                                                                kDat = mib.fetchRows(agentLista);
                                                }


                                                for (HashMap<String, String> lvHm : kDat) {

                                                                Iterator iKeys = lvHm.keySet().iterator();
                                                                vK = new Vector<>();
                                                                while (iKeys.hasNext()) {
                                                                                String key = (String) iKeys.next();
                                                                                vK.add(key);
                                                                }

                                                                vT = new Vector<>();
                                                                vT.addAll(lvHm.values());
                                                                vV.add(vT);

                                                                model.setDataVector(vV, vK);

                                                                model.fireTableStructureChanged();
                                                                model.fireTableDataChanged();
                                                                tabell.updateUI();
                                                }

                                } catch (InfException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Databasfel!");
                                                System.out.println("3 Internt felmeddelande" + ettUndantag.getMessage());
                                } catch (HeadlessException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "N�got gick fel!");
                                                System.out.println("Fel --  " + ettUndantag.getMessage() + " -- " + ettUndantag.getLocalizedMessage());
                                } catch (NullPointerException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "N�got gick fel!");
                                                sokruta.setText("Inga resultat");
                                                System.out.println("Fel --  " + ettUndantag.getMessage() + " -- " + ettUndantag.getLocalizedMessage());
                                }


                }

                private ComboBoxModel getCbModel() {

                                return lvBox;

                }
                private TableModel getTableModel() {
                                return tabell.getModel();

                }

                /**
                 * This method is called from within the constructor to
                 * initialize the form. WARNING: Do NOT modify this code. The
                 * content of this method is always regenerated by the Form
                 * Editor.
                 */
                @SuppressWarnings("unchecked")
                private void initComponents() {//GEN-BEGIN:initComponents

                                jLabel1 = new javax.swing.JLabel();
                                inloggadSom = new javax.swing.JLabel();
                                hanteraAgent = new javax.swing.JButton();
                                tillbakaKnapp = new javax.swing.JButton();
                                hanteraUtrustning = new javax.swing.JButton();
                                omradeBox = new javax.swing.JComboBox<>();
                                jScrollPane1 = new javax.swing.JScrollPane();
                                tabell = new javax.swing.JTable();
                                jLabel5 = new javax.swing.JLabel();
                                sokruta = new javax.swing.JTextField();
                                hanteraAgent1 = new javax.swing.JButton();

                                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                                setMinimumSize(new java.awt.Dimension(800, 500));
                                setSize(new java.awt.Dimension(800, 500));

                                jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
                                jLabel1.setText("Administrat�r");

                                inloggadSom.setText("Agent 1");

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

                                omradeBox.setSelectedItem(omradeBox.getSelectedItem());
                                omradeBox.addItemListener(new java.awt.event.ItemListener() {
                                                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                                                omradeBoxItemStateChanged(evt);
                                                }
                                });

                                tabell.setModel(getTableModel());
                                jScrollPane1.setViewportView(tabell);

                                jLabel5.setText("Omr�de");

                                sokruta.setColumns(12);
                                sokruta.setDocument(sokruta.getDocument());
                                sokruta.setText("S�K...");
                                sokruta.setToolTipText("S�k Agent...");
                                sokruta.setSelectionEnd(0);
                                sokruta.setSelectionStart(0);
                                sokruta.addKeyListener(new java.awt.event.KeyAdapter() {
                                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                sokrutaKeyPressed(evt);
                                                }
                                });

                                hanteraAgent1.setText("Hantera alien");
                                hanteraAgent1.addActionListener(new java.awt.event.ActionListener() {
                                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                hanteraAgent1ActionPerformed(evt);
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
                                                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
                                                                                                .addGap(33, 33, 33))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                .addComponent(omradeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                .addComponent(sokruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                                                .addComponent(hanteraUtrustning)
                                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                .addComponent(hanteraAgent1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                .addComponent(jLabel5)
                                                                                                                                                .addComponent(inloggadSom))
                                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                                                .addComponent(tillbakaKnapp, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                .addComponent(hanteraAgent, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                .addGap(39, 39, 39))))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel1)
                                                                .addGap(310, 310, 310))
                                );
                                layout.setVerticalGroup(
                                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(32, 32, 32)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(jLabel1)
                                                                                                .addGap(36, 36, 36)
                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(hanteraAgent)
                                                                                                                .addComponent(tillbakaKnapp))
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(hanteraAgent1)
                                                                                                                .addComponent(hanteraUtrustning)))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(inloggadSom)
                                                                                                .addGap(26, 26, 26)
                                                                                                .addComponent(jLabel5)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(sokruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(omradeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(56, Short.MAX_VALUE))
                                );

                                setBounds(0, 0, 817, 477);
                }//GEN-END:initComponents

    private void hanteraAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hanteraAgentActionPerformed
                                // TODO add your handling code here:
    }//GEN-LAST:event_hanteraAgentActionPerformed

    private void tillbakaKnappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tillbakaKnappActionPerformed
                                new HuvudmenyAgent(mib).setVisible(true);
                                dispose();
    }//GEN-LAST:event_tillbakaKnappActionPerformed

    private void hanteraUtrustningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hanteraUtrustningActionPerformed
                                // TODO add your handling code here:
    }//GEN-LAST:event_hanteraUtrustningActionPerformed

    private void hanteraAgent1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hanteraAgent1ActionPerformed
                                // TODO add your handling code here:
    }//GEN-LAST:event_hanteraAgent1ActionPerformed

                private void omradeBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_omradeBoxItemStateChanged
                                String agentL = "SELECT AGENT_ID, NAMN, TELEFON, ANSTALLNINGSDATUM, ADMINISTRATOR, OMRADE FROM AGENT WHERE OMRADE = '";
                                String osok = agentL + valOmrade() + "'";
                                skrivTabell(osok, true);       // TODO add your handling code here:
                }//GEN-LAST:event_omradeBoxItemStateChanged

                private void sokrutaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sokrutaKeyPressed
 String lvpString = (namnSok + "%" + sokruta.getText().toUpperCase() + "%'");
 if (evt.getKeyCode() == 10) {
                                                skrivTabell(lvpString, true);
                                                }                }//GEN-LAST:event_sokrutaKeyPressed

                /**
                 * @param args the command line arguments
                 */
                // Variables declaration - do not modify//GEN-BEGIN:variables
                public javax.swing.JButton hanteraAgent;
                public javax.swing.JButton hanteraAgent1;
                public javax.swing.JButton hanteraUtrustning;
                public javax.swing.JLabel inloggadSom;
                public javax.swing.JLabel jLabel1;
                public javax.swing.JLabel jLabel5;
                private javax.swing.JScrollPane jScrollPane1;
                public javax.swing.JComboBox<String> omradeBox;
                public javax.swing.JTextField sokruta;
                public javax.swing.JTable tabell;
                public javax.swing.JButton tillbakaKnapp;
                // End of variables declaration//GEN-END:variables
}
