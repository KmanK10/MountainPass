import java.awt.*;

public class Location {
    public int elevation;
    public Color color;

    public Location() {}

    public Location(int elevation) {
        this.elevation = elevation;
        color = new Color(0, 0, 255);
    }

    public void setColor(int max, int min) {
        int temp = 255 * (elevation - min) / (max - min);
        this.color = new Color(temp, temp, temp);
    }

    public String toString() {
        return "{elevation: " + elevation + ", color: " + color.toString() + "}";
    }
}
