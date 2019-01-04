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

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.abc.common.Request;
import com.abc.common.Response;
import com.abc.common.starter.UanApplication;
import com.abc.common.util.JsonConvertor;
import com.abc.uan.union.UnionDAO;
import com.abc.uan.union.UnionPojo;
import com.abc.uan.union.UnionQuery;
import com.abc.uan.union.UnionServiceImpl;

/**
 * Title: UnionTest
 * @Description: UnionTest
 * @author Bo Liu
 * @date 2018-09-20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UanApplication.class)
public class UnionTest {

	@Autowired
	private UnionServiceImpl unionService;
	@Autowired
	private UnionDAO unionDAO;
	@Autowired
	private JsonConvertor jsonConvertor;

	/** 
	 * @Description: test
	 */ 
	@Test
	public void test() {
		unionDAO.delete("bhd");

		List<UnionPojo> unionPojos = unionService.findAll();
		System.out.println();
		System.out.println("全部联盟成员：");
		System.out.println(unionPojos);
		System.out.println();

		UnionQuery unionQuery = new UnionQuery();
		unionQuery.setCode("abc");
		unionQuery.setName("银行");
		List<UnionPojo> wherePojos = unionService.findByWhere(unionQuery);
		System.out.println();
		System.out.println("条件联盟成员：");
		System.out.println(wherePojos);
		System.out.println();

		UnionPojo unionPojo = new UnionPojo();
		unionPojo.setAddress("北京海淀苏家坨镇三星庄路");
		unionPojo.setCode("bhd");
		unionPojo.setName("稻香湖银行");
		unionPojo.setPhone("010-83438888");
		unionPojo.setType(UnionPojo.TYPE_BANK);
		unionService.create(unionPojo);

		System.out.println();
		System.out.println("创建成功");
		System.out.println();
		
		unionPojos = unionService.findAll();
		System.out.println();
		System.out.println("新的全部联盟成员：");
		System.out.println(unionPojos);
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
		resp.createSuccessJson(unionPojos, req);
		String respJson = jsonConvertor.toJson(resp);
		System.out.println();
		System.out.println("findAll应答报文：");
		System.out.println(respJson);
		System.out.println();

		req = new Request();
		req.setAppId("uan");
		req.setUserId("110110200001011234");
		req.setData(unionQuery);
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
		req.setData(unionPojo);
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
