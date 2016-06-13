package calendar.calendar;

import org.grlea.log.*;
import java.text.*;
import java.util.*;

public class SampleDate
{
    private static final SimpleLogger log;
    
    public String calDateFormat(final Calendar calendar) {
        String s = "" + calendar.get(1);
        String s2 = "" + calendar.get(2);
        if (s2.length() == 1) {
            s2 = "0" + s2;
        }
        s = s + "-" + s2;
        s2 = "" + calendar.get(5);
        if (s2.length() == 1) {
            s2 = "0" + s2;
        }
        return s + "-" + s2;
    }
    
    private String twoDigits(final int i) {
        final String s = "" + i;
        if (s.length() == 1) {
            return "0" + s;
        }
        return s;
    }
    
    public Calendar parseDate(final String s) {
        final String s2 = s.substring(0, 4);
        final String s3 = s.substring(4, 6);
        final String s4 = s.substring(6, 8);
        return new GregorianCalendar(Integer.parseInt(s2), Integer.parseInt(s3), Integer.parseInt(s4));
    }
    
    public String getPresentday(final Calendar calendar) {
        final String strcurrent = "" + calendar.get(1) + this.twoDigits(calendar.get(2)) + this.twoDigits(calendar.get(5));
        return strcurrent;
    }
    
    public String[] getNextPreviousCurrentYear(final Calendar calendar) {
        final String[] strCalendar = new String[3];
        final GregorianCalendar gregoriancalendar = new GregorianCalendar(calendar.get(1), calendar.get(2), 1);
        final GregorianCalendar gregoriancalendar2 = new GregorianCalendar(calendar.get(1) + 1, calendar.get(2), 1);
        final GregorianCalendar gregoriancalendar3 = new GregorianCalendar(calendar.get(1) - 1, calendar.get(2), 1);
        SampleDate.log.entry("getNextPreviousCurrentYear()");
        strCalendar[0] = "" + gregoriancalendar3.get(1) + this.twoDigits(calendar.get(2)) + this.twoDigits(calendar.get(5));
        SampleDate.log.verbose("********Show Date ***********==" + strCalendar[0]);
        strCalendar[1] = "" + gregoriancalendar.get(1) + this.twoDigits(calendar.get(2)) + this.twoDigits(calendar.get(5));
        SampleDate.log.verbose("********Show Date ****strCalendar[1]*******==" + strCalendar[1]);
        strCalendar[2] = "" + gregoriancalendar2.get(1) + this.twoDigits(calendar.get(2)) + this.twoDigits(calendar.get(5));
        SampleDate.log.verbose("********Show Date ****strCalendar[2]*******==" + strCalendar[2]);
        return strCalendar;
    }
    
    private String getNextDay(final Calendar calendar) {
        final GregorianCalendar gregoriancalendar1 = new GregorianCalendar(calendar.get(1), calendar.get(2), 1);
        final String strCalendar = "" + gregoriancalendar1.get(1) + this.twoDigits(calendar.get(2)) + this.twoDigits(calendar.get(5) + 1);
        return strCalendar;
    }
    
    public String[] getNextPreviousCurrentDate(final Calendar calendar) {
        final String[] strCalendar = new String[3];
        final GregorianCalendar gregoriancalendar = new GregorianCalendar(calendar.get(1), calendar.get(2), 1);
        final GregorianCalendar gregoriancalendar2 = new GregorianCalendar(calendar.get(1), calendar.get(2), 1);
        final GregorianCalendar gregoriancalendar3 = new GregorianCalendar(calendar.get(1), calendar.get(2), 1);
        strCalendar[0] = "" + gregoriancalendar3.get(1) + this.twoDigits(calendar.get(2)) + this.twoDigits(calendar.get(5) - 1);
        strCalendar[1] = "" + gregoriancalendar.get(1) + this.twoDigits(calendar.get(2)) + this.twoDigits(calendar.get(5));
        strCalendar[2] = "" + gregoriancalendar2.get(1) + this.twoDigits(calendar.get(2)) + this.twoDigits(calendar.get(5) + 1);
        SampleDate.log.entry("getNextPreviousCurrentDate()");
        SampleDate.log.verbose("Previous =" + gregoriancalendar3.get(1) + "  " + this.twoDigits(calendar.get(2) + 1) + " " + this.twoDigits(calendar.get(5)));
        SampleDate.log.verbose("current =" + gregoriancalendar.get(1) + this.twoDigits(calendar.get(2) + 1) + this.twoDigits(calendar.get(5)));
        SampleDate.log.verbose("next =" + gregoriancalendar2.get(1) + this.twoDigits(calendar.get(2) + 1) + this.twoDigits(calendar.get(5)));
        return strCalendar;
    }
    
