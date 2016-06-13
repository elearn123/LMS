package learnitysce;

import interfaceenginev2.*;
import org.grlea.log.*;
import javax.servlet.http.*;

public class PollDefinition extends CustomEditAction
{
    public static final SimpleLogger log;
    
    public boolean AddAction(final HttpServletRequest request) {
        final boolean flag = true;
        final HttpSession mysession = request.getSession(true);
        final String sce_id = (String)mysession.getAttribute("sce_id");
        final String polltitle = request.getParameter("polltitle");
        final String polltext = request.getParameter("polltext");
        final String ismcchoice = request.getParameter("ismcchoice");
        SceDataBaseLayer.insertPollDefinition(sce_id, polltitle, polltext, ismcchoice);
        return flag;
    }
    
    static {
        log = new SimpleLogger((Class)PollDefinition.class, true);
    }
}
