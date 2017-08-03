function validate()
	{
		var myForm = document.forms[0];
		var setSubject = myForm.setSubject;
		if(setSubject.value.length == 0)
			{
				alert("Please enter Subject Name");
				setSubject.focus();
				return false;
			}
		var setSubDesc = myForm.setSubDesc;		
		if(setSubDesc.value.length == 0)
		{
			alert("Please enter Subject Description");
			setSubDesc.focus();
			return false;
		}
	}

function UpdateSubjectvalidate()
{
	var myForm = document.forms[0];
	var SubJectName = myForm.SubJectName;
	if(SubJectName.value == 0)
		{
			alert("Please Existing Subject Name");
			SubJectName.focus();
			return false;
		}
	var SubName = myForm.SubName;
	if(SubName.value.length == 0)
		{
			alert("Please enter Subject Name");
			SubName.focus();
			return false;
		}
	var SubDesc = myForm.SubDesc;		
	if(SubDesc.value.length == 0)
	{
		alert("Please enter Subject Description");
		SubDesc.focus();
		return false;
	}
}

function confirmDeleteSubject()
{
	var result=confirm("Do You Want To Delete Subject?");
	if(result)
	{
		return true;
	}
	else
	{
		return false;
	}
}

function TopicValidate()
{
	var myForm = document.forms[0];
	var subject = myForm.Subject;
	if(subject.value.length == 0)
		{
			alert("Please select the subject Name");
			subject.focus();
			return false;
		}
	
	var NewTopicName = myForm.NewTopicName;
	if(NewTopicName.value.length == 0)
		{
			alert("Please enter Topic Name");
			NewTopicName.focus();
			return false;
		}
	var TopicDesc = myForm.TopicDesc;		
	if(TopicDesc.value.length == 0)
	{
		alert("Please enter Topic Description");
		TopicDesc.focus();
		return false;
	}
	
	
}

function TopicUpdateValidate()
{
	var myForm = document.forms[0];
	var TopicUpdateName = myForm.TopicUpdateName;
	if(TopicUpdateName.value.length == 0)
		{
			alert("Please enter Topic Name");
			TopicUpdateName.focus();
			return false;
		}
	var TopicUpdateDesc = myForm.TopicUpdateDesc;		
	if(TopicUpdateDesc.value.length == 0)
	{
		alert("Please enter Topic Description");
		TopicUpdateDesc.focus();
		return false;
	}
}


function confirmDeleteTopic()
{
	var result=confirm("Do You Want To Delete Topic???");
	if(result)
	{
		return true;
	}
	else
	{
		return false;
	}
}

function SubmitQuestion()
{
	var myForm = document.forms[0];
	var SubJectName = myForm.SubJectName;
	if(SubJectName.value == 0)
		{
			alert("Please Existing Subject Name");
			SubJectName.focus();
			return false;
		}	
	var TopicID = myForm.TopicID;
	if(TopicID.value == 0)
		{
			alert("Please Existing Topic Name");
			TopicID.focus();
			return false;
		}
	
	var QuestionDesc = myForm.QuestionDesc;		
	if(QuestionDesc.value.length == 0)
	{
		alert("Please enter Question Description");
		QuestionDesc.focus();
		return false;
	}
	
	var QuesAws = myForm.QuesAws;		
	if(QuesAws.value.length == 0)
	{
		alert("Please enter Question Answer");
		QuesAws.focus();
		return false;
	}
	
}

function QuestionUpdateValidate()
{
 	var myForm=document.forms[1];
	var questionId = myForm.questionId;
	var status = true;
	for(var i=0;i<questionId.length;i++)
	{
		if(questionId[i].checked)
		{
			status=false;
			break;
		}
	}
	if(status)
	{
		alert("Please Select Any One Question");		
		return false;
	}
}

function QuestionDeleteValidate()
{
	//alert("hi i am from validate")
 	var myForm=document.forms[1];
	var DeleteQuestions = myForm.DeleteQuestions;
	//alert(DeleteQuestions);
	var status = true;
	for(var i=0;i<DeleteQuestions.length;i++)
	{
		//alert("hi am under for");
		if(DeleteQuestions[i].checked)
			{
				status=false;
				break;
			}
	}
	if(status)
	{
	//	alert("hi am under if......");
		alert("please select atleast one Question");
		
		return false;
	}
	 
	var result=confirm("Are You Want To Delete Question?");
	if(result)
	{
		return true;
	}
	else
	{
		return false;
	}
}