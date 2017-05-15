	package deliveryengine;
	
	import interfaceenginev2.FileDownloaderPojo;
	import interfaceenginev2.FileUploaderPojo;
	
	import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.FileReader;
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.InputStreamReader;
	import java.io.ObjectOutputStream;
	import java.io.OutputStream;
	import java.io.PrintStream;
	import java.io.StringWriter;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
	import java.util.Calendar;
	import java.util.Date;
	import java.util.GregorianCalendar;
	import java.util.Hashtable;
	import java.util.Iterator;
	import java.util.Set;
	import java.util.Vector;
	import java.util.zip.ZipEntry;
	import java.util.zip.ZipInputStream;
	import java.util.zip.ZipOutputStream;
	
	import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
	import javax.xml.parsers.DocumentBuilderFactory;
	import javax.xml.parsers.ParserConfigurationException;
	
	import learnityInit.Host;
	import learnityInit.index.IndexFiles;

	import oracle.xml.parser.v2.DOMParser;
	import oracle.xml.parser.v2.XMLDocument;
	
	import org.adl.samplerte.server.LMSManifestHandler;
	import org.adl.samplerte.server.LMSPackageHandler;
	import org.apache.commons.codec.EncoderException;
	import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.xml.serialize.OutputFormat;
	import org.apache.xml.serialize.XMLSerializer;
	import org.directwebremoting.WebContext;
	import org.directwebremoting.WebContextFactory;
	import org.directwebremoting.io.FileTransfer;
	import org.grlea.log.SimpleLogger;
	import org.w3c.dom.Document;
	import org.w3c.dom.Element;
	import org.w3c.dom.NamedNodeMap;
	import org.w3c.dom.Node;
	import org.w3c.dom.NodeList;
	import org.xml.sax.InputSource;
	
	public class launchcourse {
		public static final SimpleLogger log = new SimpleLogger(launchcourse.class,
				true);
		Document manifest;
		static final int BUFFER = 2048;
		private ZipOutputStream zip;
		private FileOutputStream fileWriter;
		ArrayList bookMarkCache;
	
		public launchcourse() {
			this.manifest = null;
	
			this.zip = null;
			this.fileWriter = null;
			this.bookMarkCache = new ArrayList();
		}
	
		public String onselectTitle(String paramString1, String paramString2) {
			String str1 = "false";
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
			String str2 = (String) localHttpSession.getAttribute("unit_id");
			if (str2 == null) {
				str1 = "true";
			} else {
				String str3 = (String) localHttpSession.getAttribute("user_id");
				String str4 = localHttpSession.getId();
				Hashtable localHashtable = (Hashtable) localHttpSession
						.getAttribute("SESSION_COURSE_ITEM");
				Object localObject = localHashtable.get("Identifier");
				GregorianCalendar localGregorianCalendar = new GregorianCalendar();
				Date localDate = new Date();
				localGregorianCalendar.setTime(localDate);
				String str5 = localGregorianCalendar.get(11) + ":"
						+ localGregorianCalendar.get(12) + ":"
						+ localGregorianCalendar.get(13);
				String str6 = localGregorianCalendar.get(1) + "-"
						+ (localGregorianCalendar.get(2) + 1) + "-"
						+ localGregorianCalendar.get(5);
				String str7 = str6 + " " + str5;
				localHashtable.put("Identifier", paramString1);
				localHttpSession
						.setAttribute("SESSION_COURSE_ITEM", localHashtable);
				InitializeSCO localInitializeSCO = new InitializeSCO();
				if (localObject == null) {
					DataBaseLayer.InsertIntoLearnerLoginInfo(str3, str2,
							paramString1, str6, str4, str5);
					localInitializeSCO.SCOInitialize(str3, str2, paramString1);
					DataBaseLayer.Insertintoscouserinfo(str3, str2, paramString1,
							paramString2);
				} else {
					String str8 = localObject.toString();
					DataBaseLayer.updateLoginInfo(str3, str8, str4, str5, str6,
							str2);
					localInitializeSCO.SCOFinish(str3, str2, str8);
					DataBaseLayer.InsertIntoLearnerLoginInfo(str3, str2,
							paramString1, str6, str4, str5);
					localInitializeSCO.SCOInitialize(str3, str2, paramString1);
					DataBaseLayer.Updateintoscouserinfo(str3, str2, str8,
							paramString2);
					DataBaseLayer.Insertintoscouserinfo(str3, str2, paramString1,
							paramString2);
				}
			}
			return str1;
		}
	
		public String getIdentifier() {
			String str1 = "0";
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
			str1 = (String) localHttpSession.getAttribute("bookmarkidentifier");
			String str2 = (String) localHttpSession
					.getAttribute("bookmarkTopicTitle");
			if ((str1 == null) || (str1 == "")) {
				str1 = "0";
			}
			if (str1 != "0") {
				onselectTitle(str1, str2);
			}
			return str1;
		}
	
		public String checkCourseControl() {
			String str1 = "0";
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
			String str2 = (String) localHttpSession.getAttribute("unit_id");
			Vector localVector = DataBaseLayer.getCourseDetailsList(str2);
			str1 = (String) localVector.elementAt(5);
			return str1;
		}
	
		public String treeConstruct() {
			long l1 = System.currentTimeMillis();
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
	
			String str1 = (String) localHttpSession.getAttribute("unit_id");
			String str2 = (String) localHttpSession.getAttribute("browser");
			String str3 = (String) localHttpSession.getAttribute("browserVersion");
			String str4 = (String) localHttpSession.getAttribute("user_id");
			if (str1 == null) {
				str1 = "";
			}
			if (str4 == null) {
				str4 = "";
			}
			String str5 = DataBaseLayer.getLearning_Style(str4);
	
			Document localDocument = DataBaseLayer.parse(str1, "csformat");
			NodeList localNodeList = localDocument
					.getElementsByTagName("organization");
			Object localObject1 = null;
			for (int i = 0; i < localNodeList.getLength(); i++) {
				Element localElement = (Element) localNodeList.item(i);
				if (localElement.getAttributeNode("identifier").getValue()
						.equals(str5)) {
					localObject1 = localElement;
					break;
				}
			}
			Object localObject3;
			if (localObject1 == null) {
				String localObject2 = ((Element) localDocument.getElementsByTagName(
						"organizations").item(0)).getAttributeNode("default")
						.getValue();
				System.out.println("defaultOrg = " + (String) localObject2);
				for (int j = 0; j < localNodeList.getLength(); j++) {
					localObject3 = (Element) localNodeList.item(j);
					if (((Element) localObject3).getAttributeNode("identifier")
							.getValue().equals(localObject2)) {
						localObject1 = localObject3;
						break;
					}
				}
			}
			if (localObject1 == null) {
				localObject1 = (Element) localNodeList.item(0);
			}
			Object localObject2 = "";
			String str6 = "<ul>\n";
			if (localObject1 != null) {
				localObject3 = localObject1;
				String str7 = "";
				String str8 = ((Element) localObject3)
						.getElementsByTagName("title").item(0).getFirstChild()
						.getNodeValue();
				System.out.println(" ======orgTitle===treeConstruct===== " + str8);
				try {
					byte[] arrayOfByte = str8.getBytes();
					str8 = new String(arrayOfByte, "utf-8");
				} catch (Exception localException) {
					System.out.println("Error:" + localException);
				}
				str6 = str6 + "\n<li data=\"key: '" + str8 + "',tooltip: '" + str8
						+ "', description: '" + str8 + "' ,isFolder: true\">"
						+ str8;
	
				localObject2 = showTreeInOutputStream(str1, (Element) localObject3,
						localDocument);
			}
			long l2 = System.currentTimeMillis();
			long l3 = l2 - l1;
			System.out.println("time taken====" + l3);
			return str6 + (String) localObject2 + "</ul>";
		}
	
		public String showTreeInOutputStream(String paramString,
				Element paramElement, Document paramDocument) {
			String str1 = "";
			NodeList localNodeList = paramElement.getChildNodes();
			NamedNodeMap localNamedNodeMap = null;
			String str2 = null;
			Node localNode1 = null;
			Node localNode2 = null;
			String str3 = null;
			String str4 = null;
			Object localObject1 = null;
			String str5 = null;
			Object localObject2 = null;
			str1 = str1 + "\n<ul>";
			int i = 0;
			if (localNodeList.getLength() > 0) {
				for (int j = 0; j < localNodeList.getLength(); j++) {
					localNode1 = localNodeList.item(j);
	
					str2 = localNode1.getNodeName();
					System.out.println("tagname==1= " + str2);
					if (str2.equalsIgnoreCase("item")) {
						localNamedNodeMap = localNode1.getAttributes();
						for (int k = 0; k < localNamedNodeMap.getLength(); k++) {
							localNode2 = localNamedNodeMap.item(k);
							if (localNode2.getNodeName().equalsIgnoreCase(
									"identifier")) {
								str3 = localNode2.getNodeValue();
							}
							if (localNode2.getNodeName().equalsIgnoreCase(
									"identifierref")) {
								str4 = localNode2.getNodeValue();
							}
						}
						i = DataBaseLayer.getSequence(paramString, str3);
	
						Element localElement = (Element) localNodeList.item(j);
						str5 = localElement.getElementsByTagName("title").item(0)
								.getFirstChild().getNodeValue();
						System.out.println("strItemTitle=== " + str5);
						System.out.println("strIdentifier==2= " + str3);
						System.out.println("resourseRef==if= " + str4);
	
						localNode1 = localNodeList.item(j);
	
						str2 = localNode1.getNodeName();
						System.out.println("tagname====== " + str2);
						if (str2.equalsIgnoreCase("item")) {
							if (str4 == null) {
								System.out.println("resourseRef==if= " + str4);
	
								str1 = str1
										+ "\n<li data=\"key: '"
										+ str3
										+ "',tooltip: '"
										+ str5
										+ "',url:'"
		//								+ getResourseURL(str4, paramDocument,
		//										paramString) + "' , description: '"
										+"abcd"
										+ str5 + "',sequence: '" + i
										+ "', isFolder: false\">" + str5;
							} else {
								System.out.println("resourseRef==else= " + str4);
	
								str1 = str1
										+ "\n<li data=\"key: '"
										+ str3
										+ "',tooltip: '"
										+ str5
										+ "', url:'"
										+ getResourseURL(str4, paramDocument,
												paramString) + "' ,description: '"
									//+"abcd"	
									+ str5 + "',sequence: '" + i
										+ "', isFolder: false \">" + str5;
							}
						} else {
							System.out.println("resourseRef= outer=else= " + str4);
							str1 = str1
									+ "\n<li data=\"key: '"
									+ str3
									+ "',tooltip: '"
									+ str5
									+ "', url:'"
									//+ getResourseURL(str4, paramDocument,
										//	paramString) + "' ,description: '"
									+ getResourseURL(str4, paramDocument,
											paramString) + "' ,description: '"
									//+"abcd"
										+ str5 + "',sequence: '" + i
									+ "', isFolder: false \">" + str5;
						}
						str5 = null;
						String str6 = showTreeInOutputStream1(paramString,
								localElement, paramDocument);
						str1 = str1 + "\n " + str6;
					}
				}
			}
			str1 = str1 + "\n</ul>";
			return str1;
		}
	
		public String showTreeInOutputStream1(String paramString,
				Element paramElement, Document paramDocument) {
			String str1 = "";
			NodeList localNodeList = paramElement.getChildNodes();
			NamedNodeMap localNamedNodeMap = null;
			String str2 = null;
			Node localNode1 = null;
			Node localNode2 = null;
			String str3 = null;
			String str4 = null;
	
			String str5 = null;
			Object localObject = null;
	
			int i = 0;
			if (localNodeList.getLength() > 0) {
				for (int j = 0; j < localNodeList.getLength(); j++) {
					localNode1 = localNodeList.item(j);
	
					str2 = localNode1.getNodeName();
					if (str2.equalsIgnoreCase("item")) {
						str1 = str1 + "\n<ul>";
						for (int k = 0; k < localNodeList.getLength(); k++) {
							localNode1 = localNodeList.item(k);
	
							str2 = localNode1.getNodeName();
							if (str2.equalsIgnoreCase("item")) {
								localNamedNodeMap = localNode1.getAttributes();
								for (int m = 0; m < localNamedNodeMap.getLength(); m++) {
									localNode2 = localNamedNodeMap.item(m);
									if (localNode2.getNodeName().equalsIgnoreCase(
											"identifier")) {
										str3 = localNode2.getNodeValue();
									}
									if (localNode2.getNodeName().equalsIgnoreCase(
											"identifierref")) {
										str4 = localNode2.getNodeValue();
									}
								}
								i = DataBaseLayer.getSequence(paramString, str3);
	
								Element localElement = (Element) localNodeList
										.item(k);
								str5 = localElement.getElementsByTagName("title")
										.item(0).getFirstChild().getNodeValue();
								str1 = str1
										+ "\n<li data=\"key: '"
										+ str3
										+ "',tooltip: '"
										+ str5
										+ "',url: '"
										+ getResourseURL(str4, paramDocument,
											paramString) + "' ,description: '"
								//		+"ContentFrontController?idententifier="+str4+"&unit_id="+str100;
										+ str5 + "' ,sequence: '" + i
										+ "' ,isFolder: false\">" + str5;
	
								str5 = null;
								String str6 = showTreeInOutputStream1(paramString,
										localElement, paramDocument);
								str1 = str1 + "\n " + str6;
							}
						}
						str1 = str1 + "\n</ul>";
					}
				}
			} else {
				str1 = str1 + "\n<li data=\"key: '" + str3 + "',tooltip: '" + str5
						+ "', description: '" + str5 + "',sequence: '" + i
						+ "', isFolder: true\">" + str5;
			}
			return str1;
		}
	
		public String getUnitName() {
			String str1 = "";
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
	
			String str2 = (String) localHttpSession.getAttribute("unit_id");
			String str3 = (String) localHttpSession.getAttribute("user_id");
			if (str2 == null) {
				str2 = "";
			}
			if (str3 == null) {
				str3 = "";
			}
			String str4 = DataBaseLayer.getUnitName(str2);
			str1 = "<b>" + str4 + "</b>";
	
			return str1;
		}
	
		public String getResourseURL(String paramString1, Document paramDocument,
				String paramString2) {
			
			System.out.println("-------param1:"+paramString1+">>>>>paramdoc:"+paramDocument+">>>>--param2:"+paramString2+"---------getResourseURL()------->>>>----");
	
			Object localObject = "";
			NamedNodeMap localNamedNodeMap = null;
			Node localNode1 = null;
			String str1 = null;
			Node localNode2 = null;
			int i = 0;
			DebugSwitch localDebugSwitch = new DebugSwitch(
					this);
			if (paramDocument == null) {
				System.out
						.println("------->>>>>>>>>---------No Resource Data Found-----getResourseURL()------->>>>----");
			} else {
				NodeList localNodeList1 = paramDocument
						.getElementsByTagName("resources");
				if (localNodeList1.getLength() > 0) {
					NodeList localNodeList2 = paramDocument
							.getElementsByTagName("resource");
					if (localNodeList2.getLength() > 0) {
						for (int j = 0; j < localNodeList2.getLength(); j++) {
							localNode2 = localNodeList2.item(j);
							localNamedNodeMap = localNode2.getAttributes();
							String str2 = "";
							for (int k = 0; k < localNamedNodeMap.getLength(); k++) {
								localNode1 = localNamedNodeMap.item(k);
								
								
								
								if (localNode1.getNodeName().equalsIgnoreCase(
										"href")) {
									str2 = localNode1.getNodeValue();
								}
								if (localNode1.getNodeName().equalsIgnoreCase(
										"identifier")) {
									str1 = localNode1.getNodeValue();
									if (str1.equalsIgnoreCase(paramString1)) {
										i = 1;
									} else {
										i = 0;
									}
								}
								if ((i == 1) && (!str2.equals(""))
										&& (str2 != null)) {
									
									/*Anupam*/
									
									String source="ContentFrontController?unit_id="+paramString2+"&file_name="+str2;
									
									//byte[] source = DataBaseLayer.getContentFile(paramString2,str2);
									//System.out.println(source);
									/*End*/
								
									
									String str3 = Host
											.getServerDocumentPathEngine();
	
									String str4 = new String();
									if ((str2.startsWith("http://"))
											|| (str2.startsWith("https://"))
											|| (str2.startsWith("ftp://"))) {
										localObject = str2;
										System.out
												.println("------->>>>>>>>---inside if-----resourceRef----- "
														+ (String) localObject);
									} else {
									//	localObject = Host
							//					.getServerDocumentPathEngine()
						//						+ paramString2 + "/" + str2;
										localObject=source; //Anupam
										
										if (localDebugSwitch.ON) {
											System.out
													.println("------->>>>>>>>--------resourceRef----- "
															+ (String) localObject);
										}
									}
									if (((String) localObject).endsWith("asmt")) {
										String str5 = Host
												.getServerDocumentPathEngine()
												+ paramString2 + "/" + str2;
										if (localDebugSwitch.ON) {
											System.out
													.println("------->>>>>>>>--------s----- "
															+ str5);
										}
										try {
											Base64 localBase64 = new Base64();
											localObject = "./learnityasmtserver.assessmentportal.embeddedasmt.Assessment?s="
													+ localBase64.encode(str5);
										} catch (EncoderException localEncoderException) {
										}
									}
						//			return source;
									return (String) localObject;
								}
							
							}
						}
					}
				}
			}
			
			
			return (String) localObject;
		}
	
		public String UploadUnit(String paramString1, String paramString2,
				FileTransfer paramFileTransfer,String target) {
			Object localObject1 = "Unit Imported Successfully";
			ArrayList<String> identifierList = new ArrayList<String>();
			DebugSwitch localDebugSwitch = new DebugSwitch(
					this);
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
			String str1 = (String) localHttpSession.getAttribute("user_id");
	
			GregorianCalendar localGregorianCalendar = new GregorianCalendar();
			Date localDate = new Date();
			localGregorianCalendar.setTime(localDate);
	
			String[] arrayOfString = { "January", "Feburary", "March", "April",
					"May", "June", "July", "August", "September", "October",
					"November", "December" };
	
			int i = localGregorianCalendar.get(2) + 1;
	
			String str2 = localGregorianCalendar.get(10) + ":"
					+ localGregorianCalendar.get(12);
			String str3 = arrayOfString[localGregorianCalendar.get(2)] + " "
					+ localGregorianCalendar.get(5) + ", "
					+ localGregorianCalendar.get(1);
			String str4 = str3 + " " + str2;
	
			FileUploaderPojo localFileUploaderPojo = null;
			String str5 = new String();
			try {
				Object localObject2 = "";
	
				String str7 = Host.getServerDocumentPath();
				localObject2 = str7;
				File localFile1 = null;
				if (localDebugSwitch.ON) {
					System.out.println("---111--->>>>>>>-----dir name is : "
							+ (String) localObject2);
				}
				if (localObject2 == null) {
					localObject1 = "Please supply uploadDir parameter";
				}
				localObject2 = str7 + paramString1;
				if (localDebugSwitch.ON) {
					System.out.println("---222--->>>>>>>-----dir name is : "
							+ (String) localObject2);
				}
				File localFile2 = new File((String) localObject2);
				localFile1 = new File((String) localObject2);
				if (!localFile1.exists()) {
					localFile1.mkdir();
				}
				String str8 = "";
	
				localFileUploaderPojo = new FileUploaderPojo(paramFileTransfer);
				String str9 = FileUploaderPojo.getMimeType();
				InputStream localInputStream = FileUploaderPojo.getInputStream();
				String str10 = FileUploaderPojo.getFilename();
				String str11 = (String) localObject2 + File.separator + str10;
				String str12 = "";
				try {
					File localFile3 = new File(str11);
					if (!localFile3.exists()) {
						localFile3.createNewFile();
					}
					if (localDebugSwitch.ON) {
						System.out
								.println("---333--->>>>>>>-----zip path+file is : "
										+ str11);
					}
					FileOutputStream localFileOutputStream = new FileOutputStream(
							str11);
	
					int j = 0;
					final byte[] localObject3 = new byte[1024];
					try {
						while ((localInputStream != null)
								&& ((j = localInputStream
										.read((byte[]) localObject3)) != -1)) {
							localFileOutputStream
									.write((byte[]) localObject3, 0, j);
						}
						PrintStream localPrintStream = new PrintStream(
								localFileOutputStream);
						localPrintStream.println(str12);
						localPrintStream.close();
					} catch (IOException localIOException) {
						localObject1 = "Unit Import Failed";
						if (localDebugSwitch.ON) {
							System.out
									.println("--------->>>>>>>-----IOException : "
											+ localIOException);
						}
					}
				} catch (Exception localException2) {
					localObject1 = "Unit Import Failed";
					if (localDebugSwitch.ON) {
						System.out.println("--------->>>>>>>-----Exception : "
								+ localException2);
					}
				}
				String str13 = paramString2;
				LMSPackageHandler localLMSPackageHandler = new LMSPackageHandler();
				boolean bool1 = LMSPackageHandler.findManifest(str11);
				if (bool1) {
					if (localDebugSwitch.ON) {
						System.out
								.println("-------->>>>>>>----- goes inside findManifest() path "
										+ str11);
					}
					LMSPackageHandler.extract(str11, "imsmanifest.xml",
							(String) localObject2);
					String manifestFilePath = (String) localObject2 + File.separatorChar
							+ "imsmanifest.xml";
					DataBaseLayer.insertCourse("csformat", paramString1,
							manifestFilePath, str3, str1,target);
					if (localDebugSwitch.ON) {
						System.out
								.println("-------->>>>>>>-----insertCourse() successful ");
					}
					LMSManifestHandler localLMSManifestHandler = new LMSManifestHandler();
					InputSource localInputSource = setUpInputSource(manifestFilePath);
					localLMSManifestHandler.setCourseID(paramString1);
					localLMSManifestHandler.setCourseName(paramString2);
					localLMSManifestHandler.setFileToParse(localInputSource);
					if (localDebugSwitch.ON) {
						System.out
								.println("-------->>>>>>>-----inside processManifest() ... ");
					}
	//				boolean bool2 = localLMSManifestHandler.processManifest();
					
					FileInputStream fileInputStream = new FileInputStream(manifestFilePath);
					DOMParser parser = new DOMParser();
					parser.parse(fileInputStream);
					XMLDocument doc = parser.getDocument();
					NodeList nl = doc.getElementsByTagName("item");
			    	int sequenceNo = 0;
			    	//ArrayList<String> identifierList = new ArrayList<String>();
			    	ArrayList<String> titleList = new ArrayList<String>();
			    	ArrayList<Integer> sequenceList = new ArrayList<Integer>();
					for (int loop = 0; loop < nl.getLength(); ++loop) {
			        	Element item = (Element)nl.item(loop);
			        	String identifier = item.getAttribute("identifier");
			        	identifierList.add(identifier);
			        	Node titleNode = item.getFirstChild();
			        	Node titleTextNode = titleNode.getFirstChild();
			        	String title = titleTextNode.getNodeValue();
			        	titleList.add(title);
			        	sequenceList.add(sequenceNo);	        	
			        	sequenceNo++;
					}
		        
					DataBaseLayer.insertManifestItemInfo(paramString1, identifierList, titleList, sequenceList);
					
					if (localDebugSwitch.ON) {
						System.out
								.println("-------->>>>>>>-----processManifest() successful ");
					}
					try {
						String str14 = Host.getServerDocumentPath() + str13
								+ File.separatorChar + "sequence.obj";
						if (localDebugSwitch.ON) {
							System.out
									.println("-------->>>>>>>-----sequencingFileName : "
											+ str14);
						}
						File localFile4 = new File(str14);
						String str15 = localFile4.getParent();
						if (str15 != null) {
							File localObject4 = new File(str15);
							if (!((File) localObject4).exists()) {
								((File) localObject4).mkdirs();
							}
						}
						Object localObject4 = new FileOutputStream(localFile4);
						ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(
								(OutputStream) localObject4);
						localObjectOutputStream.writeObject(localLMSManifestHandler
								.getOrgsCopy());
						localObjectOutputStream.flush();
						localObjectOutputStream.close();
					} catch (Exception localException3) {
						localObject1 = "Unit Import Failed";
						log.error("Error = " + localException3.toString());
						localException3.printStackTrace();
					}
				}
				if (localDebugSwitch.ON) {
					System.out
							.println("----111.1---->>>>>>>-----get the zip file ... "
									+ str11);
				}
				Object localObject3 = getZipFiles(str11, paramString2, paramString1,identifierList,target);
				if (!((String) localObject3).equals("")) {
					localObject1 = localObject3;
				}
			} catch (Exception localException1) {
				localObject1 = "Unit Import Failed";
				str5 = localException1.toString();
				localException1.printStackTrace();
			}
			String str6 = Host.getServerDocumentPath() + paramString1
					+ File.separatorChar + FileUploaderPojo.getFilename();
			deleteDirectory(new File(str6));
			return (String) localObject1;
		}
	
		public InputSource setUpInputSource(String paramString) {
			InputSource localInputSource = new InputSource();
			localInputSource = setupFileSource(paramString);
			return localInputSource;
		}
	
		public InputSource setupFileSource(String paramString) {
			DebugSwitch localDebugSwitch = new DebugSwitch(
					this);
			log.entry("InputSource setupFileSource()");
			try {
				File localFile = new File(paramString);
				if (localFile.isFile()) {
					FileReader localFileReader = new FileReader(localFile);
					return new InputSource(localFileReader);
				}
				System.out.println("--------->>>>>>>-----not a file : "
						+ paramString);
			} catch (NullPointerException localNullPointerException) {
				localNullPointerException.printStackTrace();
				System.out
						.println("-------->>>>>>>-----setupFileSource() : NullPointerException : ");
	
				log.error("Null pointer exception" + localNullPointerException);
			} catch (SecurityException localSecurityException) {
				localSecurityException.printStackTrace();
				System.out
						.println("-------->>>>>>>-----setupFileSource() : SecurityException : ");
	
				log.error("Security Exception" + localSecurityException);
			} catch (FileNotFoundException localFileNotFoundException) {
				localFileNotFoundException.printStackTrace();
	
				System.out
						.println("-------->>>>>>>-----setupFileSource() : FileNotFoundException : ");
	
				log.error("File Not Found Exception" + localFileNotFoundException);
			} catch (Exception localException) {
				localException.printStackTrace();
				System.out
						.println("-------->>>>>>>-----setupFileSource() : FileNotFoundException : ");
	
				log.error("File Not Found Exception" + localException);
			}
			return new InputSource();
		}
	
		public String getZipFiles(String paramString1, String paramString2,
				String paramString3, ArrayList<String> identifierList,String target) {
	
		//Need to parse identifierList one by one and send to DatabaseLayer.insertcontent	
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
			String str1 = (String) localHttpSession.getAttribute("user_id");
			
			String str2 = "";
			try {
				String str3 = "" + Host.getServerDocumentPath() + paramString3
						+ File.separator;
				byte[] arrayOfByte = new byte[1024];
				ZipInputStream localZipInputStream = null;
	
				localZipInputStream = new ZipInputStream(new FileInputStream(
						paramString1));
	
				ZipEntry localZipEntry = localZipInputStream.getNextEntry();
				
				int iden_no=0;
				while (localZipEntry != null) {
					String str4 = localZipEntry.getName();
					System.out.println("entryname " + str4);
					
					/*
					String iden=identifierList.get(iden_no);
					iden_no++;
			*/		
				//Edited by Anupam
					if (target.equals("DB")){
					 InputStream is =  localZipInputStream;
						
						int size=20000000;
						
						//DataBaseLayer.insertContent("content_management_object",localFile1.getName(), paramString3, str1,"0",size); //Only have to get size correctly
						DataBaseLayer.insertContent("content_management_object",str4, paramString3, str1,"0",size,"Database"); //Only have to get size correctly
						
						DataBaseLayer.updateUnitContent(paramString3,is,size,str4);
				
				
						DataBaseLayer.updateUnitSize(size,paramString3,str4);
											
						localZipInputStream.closeEntry();
						localZipEntry = localZipInputStream.getNextEntry();
					
					}else{
				 //End here
					
					File localFile1 = new File(str3 + str4);
					File localFile2 = null;
					if (localZipEntry.isDirectory()) {
						localFile1.mkdirs();
						localZipInputStream.closeEntry();
						localZipEntry = localZipInputStream.getNextEntry();
					} else {
						String str5 = localFile1.getParent();
						System.out.println("===========parent=============" + str5+"  identifire=");
						if (str5 != null) {
							 localFile2 = new File(str5);
							System.out
									.println("===============parentFile==============="
											+ localFile2);
							
							if (!localFile2.exists()) {
								localFile2.mkdirs();
							}
						}
						FileOutputStream localFileOutputStream = new FileOutputStream(
								str3 + str4);
						
						//if (target.equals("FS")){
						int i;
						while ((i = localZipInputStream.read(arrayOfByte, 0, 1024)) > -1) {
							localFileOutputStream.write(arrayOfByte, 0, i);
						}
						//Edited by Anupam
						long fileLength = localFile1.length();
						int size = (int)fileLength;
						DataBaseLayer.insertContent("content_management_object",str4, paramString3, str1,null,size,str3+File.separatorChar+ str4);
						//End Here
						
						localFileOutputStream.close();
						localZipInputStream.closeEntry();
						localZipEntry = localZipInputStream.getNextEntry();
					}
					
				}
					
				}
				localZipInputStream.close();
			} catch (Exception localException) {
				str2 = "Unit Import Failed";
				localException.printStackTrace();
			}
			return str2;
		}
	
		public String getNextResourceNode(String paramString) {
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
			String str1 = (String) localHttpSession.getAttribute("unit_id");
			String str2 = (String) localHttpSession.getAttribute("user_id");
			DebugSwitch localDebugSwitch = new DebugSwitch(
					this);
	
			Document localDocument = DataBaseLayer.parse(str1, "csformat");
	
			String str3 = DataBaseLayer.getLearning_Style(str2);
			ImsManifest localImsManifest = new ImsManifest(localDocument, str2,
					str1);
			Hashtable localHashtable = (Hashtable) localHttpSession
					.getAttribute("SESSION_COURSE_ITEM");
			Object localObject = localHashtable.get(str1);
			System.out.println("======>>>>>>=======1==oIdentifier= " + localObject);
			String str4 = null;
	
			GregorianCalendar localGregorianCalendar = new GregorianCalendar();
			Date localDate = new Date();
			localGregorianCalendar.setTime(localDate);
			String str5 = localGregorianCalendar.get(11) + ":"
					+ localGregorianCalendar.get(12) + ":"
					+ localGregorianCalendar.get(13);
			String str6 = localGregorianCalendar.get(1) + "-"
					+ (localGregorianCalendar.get(2) + 1) + "-"
					+ localGregorianCalendar.get(5);
	
			InitializeSCO localInitializeSCO = new InitializeSCO();
			if (localObject == null) {
				if (paramString.equals("1")) {
					str4 = localImsManifest
							.getIdentifierForFirstItemSkippingNoContent(str3);
				}
				if (paramString.equals("2")) {
					str4 = localImsManifest
							.getIdentifierForLastItemSkippingNoContent(str3);
				}
				localInitializeSCO.SCOInitialize(str2, str1, str4);
				DataBaseLayer.InsertIntoLearnerLoginInfo(str2, str1, str4, str6,
						localHttpSession.getId(), str5);
				System.out.println("======>>>>>>=======2==identifier= " + str4);
			} else {
				str4 = localObject.toString();
				DataBaseLayer.updateLoginInfo(str2, str4, localHttpSession.getId(),
						str5, str6, str1);
				localInitializeSCO.SCOFinish(str2, str1, str4);
				if (paramString.equals("1")) {
					str4 = localImsManifest
							.getIdentifierOfNextNodeSkippingNoContent(str4, str3);
				}
				if (paramString.equals("2")) {
					str4 = localImsManifest
							.getIdentifierOfPreviousNodeSkippingNoContent(str4,
									str3);
				}
				DataBaseLayer.InsertIntoLearnerLoginInfo(str2, str1, str4, str6,
						localHttpSession.getId(), str5);
				localInitializeSCO.SCOInitialize(str2, str1, str4);
				System.out.println("======>>>>>>=======3==identifier= " + str4);
			}
			localHashtable.put(str1, str4);
			localHttpSession.setAttribute("SESSION_COURSE_ITEM", localHashtable);
			return str4;
		}
	
		public String getNextResourceNode1(String paramString) {
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
			String str1 = (String) localHttpSession.getAttribute("unit_id");
			String str2 = (String) localHttpSession.getAttribute("user_id");
			DebugSwitch localDebugSwitch = new DebugSwitch(
					this);
	
			Document localDocument = DataBaseLayer.parse(str1, "csformat");
	
			String str3 = DataBaseLayer.getLearning_Style(str2);
			ImsManifest localImsManifest = new ImsManifest(localDocument, str2,
					str1);
			Hashtable localHashtable = (Hashtable) localHttpSession
					.getAttribute("SESSION_COURSE_ITEM");
			Object localObject = localHashtable.get(str1);
			System.out.println("======>>>>>>=======1==oIdentifier= " + localObject);
			String str4 = null;
	
			GregorianCalendar localGregorianCalendar = new GregorianCalendar();
			Date localDate = new Date();
			localGregorianCalendar.setTime(localDate);
			String str5 = localGregorianCalendar.get(11) + ":"
					+ localGregorianCalendar.get(12) + ":"
					+ localGregorianCalendar.get(13);
			String str6 = localGregorianCalendar.get(1) + "-"
					+ (localGregorianCalendar.get(2) + 1) + "-"
					+ localGregorianCalendar.get(5);
	
			InitializeSCO localInitializeSCO = new InitializeSCO();
			if (localObject == null) {
				if (paramString.equals("1")) {
					str4 = localImsManifest
							.getIdentifierForFirstItemSkippingNoContent(str3);
				}
				if (paramString.equals("2")) {
					str4 = localImsManifest
							.getIdentifierForLastItemSkippingNoContent(str3);
				}
				localInitializeSCO.SCOInitialize(str2, str1, str4);
				DataBaseLayer.InsertIntoLearnerLoginInfo(str2, str1, str4, str6,
						localHttpSession.getId(), str5);
				System.out.println("======>>>>>>=======2==identifier= " + str4);
			} else {
				str4 = localObject.toString();
				DataBaseLayer.updateLoginInfo(str2, str4, localHttpSession.getId(),
						str5, str6, str1);
				localInitializeSCO.SCOFinish(str2, str1, str4);
				if (paramString.equals("1")) {
					str4 = localImsManifest
							.getIdentifierOfNextNodeSkippingNoContent(str4, str3);
				}
				if (paramString.equals("2")) {
					str4 = localImsManifest
							.getIdentifierOfPreviousNodeSkippingNoContent(str4,
									str3);
				}
				DataBaseLayer.InsertIntoLearnerLoginInfo(str2, str1, str4, str6,
						localHttpSession.getId(), str5);
				localInitializeSCO.SCOInitialize(str2, str1, str4);
				System.out.println("======>>>>>>=======3==identifier= " + str4);
			}
			localHashtable.put(str1, str4);
			localHttpSession.setAttribute("SESSION_COURSE_ITEM", localHashtable);
			String str7 = localImsManifest.getResourseRef(str4, str3);
			System.out
					.println("======>>>>>>resourseref=======getNextResourceNode1()=== "
							+ str7);
			String str8 = getResourseURL(str7, localDocument, str1);
			System.out.println("======>>>>>>=======getNextResourceNode1()==URL= "
					+ str8);
			return str8;
		}
	
		public String getItemTitle(String paramString) {
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
			String str1 = (String) localHttpSession.getAttribute("unit_id");
			DebugSwitch localDebugSwitch = new DebugSwitch(
					this);
			if (localDebugSwitch.ON) {
				System.out
						.println("------------->>>>>>>>>>>>>--------------- inside getItemTitle() ... ");
			}
			Document localDocument = DataBaseLayer.parse(str1, "csformat");
			String str2 = "";
			if (localDocument == null) {
				System.out
						.println("------->>>>>>>-----getItemTitle-----No Data Found--------");
			} else {
				NodeList localNodeList = localDocument.getElementsByTagName("item");
				if (localNodeList.getLength() > 0) {
					int i = 0;
					if (i < localNodeList.getLength()) {
						Element localElement = (Element) localNodeList.item(i);
						return localElement.getFirstChild().getFirstChild()
								.getNodeValue();
					}
				}
			}
			return "";
		}
	
		public void setUserSCODetails(String paramString) {
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
			String str1 = (String) localHttpSession.getAttribute("unit_id");
			String str2 = (String) localHttpSession.getAttribute("user_id");
	
			Document localDocument = DataBaseLayer.parse(str1, "csformat");
			DebugSwitch localDebugSwitch = new DebugSwitch(
					this);
			if (localDebugSwitch.ON) {
				System.out
						.println("------------->>>>>>>>>>>>>--------------- inside setUserSCODetails() ... ");
			}
			String str3 = getItemTitle(paramString);
			String str4 = getResourseURL(paramString, localDocument, str1);
			DataBaseLayer.addUserSCODetails(str2, str1, paramString, str3, str4);
		}
	
		public String showHomePage() throws IOException {
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
			String str1 = (String) localHttpSession.getAttribute("unit_id");
			String str2 = (String) localHttpSession.getAttribute("user_id");
	
			String str3 = DataBaseLayer.getLearning_Style(str2);
			String str4 = DataBaseLayer.getStatusHomePage(str1);
			log.debug("************home_page_status*******************" + str4);
			String str5 = "";
			if (str4.equals("Empty")) {
				str5 = "<font color=\"#ff0000\"><center></center></font>\n";
				log.debug("************html*****1**************" + str5);
			} else {
				String str6;
				if (str4.equals("Custom")) {
					str6 = DataBaseLayer.getTitleHomePage(str1);
					str5 = "<font color=\"#ff0000\"><center>" + str6
							+ "</center></font>";
					log.debug("************html******2*************" + str5);
				} else if (str4.equals("Status")) {
					str6 = DataBaseLayer.getCourseName(str1);
	
					String str7 = "";
					String str8 = "Na";
					String str9 = "Na";
					String str10 = "";
					Vector localVector = DataBaseLayer.getusageInfo(str1, str2);
					if (localVector != null) {
						for (int j = 0; j < localVector.size(); j++) {
							final String[] localObject1 = (String[]) localVector.elementAt(0);
							str7 = localObject1[0];
							str8 = localObject1[1];
							str9 = localObject1[2];
							str10 = localObject1[3];
						}
					}
					int Total = 0;
					Vector localObject1 = DataBaseLayer.getUserscoInfo(str1, str2);
					if (localObject1 != null) {
						for (int k = 0; k < (localObject1).size(); k += 3) {
							int time = (Integer) localObject1.elementAt(k + 1);
							Total += time;
						}
					}
					String str11 = "" + Total + " Sec";
					String str12 = DataBaseLayer.getNoOfTimeUsed(str1, str2);
	
					Document localDocument1 = null;
					DocumentBuilder localDocumentBuilder = null;
					DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory
							.newInstance();
					try {
						localDocumentBuilderFactory.setValidating(true);
						localDocumentBuilder = localDocumentBuilderFactory
								.newDocumentBuilder();
						localDocument1 = localDocumentBuilder.newDocument();
					} catch (ParserConfigurationException localParserConfigurationException) {
					}
					Element localElement1 = localDocument1.createElement("div");
					localDocument1.appendChild(localElement1);
	
					Element localElement2 = localDocument1.createElement("table");
					localElement1.appendChild(localElement2);
	
					Element localElement3 = localDocument1.createElement("tr");
					localElement2.appendChild(localElement3);
					Element localElement4 = localDocument1.createElement("td");
					localElement3.appendChild(localElement4);
					Element localElement5 = localDocument1.createElement("div");
					localElement5.appendChild(localDocument1
							.createTextNode("Usage Information"));
					localElement5
							.setAttribute(
									"style",
									"background-color:#907A53;color:black;text-align:center;font-size:14px;font-family;tohama,verdana;font-weight:bold;");
					localElement4.appendChild(localElement5);
	
					Element localElement6 = localDocument1.createElement("tr");
					localElement2.appendChild(localElement6);
					Element localElement7 = localDocument1.createElement("td");
					localElement6.appendChild(localElement7);
					Element localElement8 = localDocument1.createElement("div");
					localElement8.appendChild(localDocument1
							.createTextNode("Unit Name"));
					localElement8
							.setAttribute(
									"style",
									"background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:bold;");
					localElement7.appendChild(localElement8);
					Element localElement9 = localDocument1.createElement("td");
					localElement6.appendChild(localElement9);
					Element localElement10 = localDocument1.createElement("div");
					localElement10.appendChild(localDocument1.createTextNode(str6));
					localElement10
							.setAttribute(
									"style",
									"background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:normal;");
					localElement9.appendChild(localElement10);
	
					Element localElement11 = localDocument1.createElement("tr");
					localElement2.appendChild(localElement11);
					Element localElement12 = localDocument1.createElement("td");
					localElement11.appendChild(localElement12);
					Element localElement13 = localDocument1.createElement("div");
					localElement13.appendChild(localDocument1
							.createTextNode("Date Regstered"));
					localElement13
							.setAttribute(
									"style",
									"background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:bold;");
					localElement12.appendChild(localElement13);
					Element localElement14 = localDocument1.createElement("td");
					localElement11.appendChild(localElement14);
					Element localElement15 = localDocument1.createElement("div");
					localElement15.appendChild(localDocument1.createTextNode(str7));
					localElement15
							.setAttribute(
									"style",
									"background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:normal;");
					localElement14.appendChild(localElement15);
	
					Element localElement16 = localDocument1.createElement("tr");
					localElement2.appendChild(localElement16);
					Element localElement17 = localDocument1.createElement("td");
					localElement16.appendChild(localElement17);
					Element localElement18 = localDocument1.createElement("div");
					localElement18.appendChild(localDocument1
							.createTextNode("Access Allowed Till"));
					localElement18
							.setAttribute(
									"style",
									"background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:bold;");
					localElement17.appendChild(localElement18);
					Element localElement19 = localDocument1.createElement("td");
					localElement16.appendChild(localElement19);
					Element localElement20 = localDocument1.createElement("div");
					localElement20.appendChild(localDocument1.createTextNode(str8));
					localElement20
							.setAttribute(
									"style",
									"background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:normal;");
					localElement19.appendChild(localElement20);
	
					Element localElement21 = localDocument1.createElement("tr");
					localElement2.appendChild(localElement21);
					Element localElement22 = localDocument1.createElement("td");
					localElement21.appendChild(localElement22);
					Element localElement23 = localDocument1.createElement("div");
					localElement23.appendChild(localDocument1
							.createTextNode("Total Access Time"));
					localElement23
							.setAttribute(
									"style",
									"background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:bold;");
					localElement22.appendChild(localElement23);
					Element localElement24 = localDocument1.createElement("td");
					localElement21.appendChild(localElement24);
					Element localElement25 = localDocument1.createElement("div");
					localElement25.appendChild(localDocument1.createTextNode(str9));
					localElement25
							.setAttribute(
									"style",
									"background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:normal;");
					localElement24.appendChild(localElement25);
	
					Element localElement26 = localDocument1.createElement("tr");
					localElement2.appendChild(localElement26);
					Element localElement27 = localDocument1.createElement("td");
					localElement26.appendChild(localElement27);
					Element localElement28 = localDocument1.createElement("div");
					localElement28.appendChild(localDocument1
							.createTextNode("Time Used"));
					localElement28
							.setAttribute(
									"style",
									"background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:bold;");
					localElement27.appendChild(localElement28);
					Element localElement29 = localDocument1.createElement("td");
					localElement26.appendChild(localElement29);
					Element localElement30 = localDocument1.createElement("div");
					localElement30
							.appendChild(localDocument1.createTextNode(str11));
					localElement30
							.setAttribute(
									"style",
									"background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:normal;");
					localElement29.appendChild(localElement30);
	
					Element localElement31 = localDocument1.createElement("tr");
					localElement2.appendChild(localElement31);
					Element localElement32 = localDocument1.createElement("td");
					localElement31.appendChild(localElement32);
					Element localElement33 = localDocument1.createElement("div");
					localElement33.appendChild(localDocument1
							.createTextNode("No of Time Accessed"));
					localElement33
							.setAttribute(
									"style",
									"background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:bold;");
					localElement32.appendChild(localElement33);
					Element localElement34 = localDocument1.createElement("td");
					localElement31.appendChild(localElement34);
					Element localElement35 = localDocument1.createElement("div");
					localElement35
							.appendChild(localDocument1.createTextNode(str12));
					localElement35
							.setAttribute(
									"style",
									"background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:normal;");
					localElement34.appendChild(localElement35);
	
					Element localElement36 = localDocument1.createElement("table");
					localElement1.appendChild(localElement36);
					Element localElement37 = localDocument1.createElement("tr");
					localElement36.appendChild(localElement37);
					Element localElement38 = localDocument1.createElement("td");
					localElement38.setAttribute("colspan", "2");
					localElement37.appendChild(localElement38);
					Element localElement39 = localDocument1.createElement("div");
					localElement38.appendChild(localElement39);
					localElement39.appendChild(localDocument1
							.createTextNode("Unit Information"));
					localElement39
							.setAttribute(
									"style",
									"background-color:#907A53;color:black;text-align:left;font-size:14px;font-family;tohama,verdana;font-weight:bold;");
	
					int i = 0;
					String str13 = null;
					Object localObject2 = null;
	
					Document localDocument2 = DataBaseLayer.parse(str1, "csformat");
	
					NodeList localNodeList = localDocument2
							.getElementsByTagName("organization");
					Element localElement40;
					for (int n = 0; n < localNodeList.getLength(); n++) {
						localElement40 = (Element) localNodeList.item(n);
						if (localElement40.getAttributeNode("identifier")
								.getValue().equals(str3)) {
							localObject2 = localElement40;
							break;
						}
					}
					if (localObject2 == null) {
						str13 = ((Element) localDocument2.getElementsByTagName(
								"organizations").item(0)).getAttributeNode(
								"default").getValue();
						for (int n = 0; n < localNodeList.getLength(); n++) {
							localElement40 = (Element) localNodeList.item(n);
							if (localElement40.getAttributeNode("identifier")
									.getValue().equals(str13)) {
								localObject2 = localElement40;
								break;
							}
						}
					}
					Object localObject3;
					if (localObject2 == null) {
						localObject3 = (Element) localNodeList.item(0);
						localObject2 = localObject3;
					}
					if (localObject2 != null) {
						localObject3 = localObject2;
						showIntroductionTree((Element) localObject3, " ", str2,
								str1, localElement36, localDocument1,
								localDocument2);
					}
					try {
						str5 = getHTML(localDocument1);
					} catch (Exception localException) {
					}
					String str14 = "" + i;
				} else {
					str5 = "<font color=\"#ff0000\"><center></center></font>\n";
					log.debug("************html***3****************" + str5);
				}
			}
			log.debug("************html***4****************" + str5);
	
			return str5;
		}
	
		public void showIntroductionTree(Element paramElement1,
				String paramString1, String paramString2, String paramString3,
				Element paramElement2, Document paramDocument1,
				Document paramDocument2) {
			NodeList localNodeList = paramElement1.getChildNodes();
			NamedNodeMap localNamedNodeMap = null;
			String str1 = null;
			paramString1 = paramString1 + "    ";
			Node localNode1 = null;
			Node localNode2 = null;
			String str2 = null;
			String str3 = null;
			String str4 = null;
			String str5 = null;
			System.out.println("========nl======" + localNodeList
					+ "==========nl.getLength()===" + localNodeList.getLength());
			for (int i = 0; i < localNodeList.getLength(); i++) {
				localNode1 = localNodeList.item(i);
				System.out.println("========nodeItem==getNodeName===="
						+ localNode1.getNodeName());
	
				str1 = localNode1.getNodeName();
				System.out.println("=================tagname========" + str1);
				if (str1.equalsIgnoreCase("item")) {
					localNamedNodeMap = localNode1.getAttributes();
					for (int j = 0; j < localNamedNodeMap.getLength(); j++) {
						localNode2 = localNamedNodeMap.item(j);
						if (localNode2.getNodeName().equalsIgnoreCase("identifier")) {
							str2 = localNode2.getNodeValue();
							System.out.println("strIdentifier in 1 if============"
									+ str2);
						}
						if (localNode2.getNodeName().equalsIgnoreCase(
								"identifierref")) {
							str3 = localNode2.getNodeValue();
						}
					}
					Element localElement1 = (Element) localNodeList.item(i);
					str4 = localElement1.getElementsByTagName("title").item(0)
							.getFirstChild().getNodeValue();
					try {
						byte[] arrayOfByte = str4.getBytes();
						str4 = new String(arrayOfByte, "utf-8");
					} catch (Exception localException) {
						System.out.println("Error:" + localException);
					}
					str5 = getResourseURL(str2, paramDocument2, paramString3);
					Element localElement3;
					Element localElement4;
					Element localElement5;
					Element localElement6;
					if (!str5.equals("")) {
						Element localElement2 = paramDocument1.createElement("tr");
						paramElement2.appendChild(localElement2);
						localElement3 = paramDocument1.createElement("td");
						localElement2.appendChild(localElement3);
						localElement4 = paramDocument1.createElement("div");
						localElement3.appendChild(localElement4);
						localElement4.appendChild(paramDocument1
								.createTextNode(paramString1 + str4));
						localElement4
								.setAttribute(
										"style",
										"background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:bold;");
	
						localElement5 = paramDocument1.createElement("td");
						localElement2.appendChild(localElement5);
						localElement6 = paramDocument1.createElement("div");
						localElement5.appendChild(localElement4);
						localElement6.appendChild(paramDocument1
								.createTextNode(DataBaseLayer.getStatus(
										paramString2, paramString3, str2)));
						localElement6
								.setAttribute(
										"style",
										"background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:normal");
					} else {
						Element localElement2 = paramDocument1.createElement("tr");
						paramElement2.appendChild(localElement2);
						localElement3 = paramDocument1.createElement("td");
						localElement2.appendChild(localElement3);
						localElement4 = paramDocument1.createElement("div");
						localElement3.appendChild(localElement4);
						localElement4.appendChild(paramDocument1
								.createTextNode(paramString1 + str4));
						localElement4
								.setAttribute(
										"style",
										"background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:bold;");
						localElement5 = paramDocument1.createElement("td");
						localElement2.appendChild(localElement5);
						localElement6 = paramDocument1.createElement("div");
						localElement5.appendChild(localElement6);
						localElement6.appendChild(paramDocument1
								.createTextNode(DataBaseLayer.getStatus(
										paramString2, paramString3, str2)));
						localElement6
								.setAttribute(
										"style",
										"background-color:#FAEEC0;color:black;text-align:left;font-size:11px;font-family;tohama,verdana;font-weight:normal");
					}
					Element localElement2 = (Element) localNodeList.item(i);
					str5 = "";
					str4 = null;
					showIntroductionTree(localElement2, paramString1, paramString2,
							paramString3, paramElement2, paramDocument1,
							paramDocument2);
				}
			}
		}
	
		public static final String getHTML(Document paramDocument) throws Exception {
			try {
				OutputFormat localOutputFormat = new OutputFormat(paramDocument);
				StringWriter localStringWriter = new StringWriter();
				XMLSerializer localXMLSerializer = new XMLSerializer(
						localStringWriter, localOutputFormat);
				localXMLSerializer.asDOMSerializer();
				localXMLSerializer.serialize(paramDocument.getDocumentElement());
	
				return localStringWriter.toString();
			} catch (Exception localException) {
				throw new Exception("XML to String Err: "
						+ localException.getMessage());
			}
		}
	
		public void setSearchKeyToSession(String paramString) {
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
			localHttpSession.setAttribute("SEARCH_CRITERIA", paramString);
		}
	
		public String getSearchResult() {
			String str1 = "";
			String str2 = "";
			String str3 = "";
			NamedNodeMap localNamedNodeMap = null;
			Node localNode1 = null;
			Node localNode2 = null;
			Hashtable localHashtable = new Hashtable();
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
			String str4 = (String) localHttpSession.getAttribute("unit_id");
			String str5 = (String) localHttpSession.getAttribute("user_id");
	
			Document localDocument = DataBaseLayer.parse(str4, "csformat");
			String str6 = (String) localHttpSession.getAttribute("SEARCH_CRITERIA");
			str1 = "You searched for : " + str6 + ".";
			if ((!str6.equals("")) && (str6 != null)) {
				localHashtable = DataBaseLayer.getSearchResultFromSCO(str4, str5,
						str6);
				str1 = str1 + "<br>" + localHashtable.size()
						+ "results returned.<br>";
				Set localSet = localHashtable.keySet();
				String str7 = null;
				Iterator localIterator = localSet.iterator();
				int i = 1;
				while (localIterator.hasNext()) {
					str7 = (String) localIterator.next();
					Vector localVector = (Vector) localHashtable.get(str7);
					String str8 = (String) localVector.elementAt(0);
					String str9 = (String) localVector.elementAt(1);
					NodeList localNodeList = localDocument
							.getElementsByTagName("item");
					if (localNodeList.getLength() > 0) {
						for (int j = 0; j < localNodeList.getLength(); j++) {
							localNode1 = localNodeList.item(j);
	
							String str10 = localNode1.getNodeName();
							if (str10.equalsIgnoreCase("item")) {
								localNamedNodeMap = localNode1.getAttributes();
								for (int k = 0; k < localNamedNodeMap.getLength(); k++) {
									localNode2 = localNamedNodeMap.item(k);
									if (localNode2.getNodeName().equalsIgnoreCase(
											"identifier")) {
										str2 = localNode2.getNodeValue();
									}
									if (localNode2.getNodeName().equalsIgnoreCase(
											"identifierref")) {
										str3 = localNode2.getNodeValue();
									}
								}
								if (str9.equalsIgnoreCase(str2)) {
									str1 = str1
											+ "<br>"
											+ i
											+ ". <a href=\""
											+ getResourseURL(str3, localDocument,
													str4) + "\">" + str8
											+ "</a><br>";
								}
							}
						}
					}
					i++;
				}
			} else {
				str1 = "Please Enter A Valid Search String";
			}
			str6 = "";
			localHttpSession.setAttribute("SEARCH_CRITERIA", str6);
			return str1;
		}
	
		public String[] getEmbeddedAssessment(String paramString) {
			String[] arrayOfString = new String[2];
			System.out.println("url==getEmbeddedAssessment=== " + paramString);
			String str1 = paramString.substring(paramString.indexOf('/') + 1);
			String str2 = str1.substring(str1.indexOf('/') + 1);
			String str3 = str2.substring(str2.indexOf('/') + 1);
			String str4 = str3.substring(str3.indexOf('/') + 1);
	
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
			String str5 = (String) localHttpSession.getAttribute("unit_id");
			System.out.println(" ======courseid===getEmbeddedAssessment===== "
					+ str5);
			arrayOfString[0] = str4;
			arrayOfString[1] = str5;
			return arrayOfString;
		}
	
		public boolean deleteDirectory(File paramFile) {
			if ((paramFile.exists()) && (paramFile.isDirectory())) {
				File[] arrayOfFile = paramFile.listFiles();
				for (int i = 0; i < arrayOfFile.length; i++) {
					if (arrayOfFile[i].isDirectory()) {
						deleteDirectory(arrayOfFile[i]);
					} else {
						arrayOfFile[i].delete();
					}
				}
			}
			return paramFile.delete();
		}
	
		public void zipFolder(String paramString1, String paramString2) {
			try {
				this.fileWriter = new FileOutputStream(paramString2);
				this.zip = new ZipOutputStream(this.fileWriter);
				addFolderToZip("", paramString1);
				return;
			} catch (Exception localException2) {
				System.out.println("Exception in zipFolder()");
				localException2.printStackTrace();
			} finally {
				try {
					this.zip.flush();
					this.zip.close();
				} catch (Exception localException4) {
					localException4.printStackTrace();
				}
			}
		}
	
		private void addToZip(String paramString1, String paramString2) {
			File localFile = new File(paramString2);
			if (localFile.isDirectory()) {
				addFolderToZip(paramString1, paramString2);
			} else {
				byte[] arrayOfByte = new byte['?'];
	
				FileInputStream localFileInputStream = null;
				try {
					localFileInputStream = new FileInputStream(paramString2);
					if (paramString1.equals("")) {
						this.zip.putNextEntry(new ZipEntry(localFile.getName()));
					} else {
						this.zip.putNextEntry(new ZipEntry(paramString1 + "/"
								+ localFile.getName()));
					}
					int i;
					while ((i = localFileInputStream.read(arrayOfByte)) > 0) {
						this.zip.write(arrayOfByte, 0, i);
					}
				} catch (Exception localException2) {
					System.out.println("Exception in addToZip()");
					localException2.printStackTrace();
				} finally {
					try {
						localFileInputStream.close();
					} catch (Exception localException4) {
						localException4.printStackTrace();
					}
				}
			}
		}
	
		private void addFolderToZip(String paramString1, String paramString2) {
			File localFile1 = new File(paramString2);
			String[] arrayOfString = localFile1.list();
			try {
				for (int i = 0; i < arrayOfString.length; i++) {
					File localFile2 = new File(paramString2 + "/"
							+ arrayOfString[i]);
					if (localFile2.isFile()) {
						addToZip(paramString1, paramString2 + "/"
								+ arrayOfString[i]);
					} else {
						String str = "";
						if (paramString1.equals("")) {
							str = arrayOfString[i];
						} else {
							str = paramString1 + "/" + arrayOfString[i];
						}
						addToZip(str, paramString2 + "/" + arrayOfString[i]);
					}
				}
			} catch (Exception localException) {
				System.out.println("Exception in addFolderToZip()");
				localException.printStackTrace();
			}
		}
	
		public void close() throws IOException {
			if (this.zip != null) {
				this.zip.close();
			}
		}
	
		public FileTransfer UnitExportFile(String paramString) {
			String str1 = "";
			FileInputStream localFileInputStream = null;
			if (!DataBaseLayer.isCourseContentExists(paramString)) {
				return null;
			}
			try {
				str1 = DataBaseLayer.getUnitName(paramString);
				str1 = str1.replaceAll("\\s+", "_");
	
				String str2 = Host.getServerDocumentPath() + str1 + ".zip";
				System.out.println("------>>>>>>------destZipFile-- " + str2);
	
				final String localObject = Host.getServerDocumentPath() + paramString
						+ File.separatorChar;
				System.out.println("------>>>>>>------dirName-- "
						+ (String) localObject);
				zipFolder((String) localObject, str2);
				System.out.println("------>>>>>>------zip creation successful-- ");
				localFileInputStream = new FileInputStream(str2);
			} catch (FileNotFoundException localFileNotFoundException) {
				localFileNotFoundException.printStackTrace();
			}
			String str3 = str1 + ".zip";
			Object localObject = new FileDownloaderPojo(localFileInputStream, "",
					str3);
			return FileDownloaderPojo.returnFileFormat();
		}
	
		public String lunchCourseAll(String paramString1, String paramString2,
				String paramString3, String paramString4, String paramString5) {
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
			localHttpSession.setAttribute("unit_id", paramString3);
			localHttpSession.setAttribute("browser", paramString1);
			localHttpSession.setAttribute("browserVersion", paramString2);
			localHttpSession.setAttribute("bookmarkidentifier", paramString4);
			localHttpSession.setAttribute("bookmarkTopicTitle", paramString5);
			String str1 = (String) localHttpSession.getAttribute("user_id");
	
			GregorianCalendar localGregorianCalendar = new GregorianCalendar();
			Date localDate = new Date();
			localGregorianCalendar.setTime(localDate);
			String str2 = localGregorianCalendar.get(11) + ":"
					+ localGregorianCalendar.get(12) + ":"
					+ localGregorianCalendar.get(13);
			String str3 = localGregorianCalendar.get(1) + "-"
					+ (localGregorianCalendar.get(2) + 1) + "-"
					+ localGregorianCalendar.get(5);
			String str4 = localHttpSession.getId();
	
			DataBaseLayer.insert_into_studynamicinfo(str1, str4, str3 + " " + str2,
					paramString3);
			Hashtable localHashtable = new Hashtable();
			localHttpSession.setAttribute("SESSION_COURSE_ITEM", localHashtable);
	
			return "";
		}
	
		public String createIndex(String paramString) {
			IndexFiles localIndexFiles = new IndexFiles();
			localIndexFiles.createIndex(paramString);
			return ("Finished Indexing the Unit: " + paramString);
		}
	
		public String getTotalUnit() {
			String str1 = "";
			String str2 = "<select name=\"locomboselect\" onchange=\"combo_onclick()\">";
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
			String str3 = (String) localHttpSession.getAttribute("course_id");
			String str4 = (String) localHttpSession.getAttribute("unit_id");
			String str5 = (String) localHttpSession.getAttribute("user_id");
			Vector localVector = DataBaseLayer.getTotalUnit(str5, str3);
			for (int i = 0; i < localVector.size(); i += 2) {
				String str6 = (String) localVector.elementAt(i);
				String str7 = (String) localVector.elementAt(i + 1);
				if (str4.equals(str6)) {
					str1 = str1 + "<option value=\"" + str6
							+ "\" selected=\"selected\">" + str7 + "</option>";
				} else {
					str1 = str1 + "<option value=\"" + str6 + "\">" + str7
							+ "</option>";
				}
			}
			return str2 + "<option value=\"0\">Choose One</option>" + str1
					+ "</select>";
		}
	
		public String setUnitID(String paramString) {
			String str1 = "";
	
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
			String str2 = (String) localHttpSession.getAttribute("course_id");
			localHttpSession.setAttribute("unit_id", paramString);
			return str1;
		}
	
		public String setSessionSequence(String paramString) {
			String str = "";
	
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
			System.out.println("===============sequence===in session======"
					+ paramString);
			localHttpSession.setAttribute("sequence", paramString);
			return str;
		}
	
		public String getNextItemNode(String paramString) {
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
			String str1 = (String) localHttpSession.getAttribute("unit_id");
			String str2 = (String) localHttpSession.getAttribute("user_id");
			String str3 = (String) localHttpSession.getAttribute("sequence");
			System.out.println("===============sequence===== 11 ====" + str3);
			if ((str3 == null) || (str3.equals(""))) {
				str3 = "0";
			}
			System.out.println("===============sequence=========" + str3);
			int i = DataBaseLayer.getMaxSequence(str1);
			System.out.println("===============max_sequence======" + i);
			int j = Integer.parseInt(str3);
			DebugSwitch localDebugSwitch = new DebugSwitch(
					this);
	
			Document localDocument = DataBaseLayer.parse(str1, "csformat");
	
			String str4 = DataBaseLayer.getLearning_Style(str2);
			ImsManifest localImsManifest = new ImsManifest(localDocument, str2,
					str1);
			Hashtable localHashtable = (Hashtable) localHttpSession
					.getAttribute("SESSION_COURSE_ITEM");
			Object localObject = localHashtable.get(str1);
			System.out.println("======>>>>>>=======1==oIdentifier= " + localObject);
			String str5 = null;
	
			GregorianCalendar localGregorianCalendar = new GregorianCalendar();
			Date localDate = new Date();
			localGregorianCalendar.setTime(localDate);
			String str6 = localGregorianCalendar.get(11) + ":"
					+ localGregorianCalendar.get(12) + ":"
					+ localGregorianCalendar.get(13);
			String str7 = localGregorianCalendar.get(1) + "-"
					+ (localGregorianCalendar.get(2) + 1) + "-"
					+ localGregorianCalendar.get(5);
			if (j != i) {
				j += 1;
				System.out.println("===============seq===if===" + j);
				str5 = DataBaseLayer.getNextIdentifierFromSequence(str1, j);
			} else {
				System.out.println("===============seq===else===" + j);
				str5 = "End";
			}
			System.out.println("=========identifier======" + str5);
	
			String str8 = Integer.toString(j);
			setSessionSequence(str8);
			localHashtable.put(str1, str5);
			localHttpSession.setAttribute("SESSION_COURSE_ITEM", localHashtable);
			return str5;
		}
	
		public String getPreviousItemNode(String paramString) {
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
			String str1 = (String) localHttpSession.getAttribute("unit_id");
			String str2 = (String) localHttpSession.getAttribute("user_id");
			String str3 = (String) localHttpSession.getAttribute("sequence");
			System.out.println("===============sequence======" + str3);
			if ((str3 == null) || (str3.equals(""))) {
				str3 = "0";
			}
			int i = DataBaseLayer.getMaxSequence(str1);
			System.out.println("===============max_sequence======" + i);
			int j = Integer.parseInt(str3);
			DebugSwitch localDebugSwitch = new DebugSwitch(
					this);
	
			Document localDocument = DataBaseLayer.parse(str1, "csformat");
	
			String str4 = DataBaseLayer.getLearning_Style(str2);
			ImsManifest localImsManifest = new ImsManifest(localDocument, str2,
					str1);
			Hashtable localHashtable = (Hashtable) localHttpSession
					.getAttribute("SESSION_COURSE_ITEM");
			Object localObject = localHashtable.get(str1);
			System.out.println("======>>>>>>=======1==oIdentifier= " + localObject);
			String str5 = null;
	
			GregorianCalendar localGregorianCalendar = new GregorianCalendar();
			Date localDate = new Date();
			localGregorianCalendar.setTime(localDate);
			String str6 = localGregorianCalendar.get(11) + ":"
					+ localGregorianCalendar.get(12) + ":"
					+ localGregorianCalendar.get(13);
			String str7 = localGregorianCalendar.get(1) + "-"
					+ (localGregorianCalendar.get(2) + 1) + "-"
					+ localGregorianCalendar.get(5);
			if (j != 0) {
				j -= 1;
				str5 = DataBaseLayer.getNextIdentifierFromSequence(str1, j);
			} else {
				str5 = "First";
			}
			String str8 = Integer.toString(j);
			setSessionSequence(str8);
			localHashtable.put(str1, str5);
			localHttpSession.setAttribute("SESSION_COURSE_ITEM", localHashtable);
			return str5;
		}
	
		public String getFirstItemNode(String paramString) {
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
			String str1 = (String) localHttpSession.getAttribute("unit_id");
			String str2 = (String) localHttpSession.getAttribute("user_id");
	
			int i = 0;
			DebugSwitch localDebugSwitch = new DebugSwitch(
					this);
	
			Document localDocument = DataBaseLayer.parse(str1, "csformat");
	
			String str3 = DataBaseLayer.getLearning_Style(str2);
			ImsManifest localImsManifest = new ImsManifest(localDocument, str2,
					str1);
			Hashtable localHashtable = (Hashtable) localHttpSession
					.getAttribute("SESSION_COURSE_ITEM");
			Object localObject = localHashtable.get(str1);
			System.out.println("======>>>>>>=======1==oIdentifier= " + localObject);
			String str4 = null;
	
			GregorianCalendar localGregorianCalendar = new GregorianCalendar();
			Date localDate = new Date();
			localGregorianCalendar.setTime(localDate);
			String str5 = localGregorianCalendar.get(11) + ":"
					+ localGregorianCalendar.get(12) + ":"
					+ localGregorianCalendar.get(13);
			String str6 = localGregorianCalendar.get(1) + "-"
					+ (localGregorianCalendar.get(2) + 1) + "-"
					+ localGregorianCalendar.get(5);
	
			str4 = DataBaseLayer.getNextIdentifierFromSequence(str1, i);
	
			String str7 = Integer.toString(i);
			setSessionSequence(str7);
			localHashtable.put(str1, str4);
			localHttpSession.setAttribute("SESSION_COURSE_ITEM", localHashtable);
			return str4;
		}
	
		public String getLastItemNode(String paramString) {
			WebContext localWebContext = WebContextFactory.get();
			HttpSession localHttpSession = localWebContext.getSession();
			String str1 = (String) localHttpSession.getAttribute("unit_id");
			String str2 = (String) localHttpSession.getAttribute("user_id");
	
			int i = DataBaseLayer.getMaxSequence(str1);
			DebugSwitch localDebugSwitch = new DebugSwitch(
					this);
	
			Document localDocument = DataBaseLayer.parse(str1, "csformat");
	
			String str3 = DataBaseLayer.getLearning_Style(str2);
			ImsManifest localImsManifest = new ImsManifest(localDocument, str2,
					str1);
			Hashtable localHashtable = (Hashtable) localHttpSession
					.getAttribute("SESSION_COURSE_ITEM");
			Object localObject = localHashtable.get(str1);
			System.out.println("======>>>>>>=======1==oIdentifier= " + localObject);
			String str4 = null;
	
			GregorianCalendar localGregorianCalendar = new GregorianCalendar();
			Date localDate = new Date();
			localGregorianCalendar.setTime(localDate);
			String str5 = localGregorianCalendar.get(11) + ":"
					+ localGregorianCalendar.get(12) + ":"
					+ localGregorianCalendar.get(13);
			String str6 = localGregorianCalendar.get(1) + "-"
					+ (localGregorianCalendar.get(2) + 1) + "-"
					+ localGregorianCalendar.get(5);
	
			str4 = DataBaseLayer.getNextIdentifierFromSequence(str1, i);
	
			String str7 = Integer.toString(i);
			setSessionSequence(str7);
			localHashtable.put(str1, str4);
			localHttpSession.setAttribute("SESSION_COURSE_ITEM", localHashtable);
			return str4;
		}
		
		public String uploadManifest(String unit_id,FileTransfer file) throws IOException {
			String html = "";	
			
			WebContext wctx1 = WebContextFactory.get();
			javax.servlet.http.HttpSession mysession = wctx1.getSession();
			String user_id = (String) mysession.getAttribute("user_id");
			System.out.println("user_id===adminuser==="+user_id);		
			String dirName="";
			String strLocation = learnityInit.Host.getServerDocumentPath();
			dirName = strLocation + unit_id;
			File ufile=null;
			System.out.println("dir name is==:"+dirName);
			
			if(dirName == null)
				html="Please supply uploadDir parameter";
	
			ufile = new File(dirName);
			if(!ufile.exists())
				ufile.mkdir(); 
						
			FileOutputStream out; 
			PrintStream p;
					
			FileUploaderPojo fu = new FileUploaderPojo(file);
			String MimeType = fu.getMimeType();
			System.out.println("MimeType======:"+MimeType);
			InputStream is = fu.getInputStream();
			String filename = fu.getFilename();
			String fullPath = dirName+File.separator+filename;
			String content = "";
			String thisLine = "";		
			try{
							
				File f;
				f=new File(fullPath);
				if(!f.exists()){
					f.createNewFile();
				}
				BufferedReader myInput = new BufferedReader
						(new InputStreamReader(is));
				while ((thisLine = myInput.readLine()) != null) 
				{  
	// 				System.out.println("thisLine==="+thisLine);
					if(content.equals(""))
					{
						content = thisLine;
					}
					else {
						content = content +"\n"+ thisLine;
					}
				}
				out = new FileOutputStream(fullPath);
	
				System.out.println("bbbbbbbbbbbbbbbb");
				p = new PrintStream( out );
			
				p.println(content);
	
				p.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			String strSize="";
			File manifestFile=new File(fullPath);
			Long size2=manifestFile.length();
			
			strSize=size2.toString();
			System.out.println("strSize======:"+strSize);
	
			boolean status = DataBaseLayer.insertManifest(unit_id,fullPath,strSize,user_id);
			if (status==true)
				html="Successfully uploaded";
			else
				html="Upload failed";			
			return html;
		}
	
		public FileTransfer downloadManifest(String unit_id)
		{
			InputStream xmlInputStream = null;
			xmlInputStream = DataBaseLayer.getManifest(unit_id);
	
	 		FileDownloaderPojo fd = new FileDownloaderPojo(xmlInputStream, "", "imsmanifest.xml");
			return fd.returnFileFormat();
		
		}
		
		
		/*Anupam */
		public String uploadUnitContent(String unit_id,FileTransfer file) throws IOException {
			String html = "";	
			
			WebContext wctx1 = WebContextFactory.get();
			javax.servlet.http.HttpSession mysession = wctx1.getSession();
			String user_id = (String) mysession.getAttribute("user_id");
			System.out.println("user_id===adminuser==="+user_id);		
			String dirName="";
			String strLocation = learnityInit.Host.getServerDocumentPath();
			dirName = strLocation + unit_id;
			File ufile=null;
			System.out.println("dir name is==:"+dirName);
			
			if(dirName == null)
				html="Please supply uploadDir parameter";
	
			ufile = new File(dirName);
			if(!ufile.exists())
				ufile.mkdir(); 
						
			FileOutputStream out; 
			PrintStream p;
					
			FileUploaderPojo fu = new FileUploaderPojo(file);
			String MimeType = fu.getMimeType();
			System.out.println("MimeType======:"+MimeType);
			InputStream is = fu.getInputStream();
			String filename = fu.getFilename();
			String fullPath = dirName+File.separator+filename;
			String content = "";
			String thisLine = "";		
			try{
							
				File f;
				f=new File(fullPath);
				if(!f.exists()){
					f.createNewFile();
				}
				BufferedReader myInput = new BufferedReader
						(new InputStreamReader(is));
				while ((thisLine = myInput.readLine()) != null) 
				{  
	// 				System.out.println("thisLine==="+thisLine);
					if(content.equals(""))
					{
						content = thisLine;
					}
					else {
						content = content +"\n"+ thisLine;
					}
				}
				out = new FileOutputStream(fullPath);
	
				System.out.println("bbbbbbbbbbbbbbbb");
				p = new PrintStream( out );
			
				p.println(content);
	
				p.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			
			//String strpath=str5+File.separator+localFile1.getName();
			
			File inFile=new File(fullPath);
			InputStream inStream= new FileInputStream(inFile);
			int size =(int)(inFile.length());
			DataBaseLayer.updateUnitContent2(unit_id,inStream,size,filename,user_id);
	
			
			String strSize="";
			File manifestFile=new File(fullPath);
			Long size2=manifestFile.length();
			
			strSize=size2.toString();
			System.out.println("strSize======:"+strSize);
	
		/*	boolean status = DataBaseLayer.insertManifest(unit_id,fullPath,strSize,user_id);
			if (status==true)
				html="Successfully uploaded";
			else
				html="Upload failed";			
			*/return html;
		}
	
		
		public FileTransfer downloadUnitContent(String unit_id,String file)	
		{  
			ByteArrayInputStream bis = new ByteArrayInputStream(DataBaseLayer.getContentFile(unit_id,file));
			InputStream xmlInputStream = bis;
			
	 		FileDownloaderPojo fd = new FileDownloaderPojo(xmlInputStream, "", file);
			return fd.returnFileFormat();
		
		}
		
		public String deleteUnitContent(String unit_id,String file){
			String status="";
			String target=DataBaseLayer.getUploadTarget(unit_id);
			
			status=DataBaseLayer.deleteUnit(unit_id,file,target);
			
			
			
			
			return status;
		}
		
		
		public boolean DeleteAction(String unitid) {

			final boolean b = false;
			final String unit_id1 =unitid; //httpServletRequest.getParameter("unit_id");
			//final String parameter2 = httpServletRequest.getParameter("course_id");
			String target = DataBaseLayer.getUploadTarget(unit_id1);
			System.out.println(" ======unitId===onselectTitle=====" + unit_id1);

			String str3 = Host.getServerDocumentPath();
			String fullpath = str3 + unit_id1;
			Path path = Paths.get(fullpath);

			if (target.equals("DB")) {
				System.out.println("In DB");
				String c=DataBaseLayer.deleteAll(unitid, target);
				String st="Deleted from DB";
				JOptionPane.showMessageDialog(null,st);
				
				return b;
			} else {

				try {
					String c=DataBaseLayer.deleteAll(unitid, target);
					FileUtils.forceDelete(new File(fullpath));
					String st="Deleted from FS";
					JOptionPane.showMessageDialog(null,st);
					
					
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

		
		/*End*/
		
	}
