package com.abc.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

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

	public static String file2Base64(String filePath) throws IOException {
		return Base64.encodeBase64String(file2byte(filePath));
	}

	public static byte[] base642Byte(String base64String) {
		return Base64.decodeBase64(base64String);
	}

}
