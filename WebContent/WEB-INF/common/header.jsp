<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="userProfile" value="${sessionScope.SessionManager.userProfileUtils }" />
<c:set var="schoolProfile" value="${sessionScope.SessionManager.schoolProfileUtils }" />
<c:if test="${empty sessionScope.SessionManager }">
<script>
		//window.location = "${pageContext.request.contextPath}/loginDefault";
</script>
</c:if>

	<header class="main-header">
        <!-- Logo -->
        <a href="#" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini">
          	<!-- <b>A</b>LT -->
          
			<c:if test="${not empty userProfile.getImage()}">
            	<img src="${pageContext.request.contextPath}/upload/user_images/${userProfile.getImage()}" class="user-image" alt="User Image" height="53px" width="50px;" />
            </c:if>
            <c:if test="${empty userProfile.getImage()}">
               	<img src="${pageContext.request.contextPath}/static/img/profile.png" class="user-image" alt="User Image" height="53px" width="50px;"/>
            </c:if>
          </span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><b>${userProfile.getUserRoleName() }</b></span>
        </a>
        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
		  <img src="${pageContext.request.contextPath}/upload/school_images/${schoolProfile.logo }" height="50px" width="50px;" class="user-image pull-left" style="border-radius: 50%; padding: 5px; background-color: #fff;" />

<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUPADMIN'}">
          <ul class="nav navbar-nav">
<!--                 <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li> -->
                <li class="dropdown school-listt" title="Active School">
                  <a aria-expanded="false" href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <span>${schoolProfile.schoolCode }</span> &nbsp; | &nbsp; <span>${schoolProfile.name } </span><span class="caret"></span>
                  </a>
                  <ul class="dropdown-menu" role="menu">
                    <!-- <li class="divider"></li>-->
<%-- 					<li><a href="#">${profile.schoolCode } - ${schoolProfile.name }</a></li> --%>
<c:forEach var="profile" items="${sessionScope.SessionManager.schoolProfiles }">
<c:if test="${schoolProfile.schoolCode ne profile.schoolCode}">
					<li>
						<a class="school-select" href="#" data-app-proj-value="${profile.schoolCode }">
							<span>${profile.schoolCode }</span> &nbsp;&nbsp;&nbsp;&nbsp;| 
							<span>${profile.name }</span>
						</a>
					</li>
</c:if>
</c:forEach>
                  </ul>
                </li>
          </ul>
</c:if>
<c:if test="${userProfile.getUserRoleId() ne 'ROLE_SUPADMIN'}">
		<div class="nav navbar-nav">
            <ul class="nav navbar-nav">
              <!-- Messages: style can be found in dropdown.less-->
				<li>
					<a href="#">${schoolProfile.name }</a>
				</li>
			</ul>
		</div>
</c:if>

          <!-- Navbar Right Menu -->
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              <!-- Messages: style can be found in dropdown.less-->
              <li>
              	<a href="resultDashboard">
                	<i class="fa fa-dashboard"></i>
                </a>
              </li>
<!-- 			  <li class="dropdown messages-menu">
                <a aria-expanded="true" href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-envelope-o"></i>
                  <span class="label label-success">4</span>
                </a>
                <ul class="dropdown-menu">
                  <li class="header">You have 4 messages</li>
                  <li>
                    inner menu: contains the actual data
                    <div style="position: relative; overflow: hidden; width: auto; height: 200px;" class="slimScrollDiv"><ul style="overflow-x: hidden; width: 100%; height: 200px;" class="menu">
                      <li>start message
                        <a href="#">
                          <div class="pull-left">
                            <img src="/SchoolManagement/static/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                          </div>
                          <h4>
                            Support Team
                            <small><i class="fa fa-clock-o"></i> 5 mins</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>end message
                      <li>
                        <a href="#">
                          <div class="pull-left">
                            <img src="/SchoolManagement/static/dist/img/user3-128x128.jpg" class="img-circle" alt="user image">
                          </div>
                          <h4>
                            AdminLTE Design Team
                            <small><i class="fa fa-clock-o"></i> 2 hours</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <div class="pull-left">
                            <img src="/SchoolManagement/static/dist/img/user4-128x128.jpg" class="img-circle" alt="user image">
                          </div>
                          <h4>
                            Developers
                            <small><i class="fa fa-clock-o"></i> Today</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <div class="pull-left">
                            <img src="/SchoolManagement/static/dist/img/user3-128x128.jpg" class="img-circle" alt="user image">
                          </div>
                          <h4>
                            Sales Department
                            <small><i class="fa fa-clock-o"></i> Yesterday</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <div class="pull-left">
                            <img src="/SchoolManagement/static/dist/img/user4-128x128.jpg" class="img-circle" alt="user image">
                          </div>
                          <h4>
                            Reviewers
                            <small><i class="fa fa-clock-o"></i> 2 days</small>
                          </h4>
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>
                    </ul><div style="background: rgb(0, 0, 0) none repeat scroll 0% 0%; width: 3px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px;" class="slimScrollBar"></div><div style="width: 3px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51) none repeat scroll 0% 0%; opacity: 0.2; z-index: 90; right: 1px;" class="slimScrollRail"></div></div>
                  </li>
                  <li class="footer"><a href="#">See All Messages</a></li>
                </ul>
              </li> -->
              <!-- Notifications Menu -->
