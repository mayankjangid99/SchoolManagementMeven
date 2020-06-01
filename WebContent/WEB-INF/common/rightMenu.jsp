<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="userProfile" value="${sessionScope.SessionManager.getUserProfileUtils() }" />
      <!-- Control Sidebar -->
      <aside class="control-sidebar control-sidebar-dark">                
        <!-- Create the tabs -->
        <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
          <li class="active"><a aria-expanded="true" href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-wrench"></i></a></li>
          <li class=""><a aria-expanded="false" href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
          <li class=""><a aria-expanded="false" href="#control-sidebar-theme-demo-options-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
        </ul>
        <!-- Tab panes -->
        <div class="tab-content">
        
          <!-- Settings tab content -->
          <div class="tab-pane active" id="control-sidebar-settings-tab">            
            <form method="post">
              <h3 class="control-sidebar-heading">General Settings</h3>
              <section class="sidebar">
	          <ul class="sidebar-menu">
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUPADMIN'}">
	          	<li class="treeview">
	              <a href="#"><i class='fa fa-gears'></i> <span>Global Config</span> <i class="fa fa-angle-left pull-right"></i></a>
		          <ul class="treeview-menu">
		            <li><a href="searchGlobalConfig"><i class='fa fa-check-square-o'></i> <span>Global Config</span></a></li>
		          </ul>
		        </li>
	          	<li class="treeview">
	              <a href="#"><i class='fa fa-gears'></i> <span>Application Config</span> <i class="fa fa-angle-left pull-right"></i></a>
		          <ul class="treeview-menu">
		            <li><a href="searchSchoolProfile"><i class='fa fa-check-square-o'></i> <span>School Profile</span></a></li>
		            <li><a href="searchSchoolSession"><i class='fa fa-check-square-o'></i> <span>School Session</span></a></li>
		            <li><a href="searchClassSection"><i class='fa fa-check-square-o'></i> <span>Class Section</span></a></li>
		            <li><a href="searchClassSubject"><i class='fa fa-check-square-o'></i> <span>Class Subject</span></a></li>
		            <li><a href="searchPaymentCategory"><i class='fa fa-check-square-o'></i> <span>Payment Category</span></a></li>
		            <li><a href="searchFeeCategory"><i class='fa fa-check-square-o'></i> <span>Fee Category</span></a></li>
		            <li><a href="searchReportCardConfig"><i class='fa fa-check-square-o'></i> <span>Report Card Config</span></a></li>
		            <li><a href="addApplicationSettings"><i class='fa fa-check-square-o'></i> <span>Settings</span></a></li>
		            <li><a href="searchDatabase"><i class='fa fa-check-square-o'></i> <span>Database</span></a></li>
		            <li><a href="getServerLog"><i class='glyphicon glyphicon-download-alt'></i> <span>Server Log</span></a></li> 
		            <li><a href="searchMicroServiceConfig"><i class='fa fa-check-square-o'></i> <span>Micro Service Config</span></a></li>
		            <li><a href="searchSalaryConfig"><i class='fa fa-check-square-o'></i> <span>Salary Config</span></a></li>
		          </ul>
		        </li>
	          	<li class="treeview">
	              <a href="#"><i class='glyphicon glyphicon-user'></i> <span>User</span> <i class="fa fa-angle-left pull-right"></i></a>
		          <ul class="treeview-menu">
		            <li><a href="searchUser"><i class='glyphicon glyphicon-user'></i> <span>User</span></a></li>
		            <li><a href="changePassword"><i class='glyphicon glyphicon-user'></i> <span>Change Password</span></a></li>
		            <li><a href="searchUserAttendanceDetails"><i class='glyphicon glyphicon-user'></i> <span>Attendance</span></a></li>
		          </ul>
		        </li>
</c:if>

<c:if test="${userProfile.getUserRoleId() eq 'ROLE_ADMIN'}">
	          	<li class="treeview">
	              <a href="#"><i class='glyphicon glyphicon-user'></i> <span>User</span> <i class="fa fa-angle-left pull-right"></i></a>
		          <ul class="treeview-menu">
		            <li><a href="searchUser"><i class='glyphicon glyphicon-user'></i> <span>User</span></a></li>
		            <li><a href="changePassword"><i class='glyphicon glyphicon-user'></i> <span>Change Password</span></a></li>
		            <li><a href="searchUserAttendanceDetails"><i class='glyphicon glyphicon-user'></i> <span>Attendance</span></a></li>
		            <li><a href="searchSalaryConfig"><i class='fa fa-check-square-o'></i> <span>Salary Config</span></a></li>
		          </ul>
		        </li>
