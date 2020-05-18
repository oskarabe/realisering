package realisering;

import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.*;
import oru.inf.*;

/**
 *
 * @author oskar
 */
public class HuvudmenyAdmin extends javax.swing.JFrame {

                private final InfDB mib;
                private ComboBoxModel lvBox;
                private TableColumnModel cmodel;
                private TableColumn tC;
                private String omID, omCB, all, agentLista;
                private final String agentID;
                private Vector<String> vC, vK, vL, vT;

                DefaultTableModel model;

                public HuvudmenyAdmin(InfDB mib) {

                                this.mib = mib;
                                model = new DefaultTableModel() {

                                                @Override
                                                public boolean isCellEditable(int row, int column) {
                                                                return false;
                                                }


                                };
                                agentID = Login.getAgentID();
                                initComponents();
                                setLabelInloggNamn();
                                setGetCbModel();
                                omradeBox.setSelectedItem(lvBox.getElementAt(0));
                                setGetTableModel();

                }

                private String valOmrade() {

                                try {
                                                String aktiv = (String) omradeBox.getSelectedItem();
                                                omID = ("SELECT OMRADES_ID FROM OMRADE");
                                                if (omradeBox.getSelectedIndex() != 0) {
                                                                omID = omID + " WHERE BENAMNING LIKE '" + aktiv + "'";
                                                                omCB = mib.fetchSingle(omID);
                                                                return omCB;
                                                }


                                } catch (InfException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Databasfel!");
                                                System.out.println("1 Internt felmeddelande" + ettUndantag.getMessage());

                                } catch (NumberFormatException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Något gick fel!");
                                                System.out.println("2 Internt felmeddelande" + ettUndantag.getMessage());
                                }
                                // Sätter en random som selected om något blir fel
                                float lvR = 3 * Math.round(Math.random());
                                return "" + Math.round(lvR);

                }

                //Anger texten i label lblinloggNamn
                private void setLabelInloggNamn() {
                                String hittaNamn = ("select namn from agent where agent_id = " + agentID);

                                try {
                                                inloggadSom.setText("Du är inloggad som: " + mib.fetchSingle(hittaNamn));
                                } catch (InfException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Databasfel!");
                                                System.out.println("1,5 - Internt felmeddelande" + ettUndantag.getMessage());
                                } catch (Exception ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Något gick fel!");
                                                System.out.println("2 - Internt felmeddelande" + ettUndantag.getMessage());
                                }
                }


                protected void skrivTabell() {
                                skrivTabell(getAgentLista());
                }

                protected void skrivTabell(String specQuery) {
                                ArrayList<HashMap<String, String>> kDat;
                                Vector<Vector<String>> vV;
                                int i = 0;

                                try {
                                                vV = new Vector<>();
                                                vK = new Vector<>();
                                                vL = new Vector<>();
                                                kDat = mib.fetchRows(specQuery);
                                                for (HashMap<String, String> lvHm : kDat) {
                                                                vL.addAll(lvHm.keySet());
                                                                while (i < 6) {
                                                                                String key = vL.get(i);
                                                                                vK.add(key);
                                                                                System.out.println(key);
                                                                                /*
                                                                                 * if
                                                                                 * (vK.retainAll(lvHm.keySet()))
                                                                                 * {
                                                                                 * break;
                                                                                 * }
                                                                                 */ i++;
                                                                }
                                                                vT = new Vector<>();
                                                                vT.addAll(lvHm.values());
                                                                vV.add(vT);
                                                }
                                                model.setDataVector(vV, vK);
                                                tabell.setRowSorter(new TableRowSorter(model));
                                                tabell.setAutoCreateRowSorter(true);
                                                tabell.setAutoCreateColumnsFromModel(true);
                                                tabell.setRowSelectionAllowed(true);
                                                tC = new TableColumn(vK.lastIndexOf(vK), model.findColumn(tabell.getColumnName(0)), tabell.getDefaultRenderer(model.getColumnClass(0)), null);
                                                TableCellEditor cellEditor = null;
                                                tC.setCellEditor(cellEditor);
                                                cmodel.addColumn(tC);

                                                tabell.removeEditor();


                                                model.fireTableStructureChanged();
                                                model.fireTableDataChanged();
                                                //    tabell.updateUI();
                                                tabell.getColumnModel().moveColumn(2, 0);
                                                tabell.getColumnModel().moveColumn(5, 4);
                                                tabell.removeEditor();
                                                tabell.enableInputMethods(false);


                                } catch (InfException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Databasfel!");
                                                System.out.println("inf fel 3 Internt felmeddelande" + ettUndantag.getMessage());
                                } catch (IndexOutOfBoundsException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Något gick fel!");
                                                System.out.println("headFel --  " + ettUndantag.getMessage() + " -- " + ettUndantag.getLocalizedMessage());
                                } catch (NullPointerException u) {

                                                lblFel.setText("Inga resultat hittades...");
                                                lblFel.setVisible(true);
                                                System.err.println("-- NullPointerEx --");
                                }


                }

