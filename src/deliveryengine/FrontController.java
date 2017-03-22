package deliveryengine;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
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

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import learnityInit.Host;

/* Author Anupam Samanta*/

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/servlet/FrontController")
public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final long ONE_SECOND_IN_MILLIS = TimeUnit.SECONDS.toMillis(1);

	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// WebContext wctx1 = WebContextFactory.get();
		// javax.servlet.http.HttpSession mysession = wctx1.getSession();
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

						// response.setHeader("Content-Disposition", "inline");
						response.setContentLength(fileData.length);

						response.getOutputStream().write(fileData);

						// response.sendRedirect(contentFileURL);
						dataIs.close();
					}
				}

			} else {

				// doPost(request,response);

				
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

					// response.setHeader("Content-Disposition", "inline");
					response.setContentLength(fileData.length);

					response.getOutputStream().write(fileData);

					// response.sendRedirect(contentFileURL);
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
