package dataStruct;

public class ReplyVO {
	private String rEPLY_NUM;
	private String mEM_NICKNAME;
	private String rEPLY_REGDATE;
	private String rEPLY_CONTENT;
	private String bOARD_NUM;//FK
	

	public ReplyVO() {
	}


	public ReplyVO(String rEPLY_NUM, String mEM_NICKNAME, String rEPLY_REGDATE, String rEPLY_CONTENT,
			String bOARD_NUM) {
		super();
		this.rEPLY_NUM = rEPLY_NUM;
		this.mEM_NICKNAME = mEM_NICKNAME;
		this.rEPLY_REGDATE = rEPLY_REGDATE;
		this.rEPLY_CONTENT = rEPLY_CONTENT;
		this.bOARD_NUM = bOARD_NUM;
	}


	public String getrEPLY_NUM() {
		return rEPLY_NUM;
	}


	public void setrEPLY_NUM(String rEPLY_NUM) {
		this.rEPLY_NUM = rEPLY_NUM;
	}


	public String getmEM_NICKNAME() {
		return mEM_NICKNAME;
	}


	public void setmEM_NICKNAME(String mEM_NICKNAME) {
		this.mEM_NICKNAME = mEM_NICKNAME;
	}


	public String getrEPLY_REGDATE() {
		return rEPLY_REGDATE;
	}


	public void setrEPLY_REGDATE(String rEPLY_REGDATE) {
		this.rEPLY_REGDATE = rEPLY_REGDATE;
	}


	public String getrEPLY_CONTENT() {
		return rEPLY_CONTENT;
	}


	public void setrEPLY_CONTENT(String rEPLY_CONTENT) {
		this.rEPLY_CONTENT = rEPLY_CONTENT;
	}


	public String getbOARD_NUM() {
		return bOARD_NUM;
	}


	public void setbOARD_NUM(String bOARD_NUM) {
		this.bOARD_NUM = bOARD_NUM;
	}


	@Override
	public String toString() {
		return "ReplyVO [rEPLY_NUM=" + rEPLY_NUM + ", mEM_NICKNAME=" + mEM_NICKNAME + ", rEPLY_REGDATE=" + rEPLY_REGDATE
				+ ", rEPLY_CONTENT=" + rEPLY_CONTENT + ", bOARD_NUM=" + bOARD_NUM + "]";
	}


	

	

	
}
