package org.Model;
public class ExamModel {
private int examid;
private String examname;
private int totalmarks;
private int passingmarks;
ScheduleModel scheduleModel;
public ScheduleModel getScheduleModel() {
	return scheduleModel;
}
public void setScheduleModel(ScheduleModel scheduleModel) {
	this.scheduleModel=scheduleModel;
}
public ExamModel() {
	
}
public ExamModel(String examname,int totalmarks,int passingmarks) {
	this.examname=examname;
	this.totalmarks=totalmarks;
	this.passingmarks=passingmarks;
}
public int getExamid() {
	return examid;
}
public void setExamid(int examid) {
	this.examid = examid;
}
public String getExamname() {
	return examname;
}
public void setExamname(String examname) {
	this.examname = examname;
}
public int getTotalmarks() {
	return totalmarks;
}
public void setTotalmarks(int totalmarks) {
	this.totalmarks = totalmarks;
}
public int getPassingmarks() {
	return passingmarks;
}
public void setPassingmarks(int passingmarks) {
	this.passingmarks = passingmarks;
}


}
