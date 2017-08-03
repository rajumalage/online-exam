package com.lara.oes.entity;

public class QuestionOption
{
	private int id;
	private String optionDesc;
	private int quesId;
	private int opsCount;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getOptionDesc()
	{
		return optionDesc;
	}
	public void setOptionDesc(String optionDesc)
	{
		this.optionDesc = optionDesc;
	}
	public int getQuesId()
	{
		return quesId;
	}
	public void setQuesId(int quesId)
	{
		this.quesId = quesId;
	}
	public int getOpsCount()
	{
		return opsCount;
	}
	public void setOpsCount(int opsCount)
	{
		this.opsCount = opsCount;
	}
	
	
}
