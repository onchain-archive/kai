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

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.abc.common.Request;
import com.abc.common.Response;
import com.abc.common.starter.UanApplication;
import com.abc.common.util.ConvertUtils;
import com.abc.common.util.JsonConvertor;
import com.abc.uan.catastrophe.CatastrophePojo;
import com.abc.uan.catastrophe.CatastropheServiceImpl;

/**
 * Title: CatastropheTest
 * @Description: CatastropheTest
 * @author Bo Liu
 * @date 2018-09-20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UanApplication.class)
public class CatastropheTest {
	@Autowired
	private JsonConvertor jsonConvertor;
	@Autowired
	private CatastropheServiceImpl catastropheService;

	/** 
	 * @Description: test
	 * @throws ParseException
	 */ 
	@Test
	public void test() throws ParseException {
		List<CatastrophePojo> catastrophePojos = catastropheService.findAll();
		System.out.println();
		System.out.println("全部巨灾：");
		System.out.println(catastrophePojos);
		System.out.println();

		List<CatastrophePojo> wherePojos = catastropheService.findByWhere("北海道");
		System.out.println();
		System.out.println("条件巨灾：");
		System.out.println(wherePojos);
		System.out.println();

		CatastrophePojo catastrophePojo = new CatastrophePojo();
		catastrophePojo.setAreas("+81 043");
		catastrophePojo.setBegin(ConvertUtils.stringToDate("2018-09-01"));
		catastrophePojo.setEnd(ConvertUtils.stringToDate("2018-10-01"));
		catastrophePojo.setName("北海道地址" + ConvertUtils.timeToString(new Date()));
		catastropheService.create(catastrophePojo);

		System.out.println();
		System.out.println("创建成功");
		System.out.println();

		catastrophePojos = catastropheService.findAll();
		System.out.println();
		System.out.println("新的全部巨灾：");
		System.out.println(catastrophePojos);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
		System.out.println();

		Request req = new Request();
		req.setAppId("uan");
		req.setUserId("110110200001011234");
		String reqJson = jsonConvertor.toJson(req);
		System.out.println();
		System.out.println("findAll请求报文：");
		System.out.println(reqJson);
		System.out.println();
		// 使用上面的findAll
		Response resp = new Response();
		resp.createSuccessJson(catastrophePojos, req);
		String respJson = jsonConvertor.toJson(resp);
		System.out.println();
		System.out.println("findAll应答报文：");
		System.out.println(respJson);
		System.out.println();

		req = new Request();
		req.setAppId("uan");
		req.setUserId("110110200001011234");
		req.setData("北海道");
		reqJson = jsonConvertor.toJson(req);
		System.out.println();
		System.out.println("findByWhere请求报文：");
		System.out.println(reqJson);
		System.out.println();
		// 使用上面的findByWhere
		resp = new Response();
		resp.createSuccessJson(wherePojos, req);
		respJson = jsonConvertor.toJson(resp);
		System.out.println();
		System.out.println("findByWhere应答报文：");
		System.out.println(respJson);
		System.out.println();

		req = new Request();
		req.setAppId("uan");
		req.setUserId("110110200001011234");
		req.setData(catastrophePojo);
		reqJson = jsonConvertor.toJson(req);
		System.out.println();
		System.out.println("create请求报文：");
		System.out.println(reqJson);
		System.out.println();
		// 使用上面的create
		resp = new Response();
		resp.createSuccessJson(null, req);
		respJson = jsonConvertor.toJson(resp);
		System.out.println();
		System.out.println("create应答报文：");
		System.out.println(respJson);
		System.out.println();
	}
}
