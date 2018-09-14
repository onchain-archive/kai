package com.abc.bank.customerinformation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abc.common.AbstractController;

@Controller
@RequestMapping(value = AbstractController.VIEW_PREFIX)
public class CustomerInformationViewControllor extends AbstractController {

	@RequestMapping(value = "/XXX", method = RequestMethod.GET)
	public String forwardXXX() {
		return "xxx";
	}

}
