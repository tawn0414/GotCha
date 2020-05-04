package multi.android.gotcha.DB;

public class CarVO {

    private String brand;		//제조사
    private String model;			//모델명
    private String car_number;			//차량번호
    private String year;			//연식
    private String fuel;			//연료
    private String grade;	//배기량
    private String transmission;	//변속기
    private String color;			//색상
    private String km;		//주행거리
    private String price;			//가격
    private String sago;		//사고 유무
    private String sail_explain;

    public CarVO(){

    }

    public CarVO(String brand, String model, String car_number, String year, String fuel, String grade, String transmission, String color, String km, String price, String sago, String sail_explain) {
        this.brand = brand;
        this.model = model;
        this.car_number = car_number;
        this.year = year;
        this.fuel = fuel;
        this.grade = grade;
        this.transmission = transmission;
        this.color = color;
        this.km = km;
        this.price = price;
        this.sago = sago;
        this.sail_explain = sail_explain;
    }

    @Override
    public String toString() {
        return "carVO{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", car_number='" + car_number + '\'' +
                ", year='" + year + '\'' +
                ", fuel='" + fuel + '\'' +
                ", grade='" + grade + '\'' +
                ", transmission='" + transmission + '\'' +
                ", color='" + color + '\'' +
                ", km='" + km + '\'' +
                ", price='" + price + '\'' +
                ", sago='" + sago + '\'' +
                ", sail_explain='" + sail_explain + '\'' +
                '}';
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

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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

    public String getSago() {
        return sago;
    }

    public void setSago(String sago) {
        this.sago = sago;
    }

    public String getSail_explain() {
        return sail_explain;
    }

    public void setSail_explain(String sail_explain) {
        this.sail_explain = sail_explain;
    }
}
