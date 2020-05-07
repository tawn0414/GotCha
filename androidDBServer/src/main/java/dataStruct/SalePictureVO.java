package dataStruct;

public class SalePictureVO {
	private String salenum;
	private String picture1;
	private String picture2;
	private String picture3;
	private String picture4;
	public SalePictureVO() {
		super();
	}
	
	public SalePictureVO(String picture1, String picture2, String picture3, String picture4) {
		super();
		this.picture1 = picture1;
		this.picture2 = picture2;
		this.picture3 = picture3;
		this.picture4 = picture4;
	}

	@Override
	public String toString() {
		return "SalePictureVO [salenum=" + salenum + ", picture1=" + picture1 + ", picture2=" + picture2 + ", picture3="
				+ picture3 + ", picture4=" + picture4 + "]";
	}
	public String getSalenum() {
		return salenum;
	}
	public void setSalenum(String salenum) {
		this.salenum = salenum;
	}
	public String getPicture1() {
		return picture1;
	}
	public void setPicture1(String picture1) {
		this.picture1 = picture1;
	}
	public String getPicture2() {
		return picture2;
	}
	public void setPicture2(String picture2) {
		this.picture2 = picture2;
	}
	public String getPicture3() {
		return picture3;
	}
	public void setPicture3(String picture3) {
		this.picture3 = picture3;
	}
	public String getPicture4() {
		return picture4;
	}
	public void setPicture4(String picture4) {
		this.picture4 = picture4;
	}
	
	
}
