







<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset=utf-8>
<title>Add New Question - </title>
<link type="image/x-icon" href="/a/images/favicon.ico" rel="shortcut icon" />
<link rel="stylesheet" href="/a/css/style.0.css?dt=12jan2016" type="text/css" />



<link rel="stylesheet" href="/a/css/feedback/feedback.css"  type="text/css" media="screen" />
<script type="text/javascript" src="/a/js/jquery-1.7.2.js?dt=19aug2013"></script>
<script type="text/javascript" src="/a/js/ui.core.js?dt=19aug2013"></script>
<script type="text/javascript" src="/a/js/ui.draggable.js?dt=19aug2013"></script>
<script type="text/javascript" src="/a/js/ui.resizable.js?dt=19aug2013"></script>
<script type="text/javascript" src="/a/js/jquery-ui.js?dt=19aug2013"></script>
<script type="text/javascript" src="/a/js/ui.datepicker.js?dt=19aug2013"></script>
<script type="text/javascript" src="/a/js/tabber.js?dt=19aug2013"></script>
<script type="text/javascript" src="/a/js/sorting/jquery.dataTables.js?dt=19aug2013"></script>

<script type="text/javascript" src="/a/js/feedback/feedback.js?dt=19aug2013"></script>
<script type="text/javascript" src="/a/js/feedback/jquery.browser.min.js?dt=19aug2013"></script>
<!--[if lt IE 9]>
  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->         
<script type="text/javascript" src="/a/js/questionbank/questionshow.js?dt=18jan2016"></script>
<script type="text/javascript" src="/a/js/editor/nicEdit.js?dt=19aug2013"></script>
<script type="text/javascript" src="/a/js/select/select2.js?dt=19aug2013"></script>
<link rel="stylesheet" href="/a/css/select/select2.css"  type="text/css" media="screen" />
<script>

$(document).ready(function() { 
	$("#subjectid").select2({
		placeholder: "",
		allowClear: true
	});
	
	$("#topicidresponse").select2({
		placeholder: "",
		allowClear: true
	});
	
	$("#levelid").select2({
		placeholder: "",
		allowClear: true
	});
	$("#qtype").select2({
		placeholder: "",
		allowClear: true
	});
	
});

function setSubCategory(){
	var subCatId = document.getElementById('topicidresponse').value;
	var frm = document.forms["QuestionForm"];
	frm.subjectId.value = subCatId;
	//document.getElementById('subjectId').value = subCatId ;
}

var contextpath = '/a';

</script>
</head>
<body>
	
	<div id="wrapper">
		
<div id="header">
    
    
    <h1><a href="/a/sitemap.do"><img id="clogo" src="/a/uploads/logos/logo-blue.png" alt="kaldin" /></a></h1>
    
  	
    
        
    
  
      
        
      
      
  <!-- top nav end -->
  <ul id="topnav">
  <li ><a href="/a/callAdminProfile.do" id="settingsId">Sourav</a></li>
  <li ><a href="/a/upgrade.do" id="upgradeId">Upgrade</a></li>
      <li ><a href="logout.do" title="Logout">Logout</a></li>
    </ul>
    <!-- topnav end -->
    
  <ul id="nav">
      <li ><a href="/a/sitemap.do" id="homeId">Dashboard</a></li>
      <li class="active"><a href="/a/callshowQuestion.do?click='true'" id="questionBankId">Questions</a></li>
      <li ><a href="/a/callCreateQuestionPaper.do" id="testId">Exams</a></li>
      <li ><a href="/a/group.do" id="usersId">Users</a></li>
      
      <li ><a href="/a/certificatetemplate.do" id="certId">Certificates</a></li>
      
      <li ><a href="/a/emailtemplate.do" id="usersId">Settings  </a></li>
      <li ><a href="/a/callTestWiseResult.do" id="reportsId">Results</a></li>
      </ul>
  <!-- nav end -->
  
