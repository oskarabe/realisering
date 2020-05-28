package realisering;

import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.*;
import oru.inf.*;

/**
 * Huvudmenyn för administratörer. Visar en tabell över alla agenter
 * Ger även möjlighet att ta sig vidare till fönster för att hantera
* agenter, aliens eller utrustning
 */
public class HuvudmenyAdmin extends javax.swing.JFrame {

    // Deklaration av variabler som behövs
                private final InfDB mib;
                private ComboBoxModel lvBox;
                private String omID, omCB, all, agentLista;
                private final String agentID;
                private Vector<String> vC, vKolumn, vData;

                DefaultTableModel model;

    //Konstruktor för HuvudmenyAdmin. Anropar metoder för att fylla i text och alternativ.
                public HuvudmenyAdmin(InfDB mib) {

                                this.mib = mib;

                                agentID = Login.getAgentID();
                                initComponents();
                                setLabelInloggNamn();
                                setGetCbModel();
                                omradeBox.setSelectedItem(lvBox.getElementAt(0));
                                setGetTableModel();

                }

    // Metod som returnerar områdes_ID för valt område
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

    // Metod för att skriva ut tabellen utan parametrar. Standardfråga för att lista alla alien används som parameter för metoden som tar parameter.
    private void skrivTabell() {
                                skrivTabell(getAgentLista());
                }

