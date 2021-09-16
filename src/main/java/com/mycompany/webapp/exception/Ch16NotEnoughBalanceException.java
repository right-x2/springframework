package com.mycompany.webapp.exception;

public class Ch16NotEnoughBalanceException extends RuntimeException{
	
	public Ch16NotEnoughBalanceException() {
		
	}
	
	public Ch16NotEnoughBalanceException(String message) {
		super(message);
	}
}
