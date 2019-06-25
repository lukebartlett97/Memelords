package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JPanel implements ActionListener {
    private static final int DELAY = 10;
    private final GameState gameState;
    private KeyHandler keyHandler = new KeyHandler();


    public Board() {
        gameState = new GameState();
        attach();
        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        step();
    }

    private void step() {
        gameState.getGameMode().update(keyHandler, gameState);
        repaint();
        keyHandler.refresh();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        setBackground(Color.WHITE);
        gameState.getGameMode().paint(g, gameState);
    }

    private void attach() {
        class KeyboardListener implements KeyListener {
            private KeyboardListener() {}

            // When a key is pressed, add the event to the solver's queue
            public void keyPressed(KeyEvent e) {
                keyHandler.addKey(e.getKeyCode());
            }

            // These are needed to implement the KeyListener interface,
            // but they don't need to do anything
            public void keyReleased(KeyEvent e) {
                keyHandler.removeKey(e.getKeyCode());
            }

            public void keyTyped(KeyEvent e) {
            }
        }

        // Add one of these listeners to the panel
        addKeyListener(new KeyboardListener());
        // The panel needs to be focusable so that it can receive KeyEvents
        setFocusable(true);
    }
}
