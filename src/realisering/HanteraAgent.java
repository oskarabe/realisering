package realisering;

import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.*;
import oru.inf.*;

/**
 * Klass f�r att hantera agenter...
 */
public class HanteraAgent
        extends javax.swing.JFrame {

    // Deklaration av variabler
    private final InfDB mib;
    private final String agentID;
    private String agentLista, agID;
    private DefaultTableModel model;
    private DefaultComboBoxModel cbmod;
    private Vector< String> vKolumn, vData;

    /**
     * Konstruktor. Kallar metoder f�r att s�tta r�tt v�rden p� text och
     * comboboxes. Kollar �ven om anv�ndaren �r vanlig agent eller admin.
     */
    public HanteraAgent(InfDB mib) {

        this.mib = mib;
        initComponents();
        agentID = Login.getAgentID();
        setAgentLista("SELECT AGENT_ID, NAMN, TELEFON, ANSTALLNINGSDATUM, ADMINISTRATOR, OMRADE FROM AGENT");
        setTableModel();
        vKolumn = new Vector<>();
        vData = new Vector<>();
        cBM(vKolumn, vData);
        setOmradeCB();
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

    /**
     * @return the agentLista
     */
    private String getAgID() {
        return agID;
    }

    /**
     * @param agentLista the agentLista to set
     */
    private void setAgID(String id) {
        this.agID = id;
    }

    // Returnerar modellen f�r en combobox med attribut
    private DefaultComboBoxModel cBM(Vector< String> vQ, Vector< String> vW) {


        Vector<String> attributVector = new Vector<>();
        attributVector.add("NAMN");
        attributVector.add("TELEFON");
        attributVector.add("ANSTALLNINGSDATUM");
        attributVector.add("ADMINISTRATOR");
        attributVector.add("OMRADE");

        cbmod = (DefaultComboBoxModel) aCB.getModel();
        cbmod.removeAllElements();
        cbmod.addAll(attributVector);
        aCB.setModel(cbmod);
        return cbmod;

    }

    // Metod f�r att �ndra valt attribut f�r vald agent
    private void editAgent() {
        int rad = tabell.getSelectedRow();

        // S�tter AGENT_ID f�r vald agent
        setAgID(tabell.getValueAt(rad, 0).toString());

        // Sparar valt attribut
        String attr = cbmod.getSelectedItem().toString();

        // Sparar nytt v�rde
        String nyttV = "'" + attributVarde.getText() + "'";

        // F�r att admin-attributet alltid ska vara stor bokstav
        if (nyttV.length() == 3) {
            nyttV = nyttV.toUpperCase();
        }

        // SQL-fr�ga f�r att uppdatera valt attribut till angivet v�rde
        String fraga = "UPDATE AGENT SET " + attr + " = " + nyttV + " WHERE AGENT_ID = " + agID + ";";
        try {
            // Uppdatera och skriv ut ny tabell
            if (attr.equalsIgnoreCase("Omrade")) {
                String omr = mib.fetchSingle("SELECT OMRADES_ID FROM OMRADE WHERE BENAMNING LIKE " + nyttV);
                if (Validering.finnsIDB(omr)) {
                    fraga = "UPDATE AGENT SET OMRADE = " + omr + " WHERE AGENT_ID = " + agID + ";";
                    mib.update(fraga);
                }               
            } else if (attr.equalsIgnoreCase("ANSTALLNINGSDATUM")) {
                if (Validering.isDatum(attributVarde)) {
                    mib.update(fraga);
                }
            } else if (attr.equalsIgnoreCase("ADMINISTRATOR")) {
                if (Validering.adminFormat(attributVarde)) {
                    mib.update(fraga);
                }
            } else if (attr.equalsIgnoreCase("NAMN")) {
                if (Validering.namnLangd(attributVarde)) {
                    mib.update(fraga);
                }
            } else if (attr.equalsIgnoreCase("TELEFON")) {
                if (Validering.telefonLangd(attributVarde)) {
                    mib.update(fraga);
                }
            } else {
                mib.update(fraga);
            }
            model.fireTableDataChanged();
            model.fireTableStructureChanged();
            setTableModel();
            tabell.updateUI();

        } catch (InfException ex) {
            Logger.getLogger(HanteraAgent.class.getName()).log(Level.SEVERE, getWarningString(), ex);
            System.out.println("InfFel " + ex.getSQLState() + " " + ex.getMessage() + " " + ex.getLocalizedMessage());
        }

    }

    //Anger omr�den i combobox
    private void setOmradeCB() {

        try {

            Vector< String> vc = new Vector<>();
            vc.addAll(mib.fetchColumn("SELECT BENAMNING FROM OMRADE"));
            ComboBoxModel lvBox = new DefaultComboBoxModel(vc);
            omrade.getModel().setSelectedItem(vc.firstElement());
            omrade.setModel(lvBox);

        } catch (InfException ettUndantag) {
            JOptionPane.showMessageDialog(null, "Databasfel!");
            System.out.println("3 Internt felmeddelande" + ettUndantag.getMessage());
        } catch (IndexOutOfBoundsException ettUndantag) {
            JOptionPane.showMessageDialog(null, "N�got gick fel!");
            System.out.println("");
        }

    }

    // Metod f�r att skriva ut tabellen utan parametrar. Standardfr�ga f�r att lista alla alien anv�nds som parameter f�r metoden som tar parameter.
    private void skrivTabell() {
        skrivTabell(getAgentLista());
    }

    // Metod f�r att skriva ut tabellen med parameter i form av SQL-fr�ga
    private void skrivTabell(String specQuery) {
        // Lokala variablar f�r at h�mta data fr�n databasen samt f�r att kunna anv�nda konstruktorn f�r tabellmodellen.
        ArrayList< HashMap< String, String>> hmData;
        Vector< Vector< String>> vRad = new Vector<>();

        try {
            vKolumn = new Vector<>();
            hmData = mib.fetchRows(specQuery);

            //        L�gger till all data rad f�r rad till vektorn vRad
            for (HashMap<String, String> hm : hmData) {
                vData = new Vector<>();
                vData.addAll(hm.values());
                vRad.add(vData);
            }

            // L�gger till kolumner
            vKolumn.addAll(hmData.get(0).keySet());

            // Anropar konstruktorn f�r en modell med de tidigare vektorerna som parametrar. S�tter denna modell som mall f�r tabellen.
            model.setDataVector(vRad, vKolumn);
            tabell.setRowSelectionAllowed(true);

            // Snyggar till ordningen p� kolumner
            tabell.getColumnModel().moveColumn(2, 0);
            tabell.getColumnModel().moveColumn(5, 4);

            // Skriv omr�de med text ist�llet f�r ID
            for (int i = 0; i < tabell.getRowCount(); i++) {
                String omradeText = "";
                omradeText = mib.fetchSingle("SELECT BENAMNING FROM OMRADE WHERE OMRADES_ID = " + tabell.getValueAt(i, 5));
                tabell.setValueAt(omradeText, i, 5);
            }

            // Om n�got g�r fel
        } catch (InfException ettUndantag) {
            JOptionPane.showMessageDialog(null, "Databasfel!");
            System.out.println("inf fel 3 Internt felmeddelande" + ettUndantag.getMessage());
        } catch (IndexOutOfBoundsException ettUndantag) {
            JOptionPane.showMessageDialog(null, "N�got gick fel!");
            System.out.println("headFel --  " + ettUndantag.getMessage() + " -- " + ettUndantag.getLocalizedMessage());
        } catch (NullPointerException u) {
            JOptionPane.showMessageDialog(null, "Inga resultat...");

            System.err.println("-- NullPointerEx --");
        }

    }

    // Skapar standardmodellen f�r tabellen(Alla agenter) och anropar skrivTabell.
    private void setTableModel() {
        setAgentLista("SELECT AGENT_ID, NAMN, TELEFON, ANSTALLNINGSDATUM, ADMINISTRATOR, OMRADE FROM AGENT");
        model = (DefaultTableModel) tabell.getModel();
        model.getDataVector().removeAllElements();
        tabell.setModel(model);
        skrivTabell();
    }

// St�nger f�nstret och �ppnar admin eller agent
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

                                attributKnapp.setText("�ndra");
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

                                jLabel6.setText("V�lj agent");

                                jLabel7.setText("V�lj attribut att �ndra");

                                attributVarde.setColumns(12);
                                attributVarde.addActionListener(new java.awt.event.ActionListener() {
                                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                attributVardeActionPerformed(evt);
                                                }
                                });

                                jLabel9.setText("Ange nytt v�rde:");

                                addAgent.setText("L�gg till agent");
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

                                kontorschef.setText("G�r till kontorschef");
                                kontorschef.addActionListener(new java.awt.event.ActionListener() {
                                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                kontorschefActionPerformed(evt);
                                                }
                                });

                                omradeschef.setText("G�r till omr�deschef i");
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

                    // Anropar metoden editAgent() n�r man klickar p� attributKnapp.
    private void attributKnappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attributKnappActionPerformed
        if (Validering.finnsText(attributVarde) && tabell.getSelectedRow() != -1) {
            editAgent();      // TODO add your handling code here:
        } else {
            JOptionPane.showMessageDialog(null, "V�lj en agent genom att klicka i tabellen");
        }
    }//GEN-LAST:event_attributKnappActionPerformed

    // Auto
    private void attributVardeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attributVardeActionPerformed
                                // TODO add your handling code here:
    }//GEN-LAST:event_attributVardeActionPerformed

    // St�ng och g� tillbaka
    private void tillbakaKnappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tillbakaKnappActionPerformed
                                avslut(false);
    }//GEN-LAST:event_tillbakaKnappActionPerformed

    // �ndrar kontorschef till vald agent
    private void kontorschefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kontorschefActionPerformed

        if (tabell.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "V�lj en agent genom att klicka i tabellen");
        } else {
        try {
                                setAgID(tabell.getValueAt(tabell.getSelectedRow(), 0).toString());
            mib.update("UPDATE KONTORSCHEF SET AGENT_ID = " + getAgID() + " WHERE KONTORSBETECKNING LIKE '�rebrokontoret'");
            JOptionPane.showMessageDialog(null, "Vald agent �r nu kontorschef");
        } catch (InfException ex) {
            Logger.getLogger(HanteraAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_kontorschefActionPerformed

    // �ndrar omr�deschef f�r valt omr�de till vald agent
    private void omradeschefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_omradeschefActionPerformed
     if (tabell.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "V�lj en agent genom att klicka i tabellen");
     } else {
         if (omrade.getSelectedItem() == null) {
            omrade.setSelectedIndex(0);
        }

        try {

            // F� ID f�r vald agent
            int rad = tabell.getSelectedRow();
            String a = (String) tabell.getValueAt(rad, 0);
            setAgID(a);

            // Sparar agent_id och omr�des_id i ArrayLists
            ArrayList< String> agentAL, omradeAL;
            String oi = mib.fetchSingle("SELECT OMRADES_ID FROM OMRADE WHERE BENAMNING LIKE '" + omrade.getSelectedItem() + "'");
            agentAL = mib.fetchColumn("SELECT AGENT_ID FROM OMRADESCHEF");
            omradeAL = mib.fetchColumn("SELECT OMRADE FROM OMRADESCHEF");

            // Kollar om det redan finns en chef f�r valt omr�de samt om valt omr�de redan har en chef
            if (agentAL.contains(agID) || omradeAL.contains(oi)) {
                // Tar bort f�reg�ende uppdrag om det finns.
                mib.delete("DELETE FROM OMRADESCHEF WHERE OMRADE = " + oi + " OR AGENT_ID = " + agID);
                // L�gger till omr�deschef
                mib.insert("INSERT INTO OMRADESCHEF VALUES(" + agID + ", " + oi + ");");
            } else {
                // L�gger till direkt om inte finns sedan tidigare.
                mib.insert("INSERT INTO OMRADESCHEF VALUES(" + agID + ", " + oi + ");");

            }

            JOptionPane.showMessageDialog(null, "Vald agent �r nu omr�deschef �ver valt omr�de");

            //  mib.insert("INSERT INTO OMRADESCHEF VALUES(" + s + ", " + agID + ");");
        } catch (InfException ex) {
            System.err.println(ex.getMessage() + ex.getLocalizedMessage() + ex.getSQLState() + ex.getErrorCode() + ex.getNextException());
            Logger.getLogger(HanteraAgent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_omradeschefActionPerformed

            // metod f�r att l�gga till ny agent med standardv�rden.
                private void addAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAgentActionPerformed

                    try {
                        // Ber anv�ndaren v�lja l�senord
                        String losen = JOptionPane.showInputDialog(null, "Ange l�senord f�r ny agent");

                        // Kollar om l�senordet redan �r upptaget
                        String hittaAlien = "SELECT LOSENORD FROM ALIEN;";
                        String hittaAgent = "SELECT LOSENORD FROM AGENT;";
                        ArrayList<String> allaLosen = new ArrayList<>();
                        allaLosen.addAll(mib.fetchColumn(hittaAlien));
                        allaLosen.addAll(mib.fetchColumn(hittaAgent));

                        if (allaLosen.contains(losen)) {
                            JOptionPane.showMessageDialog(null, "Testa med ett annat l�senord.");
                        } else {
                        int nextID = Integer.parseInt(mib.fetchSingle("SELECT MAX(AGENT_ID) FROM AGENT")) + 1;
                        String id = "" + nextID;
                        // L�gger till en agent med valt l�senord och standardv�rden.
                        mib.insert("INSERT INTO AGENT VALUES(" + id + ", 'Agent NY', '0', DATE '2020-05-01', 'N', '" + losen + "', 1)");
                        // Skriver ut ny tabell
                          skrivTabell();
                        }
                    } catch (InfException ex) {
                        Logger.getLogger(HanteraAgent.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }//GEN-LAST:event_addAgentActionPerformed

                // Metod f�r att ta bort vald agent
                private void deleteAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAgentActionPerformed
                      if (tabell.getSelectedRow() == -1) {
                            JOptionPane.showMessageDialog(null, "V�lj en agent genom att klicka i tabellen");
                      } else {
                          try {
                            setAgID(tabell.getValueAt(tabell.getSelectedRow(), 0).toString());
                              String finnsID = mib.fetchSingle("SELECT AGENT_ID FROM AGENT WHERE AGENT_ID <> " + getAgID());
                              ArrayList<String> ejOmradesChef = mib.fetchColumn("SELECT AGENT_ID FROM OMRADESCHEF");
                              for (String s : ejOmradesChef) {
                                  if (finnsID.equalsIgnoreCase(s)) {
                                finnsID = mib.fetchSingle("SELECT AGENT_ID FROM AGENT WHERE AGENT_ID <> " + getAgID() + " AND AGENT_ID <> " + finnsID);
                            }
                        }

                        mib.update("UPDATE ALIEN SET ANSVARIG_AGENT = " + finnsID + " WHERE ANSVARIG_AGENT = " + getAgID());
                        mib.update("UPDATE INNEHAR_FORDON SET AGENT_ID = " + finnsID + " WHERE AGENT_ID = " + getAgID());
                        mib.update("UPDATE INNEHAR_UTRUSTNING SET AGENT_ID = " + finnsID + " WHERE AGENT_ID = " + getAgID());
                        mib.update("UPDATE KONTORSCHEF SET AGENT_ID = " + finnsID + " WHERE AGENT_ID = " + getAgID());
                        mib.update("UPDATE OMRADESCHEF SET AGENT_ID = " + finnsID + " WHERE AGENT_ID = " + getAgID());
                        mib.delete("DELETE FROM AGENT WHERE AGENT_ID = " + getAgID());
                        skrivTabell();
                       JOptionPane.showMessageDialog(null, "Agenten �r borttagen och ansvaren �verlagda p� en kollega.");
                    } catch (InfException ex) {
                        ex.printError();
                    }
                    }
                }//GEN-LAST:event_deleteAgentActionPerformed

                // Auto
                private void aCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_aCBItemStateChanged


                                // TODO add your handling code here:
                }//GEN-LAST:event_aCBItemStateChanged

                // Auto
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
