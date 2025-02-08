/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package matchingpairs;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class Board extends javax.swing.JFrame {

    /**
     * Creates new form Board
     */
    public Board() {
        initComponents();
        JButton[] jButtons = {jButton3, jButton4, jButton5, jButton6, jButton7, jButton8, jButton9, jButton10};
        Card[] cards = Arrays.copyOf(jButtons, jButtons.length, Card[].class);
        ((Controller) jLabel1).setCards(cards);
        
        for (Card card : cards) {
            card.setController((Controller) jLabel1);
        }
        
        ((Counter) jLabel2).setController((Controller) jLabel1);
        FinishedListener finishedListener = new FinishedListener();
        ((Controller) jLabel1).addPropertyChangeListener(finishedListener);
        shuffle();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new Controller(this);
        jLabel2 = new Counter(this);
        jButton3 = new Card(0, this);
        jButton4 = new Card(1, this);
        jButton5 = new Card(2, this);
        jButton6 = new Card(3, this);
        jButton7 = new Card(4, this);
        jButton8 = new Card(5, this);
        jButton9 = new Card(6, this);
        jButton10 = new Card(7, this);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Matching Pairs");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(800, 630));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 630));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(0, 102, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Shuffle");
        jButton1.setPreferredSize(new java.awt.Dimension(100, 40));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, -1, -1));

        jButton2.setBackground(new java.awt.Color(200, 35, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Exit");
        jButton2.setPreferredSize(new java.awt.Dimension(100, 40));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pairs: 0");
        jLabel1.setPreferredSize(new java.awt.Dimension(140, 20));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 490, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Moves: 0");
        jLabel2.setPreferredSize(new java.awt.Dimension(140, 20));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 490, -1, -1));

        jButton3.setBackground(new java.awt.Color(40, 167, 67));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jButton3.setInheritsPopupMenu(true);
        jButton3.setOpaque(true);
        jButton3.setPreferredSize(new java.awt.Dimension(100, 100));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, -1, -1));

        jButton4.setBackground(new java.awt.Color(40, 167, 67));
        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jButton4.setInheritsPopupMenu(true);
        jButton4.setOpaque(true);
        jButton4.setPreferredSize(new java.awt.Dimension(100, 100));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, -1, -1));

        jButton5.setBackground(new java.awt.Color(40, 167, 67));
        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jButton5.setInheritsPopupMenu(true);
        jButton5.setOpaque(true);
        jButton5.setPreferredSize(new java.awt.Dimension(100, 100));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, -1, -1));

        jButton6.setBackground(new java.awt.Color(40, 167, 67));
        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jButton6.setInheritsPopupMenu(true);
        jButton6.setOpaque(true);
        jButton6.setPreferredSize(new java.awt.Dimension(100, 100));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 180, -1, -1));

        jButton7.setBackground(new java.awt.Color(40, 167, 67));
        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jButton7.setInheritsPopupMenu(true);
        jButton7.setOpaque(true);
        jButton7.setPreferredSize(new java.awt.Dimension(100, 100));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, -1, -1));

        jButton8.setBackground(new java.awt.Color(40, 167, 67));
        jButton8.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jButton8.setInheritsPopupMenu(true);
        jButton8.setOpaque(true);
        jButton8.setPreferredSize(new java.awt.Dimension(100, 100));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, -1, -1));

        jButton9.setBackground(new java.awt.Color(40, 167, 67));
        jButton9.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jButton9.setInheritsPopupMenu(true);
        jButton9.setOpaque(true);
        jButton9.setPreferredSize(new java.awt.Dimension(100, 100));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, -1, -1));

        jButton10.setBackground(new java.awt.Color(40, 167, 67));
        jButton10.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jButton10.setInheritsPopupMenu(true);
        jButton10.setOpaque(true);
        jButton10.setPreferredSize(new java.awt.Dimension(100, 100));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        shuffle();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ((Card) jButton4).onClick();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ((Card) jButton3).onClick();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        ((Card) jButton5).onClick();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        ((Card) jButton6).onClick();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        ((Card) jButton7).onClick();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        ((Card) jButton8).onClick();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        ((Card) jButton9).onClick();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        ((Card) jButton10).onClick();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int option = JOptionPane.showConfirmDialog(
            null,
            "Do you want to close the application?",
            "Exit",
            JOptionPane.YES_NO_OPTION
        );
        
        if (option == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
    private ArrayList<Integer> shuffle;
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    
    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }
    
    public class FinishedListener implements PropertyChangeListener, Serializable {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("finished")) {
                int option = JOptionPane.showConfirmDialog(
                    null,
                    "You finished in " + ((Counter) jLabel2).getMoves() + " moves.\n" + "Do you want to continue playing?",
                    "Finished",
                    JOptionPane.YES_NO_OPTION
                );
        
                if (option == 1) {
                    System.exit(0);
                }
                
                shuffle();
            }
        }
    }

    private void shuffle() {
        ArrayList<Integer> newShuffle = new ArrayList<Integer>();
        Random random = new Random();
        
        for (int i = 0; i < 4; i++) {
            int rand;
            
            do {
                rand = random.nextInt(10);
            } while(newShuffle.contains(rand));
            
            newShuffle.add(rand);
            newShuffle.add(rand);
        }

        Collections.shuffle(newShuffle);
        setShuffle(newShuffle);        
    }
    
    private void setShuffle(ArrayList<Integer> newShuffle) {
        ArrayList<Integer> oldShuffle = shuffle;
        shuffle = newShuffle;
        changes.firePropertyChange("shuffle", oldShuffle, newShuffle);
    }
}
