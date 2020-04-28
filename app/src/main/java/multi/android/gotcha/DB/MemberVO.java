package multi.android.gotcha.DB;

public class MemberVO {
	private String MEM_KAKAO_ID;
	private String MEM_NAME;
	private String MEM_NICKNAME;
	private String MEM_BIRTH;
	private String MEM_GENDER;
	private String MEM_EMAIL;
	private String MEM_PHONENO;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public MemberVO() {
	}

	public MemberVO(String MEM_KAKAO_ID, String MEM_NAME, String MEM_NICKNAME, String MEM_BIRTH, String MEM_GENDER, String MEM_EMAIL, String MEM_PHONENO, String status) {
		this.MEM_KAKAO_ID = MEM_KAKAO_ID;
		this.MEM_NAME = MEM_NAME;
		this.MEM_NICKNAME = MEM_NICKNAME;
		this.MEM_BIRTH = MEM_BIRTH;
		this.MEM_GENDER = MEM_GENDER;
		this.MEM_EMAIL = MEM_EMAIL;
		this.MEM_PHONENO = MEM_PHONENO;
		this.status = status;
	}

	@Override
	public String toString() {
		return "MemberVO{" +
				"MEM_KAKAO_ID='" + MEM_KAKAO_ID + '\'' +
				", MEM_NAME='" + MEM_NAME + '\'' +
				", MEM_NICKNAME='" + MEM_NICKNAME + '\'' +
				", MEM_BIRTH='" + MEM_BIRTH + '\'' +
				", MEM_GENDER='" + MEM_GENDER + '\'' +
				", MEM_EMAIL='" + MEM_EMAIL + '\'' +
				", MEM_PHONENO='" + MEM_PHONENO + '\'' +
				", status='" + status + '\'' +
				'}';
	}

	public String getMEM_KAKAO_ID() {
		return MEM_KAKAO_ID;
	}

	public void setMEM_KAKAO_ID(String mEM_KAKAO_ID) {
		MEM_KAKAO_ID = mEM_KAKAO_ID;
	}

	public String getMEM_NAME() {
		return MEM_NAME;
	}

	public void setMEM_NAME(String mEM_NAME) {
		MEM_NAME = mEM_NAME;
	}

	public String getMEM_NICKNAME() {
		return MEM_NICKNAME;
	}

	public void setMEM_NICKNAME(String mEM_NICKNAME) {
		MEM_NICKNAME = mEM_NICKNAME;
	}

	public String getMEM_BIRTH() {
		return MEM_BIRTH;
	}

	public void setMEM_BIRTH(String mEM_BIRTH) {
		MEM_BIRTH = mEM_BIRTH;
	}

	public String getMEM_GENDER() {
		return MEM_GENDER;
	}

	public void setMEM_GENDER(String mEM_GENDER) {
		MEM_GENDER = mEM_GENDER;
	}

	public String getMEM_EMAIL() {
		return MEM_EMAIL;
	}

	public void setMEM_EMAIL(String mEM_EMAIL) {
		MEM_EMAIL = mEM_EMAIL;
	}

	public String getMEM_PHONENO() {
		return MEM_PHONENO;
	}

	public void setMEM_PHONENO(String mEM_PHONENO) {
		MEM_PHONENO = mEM_PHONENO;
	}



}
