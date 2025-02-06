/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matchingpairs;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeSupport;
import java.beans.VetoableChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author gabri
 */
public class Card extends JButton {
  
    enum State {
        EXCLUDED,
        FACE_DOWN,
        FACE_UP
    }
     
    private int value;
    private State state;
    private int index;
    private VetoableChangeSupport vetos = new VetoableChangeSupport(this);
    private Board board;
    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);;
    
    private void setValue(int newValue) {
        value = newValue;
        setText(Integer.toString(value));
    }
    
    public class CardValuesListener implements PropertyChangeListener, Serializable {
        public void propertyChange(PropertyChangeEvent evt) {
            int newValue = ((ArrayList<Integer>) evt.getNewValue()).get(index);
            setValue(newValue);
        }
    }
    
    public Card(int index, Board board) {
        this.index = index;
        this.board = board;
        this.state = State.FACE_DOWN;
        CardValuesListener cardValuesListener = new CardValuesListener();
        this.board.addPropertyChangeListener(cardValuesListener);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener l) {
        if (this.changes != null) {
            this.changes.addPropertyChangeListener(l);
        } 
    }
    
    public void addVetoableChangelistener(VetoableChangeListener l) {
        vetos.addVetoableChangeListener(l);
    }
    
    public void setState(State newState) {
        State oldState = state;
        
        try{
            vetos.fireVetoableChange("state", oldState, newState);
            state = newState;
        } catch(PropertyVetoException e){
            //
        }
    }
}