</c:if>
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUBADMIN' }">
	          	<li class="treeview">
	              <a href="#"><i class='glyphicon glyphicon-user'></i> <span>User</span> <i class="fa fa-angle-left pull-right"></i></a>
		          <ul class="treeview-menu">
		            <li><a href="changePassword"><i class='glyphicon glyphicon-user'></i> <span>Change Password</span></a></li>
		            <li><a href="searchUserAttendanceDetails"><i class='glyphicon glyphicon-user'></i> <span>Attendance</span></a></li>
		          </ul>
		        </li>
</c:if>
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_STAFF' }">
	          	<li class="treeview">
	              <a href="#"><i class='glyphicon glyphicon-user'></i> <span>User</span> <i class="fa fa-angle-left pull-right"></i></a>
		          <ul class="treeview-menu">
		            <li><a href="changePassword"><i class='glyphicon glyphicon-user'></i> <span>Change Password</span></a></li>
		            <li><a href="searchUserAttendanceDetails"><i class='glyphicon glyphicon-user'></i> <span>Attendance</span></a></li>
		          </ul>
		        </li>
</c:if>
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_USER' }">
	          	<li class="treeview">
	              <a href="#"><i class='glyphicon glyphicon-user'></i> <span>User</span> <i class="fa fa-angle-left pull-right"></i></a>
		          <ul class="treeview-menu">
		            <li><a href="changePassword"><i class='glyphicon glyphicon-user'></i> <span>Change Password</span></a></li>
		          </ul>
		        </li>
