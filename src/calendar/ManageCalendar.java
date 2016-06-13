package calendar.calendar;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class ManageCalendar extends HttpServlet
{
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = null;
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        out = response.getWriter();
        if (!New_LoginAppl.checkLogin(request, out)) {
            return;
        }
        final HttpSession mysession = request.getSession(false);
        final String student_id = (String)mysession.getAttribute("student_id");
        if (student_id == null) {
            response.sendRedirect("../servlet/interfaceenginev2.PortalServlet?IID=LoginPage");
        }
        else {
            final String strPrmAddModDel = request.getParameter("prmAddModify");
            if (strPrmAddModDel != null) {
                final int iPrmAddModify = Integer.parseInt(strPrmAddModDel);
                switch (iPrmAddModify) {
                    case 0: {
                        this.addCourse(request, student_id, out);
                        break;
                    }
                    case 1: {
                        this.modifyCourse(request, student_id);
                        break;
                    }
                    case 2: {
                        this.deleteCourse(request, student_id, out);
                        break;
                    }
                }
            }
            this.getResult(request, response, out, student_id);
        }
    }
    
    public void getResult(final HttpServletRequest request, final HttpServletResponse response, final PrintWriter out, final String strAdminId) throws IOException, ServletException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/util/GregorianCalendar;
        //     3: dup            
        //     4: invokespecial   java/util/GregorianCalendar.<init>:()V
        //     7: astore          calendar
        //     9: new             Ljava/util/Date;
        //    12: dup            
        //    13: invokespecial   java/util/Date.<init>:()V
        //    16: astore          trialTime
        //    18: aload           calendar
        //    20: aload           trialTime
        //    22: invokevirtual   java/util/Calendar.setTime:(Ljava/util/Date;)V
        //    25: bipush          12
        //    27: anewarray       Ljava/lang/String;
        //    30: dup            
        //    31: iconst_0       
        //    32: ldc             "January"
        //    34: aastore        
        //    35: dup            
        //    36: iconst_1       
        //    37: ldc             "Feburary"
        //    39: aastore        
        //    40: dup            
        //    41: iconst_2       
        //    42: ldc             "March"
        //    44: aastore        
        //    45: dup            
        //    46: iconst_3       
        //    47: ldc             "April"
        //    49: aastore        
        //    50: dup            
        //    51: iconst_4       
        //    52: ldc             "May"
        //    54: aastore        
        //    55: dup            
        //    56: iconst_5       
        //    57: ldc             "June"
        //    59: aastore        
        //    60: dup            
        //    61: bipush          6
        //    63: ldc             "July"
        //    65: aastore        
        //    66: dup            
        //    67: bipush          7
        //    69: ldc             "August"
        //    71: aastore        
        //    72: dup            
        //    73: bipush          8
        //    75: ldc             "September"
        //    77: aastore        
        //    78: dup            
        //    79: bipush          9
        //    81: ldc             "October"
        //    83: aastore        
        //    84: dup            
        //    85: bipush          10
        //    87: ldc             "November"
        //    89: aastore        
        //    90: dup            
        //    91: bipush          11
        //    93: ldc             "December"
        //    95: aastore        
        //    96: astore          months
        //    98: new             Ljava/lang/StringBuilder;
        //   101: dup            
        //   102: invokespecial   java/lang/StringBuilder.<init>:()V
        //   105: aload           calendar
        //   107: bipush          10
        //   109: invokevirtual   java/util/Calendar.get:(I)I
        //   112: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   115: ldc             ":"
        //   117: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   120: aload           calendar
        //   122: bipush          12
        //   124: invokevirtual   java/util/Calendar.get:(I)I
        //   127: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   130: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   133: astore          strTime
        //   135: new             Ljava/lang/StringBuilder;
        //   138: dup            
        //   139: invokespecial   java/lang/StringBuilder.<init>:()V
        //   142: aload           months
        //   144: aload           calendar
        //   146: iconst_2       
        //   147: invokevirtual   java/util/Calendar.get:(I)I
        //   150: aaload         
        //   151: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   154: ldc             " "
        //   156: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   159: aload           calendar
        //   161: iconst_5       
        //   162: invokevirtual   java/util/Calendar.get:(I)I
        //   165: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   168: ldc             ", "
        //   170: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   173: aload           calendar
        //   175: iconst_1       
        //   176: invokevirtual   java/util/Calendar.get:(I)I
        //   179: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   182: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   185: astore          strDate
        //   187: aload_1         /* request */
        //   188: ldc             "unit_id"
        //   190: invokeinterface javax/servlet/http/HttpServletRequest.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //   195: astore          strUnitId
        //   197: new             Ljava/lang/StringBuilder;
        //   200: dup            
        //   201: invokespecial   java/lang/StringBuilder.<init>:()V
        //   204: ldc             "\n\tvar index = 0;\n\tvar rowId = 0;\n var unitId = \""
        //   206: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   209: aload           strUnitId
        //   211: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   214: ldc             "\";"
        //   216: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   219: ldc             "\n\tfunction findRow(){"
        //   221: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   224: ldc             "\n\t\tfor (var counter=0; counter<document.frm.checkbox.length; counter++) {"
        //   226: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   229: ldc             "\n\t\t\tif (document.frm.checkbox[counter].value == unitId) {"
        //   231: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   234: ldc             "\n\t\t\t\trowId = counter;"
        //   236: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   239: ldc             "\n\t\t\t}"
        //   241: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   244: ldc             "\n\t\t}"
        //   246: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   249: ldc             "\n\treturn true;"
        //   251: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   254: ldc             "\n\t}"
        //   256: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   259: ldc             "\n\tfunction test() {"
        //   261: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   264: ldc             "\n\t\tvar index = 0;"
        //   266: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   269: ldc             "\n\t\tfor (var i=0;i<document.frm.elements.length;i++){"
        //   271: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   274: ldc             "\n\t\t\tvar e = document.frm.elements[i];"
        //   276: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   279: ldc             "\n\t\t\tif (e.type=='radio'){"
        //   281: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   284: ldc             "\n\t\t\t\tindex++;"
        //   286: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   289: ldc             "\n\t\t\t}"
        //   291: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   294: ldc             "\n\t\t}"
        //   296: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   299: ldc             "\n\t\treturn index;"
        //   301: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   304: ldc             "\n\t}"
        //   306: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   309: ldc             "\n\tfunction checkEntries(){"
        //   311: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   314: ldc             "\n\t\tvar filledIn = false;"
        //   316: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   319: ldc             "\n\t\tvar i = test();"
        //   321: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   324: ldc             "\n\t\tif(i>1) {"
        //   326: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   329: ldc             "\n\t\t\tfor (var counter=0; counter<document.frm.checkbox.length; counter++)"
        //   331: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   334: ldc             "\n\t\t\t\tif (document.frm.checkbox[counter].checked == true) {"
        //   336: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   339: ldc             "\n\t\t\t\t\tindex = counter;"
        //   341: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   344: ldc             "\n\t\t\t\t\tfilledIn = true;"
        //   346: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   349: ldc             "\n\t\t\t\t}"
        //   351: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   354: ldc             "\n\t\t\tif (filledIn == false){"
        //   356: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   359: ldc             "\n\t\t\t\talert('You must select at least one file type');"
        //   361: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   364: ldc             "\n\t\t\t\treturn false;"
        //   366: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   369: ldc             "\n\t\t\t}"
        //   371: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   374: ldc             "\n\t\t}"
        //   376: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   379: ldc             "\n\t\tif(i==1)  {"
        //   381: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   384: ldc             "\n\t\t\tif (document.frm.checkbox.checked == true) {"
        //   386: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   389: ldc             "\n\t\t\t\tfilledIn = true;"
        //   391: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   394: ldc             "\n\t\t\t}"
        //   396: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   399: ldc             "\n\t\t\tif (filledIn == false){"
        //   401: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   404: ldc             "\n\t\t\t\talert('You must select at least one file type');"
        //   406: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   409: ldc             "\n\t\t\t\treturn false;"
        //   411: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   414: ldc             "\n\t\t\t}"
        //   416: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   419: ldc             "\n\t\t}"
        //   421: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   424: ldc             "\n\t\treturn true;"
        //   426: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   429: ldc             "\n\t}"
        //   431: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   434: ldc             "\n"
        //   436: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   439: ldc             "\n\tfunction addNewUnit_onclick(){"
        //   441: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   444: ldc             "\n\t\tif(validate()){"
        //   446: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   449: ldc             "\n\t\t\tdocument.frm.method=\"post\";"
        //   451: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   454: ldc             "\n\t\t\tdocument.frm.action=\"calendar.calendar.ManageCalendar?prmAddModify=0\";"
        //   456: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   459: ldc             "\n\t\t\tdocument.frm.target=\"_self\";"
        //   461: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   464: ldc             "\n\t\t\tdocument.frm.submit();"
        //   466: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   469: ldc             "\n\t\t}"
        //   471: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   474: ldc             "\n\t}"
        //   476: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   479: ldc             "\n\tfunction modifyUnit_onclick() {"
        //   481: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   484: ldc             "\n if(document.frm.UnitName.value==\"\")"
        //   486: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   489: ldc             "\n\t{ \talert(\"Please Select Calendar Name Properly\");"
        //   491: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   494: ldc             "\n\t\tdocument.frm.UnitName.focus();"
        //   496: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   499: ldc             "\n\t\t\treturn false;}"
        //   501: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   504: ldc             "\n\t\tvar i = test();"
        //   506: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   509: ldc             "\n\t\tif(i>1) {"
        //   511: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   514: ldc             "\n\t\t\tif((checkEntries()) && (validate())){"
        //   516: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   519: ldc             "\n\t\t\tdocument.frm.method=\"post\";"
        //   521: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   524: ldc             "\n\t\t\tdocument.frm.action=\"calendar.calendar.ManageCalendar?prmAddModify=1\";"
        //   526: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   529: ldc             "\n\t\t\tdocument.frm.target=\"_self\";"
        //   531: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   534: ldc             "\n\t\t\tdocument.frm.submit();"
        //   536: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   539: ldc             "\n\t\t\t}"
        //   541: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   544: ldc             "\n\t\t}"
        //   546: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   549: ldc             "\n\t\tif(i==1) {"
        //   551: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   554: ldc             "\n\t\t\tif((checkEntries()) && (validate())){"
        //   556: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   559: ldc             "\n                             document.frm.method = \"post\";"
        //   561: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   564: ldc             "\n\t\t\t\tdocument.frm.action = \"calendar.calendar.ManageCalendar?prmAddModify=1\";"
        //   566: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   569: ldc             "\n                             document.frm.submit();"
        //   571: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   574: ldc             "\n\t\t\t}"
        //   576: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   579: ldc             "\n\t\t}"
        //   581: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   584: ldc             "\n\t}"
        //   586: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   589: ldc             "\n\tfunction deleteUnit_onclick() {"
        //   591: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   594: ldc             "\n\t\tvar i = test();"
        //   596: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   599: ldc             "\n if(document.frm.UnitName.value==\"\")"
        //   601: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   604: ldc             "\n\t{ \talert(\"Please Select Calendar Name Properly\");"
        //   606: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   609: ldc             "\n\t\tdocument.frm.UnitName.focus();"
        //   611: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   614: ldc             "\n\t\t\treturn false;}"
        //   616: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   619: ldc             "\n\t\tif(i>1) {"
        //   621: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   624: ldc             "\n\t\t\tif(!checkEntries())"
        //   626: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   629: ldc             "\n\t\t\t\treturn false;"
        //   631: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   634: ldc             "\n\t\t\tdoyou = confirm(\"Are you Sure to Delete The Selected Calendar?\"); //Your question."
        //   636: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   639: ldc             "\n"
        //   641: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   644: ldc             "\n\t\t\tif (doyou == true) {"
        //   646: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   649: ldc             "\n                             document.frm.method = \"post\";"
        //   651: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   654: ldc             "\n\t\t\t\tdocument.frm.action = \"calendar.calendar.ManageCalendar?prmAddModify=2\";"
        //   656: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   659: ldc             "\n                             document.frm.submit();"
        //   661: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   664: ldc             "\n\t\t\t}"
        //   666: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   669: ldc             "\n\t\t\telse {"
        //   671: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   674: ldc             "\n\t\t\t}"
        //   676: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   679: ldc             "\n\t\t}"
        //   681: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   684: ldc             "\n\t\tif(i==1) {"
        //   686: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   689: ldc             "\n\t\t\tif(!checkEntries())"
        //   691: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   694: ldc             "\n\t\t\t\treturn false;"
        //   696: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   699: ldc             "\n\t\t\tdoyou = confirm(\"Are you Sure to Delete The Selected Calendar?\"); //Your question."
        //   701: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   704: ldc             "\n"
        //   706: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   709: ldc             "\n\t\t\tif (doyou == true) {"
        //   711: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   714: ldc             "\n                             document.frm.method = \"post\";"
        //   716: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   719: ldc             "\n\t\t\t\tdocument.frm.action = \"calendar.calendar.ManageCalendar?prmAddModify=2\";"
        //   721: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   724: ldc             "\n                             document.frm.submit();"
        //   726: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   729: ldc             "\n\t\t\t}"
        //   731: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   734: ldc             "\n\t\t\telse {"
        //   736: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   739: ldc             "\n\t\t\t}"
        //   741: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   744: ldc             "\n\t\t}"
        //   746: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   749: ldc             "\n\t\treturn true;"
        //   751: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   754: ldc             "\n\t}"
        //   756: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   759: ldc             "\n"
        //   761: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   764: ldc             "\n\tfunction manageUnitRepository_onclick() {"
        //   766: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   769: ldc             "\n\t\tlocation.href = \"UnitInformation\""
        //   771: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   774: ldc             "\n\t}"
        //   776: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   779: ldc             "\n"
        //   781: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   784: ldc             "\n\tfunction setSelectedUnitId(value,index) {"
        //   786: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   789: ldc             "\n\t\tvar i = test();"
        //   791: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   794: ldc             "\n\t\tfor(var counter=0; counter<document.frm.StatusSelect.length; counter++) {"
        //   796: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   799: ldc             "\n\t\t\t//alert(document.frm.StatusSelect[counter].value==value);"
        //   801: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   804: ldc             "\n\t\t\tif(document.frm.StatusSelect[counter].value==value) {"
        //   806: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   809: ldc             "\n\t\t\t\tdocument.frm.StatusSelect.selectedIndex=counter;"
        //   811: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   814: ldc             "\n\t\t\t\tif(i>1) {"
        //   816: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   819: ldc             "\n\t\t\t\t\tdocument.frm.UnitId.value=document.frm.checkbox[index].value;"
        //   821: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   824: ldc             "\n\t\t\t\t\tdocument.frm.UnitName.value=document.frm.hiddenUnit[index].value;"
        //   826: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   829: ldc             "\n\t\t\t\t}"
        //   831: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   834: ldc             "\n\t\t\t\tif(i==1) {"
        //   836: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   839: ldc             "\n\t\t\t\t\tdocument.frm.UnitId.value=document.frm.checkbox.value;"
        //   841: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   844: ldc             "\n\t\t\t\t\tdocument.frm.UnitName.value=document.frm.hiddenUnit.value;"
        //   846: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   849: ldc             "\n\t\t\t\t}"
        //   851: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   854: ldc             "\n\t\t\t\tbreak;"
        //   856: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   859: ldc             "\n\t\t\t}"
        //   861: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   864: ldc             "\n\t\t}"
        //   866: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   869: ldc             "\n\t\treturn;"
        //   871: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   874: ldc             "\n\t}"
        //   876: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   879: ldc             "\n"
        //   881: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   884: ldc             "\n\tfunction checkbox_onclick() {"
        //   886: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   889: ldc             "\n\t\tvar i = test();"
        //   891: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   894: ldc             "\n\t\tif(i>1) {"
        //   896: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   899: ldc             "\n\t\t\tfor(var counter=0; counter<document.frm.checkbox.length; counter++) {"
        //   901: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   904: ldc             "\n\t\t\t\tif(document.frm.checkbox[counter].checked) {"
        //   906: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   909: ldc             "\n\t\t\t\t\tsetSelectedUnitId(document.frm.hiddenStatus[counter].value,counter);"
        //   911: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   914: ldc             "\n\t\t\t\t\tbreak;"
        //   916: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   919: ldc             "\n\t\t\t\t}"
        //   921: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   924: ldc             "\n\t\t\t}"
        //   926: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   929: ldc             "\n\t\t}"
        //   931: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   934: ldc             "\n\t\tif (i==1) {"
        //   936: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   939: ldc             "\n\t\t\tif(document.frm.checkbox.checked) {"
        //   941: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   944: ldc             "\n\t\t\t\tsetSelectedUnitId(document.frm.hiddenStatus.value,0);"
        //   946: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   949: ldc             "\n\t\t\t}"
        //   951: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   954: ldc             "\n\t\t}"
        //   956: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   959: ldc             "\n\t}"
        //   961: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   964: ldc             "\n"
        //   966: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   969: ldc             "\n\tfunction checkID(){"
        //   971: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   974: ldc             "\n\t\tvar i = test();"
        //   976: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   979: ldc             "\n\t\tif(i>1) {"
        //   981: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   984: ldc             "\n\t\t\tif(document.frm.checkbox[index].value!=document.frm.UnitId.value){"
        //   986: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   989: ldc             "\n\t\t\t\talert(\"You cannot change the Unit Id\");"
        //   991: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   994: ldc             "\n\t\t\t\tdocument.frm.UnitId.value=document.frm.checkbox[index].value;"
        //   996: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   999: ldc             "\n\t\t\t}"
        //  1001: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1004: ldc             "\n\t\t} "
        //  1006: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1009: ldc             "\n\t\tif(i==1) {"
        //  1011: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1014: ldc             "\n\t\t\tif(document.frm.checkbox.value!=document.frm.UnitId.value){"
        //  1016: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1019: ldc             "\n\t\t\t\talert(\"You cannot change the Unit Id\");"
        //  1021: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1024: ldc             "\n\t\t\t\tdocument.frm.UnitId.value=document.frm.checkbox.value;"
        //  1026: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1029: ldc             "\n\t\t\t}"
        //  1031: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1034: ldc             "\n\t\t}"
        //  1036: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1039: ldc             "\n\t\treturn true;"
        //  1041: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1044: ldc             "\n\t}"
        //  1046: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1049: ldc             "\n\tfunction validate(){"
        //  1051: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1054: ldc             "\n\t\tif(!fnCheckNull(document.frm.UnitName.value,\"Calendar Name\")){"
        //  1056: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1059: ldc             "\n\t\t\tdocument.frm.UnitName.focus();"
        //  1061: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1064: ldc             "\n\t\t\treturn false;"
        //  1066: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1069: ldc             "\n\t\t}"
        //  1071: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1074: ldc             "\n\t\treturn true;"
        //  1076: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1079: ldc             "\n\t}"
        //  1081: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1084: ldc             "\n function load() {"
        //  1086: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1089: ldc             "\n\t\tvar i = test();"
        //  1091: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1094: ldc             "\n\t\tif(i>1) {"
        //  1096: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1099: ldc             "\n \t\tfindRow();"
        //  1101: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1104: ldc             "\n\t\t\tdocument.frm.checkbox[rowId].checked=true;"
        //  1106: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1109: ldc             "\n\t\t\tCCA();"
        //  1111: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1114: ldc             "\n\t\t}"
        //  1116: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1119: ldc             "\n\t\tif(i==1) {"
        //  1121: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1124: ldc             "\n\t\t\tdocument.frm.checkbox.checked=true;"
        //  1126: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1129: ldc             "\n\t\t\tCCA();"
        //  1131: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1134: ldc             "\n\t\t}"
        //  1136: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1139: ldc             "\n\t}"
        //  1141: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1144: ldc             "\n function close_onclick() {"
        //  1146: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1149: ldc             "\n     document.frm.method = \"post\";"
        //  1151: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1154: ldc             "\n    \twindow.parent.frames[2].document.frm.action =\"./calendar.calendar.MyCalendar?caltype=0\";"
        //  1156: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1159: ldc             "\n     document.frm.submit();"
        //  1161: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1164: ldc             "\n\t}"
        //  1166: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1169: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1172: astore          javaScript
        //  1174: new             Lorg/apache/ecs/html/Body;
        //  1177: dup            
        //  1178: invokespecial   org/apache/ecs/html/Body.<init>:()V
        //  1181: astore          body
        //  1183: new             Lorg/apache/ecs/html/Form;
        //  1186: dup            
        //  1187: invokespecial   org/apache/ecs/html/Form.<init>:()V
        //  1190: ldc             "post"
        //  1192: invokevirtual   org/apache/ecs/html/Form.setMethod:(Ljava/lang/String;)Lorg/apache/ecs/html/Form;
        //  1195: ldc             "frm"
        //  1197: invokevirtual   org/apache/ecs/html/Form.setName:(Ljava/lang/String;)Lorg/apache/ecs/html/Form;
        //  1200: astore          form
        //  1202: new             Lorg/apache/ecs/html/Input;
        //  1205: dup            
        //  1206: invokespecial   org/apache/ecs/html/Input.<init>:()V
        //  1209: astore          inputButton1
        //  1211: new             Lorg/apache/ecs/html/Input;
        //  1214: dup            
        //  1215: invokespecial   org/apache/ecs/html/Input.<init>:()V
        //  1218: astore          inputButton2
        //  1220: new             Lorg/apache/ecs/html/Input;
        //  1223: dup            
        //  1224: invokespecial   org/apache/ecs/html/Input.<init>:()V
        //  1227: astore          inputButton3
        //  1229: new             Lorg/apache/ecs/html/Input;
        //  1232: dup            
        //  1233: invokespecial   org/apache/ecs/html/Input.<init>:()V
        //  1236: astore          inputButton4
        //  1238: aload           inputButton4
        //  1240: ldc             "close_onclick()"
        //  1242: invokevirtual   org/apache/ecs/html/Input.setOnClick:(Ljava/lang/String;)V
        //  1245: new             Lorg/apache/ecs/html/Input;
        //  1248: dup            
        //  1249: invokespecial   org/apache/ecs/html/Input.<init>:()V
        //  1252: ldc             "hidden"
        //  1254: invokevirtual   org/apache/ecs/html/Input.setType:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  1257: ldc             "UnitId"
        //  1259: invokevirtual   org/apache/ecs/html/Input.setName:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  1262: astore          calendar_id
        //  1264: new             Lorg/apache/ecs/html/Table;
        //  1267: dup            
        //  1268: invokespecial   org/apache/ecs/html/Table.<init>:()V
        //  1271: astore          tblList
        //  1273: new             Lorg/apache/ecs/html/P;
        //  1276: dup            
        //  1277: invokespecial   org/apache/ecs/html/P.<init>:()V
        //  1280: astore          p
        //  1282: iconst_3       
        //  1283: anewarray       Lorg/apache/ecs/html/Option;
        //  1286: dup            
        //  1287: iconst_0       
        //  1288: new             Lorg/apache/ecs/html/Option;
        //  1291: dup            
        //  1292: ldc             "0"
        //  1294: invokespecial   org/apache/ecs/html/Option.<init>:(Ljava/lang/String;)V
        //  1297: ldc             "[Choose One]"
        //  1299: invokevirtual   org/apache/ecs/html/Option.addElement:(Ljava/lang/String;)Lorg/apache/ecs/html/Option;
        //  1302: aastore        
        //  1303: dup            
        //  1304: iconst_1       
        //  1305: new             Lorg/apache/ecs/html/Option;
        //  1308: dup            
        //  1309: ldc             "Active"
        //  1311: invokespecial   org/apache/ecs/html/Option.<init>:(Ljava/lang/String;)V
        //  1314: ldc             "Active"
        //  1316: invokevirtual   org/apache/ecs/html/Option.addElement:(Ljava/lang/String;)Lorg/apache/ecs/html/Option;
        //  1319: aastore        
        //  1320: dup            
        //  1321: iconst_2       
        //  1322: new             Lorg/apache/ecs/html/Option;
        //  1325: dup            
        //  1326: ldc             "Inactive"
        //  1328: invokespecial   org/apache/ecs/html/Option.<init>:(Ljava/lang/String;)V
        //  1331: ldc             "Inactive"
        //  1333: invokevirtual   org/apache/ecs/html/Option.addElement:(Ljava/lang/String;)Lorg/apache/ecs/html/Option;
        //  1336: aastore        
        //  1337: astore          option2
        //  1339: new             Lorg/apache/ecs/html/Html;
        //  1342: dup            
        //  1343: invokespecial   org/apache/ecs/html/Html.<init>:()V
        //  1346: new             Lorg/apache/ecs/html/Head;
        //  1349: dup            
        //  1350: invokespecial   org/apache/ecs/html/Head.<init>:()V
        //  1353: new             Lorg/apache/ecs/html/Title;
        //  1356: dup            
        //  1357: ldc             "Persona Calendar Management"
        //  1359: invokespecial   org/apache/ecs/html/Title.<init>:(Ljava/lang/String;)V
        //  1362: invokevirtual   org/apache/ecs/html/Head.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Head;
        //  1365: new             Lorg/apache/ecs/html/Link;
        //  1368: dup            
        //  1369: invokespecial   org/apache/ecs/html/Link.<init>:()V
        //  1372: ldc             "../coreadmin/css/stylesheetcal.css"
        //  1374: invokevirtual   org/apache/ecs/html/Link.setHref:(Ljava/lang/String;)Lorg/apache/ecs/html/Link;
        //  1377: ldc             "stylesheet"
        //  1379: invokevirtual   org/apache/ecs/html/Link.setRel:(Ljava/lang/String;)Lorg/apache/ecs/html/Link;
        //  1382: invokevirtual   org/apache/ecs/html/Head.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Head;
        //  1385: new             Lorg/apache/ecs/html/Script;
        //  1388: dup            
        //  1389: invokespecial   org/apache/ecs/html/Script.<init>:()V
        //  1392: ldc             "JavaScript"
        //  1394: invokevirtual   org/apache/ecs/html/Script.setLanguage:(Ljava/lang/String;)Lorg/apache/ecs/html/Script;
        //  1397: ldc             "../coreadmin/js/checkcal.js"
        //  1399: invokevirtual   org/apache/ecs/html/Script.setSrc:(Ljava/lang/String;)Lorg/apache/ecs/html/Script;
        //  1402: invokevirtual   org/apache/ecs/html/Head.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Head;
        //  1405: new             Lorg/apache/ecs/html/Script;
        //  1408: dup            
        //  1409: invokespecial   org/apache/ecs/html/Script.<init>:()V
        //  1412: ldc             "JavaScript"
        //  1414: invokevirtual   org/apache/ecs/html/Script.setLanguage:(Ljava/lang/String;)Lorg/apache/ecs/html/Script;
        //  1417: aload           javaScript
        //  1419: invokevirtual   org/apache/ecs/html/Script.addElement:(Ljava/lang/String;)Lorg/apache/ecs/html/Script;
        //  1422: invokevirtual   org/apache/ecs/html/Head.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Head;
        //  1425: invokevirtual   org/apache/ecs/html/Html.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Html;
        //  1428: aload           body
        //  1430: ldc             "#FFFFFF"
        //  1432: invokevirtual   org/apache/ecs/html/Body.setBgColor:(Ljava/lang/String;)Lorg/apache/ecs/html/Body;
        //  1435: aload           form
        //  1437: new             Lcalendar/calendar/CalTableExtension;
        //  1440: dup            
        //  1441: invokespecial   calendar/calendar/CalTableExtension.<init>:()V
        //  1444: new             Ljava/lang/StringBuilder;
        //  1447: dup            
        //  1448: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1451: ldc             "<b>Manage Personal Calendar: </b>"
        //  1453: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1456: aload           strAdminId
        //  1458: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1461: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1464: aload           strDate
        //  1466: aload           strTime
        //  1468: invokevirtual   calendar/calendar/CalTableExtension.headerTable:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/apache/ecs/html/Table;
        //  1471: invokevirtual   org/apache/ecs/html/Form.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Form;
        //  1474: invokevirtual   org/apache/ecs/html/Body.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Body;
        //  1477: invokevirtual   org/apache/ecs/html/Html.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Html;
        //  1480: astore          html
        //  1482: aload           form
        //  1484: aload           tblList
        //  1486: iconst_0       
        //  1487: invokevirtual   org/apache/ecs/html/Table.setCellPadding:(I)Lorg/apache/ecs/html/Table;
        //  1490: iconst_1       
        //  1491: invokevirtual   org/apache/ecs/html/Table.setCellSpacing:(I)Lorg/apache/ecs/html/Table;
        //  1494: ldc             "100%"
        //  1496: invokevirtual   org/apache/ecs/html/Table.setWidth:(Ljava/lang/String;)Lorg/apache/ecs/html/Table;
        //  1499: new             Lorg/apache/ecs/html/TR;
        //  1502: dup            
        //  1503: invokespecial   org/apache/ecs/html/TR.<init>:()V
        //  1506: new             Lorg/apache/ecs/html/TD;
        //  1509: dup            
        //  1510: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  1513: iconst_5       
        //  1514: invokevirtual   org/apache/ecs/html/TD.setColSpan:(I)Lorg/apache/ecs/html/TD;
        //  1517: new             Lorg/apache/ecs/html/NOBR;
        //  1520: dup            
        //  1521: invokespecial   org/apache/ecs/html/NOBR.<init>:()V
        //  1524: aload           p
        //  1526: ldc             "left"
        //  1528: invokevirtual   org/apache/ecs/html/P.setAlign:(Ljava/lang/String;)Lorg/apache/ecs/html/P;
        //  1531: new             Lorg/apache/ecs/html/Font;
        //  1534: dup            
        //  1535: invokespecial   org/apache/ecs/html/Font.<init>:()V
        //  1538: iconst_2       
        //  1539: invokevirtual   org/apache/ecs/html/Font.setSize:(I)Lorg/apache/ecs/html/Font;
        //  1542: new             Lorg/apache/ecs/html/B;
        //  1545: dup            
        //  1546: invokespecial   org/apache/ecs/html/B.<init>:()V
        //  1549: ldc             "&nbsp;&nbsp;Total No. Of Personal Calendars: "
        //  1551: invokevirtual   org/apache/ecs/html/B.addElement:(Ljava/lang/String;)Lorg/apache/ecs/html/B;
        //  1554: invokevirtual   org/apache/ecs/html/Font.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Font;
        //  1557: invokevirtual   org/apache/ecs/html/P.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/P;
        //  1560: invokevirtual   org/apache/ecs/html/NOBR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/NOBR;
        //  1563: invokevirtual   org/apache/ecs/html/TD.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TD;
        //  1566: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  1569: invokevirtual   org/apache/ecs/html/Table.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Table;
        //  1572: new             Lcalendar/calendar/CalTRExtension;
        //  1575: dup            
        //  1576: invokespecial   calendar/calendar/CalTRExtension.<init>:()V
        //  1579: iconst_5       
        //  1580: invokevirtual   calendar/calendar/CalTRExtension.addHR:(I)Lorg/apache/ecs/html/TR;
        //  1583: invokevirtual   org/apache/ecs/html/Table.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Table;
        //  1586: new             Lcalendar/calendar/CalTRExtension;
        //  1589: dup            
        //  1590: invokespecial   calendar/calendar/CalTRExtension.<init>:()V
        //  1593: ldc             "Currently Defined Personal Calendars"
        //  1595: iconst_5       
        //  1596: invokevirtual   calendar/calendar/CalTRExtension.addHeader:(Ljava/lang/String;I)Lorg/apache/ecs/html/TR;
        //  1599: invokevirtual   org/apache/ecs/html/Table.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Table;
        //  1602: new             Lcalendar/calendar/CalTRExtension;
        //  1605: dup            
        //  1606: invokespecial   calendar/calendar/CalTRExtension.<init>:()V
        //  1609: ldc             "Select"
        //  1611: ldc             "Calendar Name"
        //  1613: ldc             "Created Date"
        //  1615: ldc             "Last Modification Date"
        //  1617: ldc             "Status"
        //  1619: invokevirtual   calendar/calendar/CalTRExtension.addHeaderNames:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/apache/ecs/html/TR;
        //  1622: invokevirtual   org/apache/ecs/html/Table.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Table;
        //  1625: invokevirtual   org/apache/ecs/html/Form.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Form;
        //  1628: pop            
        //  1629: aload           strAdminId
        //  1631: invokestatic    calendar/calendar/CalDataBaseLayer.getCalendarDetailsList:(Ljava/lang/String;)Ljava/util/Vector;
        //  1634: astore          vCourseList
        //  1636: aload           vCourseList
        //  1638: ifnull          1785
        //  1641: aload           p
        //  1643: new             Ljava/lang/StringBuilder;
        //  1646: dup            
        //  1647: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1650: ldc             ""
        //  1652: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1655: aload           vCourseList
        //  1657: invokevirtual   java/util/Vector.size:()I
        //  1660: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1663: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1666: invokevirtual   org/apache/ecs/html/P.addElement:(Ljava/lang/String;)Lorg/apache/ecs/html/P;
        //  1669: pop            
        //  1670: iconst_0       
        //  1671: istore          i
        //  1673: iload           i
        //  1675: aload           vCourseList
        //  1677: invokevirtual   java/util/Vector.size:()I
        //  1680: if_icmpge       1782
        //  1683: aload           vCourseList
        //  1685: iload           i
        //  1687: invokevirtual   java/util/Vector.elementAt:(I)Ljava/lang/Object;
        //  1690: checkcast       Ljava/util/Vector;
        //  1693: astore          vCourse
        //  1695: aload           vCourse
        //  1697: iconst_0       
        //  1698: invokevirtual   java/util/Vector.elementAt:(I)Ljava/lang/Object;
        //  1701: checkcast       Ljava/lang/String;
        //  1704: astore          strId
        //  1706: aload           vCourse
        //  1708: iconst_1       
        //  1709: invokevirtual   java/util/Vector.elementAt:(I)Ljava/lang/Object;
        //  1712: checkcast       Ljava/lang/String;
        //  1715: astore          strUnitName
        //  1717: aload           vCourse
        //  1719: iconst_2       
        //  1720: invokevirtual   java/util/Vector.elementAt:(I)Ljava/lang/Object;
        //  1723: checkcast       Ljava/lang/String;
        //  1726: astore          strCreadtedDate
        //  1728: aload           vCourse
        //  1730: iconst_3       
        //  1731: invokevirtual   java/util/Vector.elementAt:(I)Ljava/lang/Object;
        //  1734: checkcast       Ljava/lang/String;
        //  1737: astore          strLastModDate
        //  1739: aload           vCourse
        //  1741: iconst_4       
        //  1742: invokevirtual   java/util/Vector.elementAt:(I)Ljava/lang/Object;
        //  1745: checkcast       Ljava/lang/String;
        //  1748: astore          strStatus
        //  1750: aload           tblList
        //  1752: new             Lcalendar/calendar/CalTRExtension;
        //  1755: dup            
        //  1756: invokespecial   calendar/calendar/CalTRExtension.<init>:()V
        //  1759: aload           strId
        //  1761: aload           strUnitName
        //  1763: aload           strCreadtedDate
        //  1765: aload           strLastModDate
        //  1767: aload           strStatus
        //  1769: invokevirtual   calendar/calendar/CalTRExtension.addRowUser:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/apache/ecs/html/TR;
        //  1772: invokevirtual   org/apache/ecs/html/Table.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Table;
        //  1775: pop            
        //  1776: iinc            i, 1
        //  1779: goto            1673
        //  1782: goto            1793
        //  1785: aload           p
        //  1787: ldc             "0"
        //  1789: invokevirtual   org/apache/ecs/html/P.addElement:(Ljava/lang/String;)Lorg/apache/ecs/html/P;
        //  1792: pop            
        //  1793: aload           tblList
        //  1795: new             Lorg/apache/ecs/html/TR;
        //  1798: dup            
        //  1799: invokespecial   org/apache/ecs/html/TR.<init>:()V
        //  1802: new             Lorg/apache/ecs/html/TD;
        //  1805: dup            
        //  1806: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  1809: iconst_5       
        //  1810: invokevirtual   org/apache/ecs/html/TD.setColSpan:(I)Lorg/apache/ecs/html/TD;
        //  1813: new             Lorg/apache/ecs/html/HR;
        //  1816: dup            
        //  1817: invokespecial   org/apache/ecs/html/HR.<init>:()V
        //  1820: iconst_1       
        //  1821: invokevirtual   org/apache/ecs/html/HR.setSize:(I)Lorg/apache/ecs/html/HR;
        //  1824: invokevirtual   org/apache/ecs/html/TD.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TD;
        //  1827: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  1830: invokevirtual   org/apache/ecs/html/Table.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Table;
        //  1833: pop            
        //  1834: aload           form
        //  1836: new             Lcalendar/calendar/CalTableExtension;
        //  1839: dup            
        //  1840: invokespecial   calendar/calendar/CalTableExtension.<init>:()V
        //  1843: invokevirtual   calendar/calendar/CalTableExtension.add:()Lorg/apache/ecs/html/Table;
        //  1846: new             Lorg/apache/ecs/html/Table;
        //  1849: dup            
        //  1850: invokespecial   org/apache/ecs/html/Table.<init>:()V
        //  1853: iconst_0       
        //  1854: invokevirtual   org/apache/ecs/html/Table.setBorder:(I)Lorg/apache/ecs/html/Table;
        //  1857: iconst_0       
        //  1858: invokevirtual   org/apache/ecs/html/Table.setCellPadding:(I)Lorg/apache/ecs/html/Table;
        //  1861: iconst_0       
        //  1862: invokevirtual   org/apache/ecs/html/Table.setCellSpacing:(I)Lorg/apache/ecs/html/Table;
        //  1865: ldc             "100%"
        //  1867: invokevirtual   org/apache/ecs/html/Table.setWidth:(Ljava/lang/String;)Lorg/apache/ecs/html/Table;
        //  1870: new             Lcalendar/calendar/CalTRExtension;
        //  1873: dup            
        //  1874: invokespecial   calendar/calendar/CalTRExtension.<init>:()V
        //  1877: invokevirtual   calendar/calendar/CalTRExtension.addLine:()Lorg/apache/ecs/html/TR;
        //  1880: invokevirtual   org/apache/ecs/html/Table.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Table;
        //  1883: new             Lorg/apache/ecs/html/TR;
        //  1886: dup            
        //  1887: invokespecial   org/apache/ecs/html/TR.<init>:()V
        //  1890: new             Lorg/apache/ecs/html/TD;
        //  1893: dup            
        //  1894: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  1897: ldc_w           "023264"
        //  1900: invokevirtual   org/apache/ecs/html/TD.setBgColor:(Ljava/lang/String;)Lorg/apache/ecs/html/TD;
        //  1903: iconst_1       
        //  1904: invokevirtual   org/apache/ecs/html/TD.setWidth:(I)Lorg/apache/ecs/html/TD;
        //  1907: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  1910: new             Lorg/apache/ecs/html/TD;
        //  1913: dup            
        //  1914: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  1917: aload           calendar_id
        //  1919: invokevirtual   org/apache/ecs/html/TD.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TD;
        //  1922: new             Lcalendar/calendar/CalTableExtension;
        //  1925: dup            
        //  1926: invokespecial   calendar/calendar/CalTableExtension.<init>:()V
        //  1929: ldc             "Calendar Name"
        //  1931: ldc_w           "text"
        //  1934: ldc_w           "UnitName"
        //  1937: ldc_w           "50"
        //  1940: ldc_w           "25"
        //  1943: ldc_w           "1"
        //  1946: invokevirtual   calendar/calendar/CalTableExtension.addRequired:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/apache/ecs/html/Table;
        //  1949: invokevirtual   org/apache/ecs/html/TD.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TD;
        //  1952: new             Lcalendar/calendar/CalTableExtension;
        //  1955: dup            
        //  1956: invokespecial   calendar/calendar/CalTableExtension.<init>:()V
        //  1959: ldc_w           "Calendar Status"
        //  1962: ldc_w           "StatusSelect"
        //  1965: ldc_w           "1"
        //  1968: aload           option2
        //  1970: invokevirtual   calendar/calendar/CalTableExtension.addSelect:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Lorg/apache/ecs/html/Option;)Lorg/apache/ecs/html/Table;
        //  1973: invokevirtual   org/apache/ecs/html/TD.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TD;
        //  1976: new             Lcalendar/calendar/CalTableExtension;
        //  1979: dup            
        //  1980: invokespecial   calendar/calendar/CalTableExtension.<init>:()V
        //  1983: invokevirtual   calendar/calendar/CalTableExtension.add:()Lorg/apache/ecs/html/Table;
        //  1986: invokevirtual   org/apache/ecs/html/TD.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TD;
        //  1989: new             Lorg/apache/ecs/html/Table;
        //  1992: dup            
        //  1993: invokespecial   org/apache/ecs/html/Table.<init>:()V
        //  1996: iconst_0       
        //  1997: invokevirtual   org/apache/ecs/html/Table.setBorder:(I)Lorg/apache/ecs/html/Table;
        //  2000: iconst_0       
        //  2001: invokevirtual   org/apache/ecs/html/Table.setCellPadding:(I)Lorg/apache/ecs/html/Table;
        //  2004: iconst_0       
        //  2005: invokevirtual   org/apache/ecs/html/Table.setCellSpacing:(I)Lorg/apache/ecs/html/Table;
        //  2008: ldc_w           "%100"
        //  2011: invokevirtual   org/apache/ecs/html/Table.setWidth:(Ljava/lang/String;)Lorg/apache/ecs/html/Table;
        //  2014: new             Lorg/apache/ecs/html/TBody;
        //  2017: dup            
        //  2018: invokespecial   org/apache/ecs/html/TBody.<init>:()V
        //  2021: new             Lorg/apache/ecs/html/TR;
        //  2024: dup            
        //  2025: invokespecial   org/apache/ecs/html/TR.<init>:()V
        //  2028: ldc_w           "<TD><IMG border=0 height=5 src=\"../coreadmin/images/T.gif\" width=10></TD>"
        //  2031: invokevirtual   org/apache/ecs/html/TR.addElement:(Ljava/lang/String;)Lorg/apache/ecs/html/TR;
        //  2034: ldc_w           "<TD width=\"100%\"><font color=\"#FF0000\">* Required Fields.</font></TD>"
        //  2037: invokevirtual   org/apache/ecs/html/TR.addElement:(Ljava/lang/String;)Lorg/apache/ecs/html/TR;
        //  2040: invokevirtual   org/apache/ecs/html/TBody.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TBody;
        //  2043: invokevirtual   org/apache/ecs/html/Table.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Table;
        //  2046: invokevirtual   org/apache/ecs/html/TD.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TD;
        //  2049: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  2052: new             Lorg/apache/ecs/html/TD;
        //  2055: dup            
        //  2056: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  2059: ldc_w           "023264"
        //  2062: invokevirtual   org/apache/ecs/html/TD.setBgColor:(Ljava/lang/String;)Lorg/apache/ecs/html/TD;
        //  2065: ldc_w           "1"
        //  2068: invokevirtual   org/apache/ecs/html/TD.setWidth:(Ljava/lang/String;)Lorg/apache/ecs/html/TD;
        //  2071: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  2074: invokevirtual   org/apache/ecs/html/Table.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Table;
        //  2077: new             Lcalendar/calendar/CalTRExtension;
        //  2080: dup            
        //  2081: invokespecial   calendar/calendar/CalTRExtension.<init>:()V
        //  2084: invokevirtual   calendar/calendar/CalTRExtension.addLine:()Lorg/apache/ecs/html/TR;
        //  2087: invokevirtual   org/apache/ecs/html/Table.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Table;
        //  2090: invokevirtual   org/apache/ecs/html/Table.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Table;
        //  2093: new             Lcalendar/calendar/CalTableExtension;
        //  2096: dup            
        //  2097: invokespecial   calendar/calendar/CalTableExtension.<init>:()V
        //  2100: invokevirtual   calendar/calendar/CalTableExtension.add:()Lorg/apache/ecs/html/Table;
        //  2103: invokevirtual   org/apache/ecs/html/Table.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Table;
        //  2106: invokevirtual   org/apache/ecs/html/Form.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Form;
        //  2109: pop            
        //  2110: aload           form
        //  2112: new             Lorg/apache/ecs/html/Table;
        //  2115: dup            
        //  2116: invokespecial   org/apache/ecs/html/Table.<init>:()V
        //  2119: iconst_0       
        //  2120: invokevirtual   org/apache/ecs/html/Table.setBorder:(I)Lorg/apache/ecs/html/Table;
        //  2123: iconst_0       
        //  2124: invokevirtual   org/apache/ecs/html/Table.setCellPadding:(I)Lorg/apache/ecs/html/Table;
        //  2127: iconst_0       
        //  2128: invokevirtual   org/apache/ecs/html/Table.setCellSpacing:(I)Lorg/apache/ecs/html/Table;
        //  2131: new             Lorg/apache/ecs/html/TR;
        //  2134: dup            
        //  2135: invokespecial   org/apache/ecs/html/TR.<init>:()V
        //  2138: new             Lorg/apache/ecs/html/TD;
        //  2141: dup            
        //  2142: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  2145: aload           inputButton1
        //  2147: ldc_w           "sbttn"
        //  2150: invokevirtual   org/apache/ecs/html/Input.setClassId:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2153: ldc_w           "addNewUnit"
        //  2156: invokevirtual   org/apache/ecs/html/Input.setName:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2159: iconst_2       
        //  2160: invokevirtual   org/apache/ecs/html/Input.setTabindex:(I)Lorg/apache/ecs/html/Input;
        //  2163: ldc_w           "Add New Calendar"
        //  2166: invokevirtual   org/apache/ecs/html/Input.setTitleValue:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2169: ldc_w           "button"
        //  2172: invokevirtual   org/apache/ecs/html/Input.setType:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2175: ldc_w           "Add New Calendar"
        //  2178: invokevirtual   org/apache/ecs/html/Input.setValue:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2181: invokevirtual   org/apache/ecs/html/TD.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TD;
        //  2184: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  2187: new             Lorg/apache/ecs/html/TD;
        //  2190: dup            
        //  2191: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  2194: iconst_5       
        //  2195: invokevirtual   org/apache/ecs/html/TD.setWidth:(I)Lorg/apache/ecs/html/TD;
        //  2198: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  2201: new             Lorg/apache/ecs/html/TD;
        //  2204: dup            
        //  2205: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  2208: aload           inputButton2
        //  2210: ldc_w           "sbttn"
        //  2213: invokevirtual   org/apache/ecs/html/Input.setClassId:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2216: ldc_w           "modifyUnit"
        //  2219: invokevirtual   org/apache/ecs/html/Input.setName:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2222: iconst_2       
        //  2223: invokevirtual   org/apache/ecs/html/Input.setTabindex:(I)Lorg/apache/ecs/html/Input;
        //  2226: ldc_w           "Modify Selected Calendar"
        //  2229: invokevirtual   org/apache/ecs/html/Input.setTitleValue:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2232: ldc_w           "button"
        //  2235: invokevirtual   org/apache/ecs/html/Input.setType:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2238: ldc_w           "Modify Selected Calendar"
        //  2241: invokevirtual   org/apache/ecs/html/Input.setValue:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2244: invokevirtual   org/apache/ecs/html/TD.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TD;
        //  2247: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  2250: new             Lorg/apache/ecs/html/TD;
        //  2253: dup            
        //  2254: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  2257: iconst_5       
        //  2258: invokevirtual   org/apache/ecs/html/TD.setWidth:(I)Lorg/apache/ecs/html/TD;
        //  2261: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  2264: new             Lorg/apache/ecs/html/TD;
        //  2267: dup            
        //  2268: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  2271: aload           inputButton3
        //  2273: ldc_w           "sbttn"
        //  2276: invokevirtual   org/apache/ecs/html/Input.setClassId:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2279: ldc_w           "deleteUnit"
        //  2282: invokevirtual   org/apache/ecs/html/Input.setName:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2285: iconst_2       
        //  2286: invokevirtual   org/apache/ecs/html/Input.setTabindex:(I)Lorg/apache/ecs/html/Input;
        //  2289: ldc_w           "Delete Selected Calendar"
        //  2292: invokevirtual   org/apache/ecs/html/Input.setTitleValue:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2295: ldc_w           "button"
        //  2298: invokevirtual   org/apache/ecs/html/Input.setType:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2301: ldc_w           "Delete Selected Calendar"
        //  2304: invokevirtual   org/apache/ecs/html/Input.setValue:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2307: invokevirtual   org/apache/ecs/html/TD.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TD;
        //  2310: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  2313: new             Lorg/apache/ecs/html/TD;
        //  2316: dup            
        //  2317: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  2320: iconst_5       
        //  2321: invokevirtual   org/apache/ecs/html/TD.setWidth:(I)Lorg/apache/ecs/html/TD;
        //  2324: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  2327: new             Lorg/apache/ecs/html/TD;
        //  2330: dup            
        //  2331: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  2334: aload           inputButton4
        //  2336: ldc_w           "sbttn"
        //  2339: invokevirtual   org/apache/ecs/html/Input.setClassId:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2342: ldc_w           "cancelunit"
        //  2345: invokevirtual   org/apache/ecs/html/Input.setName:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2348: iconst_2       
        //  2349: invokevirtual   org/apache/ecs/html/Input.setTabindex:(I)Lorg/apache/ecs/html/Input;
        //  2352: ldc_w           "Cancel"
        //  2355: invokevirtual   org/apache/ecs/html/Input.setTitleValue:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2358: ldc_w           "button"
        //  2361: invokevirtual   org/apache/ecs/html/Input.setType:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2364: ldc_w           "Cancel"
        //  2367: invokevirtual   org/apache/ecs/html/Input.setValue:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2370: invokevirtual   org/apache/ecs/html/TD.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TD;
        //  2373: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  2376: new             Lorg/apache/ecs/html/TD;
        //  2379: dup            
        //  2380: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  2383: iconst_5       
        //  2384: invokevirtual   org/apache/ecs/html/TD.setWidth:(I)Lorg/apache/ecs/html/TD;
        //  2387: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  2390: new             Lorg/apache/ecs/html/TD;
        //  2393: dup            
        //  2394: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  2397: iconst_5       
        //  2398: invokevirtual   org/apache/ecs/html/TD.setWidth:(I)Lorg/apache/ecs/html/TD;
        //  2401: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  2404: invokevirtual   org/apache/ecs/html/Table.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Table;
        //  2407: invokevirtual   org/apache/ecs/html/Form.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Form;
        //  2410: pop            
        //  2411: aload           body
        //  2413: ldc_w           "scrollit(100);load()"
        //  2416: invokevirtual   org/apache/ecs/html/Body.setOnLoad:(Ljava/lang/String;)V
        //  2419: aload           inputButton1
        //  2421: ldc_w           "addNewUnit_onclick();"
        //  2424: invokevirtual   org/apache/ecs/html/Input.setOnClick:(Ljava/lang/String;)V
        //  2427: aload           inputButton2
        //  2429: ldc_w           "modifyUnit_onclick();"
        //  2432: invokevirtual   org/apache/ecs/html/Input.setOnClick:(Ljava/lang/String;)V
        //  2435: aload           inputButton3
        //  2437: ldc_w           "deleteUnit_onclick();"
        //  2440: invokevirtual   org/apache/ecs/html/Input.setOnClick:(Ljava/lang/String;)V
        //  2443: aload_3         /* out */
        //  2444: aload           html
        //  2446: invokevirtual   org/apache/ecs/html/Html.toString:()Ljava/lang/String;
        //  2449: invokevirtual   java/io/PrintWriter.println:(Ljava/lang/String;)V
        //  2452: return         
        //    Exceptions:
        //  throws java.io.IOException
        //  throws javax.servlet.ServletException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name             Signature
        //  -----  ------  ----  ---------------  ----------------------------------------
        //  1695   81      25    vCourse          Ljava/util/Vector;
        //  1706   70      26    strId            Ljava/lang/String;
        //  1717   59      27    strUnitName      Ljava/lang/String;
        //  1728   48      28    strCreadtedDate  Ljava/lang/String;
        //  1739   37      29    strLastModDate   Ljava/lang/String;
        //  1750   26      30    strStatus        Ljava/lang/String;
        //  1673   109     24    i                I
        //  0      2453    0     this             Lcalendar/calendar/ManageCalendar;
        //  0      2453    1     request          Ljavax/servlet/http/HttpServletRequest;
        //  0      2453    2     response         Ljavax/servlet/http/HttpServletResponse;
        //  0      2453    3     out              Ljava/io/PrintWriter;
        //  0      2453    4     strAdminId       Ljava/lang/String;
        //  9      2444    5     calendar         Ljava/util/Calendar;
        //  18     2435    6     trialTime        Ljava/util/Date;
        //  98     2355    7     months           [Ljava/lang/String;
        //  135    2318    8     strTime          Ljava/lang/String;
        //  187    2266    9     strDate          Ljava/lang/String;
        //  197    2256    10    strUnitId        Ljava/lang/String;
        //  1174   1279    11    javaScript       Ljava/lang/String;
        //  1183   1270    12    body             Lorg/apache/ecs/html/Body;
        //  1202   1251    13    form             Lorg/apache/ecs/html/Form;
        //  1211   1242    14    inputButton1     Lorg/apache/ecs/html/Input;
        //  1220   1233    15    inputButton2     Lorg/apache/ecs/html/Input;
        //  1229   1224    16    inputButton3     Lorg/apache/ecs/html/Input;
        //  1238   1215    17    inputButton4     Lorg/apache/ecs/html/Input;
        //  1264   1189    18    calendar_id      Lorg/apache/ecs/html/Input;
        //  1273   1180    19    tblList          Lorg/apache/ecs/html/Table;
        //  1282   1171    20    p                Lorg/apache/ecs/html/P;
        //  1339   1114    21    option2          [Lorg/apache/ecs/html/Option;
        //  1482   971     22    html             Lorg/apache/ecs/html/Html;
        //  1636   817     23    vCourseList      Ljava/util/Vector;
        // 
        // The error that occurred was:
        // 
        // java.lang.StackOverflowError
        //     at java.util.Vector.contains(Vector.java:363)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:816)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:766)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2445)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:881)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:365)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:109)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at com.edu.dibyarup.DecompilerUtil.decompileFiles(DecompilerUtil.java:28)
        //     at com.edu.dibyarup.FileDB.decompileAllFiles(FileDB.java:48)
        //     at com.edu.dibyarup.FileDB.decompileAllFiles(FileDB.java:42)
        //     at com.edu.dibyarup.FileDB.decompileAllFiles(FileDB.java:42)
        //     at com.edu.dibyarup.FileDB.decompileAllFiles(FileDB.java:42)
        //     at com.edu.dibyarup.FileDB.main(FileDB.java:14)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }
    
    public void addCourse(final HttpServletRequest request, final String strCreatedBy, final PrintWriter out1) throws IOException, ServletException {
        final String m_strCourseId = request.getParameter("UnitId");
        final String m_strCourseName = request.getParameter("UnitName");
        final String m_strStatus = request.getParameter("StatusSelect");
        final Vector vCalendar = CalDataBaseLayer.publicPersonalCalendarNames("P");
        if (!vCalendar.contains(m_strCourseName)) {
            CalDataBaseLayer.addCalendarName(m_strCourseId, m_strCourseName, m_strStatus, strCreatedBy, out1);
        }
        else {
            out1.println("<font color=red>* This Calendar is already added.</font>");
        }
    }
    
    public void modifyCourse(final HttpServletRequest request, final String strModBy) throws IOException, ServletException {
        final String m_strCourseId = request.getParameter("UnitId");
        final String m_strCourseName = request.getParameter("UnitName");
        final String m_strStatus = request.getParameter("StatusSelect");
        CalDataBaseLayer.modifyCalendar(m_strCourseId, m_strCourseName, m_strStatus, strModBy);
    }
    
    public void deleteCourse(final HttpServletRequest request, final String strid, final PrintWriter out1) throws IOException, ServletException {
        final String m_strCourseId = request.getParameter("UnitId");
        CalDataBaseLayer.deleteCalendar(m_strCourseId, strid, out1);
    }
}
