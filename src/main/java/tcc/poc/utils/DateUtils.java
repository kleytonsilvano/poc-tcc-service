package tcc.poc.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtils {

    private final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'hh:mm:ss";

    public Date getDate(String date) {
        try {
            return new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public Date getDate(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public String getDate(Date date) {
        if(date != null) {
            return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(date);
        }
        return null;
    }

    public String getDate(Date date, String format) {
        if(date != null) {
            return new SimpleDateFormat(format).format(date);
        }
        return null;
    }

}
