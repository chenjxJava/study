package com.yl.util.base;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.yl.entity.vo.base.ResultStatistical;
/**时间工具类**/
public class DateConvertUtils {
    public static final String allTime = "yyyy-MM-dd HH:mm:ss";
    public static final String allDate = "yyyy-MM-dd";
    public static final String allDateMonth = "yyyy-MM";
    public static final String year = "yyyy";
    
	/**
	 * 日期型格式化成字符型
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date,String format){
		SimpleDateFormat formatter = null;
		if(date == null){
			return null;
		}else if(StringUtils.isEmpty(format)||format.length()<5){
			formatter = new SimpleDateFormat("yyyy-MM-dd");
		}else{
			formatter = new SimpleDateFormat(format);
		}
		return formatter.format(date);
	}
	/**
     * 日期型格式化成字符型
	 * @param date
	 * @return
	 */
	public static String format(Date date){
	    return format(date, allDate);
	}
	/**
	 * 字符型格式化成日期型
	 * @param datetimestr
	 * @param format
	 * @return
	 */
	public static Date parse(String datetimestr,String format){
		return parse(datetimestr, format,java.util.Date.class);
	}
	/**
     * 字符型格式化成日期型
	 * @param datetimestr
	 * @return
	 */
	public static Date parse(String datetimestr){
	    return parse(datetimestr, allDate, java.util.Date.class);
	}
	
	/**
	 * 根据日期获得所在周的日期 
	 * @param mdate 时间
	 * @param flag day：以时间为准得到以后的七天，week：以星期为准得到本周
	 * @return
	 */
    @SuppressWarnings("deprecation")
    public static List<Date> dateToWeek(Date mdate,String flag) {
        int b = mdate.getDay();
        if(b==0){//星期日放在最后，如果想放在最前面删除此if
        	b=7;
        }
        Date fdate;
        List<Date> list = new ArrayList<Date>();
        Long fTime = mdate.getTime();
        if(flag.equals("day")){
        	fTime = fTime - 1 * 24 * 3600000;
        }
        if(flag.equals("week")){
        	fTime = fTime - b * 24 * 3600000;
        }
        for (int a = 1; a <= 7; a++) {
            fdate = new Date();
            fdate.setTime(fTime + (a * 24 * 3600000));
            list.add(a-1, fdate);
        }
        return list;
    }
	
	/**
	 * 根据时间得到本周
	 * @return
	 */
	public static List<ResultStatistical> getWeekByToday() {
		  SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
		  SimpleDateFormat showMothFormat = new SimpleDateFormat("MM");
		  SimpleDateFormat showDayFormat = new SimpleDateFormat("dd");
		  SimpleDateFormat weekFormat = new SimpleDateFormat("EEE", Locale.CHINA);
	        Date currentDate = new Date();
	        List<Date> days = dateToWeek(currentDate,"day");
	        List<ResultStatistical> daysList=new ArrayList<ResultStatistical>();
	        for (Date date : days) {
	        	ResultStatistical dayVo=new ResultStatistical();
	        	if(dayFormat.format(date).equals(dayFormat.format(currentDate))){
	        		dayVo.setNum1(1L);
	        	}else{
	        		dayVo.setNum1(0L);
	        	}
	        	dayVo.setMainDimension(dayFormat.format(date));
	        	dayVo.setDimension2(showDayFormat.format(date));
	        	dayVo.setDimension4(showMothFormat.format(date));
	        	dayVo.setDimension3(weekFormat.format(date).replace("星期", ""));
	        	daysList.add(dayVo);
	        }
		return daysList;
	}
	
	/**
	 * 根据时间推算出年份时间
	 * @param year
	 * @param format
	 * @return
	 */
	public static Date parse(Integer year){
	    if (year != null) {
	        Calendar c = Calendar.getInstance();
	        year = c.get(Calendar.YEAR) - year;
	        c.set(Calendar.YEAR, year);
	        return c.getTime();
	    }
	    return null;
	}
	
	/**
	 * 根据时间推算出年龄
	 * @param year
	 * @param format
	 * @return
	 */
    public static int calcAge(Date birthDate){  
        Date nowDate= new Date();  
        Calendar flightCal= Calendar.getInstance();  
        flightCal.setTime(nowDate);  
        Calendar birthCal= Calendar.getInstance();  
        birthCal.setTime(birthDate);  
          
        int y= flightCal.get(Calendar.YEAR)-birthCal.get(Calendar.YEAR);  
        int m= flightCal.get(Calendar.MONTH)-birthCal.get(Calendar.MONTH);  
        int d= flightCal.get(Calendar.DATE)-birthCal.get(Calendar.DATE);  
        if(y<0){  
            throw new RuntimeException("还没出生");  
        }  
        if(m<0){  
            y--;  
        }  
        if(m>=0&&d<0){  
            y--;  
        }  
        return y;  
    }  
    
