package cc.eslink.fundanalyze.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期处理工具类
 * @author van.zheng
 *
 */
public class DateUtil {

    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

    private static final Object object = new Object();

    /**
     * 计算两个时间之间的相差分钟数
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Long getConsumeTimeOfMinute(String beginDate, String endDate) {
        // 当两个时间值一样的情况，代表还未超过1毫秒，显示0毫秒即可
        if (beginDate.equals(endDate)) {
            return 0L;
        }

        SimpleDateFormat dfs = new SimpleDateFormat(DateStyle.YYYY_MM_DD_HH_MM_SS.getValue());
        long diff = 0;
        try {
            Date begin = dfs.parse(beginDate);
            Date end = dfs.parse(endDate);
            long beginTime = begin.getTime();
            long endTime = end.getTime();
            if (beginTime < endTime) {
                diff = endTime - beginTime;
            } else {
                diff = beginTime - endTime;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        long minutes = diff / (60 * 1000);

        return minutes;
    }

    /**
     * 时间相减得到天数
     * @throws ParseException
     */
    public static long getDaySub(Date beginDate, Date endDate) {
        long day = 0;
        String beginDateStr = DateUtil.DateToString(beginDate, DateStyle.YYYY_MM_DD);
        String endDateStr = DateUtil.DateToString(endDate, DateStyle.YYYY_MM_DD);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            day = (format.parse(endDateStr).getTime() - format.parse(beginDateStr).getTime())
                    / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }

    /**
     * 时间相减得到小时
     *
     * @throws ParseException
     */
    public static BigDecimal getHourSub(Date beginDate, Date endDate) {
        long dayLong = 0;
        String beginDateStr = DateUtil.DateToString(beginDate, DateStyle.YYYY_MM_DD_HH_MM_SS);
        String endDateStr = DateUtil.DateToString(endDate, DateStyle.YYYY_MM_DD_HH_MM_SS);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dayLong = (format.parse(endDateStr).getTime() - format.parse(beginDateStr).getTime());
            long hourLong = 60 * 60 * 1000;
            BigDecimal day = new BigDecimal(dayLong);
            BigDecimal hour = new BigDecimal(hourLong);
            return day.divide(hour, 2, RoundingMode.HALF_UP);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取SimpleDateFormat
     *
     * @param pattern
     *            日期格式
     * @return SimpleDateFormat对象
     * @throws RuntimeException
     *             异常：非法日期格式
     */
    private static SimpleDateFormat getDateFormat(String pattern)
            throws RuntimeException {
        SimpleDateFormat dateFormat = threadLocal.get();
        if (dateFormat == null) {
            synchronized (object) {
                if (dateFormat == null) {
                    dateFormat = new SimpleDateFormat(pattern);
                    dateFormat.setLenient(false);
                    threadLocal.set(dateFormat);
                }
            }
        }
        dateFormat.applyPattern(pattern);
        return dateFormat;
    }

    /**
     * 获取日期中的某数值。如获取月份
     *
     * @param date
     *            日期
     * @param dateType
     *            日期格式
     * @return 数值
     */
    private static int getInteger(Date date, int dateType) {
        int num = 0;
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
            num = calendar.get(dateType);
        }
        return num;
    }

    /**
     *
     * @Title: getActualMaximum
     * @Description: (根据日期格式 获取最大数)
     * @param @param date 日期
     * @param @param dateType 日期格式
     * @param @return
     * @return int
     */
    private static int getActualMaximum(Date date, int dateType) {
        int num = 0;
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
            num = calendar.getActualMaximum(dateType);
        }
        return num;
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date
     *            日期字符串
     * @param dateType
     *            类型
     * @param amount
     *            数值
     * @return 计算后日期字符串
     */
    private static String addInteger(String date, int dateType, int amount) {
        String dateString = null;
        DateStyle dateStyle = getDateStyle(date);
        if (dateStyle != null) {
            Date myDate = StringToDate(date, dateStyle);
            myDate = addInteger(myDate, dateType, amount);
            dateString = DateToString(myDate, dateStyle);
        }
        return dateString;
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date
     *            日期
     * @param dateType
     *            类型
     * @param amount
     *            数值
     * @return 计算后日期
     */
    private static Date addInteger(Date date, int dateType, int amount) {
        Date myDate = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            myDate = calendar.getTime();
        }
        return myDate;
    }