<!-- 			  <li class="dropdown notifications-menu">
                <a aria-expanded="false" href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-bell-o"></i>
                  <span class="label label-warning">10</span>
                </a>
                <ul class="dropdown-menu">
                  <li class="header">You have 10 notifications</li>
                  <li>
                    inner menu: contains the actual data
                    <div style="position: relative; overflow: hidden; width: auto; height: 200px;" class="slimScrollDiv"><ul style="overflow-x: hidden; width: 100%; height: 200px;" class="menu">
                      <li>
                        <a href="#">
                          <i class="fa fa-users text-aqua"></i> 5 new members joined today
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <i class="fa fa-warning text-yellow"></i> Very long description here that may not fit into the page and may cause design problems
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <i class="fa fa-users text-red"></i> 5 new members joined
                        </a>
                      </li>

                      <li>
                        <a href="#">
                          <i class="fa fa-shopping-cart text-green"></i> 25 sales made
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <i class="fa fa-user text-red"></i> You changed your username
                        </a>
                      </li>
                    </ul><div style="background: rgb(0, 0, 0) none repeat scroll 0% 0%; width: 3px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px;" class="slimScrollBar"></div><div style="width: 3px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51) none repeat scroll 0% 0%; opacity: 0.2; z-index: 90; right: 1px;" class="slimScrollRail"></div></div>
                  </li>
                  <li class="footer"><a href="#">View all</a></li>
                </ul>
              </li> -->
              <!-- Tasks Menu -->
