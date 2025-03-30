package com.shweta.quiz_service.service;

import com.shweta.quiz_service.dao.QuizDao;
import com.shweta.quiz_service.fiegn.QuizInteface;
import com.shweta.quiz_service.model.Question;
import com.shweta.quiz_service.model.QuestionWrapper;
import com.shweta.quiz_service.model.Quiz;
import com.shweta.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
	@Autowired
	QuizDao dao;
	
	@Autowired
	QuizInteface inteface;
	
	public ResponseEntity<String> createQuiz(String category, int noOfQue, String title)
	{
		List<Integer> ques = inteface.getQuestionForQuiz(category, noOfQue).getBody();//Call the generate url We use rest template ;
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuesIds(ques);
		dao.save(quiz);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id)
	{
		Quiz quiz = dao.findById(id).get();
		List<Integer> quesIds = quiz.getQuesIds();
		ResponseEntity<List<QuestionWrapper>> ques = inteface.getQuestionFromId(quesIds);
		return ques;
	}
	
	public ResponseEntity<Integer> calcRes(Integer id, List<Response> resps)
	{
		ResponseEntity<Integer>  score = inteface.getScore(resps);
		return score;
	}
}
