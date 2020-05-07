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
	
	/* �Խ��� ��ȸ	*/
	@RequestMapping(value = "/CommunityList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CommunityVO> CommunityList(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
			
			List<CommunityVO> list = CommunityService.CommunityList();
			System.out.println("��ü ��ũ�� ��ȸ == "+list);
			return list;
	}
	/*�������� �Խ��� ��ȸ*/
	@RequestMapping(value = "/CommunityListByTitle", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CommunityVO> CommunityListByTitle(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
			System.out.println("�������� ��ȸ ����");
			String BOARD_TITLE = request.getParameter("board_Title");
			List<CommunityVO> list = CommunityService.SelectCommunityByTitle(BOARD_TITLE);
			System.out.println("�������� ��ȸ�� ����Ʈ"+list);
			return list;
	}
	/*�������� �Խ��� ��ȸ */
	
	@RequestMapping(value = "/CommunityListByContent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CommunityVO> CommunityListByContent(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
			System.out.println("�������� ��ȸ ����");
			String BOARD_CONTENT = request.getParameter("board_Content");
			List<CommunityVO> list = CommunityService.SelectCommunityByContent(BOARD_CONTENT);
			System.out.println("�������� ��ȸ�� ����Ʈ"+list);
			return list;
	}
	/*�ۼ��ڷ� �Խ��� ��ȸ*/
	@RequestMapping(value = "/CommunityListByNickname", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CommunityVO> CommunityListByNickname(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("�������� ��ȸ ����");
		String MEM_NICKNAME = request.getParameter("mem_Nickname");
		List<CommunityVO> list = CommunityService.SelectCommunityByNickname(MEM_NICKNAME);
		System.out.println("�������� ��ȸ�� ����Ʈ"+list);
		return list;
	}
	/* �Խ��� ���� ��ũ�� ��ȸ	*/
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
		System.out.println("���� ��ũ�� ��"+map);
		List<CommunityVO> list = CommunityService.CommunityNext(map);
		System.out.println("���� ��ũ�� sql���"+list);
		return list;

	}
/* �Խñ� ��� */
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
		System.out.println("�Խñ� ��� ��Ʈ�ѷ� �Դϴ�."+map);
		CommunityService.CommunityWrite(map);
		
	}
	/* �Խñ� ����, ��� ������ ���̵ǵ��� */
	@RequestMapping(value = "/CommunityDelete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void CommunityDelete(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("������ �����մϴ�.");
		String BOARD_NUM = request.getParameter("board_NUM");
		int delReplynum = ReplyService.ReplyDelete(BOARD_NUM);
		int delCommunitynum = CommunityService.CommunityDelete(BOARD_NUM);
		System.out.println("������ �� ����, ������ �� ���� = "+delCommunitynum+","+delReplynum);
		
	}
	/* ��ȸ�� ���� */
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
	
	/*���⼭ ���ʹ� ��� ��Ʈ�ѷ�*/
	//��� ��ȸ
	@RequestMapping(value = "/ReplyList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<ReplyVO> ReplyList(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("��� ��ȸ ����");
		String BOARD_NUM = request.getParameter("board_NUM");
		List<ReplyVO> list = ReplyService.ReplyList(BOARD_NUM);
		System.out.println("��ȸ�� ��� = "+list);
		return list;
	}
	//��� ���
		@RequestMapping(value = "/ReplyWrite", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		@ResponseBody
		public void ReplyWrite(HttpServletRequest request) {
			try {
				request.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			System.out.println("����� ����ϱ� �����մϴ�.");
			String MEM_NICKNAME = request.getParameter("mem_NICKNAME");
			String rEPLY_CONTENT = request.getParameter("reply_CONTENT");
			String bOARD_NUM = request.getParameter("board_NUM");
			Map<String, String> map = new HashMap<String, String>();
			map.put("mEM_NICKNAME", MEM_NICKNAME);
			map.put("rEPLY_CONTENT", rEPLY_CONTENT);
			map.put("bOARD_NUM", bOARD_NUM);
			System.out.println("��� ��� ��Ʈ�ѷ� �Դϴ�."+map);
			int data = ReplyService.ReplyWrite(map);
			System.out.println("��ϵ� ��� �� ���� = "+data);
			
		}
	
}