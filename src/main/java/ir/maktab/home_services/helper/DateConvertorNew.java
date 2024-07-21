package ir.maktab.home_services.helper;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


public class DateConvertorNew {


    public static String convertMiToKh(int year, int month, int day) {
        int[] khMonth =
                {0, 31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29};
        int[] mMonth =
                {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (((year % 4) == 0) && (!(((year % 100) == 0) && ((year % 400) != 0)))) {
            mMonth[2] = 29;
        }
        int addmonth = 0;
        for (int x = 1; x < month; x++) {
            addmonth += mMonth[x];
        }
        int year2 = 0;
        for (int z = 1; z < year; z++) {
            if (((z % 4) == 0) && (!(((z % 100) == 0) && ((z % 400) != 0)))) {
                year2 += 366;
                continue;
            } else {
                year2 += 365;
                continue;
            }
        }

        int total = year2 + addmonth + day - 226895;
        int h = 1;
        for (h = 1; total >= 366; h++) {
            if (((((((h - ((h > 0) ? 474 : 473)) % 2820) + 474) + 38) * 682) % 2816) < 682) {
                total = total - 366;
            } else {
                total = total - 365;
            }
        }
        int newYear = h;
        if (total == 0) {
            total = 365;
            newYear = newYear - 1;
        }
        int newMonth = 0;
        String newMonth1 = "";
        int newDay = 0;
        String newDay1 = "";
        int allMonth = 0;
        int allMonth1 = 0;
        for (int l = 0; l < khMonth.length - 1; l++) {
            if (((((((newYear - ((newYear > 0) ? 474 : 473)) % 2820) + 474) + 38) * 682) % 2816) < 682) {
                khMonth[12] = 30;
            }
            allMonth += khMonth[l];
            allMonth1 += khMonth[l + 1];
            if (total > allMonth && total <= allMonth1) {
                newMonth = l + 1;
                newDay = total - allMonth;
            }
        }
        newMonth1 = "" + newMonth;
        newDay1 = "" + newDay;
        if (newMonth < 10)
            newMonth1 = "0" + newMonth;
        if (newDay < 10)
            newDay1 = "0" + newDay;
        String finalDate = newYear + "/" + newMonth1 + "/" + newDay1;
        return finalDate;
    }

    public static String convertKhToMi(int year, int month, int day) {
        int[] khMonth =
                {0, 31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29};
        int[] mMonth =
                {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (((((((year - ((year > 0) ? 474 : 473)) % 2820) + 474) + 38) * 682) % 2816) < 682) {
            khMonth[12] = 30;
        }
        int addmonth = 0;
        for (int x = 1; x < month; x++) {
            addmonth += khMonth[x];
        }
        int year2 = 0;
        for (int z = 1; z < year; z++) {
            if (((((((z - ((z > 0) ? 474 : 473)) % 2820) + 474) + 38) * 682) % 2816) < 682) {
                year2 += 366;
                continue;
            } else {
                year2 += 365;
                continue;
            }
        }
        int total = year2 + addmonth + day + 226895;
        int h = 1;
        for (h = 1; total >= 367; h++) {
            if (((h % 4) == 0) && (!(((h % 100) == 0) && ((h % 400) != 0)))) {
                total = total - 366;
            } else {
                total = total - 365;
            }
        }
        int newYear = h;
        int day1 = total;
        if (day1 == 0) {
            day1 = 365;
            newYear = newYear - 1;
        }
        int newMonth = 0;
        int newDay = 0;
        int allMonth = 0;
        int allMonth1 = 0;
        int l = 0;
        for (l = 0; l < mMonth.length - 1; l++) {
            if (((newYear % 4) == 0) && (!(((newYear % 100) == 0) && ((newYear % 400) != 0)))) {
                mMonth[2] = 29;
            }
            allMonth += mMonth[l];
            allMonth1 += mMonth[l + 1];
            if (day1 > allMonth && day1 <= allMonth1) {
                newMonth = l + 1;
                newDay = day1 - allMonth;
            }
        }
        String mo = "";
        if (newMonth < 10) {
            mo = "0" + newMonth;
        } else
            mo = String.valueOf(newMonth);
        String tempDay = "";
        if (newDay < 10) {
            tempDay = "0" + newDay;
        } else
            tempDay = new Long(newDay).toString();
        String finalDate = newYear + "-" + mo + "-" + tempDay;
        return finalDate;
    }

    public static String todayDate() {
        Calendar c = Calendar.getInstance();
        Date todayDate = new Date(c.getTimeInMillis());
        String sDate = new String(todayDate.toString());
        String[] mDate = sDate.split("-");
        String khDate = convertMiToKh(new Integer(mDate[0]).intValue(), new Integer(mDate[1]).intValue(), new Integer(mDate[2]).intValue());
        return khDate;
    }

    public static String dateAdd(String dateInKh, IntervalTypes intervalType, int interval) {
        String[] khDates = null;
        if (dateInKh.indexOf("-") > 0)
            khDates = dateInKh.split("-");
        else
            khDates = dateInKh.split("/");

        String miDate = convertKhToMi(Integer.valueOf(khDates[0]), Integer.valueOf(khDates[1]) , Integer.valueOf(khDates[2]));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(miDate, formatter);
        if (intervalType.equals(IntervalTypes.DAY))
            date = date.plusDays(interval);
        else if (intervalType.equals(IntervalTypes.MONTH))
            date = date.plusMonths(interval);
        else if (intervalType.equals(IntervalTypes.YEAR))
            date = date.plusYears(interval);

        String sDate = new String(date.toString());
        String[] mDate = sDate.split("-");
        String khDate = convertMiToKh(new Integer(mDate[0]).intValue(), new Integer(mDate[1]).intValue(), new Integer(mDate[2]).intValue());
        return khDate;
    }

    public enum IntervalTypes {
        DAY,
        MONTH,
        YEAR
    }

}

