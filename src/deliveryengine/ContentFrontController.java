/**
 * 
 * @author Anupam Samanta
 */
package deliveryengine;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import learnityInit.Host;

/**
 * Servlet implementation class ContentFrontController
 */
@WebServlet("/servlet/ContentFrontController")
public class ContentFrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final long ONE_SECOND_IN_MILLIS = TimeUnit.SECONDS.toMillis(1);

	public ContentFrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null) {

			PrintWriter out = response.getWriter();
			out.print("Please Login to Continue");
			return;
		}
		/* enable caching */
		response.setHeader("Cache-Control", "public");
		String unit_id = (String) request.getParameter("unit_id");
		String file_name = (String) request.getParameter("file_name");

		/* For xAPI */
		String user_id = (String) session.getAttribute("user_id");
		String mbox = (String) session.getAttribute("mbox");
		LRSClient lrsClient = new LRSClient();
		lrsClient.SaveStatement(user_id, mbox,file_name,unit_id);

		/* End */
		Timestamp UploadTime = null;
		long fileLastModified;
		String uploadTarget = DataBaseLayer.getUploadTarget(unit_id);
		String mimetype = getServletContext().getMimeType(file_name);
		response.setContentType(mimetype);

		if (uploadTarget.equals("DB")) {
			UploadTime = DataBaseLayer.getUploadTime(unit_id, file_name);
			fileLastModified = UploadTime.getTime();
		} else {

			String contentFolder = Host.getServerDocumentPath();
			String contentFileURL = contentFolder + unit_id + "/" + file_name;
			FileTime a = Files.getLastModifiedTime(Paths.get(contentFileURL));
			fileLastModified = a.toMillis();
		}

		try {

			response.setDateHeader("Last-Modified", fileLastModified);
			long ifModifiedSince = request.getDateHeader("If-Modified-Since");

			if (ifModifiedSince != -1L) {
				boolean notModified = ifModifiedSince + ONE_SECOND_IN_MILLIS > fileLastModified;

				if (notModified) {
					response.sendError(HttpServletResponse.SC_NOT_MODIFIED);
					return;

				} else {

					if (uploadTarget.equals("DB")) {
						byte[] buffer = DataBaseLayer.getContentFile(unit_id, file_name);

						response.setContentLength(buffer.length);
						response.getOutputStream().write(buffer);
					} else {
						String contentFolder = Host.getServerDocumentPath();
						String contentFilePath = contentFolder + unit_id + "/" + file_name;
						File contentFile = new File(contentFilePath);
						long fileLength = contentFile.length();
						FileInputStream inStream = new FileInputStream(contentFile);
						byte[] fileData = new byte[(int) fileLength];
						DataInputStream dataIs = new DataInputStream(inStream);
						dataIs.readFully(fileData);
						response.setContentLength(fileData.length);
						response.getOutputStream().write(fileData);
						dataIs.close();
					}
				}

			} else {

				if (uploadTarget.equals("DB")) {
					byte[] buffer = DataBaseLayer.getContentFile(unit_id, file_name);

					response.setContentLength(buffer.length);
					response.getOutputStream().write(buffer);
				} else {
					String contentFolder = Host.getServerDocumentPath();
					String contentFilePath = contentFolder + unit_id + "/" + file_name;
					File contentFile = new File(contentFilePath);
					long fileLength = contentFile.length();
					FileInputStream inStream = new FileInputStream(contentFile);
					byte[] fileData = new byte[(int) fileLength];
					DataInputStream dataIs = new DataInputStream(inStream);
					dataIs.readFully(fileData);
					response.setContentLength(fileData.length);
					response.getOutputStream().write(fileData);
					dataIs.close();
				}
			}

		} catch (NumberFormatException e) {
			System.out.println("not a number");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
