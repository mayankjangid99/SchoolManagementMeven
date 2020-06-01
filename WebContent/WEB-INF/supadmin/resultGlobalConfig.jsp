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
			Global Config <small>Global Config Information</small>
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
				<h3 class="box-title">Global Config Details</h3>
				<div class="col-md-6 pull-right">
					<a href="addGlobalConfig" class="btn btn-primary pull-right">Add Global Config</a>
					<a href="searchGlobalConfig" class="btn btn-primary pull-right">Search Global Config</a>
				</div>
			</div>
			
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
   <script type="text/javascript" src="${pageContext.request.contextPath}/static/grid/scripts/demos.js"></script>
   
<script type="text/javascript">
        $(document).ready(function () {
			
            // prepare the data
            var source =
            {
                datatype: "json",
                datafields: [
					{ name: 'configCode'},
                    { name: 'configValue'},
                    { name: 'configDescription'}
                ],
                url: "fetchGlobalConfig?configCode=${param.configCode}"
            };

			
            
            var cellsrendererEdit = function (row, column, value) {
            	var configCode = $('#jqxgrid').jqxGrid('getrowdata', row).configCode;
            	//alert(name);
                return '<div onClick=navigateTo("Edit","'+configCode+'") class="btn btn-default btn-sm" style="margin: 10% 21%;"><span class="glyphicon glyphicon-pencil"></span></div>';
            };

            var cellsrendererView = function (row, column, value) {
            	var configCode = $('#jqxgrid').jqxGrid('getrowdata', row).configCode;
            	//alert(name);
                return '<div onClick=navigateTo("Edit","'+configCode+'") class="btn btn-default btn-sm" style="margin: 10% 20%;"><span class="glyphicon glyphicon-list"></span></div>';
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
                /* loadComplete:function (){
        			$('.delete-icon').click(function() {
        				var agree=confirm('<s:text name="common.record.inactive"/>');
        				 return agree;
        				});
        			  }, */
                columns: [
						  { text: 'Code', datafield: 'configCode', width: 170 },
                          { text: 'Value', datafield: 'configValue', width: 390},
                          { text: 'Description', datafield: 'configDescription', width: 365},
                          { text: 'Edit', datafield: 'Edit', width: 60, sortable: false, cellsrenderer: cellsrendererEdit},
                          { text: 'View', datafield: 'View', width: 60, sortable: false, cellsrenderer: cellsrendererView}
                      ]
            });

        });
var navigateTo = function(action, fieldVal)
{
	var url = "";
	var fieldMap = {"configCode" : fieldVal };
	if(action == "Edit")
	{
		url = "editGlobalConfig";
	}
	else if(action == "View")
	{
		url = "viewGlobalConfig";
	}
	sendRequest(url, "post", fieldMap);	
}

</script>
			
<%-- <%@ include file="../common/footer.jsp" %> --%>