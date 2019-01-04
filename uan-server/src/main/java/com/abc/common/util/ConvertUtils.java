/*
 * Copyright 2018 Liu Bo
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.abc.common.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

/**
 * 转换构造器，用于变量类型间的转换<br>
 * 提供方法toInt(),regexString(),toBoolean(),serialObject(),toXml(),fromXml()等方法
 * 
 * @author liubo
 */
public class ConvertUtils {
	/**
	 * 定义CHARACTER_NUMBER_ALL为不可再赋值的静态变量，值为字符串\\w\\S*
	 */
	public static final String CHARACTER_NUMBER_ALL = ".*\\w\\S*";
	public static final String CHARACTER_CHINESE_ALL = ".*([\u4E00-\u9FA5]|[\uFE30-\uFFA0]).*";

	public static final String FORMAT_DATE = "yyyy-MM-dd";
	public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss.S z";
	public static final String FORMAT_DATE_UI = "yyyy/MM/dd";
	public static final String FORMAT_DATETIME_UI = "yyyy/MM/dd HH:mm";
	public static final String[] FORMAT_PATTERNS = new String[] {
			FORMAT_DATETIME, FORMAT_DATE, FORMAT_DATE_UI, FORMAT_DATETIME_UI };

	/**
	 * 
	 * 
	 */
	public static Class<?> toClass(String str) {
		if ("string".equalsIgnoreCase(str)) {
			return String.class;
		}
		if ("double".equalsIgnoreCase(str)) {
			return Double.class;
		}
		if ("integer".equalsIgnoreCase(str)) {
			return Integer.class;
		}
		if ("float".equalsIgnoreCase(str)) {
			return Float.class;
		}
		if ("object".equalsIgnoreCase(str)) {
			return Object.class;
		}

		return String.class;
	}

	/**
	 * 
	 * 
	 */
	public static Object toValue(String str, Class<?> clz) {
		if (clz.equals(String.class)) {
			return str;
		}
		if (clz.equals(Double.class)) {
			return new Double(str);
		}
		if (clz.equals(Integer.class)) {
			return new Integer(str);
		}
		if (clz.equals(Float.class)) {
			return new Float(str);
		}

		return str;
	}

	/**
	 * 
	 * 
	 */
	public static Integer toInteger(int i) {
		return new Integer(i);
	}

	/**
	 * 
	 * 
	 */
	public static boolean isNumber(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * 
	 */
	public static int toInt(byte[] bytes) {
		return toInt(new String(bytes));
	}

	/**
	 * 静态方法,将String 转换为int 类型
	 * 
	 * @param str
	 *            要转换为 Integer 的 String
	 * @return 转换为 int 类型后该对象表示的数值
	 */
	public static int toInt(String str) {
		return new Integer(str).intValue();
	}

	/**
	 * 静态方法,toInt()方法重载将double 转换为int 类型
	 * 
	 * @param doub
	 *            用 Double 表示的值
	 * @return 该值被转换为 int 类型该对象表示的数值
	 */
	public static int toInt(double doub) {
		return new Double(doub).intValue();
	}

	/**
	 * 
	 * 
	 */
	public static Double toDouble(String d) {
		return Double.valueOf(d);
	}

	/**
	 * 
	 * 
	 */
	public static boolean regexString(String str) {
		if (str.trim().matches(CHARACTER_NUMBER_ALL)) {
			return true;
		} else  {
			return str.trim().matches(CHARACTER_CHINESE_ALL);
		}
	}

	/**
	 * 静态布尔方法,判断字符串是否匹配给定的正则表达式
	 * 
	 * @param str
	 *            需要判断的字符串
	 * @param regex
	 *            用来匹配字符串的正则表达式
	 * @return
	 */
	public static boolean regexString(String str, String regex) {
		return str.trim().matches(regex);
	}

	/**
	 * 静态布尔方法,将String 转换为基本 boolean 值
	 * 
	 * @param str
	 *            要转换的String
	 * @return false或对象的基本 boolean 值
	 */
	public static boolean toBoolean(String str) {
		if (str == null) {
			return false;
		}

		return new Boolean(str).booleanValue();
	}

	/**
	 * 将字符串数组转换为List
	 * 
	 * @param objs
	 *            对象数组
	 * @return List 返回转换后List
	 */
	public static List<String> toList(String[] strs) {
		List<String> retList = new ArrayList<String>();
		if (strs != null) {
			for (int i = 0; i < strs.length; i++) {
				retList.add(strs[i]);
			}
		}
		return retList;
	}

	/**
	 * 将Object转换为double
	 * 
	 * @param obj
	 * @return 返回Double
	 */
	public static double objectToDouble(Object obj) {
		return Double.parseDouble(String.valueOf(obj));
	}

	/**
	 * 将double转换为Double
	 * 
	 * @param value
	 * @return 返回Double
	 */
	public static Double toDouble(double value) {
		return new Double(value);
	}

	/**
	 * 将字符串数组转换为字符串,字符串之间用strConnect连接
	 * 
	 * @param value
	 *            字符串数组
	 * @param strconnect
	 *            连接串
	 * @return String
	 */
	public static String toString(String[] value, String strConnect) {
		StringBuilder buffer = new StringBuilder();
		for (String str : value) {
			buffer.append(str);
			if (strConnect != null) {
				buffer.append(strConnect);
			}
		}
		if ((strConnect != null) && (buffer.length() > 0)) {
			buffer.delete(buffer.length() - 1, buffer.length());
		}
		return buffer.toString();
	}

	/**
	 * 将set转成list
	 * 
	 * @param set
	 * @return
	 */
	public static List<?> toList(Set<?> set) {
		List<Object> list = new ArrayList<Object>();
		if (set == null) {
			return null;
		}
		Iterator<?> it = set.iterator();
		while (it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}

	/**
	 * 
	 * 
	 */
	public static String nullOfString(String str) {
		if (str == null) {
			str = "";
		}
		return str;
	}

	/**
	 * 
	 * 
	 */
	public static byte stringToByte(String str) throws NumberFormatException {
		byte b = 0;
		if (str != null) {
			b = Byte.parseByte(str);
		}
		return b;
	}

	/**
	 * 
	 * 
	 */
	public static boolean stringToBoolean(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		} else {
			if ("1".equals(str)) {
				return true;
			} else if ("0".equals(str)) {
				return false;
			} else {
				return Boolean.parseBoolean(str);
			}
		}
	}

	/**
	 * 
	 * 
	 */
	public static Boolean stringToBooleanObject(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		} else {
			if ("1".equals(str)) {
				return true;
			} else if ("0".equals(str)) {
				return false;
			} else {
				return Boolean.parseBoolean(str);
			}
		}
	}

