package realisering;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 * Klass f�r att l�gga till och registrera ny utrustning i tabellen "Utrustning" och "Innehar_utrustning"
 * Den tillagda och registrerade utrustningen kommer sedan att synas i tabellen
 * i klassen "HanteraUtrustning"
 * 
 * Utrustning kan l�ggas till som vapen, teknik eller kommunikation
 * d�r anv�ndaren m�ste ange ett v�rde p� en egenskap beroende p� vilken
 * typ av utrustning som ska l�ggas till
 */
public class RegUtrustning extends javax.swing.JFrame {

    private InfDB mib;
    
    /**
     * Konstruktor f�r RegUtrustning
     */
    public RegUtrustning(InfDB mib) {
        this.mib = mib;
        initComponents();
        skapaRdoBtnGrupp();
        setEgenskap();
    }

    //Skapa en grupp av radioButtons, f�r att hantera till�gandet av utrustning
    //Endast en radioButton ska kunna vara aktiv �t g�ngen
    private void skapaRdoBtnGrupp()
    {
        ButtonGroup G = new ButtonGroup();
        G.add(rdiobtnVapen);
        G.add(rdiobtnTeknik);
        G.add(rdiobtnKom);
        rdiobtnVapen.setSelected(true);
        setEgenskap();
    }
    
    //Anger texten f�r vilken typ av egenskap som ska skrivas in
    //Beror p� vilken radioButton som �r markerad
    private void setEgenskap()
    {
        if(rdiobtnVapen.isSelected())
            txtEgenskap.setText("Kaliber");
        else if(rdiobtnTeknik.isSelected())
            txtEgenskap.setText("Kraftk�lla");
                    else if(rdiobtnKom.isSelected())
            txtEgenskap.setText("�verf�ringsteknik");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        lblRubrik1 = new javax.swing.JLabel();
        btnRegistrera = new javax.swing.JButton();
        txtValjUtrustning = new javax.swing.JTextField();
        rdiobtnVapen = new javax.swing.JRadioButton();
        rdiobtnTeknik = new javax.swing.JRadioButton();
        rdiobtnKom = new javax.swing.JRadioButton();
        lblRubrik = new javax.swing.JLabel();
        lblNamn = new javax.swing.JLabel();
        txtTyp = new javax.swing.JLabel();
        txtEgenskap = new javax.swing.JLabel();
        txtValjEgenskap = new javax.swing.JTextField();
        lblRubrik2 = new javax.swing.JLabel();
        btnTillbaka = new javax.swing.JButton();

        jLabel2.setText("L�gg till utrustning i din samling");

        lblRubrik1.setText("Ange namn, typ av utrustning och egenskap f�r utrustningen");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(480, 360));
        setResizable(false);

