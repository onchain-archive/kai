/**  
 * Title: CustomerInformationViewControllor.java
 * Description: CustomerInformationViewControllor
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.bank.customerinformation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abc.common.AbstractController;

/**
 * Title: CustomerInformationViewControllor
 * @Description: CustomerInformationViewControllor
 * @author Bo Liu
 * @date 2018-09-20
*/
@Controller
@RequestMapping(value = AbstractController.VIEW_PREFIX)
public class CustomerInformationViewControllor extends AbstractController {

	/** 
	 * @Description: forwardXXX
	 * @return  String
	 * @throws 
	 */ 
	@RequestMapping(value = "/XXX", method = RequestMethod.GET)
	public String forwardXXX() {
		return "xxx";
	}

}