    // Metod för att skriva ut tabellen med parameter i form av SQL-fråga
    private void skrivTabell(String specQuery) {
        // Lokala variablar för at hämta data från databasen samt för att kunna använda konstruktorn för tabellmodellen.
        ArrayList< HashMap< String, String>> hmData;
        Vector< Vector< String>> vRad = new Vector<>();

        try {
            vKolumn = new Vector<>();
            hmData = mib.fetchRows(specQuery);

            //        Lägger till all data rad för rad till vektorn vRad
            for (HashMap<String, String> hm : hmData) {
                vData = new Vector<>();
                vData.addAll(hm.values());
                vRad.add(vData);
            }

            // Lägger till kolumner
            vKolumn.addAll(hmData.get(0).keySet());

            // Anropar konstruktorn för en modell med de tidigare vektorerna som parametrar. Sätter denna modell som mall för tabellen.
            model.setDataVector(vRad, vKolumn);
            tabell.setRowSelectionAllowed(true);
            tabell.getColumnModel().moveColumn(2, 0);
            tabell.getColumnModel().moveColumn(5, 4);

            // Om något går fel
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

    // Metod som skapar och returnerar en modell för hur cBoxOmrade ska se ut
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

    // metod som rensar, skapar och returnerar en basmodell för tabellen.
                private TableModel setGetTableModel() {
                    setAgentLista("SELECT AGENT_ID, NAMN, TELEFON, ANSTALLNINGSDATUM, ADMINISTRATOR, OMRADE FROM AGENT");
                                model = (DefaultTableModel) tabell.getModel();
                                model.getDataVector().removeAllElements();
                    tabell.setModel(model);
                                skrivTabell();
                                return model;
                }

    // Metod för att stänga fönstret
                private void avslut(boolean b) {
                    //      new HanteraAgent(mib).setVisible(true);

                                setEnabled(b);
                                this.dispose();
                                this.disable();
                                this.setVisible(b);

                }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        hanteraAlien = new javax.swing.JButton();
        lblFel = new javax.swing.JLabel();
        lblAdmin1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin");
        setBackground(new java.awt.Color(244, 247, 252));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        setMinimumSize(new java.awt.Dimension(800, 500));
        setPreferredSize(new java.awt.Dimension(750, 438));
        setSize(new java.awt.Dimension(800, 500));

        lblAdmin.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblAdmin.setText("Administratör");

        inloggadSom.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        inloggadSom.setText("Agent 1");

        hanteraAgent.setText("Agenter");
        hanteraAgent.setToolTipText("Hantera...");
        hanteraAgent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hanteraAgent.setMaximumSize(new java.awt.Dimension(150, 60));
        hanteraAgent.setMinimumSize(new java.awt.Dimension(150, 60));
        hanteraAgent.setPreferredSize(new java.awt.Dimension(150, 30));
        hanteraAgent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hanteraAgentActionPerformed(evt);
            }
        });

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

        hanteraUtrustning.setText("Utrustning");
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
        tabell.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
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

        lblOmrade.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblOmrade.setText("Område");

        sokruta.setColumns(12);
        sokruta.setDocument(sokruta.getDocument());
        sokruta.setText("Sök kodnamn...");
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

        hanteraAlien.setText("Aliens");
        hanteraAlien.setToolTipText("Hantera...");
        hanteraAlien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hanteraAlien.setMaximumSize(new java.awt.Dimension(150, 60));
        hanteraAlien.setMinimumSize(new java.awt.Dimension(150, 60));
        hanteraAlien.setPreferredSize(new java.awt.Dimension(150, 30));
        hanteraAlien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hanteraAlienActionPerformed(evt);
            }
        });

        lblFel.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        lblFel.setAlignmentY(0.0F);
        lblFel.setMaximumSize(new java.awt.Dimension(250, 40));
        lblFel.setMinimumSize(new java.awt.Dimension(250, 40));
        lblFel.setPreferredSize(new java.awt.Dimension(250, 40));

        lblAdmin1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblAdmin1.setText("Huvudmeny");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inloggadSom)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(omradeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(sokruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblOmrade))
                                .addGap(81, 81, 81)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAdmin)
                                    .addComponent(lblFel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(lblAdmin1)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(hanteraAlien, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(hanteraAgent, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hanteraUtrustning, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(tillbakaKnapp, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAdmin1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(inloggadSom)
                                .addGap(26, 26, 26)
                                .addComponent(lblOmrade)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(omradeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sokruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20))
                            .addComponent(lblFel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hanteraAgent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAdmin))
                        .addGap(18, 18, 18)
                        .addComponent(hanteraAlien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hanteraUtrustning, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tillbakaKnapp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Stänger fönstret och öppnar HanteraAgent vid klick
    private void hanteraAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hanteraAgentActionPerformed
        avslut(false);
        new HanteraAgent(mib).setVisible(true);
                                //Öppnar fönstret för att hantera agenter
    }//GEN-LAST:event_hanteraAgentActionPerformed

    // Tillbaka till Login
    private void tillbakaKnappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tillbakaKnappActionPerformed
        avslut(false);
        new Login(mib).setVisible(true);
                                //Tar användaren tillbaka till login-fönstret
    }//GEN-LAST:event_tillbakaKnappActionPerformed

    // Öppnar HanteraUtrustning
    private void hanteraUtrustningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hanteraUtrustningActionPerformed
        avslut(false);
        new HanteraUtrustning(mib).setVisible(true);
                                //Tar användaren till fönstret för att hantera utrustning
    }//GEN-LAST:event_hanteraUtrustningActionPerformed

    // Öppnar HuvudmenyAgent
    private void hanteraAlienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hanteraAlienActionPerformed
        avslut(false);
        new HuvudmenyAgent(mib).setVisible(true);
                                //Öppnar fönstret för att hantera aliens
    }//GEN-LAST:event_hanteraAlienActionPerformed

    // Skriver ut lista över agenter i valt område
                private void omradeBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_omradeBoxItemStateChanged
                                if (evt.getStateChange() == 1 && !omradeBox.getSelectedItem().toString().equals("Alla")) {
                                                String agentL = getAgentLista() + " WHERE OMRADE = '" + valOmrade() + "'";
                                                System.out.println(agentL);
                                                skrivTabell(agentL);

                                } else {
                                                skrivTabell();
                                }
                }//GEN-LAST:event_omradeBoxItemStateChanged

                // Söker efter agent med namn vid enter-klick
                private void sokrutaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sokrutaKeyPressed
                                String query = getAgentLista();
                    String lvpString = " WHERE LOWER (NAMN) LIKE " + "LOWER ('%" + sokruta.getText() + "%')";
                                if (!omradeBox.getSelectedItem().toString().equals("Alla")) {
                                                query = query + lvpString + " AND OMRADE LIKE '" + valOmrade() + "'";
                                } else {
                                                query += lvpString;
                                }
                                 if (evt.getKeyCode() == 10) {
                                if (Validering.finnsText(sokruta)) {

                                                System.out.println(query);
                                                skrivTabell(query);
                                                }                }//GEN-LAST:event_sokrutaKeyPressed
    }

                // Rensar text
                private void sokrutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sokrutaMouseClicked
                                lblFel.setVisible(false);
                                sokruta.setText("");
                }//GEN-LAST:event_sokrutaMouseClicked

                // Rensar text
                private void sokrutaInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_sokrutaInputMethodTextChanged
                                lblFel.setVisible(false);
                                sokruta.setText("");
                }//GEN-LAST:event_sokrutaInputMethodTextChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton hanteraAgent;
    public javax.swing.JButton hanteraAlien;
    public javax.swing.JButton hanteraUtrustning;
    public javax.swing.JLabel inloggadSom;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblAdmin;
    public javax.swing.JLabel lblAdmin1;
    private javax.swing.JLabel lblFel;
    public javax.swing.JLabel lblOmrade;
    public javax.swing.JComboBox<String> omradeBox;
    public javax.swing.JTextField sokruta;
    public javax.swing.JTable tabell;
    public javax.swing.JButton tillbakaKnapp;
    // End of variables declaration//GEN-END:variables

                //Returnera agentLista
                private String getAgentLista() {
                                return agentLista;
                }

                //Set-metod för agentLista
                private void setAgentLista(String agentLista) {
                                this.agentLista = agentLista;
                }

}
