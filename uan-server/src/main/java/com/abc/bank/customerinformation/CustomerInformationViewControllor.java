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
