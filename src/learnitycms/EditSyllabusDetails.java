package learnitycms;

import interfaceenginev2.*;
import org.grlea.log.*;
import javax.servlet.http.*;

public class EditSyllabusDetails extends CustomEditAction
{
    public static final SimpleLogger log;
    
    public boolean AddAction(final HttpServletRequest request) {
        final boolean flag = true;
        final HttpSession mysession = request.getSession(true);
        final String user_id = (String)mysession.getAttribute("user_id");
        final String course_id = (String)mysession.getAttribute("course_id");
        final String topic_id = (String)mysession.getAttribute("top_id");
        final String description = request.getParameter("description");
        final String mtype = request.getParameter("mtype");
        final String file_name = request.getParameter("file_name");
        System.out.println("====course_id===topic_id======" + course_id + topic_id);
        DataBaseLayer.insertSyllabusDetails(course_id, topic_id, file_name, description, mtype, user_id);
        return flag;
    }
    
    public boolean EditAction(final HttpServletRequest request) {
        System.out.println("===========INSIDE EditAction in ======== ");
        final boolean flag = true;
        final HttpSession mysession = request.getSession(true);
        final String user_id = (String)mysession.getAttribute("user_id");
        final String course_id = (String)mysession.getAttribute("course_id");
        final String topic_id = (String)mysession.getAttribute("top_id");
        final String topmatid = request.getParameter("topicmatid");
        final String description = request.getParameter("description");
        final String mtype = request.getParameter("mtype");
        final String file_name = request.getParameter("file_name");
        DataBaseLayer.updateSyllabusDetails(topmatid, course_id, topic_id, file_name, description, mtype, user_id);
        return flag;
    }
    
    public boolean DeleteAction(final HttpServletRequest request) {
        final boolean flag = true;
        final HttpSession mysession = request.getSession(true);
        final String course_id = (String)mysession.getAttribute("course_id");
        final String topic_id = (String)mysession.getAttribute("top_id");
        final String topmatid = request.getParameter("topicmatid");
        DataBaseLayer.deleteSyllabusDetails(course_id, topic_id, topmatid);
        return flag;
    }
    
    static {
        log = new SimpleLogger((Class)EditSyllabusDetails.class, true);
    }
}
