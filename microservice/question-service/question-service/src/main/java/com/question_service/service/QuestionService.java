package com.question_service.service;

import com.question_service.dao.QuestionRepo;
import com.question_service.model.Question;
import com.question_service.model.QuestionWrapper;
import com.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
	
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numOfQuestions)
	{
		List<Integer> ques = repo.findByCategory(categoryName, numOfQuestions);
		return new ResponseEntity<>(ques, HttpStatus.OK);
	}
	
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> quesIds)
	{
		List<QuestionWrapper> quesWrapper = new ArrayList<>();
		List<Question> questions = new ArrayList<>();
		for(Integer id : quesIds)
		{
			questions.add(repo.findById(id).get());
		}
//		for(int idx = 0; idx < questions.size(); idx++)
//		{
//			QuestionWrapper wrapper = new QuestionWrapper();
//			wrapper.setId(questions.get(idx).getId());
//			wrapper.setOption4(questions.get(idx).getOption4());
//			wrapper.setOption3(questions.get(idx).getOption3());
//			wrapper.setOption2(questions.get(idx).getOption2());
//			wrapper.setOption1(questions.get(idx).getOption1());
//			wrapper.setQuestionTitle(questions.get(idx).getQuesTitle());
//			quesWrapper.add(wrapper);
//		}
		for(Question que : questions)
		{
			QuestionWrapper wrapper = new QuestionWrapper();
			wrapper.setId(que.getId());
			wrapper.setOption4(que.getOption4());
			wrapper.setOption3(que.getOption3());
			wrapper.setOption2(que.getOption2());
			wrapper.setOption1(que.getOption1());
			wrapper.setQuestionTitle(que.getQuesTitle());
			quesWrapper.add(wrapper);
		}
		return new ResponseEntity<>(quesWrapper, HttpStatus.OK);
	}
	
	public ResponseEntity<Integer> getScore(List<Response> resps)
	{
		int right = 0;
		for(int idx = 0; idx < resps.size();idx++)
		{
			Question que = repo.findById(resps.get(idx).getId()).get();
			if(resps.get(idx).getResp().equals(que.getRightAns()));
			{
				right++;
			}
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}
}
