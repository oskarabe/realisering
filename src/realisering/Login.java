package realisering;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import oru.inf.*;

/**
 * Denna klass utgör loginfönstret för programmet. Användaren anger sitt
 * lösenord och beroende på ifall användaren är en agent, administratör eller
 * alien kommer en huvudmeny att öppnas upp
 */
public class Login extends javax.swing.JFrame {

                private InfDB mib;
                //ID:t för den alien eller agent som är inloggad.
                //Används för att kunna hitta rätt info med SQL-frågor i andra klasser
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

                // Det som händer när man klickar enter/knappen
                private void loggaIn() {
                                Boolean koll = false;
                                String hittaAlien = "SELECT LOSENORD FROM ALIEN;";
                                String hittaAgent = "SELECT LOSENORD FROM AGENT WHERE ADMINISTRATOR LIKE 'N';";
                                String hittaAdmin = "SELECT LOSENORD FROM AGENT WHERE ADMINISTRATOR LIKE 'J';";

                                ArrayList<String> allaLosenAgent;
                                ArrayList<String> allaLosenAlien;
                                ArrayList<String> allaLosenAdmin;

                                //Validering för att text ska finnas
                                if (Validering.finnsText(losenord))
                                try {

                                                // Sparar lösenord i Arraylist.
                                                allaLosenAgent = mib.fetchColumn(hittaAgent);
                                                allaLosenAlien = mib.fetchColumn(hittaAlien);
                                                allaLosenAdmin = mib.fetchColumn(hittaAdmin);

                                                //Tre st for-loopar används, som kollar igenom admin, agenter respektive aliens lösenord
                                                //Ifall lösenordet stämmer överens sparas info om användaren (isAdmin och ID-nummer)
                                                //Denna info används i andra klasser
                                                //Sist öppnas huvudmenyn för användaren

                                                for (String l : allaLosenAdmin) {
                                                                String adminLosenord = new String(losenord.getPassword());
                                                                if (adminLosenord.equals(l)) {
                                                                                isAdmin = true;
                                                                                koll = true;
                                                                                String hittaAdminID = ("SELECT AGENT_ID FROM AGENT WHERE "
                                                                                        + "LOSENORD LIKE '" + adminLosenord + "'");
                                                                                agentID = mib.fetchSingle(hittaAdminID);
                                                                                System.out.println("Admin");
                                                                                System.out.println("agentID: " + agentID);
                                                                                new HuvudmenyAdmin(mib).setVisible(true);
                                                                                dispose();
                                                                }
                                                }

                                                for (String l : allaLosenAgent) {
                                                                String agentLosenord = new String(losenord.getPassword());
                                                                if (l.equals(agentLosenord)) {
                                                                                isAdmin = false;
                                                                                koll = true;
                                                                                System.out.println("Agent");
                                                                                String hittaAgentID = ("SELECT AGENT_ID FROM AGENT WHERE "
                                                                                        + "LOSENORD LIKE '" + agentLosenord + "'");
                                                                                agentID = mib.fetchSingle(hittaAgentID);
                                                                                System.out.println("AgentID: " + agentID);
                                                                                new HuvudmenyAgent(mib).setVisible(true);
                                                                                dispose();
                                                                }
                                                }

                                                for (String l : allaLosenAlien) {
                                                                String alienLosenord = new String(losenord.getPassword());
                                                                if (l.equals(alienLosenord)) {
                                                                                System.out.println("Alien");
                                                                                koll = true;
                                                                                String hittaAlienID = ("SELECT ALIEN_ID FROM ALIEN WHERE LOSENORD LIKE " + "'" + alienLosenord + "'");
                                                                                alienID = mib.fetchSingle(hittaAlienID);
                                                                                System.out.println("AlienID: " + alienID);
                                                                                new HuvudmenyAlien(mib).setVisible(true);
                                                                                dispose();
                                                                }
                                                }

                                                // Om lösenordet inte fanns - Meddelande om fel lösenord.
                                                if (!koll) {
                                                                JOptionPane.showMessageDialog(null, "Fel lösenord!");
                                                }

                                } catch (InfException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Databasfel!");
                                                System.out.println("D Fel --  " + ettUndantag.getMessage() + " -- " + ettUndantag.getLocalizedMessage() + " -- " + ettUndantag.getCause().toString());
                                } catch (HeadlessException ettUndantag) {
                                                JOptionPane.showMessageDialog(null, "Något gick fel!");
                                                System.out.println("Fel --  " + ettUndantag.getMessage() + " -- " + ettUndantag.getLocalizedMessage() + " -- " + ettUndantag.getCause().toString());
                                }

                }

                //get-metoder för att returnera ID av den agent eller alien som är inloggad
                //Kommer användas i andra klasser
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
                private void initComponents() {//GEN-BEGIN:initComponents

                                jLabel1 = new javax.swing.JLabel();
                                losenord = new javax.swing.JPasswordField();
                                knapp = new javax.swing.JButton();
                                AndraLosen = new javax.swing.JButton();

                                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
                                losenord.setToolTipText("Ange lösenord");
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

                                AndraLosen.setBackground(new java.awt.Color(171, 190, 196));
                                AndraLosen.setForeground(new java.awt.Color(51, 51, 51));
                                AndraLosen.setText("Ändra lösenord");
                                AndraLosen.addActionListener(new java.awt.event.ActionListener() {
                                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                                AndraLosenActionPerformed(evt);
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
                                                                                                                .addComponent(losenord)
                                                                                                                .addComponent(AndraLosen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                                                                .addGap(18, 18, 18)
                                                                .addComponent(AndraLosen)
                                                                .addContainerGap(35, Short.MAX_VALUE))
                                );

                                pack();
                }//GEN-END:initComponents

                // Logga in genom att klicka på knappen
    private void knappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_knappActionPerformed
                                loggaIn();
    }//GEN-LAST:event_knappActionPerformed
    private void losenordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_losenordKeyPressed
                                if (evt.getKeyCode() == 10) {
                                                loggaIn(); // Logga in genom att trycka enter
                                }
    }//GEN-LAST:event_losenordKeyPressed

    private void losenordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_losenordActionPerformed

    }//GEN-LAST:event_losenordActionPerformed

    private void AndraLosenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AndraLosenActionPerformed
                                new AndraLosenord(mib).setVisible(true);
                                dispose();
    }//GEN-LAST:event_AndraLosenActionPerformed

                // Variables declaration - do not modify//GEN-BEGIN:variables
                private javax.swing.JButton AndraLosen;
                private javax.swing.JLabel jLabel1;
                private javax.swing.JButton knapp;
                private javax.swing.JPasswordField losenord;
                // End of variables declaration//GEN-END:variables
}
