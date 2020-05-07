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
	
	//�Խñ� ó�� ��ȸ
	public List<CommunityVO> CommunityList() {
		List<CommunityVO> CommunityList = sqlSession.selectList("community.SelectCommunity");
		return CommunityList;
	}
	//�Խñ� �������� ��ȸ
	public List<CommunityVO> SelectCommunityByTitle(String BOARD_TITLE) {
		List<CommunityVO> SelectCommunityByTitle = sqlSession.selectList("community.SelectCommunityByTitle",BOARD_TITLE);
		return SelectCommunityByTitle;
	}
	//�Խñ� �ۼ��ڷ� ��ȸ
	public List<CommunityVO> SelectCommunityByNickname(String MEM_NICKNAME) {
		List<CommunityVO> SelectCommunityByNickname = sqlSession.selectList("community.SelectCommunityByNickname",MEM_NICKNAME);
		return SelectCommunityByNickname;
	}
	//�Խñ� �������� ��ȸ
	public List<CommunityVO> SelectCommunityByContent(String BOARD_CONTENT) {
		List<CommunityVO> SelectCommunityByContent = sqlSession.selectList("community.SelectCommunityByTitle",BOARD_CONTENT);
		return SelectCommunityByContent;
	}
	//�Խñ� ���� ��ũ�� ��ȸ
	public List<CommunityVO> CommunityNext(Map map) {
		List<CommunityVO> CommunityNext = sqlSession.selectList("community.SelectCommunityNext",map);
		return CommunityNext;
	}

	//�Խñ� �ۼ�
	public int CommunityWrite(Map map) {
		return sqlSession.insert("community.CommunityWrite", map);
	}
	//�Խñ� �����
	public int CommunityDelete(String BOARD_NUM) {
		return sqlSession.delete("community.CommunityDelete", BOARD_NUM);
	}
	//�Խñ� ��ȸ��
	public int HitUpdate(String BOARD_NUM) {
		return sqlSession.update("community.HitUpdate",BOARD_NUM);
	}
}