	/**
	 * 
	 * 
	 */
	public static int stringToInt(String str) throws NumberFormatException {
		int i = 0;
		if (!StringUtils.isBlank(str)) {
			i = Integer.parseInt(str.trim());
		}
		return i;
	}

	/**
	 * 
	 * 
	 */
	public static Integer stringToIntegerObject(String str)
			throws NumberFormatException {
		Integer i = null;
		if (!StringUtils.isBlank(str)) {
			i = Integer.parseInt(str.trim());
		}
		return i;
	}

	/**
	 * 
	 * 
	 */
	public static short stringToShort(String str) throws NumberFormatException {
		short i = 0;
		if (!StringUtils.isBlank(str)) {
			i = Short.parseShort(str.trim());
		}
		return i;
	}

	/**
	 * 
	 * 
	 */
	public static Short stringToShortObject(String str)
			throws NumberFormatException {
		Short i = null;
		if (!StringUtils.isBlank(str)) {
			i = Short.parseShort(str.trim());
		}
		return i;
	}

	/**
	 * 
	 * 
	 */
	public static double stringToDouble(String str)
			throws NumberFormatException {
		double i = 0;
		if (!StringUtils.isBlank(str)) {
			i = Double.parseDouble(str.trim());
		}
		return i;
	}

	/**
	 * 
	 * 
	 */
	public static Double stringToDoubleObject(String str)
			throws NumberFormatException {
		Double d = null;
		if (!StringUtils.isBlank(str)) {
			d = Double.parseDouble(str.trim());
		}
		return d;
	}

	/**
	 * 
	 * 
	 */
	public static float stringToFloat(String str) throws NumberFormatException {
		float i = 0f;
		if (!StringUtils.isBlank(str)) {
			i = Float.parseFloat(str.trim());
		}
		return i;
	}

	/**
	 * 
	 * 
	 */
	public static Float stringToFloatObject(String str)
			throws NumberFormatException {
		Float f = null;
		if (!StringUtils.isBlank(str)) {
			f = Float.parseFloat(str.trim());
		}
		return f;
	}