        btnRegistrera.setText("Registrera");
        btnRegistrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistreraActionPerformed(evt);
            }
        });

        rdiobtnVapen.setText("Vapen");
        rdiobtnVapen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdiobtnVapenActionPerformed(evt);
            }
        });

        rdiobtnTeknik.setText("Teknik");
        rdiobtnTeknik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdiobtnTeknikActionPerformed(evt);
            }
        });

        rdiobtnKom.setText("Kommunikation");
        rdiobtnKom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdiobtnKomActionPerformed(evt);
            }
        });

        lblRubrik.setText("Ange namn, typ och egenskap f�r den utrustning du vill l�gga till");

        lblNamn.setText("Namn: ");

        txtTyp.setText("Typ: ");

        txtEgenskap.setText("Egenskap: ");

        lblRubrik2.setText("Utrustningen registreras sedan i din samling");

        btnTillbaka.setText("Tillbaka");
        btnTillbaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblRubrik)
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtTyp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdiobtnVapen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdiobtnTeknik)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdiobtnKom))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(lblNamn)
                                .addGap(18, 18, 18)
                                .addComponent(txtValjUtrustning, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(txtEgenskap)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnRegistrera, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtValjEgenskap, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(89, 89, 89))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(lblRubrik2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(btnTillbaka)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRubrik, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRubrik2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValjUtrustning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNamn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdiobtnVapen)
                    .addComponent(rdiobtnTeknik)
                    .addComponent(rdiobtnKom)
                    .addComponent(txtTyp))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValjEgenskap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEgenskap))
                .addGap(18, 18, 18)
                .addComponent(btnRegistrera)
                .addGap(18, 18, 18)
                .addComponent(btnTillbaka)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //L�gg till det angivna namnet p� utrustning i tabellerna "Utrustning" och "Innehar_utrstning"-tabellerna
    //Varje utrustning registreras i antingen vapen, teknik eller kommunikation-tabellen
    //Dagens datum l�ggs till f�r varje nyregistrerad utrustning
    private void btnRegistreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistreraActionPerformed
        String utrustningNamn = txtValjUtrustning.getText();
        String egenskapVarde = txtValjEgenskap.getText();
        String insertIntoUtrustning = "";
        String insertIntoInneharUtrustning = "";
        String nyttUtrustningsId = "";
        String insertIntoVapen = "";
        String insertIntoTeknik = "";
        String insertIntoKom = "";
        
        //Validering f�r att se att b�da textf�lten �r ifyllda
        if((Validering.finnsText(txtValjUtrustning)) && (Validering.finnsText(txtValjEgenskap)))
        {
        try
            {
                //L�gga till i utrustning- och innehar_utrustning-tabellen
                nyttUtrustningsId = mib.getAutoIncrement("utrustning", "utrustnings_id");
                insertIntoUtrustning = "insert into utrustning values " + "(" + nyttUtrustningsId + ", " + "'" + utrustningNamn + "');";
                insertIntoInneharUtrustning = "insert into innehar_utrustning values " + "(" + Login.getAgentID() +
                                               ", " + nyttUtrustningsId + ", current_timestamp)";
                mib.insert(insertIntoUtrustning);
                mib.insert(insertIntoInneharUtrustning);
                
                //Sedan l�gga till i vapen, teknik, eller kommunikation-tabellen
                if(rdiobtnVapen.isSelected())
                {
                    insertIntoVapen = "insert into vapen values " + "(" + nyttUtrustningsId +
                                       ", " + egenskapVarde + ")";
                    mib.insert(insertIntoVapen);
                }
                else if(rdiobtnTeknik.isSelected())
                {
                    insertIntoTeknik = "insert into teknik values " + "(" + nyttUtrustningsId +
                                       ", '" + egenskapVarde + "')";
                    mib.insert(insertIntoTeknik);
                }
                else if(rdiobtnKom.isSelected())
                {
                    insertIntoKom = "insert into kommunikation values " + "(" + nyttUtrustningsId +
                                       ", '" + egenskapVarde + "')";
                    mib.insert(insertIntoKom);
                }
                
                JOptionPane.showMessageDialog(null, "Ny utrustning " + utrustningNamn + " har registrerats!");
            }
        
        catch (InfException ettUndantag) {
                    JOptionPane.showMessageDialog(null, "Databasfel!");
                    System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                }   catch (Exception ettUndantag) {
                    JOptionPane.showMessageDialog(null, "N�got gick fel!");
                    System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                }
        }
    }//GEN-LAST:event_btnRegistreraActionPerformed

    //Beroende p� vilken radiobutton som �r itryckt kommer texten framf�r txtValjEgenskap �ndras
    private void rdiobtnVapenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdiobtnVapenActionPerformed
        setEgenskap();
    }//GEN-LAST:event_rdiobtnVapenActionPerformed

    private void rdiobtnTeknikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdiobtnTeknikActionPerformed
        setEgenskap();
    }//GEN-LAST:event_rdiobtnTeknikActionPerformed

    private void rdiobtnKomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdiobtnKomActionPerformed
        setEgenskap();
    }//GEN-LAST:event_rdiobtnKomActionPerformed

    private void btnTillbakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaActionPerformed
        dispose();
        new HanteraUtrustning(mib).setVisible(true);
        //�terv�nder till f�nstret HanteraUtrustning
    }//GEN-LAST:event_btnTillbakaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrera;
    private javax.swing.JButton btnTillbaka;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblNamn;
    private javax.swing.JLabel lblRubrik;
    private javax.swing.JLabel lblRubrik1;
    private javax.swing.JLabel lblRubrik2;
    private javax.swing.JRadioButton rdiobtnKom;
    private javax.swing.JRadioButton rdiobtnTeknik;
    private javax.swing.JRadioButton rdiobtnVapen;
    private javax.swing.JLabel txtEgenskap;
    private javax.swing.JLabel txtTyp;
    private javax.swing.JTextField txtValjEgenskap;
    private javax.swing.JTextField txtValjUtrustning;
    // End of variables declaration//GEN-END:variables
}
