package com.memory.connect.service;

import com.memory.connect.model.answer.entity.Answer;
import com.memory.connect.model.answer.repository.AnswerRepository;
import com.memory.connect.model.member.entity.Member;
import com.memory.connect.model.testResult.entity.TestResult;
import com.memory.connect.model.testResult.entity.TestResult;
import com.memory.connect.model.testResult.repository.TestResultRepository;
import com.memory.connect.model.test.entity.Test;
import com.memory.connect.model.test.repository.TestRepository;
import com.memory.connect.model.testResult.repository.TestResultRepository;
import com.memory.connect.service.dto.RequestData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataService {

    private final TestRepository testRepository;
    private final AnswerRepository answerRepository;
    private final TestResultRepository answerResultRepository;

    public Test getQuestionById(int id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + id));
    }

    public Test getAnswerByQuestionId(int questionId) {
        return testRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Answer not found with questionId: " + questionId));
    }

    public Answer saveAnswer(int id, RequestData requestData) {
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Test not found with id: " + id));
        return answerRepository.save(requestData.toEntity(test));
    }

    /**
     * 2023/08/23 Gpt 결과를 받아와서 데이터를 저장해주는 함수
     */
    public TestResult saveTestResult(Test test, boolean gpt_result, Member member) {


        TestResult testResult = TestResult.builder()
                .test(test)
                .gpt_result(gpt_result)
                .member(member)
                .build();
        return answerResultRepository.save(testResult);
    }

    /**
     * 여긴 나중에 조인해서 파라미터를 Member ID로 바꾸면 될것같음
     */
    public List<TestResult> getResult() {
        return answerResultRepository.findAll();
    }

    public List<Test> getAllData() {
        return testRepository.findAll();
    }

    public Test getDataById(int id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("test data not found with id: " + id));
    }
}
