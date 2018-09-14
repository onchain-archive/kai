package com.abc.bank;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abc.bank.uancontract.UanContractService;
import com.abc.common.util.CfgValueHandler;
import com.abc.common.util.LogWriter;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectFacesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;

@Component
public class VisualRecognitionService {

	@Autowired
	private CfgValueHandler cfgValueHandler;
	private VisualRecognition visualRecognitionService = null;

	public boolean detectFace(byte[] faceImg) {
		if (visualRecognitionService == null) {
			visualRecognitionService = new VisualRecognition(cfgValueHandler.getVisualRecognitionVersion());
			// Set the endpoint
			visualRecognitionService.setEndPoint(cfgValueHandler.getVisualRecognitionEndPoint());
			IamOptions options = new IamOptions.Builder().apiKey(cfgValueHandler.getVisualRecognitionIamApiKey())
					.build();
			visualRecognitionService.setIamCredentials(options);
		}

		DetectFacesOptions detectFacesOptions;
		detectFacesOptions = new DetectFacesOptions.Builder().imagesFile(new ByteArrayInputStream(faceImg)).build();
		DetectedFaces result = visualRecognitionService.detectFaces(detectFacesOptions).execute();
		LogWriter.info(UanContractService.class, "人脸分类结果：{}", result);
		if (result.getImagesProcessed() != 1) {
			return false;
		} else {
			return true;
		}

	}
}
