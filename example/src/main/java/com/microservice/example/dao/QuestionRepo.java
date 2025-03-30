package com.microservice.example.dao;

import com.microservice.example.model.Question;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends CrudRepository<Question, Integer>
{
	public List<Question> findByCategory(String catgory);
	
	@Query(value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :noOfQue", nativeQuery = true)
	public List<Question> findByCategory(String category, int noOfQue);
}
