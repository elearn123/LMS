package learnitycms;

import interfaceenginev2.*;
import org.grlea.log.*;

import comv2.aunwesha.lfutil.Pair;

import javax.servlet.http.*;

public class CmsDateValidator implements ValidatorFunction
{
    public static final SimpleLogger log;
    
    public Pair<Boolean,String> validateadd(final HttpServletRequest request) {
        Boolean validity = false;
        String message = "";
        final String startdate = request.getParameter("sdate");
        final String enddate = request.getParameter("edate");
        System.out.println("===========startdate============" + startdate);
        System.out.println("===========enddate============" + enddate);
        final String year_startdate = startdate.substring(0, startdate.indexOf("-"));
        final String year_enddate = enddate.substring(0, enddate.indexOf("-"));
        final String remaining_startdate = startdate.substring(startdate.indexOf("-") + 1);
        final String remaining_enddate = enddate.substring(enddate.indexOf("-") + 1);
        System.out.println("===========remaining_startdate============" + remaining_startdate);
        System.out.println("===========remaining_enddate============" + remaining_enddate);
        final String month_startdate = remaining_startdate.substring(0, remaining_startdate.indexOf("-"));
        final String month_enddate = remaining_enddate.substring(0, remaining_enddate.indexOf("-"));
        final String day_startdate = remaining_startdate.substring(remaining_startdate.indexOf("-") + 1);
        final String day_enddate = remaining_enddate.substring(remaining_enddate.indexOf("-") + 1);
        System.out.println("===========day_startdate============" + day_startdate);
        System.out.println("===========day_enddate============" + day_enddate);
        System.out.println("===========month_startdate============" + month_startdate);
        System.out.println("===========month_enddate============" + month_enddate);
        System.out.println("===========year_startdate============" + year_startdate);
        System.out.println("===========year_enddate============" + year_enddate);
        final int startdate_day = Integer.parseInt(day_startdate);
        final int enddate_day = Integer.parseInt(day_enddate);
        final int startdate_month = Integer.parseInt(month_startdate);
        final int enddate_month = Integer.parseInt(month_enddate);
        final int startdate_year = Integer.parseInt(year_startdate);
        final int enddate_year = Integer.parseInt(year_enddate);
        if (startdate_year > enddate_year) {
            validity = false;
            message = "Year of End Date must be greater than or equal to year of Start Date";
        }
        else if (startdate_month > enddate_month && startdate_year == enddate_year) {
            validity = false;
            message = "Month of End Date must be greater than or equal to month of Start Date startdate_year" + startdate_year + "enddate_year" + enddate_year;
        }
        else if (startdate_day > enddate_day && startdate_month == enddate_month && startdate_year == enddate_year) {
            validity = false;
            message = "End Date must be greater than Start Date";
        }
        else {
            validity = true;
            message = "OK";
        }
        return new Pair<Boolean,String>(validity, message);
    }
    
    public Pair<Boolean,String> validateedit(final HttpServletRequest request) {
        Boolean validity = false;
        String message = "";
        final String startdate = request.getParameter("sdate");
        final String enddate = request.getParameter("edate");
        System.out.println("===========startdate============" + startdate);
        System.out.println("===========enddate============" + enddate);
        final String year_startdate = startdate.substring(0, startdate.indexOf("-"));
        final String year_enddate = enddate.substring(0, enddate.indexOf("-"));
        final String remaining_startdate = startdate.substring(startdate.indexOf("-") + 1);
        final String remaining_enddate = enddate.substring(enddate.indexOf("-") + 1);
        System.out.println("===========remaining_startdate============" + remaining_startdate);
        System.out.println("===========remaining_enddate============" + remaining_enddate);
        final String month_startdate = remaining_startdate.substring(0, remaining_startdate.indexOf("-"));
        final String month_enddate = remaining_enddate.substring(0, remaining_enddate.indexOf("-"));
        final String day_startdate = remaining_startdate.substring(remaining_startdate.indexOf("-") + 1);
        final String day_enddate = remaining_enddate.substring(remaining_enddate.indexOf("-") + 1);
        System.out.println("===========day_startdate============" + day_startdate);
        System.out.println("===========day_enddate============" + day_enddate);
        System.out.println("===========month_startdate============" + month_startdate);
        System.out.println("===========month_enddate============" + month_enddate);
        System.out.println("===========year_startdate============" + year_startdate);
        System.out.println("===========year_enddate============" + year_enddate);
        final int startdate_day = Integer.parseInt(day_startdate);
        final int enddate_day = Integer.parseInt(day_enddate);
        final int startdate_month = Integer.parseInt(month_startdate);
        final int enddate_month = Integer.parseInt(month_enddate);
        final int startdate_year = Integer.parseInt(year_startdate);
        final int enddate_year = Integer.parseInt(year_enddate);
        if (startdate_year > enddate_year) {
            validity = false;
            message = "Year of End Date must be greater than or equal to year of Start Date";
        }
        else if (startdate_month > enddate_month && startdate_year == enddate_year) {
            validity = false;
            message = "Month of End Date must be greater than or equal to month of Start Date";
        }
        else if (startdate_day > enddate_day && startdate_month == enddate_month && startdate_year == enddate_year) {
            validity = false;
            message = "End Date must be greater than Start Date";
        }
        else {
            validity = true;
            message = "OK";
        }
        return new Pair<Boolean,String>(validity, message);
    }

public Pair<Boolean,String> validatedelete(HttpServletRequest request)
	{return new Pair<Boolean,String>(false, "");}
    
    static {
        log = new SimpleLogger((Class)CmsDateValidator.class, true);
    }
}
