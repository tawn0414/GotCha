package dBServer.fileupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dataStruct.SalePictureVO;

@Service
public class FileUploadService {

	@Autowired
	@Qualifier("FileUploadDAO")
	FileUploadDAO dao;

	public int saleInsert(SalePictureVO vo) {
		return dao.saleInsert(vo);
	}

	public SalePictureVO saleSelect(String salenum) {
		return dao.saleSelect(salenum);
	}
}