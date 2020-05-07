package multi.android.gotcha.DB;

public class CommunityVO {
	private String board_NUM;
	private String mem_NICKNAME;//FK
	private String board_REGDATE;
	private String board_TITLE;
	private int board_HIT;
	private int replynum;
	private String board_CONTENT;
	private String image;

	public CommunityVO() {
	}

	@Override
	public String toString() {
		return "CommunityVO{" +
				"board_NUM='" + board_NUM + '\'' +
				", mem_NICKNAME='" + mem_NICKNAME + '\'' +
				", board_REGDATE='" + board_REGDATE + '\'' +
				", board_TITLE='" + board_TITLE + '\'' +
				", board_HIT=" + board_HIT +
				", replynum=" + replynum +
				", board_CONTENT='" + board_CONTENT + '\'' +
				", image='" + image + '\'' +
				'}';
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBoard_NUM() {
		return board_NUM;
	}

	public String getMem_NICKNAME() {
		return mem_NICKNAME;
	}

	public String getBoard_REGDATE() {
		return board_REGDATE;
	}

	public String getBoard_TITLE() {
		return board_TITLE;
	}

	public int getBoard_HIT() {
		return board_HIT;
	}

	public int getReplynum() {
		return replynum;
	}

	public String getBoard_CONTENT() {
		return board_CONTENT;
	}

	public void setBoard_NUM(String board_NUM) {
		this.board_NUM = board_NUM;
	}

	public void setMem_NICKNAME(String mem_NICKNAME) {
		this.mem_NICKNAME = mem_NICKNAME;
	}

	public void setBoard_REGDATE(String board_REGDATE) {
		this.board_REGDATE = board_REGDATE;
	}

	public void setBoard_TITLE(String board_TITLE) {
		this.board_TITLE = board_TITLE;
	}

	public void setBoard_HIT(int board_HIT) {
		this.board_HIT = board_HIT;
	}

	public void setReplynum(int replynum) {
		this.replynum = replynum;
	}

	public void setBoard_CONTENT(String board_CONTENT) {
		this.board_CONTENT = board_CONTENT;
	}

}
