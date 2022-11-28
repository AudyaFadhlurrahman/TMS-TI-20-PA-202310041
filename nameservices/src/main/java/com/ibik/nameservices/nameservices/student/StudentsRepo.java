package com.ibik.nameservices.nameservices.student;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentsRepo extends CrudRepository<Students, Integer>{
  
  @Query("SELECT a FROM Students a WHERE a.firstname LIKE %:name% OR a.middlename LIKE %:name% OR a.lastname LIKE %:name% OR a.npm like %:name%")
  public Iterable<Students> findStudentByName(@Param("name") String name);

  @Query(value = "SELECT a.* FROM students a WHERE a.email = :email AND concat(DATE_FORMAT(a.birthdate, '%d%m%Y'), '@', a.npm) = :password", nativeQuery = true)
  public Iterable<Students> findStudentAuth(@Param("email") String email, @Param("password") String password);
}