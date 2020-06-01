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
			Result Fee Category <!-- <small>Allocated Sections Information</small> -->
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
				<h3 class="box-title">Fee Category Details</h3>
				<div class="col-md-6 pull-right">
					<a href="addFeeCategory" class="btn btn-primary pull-right">Add Fee Category</a> &nbsp;&nbsp;&nbsp;
					<a href="searchFeeCategory" class="btn btn-primary pull-right">Search Fee Category</a>
				</div>
			</div>
			
			<div id="jqxWidget" style="font-size: 13px; font-family: Verdana; padding: 2%;">
				<div id="jqxgrid"></div>
            	<i id="refresh" style="display: none;"></i>
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
   <script type="text/javascript" src="${pageContext.request.contextPath}/static/grid/scripts/demos.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/static/grid/jqwidgets/generatedata.js"></script>
   
<script type="text/javascript">
        $(document).ready(function () {
        	
        	var cellsrendererDeleteCategory = function (row, column, value) {
        		var classCode = $('#jqxgrid').jqxGrid('getrowdata', row).classCode;
        		var sectionCode = $('#jqxgrid').jqxGrid('getrowdata', row).sectionCode;
            	var feeCategoryCode = $('#jqxgrid').jqxGrid('getrowdata', row).feeCategoryCode;
            	var type = $('#jqxgrid').jqxGrid('getrowdata', row).type;
            	//alert(name);
                return '<div class="btn btn-default btn-sm" onClick = navigateTo("'+classCode+'","'+sectionCode+'","'+feeCategoryCode+'","'+type+'") style="margin: 9% 22%;"><span class="glyphicon glyphicon-trash"></span></div>';
            };
            
            var cellsrendererStatusCategory = function (row, column, value) {
            	var rowData = $('#jqxgrid').jqxGrid('getrowdata', row);
        		var classCode = rowData.classCode;
        		var sectionCode = rowData.sectionCode;
            	var feeCategoryCode = rowData.feeCategoryCode;
            	var type = rowData.type;
            	var status = rowData.status;
            	//alert(name);
            	if(value == 'Y')
			    {
            		return '<div class="btn btn-default btn-sm" onClick = activeDeactive(this,"'+classCode+'","'+sectionCode+'","'+feeCategoryCode+'","'+type+'") style="margin: 9% 22%; color: #3c8dbc;"><input type="hidden" class="hstatus" value='+value+' /><span class="glyphicon glyphicon-ok"></span></div>';
			    }
				else if(value == 'N')
			    {
					return '<div class="btn btn-default btn-sm" onClick = activeDeactive(this,"'+classCode+'","'+sectionCode+'","'+feeCategoryCode+'","'+type+'") style="margin: 9% 22%; color: #f26d6d;"><input type="hidden" class="hstatus" value='+value+' /><span class="glyphicon glyphicon-remove"></span></div>';
			    }
				else
			    {
					return '<div class="btn btn-default btn-sm" style="margin: 9% 22%;"><span class="activeDeactive glyphicon glyphicon-ban-circle"></span></div>';
			    }
                //return '<div class="btn btn-default btn-sm" onClick = navigateTo("'+classCode+'","'+sectionCode+'","'+feeCategoryCode+'","'+type+'") style="margin: 7% 33%;"><span class="glyphicon glyphicon-trash"></span></div>';
            };
            
            
            var cellsrendererType = function (row, datafield, value) {
                if(value=='Y')
                {
					return '<div style="margin-top: 10px;" class="jqx-grid-cell-left-align">Yearly</div>';
                }
                else if(value=='H')
                {
					return '<div style="margin-top: 10px;" class="jqx-grid-cell-left-align">Half-Yearly</div>';
                }
                else if(value=='M')
                {
					return '<div style="margin-top: 10px;" class="jqx-grid-cell-left-align">Monthly</div>';
                }
                else
                {
					return '<div style="margin-top: 17px;" class="jqx-grid-cell-left-align"></div>';
                }
            };
            
            var cellsrendererFeeType = function (row, datafield, value) {
                if(value=='A')
                {
					return '<div style="margin-top: 10px;" class="jqx-grid-cell-left-align">Additional</div>';
                }
                else if(value=='N')
                {
					return '<div style="margin-top: 10px;" class="jqx-grid-cell-left-align">Non-Additional</div>';
                }
            };
                        
            
            // prepare the data
            var source =
            {
                datatype: "json",
                datafields: [
					/* { name: 'schoolCode'},
					{ name: 'schoolName'}, */
					{ name: 'className'},
					{ name: 'classCode'},
					{ name: 'sectionName'},
					{ name: 'sectionCode'},
                    { name: 'feeCategoryName'},
                    { name: 'feeCategoryCode'},
                    { name: 'feeType'},
                    { name: 'type'},
                    { name: 'status'}
                ],
                url: "fetchFeeCategoryWithClass?${queryString}"
            };

            
            $("#jqxgrid").jqxGrid(
            {
                width: 1045,
                source: source,
                pageable: true,
                autoheight: true,
                pagesizeoptions: ['10', '20', '50', '100'],
                rowsheight: 35,
                sortable: true,
                theme: 'ui-redmond',
                columns: [
						  /* { text: 'School Code', datafield: 'schoolCode', width: 100 },
						  { text: 'School Name', datafield: 'schoolName', width: 300 }, */
						  { text: 'Class Name', datafield: 'className', width: 150 },
                          { text: 'Class Code', datafield: 'classCode', width: 100},
						  { text: 'Section Name', datafield: 'sectionName', width: 150 },
                          { text: 'Section Code', datafield: 'sectionCode', width: 100},
                          { text: 'Fee Category Name', datafield: 'feeCategoryName', width: 200},
//                           { text: 'Fee Category Code', datafield: 'feeCategoryCode', width: 100},
                          { text: 'Fee Type', datafield: 'feeType', width: 125, cellsrenderer: cellsrendererFeeType},
                          { text: 'Type', datafield: 'type', width: 100, cellsrenderer: cellsrendererType},
                          { text: 'Status', datafield: 'status', width: 60, cellsrenderer: cellsrendererStatusCategory},
                          { text: 'Delete', width: 60, cellsrenderer: cellsrendererDeleteCategory}
                      ]
            });
            
            

            var dataAdapter = new $.jqx.dataAdapter(source);
            // init buttons.
            $("#refresh").jqxButton({ theme: theme });

            $("#refresh").click(function () {
            	source.localdata = generatedata(500);
                // passing "cells" to the 'updatebounddata' method will refresh only the cells values when the new rows count is equal to the previous rows count.
                $("#jqxgrid").jqxGrid('updatebounddata', 'cells');
            });

        });
        
        var navigateTo = function (classCode, sectionCode, feeCategoryCode, type)
		{
			var url = url = "deleteAllocatedFeeCategory";
			var queryString = "classCode=" + classCode + "&sectionCode=" + sectionCode + "&feeCategoryCode=" + feeCategoryCode + "&type=" + type;
			var conf = confirm("Are you sure to delete this Record");
			if(conf)
			{
				//sendRequest(url, "post", inputMap);						
				$.ajax({
					url : url,
					data : queryString,
					type : "post",
					//dataType : "json",
					cache : false,
					async : false,
					success : function(data) {
						//called when successful
						if(data == "success")
							$("#refresh").trigger("click");
						else
							alert("Record is not deleted due to some error...!!!");
					},
					error : function(e) {
						alert("error");
					}
				});
			}
		}
        
        
        var activeDeactive = function(currentObj, classCode, sectionCode, feeCategoryCode, type) {
        	var conf = confirm('Are you sure to change status?');
        	if(conf){
        		var currentObj = $(currentObj);
        		//alert(currentObj);
        		var hstatus = currentObj.children('.hstatus');
        		var statusIcon = currentObj.children('.glyphicon');
        		var status = hstatus.val();
    			var queryString = "classCode=" + classCode + "&sectionCode=" + sectionCode + "&feeCategoryCode=" + feeCategoryCode + "&type=" + type + "&status=" + status;
        		//console.log(status);
        		$.ajax({
        				url : "changeAllocatedFeeCategoryStatus",
        				data : queryString,
        				//dataType : "json",
        				type: 'POST',
        				cache : false,
        				async : false,
        				success : function(data) {
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

</script>
			
<%-- <%@ include file="../common/footer.jsp" %> --%>