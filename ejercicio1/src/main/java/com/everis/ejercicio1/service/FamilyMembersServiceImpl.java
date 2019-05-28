package com.everis.ejercicio1.service;

import com.everis.ejercicio1.models.Families;
import com.everis.ejercicio1.models.FamilyMembers;
import com.everis.ejercicio1.models.Parents;
import com.everis.ejercicio1.models.Students;
import com.everis.ejercicio1.repository.IFamiliesDAO;
import com.everis.ejercicio1.repository.IFamilyMembersDAO;
import com.everis.ejercicio1.repository.IParentsDAO;
import com.everis.ejercicio1.repository.IStudentsDAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamilyMembersServiceImpl implements IFamilyMembersService {

  /**
   * 
   */
  
  @Autowired
  private IFamilyMembersDAO repo;

  @Autowired
  private IParentsDAO repoParents;
  
  @Autowired
  private IFamiliesDAO repoFamily;

  @Autowired
  private IStudentsDAO repoStudent;
  
  @Override
  public FamilyMembers create(FamilyMembers familyMembers) {
	  
	  Families fam = new Families();
	  
	  repoFamily.findById(fam.getFamilyId()).ifPresent((n) -> {
	      
		  familyMembers.setFamilies(n);
	      
	      if (familyMembers.getParentOrStudentMembers().equals("P")) {
	    	  
	    	  Parents par = new Parents();
	    	  
	    	  repoParents.findById(par.getParentId()).ifPresent((parent) -> { 
	    		  familyMembers.setParents(parent);
	      });
	      
	      } else if (familyMembers.getParentOrStudentMembers().equals("S")) {
	      
	    	  Students stu = new Students();
	    	  
	      repoStudent.findById(stu.getStudentId()).ifPresent((student) -> {
	    	  familyMembers.setStudents(student); 
	    	  });
	      
	      }
	      
	      });
	  
    return repo.save(familyMembers);
  }

  @Override
  public FamilyMembers update(FamilyMembers familyMembers) {
    return repo.save(familyMembers);
  }

  @Override
  public void delete(int id) {
    repo.deleteById(id);
  }

  @Override
  public Optional<FamilyMembers> listId(int id) {
    return repo.findById(id);
  }

  @Override
  public List<FamilyMembers> list() {
    return (List<FamilyMembers>) repo.findAll();
  }

@Override
public void post(FamilyMembers familyMember, int familyId, String parentOrStudentMember, int id) {
	
	repoFamily.findById(familyId).ifPresent((fa) -> {
		familyMember.setFamilies(fa);
		if(parentOrStudentMember.equals("P")) {
			repoParents.findById(id).ifPresent((pa)-> {
				familyMember.setParents(pa);
			});
		}else if(parentOrStudentMember.equals("S")) {
			repoStudent.findById(id).ifPresent((st)-> {
				familyMember.setStudents(st);
			});
		}
	});
	
repo.save(familyMember);
	
	
}

}
