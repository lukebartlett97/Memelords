package MapLoader;


import Objects.People.OldMan;
import Tiles.GrassTile;
import Tiles.StoneTile;
import Tiles.Tile;
import Objects.TileObject;
import Objects.Coin;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class TileMap {
    private Tile[][] tileArray;
    private Map<String, Class<? extends Tile>> tileNameMap = new HashMap<>();
    private Map<String, Class<? extends TileObject>> tileObjectNameMap = new HashMap<>();
    private Map<String, Class<? extends TileObject>> personMap = new HashMap<>();

    public TileMap() {
        addTiles();
        addTileObjects();
        addPeople();
        JSONMap map = loadMap();
        if(map == null) {
            System.out.println("Map not found.");
            return;
        }
        System.out.println("Loading map: " + map.getName());
        tileArray = new Tile[map.getSize().get(0)][map.getSize().get(1)];
        try {
            setDefaultTiles(map.getDefaultTile());
            setOtherTiles(map.getTiles());
            setItems(map.getItems());
            setPeople(map.getPeople());
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.deepToString(tileArray));
    }

    public Tile[][] getTileArray() {
        return tileArray;
    }

    private void setOtherTiles(List<JSONTile> tiles) throws IllegalAccessException, InstantiationException {
        for (JSONTile tile : tiles) {
            tileArray[tile.getXPos()][tile.getYPos()] = tileNameMap.get(tile.getTileName()).newInstance();
            if(tile.getExtraConfig() != null) {
                tileArray[tile.getXPos()][tile.getYPos()].handleExtraConfig(tile.getExtraConfig());
            }
        }
    }

    private void setDefaultTiles(String defaultTile) throws IllegalAccessException, InstantiationException {
        Class defaultTileClass = tileNameMap.get(defaultTile);
        for(int i=0; i<tileArray.length; i++) {
            for(int j=0; j<tileArray[i].length; j++) {
                tileArray[i][j] = (Tile) defaultTileClass.newInstance();
            }
        }
    }

    private void setItems(List<JSONItem> items) throws IllegalAccessException, InstantiationException {
        for(JSONItem item : items) {
            tileArray[item.getXPos()][item.getYPos()].setObject(tileObjectNameMap.get(item.getObjectName()).newInstance());
            if(item.getExtraConfig() != null) {
                tileArray[item.getXPos()][item.getYPos()].getObject().handleExtraConfig(item.getExtraConfig());
            }
        }
    }

    private void setPeople(List<JSONPerson> people) throws IllegalAccessException, InstantiationException {
        for(JSONPerson person : people) {
            tileArray[person.getXPos()][person.getYPos()].setObject(personMap.get(person.getId()).newInstance());
        }
    }

    private JSONMap loadMap() {
        Gson gson = new Gson();
        String fileName = "JSON/Map1.json";
        ClassLoader classLoader = TileMap.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        try (Reader reader = new FileReader(file)) {

            // Convert JSON File to Java Object
            JSONMap map = gson.fromJson(reader, JSONMap.class);

            // print staff
            System.out.println(map);
            return map;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addTiles() {
        tileNameMap.put(GrassTile.TILE_NAME, GrassTile.class);
        tileNameMap.put(StoneTile.TILE_NAME, StoneTile.class);
    }

    private void addPeople() {
        personMap.put(OldMan.PERSON_NAME, OldMan.class);
    }

    private void addTileObjects() {
        tileObjectNameMap.put(Coin.ITEM_NAME, Coin.class);
    }

    public int getWidth() {
        return tileArray.length;
    }

    public int getHeight() {
        int max = 0;
        for (Tile[] line : tileArray) {
            if (line.length > max) {
                max = line.length;
            }
        }
        return max;
    }

    public int getLineHeight(int x) {
        return tileArray[x].length;
    }

    public boolean tileIsTraversable(int x, int y) {
        if(x < 0 || x >= getWidth() || y < 0 || y >= getLineHeight(x)) {
            return false;
        }
        return getTileArray()[x][y].isTraversable();
    }


    public boolean tileIsInteractable(int x, int y) {
        if(x < 0 || x >= getWidth() || y < 0 || y >= getLineHeight(x)) {
            return false;
        }
        return getTileArray()[x][y].isInteractable();
    }
}
