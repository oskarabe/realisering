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
public class HuvudmenyAgent extends javax.swing.JFrame {

    private String agentID, omID, omCB, all, agentLista;
    private boolean isAdmin;
    private InfDB mib;
    private ComboBoxModel lvBox, rasModell;
    private Vector<String> vC, vKolumn, vData;
    private DefaultTableModel model;

    /**
     * Creates new form HuvudmenyAgent
     */
    public HuvudmenyAgent(InfDB mib) {
        initComponents();
        this.mib = mib;
        agentID = Login.getAgentID();
        setLabelInloggNamn();
        setcBoxPlats();
        setGetCbModel();
        setcBoxRas();
        setGetTableModel();
    }

    //Anger texten i lblInloggNamn
    private void setLabelInloggNamn()
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

    
    //Anger värdena i cBoxPlats
    private void setcBoxPlats()
    {
        String hittaPlats = "select benamning from plats";
        ArrayList<String> allaPlatser;
        
        try {   
            allaPlatser = mib.fetchColumn(hittaPlats);
            allaPlatser.add(0, "Alla");
            for (String platsNamn : allaPlatser) {
                cBoxPlats.addItem(platsNamn);
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

    private void setcBoxRas() {
        Vector<String> vRas = new Vector<>();
        vRas.add("Alla");
        vRas.add("Boglodite");
        vRas.add("Squid");
        vRas.add("Worm");
        rasModell = new DefaultComboBoxModel(vRas);
        cBoxRas.setModel(rasModell);
    }

    protected void skrivTabell() {
        skrivTabell(getAgentLista());
    }

    protected void skrivTabell(String specQuery) {
        ArrayList< HashMap< String, String>> hmData;
        Vector< Vector< String>> vRad = new Vector<>();

        try {
            vKolumn = new Vector<>();
            hmData = mib.fetchRows(specQuery);

            for (HashMap<String, String> hm : hmData) {
                vData = new Vector<>();
                vData.addAll(hm.values());
                vRad.add(vData);
            }

            vKolumn.addAll(hmData.get(0).keySet());

            model.setDataVector(vRad, vKolumn);
            tabell.setRowSelectionAllowed(true);
            tabell.getColumnModel().moveColumn(3, 0);
            tabell.getColumnModel().moveColumn(4, 2);
        } catch (InfException ettUndantag) {
            JOptionPane.showMessageDialog(null, "Databasfel!");
            System.out.println("inf fel 3 Internt felmeddelande" + ettUndantag.getMessage());
        } catch (IndexOutOfBoundsException ettUndantag) {
            JOptionPane.showMessageDialog(null, "Något gick fel!");
            System.out.println("headFel --  " + ettUndantag.getMessage() + " -- " + ettUndantag.getLocalizedMessage());
        } catch (NullPointerException u) {
            JOptionPane.showMessageDialog(null, "Inga resultat...");

            System.err.println("-- NullPointerEx --");
        }
    }

    private ComboBoxModel setGetCbModel() {
        vC = new Vector<>();
        try {
            vC.addAll(mib.fetchColumn("SELECT BENAMNING FROM OMRADE"));
        } catch (InfException ex) {
            Logger.getLogger(HuvudmenyAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        all = "Alla";
        vC.insertElementAt(all, 0);
        lvBox = new DefaultComboBoxModel(vC);
        lvBox.setSelectedItem(lvBox.getElementAt(0));
        cBoxOmrade.setModel(lvBox);
        return lvBox;

    }

    private TableModel setGetTableModel() {
        setAgentLista("SELECT ALIEN_ID, REGISTRERINGSDATUM, NAMN, TELEFON, PLATS, ANSVARIG_AGENT FROM ALIEN");
        model = (DefaultTableModel) tabell.getModel();
        model.getDataVector().removeAllElements();
        tabell.setModel(model);
        skrivTabell();
        return model;

    }

    private void avslut(boolean b) {

        setEnabled(b);
        this.dispose();
        this.disable();
        this.setVisible(b);

    }

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHuvudmenyAgent = new javax.swing.JLabel();
        lblInloggNamn = new javax.swing.JLabel();
        btnHanteraAliens = new javax.swing.JButton();
        btnHanteraUtrustning = new javax.swing.JButton();
        cBoxPlats = new javax.swing.JComboBox<>();
        cBoxOmrade = new javax.swing.JComboBox<>();
        cBoxRas = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabell = new javax.swing.JTable();
        txtFldDatumFran = new javax.swing.JTextField();
        txtFldDatumTill = new javax.swing.JTextField();
        lblOmrade = new javax.swing.JLabel();
        lblPlats = new javax.swing.JLabel();
        lblRas = new javax.swing.JLabel();
        lblDatum = new javax.swing.JLabel();
        txtFldSokruta = new javax.swing.JTextField();
        btnTillbakaInlogg = new javax.swing.JButton();
        lblRasVald = new javax.swing.JLabel();
        attributKnapp = new javax.swing.JButton();
        attributVarde = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        attributVal = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        attributKnapp1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 500));
        setSize(new java.awt.Dimension(800, 500));

        lblHuvudmenyAgent.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblHuvudmenyAgent.setText("Huvudmeny - Agent");

        lblInloggNamn.setText("Du är inloggad som:");

        btnHanteraAliens.setText("Hantera aliens");
        btnHanteraAliens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHanteraAliensActionPerformed(evt);
            }
        });

