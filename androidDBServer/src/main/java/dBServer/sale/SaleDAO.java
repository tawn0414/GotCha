package dBServer.sale;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dataStruct.SaleVO;

@Repository("SaleDAO")
public class SaleDAO {
	@Autowired
	SqlSession sqlSession;
	
	public int SaleUpload(Map map) {
		return sqlSession.insert("sale.carInsert",map);
	}

	public List<SaleVO> myList(String userId) {
		return sqlSession.selectList("sale.mySaleList",userId);
	}
	public int myListDelete(String carNumber) {
		return sqlSession.delete("sale.mySaleDelete", carNumber);
	}
}
