package com.mindtree.companyapp.exception.service.custom;

import com.mindtree.companyapp.exception.service.CompanyAppServiceException;

public class CompanyNotFoundException extends CompanyAppServiceException {

	private static final long serialVersionUID = 3370784903221826906L;

	public CompanyNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public CompanyNotFoundException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public CompanyNotFoundException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public CompanyNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public CompanyNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
