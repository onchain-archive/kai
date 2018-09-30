/**  
 * Title: CustomerInformationRestApiControllor.java
 * Description: CustomerInformationRestApiControllor
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.bank.customerinformation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.abc.gov.residence.RegisteredResidencePojo;

/**
 * Title: CustomerInformationRestApiControllor
 * @Description: CustomerInformationRestApiControllor
 * @author Bo Liu
 * @date 2018-09-20
*/
@RestController
@RequestMapping(value = AbstractController.REST_API_PREFIX + "/cusinf")
public class CustomerInformationRestApiControllor extends AbstractController {

	@Autowired
	private CustomerInformationServiceImpl customerInformationService;

	/** 
	 * @Description: confirmCustomer
	 * @param reqJson
	 * @param req
	 * @return  String
	 * @throws 
	 */ 
	@RequestMapping(value = "/confirmCustomer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String confirmCustomer(@RequestBody String reqJson, HttpServletRequest req) {
		Request<String> request = Request.create(reqJson, req, String.class);
		CustomerInformationPojo customerInformationPojo = customerInformationService.confirmCustomer(request.getData());
		Response<CustomerInformationPojo> resp = new Response<CustomerInformationPojo>();
		return resp.createSuccessJson(customerInformationPojo.toString(), request);
	}

	/** 
	 * @Description: login
	 * @param reqJson
	 * @param req
	 * @return  String
	 * @throws 
	 */ 
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String login(@RequestBody String reqJson, HttpServletRequest req) {
		Request<CustomerInformationPojo> request = Request.create(reqJson, req, CustomerInformationPojo.class);
		customerInformationService.checkPwd(request.getData());
		Response<CustomerInformationPojo> resp = new Response<CustomerInformationPojo>();
		return resp.createSuccessJson(null, request);
	}

	/** 
	 * @Description: findSuccessionRelations
	 * @param reqJson
	 * @param req
	 * @return  String
	 * @throws 
	 */ 
	@RequestMapping(value = "/findSuccessionRelations", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String findSuccessionRelations(@RequestBody String reqJson, HttpServletRequest req) {
		Request<CustomerInformationPojo> request = Request.create(reqJson, req, CustomerInformationPojo.class);
		RegisteredResidencePojo registeredResidencePojo = customerInformationService
				.findSuccessionRelations(request.getData());
		Response<RegisteredResidencePojo> resp = new Response<RegisteredResidencePojo>();
		return resp.createSuccessJson(registeredResidencePojo, request);
	}

	/** 
	 * @Description: faceID
	 * @param reqJson
	 * @param req
	 * @return  String
	 * @throws 
	 */ 
	@RequestMapping(value = "/faceID", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String faceID(@RequestBody String reqJson, HttpServletRequest req) {
		Request<FaceIDPojo> request = Request.create(reqJson, req, FaceIDPojo.class);
		CustomerInformationPojo customerInformationPojo = customerInformationService.faceID(request.getData());
		Response<CustomerInformationPojo> resp = new Response<CustomerInformationPojo>();
		return resp.createSuccessJson(customerInformationPojo, request);
	}

	/** 
	 * @Description: withdraw
	 * @param reqJson
	 * @param req
	 * @return  String
	 * @throws 
	 */ 
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String withdraw(@RequestBody String reqJson, HttpServletRequest req) {
		Request<WithdrawCommondPojo> request = Request.create(reqJson, req, WithdrawCommondPojo.class);
		UanTrResultPojo uanTrResultPojo = customerInformationService.withdraw(request.getData());
		Response<UanTrResultPojo> resp = new Response<UanTrResultPojo>();
		return resp.createSuccessJson(uanTrResultPojo, request);
	}

	/** 
	 * @Description: reportLoss
	 * @param reqJson
	 * @param req
	 * @return  String
	 * @throws 
	 */ 
	@RequestMapping(value = "/reportLoss", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String reportLoss(@RequestBody String reqJson, HttpServletRequest req) {
		Request<ReportLossCommondPojo> request = Request.create(reqJson, req, ReportLossCommondPojo.class);
		UanTrResultPojo uanTrResultPojo = customerInformationService.reportLoss(request.getData());
		Response<UanTrResultPojo> resp = new Response<UanTrResultPojo>();
		return resp.createSuccessJson(uanTrResultPojo, request);
	}

	/** 
	 * @Description: inquireAssets
	 * @param reqJson
	 * @param req
	 * @return  String
	 * @throws 
	 */ 
	@RequestMapping(value = "/inquireAssets", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String inquireAssets(@RequestBody String reqJson, HttpServletRequest req) {
		Request<InquireAssetsCommondPojo> request = Request.create(reqJson, req, InquireAssetsCommondPojo.class);
		List<BankCardPojo> bankCardPojos = customerInformationService.inquireAssets(request.getData());
		Response resp = new Response();
		return resp.createSuccessJson(bankCardPojos, request);
	}
}
