package Game;

import MapLoader.TileMap;
import Tiles.Tile;

import java.awt.*;

public class PlayMode implements GameMode {
    private TileMap map;
    public int BLOCK_SIZE = 64;

    public PlayMode() {
        setMap(new TileMap());
    }

    public TileMap getMap() {
        return map;
    }

    public void setMap(TileMap map) {
        this.map = map;
    }

    public void update(KeyHandler keyHandler, GameState gameState) {
        gameState.getPlayer().update(this, keyHandler);

    }

    public void checkEvent(int xPosition, int yPosition, Player player) {
        Tile tile = map.getTileArray()[xPosition][yPosition];
        if(tile.getObject() != null) {
            tile.getObject().stoodOn(player, tile);
        }
    }

    public void paint(Graphics g, GameState gameState) {
        Tile[][] map = getMap().getTileArray();

        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[i].length; j++) {
                g.drawImage(map[i][j].getImage(), BLOCK_SIZE * i, BLOCK_SIZE * j, BLOCK_SIZE, BLOCK_SIZE,null);
                if(map[i][j].getObject() != null) {
                    g.drawImage(map[i][j].getObject().getImage(), BLOCK_SIZE * i, BLOCK_SIZE * j, BLOCK_SIZE, BLOCK_SIZE, null);
                }
            }
        }

        Player player = gameState.getPlayer();

        g.drawImage(player.getImage(),
                getPosition(BLOCK_SIZE * player.getX(), BLOCK_SIZE * player.getTargetX(), player.getProgress()),
                getPosition(BLOCK_SIZE * player.getY(), BLOCK_SIZE * player.getTargetY(), player.getProgress()),
                BLOCK_SIZE, BLOCK_SIZE,
                null);

        if(player.getStatus() == PlayerStatus.TALKING) {
            g.drawImage(player.getMessageBox().getImage(),
                    800,900,
                    null);
            g.setFont(new Font("Purisa", Font.PLAIN, 13));
            g.drawString(player.getMessageBox().getDisplayedMessage(), 850, 950);

        }

    }

    private int getPosition(int start, int end, int progress) {
        if(progress == 0) {
            return start;
        }
        return (int) Math.floor(start * (1 - ((double)progress / 100)) + (end * ((double)progress / 100)));
    }
}
