package com.jino.jgank.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import timber.log.Timber;

/**
 * 与时间相关的工具类
 * Created by JINO on 2018/1/25.
 */

public class TimeUtils {

    /**
     * 将UTC格式的时间转换为标准格式（2018-01-16T08:40:08.101Z->2018-01-16 08:40:08）
     *
     * @param utcTime UTC格式的时间
     * @return 标准格式的时间
     */
    public static String convertUTCToStandarTime(String utcTime) {
        if (null == utcTime || utcTime == "") {
            return "";
        }
        SimpleDateFormat UTC_Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        try {
            Date date = UTC_Format.parse(utcTime.replace("Z", " UTC"));
            SimpleDateFormat GST_Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return GST_Format.format(date);
        } catch (ParseException e) {
            Timber.e(e);
            return "";
        }
    }

    public static String convertSearchItemDateToStandarTime(String date) {
        if (null == date || date == "") {
            return "";
        }
        SimpleDateFormat UTC_Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
        try {
            Date transformDate = UTC_Format.parse(date+ " UTC");
            SimpleDateFormat GST_Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return GST_Format.format(transformDate);
        } catch (ParseException e) {
            Timber.e(e);
            return "";
        }
    }
}
