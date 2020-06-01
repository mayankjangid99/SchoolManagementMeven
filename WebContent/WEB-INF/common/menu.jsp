<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="userProfile" value="${sessionScope.SessionManager.getUserProfileUtils() }" />
<c:set var="schoolProfile" value="${sessionScope.SessionManager.getSchoolProfileUtils() }" />
 <!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">

        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">

          <!-- Sidebar user panel (optional) -->
          <%-- <div class="user-panel">
            <div class="pull-left image">
              <c:if test="${not empty userProfile.getImage()}">
              <img src="${pageContext.request.contextPath}/upload/user_images/${userProfile.getImage()}" class="img-circle" alt="User Image" />
              </c:if>
              <c:if test="${empty userProfile.getImage()}">
              <img src="${pageContext.request.contextPath}/static/img/profile.png" class="img-circle" alt="User Image" />
              </c:if>      
            </div>
            <div class="pull-left info">
              <p style="text-transform: capitalize;">${userProfile.getFullname()}</p>
              <!-- Status -->
              <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
          </div><br> --%>

          <!-- search form (Optional) -->
          <!-- <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
              <input type="text" name="q" class="form-control" placeholder="Search..."/>
              <span class="input-group-btn">
                <button type='submit' name='search' id='search-btn' class="btn btn-flat"><i class="fa fa-search"></i></button>
              </span>
            </div>
          </form> -->
          <!-- /.search form -->

          <!-- Sidebar Menu -->
          <ul class="sidebar-menu">
            <!-- <li class="header">HEADER</li> -->
            <!-- Optionally, you can add icons to the links -->
<c:if test="${userProfile.getUserRoleId() ne 'ROLE_USER'}"> 
            <li><a href="#"><i class='fa fa-link'></i> <span>${schoolProfile.currentSessionName }</span></a></li>         
            <li class="treeview">
              <a href="#"><i class='fa fa-retweet'></i> <span>Switch Session</span> <i class="fa fa-angle-left pull-right"></i></a>
              <ul class="treeview-menu">
<c:forEach var="schoolSession" items="${schoolProfile.getSchoolSessions() }"> 
                <li><a href="switchSchoolSession?switchSession=${schoolSession.sessionDetails.sessionCode }&switchSessionName=${schoolSession.sessionDetails.sessionName }">${schoolSession.sessionDetails.sessionName }</a></li>
</c:forEach>
              </ul>
            </li>
</c:if>            

<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUPADMIN'}">            
            <li class="treeview">
              <a href="#"><i class='fa fa-group'></i> <span>Student</span> <i class="fa fa-angle-left pull-right"></i></a>
              <ul class="treeview-menu">
            	<li class="active"><a href="searchStudentDetails"><i class='fa fa-user'></i> <span>Student Details</span></a></li>
            	<li><a href="addStudentDetails"><i class='fa fa-user-plus'></i> <span>Add Student Details</span></a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class='fa fa-calendar '></i> <span>Attendance</span> <i class="fa fa-angle-left pull-right"></i></a>
	          <ul class="treeview-menu">
	            <li><a href="searchMarkAttendance"><i class='fa fa-calendar-plus-o'></i> <span>Mark Attendance</span></a></li>  
	            <li><a href="searchAttendanceDetails"><i class='fa fa-calendar-check-o'></i> <span>Get Attendance</span></a></li> 
	            <li><a href="searchStudentAttendanceDetails"><i class='fa fa-calendar-check-o'></i> <span>Get Student Attendance</span></a></li> 
	          </ul>
	        </li>
	        <li class="treeview">
              <a href="#"><i class='fa fa-money'></i> <span>Student Fee</span> <i class="fa fa-angle-left pull-right"></i></a>
	          <ul class="treeview-menu">
	            <li><a href="searchStudentFeeDetails"><i class='fa fa-check-square-o'></i> <span>Student Fee</span></a></li>
	          </ul>
	        </li>	        
	        <!-- <li class="treeview">
              <a href="#"><i class='fa fa-money'></i> <span>Notes</span> <i class="fa fa-angle-left pull-right"></i></a>
	          <ul class="treeview-menu">
	            <li><a href="addNotes"><i class='fa fa-check-square-o'></i> <span>Add Notes</span></a></li>
	          </ul>
	        </li> -->
	        <li class="treeview">
              <a href="#"><i class='fa fa-bus'></i> <span>Transport</span> <i class="fa fa-angle-left pull-right"></i></a>
	          <ul class="treeview-menu">	            
		        <li><a href="searchTransport"><i class='fa fa-check-square-o'></i> <span>Transport</span></a></li>
	          </ul>
	        </li>	        
	        <li class="treeview">
              <a href="#"><i class='fa fa-vcard-o'></i> <span>Report Card</span> <i class="fa fa-angle-left pull-right"></i></a>
	          <ul class="treeview-menu">	            
		        <li><a href="searchStudentReportCard"><i class='fa fa-file-text'></i> <span>Report Card</span></a></li>
	          </ul>
	        </li>
	        <li class="treeview">
              <a href="#"><i class='fa fa-money'></i> <span>Salary Structure</span> <i class="fa fa-angle-left pull-right"></i></a>
	          <ul class="treeview-menu">
	            <li><a href="addSalaryStructure" data-app-proj-method="post"><i class='fa fa-check-square-o'></i> <span>Add Salary Structure</span></a></li>
	          </ul>
	        </li>
