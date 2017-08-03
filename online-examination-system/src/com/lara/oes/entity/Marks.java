package com.lara.oes.entity;


public class Marks 
{
	private float obtainedMarks;
	private float percentage=0;
	private int totalMarks=0;
	private float passMarks;
	private float negativeMarks;
	private int status;
	public float getObtainedMarks() {
		return obtainedMarks;
	}
	public void setObtainedMarks(float obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
	}
	public float getPercentage() {
		return percentage;
	}
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	public int getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}
	public float getPassMarks() {
		return passMarks;
	}
	public void setPassMarks(float passMarks) {
		this.passMarks = passMarks;
	}
	public float getNegativeMarks() {
		return negativeMarks;
	}
	public void setNegativeMarks(float negativeMarks) {
		this.negativeMarks = negativeMarks;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
