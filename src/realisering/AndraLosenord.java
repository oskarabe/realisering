package realisering;

import oru.inf.InfDB;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import oru.inf.InfException;

/**
 * Klass för att ändra lösenord för agenter, admin och aliens
 * Användaren anger det gamla och det nya lösenordet för att kunna ändra det
 */
public class AndraLosenord extends javax.swing.JFrame {

    private InfDB mib;
    private ArrayList<String> allaLosenordAgent;
    private ArrayList<String> allaLosenordAlien;
    
    //Konstruktor för AndraLosenord
    public AndraLosenord(InfDB mib) {
        this.mib = mib;
        allaLosenordAgent = new ArrayList<>();
        allaLosenordAlien = new ArrayList<>();
        initComponents();
        fyllArrayList();
    }
    
    //Metod som fyller ArrayListorna med alla agenter och aliens lösenord
    public void fyllArrayList()
    {
        String hittaAlienLosenord = "SELECT LOSENORD FROM ALIEN;";
        String hittaAgentLosenord = "SELECT LOSENORD FROM AGENT;";
        
                try {
                    allaLosenordAgent = mib.fetchColumn(hittaAgentLosenord);
                    allaLosenordAlien = mib.fetchColumn(hittaAlienLosenord);
                }
        
                catch (InfException ettUndantag) {
                    JOptionPane.showMessageDialog(null, "Databasfel!");
                    System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                }   
                catch (Exception ettUndantag) {
                    JOptionPane.showMessageDialog(null, "Något gick fel!");
                    System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblrubrik = new javax.swing.JLabel();
        lblGammaltLosenord = new javax.swing.JLabel();
        lblNyttLosenord = new javax.swing.JLabel();
        btnAndra = new javax.swing.JButton();
        btnTillbaka = new javax.swing.JButton();
        gammaltLosenord = new javax.swing.JPasswordField();
        nyttLosenord = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lblrubrik.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblrubrik.setText("Ändra Lösenord");

        lblGammaltLosenord.setText("Gammalt lösenord:");

        lblNyttLosenord.setText("Nytt lösenord:");

        btnAndra.setText("Ändra");
        btnAndra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAndraActionPerformed(evt);
            }
        });

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
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGammaltLosenord)
                    .addComponent(lblNyttLosenord))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nyttLosenord)
                    .addComponent(gammaltLosenord, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(119, 119, 119))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(lblrubrik))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(btnTillbaka, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAndra, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblrubrik, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblGammaltLosenord)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNyttLosenord)
                            .addComponent(nyttLosenord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(gammaltLosenord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAndra)
                    .addComponent(btnTillbaka))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //När knappen ändra trycks kommer det gamla lösenordet att bytas ut med det nya lösenordet
    private void btnAndraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAndraActionPerformed
        String nyttLosen = new String(nyttLosenord.getPassword());
	String gammaltLosen = new String(gammaltLosenord.getPassword());
        String uppdateraLosenAgent= "UPDATE agent SET losenord = " + "'" + nyttLosen + "'" + " WHERE losenord = " + "'" + gammaltLosen + "'";
        String uppdateraLosenAlien = "UPDATE alien SET losenord = " + "'" + nyttLosen + "'" + " WHERE losenord = " + "'" + gammaltLosen + "'"; 
        
        //Variabler för att avgöra ifall det gamla och nya lösenordet hittats,
        //samt ifall lösenordet tillhör en agent eller alien
        boolean hittatGmltLosen = false;
        boolean hittatNyttLosen = false;
        boolean isAgentLosen = false;

        //Börjar med att kolla så att båda textfälten är ifyllda
        if(Validering.finnsText(nyttLosenord) && Validering.finnsText(gammaltLosenord))
        {
            //Loop för att kolla genom alla agenternas lösenord
            for(String losen : allaLosenordAgent)
                {
                    if(losen.equals(nyttLosen))
                    {
                                hittatNyttLosen = true;
                    }
                                
                    if(losen.equals(gammaltLosen))
                    {
                        hittatGmltLosen = true;
                        isAgentLosen = true;
                    }
                }

            //Loop för att kolla igenom alla aliens lösenord
            for(String losen : allaLosenordAlien)
                {
                    if(losen.equals(nyttLosen))
                    {
                                hittatNyttLosen = true;
                    }
                                
                    if(losen.equals(gammaltLosen))
                    {
                        hittatGmltLosen = true;
                    }
                }
                    
            //Ändrar en agents lösenord, ifall det nya lösenordet är unikt och
            //det gamla lösenordet fanns
            if((hittatNyttLosen == false) && (hittatGmltLosen == true) && (isAgentLosen == true))
            {
                try {
                            mib.update(uppdateraLosenAgent);
                            JOptionPane.showMessageDialog(null, "Lösenordet har ändrats!");
                            } 
                            catch (InfException ettUndantag) {
                            JOptionPane.showMessageDialog(null, "Databasfel!");
                            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                            } 
                            catch (Exception ettUndantag) {
                            JOptionPane.showMessageDialog(null, "Något gick fel!");
                            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                            }
            }
            
            //Ändrar en aliens lösenord, ifall det nya lösenordet är unikt och
            //det gamla lösenordet fanns
            if((hittatNyttLosen == false) && (hittatGmltLosen == true) && (isAgentLosen == false))
            {
                try {
                            mib.update(uppdateraLosenAlien);
                            JOptionPane.showMessageDialog(null, "Lösenordet har ändrats!");
                            } 
                            catch (InfException ettUndantag) {
                            JOptionPane.showMessageDialog(null, "Databasfel!");
                            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                            } 
                            catch (Exception ettUndantag) {
                            JOptionPane.showMessageDialog(null, "Något gick fel!");
                            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
                            }
                            //Refreshar fönstret för att undvika errors
                            dispose();
                            new AndraLosenord(mib).setVisible(true);
            }
        
            if(hittatGmltLosen == false)
            {
                JOptionPane.showMessageDialog(null, "Det gamla lösenordet finns inte");
            }
        
            if(hittatNyttLosen == true)
            {
                JOptionPane.showMessageDialog(null, "Det nya lösenordet finns redan");
            }
        }
    }//GEN-LAST:event_btnAndraActionPerformed

    private void btnTillbakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaActionPerformed
        new Login(mib).setVisible(true);
        dispose();
    }//GEN-LAST:event_btnTillbakaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAndra;
    private javax.swing.JButton btnTillbaka;
    private javax.swing.JPasswordField gammaltLosenord;
    private javax.swing.JLabel lblGammaltLosenord;
    private javax.swing.JLabel lblNyttLosenord;
    private javax.swing.JLabel lblrubrik;
    private javax.swing.JPasswordField nyttLosenord;
    // End of variables declaration//GEN-END:variables
}
