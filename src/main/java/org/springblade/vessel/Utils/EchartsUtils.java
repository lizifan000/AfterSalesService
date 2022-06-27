package org.springblade.vessel.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

public class EchartsUtils {
	/**
	 * 获取图表横坐标
	 */
	public static String getAbscissa(int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -i);
		Date m = c.getTime();
		return sdf.format(m);
	}

	/**
	 * 获得该月第一天
	 * '@param' year
	 * '@param' month
	 * '@return'
	 */

	public static String getFirstDayOfMonth(int year,int month){
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR,year);
		//设置月份
		cal.set(Calendar.MONTH, month-1);
		//获取某月最小天数
		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最小天数
		cal.set(Calendar.DAY_OF_MONTH, firstDay);
		//格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(cal.getTime());
	}

	/**
	 * 获得该月最后一天
	 * '@param' year
	 * '@param' month
	 * '@return'
	 */
	public static String getLastDayOfMonth(int year,int month){
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR,year);
		//设置月份
		cal.set(Calendar.MONTH, month-1);
		//获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		//格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(cal.getTime());
	}

	/**
	 * 获得Date中的年份
	 * '@param' date
	 * '@return'
	 */
	public static Year getYearByDate(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return Year.of(c.get(Calendar.YEAR));
	}

	/**
	 * 获取五年前的date
	 * '@param' Null
	 * '@return' Date
	 */
	public static Date getFiveYearDate() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, -5);
		Date y = c.getTime();
		String year = format.format(y);
		System.out.println("过去五年："+year);
		return format.parse(year);
	}

	/**
	 * 获取当前date
	 * '@param' Null
	 * '@return' Date
	 */
	public static Date getCurrentDate() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String format1 = format.format(new Date());
		return format.parse(format1);
	}
}
