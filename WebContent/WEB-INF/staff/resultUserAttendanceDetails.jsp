<%-- <%@ include file="../common/header.jsp" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href='${pageContext.request.contextPath}/static/calendar/css/fullcalendar.css' rel='stylesheet' />
<link href='${pageContext.request.contextPath}/static/calendar/css/fullcalendar.print.css' rel='stylesheet' media='print' />
<script src='${pageContext.request.contextPath}/static/calendar/js/moment.min.js'></script>
<%-- <script src='${pageContext.request.contextPath}/static/calendar/js/lib/jquery.min.js'></script> --%>
<script src='${pageContext.request.contextPath}/static/calendar/fullcalendar.min.js'></script>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            User Attendance
            <small>Monthly</small>
          </h1>
          <!-- <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
            <li class="active">Here</li>
          </ol> -->
        </section>
        
<%@ include file="../common/message.jsp" %>
        
        <!-- Main content -->
        <section class="content">
			<div class="box box-primary" style="min-height: 1600px;">
				<div class="box-header">
					<h3 class="box-title">User Attendance Details</h3>
					<div class="col-md-6 pull-right">
					<a href="searchUserAttendanceDetails" class="btn btn-primary pull-right">Search User Attendance</a>
				</div>
				</div>
				<br>
				<div class="col-md-12">
					<div class="col-md-6">
					</div>
					<div class="col-md-6">
					<div class="fc-button-group pull-right">
						<button type="button" class="fc-prev-button fc-button fc-state-default fc-corner-left calPrev"> <span class="fc-icon fc-icon-left-single-arrow"></span></button>
						<button type="button" class="fc-next-button fc-button fc-state-default fc-corner-right calNext"> <span class="fc-icon fc-icon-right-single-arrow"></span></button>
					</div>
					</div>
				</div>
				
         		 <!-- Your Page Content Here -->
          		<div id='calendar'></div><br><br>
          			
          
          <div class="col-md-12">
          	<div class="col-md-6">
          		<div class="box box-primary">
                <div class="box-header with-border">
                  <i class="fa fa-bar-chart-o"></i>
                  <h3 class="box-title">Month</h3>
                  <div class="box-tools pull-right">
                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
<!--                     <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button> -->
                  </div>
                </div>
                <div class="box-body">
                	<div class="chart">
	                	<div id="lineLegendPie"></div>
	                	<div class="col-md-10">
	                    	<div class="chart-responsive">
	                    		<canvas class="pieChart" width="530px" height="220px"></canvas>   
	                    	</div>
	                    </div>   
	                    <div class="col-md-2">
	                    	<ul class="chart-legend clearfix">
	                    	</ul>
	                    </div>
                    </div>            
                </div><!-- /.box-body -->
              </div>
           </div>
           <div class="col-md-6"></div>   
           <div class="col-md-10">
              <div class="box box-primary">
                <div class="box-header with-border">
                  <i class="fa fa-bar-chart-o"></i>
                  <h3 class="box-title">Year</h3>
                  <div class="box-tools pull-right">
                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
<!--                     <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button> -->
                  </div>
                </div>
                <div class="box-body">
                  <div class="chart">
                  	<div id="lineLegendBar"></div>
                  	<div class="col-md-10">
                      	<div class="chart-responsivee">
                    		<canvas class="barChart" width="530px" height="200px"></canvas>
                    	</div>
	                </div>   
	                <div class="col-md-2">
	                   	<ul class="chart-legend clearfix">
	                   	</ul>
	                </div>
                  </div>
                </div><!-- /.box-body -->
              </div>
           </div>
              
         </div>

			</div>
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->

<%-- <%@ include file="../common/footer.jsp" %> --%>

<style>
	#calendar {
		max-width: 900px;
		margin: 0 auto;
		margin-top: 3%;		
	}
</style>


<script src="${pageContext.request.contextPath}/static/plugins/chartjs/Chart.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/chartPlugin.js" type="text/javascript"></script>

<script>
	
