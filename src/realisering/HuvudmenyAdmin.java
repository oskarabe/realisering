package realisering;

import javax.swing.JOptionPane;
import oru.inf.InfException;
import oru.inf.InfDB;

/**
 *
 * @author lovee
 * Klassen förser användaren, i det här fallet en alien, med ett gränssnitt
 * där de kan se information om sitt område och sin områdeschef
 */
public class HuvudmenyAlien extends javax.swing.JFrame {

    private String alienID;
    private InfDB mib;
    
    /**
     * Creates new form HuvudmenyAlien
     */
    public HuvudmenyAlien(InfDB mib) {
        initComponents();
        this.mib = mib;
        alienID = Login.getAlienID();
        setLabelInloggNamn();
        setLabelAlienOmrade();
        setLabelOmradeschef();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        btnTillbakaInlogg = new javax.swing.JButton();
        btnListaAliens = new javax.swing.JButton();
        btnMailaAgent = new javax.swing.JButton();
        lblOmradeTillhorighet = new javax.swing.JLabel();
        lblHuvudmenyAlien = new javax.swing.JLabel();
        lblinloggNamn = new javax.swing.JLabel();
        lblAlienOmrade = new javax.swing.JLabel();
        lblOmradeschef = new javax.swing.JLabel();
        btnTillbakaInlogg1 = new javax.swing.JButton();

        btnTillbakaInlogg.setText("Tillbaka till inlogg");
        btnTillbakaInlogg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaInloggActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnListaAliens.setText("Lista aliens");
        btnListaAliens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaAliensActionPerformed(evt);
            }
        });

        btnMailaAgent.setText("Maila agent");
        btnMailaAgent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMailaAgentActionPerformed(evt);
            }
        });

        lblHuvudmenyAlien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblHuvudmenyAlien.setText("Huvudmeny - Alien");

        lblinloggNamn.setText("Du är inloggad som: ");

        lblAlienOmrade.setText("Du tillhör område: ");

        lblOmradeschef.setText("Din områdeschef är:");

        btnTillbakaInlogg1.setText("Tillbaka till inlogg");
        btnTillbakaInlogg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaInlogg1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOmradeschef, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                        .addComponent(lblHuvudmenyAlien))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAlienOmrade, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblOmradeTillhorighet))
                            .addComponent(lblinloggNamn, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnMailaAgent, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnListaAliens, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTillbakaInlogg1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblHuvudmenyAlien, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(lblinloggNamn)
                .addGap(18, 18, 18)
                .addComponent(lblAlienOmrade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblOmradeTillhorighet)
                .addGap(15, 15, 15)
                .addComponent(lblOmradeschef)
                .addGap(156, 156, 156))
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(btnTillbakaInlogg1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnListaAliens)
                .addGap(11, 11, 11)
                .addComponent(btnMailaAgent)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    //Anger texten i label lblinloggNamn
    private void setLabelInloggNamn()
    {
        String hittaNamn = ("select namn from alien where alien_id = " + alienID);
        
        try {
        lblinloggNamn.setText("Du är inloggad som: " + mib.fetchSingle(hittaNamn));
            }
        
        catch (InfException ettUndantag) {
            JOptionPane.showMessageDialog(null, "Databasfel!");
            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
        } catch (Exception ettUndantag) {
            JOptionPane.showMessageDialog(null, "Något gick fel!");
            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
        }
    }
    
    //Anger texten i label lblAlienOmrade
    private void setLabelAlienOmrade()
    {
        String hittaOmrade = ("select omrade.benamning from alien, plats, omrade where " +
                                "plats = plats_id and finns_i = omrades_id and " +
                                "alien_id = " + alienID);
        
        try {
        lblAlienOmrade.setText("Du tillhör område: " + mib.fetchSingle(hittaOmrade));
            }
        
        catch (InfException ettUndantag) {
            JOptionPane.showMessageDialog(null, "Databasfel!");
            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
        } catch (Exception ettUndantag) {
            JOptionPane.showMessageDialog(null, "Något gick fel!");
            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
        }
    }
    
   private void setLabelOmradeschef()
    {
        String hittaOmradeschefNamn = ("select agent.namn from agent, omradeschef " +
                                   "where agent.agent_id = omradeschef.agent_id " +
                                    "and omradeschef.omrade = ");
        
        String hittaOmradesId = ("(select omrades_id from alien, plats, omrade " +
                "where plats = plats_id and finns_i = omrades_id and alien_id = " +
                alienID + ")");
        
        String hittaOmradeschefTelefon = ("select agent.telefon from agent, omradeschef " +
                                   "where agent.agent_id = omradeschef.agent_id " +
                                    "and omradeschef.omrade = ");
        
        try {
        lblOmradeschef.setText("Din områdeschef är: " + mib.fetchSingle(hittaOmradeschefNamn + hittaOmradesId)
        + " (telefon: " + mib.fetchSingle(hittaOmradeschefTelefon + hittaOmradesId) + ")");
            }
        
        catch (InfException ettUndantag) {
            JOptionPane.showMessageDialog(null, "Databasfel!");
            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
        } catch (Exception ettUndantag) {
            JOptionPane.showMessageDialog(null, "Något gick fel!");
            System.out.println("Internt felmeddelande" + ettUndantag.getMessage());
        }
    }
    
    private void btnListaAliensActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void btnMailaAgentActionPerformed(java.awt.event.ActionEvent evt) {                                              
        System.out.println(Login.getAlienID());
    }                                             

    private void btnTillbakaInloggActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        dispose();
        new Login(mib).setVisible(true);
    }                                                 

    private void btnTillbakaInlogg1ActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        dispose();
        new Login(mib).setVisible(true);
    }                                                  


    // Variables declaration - do not modify                     
    private javax.swing.JButton btnListaAliens;
    private javax.swing.JButton btnMailaAgent;
    private javax.swing.JButton btnTillbakaInlogg;
    private javax.swing.JButton btnTillbakaInlogg1;
    private javax.swing.JLabel lblAlienOmrade;
    private javax.swing.JLabel lblHuvudmenyAlien;
    private javax.swing.JLabel lblOmradeTillhorighet;
    private javax.swing.JLabel lblOmradeschef;
    private javax.swing.JLabel lblinloggNamn;
    // End of variables declaration                   
}
