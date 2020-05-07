package dBServer.community;

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

import dBServer.Reply.ReplyService;
import dataStruct.CommunityVO;
import dataStruct.MemberVO;
import dataStruct.ReplyVO;

@Controller
public class CommunityDBController {
	@Autowired
	CommunityService CommunityService;
	@Autowired
	ReplyService ReplyService;
	
	/* 게시판 조회	*/
	@RequestMapping(value = "/CommunityList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CommunityVO> CommunityList(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
			
			List<CommunityVO> list = CommunityService.CommunityList();
			System.out.println("전체 스크롤 조회 == "+list);
			return list;
	}
	/*제목으로 게시판 조회*/
	@RequestMapping(value = "/CommunityListByTitle", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CommunityVO> CommunityListByTitle(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
			System.out.println("제목으로 조회 시작");
			String BOARD_TITLE = request.getParameter("board_Title");
			List<CommunityVO> list = CommunityService.SelectCommunityByTitle(BOARD_TITLE);
			System.out.println("제목으로 조회된 리스트"+list);
			return list;
	}
	/*내용으로 게시판 조회 */
	
	@RequestMapping(value = "/CommunityListByContent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CommunityVO> CommunityListByContent(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
			System.out.println("내용으로 조회 시작");
			String BOARD_CONTENT = request.getParameter("board_Content");
			List<CommunityVO> list = CommunityService.SelectCommunityByContent(BOARD_CONTENT);
			System.out.println("제목으로 조회된 리스트"+list);
			return list;
	}
	/*작성자로 게시판 조회*/
	@RequestMapping(value = "/CommunityListByNickname", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CommunityVO> CommunityListByNickname(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("내용으로 조회 시작");
		String MEM_NICKNAME = request.getParameter("mem_Nickname");
		List<CommunityVO> list = CommunityService.SelectCommunityByNickname(MEM_NICKNAME);
		System.out.println("제목으로 조회된 리스트"+list);
		return list;
	}
	/* 게시판 무한 스크롤 조회	*/
	@RequestMapping(value = "/CommunityNext", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CommunityVO> CommunityNext(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String num1 = request.getParameter("num1");
		String num2 = request.getParameter("num2");
		Map<String, String> map = new HashMap<String, String>();
		map.put("num1", num1);
		map.put("num2", num2);
		System.out.println("무한 스크롤 맵"+map);
		List<CommunityVO> list = CommunityService.CommunityNext(map);
		System.out.println("무한 스크롤 sql결과"+list);
		return list;

	}
/* 게시글 등록 */
	@RequestMapping(value = "/CommunityWrite", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void CommunityWrite(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String MEM_NICKNAME = request.getParameter("mem_NICKNAME");
		String BOARD_TITLE = request.getParameter("board_TITLE");
		String BOARD_CONTENT = request.getParameter("board_CONTENT");
		Map<String, String> map = new HashMap<String, String>();
		map.put("MEM_NICKNAME", MEM_NICKNAME);
		map.put("BOARD_TITLE", BOARD_TITLE);
		map.put("BOARD_CONTENT", BOARD_CONTENT);
		System.out.println("게시글 등록 컨트롤러 입니다."+map);
		CommunityService.CommunityWrite(map);
		
	}
	/* 게시글 삭제, 댓글 삭제도 같이되도록 */
	@RequestMapping(value = "/CommunityDelete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void CommunityDelete(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("삭제를 시작합니다.");
		String BOARD_NUM = request.getParameter("board_NUM");
		int delReplynum = ReplyService.ReplyDelete(BOARD_NUM);
		int delCommunitynum = CommunityService.CommunityDelete(BOARD_NUM);
		System.out.println("삭제된 글 갯수, 삭제된 행 갯수 = "+delCommunitynum+","+delReplynum);
		
	}
	/* 조회수 증가 */
	@RequestMapping(value = "/HitUpdate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void HitUpdate(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String BOARD_NUM = request.getParameter("board_NUM");
		CommunityService.HitUpdate(BOARD_NUM);
	}
	
	/*여기서 부터는 댓글 컨트롤러*/
	//댓글 조회
	@RequestMapping(value = "/ReplyList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<ReplyVO> ReplyList(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("댓글 조회 시작");
		String BOARD_NUM = request.getParameter("board_NUM");
		List<ReplyVO> list = ReplyService.ReplyList(BOARD_NUM);
		System.out.println("조회된 댓글 = "+list);
		return list;
	}
	//댓글 등록
		@RequestMapping(value = "/ReplyWrite", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		@ResponseBody
		public void ReplyWrite(HttpServletRequest request) {
			try {
				request.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			System.out.println("댓글을 등록하기 시작합니다.");
			String MEM_NICKNAME = request.getParameter("mem_NICKNAME");
			String rEPLY_CONTENT = request.getParameter("reply_CONTENT");
			String bOARD_NUM = request.getParameter("board_NUM");
			Map<String, String> map = new HashMap<String, String>();
			map.put("mEM_NICKNAME", MEM_NICKNAME);
			map.put("rEPLY_CONTENT", rEPLY_CONTENT);
			map.put("bOARD_NUM", bOARD_NUM);
			System.out.println("댓글 등록 컨트롤러 입니다."+map);
			int data = ReplyService.ReplyWrite(map);
			System.out.println("등록된 댓글 행 걋수 = "+data);
			
		}
	
}