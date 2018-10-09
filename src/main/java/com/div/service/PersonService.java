package com.div.service;

import java.util.List;

import com.div.model.Person;

public interface PersonService {

	public List listAllPerson();
	 
	 public void addPerson(Person person);
	 
	 public void updatePerson(Person person);
	 
	 public void delete(Person person);
	 
	 public Person findPersonById(Person person);
}
