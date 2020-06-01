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
			Backup Database List <small>Database Information</small>
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
				<h3 class="box-title">Backup Database</h3>
				<div class="col-md-6 pull-right">
					<a href="searchDatabase" class="btn btn-primary pull-right">Search Database</a>
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
			
            // prepare the data
            var source =
            {
                datatype: "json",
                datafields: [
					{ name: 'backupName'},
					{ name: 'backupLastModifiedTime'},
                    { name: 'backupSize'},
                    { name: 'backupPath'}
                ],
                url: "fetchDatabaseBackups"
            };


            var cellsrendererDownload = function (row, column, value) {
            	//var configCode = $('#jqxgrid').jqxGrid('getrowdata', row).configCode;
            	//alert(name);
                return '<div onClick=navigateTo("Download","'+value+'") class="sendPost btn btn-default btn-sm" style="margin: 6% 32%;"><span class="glyphicon glyphicon-download-alt"></span></div>';
            };
            
            var cellsrendererDelete = function (row, column, value) {
            	var backupPath = $('#jqxgrid').jqxGrid('getrowdata', row).backupPath;
            	//alert(name);
                return '<div onClick=navigateTo("Delete","'+backupPath+'") class="sendPost btn btn-default btn-sm" style="margin: 6% 32%;"><span class="glyphicon glyphicon-trash"></span></div>';
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
						  { text: 'Backup Name', datafield: 'backupName', width: 400 },
                          { text: 'Backup Last Modified Time', datafield: 'backupLastModifiedTime', width: 355},
                          { text: 'Backup Size', datafield: 'backupSize', width: 100},
                          { text: 'Download', datafield: 'backupPath', width: 95, sortable: false, cellsrenderer: cellsrendererDownload},
                          { text: 'Delete', datafield: 'Delete', width: 95, sortable: false, cellsrenderer: cellsrendererDelete},
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
function navigateTo(action, fieldVal)
{
	if(action == "Download")
	{
		sendRequest("downloadDatabaseBackup", "post", {"file" : fieldVal});
	}
	else if(action == "Delete")
	{
		var conf = confirm("Are you sure to delete database ?");
		if(conf)
		{
			//sendRequest("deleteDatabaseBackup", "post", {"file" : fieldVal});
			$.ajax({
				url : "deleteDatabaseBackup",
				data : "file=" + fieldVal,
				type : "post",
				//dataType : "json",
				cache : false,
				async : false,
				success : function(data) {
					//called when successful
					$("#refresh").trigger("click");
				},
				error : function(e) {
					alert("error");
				}
			});
		}
	}
}
//$("a").sendPostReq({event:"click",filter:".sendPost"});
</script>
			
<%-- <%@ include file="../common/footer.jsp" %> --%>