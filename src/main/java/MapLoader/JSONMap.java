package MapLoader;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JSONMap {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("size")
    @Expose
    private List<Integer> size = null;
    @SerializedName("defaultTile")
    @Expose
    private String defaultTile;
    @SerializedName("tiles")
    @Expose
    private List<JSONTile> tiles = null;
    @SerializedName("Objects")
    @Expose
    private List<JSONItem> items = null;
    @SerializedName("People")
    @Expose
    private List<JSONPerson> people = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getSize() {
        return size;
    }

    public void setSize(List<Integer> size) {
        this.size = size;
    }

    public String getDefaultTile() {
        return defaultTile;
    }

    public void setDefaultTile(String defaultTile) {
        this.defaultTile = defaultTile;
    }

    public List<JSONTile> getTiles() {
        return tiles;
    }

    public void setTiles(List<JSONTile> tiles) {
        this.tiles = tiles;
    }

    public List<JSONItem> getItems() {
        return items;
    }

    public void setItems(List<JSONItem> items) {
        this.items = items;
    }

    public List<JSONPerson> getPeople() {
        return people;
    }

    public void setPeople(List<JSONPerson> people) {
        this.people = people;
    }

}
