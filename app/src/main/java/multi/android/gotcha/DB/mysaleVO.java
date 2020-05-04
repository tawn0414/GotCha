package multi.android.gotcha.DB;

public class mysaleVO {
    private String carNumber;
    private String brand;
    private String model;

    @Override
    public String toString() {
        return "mysaleItems{" +
                "carNumber='" + carNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public mysaleVO(String carNumber, String brand, String model) {
        this.carNumber = carNumber;
        this.brand = brand;
        this.model = model;
    }

    public mysaleVO() {
    }
}
