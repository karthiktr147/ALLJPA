package com.mindtree.companyapp.exception.service.custom;

import com.mindtree.companyapp.exception.service.CompanyAppServiceException;

public class CompanyAlreadyExistsException extends CompanyAppServiceException {

	private static final long serialVersionUID = -4099776043349481060L;

	public CompanyAlreadyExistsException() {
		// TODO Auto-generated constructor stub
	}

	public CompanyAlreadyExistsException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public CompanyAlreadyExistsException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public CompanyAlreadyExistsException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public CompanyAlreadyExistsException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
