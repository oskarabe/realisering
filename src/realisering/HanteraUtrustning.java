package realisering;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 * Denna klass används för att se vilka utrustningar som finns registrerade.
 * Klassen ger även möjlighet att ta sig till nya fönster som kan
 * lägga till, registrera och ta bort utrustning
 */
public class HanteraUtrustning extends javax.swing.JFrame {

    private InfDB mib;
    private boolean isAdmin;
    private TableColumnModel cmodel;
    private TableColumn tC;
    private String utrustningLista;
    private Vector<String> vK, vL, vT;

    DefaultTableModel model;
    
    //Konstruktor för HanteraUtrustning
    //Behöver ha en beskrivning av hur tabellerna är skapta
    public HanteraUtrustning(InfDB mib) {
        
        this.mib = mib;
        model = new DefaultTableModel() {

                                                @Override
                                                public boolean isCellEditable(int row, int column) {
                                                                return false;
                                                }
                                        };
                                initComponents();
                                isAdmin = Login.getAdmin();
                                checkAdminStatus();
                                setGetTableModel("SELECT AGENT_ID, UTKVITTERINGSDATUM, UTRUSTNING.UTRUSTNINGS_ID, BENAMNING FROM UTRUSTNING, " +
                                              "INNEHAR_UTRUSTNING WHERE UTRUSTNING.UTRUSTNINGS_ID = INNEHAR_UTRUSTNING.UTRUSTNINGS_ID " +
                                              "ORDER BY UTRUSTNINGS_ID ASC;");
                                
    }
    
    protected void skrivTabell() {
                                skrivTabell(getUtrustningLista());
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
                                                                while (i < 4) {
                                                                                String key = vL.get(i);
                                                                                vK.add(key);
                                                                                System.out.println(key);
                                                                                i++;
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
                                                System.err.println("-- NullPointerEx --");
                                }
                }

                public TableModel setGetTableModel(String utrustningListan) {
                                setUtrustningLista(utrustningListan);

                                model = (DefaultTableModel) tabell.getModel();
                                model.getDataVector().removeAllElements();
                                tabell.setAutoCreateRowSorter(true);
                                tabell.setModel(model);
                                model.fireTableDataChanged();

                                skrivTabell();
                                return model;
                }
                
    //Ifall användaren inte har adminstatus inaktiveras btnTaBort
    private void checkAdminStatus()
    {
        if(isAdmin == true)
        {
            btnTaBort.setEnabled(true);
        }
        else
        {
            btnTaBort.setEnabled(false);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnTaBort = new javax.swing.JButton();
        lblUtrustning = new javax.swing.JLabel();
        sokruta = new javax.swing.JTextField();
        btnRegistrera = new javax.swing.JButton();
        btnTillbaka = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabell = new javax.swing.JTable();
        btnSok = new javax.swing.JButton();
        seAlla = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        btnTaBort.setText("Ta bort utrustning");
        btnTaBort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaBortActionPerformed(evt);
            }
        });

        lblUtrustning.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblUtrustning.setText("Utrustning");

        sokruta.setText("Sök utrustning...");
        sokruta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sokrutaMouseClicked(evt);
            }
        });

        btnRegistrera.setText("Registrera utrustning");
        btnRegistrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistreraActionPerformed(evt);
            }
        });

        btnTillbaka.setText("Tillbaka");
        btnTillbaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaActionPerformed(evt);
            }
        });

        tabell.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabell);

        btnSok.setText("Sök");
        btnSok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSokActionPerformed(evt);
            }
        });

        seAlla.setText("Se alla");
        seAlla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seAllaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnRegistrera)
                        .addGap(40, 40, 40)
                        .addComponent(btnTaBort)
                        .addGap(221, 221, 221))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(sokruta, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSok, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(lblUtrustning))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seAlla)))
                        .addGap(297, 297, 297))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(299, 299, 299)
                        .addComponent(btnTillbaka)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUtrustning)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sokruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSok)
                    .addComponent(seAlla))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaBort)
                    .addComponent(btnRegistrera))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTillbaka)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaBortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaBortActionPerformed
        dispose();
        new TaBortUtrustning(mib).setVisible(true);
        //Öppna fönster för att ta bort utrustning
    }//GEN-LAST:event_btnTaBortActionPerformed

    private void btnRegistreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistreraActionPerformed
        dispose();
        new RegUtrustning(mib).setVisible(true);
        //Öppna fönster för att lägga till utrustning
    }//GEN-LAST:event_btnRegistreraActionPerformed

    private void sokrutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sokrutaMouseClicked
        sokruta.setText("");
        //Rensar sökrutan när man klickar i den
    }//GEN-LAST:event_sokrutaMouseClicked

    private void btnTillbakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaActionPerformed
        //Tar användaren tillbaka till huvudmenyn när knappen "Tillbaka" trycks
        //Beroende på adminstatus öppnas olika fönster
        if(isAdmin == true)
        {
            new HuvudmenyAdmin(mib).setVisible(true);
            dispose();
        }
        else
        {
            new HuvudmenyAgent(mib).setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_btnTillbakaActionPerformed

    private void btnSokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSokActionPerformed
        //Filtrerar tabellen på angivet namn på utrustning i sökrutan
        //Validera först så att text är angiven
        if(Validering.finnsText(sokruta))
        {
        String sokOrd = sokruta.getText();
        String SQLsokord = "SELECT AGENT_ID, UTKVITTERINGSDATUM, UTRUSTNING.UTRUSTNINGS_ID, BENAMNING FROM UTRUSTNING, " +
                                              "INNEHAR_UTRUSTNING WHERE UTRUSTNING.UTRUSTNINGS_ID = INNEHAR_UTRUSTNING.UTRUSTNINGS_ID " +
                                              "AND BENAMNING = '" + sokOrd + "' ORDER BY UTRUSTNINGS_ID ASC;";
        setGetTableModel(SQLsokord);
        }
    }//GEN-LAST:event_btnSokActionPerformed

    private void seAllaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seAllaActionPerformed
        //När knappen "Se alla" trycks visas all registrerad utrustning i databasen
        String allUtrustning = "SELECT AGENT_ID, UTKVITTERINGSDATUM, UTRUSTNING.UTRUSTNINGS_ID, BENAMNING FROM UTRUSTNING, " +
                                              "INNEHAR_UTRUSTNING WHERE UTRUSTNING.UTRUSTNINGS_ID = INNEHAR_UTRUSTNING.UTRUSTNINGS_ID " +
                                              "ORDER BY UTRUSTNINGS_ID ASC;";
        setGetTableModel(allUtrustning);
    }//GEN-LAST:event_seAllaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrera;
    private javax.swing.JButton btnSok;
    private javax.swing.JButton btnTaBort;
    private javax.swing.JButton btnTillbaka;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblUtrustning;
    private javax.swing.JButton seAlla;
    private javax.swing.JTextField sokruta;
    private javax.swing.JTable tabell;
    // End of variables declaration//GEN-END:variables
                
                //Returnerar utrustningslistan
                private String getUtrustningLista() {
                                return utrustningLista;
                }

                //Set-metod för utrustningslistan
                private void setUtrustningLista(String utrustningLista) {
                                this.utrustningLista = utrustningLista;
                }
                
}