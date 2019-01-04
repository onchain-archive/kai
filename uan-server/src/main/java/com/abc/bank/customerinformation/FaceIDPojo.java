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
package com.abc.bank.customerinformation;

import com.abc.common.AbstractPojo;

public class FaceIDPojo extends AbstractPojo {
	
	private static final long serialVersionUID = 7920486993575575478L;
	private String idCard;
	private String faceBytes;

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getFaceBytes() {
		return faceBytes;
	}

	public void setFaceBytes(String faceBytes) {
		this.faceBytes = faceBytes;
	}

}
