package multi.android.gotcha.DB;

public class colorItem {
    private int color;
    private String name;

    public int getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "colorItem{" +
                "color='" + color + '\'' +
                "," + name;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public colorItem(int color, String name) {
        this.color = color;
        this.name = name;
    }

    public colorItem(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public colorItem(String name) {
        this.name = name;
    }
}
