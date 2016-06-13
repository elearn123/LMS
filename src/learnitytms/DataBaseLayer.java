package learnitytms;

import javax.sql.*;
import java.sql.*;
import java.util.*;

import org.grlea.log.*;
import comv2.aunwesha.param.*;

public class DataBaseLayer
{
    public static final SimpleLogger log;
    public static DataSource ds1;
    
    public static synchronized void updateEmployeeProfileFromJob(final String empId) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement1 = oConn.createStatement();
            final Statement statement2 = oConn.createStatement();
            final Statement statement3 = oConn.createStatement();
            final Statement statement4 = oConn.createStatement();
            final Statement statement5 = oConn.createStatement();
            final ResultSet rs = statement1.executeQuery("select role_id from employee_job_role where emp_id='" + empId + "'");
            if (rs.next()) {
                final String roleId = rs.getString(1);
                final ResultSet rs2 = statement2.executeQuery("select prof_id from job_skill_association where jr_id='" + roleId + "'");
                if (rs2.next()) {
                    final String profileId = rs2.getString(1);
                    final ResultSet rs3 = statement3.executeQuery("select model_id from skill_profile where id='" + profileId + "'");
                    String modelId = null;
                    if (rs3.next()) {
                        modelId = rs3.getString(1);
                    }
                    final ResultSet rs4 = statement4.executeQuery("select family_id,skill_id,level_id from skill_profile_details where prof_id='" + profileId + "'");
                    while (rs4.next()) {
                        final String[] str = { rs4.getString(1), rs4.getString(2), rs4.getString(3) };
                        System.out.println("delete from employee_skill_profile where emp_id='" + empId + "' AND skill_family='" + str[0] + "' AND skill='" + str[1] + "'");
                        statement5.executeUpdate("delete from employee_skill_profile where emp_id='" + empId + "' AND skill_family='" + str[0] + "' AND skill='" + str[1] + "'");
                        System.out.println("insert into employee_skill_profile(emp_id,skill_family,skill,competency_model,competency_level) values('" + empId + "','" + str[0] + "','" + str[1] + "','" + modelId + "','" + str[2] + "')");
                        statement5.executeUpdate("insert into employee_skill_profile(emp_id,skill_family,skill,competency_model,competency_level) values('" + empId + "','" + str[0] + "','" + str[1] + "','" + modelId + "','" + str[2] + "')");
                    }
                }
            }
            statement1.close();
            statement2.close();
            statement3.close();
            statement4.close();
            statement5.close();
            oConn.commit();
            oConn.setAutoCommit(true);
            oConn.close();
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception in DataBaseLayer.updateEmployeeProfileFromJob)");
            System.out.println("Exception is:" + sqlexception.getMessage());
        }
        catch (Exception exception) {
            System.out.println("Exception in DataBaseLayer.updateEmployeeProfileFromJob()");
            System.out.println("Exception is:" + exception.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
    }
    
    public static String getUserName(final String userId) {
        String str = null;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select concat(first_name,' ',middle_name,' ',sname) from student_details where student_id='" + userId + "'");
            while (resultset.next()) {
                str = resultset.getString(1);
            }
            statement.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception in DataBaseLayer.getUserName()");
            System.out.println("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception exception) {
            System.out.println("Exception in DataBaseLayer.getUserName()");
            System.out.println("The Error Message - " + exception.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return str;
    }
    
    public static Vector<String> getSkillGap(final String empId, final String roleId) {
        final Vector<String[]> vector1 = new Vector<String[]>();
        final Vector<String> vector2 = new Vector<String>();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement1 = oConn.createStatement();
            System.out.println("select skill_family,skill,competency_model,competency_level from employee_skill_profile where emp_id='" + empId + "'");
            final ResultSet rs1 = statement1.executeQuery("select skill_family,skill,competency_model,competency_level from employee_skill_profile where emp_id='" + empId + "'");
            while (rs1.next()) {
                final String[] str = { rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4) };
                vector1.addElement(str);
            }
            final Statement statement2 = oConn.createStatement();
            System.out.println("select prof_id from job_skill_association where jr_id='" + roleId + "'");
            final ResultSet rs2 = statement2.executeQuery("select prof_id from job_skill_association where jr_id='" + roleId + "'");
            String profileId = null;
            while (rs2.next()) {
                profileId = rs2.getString(1);
            }
            final Statement statement3 = oConn.createStatement();
            System.out.println("select family_id,skill_id,level_id from skill_profile_details where prof_id='" + profileId + "'");
            final ResultSet rs3 = statement3.executeQuery("select family_id,skill_id,level_id from skill_profile_details where prof_id='" + profileId + "'");
            while (rs3.next()) {
                final String[] str2 = { rs3.getString(1), rs3.getString(2), rs3.getString(3) };
                String retstr = "~~~";
                int k = 0;
                System.out.println("Desired:" + str2[0] + "," + str2[1] + "," + str2[2]);
                if (vector1 != null) {
                    System.out.println("size of the vector=" + vector1.size());
                }
                for (int i = 0; i < vector1.size(); ++i) {
                    retstr += "";
                    String[] str3 = new String[4];
                    str3 = vector1.elementAt(i);
                    System.out.println("Current:" + str3[0] + "," + str3[1] + "," + str3[3]);
                    if (!str2[0].equals(str3[0]) || !str2[1].equals(str3[1]) || !str2[2].equals(str3[3])) {
                        if (str2[0].equals(str3[0]) && str2[1].equals(str3[1])) {
                            System.out.println("This skill exists");
                            retstr = str2[0] + "~" + str2[1] + "~" + str3[3] + "~" + str2[2];
                            i = vector1.size();
                        }
                        else {
                            System.out.println("This skill is new");
                            retstr = str2[0] + "~" + str2[1] + "~" + "~" + str2[2];
                        }
                        ++k;
                    }
                    if (str2[0].equals(str3[0]) && str2[1].equals(str3[1]) && str2[2].equals(str3[3])) {
                        break;
                    }
                }
                boolean flag = true;
                if (vector2 != null) {
                    for (int j = 0; j < vector2.size(); ++j) {
                        final String ss = vector2.elementAt(j);
                        if (ss.equals(retstr)) {
                            flag = false;
                        }
                    }
                }
                if (k < vector1.size() - 1) {
                    flag = false;
                }
                if (flag) {
                    System.out.println("Adding elements for Desired:" + str2[0] + "," + str2[1] + "," + str2[2]);
                    System.out.println("Element Added is:" + retstr);
                    vector2.addElement(retstr);
                }
            }
            statement1.close();
            statement2.close();
            statement3.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception in DataBaseLayer.getSkillGap()");
            System.out.println("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception exception) {
            DataBaseLayer.log.fatal("Exception in DataBaseLayer.getSkillGap()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
            System.out.println("Exception in DataBaseLayer.getSkillGap()");
            System.out.println("The Error Message - " + exception.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vector2;
    }
    
    public static String getFamilyTitle(final String familyId) {
        String str1 = null;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement1 = oConn.createStatement();
            System.out.println("select title from skill_family where id='" + familyId + "'");
            final ResultSet rs = statement1.executeQuery("select title from skill_family where id='" + familyId + "'");
            while (rs.next()) {
                str1 = rs.getString(1);
            }
            statement1.close();
            rs.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception in DataBaseLayer.getFamilyTitle()");
            System.out.println("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception exception) {
            System.out.println("Exception in DataBaseLayer.getFamilyTitle()");
            System.out.println("The Error Message - " + exception.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return str1;
    }
    
    public static String getSkillTitle(final String skillId) {
        String str1 = null;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement1 = oConn.createStatement();
            final ResultSet rs = statement1.executeQuery("select title from skills where id='" + skillId + "'");
            while (rs.next()) {
                str1 = rs.getString(1);
            }
            statement1.close();
            rs.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception in DataBaseLayer.getSkillTitle()");
            System.out.println("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception exception) {
            System.out.println("Exception in DataBaseLayer.getSkillTitle()");
            System.out.println("The Error Message - " + exception.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return str1;
    }
    
    public static String getLevelTitle(final String levelId) {
        String str1 = null;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement1 = oConn.createStatement();
            System.out.println("select title from competency_level where id='" + levelId + "'");
            final ResultSet rs = statement1.executeQuery("select title from competency_level where id='" + levelId + "'");
            while (rs.next()) {
                str1 = rs.getString(1);
            }
            statement1.close();
            rs.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception in DataBaseLayer.getLevelTitle()");
            System.out.println("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception exception) {
            System.out.println("Exception in DataBaseLayer.getLevelTitle()");
            System.out.println("The Error Message - " + exception.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return str1;
    }
    
    public static String getLearningEvent(final String skillFamily, final String skill, String startLevel, final String endLevel) {
        if (startLevel == null) {
            startLevel = "-";
        }
        String str1 = null;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement1 = oConn.createStatement();
            final ResultSet rs = statement1.executeQuery("select program_id from program_skill_association where skill_family='" + skillFamily + "' AND skill='" + skill + "' AND start_level='" + startLevel + "' AND end_level='" + endLevel + "'");
            while (rs.next()) {
                str1 = rs.getString(1);
            }
            statement1.close();
            rs.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception in DataBaseLayer.getLearningEvent()");
            System.out.println("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception exception) {
            System.out.println("Exception in DataBaseLayer.getLearningEvent()");
            System.out.println("The Error Message - " + exception.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return str1;
    }
    
    public static Vector<String> getApprovedProgramNames(final String strUsrId, final String stryear) {
        final Vector<String> vprogname = new Vector<String>(3, 3);
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select a.program_id,b.program_name from record_user a,program_instance_management b where a.user_id='" + strUsrId + "' and a.period_id='" + stryear + "' and a.program_id=b.program_id");
            while (oRset.next()) {
                final String strProgram_id = oRset.getString(1);
                final String strProgram_Name = oRset.getString(2);
                vprogname.addElement(strProgram_id);
                vprogname.addElement(strProgram_Name);
            }
            oStmt.close();
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception in DataBaseLayer.getApprovedProgramNames()");
            System.out.println("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Exception in DataBaseLayer.getApprovedProgramNames()");
            System.out.println("The Error Message - " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vprogname;
    }
    
    public static Vector<String> getProgramDetForReport(final String strUserID, final String strPeriod, final String strProgram) {
        final Vector<String> vperiod = new Vector<String>(3, 15);
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select a.program_id,a.program_name,d.hours,a.cost,a.credit,c.starttime,c.endtime,c.startdate,c.enddate,b.comment from program_instance_management a, record_user b,program_schedule_management c,program_type_management d where b.user_id='" + strUserID + "' and b.period_id='" + strPeriod + "' and a.program_id='" + strProgram + "' and a.program_id=b.program_id and a.program_id=c.program_id and a.program_type_id= d.program_type_id");
            while (oRset.next()) {
                final String strprogram_id = oRset.getString(1);
                final String strprogram_name = oRset.getString(2);
                final String strhour = oRset.getString(3);
                final String strcost = oRset.getString(4);
                final String strcredit = oRset.getString(5);
                final String strstarttime = oRset.getString(6);
                final String strendtime = oRset.getString(7);
                final String strstartdate = oRset.getString(8);
                final String strenddate = oRset.getString(9);
                final String strcomment = oRset.getString(10);
                vperiod.addElement(strprogram_id);
                vperiod.addElement(strprogram_name);
                vperiod.addElement(strhour);
                vperiod.addElement(strcost);
                vperiod.addElement(strcredit);
                vperiod.addElement(strstarttime);
                vperiod.addElement(strendtime);
                vperiod.addElement(strstartdate);
                vperiod.addElement(strenddate);
                vperiod.addElement(strcomment);
            }
            oStmt.close();
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception in DataBaseLayer.getProgramDetForReport()");
            System.out.println("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Exception in DataBaseLayer.getProgramDetForReport()");
            System.out.println("The Error Message - " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vperiod;
    }
    
    public static Vector<String[]> getEvaluationLevels() {
        final Vector<String[]> v = new Vector<String[]>();
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select level_id,level_name from evaluation_level");
            while (oRset.next()) {
                final String[] str = { oRset.getString(1), oRset.getString(2) };
                v.addElement(str);
            }
            oStmt.close();
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception in DataBaseLayer.getEvaluationLevels()");
            System.out.println("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Exception in DataBaseLayer.getEvaluationLevels()");
            System.out.println("The Error Message - " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return v;
    }
    
    public static synchronized Vector<String> checkPrePost(final String strUsrId, final String strPeriod, final String strProgram) {
        final Vector<String> vprogname = new Vector<String>(6, 6);
        String strpre = null;
        String strpost = null;
        String strpreattended = null;
        String strpreresults = null;
        String strpostattended = null;
        String strpostresults = null;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select b.preattended,b.preresults from program_evaluation a,record_user b where b.program_id='" + strProgram + "' AND b.user_id='" + strUsrId + "' AND a.program_id=b.program_id AND a.eval_type='pre' ");
            while (oRset.next()) {
                strpre = "T";
                strpreattended = oRset.getString(1);
                strpreresults = oRset.getString(2);
            }
            final Statement stmt2 = oConn.createStatement();
            final ResultSet oRset2 = stmt2.executeQuery("select b.postattended,b.postresults from program_evaluation a,record_user b where b.program_id='" + strProgram + "' AND  b.user_id='" + strUsrId + "' AND a.program_id=b.program_id AND a.eval_type='post' ");
            while (oRset2.next()) {
                strpost = "T";
                strpostattended = oRset2.getString(1);
                strpostresults = oRset2.getString(2);
            }
            vprogname.addElement(strpre);
            vprogname.addElement(strpost);
            vprogname.addElement(strpreattended);
            vprogname.addElement(strpreresults);
            vprogname.addElement(strpostattended);
            vprogname.addElement(strpostresults);
            oStmt.close();
            stmt2.close();
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception in DataBaseLayer.checkPrePost()");
            System.out.println("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Exception in DataBaseLayer.checkPrePost()");
            System.out.println("The Error Message - " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vprogname;
    }
    
    public static synchronized void updateRecord(final String struserid, final String strprogramid, final String strperiodid, final String strattended, final String strcompleted, final String comment, final String strAttendedPre, final String strResultsPre, final String strAttendedPost, final String strResultsPost, final String strCreatedBy) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement = oConn.createStatement();
            System.out.println("update record_user set attended = '" + strattended + "',completed = '" + strcompleted + "',comment = '" + comment + "',preattended = '" + strAttendedPre + "',preresults = '" + strResultsPre + "',postattended = '" + strAttendedPost + "',postresults = '" + strResultsPost + "',updated_by = '" + strCreatedBy + "',updated_on =sysdate() where user_id= '" + struserid + "' and program_id= '" + strprogramid + "' and period_id = '" + strperiodid + "'");
            statement.execute("update record_user set attended = '" + strattended + "',completed = '" + strcompleted + "',comment = '" + comment + "',preattended = '" + strAttendedPre + "',preresults = '" + strResultsPre + "',postattended = '" + strAttendedPost + "',postresults = '" + strResultsPost + "',updated_by = '" + strCreatedBy + "',updated_on =sysdate() where user_id= '" + struserid + "' and program_id= '" + strprogramid + "' and period_id = '" + strperiodid + "'");
            statement.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception in DataBaseLayer.updateRecord()");
            System.out.println("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Exception in DataBaseLayer.updateRecord()");
            System.out.println("The Error Message - " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public static synchronized void deleteRecord(final String strUsrId, final String strProgram, final String strPeriod, final String strCreatedBy) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement = oConn.createStatement();
            statement.execute("delete from record_user where user_id= '" + strUsrId + "' and program_id= '" + strProgram + "' and period_id = '" + strPeriod + "'");
            statement.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception in DataBaseLayer.deleteRecord()");
            System.out.println("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Exception in DataBaseLayer.deleteRecord()");
            System.out.println("The Error Message - " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public static Vector<String> getReportIntroduction(final String strUserID, final String strPeriod) {
        final Vector<String> vplanrep = new Vector();
        int totcost = 0;
        int totcredit = 0;
        int tothours = 0;
        String strPeriodName = null;
        String strUserName = null;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            Statement stmt3 = oConn.createStatement();
            ResultSet rs4 = stmt3.executeQuery("select startdate,enddate from period where period_id='" + strPeriod + "'");
            if (rs4.next()) {
                String startdate = rs4.getString(1);
                String enddate = rs4.getString(2);
                if (startdate != null) {
                    startdate = parseDate(startdate);
                }
                if (enddate != null) {
                    enddate = parseDate(enddate);
                }
                strPeriodName = startdate + "-" + enddate;
            }
            final Statement stmt2 = oConn.createStatement();
            final ResultSet rs2 = stmt2.executeQuery("select concat(first_name,' ',middle_name,' ' ,sname) from student_details where student_id='" + strUserID + "'");
            while (rs2.next()) {
                strUserName = rs2.getString(1);
            }
            stmt3 = oConn.createStatement();
            final ResultSet rs3 = stmt3.executeQuery("select program_id from record_user where user_id='" + strUserID + "' AND attended='Y'");
            while (rs3.next()) {
                final String programId = rs3.getString(1);
                final Statement stmt4 = oConn.createStatement();
                rs4 = stmt4.executeQuery("select a.cost,a.credit,b.hours from program_instance_management a,program_type_management b where a.program_type_id=b.program_type_id and a.program_id='" + programId + "'");
                if (rs4.next()) {
                    final String cost = rs4.getString(1);
                    final String credit = rs4.getString(2);
                    final String hours = rs4.getString(3);
                    int intcost = 0;
                    int intcredit = 0;
                    int inthours = 0;
                    if (cost != null) {
                        intcost = Integer.parseInt(cost);
                    }
                    if (credit != null && !credit.equals("")) {
                        intcredit = Integer.parseInt(credit);
                    }
                    if (hours != null && !hours.equals(hours)) {
                        inthours = Integer.parseInt(hours);
                    }
                    totcost += intcost;
                    totcredit += intcredit;
                    tothours += inthours;
                }
            }
            final String strtotcost = "" + totcost;
            final String strtotcredit = "" + totcredit;
            final String strtothours = "" + tothours;
            vplanrep.addElement(strUserName);
            vplanrep.addElement(strPeriodName);
            vplanrep.addElement(strtotcost);
            vplanrep.addElement(strtotcredit);
            vplanrep.addElement(strtothours);
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception in DataBaseLayer.getReportIntroduction()");
            System.out.println("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Exception in DataBaseLayer.getReportIntroduction()");
            System.out.println("The Error Msg - " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vplanrep;
    }
    
    public static Vector<String> getPlanReport(final String strUserID, final String strPeriod) {
        final Vector<String> vplanrep = new Vector<String>();
        int totcost = 0;
        int totcredit = 0;
        int tothours = 0;
        final String strPeriodName = null;
        final String sql = null;
        final String strUserName = null;
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement stmt1 = oConn.createStatement();
            ResultSet rs2 = stmt1.executeQuery("select plan_id from plan_employee_association where emp_id='" + strUserID + "' and period_id='" + strPeriod + "'");
            while (rs2.next()) {
                final String planId = rs2.getString(1);
                final Statement stmt2 = oConn.createStatement();
                rs2 = stmt2.executeQuery("select program_id from plan_program_association where plan_id='" + planId + "'");
                while (rs2.next()) {
                    final String strProg = rs2.getString(1);
                    final Statement stmt3 = oConn.createStatement();
                    final ResultSet rs3 = stmt3.executeQuery("select a.cost,a.credit,b.hours from program_instance_management a,program_type_management b where a.program_type_id=b.program_type_id and a.program_id='" + strProg + "'");
                    if (rs3.next()) {
                        final String cost = rs3.getString(1);
                        final String credit = rs3.getString(2);
                        final String hours = rs3.getString(3);
                        int intcost = 0;
                        int intcredit = 0;
                        int inthours = 0;
                        if (cost != null) {
                            intcost = Integer.parseInt(cost);
                        }
                        if (credit != null && !credit.equals("")) {
                            intcredit = Integer.parseInt(credit);
                        }
                        if (hours != null && !hours.equals(hours)) {
                            inthours = Integer.parseInt(hours);
                        }
                        totcost += intcost;
                        totcredit += intcredit;
                        tothours += inthours;
                    }
                }
                stmt2.close();
            }
            final String strtotcost = "" + totcost;
            final String strtotcredit = "" + totcredit;
            final String strtothours = "" + tothours;
            vplanrep.addElement(strtotcost);
            vplanrep.addElement(strtotcredit);
            vplanrep.addElement(strtothours);
            stmt1.close();
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception in DataBaseLayer.getReportIntroduction()");
            System.out.println("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Exception in DataBaseLayer.getReportIntroduction()");
            System.out.println("The Error Message - " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vplanrep;
    }
    
    public static String parseDate(final String s) {
        if (s != null && s.length() > 9) {
            final String s2 = s.substring(0, 4);
            final String s3 = s.substring(5, 7);
            final String s4 = s.substring(8, 10);
            final String strdate = s4 + "-" + s3 + "-" + s2;
            DataBaseLayer.log.verbose("    date = " + strdate);
            return strdate;
        }
        return "";
    }
    
    public static synchronized void addEvaluation(final String programId, final String evalType, final String prepostType, final String evalDate, final String startTime, final String endTime) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement2 = oConn.createStatement();
            if (evalType.equals("pre")) {
                statement2.execute("insert into program_evaluation(program_id,eval_type,pre_type,pre_date,pre_start_time,pre_end_time) values('" + programId + "','" + evalType + "','" + prepostType + "','" + evalDate + "','" + startTime + "','" + endTime + "')");
            }
            if (evalType.equals("post")) {
                statement2.execute("insert into program_evaluation(program_id,eval_type,post_type,post_date,post_start_time,post_end_time) values('" + programId + "','" + evalType + "','" + prepostType + "','" + evalDate + "','" + startTime + "','" + endTime + "')");
            }
            statement2.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.fatal("Exception in TrainingDatabaseLayer.addEvaluation()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception ex) {
            System.out.println("Exception in TrainingDatabaseLayer.addEvaluation()");
            System.out.println("The Error Message - " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public static synchronized void modifyEvaluation(final String programId, final String evalType, final String prepostType, final String evalDate, final String startTime, final String endTime) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement2 = oConn.createStatement();
            if (evalType.equals("pre")) {
                statement2.executeUpdate("update program_evaluation SET  pre_type='" + prepostType + "',pre_date='" + evalDate + "',pre_start_time='" + startTime + "',pre_end_time='" + endTime + "' where program_id='" + programId + "' AND eval_type='" + evalType + "'");
            }
            if (evalType.equals("post")) {
                statement2.executeUpdate("update program_evaluation SET  post_type='" + prepostType + "',post_date='" + evalDate + "',post_start_time='" + startTime + "',post_end_time='" + endTime + "' where program_id='" + programId + "' AND eval_type='" + evalType + "'");
            }
            statement2.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception in TrainingDatabaseLayer.modifyEvaluation()");
            System.out.println("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Exception in TrainingDatabaseLayer.modifyEvaluation()");
            System.out.println("The Error Message - " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public static synchronized void deleteEvaluation(final String programId, final String evalType) {
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement2 = oConn.createStatement();
            statement2.executeUpdate("delete from program_evaluation where program_id='" + programId + "' AND eval_type='" + evalType + "'");
            statement2.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception in TrainingDatabaseLayer.deleteEvaluation()");
            System.out.println("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Exception in TrainingDatabaseLayer.deleteEvaluation()");
            System.out.println("The Error Message - " + ex.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public static synchronized Vector<Vector<String>> getResourcecalendar() {
        final Vector<Vector<String>> vector = new Vector<Vector<String>>(2, 2);
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            final ResultSet oRset = oStmt.executeQuery("select resource_id,resource_name from resource_management");
            while (oRset.next()) {
                final Vector<String> as = new Vector<String>(2, 2);
                as.addElement(oRset.getString(1));
                as.addElement(oRset.getString(2));
                vector.addElement(as);
            }
            oStmt.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.fatal("Exception in TrainingDatabaseLayer.getResourceCalendar()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception ex) {
            DataBaseLayer.log.fatal("Exception in TrainingDatabaseLayer.getResourceCalendar()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vector;
    }
    
    public static synchronized void addresource(final String catid, final String resid, final String resname) {
        Connection oConn = null;
        try {
            DataBaseLayer.log.entry("addResource()");
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement1 = oConn.createStatement();
            DataBaseLayer.log.verbose("insert into resource_management values('" + catid + "','" + resid + "','" + resname + "')");
            statement1.execute("insert into resource_management(category_id,resource_id,resource_name) values('" + catid + "','" + resid + "','" + resname + "')");
            statement1.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.fatal("Exception in TrainingDatabaseLayer.addResources()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception ex) {
            DataBaseLayer.log.fatal("Exception in TrainingDatabaseLayer.addResources()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public static synchronized void modifyresource(final String catid, final String resid, final String resname) {
        Connection oConn = null;
        try {
            DataBaseLayer.log.entry("modifyResource()");
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement1 = oConn.createStatement();
            DataBaseLayer.log.verbose("update resource_management SET resource_name='" + resname + "' where category_id='" + catid + "' and resource_id='" + resid + "'");
            statement1.execute("update resource_management SET resource_name='" + resname + "' where category_id='" + catid + "' and resource_id='" + resid + "'");
            statement1.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.fatal("Exception in TrainingDatabaseLayer.modifyResource()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception ex) {
            DataBaseLayer.log.fatal("Exception in TrainingDatabaseLayer.modifyResourse()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public static synchronized void deleteresource(final String strCat, final String resid) {
        Connection oConn = null;
        try {
            DataBaseLayer.log.entry("deleteresource()");
            oConn = DataBaseLayer.ds1.getConnection();
            oConn.setAutoCommit(false);
            final Statement statement1 = oConn.createStatement();
            DataBaseLayer.log.verbose("delete from resource_management where category_id='" + strCat + "' and resource_id='" + resid + "'");
            statement1.execute("delete from resource_management where category_id='" + strCat + "' and resource_id='" + resid + "'");
            statement1.close();
            oConn.commit();
            oConn.setAutoCommit(true);
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.fatal("Exception in TrainingDatabaseLayer.deleteResource()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception ex) {
            DataBaseLayer.log.fatal("Exception in TrainingDatabaseLayer.deleteResource()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
    }
    
    public static synchronized Vector<String[]> publicSharedCalendarName(final String s_id, final String strtype) {
        final Vector<String[]> vPublicEvent = new Vector<String[]>(5, 5);
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement1 = oConn.createStatement();
            final ResultSet oRset = statement1.executeQuery("select calendar_id  from calendar_authorization where student_id ='" + s_id + "'");
            while (oRset.next()) {
                final Statement statement2 = oConn.createStatement();
                final int c_id = oRset.getInt(1);
                final ResultSet resultset = statement2.executeQuery("select calendar_id , calendar_name from calendar_keywords where calendar_id =" + c_id);
                while (resultset.next()) {
                    final String[] strcal = new String[2];
                    final int cal_id = resultset.getInt(1);
                    strcal[0] = new Integer(cal_id).toString();
                    strcal[1] = resultset.getString(2);
                    vPublicEvent.addElement(strcal);
                }
                statement2.close();
            }
            oRset.close();
            statement1.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.fatal("Exception in DataBaseLayer.publicSharedCalendarName()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.fatal("Exception in DataBaseLayer.publicSharedCalendarName()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vPublicEvent;
    }
    
    public static synchronized Vector<String[]> publicPersonalCalendarName(final String s_id, final String strtype) {
        final Vector<String[]> vPublicEvent = new Vector<String[]>(5, 5);
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            final ResultSet resultset = statement.executeQuery("select calendar_id , calendar_name from calendar_keywords where createdby ='" + s_id + "' and  cal_type= '" + strtype + "'");
            while (resultset.next()) {
                final String[] strcal = new String[2];
                final int cal_id = resultset.getInt(1);
                strcal[0] = new Integer(cal_id).toString();
                strcal[1] = resultset.getString(2);
                vPublicEvent.addElement(strcal);
            }
            statement.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            DataBaseLayer.log.fatal("Exception in DataBaseLayer.publicPersonalCalendarName()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)sqlexception);
        }
        catch (Exception exception) {
            DataBaseLayer.log.fatal("Exception in DataBaseLayer.publicPersonalCalendarName()");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)exception);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return vPublicEvent;
    }
    
    public static synchronized Vector getCalendardate(final String str, final String strProgramID) {
        String str2 = "";
        String str3 = "";
        String flag = "";
        final Vector vREs = new Vector();
        if (!str.equals("")) {
            final String str4 = str.substring(0, 4);
            final String str5 = str.substring(4);
            final String str6 = str5.substring(0, 2);
            final String str7 = str5.substring(2);
            int ints3 = Integer.parseInt(str6);
            ++ints3;
            str2 = str4 + "-" + "0" + ints3 + "-" + str7;
            str3 = str4 + "0" + ints3 + str7;
        }
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            DataBaseLayer.log.verbose("select a.startdate, a.enddate, b.resource_id from program_schedule_management a,program_resource_management b,program_resource_management c where b.program_id='" + strProgramID + "' and b.resource_id=c.resource_id  and a.program_id=c.program_id and a.startdate='" + str2 + "'");
            final ResultSet oRset = oStmt.executeQuery("select a.startdate, a.enddate, b.resource_id from program_schedule_management a,program_resource_management b,program_resource_management c where b.program_id='" + strProgramID + "' and b.resource_id=c.resource_id and a.program_id=c.program_id and a.startdate='" + str2 + "'");
            while (oRset.next()) {
                final String strRes = oRset.getString(3);
                DataBaseLayer.log.verbose("strRes==strRes===" + strRes);
                flag = strRes;
                vREs.addElement(strRes);
            }
            DataBaseLayer.log.verbose("****flag*******" + flag);
            if (flag.equals("")) {
                final Statement oStmt2 = oConn.createStatement();
                final ResultSet oRset2 = oStmt2.executeQuery("select a.startdate, a.enddate,b.resource_id from program_schedule_management a,program_resource_management b,program_resource_management c where a.program_id=c.program_id and b.resource_id=c.resource_id and b.program_id='" + strProgramID + "'");
                while (oRset2.next()) {
                    String s1 = oRset2.getString(1);
                    String s2 = oRset2.getString(2);
                    final String strRes2 = oRset2.getString(3);
                    DataBaseLayer.log.verbose("strRes==" + strRes2);
                    if (!s1.equals("") && !s2.equals("")) {
                        final String str8 = s1.substring(0, s1.indexOf(45));
                        final String str9 = s1.substring(s1.indexOf(45) + 1);
                        final String str10 = str9.substring(0, str9.indexOf(45));
                        final String str11 = str9.substring(str9.indexOf(45) + 1);
                        s1 = str8 + str10 + str11;
                        DataBaseLayer.log.verbose("******s1********" + s1);
                        final int s3 = Integer.parseInt(s1);
                        final String str12 = s2.substring(0, s2.indexOf(45));
                        final String str13 = s2.substring(s2.indexOf(45) + 1);
                        final String str14 = str13.substring(0, str13.indexOf(45));
                        final String str15 = str13.substring(str13.indexOf(45) + 1);
                        s2 = str12 + str14 + str15;
                        DataBaseLayer.log.verbose("******s2********" + s2);
                        final int s4 = Integer.parseInt(s2);
                        if (str3.equals("")) {
                            continue;
                        }
                        final int str16 = Integer.parseInt(str3);
                        if (str16 <= s3 || str16 > s4) {
                            continue;
                        }
                        vREs.addElement(strRes2);
                    }
                }
                oRset2.close();
                oStmt2.close();
            }
            oRset.close();
            oStmt.close();
            oConn.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.fatal("getCalendarDetailsList: error due to SQL exception");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        catch (Exception ex) {
            DataBaseLayer.log.fatal("getCalendarDetailsList: error due to SQL exception");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vREs;
    }
    
    public static synchronized Vector getCalendardateRes(final String str, final String[] vres) {
        DataBaseLayer.log.verbose("Resource Name00===" + vres[0]);
        String str2 = "";
        String str3 = "";
        String flag = "";
        final Vector vREs = new Vector();
        if (!str.equals("")) {
            final String str4 = str.substring(0, 4);
            final String str5 = str.substring(4);
            final String str6 = str5.substring(0, 2);
            final String str7 = str5.substring(2);
            int ints3 = Integer.parseInt(str6);
            ++ints3;
            str2 = str4 + "-" + "0" + ints3 + "-" + str7;
            str3 = str4 + "0" + ints3 + str7;
        }
        Connection oConn = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement oStmt = oConn.createStatement();
            DataBaseLayer.log.verbose("select a.startdate, a.enddate, b.resource_id from program_schedule_management a,program_resource_management b,program_resource_management c where b.resource_id=c.resource_id  and a.program_id=c.program_id and a.startdate='" + str2 + "'");
            for (int j = 0; j < vres.length; ++j) {
                final ResultSet oRset = oStmt.executeQuery("select a.startdate, a.enddate, b.resource_id from program_schedule_management a,program_resource_management b where a.program_id=b.program_id and a.startdate='" + str2 + "' and b.resource_id='" + vres[j] + "'");
                while (oRset.next()) {
                    final String strRes = oRset.getString(3);
                    DataBaseLayer.log.verbose("strRes==strRes===" + strRes);
                    flag = strRes;
                    vREs.addElement(strRes);
                }
            }
            DataBaseLayer.log.verbose("****flag*******" + flag);
            if (flag.equals("")) {
                for (int k = 0; k < vres.length; ++k) {
                    final Statement oStmt2 = oConn.createStatement();
                    DataBaseLayer.log.verbose("select a.startdate, a.enddate,b.resource_id from program_schedule_management a,program_resource_management b where a.program_id=b.program_id and b.resource_id='" + vres[k] + "'");
                    final ResultSet oRset2 = oStmt2.executeQuery("select a.startdate, a.enddate,b.resource_id from program_schedule_management a,program_resource_management b where a.program_id=b.program_id and b.resource_id='" + vres[k] + "'");
                    while (oRset2.next()) {
                        String s1 = oRset2.getString(1);
                        String s2 = oRset2.getString(2);
                        final String strRes2 = oRset2.getString(3);
                        DataBaseLayer.log.verbose("strRes==" + strRes2);
                        if (!s1.equals("") && !s2.equals("")) {
                            final String str8 = s1.substring(0, s1.indexOf(45));
                            final String str9 = s1.substring(s1.indexOf(45) + 1);
                            final String str10 = str9.substring(0, str9.indexOf(45));
                            final String str11 = str9.substring(str9.indexOf(45) + 1);
                            s1 = str8 + str10 + str11;
                            DataBaseLayer.log.verbose("******s1********" + s1);
                            final int s3 = Integer.parseInt(s1);
                            final String str12 = s2.substring(0, s2.indexOf(45));
                            final String str13 = s2.substring(s2.indexOf(45) + 1);
                            final String str14 = str13.substring(0, str13.indexOf(45));
                            final String str15 = str13.substring(str13.indexOf(45) + 1);
                            s2 = str12 + str14 + str15;
                            DataBaseLayer.log.verbose("******s2********" + s2);
                            final int s4 = Integer.parseInt(s2);
                            if (str3.equals("")) {
                                continue;
                            }
                            final int str16 = Integer.parseInt(str3);
                            if (str16 <= s3 || str16 > s4) {
                                continue;
                            }
                            vREs.addElement(strRes2);
                        }
                    }
                    oRset2.close();
                    oStmt2.close();
                }
            }
            oStmt.close();
            oConn.close();
        }
        catch (SQLException e) {
            DataBaseLayer.log.fatal("getCalendarDetailsList: error due to SQL exception");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
        }
        catch (Exception ex) {
            DataBaseLayer.log.fatal("getCalendarDetailsList: error due to SQL exception");
            DataBaseLayer.log.dbe(DebugLevel.L1_FATAL, (Throwable)ex);
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex2) {}
            }
        }
        return vREs;
    }
    
    public static String[] getEvaluation(final String program_id, final String eval_type) {
        final String[] str = new String[5];
        Connection oConn = null;
        ResultSet resultset = null;
        try {
            oConn = DataBaseLayer.ds1.getConnection();
            final Statement statement = oConn.createStatement();
            if (eval_type.equals("pre")) {
                resultset = statement.executeQuery("SELECT pre_type,date_format(pre_date,'%d/%m/%Y'),pre_start_time,pre_end_time FROM program_evaluation where program_id='" + program_id + "' and eval_type='" + eval_type + "'");
            }
            if (eval_type.equals("post")) {
                resultset = statement.executeQuery("SELECT post_type,date_format(post_date,'%d/%m/%Y'),post_start_time,post_end_time FROM program_evaluation where program_id='" + program_id + "' and eval_type='" + eval_type + "'");
            }
            while (resultset.next()) {
                str[0] = eval_type;
                str[1] = resultset.getString(1);
                str[2] = resultset.getString(2);
                str[3] = resultset.getString(3);
                str[4] = resultset.getString(4);
            }
            statement.close();
            oConn.close();
        }
        catch (SQLException sqlexception) {
            System.out.println("Exception in DataBaseLayer.getEvaluation()");
            System.out.println("The Error Message - " + sqlexception.getMessage());
        }
        catch (Exception exception) {
            System.out.println("Exception in DataBaseLayer.getEvaluation()");
            System.out.println("The Error Message - " + exception.getMessage());
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                }
                catch (SQLException ex) {}
            }
        }
        return str;
    }
    
    static {
        log = new SimpleLogger((Class)DataBaseLayer.class, true);
        DataBaseLayer.ds1 = CoreAdminInitHostInfo.ds1;
    }
}
