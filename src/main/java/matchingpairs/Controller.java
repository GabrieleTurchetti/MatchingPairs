/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matchingpairs;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author gabri
 */
public class Controller extends JLabel {
    private Card[] cards;
    private int v1;
    private int v2;
    private int matchCount;
    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);
    
    public Controller() {
        this.v1 = -1;
        this.v2 = -1;
        this.matchCount = 0;
    }
    
    public void setCards(Card[] cards) {
        this.cards = cards;
        CardStatesListener cardStatesListener = new CardStatesListener();
        
        for (Card card : cards) {
            card.addVetoableChangelistener(cardStatesListener);
        }
    }
        
    
    public class CardStatesListener implements VetoableChangeListener, Serializable {
        @Override
        public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
            if (evt.getPropertyName().equals("state")) {
                switch ((State) evt.getOldValue()) {
                    case FACE_UP:
                        throw new PropertyVetoException("The card is already uncovered.", evt);
                        
                    case FACE_DOWN:
                        if (v1 == -1) {
                            v1 = ((Card) evt.getSource()).getValue();
                            break;
                        }
                        
                        if (v2 == -1) {
                            System.out.println("here");
                            v2 = ((Card) evt.getSource()).getValue();                           
                            onMatch();
                        }
                        
                    case EXCLUDED:
                        throw new PropertyVetoException("The card is exluded.", evt);
                }
            }
        }
    }
    
    private void onMatch() {
        if (v1 == v2) {
            System.out.println("here");
            this.setText("Pairs: " + ++matchCount);
            
            new Timer(500, e -> {
                changes.firePropertyChange("matched", false, true);
            }).start();
        }
        else {
            new Timer(500, e -> {
                changes.firePropertyChange("matched", false, false);
            }).start();
        }
    }
}
