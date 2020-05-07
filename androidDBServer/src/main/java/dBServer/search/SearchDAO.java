package dBServer.search;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dataStruct.CarVO;
import dataStruct.MemberVO;

@Repository("CarDAO")
public class SearchDAO {
	@Autowired
	SqlSession sqlSession;
	
	
	public List<CarVO> modelSearch() {
		List<CarVO> carList = sqlSession.selectList("car.modelSearch");
		return carList;
	}
	
	public List<CarVO> modelSearch(String model) {
		List<CarVO> carList = sqlSession.selectList("car.modelSearch",model);
		System.out.println("dao:"+carList);
		return carList;
	}
	
	public List<CarVO> brandSearch(String brand) {
		List<CarVO> carList = sqlSession.selectList("car.brandSearch",brand);
		System.out.println("dao:"+carList);
		return carList;
	}
	
	public List<CarVO> fuelSearch(String fuel) {
		List<CarVO> carList = sqlSession.selectList("car.fuelSearch",fuel);
		System.out.println("dao:"+carList);
		return carList;
	}
	
	public List<CarVO> kmSearch(String km) {
		List<CarVO> carList = sqlSession.selectList("car.kmSearch",km);
		System.out.println("dao:"+carList);
		return carList;
	}
	
/*	public List<CarVO> dynamicSearch(String tag) {
		List<CarVO> carList = sqlSession.selectList("car.dynamicSearch",tag);
		System.out.println("dao:"+carList);
		return carList;
	}*/
	
}
