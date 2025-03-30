package com.microservice.example.controller;

import com.microservice.example.model.Question;
import com.microservice.example.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController
{
	@Autowired
	QuestionService serv;
	
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuetions()
	{
		return serv.getAllQuestions();
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getByCategory(@PathVariable("category") String category)
	{
		return serv.getByCategory(category);
	}
	
	@PostMapping("addQuestion")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question)
	{
		ResponseEntity<Question> que = serv.addQuestion(question);
		return que;
	}
	
	@PutMapping("updateQue/{id}")
	public ResponseEntity<Question> update(@RequestBody Question question, @PathVariable("id") int id)
	{
		ResponseEntity<Question> que = serv.updateQue(question, id);
		return que;
	}
}
