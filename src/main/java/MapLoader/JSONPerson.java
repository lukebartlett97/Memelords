package MapLoader;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JSONPerson {

    @SerializedName("xPos")
    @Expose
    private Integer xPos;
    @SerializedName("yPos")
    @Expose
    private Integer yPos;
    @SerializedName("id")
    @Expose
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