</div>
  <!-- header end -->
		<div id="content">
			<div id="sidebar">
					<ul class="sidebar_nav">
						<li><a href="/a/callshowQuestion.do?click='true'" class="active">Questions</a></li>		
						<li><a href="/a/Subject.do">Question Category</a></li>
						<li><a href="/a/Topic.do">Question Sub-Category</a></li>
						<li><a href="/a/Level.do">Complexity</a></li>
						<li><a href="/a/callqueimport.do">Import Questions</a></li> 
					</ul>
					<div class="sidebar_note message note"><p>Arrange questions into categories, sub-categories and their complexities. makes it easy to group and find questions.</p>
				<p> Question paper can contain combination of questions from different categories and complexities giving you an ability to design much simpler to most complex exams you like.</p>
				</div>
			</div>
			<!--RIGHT SECTION ENDS HERE-->
			<div id="main">
				<h3>Add Question</h3>
				<hr>
				
				<div id="ouestionshows" class="add_question">
					<p>
						<label>Category:</label>
						
								<select id='subjectid' onchange='getTopicSecond();' style="width: 460px;">
									<option value='0'>Select Question Category</option>
                            		
                            		
                            		    <option value='6903' selected="selected">Aptitude Questions</option>
                            		
                            		
									
								</select>
							
					</p>
					<p>
					
					
						<label>Sub-Category:</label>
							<span id='topicContain'>
								<select id='topicidresponse' style="width: 460px;">
								<option value='0'>Select Question Sub-Category</option>
                            	
                            		
                            		    <option value='3808' selected="selected">General Questions</option>
                            		
                            		
									
								</select>
							</span>
							<small>(optional)</small>
					
					</p>
					 <p>
						<label>Complexity:</label>
						
								<select id='levelid' style="width: 460px;">
									<option value='0'>Select Complexity</option>
									
									
        								<option value='8953' selected="selected">Beginners </option>
                            		
                            		
			
									
									
                            		
   										<option value='8954'>Intermediate</option>
                            		
			
									
									
                            		
   										<option value='8955'>Expert</option>
                            		
			
									
								</select>
							
							<small>(optional)</small>
					</p>
					<p><label>Question Type:</label>
						<select id="qtype" style="width: 150px;" onchange="showhideqtype();">
							<option value="0">Text</option>
							<option value="1">Audio</option>
							<option value="2">Video</option>
						</select>
					</p>
					<div id="questtype" style="display:none;"><p><label>URL:</label> <input type="text" name="qtypeurl" id="qtypeurl" placeholder="Enter the URL" size="50"/>
					<small id="typeSupport"></small></p>
					</div>
					<table>
						<tr>
							<td valign="top" style="border:0px;imprtant!;width: 120px;" align="right">Question:</td>
							<td style="border:0px;padding-left:0px;imprtant!; "><textarea id="newquestion" cols="80" rows="5"></textarea></td>
						</tr>
					</table>						
					
					<p style="margin: 5px 0;">
						<label>Answer Type:</label>
						<span style="margin: 5px 0;">
						<input type='radio' name='answerType' onclick='getChoice();' checked="checked" id="singleAswerType"/>Single Choice  
						&nbsp; &nbsp; &nbsp;<input type='radio' name='answerType' onclick='getChoice();' id="mulitpleAswerType" />&nbsp;Multiple Choice
						&nbsp; &nbsp; &nbsp;<input type='radio' name='answerType' onclick='getChoice();' id="subjectiveAswerType" />&nbsp;Subjective
						</span>
					</p>
					
					<p id="lblTotalOptions">
						<label>Options:</label>
						<select id='TotalOptions' onChange='getTextBoxes();'>
								<option value='0'>Select Total Options:</option>
								<option value='2'>2</option>
								<option value='3'>3</option>
								<option value='4' selected="selected">4</option>
								<option value='5'>5</option>
								<option value='6'>6</option>
								<option value='7'>7</option>
						</select>
					</p>
					
					
					<table id="optA">
						<tr >
							<td valign="top" style="border:0px;imprtant!;width: 120px;" align="right">A:</td>
							<td style="border:0px;padding-left:0px;imprtant!; "><textarea id="optionA" cols="60" rows="2" > </textarea></td>
						</tr>
					</table>						
					
					<table id="optB">
						<tr>
							<td valign="top" style="border:0px;imprtant!;width: 120px;" align="right">B:</td>
							<td style="border:0px;padding-left:0px;imprtant!; "><textarea id="optionB" cols="60" rows="2" > </textarea></td>
						</tr>
					</table>						
					
					<table  id="optC">
						<tr>
							<td valign="top" style="border:0px;imprtant!;width: 120px;" align="right">C:</td>
							<td style="border:0px;padding-left:0px;imprtant!; "><textarea id="optionC" cols="60" rows="2" > </textarea></td>
						</tr>
					</table>						
					
					<table id="optD">
						<tr>
							<td valign="top" style="border:0px;imprtant!;width: 120px;" align="right">D:</td>
							<td style="border:0px;padding-left:0px;imprtant!; "><textarea id="optionD" cols="60" rows="2" > </textarea></td>
						</tr>
					</table>						
					
					<table id="optE">
						<tr>
							<td valign="top" style="border:0px;imprtant!;width: 120px;" align="right">E:</td>
							<td style="border:0px;padding-left:0px;imprtant!; "><textarea id="optionE" cols="60" rows="2" > </textarea></td>
						</tr>
					</table>						
					
					<table id="optF">
						<tr>
							<td valign="top" style="border:0px;imprtant!;width: 120px;" align="right">F:</td>
							<td style="border:0px;padding-left:0px;imprtant!; "><textarea id="optionF" cols="60" rows="2" > </textarea></td>
						</tr>
					</table>						
									
					
					<table id="optG">
						<tr>
							<td valign="top" style="border:0px;imprtant!;width: 120px;" align="right">G:</td>
							<td style="border:0px;padding-left:0px;imprtant!; "><textarea id="optionG" cols="60" rows="2" > </textarea></td>
						</tr>
					</table>						
							
					
				
					
					<p>
						<span id='PlaceAnswerChoice' style="width: 100%;"></span>
					</p>
					
					<table>
						<tr>
							<td valign="top" style="border:0px;imprtant!;width: 120px;" align="right">Hint:</td>
							<td style="border:0px;padding-left:0px;imprtant!; "><textarea id="questionHint" cols="53" rows="2" placeholder="optional"></textarea></td>
						</tr>
					</table>
					
					<p>
						<label></label>
						<input type='button' class="btn_green" value='Save Question' onclick='saveQuestion("");'>
						<input type='button' class="btn_green" value='Save and Add Another Question' style="text-transform:none;" onclick='saveQuestion("addanother");'>
					</p>
					<input type="hidden" name="selectedsubjectid" id="selectedsubjectid">
					<input type="hidden" name="selectedtopicid" id="selectedtopicid">
					<input type="hidden" name="selectedcomplexity" id="selectedcomplexity">
					
				</div>
				<form name="QuestionForm" method="post" action="/a/showQuestion.do" accept-charset="utf-8">
					<input type="hidden" name="operation" value="">
					<input type="hidden" name="question" value="">
					<input type="hidden" name="topicid" value="3808">
					<input type="hidden" name="optionA" value="">
					<input type="hidden" name="optionB" value="">
					<input type="hidden" name="optionC" value="">
					<input type="hidden" name="optionD" value="">
					<input type="hidden" name="optionE" value="">
					<input type="hidden" name="optionF" value="">
					<input type="hidden" name="optionG" value="">
					<input type="hidden" name="answer" value="">
					<input type="hidden" name="levelid" value="8953">
					<input type="hidden" name="subjectId" value="6903">
					<input type="hidden" name="questionid" value="0">
					<input type="hidden" name="showAsMCQ" value="0">
					<input type="hidden" name="questionHint" value="">
					<input type="hidden" name="selectedsubjectid" value="6903" id="selectedsubjectid">
					<input type="hidden" name="selectedtopicid" value="3808" id="selectedtopicid">
					<input type="hidden" name="selectedcomplexity" value="" id="selectedcomplexity">
					<input type="hidden" name="questionURL" value="">
					<input type="hidden" name="questiontype" value="0">
				</form>
			</div>
		</div>
	</div>
	<script>
		new nicEditor({fullPanel : true}).panelInstance('newquestion');
		
		new nicEditor({fullPanel : true}).panelInstance('optionA');
		new nicEditor({fullPanel : true}).panelInstance('optionB');
		new nicEditor({fullPanel : true}).panelInstance('optionC');
		new nicEditor({fullPanel : true}).panelInstance('optionD');
		new nicEditor({fullPanel : true}).panelInstance('optionE');
		new nicEditor({fullPanel : true}).panelInstance('optionF');
		new nicEditor({fullPanel : true}).panelInstance('optionG');		
		new nicEditor({fullPanel : true}).panelInstance('questionHint');		
		getTextBoxes();
	</script>

</body>
</html>