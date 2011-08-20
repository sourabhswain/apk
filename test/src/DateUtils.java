import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: yuml
 * Date: 11-7-29
 * Time: 下午9:17
 * 日期工具类
 */
public class DateUtils {

    public static final String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_SHORT = "yyyy-MM-dd";

    public static String format(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String formatLong(Date date){
        return format(date, FORMAT_LONG);
    }

    public static String formatShort(Date date){
        return format(date, FORMAT_SHORT);
    }

    public static Date parse(String text, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseLong(String text){
        return parse(text, FORMAT_LONG);
    }

    public static Date parseShort(String text){
        return parse(text, FORMAT_SHORT);
    }

    public static String long2Short(String text){
         return formatShort(parseLong(text)) ;
    }

}
