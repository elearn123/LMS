/*package deliveryengine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.adl.util.debug.DebugIndicator;

public class LMSPackageHandler
{
  public static ZipFile zf;
  private static boolean _Debug = DebugIndicator.ON;
  
  public static String version()
  {
    System.out.println("*************");
    System.out.println("in version()");
    System.out.println("*************\n");
    
    String str = new String("");
    str = "Version 1.03 For Live Site";
    
    return str;
  }
  
  public static void display(String paramString)
  {
    if (_Debug)
    {
      System.out.println("*************");
      System.out.println("in display()");
      System.out.println("*************\n");
    }
    try
    {
      System.out.println("** " + paramString + " **\n");
      System.out.println("*****************************************");
      System.out.println("The Package Contains the following files:");
      System.out.println("*****************************************\n");
      
      zf = new ZipFile(paramString);
      for (Enumeration localEnumeration = zf.entries(); localEnumeration.hasMoreElements();) {
        System.out.println(((ZipEntry)localEnumeration.nextElement()).getName());
      }
      zf.close();
    }
    catch (IOException localIOException)
    {
      System.out.println("IO Exception Caught: " + localIOException);
    }
    if (_Debug) {
      System.out.println("\n\n");
    }
  }
  
  public static String extract(String paramString1, String paramString2, String paramString3)
  {
    if (_Debug)
    {
      System.out.println("***********************");
      System.out.println("in extract()           ");
      System.out.println("***********************");
      System.out.println("zip file: " + paramString1);
      System.out.println("file to extract: " + paramString2);
    }
    String str1 = new String("");
    try
    {
      String str2 = new String("");
      
      ZipInputStream localZipInputStream = new ZipInputStream(new FileInputStream(paramString1));
      
      int i = paramString2.lastIndexOf("/") + 1;
      str1 = paramString2.substring(i);
      str2 = paramString3 + File.separatorChar + str1;
      
      FileOutputStream localFileOutputStream = new FileOutputStream(str2);
      
      byte[] arrayOfByte = new byte['?'];
      
      int k = 0;
      while (k != 1)
      {
        ZipEntry localZipEntry = localZipInputStream.getNextEntry();
        if (localZipEntry.getName().equalsIgnoreCase(paramString2))
        {
          if (_Debug) {
            System.out.println("Found file to extract...  extracting to " + paramString3);
          }
          k = 1;
        }
      }
      int j;
      while ((j = localZipInputStream.read(arrayOfByte)) > 0) {
        localFileOutputStream.write(arrayOfByte, 0, j);
      }
      localFileOutputStream.close();
      localZipInputStream.close();
    }
    catch (IOException localIOException)
    {
      if (_Debug) {
        System.out.println("IO Exception Caught: " + localIOException);
      }
    }
    return str1;
  }
  
  public static boolean findManifest(String paramString)
  {
    if (_Debug)
    {
      System.out.println("***********************");
      System.out.println("in findManifest()      ");
      System.out.println("***********************\n");
    }
    boolean bool = false;
    try
    {
      ZipInputStream localZipInputStream = new ZipInputStream(new FileInputStream(paramString));
      
      int i = 0;
      while ((i != 1) && (localZipInputStream.available() != 0))
      {
        ZipEntry localZipEntry = localZipInputStream.getNextEntry();
        if ((localZipInputStream.available() != 0) && 
        
          (localZipEntry.getName().equalsIgnoreCase("imsmanifest.xml")))
        {
          if (_Debug) {
            System.out.println("Located manifest.... returning true");
          }
          i = 1;
          bool = true;
        }
      }
      localZipInputStream.close();
    }
    catch (IOException localIOException)
    {
      if (_Debug) {
        System.out.println("IO Exception Caught: " + localIOException);
      }
    }
    return bool;
  }
  
  public static boolean findMetadata(String paramString)
  {
    if (_Debug)
    {
      System.out.println("***********************");
      System.out.println("in findMetadata()      ");
      System.out.println("***********************\n");
    }
    boolean bool = false;
    String str = ".xml";
    try
    {
      ZipInputStream localZipInputStream = new ZipInputStream(new FileInputStream(paramString));
      while (localZipInputStream.available() != 0)
      {
        ZipEntry localZipEntry = localZipInputStream.getNextEntry();
        if ((localZipInputStream.available() != 0) && 
        
          (localZipEntry.getName().endsWith(str)))
        {
          bool = true;
          if (_Debug) {
            System.out.println("Other Meta-data located... returning true");
          }
        }
      }
      localZipInputStream.close();
    }
    catch (IOException localIOException)
    {
      if (_Debug) {
        System.out.println("IO Exception Caught: " + localIOException);
      }
    }
    return bool;
  }
  
  public static Vector locateMetadata(String paramString)
  {
    if (_Debug)
    {
      System.out.println("***********************");
      System.out.println("in locateMetadata()    ");
      System.out.println("***********************\n");
    }
    Vector localVector = new Vector();
    String str = ".xml";
    try
    {
      ZipInputStream localZipInputStream = new ZipInputStream(new FileInputStream(paramString));
      if (_Debug) {
        System.out.println("Other meta-data located:");
      }
      while (localZipInputStream.available() != 0)
      {
        ZipEntry localZipEntry = localZipInputStream.getNextEntry();
        if ((localZipInputStream.available() != 0) && 
        
          (localZipEntry.getName().endsWith(str)))
        {
          if (_Debug) {
            System.out.println(localZipEntry.getName());
          }
          localVector.addElement(localZipEntry.getName());
        }
      }
      localZipInputStream.close();
    }
    catch (IOException localIOException)
    {
      if (_Debug) {
        System.out.println("IO Exception Caught: " + localIOException);
      }
    }
    return localVector;
  }
  
  public static String getListOfMetadata(String paramString)
  {
    if (_Debug)
    {
      System.out.println("***********************");
      System.out.println("in getListOfMetadata() ");
      System.out.println("***********************\n");
    }
    Vector localVector = new Vector();
    localVector = locateMetadata(paramString);
    
    String str = new String();
    str = localVector.toString();
    if (_Debug)
    {
      System.out.println("**********************************************");
      System.out.println("in getListOfMetadata(): String is " + str);
      System.out.println("**********************************************\n");
    }
    return str;
  }
}
*/