package com.jsp.common;

import java.time.LocalDateTime;

public class ApiResponse {
	private final boolean success;
	private final String message;
	public boolean isSuccess() {
		return success;
	}
	public String getMessage() {
		return message;
	}
	/**
	 * @param success
	 * @param message
	 */
	public ApiResponse(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}
	public String getTimestamp() {
		return LocalDateTime.now().toString();
	}	
}
