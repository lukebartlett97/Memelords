package Game;

import Events.EventHandler;
import MapLoader.TileMap;
import Objects.TileObject;
import Quests.QuestCollection;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.util.Set;

public class Player extends GameEntity{
    private int xPosition;
    private int yPosition;
    private int progress;
    private String direction;
    private List<TileObject> inventory = new ArrayList<>();
    private int targetX;
    private int targetY;
    private PlayerStatus status = PlayerStatus.IDLE;
    private QuestCollection questCollection;
    private MessageBox messageBox;

    Player(int xPos, int yPos) {
        xPosition = xPos;
        yPosition = yPos;
        setImage("Players/Player.png",  new Color(255,255,255));
        questCollection = new QuestCollection();
    }

    private void setTargetPosition() {
        switch (direction){
            case "Left":
                targetX = xPosition - 1;
                targetY = yPosition;
                break;
            case "Right":
                targetX = xPosition + 1;
                targetY = yPosition;
                break;
            case "Up":
                targetX = xPosition;
                targetY = yPosition - 1;
                break;
            case "Down":
                targetX = xPosition;
                targetY = yPosition + 1;
                break;
        }
    }

    void move(PlayMode playState, String direction) {
        this.direction = direction;
        setTargetPosition();
        if(playState.getMap().tileIsTraversable(targetX, targetY)) {
            status = PlayerStatus.MOVING;
        }
    }

    int getX() {
        return xPosition;
    }

    int getY() {
        return yPosition;
    }

    public int getTargetX() {
        return targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    int getProgress() {
        return progress;
    }

    public boolean collect(TileObject object) {
        inventory.add(object);
        EventHandler.getInstance().collectEvent.notify(object);
        logger.debug("Player collected " + object.getName());
        return true;
    }

    public void update(PlayMode playMode, KeyHandler keyHandler) {
        Set<Integer> keysPressed = keyHandler.getKeysPressed();
        switch(status) {
            case IDLE:
                if (keysPressed.contains(KeyEvent.VK_LEFT)) {
                    move(playMode, "Left");
                } else if (keysPressed.contains(KeyEvent.VK_RIGHT)) {
                    move(playMode, "Right");
                } else if (keysPressed.contains(KeyEvent.VK_UP)) {
                    move(playMode, "Up");
                } else if (keysPressed.contains(KeyEvent.VK_DOWN)) {
                    move(playMode, "Down");
                } else if (keysPressed.contains(KeyEvent.VK_SPACE)) {
                    if(keyHandler.getNewKeysPressed().contains(KeyEvent.VK_SPACE)) {
                        interact(playMode.getMap());
                    }
                }
                break;
            case TALKING:
                boolean finished = messageBox.update(keyHandler);
                if(finished) {
                    setStatus(PlayerStatus.IDLE);
                }
                break;
            case MOVING:
                if(progress < 100) {
                    progress += 8;
                    if(progress > 100) {
                        progress = 100;
                    }
                } else {
                    progress = 0;
                    status = PlayerStatus.IDLE;
                    xPosition = targetX;
                    yPosition = targetY;
                    playMode.checkEvent(xPosition, yPosition, this);
                }
                break;
        }
    }

    public void interact(TileMap map) {
        setTargetPosition();
        if (map.tileIsInteractable(targetX,targetY)) {
            map.getTileArray()[targetX][targetY].interact(this);
        }
    }

    public PlayerStatus getStatus() {
        return status;
    }

    public void setStatus(PlayerStatus status) {
        this.status = status;
    }

    public QuestCollection getQuestCollection() {
        return questCollection;
    }

    public MessageBox getMessageBox() {
        return messageBox;
    }

    public void setMessageBox(MessageBox messageBox) {
        this.messageBox = messageBox;
    }

    public void startTalking(ArrayList<String> messages) {
        setStatus(PlayerStatus.TALKING);
        messageBox = new MessageBox(messages);
    }
}
