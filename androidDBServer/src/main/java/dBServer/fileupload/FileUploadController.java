package dBServer.fileupload;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileUploadController {
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, String> fileUpload(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String filename1 = request.getParameter("filename1");
		String image1 = request.getParameter("file1");
		String filename2 = request.getParameter("filename2");
		String image2 = request.getParameter("file2");
		String filename3 = request.getParameter("filename3");
		String image3 = request.getParameter("file3");
		String filename4 = request.getParameter("filename4");
		String image4 = request.getParameter("file4");
		String path = "/WEB-INF/images";
		String uploadPath = request.getServletContext().getRealPath(path);
		byte[] bytearr1 = binaryStringToByteArray(image1);
		byte[] bytearr2 = binaryStringToByteArray(image2);
		byte[] bytearr3 = binaryStringToByteArray(image3);
		byte[] bytearr4 = binaryStringToByteArray(image4);
		try {
			File file1 = new File(uploadPath + "/" + filename1);
			OutputStream output1 = new FileOutputStream(uploadPath + "/" + filename1);
			output1.write(bytearr1);
			output1.close();

			File file2 = new File(uploadPath + "/" + filename2);
			OutputStream output2 = new FileOutputStream(uploadPath + "/" + filename2);
			output2.write(bytearr2);
			output2.close();

			File file3 = new File(uploadPath + "/" + filename3);
			OutputStream output3 = new FileOutputStream(uploadPath + "/" + filename3);
			output3.write(bytearr3);
			output3.close();

			File file4 = new File(uploadPath + "/" + filename4);
			OutputStream output4 = new FileOutputStream(uploadPath + "/" + filename4);
			output4.write(bytearr4);
			output4.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		Map<String, String> map = new HashMap<String, String>();
		return map;
	}

	@RequestMapping(value = "/fileCommUpload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, String> fileCommUpload(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String path = "/WEB-INF/images";
		String uploadPath = request.getServletContext().getRealPath(path);

		int length = Integer.parseInt(request.getParameter("length"));
		System.out.println("length " + length);

		for (int i = 0; i < length; i++) {
			String filename = request.getParameter("filename"+i);
			System.out.println("filename " + filename);
			String image = request.getParameter("file"+i);
			System.out.println("image " + image.substring(0,20));
			byte[] bytearr = binaryStringToByteArray(image);
			try {
				File file = new File(uploadPath + "/" + filename);
				OutputStream output = new FileOutputStream(uploadPath + "/" + filename);
				output.write(bytearr);
				output.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		return map;
	}

	public byte[] binaryStringToByteArray(String s) {
		int count = s.length() / 8;
		byte[] b = new byte[count];
		for (int i = 1; i < count; ++i) {
			String t = s.substring((i - 1) * 8, i * 8);
			b[i - 1] = binaryStringToByte(t);
		}
		return b;
	}

	public byte binaryStringToByte(String s) {
		byte ret = 0, total = 0;
		for (int i = 0; i < 8; ++i) {
			ret = (s.charAt(7 - i) == '1') ? (byte) (1 << i) : 0;
			total = (byte) (ret | total);
		}
		return total;
	}
}