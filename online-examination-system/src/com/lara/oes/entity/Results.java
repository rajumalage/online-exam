package com.lara.oes.entity;

public class Results 
{
	private int resultId;
	private int resultStudentId;
	private int resultQuestionId;
	private double marks;
	public int getResultId() {
		return resultId;
	}
	public void setResultId(int resultId) {
		this.resultId = resultId;
	}
	public int getResultStudentId() {
		return resultStudentId;
	}
	public void setResultStudentId(int resultStudentId) {
		this.resultStudentId = resultStudentId;
	}
	public int getResultQuestionId() {
		return resultQuestionId;
	}
	public void setResultQuestionId(int resultQuestionId) {
		this.resultQuestionId = resultQuestionId;
	}
	public double getMarks() {
		return marks;
	}
	public void setMarks(double marks) {
		this.marks = marks;
	}
}
