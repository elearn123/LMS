package learnitycms;

import interfaceenginev2.*;
import org.grlea.log.*;
import javax.servlet.http.*;
import java.util.*;

public class InsertInUserSCOInfo extends CustomEditAction
{
    public static final SimpleLogger log;
    
    public boolean AddAction(final HttpServletRequest request) {
        System.out.println("INSIDE AddAction in InsertInUserSCOInfo");
        final boolean flag = true;
        //final HttpSession mysession = request.getSession(true);
        final String student_id = request.getParameter("student_id");
        System.out.println("=============student_id=======" + student_id);
        final Vector<String> user_vector = this.StringtoVector(student_id);
        System.out.println("=========user_vector=======" + user_vector);
        final String course_id = request.getParameter("course_id");
        for (int i = 0; i < user_vector.size(); ++i) {
            final String u_id = user_vector.elementAt(i);
            System.out.println("====course_id===student_id======" + course_id + student_id);
            DataBaseLayer.insertUserSCOInfo(course_id, u_id);
        }
        return flag;
    }
    
    public boolean DeleteAction(final HttpServletRequest request) {
        System.out.println("INSIDE DeleteAction in InsertInUserSCOInfo");
        final boolean flag = true;
        //final HttpSession mysession = request.getSession(true);
        final String student_id = request.getParameter("student_id");
        final String course_id = request.getParameter("course_id");
        System.out.println("====course_id===student_id======" + course_id + student_id);
        DataBaseLayer.deleteUserSCOInfo(course_id, student_id);
        return flag;
    }
    
    public Vector<String> StringtoVector(String StringtoConvert) {
        int index = 0;
        final Vector<String> vv = new Vector<String>();
        //final String remain_string = "";
        String table_name = "";
        for (int i = 0; i < StringtoConvert.length(); ++i) {
            if (StringtoConvert.charAt(i) == ',') {
                ++index;
            }
        }
        if (index == 0) {
            vv.addElement(StringtoConvert);
        }
        else {
            for (int j = 0; j < index; ++j) {
                table_name = StringtoConvert.substring(0, StringtoConvert.indexOf(","));
                StringtoConvert = StringtoConvert.substring(StringtoConvert.indexOf(",") + 1);
                vv.addElement(table_name);
            }
            vv.addElement(StringtoConvert);
        }
        System.out.println("==============vv===========" + vv);
        return vv;
    }
    
    static {
        log = new SimpleLogger((Class)InsertInUserSCOInfo.class, true);
    }
}