        btnHanteraUtrustning.setText("Hantera utrustning");
        btnHanteraUtrustning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHanteraUtrustningActionPerformed(evt);
            }
        });

        cBoxPlats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cBoxPlatsActionPerformed(evt);
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
        tabell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabellMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabell);

        txtFldDatumFran.setText("Från");

        txtFldDatumTill.setText("Till");

        lblOmrade.setText("Område");

        lblPlats.setText("Plats");

        lblRas.setText("Ras");

        lblDatum.setText("Datum");

        txtFldSokruta.setText("Sök alien...");

        btnTillbakaInlogg.setText("Tillbaka till inlogg");
        btnTillbakaInlogg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaInloggActionPerformed(evt);
            }
        });

        lblRasVald.setText("Ras på vald Alien: ");

        attributKnapp.setText("Ändra");
        attributKnapp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attributKnappActionPerformed(evt);
            }
        });

        attributVarde.setColumns(12);
        attributVarde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attributVardeActionPerformed(evt);
            }
        });

        jLabel9.setText("Ange nytt värde:");

        attributVal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Välj attribut att ändra");

        attributKnapp1.setText("Byt");
        attributKnapp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attributKnapp1ActionPerformed(evt);
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
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(attributVarde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(attributKnapp, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(attributVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(attributKnapp1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblRasVald))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblOmrade)
                                        .addGap(53, 53, 53)
                                        .addComponent(lblPlats)
                                        .addGap(30, 30, 30))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cBoxOmrade, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cBoxPlats, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(lblInloggNamn)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(36, 36, 36)
                                                .addComponent(lblRas))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(cBoxRas, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblDatum)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtFldDatumFran, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtFldDatumTill, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtFldSokruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(33, 33, 33)
                                        .addComponent(btnHanteraUtrustning))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(73, 73, 73)
                                        .addComponent(lblHuvudmenyAgent)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnHanteraAliens, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnTillbakaInlogg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(33, 33, 33))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(lblHuvudmenyAgent)
                                .addGap(79, 79, 79))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblInloggNamn)
                                .addGap(49, 49, 49)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPlats)
                            .addComponent(lblOmrade)
                            .addComponent(lblRas)
                            .addComponent(lblDatum))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cBoxOmrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cBoxRas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFldDatumFran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFldDatumTill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFldSokruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cBoxPlats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(btnTillbakaInlogg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHanteraAliens)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHanteraUtrustning)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblRasVald))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(attributVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(attributKnapp1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(attributVarde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(attributKnapp))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnHanteraAliensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHanteraAliensActionPerformed
        new HanteraAlien(mib).setVisible(true);
        dispose();
    }//GEN-LAST:event_btnHanteraAliensActionPerformed

    private void btnHanteraUtrustningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHanteraUtrustningActionPerformed
        new HanteraUtrustning(mib).setVisible(true);
        dispose();
    }//GEN-LAST:event_btnHanteraUtrustningActionPerformed

    private void cBoxPlatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cBoxPlatsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cBoxPlatsActionPerformed

    private void btnTillbakaInloggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaInloggActionPerformed
        dispose();
        new Login(mib).setVisible(true);
    }//GEN-LAST:event_btnTillbakaInloggActionPerformed

    private void tabellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabellMouseClicked

        int rad = tabell.getSelectedRow();
        String ras = "";
        String id =  (String) tabell.getValueAt(rad, 0);
        try {
            if (mib.fetchColumn("SELECT ALIEN_ID FROM BOGLODITE").contains(id)) {
                ras = "Boglodite";
            } else if (mib.fetchColumn("SELECT ALIEN_ID FROM SQUID").contains(id)) {
                ras = "Squid";
            } else if (mib.fetchColumn("SELECT ALIEN_ID FROM WORM").contains(id)) {
                ras = "Worm";
            }
                lblRasVald.setText("Ras för vald alien: " + ras);
        } catch (InfException ex) {
            Logger.getLogger(HuvudmenyAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tabellMouseClicked

    private void attributKnappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attributKnappActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_attributKnappActionPerformed

    private void attributVardeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attributVardeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_attributVardeActionPerformed

    private void attributKnapp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attributKnapp1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_attributKnapp1ActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton attributKnapp;
    private javax.swing.JButton attributKnapp1;
    private javax.swing.JComboBox<String> attributVal;
    private javax.swing.JTextField attributVarde;
    private javax.swing.JButton btnHanteraAliens;
    private javax.swing.JButton btnHanteraUtrustning;
    private javax.swing.JButton btnTillbakaInlogg;
    private javax.swing.JComboBox<String> cBoxOmrade;
    private javax.swing.JComboBox<String> cBoxPlats;
    private javax.swing.JComboBox<String> cBoxRas;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDatum;
    private javax.swing.JLabel lblHuvudmenyAgent;
    private javax.swing.JLabel lblInloggNamn;
    private javax.swing.JLabel lblOmrade;
    private javax.swing.JLabel lblPlats;
    private javax.swing.JLabel lblRas;
    private javax.swing.JLabel lblRasVald;
    private javax.swing.JTable tabell;
    private javax.swing.JTextField txtFldDatumFran;
    private javax.swing.JTextField txtFldDatumTill;
    private javax.swing.JTextField txtFldSokruta;
    // End of variables declaration//GEN-END:variables
}