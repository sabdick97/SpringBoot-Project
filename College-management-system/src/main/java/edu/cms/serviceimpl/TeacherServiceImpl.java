package edu.cms.serviceimpl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cms.entity.Teacher;
import edu.cms.exception.ResourceNotFoundException;
import edu.cms.model.TeacherDTO;
import edu.cms.repository.TeacherRepository;
import edu.cms.service.TeacherService;
import edu.cms.util.TeacherConverter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j


public class TeacherServiceImpl implements TeacherService{
	
	//logger statically created
	private static final Logger log= LoggerFactory.getLogger(Teacher.class);

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private TeacherConverter converter;
	//To save the teacher details 
	@Override
	public String createTeacher(Teacher teacher) {
		String msg=null;
		
		teacher.setUserName(teacher.getUserName());
		teacher.setPassword(teacher.getPassword());
		
		teacherRepository.save(teacher);
		log.info("Teacher "+teacher.toString()+" added at "+ new Date());
		if(teacher!=null)
		{
			msg = "Teacher details saved successfully!";
		}
		return msg;
	}
    // To show the teacher details by id
	@Override
	public TeacherDTO getTeacherById(int id) {
		
		Teacher teacher = teacherRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Teacher", "Id", id));
		return converter.convertTeacherEntityToDTO(teacher);
	}
	
    // To show all teacher details 
	@Override
	public List<TeacherDTO> getAllTeacherDetails() {
		List<Teacher> teachers =teacherRepository.findAll();
		List<TeacherDTO> teachDTO = new ArrayList<>();
		for(Teacher t: teachers)
		{
			teachDTO.add(converter.convertTeacherEntityToDTO(t));
		}
		return teachDTO;
	}
    //To update the teacher details 
	@Override
	public TeacherDTO updateTeacherDetails(int id, Teacher teacher) {
		
		Teacher existingT = teacherRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Teacher", "Id", id));
		
		//we will get data from client and set in the existing teacher
		existingT.setFirstName(teacher.getFirstName());
		existingT.setLastName(teacher.getLastName());
		existingT.setContact(teacher.getContact());
		existingT.setEmail(teacher.getEmail());
		existingT.setUserName(teacher.getUserName());
		existingT.setPassword(teacher.getPassword());
		
		teacherRepository.save(existingT);
		
		return converter.convertTeacherEntityToDTO(existingT);
	}
   // To delete the teacher details by id 
	@Override
	public String deleteTeacherById(int id) {
		String msg = null;
		Optional<Teacher> teacher = teacherRepository.findById(id);
		if(teacher.isPresent())
		{
			teacherRepository.deleteById(id);
			msg = "Teacher with id "+id+" has been deleted!";
		}
		else
		{
			throw new ResourceNotFoundException("Teacher", "Id", id);
		}
		
		return msg;
	}
	
//To delete all the teacher details 
	@Override
	public void deleteAllTeacherDetails() {
		teacherRepository.deleteAll();
		
	}
   //To show teacher details  by using email
	@Override
	public TeacherDTO findTeacherByEmail(String email) {
		Teacher temail = teacherRepository.findByEmail(email);
		
		return converter.convertTeacherEntityToDTO(temail);
	}
   //To show teacher details by their first name
	@Override
	public List<TeacherDTO> findByFirstName(String name) {
		List<Teacher> teachers =teacherRepository.findByFirstName(name);
		List<TeacherDTO> tDTO = new ArrayList<>();
		for(Teacher t: teachers)
		{
			tDTO.add(converter.convertTeacherEntityToDTO(t));
		}
		return tDTO;
	}

}

	


