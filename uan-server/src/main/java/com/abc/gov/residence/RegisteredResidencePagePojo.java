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
package com.abc.gov.residence;

import com.abc.common.AbstractPojo;

/**
 * Title: RegisteredResidencePagePojo
 * @Description: RegisteredResidencePagePojo
 * @author Bo Liu
 * @date 2018-09-20
 */
public class RegisteredResidencePagePojo extends AbstractPojo {

	private static final long serialVersionUID = -6042221711060730595L;

	private String id;
	private RegisteredResidencePojo registeredResidence;
	private String idCard;
	private String name;
	private String telephone;
	private int relationshipWithHouseholder;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public RegisteredResidencePojo getRegisteredResidence() {
		return registeredResidence;
	}

	public void setRegisteredResidence(RegisteredResidencePojo registeredResidence) {
		this.registeredResidence = registeredResidence;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getRelationshipWithHouseholder() {
		return relationshipWithHouseholder;
	}

	public void setRelationshipWithHouseholder(int relationshipWithHouseholder) {
		this.relationshipWithHouseholder = relationshipWithHouseholder;
	}

}