</c:if>	 
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_ADMIN'}">            
            <li class="treeview">
              <a href="#"><i class='fa fa-group'></i> <span>Student</span> <i class="fa fa-angle-left pull-right"></i></a>
              <ul class="treeview-menu">
            	<li class="active"><a href="searchStudentDetails"><i class='fa fa-user'></i> <span>Student Details</span></a></li>
            	<li><a href="addStudentDetails"><i class='fa fa-user-plus'></i> <span>Add Student Details</span></a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class='fa fa-calendar '></i> <span>Attendance</span> <i class="fa fa-angle-left pull-right"></i></a>
	          <ul class="treeview-menu">
	            <li><a href="searchMarkAttendance"><i class='fa fa-calendar-plus-o'></i> <span>Mark Attendance</span></a></li>  
	            <li><a href="searchAttendanceDetails"><i class='fa fa-calendar-check-o'></i> <span>Get Attendance</span></a></li> 
	            <li><a href="searchStudentAttendanceDetails"><i class='fa fa-calendar-check-o'></i> <span>Get Student Attendance</span></a></li> 
	          </ul>
	        </li>
	        <li class="treeview">
              <a href="#"><i class='fa fa-money'></i> <span>Student Fee</span> <i class="fa fa-angle-left pull-right"></i></a>
	          <ul class="treeview-menu">
	            <li><a href="searchStudentFeeDetails"><i class='fa fa-check-square-o'></i> <span>Student Fee</span></a></li>
	          </ul>
	        </li>
	        <li class="treeview">
              <a href="#"><i class='fa fa-money'></i> <span>Transport</span> <i class="fa fa-angle-left pull-right"></i></a>
	          <ul class="treeview-menu">	            
		        <li><a href="searchTransport"><i class='fa fa-check-square-o'></i> <span>Transport</span></a></li>
	          </ul>
	        </li>
