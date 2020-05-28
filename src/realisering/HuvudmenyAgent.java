package realisering;

import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.*;
import oru.inf.*;

/**
 * Klass för agenter samt för administratörer för att hantera aliens.
 * Huvudfönster för agenter utan administratörsbehörighet.
 */
public class HuvudmenyAgent extends javax.swing.JFrame {

    // Deklaration av variabler som behövs.
    private String agentID, rasID, all, alienLista, plats, platsID, ras;
    private InfDB mib;
    private ComboBoxModel lvBox, rasModell, platsModell, attrModell, rasModellByt;
    private Vector<String> vC, vKolumn, vData, allaPlatser;
    private DefaultTableModel model;

    /**
     * Konstruktor. Kallar metoder för att sätta rätt värden på text och
     * comboboxes. Kollar även om användaren är vanlig agent eller admin.
     */
    public HuvudmenyAgent(InfDB mib) {
        initComponents();
        this.mib = mib;
        agentID = Login.getAgentID();
        setLabelInloggNamn();
        setcBoxPlats();
        setGetCbModel();
        setcBoxRas();
        setAttributVal();
        setGetTableModel();
        if (!Login.getAdmin()) {
            deleteAlien.setVisible(false);
        }
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
        allaPlatser = new Vector<>();
        try {
            allaPlatser.addAll(mib.fetchColumn("SELECT BENAMNING FROM PLATS"));

            
        } catch (InfException ettUndantag) {
            JOptionPane.showMessageDialog(null, "Databasfel!");
            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
        }
        
        catch (Exception ettUndantag) {
            JOptionPane.showMessageDialog(null, "Något gick fel!");
            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
        }

        allaPlatser.insertElementAt("Alla", 0);
        platsModell = new DefaultComboBoxModel(allaPlatser);
        cBoxPlats.setModel(platsModell);

    }

    // Anger värdena i cBoxRas
    private void setcBoxRas() {
        Vector<String> vRas = new Vector<>();
        Vector<String> vRasByt = new Vector<>();
        vRas.add("Boglodite");
        vRas.add("Squid");
        vRas.add("Worm");
        vRasByt.addAll(vRas);
        vRas.add(0, "Alla");

        rasModell = new DefaultComboBoxModel(vRas);
        rasModellByt = new DefaultComboBoxModel(vRasByt);
        cBoxRas.setModel(rasModell);
        cBoxBytRas.setModel(rasModellByt);
        cBoxRas.setSelectedIndex(0);
    }

    // Anger värderna i cBoxAttrVal
    private void setAttributVal() {
        Vector<String> attributVector = new Vector<>();
        attributVector.add("NAMN");
        attributVector.add("PLATS");
        attributVector.add("TELEFON");
        attributVector.add("ANSVARIG_AGENT");

        attrModell = new DefaultComboBoxModel(attributVector);
        cBoxAttrVal.setModel(attrModell);
    }

    // Metod för att skriva ut tabellen utan parametrar. Standardfråga för att lista alla alien används som parameter för metoden som tar parameter.
    private void skrivTabell() {
        setAlienLista("SELECT ALIEN_ID, REGISTRERINGSDATUM, NAMN, TELEFON, PLATS, ANSVARIG_AGENT FROM ALIEN ");
        skrivTabell(getAlienLista());
    }

    // Metod för att skriva ut tabellen.
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

            // Läger till kolumnerna
            vKolumn.addAll(hmData.get(0).keySet());

            // Anropar konstruktorn för en modell med de tidigare vektorerna som parametrar. Sätter denna modell som mall för tabellen.
            model.setDataVector(vRad, vKolumn);
            tabell.setRowSelectionAllowed(true);

            // Snyggar till ordningen på kolumnerna
            tabell.getColumnModel().moveColumn(3, 0);
            tabell.getColumnModel().moveColumn(4, 2);

