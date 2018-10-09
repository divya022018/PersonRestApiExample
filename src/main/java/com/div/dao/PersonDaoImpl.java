package com.div.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.div.model.Person;


@Repository
public class PersonDaoImpl implements PersonDao {
 
 NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 
 @Autowired
 public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
  this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
 }

 public List listAllPerson() {
  List list = new ArrayList();
  
  String sql = "SELECT id, firstname, lastname, address FROM persons";
  
  list = namedParameterJdbcTemplate.query(sql, getSqlParameterByModel(null), new PersonMapper());
  
  return list;
 }
 
 private SqlParameterSource getSqlParameterByModel(Person person){
  MapSqlParameterSource parameterSource = new MapSqlParameterSource();
  if(person != null){
   parameterSource.addValue("id", person.getId());
   parameterSource.addValue("firstname", person.getFirstname());
   parameterSource.addValue("lastname", person.getLastname());
   parameterSource.addValue("address", person.getAddress());
  }
  return parameterSource;
 }
 
 private static final class PersonMapper implements RowMapper{

  public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
   Person person = new Person();
   person.setId(rs.getInt("id"));
   person.setFirstname(rs.getString("firstname"));
   person.setLastname(rs.getString("lastname"));
   person.setAddress(rs.getString("address"));
   
   return person;
  }
  
 }

 public void addPerson(Person person) {
  String sql = "INSERT INTO persons(firstname, lastname, address) VALUES(:firstname, :lastname, :address)";
  
  namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(person));
 }

 public void updatePerson(Person person) {
  String sql = "UPDATE persons SET firstname=:firstname, lastname=:lastname, address=:address WHERE id =:id";
  
  namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(person));
 }

 public void delete(Person person) {
  String sql = "DELETE FROM persons WHERE id=:id";
  
  namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(person));
 }

 public Person findPersonById(Person person) {
  String sql = "SELECT * FROM persons WHERE id =:id";
  
  return namedParameterJdbcTemplate.queryForObject(sql, getSqlParameterByModel(person), new PersonMapper());
 }

}