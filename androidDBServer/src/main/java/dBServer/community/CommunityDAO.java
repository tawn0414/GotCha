package dBServer.community;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dataStruct.CommunityVO;

@Repository("CommunityDAO")
public class CommunityDAO {
	@Autowired
	SqlSession sqlSession;
	
	//게시글 처음 조회
	public List<CommunityVO> CommunityList() {
		List<CommunityVO> CommunityList = sqlSession.selectList("community.SelectCommunity");
		return CommunityList;
	}
	//게시글 제목으로 조회
	public List<CommunityVO> SelectCommunityByTitle(String BOARD_TITLE) {
		List<CommunityVO> SelectCommunityByTitle = sqlSession.selectList("community.SelectCommunityByTitle",BOARD_TITLE);
		return SelectCommunityByTitle;
	}
	//게시글 작성자로 조회
	public List<CommunityVO> SelectCommunityByNickname(String MEM_NICKNAME) {
		List<CommunityVO> SelectCommunityByNickname = sqlSession.selectList("community.SelectCommunityByNickname",MEM_NICKNAME);
		return SelectCommunityByNickname;
	}
	//게시글 내용으로 조회
	public List<CommunityVO> SelectCommunityByContent(String BOARD_CONTENT) {
		List<CommunityVO> SelectCommunityByContent = sqlSession.selectList("community.SelectCommunityByTitle",BOARD_CONTENT);
		return SelectCommunityByContent;
	}
	//게시글 무한 스크롤 조회
	public List<CommunityVO> CommunityNext(Map map) {
		List<CommunityVO> CommunityNext = sqlSession.selectList("community.SelectCommunityNext",map);
		return CommunityNext;
	}

	//게시글 작석
	public int CommunityWrite(Map map) {
		return sqlSession.insert("community.CommunityWrite", map);
	}
	//게시글 지우기
	public int CommunityDelete(String BOARD_NUM) {
		return sqlSession.delete("community.CommunityDelete", BOARD_NUM);
	}
	//게시글 조회수
	public int HitUpdate(String BOARD_NUM) {
		return sqlSession.update("community.HitUpdate",BOARD_NUM);
	}
}
