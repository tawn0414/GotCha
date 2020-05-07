package dBServer.sale;

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

import dBServer.fileupload.FileUploadService;
import dataStruct.SalePictureVO;
import dataStruct.SaleVO;

@Controller
public class SaleController {
	@Autowired
	SaleService saleService;
	@Autowired
	FileUploadService fileService;
	
	//판매차량 정보 등록
	@RequestMapping(value = "/saleInsert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, String> saleInsert(HttpServletRequest request) {
		System.out.println("등록 완료되었습니다");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String userId = request.getParameter("userId");
		String carNumber = request.getParameter("carNumber");
		String brand = request.getParameter("brand");
		String model = request.getParameter("model");
		String fuel = request.getParameter("fuel");
		String transmission = request.getParameter("transmission");
		String color = request.getParameter("color");
		String year = request.getParameter("year");
		String cc = request.getParameter("cc");
		String km = request.getParameter("km");
		String sago = request.getParameter("sago");
		String price = request.getParameter("price");
		String salePredict = request.getParameter("salePredict");
		String saleExplain = request.getParameter("saleExplain");
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("carNumber", carNumber);
		map.put("brand", brand);
		map.put("model", model);
		map.put("fuel", fuel);
		map.put("transmission", transmission);
		map.put("color", color);
		map.put("year", year);
		map.put("cc", cc);
		map.put("km", km);
		map.put("sago", sago);
		map.put("price", price);
		map.put("salePredict", salePredict);
		map.put("saleExplain", saleExplain);
		System.out.println(map);
		saleService.SaleUpload(map);
		
		
		String file1 = request.getParameter("filename1");
		String file2 = request.getParameter("filename2");
		String file3 = request.getParameter("filename3");
		String file4 = request.getParameter("filename4");
		SalePictureVO vo = new SalePictureVO(file1,file2,file3,file4);
		System.out.println(vo);
		fileService.saleInsert(vo);
		return map;
	}
	
	//내가 올린 판매 차량 목록
	@RequestMapping(value = "/myList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<SaleVO> myList(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("내 판매 목록");
		String userId = request.getParameter("userId");
		System.out.println(userId);
		List<SaleVO> myList = saleService.myList(userId);
		return myList;
	}
	// 내가 올린 판매 차량 판매완료
		@RequestMapping(value = "/myListDelete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		@ResponseBody
		public int myListDelete(HttpServletRequest request) {
			System.out.println("판매 완료");
			try {
				request.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String carNumber = request.getParameter("carNumber");
			saleService.myListDelete(carNumber);
			return 1;
		}
}