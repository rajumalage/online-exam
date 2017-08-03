package com.lara.oes.entity;


public class StudentAnswer 
{
	private int stuAnsId;
	private int quesId;
	private int opesId;
	private int studentId;
	private String questionId;
	private String OptionsId[];
	
	public int getStuAnsId() {
		return stuAnsId;
	}
	public void setStuAnsId(int stuAnsId) {
		this.stuAnsId = stuAnsId;
	}
	public int getQuesId() {
		return quesId;
	}
	public void setQuesId(int quesId) {
		this.quesId = quesId;
	}
	public int getOpesId() {
		return opesId;
	}
	public void setOpesId(int opesId) {
		this.opesId = opesId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String[] getOptionsId() {
		return OptionsId;
	}
	public void setOptionsId(String[] optionsId) {
		OptionsId = optionsId;
	}
	
	
}