    public String[] getNextPreviousCurrentWeek(final Calendar calendar) {
        final String[] strCalendar = new String[3];
        final GregorianCalendar gregoriancalendar = new GregorianCalendar(calendar.get(1), calendar.get(2), 1);
        final GregorianCalendar gregoriancalendar2 = new GregorianCalendar(calendar.get(1), calendar.get(2), 1);
        final GregorianCalendar gregoriancalendar3 = new GregorianCalendar(calendar.get(1), calendar.get(2), 1);
        strCalendar[0] = "" + gregoriancalendar3.get(1) + this.twoDigits(calendar.get(2)) + this.twoDigits(calendar.get(5) - 7);
        strCalendar[1] = "" + gregoriancalendar.get(1) + this.twoDigits(calendar.get(2)) + this.twoDigits(calendar.get(5));
        strCalendar[2] = "" + gregoriancalendar2.get(1) + this.twoDigits(calendar.get(2)) + this.twoDigits(calendar.get(5) + 7);
        return strCalendar;
    }
    
    public String[] getNextPreviousCurrentMonth(final Calendar calendar) {
        final String[] strCalendar = new String[3];
        final GregorianCalendar gregoriancalendar = new GregorianCalendar(calendar.get(1), calendar.get(2), 1);
        final GregorianCalendar gregoriancalendar2 = new GregorianCalendar(calendar.get(1), calendar.get(2), 1);
        final GregorianCalendar gregoriancalendar3 = new GregorianCalendar(calendar.get(1), calendar.get(2), 1);
        strCalendar[0] = "" + gregoriancalendar3.get(1) + this.twoDigits(calendar.get(2) - 1) + this.twoDigits(calendar.get(5));
        strCalendar[1] = "" + gregoriancalendar.get(1) + this.twoDigits(calendar.get(2)) + this.twoDigits(calendar.get(5));
        strCalendar[2] = "" + gregoriancalendar2.get(1) + this.twoDigits(calendar.get(2) + 1) + this.twoDigits(calendar.get(5));
        return strCalendar;
    }
    
    public String getDate(final Calendar calendar) {
        final SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEEEE, MMMMM d, yyyy");
        final GregorianCalendar gregoriancalendar = new GregorianCalendar(calendar.get(1), calendar.get(2), calendar.get(5));
        final String dateform = simpledateformat.format(gregoriancalendar.getTime());
        return dateform;
    }
    
    public String calDateFormat1(final Calendar calendar) {
        String s = "" + calendar.get(1);
        final int tempInt = calendar.get(2) + 1;
        String s2 = "" + Integer.toString(tempInt);
        if (s2.length() == 1) {
            s2 = "0" + s2;
        }
        s = s + "-" + s2;
        s2 = "" + calendar.get(5);
        if (s2.length() == 1) {
            s2 = "0" + s2;
        }
        return s + "-" + s2;
    }
    
    public String getDate1(final Calendar calendar) {
        final SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEEEE, MMMMM d, yyyy");
        final GregorianCalendar gregoriancalendar = new GregorianCalendar(calendar.get(1), calendar.get(2) + 1, calendar.get(5));
        final String dateform = simpledateformat.format(gregoriancalendar.getTime());
        return dateform;
    }
    
