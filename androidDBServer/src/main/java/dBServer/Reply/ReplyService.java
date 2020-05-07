package dBServer.Reply;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dataStruct.ReplyVO;

@Service
public class ReplyService {
	@Autowired
	@Qualifier("ReplyDAO")
	ReplyDAO dao;
	
	//댓글 전체조회
	public List<ReplyVO> ReplyList(String board_num) {
		return dao.ReplyList(board_num);
		}

	public int ReplyWrite(Map map) {
		return dao.ReplyWrite(map);
	}
	public int ReplyDelete(String bOARD_NUM) {
		return dao.ReplyDelete(bOARD_NUM);
	}
}
