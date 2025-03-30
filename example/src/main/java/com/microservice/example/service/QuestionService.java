package com.microservice.example.service;

import com.microservice.example.dao.QuestionRepo;
import com.microservice.example.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService
{
	@Autowired
	QuestionRepo repo;
	
	public ResponseEntity<List<Question>> getAllQuestions()
	{
		List<Question> ques = new ArrayList<>();
		ResponseEntity<List<Question>> respQues = null;
		try
		{
			ques = (List<Question>) repo.findAll();
			respQues = new ResponseEntity<>(ques, HttpStatus.OK);
		}
		catch(Throwable e)
		{
			respQues = new ResponseEntity<>(ques, HttpStatus.BAD_REQUEST);
		}
		return respQues;
	}
	
	public ResponseEntity<List<Question>> getByCategory(String category)
	{
		List<Question> ques = new ArrayList<>();
		ResponseEntity<List<Question>> respQues = null;
		try
		{
			ques = repo.findByCategory(category);
			respQues =  new ResponseEntity<>(ques, HttpStatus.OK);
		}
		catch(Throwable e)
		{
			respQues = new ResponseEntity<>(ques, HttpStatus.BAD_REQUEST);
		}
		return respQues;
	}
	
	/**
	 *
	 * @param question
	 * @return
	 */
	public ResponseEntity<Question> addQuestion(Question question)
	{
		Question que = null;
		ResponseEntity<Question> que1 = null;
		try
		{
			que = repo.save(question);
			que1 = new ResponseEntity<>(que, HttpStatus.OK);
		}
		catch(Throwable e)
		{
			que1 = new ResponseEntity<>(que, HttpStatus.BAD_REQUEST);
		}
		return que1;
	}
	
	public ResponseEntity<Question> updateQue(Question question, Integer id)
	{
		Question que = null;
		ResponseEntity<Question> que1 = null;
		try
		{
			if(repo.existsById(id))
			{
				Optional<Question> oldData = repo.findById(id);
				if(oldData.isPresent())
				{
					que = repo.save(question);
					que1 = new ResponseEntity<>(que, HttpStatus.OK);
				}
			}
		}
		catch(Throwable e)
		{
			que1 = new ResponseEntity<>(que, HttpStatus.BAD_REQUEST);
		}
		return que1;
	}
}
