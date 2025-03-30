package com.microservice.example.service;

import com.microservice.example.dao.QuestionRepo;
import com.microservice.example.dao.QuizDao;
import com.microservice.example.model.Question;
import com.microservice.example.model.QuestionWrapper;
import com.microservice.example.model.Quiz;
import com.microservice.example.model.Response;
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
	QuestionRepo quesDao;
	
	public ResponseEntity<String> createQuiz(String category, int noOfQue, String title)
	{
		List<Question> ques = quesDao.findByCategory(category, noOfQue);
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQues(ques);
		dao.save(quiz);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id)
	{
		Optional<Quiz> quiz = dao.findById(id);
		List<QuestionWrapper> wrapper = new ArrayList<>();
		if(quiz.isPresent())
		{
			List<Question> ques = quiz.get().getQues();
			for(Question que : ques)
			{
				QuestionWrapper w = new QuestionWrapper(que.getId(), que.getOption4(), que.getOption3(), que.getOption2(), que.getOption1(), que.getQuesTitle());
				wrapper.add(w);
				
			}
		}
		return new ResponseEntity<>(wrapper, HttpStatus.OK);
	}
	
	public ResponseEntity<Integer> calcRes(Integer id, List<Response> resps)
	{
		Quiz quiz = dao.findById(id).get();
		List<Question> ques = quiz.getQues();
		int right = 0;
		int i = 0;
		for(int idx = 0; idx < resps.size();idx++)
		{
			if(resps.get(idx).getResp().equals(ques.get(idx).getRightAns()))
			{
				right++;
			}
		}
//		for(Response resp : resps)
//		{
//			if(resp.getResp().equals(ques.get(i).getRightAns()))
//			{
//				right++;
//			}
//			i++;
//		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}
}
