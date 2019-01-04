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
package com.abc.common;

/**
 * Title: RejectedAccessException
 * @Description: RejectedAccessException
 * @author Bo Liu
 * @date 2018-09-20
*/
public class RejectedAccessException extends UanException {

	private static final long serialVersionUID = 4894033051527443132L;

	
	public RejectedAccessException() {
		super();
	}

	public RejectedAccessException(String msg) {
		super(msg);
	}

	public RejectedAccessException(Throwable cause) {
		super(cause);
	}

	public RejectedAccessException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
