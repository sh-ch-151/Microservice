package com.question_service.controller;

import com.question_service.model.Question;
import com.question_service.model.QuestionWrapper;
import com.question_service.model.Response;
import com.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController
{
	@Autowired
	QuestionService serv;
	
	@Autowired
	Environment env;
	
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
	
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionForQuiz
		(@RequestParam String categoryName, @RequestParam Integer numOfQuestions)
	{
		return serv.getQuestionsForQuiz(categoryName, numOfQuestions);
	}
	
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> quesIds)
	{
		System.out.println(env.getProperty("local.server.port"));
		return serv.getQuestionsFromId(quesIds);
	}
	
	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> resps)
	{
		return serv.getScore(resps);
	}
}
