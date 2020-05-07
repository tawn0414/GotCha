package dBServer.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dataStruct.MemberVO;

@Repository("MemberDAO")
public class MemberDAO {
	@Autowired
	SqlSession sqlSession;
	
	
	public List<MemberVO> selectAll() {
		List<MemberVO> voList = sqlSession.selectList("member.selectAll");
		return voList;
	}
	public MemberVO searchIdInKakao(String kakaoNo) {
		MemberVO findKakaoNo = sqlSession.selectOne("member.searchIdInKakao", kakaoNo);
		return findKakaoNo;
	}
	public int signUp(Map map) {
		return sqlSession.insert("member.signUp", map);
	}
	public int signDown(String kakaoNo) {
		return sqlSession.delete("member.signDown", kakaoNo);
	}
}