    public String[] getNextPreviousCurrentYear1(final Calendar calendar) {
        final String[] strCalendar = new String[3];
        final GregorianCalendar gregoriancalendar = new GregorianCalendar(calendar.get(1), calendar.get(2) + 1, 1);
        final GregorianCalendar gregoriancalendar2 = new GregorianCalendar(calendar.get(1) + 1, calendar.get(2) + 1, 1);
        final GregorianCalendar gregoriancalendar3 = new GregorianCalendar(calendar.get(1) - 1, calendar.get(2) + 1, 1);
        SampleDate.log.entry("getNextPreviousCurrentYear()");
        strCalendar[0] = "" + gregoriancalendar3.get(1) + this.twoDigits(gregoriancalendar3.get(2)) + this.twoDigits(gregoriancalendar3.get(5));
        SampleDate.log.verbose("********Show Date ***********==" + strCalendar[0]);
        strCalendar[1] = "" + gregoriancalendar.get(1) + this.twoDigits(gregoriancalendar.get(2)) + this.twoDigits(gregoriancalendar.get(5));
        SampleDate.log.verbose("********Show Date ****strCalendar[1]*******==" + strCalendar[1]);
        strCalendar[2] = "" + gregoriancalendar2.get(1) + this.twoDigits(gregoriancalendar2.get(2)) + this.twoDigits(gregoriancalendar2.get(5));
        SampleDate.log.verbose("********Show Date ****strCalendar[2]*******==" + strCalendar[2]);
        return strCalendar;
    }
    
    public String[] getNextPreviousCurrentDate1(final Calendar calendar) {
        final String[] strCalendar = new String[3];
        final GregorianCalendar gregoriancalendar = new GregorianCalendar(calendar.get(1), calendar.get(2) + 1, 1);
        final GregorianCalendar gregoriancalendar2 = new GregorianCalendar(calendar.get(1), calendar.get(2) + 1, 1);
        final GregorianCalendar gregoriancalendar3 = new GregorianCalendar(calendar.get(1), calendar.get(2) + 1, 1);
        strCalendar[0] = "" + gregoriancalendar3.get(1) + this.twoDigits(gregoriancalendar3.get(2)) + this.twoDigits(gregoriancalendar3.get(5) - 1);
        strCalendar[1] = "" + gregoriancalendar.get(1) + this.twoDigits(gregoriancalendar.get(2)) + this.twoDigits(gregoriancalendar.get(5));
        strCalendar[2] = "" + gregoriancalendar2.get(1) + this.twoDigits(gregoriancalendar2.get(2)) + this.twoDigits(gregoriancalendar2.get(5) + 1);
        SampleDate.log.entry("getNextPreviousCurrentDate()");
        SampleDate.log.verbose("Previous =" + gregoriancalendar3.get(1) + "  " + this.twoDigits(calendar.get(2) + 1) + " " + this.twoDigits(calendar.get(5)));
        SampleDate.log.verbose("current =" + gregoriancalendar.get(1) + this.twoDigits(calendar.get(2) + 1) + this.twoDigits(calendar.get(5)));
        SampleDate.log.verbose("next =" + gregoriancalendar2.get(1) + this.twoDigits(calendar.get(2) + 1) + this.twoDigits(calendar.get(5)));
        return strCalendar;
    }
    
    public String[] getNextPreviousCurrentWeek1(final Calendar calendar) {
        final String[] strCalendar = new String[3];
        final GregorianCalendar gregoriancalendar = new GregorianCalendar(calendar.get(1), calendar.get(2) + 1, 1);
        final GregorianCalendar gregoriancalendar2 = new GregorianCalendar(calendar.get(1), calendar.get(2) + 1, 1);
        final GregorianCalendar gregoriancalendar3 = new GregorianCalendar(calendar.get(1), calendar.get(2) + 1, 1);
        strCalendar[0] = "" + gregoriancalendar3.get(1) + this.twoDigits(gregoriancalendar3.get(2)) + this.twoDigits(gregoriancalendar3.get(5) - 7);
        strCalendar[1] = "" + gregoriancalendar.get(1) + this.twoDigits(gregoriancalendar.get(2)) + this.twoDigits(gregoriancalendar.get(5));
        strCalendar[2] = "" + gregoriancalendar2.get(1) + this.twoDigits(gregoriancalendar2.get(2)) + this.twoDigits(gregoriancalendar2.get(5) + 7);
        return strCalendar;
    }
    
