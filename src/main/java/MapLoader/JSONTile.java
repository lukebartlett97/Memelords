package MapLoader;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JSONTile {

    @SerializedName("tileName")
    @Expose
    private String tileName;
    @SerializedName("xPos")
    @Expose
    private Integer xPos;
    @SerializedName("yPos")
    @Expose
    private Integer yPos;
    @SerializedName("extraConfig")
    @Expose
    private List<ExtraConfig> extraConfig = null;

    public String getTileName() {
        return tileName;
    }

    public void setTileName(String tileName) {
        this.tileName = tileName;
    }

    public Integer getXPos() {
        return xPos;
    }

    public void setXPos(Integer xPos) {
        this.xPos = xPos;
    }

    public Integer getYPos() {
        return yPos;
    }

    public void setYPos(Integer yPos) {
        this.yPos = yPos;
    }

    public List<ExtraConfig> getExtraConfig() {
        return extraConfig;
    }

    public void setExtraConfig(List<ExtraConfig> extraConfig) {
        this.extraConfig = extraConfig;
    }

}
