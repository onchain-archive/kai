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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.abc.common.starter.UanApplication;

/**
 * Title: BlockChainTest
 * @Description: BlockChainTest
 * @author Bo Liu
 * @date 2018-09-20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UanApplication.class)
public class BlockChainTest {

	/** 
	 * @Description: testConnection
	 */ 
	@Test
	public void testConnection() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());

		String reqJson = "{ \"$class\": \"org.acme.mynetwork.Trade\",\r\n" + " \"serialnumber\": \"98765432100\",\r\n"
				+ " \"trType\": \"1\",\r\n" + " \"trDate\": \"20180829\",\r\n" + " \"trDetail\": \"testMoney\",\r\n"
				+ " \"reference\": \"12345\",\r\n" + " \"party\": \"1234\",\r\n" + " \"amt\": 100,\r\n"
				+ " \"account\": \"resource:org.acme.mynetwork.OrgAccount#6228480000001111234\",\r\n"
				+ " \"customer\": \"resource:org.acme.mynetwork.Member#362413198900000012\",\r\n"
				+ " \"transactionId\": \"\",\r\n" + " \"timestamp\": \"2018-08-29T10:23:17.565Z\"\r\n" + "} ";

		HttpEntity<String> httpEntity = new HttpEntity<String>(reqJson, headers); 
		String result = restTemplate.postForObject("http://192.168.0.110:3000/api/Trade", httpEntity, String.class);
		System.out.println(result);
	}

}
