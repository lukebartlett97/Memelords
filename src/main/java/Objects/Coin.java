package Objects;

import Game.Player;
import Tiles.Tile;

import java.awt.*;

public class Coin extends TileObject{
    static public String ITEM_NAME = "Coin";

    public Coin() {
        setImage("Objects/Coin.png", new Color(255,255,255));
        blocking = false;
        name = "Coin";
        description = "A golden coin";
    }

    @Override
    public void stoodOn(Player player, Tile tile) {
        if(player.collect(this)) {
            tile.setObject(null);
        }
    }
}
