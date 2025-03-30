package com.shweta.quiz_service.controller;

import com.shweta.quiz_service.model.QuestionWrapper;
import com.shweta.quiz_service.model.QuizDto;
import com.shweta.quiz_service.model.Response;
import com.shweta.quiz_service.service.QuizService;
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
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto dto)
	{
		return serv.createQuiz(dto.getCategory(), dto.getNoOfQue(), dto.getTitle());
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
