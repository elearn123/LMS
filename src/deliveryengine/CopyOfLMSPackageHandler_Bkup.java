package deliveryengine;

import java.util.zip.*;
import java.io.*;
import java.util.*;
import org.adl.util.debug.*;

public class CopyOfLMSPackageHandler_Bkup
{
    public static ZipFile zf;
    private static boolean _Debug;
    
    public static String version() {
        System.out.println("*************");
        System.out.println("in version()");
        System.out.println("*************\n");
        final String s = new String("");
        return "Version 1.03 For Live Site";
    }
    
    public static void display(final String s) {
        if (CopyOfLMSPackageHandler_Bkup._Debug) {
            System.out.println("*************");
            System.out.println("in display()");
            System.out.println("*************\n");
        }
        try {
            System.out.println("** " + s + " **\n");
            System.out.println("*****************************************");
            System.out.println("The Package Contains the following files:");
            System.out.println("*****************************************\n");
            CopyOfLMSPackageHandler_Bkup.zf = new ZipFile(s);
            final Enumeration<? extends ZipEntry> entries = CopyOfLMSPackageHandler_Bkup.zf.entries();
            while (entries.hasMoreElements()) {
                System.out.println(((ZipEntry)entries.nextElement()).getName());
            }
            CopyOfLMSPackageHandler_Bkup.zf.close();
        }
        catch (IOException ex) {
            System.out.println("IO Exception Caught: " + ex);
        }
        if (CopyOfLMSPackageHandler_Bkup._Debug) {
            System.out.println("\n\n");
        }
    }
    
    public static String extract(final String s, final String s2, final String s3) {
        if (CopyOfLMSPackageHandler_Bkup._Debug) {
            System.out.println("***********************");
            System.out.println("in extract()           ");
            System.out.println("***********************");
            System.out.println("zip file: " + s);
            System.out.println("file to extract: " + s2);
        }
        String substring = new String("");
        try {
            final String s4 = new String("");
            final ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(s));
            substring = s2.substring(s2.lastIndexOf("/") + 1);
            final FileOutputStream fileOutputStream = new FileOutputStream(s3 + File.separatorChar + substring);
            final byte[] array = new byte[1024];
            for (int i = 0; i != 1; i = 1) {
                if (zipInputStream.getNextEntry().getName().equalsIgnoreCase(s2)) {
                    if (CopyOfLMSPackageHandler_Bkup._Debug) {
                        System.out.println("Found file to extract...  extracting to " + s3);
                    }
                }
            }
            int read;
            while ((read = zipInputStream.read(array)) > 0) {
                fileOutputStream.write(array, 0, read);
            }
            fileOutputStream.close();
            zipInputStream.close();
        }
        catch (IOException ex) {
            if (CopyOfLMSPackageHandler_Bkup._Debug) {
                System.out.println("IO Exception Caught: " + ex);
            }
        }
        return substring;
    }
    
    public static boolean findManifest(final String s) {
        if (CopyOfLMSPackageHandler_Bkup._Debug) {
            System.out.println("***********************");
            System.out.println("in findManifest()      ");
            System.out.println("***********************\n");
        }
        boolean b = false;
        try {
            final ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(s));
            for (int n = 0; n != 1 && zipInputStream.available() != 0; n = 1, b = true) {
                final ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (zipInputStream.available() != 0 && nextEntry.getName().equalsIgnoreCase("imsmanifest.xml")) {
                    if (CopyOfLMSPackageHandler_Bkup._Debug) {
                        System.out.println("Located manifest.... returning true");
                    }
                }
            }
            zipInputStream.close();
        }
        catch (IOException ex) {
            if (CopyOfLMSPackageHandler_Bkup._Debug) {
                System.out.println("IO Exception Caught: " + ex);
            }
        }
        return b;
    }
    
    public static boolean findMetadata(final String s) {
        if (CopyOfLMSPackageHandler_Bkup._Debug) {
            System.out.println("***********************");
            System.out.println("in findMetadata()      ");
            System.out.println("***********************\n");
        }
        boolean b = false;
        final String s2 = ".xml";
        try {
            final ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(s));
            while (zipInputStream.available() != 0) {
                final ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (zipInputStream.available() != 0 && nextEntry.getName().endsWith(s2)) {
                    b = true;
                    if (!CopyOfLMSPackageHandler_Bkup._Debug) {
                        continue;
                    }
                    System.out.println("Other Meta-data located... returning true");
                }
            }
            zipInputStream.close();
        }
        catch (IOException ex) {
            if (CopyOfLMSPackageHandler_Bkup._Debug) {
                System.out.println("IO Exception Caught: " + ex);
            }
        }
        return b;
    }
    
    public static Vector locateMetadata(final String s) {
        if (CopyOfLMSPackageHandler_Bkup._Debug) {
            System.out.println("***********************");
            System.out.println("in locateMetadata()    ");
            System.out.println("***********************\n");
        }
        final Vector<String> vector = new Vector<String>();
        final String s2 = ".xml";
        try {
            final ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(s));
            if (CopyOfLMSPackageHandler_Bkup._Debug) {
                System.out.println("Other meta-data located:");
            }
            while (zipInputStream.available() != 0) {
                final ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (zipInputStream.available() != 0 && nextEntry.getName().endsWith(s2)) {
                    if (CopyOfLMSPackageHandler_Bkup._Debug) {
                        System.out.println(nextEntry.getName());
                    }
                    vector.addElement(nextEntry.getName());
                }
            }
            zipInputStream.close();
        }
        catch (IOException ex) {
            if (CopyOfLMSPackageHandler_Bkup._Debug) {
                System.out.println("IO Exception Caught: " + ex);
            }
        }
        return vector;
    }
    
    public static String getListOfMetadata(final String s) {
        if (CopyOfLMSPackageHandler_Bkup._Debug) {
            System.out.println("***********************");
            System.out.println("in getListOfMetadata() ");
            System.out.println("***********************\n");
        }
        final Vector vector = new Vector();
        final Vector locateMetadata = locateMetadata(s);
        final String s2 = new String();
        final String string = locateMetadata.toString();
        if (CopyOfLMSPackageHandler_Bkup._Debug) {
            System.out.println("**********************************************");
            System.out.println("in getListOfMetadata(): String is " + string);
            System.out.println("**********************************************\n");
        }
        return string;
    }
    
    static {
        CopyOfLMSPackageHandler_Bkup._Debug = DebugIndicator.ON;
    }
}
