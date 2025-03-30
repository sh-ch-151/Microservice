package com.question_service.dao;

import com.question_service.model.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends CrudRepository<Question, Integer>
{
	public List<Question> findByCategory(String catgory);
	
	@Query(value = "SELECT q.id FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :noOfQue", nativeQuery = true)
	public List<Integer> findByCategory(String category, int noOfQue);
}