</c:if>	 
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUBADMIN'}">            
            <li class="treeview">
              <a href="#"><i class='fa fa-group'></i> <span>Student</span> <i class="fa fa-angle-left pull-right"></i></a>
              <ul class="treeview-menu">
            	<li class="active"><a href="searchStudentDetails"><i class='fa fa-user'></i> <span>Student Details</span></a></li>
            	<li><a href="addStudentDetails"><i class='fa fa-user-plus'></i> <span>Add Student Details</span></a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class='fa fa-calendar '></i> <span>Attendance</span> <i class="fa fa-angle-left pull-right"></i></a>
	          <ul class="treeview-menu">
	            <li><a href="searchMarkAttendance"><i class='fa fa-calendar-plus-o'></i> <span>Mark Attendance</span></a></li>  
	            <li><a href="searchAttendanceDetails"><i class='fa fa-calendar-check-o'></i> <span>Get Attendance</span></a></li> 
	            <li><a href="searchStudentAttendanceDetails"><i class='fa fa-calendar-check-o'></i> <span>Get Student Attendance</span></a></li> 
	          </ul>
	        </li>
	        <li class="treeview">
              <a href="#"><i class='fa fa-money'></i> <span>Student Fee</span> <i class="fa fa-angle-left pull-right"></i></a>
	          <ul class="treeview-menu">
	            <li><a href="searchStudentFeeDetails"><i class='fa fa-check-square-o'></i> <span>Student Fee</span></a></li>
	          </ul>
	        </li>
	        <li class="treeview">
              <a href="#"><i class='fa fa-money'></i> <span>Transport</span> <i class="fa fa-angle-left pull-right"></i></a>
	          <ul class="treeview-menu">	            
		        <li><a href="searchTransport"><i class='fa fa-check-square-o'></i> <span>Transport</span></a></li>
	          </ul>
	        </li>
</c:if>	 
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_STAFF'}">            
            <li class="treeview">
              <a href="#"><i class='fa fa-group'></i> <span>Student</span> <i class="fa fa-angle-left pull-right"></i></a>
              <ul class="treeview-menu">
            	<li class="active"><a href="resultStudentDetails" data-app-proj-method="post"><i class='fa fa-user'></i> <span>Student Details</span></a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class='fa fa-calendar '></i> <span>Attendance</span> <i class="fa fa-angle-left pull-right"></i></a>
	          <ul class="treeview-menu">
	            <li><a href="searchMarkAttendance"><i class='fa fa-calendar-plus-o'></i> <span>Mark Attendance</span></a></li>  
	            <li><a href="searchAttendanceDetails"><i class='fa fa-calendar-check-o'></i> <span>Get Attendance</span></a></li> 
	            <li><a href="searchStudentAttendanceDetails"><i class='fa fa-calendar-check-o'></i> <span>Get Student Attendance</span></a></li> 
	          </ul>
	        </li>
	        <li class="treeview">
              <a href="#"><i class='fa fa-money'></i> <span>Student Fee</span> <i class="fa fa-angle-left pull-right"></i></a>
	          <ul class="treeview-menu">
	            <li><a href="searchStudentFeeDetails"><i class='fa fa-check-square-o'></i> <span>Student Fee</span></a></li>
	          </ul>
	        </li>
</c:if>	 
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_USER'}">            
            <li class="treeview">
              <a href="#"><i class='fa fa-group'></i> <span>Student</span> <i class="fa fa-angle-left pull-right"></i></a>
              <ul class="treeview-menu">
            	<li><a href="viewStudentDetails?admissionNo=${userProfile.studentProfileUtils.admissionNo}" data-app-proj-method="post"><i class='fa fa-user'></i> <span>Student Details</span></a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class='fa fa-calendar '></i> <span>Attendance</span> <i class="fa fa-angle-left pull-right"></i></a>
	          <ul class="treeview-menu">
	            <li><a href="searchStudentAttendanceDetails"><i class='fa fa-calendar-check-o'></i> <span>Get Student Attendance</span></a></li> 
	          </ul>
	        </li>
	        <li class="treeview">
              <a href="#"><i class='fa fa-money'></i> <span>Student Fee</span> <i class="fa fa-angle-left pull-right"></i></a>
	          <ul class="treeview-menu">
	            <li><a href="resultStudentFeeDetails" data-app-proj-method="post"><i class='fa fa-check-square-o'></i> <span>Result Student Fee</span></a></li>
	          </ul>
	        </li>
</c:if>	        
	        
	        
          </ul><!-- /.sidebar-menu -->
        </section>
        <!-- /.sidebar -->
      </aside>