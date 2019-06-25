package Game;

import java.util.HashSet;
import java.util.Set;

public class KeyHandler {
    private Set<Integer> keysPressed = new HashSet<>();
    private Set<Integer> newKeysPressed = new HashSet<>();

    void addKey(int keyCode) {
        keysPressed.add(keyCode);
        newKeysPressed.add(keyCode);
    }

    void removeKey(int keyCode) {
        keysPressed.remove(keyCode);
    }

    void refresh() {
        newKeysPressed = new HashSet<>();
    }

    public Set<Integer> getKeysPressed() {
        return keysPressed;
    }

    public Set<Integer> getNewKeysPressed() {
        return newKeysPressed;
    }
}
