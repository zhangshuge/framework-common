package com.ccbtrust.common.utils;
/**
 * 系统常量类
 * @author ciyuan
 *
 */
public class Constants {
	
	public static enum DatePatterns{
		Date("yyyy-MM-dd"), Time("HH:mm:ss.SSSZ"), DateTime("yyyy-MM-dd HH:mm:ss.SSSZ");
		private String pattern;
		/**
		 * 私有构造方法
		 * 
		 * @param pattern 日期模式字符串
		 */
		private DatePatterns(String pattern) {
			this.pattern = pattern;
		}
		/**
		 * 获取日期模式字符串
		 * 
		 * @return 返回日期格式模式字符串
		 */
		public String getPattern() {
			return pattern;
		}
	}
}
