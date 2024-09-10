package org.Service;

import org.Helper.PathHelper;
import org.Model.QuestionModel;
import java.io.*;
import java.util.*;

import org.Repository.QuestionRepository;
import java.io.*;
public class QuestionService {
	boolean flag=false;
QuestionRepository queRepo=new QuestionRepository();
SubjectService sv=new SubjectService();
public boolean isaddQuetion(QuestionModel qModel,String subName) {
	return queRepo.isAddQuestion(qModel,subName);
}
public boolean createFiles() {
	try {
		String path=PathHelper.filePath;
		File f=new File(path);
		if(!f.exists()) {
		f.mkdir();
		}
		List<String> subList=sv.getAllSubject();
		if(subList!=null) {
			for(String subName:subList) {
				f=new File(path+"\\"+subName+".csv");
				if(!f.exists()) {
					f.createNewFile();
				}
			}
		}
		else {
			
			
			System.out.println("Subject not found");
		}
	}
	catch(Exception ex) {
		System.out.println("Error is "+ex);
	}
	return true;
}
public boolean uploadBulkQuestion(String subName) {
	boolean b=this.createFiles();
	if(b){
		File f=new File(PathHelper.filePath);
		File []fileList=f.listFiles();
		
		for(File file:fileList) {
			if(file.isFile()) {
				int index=file.toString().indexOf(subName);
				if(index!=-1) {
					flag = true;
					break;
				}
			}
		}
		if(flag) {
			try {
			FileReader fr=new FileReader(PathHelper.filePath+"\\"+subName+".csv");
			BufferedReader br=new BufferedReader(fr);
		    String question;
		    flag=false;
			while((question=br.readLine())!=null) {
			String qwithop[]=question.split(",");
			flag=queRepo.uploadBulkQuestion(qwithop,subName);
			}
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		return true;
		}
	else {
		return false;
	}
	
}
}
