package learnitycms;

import interfaceenginev2.*;
import org.grlea.log.*;
import javax.servlet.http.*;

public class AssignmentTeacherAction extends CustomEditAction
{
    public static final SimpleLogger log;
    
    public boolean AddAction(final HttpServletRequest request) {
        final boolean flag = true;
        final HttpSession mysession = request.getSession(true);
        final String user_id = (String)mysession.getAttribute("user_id");
        final String course_id = (String)mysession.getAttribute("course_id");
        final String assign_title = request.getParameter("title");
        final String description = request.getParameter("description");
        String submission_last_date = request.getParameter("submission_last_date");
        String start_avail_date = request.getParameter("start_avail_date");
        String end_avail_date = request.getParameter("end_avail_date");
        final String lateflag = request.getParameter("lateflag");
        String late_allowed_till = request.getParameter("late_allowed_till");
        final String penalty_for_late = request.getParameter("penalty_for_late");
        final String full_mark = request.getParameter("full_mark");
        final String pass_mark = request.getParameter("pass_mark");
        if (end_avail_date.equals("") || end_avail_date == null) {
            end_avail_date = "NULL";
        }
        else {
            end_avail_date = "STR_TO_DATE('" + end_avail_date + "','%d-%m-%Y')";
        }
        if (start_avail_date.equals("") || start_avail_date == null) {
            start_avail_date = "NULL";
        }
        else {
            start_avail_date = "STR_TO_DATE('" + start_avail_date + "','%d-%m-%Y')";
        }
        if (submission_last_date.equals("") || submission_last_date == null) {
            submission_last_date = "NULL";
        }
        else {
            submission_last_date = "STR_TO_DATE('" + submission_last_date + "','%d-%m-%Y')";
        }
        if (late_allowed_till.equals("") || late_allowed_till == null) {
            late_allowed_till = "NULL";
        }
        else {
            late_allowed_till = "STR_TO_DATE('" + late_allowed_till + "','%d-%m-%Y')";
        }
        DataBaseLayer.insertAssignmentByTeacher(course_id, assign_title, description, submission_last_date, start_avail_date, end_avail_date, lateflag, late_allowed_till, penalty_for_late, full_mark, pass_mark, user_id);
        return flag;
    }
    
    static {
        log = new SimpleLogger((Class)AssignmentTeacherAction.class, true);
    }
}
