package multi.android.gotcha.DB;

public class CarVO {

    private String saleNum;
    private String userId;
    private String carNumber;
    private String brand;
    private String model;
    private String fuel;
    private String transmission;
    private String color;
    private String year;
    private String cc;
    private String km;
    private String sago;
    private String price;
    private String pricePredict;
    private String saleExplain;
    private String image;



    public CarVO() {
        super();
    }

    public CarVO(String saleNum, String userId, String carNumber, String brand, String model, String fuel,
                 String transmission, String color, String year, String cc, String km, String sago, String price,
                 String pricePredict, String saleExplain) {
        super();
        this.saleNum = saleNum;
        this.userId = userId;
        this.carNumber = carNumber;
        this.brand = brand;
        this.model = model;
        this.fuel = fuel;
        this.transmission = transmission;
        this.color = color;
        this.year = year;
        this.cc = cc;
        this.km = km;
        this.sago = sago;
        this.price = price;
        this.pricePredict = pricePredict;
        this.saleExplain = saleExplain;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getSago() {
        return sago;
    }

    public void setSago(String sago) {
        this.sago = sago;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPricePredict() {
        return pricePredict;
    }

    public void setPricePredict(String pricePredict) {
        this.pricePredict = pricePredict;
    }

    public String getSaleExplain() {
        return saleExplain;
    }

    public void setSaleExplain(String saleExplain) {
        this.saleExplain = saleExplain;
    }

    @Override
    public String toString() {
        return "CarVO [saleNum=" + saleNum + ", userId=" + userId + ", carNumber=" + carNumber + ", brand=" + brand
                + ", model=" + model + ", fuel=" + fuel + ", transmission=" + transmission + ", color=" + color
                + ", year=" + year + ", cc=" + cc + ", km=" + km + ", sago=" + sago + ", price=" + price
                + ", pricePredict=" + pricePredict + ", saleExplain=" + saleExplain + ", image=" + image + "]";
    }



}