                private ComboBoxModel setGetCbModel() {
                                vC = new Vector<>();
                                all = "Alla";
                                try {
                                                vC.addAll(mib.fetchColumn("SELECT BENAMNING FROM OMRADE"));
                                } catch (InfException ex) {
                                                Logger.getLogger(HuvudmenyAdmin.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                vC.insertElementAt(all, 0);
                                lvBox = new DefaultComboBoxModel(vC);
                                lvBox.setSelectedItem(lvBox.getElementAt(0));
                                omradeBox.setModel(lvBox);
                                return lvBox;

                }

                private TableModel setGetTableModel() {
                                setAgentLista("SELECT AGENT_ID, NAMN, TELEFON, ANSTALLNINGSDATUM, ADMINISTRATOR, OMRADE FROM AGENT");

                                model = (DefaultTableModel) tabell.getModel();
                                model.getDataVector().removeAllElements();
                                tabell.setAutoCreateRowSorter(true);
                                tabell.setModel(model);
                                model.fireTableDataChanged();

                                skrivTabell();
                                return model;

                }


                private void avslut(boolean b) {
                                new HanteraAgent(mib).setVisible(true);

                                setEnabled(b);
                                this.dispose();
                                this.disable();
                                this.setVisible(b);


                }


                /**
                 * This method is called from within the constructor to
                 * initialize the form. WARNING: Do NOT modify this code. The
                 * content of this method is always regenerated by the Form
                 * Editor.
                 */
                @SuppressWarnings("unchecked")
                private void initComponents() {//GEN-BEGIN:initComponents

                                lblAdmin = new javax.swing.JLabel();
                                inloggadSom = new javax.swing.JLabel();
                                hanteraAgent = new javax.swing.JButton();
                                tillbakaKnapp = new javax.swing.JButton();
                                hanteraUtrustning = new javax.swing.JButton();
                                omradeBox = new javax.swing.JComboBox<>();
                                jScrollPane1 = new javax.swing.JScrollPane();
                                tabell = new javax.swing.JTable();
                                lblOmrade = new javax.swing.JLabel();
                                sokruta = new javax.swing.JTextField();
                                hanteraAgent1 = new javax.swing.JButton();
                                lblFel = new javax.swing.JLabel();

                                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                                setTitle("Admin");
                                setBackground(new java.awt.Color(244, 247, 252));
                                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                                setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
                                setMinimumSize(new java.awt.Dimension(800, 500));
                                setSize(new java.awt.Dimension(800, 500));

                                lblAdmin.setFont(new java.awt.Font("Berlin Sans FB", 0, 36)); // NOI18N
                                lblAdmin.setText("Administratör");

                                inloggadSom.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
                                inloggadSom.setText("Agent 1");

                                hanteraAgent.setFont(new java.awt.Font("Berlin Sans FB", 0, 16)); // NOI18N
                                hanteraAgent.setText("AGENTER");
                                hanteraAgent.setToolTipText("Hantera...");
                                hanteraAgent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                hanteraAgent.setMaximumSize(new java.awt.Dimension(150, 60));
                                hanteraAgent.setMinimumSize(new java.awt.Dimension(150, 60));
                                hanteraAgent.setPreferredSize(new java.awt.Dimension(150, 30));
                                hanteraAgent.addMouseListener(new java.awt.event.MouseAdapter() {
                                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                hanteraAgentMouseClicked(evt);
                                                }
                                });
                                hanteraAgent.addActionListener(new java.awt.event.ActionListener() {
                                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                hanteraAgentActionPerformed(evt);
                                                }
                                });

                                tillbakaKnapp.setFont(new java.awt.Font("Berlin Sans FB", 0, 16)); // NOI18N
                                tillbakaKnapp.setText("Tillbaka");
                                tillbakaKnapp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                tillbakaKnapp.setMaximumSize(new java.awt.Dimension(150, 40));
                                tillbakaKnapp.setMinimumSize(new java.awt.Dimension(150, 40));
                                tillbakaKnapp.setPreferredSize(new java.awt.Dimension(150, 40));
                                tillbakaKnapp.addActionListener(new java.awt.event.ActionListener() {
                                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                tillbakaKnappActionPerformed(evt);
                                                }
                                });

