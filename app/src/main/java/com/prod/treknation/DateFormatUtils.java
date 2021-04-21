package com.prod.treknation;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateFormatUtils {

    @SuppressLint("SimpleDateFormat")
    public static String DateFormat(Date date) {
        if(date!=null){
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.setTimeZone(TimeZone.getTimeZone("EST"));
            int day = cal.get(Calendar.DATE);

            if (!((day > 10) && (day < 19)))
                switch (day % 10) {
                    case 1:
                        return new SimpleDateFormat("d'st' MMM', ' yyyy").format(date);
                    case 2:
                        return new SimpleDateFormat("d'nd'  MMM', ' yyyy").format(date);
                    case 3:
                        return new SimpleDateFormat("d'rd'  MMM', ' yyyy").format(date);
                    default:
                        return new SimpleDateFormat("d'th'  MMM', ' yyyy").format(date);
                }
            return new SimpleDateFormat("d'th' MMM', ' yyyy").format(date);
        }
      return "-";
    }

}
