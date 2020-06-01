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
			Result Class Subject <!-- <small>Allocated Sections Information</small> -->
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
				<h3 class="box-title">Class Subject</h3>
				<div class="col-md-6 pull-right">
					<a href="addClassSubject" class="btn btn-primary pull-right">Add Class Subject</a> &nbsp;&nbsp;&nbsp;
					<a href="searchClassSubject" class="btn btn-primary pull-right">Search Class Subject</a>
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
        		var schoolCode = $('#jqxgrid').jqxGrid('getrowdata', row).schoolCode;
        		var classCode = $('#jqxgrid').jqxGrid('getrowdata', row).classCode;
        		var sectionCode = $('#jqxgrid').jqxGrid('getrowdata', row).sectionCode;
            	var subjectCode = $('#jqxgrid').jqxGrid('getrowdata', row).subjectCode;
            	var type = $('#jqxgrid').jqxGrid('getrowdata', row).type;
            	//alert(name);
                return '<div class="btn btn-default btn-sm" onClick = navigateTo("'+schoolCode+'","'+classCode+'","'+sectionCode+'","'+subjectCode+'","'+type+'") style="margin: 7% 30%;"><span class="glyphicon glyphicon-trash"></span></div>';
            };
                
            var cellsrendererType = function (row, datafield, value) {
                if(value=='M')
                {
					return '<div style="margin-top: 10px;" class="jqx-grid-cell-left-align">Mandatory</div>';
                }
                else if(value='O')
                {
					return '<div style="margin-top: 10px;" class="jqx-grid-cell-left-align">Optional</div>';
                }
                else if(value='C')
                {
					return '<div style="margin-top: 10px;" class="jqx-grid-cell-left-align">Co-Scholastic</div>';
                }
                else
                {
					return '<div style="margin-top: 17px;" class="jqx-grid-cell-left-align"></div>';
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
                    { name: 'subjectName'},
                    { name: 'subjectCode'},
                    { name: 'type'}
                ],
                url: "fetchClassSubject?${queryString}"
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
                          { text: 'Class Code', datafield: 'classCode', width: 120},
						  { text: 'Section Name', datafield: 'sectionName', width: 150 },
                          { text: 'Section Code', datafield: 'sectionCode', width: 120},
                          { text: 'Subject Name', datafield: 'subjectName', width: 155},
                          { text: 'Subject Code', datafield: 'subjectCode', width: 120},
                          { text: 'Type', datafield: 'type', width: 150, cellsrenderer: cellsrendererType},
                          { text: 'Delete', width: 80, cellsrenderer: cellsrendererDeleteCategory}
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
        
        var navigateTo = function (schoolCode, classCode, sectionCode, subjectCode, type)
		{
			var url = url = "deleteAllocatedSubjectDetails";
			var queryString = "schoolCode=" + schoolCode + "&classCode=" + classCode + "&sectionCode=" + sectionCode + "&subjectCode=" + subjectCode + "&type=" + type;
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

</script>
			
<%-- <%@ include file="../common/footer.jsp" %> --%>