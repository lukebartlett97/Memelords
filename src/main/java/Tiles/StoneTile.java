package Tiles;

public class StoneTile extends Tile{
    static public String TILE_NAME = "Stone";


    public StoneTile() {
        setImage("Tiles/Stone.png", null);
        traversable = false;
    }

    @Override
    public String getTileName() {
        return TILE_NAME;
    }
}
