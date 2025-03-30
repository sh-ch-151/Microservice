package com.microservice.example.controller;

import com.microservice.example.model.QuestionWrapper;
import com.microservice.example.model.Response;
import com.microservice.example.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController
{
	@Autowired
	QuizService serv;
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int noOfQue, @RequestParam String title)
	{
		return serv.createQuiz(category, noOfQue, title);
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQues(@PathVariable int id)
	{
		return serv.getQuizQuestions(id);
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> resps)
	{
		return serv.calcRes(id, resps);
	}
}
