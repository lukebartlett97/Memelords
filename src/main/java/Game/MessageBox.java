package Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class MessageBox extends GameEntity{
    private List<String> messages;
    private int messageIndex;
    public MessageBox(List<String> messages) {
        this.messages = messages;
        messageIndex = 0;
        this.setImage("UI/MessageBox.png", new Color(255,255,255));
    }

    boolean update(KeyHandler keysPressed) {
        if(keysPressed.getNewKeysPressed().contains(KeyEvent.VK_SPACE)) {
            if(messageIndex == messages.size() - 1) {
                return true;
            } else {
                messageIndex++;
            }
        }
        return false;
    }

    public String getDisplayedMessage() {
        return messages.get(messageIndex);
    }
}
