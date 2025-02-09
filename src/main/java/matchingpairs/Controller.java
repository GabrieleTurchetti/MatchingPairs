/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matchingpairs;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author gabri
 */
public class Controller extends JLabel {
    private Card[] cards;
    private int v1;
    private int v2;
    private int pairs;
    private JFrame board;
    ScheduledExecutorService scheduler;
    private ArrayList<Boolean> cardsClicked; // Maintain which cards were clicked
    
    public Controller(JFrame board) {
        this.v1 = -1;
        this.v2 = -1;
        this.pairs = 0;
        this.board = board;
        ShuffleListener cardValuesListener = new ShuffleListener();
        this.board.addPropertyChangeListener(cardValuesListener);
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.cardsClicked = new ArrayList<Boolean>();
    }
    
    // Listener class for the cards click event
    public class ClickedListener implements PropertyChangeListener, Serializable {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("clicked")) {
                int index = ((Card) evt.getSource()).getIndex();
                cardsClicked.set(index, true);
            }
        }
    }
    
    // Listener class for the cards state changing event
    public class CardStatesListener implements VetoableChangeListener, Serializable {
        @Override
        public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
            if (evt.getPropertyName().equals("state")) {
                State oldState = (State) evt.getOldValue();
                int index = ((Card) evt.getSource()).getIndex();
                
                switch (oldState) {
                    // If the card want to change the state from FACE_DOWN to FACE_UP
                    case FACE_DOWN:
                        cardsClicked.set(index, false);
                        
                        // Save the value of the first uncovered card
                        if (v1 == -1) {
                            v1 = ((Card) evt.getSource()).getValue();
                            firePropertyChange("uncovered", null, true); // Fires the event for the Counter
                            break;
                        }

                        // Save the value of the second uncovered card
                        if (v2 == -1) {
                            v2 = ((Card) evt.getSource()).getValue();
                            firePropertyChange("uncovered", null, true); // Fires the event for the Counter
                            onMatch();
                            break;
                        }
                            
                        throw new PropertyVetoException("The are already 2 uncovered cards.", evt);
                    
                    // If the card want to change the state from FACE_UP to FACE_DOWN
                    case FACE_UP:
                        // If the state change derives from a click event the change is refused
                        if (cardsClicked.get(index)) {
                            cardsClicked.set(index, false);
                            throw new PropertyVetoException("The card is uncovered.", evt);
                        }

                        break;                      
                    
                    // If the card want to change the state from EXCLUDED to FACE_DOWN
                    case EXCLUDED:
                        // If the change of the state derives from a click event the change is refused
                        if (cardsClicked.get(index)) {
                            cardsClicked.set(index, false);
                            throw new PropertyVetoException("The card is excluded.", evt);
                        }
                        
                        break;                      
                }
            }
        }
    }
    
    // Listener class for the shuffle event    
    public class ShuffleListener implements PropertyChangeListener, Serializable {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("shuffle")) {
                setPairs(0);
                v1 = -1;
                v2 = -1;
            }
        }
    }   
    
    public void setCards(Card[] cards) {
        this.cards = cards;
        CardStatesListener cardStatesListener = new CardStatesListener();
        
        for (Card card : cards) {
            card.addVetoableChangelistener(cardStatesListener);
            cardsClicked.add(false);
        }
    }
    
    public void setPairs(int newPairs) {
        pairs = newPairs;
        setText("Pairs: " + pairs); 
    } 
    
    // Implement the business logic for the match event
    private void onMatch() {
        if (v1 == v2) {
            setPairs(pairs + 1);
           
            scheduler.schedule(() -> {
                firePropertyChange("matched", null, true);
                v1 = -1;
                v2 = -1;
                
                if (pairs == 4) {
                    firePropertyChange("finished", null, true); // If the player reaches 4 pairs send the endgame event
                }
            }, 500, TimeUnit.MILLISECONDS);
            
            return;
        }
            
        scheduler.schedule(() -> {
            firePropertyChange("matched", null, false);
            v1 = -1;
            v2 = -1;
        }, 500, TimeUnit.MILLISECONDS);
    }
}
