package learnityinterfaceportal;

/**
 * Title:        Search Common Class
 * Description:
 * Copyright:    Copyright (c) 2003
 * Company:      Aunwesha Knowledge Technology Pvt.Ltd.
 * @author 		 Avik Chatterjee
 * @version 	 3.0
 */

import java.util.Vector;
//import java.io.IOException;
import java.util.StringTokenizer;

public class SearchCommon {
	
	static boolean flag=false;
	static boolean mark=false;	
	
	public static boolean SearchItem(String strfromdatabase, String search_string)
     
     {
     	Vector v = new Vector(3,3);
     	Vector vNewData = new Vector(3,3);///
//	    Vector vFinalData = new Vector(3,3);	
	    String cmd[];
		
		//System.out.println("search_string ="+search_string);
		 
     	StringTokenizer t = new StringTokenizer(search_string);
     	mark=false;
     	
     	while (t.hasMoreTokens())			
	    		v.addElement(t.nextToken());	
	    for(int j=0 ; j< v.size(); j++)
	    {
	   	String strElement = (String)v.elementAt(j);
	   	//System.out.println("List ="+strElement);
	      if(MatchString(strElement,strfromdatabase))
	      {
	      	 mark=true;
	        // strItemTitle = (String)vFinalData.elementAt(j);
	   	if(!(vNewData.contains(strfromdatabase)))
	   	{
	   		
	   		vNewData.addElement(strfromdatabase);
	   		
	   		//System.out.println("StringItem ="+strDatabase);
	   	 }
	   		   	 
	      }
	     	           
	   } 
	     
	         cmd = new String[vNewData.size()];
		// for (int i = 0; i < cmd.length; i++)
		// {
	   	 // cmd[i] = (String) vNewData.elementAt(i);
	   	 // System.out.println("Final ="+cmd[i]);
		// }		
     	return mark;
     }

    ///////////////////Region Matches Search method/////////////////////////////////////////////	
    	 
    public static boolean MatchString(String s1,String s2)
       
    {
    	 flag=false;
         
          for (int i = 0 ; i < (s2.length() - s1.length()+1) && s2.length()>=s1.length();i++)
          {
         //System.out.println(s1.substring(0,s1.length()-1));
         //System.out.println(s2.substring(i,s1.length()+i));
         flag =s2.regionMatches(true,i,s1,0,s1.length()); 
         
         if (flag)
          break ;
        
          }
        /* if (flag) 
          	System.out.println("PatternMatched");
           else
         	System.out.println("No PatternMatched");
         */
         
        return flag;
        
       }
	
}