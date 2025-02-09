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
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
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
    private int pairs;
    private JFrame board;
    ScheduledExecutorService scheduler;
    private ArrayList<Boolean> cardsClicked;
    
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
    
    public void setCards(Card[] cards) {
        this.cards = cards;
        CardStatesListener cardStatesListener = new CardStatesListener();
        
        for (Card card : cards) {
            card.addVetoableChangelistener(cardStatesListener);
            cardsClicked.add(false);
        }
    }
    
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
    
    public void setPairs(int newPairs) {
        pairs = newPairs;
        setText("Pairs: " + pairs);
        
        if (pairs == 4) {
            scheduler.schedule(() -> {
                firePropertyChange("finished", null, true);
            }, 600, TimeUnit.MILLISECONDS);
        }
    }
    
    public class ClickedListener implements PropertyChangeListener, Serializable {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("clicked")) {
                int index = ((Card) evt.getSource()).getIndex();
                cardsClicked.set(index, true);
            }
        }
    }
    
    public class CardStatesListener implements VetoableChangeListener, Serializable {
        @Override
        public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
            if (evt.getPropertyName().equals("state")) {
                State oldState = (State) evt.getOldValue();
                State newState = (State) evt.getNewValue();
                int index = ((Card) evt.getSource()).getIndex();
                
                switch (oldState) {
                    case FACE_DOWN:
                        cardsClicked.set(index, false);
                        
                        if (v1 == -1) {
                            v1 = ((Card) evt.getSource()).getValue();
                            firePropertyChange("uncovered", null, true);
                            break;
                        }

                        if (v2 == -1) {
                            v2 = ((Card) evt.getSource()).getValue();
                            firePropertyChange("uncovered", null, true);
                            onMatch();
                            break;
                        }
                            
                        throw new PropertyVetoException("The are already 2 uncovered cards.", evt);
                        
                    case FACE_UP:
                        if (cardsClicked.get(index)) {
                            cardsClicked.set(index, false);
                            throw new PropertyVetoException("The card is uncovered.", evt);
                        }

                        break;                      
                        
                    case EXCLUDED:
                        if (cardsClicked.get(index)) {
                            cardsClicked.set(index, false);
                            throw new PropertyVetoException("The card is excluded.", evt);
                        }
                        
                        break;                      
                }
                
                
                /*switch (newState) {
                    case FACE_UP:
                        if (oldState == State.FACE_DOWN) {
                            if (v1 == -1) {
                                v1 = ((Card) evt.getSource()).getValue();
                                firePropertyChange("uncovered", null, true);
                                break;
                            }

                            if (v2 == -1) {
                                v2 = ((Card) evt.getSource()).getValue();
                                firePropertyChange("uncovered", null, true);
                                onMatch();
                                break;
                            }
                            
                            throw new PropertyVetoException("The are already 2 uncovered cards.", evt);
                        }
                        
                        throw new PropertyVetoException("The card is already excluded.", evt);
                        
                    case FACE_DOWN:
                        break;
                        
                    case EXCLUDED:
                        break;
                }*/
            }
        }
    }
    
    private void onMatch() {
        if (v1 == v2) {
            setPairs(pairs + 1);
           
            scheduler.schedule(() -> {
                firePropertyChange("matched", null, true);
                v1 = -1;
                v2 = -1;
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
