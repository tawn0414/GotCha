package dBServer.fileupload;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dataStruct.SalePictureVO;
@Repository("FileUploadDAO")
public class FileUploadDAO {
	@Autowired
	SqlSession sqlSession;
	
	public int saleInsert(SalePictureVO vo) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("picture1",vo.getPicture1());
		map.put("picture2",vo.getPicture2());
		map.put("picture3",vo.getPicture3());
		map.put("picture4",vo.getPicture4());
		return sqlSession.insert("file.saleInsert", map);
	}
	public SalePictureVO saleSelect(String salenum) {
		return sqlSession.selectOne("file.saleSelect", salenum);
	}
}