            // Om något går fel
        } catch (InfException ettUndantag) {
            JOptionPane.showMessageDialog(null, "Databasfel!");
            System.out.println("inf fel 3 Internt felmeddelande" + ettUndantag.getMessage());
        } catch (IndexOutOfBoundsException ettUndantag) {
            JOptionPane.showMessageDialog(null, "Något gick fel!");
            System.out.println("headFel --  " + ettUndantag.getMessage() + " -- " + ettUndantag.getLocalizedMessage());
        } catch (NullPointerException u) {
            JOptionPane.showMessageDialog(null, "Inga resultat...");
            cBoxOmrade.setSelectedIndex(0);
            cBoxRas.setSelectedIndex(0);
            cBoxPlats.setSelectedIndex(0);
        }
    }

    // Metod som skapar och returnerar en modell för hur cBoxOmrade ska se ut
    private ComboBoxModel setGetCbModel() {
        vC = new Vector<>();
        try {
            vC.addAll(mib.fetchColumn("SELECT BENAMNING FROM OMRADE"));
        } catch (InfException ex) {
            Logger.getLogger(HuvudmenyAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        // lägger till ett alternativ för alla områden
        all = "Alla";
        vC.insertElementAt(all, 0);
        lvBox = new DefaultComboBoxModel(vC);
        lvBox.setSelectedItem(lvBox.getElementAt(0));
        cBoxOmrade.setModel(lvBox);
        return lvBox;

    }

    // metod som rensar, skapar och returnerar en basmodell för tabellen.
    private TableModel setGetTableModel() {
        setAlienLista("SELECT ALIEN_ID, REGISTRERINGSDATUM, NAMN, TELEFON, PLATS, ANSVARIG_AGENT FROM ALIEN");
        model = (DefaultTableModel) tabell.getModel();
        model.getDataVector().removeAllElements();
        tabell.setModel(model);
        skrivTabell();
        return model;

    }

    // Metod för att stänga fönstret
    private void avslut(boolean b) {

        setEnabled(b);
        this.dispose();
        this.setVisible(b);

    }

    /**
     * @return the agentLista, SQL-fråga för att lista alla aliens.
     */
    private String getAlienLista() {
        return alienLista;
    }

    /**
     * @param agentLista the agentLista to set. Metod för att ändra SQL-frågan
     */
    private void setAlienLista(String agentLista) {
        this.alienLista = agentLista;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHuvudmenyAgent = new javax.swing.JLabel();
        lblInloggNamn = new javax.swing.JLabel();
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
        sokruta = new javax.swing.JTextField();
        btnTillbakaInlogg = new javax.swing.JButton();
        lblRasVald = new javax.swing.JLabel();
        attributKnapp = new javax.swing.JButton();
        attributVarde = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cBoxAttrVal = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        bytRas = new javax.swing.JButton();
        visaDatumKnapp = new javax.swing.JButton();
        deleteAlien = new javax.swing.JButton();
        addAlien = new javax.swing.JButton();
        cBoxBytRas = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 500));
        setSize(new java.awt.Dimension(800, 500));

        lblHuvudmenyAgent.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblHuvudmenyAgent.setText("Huvudmeny - Agent");

        lblInloggNamn.setText("Du är inloggad som:");

        btnHanteraUtrustning.setText("Hantera utrustning");
        btnHanteraUtrustning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHanteraUtrustningActionPerformed(evt);
            }
        });

        cBoxPlats.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cBoxPlatsItemStateChanged(evt);
            }
        });
        cBoxPlats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cBoxPlatsActionPerformed(evt);
            }
        });

        cBoxOmrade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cBoxOmradeItemStateChanged(evt);
            }
        });

        cBoxRas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cBoxRasItemStateChanged(evt);
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
        txtFldDatumFran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFldDatumFranMouseClicked(evt);
            }
        });

        txtFldDatumTill.setText("Till");
        txtFldDatumTill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFldDatumTillMouseClicked(evt);
            }
        });

        lblOmrade.setText("Område");

        lblPlats.setText("Plats");

        lblRas.setText("Ras");

        lblDatum.setText("Datum");

        sokruta.setText("Sök alien...");
        sokruta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sokrutaMouseClicked(evt);
            }
        });
        sokruta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sokrutaKeyPressed(evt);
            }
        });

        btnTillbakaInlogg.setText("Tillbaka till inlogg");
        btnTillbakaInlogg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaInloggActionPerformed(evt);
            }
        });

        lblRasVald.setText("Ras på vald Alien: ");

        attributKnapp.setText("Ändra");
        attributKnapp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                attributKnappMouseClicked(evt);
            }
        });
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

        cBoxAttrVal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Välj attribut att ändra");

        bytRas.setText("Byt ras");
        bytRas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bytRasMouseClicked(evt);
            }
        });
        bytRas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bytRasActionPerformed(evt);
            }
        });

        visaDatumKnapp.setText("Visa");
        visaDatumKnapp.setToolTipText("");
        visaDatumKnapp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                visaDatumKnappMouseClicked(evt);
            }
        });

        deleteAlien.setText("Ta bort alien");
        deleteAlien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteAlienMouseClicked(evt);
            }
        });
        deleteAlien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAlienActionPerformed(evt);
            }
        });

        addAlien.setText("Lägg till alien");
        addAlien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addAlienMouseClicked(evt);
            }
        });
        addAlien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAlienActionPerformed(evt);
            }
        });

        cBoxBytRas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteAlien, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addAlien, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cBoxAttrVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cBoxBytRas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bytRas, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblRasVald))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(lblInloggNamn))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cBoxOmrade, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cBoxPlats, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblOmrade)
                                        .addGap(53, 53, 53)
                                        .addComponent(lblPlats)
                                        .addGap(30, 30, 30)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(lblRas))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(cBoxRas, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblHuvudmenyAgent)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnTillbakaInlogg, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sokruta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 83, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtFldDatumFran, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFldDatumTill, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(visaDatumKnapp)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblDatum)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnHanteraUtrustning)))))))
                .addGap(33, 33, 33))
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
                            .addComponent(cBoxPlats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(visaDatumKnapp)
                            .addComponent(sokruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(btnTillbakaInlogg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHanteraUtrustning)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblRasVald))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cBoxAttrVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bytRas)
                    .addComponent(cBoxBytRas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(attributVarde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(attributKnapp)
                    .addComponent(addAlien)
                    .addComponent(deleteAlien))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     *
     * Stäng fönster och öppna HanteraUtrustning
     */
    private void btnHanteraUtrustningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHanteraUtrustningActionPerformed
        new HanteraUtrustning(mib).setVisible(true);
        avslut(false);
    }//GEN-LAST:event_btnHanteraUtrustningActionPerformed

    // Autogenererad
    private void cBoxPlatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cBoxPlatsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cBoxPlatsActionPerformed

    // Stäng och gå tillbaka till login
    private void btnTillbakaInloggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaInloggActionPerformed
        dispose();
        new Login(mib).setVisible(true);
    }//GEN-LAST:event_btnTillbakaInloggActionPerformed

    // Metod för att visa vald aliens ras
    private void tabellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabellMouseClicked

        int rad = tabell.getSelectedRow();
        ras = "";
        String id =  (String) tabell.getValueAt(rad, 0);

        try {
            // Skapar ArrayList:s och lägger till Alien_ID för de olika raserna
            ArrayList<String> bog = new ArrayList<>();
            addNotNull(bog, mib.fetchColumn("SELECT ALIEN_ID FROM BOGLODITE"));
            ArrayList<String> squid = new ArrayList<>();
            addNotNull(squid, mib.fetchColumn("SELECT ALIEN_ID FROM SQUID"));
            ArrayList<String> worm = new ArrayList<>();
            addNotNull(worm, mib.fetchColumn("SELECT ALIEN_ID FROM WORM"));

            // Kollar vilken ras vald alien tillhör och ändrar lblRasVald utefter
            if (bog.contains(id)) {
                ras = "Boglodite";
            } else if (squid.contains(id)) {
                ras = "Squid";
            } else if (worm.contains(id)) {
                ras = "Worm";
            } else { ras = "Ingen";}
            lblRasVald.setText("Ras för vald alien: " + ras);
        } catch (InfException ex) {
            Logger.getLogger(HuvudmenyAgent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        }
    }//GEN-LAST:event_tabellMouseClicked

    // Auto
    private void attributKnappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attributKnappActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_attributKnappActionPerformed

    // Auto
    private void attributVardeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attributVardeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_attributVardeActionPerformed

    // Auto
    private void bytRasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bytRasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bytRasActionPerformed

    // Skriver ut en lista över aliens med de som tillhör vald ras. Skriver ut alla om ingen ras är vald.
    private void cBoxRasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cBoxRasItemStateChanged

         String aktiv = cBoxRas.getSelectedItem().toString().toUpperCase();
        if (evt.getStateChange() == 1 && !cBoxRas.getSelectedItem().toString().equals("Alla")) {
            skrivTabell("SELECT ALIEN_ID, REGISTRERINGSDATUM, NAMN, TELEFON, PLATS, ANSVARIG_AGENT FROM ALIEN where ALIEN_ID = ANY (SELECT ALIEN_ID FROM " + aktiv + ")");

        } else {
            skrivTabell();
        }

        cBoxOmrade.setSelectedIndex(0);
        cBoxPlats.setSelectedIndex(0);
    }//GEN-LAST:event_cBoxRasItemStateChanged

    // Skriver ut en lista med de aliens som finns på vald plats.
    private void cBoxPlatsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cBoxPlatsItemStateChanged

        if (evt.getStateChange() == 1 && !cBoxPlats.getSelectedItem().toString().equals("Alla")) {
            skrivTabell(getAlienLista() + " where PLATS = " + valPlats());
            System.out.println("Kom hit");

        } else {
            skrivTabell();
        }

       cBoxOmrade.setSelectedIndex(0);
       cBoxRas.setSelectedIndex(0);

    }//GEN-LAST:event_cBoxPlatsItemStateChanged

    // Skriver ut en lista med aliens som finns i valt område.
    private void cBoxOmradeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cBoxOmradeItemStateChanged

        String faOmrade = "SELECT PLATS_ID FROM PLATS WHERE FINNS_I = (" + valOmrade() + ")";
         if (evt.getStateChange() == 1 && !cBoxOmrade.getSelectedItem().toString().equals("Alla")) {
             skrivTabell(getAlienLista() + " WHERE PLATS = ANY " + valOmrade());
            System.out.println("Kom hit");

        } else {
            skrivTabell();
        }
         cBoxPlats.setSelectedIndex(0);
         cBoxRas.setSelectedIndex(0);

    }//GEN-LAST:event_cBoxOmradeItemStateChanged

    // Söker efter alien med namn när man klivkar enter. Skriver ut resultatet.
    private void sokrutaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sokrutaKeyPressed


        String query = getAlienLista() + " WHERE LOWER (NAMN) LIKE " + "LOWER ('%" + sokruta.getText() + "%')";

        if (evt.getKeyCode() == 10 && Validering.finnsText(sokruta)) {
                                                skrivTabell(query);
                                                }                


    }//GEN-LAST:event_sokrutaKeyPressed

    // Rensar texten i sökrutan vid klick.
    private void sokrutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sokrutaMouseClicked

        sokruta.setText("");

    }//GEN-LAST:event_sokrutaMouseClicked

    // Skriver ut en lista över aliens registrerade mellan valda datum.
    private void visaDatumKnappMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_visaDatumKnappMouseClicked
        if (Validering.finnsText(txtFldDatumFran) && Validering.finnsText(txtFldDatumTill)) {
        skrivTabell(getAlienLista() + " WHERE REGISTRERINGSDATUM BETWEEN '" + txtFldDatumFran.getText() + "' AND '" + txtFldDatumTill.getText() + "'");
        }
    }//GEN-LAST:event_visaDatumKnappMouseClicked

    // Rensar text vid klick
    private void txtFldDatumFranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFldDatumFranMouseClicked
        txtFldDatumFran.setText("");
    }//GEN-LAST:event_txtFldDatumFranMouseClicked

    // Rensar text vid klick.
    private void txtFldDatumTillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFldDatumTillMouseClicked
      txtFldDatumTill.setText("");
    }//GEN-LAST:event_txtFldDatumTillMouseClicked

    // Auto
    private void deleteAlienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAlienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteAlienActionPerformed

    // Auto
    private void addAlienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAlienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addAlienActionPerformed

    // Anropar metoden editAlien() när man klickar på attributKnapp.
    private void attributKnappMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_attributKnappMouseClicked
        if (Validering.finnsText(attributVarde)) {
        editAlien();
        }
    }//GEN-LAST:event_attributKnappMouseClicked

    // Lägger till en ny alien med standardvärden och ras Boglodite.
    private void addAlienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addAlienMouseClicked

         try {
                                                String losen = JOptionPane.showInputDialog(null, "Ange lösenord för ny alien");
             int radAntal = model.getRowCount() + 1;
                        String id = "" + radAntal;
             mib.insert("INSERT INTO ALIEN VALUES(" + id + ", DATE '2020-05-01', '" + losen + "', 'Alien NY', '0', '1', '1')");
             mib.insert("INSERT INTO BOGLODITE VALUES (" + id + ", 2)");
             skrivTabell();
                    } catch (InfException ex) {
                        Logger.getLogger(HanteraAgent.class.getName()).log(Level.SEVERE, null, ex);
                    }

    }//GEN-LAST:event_addAlienMouseClicked

    // Tar bort vald alien från databasen.
    private void deleteAlienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteAlienMouseClicked

        try {
                      String aID = tabell.getValueAt(tabell.getSelectedRow(), 0).toString();
                        mib.delete("DELETE FROM ALIEN WHERE ALIEN_ID = " + aID);
                        skrivTabell();
                    } catch (InfException ex) {
                        Logger.getLogger(HanteraAgent.class.getName()).log(Level.SEVERE, null, ex);
                    }

    }//GEN-LAST:event_deleteAlienMouseClicked

    // Metod för att byta ras.
    private void bytRasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bytRasMouseClicked

        int rad = tabell.getSelectedRow();
        String id = (String) tabell.getValueAt(rad, 0);

        try {
            // Lägger till alla ALIEN_ID från rastabellerna. För att komma undan nullPointerEx efter den gamla rasen tagits bort.
            ArrayList<String> alla = new ArrayList<>();
            addNotNull(alla, mib.fetchColumn("SELECT ALIEN_ID FROM BOGLODITE"));
            addNotNull(alla, mib.fetchColumn("SELECT ALIEN_ID FROM SQUID"));
            addNotNull(alla, mib.fetchColumn("SELECT ALIEN_ID FROM WORM"));


            String vald = cBoxBytRas.getSelectedItem().toString().toUpperCase();
            if (alla.contains(id)) {
                mib.delete("DELETE FROM " + ras.toUpperCase() + " WHERE ALIEN_ID = " + id);
            }
            if (vald.equalsIgnoreCase("WORM")) {
                mib.insert("INSERT INTO WORM VALUES(" + id + ")");

                
            } else {
                // Är rasen inte worm är antalet kolumner två istället för en. Lägger till val för detta.
                String ben = JOptionPane.showInputDialog("Antal boogies/ben:");

                mib.insert("INSERT INTO " + vald + " VALUES(" + id + ", " + ben + ")");
            }


        } catch (InfException ex) {
            Logger.getLogger(HuvudmenyAgent.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_bytRasMouseClicked

    // För att undvika problem med SQL-frågor som returnerar null
    public static <E> void addNotNull(List<E> list, Collection<? extends E> c) {
    if (c != null) {
        list.addAll(c);
    }
}

    // Metod som returnerar valt områdes områdes_ID. Returnerar ett slumpvis om det inte fungerar.
    private String valOmrade() {

                                try {
                                                String aktiv = (String) cBoxOmrade.getSelectedItem();
                                                String omID = ("SELECT OMRADES_ID FROM OMRADE");
                                                if (cBoxOmrade.getSelectedIndex() != 0) {
                                                                omID = omID + " WHERE BENAMNING LIKE '" + aktiv + "'";
                                                    String oID = "(SELECT PLATS_ID FROM PLATS WHERE FINNS_I = " + mib.fetchSingle(omID) + ")";
                                                                return oID;
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

// Metod som returnerar Plats_ID för vald plats.
    private String valPlats() {

        try {
            String aktiv = (String) cBoxPlats.getSelectedItem();
            if (cBoxPlats.getSelectedIndex() != 0 || !aktiv.equals("Alla")) {
                plats = ("SELECT PLATS_ID FROM PLATS WHERE BENAMNING LIKE  '" + aktiv + "'");
                platsID = mib.fetchSingle(plats);
                return platsID;
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

    // Metod för att ändra valt attribut för vald alien
private void editAlien() {
    
     int rad = tabell.getSelectedRow();

    // Få ID för vald alien
    String alienID = tabell.getValueAt(rad, 0).toString();

    // Få valt attribut
    String attr = cBoxAttrVal.getSelectedItem().toString();

    // Ifyllt värde
        String nyttV = "'" + attributVarde.getText() + "'";

    // SQL-fråga för att ändra valt attribut till valt värde.
    String fraga = "UPDATE ALIEN SET " + attr + " = " + nyttV + " WHERE ALIEN_ID = " + alienID + ";";
    try {
        // uppdatera tabell
            mib.update(fraga);
            model.fireTableDataChanged();
            model.fireTableStructureChanged();
            tabell.updateUI();
            skrivTabell();

        } catch (InfException ex) {
            Logger.getLogger(HanteraAgent.class.getName()).log(Level.SEVERE, getWarningString(), ex);
            System.out.println("InfFel " + ex.getSQLState() + " " + ex.getMessage() + " " + ex.getLocalizedMessage());
        }

    
    }


    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAlien;
    private javax.swing.JButton attributKnapp;
    private javax.swing.JTextField attributVarde;
    private javax.swing.JButton btnHanteraUtrustning;
    private javax.swing.JButton btnTillbakaInlogg;
    private javax.swing.JButton bytRas;
    private javax.swing.JComboBox<String> cBoxAttrVal;
    private javax.swing.JComboBox<String> cBoxBytRas;
    private javax.swing.JComboBox<String> cBoxOmrade;
    private javax.swing.JComboBox<String> cBoxPlats;
    private javax.swing.JComboBox<String> cBoxRas;
    private javax.swing.JButton deleteAlien;
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
    private javax.swing.JTextField sokruta;
    private javax.swing.JTable tabell;
    private javax.swing.JTextField txtFldDatumFran;
    private javax.swing.JTextField txtFldDatumTill;
    private javax.swing.JButton visaDatumKnapp;
    // End of variables declaration//GEN-END:variables
}