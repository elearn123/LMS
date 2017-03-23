package deliveryengine;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.*;

import org.apache.commons.io.FileUtils;

import interfaceenginev2.CustomEditAction;
import learnityInit.Host;

public class DeleteContentFromFS extends CustomEditAction {

	public boolean DeleteAction(final HttpServletRequest httpServletRequest) {

		final boolean b = false;
		final String unit_id = httpServletRequest.getParameter("unit_id");
		final String parameter2 = httpServletRequest.getParameter("course_id");
		String target = DataBaseLayer.getUploadTarget(unit_id);
		System.out.println(" ======unitId===onselectTitle=====" + unit_id);

		String str3 = Host.getServerDocumentPath();
		String fullpath = str3 + unit_id;
		Path path = Paths.get(fullpath);

		if (target.equals("DB")) {

			return b;
		} else {

			try {

				FileUtils.forceDelete(new File(fullpath));
			} catch (NoSuchFileException x) {
				System.err.format("%s: no such" + " file or directory%n", path);
			} catch (DirectoryNotEmptyException x) {
				System.err.format("%s not empty%n", path);
			} catch (IOException x) {

				System.err.println(x);
			}

		}

		return b;
	}

}
