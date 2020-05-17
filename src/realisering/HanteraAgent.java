/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realisering;

import java.awt.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.*;
import oru.inf.*;

/**
 *
 * @author oskar
 */
public class HanteraAgent extends javax.swing.JFrame {

                private final InfDB mib;
                private final String agentID;

                private ArrayList<String> AyL, sList;
                private DefaultTableModel model;
                private DefaultComboBoxModel cbmod;
                private final String agentLista;
                private String cbVal;
                private Vector<String> vA, vS, vK, vT, vN, vM;

                public HanteraAgent(InfDB mib) {

                                initComponents();
                                this.mib = mib;
                                agentID = Login.getAgentID();
                                agentLista = "SELECT AGENT_ID, NAMN, TELEFON, ANSTALLNINGSDATUM, ADMINISTRATOR, OMRADE FROM AGENT";
                                setTableModel();
                                cBM(vK, vT);
                                setOmradeCB();


                }


                private DefaultComboBoxModel cBM(Vector<String> vQ, Vector<String> vW) {
                                try {
                                                //       vQ = new Vector<>();
                                                //      vW = new Vector<>();
                                                //     Iterator ite = mib.fetchRow(agentLista).keySet().iterator();
                                                AyL = new ArrayList<>();
                                                sList = new ArrayList<>();

                                                                                String agentVal = "SELECT NAMN, TELEFON, ANSTALLNINGSDATUM, ADMINISTRATOR, OMRADE FROM AGENT";

                                                AyL.addAll(mib.fetchRow(agentVal).keySet());
                                                sList.addAll(AyL.subList(0, 5));
                                                Collections.sort(sList);


                                                vQ.addAll(sList);
                                                vW.addAll(mib.fetchRow(agentLista).values());


                                                cbmod = (DefaultComboBoxModel) aCB.getModel();
                                                cbmod.removeAllElements();
                                                cbmod.addAll(sList);
                                                aCB.setModel(cbmod);
                                                //     aCB = new JComboBox(cbmod);


                                                //      System.out.println(aCB.getModel().getSize());
                                                aCB.getModel().setSelectedItem(vA);

                                                return cbmod;

                                                // 3 till 1, 2 till tre. 1 te två

                                } catch (InfException ex) {
                                                Logger.getLogger(HanteraAgent.class.getName()).log(Level.SEVERE, null, ex);
                                                return cbmod;

                                }
                }

                private void editAgent() {
                                int rad = tabell.getSelectedRow();
                                String agID = tabell.getValueAt(rad, 0).toString();
                                String attr = cbmod.getSelectedItem().toString();
                                String nyttV = "'" + attributVarde.getText() + "'";
                                System.err.println(attr + " - " + nyttV + nyttV.length() + " " + agID + rad );
                                if (nyttV.length() == 3) nyttV = nyttV.toUpperCase();
                                String fraga = "UPDATE AGENT SET " + attr + " = " + nyttV + " WHERE AGENT_ID = " + agID + ";";
                    try {
                        mib.update(fraga);
                        model.fireTableDataChanged();
                        model.fireTableStructureChanged();
                        setTableModel();
                        tabell.updateUI();
                        
                    } catch (InfException ex) {
                        Logger.getLogger(HanteraAgent.class.getName()).log(Level.SEVERE, getWarningString(), ex);
                        System.out.println("InfFel " + ex.getSQLState() + " " + ex.getMessage() + " " + ex.getLocalizedMessage());
                    }

                }
                
                 //Anger områden i combobox
                private void setOmradeCB() {
                                Vector<String> vc = new Vector<>();
                                try {
                                                vc.addAll(mib.fetchColumn("SELECT BENAMNING FROM OMRADE"));
                                                ComboBoxModel lvBox = new DefaultComboBoxModel(vc);
                                              omrade.getModel().setSelectedItem(vc.firstElement());
                                                omrade.setModel(lvBox);

                                } catch (InfException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Databasfel!");
                                                System.out.println("3 Internt felmeddelande" + ettUndantag.getMessage());
                                } catch (IndexOutOfBoundsException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Något gick fel!");
                                                System.out.println("");
                                }

                }


                private void skrivTabell(String querySQL, boolean ja) {
                                model = (DefaultTableModel) tabell.getModel();
                                model.getDataVector().removeAllElements();
                                tabell.setAutoCreateRowSorter(true);

                                tabell.setModel(model);
                                model.fireTableDataChanged();

                                Vector<Vector> vV = new Vector<>(30, 10);
                                try {

                                                //  System.out.println(querySQL);
                                                ArrayList<HashMap<String, String>> kDat;
                                                if (2 < 1) {
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

                                                }
                                                model.setDataVector(vV, vK);
                                                tabell.setRowSorter(new TableRowSorter(model));
                                                tabell.setAutoCreateRowSorter(true);
                                                tabell.setAutoCreateColumnsFromModel(true);
                                                tabell.setRowSelectionAllowed(true);


                                                model.fireTableStructureChanged();
                                                model.fireTableDataChanged();
                                                tabell.updateUI();
                                                tabell.getColumnModel().moveColumn(2, 0);
                                                tabell.getColumnModel().moveColumn(5, 4);


                                } catch (InfException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Databasfel!");
                                                System.out.println("3 Internt felmeddelande" + ettUndantag.getMessage());
                                } catch (HeadlessException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Något gick fel!");
                                                System.out.println("Fel --  " + ettUndantag.getMessage() + " -- " + ettUndantag.getLocalizedMessage());
                                } catch (NullPointerException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Något gick fel!");
                                                System.out.println("Fel --  " + ettUndantag.getMessage() + " -- " + ettUndantag.getLocalizedMessage());
                                }


                }


