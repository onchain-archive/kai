/*
 * Copyright 2018 Liu Bo
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
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

/**
 * Title: VisualRecognitionTest
 * @Description: VisualRecognitionTest
 * @author Bo Liu
 * @date 2018-09-20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UanApplication.class)
public class VisualRecognitionTest {

	@Autowired
	private VisualRecognitionService visualRecognitionService;

	/** 
	 * @Description: testConnection
	 */ 
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

	/** 
	 * @Description: test1
	 */ 
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
