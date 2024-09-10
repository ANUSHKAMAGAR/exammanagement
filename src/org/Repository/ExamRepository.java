package org.Repository;

	
	import java.sql.*;
import java.sql.Date;
//import java.sql.*;
//import java.sql.Date;
import java.util.*;
//import java.util.Date;

import org.Model.ExamModel;
import org.Model.ScheduleModel;
	public class ExamRepository extends DBConfig {
		List<ExamModel> listExams=new ArrayList<ExamModel>();
		QuestionRepository qRepo=new QuestionRepository();
		public boolean isExamPresent(String examname) {
			try {
				stmt=conn.prepareStatement("select *from exam where examname=?");
				stmt.setString(1, examname);
				rs=stmt.executeQuery();
				return rs.next();
			}
			catch(Exception ex) {
				return false;
			}
			
		}
		public boolean isAddExam(ExamModel model) {
			try {
				stmt=conn.prepareStatement("insert into Exam values('0',?,?,?)");
				stmt.setString(1, model.getExamname());
				stmt.setInt(2, model.getTotalmarks());
				stmt.setInt(3,model.getPassingmarks());
				int value=stmt.executeUpdate();
					if(value>0) {
						return true;
					}
					else {
						return false;
					}
			}
			catch(Exception ex) {
				System.out.println("Error is +ex");
				return false;
			}
		}
		public List<ExamModel> getAllExams(){
			try {
				stmt=conn.prepareStatement("select *from exam");
				rs=stmt.executeQuery();
				while(rs.next()) {
					ExamModel model=new ExamModel();
					model.setExamid(rs.getInt(1));
					model.setExamname(rs.getString(2));
					model.setTotalmarks(rs.getInt(3));
					model.setPassingmarks(rs.getInt(4));
					listExams.add(model);
				}
				return listExams.size()>0?listExams:null;
			}
			catch(Exception e) {
				System.out.println("Error is"+e);
			}
			return null;
		}

	public ExamModel getExamIdByName(String name) {
		try {
			stmt=conn.prepareStatement("select *from exam where examname='"+name+"'");
			
			rs=stmt.executeQuery();
	        ExamModel model=null;
	        if(rs.next()) {
	        	model=new ExamModel();
	        	model.setExamid(rs.getInt(1));
	        	model.setExamname(rs.getString(2));
	        	model.setTotalmarks(rs.getInt(3));
	        	model.setPassingmarks(rs.getInt(4));
	        }
	        return model!=null?model: null;
			}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return null;
		}
	}
	int getSchedule() {
		int count=0;
		try {
			stmt=conn.prepareStatement("select max(schid) from schedule");
					rs=stmt.executeQuery();
					if(rs.next()) {
						count=rs.getInt(1);
					}
					++count;
					return count;
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return 0;
		}
		
	}
	public boolean isSetSchedule(ExamModel model,String subName) {
		try {
			int schid=this.getSchedule();
			if(schid!=0) {
				int subId1=qRepo.getSubjectIdByName(subName);
				ScheduleModel smodel=model.getScheduleModel();
			   String examDate=smodel.getExamDate().toLocaleString();
			   System.out.println(examDate);
			 String d[]=examDate.split(",");
			 String []dateSplit=d[0].split("-");
			 System.out.println(dateSplit[0]+"\t"+dateSplit[1]+"\t"+dateSplit[2]);
			 int months[]=new int[] {0,1,2,3,4,5,6,7,8,9,10,11};
			 int m=0;
			 switch(dateSplit[1]) {
			 case "Jan":
				 m=0;
				 break;
			 case "Feb":
				 m=1;
				 break;
			 case "Mar":
				 m=2;
				 break;
			 case "April":
				 m=3;
				 break;
			 case "May":
				 m=4;
				 break;
			 case "June":
				 m=5;
				 break;
			 case "July":
				 m=6;
				 break;
			 case "Aug":
				 m=7;
				 break;
			 case "Sap":
				 m=8;
				 break;
			 case "Oct":
				 m=9;
				 break;
			 case "Nov":
				 m=10;
				 break;
			 case "Dec":
				 m=11;
				 break;
			 }
			 String newYear=dateSplit[2].substring(2,dateSplit[2].length());
			 Date d1=new Date((Integer.parseInt(newYear)+100),m,Integer.parseInt(dateSplit[0]));
			stmt=conn.prepareStatement("insert into schedule values(?,?,?,?,?,?)");
			stmt.setInt(1, schid);
			stmt.setInt(2, model.getExamid());
			stmt.setDate(3, d1);
			stmt.setString(4,smodel.getStartTime());
			stmt.setString(5, smodel.getEndTime());
			stmt.setInt(6, subId1);
			int value=stmt.executeUpdate();
			return value>0?true:false;
		
		}
			else {
				System.out.println("Some Problem is there...........");
				return true;
		}
		}
		catch(Exception ex) {
			System.out.println("Error is "+ex);
			return false;
		}
	
	}
	}

