package multi.android.gotcha.DB;

public class CarSaleVO {
	private String Brand;
	private String Model;
	private String Fuel;
	private String Transmission;
	private String Color;
	private String Year;
	private String Displacement;
	private String Km;
	private String Sago;
	private String Price;
	private String sail_explain;
	private int picture1;
	private int picture2;
	private int picture3;
	private int picture4;

	public CarSaleVO(){

	}

	public CarSaleVO(String brand, String model, String fuel, String transmission, String color, String year, String displacement, String km, String sago, String price, String sail_explain, int picture1, int picture2, int picture3, int picture4) {
		Brand = brand;
		Model = model;
		Fuel = fuel;
		Transmission = transmission;
		Color = color;
		Year = year;
		Displacement = displacement;
		Km = km;
		Sago = sago;
		Price = price;
		this.sail_explain = sail_explain;
		this.picture1 = picture1;
		this.picture2 = picture2;
		this.picture3 = picture3;
		this.picture4 = picture4;
	}

	@Override
	public String toString() {
		return "CarSaleVO{" +
				"Brand='" + Brand + '\'' +
				", Model='" + Model + '\'' +
				", Fuel='" + Fuel + '\'' +
				", Transmission='" + Transmission + '\'' +
				", Color='" + Color + '\'' +
				", Year='" + Year + '\'' +
				", Displacement='" + Displacement + '\'' +
				", Km='" + Km + '\'' +
				", Sago='" + Sago + '\'' +
				", Price='" + Price + '\'' +
				", sail_explain='" + sail_explain + '\'' +
				", picture1=" + picture1 +
				", picture2=" + picture2 +
				", picture3=" + picture3 +
				", picture4=" + picture4 +
				'}';
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		Model = model;
	}

	public String getFuel() {
		return Fuel;
	}

	public void setFuel(String fuel) {
		Fuel = fuel;
	}

	public String getTransmission() {
		return Transmission;
	}

	public void setTransmission(String transmission) {
		Transmission = transmission;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

	public String getDisplacement() {
		return Displacement;
	}

	public void setDisplacement(String displacement) {
		Displacement = displacement;
	}

	public String getKm() {
		return Km;
	}

	public void setKm(String km) {
		Km = km;
	}

	public String getSago() {
		return Sago;
	}

	public void setSago(String sago) {
		Sago = sago;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getSail_explain() {
		return sail_explain;
	}

	public void setSail_explain(String sail_explain) {
		this.sail_explain = sail_explain;
	}

	public int getPicture1() {
		return picture1;
	}

	public void setPicture1(int picture1) {
		this.picture1 = picture1;
	}

	public int getPicture2() {
		return picture2;
	}

	public void setPicture2(int picture2) {
		this.picture2 = picture2;
	}

	public int getPicture3() {
		return picture3;
	}

	public void setPicture3(int picture3) {
		this.picture3 = picture3;
	}

	public int getPicture4() {
		return picture4;
	}

	public void setPicture4(int picture4) {
		this.picture4 = picture4;
	}
}
