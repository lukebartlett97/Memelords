package Game;

import java.awt.*;

public interface GameMode {
    void update(KeyHandler keysPressed, GameState gameState);
    void paint(Graphics g, GameState gameState);
}
