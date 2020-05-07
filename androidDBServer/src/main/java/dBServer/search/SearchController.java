package dBServer.search;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dataStruct.CarVO;

@Controller
public class SearchController {
	@Autowired
	SearchService carService;

	@RequestMapping(value = "/modelSearch", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CarVO> modelSearch(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String result = "";
		String model = request.getParameter("model");
		List<CarVO> carList = carService.modelSearch(model);
		System.out.println("ajax통신" + carList);
		System.out.println("ajax통신" + model);
		return carList;
	}
	
	
	@RequestMapping(value = "/brandSearch", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CarVO> brandSearch(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String result = "";
		String brand = request.getParameter("brand");
		List<CarVO> carList = carService.brandSearch(brand);
		System.out.println("ajax통신" + carList);
		System.out.println("ajax통신" + brand);
		return carList;
	}
	
	@RequestMapping(value = "/fuelSearch", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CarVO> fuelSearch(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String result = "";
		String fuel = request.getParameter("fuel");
		List<CarVO> carList = carService.fuelSearch(fuel);
		System.out.println("ajax통신" + carList);
		System.out.println("ajax통신" + fuel);
		return carList;
	}
	
	@RequestMapping(value = "/kmSearch", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CarVO> kmSearch(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String result = "";
		String km = request.getParameter("km");
		List<CarVO> carList = carService.kmSearch(km);
		System.out.println("ajax통신" + carList);
		System.out.println("ajax통신" + km);
		return carList;
	}
	
	/*@RequestMapping(value = "/dynamicSearch", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<CarVO> dynamicSearch(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String result = "";
		String tag = request.getParameter("tag");
		List<CarVO> carList = carService.dynamicSearch(tag);
		System.out.println("ajax통신" + carList);
		System.out.println("ajax통신" + tag);
		return carList;
	
	}*/
}
