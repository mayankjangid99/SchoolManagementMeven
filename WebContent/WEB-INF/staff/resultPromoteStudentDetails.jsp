<%-- <%@ include file="../common/header.jsp" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="userProfile" value="${sessionScope.SessionManager.getUserProfileUtils() }" />
<style>
.jqx-grid-header{height: 50px !important;}
.jqx-grid-column-header div div{line-height: 40px;}
</style>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/grid/styles/jqx.base.css" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/grid/styles/jqx.ui-redmond.css" type="text/css" />
   
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Promote Student <small>Student Information</small>
		</h1>
		<!-- <ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
			<li class="active">Here</li>
		</ol> -->
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">Students Details</h3>
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUPADMIN' or userProfile.getUserRoleId() eq 'ROLE_ADMIN' or userProfile.getUserRoleId() eq 'ROLE_SUBADMIN'}">				
				<div class="col-md-6 pull-right">
					<a href="addStudentDetails" class="btn btn-primary pull-right">Add Student Details</a>
					<a href="searchStudentDetails" class="btn btn-primary pull-right">Search Student Details</a>
				</div>
</c:if>				
			</div>
			<form role="form" action="updatePromoteStudentDetails" class="formValid" method="post">
				<!-- Hidden Variables -->
				<div style="display: none;">
					<input type="hidden" name="admissionNos" value="${admissionNos }">
				</div>
				<div class="box-body">
					<div class="col-md-12"><br>
						<div class="col-md-4">
							<fieldset class="form-group">
								<label>Session <span class="red">*</span></label> 
								<select name="sessionCode" class="sessionName form-control">
									<option value="">Select</option>
<c:forEach var="session" items="${SessionList }">
									<option value="${session.sessionDetails.sessionCode }">${session.sessionDetails.sessionName }</option>
</c:forEach>							
								</select>
							</fieldset>
						</div>
					</div>
					<div class="col-md-8">
						<div class="col-md-6">
							<div class="form-group">
								<label>Class</label> 
								<input type="text" class="form-control intelliClass-0-className stdClassHints className" name="className" placeholder="Class Name" value="${reportCardConfig.classInformation.className }" />
							</div>
						</div>
						<div class="col-md-2">
							<label>Class ID</label> 
							<input type="text" class="form-control intelliClass-0-classCode classCode" name="classCode" readonly="readonly" value="${reportCardConfig.classInformation.classCode }" />
						</div>
					</div>
					
					<div class="col-md-8">
						<div class="col-md-6">
							<div class="form-group">
								<label>Section</label> 
								<input type="text" class="form-control intelliClass-0-sectionName stdSectionHints sectionName" name="sectionName" placeholder="Section Name" value="${reportCardConfig.sectionInformation.sectionName }" />
							</div>
						</div>
						<div class="col-md-2">
							<label>Section ID</label> 
							<input type="text" class="form-control intelliClass-0-sectionCode sectionCode" name="sectionCode" readonly="readonly" value="${reportCardConfig.sectionInformation.sectionCode }" />
						</div>
					</div>
					
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<input type="submit" class="btn btn-primary" value="Submit" />
				</div>
			</form>
			<div id="jqxWidget" style="font-size: 13px; font-family: Verdana; padding: 2%;">
				<div id="jqxgrid">
				</div>
			</div>
		</div>
	</section>
</div>

<%--     <script type="text/javascript" src="${pageContext.request.contextPath}/static/grid/scripts/jquery-1.11.1.min.js"></script> --%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/grid/jqwidgets/jqxcore.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/grid/jqwidgets/jqxdata.js"></script> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/grid/jqwidgets/jqxbuttons.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/grid/jqwidgets/jqxscrollbar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/grid/jqwidgets/jqxlistbox.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/grid/jqwidgets/jqxdropdownlist.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/grid/jqwidgets/jqxmenu.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/grid/jqwidgets/jqxgrid.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/grid/jqwidgets/jqxgrid.pager.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/grid/jqwidgets/jqxgrid.sort.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/grid/jqwidgets/jqxgrid.filter.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/grid/jqwidgets/jqxgrid.columnsresize.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/grid/jqwidgets/jqxgrid.selection.js"></script> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/grid/jqwidgets/jqxpanel.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/grid/jqwidgets/jqxgrid.edit.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/grid/jqwidgets/jqxdropdownlist.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/grid/jqwidgets/jqxcombobox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/grid/scripts/demos.js"></script>
<script type="text/javascript">
   
        $(document).ready(function () {
        	initializeHints();
            // prepare the data
            var source =
            {
                datatype: "json",
                datafields: [
					{ name: 'sImage'},
                    { name: 'admissionNo'},
                    { name: 'rollNo'},
                    { name: 'className'},
                    { name: 'sectionName'},
                    { name: 'sName'},
                    { name: 'fName'},
                    { name: 'mName'},
                    { name: 'gender'},
                    { name: 'status'}
                ],
                id: 'admissionNo',
                url: "fetchPromoteStudentsDetails?admissionNos=${admissionNos}"
            };

            var cellsrendererImage = function (row, datafield, value) {
                //return '<img style="margin-left: 15px; margin-top: 5px; border-radius: 50%;" height="40" width="40" src="${pageContext.request.contextPath}/upload/student_images/' + value + '"/>';
                if(value != "")
                	return '<img style="margin-left: 15px; margin-top: 5px; border-radius: 50%;" height="40" width="40" src="${pageContext.request.contextPath}/upload/student_images/' + value + '"/>';
                else
                	return '<img style="margin-left: 15px; margin-top: 5px; border-radius: 50%;" height="40" width="40" src="${pageContext.request.contextPath}/static/img/profile.png"/>';
            };

            var cellsrendererGender = function (row, datafield, value) {
                if(value=='M')
                {
					return '<div style="margin-top: 17px;" class="jqx-grid-cell-left-align">Male</div>';
                }
                else if(value='F')
                {
					return '<div style="margin-top: 17px;" class="jqx-grid-cell-left-align">Female</div>';
                }
                else
                    {
						return '<div style="margin-top: 17px;" class="jqx-grid-cell-left-align"></div>';
                    }
            };                
            
            $("#jqxgrid").jqxGrid(
            {
                width: 965,
                source: source,
                pageable: true,
                autoheight: true,
                pagesizeoptions: ['10', '20', '50', '100'],
                rowsheight: 50,
                sortable: true,
                editmode: "click",
                theme: 'ui-redmond',
                /* loadComplete:function (){
        			$('.delete-icon').click(function() {
        				var agree=confirm('<s:text name="common.record.inactive"/>');
        				 return agree;
        				});
        			  }, */
                columns: [
						  { text: 'Image', datafield: 'sImage', width: 70, cellsrenderer: cellsrendererImage },
                          { text: 'Admission', datafield: 'admissionNo', width: 115},
                          { text: 'Roll', datafield: 'rollNo', width: 65},
                          { text: 'Class', datafield: 'className', width: 100},
                          { text: 'Section', datafield: 'sectionName', width: 100},
                          { text: 'Student Name', datafield: 'sName', width: 150 },
                          { text: 'Fahter Name', datafield: 'fName', width: 150 },
                          { text: 'Mother Name', datafield: 'mName', width: 150 },
                          { text: 'Gender', datafield: 'gender', width: 65, cellsrenderer: cellsrendererGender }
                      ]
            });

        });

				
</script>
			
<%-- <%@ include file="../common/footer.jsp" %> --%>