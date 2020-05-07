package dBServer.Reply;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dataStruct.CommunityVO;
import dataStruct.ReplyVO;

@Repository("ReplyDAO")
public class ReplyDAO {
	@Autowired
	SqlSession sqlSession;
	
	//댓글 전체조회
	public List<ReplyVO> ReplyList(String board_num) {
		List<ReplyVO> ReplyList = sqlSession.selectList("reply.ReplyList",board_num);
		return ReplyList;
	}
	
	public int ReplyWrite(Map map) {
		return sqlSession.insert("reply.ReplyWrite", map);
	}
	public int ReplyDelete(String bOARD_NUM) {
		return sqlSession.delete("reply.ReplyDelete", bOARD_NUM);
	}
}