    public String[] getNextPreviousCurrentMonth1(final Calendar calendar) {
        final String[] strCalendar = new String[3];
        final GregorianCalendar gregoriancalendar = new GregorianCalendar(calendar.get(1), calendar.get(2) + 1, 1);
        final GregorianCalendar gregoriancalendar2 = new GregorianCalendar(calendar.get(1), calendar.get(2) + 1, 1);
        final GregorianCalendar gregoriancalendar3 = new GregorianCalendar(calendar.get(1), calendar.get(2) + 1, 1);
        strCalendar[0] = "" + gregoriancalendar3.get(1) + this.twoDigits(gregoriancalendar3.get(2) - 1) + this.twoDigits(gregoriancalendar3.get(5));
        strCalendar[1] = "" + gregoriancalendar.get(1) + this.twoDigits(gregoriancalendar.get(2)) + this.twoDigits(gregoriancalendar.get(5));
        strCalendar[2] = "" + gregoriancalendar2.get(1) + this.twoDigits(gregoriancalendar2.get(2) + 1) + this.twoDigits(gregoriancalendar2.get(5));
        return strCalendar;
    }
    
    public Vector monthData1(final String strdate) {
        final Vector vMonth = new Vector(3, 3);
        final String[] strdays = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        final Calendar calendar = this.parseDate(strdate);
        final int days = this.getDaysInMonth(calendar);
        final int currentDays = this.getDays(calendar);
        final Calendar gregoriancalendar1 = new GregorianCalendar(calendar.get(1), calendar.get(2) + 1, 1);
        final String strdayName = this.getdayName(gregoriancalendar1);
        int position = 0;
        for (int i = 0; i < strdays.length; ++i) {
            if (strdayName.equalsIgnoreCase(strdays[i])) {
                position = i;
                break;
            }
        }
        final String[] strMonthInfo = { "" + days, "" + currentDays };
        vMonth.addElement(strMonthInfo);
        String newweekday = "" + gregoriancalendar1.get(1) + this.twoDigits(gregoriancalendar1.get(2)) + this.twoDigits(gregoriancalendar1.get(5) - position);
        int temp = position;
        int count = 0;
        while (count < days + position) {
            final Vector vWeekDays = new Vector(7, 7);
            for (int j = 0; j < 7 && count < days + position; ++count, ++j) {
                final String[] strday = new String[2];
                final Calendar calendar2 = this.parseDate(newweekday);
                if (j < temp) {
                    strday[1] = (strday[0] = "");
                }
                else {
                    temp = 0;
                    strday[0] = "" + this.getDays(calendar2);
                    strday[1] = newweekday;
                }
                newweekday = this.getNextDay(calendar2);
                if (j == 0) {
                    final String[] weeks = { "wk-" + calendar2.get(3), newweekday };
                    vWeekDays.addElement(weeks);
                }
                vWeekDays.addElement(strday);
            }
            vMonth.addElement(vWeekDays);
        }
        return vMonth;
    }
    
    public int getDays(final Calendar calendar) {
        return calendar.get(5);
    }
    
    public int getMonth(final Calendar calendar) {
        return calendar.get(2);
    }
    
    public int getYear(final Calendar calendar) {
        return calendar.get(1);
    }
    
    private boolean isLeapYear(final int Year) {
        return (Year % 4 == 0 && Year % 100 != 0) || Year % 400 == 0;
    }
    
    private String getdayName(final Calendar calendar) {
        final SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEEEE");
        final GregorianCalendar gregoriancalendar = new GregorianCalendar(calendar.get(1), calendar.get(2), calendar.get(5));
        final String dateform = simpledateformat.format(gregoriancalendar.getTime());
        return dateform;
    }
    
