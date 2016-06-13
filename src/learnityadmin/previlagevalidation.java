package learnityadmin;

import interfaceenginev2.*;
import org.grlea.log.*;

import comv2.aunwesha.lfutil.Pair;

import javax.servlet.http.*;

public class previlagevalidation implements ValidatorFunction
{
    public static final SimpleLogger log;
    
    public Pair<Boolean,String> validatedelete(final HttpServletRequest request) {
        Boolean b = false;
        String message = "";
        final HttpSession mysession = request.getSession(true);
        final String strAdminId = (String)mysession.getAttribute("user_id");
        System.out.println("=strAdminId=nnnn==========" + strAdminId);
        final String strAdministratorPreviledge = DataBaseLayer.getAdminstratorPreviledge(strAdminId);
        if (strAdministratorPreviledge != null && strAdministratorPreviledge.charAt(19) == 'T') {
            b = true;
        }
        message = "You have no permission to delete";
        return new Pair<Boolean,String>(b, message);
    }

	public Pair<Boolean,String> validateadd(HttpServletRequest request)
	{return new Pair<Boolean,String>(false, "");}
	
	public Pair<Boolean,String> validateedit(HttpServletRequest request)
	{return new Pair<Boolean,String>(false, "");}
	

    static {
        log = new SimpleLogger((Class)previlagevalidation.class, true);
    }
}
