package multi.android.gotcha.DB;

public class CarMLVO {
    private String company;
    private String model;
    private String option;
    private String old;
    private String fuel;
    private String km;
    private String price;
    private String prediction;
    private String diff;

    public CarMLVO() {
    }

    public CarMLVO(String company, String model, String option, String old, String fuel, String km, String price, String prediction, String diff) {
        this.company = company;
        this.model = model;
        this.option = option;
        this.old = old;
        this.fuel = fuel;
        this.km = km;
        this.price = price;
        this.prediction = prediction;
        this.diff = diff;
    }

    @Override
    public String toString() {
        return "CarMLVO{" +
                "company='" + company + '\'' +
                ", model='" + model + '\'' +
                ", option='" + option + '\'' +
                ", old='" + old + '\'' +
                ", fuel='" + fuel + '\'' +
                ", km='" + km + '\'' +
                ", price='" + price + '\'' +
                ", prediction='" + prediction + '\'' +
                ", diff='" + diff + '\'' +
                '}';
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }
}
