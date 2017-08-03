<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
<div class="generalIns">
<table>
<tr><th><u>General Instructions:</u></th></tr>
</table>
	<div class="generalInsOps">
	<table >
		<tr><th>1</th>
			<td>Total duration of examination is <c:out value="${QuestionPaperDuration}"/> minutes.</td>
		</tr>
		<tr><th>2</th>
			<td>The clock will be set at the server. The countdown timer in the top right corner 
				of screen and display the remaining time available to you complete the examination.
		 		When the time reaches zero the examination will end by itself.
		 		 You will not request to end or submit your examination.
		 	</td>
		</tr>
		<tr><th>3</th>
			<td>The Question palette displayed on the right side of screen will show the status 
				of each question using one of the following symbols.
			</td>
		</tr>
	</table>	
	</div>
	<div class="generalInsOpsBtn">
		<table>
			<tr><td><img alt="Gray" src="images/gray.png"></td><td>You have not visited the question yet.</td></tr>
			<tr><td><img alt="Gray" src="images/green.png"></td><td>You have answered the question.</td></tr>
			<tr><td><img alt="Gray" src="images/red.png"></td><td>You have not answered the question.</td></tr>		
		</table>
	</div>
	<div class="generalInsOpsBtn">
	 <p>The marked for review status for a question simply indicates that you would like at the question again.
	<span>(in red color if a question is answered and  marked for review  you answer for that question will be  considered in the evaluation).</span></p>
	</div>
</div>

<div style="margin-top:12px;" class="generalIns">
<table>
<tr><th><u>Navigating to a Question:</u></th></tr>
</table>
	<div class="generalInsOps">
		<table >
			<tr><th>4</th>
				<td><b>To answer a question,do the following</b></td>
			</tr>
		</table>
		<div class="generalInsDOps">
			<table >
				<tr><th>a. </th>
					<td>click on the question number in the Question Palette to go to that question directly.</td>
				</tr>
				<tr><th>b.</th>
					<td>select an answer for a multiple choice type question.
						Use the virtual numeric keypad to enter a number an answer for a numeric type question.</td>
				</tr>
				<tr><th>c.</th>
					<td>click on<b> Save & Next</b> to save your answer for the correct question and then go to the next question.</td>
				</tr>
				<tr><th>d.</th>
					<td>click on mark for <b>review & next</b> to save your answer for the correct question,
						markit for review and then go to the next question.</td>
				</tr>
				<tr><th>e.</th>
					<td style="color:red;">Caution note that your answer for the correct 
						question will not be saved if you navigate to another 
						questiondirectly by clicking on its question number.</td>
				</tr>
			</table>
		</div>
		<table >
			<tr><th>5</th>
				<td>You can view all the question by clicking on the question paper button.
						Note That the options for multiple choice type questions will not be shown.</td>
			</tr>
		</table>
	</div>
</div>

<div style="margin-top:12px;" class="generalIns">
<table>
<tr><th><u>Answering a Question:</u></th></tr>
</table>
	<div class="generalInsOps">
		<table >
			<tr><th>6</th>
				<td><b>Procedure for answering a multiple choice type question.</b></td>
			</tr>
		</table>
		<div class="generalInsDOps">
			<table >
				<tr><th>a. </th>
					<td>To select your answer,Click on the button of one of the options.</td>
				</tr>
				<tr><th>b.</th>
					<td>To unselect your chosen answer click on the button of the chosen option again on the clear Response button.</td>
				</tr>
				<tr><th>c.</th>
					<td>To change your chosen answer click on the button of another option.</td>
				</tr>
				<tr><th>d.</th>
					<td>To save your answer.You MUST click on the <b>Save & Next</b> button.</td>
				</tr>
				<tr><th>e.</th>
					<td style="color:red;">To mark the question for review click on the mark for review & Next button. 
								If an answer is selected for a question that is Marked 
								for Review the answer will be be considered in the evaluation.</td>
				</tr>
			</table>
		</div>
		<table >
			<tr><th>7</th>
				<td>To change your answer to a question the has already been answered ,
				first select that question to answering and the follow the procedure for answering that type of question.</td>
			</tr>
			<tr><th>8</th>
				<td>Note the ONLY Question for which answer are  are saved or  marked for review after answer will be considered for evaluation.</td>
			</tr>
		</table>
	</div>
</div>

<div style="margin-top:12px;" class="ExamPaperAggree">
	<table>
		<tr><td><input type="checkbox" id="aggreefirst" name="aggreefirst" value="aggree"></td>
		<td>I Have Read The Instructions .</td>
		</tr>
	</table>
</div>
</body>
</html>