$(document).ready(function() {
	var attendanceDataYearly = null;
	var attendanceDataMonthly = null;
	var totalPresent = 0;
	var totalAbsent = 0;
	
		// Calendar	And Pie Chart at Loading... time
		$.ajax({
			   url : "fetchUserAttendanceDetails",
			   data: "date=${param.date }&classCode=${param.classCode }&sectionCode=${param.sectionCode }&rollNo=${param.rollNo }&admissionNo=${param.admissionNo }" ,
				//dataType : "json",
				cache: false,
				async: false,
			   success: function(data) {
			 	//called when successful
			 		attendanceDataMonthly = data;
			 		calMonthlyPresentAbsent();
			 		//console.log(totalAbsent);
			 		var jsonData = {"chartPieData":[{value: totalAbsent,color: "#f56954",highlight: "#f56954",label: "Absent"},{value: totalPresent,color: "#00a65a",highlight: "#00a65a",label: "Present"}]};
			 		$("#lineLegendPie").popPieChart(jsonData);
			 		//pupulatePieChart("lineLegendPie", totalPresent, totalAbsent);
			   },
			   error: function(e) {
				   alert("error");
			   }
		});


//		Populate Event on Next Button 
		$('.fc-next-button').off('click').on('click', function(){
			$.ajax({
				   url : "fetchUserAttendanceDetails",
				   data: "date="+attendanceDataMonthly.date+"-"+attendanceDataMonthly.month+"-"+attendanceDataMonthly.year+"&classCode=${param.classCode }&sectionCode=${param.sectionCode }&rollNo=${param.rollNo }&admissionNo=${param.admissionNo }&nextMonth=Y&prevMonth=N" ,
					//dataType : "json",
					cache: false,
					async: false,
				   success: function(data) {
				 	//called when successful
				 		attendanceDataMonthly = data;
				 		if(data.hasError == "true")
				 			alert(attendanceDataMonthly.errorMsg);
				 		calMonthlyPresentAbsent();
				 		var jsonData = {"chartPieData":[{value: totalAbsent,color: "#f56954",highlight: "#f56954",label: "Absent"},{value: totalPresent,color: "#00a65a",highlight: "#00a65a",label: "Present"}]};
				 		$("#lineLegendPie").popPieChart(jsonData);
				 		//pupulatePieChart("lineLegendPie", totalPresent, totalAbsent);
				   },
				   error: function(e) {
					   alert("error");
				   }
			});
			$('#calendar').fullCalendar('removeEvents');
			$('#calendar').fullCalendar('addEventSource', attendanceDataMonthly.attendanceCalendarJsonModels);
			$('#calendar').fullCalendar('rerenderEvents');
			$('#calendar').fullCalendar('next');
		});


// 		Populate Event on Previous Button 
		$('.fc-prev-button').off('click').on('click', function(){
			$.ajax({
				   url : "fetchUserAttendanceDetails",
				   data: "date="+attendanceDataMonthly.date+"-"+attendanceDataMonthly.month+"-"+attendanceDataMonthly.year+"&classCode=${param.classCode }&sectionCode=${param.sectionCode }&rollNo=${param.rollNo }&admissionNo=${param.admissionNo }&nextMonth=N&prevMonth=Y" ,
					//dataType : "json",
					cache: false,
					async: false,
				   success: function(data) {
				 	//called when successful
					 	attendanceDataMonthly = data;
				 		if(data.hasError == "true"){
				 			alert(attendanceDataMonthly.errorMsg);
				 		}
					 	calMonthlyPresentAbsent();
					 	var jsonData = {"chartPieData":[{value: totalAbsent,color: "#f56954",highlight: "#f56954",label: "Absent"},{value: totalPresent,color: "#00a65a",highlight: "#00a65a",label: "Present"}]};
					 	$("#lineLegendPie").popPieChart(jsonData);
					 	//pupulatePieChart("lineLegendPie", totalPresent, totalAbsent);
				   },
				   error: function(e) {
					   alert("error");
				   }
			});
			$('#calendar').fullCalendar('removeEvents');
			$('#calendar').fullCalendar('addEventSource', attendanceDataMonthly.attendanceCalendarJsonModels);
			$('#calendar').fullCalendar('rerenderEvents');
			$('#calendar').fullCalendar('prev');
		});

// 		Populate Calendar
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			defaultDate: attendanceDataMonthly.year+'-'+attendanceDataMonthly.month+'-'+attendanceDataMonthly.date, //'2016-01-12'
			editable: true,
			eventLimit: true, // allow "more" link when too many events
			events: attendanceDataMonthly.attendanceCalendarJsonModels
// 				attendanceDataMonthly.result
			,
			loading: function(bool) {
				$('#loading').toggle(bool);
			}
		});
 		$('.fc-right').remove();
 		$('.fc-left').hide();
 		$('.fc-today-button').remove();		


//  	Calculate Monthly Present and Absent
		function calMonthlyPresentAbsent()
		{
			totalPresent = 0;
			totalAbsent = 0;
			if(attendanceDataMonthly.attendanceCalendarJsonModels){
			 	$.each(attendanceDataMonthly.attendanceCalendarJsonModels, function(i, val){
			 		if(val.title == '1. Present')
			 			totalPresent = totalPresent + 1;
			 		else if(val.title == '1. Absent')
			 			totalAbsent = totalAbsent + 1;
			 	});
			}
		}



// 		Bar chart
		$.ajax({
 			   url : "fetchUserAttendanceYearly",
 			   data: "date=${param.date }&classCode=${param.classCode }&sectionCode=${param.sectionCode }&rollNo=${param.rollNo }&admissionNo=${param.admissionNo }" ,
 				//dataType : "json",
 				cache: false,
 				async: false,
 			   success: function(data) {
 			 	//called when successful
 			 		attendanceDataYearly = data.attendanceBarChartJsonModel;
 			 		var jsonData = {"chartLabels": attendanceDataYearly.months,
	                 "chartDatasets": [
	                   {
	                     label: "Absent",
	                     fillColor: "#f56954",
	                     strokeColor: "#f56954",
	                     pointColor: "#f56954",
	                     pointStrokeColor: "#c1c7d1",
	                     pointHighlightFill: "#fff",
	                     pointHighlightStroke: "rgba(220,220,220,1)",
	                     data: attendanceDataYearly.absentList
	                   },
	                   {
	                     label: "Present",
	                     fillColor: "rgba(60,141,188,0.9)",
	                     strokeColor: "rgba(60,141,188,0.8)",
	                     pointColor: "#3b8bba",
	                     pointStrokeColor: "rgba(60,141,188,1)",
	                     pointHighlightFill: "#fff",
	                     pointHighlightStroke: "rgba(60,141,188,1)",
	                     data: attendanceDataYearly.presentList
	                   }
	                 ]};
 			 		$("#lineLegendBar").popBarChart(jsonData);
 				    //console.log(attendanceDataYearly.presentList);
 			 		//pupulateBarChart(attendanceDataYearly.months, attendanceDataYearly.presentList, attendanceDataYearly.absentList);
 			   },
 			   error: function(e) {
 				   alert("error");
 			   }
 		});

		setTimeout(function(){
			//$(".barChart").css("height", "230px");
		},2000);
		
 		
});
</script>

