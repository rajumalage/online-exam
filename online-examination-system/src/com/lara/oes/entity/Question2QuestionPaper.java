package com.lara.oes.entity;

import java.util.ArrayList;

public class Question2QuestionPaper 
{
	private int id;
	private int questionPaperId;
	private int questionId;
	 
	private ArrayList questionsInQPaper;
	private String question;	
	private String options[];
	private String OptionsId[];
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuestionPaperId() {
		return questionPaperId;
	}
	public void setQuestionPaperId(int questionPaperId) {
		this.questionPaperId = questionPaperId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public ArrayList getQuestionsInQPaper() {
		return questionsInQPaper;
	}
	public void setQuestionsInQPaper(ArrayList questionsInQPaper) {
		this.questionsInQPaper = questionsInQPaper;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String[] getOptions() {
		return options;
	}
	public void setOptions(String[] options) {
		this.options = options;
	}
	public String[] getOptionsId() {
		return OptionsId;
	}
	public void setOptionsId(String[] optionsId) {
		OptionsId = optionsId;
	}
	
	
	
}
 