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
package com.abc.uan.blockchain;

/**
 * Title: AgreementPojo
 * @Description: AgreementPojo
 * @author Bo Liu
 * @date 2018-09-20
 */
public class AgreementPojo extends AbstractBlockChainPojo {

	private static final long serialVersionUID = 3681912757975870845L;

	private String[] personnelIdCards= {"None"};
	private String[] bindingCardNums= {"None"};
	private String party="None";

	public String[] getPersonnelIdCards() {
		return personnelIdCards;
	}

	public void setPersonnelIdCards(String[] personnelIdCards) {
		this.personnelIdCards = personnelIdCards;
	}

	public String[] getBindingCardNums() {
		return bindingCardNums;
	}

	public void setBindingCardNums(String[] bindingCardNums) {
		this.bindingCardNums = bindingCardNums;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

}
