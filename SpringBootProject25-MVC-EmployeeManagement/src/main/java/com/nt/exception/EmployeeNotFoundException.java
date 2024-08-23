package com.nt.exception;

public class EmployeeNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmployeeNotFoundException() {
		super();
	}
	
	public EmployeeNotFoundException(String msg) {
		super(msg);
	}

}
