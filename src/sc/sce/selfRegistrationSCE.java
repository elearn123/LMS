package sc.sce;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.grlea.log.SimpleLogger;

public class selfRegistrationSCE extends HttpServlet
{
    private static final SimpleLogger log;
    
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2         /* response */
        //     1: invokeinterface javax/servlet/http/HttpServletResponse.getWriter:()Ljava/io/PrintWriter;
        //     6: astore_3        /* out */
        //     7: aload_1         /* request */
        //     8: aload_3         /* out */
        //     9: invokestatic    sc/sce/New_LoginAppl.checkLogin:(Ljavax/servlet/http/HttpServletRequest;Ljava/io/PrintWriter;)Z
        //    12: ifne            16
        //    15: return         
        //    16: aload_2         /* response */
        //    17: ldc             "Pragma"
        //    19: ldc             "no-cache"
        //    21: invokeinterface javax/servlet/http/HttpServletResponse.setHeader:(Ljava/lang/String;Ljava/lang/String;)V
        //    26: aload_2         /* response */
        //    27: ldc             "Cache-Control"
        //    29: ldc             "no-cache"
        //    31: invokeinterface javax/servlet/http/HttpServletResponse.setHeader:(Ljava/lang/String;Ljava/lang/String;)V
        //    36: aload_2         /* response */
        //    37: ldc             "Expires"
        //    39: lconst_0       
        //    40: invokeinterface javax/servlet/http/HttpServletResponse.setDateHeader:(Ljava/lang/String;J)V
        //    45: aload_2         /* response */
        //    46: ldc             "text/html"
        //    48: invokeinterface javax/servlet/http/HttpServletResponse.setContentType:(Ljava/lang/String;)V
        //    53: aload_1         /* request */
        //    54: iconst_0       
        //    55: invokeinterface javax/servlet/http/HttpServletRequest.getSession:(Z)Ljavax/servlet/http/HttpSession;
        //    60: astore          mysession
        //    62: aload           mysession
        //    64: ldc             "student_id"
        //    66: invokeinterface javax/servlet/http/HttpSession.getAttribute:(Ljava/lang/String;)Ljava/lang/Object;
        //    71: checkcast       Ljava/lang/String;
        //    74: astore          student_id
        //    76: aload_1         /* request */
        //    77: ldc             "unit_id"
        //    79: invokeinterface javax/servlet/http/HttpServletRequest.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //    84: astore          strUnitId
        //    86: aconst_null    
        //    87: astore          strAdminId
        //    89: aload_1         /* request */
        //    90: ldc             "prmAddModify"
        //    92: invokeinterface javax/servlet/http/HttpServletRequest.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //    97: astore          strPrmAddModDel
        //    99: aload           strPrmAddModDel
        //   101: ifnull          179
        //   104: aload           strPrmAddModDel
        //   106: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   109: istore          iPrmAddModify
        //   111: iload           iPrmAddModify
        //   113: tableswitch {
        //                0: 144
        //                1: 179
        //                2: 158
        //                3: 170
        //          default: 179
        //        }
        //   144: aload_0         /* this */
        //   145: aload_1         /* request */
        //   146: aload_2         /* response */
        //   147: aload           strAdminId
        //   149: aload_3         /* out */
        //   150: aload           student_id
        //   152: invokevirtual   sc/sce/selfRegistrationSCE.addSC_User_Association:(Ljavax/servlet/http/HttpServletRequest;Ljavax/servlet/http/HttpServletResponse;Ljava/lang/String;Ljava/io/PrintWriter;Ljava/lang/String;)V
        //   155: goto            179
        //   158: aload_0         /* this */
        //   159: aload_1         /* request */
        //   160: aload_2         /* response */
        //   161: aload_3         /* out */
        //   162: aload           student_id
        //   164: invokevirtual   sc/sce/selfRegistrationSCE.deleteSC_User_Association:(Ljavax/servlet/http/HttpServletRequest;Ljavax/servlet/http/HttpServletResponse;Ljava/io/PrintWriter;Ljava/lang/String;)V
        //   167: goto            179
        //   170: aload_0         /* this */
        //   171: aload_1         /* request */
        //   172: aload_2         /* response */
        //   173: aload_3         /* out */
        //   174: aload           student_id
        //   176: invokevirtual   sc/sce/selfRegistrationSCE.deleteSC_AllUser_Association:(Ljavax/servlet/http/HttpServletRequest;Ljavax/servlet/http/HttpServletResponse;Ljava/io/PrintWriter;Ljava/lang/String;)V
        //   179: new             Ljava/lang/StringBuilder;
        //   182: dup            
        //   183: invokespecial   java/lang/StringBuilder.<init>:()V
        //   186: ldc             "\n\tvar groupIndex = 1;\n\tvar rowId = 0;\n\tvar unitId = \""
        //   188: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   191: aload           strUnitId
        //   193: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   196: ldc             "\";"
        //   198: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   201: ldc             "\n\tvar unitId2nd = 0;"
        //   203: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   206: ldc             "\n"
        //   208: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   211: ldc             "\n\tfunction test() {"
        //   213: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   216: ldc             "\n\t\tvar index = 0;"
        //   218: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   221: ldc             "\n\t\tfor (var i=0;i<document.frm.elements.length;i++){"
        //   223: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   226: ldc             "\n\t\t\tvar e = document.frm.elements[i];"
        //   228: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   231: ldc             "\n\t\t\tif (e.type=='radio'){"
        //   233: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   236: ldc             "\n\t\t\t\tindex++;"
        //   238: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   241: ldc             "\n\t\t\t}"
        //   243: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   246: ldc             "\n\t\t}"
        //   248: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   251: ldc             "\n\t\treturn index;"
        //   253: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   256: ldc             "\n\t}"
        //   258: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   261: ldc             "\n\tfunction UserSelect_onchange() {"
        //   263: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   266: ldc             "\n\t\tdocument.frm.method=\"post\";"
        //   268: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   271: ldc             "\n\t\tdocument.frm.action=\"sce.selfRegistrationSCE\";"
        //   273: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   276: ldc             "\n\t\tdocument.frm.submit();"
        //   278: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   281: ldc             "\n\t}"
        //   283: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   286: ldc             "\n"
        //   288: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   291: ldc             "\n\tfunction findRow(){"
        //   293: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   296: ldc             "\n\t\tfor (var counter=0; counter<document.frm.checkbox.length; counter++) {"
        //   298: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   301: ldc             "\n\t\t\tif (document.frm.checkbox[counter].value == unitId) {"
        //   303: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   306: ldc             "\n\t\t\t\trowId = counter;"
        //   308: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   311: ldc             "\n\t\t\t}"
        //   313: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   316: ldc             "\n\t\t}"
        //   318: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   321: ldc             "\n\t\treturn true;"
        //   323: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   326: ldc             "\n\t}"
        //   328: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   331: ldc             "\n\tfunction checkEntries(){"
        //   333: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   336: ldc             "\n\t\tvar filledIn = false;"
        //   338: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   341: ldc             "\n\t\tvar i = test();"
        //   343: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   346: ldc             "\n\t\tif(i>1) {"
        //   348: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   351: ldc             "\n\t\t\tfor (var counter=0; counter<document.frm.checkbox.length; counter++)"
        //   353: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   356: ldc             "\n\t\t\t\tif (document.frm.checkbox[counter].checked == true) {"
        //   358: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   361: ldc             "\n\t\t\t\t\tfilledIn = true;"
        //   363: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   366: ldc             "\n\t\t\t\t}"
        //   368: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   371: ldc             "\n\t\t\tif (filledIn == false){"
        //   373: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   376: ldc             "\n\t\t\t\talert('You must select at least one file type');"
        //   378: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   381: ldc             "\n\t\t\t\treturn false;"
        //   383: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   386: ldc             "\n\t\t\t}"
        //   388: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   391: ldc             "\n\t\t}"
        //   393: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   396: ldc             "\n\t\tif(i==1) {"
        //   398: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   401: ldc             "\n\t\t\tif (document.frm.checkbox.checked == true) {"
        //   403: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   406: ldc             "\n\t\t\t\tfilledIn = true;"
        //   408: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   411: ldc             "\n\t\t\t}"
        //   413: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   416: ldc             "\n\t\t\tif (filledIn == false){"
        //   418: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   421: ldc             "\n\t\t\t\talert('You must select at least one file type');"
        //   423: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   426: ldc             "\n\t\t\t\treturn false;"
        //   428: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   431: ldc             "\n\t\t\t}"
        //   433: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   436: ldc             "\n\t\t}"
        //   438: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   441: ldc             "\n\t\treturn true;"
        //   443: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   446: ldc             "\n\t}"
        //   448: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   451: ldc             "\n"
        //   453: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   456: ldc             "\n"
        //   458: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   461: ldc             "\n\tfunction setSelectedUnitId(value,index) {"
        //   463: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   466: ldc             "\n\t\tvar x = test();"
        //   468: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   471: ldc             "\n\t\tvar\tdt;"
        //   473: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   476: ldc             "\n\t\tif(x > 1) {"
        //   478: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   481: ldc             "\n\t\t\tfor(var counter=0; counter<document.frm.UnitSelect.length; counter++) {"
        //   483: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   486: ldc             "\n\t\t\t\tif(document.frm.UnitSelect[counter].value==value) {"
        //   488: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   491: ldc             "\n\t\t\t\t\tdocument.frm.UnitSelect.selectedIndex=counter;"
        //   493: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   496: ldc             "\n\t\t\t\t\tunitId2nd = counter;"
        //   498: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   501: ldc             "\n\t\t\t\t\tbreak;"
        //   503: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   506: ldc             "\n\t\t\t\t}"
        //   508: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   511: ldc             "\n\t\t\t}"
        //   513: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   516: ldc             "\n\t\t}"
        //   518: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   521: ldc             "\n\t\tif(x == 1) {"
        //   523: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   526: ldc             "\n\t\t\tfor(var counter=0; counter<document.frm.UnitSelect.length; counter++) {"
        //   528: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   531: ldc             "\n\t\t\t\tif(document.frm.UnitSelect[counter].value==value) {"
        //   533: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   536: ldc             "\n\t\t\t\t\tdocument.frm.UnitSelect.selectedIndex=counter;"
        //   538: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   541: ldc             "\n\t\t\t\t\tunitId2nd = counter;"
        //   543: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   546: ldc             "\n\t\t\t\t\tbreak;"
        //   548: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   551: ldc             "\n\t\t\t\t}"
        //   553: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   556: ldc             "\n\t\t\t}"
        //   558: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   561: ldc             "\n\t\t}"
        //   563: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   566: ldc             "\n\t\treturn unitId2nd;"
        //   568: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   571: ldc             "\n\t}"
        //   573: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   576: ldc             "\n"
        //   578: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   581: ldc             "\n\tfunction isInteger(value) {"
        //   583: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   586: ldc             "\n\t\treturn (parseInt(value) == value);"
        //   588: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   591: ldc             "\n\t}"
        //   593: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   596: ldc             "\n\tfunction validate() {"
        //   598: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   601: ldc             "\n\t\tif (document.frm.UnitSelect.value == '0') {"
        //   603: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   606: ldc             "\n\t\t\talert(\"Select a SCE\");"
        //   608: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   611: ldc             "\n\t\t\tdocument.frm.UnitSelect.focus();"
        //   613: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   616: ldc             "\n\t\t\treturn false;"
        //   618: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   621: ldc             "\n\t\t}"
        //   623: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   626: ldc             "\n\t\treturn true;"
        //   628: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   631: ldc             "\n }"
        //   633: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   636: ldc             "\n\tfunction registerNewUnit_onclick() {"
        //   638: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   641: ldc             "\n\t\tif (validate()) {"
        //   643: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   646: ldc             "\n\t\t\tdocument.frm.method=\"post\";"
        //   648: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   651: ldc             "\n\t\t\tdocument.frm.action = \"sc.sce.selfRegistrationSCE?prmAddModify=0&unit_id=\"+document.frm.UnitSelect.value;"
        //   653: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   656: ldc             "\n\t\t\tdocument.frm.submit();"
        //   658: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   661: ldc             "\n\t\t}"
        //   663: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   666: ldc             "\n\t}"
        //   668: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   671: ldc             "\n\tfunction modifyRegistrationParameter_onclick() {"
        //   673: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   676: ldc             "\n\t\tif((validate())&&(checkEntries())) {"
        //   678: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   681: ldc             "\n\t\t\tdocument.frm.method=\"post\";"
        //   683: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   686: ldc             "\n\t\t\tdocument.frm.action = \"sc.sce.selfRegistrationSCE?prmAddModify=1&unit_id=\"+document.frm.UnitSelect.value;"
        //   688: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   691: ldc             "\n\t\t\tdocument.frm.submit();"
        //   693: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   696: ldc             "\n\t\t}"
        //   698: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   701: ldc             "\n\t}"
        //   703: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   706: ldc             "\n\tfunction unregisteredExistingUnit_onclick() {"
        //   708: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   711: ldc             "\n\t\tif(checkEntries()) {"
        //   713: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   716: ldc             "\n\t\t\tdocument.frm.method=\"post\";"
        //   718: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   721: ldc             "\n\t\t\tdocument.frm.action = \"sc.sce.selfRegistrationSCE?prmAddModify=2\";"
        //   723: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   726: ldc             "\n\t\t\tdocument.frm.submit();"
        //   728: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   731: ldc             "\n\t\t}"
        //   733: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   736: ldc             "\n\t}"
        //   738: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   741: ldc             "\n\tfunction unregisteredAllUnit_onclick() {"
        //   743: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   746: ldc             "\n\t\tif(checkEntries()) {"
        //   748: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   751: ldc             "\n\t\t\tdocument.frm.method=\"post\";"
        //   753: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   756: ldc             "\n\t\t\tdocument.frm.action = \"sc.sce.selfRegistrationSCE?prmAddModify=3\";"
        //   758: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   761: ldc             "\n\t\t\tdocument.frm.submit();"
        //   763: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   766: ldc             "\n\t\t}"
        //   768: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   771: ldc             "\n\t}"
        //   773: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   776: ldc             "\n\tfunction browseCatalogue() {"
        //   778: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   781: ldc             "\n\t\t\tdocument.frm.method=\"post\";"
        //   783: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   786: ldc             "\n\t\t\tdocument.frm.action = \"PortalMgmt.portal.BrowseCatalogSelf?forum=2\";"
        //   788: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   791: ldc             "\n\t\t\twindow.open(\"\",\"browsecatalogself\",\"width=600,height=600,status=yes,scrollbars=yes,resizable=yes,toolbar=no,menubar=no\");"
        //   793: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   796: ldc             "\n\t\t\tdocument.frm.target=\"browsecatalogself\";"
        //   798: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   801: ldc             "\n\t\t\tdocument.frm.submit();"
        //   803: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   806: ldc             "\n\t}"
        //   808: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   811: ldc             "\n\tfunction checkbox_onclick() {"
        //   813: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   816: ldc             "\n\t\tfor(var counter=0; counter<document.frm.checkbox.length; counter++) {"
        //   818: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   821: ldc             "\n\t\t\tif(document.frm.checkbox[counter].checked) {"
        //   823: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   826: ldc             "\n\t\t\t\tsetSelectedUnitId(document.frm.checkbox[counter].value,counter);"
        //   828: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   831: ldc             "\n\t\t\t\tbreak;"
        //   833: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   836: ldc             "\n\t\t\t}"
        //   838: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   841: ldc             "\n\t\t}"
        //   843: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   846: ldc             "\n\t}"
        //   848: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   851: ldc             "\n"
        //   853: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   856: ldc             "\n\tfunction findUser(){"
        //   858: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   861: ldc             "\n\t\tfor (var counter=0; counter<document.frm.UserSelect.length; counter++) {"
        //   863: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   866: ldc             "\n\t\t\tif (document.frm.UserSelect[counter].value == groupId) {"
        //   868: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   871: ldc             "\n\t\t\t\tgroupIndex = counter;"
        //   873: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   876: ldc             "\n\t\t\t}"
        //   878: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   881: ldc             "\n\t\t}"
        //   883: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   886: ldc             "\n\t\treturn true;"
        //   888: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   891: ldc             "\n\t}"
        //   893: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   896: ldc             "\n function load() {"
        //   898: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   901: ldc             "\n\t\tvar i = test();"
        //   903: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   906: ldc             "\n\t\tif(i > 1) {"
        //   908: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   911: ldc             "\n\t\t\tfindRow();"
        //   913: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   916: ldc             "\n\t\t\tdocument.frm.checkbox[rowId].checked=true;"
        //   918: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   921: ldc             "\n\t\t\tsetSelectedUnitId(document.frm.checkbox[rowId].value,rowId);"
        //   923: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   926: ldc             "\n\t\t}"
        //   928: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   931: ldc             "\n\t\tif(i == 1) {"
        //   933: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   936: ldc             "\n\t\t\tdocument.frm.checkbox.checked=true;"
        //   938: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   941: ldc             "\n\t\t\tsetSelectedUnitId(document.frm.checkbox.value,0);"
        //   943: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   946: ldc             "\n\t\t}"
        //   948: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   951: ldc             "\n\t\tCCA();"
        //   953: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   956: ldc             "\n\t}"
        //   958: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   961: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   964: astore          javaScript
        //   966: aconst_null    
        //   967: astore          option2
        //   969: invokestatic    sc/sceadmin/SceDataBaseLayer.getSCEListSelf:()Ljava/util/Vector;
        //   972: astore          vUnitList
        //   974: aload           vUnitList
        //   976: ifnonnull       1006
        //   979: iconst_1       
        //   980: anewarray       Lorg/apache/ecs/html/Option;
        //   983: astore          option2
        //   985: aload           option2
        //   987: iconst_0       
        //   988: new             Lorg/apache/ecs/html/Option;
        //   991: dup            
        //   992: ldc             "0"
        //   994: invokespecial   org/apache/ecs/html/Option.<init>:(Ljava/lang/String;)V
        //   997: ldc             "[Choose One]"
        //   999: invokevirtual   org/apache/ecs/html/Option.addElement:(Ljava/lang/String;)Lorg/apache/ecs/html/Option;
        //  1002: aastore        
        //  1003: goto            1110
        //  1006: aload           vUnitList
        //  1008: invokevirtual   java/util/Vector.size:()I
        //  1011: iconst_1       
        //  1012: iadd           
        //  1013: anewarray       Lorg/apache/ecs/html/Option;
        //  1016: astore          option2
        //  1018: aload           option2
        //  1020: iconst_0       
        //  1021: new             Lorg/apache/ecs/html/Option;
        //  1024: dup            
        //  1025: ldc             "0"
        //  1027: invokespecial   org/apache/ecs/html/Option.<init>:(Ljava/lang/String;)V
        //  1030: ldc             "[Choose One]"
        //  1032: invokevirtual   org/apache/ecs/html/Option.addElement:(Ljava/lang/String;)Lorg/apache/ecs/html/Option;
        //  1035: aastore        
        //  1036: iconst_0       
        //  1037: istore          i
        //  1039: iload           i
        //  1041: aload           vUnitList
        //  1043: invokevirtual   java/util/Vector.size:()I
        //  1046: if_icmpge       1110
        //  1049: aload           vUnitList
        //  1051: iload           i
        //  1053: invokevirtual   java/util/Vector.elementAt:(I)Ljava/lang/Object;
        //  1056: checkcast       Ljava/util/Vector;
        //  1059: astore          vUnit
        //  1061: aload           vUnit
        //  1063: iconst_0       
        //  1064: invokevirtual   java/util/Vector.elementAt:(I)Ljava/lang/Object;
        //  1067: checkcast       Ljava/lang/String;
        //  1070: astore          strId
        //  1072: aload           vUnit
        //  1074: iconst_1       
        //  1075: invokevirtual   java/util/Vector.elementAt:(I)Ljava/lang/Object;
        //  1078: checkcast       Ljava/lang/String;
        //  1081: astore          strName
        //  1083: aload           option2
        //  1085: iload           i
        //  1087: iconst_1       
        //  1088: iadd           
        //  1089: new             Lorg/apache/ecs/html/Option;
        //  1092: dup            
        //  1093: aload           strId
        //  1095: invokespecial   org/apache/ecs/html/Option.<init>:(Ljava/lang/String;)V
        //  1098: aload           strName
        //  1100: invokevirtual   org/apache/ecs/html/Option.addElement:(Ljava/lang/String;)Lorg/apache/ecs/html/Option;
        //  1103: aastore        
        //  1104: iinc            i, 1
        //  1107: goto            1039
        //  1110: new             Lorg/apache/ecs/html/Body;
        //  1113: dup            
        //  1114: invokespecial   org/apache/ecs/html/Body.<init>:()V
        //  1117: astore          body
        //  1119: new             Lorg/apache/ecs/html/Form;
        //  1122: dup            
        //  1123: invokespecial   org/apache/ecs/html/Form.<init>:()V
        //  1126: astore          form
        //  1128: new             Lorg/apache/ecs/html/Form;
        //  1131: dup            
        //  1132: invokespecial   org/apache/ecs/html/Form.<init>:()V
        //  1135: astore          formpending
        //  1137: new             Lorg/apache/ecs/html/Input;
        //  1140: dup            
        //  1141: invokespecial   org/apache/ecs/html/Input.<init>:()V
        //  1144: astore          inputButton1
        //  1146: new             Lorg/apache/ecs/html/Input;
        //  1149: dup            
        //  1150: invokespecial   org/apache/ecs/html/Input.<init>:()V
        //  1153: astore          inputButton3
        //  1155: new             Lorg/apache/ecs/html/Input;
        //  1158: dup            
        //  1159: invokespecial   org/apache/ecs/html/Input.<init>:()V
        //  1162: astore          inputButton4
        //  1164: new             Lorg/apache/ecs/html/Input;
        //  1167: dup            
        //  1168: invokespecial   org/apache/ecs/html/Input.<init>:()V
        //  1171: astore          inputButton5
        //  1173: new             Lorg/apache/ecs/html/Html;
        //  1176: dup            
        //  1177: invokespecial   org/apache/ecs/html/Html.<init>:()V
        //  1180: new             Lorg/apache/ecs/html/Head;
        //  1183: dup            
        //  1184: invokespecial   org/apache/ecs/html/Head.<init>:()V
        //  1187: new             Lorg/apache/ecs/html/Title;
        //  1190: dup            
        //  1191: ldc             "Self Registration"
        //  1193: invokespecial   org/apache/ecs/html/Title.<init>:(Ljava/lang/String;)V
        //  1196: invokevirtual   org/apache/ecs/html/Head.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Head;
        //  1199: new             Lorg/apache/ecs/html/Link;
        //  1202: dup            
        //  1203: invokespecial   org/apache/ecs/html/Link.<init>:()V
        //  1206: ldc             "../js/stylesheet.css"
        //  1208: invokevirtual   org/apache/ecs/html/Link.setHref:(Ljava/lang/String;)Lorg/apache/ecs/html/Link;
        //  1211: ldc             "stylesheet"
        //  1213: invokevirtual   org/apache/ecs/html/Link.setRel:(Ljava/lang/String;)Lorg/apache/ecs/html/Link;
        //  1216: invokevirtual   org/apache/ecs/html/Head.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Head;
        //  1219: new             Lorg/apache/ecs/html/Script;
        //  1222: dup            
        //  1223: invokespecial   org/apache/ecs/html/Script.<init>:()V
        //  1226: ldc             "JavaScript"
        //  1228: invokevirtual   org/apache/ecs/html/Script.setLanguage:(Ljava/lang/String;)Lorg/apache/ecs/html/Script;
        //  1231: ldc             "../js/check.js"
        //  1233: invokevirtual   org/apache/ecs/html/Script.setSrc:(Ljava/lang/String;)Lorg/apache/ecs/html/Script;
        //  1236: invokevirtual   org/apache/ecs/html/Head.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Head;
        //  1239: new             Lorg/apache/ecs/html/Script;
        //  1242: dup            
        //  1243: invokespecial   org/apache/ecs/html/Script.<init>:()V
        //  1246: ldc             "JavaScript"
        //  1248: invokevirtual   org/apache/ecs/html/Script.setLanguage:(Ljava/lang/String;)Lorg/apache/ecs/html/Script;
        //  1251: ldc             "../js/calendar.js"
        //  1253: invokevirtual   org/apache/ecs/html/Script.setSrc:(Ljava/lang/String;)Lorg/apache/ecs/html/Script;
        //  1256: invokevirtual   org/apache/ecs/html/Head.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Head;
        //  1259: new             Lorg/apache/ecs/html/Script;
        //  1262: dup            
        //  1263: invokespecial   org/apache/ecs/html/Script.<init>:()V
        //  1266: ldc             "JavaScript"
        //  1268: invokevirtual   org/apache/ecs/html/Script.setLanguage:(Ljava/lang/String;)Lorg/apache/ecs/html/Script;
        //  1271: ldc             "../js/validate.js"
        //  1273: invokevirtual   org/apache/ecs/html/Script.setSrc:(Ljava/lang/String;)Lorg/apache/ecs/html/Script;
        //  1276: invokevirtual   org/apache/ecs/html/Head.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Head;
        //  1279: new             Lorg/apache/ecs/html/Script;
        //  1282: dup            
        //  1283: invokespecial   org/apache/ecs/html/Script.<init>:()V
        //  1286: ldc             "JavaScript"
        //  1288: invokevirtual   org/apache/ecs/html/Script.setLanguage:(Ljava/lang/String;)Lorg/apache/ecs/html/Script;
        //  1291: aload           javaScript
        //  1293: invokevirtual   org/apache/ecs/html/Script.addElement:(Ljava/lang/String;)Lorg/apache/ecs/html/Script;
        //  1296: invokevirtual   org/apache/ecs/html/Head.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Head;
        //  1299: invokevirtual   org/apache/ecs/html/Html.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Html;
        //  1302: aload           body
        //  1304: aload           formpending
        //  1306: ldc             "frmpend"
        //  1308: invokevirtual   org/apache/ecs/html/Form.setName:(Ljava/lang/String;)Lorg/apache/ecs/html/Form;
        //  1311: invokevirtual   org/apache/ecs/html/Body.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Body;
        //  1314: aload           form
        //  1316: ldc             "frm"
        //  1318: invokevirtual   org/apache/ecs/html/Form.setName:(Ljava/lang/String;)Lorg/apache/ecs/html/Form;
        //  1321: invokevirtual   org/apache/ecs/html/Body.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Body;
        //  1324: invokevirtual   org/apache/ecs/html/Html.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Html;
        //  1327: astore          html
        //  1329: new             Ljava/lang/StringBuilder;
        //  1332: dup            
        //  1333: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1336: ldc             "select b.name as \"SCE Name\",a.status as \"Status\" from confirmation_requisition a, synchronous_collaboration b where a.user_id='"
        //  1338: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1341: aload           student_id
        //  1343: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1346: ldc             "' and a.id=b.id and a.entity_type = 'S'"
        //  1348: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1351: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1354: astore          sql1
        //  1356: new             Lcom/aunwesha/JSPGrid/JSPGridPro;
        //  1359: dup            
        //  1360: aload_1         /* request */
        //  1361: ldc             "frmpend"
        //  1363: invokespecial   com/aunwesha/JSPGrid/JSPGridPro.<init>:(Ljavax/servlet/http/HttpServletRequest;Ljava/lang/String;)V
        //  1366: astore          grid2
        //  1368: aload           grid2
        //  1370: aload           sql1
        //  1372: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setConnectionParameters:(Ljava/lang/String;)V
        //  1375: aload           grid2
        //  1377: ldc             "100%"
        //  1379: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setWidth:(Ljava/lang/String;)V
        //  1382: aload           grid2
        //  1384: iconst_2       
        //  1385: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setCellPadding:(I)V
        //  1388: aload           grid2
        //  1390: iconst_1       
        //  1391: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setCellSpacing:(I)V
        //  1394: aload           grid2
        //  1396: ldc             "Arial"
        //  1398: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setFontFace:(Ljava/lang/String;)V
        //  1401: aload           grid2
        //  1403: iconst_2       
        //  1404: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setFontSize:(I)V
        //  1407: aload           grid2
        //  1409: ldc             "#C0C0C0"
        //  1411: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setEvenRowBgColor:(Ljava/lang/String;)V
        //  1414: aload           grid2
        //  1416: ldc             "#F0F0F0"
        //  1418: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setOddRowBgColor:(Ljava/lang/String;)V
        //  1421: aload           grid2
        //  1423: ldc             "Currently Pending SCE"
        //  1425: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setCaption:(Ljava/lang/String;)V
        //  1428: aload           grid2
        //  1430: iconst_5       
        //  1431: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setMaxRowsPerPage:(I)V
        //  1434: aload           grid2
        //  1436: iconst_5       
        //  1437: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setMaxResultPagesPerLoad:(I)V
        //  1440: aload           grid2
        //  1442: ldc             "#48E6F7"
        //  1444: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setLineNoHeaderBgColor:(Ljava/lang/String;)V
        //  1447: aload           grid2
        //  1449: iconst_0       
        //  1450: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.Cols:(I)Lcom/aunwesha/JSPGrid/JSPCol;
        //  1453: invokevirtual   com/aunwesha/JSPGrid/JSPCol.Header:()Lcom/aunwesha/JSPGrid/JSPHeader;
        //  1456: ldc             "swb"
        //  1458: invokevirtual   com/aunwesha/JSPGrid/JSPHeader.setClassID:(Ljava/lang/String;)V
        //  1461: aload           grid2
        //  1463: iconst_1       
        //  1464: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.Cols:(I)Lcom/aunwesha/JSPGrid/JSPCol;
        //  1467: invokevirtual   com/aunwesha/JSPGrid/JSPCol.Header:()Lcom/aunwesha/JSPGrid/JSPHeader;
        //  1470: ldc             "swb"
        //  1472: invokevirtual   com/aunwesha/JSPGrid/JSPHeader.setClassID:(Ljava/lang/String;)V
        //  1475: aload           grid2
        //  1477: ldc             "20"
        //  1479: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setEachRowHeight:(Ljava/lang/String;)V
        //  1482: aload           grid2
        //  1484: iconst_0       
        //  1485: iconst_0       
        //  1486: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.canSort:(IZ)V
        //  1489: aload           grid2
        //  1491: ldc             "Click to Sort"
        //  1493: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setSortableColumnsToolTip:(Ljava/lang/String;)V
        //  1496: aload           grid2
        //  1498: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.showPageNavigationFirst:()V
        //  1501: aload           grid2
        //  1503: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.showPageNavigationLast:()V
        //  1506: aload           grid2
        //  1508: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.hidePageNavigationHTML:()V
        //  1511: aload           grid2
        //  1513: ldc             "Arial"
        //  1515: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setPageNavigationFontFace:(Ljava/lang/String;)V
        //  1518: aload           grid2
        //  1520: iconst_2       
        //  1521: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setPageNavigationFontSize:(I)V
        //  1524: aload           grid2
        //  1526: ldc             "../images/asc.gif"
        //  1528: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setASCImageSource:(Ljava/lang/String;)V
        //  1531: aload           grid2
        //  1533: ldc             "../images/desc.gif"
        //  1535: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setDESCImageSource:(Ljava/lang/String;)V
        //  1538: aload           grid2
        //  1540: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.buildGrid:()V
        //  1543: aload           grid2
        //  1545: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.isResultSetEmpty:()Z
        //  1548: ifeq            1562
        //  1551: aload           formpending
        //  1553: ldc             "<p id=\"record\">No Pending SCE"
        //  1555: invokevirtual   org/apache/ecs/html/Form.addElement:(Ljava/lang/String;)Lorg/apache/ecs/html/Form;
        //  1558: pop            
        //  1559: goto            1578
        //  1562: aload           grid2
        //  1564: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.countResultSet:()V
        //  1567: aload           formpending
        //  1569: aload           grid2
        //  1571: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.getGrid:()Ljava/lang/String;
        //  1574: invokevirtual   org/apache/ecs/html/Form.addElement:(Ljava/lang/String;)Lorg/apache/ecs/html/Form;
        //  1577: pop            
        //  1578: goto            1588
        //  1581: astore          exp
        //  1583: aload           exp
        //  1585: invokevirtual   java/lang/Exception.printStackTrace:()V
        //  1588: new             Ljava/lang/StringBuilder;
        //  1591: dup            
        //  1592: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1595: ldc             "select a.id as \"Select\", b.name as\"SCE Name\" from sc_user_association a, synchronous_collaboration b where user_id='"
        //  1597: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1600: aload           student_id
        //  1602: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1605: ldc             "' "
        //  1607: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1610: ldc             "and a.id=b.id and a.registered_by <=> 'null'"
        //  1612: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1615: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1618: astore          sql
        //  1620: new             Lcom/aunwesha/JSPGrid/JSPGridPro;
        //  1623: dup            
        //  1624: aload_1         /* request */
        //  1625: ldc             "frm"
        //  1627: invokespecial   com/aunwesha/JSPGrid/JSPGridPro.<init>:(Ljavax/servlet/http/HttpServletRequest;Ljava/lang/String;)V
        //  1630: astore          grid1
        //  1632: aload           grid1
        //  1634: aload           sql
        //  1636: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setConnectionParameters:(Ljava/lang/String;)V
        //  1639: aload           grid1
        //  1641: ldc             "100%"
        //  1643: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setWidth:(Ljava/lang/String;)V
        //  1646: aload           grid1
        //  1648: iconst_2       
        //  1649: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setCellPadding:(I)V
        //  1652: aload           grid1
        //  1654: iconst_1       
        //  1655: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setCellSpacing:(I)V
        //  1658: aload           grid1
        //  1660: ldc             "Arial"
        //  1662: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setFontFace:(Ljava/lang/String;)V
        //  1665: aload           grid1
        //  1667: iconst_2       
        //  1668: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setFontSize:(I)V
        //  1671: aload           grid1
        //  1673: ldc             "#C0C0C0"
        //  1675: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setEvenRowBgColor:(Ljava/lang/String;)V
        //  1678: aload           grid1
        //  1680: ldc             "#F0F0F0"
        //  1682: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setOddRowBgColor:(Ljava/lang/String;)V
        //  1685: aload           grid1
        //  1687: ldc             "Currently Self Registered Synchronous Collaboration Environment"
        //  1689: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setCaption:(Ljava/lang/String;)V
        //  1692: aload           grid1
        //  1694: iconst_5       
        //  1695: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setMaxRowsPerPage:(I)V
        //  1698: aload           grid1
        //  1700: iconst_5       
        //  1701: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setMaxResultPagesPerLoad:(I)V
        //  1704: aload           grid1
        //  1706: ldc             "#48E6F7"
        //  1708: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setLineNoHeaderBgColor:(Ljava/lang/String;)V
        //  1711: aload           grid1
        //  1713: iconst_0       
        //  1714: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.Cols:(I)Lcom/aunwesha/JSPGrid/JSPCol;
        //  1717: aload           grid1
        //  1719: pop            
        //  1720: ldc             "radio"
        //  1722: invokevirtual   com/aunwesha/JSPGrid/JSPCol.setFieldType:(Ljava/lang/String;)V
        //  1725: aload           grid1
        //  1727: iconst_1       
        //  1728: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.Cols:(I)Lcom/aunwesha/JSPGrid/JSPCol;
        //  1731: aload           grid1
        //  1733: pop            
        //  1734: ldc             "HIDDEN"
        //  1736: invokevirtual   com/aunwesha/JSPGrid/JSPCol.setFieldType:(Ljava/lang/String;)V
        //  1739: aload           grid1
        //  1741: iconst_0       
        //  1742: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.Cols:(I)Lcom/aunwesha/JSPGrid/JSPCol;
        //  1745: ldc             "checkbox"
        //  1747: invokevirtual   com/aunwesha/JSPGrid/JSPCol.setFieldName:(Ljava/lang/String;)V
        //  1750: aload           grid1
        //  1752: iconst_1       
        //  1753: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.Cols:(I)Lcom/aunwesha/JSPGrid/JSPCol;
        //  1756: ldc             "UnitName"
        //  1758: invokevirtual   com/aunwesha/JSPGrid/JSPCol.setFieldName:(Ljava/lang/String;)V
        //  1761: aload           grid1
        //  1763: iconst_0       
        //  1764: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.Cols:(I)Lcom/aunwesha/JSPGrid/JSPCol;
        //  1767: invokevirtual   com/aunwesha/JSPGrid/JSPCol.Header:()Lcom/aunwesha/JSPGrid/JSPHeader;
        //  1770: ldc             "swb"
        //  1772: invokevirtual   com/aunwesha/JSPGrid/JSPHeader.setClassID:(Ljava/lang/String;)V
        //  1775: aload           grid1
        //  1777: iconst_1       
        //  1778: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.Cols:(I)Lcom/aunwesha/JSPGrid/JSPCol;
        //  1781: invokevirtual   com/aunwesha/JSPGrid/JSPCol.Header:()Lcom/aunwesha/JSPGrid/JSPHeader;
        //  1784: ldc             "swb"
        //  1786: invokevirtual   com/aunwesha/JSPGrid/JSPHeader.setClassID:(Ljava/lang/String;)V
        //  1789: aload           grid1
        //  1791: iconst_0       
        //  1792: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.Cols:(I)Lcom/aunwesha/JSPGrid/JSPCol;
        //  1795: ldc             "onclick=\"CCA();checkbox_onclick();\""
        //  1797: invokevirtual   com/aunwesha/JSPGrid/JSPCol.insertFieldScript:(Ljava/lang/String;)V
        //  1800: aload           grid1
        //  1802: ldc             "20"
        //  1804: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setEachRowHeight:(Ljava/lang/String;)V
        //  1807: aload           grid1
        //  1809: iconst_0       
        //  1810: iconst_0       
        //  1811: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.canSort:(IZ)V
        //  1814: aload           grid1
        //  1816: iconst_1       
        //  1817: iconst_1       
        //  1818: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.canSort:(IZ)V
        //  1821: aload           grid1
        //  1823: ldc             "Click to Sort"
        //  1825: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setSortableColumnsToolTip:(Ljava/lang/String;)V
        //  1828: aload           grid1
        //  1830: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.showPageNavigationFirst:()V
        //  1833: aload           grid1
        //  1835: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.showPageNavigationLast:()V
        //  1838: aload           grid1
        //  1840: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.hidePageNavigationHTML:()V
        //  1843: aload           grid1
        //  1845: ldc             "Arial"
        //  1847: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setPageNavigationFontFace:(Ljava/lang/String;)V
        //  1850: aload           grid1
        //  1852: iconst_2       
        //  1853: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setPageNavigationFontSize:(I)V
        //  1856: aload           grid1
        //  1858: ldc             "../images/asc.gif"
        //  1860: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setASCImageSource:(Ljava/lang/String;)V
        //  1863: aload           grid1
        //  1865: ldc             "../images/desc.gif"
        //  1867: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.setDESCImageSource:(Ljava/lang/String;)V
        //  1870: aload           grid1
        //  1872: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.buildGrid:()V
        //  1875: aload           grid1
        //  1877: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.isResultSetEmpty:()Z
        //  1880: ifeq            1894
        //  1883: aload           form
        //  1885: ldc             "<p id=\"record\">No SCE's Found"
        //  1887: invokevirtual   org/apache/ecs/html/Form.addElement:(Ljava/lang/String;)Lorg/apache/ecs/html/Form;
        //  1890: pop            
        //  1891: goto            1910
        //  1894: aload           grid1
        //  1896: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.countResultSet:()V
        //  1899: aload           form
        //  1901: aload           grid1
        //  1903: invokevirtual   com/aunwesha/JSPGrid/JSPGridPro.getGrid:()Ljava/lang/String;
        //  1906: invokevirtual   org/apache/ecs/html/Form.addElement:(Ljava/lang/String;)Lorg/apache/ecs/html/Form;
        //  1909: pop            
        //  1910: goto            1915
        //  1913: astore          exp
        //  1915: aload           form
        //  1917: new             Lsc/sceadmin/TableExtension;
        //  1920: dup            
        //  1921: invokespecial   sc/sceadmin/TableExtension.<init>:()V
        //  1924: invokevirtual   sc/sceadmin/TableExtension.add:()Lorg/apache/ecs/html/Table;
        //  1927: new             Lorg/apache/ecs/html/Table;
        //  1930: dup            
        //  1931: invokespecial   org/apache/ecs/html/Table.<init>:()V
        //  1934: iconst_0       
        //  1935: invokevirtual   org/apache/ecs/html/Table.setBorder:(I)Lorg/apache/ecs/html/Table;
        //  1938: iconst_0       
        //  1939: invokevirtual   org/apache/ecs/html/Table.setCellPadding:(I)Lorg/apache/ecs/html/Table;
        //  1942: iconst_0       
        //  1943: invokevirtual   org/apache/ecs/html/Table.setCellSpacing:(I)Lorg/apache/ecs/html/Table;
        //  1946: ldc             "100%"
        //  1948: invokevirtual   org/apache/ecs/html/Table.setWidth:(Ljava/lang/String;)Lorg/apache/ecs/html/Table;
        //  1951: new             Lsc/sceadmin/TRExtension;
        //  1954: dup            
        //  1955: invokespecial   sc/sceadmin/TRExtension.<init>:()V
        //  1958: invokevirtual   sc/sceadmin/TRExtension.addLine:()Lorg/apache/ecs/html/TR;
        //  1961: invokevirtual   org/apache/ecs/html/Table.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Table;
        //  1964: new             Lorg/apache/ecs/html/TR;
        //  1967: dup            
        //  1968: invokespecial   org/apache/ecs/html/TR.<init>:()V
        //  1971: new             Lorg/apache/ecs/html/TD;
        //  1974: dup            
        //  1975: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  1978: ldc             "023264"
        //  1980: invokevirtual   org/apache/ecs/html/TD.setBgColor:(Ljava/lang/String;)Lorg/apache/ecs/html/TD;
        //  1983: iconst_1       
        //  1984: invokevirtual   org/apache/ecs/html/TD.setWidth:(I)Lorg/apache/ecs/html/TD;
        //  1987: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  1990: new             Lorg/apache/ecs/html/TD;
        //  1993: dup            
        //  1994: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  1997: new             Lsc/sceadmin/TableExtension;
        //  2000: dup            
        //  2001: invokespecial   sc/sceadmin/TableExtension.<init>:()V
        //  2004: ldc             "SCE Available For Self Registration"
        //  2006: ldc             "UnitSelect"
        //  2008: ldc             "1"
        //  2010: aload           option2
        //  2012: invokevirtual   sc/sceadmin/TableExtension.addSelect:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Lorg/apache/ecs/html/Option;)Lorg/apache/ecs/html/Table;
        //  2015: invokevirtual   org/apache/ecs/html/TD.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TD;
        //  2018: new             Lsc/sceadmin/TableExtension;
        //  2021: dup            
        //  2022: invokespecial   sc/sceadmin/TableExtension.<init>:()V
        //  2025: invokevirtual   sc/sceadmin/TableExtension.add:()Lorg/apache/ecs/html/Table;
        //  2028: invokevirtual   org/apache/ecs/html/TD.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TD;
        //  2031: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  2034: new             Lorg/apache/ecs/html/TD;
        //  2037: dup            
        //  2038: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  2041: ldc             "023264"
        //  2043: invokevirtual   org/apache/ecs/html/TD.setBgColor:(Ljava/lang/String;)Lorg/apache/ecs/html/TD;
        //  2046: ldc             "1"
        //  2048: invokevirtual   org/apache/ecs/html/TD.setWidth:(Ljava/lang/String;)Lorg/apache/ecs/html/TD;
        //  2051: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  2054: invokevirtual   org/apache/ecs/html/Table.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Table;
        //  2057: new             Lsc/sceadmin/TRExtension;
        //  2060: dup            
        //  2061: invokespecial   sc/sceadmin/TRExtension.<init>:()V
        //  2064: invokevirtual   sc/sceadmin/TRExtension.addLine:()Lorg/apache/ecs/html/TR;
        //  2067: invokevirtual   org/apache/ecs/html/Table.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Table;
        //  2070: invokevirtual   org/apache/ecs/html/Table.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Table;
        //  2073: new             Lsc/sceadmin/TableExtension;
        //  2076: dup            
        //  2077: invokespecial   sc/sceadmin/TableExtension.<init>:()V
        //  2080: invokevirtual   sc/sceadmin/TableExtension.add:()Lorg/apache/ecs/html/Table;
        //  2083: invokevirtual   org/apache/ecs/html/Table.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Table;
        //  2086: invokevirtual   org/apache/ecs/html/Form.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Form;
        //  2089: pop            
        //  2090: aload           form
        //  2092: new             Lorg/apache/ecs/html/Table;
        //  2095: dup            
        //  2096: invokespecial   org/apache/ecs/html/Table.<init>:()V
        //  2099: iconst_0       
        //  2100: invokevirtual   org/apache/ecs/html/Table.setBorder:(I)Lorg/apache/ecs/html/Table;
        //  2103: iconst_0       
        //  2104: invokevirtual   org/apache/ecs/html/Table.setCellPadding:(I)Lorg/apache/ecs/html/Table;
        //  2107: iconst_0       
        //  2108: invokevirtual   org/apache/ecs/html/Table.setCellSpacing:(I)Lorg/apache/ecs/html/Table;
        //  2111: new             Lorg/apache/ecs/html/TR;
        //  2114: dup            
        //  2115: invokespecial   org/apache/ecs/html/TR.<init>:()V
        //  2118: new             Lorg/apache/ecs/html/TD;
        //  2121: dup            
        //  2122: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  2125: aload           inputButton1
        //  2127: ldc             "sbttn"
        //  2129: invokevirtual   org/apache/ecs/html/Input.setClassId:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2132: ldc             "registerNewUnit"
        //  2134: invokevirtual   org/apache/ecs/html/Input.setName:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2137: iconst_2       
        //  2138: invokevirtual   org/apache/ecs/html/Input.setTabindex:(I)Lorg/apache/ecs/html/Input;
        //  2141: ldc_w           "Register New SCE"
        //  2144: invokevirtual   org/apache/ecs/html/Input.setTitleValue:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2147: ldc_w           "button"
        //  2150: invokevirtual   org/apache/ecs/html/Input.setType:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2153: ldc_w           "Register New SCE"
        //  2156: invokevirtual   org/apache/ecs/html/Input.setValue:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2159: invokevirtual   org/apache/ecs/html/TD.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TD;
        //  2162: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  2165: new             Lorg/apache/ecs/html/TD;
        //  2168: dup            
        //  2169: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  2172: iconst_5       
        //  2173: invokevirtual   org/apache/ecs/html/TD.setWidth:(I)Lorg/apache/ecs/html/TD;
        //  2176: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  2179: new             Lorg/apache/ecs/html/TD;
        //  2182: dup            
        //  2183: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  2186: aload           inputButton3
        //  2188: ldc             "sbttn"
        //  2190: invokevirtual   org/apache/ecs/html/Input.setClassId:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2193: ldc_w           "unregisteredExistingUnit"
        //  2196: invokevirtual   org/apache/ecs/html/Input.setName:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2199: iconst_2       
        //  2200: invokevirtual   org/apache/ecs/html/Input.setTabindex:(I)Lorg/apache/ecs/html/Input;
        //  2203: ldc_w           "Unregister Existing SCE"
        //  2206: invokevirtual   org/apache/ecs/html/Input.setTitleValue:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2209: ldc_w           "button"
        //  2212: invokevirtual   org/apache/ecs/html/Input.setType:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2215: ldc_w           "Unregister Existing SCE"
        //  2218: invokevirtual   org/apache/ecs/html/Input.setValue:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2221: invokevirtual   org/apache/ecs/html/TD.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TD;
        //  2224: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  2227: new             Lorg/apache/ecs/html/TD;
        //  2230: dup            
        //  2231: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  2234: iconst_5       
        //  2235: invokevirtual   org/apache/ecs/html/TD.setWidth:(I)Lorg/apache/ecs/html/TD;
        //  2238: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  2241: new             Lorg/apache/ecs/html/TD;
        //  2244: dup            
        //  2245: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  2248: aload           inputButton4
        //  2250: ldc             "sbttn"
        //  2252: invokevirtual   org/apache/ecs/html/Input.setClassId:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2255: ldc_w           "unregisteredAllUnit"
        //  2258: invokevirtual   org/apache/ecs/html/Input.setName:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2261: iconst_2       
        //  2262: invokevirtual   org/apache/ecs/html/Input.setTabindex:(I)Lorg/apache/ecs/html/Input;
        //  2265: ldc_w           "Unregister All SCE"
        //  2268: invokevirtual   org/apache/ecs/html/Input.setTitleValue:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2271: ldc_w           "button"
        //  2274: invokevirtual   org/apache/ecs/html/Input.setType:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2277: ldc_w           "Unregister All SCE"
        //  2280: invokevirtual   org/apache/ecs/html/Input.setValue:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2283: invokevirtual   org/apache/ecs/html/TD.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TD;
        //  2286: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  2289: new             Lorg/apache/ecs/html/TD;
        //  2292: dup            
        //  2293: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  2296: iconst_5       
        //  2297: invokevirtual   org/apache/ecs/html/TD.setWidth:(I)Lorg/apache/ecs/html/TD;
        //  2300: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  2303: new             Lorg/apache/ecs/html/TD;
        //  2306: dup            
        //  2307: invokespecial   org/apache/ecs/html/TD.<init>:()V
        //  2310: aload           inputButton5
        //  2312: ldc             "sbttn"
        //  2314: invokevirtual   org/apache/ecs/html/Input.setClassId:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2317: ldc_w           "browse"
        //  2320: invokevirtual   org/apache/ecs/html/Input.setName:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2323: iconst_2       
        //  2324: invokevirtual   org/apache/ecs/html/Input.setTabindex:(I)Lorg/apache/ecs/html/Input;
        //  2327: ldc_w           "Browse Catalogue"
        //  2330: invokevirtual   org/apache/ecs/html/Input.setTitleValue:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2333: ldc_w           "button"
        //  2336: invokevirtual   org/apache/ecs/html/Input.setType:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2339: ldc_w           "Browse Catalogue"
        //  2342: invokevirtual   org/apache/ecs/html/Input.setValue:(Ljava/lang/String;)Lorg/apache/ecs/html/Input;
        //  2345: invokevirtual   org/apache/ecs/html/TD.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TD;
        //  2348: invokevirtual   org/apache/ecs/html/TR.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/TR;
        //  2351: invokevirtual   org/apache/ecs/html/Table.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Table;
        //  2354: invokevirtual   org/apache/ecs/html/Form.addElement:(Lorg/apache/ecs/Element;)Lorg/apache/ecs/html/Form;
        //  2357: pop            
        //  2358: aload           body
        //  2360: ldc_w           "scrollit(100);load()"
        //  2363: invokevirtual   org/apache/ecs/html/Body.setOnLoad:(Ljava/lang/String;)V
        //  2366: aload           inputButton1
        //  2368: ldc_w           "registerNewUnit_onclick();"
        //  2371: invokevirtual   org/apache/ecs/html/Input.setOnClick:(Ljava/lang/String;)V
        //  2374: aload           inputButton3
        //  2376: ldc_w           "unregisteredExistingUnit_onclick();"
        //  2379: invokevirtual   org/apache/ecs/html/Input.setOnClick:(Ljava/lang/String;)V
        //  2382: aload           inputButton4
        //  2384: ldc_w           "unregisteredAllUnit_onclick();"
        //  2387: invokevirtual   org/apache/ecs/html/Input.setOnClick:(Ljava/lang/String;)V
        //  2390: aload           inputButton5
        //  2392: ldc_w           "browseCatalogue();"
        //  2395: invokevirtual   org/apache/ecs/html/Input.setOnClick:(Ljava/lang/String;)V
        //  2398: aload_3         /* out */
        //  2399: aload           html
        //  2401: invokevirtual   org/apache/ecs/html/Html.toString:()Ljava/lang/String;
        //  2404: invokevirtual   java/io/PrintWriter.println:(Ljava/lang/String;)V
        //  2407: return         
        //    Exceptions:
        //  throws javax.servlet.ServletException
        //  throws java.io.IOException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name             Signature
        //  -----  ------  ----  ---------------  ----------------------------------------
        //  111    68      9     iPrmAddModify    I
        //  1061   43      13    vUnit            Ljava/util/Vector;
        //  1072   32      14    strId            Ljava/lang/String;
        //  1083   21      15    strName          Ljava/lang/String;
        //  1039   71      12    i                I
        //  1368   210     21    grid2            Lcom/aunwesha/JSPGrid/JSPGridPro;
        //  1583   5       21    exp              Ljava/lang/Exception;
        //  1632   278     22    grid1            Lcom/aunwesha/JSPGrid/JSPGridPro;
        //  1915   0       22    exp              Ljava/lang/Exception;
        //  0      2408    0     this             Lsc/sce/selfRegistrationSCE;
        //  0      2408    1     request          Ljavax/servlet/http/HttpServletRequest;
        //  0      2408    2     response         Ljavax/servlet/http/HttpServletResponse;
        //  7      2401    3     out              Ljava/io/PrintWriter;
        //  62     2346    4     mysession        Ljavax/servlet/http/HttpSession;
        //  76     2332    5     student_id       Ljava/lang/String;
        //  86     2322    6     strUnitId        Ljava/lang/String;
        //  89     2319    7     strAdminId       Ljava/lang/String;
        //  99     2309    8     strPrmAddModDel  Ljava/lang/String;
        //  966    1442    9     javaScript       Ljava/lang/String;
        //  969    1439    10    option2          [Lorg/apache/ecs/html/Option;
        //  974    1434    11    vUnitList        Ljava/util/Vector;
        //  1119   1289    12    body             Lorg/apache/ecs/html/Body;
        //  1128   1280    13    form             Lorg/apache/ecs/html/Form;
        //  1137   1271    14    formpending      Lorg/apache/ecs/html/Form;
        //  1146   1262    15    inputButton1     Lorg/apache/ecs/html/Input;
        //  1155   1253    16    inputButton3     Lorg/apache/ecs/html/Input;
        //  1164   1244    17    inputButton4     Lorg/apache/ecs/html/Input;
        //  1173   1235    18    inputButton5     Lorg/apache/ecs/html/Input;
        //  1329   1079    19    html             Lorg/apache/ecs/html/Html;
        //  1356   1052    20    sql1             Ljava/lang/String;
        //  1620   788     21    sql              Ljava/lang/String;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  1356   1578   1581   1588   Ljava/lang/Exception;
        //  1620   1910   1913   1915   Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.StackOverflowError
        //     at com.strobel.assembler.metadata.TypeReference.getInternalName(TypeReference.java:95)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.getInternalName(CoreMetadataFactory.java:529)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveCore(MetadataSystem.java:76)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:104)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:576)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:128)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:586)
        //     at com.strobel.assembler.metadata.MethodReference.resolve(MethodReference.java:172)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2420)
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
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypesForVariables(TypeAnalysis.java:586)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:397)
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
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    /**
     * TODO: EditableDao Method not found..need to ask Diptendu sir
     */
    
    /*
    public void addSC_User_Association(final HttpServletRequest request, final HttpServletResponse response, final String strCreatedBy, final PrintWriter out, final String student_id) throws IOException, ServletException {
        final String strSCId = request.getParameter("UnitSelect");
        if (SceDataBaseLayer.checkConfirmationSCE(strSCId).equals("T")) {
            selfRegistrationSCE.log.debug("Request Sent");
            SceDataBaseLayer.sentForConfirmationSCE(student_id, strSCId);
        }
        else {
            SceDataBaseLayer.addUserToSCAssociation(strSCId, student_id, strCreatedBy, out);
        }
    }
    
    public void deleteSC_User_Association(final HttpServletRequest request, final HttpServletResponse response, final PrintWriter out, final String strUsrId) throws IOException, ServletException {
        final String strSCId = request.getParameter("UnitSelect");
        SceDataBaseLayer.deleteUserFromSCAssociation(strSCId, strUsrId, out);
    }
    
    public void deleteSC_AllUser_Association(final HttpServletRequest request, final HttpServletResponse response, final PrintWriter out, final String strUsrId) throws IOException, ServletException {
        final Vector vUserSCList = SceDataBaseLayer.getSelfSCE(strUsrId);
        for (int j = 0; j < vUserSCList.size(); ++j) {
            final String strSCId = vUserSCList.elementAt(j);
            SceDataBaseLayer.deleteUserFromSCAssociation(strSCId, strUsrId, out);
        }
    }*/
    
    static {
        log = new SimpleLogger((Class)selfRegistrationSCE.class);
    }
}
