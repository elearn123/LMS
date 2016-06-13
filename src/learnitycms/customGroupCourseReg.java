package learnitycms;

import interfaceenginev2.*;
import org.grlea.log.*;
import javax.servlet.http.*;
import java.util.*;

public class customGroupCourseReg extends CustomEditAction
{
    public static final SimpleLogger log;
    
    public boolean AddAction(final HttpServletRequest httpServletRequest) {
        final boolean b = true;
        final String s = (String)httpServletRequest.getSession(true).getAttribute("user_id");
        final String parameter = httpServletRequest.getParameter("course_id");
        final String parameter2 = httpServletRequest.getParameter("group_id");
        final String parameter3 = httpServletRequest.getParameter("total_access_time");
        String parameter4 = httpServletRequest.getParameter("access_allowed_till");
        if (parameter4.equals("") || parameter4 == null) {
            parameter4 = "NULL";
        }
        final Vector<String> stringtoVector = this.StringtoVector(parameter2);
        System.out.println("=========group_vector=======" + stringtoVector);
        for (int i = 0; i < stringtoVector.size(); ++i) {
            DataBaseLayer.insertGroupCourseReg(parameter, stringtoVector.elementAt(i), parameter3, parameter4, s);
        }
        return b;
    }
    
    public boolean EditAction(final HttpServletRequest httpServletRequest) {
        System.out.println("===========INSIDE EditAction in ======== ");
        final boolean b = true;
        final String s = (String)httpServletRequest.getSession(true).getAttribute("user_id");
        final String parameter = httpServletRequest.getParameter("course_id");
        final String parameter2 = httpServletRequest.getParameter("group_id");
        final String parameter3 = httpServletRequest.getParameter("total_access_time");
        String parameter4 = httpServletRequest.getParameter("access_allowed_till");
        if (parameter4.equals("") || parameter4 == null) {
            parameter4 = "NULL";
        }
        DataBaseLayer.modifyGroupCourseReg(parameter, parameter2, parameter3, parameter4, s);
        return b;
    }
    
    public Vector StringtoVector(String substring) {
        int n = 0;
        final Vector<String> vector = new Vector<String>();
        for (int i = 0; i < substring.length(); ++i) {
            if (substring.charAt(i) == ',') {
                ++n;
            }
        }
        if (n == 0) {
            vector.addElement(substring);
        }
        else {
            for (int j = 0; j < n; ++j) {
                final String substring2 = substring.substring(0, substring.indexOf(","));
                substring = substring.substring(substring.indexOf(",") + 1);
                vector.addElement(substring2);
            }
            vector.addElement(substring);
        }
        System.out.println("==============vv===========" + vector);
        return vector;
    }
    
    static {
        log = new SimpleLogger((Class)customGroupCourseReg.class, true);
    }
}
