package com.abc.uan;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.abc.bank.VisualRecognitionService;
import com.abc.common.starter.UanApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UanApplication.class)
public class VisualRecognitionTest {

	@Autowired
	private VisualRecognitionService visualRecognitionService;

	@Test
	public void testConnection() {
		byte[] faceImg = null;
		try {
			faceImg = file2byte("src/test/java/com/abc/uan/face.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

		System.out.println(visualRecognitionService.detectFace(faceImg));

	}

	private byte[] file2byte(String filePath) throws IOException {
		byte[] buffer = null;

		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int n;
		while ((n = fis.read(b)) != -1) {
			bos.write(b, 0, n);
		}
		fis.close();
		bos.close();
		buffer = bos.toByteArray();

		return buffer;
	}

}