</c:if>
	          </ul>
	          </section>

              <!-- <h3 class="control-sidebar-heading">Chat Settings</h3>

              <div class="form-group">
                <label class="control-sidebar-subheading">
                  Show me as online
                  <input class="pull-right" checked="" type="checkbox">
                </label>                
              </div>/.form-group

              <div class="form-group">
                <label class="control-sidebar-subheading">
                  Turn off notifications
                  <input class="pull-right" type="checkbox">
                </label>                
              </div>/.form-group

              <div class="form-group">
                <label class="control-sidebar-subheading">
                  Delete chat history
                  <a href="javascript::;" class="text-red pull-right"><i class="fa fa-trash-o"></i></a>
                </label>                
              </div> -->
              <!-- /.form-group -->
            </form>
            
          </div><!-- /.tab-pane -->
          <!-- Home tab content -->
          <div class="tab-pane" id="control-sidebar-home-tab">
            <h3 class="control-sidebar-heading">Recent Activity</h3>
            <ul class="control-sidebar-menu">
              <li>
                <a href="javascript::;">
                  <i class="menu-icon fa fa-birthday-cake bg-red"></i>
                  <div class="menu-info">
                    <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>
                    <p>Will be 23 on April 24th</p>
                  </div>
                </a>
              </li>
              <li>
                <a href="javascript::;">
                  <i class="menu-icon fa fa-user bg-yellow"></i>
                  <div class="menu-info">
                    <h4 class="control-sidebar-subheading">Frodo Updated His Profile</h4>
                    <p>New phone +1(800)555-1234</p>
                  </div>
                </a>
              </li>
              <li>
                <a href="javascript::;">
                  <i class="menu-icon fa fa-envelope-o bg-light-blue"></i>
                  <div class="menu-info">
                    <h4 class="control-sidebar-subheading">Nora Joined Mailing List</h4>
                    <p>nora@example.com</p>
                  </div>
                </a>
              </li>
              <li>
                <a href="javascript::;">
                  <i class="menu-icon fa fa-file-code-o bg-green"></i>
                  <div class="menu-info">
                    <h4 class="control-sidebar-subheading">Cron Job 254 Executed</h4>
                    <p>Execution time 5 seconds</p>
                  </div>
                </a>
              </li>
            </ul><!-- /.control-sidebar-menu -->

            <h3 class="control-sidebar-heading">Tasks Progress</h3> 
            <ul class="control-sidebar-menu">
              <li>
                <a href="javascript::;">               
                  <h4 class="control-sidebar-subheading">
                    Custom Template Design
                    <span class="label label-danger pull-right">70%</span>
                  </h4>
                  <div class="progress progress-xxs">
                    <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
                  </div>                                    
                </a>
              </li> 
              <li>
                <a href="javascript::;">               
                  <h4 class="control-sidebar-subheading">
                    Update Resume
                    <span class="label label-success pull-right">95%</span>
                  </h4>
                  <div class="progress progress-xxs">
                    <div class="progress-bar progress-bar-success" style="width: 95%"></div>
                  </div>                                    
                </a>
              </li> 
              <li>
                <a href="javascript::;">               
                  <h4 class="control-sidebar-subheading">
                    Laravel Integration
                    <span class="label label-waring pull-right">50%</span>
                  </h4>
                  <div class="progress progress-xxs">
                    <div class="progress-bar progress-bar-warning" style="width: 50%"></div>
                  </div>                                    
                </a>
              </li> 
              <li>
                <a href="javascript::;">               
                  <h4 class="control-sidebar-subheading">
                    Back End Framework
                    <span class="label label-primary pull-right">68%</span>
                  </h4>
                  <div class="progress progress-xxs">
                    <div class="progress-bar progress-bar-primary" style="width: 68%"></div>
                  </div>                                    
                </a>
              </li>               
            </ul><!-- /.control-sidebar-menu -->         

          </div>
		<div class="tab-pane"
			id="control-sidebar-theme-demo-options-tab">
			<div>
				<!-- <h4 class="control-sidebar-heading">Layout Options</h4>
				<div class="form-group">
					<label class="control-sidebar-subheading"><input
						data-controlsidebar="control-sidebar-open" class="pull-right"
						type="checkbox"> Toggle Right Sidebar Slide</label>
					<p>Toggle between slide over content and push content effects</p>
				</div>
				<div class="form-group">
					<label class="control-sidebar-subheading"><input
						data-sidebarskin="toggle" class="pull-right" type="checkbox">
						Toggle Right Sidebar Skin</label>
					<p>Toggle between dark and light skins for the right sidebar</p>
				</div> -->
				<h4 class="control-sidebar-heading">Skins</h4>
				<ul class="list-unstyled clearfix">
					<li style="float: left; width: 33.33333%; padding: 5px;"><a
						href="javascript:void(0);" data-skin="skin-blue"
						style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
						class="clearfix full-opacity-hover"><div>
								<span
									style="display: block; width: 20%; float: left; height: 7px; background: #367fa9;"></span><span
									class="bg-light-blue"
									style="display: block; width: 80%; float: left; height: 7px;"></span>
							</div>
							<div>
								<span
									style="display: block; width: 20%; float: left; height: 20px; background: #222d32;"></span><span
									style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
							</div></a>
					<p class="text-center no-margin">Blue</p></li>
					<li style="float: left; width: 33.33333%; padding: 5px;"><a
						href="javascript:void(0);" data-skin="skin-black"
						style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
						class="clearfix full-opacity-hover"><div
								style="box-shadow: 0 0 2px rgba(0, 0, 0, 0.1)" class="clearfix">
								<span
									style="display: block; width: 20%; float: left; height: 7px; background: #fefefe;"></span><span
									style="display: block; width: 80%; float: left; height: 7px; background: #fefefe;"></span>
							</div>
							<div>
								<span
									style="display: block; width: 20%; float: left; height: 20px; background: #222;"></span><span
									style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
							</div></a>
					<p class="text-center no-margin">Black</p></li>
					<li style="float: left; width: 33.33333%; padding: 5px;"><a
						href="javascript:void(0);" data-skin="skin-purple"
						style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
						class="clearfix full-opacity-hover"><div>
								<span
									style="display: block; width: 20%; float: left; height: 7px;"
									class="bg-purple-active"></span><span class="bg-purple"
									style="display: block; width: 80%; float: left; height: 7px;"></span>
							</div>
							<div>
								<span
									style="display: block; width: 20%; float: left; height: 20px; background: #222d32;"></span><span
									style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
							</div></a>
					<p class="text-center no-margin">Purple</p></li>
					<li style="float: left; width: 33.33333%; padding: 5px;"><a
						href="javascript:void(0);" data-skin="skin-green"
						style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
						class="clearfix full-opacity-hover"><div>
								<span
									style="display: block; width: 20%; float: left; height: 7px;"
									class="bg-green-active"></span><span class="bg-green"
									style="display: block; width: 80%; float: left; height: 7px;"></span>
							</div>
							<div>
								<span
									style="display: block; width: 20%; float: left; height: 20px; background: #222d32;"></span><span
									style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
							</div></a>
					<p class="text-center no-margin">Green</p></li>
					<li style="float: left; width: 33.33333%; padding: 5px;"><a
						href="javascript:void(0);" data-skin="skin-red"
						style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
						class="clearfix full-opacity-hover"><div>
								<span
									style="display: block; width: 20%; float: left; height: 7px;"
									class="bg-red-active"></span><span class="bg-red"
									style="display: block; width: 80%; float: left; height: 7px;"></span>
							</div>
							<div>
								<span
									style="display: block; width: 20%; float: left; height: 20px; background: #222d32;"></span><span
									style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
							</div></a>
					<p class="text-center no-margin">Red</p></li>
					<li style="float: left; width: 33.33333%; padding: 5px;"><a
						href="javascript:void(0);" data-skin="skin-yellow"
						style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
						class="clearfix full-opacity-hover"><div>
								<span
									style="display: block; width: 20%; float: left; height: 7px;"
									class="bg-yellow-active"></span><span class="bg-yellow"
									style="display: block; width: 80%; float: left; height: 7px;"></span>
							</div>
							<div>
								<span
									style="display: block; width: 20%; float: left; height: 20px; background: #222d32;"></span><span
									style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
							</div></a>
					<p class="text-center no-margin">Yellow</p></li>
					<li style="float: left; width: 33.33333%; padding: 5px;"><a
						href="javascript:void(0);" data-skin="skin-blue-light"
						style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
						class="clearfix full-opacity-hover"><div>
								<span
									style="display: block; width: 20%; float: left; height: 7px; background: #367fa9;"></span><span
									class="bg-light-blue"
									style="display: block; width: 80%; float: left; height: 7px;"></span>
							</div>
							<div>
								<span
									style="display: block; width: 20%; float: left; height: 20px; background: #f9fafc;"></span><span
									style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
							</div></a>
					<p class="text-center no-margin" style="font-size: 12px">Blue
							Light</p></li>
					<li style="float: left; width: 33.33333%; padding: 5px;"><a
						href="javascript:void(0);" data-skin="skin-black-light"
						style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
						class="clearfix full-opacity-hover"><div
								style="box-shadow: 0 0 2px rgba(0, 0, 0, 0.1)" class="clearfix">
								<span
									style="display: block; width: 20%; float: left; height: 7px; background: #fefefe;"></span><span
									style="display: block; width: 80%; float: left; height: 7px; background: #fefefe;"></span>
							</div>
							<div>
								<span
									style="display: block; width: 20%; float: left; height: 20px; background: #f9fafc;"></span><span
									style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
							</div></a>
					<p class="text-center no-margin" style="font-size: 12px">Black
							Light</p></li>
					<li style="float: left; width: 33.33333%; padding: 5px;"><a
						href="javascript:void(0);" data-skin="skin-purple-light"
						style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
						class="clearfix full-opacity-hover"><div>
								<span
									style="display: block; width: 20%; float: left; height: 7px;"
									class="bg-purple-active"></span><span class="bg-purple"
									style="display: block; width: 80%; float: left; height: 7px;"></span>
							</div>
							<div>
								<span
									style="display: block; width: 20%; float: left; height: 20px; background: #f9fafc;"></span><span
									style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
							</div></a>
					<p class="text-center no-margin" style="font-size: 12px">Purple
							Light</p></li>
					<li style="float: left; width: 33.33333%; padding: 5px;"><a
						href="javascript:void(0);" data-skin="skin-green-light"
						style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
						class="clearfix full-opacity-hover"><div>
								<span
									style="display: block; width: 20%; float: left; height: 7px;"
									class="bg-green-active"></span><span class="bg-green"
									style="display: block; width: 80%; float: left; height: 7px;"></span>
							</div>
							<div>
								<span
									style="display: block; width: 20%; float: left; height: 20px; background: #f9fafc;"></span><span
									style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
							</div></a>
					<p class="text-center no-margin" style="font-size: 12px">Green
							Light</p></li>
					<li style="float: left; width: 33.33333%; padding: 5px;"><a
						href="javascript:void(0);" data-skin="skin-red-light"
						style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
						class="clearfix full-opacity-hover"><div>
								<span
									style="display: block; width: 20%; float: left; height: 7px;"
									class="bg-red-active"></span><span class="bg-red"
									style="display: block; width: 80%; float: left; height: 7px;"></span>
							</div>
							<div>
								<span
									style="display: block; width: 20%; float: left; height: 20px; background: #f9fafc;"></span><span
									style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
							</div></a>
					<p class="text-center no-margin" style="font-size: 12px">Red
							Light</p></li>
					<li style="float: left; width: 33.33333%; padding: 5px;"><a
						href="javascript:void(0);" data-skin="skin-yellow-light"
						style="display: block; box-shadow: 0 0 3px rgba(0, 0, 0, 0.4)"
						class="clearfix full-opacity-hover"><div>
								<span
									style="display: block; width: 20%; float: left; height: 7px;"
									class="bg-yellow-active"></span><span class="bg-yellow"
									style="display: block; width: 80%; float: left; height: 7px;"></span>
							</div>
							<div>
								<span
									style="display: block; width: 20%; float: left; height: 20px; background: #f9fafc;"></span><span
									style="display: block; width: 80%; float: left; height: 20px; background: #f4f5f7;"></span>
							</div></a>
					<p class="text-center no-margin" style="font-size: 12px;">Yellow
							Light</p></li>
				</ul>
			</div>
		</div>
		<!-- /.tab-pane -->
        </div>
      </aside>
	<div class='control-sidebar-bg'></div>