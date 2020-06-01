<%-- <%@ include file="../common/header.jsp" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="userProfile" value="${sessionScope.SessionManager.getUserProfileUtils() }" />
<c:set var="std" value="${StudentsDetails[0] }" />

<c:forEach var="admission" items="${std.admissionDetailses }" begin="0" end="0">
<c:set var="admissionDetails" value="${admission }"/>
</c:forEach>

	<style>
	.glyphicon glyphicon-transfer {
    font-size: 70px;
    
}
.btn-info.raised {
    box-shadow: 0 3px 0 0 #35a2b5;
}
.btn.round {
    border-radius: 24px;
}
.btn-info {
    background: #31cde8;
    color: #ffffff;
}
.btn-info2 {
    background: #f26d6d;
    color: #ffffff;
}
.btn-info2:hover {
    background: #e33d3d;
    color: #ffffff;
}
.abtn {
    padding: 6px 20px;
    border: 0 none;
    font-weight: 700;
    letter-spacing: 1px;
    text-transform: uppercase;
    font-size: 16px;
}

td{vertical-align: middle !important;padding: 3px !important;}
thead, tfoot{background-color: #3c8dbc; color: #fff; border-right-color: #3c8dbc; }
.swapAllAttndnc, .swapAllSms, .swapAllEmail{cursor: pointer; color: #3c8dbc; text-align: center; font-size: 14px; font-weight: bold;}
th .fa { font-weight: bold; font-size: 17px; }
</style>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Result Attendance <small>it all starts here</small>
		</h1>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
<%-- 				<c:set var="std" value="${StudentsDetails[0] }" /> --%>
<%-- 				<c:if test="${not empty std }"> --%>
					<h3 class="box-title">
						${admissionDetails.getClassInformation().getClassName() }
						-
						${admissionDetails.getSectionInformation().getSectionName() }
					</h3>
					<div class="col-md-6 pull-right">
						<a href="searchMarkAttendance" class="btn btn-primary pull-right">Search Mark Attendance</a>
						<a href="searchStudentAttendanceDetails" class="btn btn-primary pull-right">Get Student Attendance</a>
					</div>
<%-- 				</c:if> --%>

			</div>

			<form action="saveMarkAttendance" method="post">
			<input type="hidden" name="classCode" value="${ClassCode }">
			<input type="hidden" name="sectionCode" value="${SectionCode }">
				<div class="box-body">
					<!-- <div class="col-sm-12">
						<div class="col-md-9"></div>
						<div class="col-md-3">
							<fieldset class="form-group">
								<label>Date <span class="red">*</span></label> 
								<div class="input-group">
									<input type='text' class="form-control attnDate" name="date" required="required" readonly="readonly" placeholder="dd-mm" />
									<div class="input-group-addon">
				                        <i class="fa fa-calendar"></i>
				                    </div>
				                </div>
							</fieldset>
						</div>
						<br>
					</div> -->
					<div id="example1_wrapper"
						class="dataTables_wrapper form-inline dt-bootstrap">

						<div class="row">
							<div class="col-sm-12">
								<table id="example1"
									class="table table-bordered table-striped dataTable"
									role="grid" aria-describedby="example1_info">
									<thead>
										<tr role="row">
											<th style="width: 90px;">Image</th>
											<th style="width: 120px;">Admission</th>
											<th style="width: 100px;">Roll No</th>
											<th style="width: 200px;">Name</th>
											<th style="width: 200px;">Father Name</th>
											<th style="width: 110px;">Attendance</th>
											<th style="width: 110px;">SMS</th>
											<th style="width: 110px;">Email</th>
										</tr>
									</thead>
									<tbody>

										<c:forEach var="student" items="${StudentMap }">
											<tr role="row" class="odd">
												<td class="sorting_1"><img
													style="margin-left: 22px; border-radius: 50%;" height="40"
													width="40"
													src="${pageContext.request.contextPath }/upload/student_images/${student['image'] }" />
												</td>
												<td>
													${student['admissionNo'] }
													<input type="hidden" name="admissionNo"
													value="${student['admissionNo'] }" />
												</td>
												<td>${student['rollNo'] }
												<input type="hidden" name="rollNo"
													value="${student['rollNo'] }" />
												</td>
												<td>${student['sName'] }</td>
												<td>${student['fName'] }</td>
												<td style="text-align: center;">
												<c:if test="${student['attendance'] eq 'P' }">
													<label class="attendance_status btn abtn btn-info round">${student['attendance'] }</label>
												</c:if>
												<c:if test="${student['attendance'] eq 'A' }">
													<label class="attendance_status btn abtn btn-info2 round">${student['attendance'] }</label>
												</c:if>
												<c:if test="${student['attendance'] eq null }">
													<label class="attendance_status btn btn-info2 round">No data</label>
												</c:if>
<%-- 												<label class="attendance_status btn abtn btn-info round">${student['attendance'] }</label> --%>
													<input type="hidden" class="hAttendence" value="${student['attendance'] }" name="attendanceStatus" /></td>
												<td style="text-align: center;">
												<c:if test="${student['sms'] eq 'Y' }">
													<label class="attendance_status btn abtn btn-info round">${student['sms'] }</label>
												</c:if>
												<c:if test="${student['sms'] eq 'N' }">
													<label class="attendance_status btn abtn btn-info2 round">${student['sms'] }</label>
												</c:if>
												<c:if test="${student['sms'] eq null }">
													<label class="attendance_status btn btn-info2 round">No data</label>
												</c:if>
<!-- 												<label class="sms_status btn abtn btn-info round">Y</label>  -->
													<input type="hidden" class="hSms" value="${student['sms'] }" name="smsStatus" /></td>
												<td style="text-align: center;">
												<c:if test="${student['email'] eq 'Y' }">
													<label class="attendance_status btn abtn btn-info round">${student['email'] }</label>
												</c:if>
												<c:if test="${student['email'] eq 'N' }">
													<label class="attendance_status btn abtn btn-info2 round">${student['email'] }</label>
												</c:if>
												<c:if test="${student['email'] eq null }">
													<label class="attendance_status btn btn-info2 round">No data</label>
												</c:if>
<!-- 												<label class="email_status btn abtn btn-info2 round">N</label>  -->
													<input type="hidden" class="hEmail" value="${student['email'] }" name="emailStatus" />
												</td>
											</tr>
										</c:forEach>
										<c:if test="${empty StudentMap }">
											<tr height="60px;">
												<td colspan="8" style="text-align: center;">No data to
													display</td>
											</tr>
										</c:if>
									</tbody>

									<!-- <tfoot style="background-color: #d9e0e2;">
										<tr>
											<th rowspan="1" colspan="5" style="color: #3c8dbc; text-align: center;">Swap All</th>
											<th rowspan="1" colspan="1" class="swapAllAttndnc">
											<i class="fa fa-exchange"></i></th>
											<th rowspan="1" colspan="1" class="swapAllSms">
											<i class="fa fa-exchange"></i></th>
											<th rowspan="1" colspan="1" class="swapAllEmail">
											<i class="fa fa-exchange"></i></th>
										</tr>
									</tfoot> -->
									<tfoot>
										<tr>
											<th rowspan="1" colspan="1">Image</th>
											<th rowspan="1" colspan="1">Admission</th>
											<th rowspan="1" colspan="1">Roll No</th>
											<th rowspan="1" colspan="1">Name</th>
											<th rowspan="1" colspan="1">Father Name</th>
											<th rowspan="1" colspan="1">Attendance</th>
											<th rowspan="1" colspan="1">SMS</th>
											<th rowspan="1" colspan="1">Email</th>
										</tr>
									</tfoot>
								</table>
							</div>
						</div>

						<div class="row">
							<div class="col-xs-12">
								<div class="col-md-3 col-sm-6 col-xs-6 text-center">
									<div style="display: inline; width: 90px; height: 90px;">
										<input type="text" class="knob knobPresent" value="30" readonly="readonly"
											data-width="90" data-height="90" data-fgcolor="#3c8dbc"
											style="width: 49px; height: 30px; position: absolute; vertical-align: middle; margin-top: 30px; margin-left: -69px; border: 0px; font-style: normal; font-variant: normal; font-weight: bold; font-stretch: normal; font-size: 18px; line-height: normal; font-family: Arial; text-align: center; color: rgb(60, 141, 188); padding: 0px; -webkit-appearance: none; background: none;">
									</div>
									<div class="knob-label">Present</div>
								</div>
								<!-- ./col -->
								<div class="col-md-3 col-sm-6 col-xs-6 text-center">
									<div style="display: inline; width: 90px; height: 90px;">
										<input type="text" class="knob knobAbsent" value="30" readonly="readonly"
											data-width="90" data-height="90" data-fgcolor="#f56954"
											style="width: 49px; height: 30px; position: absolute; vertical-align: middle; margin-top: 30px; margin-left: -69px; border: 0px; font-style: normal; font-variant: normal; font-weight: bold; font-stretch: normal; font-size: 18px; line-height: normal; font-family: Arial; text-align: center; color: rgb(245, 105, 84); padding: 0px; -webkit-appearance: none; background: none;">
									</div>
									<div class="knob-label">Absent</div>
								</div>
								<!-- ./col -->
								<div class="col-md-3 col-sm-6 col-xs-6 text-center">
									<div style="display: inline; width: 90px; height: 90px;">
										<input type="text" class="knob knobSms" value="30" readonly="readonly"
											data-width="90" data-height="90" data-fgcolor="#00a65a"
											style="width: 49px; height: 30px; position: absolute; vertical-align: middle; margin-top: 30px; margin-left: -69px; border: 0px; font-style: normal; font-variant: normal; font-weight: bold; font-stretch: normal; font-size: 18px; line-height: normal; font-family: Arial; text-align: center; color: rgb(0, 166, 90); padding: 0px; -webkit-appearance: none; background: none;">
									</div>
									<div class="knob-label">SMS</div>
								</div>
								<!-- ./col -->
								<div class="col-md-3 col-sm-6 col-xs-6 text-center">
									<div style="display: inline; width: 90px; height: 90px;">
										<input type="text" class="knob knobEmail" value="30" readonly="readonly"
											data-width="90" data-height="90" data-fgcolor="#00c0ef"
											style="width: 49px; height: 30px; position: absolute; vertical-align: middle; margin-top: 30px; margin-left: -69px; border: 0px; font-style: normal; font-variant: normal; font-weight: bold; font-stretch: normal; font-size: 18px; line-height: normal; font-family: Arial; text-align: center; color: rgb(0, 192, 239); padding: 0px; -webkit-appearance: none; background: none;">
									</div>
									<div class="knob-label">Email</div>
								</div>
								<!-- ./col -->
							</div><br><br>
						</div>

					</div>
					<!-- <div class="box-footer">
						<input type="submit" class="btn btn-primary" value="Submit" />
					</div> -->
				</div>
			</form>
		</div>
	</section>
</div>

<%-- <%@ include file="../common/footer.jsp" %> --%>

	<!-- jQuery Knob -->
    <script src="${pageContext.request.contextPath}/static/plugins/knob/jquery.knob.js" type="text/javascript"></script>
    <!-- Sparkline -->
    <script src="${pageContext.request.contextPath}/static/plugins/sparkline/jquery.sparkline.min.js" type="text/javascript"></script>
    
    <script src="${pageContext.request.contextPath}/static/js/chartPlugin.js" type="text/javascript"></script>
    
<!-- <script>
	$(document).ready(function() {
		
		$('.attnDate').datepicker({format: 'dd-mm'});
		
		$(".attendance_status").click(function() {
			//alert("rr");
			var atten_status = $(this).text();
			if (atten_status == 'P') {
				$(this).text('A');
				//$(this).attr('style','color:red;font-size: 20px');
				$(this).removeClass('btn-info').addClass('btn-info2');
			} else {
				$(this).text('P');
				$(this).removeClass('btn-info2').addClass('btn-info');
			}
			var closerInput = searchClosestElementByModel($(this), 'hAttendence');
			$(closerInput).val($(this).text());
			//console.log(closerInput);

// 			For Knob Graph
			var totalPresent = 0;
			var totalAbsent = 0;
			$('.hAttendence').each(function(i, items) {
			//console.log($(this).val()+'i::'+i);
				var hAttenVal = $(this).val();
				if (hAttenVal == 'P')
					totalPresent = totalPresent + 1;
				else
					totalAbsent = totalAbsent + 1;
			});
			$('.knobPresent').val(totalPresent).trigger('change');
			$('.knobAbsent').val(totalAbsent).trigger('change');

			//console.log('P='+totP+',A='+totA);
		});


		
		$(".sms_status").click(function() {
			//alert("rr");
			var sms_status_value = $(this).text();
			if (sms_status_value == 'Y') {
				$(this).text('N');
				$(this).removeClass('btn-info').addClass('btn-info2');
			} else {
				$(this).text('Y');
				$(this).removeClass('btn-info2').addClass('btn-info');
			}
			var closerInput = searchClosestElementByModel($(this), 'hSms');
			$(closerInput).val($(this).text());
			//console.log(closerInput);

			//For Knob Graph
			var totalSmsY = 0;
			var totalSmsN = 0;
			$('.hSms').each(function(i, items) {
				//console.log($(this).val()+'i::'+i);
				var hsmsVal = $(this).val();
				if (hsmsVal == 'Y')
					totalSmsY = totalSmsY + 1;
				else
					totalSmsN = totalSmsN + 1;
			});
			$('.knobSms').val(totalSmsY).trigger('change');
		});


		
		$(".email_status").click(function() {				
			var email_status_value = $(this).text();
			if (email_status_value == 'Y') {
				$(this).text('N');
				$(this).removeClass('btn-info').addClass('btn-info2');
			} else {
				$(this).text('Y');
				$(this).removeClass('btn-info2').addClass('btn-info');
			}
			var closerInput = searchClosestElementByModel($(this), 'hEmail');
			$(closerInput).val($(this).text());
			//console.log(closerInput);

 			//For Knob Graph
			var totalEmailY = 0;
			var totalEmailN = 0;
			$('.hEmail').each(function(i, items) {
				//console.log($(this).val()+'i::'+i);
				var hEmailVal = $(this).val();
				if (hEmailVal == 'Y')
					totalEmailY = totalEmailY + 1;
				else
					totalEmailN = totalEmailN + 1;
			});
			$('.knobEmail').val(totalEmailY).trigger('change');
		});


		
		$('.swapAllAttndnc').click(function() {
			var hAttenVal = $('.hAttendence').val();
			var hAttenLen = $('.hAttendence').length;
			//console.log(len+'kkk');
			$('.hAttendence').each(function(i, items) {
				//console.log($(this).val()+'i::'+i+'iiii'+items.length);
				if (hAttenVal == 'A') {
					$(this).val('P');
					$('.knobPresent').val(hAttenLen).trigger('change');
					$('.knobAbsent').val(0).trigger('change');
				} else {
					$(this).val('A');
					$('.knobPresent').val(0).trigger('change');
					$('.knobAbsent').val(hAttenLen).trigger('change');
				}
			});
			$('.attendance_status').each(function(i, items) {
				//console.log($(this).val()+'i::'+i);
				if (hAttenVal == 'A') {
					$(this).text('P');
					$(this).removeClass('btn-info2').addClass('btn-info');
				} else {
					$(this).text('A');
					$(this).removeClass('btn-info').addClass('btn-info2');
				}
			});

		});


		
		$('.swapAllSms').click(function() {
			var hSmsVal = $('.hSms').val();
			var hSmsLen = $('.hSms').length;
			//console.log(p);
			$('.hSms').each(function(i, items) {
				//console.log($(this).val()+'i::'+i);
				if (hSmsVal == 'N') {
					$(this).val('Y');
					$('.knobSms').val(hSmsLen).trigger('change');
				} else{
					$(this).val('N');
					$('.knobSms').val(0).trigger('change');
				}
			});
			
			$('.sms_status').each(function(i, items) {
				//console.log($(this).val()+'i::'+i);
				if (hSmsVal == 'N') {
					$(this).text('Y');
					$(this).removeClass('btn-info2').addClass('btn-info');
				} else {
					$(this).text('N');
					$(this).removeClass('btn-info').addClass('btn-info2');
				}
			});
		});


		
		$('.swapAllEmail').click(function() {
			var hEmailVal = $('.hEmail').val();
			var hEmailLen = $('.hEmail').length;
			//console.log(p);
			$('.hEmail').each(function(i, items) {
				//console.log($(this).val()+'i::'+i);
				if (hEmailVal == 'N') {
					$(this).val('Y');
					$('.knobEmail').val(hEmailLen).trigger('change');
				} else {
					$(this).val('N');
					$('.knobEmail').val(0).trigger('change');
				}
			});
			$('.email_status').each(function(i, items) {
				//console.log($(this).val()+'i::'+i);
				if (hEmailVal == 'N') {
					$(this).text('Y');
					$(this).removeClass('btn-info2').addClass('btn-info');
				} else {
					$(this).text('N');
					$(this).removeClass('btn-info').addClass('btn-info2');
				}
			});

		});


		
		function searchClosestElementByModel(thisElement, modelName) {
			var element = null;
			var elements = null;
			var parentElement = thisElement;
			for (var i = 0; i < 3; i++) {
				parentElement = $(parentElement).parent();
				if ((elements = $(parentElement).find('input[class~=' + modelName + ']')) && elements.length > 0) {
					element = elements[0];
					break;
				}
			}
			return element;
		}

	});
</script> -->


<!-- page script -->
<script type="text/javascript">
$(function () {
        /* jQueryKnob */
		//$('.knob').val(0).trigger('change');

        //For Knob Graph for Attendence
		var totalPresent = 0;
		var totalAbsent = 0;
		$('.hAttendence').each(function(i, items) {
			//console.log($(this).val()+'i::'+i);
			var hAttenVal = $(this).val();
			if (hAttenVal == 'P')
				totalPresent = totalPresent + 1;
			else if(hAttenVal == 'A')
				totalAbsent = totalAbsent + 1;
		});
		$('.knobPresent').val(totalPresent).trigger('change');
		$('.knobAbsent').val(totalAbsent).trigger('change');
		
		
		//For Knob Graph from Sms
		var totalSmsY = 0;
		var totalSmsN = 0;
		$('.hSms').each(function(i, items) {
			//console.log($(this).val()+'i::'+i);
			var hsmsVal = $(this).val();
			if (hsmsVal == 'Y')
				totalSmsY = totalSmsY + 1;
			else if (hsmsVal == 'N')
				totalSmsN = totalSmsN + 1;
		});
		$('.knobSms').val(totalSmsY).trigger('change');



      //For Knob Graph for Email
		var totalEmailY = 0;
		var totalEmailN = 0;
		$('.hEmail').each(function(i, items) {
			//console.log($(this).val()+'i::'+i);
			var hEmailVal = $(this).val();
			if (hEmailVal == 'Y')
				totalEmailY = totalEmailY + 1;
			else if (hEmailVal == 'N')
				totalEmailN = totalEmailN + 1;
		});
		$('.knobEmail').val(totalEmailY).trigger('change');


});

</script>