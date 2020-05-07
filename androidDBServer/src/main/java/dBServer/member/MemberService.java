package dBServer.member;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dataStruct.MemberVO;

@Service
public class MemberService {
	@Autowired
	@Qualifier("MemberDAO")
	MemberDAO dao;
	
	
	public List<MemberVO> selectAll() {
		return dao.selectAll();
	}
	public MemberVO searchIdInKakao(String kakaoNo) {
		return dao.searchIdInKakao(kakaoNo);
	}
	public int signUp(Map map) {
		return dao.signUp(map);
	}
	public int signDown(String kakaoNo) {
		return dao.signDown(kakaoNo);
	}
}
