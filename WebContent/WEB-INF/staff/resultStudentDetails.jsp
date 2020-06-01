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
			Student List <small>Student Information</small>
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
				<div class="col-md-8 pull-right">
					<a href="addStudentDetails" class="btn btn-primary pull-right">Add Student Details</a>
					<a href="searchStudentDetails" class="btn btn-primary pull-right">Search Student Details</a>
					<a href="#" id="promoteStudentBtn" class="btn bg-orange pull-right">Promote Student Details</a>
				</div>
</c:if>				
			</div>
			
			<div id="jqxWidget" style="font-size: 13px; font-family: Verdana; padding: 2%;">
				<div id="jqxgrid">
					 <script type="text/javascript">
		                var selectionChanged = function (event) {
			                var selectedClass = "${param.classCode}";
			                var selectedSec = "${param.sectionCode}";
			                //console.log(selectedClass);
		                    /* var id = event.target.id;
		                    var rowIndex = parseInt(id.toString().substring(6)); */
		                    var value = event.target.value;
// 		                    $("#jqxgrid").jqxGrid('setcellvalue', rowIndex, 'Nav', value);

		                    //alert(formatDate());
		                    if(value != "")
			                {
			                    var currentDate = formatDate();
								var values = value.split("~");
								var url = "";
								var inputMap = {};
								var method = "";
								if(values[2] == "attendance") {
									url = "resultStudentAttendanceDetails";
									method = "post";
									inputMap = {"classCode":selectedClass,"sectionCode":selectedSec,"admissionNo":values[0],"date":currentDate };
								} else if(values[2] == "feeDetails") {
									url = "resultStudentFeeDetails";
									method = "post";
									inputMap = {"classCode":selectedClass,"sectionCode":selectedSec,"admissionNo":values[0],"rollNo":values[1] };									
								}
								sendRequest(url, method, inputMap);
								//window.location = url;
			                    //console.log(value);
			                }
		                }
		            </script>
					
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
                url: "fetchAllStudentDetails?classCode=${param.classCode}&sectionCode=${param.sectionCode}&status=${param.status}"
            };

			
            
            var cellsrendererEdit = function (row, column, value) {
            	var admissionNo = $('#jqxgrid').jqxGrid('getrowdata', row).admissionNo;
            	//alert(name);
                return '<div class="btn btn-default btn-sm" onClick = navigateTo("Edit","'+admissionNo+'") style="margin: 25% 18%;"><span class="glyphicon glyphicon-pencil"></span></div>';
            };

            var cellsrendererView = function (row, column, value) {
            	var admissionNo = $('#jqxgrid').jqxGrid('getrowdata', row).admissionNo;
            	//alert(name);
                return '<div class="btn btn-default btn-sm" onClick = navigateTo("View","'+admissionNo+'") style="margin: 21% 20%;"><span class="glyphicon glyphicon-list"></span></div>';
            };
            
            var cellsrendererStatus = function (row, column, value) {
            	var admissionNo = $('#jqxgrid').jqxGrid('getrowdata', row).admissionNo;
            	//alert(name);
            	if(value == 'Y')
                {
            		return '<div id="'+admissionNo+'" class="btn btn-default btn-sm" style="margin: 23% 20%; color: #3c8dbc;" onClick=activeDeactive("'+admissionNo+'") ><input type="hidden" class="hstatus" value='+value+' /><span class="glyphicon glyphicon-ok"></span></div>';
                }
            	else if(value == 'N')
                {
            		return '<div id="'+admissionNo+'" class="btn btn-default btn-sm" style="margin: 23% 20%; color: #f26d6d;" onClick=activeDeactive("'+admissionNo+'") ><input type="hidden" class="hstatus" value='+value+' /><span class="glyphicon glyphicon-remove"></span></div>';
                }
            	else
                {
            		return '<div id="'+admissionNo+'" class="btn btn-default btn-sm" style="margin: 23% 20%;"><span class="activeDeactive glyphicon glyphicon-ban-circle"></span></div>';
                }
                
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
            var productNames =
                [
                    "Black Tea", "Green Tea", "Caffe Espresso", "Doubleshot Espresso", "Caffe Latte", "White Chocolate Mocha", "Cramel Latte", "Caffe Americano", "Cappuccino", "Espresso Truffle", "Espresso con Panna", "Peppermint Mocha Twist"
                ];
            /* var renderlist = function (row, column, value) {
            	var admissionNo = $('#jqxgrid').jqxGrid('getrowdata', row).admissionNo;
                var buildList = '<div class="form-group"><select id="Select' + row + '" onChange="selectionChanged(event)" class="grid-form-control">';
                for (var i = 0; i < productNames.length; i++) {
                    if (value == productNames[i]) {
                        buildList += '<option selected="true" value="'+admissionNo+'~'+productNames[i]+'">' + productNames[i] + '</option>';
                    }
                    else {
                        buildList += '<option value="'+admissionNo+'~'+productNames[i]+'">' + productNames[i] + '</option>';
                    }
                }
                buildList += '</select></div>';
                return buildList;
            } */
            var renderlist = function (row, column, value) {
            	var admissionNo = $('#jqxgrid').jqxGrid('getrowdata', row).admissionNo;
            	var rollNo = $('#jqxgrid').jqxGrid('getrowdata', row).rollNo;
                var buildList = '<div class="form-group"><select id="Select' + row + '" onChange="selectionChanged(event)" class="grid-form-control">';
                buildList += '<option selected="true" value="">-- Select --</option>';
                buildList += '<option value="'+admissionNo+'~'+rollNo+'~attendance">Attendance</option>';
                buildList += '<option value="'+admissionNo+'~'+rollNo+'~feeDetails">Fee Details</option>';
                buildList += '</select></div>';
                return buildList;
            }
                
            
            $("#jqxgrid").jqxGrid(
            {
                width: 980,
                source: source,
                pageable: true,
                autoheight: true,
                pagesizeoptions: ['10', '20', '50', '100'],
                rowsheight: 50,
                sortable: true,
                editmode: "click",
                theme: 'ui-redmond',
                selectionmode: 'multiplerows',
                /* loadComplete:function (){
        			$('.delete-icon').click(function() {
        				var agree=confirm('<s:text name="common.record.inactive"/>');
        				 return agree;
        				});
        			  }, */
                columns: [
						  { text: 'Image', datafield: 'sImage', width: 70, cellsrenderer: cellsrendererImage },
                          { text: 'Admission', datafield: 'admissionNo', width: 85},
                          { text: 'Roll', datafield: 'rollNo', width: 45},
                          { text: 'Class', datafield: 'className', width: 70},
                          { text: 'Section', datafield: 'sectionName', width: 80},
                          { text: 'Student Name', datafield: 'sName', width: 150 },
                          { text: 'Fahter Name', datafield: 'fName', width: 150 },
                          { text: 'Mother Name', datafield: 'mName', width: 150 },
                          { text: 'Gender', datafield: 'gender', width: 55, cellsrenderer: cellsrendererGender },
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUPADMIN' or userProfile.getUserRoleId() eq 'ROLE_ADMIN' or userProfile.getUserRoleId() eq 'ROLE_SUBADMIN'}">				                          
                          { text: 'Status', datafield: 'status', width: 55, sortable: false, cellsrenderer: cellsrendererStatus},
                          { text: 'Edit', datafield: 'Edit', width: 50, sortable: false, cellsrenderer: cellsrendererEdit},
</c:if>                          
                          { text: 'View', datafield: 'View', width: 60, sortable: false, cellsrenderer: cellsrendererView},
                          { text: 'More Details', datafield: 'Nav', width: 160, sortable: false, cellsrenderer: renderlist}
                      ]
            });

        });


				var checkConfirm = function(adNo) {
					var agree = confirm('Are you sure to delete this record ?');
					//console.log(agree);
					return agree;
				};

				var activeDeactive = function(admissionNo) {
					var currentObj = $('div[id~='+admissionNo+']');
					//alert(currentObj);
					var hstatus = currentObj.children('.hstatus');
					var statusIcon = currentObj.children('.glyphicon');
					var status = hstatus.val();
					//console.log(status);
					$.ajax({
							url : "changeStudentStatus",
							data : "status=" + status +"&admissionNo="+admissionNo,
							//dataType : "json",
							cache : false,
							async : false,
							success : function(data) {
								//called when successful
								//console.log(data);
								if(data=='changed')
								{
									if (status == 'Y') {
										statusIcon.removeClass('glyphicon-ok');
										statusIcon.addClass('glyphicon-remove');
										currentObj.css("color", "#f26d6d");
										hstatus.val('N');
									}
									if (status == 'N') {
										statusIcon.removeClass('glyphicon-remove');
										statusIcon.addClass('glyphicon-ok');
										currentObj.css("color", "#3c8dbc");
										hstatus.val('Y');
									}
								}
								
							},
							error : function(e) {
								alert("error");
							}
						});
					};

					var navigateTo = function (actionType, admissionNo)
					{
						var url = "";
						if(actionType == "View")
							url = "viewStudentDetails";
						else if(actionType == "Edit")
							url = "editStudentDetails";
						var inputMap = {"admissionNo":admissionNo};
						sendRequest(url, "post", inputMap);
					}
					
					$("#promoteStudentBtn").on('click', function () {
		                var getselectedrowindexes = $('#jqxgrid').jqxGrid('getselectedrowindexes');
		                if (getselectedrowindexes.length > 0) {
		                	var admissionNos = [];
		                	$.each(getselectedrowindexes, function(i, v) {
			                    // returns the selected row's data.
			                    var selectedRowData = $('#jqxgrid').jqxGrid('getrowdata', v);
			                    admissionNos.push(selectedRowData.admissionNo);
		                	});
							var inputMap = {"admissionNos":admissionNos.join('~')};
		                    sendRequest("resultPromoteStudentDetails", "post", inputMap);
		                } else {
		        			$.processMsg.init({errors:[{element:$(this), msg:'Please select atleast one data.'}]}, document.forms[0]);
		                }
		             });
</script>
			
<%-- <%@ include file="../common/footer.jsp" %> --%>