package org.Repository;

import java.util.*;
import org.Model.SubjectModel;
public class SubjectRepository extends DBConfig{
List<String> list=new ArrayList<String>();
public boolean isAddSubject(SubjectModel model) {
	try {
		stmt=conn.prepareStatement("insert into subject values('0',?)");
		stmt.setString(1, model.getName());
		int value=stmt.executeUpdate();
		if(value>0) {
			return true;
		}
		else {
			return false;
		}
	}
	catch(Exception ex) {
	System.out.println("Error is "+ex);
	return false;
	}
}
public boolean isSubjectPresent(String subName) {
	try {
		stmt=conn.prepareStatement("select *from subject where subjectname=?");
		stmt.setString(1, subName);
        rs=stmt.executeQuery();
		return rs.next();
	}
	catch(Exception ex){
		System.out.println("Error is"+ex);
		return false;
	}
}

public List<String> getAllSubject(){
	try {
		stmt=conn.prepareStatement("select subjectname from subject");
		rs=stmt.executeQuery();
		while(rs.next()) {
			list.add(rs.getString(1));
		}
		return list.size()>0?list:null;
	}
	catch(Exception ex) {
		System.out.println("error is"+ex);
		return null;
	}
	
}
}