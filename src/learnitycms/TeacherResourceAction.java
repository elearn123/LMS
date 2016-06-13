package learnitycms;

import interfaceenginev2.*;
import org.grlea.log.*;
import javax.servlet.http.*;

public class TeacherResourceAction extends CustomEditAction
{
    public static final SimpleLogger log;
    
    public boolean AddAction(final HttpServletRequest request) {
        final boolean flag = true;
        final HttpSession mysession = request.getSession(true);
        final String user_id = (String)mysession.getAttribute("user_id");
        final String course_id = (String)mysession.getAttribute("course_id");
        final String material_name = request.getParameter("material_name");
        final String mtype = request.getParameter("mtype");
        final String size = request.getParameter("size");
        final String material = request.getParameter("material");
        final String description = request.getParameter("description");
        DataBaseLayer.insertMaterialTeacherDetails(course_id, material_name, mtype, size, material, user_id, description);
        return flag;
    }
    
    public boolean EditAction(final HttpServletRequest request) {
        System.out.println("===========INSIDE EditAction in ======== ");
        final boolean flag = true;
        final HttpSession mysession = request.getSession(true);
        final String user_id = (String)mysession.getAttribute("user_id");
        final String course_id = (String)mysession.getAttribute("course_id");
        final String material_name = request.getParameter("material_name");
        final String mtype = request.getParameter("mtype");
        final String size = request.getParameter("size");
        final String material = request.getParameter("material");
        final String description = request.getParameter("description");
        final String material_id = request.getParameter("material_id");
        DataBaseLayer.updateMaterialTeacherDetails(course_id, material_name, mtype, size, material, description, material_id, user_id);
        return flag;
    }
    
    public boolean DeleteAction(final HttpServletRequest request) {
        final boolean flag = true;
        final HttpSession mysession = request.getSession(true);
        final String user_id = (String)mysession.getAttribute("user_id");
        final String course_id = (String)mysession.getAttribute("course_id");
        final String material_id = request.getParameter("material_id");
        DataBaseLayer.deleteMaterialTeacherDetails(course_id, material_id);
        return flag;
    }
    
    static {
        log = new SimpleLogger((Class)TeacherResourceAction.class, true);
    }
}
