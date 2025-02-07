/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matchingpairs;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import javax.swing.JLabel;

/**
 *
 * @author gabri
 */
public class Counter extends JLabel {
    private int count;
    private Controller controller;
    
    public Counter() {
        this.count = 0;
    }
    
    public void setController(Controller controller) {
        this.controller = controller;
        UncoveredListener uncoveredListener = new UncoveredListener();
        controller.addPropertyChangeListener(uncoveredListener);
    }
    
    public class UncoveredListener implements PropertyChangeListener, Serializable {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("uncovered")) {
                setText("Moves: " + ++count);
            }
        }
    }
}
