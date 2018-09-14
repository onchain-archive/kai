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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UanApplication.class)
public class BlockChainTest {

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