    /**
     * 获取精确的日期
     *
     * @param timestamps
     *            时间long集合
     * @return 日期
     */
    private static Date getAccurateDate(List<Long> timestamps) {
        Date date = null;
        long timestamp = 0;
        Map<Long, long[]> map = new HashMap<Long, long[]>();
        List<Long> absoluteValues = new ArrayList<Long>();

        if (timestamps != null && timestamps.size() > 0) {
            if (timestamps.size() > 1) {
                for (int i = 0; i < timestamps.size(); i++) {
                    for (int j = i + 1; j < timestamps.size(); j++) {
                        long absoluteValue = Math.abs(timestamps.get(i)
                                - timestamps.get(j));
                        absoluteValues.add(absoluteValue);
                        long[] timestampTmp = {timestamps.get(i),
                                timestamps.get(j)};
                        map.put(absoluteValue, timestampTmp);
                    }
                }

                // 有可能有相等的情况。如2012-11和2012-11-01。时间戳是相等的。此时minAbsoluteValue为0
                // 因此不能将minAbsoluteValue取默认值0
                long minAbsoluteValue = -1;
                if (!absoluteValues.isEmpty()) {
                    minAbsoluteValue = absoluteValues.get(0);
                    for (int i = 1; i < absoluteValues.size(); i++) {
                        if (minAbsoluteValue > absoluteValues.get(i)) {
                            minAbsoluteValue = absoluteValues.get(i);
                        }
                    }
                }

                if (minAbsoluteValue != -1) {
                    long[] timestampsLastTmp = map.get(minAbsoluteValue);

                    long dateOne = timestampsLastTmp[0];
                    long dateTwo = timestampsLastTmp[1];
                    if (absoluteValues.size() > 1) {
                        timestamp = Math.abs(dateOne) > Math.abs(dateTwo) ? dateOne
                                : dateTwo;
                    }
                }
            } else {
                timestamp = timestamps.get(0);
            }
        }

        if (timestamp != 0) {
            date = new Date(timestamp);
        }
        return date;
    }

    /**
     * 判断字符串是否为日期字符串
     *
     * @param date
     *            日期字符串
     * @return true or false
     */
    public static boolean isDate(String date) {
        boolean isDate = false;
        if (date != null) {
            if (getDateStyle(date) != null) {
                isDate = true;
            }
        }
        return isDate;
    }