                private ComboBoxModel getCbModel() {

                                return cbmod;

                }

                private void setTableModel() {
                                model = (DefaultTableModel) tabell.getModel();
                                model.getDataVector().removeAllElements();
                                tabell.setAutoCreateRowSorter(true);

                                tabell.setModel(model);
                                skrivTabell(agentID, true);
                                model.fireTableDataChanged();

                }

                private TableModel getTableModel() {

                                return tabell.getModel();

                }

                private void avslut(boolean b) {
                                new HuvudmenyAdmin(mib).setVisible(Login.getAdmin());
                                new HuvudmenyAgent(mib).setVisible(!Login.getAdmin());

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

                                jLabel1 = new javax.swing.JLabel();
                                attributKnapp = new javax.swing.JButton();
                                tillbakaKnapp = new javax.swing.JButton();
                                aCB = new javax.swing.JComboBox<>();
                                jScrollPane1 = new javax.swing.JScrollPane();
                                tabell = new javax.swing.JTable();
                                jLabel6 = new javax.swing.JLabel();
                                jLabel7 = new javax.swing.JLabel();
                                attributVarde = new javax.swing.JTextField();
                                jLabel9 = new javax.swing.JLabel();
                                addAgent = new javax.swing.JButton();
                                deleteAgent = new javax.swing.JButton();
                                kontorschef = new javax.swing.JButton();
                                omradeschef = new javax.swing.JButton();
                                omrade = new javax.swing.JComboBox<>();

                                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                                setMinimumSize(new java.awt.Dimension(800, 500));
                                setSize(new java.awt.Dimension(800, 500));

                                jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
                                jLabel1.setText("Hantera agent");

                                attributKnapp.setText("Ändra");
                                attributKnapp.addActionListener(new java.awt.event.ActionListener() {
                                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                attributKnappActionPerformed(evt);
                                                }
                                });

                                tillbakaKnapp.setText("Tillbaka");
                                tillbakaKnapp.addActionListener(new java.awt.event.ActionListener() {
                                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                tillbakaKnappActionPerformed(evt);
                                                }
                                });

                                aCB.addItemListener(new java.awt.event.ItemListener() {
                                                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                                                aCBItemStateChanged(evt);
                                                }
                                });
                                aCB.addInputMethodListener(new java.awt.event.InputMethodListener() {
                                                public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                                                }
                                                public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                                                                aCBInputMethodTextChanged(evt);
                                                }
                                });

                                tabell.setModel(tabell.getModel());
                                jScrollPane1.setViewportView(tabell);

                                jLabel6.setText("Välj agent");

                                jLabel7.setText("Välj attribut att ändra");

                                attributVarde.setColumns(12);
                                attributVarde.addActionListener(new java.awt.event.ActionListener() {
                                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                attributVardeActionPerformed(evt);
                                                }
                                });

                                jLabel9.setText("Ange nytt värde:");

                                addAgent.setText("Lägg till agent");
                                addAgent.addActionListener(new java.awt.event.ActionListener() {
                                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                addAgentActionPerformed(evt);
                                                }
                                });

                                deleteAgent.setText("Ta bort agent");
                                deleteAgent.addActionListener(new java.awt.event.ActionListener() {
                                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                deleteAgentActionPerformed(evt);
                                                }
                                });

                                kontorschef.setText("Gör till kontorschef");
                                kontorschef.addActionListener(new java.awt.event.ActionListener() {
                                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                kontorschefActionPerformed(evt);
                                                }
                                });

                                omradeschef.setText("Gör till områdeschef i");
                                omradeschef.addActionListener(new java.awt.event.ActionListener() {
                                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                omradeschefActionPerformed(evt);
                                                }
                                });

                                omrade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

                                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                                getContentPane().setLayout(layout);
                                layout.setHorizontalGroup(
                                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(28, 28, 28)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(jLabel6)
                                                                                                .addGap(233, 233, 233)
                                                                                                .addComponent(jLabel1)
                                                                                                .addContainerGap())
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(6, 6, 6)
                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                .addComponent(jLabel7)
                                                                                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                                                .addComponent(jLabel9)
                                                                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                                                .addComponent(attributVarde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                                                .addComponent(attributKnapp, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                                .addComponent(aCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                .addComponent(tillbakaKnapp)
                                                                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                                                                                                .addComponent(addAgent, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                                                                                                                                                .addComponent(kontorschef, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                                                .addGap(76, 76, 76)
                                                                                                                                                                .addComponent(omrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                                .addComponent(deleteAgent, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(omradeschef))
                                                                                                                                .addGap(16, 16, 16))))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
                                                                                                .addGap(13, 13, 13))))
                                );
                                layout.setVerticalGroup(
                                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(21, 21, 21)
                                                                                                .addComponent(jLabel1))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(49, 49, 49)
                                                                                                .addComponent(jLabel6)))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel7)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(aCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(omrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(tillbakaKnapp))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(attributVarde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel9)
                                                                                .addComponent(attributKnapp)
                                                                                .addComponent(kontorschef)
                                                                                .addComponent(omradeschef))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(deleteAgent)
                                                                                .addComponent(addAgent))
                                                                .addContainerGap(50, Short.MAX_VALUE))
                                );

                                pack();
                }//GEN-END:initComponents

    private void attributKnappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attributKnappActionPerformed
                          editAgent();      // TODO add your handling code here:
    }//GEN-LAST:event_attributKnappActionPerformed

    private void attributVardeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attributVardeActionPerformed
                                // TODO add your handling code here:
    }//GEN-LAST:event_attributVardeActionPerformed

    private void tillbakaKnappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tillbakaKnappActionPerformed
                                avslut(false);
    }//GEN-LAST:event_tillbakaKnappActionPerformed

    private void kontorschefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kontorschefActionPerformed
                                // TODO add your handling code here:
    }//GEN-LAST:event_kontorschefActionPerformed

    private void omradeschefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_omradeschefActionPerformed
