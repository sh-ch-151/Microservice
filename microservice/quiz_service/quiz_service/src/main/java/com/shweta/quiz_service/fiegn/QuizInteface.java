package com.shweta.quiz_service.fiegn;

import com.shweta.quiz_service.model.QuestionWrapper;
import com.shweta.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInteface
{
	@GetMapping("questions/generate")
	public ResponseEntity<List<Integer>> getQuestionForQuiz
		(@RequestParam String categoryName, @RequestParam Integer numOfQuestions);
	
	@PostMapping("questions/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> quesIds);
	
	@PostMapping("questions/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> resps);
}
