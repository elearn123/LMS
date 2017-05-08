package learnityinterfaceportal;

import org.w3c.dom.*;
import java.sql.*;
import java.io.*;
import java.util.Vector;
import org.grlea.log.*;
import oracle.xml.parser.v2.*;
import javax.sql.DataSource;
import comv2.aunwesha.param.*;

/**
 * Title: Description: Copyright: Copyright (c) 2007 Company: Aunwesha
 * 
 * @author Shibaji Chatterjee
 * @version
 */

public class DataBaseLayer {

	public static DataSource ds = CoreAdminInitHostInfo.ds;
	public static DataSource ds1 = CoreAdminInitHostInfo.ds1;

	public static final SimpleLogger log = new SimpleLogger(DataBaseLayer.class, true);// Create
																						// a
																						// SimpleLogger:

	public static int validate(String s, String s1) throws SQLException {
		Object obj = null;
		Object obj1 = null;
		byte byte0 = 0;
		Connection oConn = ds1.getConnection();
		Statement statement = null;
		ResultSet resultset = null;
		try {
			// checkConnection();

			statement = oConn.createStatement();
			resultset = statement.executeQuery("select count(*) from student_password where student_id='" + s + "'");
			resultset.next();
			if (resultset.getInt(1) > 0) {
				Statement statement1 = oConn.createStatement();
				ResultSet resultset1 = statement1
						.executeQuery("select password from student_password where student_id ='" + s + "'");
				resultset1.next();
				String s2 = resultset1.getString(1);
				if (s2.equals(s1))
					byte0 = 1;
				else
					byte0 = -2;
				resultset1.close();
				statement1.close();
			} else {
				byte0 = -1;
			}
			// resultset.close();
			// statement.close();
			// try{oConn.close();}catch(SQLException e){}
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			System.out.println(" error due to SQL exception inside DataBaseLayer.validate()");
			System.out.println("Error due to SQLException , the error code - " + sqlexception.getErrorCode());
			System.out.println("Error message - " + sqlexception.getMessage());
		} catch (Exception exception) {
			System.out.println(" error due to java.lang.exception");
			exception.printStackTrace();
			System.out.println(" printStack is :: " + exception.getMessage());
		} finally {
			if (oConn != null) {
				try {
					resultset.close();
					statement.close();
					oConn.close();

				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return byte0;
	}

	public static void insertStudentLogin(String s, String s1, String s2, String s3) {
		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Connection oConn = null;
		Statement statement = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			boolean flag = statement
					.execute("Insert into student_login_time(session_id, student_id, logged_in_at) values ('" + s
							+ "','" + s1 + "','" + s3 + " " + s2 + "')");
			System.out.println("Insert into student_login_time(session_id, student_id, logged_in_at) values ('" + s
					+ "','" + s1 + "','" + s3 + " " + s2 + "')");
			flag = statement.execute("Insert into user_login_time(session_id, student_id, logged_in_at) values ('" + s
					+ "','" + s1 + "','" + s3 + " " + s2 + "')");
			System.out.println("Insert into user_login_time(session_id, student_id, logged_in_at) values ('" + s + "','"
					+ s1 + "','" + s3 + " " + s2 + "')");
			// statement.close();

		} catch (SQLException sqlexception) {
			System.out.println("...............................................>>>>>>>>>>>>>>>>>>>>>................"
					+ sqlexception.getMessage());
			System.out.println("The Error Message - " + sqlexception.getMessage());
			System.out.println("The Error Code    - " + sqlexception.getErrorCode());
			while ((sqlexception = sqlexception.getNextException()) != null) {
				System.out.println("The Error message - " + sqlexception.getMessage());
				System.out.println("The Error code is - " + sqlexception.getErrorCode());
			}
		} catch (Exception exception) {
			System.out.println("General exception in DataBaseLayer.insertStudenLogin()");
			exception.printStackTrace();
			System.out.println("The message - " + exception.toString());
		} finally {

			if (oConn != null) {
				try {
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
	}

	public static String getLoginWelcome3(String s) {
		String loginno = null;

		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			// checkConnection();
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			resultset = statement.executeQuery("select count(*) from student_login_time where student_id ='" + s + "'");
			resultset.next();
			loginno = resultset.getString(1);
			// resultset.close();
			// statement.close();
		} catch (SQLException sqlexception) {
			System.out.println("Inside DataBaseLayer getPluginName(), SQLException !!!!");
			System.out
					.println("Inside DataBaseLayer getPluginName(), the error message - " + sqlexception.getMessage());
			System.out.println(
					"Inside DataBaseLayer getPluginName(), the error message - " + sqlexception.getErrorCode());
		} catch (Exception exception) {
			System.out.println("Inside DataBaseLayer getPluginName(), Exception !!!!");
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					resultset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return loginno;
	}

	public static String getLoginWelcome4(String s) {
		String createdate = null;

		/*
		 * DBConnectionManager connMgr = DBConnectionManager.getInstance();
		 * Connection oConn = connMgr.getConnection("mysql");
		 */
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			// checkConnection();

			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			resultset = statement.executeQuery(
					"select date_format(( date_student_created),\"%d-%m-%Y %r\") from  student_creation_details where student_id ='"
							+ s + "'");
			resultset.next();
			createdate = resultset.getString(1);
			// resultset.close();
			// statement.close();
		} catch (SQLException sqlexception) {
			System.out.println("Inside DataBaseLayer getPluginName(), SQLException !!!!");
			System.out
					.println("Inside DataBaseLayer getPluginName(), the error message - " + sqlexception.getMessage());
			System.out.println(
					"Inside DataBaseLayer getPluginName(), the error message - " + sqlexception.getErrorCode());
		} catch (Exception exception) {
			System.out.println("Inside DataBaseLayer getPluginName(), Exception !!!!");
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					resultset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return createdate;
	}

	public static String systemaccess(String s) {
		int systemaccess1 = 0;
		String accesstime = "";
		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			// checkConnection();

			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			resultset = statement.executeQuery(
					"select sum((time_to_sec(logged_out_at)-time_to_sec(logged_in_at))) from student_login_time where student_id='"
							+ s + "' group by student_id");
			while (resultset.next()) {
				systemaccess1 = resultset.getInt(1);
			}
			// System.out.println("@@@@@@@@@@@@@@@@@@@systemaccess1========"+systemaccess1);
			int hr = systemaccess1 / 3600;
			int secondleft = systemaccess1 % 3600;
			int min = secondleft / 60;
			int scnd = secondleft % 60;

			String hour = Integer.toString(hr);
			String minute = Integer.toString(min);
			String second = Integer.toString(scnd);

			accesstime = hour + ":" + minute + ":" + second;
			// System.out.println("=================accesstime==========="+accesstime);

		} catch (SQLException sqlexception) {
			System.out.println("Inside DataBaseLayer getPluginName(), SQLException !!!!");
			System.out
					.println("Inside DataBaseLayer getPluginName(), the error message - " + sqlexception.getMessage());
			System.out.println(
					"Inside DataBaseLayer getPluginName(), the error message - " + sqlexception.getErrorCode());
		} catch (Exception exception) {
			System.out.println("Inside DataBaseLayer getPluginName(), Exception !!!!");
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					resultset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return accesstime;
	}

	public static String getAccountStatus(String s) {
		String s1 = "";
		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			resultset = statement
					.executeQuery("select account_status from student_details where student_id='" + s + "'");
			while (resultset.next()) {
				s1 = resultset.getString(1);
			}
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					resultset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return s1;
	}

	public static String getLoginWelcome(String s) {
		String s1 = null;
		Object obj = null;
		String s3 = null;
		Object obj1 = null;
		// ***************************SHIBAJI
		// CHATTERJEE**********************************//

		String baseName = "";
		int flag = 0;
		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Connection oConn = null;
		Statement statement1 = null;
		ResultSet resultset1 = null;
		try {
			oConn = ds1.getConnection();
			statement1 = oConn.createStatement();
			resultset1 = statement1.executeQuery(
					"select account_status , concat(first_name,' ',middle_name,' ',sname) from student_details where student_id ='"
							+ s + "'");
			if (resultset1.next()) {

				String s4 = resultset1.getString(1);
				String s2 = resultset1.getString(2);
				if (s4.equalsIgnoreCase("active")) {
					Statement statement = oConn.createStatement();
					ResultSet resultset = statement
							.executeQuery("select count(*) from student_login_time where student_id ='" + s + "'");
					resultset.next();
					int i = resultset.getInt(1);
					resultset.close();
					if (i > 0) {
						// resultset = statement.executeQuery("select
						// date_format(max(logged_in_at),\"%d-%m-%Y %r\") from
						// student_login_time where student_id ='" + s + "'");
						resultset = statement.executeQuery(
								"select date_format(max(logged_in_at),\"%d-%m-%Y %r\") from student_login_time where date_format(logged_in_at,\"%d-%m-%Y %r\") < (select date_format(max(logged_in_at),\"%d-%m-%Y %r\") from student_login_time where student_id ='"
										+ s + "') and student_id ='" + s + "' ");
						resultset.next();
						s3 = resultset.getString(1);
					}
					resultset.close();
					statement.close();

					if (i == 0)
						s1 = "Welcome " + s2;

					if (i > 0)
						s1 = "Welcome Back " + s2 + ". " + "Last login " + s3;
				} else {
					s1 = s2 + "! Your account has been deactivated. Please contact your LearnITy administrator.";
				}
			}
		} catch (NumberFormatException numberformatexception) {
			System.out.println("NumberFormatException excepiton");
		} catch (SQLException sqlexception) {
			System.out.println("SQL error inside DataBaseLayer.getLoginWelcome()");
			System.out.println("sql exception" + sqlexception.toString());
		} catch (Exception exception) {
			System.out.println("general exception" + exception.toString());
		} finally {
			if (oConn != null) {
				try {
					resultset1.close();
					statement1.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}

		// connMgr.freeConnection("mysql", oConn);
		return s1;
	}

	public static Vector getLoginInfo(String s) {
		Vector vector = new Vector(10, 10);
		Object obj = null;
		String s11 = null;
		String s12 = null;
		String s13 = null;
		String s14 = null;
		String s15 = null;
		String s16 = null;
		String s17 = null;
		Object obj1 = null;
		Object obj2 = null;
		Object obj3 = null;
		Object obj4 = null;
		int i = 0;

		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {

			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			// ResultSet resultset;
			for (resultset = statement.executeQuery(
					"select sc.unit_id , sc.total_access_time, sc.access_allowed_till,sc.date_registration from unit_student_association sc , unit_details c where c.unit_id=sc.unit_id and  sc.student_id = '"
							+ s + "'"); resultset.next();) {
				String s1 = resultset.getString(1);// course_id
				// System.out.println("course_id ="+s1);
				String s7 = resultset.getString(2);// total_access_time
				String s5 = resultset.getString(3);// access_allowed_till
				String sregister = resultset.getString(4);// date register//
				Statement statement1 = oConn.createStatement();
				/// System.out.println("select course_name from course where
				/// course_id = '" + s1 + "'");
				ResultSet resultset2 = statement1
						.executeQuery("select unit_name from unit_details where unit_id = '" + s1 + "'");
				String s3 = "";
				while (resultset2.next()) {
					s3 = resultset2.getString(1);// course_name
				}
				resultset2 = statement1.executeQuery(
						"select coalesce(sum(if(logout_datetime>=login_datetime,(to_days(logout_datetime)-to_days(login_datetime))*86400,0) + if(time_to_sec(logout_datetime)>= time_to_sec(login_datetime),time_to_sec(logout_datetime)-time_to_sec(login_datetime),(86400+time_to_sec(logout_datetime))-time_to_sec(login_datetime)))/3600,0) from learner_login_info where student_id = '"
								+ s + "' and unit_id = '" + s1 + "'");
				resultset2.next();
				String s9 = resultset2.getString(1); // time
				if (!s9.equals("0")) {
					resultset2 = statement1.executeQuery(
							"select min(login_datetime) , max(login_datetime) from learner_login_info where student_id = '"
									+ s + "' and unit_id = '" + s1 + "'");
					if (resultset2.next()) {
						s11 = resultset2.getString(1); // min(login_datetime)
						s12 = resultset2.getString(2);// max(login_datetime)
						System.out.println("s12 =====1====" + s12);
					}
					// resultset2 = statement1.executeQuery("select topic_id
					// ,topicname, autype, auname from learner_login_info where
					// login_datetime = '" + s12 + "' and course_id = '" + s1 +
					// "' and student_id = '" + s + "' and topic_id is not
					// null");
					resultset2 = statement1
							.executeQuery("select topic_id  from learner_login_info where login_datetime = '" + s12
									+ "' and unit_id = '" + s1 + "' and student_id = '" + s
									+ "' and topic_id is not null");
					if (resultset2.next())
						s13 = resultset2.getString(1);// topic_id

				}
				// resultset2 = statement1.executeQuery("select if(coalesce('" +
				// s5 + "','9999-12-31 23:59:59') < sysdate(),'Date
				// Over','Currently Available')");
				resultset2 = statement1.executeQuery(
						"select TO_DAYS('" + s5 + "') - TO_DAYS(sysdate()) from unit_student_association");
				resultset2.next();
				int dayleft = resultset2.getInt(1);
				String s20 = null;
				if (dayleft >= 0)
					s20 = "Currently Available";
				else
					s20 = "Date Over"; // 'Date Over','Currently Available')
				// System.out.println("============== date
				// ================"+s20);
				String s22;
				if (s9 != null && s7 != null) {
					if ((new Float(s9)).floatValue() >= (new Float(s7)).floatValue())
						s22 = "Time Over";
					else
						s22 = "Currently Available";
				} else {
					s22 = "Currently Available";
				}
				resultset2 = statement1.executeQuery(
						"select if(status='Active','Currently Available','Course Made Inactive') from unit_creation_details where unit_id = '"
								+ s1 + "'");
				String s24 = "";
				// System.out.println("select if(status='Active','Currently
				// Available','Course Made Inactive') from
				// course_creation_details where course_id = '" + s1 + "'");
				while (resultset2.next()) {
					s24 = resultset2.getString(1);// 'Currently
													// Available','Course Made
													// Inactive'
				}
				String s18 = "Currently Available";
				String s26 = "Active";
				if (s20.equalsIgnoreCase("Date Over")) {
					s18 = "Date Over";
					s26 = "Inactive";
				}
				if (s22.equalsIgnoreCase("Time Over")) {
					s18 = "Time Over";
					s26 = "Inactive";
				}
				if (s24.equalsIgnoreCase("Course Made Inactive")) {
					s18 = "Course Made Inactive";
					s26 = "Inactive";
				}

				if (s5 == null) {
					s5 = "Not Applicable";
				} else {
					resultset2 = statement1.executeQuery("select date_format('" + s5 + "',\"%D %M %Y %r\")");
					resultset2.next();
					s5 = resultset2.getString(1);
				}

				if (sregister == null) {
					sregister = "Not Applicable";
				} else {
					resultset2 = statement1.executeQuery("select date_format('" + sregister + "',\"%D %M %Y %r\")");
					resultset2.next();
					sregister = resultset2.getString(1);
				}

				if (s12 == null) {
					s12 = "Not Accessed Yet!";
				} else {
					resultset2 = statement1.executeQuery("select date_format('" + s12 + "',\"%D %M %Y %r\")");
					resultset2.next();
					s12 = resultset2.getString(1);
					System.out.println("s12 =====2====" + s12);
				}
				resultset2.close();
				statement1.close();
				vector.addElement(s1);// course_id
				vector.addElement(s3);// course_name
				vector.addElement(s5);// access_allowed_till
				vector.addElement(sregister);// date register
				vector.addElement(s12);// max(login_datetime)
				System.out.println("s12------------3----------- " + s12);
				vector.addElement(s13);// topic_id

				vector.addElement(s7);// total_access_time
				vector.addElement(s9);//// time
				vector.addElement(s18);// date over
				vector.addElement(s26);// active or inactive
				System.out.println("vector 1" + vector);
				s3 = null;
				s5 = null;
				s11 = null;
				s12 = null;
				s13 = null;
				s14 = null;
				s15 = null;
				s16 = null;
				s7 = null;
				s9 = null;
				s18 = null;
				s26 = null;
				if (i == 0)
					s17 = "'" + s1 + "'";
				else
					s17 = s17 + ",'" + s1 + "'";
				i++;
			}

			if (s17 == null) {
				s17 = "'null'";
			}

			// System.out.println("Hello1 ="+s17);
			ResultSet resultset1;
			for (resultset1 = statement.executeQuery(
					"select a.unit_id , a.total_access_time, a.access_allowed_till,a.date_registration from unit_group_association a ,student_group_association b , student_group c where b.student_id = '"
							+ s + "' and a.group_id = b.group_id  and a.group_id = c.group_id and a.unit_id not in ("
							+ s17 + ") group by a.unit_id"); resultset1.next();) {
				String s2 = resultset1.getString(1); // a.course_id

				String s8 = resultset1.getString(2); // a.total_access_time
				String s6 = resultset1.getString(3); // a.access_allowed_till
				String groupregister = resultset1.getString(4);// date register

				Statement statement2 = oConn.createStatement();
				ResultSet resultset3 = statement2
						.executeQuery("select unit_name from unit_details where unit_id = '" + s2 + "'");
				resultset3.next();
				String s4 = resultset3.getString(1);// course_name
				resultset3 = statement2.executeQuery(
						"select coalesce(sum(if(logout_datetime>=login_datetime,(to_days(logout_datetime)-to_days(login_datetime))*86400,0) + if(time_to_sec(logout_datetime)>= time_to_sec(login_datetime),time_to_sec(logout_datetime)-time_to_sec(login_datetime),(86400+time_to_sec(logout_datetime))-time_to_sec(login_datetime)))/3600,0) from learner_login_info where student_id = '"
								+ s + "' and unit_id = '" + s2 + "'");
				resultset3.next();
				String s10 = resultset3.getString(1); // total time
				if (!s10.equals("0")) {
					resultset3 = statement2.executeQuery(
							"select min(login_datetime) , max(login_datetime) from learner_login_info where student_id = '"
									+ s + "' and unit_id = '" + s2 + "'");
					if (resultset3.next()) {
						s11 = resultset3.getString(1);
						s12 = resultset3.getString(2);
						System.out.println("s12 =====4====" + s12);
					}
					resultset3 = statement2
							.executeQuery("select topic_id  from learner_login_info where login_datetime = '" + s12
									+ "' and unit_id = '" + s2 + "' and student_id = '" + s
									+ "' and topic_id is not null");
					if (resultset3.next())
						s13 = resultset3.getString(1);// topic_id ,topicname,
														// autype, auname

				}
				resultset3 = statement2.executeQuery("select if(coalesce('" + s6
						+ "','9999-12-31 23:59:59') <= sysdate(),'Date Over','Currently Available')");
				resultset3.next();
				String s21 = resultset3.getString(1);
				String s23;
				if (s10 != null && s8 != null) {
					if ((new Float(s10)).floatValue() >= (new Float(s8)).floatValue())
						s23 = "Time Over";
					else
						s23 = "Currently Available";
				} else {
					s23 = "Currently Available";
				}
				resultset3 = statement2.executeQuery(
						"select if(status='Active','Currently Available','Course Made Inactive') from unit_creation_details where unit_id = '"
								+ s2 + "'");
				resultset3.next();
				String s25 = resultset3.getString(1);
				String s19 = "Currently Available";
				String s27 = "Active";
				if (s21.equalsIgnoreCase("Date Over")) {
					s19 = "Date Over";
					s27 = "Inactive";
				}
				if (s23.equalsIgnoreCase("Time Over")) {
					s19 = "Time Over";
					s27 = "Inactive";
				}
				if (s25.equalsIgnoreCase("Course Made Inactive")) {
					s19 = "Course Made Inactive";
					s27 = "Inactive";
				}
				if (s8 == null)
					s8 = "Not Applicable";
				if (s6 == null) {
					s6 = "Not Applicable";
				} else {
					resultset3 = statement2.executeQuery("select date_format('" + s6 + "',\"%D %M %Y %r\")");
					resultset3.next();
					s6 = resultset3.getString(1);
				}

				if (groupregister == null) {
					groupregister = "Not Applicable";
				} else {
					resultset3 = statement2.executeQuery("select date_format('" + groupregister + "',\"%D %M %Y %r\")");
					resultset3.next();
					groupregister = resultset3.getString(1);
				}

				if (s11 == null) {
					s11 = "Not Accessed Yet!";
				} else {
					resultset3 = statement2.executeQuery("select date_format('" + s11 + "',\"%D %M %Y %r\")");
					resultset3.next();
					s11 = resultset3.getString(1);
				}
				if (s12 == null) {
					s12 = "Not Accessed Yet!";
				} else {
					resultset3 = statement2.executeQuery("select date_format('" + s12 + "',\"%D %M %Y %r\")");
					resultset3.next();
					s12 = resultset3.getString(1);
					System.out.println("s12 =====5====" + s12);
				}
				resultset3.close();
				statement2.close();

				vector.addElement(s2);// a.course_id ,
				vector.addElement(s4);// course_name
				vector.addElement(s6);// a.access_allowed_till
				vector.addElement(groupregister);// dateregister
				vector.addElement(s12);// max(login_datetime)
				System.out.println("s12==============6==== " + s12);

				vector.addElement(s13);// topic_id

				vector.addElement(s8);// a.total_access_time,
				vector.addElement(s10);// total time
				vector.addElement(s19);// Currently Available";
				vector.addElement(s27);// "Active";
				System.out.println("vector 2==" + vector);
				s4 = null;
				s6 = null;
				s11 = null;
				s12 = null;
				s13 = null;
				s14 = null;
				s15 = null;
				s16 = null;
				s8 = null;
				s10 = null;
				s19 = null;
				s27 = null;

			}

			resultset1.close();

			// statement.close();
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			System.out.println("SQL Error inside DataBaseLayer.getLoginInfo()");
			System.out.println("The Error message is" + sqlexception.getMessage());
			System.out.println("The Error code is" + sqlexception.getErrorCode());
			while ((sqlexception = sqlexception.getNextException()) != null) {
				System.out.println("The Error message - " + sqlexception.getMessage());
				System.out.println("The Error code is - " + sqlexception.getErrorCode());
			}
		} catch (Exception exception) {
			System.out.println("Inside DataBaseLayer, getLoginInfo() Exception!!!!");
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					resultset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		// System.out.println("1111111111777777777777777="+vector.size());
		return vector;
	}

	public static XMLDocument parse(String s, String s1) {
		Object obj = null;
		XMLDocument xmldocument = null;
		/*
		 * DBConnectionManager connMgr = DBConnectionManager.getInstance();
		 * Connection oConn = connMgr.getConnection("mysql");
		 */
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			// checkConnection();

			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			String s2 = s1.toLowerCase();
			resultset = statement.executeQuery("select csf from " + s2 + " where unit_id='" + s + "'");
			if (resultset.next()) {
				java.io.InputStream inputstream = resultset.getAsciiStream(1);
				BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
				DOMParser domparser = new DOMParser();
				domparser.setValidationMode(false);
				domparser.parse(bufferedreader);
				xmldocument = domparser.getDocument();

				bufferedreader.close();
			}
		} catch (SQLException sqlexception) {
			System.out.println(" error due to SQL exception inside DataBaseLayer.XMLDocument parse()");
			int i = sqlexception.getErrorCode();
			String s3 = sqlexception.getMessage();
		} catch (Exception exception) {
			System.out.println(" error due to java.lang.exception");
			exception.printStackTrace();
			System.out.println(" printStack is :: " + exception.getMessage());
		} finally {
			if (oConn != null) {
				try {
					resultset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return xmldocument;
	}

	public static String unitaccess(String s, String s1) {
		int systemaccess1 = 0;
		String accesstime = "";
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;

		try {

			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			// System.out.println("select
			// sum(time_to_sec(end_time)-time_to_sec(start_time)) from
			// sco_user_info where student_id='" +s+ "' and unit_id='" +s1+ "'
			// group by unit_id");
			// resultset = statement.executeQuery("select
			// sum(time_to_sec(end_time)-time_to_sec(start_time)) from
			// sco_user_info where student_id='" +s+ "' and unit_id='" +s1+ "'
			// group by unit_id");
			// System.out.println("select
			// sum(((TO_DAYS(end_time)-To_DAYS(start_time))*24*3600)+(time_to_sec(end_time)-time_to_sec(start_time)))
			// from sco_user_info where student_id='" +s+ "' and unit_id='" +s1+
			// "' group by unit_id");
			resultset = statement.executeQuery(
					"select sum(((TO_DAYS(end_time)-To_DAYS(start_time))*24*3600)+(time_to_sec(end_time)-time_to_sec(start_time))) from sco_user_info where student_id='"
							+ s + "' and unit_id='" + s1 + "' group by unit_id");
			while (resultset.next()) {
				systemaccess1 = resultset.getInt(1);
			}
			// System.out.println("systemaccess1==="+systemaccess1);
			int hr = systemaccess1 / 3600;
			int secondleft = systemaccess1 % 3600;
			int min = secondleft / 60;
			int scnd = secondleft % 60;

			String hour = Integer.toString(hr);
			String minute = Integer.toString(min);
			String second = Integer.toString(scnd);
			// System.out.println("hour==="+hour);
			// System.out.println("minute==="+minute);
			// System.out.println("second==="+second);
			accesstime = hour + ":" + minute + ":" + second;
			// System.out.println("accesstime==="+accesstime);

		} catch (SQLException sqlexception) {
			System.out.println("Inside DataBaseLayer getPluginName(), SQLException !!!!");
			System.out
					.println("Inside DataBaseLayer getPluginName(), the error message - " + sqlexception.getMessage());
			System.out.println(
					"Inside DataBaseLayer getPluginName(), the error message - " + sqlexception.getErrorCode());
		} catch (Exception exception) {
			System.out.println("Inside DataBaseLayer getPluginName(), Exception !!!!");
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					resultset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return accesstime;
	}

	public static String nounitaccess(String s, String s1) {
		String systemaccess1 = null;

		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			// checkConnection();

			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			resultset = statement
					.executeQuery("select count(distinct session_id)  from learner_login_info where student_id='" + s
							+ "'and unit_id='" + s1 + "'");
			resultset.next();
			systemaccess1 = resultset.getString(1);
			System.out.println("99999999999999999999999ffffffffffffff" + systemaccess1);
			resultset.close();
			statement.close();
		} catch (SQLException sqlexception) {
			System.out.println("Inside DataBaseLayer getPluginName(), SQLException !!!!");
			System.out
					.println("Inside DataBaseLayer getPluginName(), the error message - " + sqlexception.getMessage());
			System.out.println(
					"Inside DataBaseLayer getPluginName(), the error message - " + sqlexception.getErrorCode());
		} catch (Exception exception) {
			System.out.println("Inside DataBaseLayer getPluginName(), Exception !!!!");
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					resultset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return systemaccess1;
	}

	public static String getRequisiteunitaccess(String s) {
		// String as[] = new String[2];
		String timespent = null;
		/*
		 * DBConnectionManager connMgr = DBConnectionManager.getInstance();
		 * Connection oConn = connMgr.getConnection("mysql");
		 */
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			// checkConnection();

			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			System.out.println("select distinct(unit_id),total_time from unit_completion_sco where unit_id ='" + s
					+ "' and sco_id='0'");
			resultset = statement
					.executeQuery("select distinct(unit_id),total_time from unit_completion_sco where unit_id ='" + s
							+ "' and sco_id='0'");
			while (resultset.next()) {

				timespent = resultset.getString(2);
			}
			System.out.println("timespent=====" + timespent);
		} catch (SQLException sqlexception) {
			System.out.println("Inside DataBaseLayer getRequisiteunitaccess(), SQLException !!!!");
			System.out.println("The Error code is - " + sqlexception.getErrorCode());
			System.out.println("The Error Message is - " + sqlexception.getMessage());
		} catch (Exception exception) {
			System.out.println("Inside DataBaseLayer getRequisiteunitaccess(), Exception!!!!");
			System.out.println("The Error message - " + exception.toString());
		} finally {
			if (oConn != null) {
				try {
					resultset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return timespent;
	}

	public static String countuserlogin() {
		String count1 = null;

		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			// checkConnection();

			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			resultset = statement.executeQuery("select count(distinct student_id) from user_login_time");
			resultset.next();
			count1 = resultset.getString(1);
			resultset.close();
			statement.close();
		} catch (SQLException sqlexception) {
			System.out.println("Inside DataBaseLayer countuserlogin(), SQLException !!!!");
			System.out
					.println("Inside DataBaseLayer countuserlogin(), the error message - " + sqlexception.getMessage());
			System.out.println(
					"Inside DataBaseLayer countuserlogin(), the error message - " + sqlexception.getErrorCode());
		} catch (Exception exception) {
			System.out.println("Inside DataBaseLayer getPluginName(), Exception !!!!");
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					resultset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return count1;
	}

	/** CHANGES BY ANINDYA ON 08.09.2008 */
	// public static Vector getForumName(String user)
	// {
	// DBConnectionManager connMgr = DBConnectionManager.getInstance();
	// Connection oConn = connMgr.getConnection("mysql");
	// Vector vForumList = new Vector();
	// String str[] = new String[5];
	// try
	// {
	//
	// Statement statement = oConn.createStatement();
	// String sql = "select c.forum_id,
	// a.forum_name,a.start_date,date_format(a.last_message_posted,\"%d-%m-%Y
	// %H:%i\"),a.no_of_message from forum a, user_forum_association c where
	// c.student_id='"+user+"' and c.forum_id=a.forum_id and c.registered_by !=
	// 'null'";
	// System.out.println("#############sql###############"+sql);
	// ResultSet oRset = statement.executeQuery(sql);
	// while(oRset.next()){
	//
	// str= new String[5];
	// str[0] = oRset.getString(1);
	// str[1] = oRset.getString(2);
	// str[2] = oRset.getString(3);
	// str[3] = oRset.getString(4);
	// str[4] = oRset.getString(5);
	// System.out.println("==str[0]== "+str[0]+" ==str[1]== "+str[1]);
	// vForumList.addElement(str);
	// }
	// System.out.println(" THE VECTOR in db ::"+vForumList +" size
	// "+vForumList.size());
	// try{oConn.close();}catch(SQLException e){}
	// }
	// catch(SQLException sqlexception)
	// {
	// System.out.println("SQLException in getForumName "+sqlexception );
	// }
	//
	// catch(Exception exception)
	// {
	// System.out.println("Exception in getForumName "+exception );
	// }
	// connMgr.freeConnection("mysql", oConn);
	// return vForumList;
	// }

	// public static Vector getforummessage(String f_name)
	// {
	//
	// Vector vForumList = new Vector(3,3);
	// DBConnectionManager connMgr = DBConnectionManager.getInstance();
	// Connection oConn = connMgr.getConnection("mysql");
	// try
	// {
	//
	// Statement statement = oConn.createStatement();
	// //ResultSet oRset = statement.executeQuery("select no_of_message from
	// forum_thread where forum_name='"+f_name+"' ");
	// ResultSet oRset = statement.executeQuery("select
	// count(no_of_message),max(created_on),sum(no_of_views) from forum_thread
	// where forum_name='"+f_name+"' ");
	// while(oRset.next()){
	//
	// //String str[] = new String[3];
	// String str1 = oRset.getString(1);
	// String str2 = oRset.getString(2);
	// String str3 = oRset.getString(3);
	// //str[3] = oRset.getString(4);
	// /*str[3] = oRset.getString(4);
	// str[4] = oRset.getString(5);*/
	// vForumList.addElement(str1);
	// vForumList.addElement(str2);
	// vForumList.addElement(str3);
	// }
	// try{oConn.close();}catch(SQLException e){}
	// }
	// catch(SQLException sqlexception)
	// {
	// System.out.println("SQLException in getforummessage "+sqlexception );
	// }
	//
	// catch(Exception exception)
	// {
	// System.out.println("Exception in getforummessage "+exception );
	// }
	// connMgr.freeConnection("mysql", oConn);
	// return vForumList;
	// }

	public static String postmade(String for_id) {
		String postmade = null;
		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {

			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			resultset = statement
					.executeQuery("select sum(no_of_message) from forum_thread where forum_id='" + for_id + "'");
			resultset.next();
			postmade = resultset.getString(1);
			resultset.close();
			statement.close();
			// try{oConn.close();}catch(SQLException e){}
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in postmade " + sqlexception);
		}

		catch (Exception exception) {
			System.out.println("Exception in postmade " + exception);
		} finally {
			if (oConn != null) {
				try {
					resultset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return postmade;
	}

	public static String thread1(String for_id) {
		String postmade = null;
		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {

			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			resultset = statement.executeQuery("select sum(no_of_message) from forum_thread where forum_id='" + for_id
					+ "' and parent_thread='null'");
			resultset.next();
			postmade = resultset.getString(1);
			resultset.close();
			statement.close();
			// try{oConn.close();}catch(SQLException e){}
		} catch (SQLException sqlexception) {
			System.out.println("SQLException in thread1 " + sqlexception);
		}

		catch (Exception exception) {
			System.out.println("Exception in thread1 " + exception);
		} finally {
			if (oConn != null) {
				try {
					resultset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return postmade;
	}

	/*****************************
	 * Changes By Indra for Course Management (Portal)
	 *******************************/
	public static Vector getCourseName(String user, String status) {
		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Connection oConn = null;
		Vector vCourseList = new Vector();
		Statement statement = null;
		ResultSet oRset = null;
		System.out.println("---------------STATUS1------------->" + status);
		try {

			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			// ResultSet oRset = statement.executeQuery("select a.course_id,
			// a.course_name, a.type,b.session_name, a.sdate,
			// a.edate,a.createdby,a.createdon,a.description,a.cpoints,a.ttimes,a.cost,a.intructor,a.intructor1
			// from course_definition a, course_session b where a.session
			// =b.session_id ");
			if (status.equals("Other")) {

				oRset = statement.executeQuery(
						"select distinct a.course_id,a.course_name, c.book_marks,date_format(c.registration_date,'%d-%m-%Y'),ifnull(date_format(c.access_allowed_till,'%d-%m-%Y'),'No Limit'),ifnull(a.ttimes,'No Limit'),c.total_access_time from course_definition a,usergroup_course_registration c where c.course_id not in (select course_id from course_collection_asso) and c.course_id not in (select course_id from course_instructor_association where user_id='"
								+ user + "') and c.student_id='" + user + "' and c.course_id=a.course_id");
				System.out.println(
						"-----------------QUERY 1 IN getCourseName(String user,String status) STATUS===OTHER-----------------");
				System.out.println(
						"select distinct a.course_id,a.course_name, c.book_marks,date_format(c.registration_date,'%d-%m-%Y'),ifnull(date_format(c.access_allowed_till,'%d-%m-%Y'),'No Limit'),ifnull(a.ttimes,'No Limit'),c.total_access_time from course_definition a,usergroup_course_registration c where c.course_id not in (select course_id from course_collection_asso) and c.course_id not in (select course_id from course_instructor_association where user_id='"
								+ user + "') and c.student_id='" + user + "' and c.course_id=a.course_id");
				// System.out.println("select a.course_id,a.course_name,
				// c.book_marks,date_format(c.registration_date,'%d-%m-%Y'),date_format(c.access_allowed_till,'%d-%m-%Y'),a.ttimes,c.total_access_time
				// from course_definition a,course_collection_asso
				// b,usergroup_course_registration c where
				// a.course_id=b.course_id and c.student_id='"+user+"' and
				// c.course_id=a.course_id and
				// b.collection_id='"+collection_id+"'");
				while (oRset.next()) {
					String str[] = new String[7];
					str[0] = oRset.getString(1);
					str[1] = oRset.getString(2);
					str[2] = oRset.getString(3);
					str[3] = oRset.getString(4);
					str[4] = oRset.getString(5);
					str[5] = oRset.getString(6);
					str[6] = oRset.getString(7);
					vCourseList.addElement(str);

				}

			} else {

				oRset = statement.executeQuery(
						"select distinct a.course_id,a.course_name, c.book_marks,date_format(c.registration_date,'%d-%m-%Y'),ifnull(date_format(c.access_allowed_till,'%d-%m-%Y'),'No Limit'),ifnull(a.ttimes,'No Limit'),c.total_access_time from course_definition a,usergroup_course_registration c,course_instructor_association d where c.course_id not in (select course_id from course_collection_asso) and c.student_id='"
								+ user
								+ "' and c.student_id=d.user_id and c.course_id=a.course_id and c.course_id=d.course_id");
				System.out.println(
						"-----------------QUERY 2 IN getCourseName(String user,String status) STATUS===OWN-----------------");
				System.out.println(
						"select distinct a.course_id,a.course_name, c.book_marks,date_format(c.registration_date,'%d-%m-%Y'),ifnull(date_format(c.access_allowed_till,'%d-%m-%Y'),'No Limit'),ifnull(a.ttimes,'No Limit'),c.total_access_time from course_definition a,usergroup_course_registration c,course_instructor_association d where c.course_id not in (select course_id from course_collection_asso) and c.student_id='"
								+ user
								+ "' and c.student_id=d.user_id and c.course_id=a.course_id and c.course_id=d.course_id");
				// System.out.println("select a.course_id,a.course_name,
				// c.book_marks,date_format(c.registration_date,'%d-%m-%Y'),date_format(c.access_allowed_till,'%d-%m-%Y'),a.ttimes,c.total_access_time
				// from course_definition a,course_collection_asso
				// b,usergroup_course_registration c where
				// a.course_id=b.course_id and c.student_id='"+user+"' and
				// c.course_id=a.course_id and
				// b.collection_id='"+collection_id+"'");
				while (oRset.next()) {
					String str[] = new String[7];
					str[0] = oRset.getString(1);
					str[1] = oRset.getString(2);
					str[2] = oRset.getString(3);
					str[3] = oRset.getString(4);
					str[4] = oRset.getString(5);
					str[5] = oRset.getString(6);
					str[6] = oRset.getString(7);
					vCourseList.addElement(str);

				}

			}

			// oRset.close();
			// statement.close();
			// try{oConn.close();}catch(SQLException e){}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					oRset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		// System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh="+vCourseList.size());
		return vCourseList;
	}

	public static Vector getCourseName(String user, String collection_id, String status) {
		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Connection oConn = null;
		Vector vCourseList = new Vector();
		Statement statement = null;
		ResultSet oRset = null;
		System.out.println("---------------STATUS------------------------>" + status);
		try {

			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			if (status.equals("Other")) {
				System.out.println("=========INSIDE if 2==============");
				String sql = "select distinct a.course_id,a.course_name, c.book_marks,date_format(c.registration_date,'%d-%m-%Y'),ifnull(date_format(c.access_allowed_till,'%d-%m-%Y'),'No Limit'),ifnull(a.ttimes,'No Limit'),ifnull(c.total_access_time,'No Limit') from course_definition a,course_collection_asso b,usergroup_course_registration c where a.course_id=b.course_id and c.student_id='"
						+ user
						+ "' and c.course_id=a.course_id and c.course_id not in (select course_id from course_instructor_association where user_id='"
						+ user + "') and b.collection_id='" + collection_id + "'";
				// System.out.println("-----------------QUERY 1 IN
				// getCourseName(String user,String collection_id,String status)
				// STATUS===OTHER-----------------");

				if (collection_id == null || collection_id.equals(""))
					sql = "select a.course_id,a.course_name, c.book_marks,date_format(c.registration_date,'%d-%m-%Y'),ifnull(date_format(c.access_allowed_till,'%d-%m-%Y'),'No Limit'),ifnull(a.ttimes,'No Limit'),ifnull(c.total_access_time,'No Limit') from course_definition a,usergroup_course_registration c where c.student_id='"
							+ user
							+ "' and c.course_id=a.course_id and c.course_id not in (select course_id from course_instructor_association where user_id='"
							+ user + "')";
				// System.out.println("-----------------QUERY 2 IN
				// getCourseName(String user,String collection_id,String status)
				// STATUS===OTHER-----------------");
				// System.out.println("select a.course_id,a.course_name,
				// c.book_marks,date_format(c.registration_date,'%d-%m-%Y'),ifnull(date_format(c.access_allowed_till,'%d-%m-%Y'),'No
				// Limit'),ifnull(a.ttimes,'No
				// Limit'),ifnull(c.total_access_time,'No Limit') from
				// course_definition a,usergroup_course_registration c where
				// c.student_id='"+user+"' and c.course_id=a.course_id");
				System.out.println("other with collections sql = " + sql);
				oRset = statement.executeQuery(sql);
				while (oRset.next()) {
					String str[] = new String[7];
					str[0] = oRset.getString(1);
					str[1] = oRset.getString(2);
					str[2] = oRset.getString(3);
					str[3] = oRset.getString(4);
					str[4] = oRset.getString(5);
					str[5] = oRset.getString(6);
					str[6] = oRset.getString(7);
					vCourseList.addElement(str);

				}

			} else {
				System.out.println("=========INSIDE else==============");

				String sql = "select a.course_id,a.course_name, c.book_marks,date_format(c.registration_date,'%d-%m-%Y'),ifnull(date_format(c.access_allowed_till,'%d-%m-%Y'),'No Limit'),ifnull(a.ttimes,'No Limit'),ifnull(c.total_access_time,'No Limit') from course_definition a,course_collection_asso b,usergroup_course_registration c,course_instructor_association d where a.course_id=b.course_id and c.student_id='"
						+ user
						+ "' and c.student_id=d.user_id and c.course_id=a.course_id and d.course_id=a.course_id and b.collection_id='"
						+ collection_id + "'";
				System.out.println(
						"-----------------QUERY 1 IN getCourseName(String user,String collection_id,String status) STATUS===OWN-----------------");
				// System.out.println("select a.course_id,a.course_name,
				// c.book_marks,date_format(c.registration_date,'%d-%m-%Y'),ifnull(date_format(c.access_allowed_till,'%d-%m-%Y'),'No
				// Limit'),ifnull(a.ttimes,'No
				// Limit'),ifnull(c.total_access_time,'No Limit') from
				// course_definition a,course_collection_asso
				// b,usergroup_course_registration
				// c,course_instructor_association d where
				// a.course_id=b.course_id and c.student_id='"+user+"' and
				// c.student_id=d.user_id and c.course_id=a.course_id and
				// b.collection_id='"+collection_id+"'");
				if (collection_id == null || collection_id.equals("")) {
					sql = "select a.course_id,a.course_name, c.book_marks,date_format(c.registration_date,'%d-%m-%Y'),ifnull(date_format(c.access_allowed_till,'%d-%m-%Y'),'No Limit'),ifnull(a.ttimes,'No Limit'),ifnull(c.total_access_time,'No Limit') from course_definition a,usergroup_course_registration c,course_instructor_association d  where c.student_id='"
							+ user
							+ "' and c.student_id=d.user_id and d.course_id=a.course_id and c.course_id=a.course_id";
					System.out.println(
							"-----------------QUERY 2 IN getCourseName(String user,String collection_id,String status) STATUS===OWN-----------------");
					// System.out.println("select a.course_id,a.course_name,
					// c.book_marks,date_format(c.registration_date,'%d-%m-%Y'),ifnull(date_format(c.access_allowed_till,'%d-%m-%Y'),'No
					// Limit'),ifnull(a.ttimes,'No
					// Limit'),ifnull(c.total_access_time,'No Limit') from
					// course_definition a,usergroup_course_registration
					// c,course_instructor_association d where
					// c.student_id='"+user+"' and c.student_id=d.user_id and
					// d.course_id=a.course_id");
				}
				oRset = statement.executeQuery(sql);
				System.out.println(sql);
				while (oRset.next()) {
					String str[] = new String[7];
					str[0] = oRset.getString(1);
					str[1] = oRset.getString(2);
					str[2] = oRset.getString(3);
					str[3] = oRset.getString(4);
					str[4] = oRset.getString(5);
					str[5] = oRset.getString(6);
					str[6] = oRset.getString(7);
					vCourseList.addElement(str);

				}

			}

			// oRset.close();
			// statement.close();
			// try{oConn.close();}catch(SQLException e){}
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					oRset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		// System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh="+vCourseList.size());
		return vCourseList;
	}

	public static String courseaccess(String s, String s1) {
		int systemaccess1 = 0;
		String accesstime = "";
		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			resultset = statement.executeQuery(
					"select sum(time_to_sec(logout_datetime)-time_to_sec(login_datetime))  from course_learner_login_info where student_id='"
							+ s + "'and course_id='" + s1 + "' group by course_id");
			while (resultset.next()) {
				systemaccess1 = resultset.getInt(1);
			}
			System.out.println("99999999999999999999999ffffffffffffff" + systemaccess1);

			int hr = systemaccess1 / 3600;
			int secondleft = systemaccess1 % 3600;
			int min = secondleft / 60;
			int scnd = secondleft % 60;

			String hour = Integer.toString(hr);
			String minute = Integer.toString(min);
			String second = Integer.toString(scnd);

			accesstime = hour + ":" + minute + ":" + second;
			System.out.println("=================accesstime===========" + accesstime);

			// resultset.close();
			// statement.close();
			// try{oConn.close();}catch(SQLException e){}
		} catch (SQLException sqlexception) {
			System.out.println("Inside DataBaseLayer courseaccess(), SQLException !!!!");
			System.out.println("Inside DataBaseLayer courseaccess(), the error message - " + sqlexception.getMessage());
			System.out
					.println("Inside DataBaseLayer courseaccess(), the error message - " + sqlexception.getErrorCode());
		} catch (Exception exception) {
			System.out.println("Inside DataBaseLayer courseaccess(), Exception !!!!");
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					resultset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return accesstime;
	}

	public static void InsertIntoCourseLearnerLoginInfo(String student_id, String course_id, String topic_id,
			String strDate, String session_id, String strTime)

	{
		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Connection oConn = null;
		Statement statement = null;
		try {
			// C
			// checkConnection();
			oConn = ds1.getConnection();
			oConn.setAutoCommit(false);
			statement = oConn.createStatement();
			boolean flag = statement.execute(
					"insert into course_learner_login_info(student_id,course_id,session_id,topic_id,login_datetime,logout_datetime)  values('"
							+ student_id + "','" + course_id + "','" + session_id + "','" + topic_id + "','" + strDate
							+ " " + strTime + "','" + strDate + " " + strTime + "')");
			// statement.close();
			oConn.commit();
			oConn.setAutoCommit(true);

		} catch (SQLException sqlexception) {
			System.out.println("Inside DataBaseLayer InsertIntoCourseLearnerLoginInfo(), Error due to SQL exception!");
			int i = sqlexception.getErrorCode();
			String s9 = sqlexception.getMessage();
			System.out.println("The Error is :- " + s9);
		} catch (Exception exception) {
			System.out.println("Inside DataBaseLayer InsertIntoCourseLearnerLoginInfo(), exception!");
			exception.printStackTrace();
			System.out.println(" printStack is :: " + exception.getMessage());
		} finally {
			if (oConn != null) {
				try {
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public static synchronized void updateCourseLoginInfo(String student_id, String topic_id, String present_sessid,
			String strTime, String strDate, String course_id) {
		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Connection oConn = null;
		Statement statement = null;
		try {
			// Connection oConn = null;
			// checkConnection();
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			statement.execute("update course_learner_login_info set logout_datetime = '" + strDate + " " + strTime
					+ "' where session_id = '" + present_sessid + "'and student_id = '" + student_id
					+ "' and topic_id= '" + topic_id + "' and course_id='" + course_id
					+ "' and logout_datetime = login_datetime");
			// statement.close();

		} catch (SQLException sqlexception) {
			System.out.println("SQL error inside DataBaseLayer.updateCourseLoginInfo()");
			System.out.println("The error code is" + sqlexception.getErrorCode());
			System.out.println("The error message is" + sqlexception.getMessage());
		} catch (Exception exception) {
			System.out.println("General exception inside DataBaseLayer.updateCourseLoginInfo()");
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
	}

	public synchronized static boolean insertBookmark(String sid, String cid, String book_mark) {
		//// course_id , schedule_id,
		//// schedule_name,location,sdate,edate,stime,etime,comment,
		//// createdby,createdon
		Statement oStmt = null;
		boolean succ = false;

		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Connection oConn = null;
		try {
			oConn = ds1.getConnection();
			// Connection oConn = null;
			// course_id , schedule_id,
			// schedule_name,location,sdate,edate,stime,etime,comment,
			// createdby,createdon//
			oConn.setAutoCommit(false);
			oStmt = oConn.createStatement();
			oStmt.execute("update  usergroup_course_registration set book_marks ='" + book_mark + "' where course_id='"
					+ cid + "' and student_id ='" + sid + "'");
			oConn.commit();
			oConn.setAutoCommit(true);
			// oStmt.close();

			succ = true;
		} catch (SQLException e) {
			System.err.println("SQLException in insertBookmark" + e.getMessage());

		} catch (Exception ex) {
			System.err.println("Exception in insertBookmark" + ex.getMessage());
		} finally {
			if (oConn != null) {
				try {
					oStmt.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return succ;
	}

	public static String getTopic(String c_id) {
		String strTopicId = "";
		/*
		 * DBConnectionManager connMgr = DBConnectionManager.getInstance();
		 * Connection oConn = connMgr.getConnection("mysql");
		 */
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			System.out.println("select book_marks from usergroup_course_registration where course_id='" + c_id + "'");
			resultset = statement.executeQuery(
					"select book_marks from usergroup_course_registration where course_id='" + c_id + "'");
			while (resultset.next()) {
				strTopicId = resultset.getString(1);

			}
			// statement.close();
			// resultset.close();

		} catch (SQLException sqlexception) {
			// log.fatal("The Error message");
			// log.dbe(DebugLevel.L1_FATAL,sqlexception);
			// sqlexception.printStackTrace();
		} catch (Exception exception) {
			// log.fatal("The Error message");
			// log.dbe(DebugLevel.L1_FATAL,exception);
			// exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					statement.close();
					resultset.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return strTopicId;

	}

	/*
	 * public static Vector getCourseDefinition(String c_id) {
	 * 
	 * //DBConnectionManager connMgr = DBConnectionManager.getInstance();
	 * //Connection oConn = connMgr.getConnection("mysql"); Vector vCourseList =
	 * new Vector(7,7);
	 * 
	 * try { Connection oConn = null; ////log.entry("getCourseDefinition()");
	 * Statement statement = oConn.createStatement(); ResultSet oRset =
	 * statement.
	 * executeQuery("select a.course_id, a.course_name, a.type,a.session, date_format(a.sdate,\"%M %e, %Y \"), date_format(a.edate,\"%M %e, %Y \"),a.createdby,a.createdon,a.description,a.cpoints,a.ttimes,a.cost,a.intructor,a.intructor1 from course_definition a ,course_session b where course_id='"
	 * +c_id + "'"); if(oRset.next()){ String str[] = new String[18]; str[0] =
	 * oRset.getString(1); str[1] = oRset.getString(2); str[2] =
	 * oRset.getString(3); str[3] = oRset.getString(4); str[4] =
	 * oRset.getString(5); str[5] = oRset.getString(6); str[6] =
	 * oRset.getString(7); str[7] = oRset.getString(8); str[8] =
	 * oRset.getString(9); str[9] = oRset.getString(10); str[10]
	 * =""+oRset.getInt(11); str[11] =""+ oRset.getInt(12); str[12] =
	 * oRset.getString(13); str[13] = oRset.getString(14);
	 * ////log.verbose("str[12] = "+str[12] );
	 * ////log.verbose("str[13] = "+str[13] );
	 * //System.out.println("str[12] = "+str[12] );
	 * //System.out.println("str[13] = "+str[13] ); Statement statement1 =
	 * oConn.createStatement(); ResultSet oRset1 = statement1.
	 * executeQuery("select concat(fname,' ', mname,' ', ename),email,affiliation from course_instructor where instructor_id ='"
	 * +str[12]+"'"); if(oRset1.next()){ str[12] = oRset1.getString(1); str[14]
	 * = oRset1.getString(2); str[16] = oRset1.getString(3);
	 * System.out.println("===affiliation===="+str[16]); } ResultSet oRset2 =
	 * statement1.
	 * executeQuery("select concat(fname,' ', mname,' ', ename),email,affiliation from course_instructor where instructor_id ='"
	 * +str[13]+"'"); if(oRset2.next()){ str[13] = oRset2.getString(1); str[15]
	 * = oRset2.getString(2); str[17] = oRset2.getString(3); }
	 * 
	 * ResultSet oRset3 = statement1.
	 * executeQuery("select session_name from course_session where session_id ='"
	 * +str[3]+"'"); if(oRset3.next()){ str[3] = oRset3.getString(1);
	 * 
	 * }
	 * 
	 * vCourseList.addElement(str);
	 * 
	 * }
	 * 
	 * statement.close(); oRset.close(); try{oConn.close();}catch(SQLException
	 * e){} } catch(SQLException sqlexception) {
	 * ////log.fatal("The Error message"); //
	 * //log.dbe(DebugLevel.L1_FATAL,sqlexception);
	 * sqlexception.printStackTrace(); } catch(Exception exception) {
	 * ////log.fatal("The Error message");
	 * ////log.dbe(DebugLevel.L1_FATAL,exception); exception.printStackTrace();
	 * } //connMgr.freeConnection("mysql", oConn); return vCourseList; }
	 */

	public static Vector selectNoticeInformation(String s)// new
	{
		// String strBody[] = new String[5];
		Vector vCourseList = new Vector(5, 5);
		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();

			resultset = statement.executeQuery(
					"select notice_heading,date_format(postedon,\"%D %M %Y %r\"),body,attachments from anno_mgmt where course_id='"
							+ s + "'ORDER BY postedon");
			while (resultset.next()) {
				String strBody[] = new String[5];
				strBody[0] = resultset.getString(1);// heading
				strBody[1] = resultset.getString(2);// posted on

				strBody[2] = resultset.getString(3);// body
				strBody[3] = resultset.getString(4);// attacch
				vCourseList.addElement(strBody);
				// log.verbose("It is from Layer:
				// "+resultset.getString(1)+""+resultset.getString(2)+""+resultset.getString(3));
				// System.out.println("It is from Layer:
				// "+resultset.getString(1)+""+resultset.getString(2)+""+resultset.getString(3));
			}
			// statement.close();
			// resultset.close();

		} catch (SQLException sqlexception) {
			// log.fatal("The Error message");
			// log.dbe(DebugLevel.L1_FATAL,sqlexception);
			// sqlexception.printStackTrace();
		} catch (Exception exception) {
			// log.fatal("The Error message");
			// log.dbe(DebugLevel.L1_FATAL,exception);
			// exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					statement.close();
					resultset.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return vCourseList;
	}

	public static String selectNumAnnounce(String s) {
		String strBody = "";
		/*
		 * DBConnectionManager connMgr = DBConnectionManager.getInstance();
		 * Connection oConn = connMgr.getConnection("mysql");
		 */
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();

			resultset = statement.executeQuery("select count(*) from anno_mgmt where course_id='" + s + "'");
			while (resultset.next()) {
				strBody = resultset.getString(1);// heading

			}
			// statement.close();
			// resultset.close();

		} catch (SQLException sqlexception) {
			// log.fatal("The Error message");
			// log.dbe(DebugLevel.L1_FATAL,sqlexception);
			// sqlexception.printStackTrace();
		} catch (Exception exception) {
			// log.fatal("The Error message");
			// log.dbe(DebugLevel.L1_FATAL,exception);
			// exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					statement.close();
					resultset.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return strBody;
	}

	public static Vector selectItemInformationCidAdmin(String c)// for
																// ViewGradeadmin
	{
		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		String temp = "";
		Vector vprovider_det1 = new Vector(3, 3);
		// Statement oStmt;
		Connection oConn = null;
		Statement oStmt = null;
		ResultSet oRset = null;
		try {
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oRset = oStmt.executeQuery("select a.gradebook from book_course_asso a where a.course='" + c + "'");
			while (oRset.next()) {
				temp = oRset.getString(1);
				for (ResultSet oRset1 = oStmt.executeQuery(
						"select a.item_name,a.attempt,a.item_id from item_mgmt a,book_item_mgmt b where a.item_id=b.item and b.book_id='"
								+ temp + "'"); oRset1.next();) {
					String strprovidername1 = oRset1.getString(1);
					String strattempt = oRset1.getString(2);
					String id = oRset1.getString(3);
					vprovider_det1.addElement(strprovidername1);
					vprovider_det1.addElement(strattempt);
					vprovider_det1.addElement(id);
				}
			}
			// oStmt.close();
			// oRset.close();

		} catch (SQLException sqlexception) {
			log.fatal("Exception in TrainingDatabaseLayer.getItemList()");
			log.dbe(DebugLevel.L1_FATAL, sqlexception);
			// System.out.println("Exception in
			// TrainingDatabaseLayer.getItemList()");
			// System.out.println("The Error Message - " +
			// sqlexception.getMessage());
		} catch (Exception ex) {
			log.fatal("Exception in TrainingDatabaseLayer.getItemList()");
			log.dbe(DebugLevel.L1_FATAL, ex);
			// System.out.println("Exception in
			// TrainingDatabaseLayer.getItemList()");
			// System.out.println("The Error Message - " + ex.getMessage());
		} finally {
			if (oConn != null) {
				try {
					oStmt.close();
					oRset.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return vprovider_det1;
	}

	/*****************************
	 * Changes By Indra for Course Management (Portal) ---> End
	 *******************************/

	/** CHANGES BY ANINDYA ON 08.09.2008 */
	public static Vector getForumName(String user) {
		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Vector vForumList = new Vector();
		String str[] = new String[5];
		Connection oConn = null;
		Statement statement = null;
		ResultSet oRset = null;
		Statement statement1 = null;
		ResultSet oRset1 = null;
		Statement statement2 = null;
		ResultSet oRset2 = null;
		try {
			oConn = ds1.getConnection();
			System.out.println("=====oConn==== " + oConn);
			statement = oConn.createStatement();
			statement1 = oConn.createStatement();
			statement2 = oConn.createStatement();
			String sql = "select c.forum_id, a.forum_name,date_format(a.start_date,\"%d-%m-%Y\"),date_format(a.last_message_posted,\"%d-%m-%Y %H:%i\"),a.no_of_message from forum a, user_forum_association c where c.student_id='"
					+ user + "' and c.forum_id=a.forum_id and c.registered_by != 'null'";
			System.out.println("#############sql###############" + sql);
			oRset = statement.executeQuery(sql);
			while (oRset.next()) {

				str = new String[5];
				str[0] = oRset.getString(1);
				str[1] = oRset.getString(2);
				str[2] = oRset.getString(3);
				str[3] = oRset.getString(4);
				str[4] = oRset.getString(5);
				System.out.println("==str[0]== " + str[0] + " ==str[1]== " + str[1]);
				vForumList.addElement(str);
			}
			oRset1 = statement1.executeQuery(
					"select c.forum_id, a.forum_name,date_format(a.start_date,\"%d-%m-%Y\"),date_format(a.last_message_posted,\"%d-%m-%Y %H:%i\"),a.no_of_message from forum a, group_forum_association c,student_group_association b where b.student_id='"
							+ user + "' and c.forum_id=a.forum_id and b.group_id=c.group_id");
			while (oRset1.next()) {
				oRset2 = statement2.executeQuery("select forum_id from user_forum_association where student_id='" + user
						+ "' and forum_id='" + oRset1.getString(1) + "'");
				if (!oRset2.next()) {
					str = new String[5];
					str[0] = oRset1.getString(1);
					str[1] = oRset1.getString(2);
					str[2] = oRset1.getString(3);
					str[3] = oRset1.getString(4);
					str[4] = oRset1.getString(5);
					vForumList.addElement(str);
				}
			}
			System.out.println("   THE VECTOR in db  ::" + vForumList + " size " + vForumList.size());

		} catch (SQLException sqlexception) {
			System.out.println("SQLException in getForumName " + sqlexception);
		}

		catch (Exception exception) {
			System.out.println("Exception in getForumName " + exception);
		} finally {
			if (oConn != null) {
				try {
					if (statement != null)
						statement.close();
					if (statement1 != null)
						statement1.close();
					if (statement2 != null)
						statement2.close();
					if (oRset != null)
						oRset.close();
					if (oRset1 != null)
						oRset1.close();
					if (oRset2 != null)
						oRset2.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return vForumList;
	}

	public static Vector getforummessage(String f_name) {

		Vector vForumList = new Vector(3, 3);
		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Connection oConn = null;
		Statement statement = null;
		ResultSet oRset = null;
		try {

			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			// ResultSet oRset = statement.executeQuery("select no_of_message
			// from forum_thread where forum_name='"+f_name+"' ");
			oRset = statement.executeQuery(
					"select count(no_of_message),max(created_on),sum(no_of_views) from forum_thread  where forum_name='"
							+ f_name + "' ");
			while (oRset.next()) {

				// String str[] = new String[3];
				String str1 = oRset.getString(1);
				String str2 = oRset.getString(2);
				String str3 = oRset.getString(3);
				// str[3] = oRset.getString(4);
				/*
				 * str[3] = oRset.getString(4); str[4] = oRset.getString(5);
				 */
				vForumList.addElement(str1);
				vForumList.addElement(str2);
				vForumList.addElement(str3);
			}

		} catch (SQLException sqlexception) {
			System.out.println("SQLException in getforummessage " + sqlexception);
		}

		catch (Exception exception) {
			System.out.println("Exception in getforummessage " + exception);
		} finally {
			if (oConn != null) {
				try {
					statement.close();
					oRset.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return vForumList;
	}

	/** CHANGES BY ANINDYA ON 15.09.2008 */

	public static Vector getForumThreadInfo(String s1, String s2) {
		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Vector vThreadList = new Vector(2, 2);
		Connection oConn = null;
		Statement statement = null;
		ResultSet oRset = null;
		try {
			// checkConnection();
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			oRset = statement.executeQuery("select reply_id,reply_title from forum_reply where forum_name = '" + s1
					+ "' AND thread_title='" + s2 + "'");
			while (oRset.next()) {
				String str[] = new String[2];
				str[0] = oRset.getString(1);
				str[1] = oRset.getString(2);
				vThreadList.addElement(str);
			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					statement.close();
					oRset.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return vThreadList;
	}

	public static Vector getThreadInfo(String forum_id) {

		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Vector vCourseList = new Vector(7, 7);
		Connection oConn = null;
		Statement statement = null;
		ResultSet oRset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			oRset = statement.executeQuery(
					"select a.forum_name,b.thread_id,b.thread_title,b.created_by,b.no_of_message,b.no_of_views,date_format(b.created_on,'%d-%m-%Y %H:%i:%S') from forum a,forum_thread b where a.forum_name='"
							+ forum_id + "' and a.forum_name=b.forum_name and parent_thread='null'");
			while (oRset.next()) {
				String str[] = new String[7];
				str[0] = oRset.getString(1);
				str[1] = oRset.getString(2);
				str[2] = oRset.getString(3);
				str[3] = oRset.getString(4);
				str[4] = oRset.getString(5);
				str[5] = oRset.getString(6);
				str[6] = oRset.getString(7);

				vCourseList.addElement(str);
			}
			// vCourseList.addElement(str);

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
		}

		catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					statement.close();
					oRset.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return vCourseList;
	}

	public static Vector getnoofmessage(String s1, String s2) {
		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Vector vThreadList = new Vector(2, 2);
		Connection oConn = null;
		Statement statement = null;
		ResultSet oRset = null;
		try {
			// checkConnection();
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			oRset = statement.executeQuery("select thread_title,forum_name from forum_thread where forum_name = '" + s1
					+ "' AND parent_thread='" + s2 + "'");
			while (oRset.next()) {
				String str[] = new String[2];
				str[0] = oRset.getString(1);
				str[1] = oRset.getString(2);
				vThreadList.addElement(str);
			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					statement.close();
					oRset.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return vThreadList;
	}

	public static String returnforumname(String s) {
		String strBody = "";

		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();

			resultset = statement.executeQuery("select forum_name from forum where forum_id='" + s + "'");
			System.out.println("select forum_name from forum where forum_id='" + s + "'");
			while (resultset.next()) {
				strBody = resultset.getString(1);// heading

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					statement.close();
					resultset.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}

		return strBody;
	}

	/** +++++++++++++++ END ANINDYA +++++++++++++++++ */

	public static String getLoginTime(String user_id, String session_id) {
		String strTime = "";
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			String sql = "select logged_in_at from user_login_time where session_id='" + session_id
					+ "' and student_id='" + user_id + "'";
			System.out.println(sql);
			resultset = statement.executeQuery(sql);

			while (resultset.next()) {
				strTime = resultset.getString(1);// heading

			}

		} catch (SQLException sqlexception) {
			System.out.println("SQLException in returnforumname " + sqlexception);
		} catch (Exception exception) {
			System.out.println("Exception in returnforumname " + exception);
		} finally {
			if (oConn != null) {
				try {
					statement.close();
					resultset.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return strTime;
	}

	public static void setForumDynamicInfo(String user_id, String session_id, String logintime, String forum_id) {
		Connection oConn = null;
		Statement statement = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			String sql = "INSERT INTO forum_dynamic_information (student_id, session_id, logged_in_at, forum_id) VALUES ('"
					+ user_id + "','" + session_id + "',NOW(),'" + forum_id
					+ "') ON DUPLICATE KEY UPDATE logged_in_at = NOW()";

			// String sql="insert into forum_dynamic_information values
			// ('"+user_id+"','"+session_id+"','"+logintime+"','"+forum_id+"')";
			System.out.println(sql);
			statement.execute(sql);

		} catch (SQLException sqlexception) {
			System.out.println("SQLException in setForumDynamicInfo " + sqlexception);
		} catch (Exception exception) {
			System.out.println("Exception in setForumDynamicInfo " + exception);
		} finally {
			if (oConn != null) {
				try {
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}

	}

	public static Vector getAllMessages(String forum_name) {

		Vector vCourseList = new Vector(6, 6);
		Connection oConn = null;
		Statement statement = null;
		ResultSet oRset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			String sql = "select a.forum_name,a.message,a.thread_title,a.created_by,date_format(a.created_on,'%d-%m-%Y %H:%i:%S'),a.attachments from forum_message_body a where a.forum_name='"
					+ forum_name + "'";
			System.out.println(sql);
			oRset = statement.executeQuery(sql);
			while (oRset.next()) {
				String str[] = new String[6];
				str[0] = oRset.getString(1);
				str[1] = oRset.getString(2);
				str[2] = oRset.getString(3);
				str[3] = oRset.getString(4);
				str[4] = oRset.getString(5);
				str[5] = oRset.getString(6);

				vCourseList.addElement(str);
			}

		} catch (SQLException sqlexception) {
			System.out.println("SQLException in returnforumname " + sqlexception);
		} catch (Exception exception) {
			System.out.println("Exception in returnforumname " + exception);
		} finally {
			if (oConn != null) {
				try {
					statement.close();
					oRset.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return vCourseList;
	}

	public static String getUserRole(String s) {
		String role = null;
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			resultset = statement.executeQuery(
					"select a.title from role a,user_role b where  a.role_id=b.role_id and b.user_id='" + s + "'");
			while (resultset.next()) {
				role = resultset.getString(1);
			}
			// resultset.close();
			// statement.close();
		} catch (SQLException sqlexception) {
			System.out.println("Inside DataBaseLayer getPluginName(), SQLException !!!!");
			System.out.println("Inside DataBaseLayer getUserRole  the error message - " + sqlexception.getMessage());
			System.out.println(
					"Inside DataBaseLayer getPluginName(), the error message - " + sqlexception.getErrorCode());
		} catch (Exception exception) {
			System.out.println("Inside DataBaseLayer getPluginName(), Exception !!!!");
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					resultset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return role;
	}

	public static void deleteUserLoginTime(String s) {
		Connection oConn = null;
		Statement statement = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			boolean flag = statement.execute("delete from user_login_time where student_id='" + s + "'");

		} catch (SQLException sqlexception) {
			// System.out.println("...............................................>>>>>>>>>>>>>>>>>>>>>................"+sqlexception.getMessage());
			System.out.println("The Error Message - " + sqlexception.getMessage());
			System.out.println("The Error Code    - " + sqlexception.getErrorCode());
			while ((sqlexception = sqlexception.getNextException()) != null) {
				System.out.println("The Error message - " + sqlexception.getMessage());
				System.out.println("The Error code is - " + sqlexception.getErrorCode());
			}
		} catch (Exception exception) {
			System.out.println("General exception in DataBaseLayer.deleteStudenLogin()");
			exception.printStackTrace();
			System.out.println("The message - " + exception.toString());
		} finally {
			if (oConn != null) {
				try {
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public static byte[] getUserPhoto(String userId) {

		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection conn = connMgr.getConnection("mysql");
		byte[] _blob = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds1.getConnection();
			pstmt = conn.prepareStatement("SELECT photo FROM student_details WHERE student_id = ?");
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("rs has next");
				Blob blob = rs.getBlob(1);
				int length = (int) blob.length();
				System.out.println("length is:" + length);
				_blob = blob.getBytes(1, length);

			}
			System.out.println("Completed.Retreiving..");
			// conn.close();

		} catch (SQLException ex) {
			System.out.println("SQLException in getUserPhoto " + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in getUserPhoto " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
		return _blob;
	}

	////////////////////////////////////////////////////////////
	// Nayna
	/////////////////////////////////////////////////////
	public static synchronized void insert_into_studynamicinfo(String s, String s1, String s2, String s3) {
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			resultset = statement.executeQuery("select count(*) from student_dynamic_information where student_id ='"
					+ s + "' and session_id = '" + s1 + "'");
			resultset.next();
			if (resultset.getInt(1) == 0) {
				Statement statement1 = oConn.createStatement();
				boolean flag = statement1.execute(
						"Insert into student_dynamic_information(student_id, session_id, logged_in_at, course_id) values ('"
								+ s + "','" + s1 + "','" + s2 + "','" + s3 + "')");
			} else if (resultset.getInt(1) > 0) {
				Statement statement2 = oConn.createStatement();
				boolean flag1;
				if (s3 != null)
					flag1 = statement2.execute("update student_dynamic_information set course_id = '" + s3
							+ " 'where student_id ='" + s + "' and session_id = '" + s1 + "'");
				else
					flag1 = statement2
							.execute("update student_dynamic_information set course_id = null 'where student_id ='" + s
									+ "' and session_id = '" + s1 + "'");
			}
			// resultset.close();
			// statement.close();

		} catch (SQLException sqlexception) {
			System.out.println("Inside DataBaseLayer, insert_into_studynamicinfo(), SQLException!!!!");
			sqlexception.printStackTrace();
		} catch (Exception exception) {
			System.out.println("Inside DataBaseLayer, insert_into_studynamicinfo(), Exception!!!!");
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					resultset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}

	}

	////////////////////// Nayna//////////////////////
	public static String getScoTitle(String sco_id, String course_id) {

		String str = "";
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset1 = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			// ResultSet resultset1;
			System.out.println("select sco_title from sco_user_info where sco_id='" + sco_id + "' and unit_id='"
					+ course_id + "'");
			resultset1 = statement.executeQuery("select sco_title from sco_user_info where sco_id='" + sco_id
					+ "' and unit_id='" + course_id + "'");
			while (resultset1.next()) {
				str = resultset1.getString(1);
			}
			// resultset1.close();
			// statement.close();

		} catch (SQLException sqlexception) {
			System.out.println("Error due to SQL exception inside DataBaseLayer.getUserscoInfo() getLessonStatus");
			int i = sqlexception.getErrorCode();
			String s2 = sqlexception.getMessage();
		} catch (Exception exception) {
			System.out.println("Exception inside DataBaseLayer.getUserscoInfo() getLessonStatus");
			exception.printStackTrace();
			System.out.println(" printStack is :: " + exception.getMessage());
		} finally {
			if (oConn != null) {
				try {
					resultset1.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return str;
	}

	/*********************************** surajit *******************/
	public static Vector getCollections(String user) {
		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Vector vCollectionList = new Vector();
		String str[] = new String[2];
		Connection oConn = null;
		Statement statement = null;
		ResultSet oRset = null;
		try {

			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			String sql = "select distinct a.collection_id,a.collection_name from course_collection_mgmt a,course_collection_asso b,usergroup_course_registration c where a.collection_id=b.collection_id and c.course_id=b.course_id and c.student_id='"
					+ user + "'";
			System.out.println("#############sql###############" + sql);
			oRset = statement.executeQuery(sql);
			while (oRset.next()) {

				str = new String[2];
				str[0] = oRset.getString(1);
				str[1] = oRset.getString(2);

				System.out.println("==str[0]== " + str[0] + " ==str[1]== " + str[1]);
				vCollectionList.addElement(str);
			}
			System.out.println("THE VECTOR in db  ::" + vCollectionList + " size " + vCollectionList.size());

		} catch (SQLException sqlexception) {
			System.out.println("SQLException in getCollections " + sqlexception);
		}

		catch (Exception exception) {
			System.out.println("Exception in getCollections " + exception);
		} finally {
			if (oConn != null) {
				try {
					oRset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return vCollectionList;
	}

	/********************************* surajit 13.01.09 *****************/

	public static String getStudentName(String s) {
		String user = "";
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			resultset = statement.executeQuery(
					"select concat(first_name,' ',middle_name,' ',sname) from student_details where student_id='" + s
							+ "'");
			while (resultset.next()) {
				user = resultset.getString(1);
			}
			// resultset.close();
			// statement.close();
		} catch (SQLException sqlexception) {
			System.out.println("Inside DataBaseLayer getStudentName(), SQLException !!!!");
			System.out.println("Inside DataBaseLayer getStudentName the error message - " + sqlexception.getMessage());
			System.out.println(
					"Inside DataBaseLayer getStudentName(), the error message - " + sqlexception.getErrorCode());
		} catch (Exception exception) {
			System.out.println("Inside DataBaseLayer getStudentName(), Exception !!!!");
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					resultset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return user;
	}

	public static Vector getTrainingInfo(String student_id) {

		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Vector vtrain = new Vector(10, 10);
		Connection oConn = null;
		Statement statement = null;
		ResultSet oRset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			oRset = statement.executeQuery(
					"select a.type,c.startdate,c.enddate,b.attended,b.completed,a.status from program_instance_management a,record_user b,program_schedule_management c where a.program_id=c.program_id and a.program_id=b.program_id and b.user_id='"
							+ student_id + "'");
			while (oRset.next()) {

				String type = oRset.getString(1);
				String sdate = oRset.getString(2);
				String edate = oRset.getString(3);
				String att = oRset.getString(4);// attended
				String comp = oRset.getString(5);// completed
				String st = oRset.getString(6);// status
				vtrain.addElement(type);
				vtrain.addElement(sdate);
				vtrain.addElement(edate);
				vtrain.addElement(att);
				vtrain.addElement(comp);
				vtrain.addElement(st);
			}
			// statement.close();
			// oRset.close();

		} catch (SQLException sqlexception) {
			log.fatal("The Error Message");
			log.dbe(DebugLevel.L1_FATAL, sqlexception);
			// sqlexception.printStackTrace();
		} catch (Exception exception) {
			log.fatal("The Error Message");
			log.dbe(DebugLevel.L1_FATAL, exception);
			// exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					statement.close();
					oRset.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return vtrain;
	}

	/********************************* End surajit 13.01.09 *****************/

	/********************************* end surajit *****************/

	/* ===========Nayna========================== */
	public static Vector getCourses(String userid) {
		Vector vector = new Vector(1, 1);
		Connection oConn = null;
		Statement statement = null;
		Statement statement1 = null;
		ResultSet resultset = null;
		ResultSet resultset1 = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			statement1 = oConn.createStatement();
			for (resultset = statement.executeQuery(
					"select a.unit_id , a.unit_name from unit_details a , csformat b,unit_student_association c where b.unit_id = a.unit_id and  a.unit_id=c.unit_id and c.student_id='"
							+ userid + "'  and b.csf is not null"); resultset
									.next(); vector.addElement(resultset.getString(2)))
				vector.addElement(resultset.getString(1));

			for (resultset1 = statement1.executeQuery(
					"select distinct a.unit_id , a.unit_name from unit_details a , csformat b,student_group_association c,unit_group_association d where b.unit_id = a.unit_id and  a.unit_id=d.unit_id and c.group_id=d.group_id and c.student_id='"
							+ userid
							+ "' and d.unit_id not in(select unit_id from unit_student_association where student_id='"
							+ userid + "') and b.csf is not null"); resultset1
									.next(); vector.addElement(resultset1.getString(2)))
				vector.addElement(resultset1.getString(1));

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					if (statement != null)
						statement.close();
					if (statement1 != null)
						statement1.close();
					if (resultset != null)
						resultset.close();
					if (resultset1 != null)
						resultset1.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return vector;
	}

	public static Vector getNoticeInformation(String useid) {
		Vector vector = new Vector(6, 6);
		ResultSet resultset = null;
		Statement statement = null;
		ResultSet resultset1;
		ResultSet resultset2;
		boolean b = false;
		Connection oConn = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			Statement statement1 = oConn.createStatement();
			Statement statement2 = oConn.createStatement();
			String as[] = new String[7];
			// System.out.println("==========getNoticeInformation==========");

			resultset = statement.executeQuery(
					"select a.notice_id,a.heading from bulletin_board a where a.status = 'Active' and (a.group_id='0' or a.group_id in (select group_id from student_group_association where student_id='"
							+ useid + "')) order by a.posted_on DESC");
			while (resultset.next()) {

				vector.addElement(resultset.getString(1));
				vector.addElement(resultset.getString(2));
			}

			// statement.close();

		} catch (SQLException sqlexception) {
			System.out.println("Exception in DataBaseLayer.getNoticeInformation()");
			System.out.println("The Error Message in notice- " + sqlexception.getMessage());
		} catch (Exception exception) {
			System.out.println("Exception in DataBaseLayer.getNoticeInformation()");
			System.out.println("The Error Message in notice-- " + exception.getMessage());
		} finally {
			if (oConn != null) {
				try {
					if (statement != null)
						statement.close();
					if (resultset != null)
						resultset.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return vector;
	}

	public static String checkRegistration(String s, String s1) {
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Object obj3 = null;
		Object obj4 = null;
		String s12 = null;
		Object obj5 = null;
		String s15 = null;
		String s16 = "U";
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			resultset = statement
					.executeQuery("select account_status from student_details where student_id = '" + s1 + "'");
			String s13;
			if (resultset.next()) {
				s13 = resultset.getString(1);
			} else {
				String s14 = "V";
				return s16;
			}
			resultset = statement.executeQuery(
					"select total_access_time, access_allowed_till, if(access_allowed_till is not null and (access_allowed_till< sysdate()) ,'Date Over','Available') from unit_student_association where unit_id ='"
							+ s + "' and student_id = '" + s1 + "'");
			if (resultset.next()) {
				String s2 = resultset.getString(1);
				String s4 = resultset.getString(2);
				String s10 = resultset.getString(3);
				Statement statement1 = oConn.createStatement();
				ResultSet resultset1 = statement1.executeQuery(
						"select coalesce(sum(if(logout_datetime>=login_datetime,(to_days(logout_datetime)-to_days(login_datetime))*86400,0) + if(time_to_sec(logout_datetime)>= time_to_sec(login_datetime),time_to_sec(logout_datetime)-time_to_sec(login_datetime),(86400+time_to_sec(logout_datetime))-time_to_sec(login_datetime)))/3600,0) from learner_login_info where student_id = '"
								+ s1 + "' and unit_id = '" + s + "'");
				resultset1.next();
				String s6 = resultset1.getString(1);
				String s8;
				if (s6 != null && s2 != null) {
					if ((new Float(s6)).floatValue() >= (new Float(s2)).floatValue())
						s8 = "Time Over";
					else
						s8 = "Currently Available";
				} else {
					s8 = "Currently Available";
				}
				resultset1 = statement1
						.executeQuery("select status from unit_creation_details where unit_id = '" + s + "'");
				if (resultset1.next())
					s12 = resultset1.getString(1);
				s15 = "'" + s + "'";
				s16 = "A";
				if (s8.equalsIgnoreCase("Time Over"))
					s16 = "T";
				if (s10.equalsIgnoreCase("Date Over"))
					s16 = "D";
				if (s12.equalsIgnoreCase("Inactive"))
					s16 = "I";
				s2 = null;
				s4 = null;
				s8 = null;
				s10 = null;
				s12 = null;
			}
			resultset = statement.executeQuery(
					"select a.total_access_time, a.access_allowed_till, if((a.access_allowed_till is not null) and (a.access_allowed_till < sysdate()),'Date Over','Available') from unit_group_association a ,student_group_association b where b.student_id = '"
							+ s1 + "' and a.group_id = b.group_id and a.unit_id not in (" + s15 + ") and a.unit_id='"
							+ s + "'");
			if (resultset.next()) {
				String s3 = resultset.getString(1);
				String s5 = resultset.getString(2);
				String s11 = resultset.getString(3);
				Statement statement2 = oConn.createStatement();
				ResultSet resultset2 = statement2.executeQuery(
						"select coalesce(sum(if(logout_datetime>=login_datetime,(to_days(logout_datetime)-to_days(login_datetime))*86400,0) + if(time_to_sec(logout_datetime)>= time_to_sec(login_datetime),time_to_sec(logout_datetime)-time_to_sec(login_datetime),(86400+time_to_sec(logout_datetime))-time_to_sec(login_datetime)))/3600,0) from learner_login_info where student_id = '"
								+ s1 + "' and unit_id = '" + s + "'");
				resultset2.next();
				String s7 = resultset2.getString(1);
				String s9;
				if (s7 != null && s3 != null) {
					if ((new Float(s7)).floatValue() >= (new Float(s3)).floatValue())
						s9 = "Time Over";
					else
						s9 = "Currently Available";
				} else {
					s9 = "Currently Available";
				}
				resultset2 = statement2
						.executeQuery("select status from unit_creation_details where unit_id = '" + s + "'");
				if (resultset2.next())
					s12 = resultset2.getString(1);
				s16 = "A";
				if (s9.equalsIgnoreCase("Time Over"))
					s16 = "T";
				if (s11.equalsIgnoreCase("Date Over"))
					s16 = "D";
				if (s12.equalsIgnoreCase("Inactive"))
					s16 = "I";
			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					statement.close();
					resultset.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return s16;
	}

	// public synchronized static Vector getForumName1(String userid)
	// {
	//
	//
	// Vector vCourseList = new Vector(2,2);
	//
	// try
	// {
	// Connection oConn = null;
	// Statement statement = oConn.createStatement();
	// ResultSet oRset = statement.executeQuery("select a.forum_name, a.forum_id
	// from forum a, user_forum_association b where a.forum_id=b.forum_id and
	// b.student_id='"+userid+"'");
	// while(oRset.next()){
	// String str[] = new String[2];
	// str[0] = oRset.getString(1);
	// str[1] = oRset.getString(2);
	// vCourseList.addElement(str);
	// }
	// Statement statement1 = oConn.createStatement();
	// ResultSet oRset2 = statement1.executeQuery("select a.forum_name,
	// a.forum_id from forum a, group_forum_association
	// b,student_group_association c where a.forum_id=b.forum_id and
	// b.group_id=c.group_id and c.student_id='"+userid+"'");
	// while(oRset2.next()){
	// String str[] = new String[2];
	// str[0] = oRset2.getString(1);
	// str[1] = oRset2.getString(2);
	// vCourseList.addElement(str);
	// }
	// try{oConn.close();}catch(SQLException e){}
	// }
	// catch(SQLException sqlexception)
	// {
	// sqlexception.printStackTrace();
	// }
	//
	// catch(Exception exception)
	// {
	// exception.printStackTrace();
	// }
	//
	// return vCourseList;
	// }
	public synchronized static Vector getForumName1() {

		Vector vCourseList = new Vector(2, 2);
		Connection oConn = null;
		Statement statement = null;
		ResultSet oRset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			oRset = statement.executeQuery("select forum_name, forum_id from forum ");
			while (oRset.next()) {
				String str[] = new String[2];
				str[0] = oRset.getString(1);
				System.out.println("str[0]=====getForumName1=========== " + str[0]);
				str[1] = oRset.getString(2);
				System.out.println("str[1] ========getForumName1========= " + str[1]);
				vCourseList.addElement(str);
			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
		}

		catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					oRset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return vCourseList;
	}

	public static Vector getForumList(String sid) {

		Vector vForum = new Vector(2, 2);
		int i = 0;
		String student_id = null;
		String forum_id = null;
		String s17 = null;
		Connection oConn = null;
		Statement statement = null;
		ResultSet oRset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			// ResultSet oRset = statement.executeQuery("select
			// student_id,forum_id from user_forum_association where student_id
			// ='"+sid+"'");

			oRset = statement.executeQuery(
					"select u.student_id,f.forum_name from user_forum_association u, forum f where u.forum_id= f.forum_id and student_id ='"
							+ sid + "'");
			while (oRset.next()) {
				student_id = oRset.getString(1);
				forum_id = oRset.getString(2);
				vForum.addElement(forum_id);
				vForum.addElement(student_id);

				Statement statement1 = oConn.createStatement();
				ResultSet resultset1 = statement1
						.executeQuery("select first_name from student_details  where student_id='" + student_id + "'");
				while (resultset1.next()) {
					String student_fname = resultset1.getString(1);
					vForum.addElement(student_fname);
				}

				if (i == 0)
					s17 = "'" + forum_id + "'";
				else
					s17 = s17 + ",'" + forum_id + "'";
				// System.out.println("***********forum:"+ s17);
				i++;

				resultset1.close();
				statement1.close();

			}

			ResultSet resultset2;
			Statement statement3 = oConn.createStatement();
			// resultset2 = statement3.executeQuery("select
			// a.forum_id,a.group_id,b.student_id from group_forum_association a
			// ,student_group_association b where b.group_id =a.group_id and
			// b.student_id='"+sid+"' and a.forum_id not in ("+s17+")");

			resultset2 = statement3.executeQuery(
					"select f.forum_name,a.group_id, b.student_id from group_forum_association a ,student_group_association b, forum f where a.forum_id=f.forum_id and b.group_id =a.group_id and b.student_id='"
							+ sid + "' and f.forum_name not in (" + s17 + ")");
			while (resultset2.next()) {
				forum_id = resultset2.getString(1);
				String s3 = resultset2.getString(2);
				String s4 = resultset2.getString(3);

				// System.out.println("***********forum:"+ forum_id);
				if (vForum.contains(forum_id)) {
					// System.out.println("contains");
				} else {
					vForum.addElement(forum_id);
					vForum.addElement(s4);

					Statement statement4 = oConn.createStatement();
					ResultSet resultset3 = statement4
							.executeQuery("select first_name from student_details  where student_id='" + sid + "'");
					while (resultset3.next()) {
						String student_fname = resultset3.getString(1);
						vForum.addElement(student_fname);
					}

					s17 = s17 + "," + forum_id + "'";
					// System.out.println("s17:"+ s17);

					resultset3.close();
					statement4.close();
				}
			}

			resultset2.close();
			statement3.close();

			// oConn.commit();
			// oConn.setAutoCommit(true);
			// oRset.close();
			// statement.close();

		} catch (SQLException sqlexception) {
			System.out.println("Exception in DataBaseLayer.getForumList()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
		} catch (Exception exception) {
			System.out.println("Exception in DataBaseLayer.getForumList()");
			System.out.println("The Error Message - " + exception.getMessage());
		} finally {
			if (oConn != null) {
				try {
					oRset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		System.out.println("===============vForum:====== " + vForum);
		return vForum;
	}

	///////////////////////////////////// end
	///////////////////////////////////// search////////////////////////////////////////
	//////////////////////////// prerequesite
	///////////////////////////////////// start///////////////////////////////////////////
	public synchronized static boolean isPreUnitExists(String course_id) {

		Statement oStmt = null;
		ResultSet oRset = null;
		boolean flag = false;
		Connection oConn = null;
		try {
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oRset = oStmt.executeQuery("select * from unit_prerequisite where unit_id='" + course_id + "'");
			if (oRset.next()) {
				flag = true;
			}
			// oRset.close();
			// oStmt.close();

		} catch (SQLException e) {
			System.out.println("Error due to SQL exception : parse()");
			System.out.println("SQL exception : " + e.toString());
		} catch (Exception ex) {
			System.out.println("Error due to exception : parse()");
			ex.printStackTrace();
			System.out.println(" printStack is :: " + ex.getMessage());
		} finally {
			if (oConn != null) {
				try {
					oRset.close();
					oStmt.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return flag;
	}

	public static Vector getPreUnitInfo(String u_id) {

		Vector vCourseList = new Vector(2, 2);
		Connection oConn = null;
		Statement statement = null;
		ResultSet oRset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			oRset = statement
					.executeQuery("select pre_unit_id,unit_id from unit_prerequisite where unit_id = '" + u_id + "'");
			while (oRset.next()) {

				String str0 = oRset.getString(1);
				String str1 = oRset.getString(2);
				vCourseList.addElement(str0);
				vCourseList.addElement(str1);
			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
		}

		catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					oRset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return vCourseList;
	}

	public synchronized static boolean isPreUnitCompleted(String u_id, String s_id) {

		Statement oStmt = null;
		ResultSet oRset = null;
		ResultSet oRset1;
		ResultSet oRset2;
		ResultSet oRset3;
		ResultSet oRset4;
		ResultSet oRset5;
		boolean flag = false;
		String tottime = null;
		String time_vis = null;
		String login_info = null;
		String timeaccess = null;
		String sco_id = null;
		String title = null;
		String status = null;
		String LessonStatus = null;
		String time_spent = null;
		String scotime = null;
		String marks = null;
		String RawScore = null;
		java.util.Date start = null;
		java.util.Date end = null;
		int c = 1;
		int q = 1;
		int tspent = 0;
		int scot = 0;
		int commarks = 0;
		int scomarks = 0;
		int m = 1;

		// String myFormatString = "HH:mm:ss";
		// SimpleDateFormat df = new SimpleDateFormat(myFormatString);

		Connection oConn = null;

		try {
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			// System.out.println("select total_time,times_visited from
			// unit_completion_sco where unit_id='"+u_id+"' group by unit_id");
			oRset = oStmt.executeQuery("select total_time,times_visited from unit_completion_sco where unit_id='" + u_id
					+ "' group by unit_id");

			if (oRset.next()) {
				tottime = oRset.getString(1);
				time_vis = oRset.getString(2);
				// System.out.println("=====isPreUnitCompleted=====tottime====="+tottime);
				// System.out.println("=====isPreUnitCompleted=====time_vis====="+time_vis);

			}
			// System.out.println("select count(distinct session_id) from
			// learner_login_info where student_id='" +s_id+ "' and course_id='"
			// +u_id+ "'");
			oRset1 = oStmt.executeQuery("select count(distinct session_id)  from learner_login_info where student_id='"
					+ s_id + "' and unit_id='" + u_id + "'");
			if (oRset1.next()) {
				login_info = oRset1.getString(1);
				// System.out.println("=====isPreUnitCompleted=====login_info====="+login_info);
			}
			// System.out.println("select
			// sum(((TO_DAYS(end_time)-To_DAYS(start_time))*24*3600+(time_to_sec(end_time)-time_to_sec(start_time)))/3600)
			// from sco_user_info where student_id='" +s_id+ "'and unit_id='"
			// +u_id+ "' group by unit_id");
			oRset2 = oStmt.executeQuery(
					"select sum(((TO_DAYS(end_time)-To_DAYS(start_time))*24*3600+(time_to_sec(end_time)-time_to_sec(start_time)))/3600)  from sco_user_info where student_id='"
							+ s_id + "'and unit_id='" + u_id + "' group by unit_id");
			if (oRset2.next()) {
				timeaccess = oRset2.getString(1);
				// System.out.println("=====timeaccess=====login_info====="+timeaccess);
			}
			// System.out.println("select
			// a.sco_id,a.status,b.Identifier,c.LessonStatus from
			// unit_completion_sco a,ItemInfo b,UserSCOInfo c where
			// a.unit_id='"+u_id+"' and a.unit_id=b.CourseID and
			// a.unit_id=c.CourseID and a.sco_id=b.Identifier and
			// c.SCOID=b.Identifier and c.UserID='"+s_id+"'");
			oRset3 = oStmt.executeQuery(
					"select a.sco_id,a.status,b.identifier,c.lessonstatus from unit_completion_sco a,iteminfo b,userscoinfo c where a.unit_id='"
							+ u_id
							+ "' and a.unit_id=b.unit_id and a.unit_id=c.unit_id and a.sco_id=b.identifier and c.sco_id=b.identifier and c.user_id='"
							+ s_id + "'");
			while (oRset3.next()) {
				title = oRset3.getString(1);

				status = oRset3.getString(2);
				sco_id = oRset3.getString(3);
				LessonStatus = oRset3.getString(4);
				// System.out.println("=====status=====login_info====="+status);
				// System.out.println("=====sco_id=====login_info====="+sco_id);
				// System.out.println("=====LessonStatus=====login_info====="+LessonStatus);

				if (!status.equals(LessonStatus)) {

					c = 0;
					// System.out.println("====isPreUnitCompleted===c====="+c);
					break;
				} else {
					c = 1;
					// System.out.println("====isPreUnitCompleted===c====="+c);
				}

			}
			// System.out.println("select
			// a.sco_id,a.time_spent,b.Identifier,sum(((TO_DAYS(c.end_time)-To_DAYS(c.start_time))*24*3600+(time_to_sec(c.end_time)-time_to_sec(c.start_time))))/60
			// from unit_completion_sco a,ItemInfo b,sco_user_info c where
			// a.unit_id='"+u_id+"' and a.unit_id=b.CourseID and
			// a.unit_id=c.unit_id and a.sco_id=b.Identifier and
			// c.sco_id=b.Identifier and c.student_id='"+s_id+"' group by
			// c.sco_id");
			oRset4 = oStmt.executeQuery(
					"select a.sco_id,a.time_spent,b.identifier,sum(((TO_DAYS(c.end_time)-To_DAYS(c.start_time))*24*3600+(time_to_sec(c.end_time)-time_to_sec(c.start_time))))/60 from unit_completion_sco a,iteminfo b,sco_user_info c where a.unit_id='"
							+ u_id
							+ "' and a.unit_id=b.unit_id and a.unit_id=c.unit_id and a.sco_id=b.identifier and c.sco_id=b.identifier and c.student_id='"
							+ s_id + "' group by c.sco_id");
			while (oRset4.next()) {

				time_spent = oRset4.getString(2);
				tspent = Integer.parseInt(time_spent);
				// System.out.println("-----isPreUnitCompleted-----tspent-"+tspent);
				scotime = oRset4.getString(4);
				// System.out.println("-----isPreUnitCompleted-----scotime-"+scotime);
				if (scotime == null) {
					scotime = "0";

				} else {
					scotime = scotime.substring(0, scotime.indexOf('.'));
				}
				// System.out.println("----isPreUnitCompleted------scotime-"+scotime);
				scot = Integer.parseInt(scotime);
				// System.out.println("--isPreUnitCompleted---scot-----"+scot);

				if (scot < tspent) {

					q = 0;
					break;
				} else {
					q = 1;
				}

				// System.out.println("====isPreUnitCompleted===q====="+q);
			}

			System.out.println(
					"select a.sco_id,a.marks,b.Identifier,c.RawScore from unit_completion_sco a,iteminfo b,userscoinfo c where a.unit_id='"
							+ u_id
							+ "' and a.unit_id=b.unit_id and a.unit_id=c.unit_id and a.sco_id=b.identifier and c.sco_id=b.identifier and c.user_id='"
							+ s_id + "'");
			oRset5 = oStmt.executeQuery(
					"select a.sco_id,a.marks,b.identifier,c.rawscore from unit_completion_sco a,iteminfo b,userscoinfo c where a.unit_id='"
							+ u_id
							+ "' and a.unit_id=b.unit_id and a.unit_id=c.unit_id and a.sco_id=b.identifier and c.sco_id=b.identifier and c.user_id='"
							+ s_id + "'");
			while (oRset5.next()) {

				marks = oRset5.getString(2);
				commarks = Integer.parseInt(marks);

				RawScore = oRset5.getString(4);
				// System.out.println("====isPreUnitCompleted===RawScore====="+RawScore);
				if (RawScore == null) {
					RawScore = "0";

				}
				scomarks = Integer.parseInt(RawScore);

				if (scomarks < commarks) {

					m = 0;
					break;
				} else {
					m = 1;
				}

				// System.out.println("====isPreUnitCompleted===m====="+m);
			}

			oRset5.close();
			oRset4.close();
			oRset3.close();
			oRset2.close();
			oRset1.close();
			// oRset.close();
			// oStmt.close();

		} catch (SQLException e) {
			System.out.println("Error due to SQL exception : isPreUnitCompleted()");
			System.out.println("SQL exception : " + e.toString());
		} catch (Exception ex) {
			System.out.println("Error due to exception : isPreUnitCompleted()");
			ex.printStackTrace();
			System.out.println(" printStack is :: " + ex.getMessage());
		} finally {
			if (oConn != null) {
				try {
					oRset.close();
					oStmt.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		if (timeaccess == null) {
			timeaccess = "0";
		}

		if (time_vis == null) {
			time_vis = "0";
		}

		if (tottime == null) {
			tottime = "0";
		}

		int tvis = Integer.parseInt(time_vis);
		int linfo = Integer.parseInt(login_info);
		double ttime = Double.valueOf(tottime).doubleValue();
		// System.out.println("====isPreUnitCompleted====ttime======"+ttime);
		double taccess = Double.valueOf(timeaccess).doubleValue();
		// System.out.println("====isPreUnitCompleted=====taccess===="+taccess);
		// System.out.println("====isPreUnitCompleted=====tvis===="+tvis);
		// System.out.println("====linfo=====taccess====="+linfo);
		// System.out.println("====c=====isPreUnitCompleted====="+c);
		// System.out.println("====m=====isPreUnitCompleted====="+m);
		// System.out.println("====q=====isPreUnitCompleted====="+q);
		// if(tvis <= linfo){
		// System.out.println("====tvis <= linfo====");
		// }
		// if(ttime <= taccess){
		// System.out.println("====ttime <= taccess====");
		// }
		// if((c == 1) && (q == 1) && (m == 1)){
		// System.out.println("====(c == 1) && (q == 1) && (m == 1)====");
		// }

		if ((tvis <= linfo) && (ttime <= taccess) && (c == 1) && (q == 1) && (m == 1)) {
			flag = true;
		}
		// System.out.println("-isPreUnitCompleted----flag----"+flag);
		return flag;
	}

	//////////////////////////// end
	//////////////////////////// prerequesite////////////////////////////////////////////
	public static Vector getAssessmentInformation(String userid) {
		Vector vector = new Vector();
		Connection oConn = null;
		Statement statement = null;
		Statement statement1 = null;
		ResultSet resultset = null;
		ResultSet resultset1 = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			statement1 = oConn.createStatement();
			for (resultset = statement.executeQuery(
					"select a.title,a.assessment_id from assessment_management a , assessment_user_registration b where a.assessment_id=b.assessment_id and b.student_id='"
							+ userid + "'"); resultset.next(); vector.addElement(resultset.getString(2)))
				vector.addElement(resultset.getString(1));

			for (resultset1 = statement1.executeQuery(
					"select a.title,a.assessment_id from assessment_management a , assessment_group_registration b,student_group_association c where a.assessment_id=b.assessment_id and b.group_id=c.group_id and c.student_id='"
							+ userid + "'"); resultset1.next(); vector.addElement(resultset1.getString(2)))
				vector.addElement(resultset1.getString(1));

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					if (statement != null)
						statement.close();
					if (statement1 != null)
						statement1.close();
					if (resultset != null)
						resultset.close();
					if (resultset1 != null)
						resultset1.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return vector;
	}

	public static Vector getScoInformation(String userid) {
		Vector vector = new Vector();
		Connection oConn = null;
		Statement statement = null;
		Statement statement1 = null;
		ResultSet resultset = null;
		ResultSet resultset1 = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			statement1 = oConn.createStatement();
			resultset = statement.executeQuery(
					"select a.id,a.name,a.rsession from synchronous_collaboration a , sc_user_association b where a.id=b.id and b.user_id='"
							+ userid + "' and datediff(a.end,sysdate())>=0");
			while (resultset.next()) {

				vector.addElement(resultset.getString(1));
				vector.addElement(resultset.getString(2));
				vector.addElement(resultset.getString(3));
			}

			resultset1 = statement1.executeQuery(
					"select a.id,a.name,a.rsession from synchronous_collaboration a , sc_group_association b,student_group_association c where a.id=b.sc_id and b.group_id=c.group_id and c.student_id='"
							+ userid + "' and datediff(a.end,sysdate())>=0");
			while (resultset1.next()) {

				vector.addElement(resultset1.getString(1));
				vector.addElement(resultset1.getString(2));
				vector.addElement(resultset1.getString(3));
			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					if (statement != null)
						statement.close();
					if (statement1 != null)
						statement1.close();
					if (resultset != null)
						resultset.close();
					if (resultset1 != null)
						resultset1.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return vector;
	}

	public static Vector getCourseInformation(String userid) {
		Vector vector = new Vector();
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			// Statement statement1 = oConn.createStatement();
			for (resultset = statement.executeQuery(
					"select a.course_id,a.course_name from course_definition a , usergroup_course_registration b where a.course_id=b.course_id and b.student_id='"
							+ userid + "'"); resultset.next(); vector.addElement(resultset.getString(2)))
				vector.addElement(resultset.getString(1));

			// for(ResultSet resultset1 = statement1.executeQuery("select
			// a.id,a.name from synchronous_collaboration a ,
			// sc_group_association b,student_group_association c where
			// a.id=b.sc_id and b.group_id=c.group_id and
			// c.student_id='"+userid+"'"); resultset1.next();
			// vector.addElement(resultset1.getString(2)))
			// vector.addElement(resultset1.getString(1));

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					if (statement != null)
						statement.close();

					if (resultset != null)
						resultset.close();

					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return vector;
	}

	public static Vector getForumInformation(String userid) {
		Vector vector = new Vector();
		Connection oConn = null;
		Statement statement = null;
		Statement statement1 = null;
		ResultSet resultset = null;
		ResultSet resultset1 = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			statement1 = oConn.createStatement();
			for (resultset = statement.executeQuery(
					"select a.forum_id,a.forum_name from forum a , user_forum_association b where a.forum_id=b.forum_id and b.student_id='"
							+ userid + "'"); resultset.next(); vector.addElement(resultset.getString(2)))
				vector.addElement(resultset.getString(1));

			for (resultset1 = statement1.executeQuery(
					"select a.forum_id,a.forum_name from forum a , group_forum_association b,student_group_association c where a.forum_id=b.forum_id and b.group_id=c.group_id and c.student_id='"
							+ userid + "'"); resultset1.next(); vector.addElement(resultset1.getString(2)))
				vector.addElement(resultset1.getString(1));

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					if (statement != null)
						statement.close();
					if (statement1 != null)
						statement1.close();
					if (resultset != null)
						resultset.close();
					if (resultset1 != null)
						resultset1.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return vector;
	}

	/**************************************
	 * Course Accessed Valid or not
	 ************************************/

	public synchronized static boolean isCourseaccessDateValid(String u_id, String s_id) {

		Statement oStmt = null;

		ResultSet oRset5 = null;
		ResultSet oRset6 = null;
		boolean flag = false;

		String accdate = null;
		String acctodate = "0000-00-00";

		double dateacc = 0.0;
		double datetoacc = 0.0;

		String myFormatString = "HH:mm:ss";
		// SimpleDateFormat df = new SimpleDateFormat(myFormatString);

		Connection oConn = null;

		try {
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			System.out.println(
					"select date_format(access_allowed_till,\"%Y-%m-%d\") from usergroup_course_registration where course_id = '"
							+ u_id + "' and student_id='" + s_id + "'");
			oRset6 = oStmt.executeQuery(
					"select date_format(access_allowed_till,\"%Y-%m-%d\") from usergroup_course_registration where course_id = '"
							+ u_id + "' and student_id='" + s_id + "'");
			while (oRset6.next()) {

				acctodate = oRset6.getString(1);
				System.out.println("========acctodate1=====" + acctodate);
			}

			System.out.println("========acctodate=====" + acctodate);
			oRset5 = oStmt.executeQuery("select TO_DAYS('" + acctodate + "') - TO_DAYS(sysdate())");
			while (oRset5.next()) {

				int dayleft = oRset5.getInt(1);

				if (dayleft >= 0)
					accdate = "Currently Available";
				else
					accdate = "Date Over";

			}
			// oRset5.close();
			// oRset6.close();

		} catch (SQLException e) {
			log.debug("Error due to SQL exception : isCourseaccessDateValid()");
			log.debug("SQL exception : " + e.toString());
		} catch (Exception ex) {
			log.debug("Error due to exception : isCourseaccessDateValid()");
			ex.printStackTrace();
			log.debug(" printStack is :: " + ex.getMessage());
		} finally {
			if (oConn != null) {
				try {

					oRset5.close();
					oRset6.close();
					oStmt.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		log.debug("==============accdate=====" + accdate);

		if (accdate.equals("Date Over")) {
			flag = true;
		}
		// connMgr.freeConnection("mysql", oConn);
		return flag;
	}

	public synchronized static boolean isCourseaccesstimeValid(String u_id, String s_id) {

		Statement oStmt = null;
		Statement oStmt1 = null;
		ResultSet oRset5 = null;
		ResultSet oRset6 = null;
		boolean flag = false;

		String acctime = null;
		String acctotime = null;

		double timeacc = 0.0;
		double timetoacc = 0.0;

		String myFormatString = "HH:mm:ss";
		// SimpleDateFormat df = new SimpleDateFormat(myFormatString);

		Connection oConn = null;

		try {
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oStmt1 = oConn.createStatement();
			oRset6 = oStmt.executeQuery("select total_access_time from usergroup_course_registration where course_id='"
					+ u_id + "' and student_id='" + s_id + "' ");
			while (oRset6.next()) {

				acctotime = oRset6.getString(1);
				// log.debug("======timetoacc==="+acctotime);
				if (acctotime == null) {
					acctotime = "0";

				}

				timetoacc = Double.valueOf(acctotime).doubleValue();
				System.out.println("=====timetoacc==....................===" + timetoacc);

			}

			if (timetoacc != 0.0) {
				System.out.println(
						"select sum(((TO_DAYS(logout_datetime)-To_DAYS(login_datetime))*24*3600+(time_to_sec(logout_datetime)-time_to_sec(login_datetime)))/3600)  from course_learner_login_info where course_id='"
								+ u_id + "' and student_id='" + s_id + "' ");
				oRset5 = oStmt1.executeQuery(
						"select sum(((TO_DAYS(logout_datetime)-To_DAYS(login_datetime))*24*3600+(time_to_sec(logout_datetime)-time_to_sec(login_datetime)))/3600)  from course_learner_login_info where course_id='"
								+ u_id + "' and student_id='" + s_id + "' ");
				System.out.println("=======================================1");
				while (oRset5.next()) {
					System.out.println("=======================================2");

					acctime = oRset5.getString(1);

					if (acctime == null) {
						acctime = "0";
					}
					timeacc = Double.valueOf(acctime).doubleValue();
					System.out.println("====.........not equals..................=timeacc=====" + timeacc);

				}

			}

			System.out.println("========last======timeacc=====" + timeacc);
			System.out.println("========last======timetoacc=====" + timetoacc);
			if (timeacc > timetoacc) {
				flag = true;
			}
			System.out.println("flag = " + flag);
		} catch (SQLException e) {
			System.out.println("Error due to SQL exception : isPreUnitCompleted()" + e.getMessage());
			log.debug("SQL exception : " + e.toString());
		} catch (Exception ex) {
			log.debug("Error due to exception : isCourseaccesstimeValid()");
			ex.printStackTrace();
			log.debug(" printStack is :: " + ex.getMessage());
		} finally {
			if (oConn != null) {
				try {

					// oRset6.close();
					// oRset5.close();
					// oStmt.close();
					// oStmt1.close();
					if (oRset6 != null) {
						oRset6.close();
						oRset6 = null;
					}
					if (oStmt != null) {
						oStmt.close();
						oStmt = null;
					}
					if (oRset5 != null) {
						oRset5.close();
						oRset5 = null;
					}
					if (oStmt1 != null) {
						oStmt1.close();
						oStmt1 = null;
					}
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}

		// connMgr.freeConnection("mysql", oConn);
		System.out.println("return flag = " + flag);
		return flag;
	}

	public static synchronized void updateLogout(String student_id, String session_id, String strDate, String strTime) {
		Connection oConn = null;
		Statement statement = null;
		try {

			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			// System.out.println("update student_login_time set logged_out_at =
			// '" + strDate + " " + strTime + "' where session_id = '" +
			// session_id + "' and student_id = '" + student_id + "' ");
			statement.execute("update student_login_time set logged_out_at = '" + strDate + " " + strTime
					+ "' where session_id = '" + session_id + "' and student_id = '" + student_id + "' ");
			statement.execute("delete from user_login_time where session_id = '" + session_id + "' and student_id = '"
					+ student_id + "' ");
			// statement.close();

		} catch (SQLException sqlexception) {
			System.out.println("SQL error inside DataBaseLayer.updateCourseLoginInfo()");
			System.out.println("The error code is" + sqlexception.getErrorCode());
			System.out.println("The error message   updateLogout is" + sqlexception.getMessage());
		} catch (Exception exception) {
			System.out.println("General exception inside DataBaseLayer.updateCourseLoginInfo()");
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public synchronized static boolean isGroupExists(String s_id) {

		Statement oStmt = null;
		ResultSet oRset = null;
		// ResultSet oRset1;

		String group_id = "";
		boolean flag = false;
		Connection oConn = null;

		try {
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oRset = oStmt
					.executeQuery("select group_id from  student_group_association where student_id='" + s_id + "'");

			if (oRset.next()) {
				group_id = oRset.getString(1);

			}

			// oRset.close();
			// oStmt.close();

			if ((group_id == null) || (group_id.equals(""))) {
				flag = false;

			} else {
				flag = true;

			}

		} catch (SQLException e) {
			log.debug("Error due to SQL exception : isGroupExists()");
			System.out.println("SQL exception isGroupExists: " + e.toString());
		} catch (Exception ex) {
			log.debug("Error due to exception : isGroupExists()");
			ex.printStackTrace();
			log.debug(" printStack is :: " + ex.getMessage());
		} finally {
			if (oConn != null) {
				try {
					oRset.close();
					oStmt.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return flag;
	}

	public synchronized static boolean isTimeCompleted(String s_id) {

		Statement oStmt = null;
		ResultSet oRset = null;
		ResultSet oRset1 = null;

		boolean flag = false;
		String acctime = null;

		String time_vis = null;

		Double timeacc = 0.0;
		Double tvis = 0.0;
		Connection oConn = null;
		try {
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oRset = oStmt.executeQuery("select accesstime from  useraccessmanagement where user_id='" + s_id + "'");

			if (oRset.next()) {
				acctime = oRset.getString(1);

			}

			oRset1 = oStmt.executeQuery(
					"select sum(((TO_DAYS(logged_out_at)-To_DAYS(logged_in_at))*24*3600+(time_to_sec(logged_out_at)-time_to_sec(logged_in_at)))/3600) from student_login_time where student_id='"
							+ s_id
							+ "' and date_format(student_login_time.logged_out_at,'%y-%m-%d')=date_format(sysdate(),'%y-%m-%d') and date_format(student_login_time.logged_in_at,'%y-%m-%d')=date_format(sysdate(),'%y-%m-%d')");
			if (oRset1.next()) {
				time_vis = oRset1.getString(1);

			}

			if (acctime == null) {
				acctime = "0";

			}

			if (time_vis == null) {
				time_vis = "0";

			}

			timeacc = Double.valueOf(acctime).doubleValue();
			log.debug("====================== time permited ===========" + timeacc);
			tvis = Double.valueOf(time_vis).doubleValue();

			if (timeacc == 0.0) {
				flag = true;
			} else {

				if (tvis <= timeacc) {
					flag = true;
				}
			}

		} catch (SQLException e) {
			log.debug("Error due to SQL exception : isTimeCompleted()");
			System.out.println("SQL exception isTimeCompleted: " + e.toString());
		} catch (Exception ex) {
			log.debug("Error due to exception : isTimeCompleted()");
			ex.printStackTrace();
			log.debug(" printStack is :: " + ex.getMessage());
		} finally {
			if (oConn != null) {
				try {
					oRset.close();
					oRset1.close();
					oStmt.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return flag;
	}

	public synchronized static boolean isTimeCompletedgroup(String s_id) {

		Statement oStmt = null;
		Statement oStmt1 = null;
		// Statement oStmt2;
		Statement oStmt3 = null;
		ResultSet oRset = null;
		ResultSet oRset1 = null;

		ResultSet oRset3 = null;

		boolean flag = false;
		String acctime = null;

		String time_vis = null;

		Double timeacc = 0.0;
		Double tvis = 0.0;

		String group_id = "";
		Connection oConn = null;
		try {
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oStmt1 = oConn.createStatement();

			oStmt3 = oConn.createStatement();

			oRset = oStmt1
					.executeQuery("select group_id from student_group_association where student_id='" + s_id + "'");
			while (oRset.next()) {
				group_id = oRset.getString(1);

			}
			oRset3 = oStmt3
					.executeQuery("select accesstime from groupaccessmanagement where group_id='" + group_id + "'");
			while (oRset3.next()) {
				acctime = oRset3.getString(1);

			}

			oRset1 = oStmt.executeQuery(
					"select sum(((TO_DAYS(logged_out_at)-To_DAYS(logged_in_at))*24*3600+(time_to_sec(logged_out_at)-time_to_sec(logged_in_at)))/3600) from student_login_time where student_id='"
							+ s_id
							+ "' and date_format(student_login_time.logged_out_at,'%y-%m-%d')=date_format(sysdate(),'%y-%m-%d')");
			if (oRset1.next()) {
				time_vis = oRset1.getString(1);

			}

			if (acctime == null) {
				acctime = "0";

			}

			if (time_vis == null) {
				time_vis = "0";

			}

			timeacc = Double.valueOf(acctime).doubleValue();
			log.debug("====================== time permited ===========" + timeacc);
			tvis = Double.valueOf(time_vis).doubleValue();

			if (timeacc == 0.0) {
				flag = true;
			} else {

				if (tvis <= timeacc) {
					flag = true;
				}
			}

		} catch (SQLException e) {
			log.debug("Error due to SQL exception : isTimeCompletedgroup()");
			System.out.println("SQL exception isTimeCompletedgroup: " + e.toString());
		} catch (Exception ex) {
			log.debug("Error due to exception : isPreUnitCompleted()");
			ex.printStackTrace();
			log.debug(" printStack is :: " + ex.getMessage());
		} finally {
			if (oConn != null) {
				try {
					oRset.close();
					oRset1.close();
					oRset3.close();
					oStmt.close();
					oStmt1.close();
					oStmt3.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return flag;
	}

	/////////////////////// SHIBAJI ADD 18.03.2009////////////////////////

	public static synchronized void updateOldPassword(String user_id, String password) {

		Connection oConn = null;
		Statement statement = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			statement.execute(
					"update student_password set password = '" + password + "'  where student_id = '" + user_id + "' ");

		} catch (SQLException sqlexception) {
			System.out.println("SQL error inside DataBaseLayer.updateOldPassword()");
			System.out.println("The error code is" + sqlexception.getErrorCode());
			System.out.println("The error message is" + sqlexception.getMessage());
		} catch (Exception exception) {
			System.out.println("General exception inside DataBaseLayer.updateOldPassword()");
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	/*
	 * public static String getFirstLogin(String s) { String loginno = "";
	 * String status = ""; Connection oConn = null; Statement statement =null;
	 * ResultSet resultset =null; try { oConn = ds1.getConnection(); statement =
	 * oConn.createStatement(); resultset = statement.
	 * executeQuery("select password from student_password where student_id ='"
	 * + s + "'"); resultset.next(); loginno = resultset.getString(1);
	 * if(loginno.equals(s)) { status="same"; }
	 * 
	 * } catch(SQLException sqlexception) { System.out.
	 * println("Inside DataBaseLayer getFirstLogin(), the error message - " +
	 * sqlexception.getMessage()); } catch(Exception exception) {
	 * System.out.println("Inside DataBaseLayer getFirstLogin(), Exception !!!!"
	 * ); exception.printStackTrace(); } finally{ if(oConn!=null) { try{
	 * resultset.close(); statement.close(); oConn.close(); }catch(SQLException
	 * e){} } } return status; }
	 */
	public static String getFirstLogin(String s) {
		String loginno = "";
		String status = "";
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			resultset = statement.executeQuery("select password from student_password where student_id ='" + s + "'");
			resultset.next();
			loginno = resultset.getString(1);
			if (loginno.equals("cme123") || loginno.equals("cmestu123")) {
				status = "same";
			}

		} catch (SQLException sqlexception) {
			System.out
					.println("Inside DataBaseLayer getFirstLogin(), the error message - " + sqlexception.getMessage());
		} catch (Exception exception) {
			System.out.println("Inside DataBaseLayer getFirstLogin(), Exception !!!!");
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					resultset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return status;
	}

	public static synchronized String changePassword(String OldPassworddata, String NewPassworddata,
			String RetypePassworddata, String user_id) {

		Statement oStmt = null;
		Statement oStmt2 = null;
		Connection oConn = null;
		String oldpass = "";
		ResultSet rs = null;
		String val = "";
		try {
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oStmt2 = oConn.createStatement();

			rs = oStmt2.executeQuery("select password from student_password where student_id='" + user_id + "'");
			while (rs.next()) {
				oldpass = rs.getString(1);
			}
			System.out.println("----------oldpass-------------" + oldpass);
			if (OldPassworddata.equals(oldpass)) {
				System.out.println("----------password-------------update student_password set password='"
						+ NewPassworddata + "' where student_id='" + user_id + "'");
				oStmt.execute("update student_password set password='" + NewPassworddata + "' where student_id='"
						+ user_id + "'");

			} else {
				val = "no";
			}

		} catch (SQLException sqlexception) {
			System.out.println("changePassword()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			sqlexception.printStackTrace();
		} catch (Exception ex) {
			System.out.println("changePassword()");
			System.out.println("The Error Message - " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			if (oConn != null) {
				try {

					oStmt.close();
					oStmt2.close();
					oConn.close();
					rs.close();

				} catch (SQLException e) {
				}

			}

		}
		return val;
	}

	public static void delete_userlogin(String s, String sess) {
		Connection oConn = null;
		Statement statement = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			boolean flag = statement
					.execute("delete from user_login_time where student_id='" + s + "' and session_id='" + sess + "'");

		} catch (SQLException sqlexception) {
			// System.out.println("...............................................>>>>>>>>>>>>>>>>>>>>>................"+sqlexception.getMessage());
			System.out.println("The Error Message - " + sqlexception.getMessage());
			System.out.println("The Error Code    - " + sqlexception.getErrorCode());
			while ((sqlexception = sqlexception.getNextException()) != null) {
				System.out.println("The Error message - " + sqlexception.getMessage());
				System.out.println("The Error code is - " + sqlexception.getErrorCode());
			}
		} catch (Exception exception) {
			System.out.println("General exception in DataBaseLayer.deleteStudenLogin()");
			exception.printStackTrace();
			System.out.println("The message - " + exception.toString());
		} finally {
			if (oConn != null) {
				try {
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public static synchronized void getRowDynaInfo() {

		Statement oStmt = null;
		Statement oStmt2 = null;
		Statement oStmt3 = null;
		Connection oConn = null;
		ResultSet rs = null;
		try {
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oStmt2 = oConn.createStatement();
			oStmt3 = oConn.createStatement();
			boolean flag = oStmt3.execute("delete from dynamic_info");

			for (rs = oStmt2.executeQuery("select session_id,student_id,logged_in_at from user_login_time "); rs
					.next();) {
				String session_id = rs.getString(1);
				String s_id = rs.getString(2);
				String datetime = rs.getString(3);
				// System.out.println("insert into
				// dynamic_info(session_id,student_id,logged_in_at)values('"+session_id+"','"+s_id+"','"+datetime+"'");
				boolean flag1 = oStmt.execute("insert into dynamic_info(session_id,student_id,logged_in_at)values('"
						+ session_id + "','" + s_id + "','" + datetime + "')");
			}

		} catch (SQLException sqlexception) {
			System.out.println("getRowDynaInfo()");
			System.out.println("The Error Message - " + sqlexception.getMessage());
			sqlexception.printStackTrace();
		} catch (Exception ex) {
			System.out.println("getRowDynaInfo()");
			System.out.println("The Error Message - " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			if (oConn != null) {
				try {

					rs.close();
					oStmt.close();
					oStmt2.close();
					oConn.close();

				} catch (SQLException e) {
				}

			}

		}

	}

	public static Boolean isInstructor(String user_id, String course_id) {
		Statement statement = null;
		boolean flag = false;
		Connection oConn = null;
		ResultSet rs = null;
		try {
			oConn = ds1.getConnection();

			oConn.setAutoCommit(false);
			statement = oConn.createStatement();
			System.out.println("select course_id from course_instructor_association where user_id = '" + user_id
					+ "' and course_id = '" + course_id + "'");
			rs = statement.executeQuery("select course_id from course_instructor_association where user_id = '"
					+ user_id + "' and course_id = '" + course_id + "'");
			while (rs.next()) {
				flag = true;
				break;
			}
		} catch (SQLException sqlexception) {
			System.out.println("The Error in isInstructor:- " + sqlexception.getMessage());
		}

		finally {
			if (oConn != null) {
				try {
					statement.close();
					oConn.commit();
					oConn.setAutoCommit(true);
					oConn.close();
					rs.close();
				} catch (SQLException e) {
				}

			}

		}
		return flag;
	}

	/****************************************
	 * Partha 0n 06.02.10
	 *************************/
	public static String checkSelf(String student_id) {
		String s1 = "";
		Connection oConn = null;
		Statement statement = null;
		Statement statement3 = null;
		ResultSet resultset = null;
		ResultSet resultset2 = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			statement3 = oConn.createStatement();
			for (resultset = statement.executeQuery(
					"select strself from student_details where  student_id ='" + student_id + "'"); resultset.next();)
				s1 = resultset.getString(1);
			if (s1 == null)
				s1 = "F";

			if (s1.equals("F")) {

				resultset2 = statement3.executeQuery(
						"select self_regis from student_group_association a, student_group b where  student_id ='"
								+ student_id + "' and a.group_id=b.group_id");

				while (resultset2.next()) {
					s1 = resultset2.getString(1); // Alphabetic first will be
													// returned
					return s1;
					// break;
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
		} catch (Exception exception) {

			exception.printStackTrace();

		}
		// connMgr.freeConnection("mysql", oConn);
		finally {
			try {
				if (oConn != null) {
					if (resultset != null)
						resultset.close();
					if (resultset2 != null)
						resultset2.close();
					if (statement3 != null)
						statement3.close();
					if (statement != null)
						statement.close();
					oConn.close();
				}
			} catch (Exception e) {
			}
		}
		return s1;
	}

	public synchronized static String checkConfirmation(String unit_id) {

		Statement oStmt = null;
		ResultSet oRset = null;
		Connection oConn = null;
		String flag = null;

		try {
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oRset = oStmt.executeQuery(
					"select ifnull(confirmation_reqd,\"null\") from unit_creation_details where unit_id = '" + unit_id
							+ "'");
			if (oRset.next()) {
				flag = oRset.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (oConn != null) {
					if (oRset != null)
						oRset.close();
					if (oStmt != null)
						oStmt.close();
					oConn.close();
				}
			} catch (Exception e) {
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return flag;
	}

	public synchronized static boolean isManifestExists(String unit_id) {

		Statement oStmt = null;
		ResultSet oRset = null;
		Connection oConn = null;
		boolean flag = false;

		try {
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oRset = oStmt.executeQuery("select * from csformat where unit_id='" + unit_id + "'");
			if (oRset.next()) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			try {
				if (oConn != null) {
					if (oRset != null)
						oRset.close();
					if (oStmt != null)
						oStmt.close();
					oConn.close();
				}

			} catch (Exception e) {
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return flag;
	}

	public synchronized static String sentForConfirmation(String user_id, String unit_id) {
		String msg = "";
		Statement oStmt = null;
		Connection oConn = null;

		try {
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			// if (strTotalTime.equals("") && strValidTill.equals("")) {
			oStmt.executeUpdate("insert into confirmation_requisition (id, user_id, "
					+ "access_allowed_till, total_access_time,status,entity_type) values ('" + unit_id + "', '"
					+ user_id + "',null,null,'Pending','unit')");
			msg = "Requisition sent to administrator";
			// }
			// if ((strTotalTime.equals("")) && (!strValidTill.equals(""))) {
			// oStmt.executeUpdate("insert into confirmation_requisition (id,
			// user_id, "+
			// "access_allowed_till, total_access_time, status,entity_type)
			// values
			// ('"+strCourseId+"','"+strUsrId+"','"+strValidTill+"',null,'Pending','U')");
			// }
			// if ((!strTotalTime.equals("")) && (strValidTill.equals(""))) {
			// int iTotalTime = Integer.parseInt(strTotalTime);
			// oStmt.executeUpdate("insert into confirmation_requisition
			// (id,user_id, "+
			// "access_allowed_till, total_access_time, status,entity_type)
			// values
			// ('"+strCourseId+"','"+strUsrId+"',null,"+iTotalTime+",'Pending','U')");
			// }
			// if ((!strTotalTime.equals("")) && (!strValidTill.equals(""))) {
			// int iTotalTime = Integer.parseInt(strTotalTime);
			// oStmt.executeUpdate("insert into confirmation_requisition (id,
			// user_id, "+
			// "access_allowed_till, total_access_time, status,entity_type)
			// values
			// ('"+strCourseId+"','"+strUsrId+"','"+strValidTill+"',"+iTotalTime+",'Pending','U')");
			// }

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			try {
				if (oConn != null) {
					if (oStmt != null)
						oStmt.close();
					oConn.close();
				}
			} catch (Exception e) {
			}
		}
		//// connMgr.freeConnection("mysql", oConn);
		return msg;

	}

	public synchronized static String insertCourseUserAssociationSelf(String user_id, String unit_id) {
		String msg = "";
		Statement oStmt = null;
		Statement oStmt1 = null;
		ResultSet oRset = null;
		Connection oConn = null;

		try {
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oStmt1 = oConn.createStatement();
			oRset = oStmt.executeQuery("select * from unit_student_association where student_id = '" + user_id
					+ "' and unit_id = '" + unit_id + "'");
			if (oRset.next()) {
				msg = "Already Registered";
			}

			else {

				// if (strTotalTime.equals("") && strValidTill.equals("")) {
				oStmt1.executeUpdate("insert into unit_student_association (student_id, unit_id, date_registration, "
						+ "registered_by, access_allowed_till, total_access_time) values ('" + user_id + "','" + unit_id
						+ "',sysdate(),null,null,null)");
				msg = "Successfully Registered";
				// }
				// if ((strTotalTime.equals("")) && (!strValidTill.equals("")))
				// {
				// count=oStmt.executeUpdate("insert into
				// course_student_association (student_id, course_id,
				// date_registration, "+
				// "registered_by, access_allowed_till, total_access_time)
				// values ('"+strUsrId+"','"+strCourseId+
				// "',sysdate(),null,'"+strValidTill+"',null)");
				// }
				// if ((!strTotalTime.equals("")) && (strValidTill.equals("")))
				// {
				// int iTotalTime = Integer.parseInt(strTotalTime);
				// count=oStmt.executeUpdate("insert into
				// course_student_association (student_id, course_id,
				// date_registration, "+
				// "registered_by, access_allowed_till, total_access_time)
				// values ('"+strUsrId+"','"+strCourseId+
				// "',sysdate(),null,null,"+iTotalTime+")");
				// }
				// if ((!strTotalTime.equals("")) && (!strValidTill.equals("")))
				// {
				// int iTotalTime = Integer.parseInt(strTotalTime);
				// count=oStmt.executeUpdate("insert into
				// course_student_association (student_id, course_id,
				// date_registration, "+
				// "registered_by, access_allowed_till, total_access_time)
				// values ('"+strUsrId+"','"+strCourseId+
				// "',sysdate(),null,'"+strValidTill+"',"+iTotalTime+")");
				// }
			}

		} catch (SQLException e) {
			log.debug("Error due to SQL exception : " + e.getMessage());
			// int errCode=e.getErrorCode();
			// String errMessage = e.getMessage();
		} catch (Exception ex) {
			log.debug(" error due to java.lang.exception");
			ex.printStackTrace();
			log.debug(" printStack is :: " + ex.getMessage());
		} finally {
			try {
				if (oConn != null) {
					if (oStmt != null)
						oStmt.close();
					if (oStmt1 != null)
						oStmt1.close();
					if (oRset != null)
						oRset.close();
					oConn.close();
				}
			} catch (Exception e) {
			}
		}
		return msg;
	}

	public synchronized static String deleteCourseUserAssociation(String strUsrId, String strCourseId) {
		String msg = "";
		Statement oStmt = null;
		Statement oStmt1 = null;
		ResultSet oRset = null;
		Connection oConn = null;

		try {
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oStmt1 = oConn.createStatement();
			oRset = oStmt.executeQuery("select * from unit_student_association where student_id = '" + strUsrId
					+ "' and unit_id = '" + strCourseId + "'");
			if (oRset.next()) {
				oStmt1.execute("delete from unit_student_association where student_id = '" + strUsrId
						+ "' and unit_id = '" + strCourseId + "'");

				msg = "Selected Unit Unregistered";
			}
			oStmt1.execute("delete from confirmation_requisition where user_id = '" + strUsrId + "' and id = '"
					+ strCourseId + "' and entity_type='unit'");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (oConn != null) {
					if (oStmt != null)
						oStmt.close();
					if (oStmt1 != null)
						oStmt1.close();
					if (oRset != null)
						oRset.close();
					oConn.close();
				}
			} catch (Exception e) {
			}
		}

		return msg;
	}

	/************************************
	 * End of Partha on 06.02.10
	 *******************/
	/*************************** Partha 0n 22.02.10 ***********************/

	public synchronized static boolean isAccessTimeCompleted(String s_id) {

		Statement oStmt = null;
		ResultSet oRset = null;
		ResultSet oRset1 = null;
		Connection oConn = null;

		boolean flag = false;
		String acctime = null;

		String time_vis = null;

		Double timeacc = 0.0;
		Double tvis = 0.0;

		try {
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oRset = oStmt.executeQuery("select accesstime from  useraccessmanagement where user_id='" + s_id + "'");

			if (oRset.next()) {
				acctime = oRset.getString(1);

			}

			oRset1 = oStmt.executeQuery(
					"select sum(((TO_DAYS(logged_out_at)-To_DAYS(logged_in_at))*24*3600+(time_to_sec(logged_out_at)-time_to_sec(logged_in_at)))/3600) from student_login_time where student_id='"
							+ s_id
							+ "' and date_format(student_login_time.logged_out_at,'%y-%m-%d')=date_format(sysdate(),'%y-%m-%d') and date_format(student_login_time.logged_in_at,'%y-%m-%d')=date_format(sysdate(),'%y-%m-%d')");
			if (oRset1.next()) {
				time_vis = oRset1.getString(1);

			}

			if (acctime == null) {
				acctime = "0";

			}

			if (time_vis == null) {
				time_vis = "0";

			}

			timeacc = Double.valueOf(acctime).doubleValue();
			log.debug("====================== time permited ===========" + timeacc);
			tvis = Double.valueOf(time_vis).doubleValue();

			if (timeacc == 0.0) {
				flag = true;
			} else {

				if (tvis <= timeacc) {
					flag = true;
				}
			}
			oConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			try {
				if (oStmt != null)
					oStmt.close();
				if (oRset != null)
					oRset.close();
				if (oRset1 != null)
					oRset1.close();
				if (oConn != null)
					oConn.close();
			} catch (Exception e) {
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return flag;
	}

	public static String getMailServerTitle() {

		String s1 = null;
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();

			for (resultset = statement.executeQuery(
					"select title from mail_server_configuration where status='Active'"); resultset.next();)
				s1 = resultset.getString(1);

		} catch (SQLException sqlexception) {

			sqlexception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {

			if (oConn != null) {
				try {
					if (resultset != null)
						resultset.close();
					if (statement != null)
						statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return s1;
	}

	public static String getPassword(String student_id) {

		String s1 = "";
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();

			for (resultset = statement.executeQuery(
					"select password from student_password where student_id='" + student_id + "'"); resultset.next();)
				s1 = resultset.getString(1);

			if (s1 == null)
				s1 = "";

		} catch (SQLException sqlexception) {

			sqlexception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {

			if (oConn != null) {
				try {
					if (resultset != null)
						resultset.close();
					if (statement != null)
						statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return s1;
	}

	public static String getMailId(String s) {

		String s1 = null;
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();

			for (resultset = statement.executeQuery(
					"select email_id from student_details where student_id='" + s + "'"); resultset.next();)
				s1 = resultset.getString(1);

		} catch (SQLException sqlexception) {
			int i = sqlexception.getErrorCode();
			log.fatal("Error due to SQL exception inside NewDataBaseLayer.getMailId() : " + sqlexception.getMessage());
			log.dbe(DebugLevel.L1_FATAL, sqlexception);
		} catch (Exception exception) {
			log.fatal("error due to java.lang.exception");
			log.dbe(DebugLevel.L1_FATAL, exception);
			log.fatal(" printStack is :: " + exception.getMessage());
		} finally {

			if (oConn != null) {
				try {
					if (resultset != null)
						resultset.close();
					if (statement != null)
						statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return s1;
	}

	/*************************** End of Partha on 22.02.10 ******************/

	/*********************************** Partha on 16.03.10 *******************/
	public static Vector getDepartments(String user) {
		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Vector vCollectionList = new Vector();
		String str[] = new String[2];
		Connection oConn = null;
		Statement statement = null;
		ResultSet oRset = null;
		try {

			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			String sql = "select distinct a.dept_id,b.dept_name from dept_course_association a,department_master b,user_course_registration c where a.dept_id=b.dept_id and c.course_id=a.course_id and c.student_id='"
					+ user + "'";
			System.out.println("#############sql###############" + sql);
			oRset = statement.executeQuery(sql);
			while (oRset.next()) {

				str = new String[2];
				str[0] = oRset.getString(1);
				str[1] = oRset.getString(2);

				System.out.println("==str[0]== " + str[0] + " ==str[1]== " + str[1]);
				vCollectionList.addElement(str);
			}
			System.out.println("THE VECTOR in db  ::" + vCollectionList + " size " + vCollectionList.size());

		} catch (SQLException sqlexception) {
			System.out.println("SQLException in getCollections " + sqlexception);
		}

		catch (Exception exception) {
			System.out.println("Exception in getCollections " + exception);
		} finally {
			if (oConn != null) {
				try {
					oRset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return vCollectionList;
	}

	public static Vector getCollectionsFromDept(String user, String dept_id) {
		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Vector vCollectionList = new Vector();
		String str[] = new String[2];
		Connection oConn = null;
		Statement statement = null;
		ResultSet oRset = null;
		try {

			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			String sql = "select distinct a.collection_id,a.collection_name from course_collection_mgmt a,course_collection_asso b,user_course_registration c,dept_course_association d where a.collection_id=b.collection_id and c.course_id=b.course_id and b.course_id=d.course_id and c.student_id='"
					+ user + "' and d.dept_id='" + dept_id + "'";
			System.out.println("#############sql###############" + sql);
			oRset = statement.executeQuery(sql);
			while (oRset.next()) {

				str = new String[2];
				str[0] = oRset.getString(1);
				str[1] = oRset.getString(2);

				System.out.println("==str[0]== " + str[0] + " ==str[1]== " + str[1]);
				vCollectionList.addElement(str);
			}
			System.out.println("THE VECTOR in db  ::" + vCollectionList + " size " + vCollectionList.size());

		} catch (SQLException sqlexception) {
			System.out.println("SQLException in getCollections " + sqlexception);
		}

		catch (Exception exception) {
			System.out.println("Exception in getCollections " + exception);
		} finally {
			if (oConn != null) {
				try {
					oRset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return vCollectionList;
	}

	/*********************************
	 * end of Partha on 16.03.10
	 *****************/
	/*************************
	 * Start soma for Forum Self registration
	 **********/

	public synchronized static String checkforumConfirmation(String forum_id) {

		Statement oStmt = null;
		ResultSet oRset = null;
		Connection oConn = null;
		String flag = null;

		try {
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oRset = oStmt.executeQuery(
					"select ifnull(confirmation_reqd,\"null\") from forum where forum_id = '" + forum_id + "'");
			if (oRset.next()) {
				flag = oRset.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (oConn != null) {
					if (oRset != null)
						oRset.close();
					if (oStmt != null)
						oStmt.close();
					oConn.close();
				}
			} catch (Exception e) {
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		return flag;
	}

	public synchronized static String sentForForumConfirmation(String user_id, String forum_id) {
		String msg = "";
		Statement oStmt = null;
		Connection oConn = null;

		try {
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oStmt.executeUpdate("insert into confirmation_requisition (id, user_id, "
					+ "access_allowed_till, total_access_time,status,entity_type) values ('" + forum_id + "', '"
					+ user_id + "',null,null,'Pending','forum')");
			msg = "Requisition sent to administrator";

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			try {
				if (oConn != null) {
					if (oStmt != null)
						oStmt.close();
					oConn.close();
				}
			} catch (Exception e) {
			}
		}
		return msg;

	}

	public synchronized static String insertForumUserAssociationSelf(String user_id, String forum_id) {
		String msg = "";
		Statement oStmt = null;
		Statement oStmt1 = null;
		ResultSet oRset = null;
		Connection oConn = null;

		try {
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oStmt1 = oConn.createStatement();
			System.out.println("select * from user_forum_association where student_id = '" + user_id
					+ "' and forum_id = '" + forum_id + "'");
			oRset = oStmt.executeQuery("select * from user_forum_association where student_id = '" + user_id
					+ "' and forum_id = '" + forum_id + "'");
			if (oRset.next()) {
				msg = "Already Registered";
			}

			else {

				oStmt1.executeUpdate("insert into user_forum_association (student_id, forum_id,registered_by) values ('"
						+ user_id + "','" + forum_id + "',null)");
				msg = "Successfully Registered";

			}

		} catch (SQLException e) {
			log.debug("Error due to SQL exception : " + e.getMessage());

		} catch (Exception ex) {
			log.debug(" error due to java.lang.exception");
			ex.printStackTrace();
			log.debug(" printStack is :: " + ex.getMessage());
		} finally {
			try {
				if (oConn != null) {
					if (oStmt != null)
						oStmt.close();
					if (oStmt1 != null)
						oStmt1.close();
					if (oRset != null)
						oRset.close();
					oConn.close();
				}
			} catch (Exception e) {
			}
		}
		return msg;
	}

	public synchronized static String deleteForumUserAssociation(String strUsrId, String strForumId) {
		String msg = "";
		Statement oStmt = null;
		Statement oStmt1 = null;
		ResultSet oRset = null;
		Connection oConn = null;

		try {
			oConn = ds1.getConnection();
			oStmt = oConn.createStatement();
			oStmt1 = oConn.createStatement();
			oRset = oStmt.executeQuery("select * from user_forum_association where student_id = '" + strUsrId
					+ "' and forum_id = '" + strForumId + "'");
			if (oRset.next()) {
				oStmt1.execute("delete from user_forum_association where student_id = '" + strUsrId
						+ "' and forum_id = '" + strForumId + "'");

				msg = "Selected Unit Unregistered";
			}
			oStmt1.execute("delete from confirmation_requisition where user_id = '" + strUsrId + "' and id = '"
					+ strForumId + "' and entity_type='forum'");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (oConn != null) {
					if (oStmt != null)
						oStmt.close();
					if (oStmt1 != null)
						oStmt1.close();
					if (oRset != null)
						oRset.close();
					oConn.close();
				}
			} catch (Exception e) {
			}
		}

		return msg;
	}

	/****************************** SHIBAJI ******************************/
	public static String[] getnewLoginWelcome(String s) {
		String s1 = null;
		Object obj = null;
		String s3 = null;
		Object obj1 = null;
		// ***************************SHIBAJI
		// CHATTERJEE**********************************//
		String[] Details = new String[2];

		String baseName = "";
		int flag = 0;
		// DBConnectionManager connMgr = DBConnectionManager.getInstance();
		// Connection oConn = connMgr.getConnection("mysql");
		Connection oConn = null;
		Statement statement1 = null;
		ResultSet resultset1 = null;
		try {
			oConn = ds1.getConnection();
			statement1 = oConn.createStatement();
			resultset1 = statement1.executeQuery(
					"select account_status , concat(first_name,' ',middle_name,' ',sname) from student_details where student_id ='"
							+ s + "'");
			if (resultset1.next()) {

				String s4 = resultset1.getString(1);
				String s2 = resultset1.getString(2);
				if (s4.equalsIgnoreCase("active")) {
					Statement statement = oConn.createStatement();
					ResultSet resultset = statement
							.executeQuery("select count(*) from student_login_time where student_id ='" + s + "'");
					resultset.next();
					int i = resultset.getInt(1);
					resultset.close();
					if (i > 0) {
						resultset = statement.executeQuery(
								"select date_format(max(logged_in_at),\"%d-%m-%Y %r\") from student_login_time where student_id ='"
										+ s + "'");
						resultset.next();
						s3 = resultset.getString(1);
					}
					resultset.close();
					statement.close();

					if (i == 0)
						Details[0] = "Welcome Back  <b>" + s2 + "</b>";
					Details[1] = "";

					if (i > 0)
						Details[0] = "Welcome Back  <b>" + s2 + "</b>";
					Details[1] = "Last login " + s3;
				} else {
					Details[0] = s2;
					Details[1] = "! Your account has been deactivated. Please contact your LearnITy administrator.";
				}
			}
		} catch (NumberFormatException numberformatexception) {
			System.out.println("NumberFormatException excepiton");
		} catch (SQLException sqlexception) {
			System.out.println("SQL error inside DataBaseLayer.getLoginWelcome()");
			System.out.println("sql exception" + sqlexception.toString());
		} catch (Exception exception) {
			System.out.println("general exception" + exception.toString());
		} finally {
			if (oConn != null) {
				try {
					resultset1.close();
					statement1.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}

		// connMgr.freeConnection("mysql", oConn);
		return Details;
	}

	/*******************************************
	 * Surajit for Corporate user
	 ********************/
	public static Vector getCourseList(String user) {
		Connection oConn = null;
		Vector vCourseList = new Vector();
		Statement statement = null;
		Statement statement1 = null;
		ResultSet oRset = null;
		ResultSet oRset1 = null;
		try {

			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			System.out.println(
					"SELECT p.course_id, c.course_name, ple.date,if(p.course_study='M','Mandatory','Optional') FROM program_course_association p, plan_program_association pl, plan_employee_association ple, course_definition c WHERE pl.program_id=p.program_id AND ple.plan_id=pl.plan_id AND ple.emp_id='"
							+ user + "' AND c.course_id=p.course_id");
			oRset = statement.executeQuery(
					"SELECT p.course_id, c.course_name, ple.date,if(p.course_study='M','Mandatory','Optional') FROM program_course_association p, plan_program_association pl, plan_employee_association ple, course_definition c WHERE pl.program_id=p.program_id AND ple.plan_id=pl.plan_id AND ple.emp_id='"
							+ user + "' AND c.course_id=p.course_id");
			while (oRset.next()) {
				String str[] = new String[4];
				str[0] = oRset.getString(1);
				str[1] = oRset.getString(2);
				str[2] = oRset.getString(3);
				str[3] = oRset.getString(4);
				vCourseList.addElement(str);

			}
			statement1 = oConn.createStatement();
			// System.out.println("SELECT p.course_id, c.course_name,
			// ple.date,if(p.course_study='M','Mandatory','Optional') FROM
			// program_course_association p, plan_program_association pl,
			// plan_group_association ple, course_definition c,
			// student_group_association s WHERE pl.program_id=p.program_id AND
			// ple.plan_id=pl.plan_id AND s.student_id='"+user+"' AND
			// c.course_id=p.course_id AND s.group_id=ple.group_id group by
			// p.course_id");
			System.out.println(
					"SELECT p.course_id, c.course_name, ple.date,if(p.course_study='M','Mandatory','Optional') FROM program_course_association p, plan_program_association pl, plan_group_association ple, course_definition c, student_group_association s WHERE pl.program_id=p.program_id AND ple.plan_id=pl.plan_id AND s.student_id='"
							+ user
							+ "' AND c.course_id=p.course_id AND s.group_id=ple.group_id and c.course_id not in (SELECT p.course_id FROM program_course_association p, plan_program_association pl, plan_employee_association ple, course_definition c WHERE pl.program_id=p.program_id AND ple.plan_id=pl.plan_id  AND c.course_id=p.course_id and ple.emp_id='"
							+ user + "') group by p.course_id");
			oRset1 = statement.executeQuery(
					"SELECT p.course_id, c.course_name, ple.date,if(p.course_study='M','Mandatory','Optional') FROM program_course_association p, plan_program_association pl, plan_group_association ple, course_definition c, student_group_association s WHERE pl.program_id=p.program_id AND ple.plan_id=pl.plan_id AND s.student_id='"
							+ user
							+ "' AND c.course_id=p.course_id AND s.group_id=ple.group_id and c.course_id not in (SELECT p.course_id FROM program_course_association p, plan_program_association pl, plan_employee_association ple, course_definition c WHERE pl.program_id=p.program_id AND ple.plan_id=pl.plan_id  AND c.course_id=p.course_id and ple.emp_id='"
							+ user + "') group by p.course_id");
			while (oRset1.next()) {
				String str[] = new String[4];
				str[0] = oRset1.getString(1);
				str[1] = oRset1.getString(2);
				str[2] = oRset1.getString(3);
				str[3] = oRset1.getString(4);
				vCourseList.addElement(str);

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					oRset.close();
					oRset1.close();
					statement.close();
					statement1.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}

		return vCourseList;
	}

	////////////////////// SJIBAJI ADD FOR PROGRAME/////////////////////

	public static Vector ProgramList(String user) {
		Connection oConn = null;
		Vector vProgrameList = new Vector();
		Statement statement = null;
		Statement statement1 = null;
		ResultSet oRset = null;
		ResultSet oRset1 = null;
		try {

			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			System.out.println(
					"select a.program_id,a.program_name,a.type,b.startdate,b.enddate from program_instance_management a,program_schedule_management b ,plan_program_association c,plan_employee_association  d where a.program_id=b.program_id and a.program_id=c.program_id and c.plan_id=d.plan_id and d.emp_id='"
							+ user + "'");
			oRset = statement.executeQuery(
					"select a.program_id,a.program_name,a.type,b.startdate,b.enddate from program_instance_management a,program_schedule_management b ,plan_program_association c,plan_employee_association  d where a.program_id=b.program_id and a.program_id=c.program_id and c.plan_id=d.plan_id and d.emp_id='"
							+ user + "'");
			while (oRset.next()) {

				vProgrameList.addElement(oRset.getString(1));
				vProgrameList.addElement(oRset.getString(2));
				vProgrameList.addElement(oRset.getString(3));
				vProgrameList.addElement(oRset.getString(4));
				vProgrameList.addElement(oRset.getString(5));

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					oRset.close();
					// oRset1.close();
					statement.close();
					// statement1.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}

		return vProgrameList;
	}

	public static Vector getSurveyByPrograme(String program_id) {
		Connection oConn = null;
		Vector vSurveyList = new Vector();
		Statement statement = null;
		Statement statement1 = null;
		ResultSet oRset = null;
		ResultSet oRset1 = null;
		try {

			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			oRset = statement.executeQuery(
					"select a.survey_item_id,a.survey_type,a.survey_item_text from survey_definition a,survey_program_association b where a.survey_id=b.survey_id and b.program_id='"
							+ program_id + "'");
			while (oRset.next()) {

				vSurveyList.addElement(oRset.getString(1));
				vSurveyList.addElement(oRset.getString(2));
				vSurveyList.addElement(oRset.getString(3));

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					oRset.close();
					// oRset1.close();
					statement.close();
					// statement1.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}

		return vSurveyList;
	}

	public static String getProgrameName(String program_id) {

		String program_name = null;
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();

			for (resultset = statement
					.executeQuery("select a.program_name from program_instance_management a where a.program_id='"
							+ program_id + "'"); resultset.next();)
				program_name = resultset.getString(1);

		} catch (SQLException sqlexception) {

			sqlexception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {

			if (oConn != null) {
				try {
					if (resultset != null)
						resultset.close();
					if (statement != null)
						statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return program_name;
	}

	public static String getSurveyProgramID(String program_id) {

		String servey_program_id = null;
		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			oConn = ds1.getConnection();
			statement = oConn.createStatement();

			for (resultset = statement.executeQuery(
					"select a.survey_program_association_id from survey_program_association a where a.program_id='"
							+ program_id + "'"); resultset.next();)
				servey_program_id = resultset.getString(1);

		} catch (SQLException sqlexception) {

			sqlexception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {

			if (oConn != null) {
				try {
					if (resultset != null)
						resultset.close();
					if (statement != null)
						statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		return servey_program_id;
	}

	/**************************************
	 * Partha on 16.01.2012
	 ******************************/

	public static Vector getTrainingProgramInfo(String training_program_id, String s) {
		Vector vector = new Vector(10, 10);
		Object obj = null;
		String s11 = null;
		String s12 = null;
		String s13 = null;
		String s14 = null;
		String s15 = null;
		String s16 = null;
		String s17 = null;
		Object obj1 = null;
		Object obj2 = null;
		Object obj3 = null;
		Object obj4 = null;
		int i = 0;

		Connection oConn = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {

			oConn = ds1.getConnection();
			statement = oConn.createStatement();
			// ResultSet resultset;
			for (resultset = statement.executeQuery(
					"select sc.unit_id from training_program_unit_association sc where sc.training_program_id = '"
							+ training_program_id + "'"); resultset.next();) {
				String s1 = resultset.getString(1);// course_id
				// System.out.println("course_id ="+s1);

				Statement statement1 = oConn.createStatement();
				/// System.out.println("select course_name from course where
				/// course_id = '" + s1 + "'");
				ResultSet resultset2 = statement1
						.executeQuery("select unit_name from unit_details where unit_id = '" + s1 + "'");
				String s3 = "";
				while (resultset2.next()) {
					s3 = resultset2.getString(1);// course_name
				}
				resultset2 = statement1.executeQuery(
						"select coalesce(sum(if(logout_datetime>=login_datetime,(to_days(logout_datetime)-to_days(login_datetime))*86400,0) + if(time_to_sec(logout_datetime)>= time_to_sec(login_datetime),time_to_sec(logout_datetime)-time_to_sec(login_datetime),(86400+time_to_sec(logout_datetime))-time_to_sec(login_datetime)))/3600,0) from learner_login_info where student_id = '"
								+ s + "' and unit_id = '" + s1 + "'");
				resultset2.next();
				String s9 = resultset2.getString(1); // time
				if (!s9.equals("0")) {
					resultset2 = statement1.executeQuery(
							"select min(login_datetime) , max(login_datetime) from learner_login_info where student_id = '"
									+ s + "' and unit_id = '" + s1 + "'");
					if (resultset2.next()) {
						s11 = resultset2.getString(1); // min(login_datetime)
						s12 = resultset2.getString(2);// max(login_datetime)
						System.out.println("s12 =====1====" + s12);
					}
					// resultset2 = statement1.executeQuery("select topic_id
					// ,topicname, autype, auname from learner_login_info where
					// login_datetime = '" + s12 + "' and course_id = '" + s1 +
					// "' and student_id = '" + s + "' and topic_id is not
					// null");
					resultset2 = statement1
							.executeQuery("select topic_id  from learner_login_info where login_datetime = '" + s12
									+ "' and unit_id = '" + s1 + "' and student_id = '" + s
									+ "' and topic_id is not null");
					if (resultset2.next())
						s13 = resultset2.getString(1);// topic_id

				}
				// resultset2 = statement1.executeQuery("select if(coalesce('" +
				// s5 + "','9999-12-31 23:59:59') < sysdate(),'Date
				// Over','Currently Available')");

				if (s12 == null) {
					s12 = "Not Accessed Yet!";
				} else {
					resultset2 = statement1.executeQuery("select date_format('" + s12 + "',\"%D %M %Y %r\")");
					resultset2.next();
					s12 = resultset2.getString(1);
					System.out.println("s12 =====2====" + s12);
				}
				resultset2.close();
				statement1.close();
				vector.addElement(s1);// course_id
				vector.addElement(s3);// course_name
				vector.addElement(s12);// max(login_datetime)
				System.out.println("s12------------3----------- " + s12);
				vector.addElement(s13);// topic_id

				System.out.println("vector 1" + vector);
				s3 = null;

				s11 = null;
				s12 = null;
				s13 = null;
				s14 = null;
				s15 = null;
				s16 = null;

				s9 = null;

			}

			s11 = null;
			s12 = null;
			s13 = null;
			s14 = null;
			s15 = null;
			s16 = null;

			// statement.close();
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			System.out.println("SQL Error inside DataBaseLayer.getLoginInfo()");
			System.out.println("The Error message is" + sqlexception.getMessage());
			System.out.println("The Error code is" + sqlexception.getErrorCode());
			while ((sqlexception = sqlexception.getNextException()) != null) {
				System.out.println("The Error message - " + sqlexception.getMessage());
				System.out.println("The Error code is - " + sqlexception.getErrorCode());
			}
		} catch (Exception exception) {
			System.out.println("Inside DataBaseLayer, getLoginInfo() Exception!!!!");
			exception.printStackTrace();
		} finally {
			if (oConn != null) {
				try {
					resultset.close();
					statement.close();
					oConn.close();
				} catch (SQLException e) {
				}
			}
		}
		// connMgr.freeConnection("mysql", oConn);
		// System.out.println("1111111111777777777777777="+vector.size());
		return vector;
	}

	/**************************************
	 * End of Partha on 16.01.2012
	 ******************************/

	/* Anupam Samanta */

	public static String getMbox(String user_id) {
		String mbox = "";
		Connection oConn3 = null;
		Statement statement3 = null;
		ResultSet resultset3 = null;
		try {
			oConn3 = ds1.getConnection();
			statement3 = oConn3.createStatement();
			resultset3 = statement3
					.executeQuery("select email_id from  student_details where student_id ='" + user_id + "'");

			while (resultset3.next()) {
				mbox = resultset3.getString(1);
			}
			statement3.close();

		} catch (SQLException sqlexception) {
			System.out.println("Inside DataBaseLayer getMbox(), SQLException !!!!");
			System.out.println("Inside DataBaseLayer getMbox(), the error message - " + sqlexception.getMessage());
			System.out.println("Inside DataBaseLayer getMbox(), the error message - " + sqlexception.getErrorCode());
		} finally {
			if (oConn3 != null) {
				try {
					oConn3.close();
				} catch (SQLException ex2) {
				}
			}
		}
		return mbox;
	}

}
