package org.Client.Application;
import org.Model.ExamModel;
import org.Model.QuestionModel;
import org.Model.ScheduleModel;
import org.Model.SubjectModel;
import org.Service.ExamService;
import org.Service.QuestionService;
import org.Service.SubjectService;
import java.util.*;
//import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

public class ExamClientApplication {

	public static void main(String[] args) {
		SubjectService sv=new SubjectService();
		ExamService ev=new ExamService();
		QuestionService qService=new QuestionService();
		do {
			System.out.println("1.Add New Subject");
			System.out.println("2.Add Quetions");
			System.out.println("3.Add bulk question");
			System.out.println("4.Add Exam");
			System.out.println("5.create Exam Schedule");
			System.out.println("Enter your choice");
			Scanner sc=new Scanner(System.in);
			int choice=sc.nextInt();
			switch(choice) {
			case 1:
				sc.nextLine();
				System.out.println("Enter subject name");
				String name=sc.next();
				SubjectModel model=new SubjectModel();
				model.setName(name);
				int result=sv.addSubject(model);
				if(result==1){
					System.out.println("Subject added success...........");
				}
				else if(result==-1){
					System.out.println("Subject already present..........");
				}
				else {
					System.out.println("Subject not added..........");
				}
				break;
			case 2:
				sc.nextLine();
				System.out.println("Enter Question");
				String question=sc.nextLine();
				System.out.println("Enter Option1");
				String option1=sc.nextLine();
				System.out.println("Enter Option2");
				String option2=sc.nextLine();
				System.out.println("Enter Option3");
				String option3=sc.nextLine();
				System.out.println("Enter Option4");
				String option4=sc.nextLine();
				System.out.println("Enter Answer");
				int answer=sc.nextInt();
				sc.nextLine();
				System.out.println("Enter subject name");
				String subName = sc.nextLine();
				QuestionModel qmodel=new QuestionModel(question,option1,option2,option3,option4,answer);
				
				boolean b=qService.isaddQuetion(qmodel, subName);
				
				if(b) {
					System.out.println("Quetion added successfully...............");
				}
				else {
					System.out.println("Some Problem is there............");
				}
				break;
			case 3:
				sc.nextLine();
				System.out.println("Enter subject name for strore bulk question");
				String subname=sc.nextLine();
				qService.uploadBulkQuestion(subname);
				break;
			case 4:
				sc.nextLine();
				System.out.println("Enter exam name total marks and passing marks");
				String examName=sc.nextLine();
				int totalMarks=sc.nextInt();
				int passMarks=sc.nextInt();
				ExamModel examModel=new ExamModel(examName,totalMarks,passMarks);
			    result=(int) ev.isAddExam(examModel);
				if(result==1) {
					System.out.println("Exam added successfully.........");
				}
				else if(result==-1){
					System.out.println("Exam Already present...............");
				}
				else {
					System.out.println("Some problem is there..................");
				}
				break;
			case 5:
				sc.nextLine();
				List<ExamModel> list=ev.getAllExams();
				System.out.println("Hey user you have list of exams and select one for schedule");
			System.out.println("===============================================================");
				for(ExamModel m:list) {
					System.out.println(m.getExamid()+"\t"+m.getExamname()+"\t"+m.getTotalmarks()+"\t"+m.getPassingmarks());
				}
				System.out.println("======================================================================================");
				System.out.println("Enter exam name for schedule");
				examName=sc.nextLine();
				ExamModel emodel=ev.getExamIdByName(examName);
				if(emodel!=null) {
					System.out.println("Enter exam date start time and end time");
		            String dateFormat=sc.nextLine();
		            Date d1=new Date(dateFormat);
		            String StartTime=sc.nextLine();
		            String endTime=sc.nextLine();
		            ScheduleModel smodel=new ScheduleModel();
		            smodel.setExamDate(d1);
		            smodel.setStartTime(StartTime);
		            smodel.setEndTime(endTime);
		            smodel.setExamid(smodel.getExamid());
		            emodel.setScheduleModel(smodel);
		            System.out.println("Enter subject name for exam");
		            subName=sc.nextLine();
		            b=ev.isSetSchedule(emodel,subName);
		          if(b) {
		            	System.out.println("Exam schedule success...................");
		            }
		            else {
		            	System.out.println("Some problem is there...............");
		            }
				}
				else {
					System.out.println("Enter correct exam");
				}
				break;
			}
		}while(true);
	}
}