<!-- 			  <li class="dropdown tasks-menu">
                <a aria-expanded="false" href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-flag-o"></i>
                  <span class="label label-danger">9</span>
                </a>
                <ul class="dropdown-menu">
                  <li class="header">You have 9 tasks</li>
                  <li>
                    inner menu: contains the actual data
                    <div style="position: relative; overflow: hidden; width: auto; height: 200px;" class="slimScrollDiv"><ul style="overflow-x: hidden; width: 100%; height: 200px;" class="menu">
                      <li>Task item
                        <a href="#">
                          <h3>
                            Design some buttons
                            <small class="pull-right">20%</small>
                          </h3>
                          <div class="progress xs">
                            <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                              <span class="sr-only">20% Complete</span>
                            </div>
                          </div>
                        </a>
                      </li>end task item
                      <li>Task item
                        <a href="#">
                          <h3>
                            Create a nice theme
                            <small class="pull-right">40%</small>
                          </h3>
                          <div class="progress xs">
                            <div class="progress-bar progress-bar-green" style="width: 40%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                              <span class="sr-only">40% Complete</span>
                            </div>
                          </div>
                        </a>
                      </li>end task item
                      <li>Task item
                        <a href="#">
                          <h3>
                            Some task I need to do
                            <small class="pull-right">60%</small>
                          </h3>
                          <div class="progress xs">
                            <div class="progress-bar progress-bar-red" style="width: 60%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                              <span class="sr-only">60% Complete</span>
                            </div>
                          </div>
                        </a>
                      </li>end task item
                      <li>Task item
                        <a href="#">
                          <h3>
                            Make beautiful transitions
                            <small class="pull-right">80%</small>
                          </h3>
                          <div class="progress xs">
                            <div class="progress-bar progress-bar-yellow" style="width: 80%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                              <span class="sr-only">80% Complete</span>
                            </div>
                          </div>
                        </a>
                      </li>end task item
                    </ul><div style="background: rgb(0, 0, 0) none repeat scroll 0% 0%; width: 3px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px;" class="slimScrollBar"></div><div style="width: 3px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51) none repeat scroll 0% 0%; opacity: 0.2; z-index: 90; right: 1px;" class="slimScrollRail"></div></div>
                  </li>
                  <li class="footer">
                    <a href="#">View all tasks</a>
                  </li>
                </ul>
              </li> -->
              <!-- User Account Menu -->
              <li class="dropdown user user-menu">
                <!-- Menu Toggle Button -->
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <!-- The user image in the navbar-->
                  <c:if test="${not empty userProfile.getImage()}">
	              	<img src="${pageContext.request.contextPath}/upload/user_images/${userProfile.getImage()}" class="user-image" alt="User Image" />
	              </c:if>
	              <c:if test="${empty userProfile.getImage()}">
                  	<img src="${pageContext.request.contextPath}/static/img/profile.png" class="user-image" alt="User Image"/>
	              </c:if>
                  <!-- hidden-xs hides the username on small devices so only the image appears. -->
                  <span class="hidden-xs" style="text-transform: capitalize;">${userProfile.getFullname()}</span>
                </a>
                <ul class="dropdown-menu">
                  <!-- The user image in the menu -->
                  <li class="user-header">
                    <c:if test="${not empty userProfile.getImage()}">
	                <img src="${pageContext.request.contextPath}/upload/user_images/${userProfile.getImage()}" class="img-circle" alt="User Image" />
	                </c:if>
	                <c:if test="${empty userProfile.getImage()}">
	                <img src="${pageContext.request.contextPath}/static/img/profile.png" class="img-circle" alt="User Image" />
	                </c:if>  
              		<p style="text-transform: capitalize;">
                    	${userProfile.getFullname()} (${userProfile.getUsername()})
                      <!-- <small>Member since Nov. 2012</small> -->
                    </p>
                  </li>
                  <!-- Menu Body -->
                  <!-- <li class="user-body">
                    <div class="col-xs-4 text-center">
                      <a href="#">Follow</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#">Sales</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#">Friends</a>
                    </div>
                  </li> -->
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_ADMIN' or userProfile.getUserRoleId() eq 'ROLE_SUBADMIN' or userProfile.getUserRoleId() eq 'ROLE_STAFF' }">        
                  <!-- Menu Body -->
                  <li class="user-body" style="background-color: #ecf0f5;">
                    <div class="col-xs-4 text-center">
                    <c:if test="${userProfile.getCheckInStatus() eq 'checkIn'}">
                      <span data-type="${userProfile.getCheckInStatus()}" class="checkin" style="color: #dd4b39 !important;"><span class="glyphicon glyphicon-off"></span></span>
                    </c:if>
                    <c:if test="${userProfile.getCheckInStatus() eq 'checkOut'}">
                      <span data-type="${userProfile.getCheckInStatus()}" class="checkin" style="color: #3c8dbc !important;"><span class="glyphicon glyphicon-off"></span></span>
                    </c:if>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#" class="checkinTime">${userProfile.getCheckIn()}</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#" class="checkoutTime">${userProfile.getCheckOut()}</a>
                    </div>
                  </li>
</c:if>                  
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
<%--                       <a href="viewUser?userId=${userProfile.getUserId()}" class="sendPost btn btn-default btn-flat">Profile</a> --%>
                      <a href="viewUser?encUserId=${userProfile.encUserId}" class="sendPost btn btn-default btn-flat getme" data-type="${userProfile.getUserId()}">Profile</a>
                    </div>
                    <div class="pull-right">
                      <a href="logout" class="btn btn-default btn-flat">Sign Out</a>
                    </div>
                  </li>
                </ul>
              </li>
              <!-- Control Sidebar Toggle Button -->
              <li>
                <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
              </li>
            </ul>
          </div>
        </nav>
      </header>