	/**
	 * 
	 * 
	 */
	public static byte[] stringToByteArray(String str) {
		if (str != null) {
			try {
				return str.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 
	 */
	public static BigInteger stringToBigInteger(String str) {
		if (!StringUtils.isBlank(str)) {
			// 已改进，直接由String装换成BigDecimal，替换以前String-Double-BigDecimal的方法
			return new BigInteger(str.trim());
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 
	 */
	public static BigDecimal stringToBigDecimal(String str) {
		if (!StringUtils.isBlank(str)) {
			// 已改进，直接由String装换成BigDecimal，替换以前String-Double-BigDecimal的方法
			return new BigDecimal(str.trim());
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 
	 */
	public static long doubleToLong(double d) throws NumberFormatException {
		long lo = (long) 0;
		// double转换成long前要过滤掉double类型小数点后数据
		lo = Long.parseLong(String.valueOf(d).substring(0,
				String.valueOf(d).lastIndexOf(".")));
		return lo;
	}

	/**
	 * 
	 * 
	 */
	public static int doubleToInt(double d) throws NumberFormatException {
		int i = 0;
		// double转换成long前要过滤掉double类型小数点后数据
		i = Integer.parseInt(String.valueOf(d).substring(0,
				String.valueOf(d).lastIndexOf(".")));
		return i;
	}

	/**
	 * 
	 * 
	 */
	public static double longToDouble(long d) throws NumberFormatException {
		double lo = (double) 0;
		lo = Double.parseDouble(String.valueOf(d));
		return lo;
	}

	/**
	 * 
	 * 
	 */
	public static int longToInt(long d) throws NumberFormatException {
		int lo = 0;
		lo = Integer.parseInt(String.valueOf(d));
		return lo;
	}

	/**
	 * 
	 * 
	 */
	public static long stringToLong(String str) throws NumberFormatException {
		long li = (long) 0;
		if (!StringUtils.isBlank(str)) {
			li = Long.valueOf(str);
		}
		return li;
	}

	/**
	 * 
	 * 
	 */
	public static Long stringToLongObject(String str)
			throws NumberFormatException {
		Long li = null;
		if (!StringUtils.isBlank(str)) {
			li = Long.valueOf(str);
		}
		return li;
	}

	/**
	 * 日期转换成String
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date, String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}

	/**
	 * 日期转换成String
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		return dateToString(date, FORMAT_DATE);
	}

	/**
	 * 日期转换成String
	 * 
	 * @param date
	 * @return
	 */
	public static String timeToString(Date date) {
		return dateToString(date, FORMAT_DATETIME);
	}

	/**
	 * String字符串转换为Date
	 * 
	 * @throws ParseException
	 */
	public static Date stringToDate(String str, String[] patterns)
			throws ParseException {
		if (!StringUtils.isBlank(str)) {
			return DateUtils.parseDate(str, patterns);
		} else {
			return null;
		}
	}

	/**
	 * String字符串转换为Date
	 * 
	 * @throws ParseException
	 */
	public static Date stringToDate(String str) throws ParseException {
		return stringToDate(str, new String[] { FORMAT_DATE });
	}

	/**
	 * String字符串转换为Date
	 * 
	 * @throws ParseException
	 */
	public static Date stringToTime(String str) throws ParseException {
		return stringToDate(str, new String[] { FORMAT_DATETIME });
	}

	/**
	 * String转换character
	 */
	public static Character stringToCharacter(String str) {
		if (!StringUtils.isBlank(str)) {
			return Character.valueOf(str.charAt(0));
		} else {
			return null;
		}
	}

	/**
	 * character转换String
	 */
	public static String characterToString(Character character) {
		if (character != null) {
			return character.toString();
		} else {
			return null;
		}
	}

	/**
	 * boolean转化为String
	 */
	public static String booleanToString(Boolean boo) {
		if (boo != null) {
			return boo.toString();
		} else {
			return null;
		}
	}

	/**
	 * byte转化为String
	 */
	public static String byteToString(Byte bee) {
		if (bee != null) {
			return bee.toString();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 
	 */
	public static String byteArrayToString(byte[] bee)
			throws UnsupportedEncodingException {
		if (bee != null) {
			return new String(bee, "UTF-8");
		} else {
			return null;
		}
	}

	/**
	 * int转化为String
	 */
	public static String intToString(Integer i) {
		if (i != null) {
			return i.toString();
		} else {
			return null;
		}
	}

	/**
	 * double转化为String
	 */
	public static String doubleToString(Double doo) {
		if (doo != null) {
			return doo.toString();
		} else {
			return null;
		}
	}

	/**
	 * float转化为String
	 */
	public static String floatToString(Float foo) {
		if (foo != null) {
			return foo.toString();
		} else {
			return null;
		}
	}

	/**
	 * short转换String
	 */
	public static String shortToString(Short soo) {
		if (soo != null) {
			return soo.toString();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 
	 */
	public static String bigIntegerToString(BigInteger bint) {
		if (bint != null) {
			return String.valueOf(bint.toString());
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 
	 */
	public static String bigDecimalToString(BigDecimal dec) {
		if (dec != null) {
			return String.valueOf(dec.toString());
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 
	 */
	public static String longToString(Long li) {
		if (li != null) {
			return String.valueOf(li);
		} else {
			return null;
		}
	}

}