/**
 * 
 */
package com.vouov.exception;

/**
 * App 抛出的异常类，主要区别于系统的错误
 * @author Minglong.Yu
 * @version 1.0 
 * @created Jun 12, 2011 6:25:31 PM
 */
public class SVException extends Exception {
	private static final long serialVersionUID = -79488707061903642L;
	private int code;

	public SVException(int code, String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
		this.code = code;
	}

	public SVException(int code, String detailMessage) {
		super(detailMessage);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
