package dataStruct;

public class ImageVO {
	private String IMAGE_NUM;
	private String IMAGE;
	private String BOARD_NUM;//FK

	

	public ImageVO() {
	}



	public ImageVO(String iMAGE_NUM, String iMAGE, String bOARD_NUM) {
		super();
		IMAGE_NUM = iMAGE_NUM;
		IMAGE = iMAGE;
		BOARD_NUM = bOARD_NUM;
	}



	public String getIMAGE_NUM() {
		return IMAGE_NUM;
	}



	public void setIMAGE_NUM(String iMAGE_NUM) {
		IMAGE_NUM = iMAGE_NUM;
	}



	public String getIMAGE() {
		return IMAGE;
	}



	public void setIMAGE(String iMAGE) {
		IMAGE = iMAGE;
	}



	public String getBOARD_NUM() {
		return BOARD_NUM;
	}



	public void setBOARD_NUM(String bOARD_NUM) {
		BOARD_NUM = bOARD_NUM;
	}



	@Override
	public String toString() {
		return "ImageVO [IMAGE_NUM=" + IMAGE_NUM + ", IMAGE=" + IMAGE + ", BOARD_NUM=" + BOARD_NUM + "]";
	}
}
