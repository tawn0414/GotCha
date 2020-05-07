package dBServer.community;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dataStruct.CommunityVO;

@Service
public class CommunityService {
	@Autowired
	@Qualifier("CommunityDAO")
	CommunityDAO dao;
	
	
	public List<CommunityVO> CommunityList() {
		return dao.CommunityList();	
	}

	public List<CommunityVO> SelectCommunityByTitle(String BOARD_TITLE) {
		return dao.SelectCommunityByTitle(BOARD_TITLE);
	}
	
	public List<CommunityVO> SelectCommunityByNickname(String MEM_NICKNAME) {
		return dao.SelectCommunityByNickname(MEM_NICKNAME);
	}
	
	public List<CommunityVO> SelectCommunityByContent(String BOARD_CONTENT) {
		return dao.SelectCommunityByContent(BOARD_CONTENT);
	}
	
	public List<CommunityVO> CommunityNext(Map map) {
		return dao.CommunityNext(map);
	}

	public int CommunityWrite(Map map) {
		return dao.CommunityWrite(map);
	}
	public int CommunityDelete(String BOARD_NUM) {
		return dao.CommunityDelete(BOARD_NUM);
	}
	public int HitUpdate(String BOARD_NUM) {
		return dao.HitUpdate(BOARD_NUM);
	}
}