if (omrade.getSelectedItem() == null) {omrade.setSelectedIndex(0);}

        try {            
            tabell.setRowSelectionAllowed(true);
                               model.fireTableDataChanged();
                               model.fireTableStructureChanged();
                                String agID = tabell.getValueAt(tabell.getSelectedRow(), 0).toString();
                                                      int agIN = Integer.parseInt(agID);

                                ArrayList<String> vS = new ArrayList<>(); 
                    String oi = mib.fetchSingle("SELECT OMRADES_ID FROM OMRADE WHERE BENAMNING LIKE '" + (String) omrade.getSelectedItem() + "'");
                            int omid =  Integer.parseInt((String) oi); 
                           vS.addAll(mib.fetchColumn("SELECT a.AGENT_ID FROM AGENT as a, OMRADESCHEF as o WHERE a.AGENT_ID = o.AGENT_ID ORDER BY a.AGENT_ID DESC"));
                          System.out.println(vS.size());
                           if (agID.equals(vS.get(0)) || agID.equals(vS.get(1))) {
                                 mib.update("UPDATE OMRADESCHEF SET OMRADE = " + omid + " WHERE AGENT_ID = " + agIN); 
                               System.out.println("Nådde if");
                           } else {
                            mib.insert("INSERT INTO OMRADESCHEF VALUES(" + oi + ", " + agID + ");");
                                                           System.out.println("Nådde else");

                           }   
                
                
            
            
                                
    
                          //  mib.insert("INSERT INTO OMRADESCHEF VALUES(" + s + ", " + agID + ");");
                            
                                
                        
        
        
        } catch (InfException ex) {
                        System.err.println(ex.getMessage() + ex.getLocalizedMessage() + ex.getSQLState() + ex.getErrorCode() + ex.getNextException());
                        Logger.getLogger(HanteraAgent.class.getName()).log(Level.SEVERE, null, ex);
                    }


    }//GEN-LAST:event_omradeschefActionPerformed

                private void addAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAgentActionPerformed
                                // TODO add your handling code here:
                }//GEN-LAST:event_addAgentActionPerformed

                private void deleteAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAgentActionPerformed
                                // TODO add your handling code here:
                }//GEN-LAST:event_deleteAgentActionPerformed

                private void aCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_aCBItemStateChanged


                                // TODO add your handling code here:
                }//GEN-LAST:event_aCBItemStateChanged

                private void aCBInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_aCBInputMethodTextChanged
                                // TODO add your handling code here:
                }//GEN-LAST:event_aCBInputMethodTextChanged

                /**
                 * @param args the command line arguments
                 */
                // Variables declaration - do not modify//GEN-BEGIN:variables
                private javax.swing.JComboBox<String> aCB;
                private javax.swing.JButton addAgent;
                private javax.swing.JButton attributKnapp;
                private javax.swing.JTextField attributVarde;
                private javax.swing.JButton deleteAgent;
                private javax.swing.JLabel jLabel1;
                private javax.swing.JLabel jLabel6;
                private javax.swing.JLabel jLabel7;
                private javax.swing.JLabel jLabel9;
                private javax.swing.JScrollPane jScrollPane1;
                private javax.swing.JButton kontorschef;
                private javax.swing.JComboBox<String> omrade;
                private javax.swing.JButton omradeschef;
                private javax.swing.JTable tabell;
                private javax.swing.JButton tillbakaKnapp;
                // End of variables declaration//GEN-END:variables
}
