package com.ccbtrust.common.exception;
/**
 * 
 * 应用异常类，通过获取异常代码进行异常消息国际化处理
 * @author ciyuan
 *
 */
public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = -6308995076388326396L;
	/**
	 * 异常代码
	 */
	private String code;
	
	/**
	 * 异常消息参数 
	 */
	private Object[] args;

	/**
	 * 构造方法 使用异常代码
	 * @param code 异常代码
	 */
	public ApplicationException(String code) {
		this.code = code;
	}
	/**
	 * 构造方法 使用异常代码，异常目标
	 * @param code 异常代码 
	 * @param args 异常消息参数 
	 */
	public ApplicationException(String code, Object... args) {
		this.code = code;
		this.args = args;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @return the args
	 */
	public Object[] getArgs() {
		return args;
	}
}
