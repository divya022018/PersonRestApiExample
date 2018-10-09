package com.div.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.div.dao.PersonDao;
import com.div.model.Person;

@Service
public class PersonServiceImpl implements PersonService {
	 
	 PersonDao personDao;
	 
	 @Autowired
	 public void setPersonDao(PersonDao personDao) {
	  this.personDao = personDao;
	 }

	 public List listAllPerson() {
	  return personDao.listAllPerson();
	 }

	 public void addPerson(Person person) {
	  personDao.addPerson(person);
	 }

	 public void updatePerson(Person person) {
	  personDao.updatePerson(person);
	 }

	 public void delete(Person person) {
	  personDao.delete(person);
	 }

	 public Person findPersonById(Person person) {
	  return personDao.findPersonById(person);
	 }
}
