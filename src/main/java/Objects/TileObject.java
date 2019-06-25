package Objects;

import Game.GameEntity;
import Game.Player;
import MapLoader.ExtraConfig;
import Tiles.Tile;

import java.util.List;

public abstract class TileObject extends GameEntity {
    protected boolean blocking = true;
    protected boolean interactable = false;
    protected String name;
    protected String description;

    public boolean isBlocking() {
        return blocking;
    }

    public void interact(Player player) {
        //Do Nothing...
    }

    public void stoodOn(Player player, Tile tile) {
        //Do Nothing...
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void handleExtraConfig(List<ExtraConfig> extraConfig) {
        //Do Nothing..
    }

    public boolean isInteractable() {
        return interactable;
    }
}
