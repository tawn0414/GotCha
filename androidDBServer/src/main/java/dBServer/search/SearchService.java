package dBServer.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dataStruct.CarVO;

@Service
public class SearchService {
	@Autowired
	@Qualifier("CarDAO")
	SearchDAO dao;
	
	public List<CarVO> modelSearch() {
		return dao.modelSearch();
	}
	
	
	public List<CarVO> modelSearch(String model) {
		System.out.println("service:"+model);
		return dao.modelSearch(model);
	}
	
	public List<CarVO> brandSearch(String brand) {
		
		System.out.println("service:"+brand);
		return dao.brandSearch(brand);
	}
	
	public List<CarVO> fuelSearch(String fuel) {
		
		System.out.println("service:"+fuel);
		return dao.fuelSearch(fuel);
	}
	
	public List<CarVO> kmSearch(String km) {
		
		System.out.println("service:"+km);
		return dao.kmSearch(km);
	}
	

	/*public List<CarVO> dynamicSearch(String tag) {
		
		System.out.println("service:"+tag);
		return dao.dynamicSearch(tag);
	}*/
}
