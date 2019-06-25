package Tiles;

public class GrassTile extends Tile{
    static public String TILE_NAME = "Grass";

    public GrassTile() {
        setImage("Tiles/Grass.png", null);
    }

    @Override
    public String getTileName() {
        return TILE_NAME;
    }
}
