package kr.or.ddit.util;

public class FileUtil {
	//contentDisposition ==> "form-data"; name="file"; filename="brown.png";
  public static String getFileName(String contentDisposition) {
	 String[] attrs = contentDisposition.split("; ");
	 
	 for(String attr : attrs) {
		 if (attr.startsWith("filename=")){
		 //filename="brown.png"  
		 attr = attr.replace("filename=", "");
		 
		 // "brown.png"
		 return attr.substring(1, attr.length() -1);
		 }
	 }
	 return"";
  }
  
  public static String getFileExtension(String filename) {
	  
	  //line.brown.png
	  //filename.split("\\.")[1]
	  
	  if(filename.indexOf(".") == -1) {
		  return "";
	  }
	  return "."+filename.substring(filename.lastIndexOf(".")+1);
  }
}
