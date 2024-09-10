package org.Helper;
/*select s.pdate,s.starttime,s.endtime,e.examname,ss.subjectname from schedule s inner join exam e on s.examid=e.examid inner join subject ss on s.sid=ss.sid*/
import java.io.FileInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Properties;

public class PathHelper {
public static String  completePath="";
public static Properties p=new Properties();
public static final String filePath="C:\\Users\\91976\\Documents\\JAVA\\questionbank";
public PathHelper() {
	try {
		Path currentDirectoryPath=FileSystems.getDefault().getPath("");
		String currentDirectoryName=currentDirectoryPath.toAbsolutePath().toString();
		completePath=currentDirectoryName+"\\src\\resources\\DB.properties";
		FileInputStream finf=new FileInputStream(completePath);
		p.load(finf);
	}
	catch(Exception ex) {
		System.out.println("Error is" +ex);
	}
}
}
