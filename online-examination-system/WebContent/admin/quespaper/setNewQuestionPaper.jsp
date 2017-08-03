<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset=utf-8>
<title>Online Examination System</title>

<link type="image/x-icon" href="/a/images/favicon.ico" rel="shortcut icon" />
<link rel="stylesheet" href="css/Style2.css" type="text/css" />
<!-- <link href='https://fonts.googleapis.com/css?family=Quando' rel='stylesheet' type='text/css'> -->
<script type="text/javascript">

	function QuestionPaperValidate() {
		
		var givenquestion = document.getElementById("numberquestiongiven").value;
		var markperquestion = document.getElementById("markperquestion").value;

		var passingmarks = document.getElementById("passingmarks").value;
	
		var testname = document.getElementById("quesPaperDescription").value;
		
		if (testname == 0 || testname == null) {
			alert("Enter Name of Question Paper! ");
			return false;
		} 
		else if(givenquestion == 0 || givenquestion == null) {
			alert("Enter the No of Questions!");
			return false;
		} 
		else if (isNaN(givenquestion)) {
			alert("Please Enter Valid No of Questions");
			return false;
		} 
		else if (markperquestion == 0 || markperquestion == null) {
			alert("Enter the marks per Questions!");
			return false;
		} 
		else if (passingmarks == 0 || passingmarks == null) {
			alert("Enter the passing marks! ");
			return false;
		} 
	}
</script>
</head>
<body>
	
<div id="wrapper">
		
	<%@ include file="header.jsp"%>
		<!-- topnav end -->
	  <ul id="nav">
	      <li ><a href="SetQuestionPaper.htm" >Question Paper</a></li>
	      <li class="active"><a href="SetNewQuestionPaper.htm">New Question Papers</a></li>
	      <li><a href="#">Question Select</a></li>
	      <li ><a href="#">Settings</a></li>
	   </ul>
	  <!-- nav end -->  
</div>
  <!-- header end -->
	<div id="content">
		<div id="sidebar">
			<ul class="sidebar_nav">
				<li><a href="#" class="active">Question Paper</a></li>
				<li><a href="#">Scheduled Exam</a></li>
			</ul>
			<div class="sidebar_note message note">
			<p>Combine questions from different categories and complexities to create a complete Question Paper.</p>
		</div>
	</div>
		<div id="main">
			<h3>Create Question Paper</h3>
			<hr>
			<div id="q_bank4">
				<form action="SetQuestionSelect.htm" name="QuestionSelect" method="post" >
					<div style="width: 100%; float: left;">
					 <div id="QuestionPaper">
							<p>
								<label>Name of Question Paper:- </label>
								<input type="text" name="quesPaperDescription" value="" id="quesPaperDescription"> 
								<small>Required</small>
							</p>
							<p>
							<label>Duration:</label>
							<select name="duration">
								<option value="15">15 Minutes</option>
								<option value="30">30 Minutes</option>
								<option value="45">45 Minutes</option>
								<option value="60">60 Minutes</option>
								<option value="90">90 Minutes</option>
								<option value="120">120 Minutes</option>
								<option value="150">150 Minutes</option>
								<option value="180">180 Minutes</option>
								<option value="210">210 Minutes</option>
								<option value="240">240 Minutes</option></select>  
								<small>Required</small>
							</p>
							<p>
								<label>No Of Questions:</label>
								<input type="text" name="noofquestions" value="" id="numberquestiongiven" onblur="calculateMark();"> 
								<small>Required</small>
								</p>
							<p>
								<label>Marks per question:</label>
								<input type="text" name="perquestionmarks" value="" id="markperquestion" onblur="calculateMark();">
																			 
								<small>Required</small>
							</p>
							<p>
								<label>Total Marks:</label>
								<input type="hidden" name="totalmarks" value="" id="totalmarks">
								<span id="totalmrk"></span>
							</p>
							<p>
								<label>Passing Marks:</label>
								<input type="text" name="passingmarks" value="" id="passingmarks"> 
								<small>Required</small>
								</p>
							<p>
								<label>Marks per wrong answer:</label>
								<input type="text" name="negativemarks" value="" id="negativemarks"> 
								<small class="more_info">(0 for no negative marking)</small>
							</p>
						</div>
					</div>						

					<div style="margin: 10px auto;">
						<h6>Additional Comments to be shown before Exam Starts:</h6>
						<textarea name="QuesPaperInfo" cols="80" rows="10" id="QuesPaperInfo" style="width: 90%;"></textarea>
					</div>
					
					<div style="margin: 10px auto;">
						<h6>Upload Exam Information document (If it is required)</h6>
						<input type="file" name="file" id="file" class="inputfile" />
						<label for="file">Choose a file</label>
						<br>
					</div>
					<div>
						<input type="submit" class="btn_green"  id="btnsave" value="Choose Individual Questions"  onclick="return QuestionPaperValidate()" >							
					</div>
				</form>
			</div>
		</div>
	</div>
</body>


<script type="text/javascript">
function calculateMark() 
{
	var questionCount = '';
	var markperquest = '';
	var questionCount = '';
	if (document.getElementById("markperquestion").value != '' && document.getElementById("numberquestiongiven").value != '') 
	{
		markperquest = parseInt(document.getElementById("markperquestion").value);
		questionCount = parseInt(document.getElementById("numberquestiongiven").value);
		
		totalMark = (markperquest * questionCount);
		
		document.getElementById("totalmrk").innerHTML = totalMark;
		document.forms["QuestionSelect"].totalmarks.value = totalMark;
	}
}
</script>
</html>
