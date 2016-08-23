package learnityInit.index;

import org.apache.lucene.document.*;
import org.apache.lucene.demo.html.*;
import java.io.*;

public class HTMLDocument
{
    static char dirSep;
    
    public static String uid(final File f) {
        return f.getPath().replace(HTMLDocument.dirSep, '\0') + "\u0000" + DateField.timeToString(f.lastModified());
    }
    
    public static String uid2url(final String uid) {
        final String url = uid.replace('\0', '/');
        return url.substring(0, url.lastIndexOf(47));
    }
    
    public static Document Document(final File f) throws IOException, InterruptedException {
        final Document doc = new Document();
        doc.add(Field.UnIndexed("url", f.getPath().replace(HTMLDocument.dirSep, '/')));
        doc.add(Field.Keyword("modified", DateField.timeToString(f.lastModified())));
        doc.add(new Field("uid", uid(f), false, true, false));
        final HTMLParser parser = new HTMLParser(f);
        doc.add(Field.Text("contents", parser.getReader()));
        doc.add(Field.UnIndexed("summary", parser.getSummary()));
        doc.add(Field.Text("title", parser.getTitle()));
        return doc;
    }
    
    static {
        HTMLDocument.dirSep = System.getProperty("file.separator").charAt(0);
    }
}
