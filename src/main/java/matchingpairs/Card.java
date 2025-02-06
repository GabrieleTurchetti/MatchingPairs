/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matchingpairs;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeSupport;
import java.beans.VetoableChangeListener;
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
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private VetoableChangeSupport vetos = new VetoableChangeSupport(this);
    
    public Card(int index) {
        this.index = index;
        this.state = State.FACE_DOWN;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
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