                                hanteraUtrustning.setFont(new java.awt.Font("Berlin Sans FB", 0, 16)); // NOI18N
                                hanteraUtrustning.setText("UTRUSTNING");
                                hanteraUtrustning.setToolTipText("Hantera...");
                                hanteraUtrustning.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                hanteraUtrustning.setMaximumSize(new java.awt.Dimension(150, 60));
                                hanteraUtrustning.setMinimumSize(new java.awt.Dimension(150, 60));
                                hanteraUtrustning.setPreferredSize(new java.awt.Dimension(130, 30));
                                hanteraUtrustning.addActionListener(new java.awt.event.ActionListener() {
                                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                hanteraUtrustningActionPerformed(evt);
                                                }
                                });

                                omradeBox.setFont(omradeBox.getFont());
                                omradeBox.setModel(setGetCbModel());
                                omradeBox.setSelectedIndex(omradeBox.getSelectedIndex());
                                omradeBox.setSelectedItem(omradeBox.getSelectedItem());
                                omradeBox.addItemListener(new java.awt.event.ItemListener() {
                                                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                                                omradeBoxItemStateChanged(evt);
                                                }
                                });

                                jScrollPane1.setFont(jScrollPane1.getFont());

                                tabell.setAutoCreateRowSorter(true);
                                tabell.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
                                tabell.setFont(new java.awt.Font("Berlin Sans FB", 0, 16)); // NOI18N
                                tabell.setModel(setGetTableModel());
                                tabell.setColumnSelectionAllowed(true);
                                tabell.setEnabled(tabell.isVisible());
                                tabell.setFocusable(false);
                                tabell.setIntercellSpacing(new java.awt.Dimension(10, 10));
                                tabell.setRequestFocusEnabled(false);
                                tabell.setRowHeight(24);
                                tabell.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
                                tabell.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
                                tabell.setShowGrid(true);
                                jScrollPane1.setViewportView(tabell);
                                tabell.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

                                lblOmrade.setFont(getFont());
                                lblOmrade.setText("Område");

                                sokruta.setColumns(12);
                                sokruta.setDocument(sokruta.getDocument());
                                sokruta.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
                                sokruta.setText("SÖK KODNAMN...");
                                sokruta.setToolTipText("Sök Agent...");
                                sokruta.addMouseListener(new java.awt.event.MouseAdapter() {
                                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                                sokrutaMouseClicked(evt);
                                                }
                                });
                                sokruta.addInputMethodListener(new java.awt.event.InputMethodListener() {
                                                public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                                                }
                                                public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                                                                sokrutaInputMethodTextChanged(evt);
                                                }
                                });
                                sokruta.addKeyListener(new java.awt.event.KeyAdapter() {
                                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                                                sokrutaKeyPressed(evt);
                                                }
                                });

                                hanteraAgent1.setFont(new java.awt.Font("Berlin Sans FB", 0, 16)); // NOI18N
                                hanteraAgent1.setText("ALIENS");
                                hanteraAgent1.setToolTipText("Hantera...");
                                hanteraAgent1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                hanteraAgent1.setMaximumSize(new java.awt.Dimension(150, 60));
                                hanteraAgent1.setMinimumSize(new java.awt.Dimension(150, 60));
                                hanteraAgent1.setPreferredSize(new java.awt.Dimension(150, 30));
                                hanteraAgent1.addActionListener(new java.awt.event.ActionListener() {
                                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                hanteraAgent1ActionPerformed(evt);
                                                }
                                });

                                lblFel.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
                                lblFel.setAlignmentY(0.0F);
                                lblFel.setMaximumSize(new java.awt.Dimension(250, 40));
                                lblFel.setMinimumSize(new java.awt.Dimension(250, 40));
                                lblFel.setPreferredSize(new java.awt.Dimension(250, 40));

                                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                                getContentPane().setLayout(layout);
                                layout.setHorizontalGroup(
                                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(30, 30, 30)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(jScrollPane1)
                                                                                                .addGap(33, 33, 33))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                .addComponent(inloggadSom)
                                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                .addComponent(omradeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                .addComponent(sokruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addComponent(lblOmrade))
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                                                                                                .addComponent(lblFel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(74, 74, 74)
                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                .addGap(6, 6, 6)
                                                                                                                                .addComponent(hanteraUtrustning, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addComponent(hanteraAgent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(hanteraAgent1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(55, 55, 55))))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                                .addComponent(lblAdmin)
                                                                                                .addGap(310, 310, 310))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                                .addComponent(tillbakaKnapp, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(46, 46, 46))))
                                );
                                layout.setVerticalGroup(
                                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(32, 32, 32)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(lblAdmin)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(inloggadSom)
                                                                                                .addGap(26, 26, 26)
                                                                                                .addComponent(lblOmrade)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(omradeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(sokruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(20, 20, 20))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(hanteraAgent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(hanteraAgent1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(hanteraUtrustning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(23, 23, 23))
                                                                                .addComponent(lblFel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(tillbakaKnapp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(19, Short.MAX_VALUE))
                                );

                                pack();
                }//GEN-END:initComponents

    private void hanteraAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hanteraAgentActionPerformed

                                avslut(false);

    }//GEN-LAST:event_hanteraAgentActionPerformed

    private void tillbakaKnappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tillbakaKnappActionPerformed
                                avslut(false);


    }//GEN-LAST:event_tillbakaKnappActionPerformed

    private void hanteraUtrustningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hanteraUtrustningActionPerformed
                                // TODO add your handling code here:
    }//GEN-LAST:event_hanteraUtrustningActionPerformed

    private void hanteraAgent1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hanteraAgent1ActionPerformed
                                avslut(false);
    }//GEN-LAST:event_hanteraAgent1ActionPerformed

                private void omradeBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_omradeBoxItemStateChanged
                                if (evt.getStateChange() == 1 && !omradeBox.getSelectedItem().toString().equals("Alla")) {
                                                String agentL = getAgentLista() + " WHERE OMRADE = '" + valOmrade() + "'";
                                                System.out.println(agentL);
                                                skrivTabell(agentL);

                                } else {
                                                skrivTabell();
                                }     // TODO add your handling code here:
                }//GEN-LAST:event_omradeBoxItemStateChanged

                private void sokrutaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sokrutaKeyPressed
                                String query = getAgentLista();
                                String lvpString = " WHERE NAMN LIKE " + "'%" + sokruta.getText() + "%'";
                                if (!omradeBox.getSelectedItem().toString().equals("Alla")) {
                                                query = query + lvpString + " AND OMRADE LIKE '" + valOmrade() + "'";
                                } else {
                                                query += lvpString;
                                }
                                if (evt.getKeyCode() == 1) {
                                                System.out.println(query);
                                                skrivTabell(query);
                                                }                }//GEN-LAST:event_sokrutaKeyPressed

                private void hanteraAgentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hanteraAgentMouseClicked
                                avslut(false);


                                // TODO add your handling code here:
                }//GEN-LAST:event_hanteraAgentMouseClicked

                private void sokrutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sokrutaMouseClicked

                                lblFel.setVisible(false);
                                sokruta.setText("");


                }//GEN-LAST:event_sokrutaMouseClicked

                private void sokrutaInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_sokrutaInputMethodTextChanged

                                lblFel.setVisible(false);

                                sokruta.setText("");


                }//GEN-LAST:event_sokrutaInputMethodTextChanged

                /**
                 * @param args the command line arguments
                 */
                // Variables declaration - do not modify//GEN-BEGIN:variables
                public javax.swing.JButton hanteraAgent;
                public javax.swing.JButton hanteraAgent1;
                public javax.swing.JButton hanteraUtrustning;
                public javax.swing.JLabel inloggadSom;
                private javax.swing.JScrollPane jScrollPane1;
                public javax.swing.JLabel lblAdmin;
                private javax.swing.JLabel lblFel;
                public javax.swing.JLabel lblOmrade;
                public javax.swing.JComboBox<String> omradeBox;
                public javax.swing.JTextField sokruta;
                public javax.swing.JTable tabell;
                public javax.swing.JButton tillbakaKnapp;
                // End of variables declaration//GEN-END:variables

                /**
                 * @return the agentLista
                 */
                private String getAgentLista() {
                                return agentLista;
                }

                /**
                 * @param agentLista the agentLista to set
                 */
                private void setAgentLista(String agentLista) {
                                this.agentLista = agentLista;
                }

}
