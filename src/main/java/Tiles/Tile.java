package Tiles;

import Game.GameEntity;
import Game.Player;
import MapLoader.ExtraConfig;
import Objects.TileObject;

import java.util.List;

public abstract class Tile extends GameEntity {
    protected boolean traversable = true;
    protected boolean interactable = false;
    protected TileObject object = null;

    public boolean isTraversable() {
        if(object != null) {
            return traversable && !object.isBlocking();
        }
        return traversable;
    }

    public boolean isInteractable() {
        if(object != null && object.isInteractable()) {
            return true;
        }
        return interactable;
    }

    public void interact(Player player) {
        if(object != null && object.isInteractable()) {
            logger.debug("Player interacting with " + object.getName() + " on (" + player.getTargetX() + "," + player.getTargetY() + ")");
            object.interact(player);
        }
    }

    public void setObject(TileObject myObject) {
        object = myObject;
    }

    public void handleExtraConfig(List<ExtraConfig> config) {
        //Do Nothing...
    }

    public abstract String getTileName();

    @Override
    public String toString() {
        return getTileName();
    }

    public TileObject getObject() {
        return object;
    }
}
