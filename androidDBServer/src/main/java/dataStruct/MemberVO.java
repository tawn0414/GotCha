package dataStruct;

public class MemberVO {
	private String mem_kakao_id;
	private String mem_name;
	private String mem_nickname;
	private String mem_birth;
	private String mem_gender;
	private String mem_email;
	private String mem_phoneno;

	public MemberVO() {
	}

	
	
	
	public MemberVO(String mem_kakao_id, String mem_name, String mem_nickname, String mem_birth, String mem_gender,
			String mem_email, String mem_phoneno) {
		super();
		this.mem_kakao_id = mem_kakao_id;
		this.mem_name = mem_name;
		this.mem_nickname = mem_nickname;
		this.mem_birth = mem_birth;
		this.mem_gender = mem_gender;
		this.mem_email = mem_email;
		this.mem_phoneno = mem_phoneno;
	}




	@Override
	public String toString() {
		return "MemberVO [mem_kakao_id=" + mem_kakao_id + ", mem_name=" + mem_name + ", mem_nickname=" + mem_nickname
				+ ", mem_birth=" + mem_birth + ", mem_gender=" + mem_gender + ", mem_email=" + mem_email
				+ ", mem_phoneno=" + mem_phoneno + "]";
	}




	public String getMem_kakao_id() {
		return mem_kakao_id;
	}

	public void setMem_kakao_id(String mem_kakao_id) {
		this.mem_kakao_id = mem_kakao_id;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_nickname() {
		return mem_nickname;
	}

	public void setMem_nickname(String mem_nickname) {
		this.mem_nickname = mem_nickname;
	}

	public String getMem_birth() {
		return mem_birth;
	}

	public void setMem_birth(String mem_birth) {
		this.mem_birth = mem_birth;
	}

	public String getMem_gender() {
		return mem_gender;
	}

	public void setMem_gender(String mem_gender) {
		this.mem_gender = mem_gender;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getMem_phoneno() {
		return mem_phoneno;
	}

	public void setMem_phoneno(String mem_phoneno) {
		this.mem_phoneno = mem_phoneno;
	}

	

}
