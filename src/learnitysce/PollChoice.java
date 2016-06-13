package learnitysce;

import interfaceenginev2.*;
import org.grlea.log.*;
import javax.servlet.http.*;

public class PollChoice extends CustomEditAction
{
    public static final SimpleLogger log;
    
    public boolean AddAction(final HttpServletRequest request) {
        final boolean flag = true;
        final HttpSession mysession = request.getSession(true);
        final String pollid = (String)mysession.getAttribute("pollid");
        final String choicetext = request.getParameter("choicetext");
        SceDataBaseLayer.insertPollChoice(pollid, choicetext);
        return flag;
    }
    
    static {
        log = new SimpleLogger((Class)PollChoice.class, true);
    }
}
