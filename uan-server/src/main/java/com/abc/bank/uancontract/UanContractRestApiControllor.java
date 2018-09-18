package com.abc.bank.uancontract;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abc.bank.bankcard.BankCardPojo;
import com.abc.common.AbstractController;
import com.abc.common.Request;
import com.abc.common.Response;
import com.abc.common.UanException;
import com.abc.common.util.FileUtils;

@RestController
@RequestMapping(value = AbstractController.REST_API_PREFIX + "/uancon")
public class UanContractRestApiControllor extends AbstractController {

	@Autowired
	private UanContractService uanContractService;

	@RequestMapping(value = "/contract", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String contract(@RequestBody String reqJson, HttpServletRequest req) {
		Request<UanContractPojo> request = Request.create(reqJson, req, UanContractPojo.class);
		UanContractPojo uanContractPojo = uanContractService.contract(request.getData());
		Response<UanContractPojo> resp = new Response<UanContractPojo>();
		return resp.createSuccessJson(uanContractPojo, request);
	}

	@RequestMapping(value = "/generate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String generate(@RequestBody String reqJson, HttpServletRequest req) {
		Request request = Request.create(reqJson, req, null);
		String str = uanContractService.generate();
		Response<String> resp = new Response<String>();
		return resp.createSuccessJson(str, request);
	}

	@RequestMapping(value = "/findMasters", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String findMasters(@RequestBody String reqJson, HttpServletRequest req) {
		Request<String> request = Request.create(reqJson, req, String.class);
		List<PersonnelRelationshipPojo> personnelRelationshipPojos = uanContractService
				.findMasterPojos(request.getData());
		Response<List> resp = new Response<List>();
		return resp.createSuccessJson(personnelRelationshipPojos, request);
	}

	@RequestMapping(value = "/findBindingCards", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String findBindingCards(@RequestBody String reqJson, HttpServletRequest req) {
		Request<String> request = Request.create(reqJson, req, String.class);
		List<BankCardPojo> bankCardPojos = uanContractService.findBindingCards(request.getData());
		Response<List> resp = new Response<List>();
		return resp.createSuccessJson(bankCardPojos, request);
	}

	@RequestMapping(value = "/mockFaceID")
	public String mockFaceID(HttpServletRequest req) {
		String faceidStr = null;
		try {
			faceidStr = FileUtils.file2Base64(this.getClass().getResource("").getPath() + "face.gif");
		} catch (IOException e) {
			throw new UanException("mock face id fail", e);
		}
		Response<String> resp = new Response<String>();
		return resp.createSuccessJson(faceidStr, null);
	}

}
