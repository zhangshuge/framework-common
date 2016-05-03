package com.ccbtrust.common.log;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author ciyuan
 *
 */
public class ThreadValue {
	//定义线程局部变量
	@SuppressWarnings("rawtypes")
	private static ThreadLocal<Map> tlval = new ThreadLocal<Map>();
	static{
		tlval.set(new HashMap<Object, Object>());
	}
	/**
	 * 根据键获取值
	 * @param key
	 * @return
	 */
	public static String get(String key){
		if(null == tlval){
			tlval = new ThreadLocal<Map>();
		}
		Map<?,?> map = tlval.get();
		if(null == map){
			return null;
		}
		return (String)map.get(key);
	}
	/**
	 * 放入键值对
	 * @param key
	 * @param value
	 */
	@SuppressWarnings("rawtypes")
	public static void put(String key,String value){
		if (null == tlval) {
		    tlval = new ThreadLocal<Map>();
		}
		@SuppressWarnings("unchecked")
		Map<String, String> map = tlval.get();
		if (null == map) {
		    map = new HashMap<String, String>();
		    tlval.set(map);
		}
		map.put(key, value);
	}
}
