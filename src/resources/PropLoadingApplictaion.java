package resources;
	import java.io.*;
	import java.nio.file.*;
	import java.util.Properties;
	public class PropLoadingApplictaion {
	public static void main(String x[])throws Exception {
		Path currentDirectoryPath=FileSystems.getDefault().getPath("");
		String currentDirectoryName =currentDirectoryPath.toAbsolutePath().toString();
		String completePath=currentDirectoryName+"\\src\\resources\\DB.properties";
		System.out.println("Current Directory = \""+currentDirectoryName+"\"");
		Properties p=new Properties();
		FileInputStream finf=new FileInputStream(completePath);
		p.load(finf);
		String driverClassName=p.getProperty("driver");
		String urlName=p.getProperty("url");
		String password=p.getProperty("pass");
		System.out.println(driverClassName);
		System.out.println(urlName);
		System.out.println(password);
		System.out.println(urlName);
	}
	}


