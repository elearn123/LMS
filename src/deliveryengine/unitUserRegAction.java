package deliveryengine;

import interfaceenginev2.*;
import org.grlea.log.*;
import javax.servlet.http.*;
import org.adl.samplerte.util.*;
import learnityInit.*;
import org.adl.parsers.dom.*;
import java.io.*;

public class unitUserRegAction extends CustomEditAction
{
    public static final SimpleLogger log;
    
    public boolean AddAction(final HttpServletRequest httpServletRequest) {
        final boolean b = false;
        final String parameter = httpServletRequest.getParameter("student_id");
        final String parameter2 = httpServletRequest.getParameter("course_id");
        final String parameter3 = httpServletRequest.getParameter("access_allowed_till");
        String s;
        if (parameter3.equals("")) {
            s = "NULL";
        }
        else {
            s = parameter3;
        }
        final String parameter4 = httpServletRequest.getParameter("total_access_time");
        final String s2 = (String)httpServletRequest.getSession().getAttribute("user_id");
        System.out.println(" ======userId===onselectTitle=====" + s2);
        DataBaseLayer.insertCourseUserAssociation(parameter, parameter2, s2, s, parameter4);
        final RTEFileHandler rteFileHandler = new RTEFileHandler();
        rteFileHandler.setUserID(parameter);
        rteFileHandler.setCourseID(parameter2);
        rteFileHandler.initializeCourseFiles(Host.getServerDocumentPath());
        try {
            final FileInputStream fileInputStream = new FileInputStream(Host.getServerDocumentPath() + File.separatorChar + parameter2 + File.separatorChar + "sequence.obj");
            final ADLOrganizations adlOrganizations = (ADLOrganizations)new ObjectInputStream(fileInputStream).readObject();
            fileInputStream.close();
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(Host.getServerDocumentPath() + File.separatorChar + parameter2 + File.separatorChar + "sequence." + parameter)));
            objectOutputStream.writeObject(adlOrganizations);
            objectOutputStream.flush();
            objectOutputStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }
    
    public boolean EditAction(final HttpServletRequest httpServletRequest) {
        final boolean b = false;
        final String parameter = httpServletRequest.getParameter("student_id");
        final String parameter2 = httpServletRequest.getParameter("course_id");
        final String parameter3 = httpServletRequest.getParameter("access_allowed_till");
        String s;
        if (parameter3.equals("")) {
            s = "NULL";
        }
        else {
            s = parameter3;
        }
        final String parameter4 = httpServletRequest.getParameter("total_access_time");
        final String s2 = (String)httpServletRequest.getSession().getAttribute("user_id");
        System.out.println(" ======userId===onselectTitle=====" + s2);
        DataBaseLayer.modifyCourseUserAssociation(parameter, parameter2, s2, s, parameter4);
        return b;
    }
    
    static {
        log = new SimpleLogger((Class)unitUserRegAction.class, true);
    }
}