    /**
     * 获取日期字符串的日期风格。失敗返回null。
     *
     * @param date
     *            日期字符串
     * @return 日期风格
     */
    public static DateStyle getDateStyle(String date) {
        DateStyle dateStyle = null;
        Map<Long, DateStyle> map = new HashMap<Long, DateStyle>();
        List<Long> timestamps = new ArrayList<Long>();
        for (DateStyle style : DateStyle.values()) {
            if (style.isShowOnly()) {
                continue;
            }
            Date dateTmp = null;
            if (date != null) {
                try {
                    ParsePosition pos = new ParsePosition(0);
                    dateTmp = getDateFormat(style.getValue()).parse(date, pos);
                    if (pos.getIndex() != date.length()) {
                        dateTmp = null;
                    }
                } catch (Exception e) {
                }
            }
            if (dateTmp != null) {
                timestamps.add(dateTmp.getTime());
                map.put(dateTmp.getTime(), style);
            }
        }
        Date accurateDate = getAccurateDate(timestamps);
        if (accurateDate != null) {
            dateStyle = map.get(accurateDate.getTime());
        }
        return dateStyle;
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date
     *            日期字符串
     * @return 日期
     */
    public static Date StringToDate(String date) {
        DateStyle dateStyle = getDateStyle(date);
        return StringToDate(date, dateStyle);
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date
     *            日期字符串
     * @param pattern
     *            日期格式
     * @return 日期
     */
    public static Date StringToDate(String date, String pattern) {
        Date myDate = null;
        if (date != null) {
            try {
                myDate = getDateFormat(pattern).parse(date);
            } catch (Exception e) {
            }
        }
        return myDate;
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date
     *            日期字符串
     * @param dateStyle
     *            日期风格
     * @return 日期
     */
    public static Date StringToDate(String date, DateStyle dateStyle) {
        Date myDate = null;
        if (dateStyle != null) {
            myDate = StringToDate(date, dateStyle.getValue());
        }
        return myDate;
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date
     *            日期
     * @param pattern
     *            日期格式
     * @return 日期字符串
     */
    public static String DateToString(Date date, String pattern) {
        String dateString = null;
        if (date != null) {
            try {
                dateString = getDateFormat(pattern).format(date);
            } catch (Exception e) {
            }
        }
        return dateString;
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date
     *            日期
     * @param dateStyle
     *            日期风格
     * @return 日期字符串
     */
    public static String DateToString(Date date, DateStyle dateStyle) {
        String dateString = null;
        if (dateStyle != null) {
            dateString = DateToString(date, dateStyle.getValue());
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date
     *            旧日期字符串
     * @param newPattern
     *            新日期格式
     * @return 新日期字符串
     */
    public static String StringToString(String date, String newPattern) {
        DateStyle oldDateStyle = getDateStyle(date);
        return StringToString(date, oldDateStyle, newPattern);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date
     *            旧日期字符串
     * @param newDateStyle
     *            新日期风格
     * @return 新日期字符串
     */
    public static String StringToString(String date, DateStyle newDateStyle) {
        DateStyle oldDateStyle = getDateStyle(date);
        return StringToString(date, oldDateStyle, newDateStyle);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date
     *            旧日期字符串
     * @param olddPattern
     *            旧日期格式
     * @param newPattern
     *            新日期格式
     * @return 新日期字符串
     */
    public static String StringToString(String date, String olddPattern,
                                        String newPattern) {
        return DateToString(StringToDate(date, olddPattern), newPattern);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date
     *            旧日期字符串
     * @param olddDteStyle
     *            旧日期风格
     * @param newParttern
     *            新日期格式
     * @return 新日期字符串
     */
    public static String StringToString(String date, DateStyle olddDteStyle,
                                        String newParttern) {
        String dateString = null;
        if (olddDteStyle != null) {
            dateString = StringToString(date, olddDteStyle.getValue(),
                    newParttern);
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date
     *            旧日期字符串
     * @param olddPattern
     *            旧日期格式
     * @param newDateStyle
     *            新日期风格
     * @return 新日期字符串
     */
    public static String StringToString(String date, String olddPattern,
                                        DateStyle newDateStyle) {
        String dateString = null;
        if (newDateStyle != null) {
            dateString = StringToString(date, olddPattern,
                    newDateStyle.getValue());
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date
     *            旧日期字符串
     * @param olddDteStyle
     *            旧日期风格
     * @param newDateStyle
     *            新日期风格
     * @return 新日期字符串
     */
    public static String StringToString(String date, DateStyle olddDteStyle,
                                        DateStyle newDateStyle) {
        String dateString = null;
        if (olddDteStyle != null && newDateStyle != null) {
            dateString = StringToString(date, olddDteStyle.getValue(),
                    newDateStyle.getValue());
        }
        return dateString;
    }

    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date
     *            日期
     * @param yearAmount
     *            增加数量。可为负数
     * @return 增加年份后的日期字符串
     */
    public static String addYear(String date, int yearAmount) {
        return addInteger(date, Calendar.YEAR, yearAmount);
    }

    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date
     *            日期
     * @param yearAmount
     *            增加数量。可为负数
     * @return 增加年份后的日期
     */
    public static Date addYear(Date date, int yearAmount) {
        return addInteger(date, Calendar.YEAR, yearAmount);
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date
     *            日期
     * @param monthAmount
     *            增加数量。可为负数
     * @return 增加月份后的日期字符串
     */
    public static String addMonth(String date, int monthAmount) {
        return addInteger(date, Calendar.MONTH, monthAmount);
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date
     *            日期
     * @param monthAmount
     *            增加数量。可为负数
     * @return 增加月份后的日期
     */
    public static Date addMonth(Date date, int monthAmount) {
        return addInteger(date, Calendar.MONTH, monthAmount);
    }

    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date
     *            日期字符串
     * @param dayAmount
     *            增加数量。可为负数
     * @return 增加天数后的日期字符串
     */
    public static String addDay(String date, int dayAmount) {
        return addInteger(date, Calendar.DATE, dayAmount);
    }

    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date
     *            日期
     * @param dayAmount
     *            增加数量。可为负数
     * @return 增加天数后的日期
     */
    public static Date addDay(Date date, int dayAmount) {
        return addInteger(date, Calendar.DATE, dayAmount);
    }

    /**
     * 增加日期的小时。失败返回null。
     *
     * @param date
     *            日期字符串
     * @param hourAmount
     *            增加数量。可为负数
     * @return 增加小时后的日期字符串
     */
    public static String addHour(String date, int hourAmount) {
        return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
    }

    /**
     * 增加日期的小时。失败返回null。
     *
     * @param date
     *            日期
     * @param hourAmount
     *            增加数量。可为负数
     * @return 增加小时后的日期
     */
    public static Date addHour(Date date, int hourAmount) {
        return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
    }

    /**
     * 增加日期的分钟。失败返回null。
     *
     * @param date
     *            日期字符串
     * @param minuteAmount
     *            增加数量。可为负数
     * @return 增加分钟后的日期字符串
     */
    public static String addMinute(String date, int minuteAmount) {
        return addInteger(date, Calendar.MINUTE, minuteAmount);
    }

    /**
     * 增加日期的分钟。失败返回null。
     *
     * @param date
     *            日期
     * @param dayAmount
     *            增加数量。可为负数
     * @return 增加分钟后的日期
     */
    public static Date addMinute(Date date, int minuteAmount) {
        return addInteger(date, Calendar.MINUTE, minuteAmount);
    }

    /**
     * 增加日期的秒钟。失败返回null。
     *
     * @param date
     *            日期字符串
     * @param dayAmount
     *            增加数量。可为负数
     * @return 增加秒钟后的日期字符串
     */
    public static String addSecond(String date, int secondAmount) {
        return addInteger(date, Calendar.SECOND, secondAmount);
    }

    /**
     * 增加日期的秒钟。失败返回null。
     *
     * @param date
     *            日期
     * @param dayAmount
     *            增加数量。可为负数
     * @return 增加秒钟后的日期
     */
    public static Date addSecond(Date date, int secondAmount) {
        return addInteger(date, Calendar.SECOND, secondAmount);
    }

    /**
     * 获取日期的年份。失败返回0。
     *
     * @param date
     *            日期字符串
     * @return 年份
     */
    public static int getYear(String date) {
        return getYear(StringToDate(date));
    }

    /**
     * 获取日期的年份。失败返回0。
     *
     * @param date
     *            日期
     * @return 年份
     */
    public static int getYear(Date date) {
        return getInteger(date, Calendar.YEAR);
    }

    /**
     * 获取日期的月份。失败返回0。
     *
     * @param date
     *            日期字符串
     * @return 月份
     */
    public static int getMonth(String date) {
        return getMonth(StringToDate(date));
    }

    /**
     * 获取日期的月份。失败返回0。
     *
     * @param date
     *            日期
     * @return 月份
     */
    public static int getMonth(Date date) {
        return getInteger(date, Calendar.MONTH) + 1;
    }

    /**
     * 获取日期的天数。失败返回0。
     *
     * @param date
     *            日期字符串
     * @return 天
     */
    public static int getDay(String date) {
        return getDay(StringToDate(date));
    }

    /**
     * 获取日期的天数。失败返回0。
     *
     * @param date
     *            日期
     * @return 天
     */
    public static int getDay(Date date) {
        return getInteger(date, Calendar.DATE);
    }

    /**
     *
     * @Title: getLastDay
     * @Description: (获取日期所在月的 天数)
     * @param @param date
     * @param @return
     * @return int
     */
    public static int getLastDay(Date date) {
        return getActualMaximum(date, Calendar.DAY_OF_MONTH);
    }

    /**
     *
     * @Title: getLastDay
     * @Description: (获取日期所在月的 天数)
     * @param @param date
     * @param @return
     * @return int
     */
    public static int getLastDay(String date) {
        return getLastDay(StringToDate(date));
    }

    /**
     *
     * @Title: getDaysFromLast
     * @Description: (获取日期 距离所在月的最后一天还有多少天)
     * @param @param date
     * @param @return
     * @return int
     */
    public static int getDaysFromLast(String date) {
        return getLastDay(date) - getDay(date);
    }

    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date
     *            日期字符串
     * @return 小时
     */
    public static int getHour(String date) {
        return getHour(StringToDate(date));
    }

    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date
     *            日期
     * @return 小时
     */
    public static int getHour(Date date) {
        return getInteger(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date
     *            日期字符串
     * @return 分钟
     */
    public static int getMinute(String date) {
        return getMinute(StringToDate(date));
    }

    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date
     *            日期
     * @return 分钟
     */
    public static int getMinute(Date date) {
        return getInteger(date, Calendar.MINUTE);
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date
     *            日期字符串
     * @return 秒钟
     */
    public static int getSecond(String date) {
        return getSecond(StringToDate(date));
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date
     *            日期
     * @return 秒钟
     */
    public static int getSecond(Date date) {
        return getInteger(date, Calendar.SECOND);
    }

    /**
     * 获取日期 。默认yyyy-MM-dd格式。失败返回null。
     *
     * @param date
     *            日期字符串
     * @return 日期
     */
    public static String getDate(String date) {
        return StringToString(date, DateStyle.YYYY_MM_DD);
    }

    /**
     * 获取日期。默认yyyy-MM-dd格式。失败返回null。
     *
     * @param date
     *            日期
     * @return 日期
     */
    public static String getDate(Date date) {
        return DateToString(date, DateStyle.YYYY_MM_DD);
    }

    /**
     * 获取日期。默认格式yyyyMMddHHmmssSSS。失败返回null。
     *
     * @param date
     *            日期
     * @return 日期
     */
    public static String getLongDateSSS(Date date) {
        return DateToString(date, DateStyle.YYYYMMDDHHMMSSSSS);
    }

    /**
     * 获取时间。默认格式yyyy-MM-dd HH:mm:ss。失败返回null。
     *
     * @param date
     *            日期
     * @return 日期
     */
    public static String getLongDate(Date date) {
        return DateToString(date, DateStyle.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
     *
     * @param date
     *            日期字符串
     * @return 时间
     */
    public static String getTime(String date) {
        return StringToString(date, DateStyle.HH_MM_SS);
    }

    /**
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
     *
     * @param date
     *            日期
     * @return 时间
     */
    public static String getTime(Date date) {
        return DateToString(date, DateStyle.HH_MM_SS);
    }

    /**
     * 获取两个日期相差的天数
     *
     * @param date
     *            日期字符串
     * @param otherDate
     *            另一个日期字符串
     * @return 相差天数。如果失败则返回-1
     */
    public static int getIntervalDays(String date, String otherDate) {
        return getIntervalDays(StringToDate(date), StringToDate(otherDate));
    }

    /**
     * @param date
     *            日期
     * @param otherDate
     *            另一个日期
     * @return 相差天数。如果失败则返回-1
     */
    public static int getIntervalDays(Date date, Date otherDate) {
        int num = -1;
        Date dateTmp = DateUtil.StringToDate(DateUtil.getDate(date),
                DateStyle.YYYY_MM_DD);
        Date otherDateTmp = DateUtil.StringToDate(DateUtil.getDate(otherDate),
                DateStyle.YYYY_MM_DD);
        if (dateTmp != null && otherDateTmp != null) {
            long time = dateTmp.getTime() - otherDateTmp.getTime();
            num = (int) (time / (24 * 60 * 60 * 1000));
        }
        return num;
    }

    /**
     * 获取日期的最小时间
     *
     * @param date
     * @return
     */
    public static Date getDayMin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 获取日期的最大时间
     *
     * @param date
     * @return
     */
    public static Date getDayMax(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return calendar.getTime();
    }

    public static String getDayMinStr(String date) {
        return getDayMinStr(StringToDate(date));
    }

    /**
     * 获取日期的最小时间
     *
     * @param date
     * @return
     */
    public static String getDayMinStr(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return DateToString(calendar.getTime(), DateStyle.YYYY_MM_DD_HH_MM_SS);
    }

    public static String getDayMaxStr(String date) {
        return getDayMinStr(StringToDate(date));
    }

    /**
     * 获取日期的最大时间
     *
     * @param date
     * @return
     */
    public static String getDayMaxStr(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return DateToString(calendar.getTime(), DateStyle.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取当天的开始时间
     */
    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当天的结束时间
     *
     * @return
     */
    public static Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 获取昨天的开始时间
     *
     * @return
     */
    public static Date getBeginDayOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获取昨天的结束时间
     *
     * @return
     */
    public static Date getEndDayOfYesterDay() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获取明天的开始时间
     *
     * @return
     */
    public static Date getBeginDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, 1);

        return cal.getTime();
    }

    // 获取明天的结束时间
    public static Date getEndDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 获取本周的开始时间
     *
     * @return
     */
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        return getBeginDayOfWeek(date);
    }

    /**
     * 获取本周的开始时间
     *
     * @return
     */
    public static Date getBeginDayOfWeek(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayMin(cal.getTime());
    }

    /**
     * 获取本周的结束时间
     *
     * @return
     */
    public static Date getEndDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayMax(weekEndSta);
    }

    /**
     * 获取本周的结束时间
     *
     * @param date
     * @return
     * @author shaosen
     * @Date 2018年9月12日
     */
    public static Date getEndDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek(date));
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayMax(weekEndSta);
    }

    /**
     * 获取本月的开始时间
     *
     * @return
     */
    public static Date getBeginDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getCurrentYear(), getCurrentMonth() - 1, 1);
        return getDayMin(calendar.getTime());
    }

    /**
     * 获取本月的开始时间
     *
     * @return
     */
    public static Date getBeginDayOfMonth(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        return getDayMin(calendar.getTime());
    }

    /**
     * 获取本月的结束时间
     *
     * @return
     */
    public static Date getEndDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getCurrentYear(), getCurrentMonth() - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getCurrentYear(), getCurrentMonth() - 1, day);
        return getDayMax(calendar.getTime());
    }

    /**
     * 获取本月的结束时间
     *
     * @return
     */
    public static Date getEndDayOfMonth(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), day);
        return getDayMax(calendar.getTime());
    }

    /**
     * 获取本年的开始时间
     *
     * @return
     */
    public static Date getBeginDayOfYear() {
        return getBeginDayOfYear(new Date());
    }

    /**
     * 获得time所在年的第一天
     *
     * @param time
     * @return
     */
    public static Date getBeginDayOfYear(Date time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);

        return getDayMin(cal.getTime());
    }

    /**
     * 获取本年的结束时间
     *
     * @return
     */
    public static Date getEndDayOfYear() {
        return getEndDayOfYear(new Date());
    }

    /**
     * 获取本年的结束时间
     *
     * @return
     */
    public static Date getEndDayOfYear(Date time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return getDayMax(cal.getTime());
    }

    /**
     * 获取当前年度
     * @return
     */
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    /**
     * 获取本月是哪一月
     *
     * @return
     */
    public static int getCurrentMonth() {
        Date date = new Date();
        return getCurrentMonth(date);
    }

    public static int getCurrentMonth(Date date) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }

    /**
     *
     * @Title: getCurrentDate
     * @Description: (获取当前时间日期 获取日期 。 默认yyyy - MM - dd格式)
     * @param @return
     * @return String
     */
    public static String getCurrentDate() {
        return getDate(new Date());
    }

    public static String getCurrentDate2() {
        return DateUtil.DateToString(new Date(), DateStyle.YYYYMMDD);
    }

    /**
     * 获取当前日期时间
     * @return
     */
    public static String getCurrentDateTime() {
        return DateUtil.DateToString(new Date(), DateStyle.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取指定往前天数日期
     * @return
     */
    public static String getPreviousDate(int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -num);
        Date date = calendar.getTime();
        return DateToString(date, DateStyle.YYYY_MM_DD);
    }

    /**
     * 获取两个时间的相差值(精确到毫秒)
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static String getConsumeTime2(String beginDate, String endDate) {
        // 当两个时间值一样的情况，代表还未超过1毫秒，显示0毫秒即可
        if (beginDate.equals(endDate)) {
            return "0毫秒";
        }

        SimpleDateFormat dfs = new SimpleDateFormat(
                DateStyle.YYYY_MM_DD_HH_MM_SS_SSS.getValue());
        long between = 0;
        try {
            Date begin = dfs.parse(beginDate);
            Date end = dfs.parse(endDate);
            between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
                - min * 60 * 1000 - s * 1000);

        String consumeTime = "";
        if (day > 0) {
            consumeTime += day + "天";
        }
        if (hour > 0) {
            consumeTime += hour + "小时";
        }
        if (min > 0) {
            consumeTime += min + "分钟";
        }
        if (s > 0) {
            consumeTime += s + "秒";
        }
        if (ms > 0) {
            consumeTime += ms + "毫秒";
        }
        return consumeTime;
    }

    public static String getConsumeTime(String beginDate, String endDate) {
        return getConsumeTime(beginDate, endDate, DateStyle.YYYY_MM_DD_HH_MM_SS, 4);
    }

    public static String getConsumeTime(String beginDate, String endDate, DateStyle dateStyle, int level) {
        // 当两个时间值一样的情况，代表还未超过1毫秒，显示0毫秒即可
        if (StringUtils.isAnyBlank(beginDate, endDate)) {
            return null;
        }
        Date begin = null, end = null;
        try {
            begin = StringToDate(beginDate, dateStyle);
            end = StringToDate(endDate, dateStyle);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return getConsumeTime(begin, end, level);
    }

    public static String getConsumeTime(Date begin, Date end, int level) {
        // 当两个时间值一样的情况，代表还未超过1毫秒，显示0毫秒即可
        if (null == begin || null == end) {
            return null;
        }
        long between = Math.abs(end.getTime() - begin.getTime());// 得到两者的毫秒数
        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
                - min * 60 * 1000 - s * 1000);

        Map<String, Long> map = new LinkedHashMap<>();
        map.put("天", day);
        map.put("小时", hour);
        map.put("分钟", min);
        map.put("秒", s);
        map.put("毫秒", ms);
        String consumeTime = "";
        int index = 0, count = level;
        Iterator<Map.Entry<String, Long>> it = map.entrySet().iterator();
        while (it.hasNext() && count-- > 0) {
            Map.Entry<String, Long> item = it.next();
            if (item.getValue() > 0 || (!consumeTime.isEmpty() && index > 0)) {
                consumeTime += item.getValue() + item.getKey();
            }
            // 如果后边的都是0，则不显示
            if (isRightZero(map, index + 1, level)) {
                break;
            }
            index++;
        }
        if (consumeTime.isEmpty()) {
            return "0秒";
        }
        return consumeTime;
    }

    private static boolean isRightZero(Map<String, Long> map, int beginIndex, int endIndex) {
        int index = -1;
        for (Map.Entry<String, Long> item : map.entrySet()) {
            index++;
            if (index < beginIndex || index >= endIndex) {
                continue;
            }
            if (item.getValue() > 0) {
                return false;
            }
        }
        return true;
    }

    public static Long getTimesStamp(String date) {
        return getTimesStamp(StringToDate(date));
    }

    public static Long getTimesStamp(Date date) {
        return date.getTime();
    }

    /**
     *
     * @Title: compareDate
     * @Description: (时间比较大小)
     * @param @param date
     * @param @param otherDate
     * @param @return    
     * @return int
     */
    public static int compareDate(Date date, Date otherDate) {
        int num = -1;
        if (date != null && otherDate != null) {
            num = date.compareTo(otherDate);
        }
        return num;
    }

    /**
     *
     * @Title: compareDate
     * @Description: (时间比较大小)
     * @param @param date
     * @param @param otherDate
     * @param @return    
     * @return int
     */
    public static int compareDate(String date, String otherDate) {
        return compareDate(StringToDate(date), StringToDate(otherDate));
    }

    /**
     * 得到本月的第一天
     *  
     *
     * @return
     */
    public static String getCurrentMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return DateToString(calendar.getTime(), DateStyle.YYYY_MM_DD);
    }

    /**
     * 得到本月的最后一天
     *  
     *
     * @return
     */
    public static String getCurrentMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return DateToString(calendar.getTime(), DateStyle.YYYY_MM_DD);
    }

    /**
     * 获取指定年月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        int firstDay = cal.getMinimum(Calendar.DATE);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        return DateToString(cal.getTime(), DateStyle.YYYY_MM_DD);
    }

    /**
     * 获取指定年月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        return DateToString(cal.getTime(), DateStyle.YYYY_MM_DD);
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.getConsumeTime("2019-06-21 17:00:00", "2019-06-24 17:00:02", DateStyle.YYYY_MM_DD_HH_MM_SS, 5));
//        String applyStartDay = DateUtil.getDate( "2019-03-14 00:00:00" );
//        String applyEndDay = DateUtil.getDate( "2019-03-18 00:00:00" );
//        System.out.println( applyStartDay + "," + applyEndDay );
//        Map<String, String> signMap = new HashMap<>();
//        while (DateUtil.compareDate( applyStartDay, applyEndDay ) <= 0) {
//            if (signMap.containsKey( applyStartDay )) {
//            }
//            else {
//                signMap.put( applyStartDay, applyStartDay );
//            }
//
//            applyStartDay = DateUtil.addDay( applyStartDay, 1 );
//        }
//        System.out.println( signMap );
    }

}