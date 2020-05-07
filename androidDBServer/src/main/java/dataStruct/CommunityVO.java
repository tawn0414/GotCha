package dataStruct;

public class CommunityVO {
	private String BOARD_NUM;
	private String MEM_NICKNAME;//FK
	private String BOARD_REGDATE;
	private String BOARD_TITLE;
	private int BOARD_HIT;
	private	int REPLYNUM;
	private String BOARD_CONTENT;

	public CommunityVO() {
	}

	
	public CommunityVO(String bOARD_NUM, String mEM_NICKNAME, String bOARD_REGDATE, String bOARD_TITLE, int bOARD_HIT,
			int rEPLYNUM, String bOARD_CONTENT) {
		super();
		BOARD_NUM = bOARD_NUM;
		MEM_NICKNAME = mEM_NICKNAME;
		BOARD_REGDATE = bOARD_REGDATE;
		BOARD_TITLE = bOARD_TITLE;
		BOARD_HIT = bOARD_HIT;
		REPLYNUM = rEPLYNUM;
		BOARD_CONTENT = bOARD_CONTENT;
	}


	public String getBOARD_NUM() {
		return BOARD_NUM;
	}


	public void setBOARD_NUM(String bOARD_NUM) {
		BOARD_NUM = bOARD_NUM;
	}


	public String getMEM_NICKNAME() {
		return MEM_NICKNAME;
	}


	public void setMEM_NICKNAME(String mEM_NICKNAME) {
		MEM_NICKNAME = mEM_NICKNAME;
	}


	public String getBOARD_REGDATE() {
		return BOARD_REGDATE;
	}


	public void setBOARD_REGDATE(String bOARD_REGDATE) {
		BOARD_REGDATE = bOARD_REGDATE;
	}


	public String getBOARD_TITLE() {
		return BOARD_TITLE;
	}


	public void setBOARD_TITLE(String bOARD_TITLE) {
		BOARD_TITLE = bOARD_TITLE;
	}


	public int getBOARD_HIT() {
		return BOARD_HIT;
	}


	public void setBOARD_HIT(int bOARD_HIT) {
		BOARD_HIT = bOARD_HIT;
	}


	public int getREPLYNUM() {
		return REPLYNUM;
	}


	public void setREPLYNUM(int rEPLYNUM) {
		REPLYNUM = rEPLYNUM;
	}


	public String getBOARD_CONTENT() {
		return BOARD_CONTENT;
	}


	public void setBOARD_CONTENT(String bOARD_CONTENT) {
		BOARD_CONTENT = bOARD_CONTENT;
	}


	@Override
	public String toString() {
		return "CommunityVO [BOARD_NUM=" + BOARD_NUM + ", MEM_NICKNAME=" + MEM_NICKNAME + ", BOARD_REGDATE="
				+ BOARD_REGDATE + ", BOARD_TITLE=" + BOARD_TITLE + ", BOARD_HIT=" + BOARD_HIT + ", REPLYNUM=" + REPLYNUM
				+ ", BOARD_CONTENT=" + BOARD_CONTENT + "]";
	}
}
