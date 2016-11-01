package com.apex.greendao;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimer
{

    public static String getCurrentDateTime()
    {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=df.format(c.getTime());

        return date;
    }

    public static String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(c.getTime());

        return date;
    }

}
