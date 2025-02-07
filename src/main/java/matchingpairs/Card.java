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
    private int value;
    private State state;
    private int index;
    private VetoableChangeSupport vetos = new VetoableChangeSupport(this);
    private Board board;
    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private Controller controller;
    
    private void setValue(int newValue) {
        value = newValue;
        setState(State.FACE_DOWN);
    }
    
    public void setController(Controller controller) {
        this.controller = controller;
        MatchedListener matchedListener = new MatchedListener();
        controller.addPropertyChangeListener(matchedListener);
    }
    
    public class ShuffleListener implements PropertyChangeListener, Serializable {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("shuffle")) {
                int newValue = ((ArrayList<Integer>) evt.getNewValue()).get(index);
                setValue(newValue);
                setState(State.FACE_DOWN);
            }
        }
    }

    public class MatchedListener implements PropertyChangeListener, Serializable {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("matched")) {
                if (state == State.FACE_UP) {
                    if (((boolean) evt.getNewValue())) {
                        setState(State.EXCLUDED);  
                        return;
                    }
                    
                    setState(State.FACE_DOWN);
                }
            }
        }
    }
    
    public Card(int index, Board board) {
        this.index = index;
        this.board = board;
        this.state = State.FACE_DOWN;
        ShuffleListener cardValuesListener = new ShuffleListener();
        this.board.addPropertyChangeListener(cardValuesListener);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener l) {
        if (changes != null) {
            changes.addPropertyChangeListener(l);
        } 
    }
    
    public void addVetoableChangelistener(VetoableChangeListener l) {
        vetos.addVetoableChangeListener(l);
    }
    
    public void onClick() {
        setState(State.FACE_UP);
    }
    
    public void setState(State newState) {
        State oldState = state;
        
        try{
            vetos.fireVetoableChange("state", oldState, newState);
            state = newState;
            
            switch (state) {
                case FACE_UP:
                    setBackground(Colors.white);
                    setText(Integer.toString(value));
                    break;
                    
                case FACE_DOWN:
                    setText("");
                    setBackground(Colors.green);
                    break;
                    
                case EXCLUDED:
                    setBackground(Colors.red);
                    setText("");
                    break;
            }
        } catch(PropertyVetoException e) {}
    }
    
    public int getValue() {
        return value;
    }
}
