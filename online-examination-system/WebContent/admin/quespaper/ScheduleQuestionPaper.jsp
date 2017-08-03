<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset=utf-8>
<title>Schedule Exam - Online Examination System</title>

<link rel="stylesheet" href="../../css/Style2.css" type="text/css" />
<!-- <link href='https://fonts.googleapis.com/css?family=Quando' rel='stylesheet' type='text/css'> -->


<script type="text/javascript" src="../../Javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../../Javascript/ui.core.js"></script>
<script type="text/javascript" src="../../Javascript/jquery-ui.js"></script>
<script type="text/javascript" src="../../Javascript/ui.datepicker.js"></script>
<script type="text/javascript" src="../../Javascript/jquery.dataTables.js"></script>
<script type="text/javascript">
	$(function() {
		$("#example").dataTable();
	})
</script>
</head>
<body>
	
	<div id="wrapper">
		
	<%@ include file="header.jsp"%>
	
		<!--	CONTENT -->
	<div id="content">
		<div id="sidebar">
			<ul class="sidebar_nav">
				<li><a href="#">Question Paper</a></li>
				<li ><a href="#" class="active">Scheduled Exam</a></li>
			</ul>
			<div class="sidebar_note">
				<p class="message note">
					Schedule Exams by selecting employees/candidates for any of the Question Papers you created.
				</p>
			</div>
		</div>
		<div id="main">
			<h3>Schedule a Exam</h3>
			<hr/>

			<div class="schedule">
				<form action="#" name="ScheduleTestForm" method="post" >
					<div class="schedule_exm">
							<p>
								<label>Exam Name:</label>
								<input type="text" name="examName" maxlength="45" size="20" value="">
								<select name="testId" id="selectTest">
								<option value="0">Select Question Paper</option>
								<option value="T3989">Core Java</option></select>&nbsp;&nbsp;
								<a href="#" target="_new">
								<img src="../../images/help_on.gif" title="Help on Exam Name"></a>
							</p>	
							<p>
								<label>Exam Start Date & Time:</label>
								<input type="text" name="startDate" size="12" value="" id="tdate">
								<select name="startTime" id="stime">
									<option value="10:00">10:00 AM</option>
									<option value="10:30">10:30 AM</option>
									<option value="11:00">11:00 AM</option>
									<option value="11:30">11:30 AM</option>
									<option value="12:00">12:00 PM</option>
									<option value="12:30">12:30 PM</option>
									<option value="13:00">1:00 PM</option>
									<option value="13:30">1:30 PM</option>
									<option value="14:00">2:00 PM</option>
									<option value="14:30">2:30 PM</option>
									</select>
							</p>	
							<p>
							<label>Exam End Date & Time:</label>
							<input type="text" name="endDate" size="13" value="" id="edate">
							<select name="endTime" id="etime">
								<option value="00:00">12:00 AM</option>
								<option value="00:30">12:30 AM</option>	
								<option value="11:00">11:00 AM</option>
								<option value="11:30">11:30 AM</option>
								<option value="12:00">12:00 PM</option>
								<option value="12:30">12:30 PM</option>
								<option value="13:00">1:00 PM</option>
								<option value="13:30">1:30 PM</option>
								<option value="14:00">2:00 PM</option>
								<option value="14:30">2:30 PM</option>
								<option value="15:00">3:00 PM</option>
								<option value="15:30">3:30 PM</option>
								<option value="16:00">4:00 PM</option>
								<option value="16:30">4:30 PM</option>
								<option value="17:00">5:00 PM</option>
								<option value="17:30">5:30 PM</option>
								<option value="18:00">6:00 PM</option>
								<option value="18:30">6:30 PM</option>
								</select>
									&nbsp;&nbsp;<a href="#" target="_new"><img src="../../images/help_on.gif" title="Help on Dates"></a>
							</p>	
                           
                             <p>
                                  <label>Questions for Exam:</label>                                 
                                  <select name="quesSequence" id="selectquesSequence">
                                  <option value="1">Randomize</option>
								  <option value="0">Sequential</option>
								  </select>
								  &nbsp;&nbsp;<a href="#" target="_new"><img src="../../images/help_on.gif" title="Help on Questions in Exam"></a>
                             </p>
 						
							<p>
							<label>Send Result Email:</label>
								<select name="sendResultEmail" id="sendResultEmail">
								<option value="1">Yes</option>
								<option value="0" selected="selected">No</option>
								</select>
						  	&nbsp;&nbsp;<a href="#" target="_new"><img src="../../images/help_on.gif" title="Help on Result Email"></a>
							</p>
							<p>
								<label>Enable Certificate:</label>
								<select name="isCertificateEnabled" " id="iscertificateenabled">
									<option value="1">Yes</option>
									<option value="0" selected="selected">No</option>
								</select>
							  	&nbsp;&nbsp;<a href="#" target="_new"><img src="../../images/help_on.gif" title="Help on Certificate"></a>
							</p>
							<p>
								<label>Allow Multiple Exam:</label>
								<select name="multiplePublicExam" id="multiplePublicExam">
									<option value="1" selected="selected">Yes</option>
									<option value="0">No</option>
								</select>
						  		&nbsp;&nbsp;<a href="#" target="_new"><img src="../../images/help_on.gif" title="Help on Multiple Exams"></a>
						  		<small class="more_info">This option is only applicable for candidate/users giving Public Exams</small>
							</p>
						</div>
						<div>
							<label>Select Group:</label>
							<select name="groupid" id="groupid">
							<option value="0" selected="selected">All Groups</option>
							<option value="1" selected="selected">Day 1</option>
							<option value="2">Day 2</option></select>						
						</div>
						<div style="margin-top:32px">
						
						<table id="example" class="display">
							<thead>
								<tr>
									<th rowspan="1" colspan="1" width="10%"><input
										type="checkbox" onchange="selectEmail()" name="selectemail"></th>
									<th rowspan="1" colspan="1" width="35%">Group</th>
									<th rowspan="1" colspan="1" width="*">Email ID</th>
								</tr>
							</tbody> 
						</table>
						</div>
						<div>
							<button type="button" class="btn_green" onclick="doSubmit('false');return false;">Schedule Exam</button>
						</div>
								
					</form>
				</div>
			</div>
		</div>
		</div>
	</body>
	<script type="text/javascript">
		$(function() {
			$("#tdate").datepicker({dateFormat: 'dd-M-yy',showOn: 'both', buttonImage: '../../images/cal.png', buttonImageOnly: true,minDate:-0});
		});
	</script>
	<script type="text/javascript">
		$(function() {
			$("#edate").datepicker({dateFormat: 'dd-M-yy',showOn: 'both', buttonImage: '../../images/cal.png', buttonImageOnly: true,minDate:-0});
		});
		
		$(document).ready(function() { 
			
			$(".ui-datepicker-trigger").mouseover(function() {
			    $(this).css('cursor', 'pointer');
			});
		});
	</script>
</html>
