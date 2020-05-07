package dBServer.member;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dataStruct.MemberVO;

@Controller
public class MemberController {
	@Autowired
	MemberService memberService;
	//로그인 시 기존회원 체크
	@RequestMapping(value = "/searchIdInKakao", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, String> SearchIdInKakao(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<String, String>();
		String kakaoNo = request.getParameter("kakaoNo");
		System.out.println(kakaoNo);
		MemberVO vo = memberService.searchIdInKakao(kakaoNo);
		if (vo!=null) {
			map.put("status", "true");
			map.put("mem_kakao_id", vo.getMem_kakao_id());
			map.put("mem_name", vo.getMem_name());
			map.put("mem_nickname", vo.getMem_nickname());
			map.put("mem_birth", vo.getMem_birth());
			map.put("mem_gender", vo.getMem_gender());
			map.put("mem_email", vo.getMem_email());
			map.put("mem_phoneno", vo.getMem_phoneno());
		} else {
			map.put("status", "false");
		}
		System.out.println(map);
		return map;
	}
	//회원가입
	@RequestMapping(value = "/signUp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, String> signUp(HttpServletRequest request) {
		System.out.println("접속성공");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String kakaoNo = request.getParameter("kakaoNo");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String birth = request.getParameter("birth");
		String gender = request.getParameter("gender");
		String eMail = request.getParameter("eMail");
		String phoneNo = request.getParameter("phoneNo");
		Map<String, String> map = new HashMap<String, String>();
		map.put("MEM_KAKAO_ID", kakaoNo);
		map.put("MEM_NAME", name);
		map.put("MEM_NICKNAME", nickname);
		map.put("MEM_BIRTH", birth);
		map.put("MEM_GENDER", gender);
		map.put("MEM_EMAIL", eMail);
		map.put("MEM_PHONENO", phoneNo);
		memberService.signUp(map);
		System.out.println(map);
		return map;
	}
	//회원탈퇴
	@RequestMapping(value = "/signDown", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, String> signDown(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String kakaoNo = request.getParameter("kakaoNo");
		System.out.println("탈퇴작업시도");
		memberService.signDown(kakaoNo);
		Map<String, String> map = new HashMap<String, String>();
		return map;
	}

	
	
}