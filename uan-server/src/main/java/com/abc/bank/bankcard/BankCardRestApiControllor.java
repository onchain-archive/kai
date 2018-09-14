package com.abc.bank.bankcard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abc.common.AbstractController;
import com.abc.common.Request;
import com.abc.common.Response;

@RestController
@RequestMapping(value = AbstractController.REST_API_PREFIX + "/bancar")
public class BankCardRestApiControllor extends AbstractController {

	@Autowired
	private BankCardService bankCardService;

	@RequestMapping(value = "/findMyCard", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String findMyCard(@RequestBody String reqJson, HttpServletRequest req) {
		Request request = Request.create(reqJson, req, null);
		List<BankCardPojo> bankCardPojos = bankCardService.findMyCard();
		Response<List> resp = new Response<List>();
		return resp.createSuccessJson(bankCardPojos, request);
	}

}
