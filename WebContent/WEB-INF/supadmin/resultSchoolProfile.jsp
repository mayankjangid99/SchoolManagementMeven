<%-- <%@ include file="../common/header.jsp" %> --%>
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
			Result School Profile <!-- <small>Student Information</small> -->
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
				<h3 class="box-title">School Details</h3>
				<div class="col-md-6 pull-right">
					<a href="addSchoolProfile" class="btn btn-primary pull-right">Add School Profile</a>
					<a href="searchSchoolProfile" class="btn btn-primary pull-right">Search School Profile</a>
				</div>
			</div>
			${pageContext.request.queryString}
			<div id="jqxWidget" style="font-size: 13px; font-family: Verdana; padding: 2%;">
				<div id="jqxgrid"></div>
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
					{ name: 'logo'},
					{ name: 'schoolCode'},
                    { name: 'name'},
                    { name: 'address'},
                    { name: 'state'},
                    { name: 'phone'},
                    { name: 'email'}
                    /* { name: 'active'} */
                ],
                url: "fetchSchoolProfile?${queryString}"
            };

			
            
            var cellsrendererEdit = function (row, column, value) {
            	var schoolCode = $('#jqxgrid').jqxGrid('getrowdata', row).schoolCode;
            	//alert(name);
                return '<div class="btn btn-default btn-sm" onClick = navigateTo("Edit","'+schoolCode+'") style="margin: 25% 18%;"><span class="glyphicon glyphicon-pencil"></span></div>';
            };

            var cellsrendererView = function (row, column, value) {
            	var schoolCode = $('#jqxgrid').jqxGrid('getrowdata', row).schoolCode;
            	//alert(userId);
                return '<div class="btn btn-default btn-sm" onClick = navigateTo("View","'+schoolCode+'") style="margin: 21% 20%;"><span class="glyphicon glyphicon-list"></span></div>';
            };
            
            var cellsrendererActive = function (row, column, value) {
            	var userId = $('#jqxgrid').jqxGrid('getrowdata', row).userId;
            	//alert(value);
            	if(value == 'Y')
                {
            		return '<div id="'+userId+'" class="btn btn-default btn-sm" style="margin: 23% 20%; color: #3c8dbc;" onClick=activeDeactive("'+userId+'") ><input type="hidden" class="hActive" value='+value+' /><span class="glyphicon glyphicon-ok"></span></div>';
                }
            	else if(value == 'N')
                {
            		return '<div id="'+userId+'" class="btn btn-default btn-sm" style="margin: 23% 20%; color: #f26d6d;" onClick=activeDeactive("'+userId+'") ><input type="hidden" class="hActive" value='+value+' /><span class="glyphicon glyphicon-remove"></span></div>';
                }
            	else
                {
            		return '<div id="'+userId+'" class="btn btn-default btn-sm" style="margin: 23% 20%;"><span class="activeDeactive glyphicon glyphicon-ban-circle"></span></div>';
                }
                
            };

            var cellsrendererImage = function (row, datafield, value) {
            	if(value != "")
                	return '<img style="margin-left: 15px; margin-top: 5px; border-radius: 50%;" height="40" width="40" src="${pageContext.request.contextPath}/upload/school_images/' + value + '"/>';
                else
                	return '<img style="margin-left: 15px; margin-top: 5px; border-radius: 50%;" height="40" width="40" src="${pageContext.request.contextPath}/static/img/profile.png"/>';
            };

            /* var cellsrendererGender = function (row, datafield, value) {
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
            }; */
           
            /* var renderlist = function (row, column, value) {
            	var admissionNo = $('#jqxgrid').jqxGrid('getrowdata', row).admissionNo;
                var buildList = '<div class="form-group"><select id="Select' + row + '" onChange="selectionChanged(event)" class="grid-form-control">';
                buildList += '<option selected="true" value="">-- Select --</option>';
                buildList += '<option value="'+admissionNo+'~attendance">Attendance</option>';
                buildList += '</select></div>';
                return buildList;
            } */
                
            
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
                columns: [
						  { text: 'Logo', datafield: 'logo', width: 70, cellsrenderer: cellsrendererImage },
						  { text: 'Code', datafield: 'schoolCode', width: 70 },
                          { text: 'Name', datafield: 'name', width: 200},
                          { text: 'Address', datafield: 'address', width: 200},
                          { text: 'State', datafield: 'state', width: 80},
                          { text: 'Phone', datafield: 'phone', width: 100},
                          { text: 'Email', datafield: 'email', width: 150 },
                          /* { text: 'Active', datafield: 'active', width: 55, srtable: false, cellsrenderer: cellsrendererActive}, */
                          { text: 'Edit', datafield: 'Edit', width: 50, sortable: false, cellsrenderer: cellsrendererEdit},
                          { text: 'View', datafield: 'View', width: 60, sortable: false, cellsrenderer: cellsrendererView}
                          /* ,
                          { text: 'More Details', datafield: 'Nav', width: 160, sortable: false, cellsrenderer: renderlist} */
                      ]
            });

        });


				var checkConfirm = function(adNo) {
					var agree = confirm('Are you sure to delete this record ?');
					//console.log(agree);
					return agree;
				};

				var activeDeactive = function(userId) {
					var currentObj = $('div[id~='+userId+']');
					//alert(currentObj);
					var hstatus = currentObj.children('.hActive');
					var statusIcon = currentObj.children('.glyphicon');
					var status = hstatus.val();
					//console.log(status);
					var conf = false;
					if(status == "Y")
						conf = confirm("Are you sure to Deactive this User?");
					else
						conf = confirm("Are you sure to Active this User?");
					if(conf)
					{
						$.ajax({
								url : "changeUserActive",
								data : "currentStatus=" + status +"&userId="+userId,
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
						}
					};

					

	                var selectionChanged = function (event) {
		                var selectedClass = "${param.classCode}";
		                var selectedSec = "${param.sectionCode}";
		                //console.log(selectedClass);
	                    /* var id = event.target.id;
	                    var rowIndex = parseInt(id.toString().substring(6)); */
	                    var value = event.target.value;
//		                    $("#jqxgrid").jqxGrid('setcellvalue', rowIndex, 'Nav', value);

	                    //alert(formatDate());
	                    if(value != "")
		                {
		                    var currentDate = formatDate();
							var values = value.split("~");
							var url = "";
							var inputMap = {};
							var method = "";
							if(values[1] == "attendance")
							{
								url = "resultStudentAttendanceDetails";
								method = "post";
								inputMap = {"classCode":selectedClass,"sectionCode":selectedSec,"admissionNo":values[0],"date":currentDate };
							}
							sendRequest(url, method, inputMap);
		                }
	                }
	                
	                
					var navigateTo = function (actionType, fieldVal)
					{
						var url = "";
						if(actionType == "View")
							url = "viewSchoolProfile";
						else if(actionType == "Edit")
							url = "editSchoolProfile";
						var inputMap = {"schoolCode":fieldVal};
						sendRequest(url, "post", inputMap);
					}
					
					
</script>
			
<%-- <%@ include file="../common/footer.jsp" %> --%>