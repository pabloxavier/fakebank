package br.com.fakebank.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static Date getDateNow() {
        Date today = Calendar.getInstance().getTime();
        return today;
    }

    public static String getDateNowFormatted() {
        return sdf.format(getDateNow());
    }

    public static Date getDateFromString(String dateParameter) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = formatter.parse(dateParameter);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}