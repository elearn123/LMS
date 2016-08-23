package learnityInit.index;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Date;

import learnityInit.Host;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermEnum;
import org.grlea.log.DebugLevel;
import org.grlea.log.SimpleLogger;
import org.pdfbox.searchengine.lucene.LucenePDFDocument;

public class IndexFiles
{
    static boolean create;
    private static boolean deleting;
    private static IndexReader reader;
    private static IndexWriter writer;
    private static TermEnum uidIter;
    private static final SimpleLogger log;
    
    public void createIndex(final String unit_id) {
        final String sdp = Host.getServerDocumentPath();
        final String index = sdp + File.separatorChar + unit_id + File.separatorChar + "index";
        final File indexDir = new File(index);
        if (!indexDir.exists()) {
            indexDir.mkdir();
        }
        final String dirName = sdp + File.separatorChar + unit_id;
        System.out.println("createIndex index : " + index + " : createIndex dirName : " + dirName);
        try {
            File root = null;
            root = new File(dirName);
            final Date start = new Date();
            IndexFiles.writer = new IndexWriter(index, (Analyzer)new StandardAnalyzer(), IndexFiles.create);
            IndexFiles.writer.maxFieldLength = 1000000;
            indexDocs(root, index, IndexFiles.create);
            IndexFiles.log.info("Optimizing index...");
            IndexFiles.writer.optimize();
            IndexFiles.writer.close();
            final Date end = new Date();
        }
        catch (Exception e) {
            IndexFiles.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
            IndexFiles.log.fatal(" caught a " + e.getClass() + "with message: " + e.getMessage());
        }
    }
    
    private static void indexDocs(final File file, final String index, final boolean create) throws Exception {
        if (!create) {
            IndexFiles.reader = IndexReader.open(index);
            IndexFiles.uidIter = IndexFiles.reader.terms(new Term("uid", ""));
            indexDoc(file);
            if (IndexFiles.deleting) {
                while (IndexFiles.uidIter.term() != null && IndexFiles.uidIter.term().field() == "uid") {
                    IndexFiles.log.info("deleting " + HTMLDocument.uid2url(IndexFiles.uidIter.term().text()));
                    IndexFiles.reader.delete(IndexFiles.uidIter.term());
                    IndexFiles.uidIter.next();
                }
                IndexFiles.deleting = false;
            }
            IndexFiles.uidIter.close();
            IndexFiles.reader.close();
        }
        else {
            indexDoc(file);
        }
    }
    
    private static void indexDoc(final File file) throws Exception {
        final String text = null;
        if (file.isDirectory()) {
            final String[] files = file.list();
            Arrays.sort(files);
            for (int i = 0; i < files.length; ++i) {
                indexDoc(new File(file, files[i]));
            }
        }
        else if (file.getPath().endsWith(".html") || file.getPath().endsWith(".htm") || file.getPath().endsWith(".xhtml") || file.getPath().endsWith(".txt")) {
            if (IndexFiles.uidIter != null) {
                final String uid = HTMLDocument.uid(file);
                while (IndexFiles.uidIter.term() != null && IndexFiles.uidIter.term().field() == "uid" && IndexFiles.uidIter.term().text().compareTo(uid) < 0) {
                    if (IndexFiles.deleting) {
                        IndexFiles.log.info("deleting " + HTMLDocument.uid2url(IndexFiles.uidIter.term().text()));
                        IndexFiles.reader.delete(IndexFiles.uidIter.term());
                    }
                    IndexFiles.uidIter.next();
                }
                if (IndexFiles.uidIter.term() != null && IndexFiles.uidIter.term().field() == "uid" && IndexFiles.uidIter.term().text().compareTo(uid) == 0) {
                    IndexFiles.uidIter.next();
                }
                else if (!IndexFiles.deleting) {
                    final Document doc = HTMLDocument.Document(file);
                    IndexFiles.log.info("adding " + doc.get("url"));
                    IndexFiles.writer.addDocument(doc);
                }
            }
            else {
                final Document doc2 = HTMLDocument.Document(file);
                IndexFiles.log.info("adding " + doc2.get("url"));
                IndexFiles.writer.addDocument(doc2);
            }
        }
        else if (file.getPath().endsWith(".pdf")) {
            IndexFiles.log.info("IndexFiles " + file.getCanonicalPath());
            try {
                final Document doc2 = LucenePDFDocument.getDocument(file);
                doc2.add(Field.Text("contents", (Reader)new FileReader(file)));
                doc2.add(Field.Keyword("filename", file.getCanonicalPath()));
                IndexFiles.writer.addDocument(doc2);
            }
            catch (Exception e) {
                IndexFiles.log.dbe(DebugLevel.L1_FATAL, (Throwable)e);
            }
        }
    }
    
    static {
        IndexFiles.create = true;
        IndexFiles.deleting = false;
        log = new SimpleLogger((Class)IndexFiles.class, true);
    }
}
