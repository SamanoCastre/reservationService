package com.hopital.reservation.exceptions;

public class ResourceNotInsertedException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotInsertedException(String message) {
		super(message);
	}
}
