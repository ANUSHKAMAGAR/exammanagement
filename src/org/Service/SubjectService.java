package org.Service;

import java.util.*;

import org.Model.SubjectModel;
import org.Repository.SubjectRepository;

public class SubjectService {
SubjectRepository subRepo=new SubjectRepository();
public int addSubject(SubjectModel model) {
	return (subRepo.isSubjectPresent(model.getName()))?-1:subRepo.isAddSubject(model)?1:0;
}
public List<String> getAllSubject(){
	return this.subRepo.getAllSubject();
}
}