    /**
     * 两个时间相减得到相差年数
     * @param startDate
     * @param endDate
     * @return
     */
    public static int calcYear(Date startDate,Date endDate){  
        Calendar startCal= Calendar.getInstance();  
        startCal.setTime(startDate);  
        Calendar endCal= Calendar.getInstance();  
        endCal.setTime(endDate);  
        return endCal.get(Calendar.YEAR)-startCal.get(Calendar.YEAR);  
    } 
    
    /**
     * 两个时间相减得到相差月数
     * @param startDate
     * @param endDate
     * @return
     */
    public static int calcMonth(Date startDate,Date endDate){  
        Calendar startCal= Calendar.getInstance();  
        startCal.setTime(startDate);  
        Calendar endCal= Calendar.getInstance();  
        endCal.setTime(endDate); 
        return endCal.get(Calendar.MONTH)-startCal.get(Calendar.MONTH);  
    } 
    
    /**
     * 两个时间相减得到相差天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static int calcDay(Date startDate,Date endDate){  
        Calendar startCal= Calendar.getInstance();  
        startCal.setTime(startDate);  
        Calendar endCal= Calendar.getInstance();  
        endCal.setTime(endDate);  
        return endCal.get(Calendar.DATE)-startCal.get(Calendar.DATE);  
    } 
	
	/**
	 * 结构化时间
	 * @param dateString
	 * @param dateFormat
	 * @param targetResultType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends java.util.Date> T parse(String dateString,String dateFormat,Class<T> targetResultType) {
		if(StringUtils.isEmpty(dateString))
			return null;
		DateFormat df = new SimpleDateFormat(dateFormat);
		try {
			long time = df.parse(dateString).getTime();
			Date t = targetResultType.getConstructor(long.class).newInstance(time);
			return (T)t;
		} catch (ParseException e) {
			T descDate = (T) parseErrorDate(dateString);
			if (descDate == null) {
				String errorInfo = "cannot use dateformat:"+dateFormat+" parse datestring:"+dateString;
				throw new IllegalArgumentException(errorInfo,e);
			} else {
				return descDate;
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("error targetResultType:"+targetResultType.getName(),e);
		}
	}
	
	/**
	 * 处理时间格式化失败的字符创
	 * @param dateString
	 * @return
	 */
	public static Date parseErrorDate(String dateString) {
		Date descDate = null;
		DateFormat df = null;
		List<String> sdfList = new ArrayList<String>();
		sdfList.add("yyyy-MM-dd");
		sdfList.add("yyyy/MM/dd");
		sdfList.add("yyyy.MM.dd");
		sdfList.add("yy-MM-dd");
		sdfList.add("yy.MM.dd");
		sdfList.add("yyyy-MM-dd hh:mm:ss");
		sdfList.add("yyyy/MM/dd hh:mm:ss");
		for(String sdf : sdfList) {
			if (descDate != null) {
				break;
			} else {
				try {
					df = new SimpleDateFormat(sdf);
					descDate = df.parse(dateString);
				} catch (Exception e) {
					descDate = null;
				}
			}
		}
		return descDate;
	}
	