    public int getDaysInMonth(final Calendar calDate) {
        int days = 0;
        final int month = this.getMonth(calDate) + 1;
        final int year = this.getYear(calDate);
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            days = 31;
        }
        else if (month == 4 || month == 6 || month == 9 || month == 11) {
            days = 30;
        }
        else if (month == 2) {
            if (this.isLeapYear(year)) {
                days = 29;
            }
            else {
                days = 28;
            }
        }
        return days;
    }
    
    public Vector<String[]> weekData(final String strdate) {
        final Vector<String[]> vWeekDays = new Vector<String[]>(7, 7);
        final String[] strdays = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        final Calendar calendar = this.parseDate(strdate);
        final String strdayName = this.getdayName(calendar);
        int count = 0;
        for (int i = 0; i < strdays.length; ++i) {
            if (strdayName.equalsIgnoreCase(strdays[i])) {
                count = i;
                break;
            }
        }
        final GregorianCalendar gregoriancalendar1 = new GregorianCalendar(calendar.get(1), calendar.get(2), 1);
        String newweekday = "" + gregoriancalendar1.get(1) + this.twoDigits(calendar.get(2)) + this.twoDigits(calendar.get(5) - count);
        for (int j = 0; j < 7; ++j) {
            final String[] strday = new String[2];
            final Calendar calendar2 = this.parseDate(newweekday);
            strday[0] = "" + this.getDays(calendar2);
            strday[1] = newweekday;
            newweekday = this.getNextDay(calendar2);
            vWeekDays.addElement(strday);
        }
        return vWeekDays;
    }
    
    public Vector<String[]> monthData(final String strdate) {
        final Vector vMonth = new Vector(3, 3);
        final String[] strdays = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        final Calendar calendar = this.parseDate(strdate);
        final int days = this.getDaysInMonth(calendar);
        final int currentDays = this.getDays(calendar);
        final Calendar gregoriancalendar1 = new GregorianCalendar(calendar.get(1), calendar.get(2), 1);
        final String strdayName = this.getdayName(gregoriancalendar1);
        int position = 0;
        for (int i = 0; i < strdays.length; ++i) {
            if (strdayName.equalsIgnoreCase(strdays[i])) {
                position = i;
                break;
            }
        }
        final String[] strMonthInfo = { "" + days, "" + currentDays };
        vMonth.addElement(strMonthInfo);
        String newweekday = "" + gregoriancalendar1.get(1) + this.twoDigits(gregoriancalendar1.get(2)) + this.twoDigits(gregoriancalendar1.get(5) - position);
        int temp = position;
        int count = 0;
        while (count < days + position) {
            final Vector vWeekDays = new Vector(7, 7);
            for (int j = 0; j < 7 && count < days + position; ++count, ++j) {
                final String[] strday = new String[2];
                final Calendar calendar2 = this.parseDate(newweekday);
                if (j < temp) {
                    strday[1] = (strday[0] = "");
                }
                else {
                    temp = 0;
                    strday[0] = "" + this.getDays(calendar2);
                    strday[1] = newweekday;
                }
                newweekday = this.getNextDay(calendar2);
                if (j == 0) {
                    final String[] weeks = { "wk-" + calendar2.get(3), newweekday };
                    vWeekDays.addElement(weeks);
                }
                vWeekDays.addElement(strday);
            }
            vMonth.addElement(vWeekDays);
        }
        return vMonth;
    }
    
    public Vector yearData(final String strdata) {
        final Vector vFullMonthYear = new Vector(3, 3);
        final String[] months = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        final Calendar calendar1 = this.parseDate(strdata);
        for (int i = 0; i < 12; ++i) {
            final Vector vMonth = new Vector(3, 3);
            final String[] strMonth = new String[2];
            final String newMonth = "" + calendar1.get(1) + this.twoDigits(i) + this.twoDigits(1);
            strMonth[0] = months[i];
            strMonth[1] = newMonth;
            vMonth.addElement(strMonth);
            final Vector vMonthData = this.monthData(newMonth);
            vMonth.addElement(vMonthData);
            vFullMonthYear.addElement(vMonth);
        }
        return vFullMonthYear;
    }
    
    static {
        log = new SimpleLogger((Class)SampleDate.class);
    }
}
