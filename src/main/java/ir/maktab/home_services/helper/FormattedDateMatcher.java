package ir.maktab.home_services.helper;

import java.util.regex.Pattern;

public class FormattedDateMatcher {

    private static Pattern DATE_PATTERN = Pattern.compile(
            "^\\d{4}/\\d{2}/\\d{2}$");

    public static boolean matches(String date) {
        return DATE_PATTERN.matcher(date).matches();
    }

    public static boolean checkGraterThanToday(String date) {
        boolean isOk = matches(date);
        if(isOk){
            String todayDate = DateConvertorNew.todayDate();
            isOk = date.compareTo(todayDate) >= 0 ;
        }
        return isOk;
    }

}