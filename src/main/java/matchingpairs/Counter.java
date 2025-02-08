/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matchingpairs;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author gabri
 */
public class Counter extends JLabel {
    private int moves;
    private Controller controller;
    private JFrame board;
    
    public Counter(JFrame board) {
        this.moves = 0;
        this.board = board;
        ShuffleListener cardValuesListener = new ShuffleListener();
        this.board.addPropertyChangeListener(cardValuesListener);
    }
    
    public void setController(Controller controller) {
        this.controller = controller;
        UncoveredListener uncoveredListener = new UncoveredListener();
        controller.addPropertyChangeListener(uncoveredListener);
    }
    
    public class ShuffleListener implements PropertyChangeListener, Serializable {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("shuffle")) {
                setMoves(0);
            }
        }
    }
    
    public int getMoves() {
        return moves;
    }
    
    private void setMoves(int newMoves) {
        moves = newMoves;
        setText("Moves: " + moves);
    }
    
    public class UncoveredListener implements PropertyChangeListener, Serializable {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("uncovered")) {
                setMoves(moves + 1);
            }
        }
    }
}
