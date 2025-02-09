/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matchingpairs;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeSupport;
import java.beans.VetoableChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JButton;



/**
 *
 * @author gabri
 */
public class Card extends JButton {
    private int value;
    private State state;
    private int index;
    private VetoableChangeSupport vetos;
    private Board board;
    private Controller controller;
    
    public Card(int index, Board board) {
        this.index = index;
        this.board = board;
        this.state = State.FACE_DOWN;
        ShuffleListener cardValuesListener = new ShuffleListener();
        this.board.addPropertyChangeListener(cardValuesListener);
        vetos = new VetoableChangeSupport(this);
    }
    
    // Listener class for the shuffle event
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

    // Listener class for the matching event
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
    
    public void addVetoableChangelistener(VetoableChangeListener listener) {
        vetos.addVetoableChangeListener(listener);
    } 

    @Override
    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        vetos.removeVetoableChangeListener(listener);
    }
    
    public int getIndex() {
        return index;
    }
    
    public int getValue() {
        return value;
    }

    private void setValue(int newValue) {
        value = newValue;
    }
    
    public void setController(Controller controller) {
        this.controller = controller;
        MatchedListener matchedListener = new MatchedListener();
        controller.addPropertyChangeListener(matchedListener);
    }
    
    public void setState(State newState) {
        State oldState = state;
        
        try{
            vetos.fireVetoableChange("state", oldState, newState);
            firePropertyChange("state", oldState, newState);
            state = newState;
            
            // Implement the business logic for the change of the state
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
    
    // Implement the business logic for the click event
    public void onClick() {
        firePropertyChange("clicked", false, true); // Fire the click event to the Controller
        
        if (state == State.FACE_DOWN) {
            setState(State.FACE_UP);
            return;
        }
        
        setState(State.FACE_DOWN);
    }
}