	/**
	 * 判断是否是日期字符串
	 * @param str
	 * @return
	 */
	public static boolean isDateString(String str){
		if(StringUtils.isNotBlank(str)){
			Pattern patternDate = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}");
			Pattern patternDateTime = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
			Pattern patternday = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
			if(patternDate.matcher(str).matches() || patternday.matcher(str).matches() || patternDateTime.matcher(str).matches()){
				return true;
			}
		}
		return false;
	}

	/**
	 * 最后一分钟
	 * @param date
	 * @return
	 */
	public static String endDateString(Date date){
		if(date == null){
			return format(getNow(), "yyyy-MM-dd")+" 23:59:59";
		}else{
			return format(date, "yyyy-MM-dd")+" 23:59:59";
		}
	}
	
	/**
	 * 格式化成一天的第一分钟
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date firstDate(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formatter.parse(formatter.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 格式化成一天的最后1分钟
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date endDate(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return parse(formatter.format(date)+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 取当前时间
	 * @return
	 */
	public static Date getNow(){
		return Calendar.getInstance().getTime();
	}
	
    /**
     * 根据值生成 对应 格式时间
     * 
     * @param d
     * @return
     * @author Tolk, Mar 11, 2013 10:31:09 AM
     */
    public static Date fomartDateByString(String d) {
        Date parseDate = null;
        try {
            if (d != null && !"".equals(d)) {
                String s = d;
                s = s.replaceFirst("^[0-9]{4}([^0-9]?)", "yyyy$1");
                s = s.replaceFirst("^[0-9]{2}([^0-9]?)", "yy$1");
                s = s.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1MM$2");
                s = s.replaceFirst("([^0-9]?)[0-9]{1,2}( ?)", "$1dd$2");
                s = s.replaceFirst("( )[0-9]{1,2}([^0-9]?)", "$1HH$2");
                s = s.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1mm$2");
                s = s.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1ss$2");
                DateFormat f = new SimpleDateFormat(s);
                parseDate = f.parse(d);
            }
        } catch (Exception e) {
            System.out.println("日期转换失败:" + d);
        }
        return parseDate;
    }

    /**
     * 格式出工作年限的时间段
     * @param numInteger
     * @return data
     */
    public static Map<String, Object> parseWorkYear(Integer numInteger) {
        Map<String, Object> data = new HashMap<String, Object> ();
        switch (numInteger) {// 1 = 1-3年 ，2= 3-5年 ， 3 = 5-10年 ， 4= 10以上
        case 1:
            data.put("GTE_jobAge", DateConvertUtils.format(DateConvertUtils.parse(1), ""));// 最大工作年龄开始时间
            data.put("LTE_jobAge", DateConvertUtils.format(DateConvertUtils.parse(3), ""));// 最小工作年龄结束时间
            break;
        case 2:
            data.put("GTE_jobAge", DateConvertUtils.format(DateConvertUtils.parse(3), ""));// 最大工作年龄开始时间
            data.put("LTE_jobAge", DateConvertUtils.format(DateConvertUtils.parse(5), ""));// 最小工作年龄结束时间
            break;
        case 3:
            data.put("GTE_jobAge", DateConvertUtils.format(DateConvertUtils.parse(5), ""));// 最大工作年龄开始时间
            data.put("LTE_jobAge", DateConvertUtils.format(DateConvertUtils.parse(10), ""));// 最小工作年龄结束时间
            break;
        case 4:
            data.put("GTE_jobAge", DateConvertUtils.format(DateConvertUtils.parse(10), ""));// 最大工作年龄开始时间
            break;
        default:
            break;
        }
        return data;
    }
    
    /**
     * 处理时间段条件
     * @param searchParams
     */
    public static void parseTimeSlot(Map<String, Object> searchParams,String label, String param1, String param2) {
        Object applyTimes = searchParams.get(label);
        if(applyTimes != null && applyTimes != ""){
        	if(!applyTimes.toString().startsWith("20")){
        		return;
        	}
            String startApplyTime = "";
            String endApplyTime = "";
            String applyTime = applyTimes.toString();
            if (applyTime.contains(" - ")) {
                String[] str1 = applyTime.split(" - ");
                startApplyTime = str1[0].trim();
                endApplyTime = str1[1].trim();
            }else {
                startApplyTime = applyTime.trim();
                endApplyTime = applyTime.trim();
            }
            searchParams.put(param1, startApplyTime+" 00:00:00");
            searchParams.put(param2, endApplyTime+" 23:59:59");
        }
    }
    
    /**
     * 格式出以'yyyy-MM-dd - yyyy-MM-dd'为格式的的时间段
     * @param dateStr
     * @return List<Date>
     */
    public static List<Date> parseDate (String dateStr, String sdf) {
        return parseDate (dateStr, sdf, null);
    }
    
    /**
     * 格式出以双时间格式的的时间段
     * @param dateStr
     * @param sdf
     * @param spiltStr
     * @return
     */
    public static List<Date> parseDate (String dateStr, String sdf, String spiltStr) {
        if (dateStr == null || "".equals(dateStr)) {
            return null;
        } else {
            List<Date> data = new ArrayList<Date>();
            // 时间格式化
            if (sdf == null) {
                sdf = allDate;
            }
            if (spiltStr != null && !"".equals(spiltStr)) {
                dateStr.replace(spiltStr, " - ");
            }
            String[] dates = dateStr.split(" - ");
            if (dates != null && dates.length > 0) {
                for (String date : dates) {
                    data.add(parse(date, sdf));
                }
            }
            return data;
        }
    }
    
    /**
     * 得到当前时间的前几天时间
     * @return data
     */
   public static Date getDateBeform(int beform) {
	   Date date = new Date();
	   	Date dBefore = new Date();
	    Calendar calendar = Calendar.getInstance(); //得到日历
	    calendar.setTime(date);//把当前时间赋给日历
	    calendar.add(Calendar.DAY_OF_MONTH, -beform);  //设置为前一天
	    dBefore = calendar.getTime();   //得到前一天的时间
	    return dBefore;
    }
    
    /**
     * 得到当天时间的String格式  yyyy-MM
     * @return
     */
   public static String getDateMonth () {
       Date now = getNow();
       return format(now, allDateMonth);
    }
   
   /**
    * 得到当天时间的String格式  yyyy-MM-dd
    * @return
    */
   public static String getDate () {
	   Date now = getNow();
	   return format(now, allDate);
   }
   
   /**
    * yyyy-MM-dd HH:mm:ss
    * @return
    */
   public static String getTime () {
       Date now = getNow();
       return format(now, allTime);
   }
	
}
