package org.Service;
//import java.sql.Date;
import org.Model.ExamModel;
import org.Model.ScheduleModel;
import org.Repository.DBConfig;
import org.Repository.ExamRepository;

import java.util.*;
import java.util.List;
public class ExamService{
	
	ExamRepository examRepo=new ExamRepository();
	private String subName;
	public int isAddExam(ExamModel model) {
		return (examRepo.isExamPresent(model.getExamname()))?-1:(examRepo.isAddExam(model))?1:0;
		}
	public List<ExamModel> getAllExams(){
		return examRepo.getAllExams();
	}
	public ExamModel getExamIdByName(String name) {
		return examRepo.getExamIdByName(name);
	}
	
	public boolean isSetSchedule(ExamModel model, String name) {
		Date d=new Date();
		
		String []s=d.toLocaleString().split(",");
		
		String dsplit[]=s[0].split("-");
		ScheduleModel smodel=model.getScheduleModel();
		Date userDate=smodel.getExamDate();
		
	    String userDateArr[]=userDate.toLocaleString().split(",");
		String userDates[]=userDateArr[0].split("-");
		if(Integer.parseInt(userDates[2])>=Integer.parseInt(dsplit[2]) && userDates[1].equals(dsplit[1])) {
			if(Integer.parseInt(userDates[0])>=Integer.parseInt(dsplit[0])) {
				System.out.println("You can schdule exam");
			
				boolean b=examRepo.isSetSchedule(model,name);
				return b?true:false;
			}
			else {
				System.out.println("You may be insert date before system");
				
			}
		}
		else {
			System.out.println("You may be insert previous year or may be previous month");
		}
		
		return false;
	}
	}

