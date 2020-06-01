<%-- <%@ include file="../common/header.jsp" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			Report Card Config <small>Report Card Config Information</small>
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
				<h3 class="box-title">Report Card Config Details</h3>
				<div class="col-md-6 pull-right">
					<a href="addReportCardConfig" class="btn btn-primary pull-right">Add Report Card Config</a>
					<a href="searchReportCardConfig" class="btn btn-primary pull-right">Search Report Card Config</a>
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
			var configName = {};
<c:forEach var="config" items="${reportCardTermData }">
			configName["${config.key }"] = "${config.value }"
</c:forEach>
            // prepare the data
            var source =
            {
                datatype: "json",
                datafields: [
					{ name: 'classCode'},
					{ name: 'sectionCode'},
					{ name: 'configCode'},
                    { name: 'configValue'},
                    { name: 'configDescription'}
                ],
                url: "fetchAllReportCardConfig?configCode=${param.configCode}&classCode=${param.classCode}&sectionCode=${param.sectionCode}"
            };

            var cellsrendererName = function (row, column, value) {
				return '<div class="jqx-grid-cell-left-align" style="margin-top: 10px;">' + configName[value] + '</div>';
			};
            
            var cellsrendererEdit = function (row, column, value) {
            	var configCode = $('#jqxgrid').jqxGrid('getrowdata', row).configCode;
            	var classCode = $('#jqxgrid').jqxGrid('getrowdata', row).classCode;
            	var sectionCode = $('#jqxgrid').jqxGrid('getrowdata', row).sectionCode;
            	//alert(name);
                return '<div onClick=navigateTo("Edit","'+configCode+'","'+classCode+'","'+sectionCode+'") class="btn btn-default btn-sm" style="margin: 10% 21%;"><span class="glyphicon glyphicon-pencil"></span></div>';
            };

            var cellsrendererView = function (row, column, value) {
            	var configCode = $('#jqxgrid').jqxGrid('getrowdata', row).configCode;
            	var classCode = $('#jqxgrid').jqxGrid('getrowdata', row).classCode;
            	var sectionCode = $('#jqxgrid').jqxGrid('getrowdata', row).sectionCode;
            	//alert(name);
                return '<div onClick=navigateTo("Edit","'+configCode+'","'+classCode+'","'+sectionCode+'") class="btn btn-default btn-sm" style="margin: 10% 20%;"><span class="glyphicon glyphicon-list"></span></div>';
            };
            
            
            $("#jqxgrid").jqxGrid(
            {
                width: 975,
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
					  	  { text: 'Class Code', datafield: 'classCode', width: 100},
					  	  { text: 'Section Code', datafield: 'sectionCode', width: 100},
						  { text: 'Code', datafield: 'configCode', width: 170, cellsrenderer: cellsrendererName },
                          { text: 'Value', datafield: 'configValue', width: 280},
                          { text: 'Description', datafield: 'configDescription', width:265},
                          { text: 'Edit', datafield: 'Edit', width: 60, sortable: false, cellsrenderer: cellsrendererEdit},
                      ]
            });

        });
        
        
var navigateTo = function(action, fieldVal, classCode, sectionCode) {
	var url = "";
	var fieldMap = {"configCode" : fieldVal, "classCode" : classCode, "sectionCode" : sectionCode };
	if(action == "Edit") {
		url = "editReportCardConfig";
	} else if(action == "View") {
		url = "viewReportCardConfig";
	}
	sendRequest(url, "post", fieldMap);	
}

</script>
			
<%-- <%@ include file="../common/footer.jsp" %> --%>