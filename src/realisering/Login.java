package realisering;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import oru.inf.*;

/**
 *
 * @author oskar
 * Denna klass utg�r loginf�nstret f�r programmet. Anv�ndaren anger sitt
 * l�senord och beroende p� ifall anv�ndaren �r en agent, administrat�r eller
 * alien kommer olika f�nster �ppnas upp
 */
public class Login extends javax.swing.JFrame {

                private InfDB mib;
                //ID:t f�r den alien eller agent som �r inloggad.
                //Anv�nds f�r att kunna hitta r�tt info med SQL-fr�gor i andra klasser
                private static String alienID;
                private static String agentID;
                private static boolean isAdmin;

                /**
                 * Konstruktor
                 */
                public Login(InfDB mib) {
                                this.mib = mib;
                                initComponents();
                }

                // Det som h�nder n�r man klickar enter/knappen
                private void loggaIn() {
                                Boolean koll = false;
                                String hittaAlien = "SELECT LOSENORD FROM ALIEN;";
                                String hittaAgent = "SELECT LOSENORD FROM AGENT;";
                                String hittaAdmin = "SELECT LOSENORD FROM AGENT WHERE ADMINISTRATOR LIKE 'J';";

                                ArrayList<String> allaLosenAgent;
                                ArrayList<String> allaLosenAlien;
                                ArrayList<String> allaLosenAdmin;

                                try {

                                                // Sparar l�senord i Arraylist.
                                                allaLosenAgent = mib.fetchColumn(hittaAgent);
                                                allaLosenAlien = mib.fetchColumn(hittaAlien);
                                                allaLosenAdmin = mib.fetchColumn(hittaAdmin);

                                                // Kollar igenom alla Agenters l�senord
                                                for (String l : allaLosenAdmin) {
                                                                String adminLosenord = new String(losenord.getPassword());

                                                                if (adminLosenord.equals(l)) {
                                                                                isAdmin = true;
                                                                                System.out.println("Admin");
                                                                                //�ppnar upp huvudmenyn f�r admin
                                                                                new HuvudmenyAdmin(mib).setVisible(true);
                                                                                this.dispose();
                                                                } else {
                                                                                isAdmin = false;
                                                                }
                                                }

                                                // Kollar igenom alla Agenters l�senord
                                                for (String l : allaLosenAgent) {
                                                                String agentLosenord = new String(losenord.getPassword());
                                                                if (l.equals(agentLosenord)) {
                                                                                System.out.println("Agent");
                                                                                koll = true;
                                                                                //Registrera Agent-ID f�r att kunna anv�ndas i andra klasser
                                                                                String hittaAgentID = ("SELECT AGENT_ID FROM AGENT WHERE "
                                                                                        + "LOSENORD LIKE '" + agentLosenord + "' ");
                                                                                agentID = mib.fetchSingle(hittaAgentID);
                                                                                System.out.println("AgentID: " + agentID);
                                                                                //�ppna huvudmenyn f�r agenter
                                                                                if(isAdmin == false)
                                                                                {
                                                                                new HuvudmenyAgent(mib).setVisible(true);
                                                                                this.dispose();
                                                                                }
                                                                }
                                                }

                                                // Kollar igenom alla Aliens l�senord.
                                                for (String l : allaLosenAlien) {
                                                                String alienLosenord = new String(losenord.getPassword());
                                                                if (l.equals(alienLosenord)) {
                                                                                System.out.println("Alien");
                                                                                koll = true;
                                                                                //Registrera Alien-ID f�r att kunna anv�ndas i andra klasser
                                                                                String hittaAlienID = ("SELECT ALIEN_ID FROM ALIEN WHERE LOSENORD LIKE " + "'" + alienLosenord + "'");
                                                                                alienID = mib.fetchSingle(hittaAlienID);
                                                                                System.out.println("AlienID: " + alienID);
                                                                                // �ppna f�nster HuvudmenyAlien
                                                                                new HuvudmenyAlien(mib).setVisible(true);
                                                                                dispose();
                                                                }
                                                }

                                                // Om l�senordet inte fanns - Meddelande om fel l�senord.
                                                if (!koll) {
                                                                JOptionPane.showMessageDialog(null, "Fel l�senord!");
                                                }

                                } catch (InfException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Databasfel!");
                                                System.out.println("D Fel --  " + ettUndantag.getMessage() + " -- " + ettUndantag.getLocalizedMessage() + " -- " + ettUndantag.getCause().toString());
                                } catch (HeadlessException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "N�got gick fel!");
                                                System.out.println("Fel --  " + ettUndantag.getMessage() + " -- " + ettUndantag.getLocalizedMessage() + " -- " + ettUndantag.getCause().toString());
                                }

                }

                //get-metoder f�r att returnera ID av den agent eller alien som �r inloggad
                //Kommer anv�ndas i andra klasser
                public static String getAlienID() {
                                return alienID;
                }

                public static String getAgentID() {
                                return agentID;
                }

                public static Boolean getAdmin() {
                                return isAdmin;
                }

                @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        losenord = new javax.swing.JPasswordField();
        knapp = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(47, 59, 94));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Gill Sans MT Condensed", 1, 14)); // NOI18N
        setMinimumSize(new java.awt.Dimension(400, 350));
        setResizable(false);
        setSize(new java.awt.Dimension(400, 350));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/realisering/Men_In_Black_logo.png"))); // NOI18N

        losenord.setColumns(12);
        losenord.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        losenord.setToolTipText("Ange l�senord");
        losenord.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        losenord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                losenordActionPerformed(evt);
            }
        });
        losenord.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                losenordKeyPressed(evt);
            }
        });

        knapp.setFont(new java.awt.Font("Open Sans SemiBold", 1, 16)); // NOI18N
        knapp.setText("LOGGA IN");
        knapp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                knappActionPerformed(evt);
            }
        });

        jLabel2.setText("�ndra l�senord");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(knapp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(losenord)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jLabel2)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(losenord, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(knapp, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

                // Logga in genom att klicka p� knappen
    private void knappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_knappActionPerformed
                                loggaIn();
    }//GEN-LAST:event_knappActionPerformed
    private void losenordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_losenordKeyPressed
                                if (evt.getKeyCode() == 10) {
                                                loggaIn(); // Logga in genom att trycka enter
                                }
    }//GEN-LAST:event_losenordKeyPressed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
                                dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void losenordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_losenordActionPerformed

    }//GEN-LAST:event_losenordActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton knapp;
    private javax.swing.JPasswordField losenord;
    // End of variables declaration//GEN-END:variables
}
