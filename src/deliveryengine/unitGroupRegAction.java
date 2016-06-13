package deliveryengine;

import interfaceenginev2.*;
import org.grlea.log.*;
import javax.servlet.http.*;
import learnityInit.*;
import org.adl.parsers.dom.*;
import java.util.*;

import org.adl.samplerte.util.*;
import java.io.*;

public class unitGroupRegAction extends CustomEditAction
{
    public static final SimpleLogger log;
    
    public boolean AddAction(final HttpServletRequest httpServletRequest) {
        final boolean b = false;
        final String parameter = httpServletRequest.getParameter("group_name");
        System.out.println(" ======group_id===onselectTitle=====" + parameter);
        final String parameter2 = httpServletRequest.getParameter("course_id");
        final String parameter3 = httpServletRequest.getParameter("access_allowed_till");
        String s;
        if (parameter3.equals("") || parameter3 == null) {
            s = "NULL";
        }
        else {
            s = parameter3;
        }
        final String parameter4 = httpServletRequest.getParameter("total_access_time");
        final String s2 = (String)httpServletRequest.getSession().getAttribute("user_id");
        System.out.println(" ======userId===onselectTitle=====" + s2);
        if (DataBaseLayer.isForumExists(parameter2)) {
            DataBaseLayer.registerForumGroup(parameter2, parameter, s2, s);
        }
        DataBaseLayer.insertCourseGroupAssociation(parameter, parameter2, s2, s, parameter4);
        final Vector<Vector<String>> userGroupDetailsList = DataBaseLayer.getUserGroupDetailsList(parameter);
        if (userGroupDetailsList != null && userGroupDetailsList.size() > 0) {
            try {
                final FileInputStream fileInputStream = new FileInputStream(Host.getServerDocumentPath() + File.separatorChar + parameter2 + File.separatorChar + "sequence.obj");
                final ADLOrganizations adlOrganizations = (ADLOrganizations)new ObjectInputStream(fileInputStream).readObject();
                fileInputStream.close();
                for (int i = 0; i < userGroupDetailsList.size(); ++i) {
                    final String userID = userGroupDetailsList.elementAt(i).elementAt(0);
                    final RTEFileHandler rteFileHandler = new RTEFileHandler();
                    rteFileHandler.setUserID(userID);
                    rteFileHandler.setCourseID(parameter2);
                    rteFileHandler.initializeCourseFiles(Host.getServerDocumentPath());
                    final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(Host.getServerDocumentPath() + File.separatorChar + parameter2 + File.separatorChar + "sequence." + userID)));
                    objectOutputStream.writeObject(adlOrganizations);
                    objectOutputStream.flush();
                    objectOutputStream.close();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return b;
    }
    
    public boolean EditAction(final HttpServletRequest httpServletRequest) {
        final boolean b = false;
        final String parameter = httpServletRequest.getParameter("group_name");
        System.out.println(" ======group_id===onselectTitle=====" + parameter);
        final String parameter2 = httpServletRequest.getParameter("course_id");
        final String parameter3 = httpServletRequest.getParameter("access_allowed_till");
        String s;
        if (parameter3.equals("") || parameter3 == null) {
            s = "NULL";
        }
        else {
            s = parameter3;
        }
        final String parameter4 = httpServletRequest.getParameter("total_access_time");
        final String parameter5 = httpServletRequest.getParameter("current_login_user_id");
        System.out.println(" ======userId===onselectTitle=====" + parameter5);
        DataBaseLayer.modifyCourseGroupAssociation(parameter, parameter2, parameter5, s, parameter4);
        return b;
    }
    
    static {
        log = new SimpleLogger((Class)unitGroupRegAction.class, true);
    }
}
