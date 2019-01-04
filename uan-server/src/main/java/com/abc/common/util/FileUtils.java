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
package com.abc.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

/**
 * Title: FileUtils
 * @Description: FileUtils
 * @author Bo Liu
 * @date 2018-09-20
 */
public class FileUtils {

	public static byte[] file2byte(String filePath) throws IOException {
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

	/** 
	 * @Description: file2Base64
	 * @param filePath
	 * @return String
	 * @throws IOException
	 */ 
	public static String file2Base64(String filePath) throws IOException {
		return Base64.encodeBase64String(file2byte(filePath));
	}

	/** 
	 * @Description: base642Byte
	 * @param base64String
	 * @return byte[]
	 */ 
	public static byte[] base642Byte(String base64String) {
		return Base64.decodeBase64(base64String);
	}

}
