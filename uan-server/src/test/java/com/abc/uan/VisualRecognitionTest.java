package com.abc.uan;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.abc.bank.VisualRecognitionService;
import com.abc.common.starter.UanApplication;
import com.abc.common.util.FileUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UanApplication.class)
public class VisualRecognitionTest {

	@Autowired
	private VisualRecognitionService visualRecognitionService;

	@Test
	public void testConnection() {
		byte[] faceImg = null;
		try {
			faceImg = FileUtils.file2byte("src/test/java/com/abc/uan/face.gif");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		System.out.println(visualRecognitionService.detectFace(faceImg));

	}

	@Test
	public void test1() {
		try {
			String path = this.getClass().getResource("").getPath() + "/face.jpg";
			System.out.println();
			System.out.println(FileUtils.file2Base64(path));
			System.out.println();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

}
