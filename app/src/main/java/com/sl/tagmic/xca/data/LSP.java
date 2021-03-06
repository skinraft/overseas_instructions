package com.sl.tagmic.xca.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @类名:MySharepreferences
 * @功能描述:Sharepreferences
 * @作者:XuanKe'Huang
 * @时间:2014-10-13 下午6:34:24
 * @Copyright 2014
 */
public class LSP {
	/**
	 * 方法名: getString
	 * 
	 * 功能描述:得到字符串
	 * 
	 * @param fileName
	 *            文件名
	 * @param key
	 *            key
	 * @return String 结果.如不存在,返回null
	 * 
	 *         </br>throws
	 */
	public static String getString(Context context, String fileName, String key) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return preferences.getString(key, "");
	}

	/**
	 * 方法名: getInt
	 * 
	 * 功能描述:得到Int
	 * 
	 * @param fileName
	 *            文件名
	 * @param key
	 *            key
	 * @return int 结果.如不存在,返回-0x10086
	 * 
	 *         </br>throws
	 */
	public static int getInt(Context context, String fileName, String key) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return preferences.getInt(key, 0);
	}

	/**
	 * 方法名: getLong
	 * 
	 * 功能描述:得到long
	 * 
	 * @param fileName
	 *            文件名
	 * @param key
	 *            key
	 * @return long 结果.如不存在,返回-0x10086
	 * 
	 *         </br>throws
	 */
	public static long getLong(Context context, String fileName, String key) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return preferences.getLong(key, 0);
	}

	/**
	 * 方法名: getFloat
	 * 
	 * 功能描述:得到float
	 * 
	 * @param fileName
	 *            文件名
	 * @param key
	 *            key
	 * @return long 结果.如不存在,返回-10086.0f
	 * 
	 *         </br>throws
	 */
	public static float getFloat(Context context, String fileName, String key) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return preferences.getFloat(key, -0.0f);
	}

	/**
	 * 方法名: getBoolean
	 * 
	 * 功能描述:得到boolean
	 * 
	 * @param fileName
	 *            文件名
	 * @param key
	 *            key
	 * @return long 结果.true or false.默认返回false
	 * 
	 *         </br>throws
	 */
	public static boolean getBoolean(Context context, String fileName,
				   String key) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return preferences.getBoolean(key, false);
	}

	/**
	 * 方法名: getBoolean
	 * 
	 * 功能描述:得到boolean
	 * 
	 * @param context
	 *            上下文对象
	 * @param fileName
	 *            文件名
	 * @param key
	 *            key
	 * @param flag
	 *            默认值
	 * @return boolean long 结果.true or false.
	 * 
	 *         </br>throws
	 */
	public static boolean getBoolean(Context context, String fileName,
				   String key, boolean flag) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return preferences.getBoolean(key, flag);
	}

	/**
	 * 方法名: putString
	 * 
	 * 功能描述:存放String
	 * 
	 * @param context
	 *            上下文对象
	 * @param fileName
	 *            文件名
	 * @param key
	 *            key
	 * @param value
	 *            存放的内容
	 * @return void
	 * 
	 *         </br>throws
	 */
	public static void putString(Context context, String fileName, String key,
			         String value) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * 方法名: putInt
	 * 
	 * 功能描述:存放Int
	 * 
	 * @param context
	 *            上下文对象
	 * @param fileName
	 *            文件名
	 * @param key
	 *            key
	 * @param value
	 *            存放的内容
	 * @return void
	 * 
	 *         </br>throws
	 */
	public static void putInt(Context context, String fileName, String key,
			      int value) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	/**
	 * 方法名: putLong
	 * 
	 * 功能描述:存放long
	 * 
	 * @param context
	 *            上下文对象
	 * @param fileName
	 *            文件名
	 * @param key
	 *            key
	 * @param value
	 *            存放的内容
	 * @return void
	 * 
	 *         </br>throws
	 */
	public static void putLong(Context context, String fileName, String key,
			       long value) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	/**
	 * 方法名: putFloat
	 * 
	 * 功能描述:存放long
	 * 
	 * @param context
	 *            上下文对象
	 * @param fileName
	 *            文件名
	 * @param key
	 *            key
	 * @param value
	 *            存放的内容
	 * @return void
	 * 
	 *         </br>throws
	 */
	public static void putFloat(Context context, String fileName, String key,
			        float value) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putFloat(key, value);
		editor.commit();
	}

	/**
	 * 方法名: putBoolean
	 * 
	 * 功能描述:存放Boolean
	 * 
	 * @param context
	 *            上下文对象
	 * @param fileName
	 *            文件名
	 * @param key
	 *            key
	 * @param value
	 *            存放的内容
	 * @return void
	 * 
	 *         </br>throws
	 */
	public static void putBoolean(Context context, String fileName, String key,
				boolean value) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * 
	 * @param context
	 * @param fileName
	 * @param key
	 * @return
	 */
	public static boolean contains(Context context, String fileName, String key) {
		SharedPreferences preferences = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return preferences.contains(key);
	}
}
