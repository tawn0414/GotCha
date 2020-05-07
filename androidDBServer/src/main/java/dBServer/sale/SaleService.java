package dBServer.sale;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dataStruct.SaleVO;

@Service
public class SaleService {
	@Autowired
	@Qualifier("SaleDAO")
	SaleDAO dao;
	
	public int SaleUpload(Map map) {
		return dao.SaleUpload(map);
	}
	public List<SaleVO> myList(String userId) {
		return dao.myList(userId);
	}
	public int myListDelete(String carNumber) {
		return dao.myListDelete(carNumber);
	}
}