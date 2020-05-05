package multi.android.gotcha.DB;

import android.net.Uri;

import java.util.List;

public class ImageVO {
	private String IMAGE_NUM;
	private List<Uri> IMAGE;
	private String BOARD_NUM;//FK


	public ImageVO() {
	}

	public ImageVO(List<Uri> IMAGE) {
		this.IMAGE = IMAGE;
	}

	public ImageVO(String IMAGE_NUM, List<Uri> IMAGE, String BOARD_NUM) {
		this.IMAGE_NUM = IMAGE_NUM;
		this.IMAGE = IMAGE;
		this.BOARD_NUM = BOARD_NUM;
	}

	public String getIMAGE_NUM() {
		return IMAGE_NUM;
	}

	public List<Uri> getIMAGE() {
		return IMAGE;
	}

	public String getBOARD_NUM() {
		return BOARD_NUM;
	}

	public void setIMAGE_NUM(String IMAGE_NUM) {
		this.IMAGE_NUM = IMAGE_NUM;
	}

	public void setIMAGE(List<Uri> IMAGE) {
		this.IMAGE = IMAGE;
	}

	public void setBOARD_NUM(String BOARD_NUM) {
		this.BOARD_NUM = BOARD_NUM;
	}

	@Override
	public String toString() {
		return "ImageVO{" +
				"IMAGE_NUM='" + IMAGE_NUM + '\'' +
				", IMAGE=" + IMAGE +
				", BOARD_NUM='" + BOARD_NUM + '\'' +
				'}';
	}
}
