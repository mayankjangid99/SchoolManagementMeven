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
			Result Payment Category <!-- <small>Global Config Information</small> -->
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
				<h3 class="box-title">Payment Category</h3>
				<div class="col-md-6 pull-right">
					<a href="addPaymentCategory" class="btn btn-primary pull-right">Add Payment Category</a>
					<a href="searchPaymentCategory" class="btn btn-primary pull-right">Search Payment Category</a>
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
					{ name: 'schoolPaymentCategoryId'},
					{ name: 'schoolCode'},
					{ name: 'schoolName'},
                    { name: 'sessionCode'},
					{ name: 'sessionName'},
                    { name: 'paymentCategoryCode'},
                    { name: 'paymentCategoryName'}
                ],
                url: "fetchAllocatedPaymentCategory?${QueryString}"
            };

			
            
            var cellsrendererEdit = function (row, column, value) {
            	var sessionCode = $('#jqxgrid').jqxGrid('getrowdata', row).sessionCode;
            	var schoolSessionId = $('#jqxgrid').jqxGrid('getrowdata', row).schoolSessionId;
            	//alert(name);
                return '<div class="btn btn-default btn-sm" onClick = navigateTo("Edit","'+sessionCode+'","'+schoolSessionId+'") style="margin: 7% 25%;"><span class="glyphicon glyphicon-pencil"></span></a>';
            };

            var cellsrendererDelete = function (row, column, value) {
            	var schoolPaymentCategoryId = $('#jqxgrid').jqxGrid('getrowdata', row).schoolPaymentCategoryId;
            	//alert(name);
                return '<div class="btn btn-default btn-sm" onClick = navigateTo("'+schoolPaymentCategoryId+'") style="margin: 7% 25%;"><span class="glyphicon glyphicon-trash"></span></div>';
            };
            
            var cellsrendererStatus = function (row, datafield, value) {
                if(value=='C')
                {
					return '<div style="margin-top: 10px;" class="jqx-grid-cell-left-align">Current</div>';
                }
                else if(value='P')
                {
					return '<div style="margin-top: 10px;" class="jqx-grid-cell-left-align">Previous</div>';
                }
                else
                {
					return '<div style="margin-top: 17px;" class="jqx-grid-cell-left-align"></div>';
                }
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
						  { text: 'School Session Id', datafield: 'schoolPaymentCategoryId', hidden: true },
						  { text: 'School Code', datafield: 'schoolCode', width: 100 },
						  { text: 'School Name', datafield: 'schoolName', width: 315 },
                          { text: 'Session Code', datafield: 'sessionCode', width: 110},
						  { text: 'Session Name', datafield: 'sessionName', width: 150 },
                          { text: 'Payment Code', datafield: 'paymentCategoryCode', width: 120},
                          { text: 'Payment Name', datafield: 'paymentCategoryName', width: 180},
                          { text: 'Delete', datafield: 'Delete', width: 70, sortable: false, cellsrenderer: cellsrendererDelete}
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

        
        var navigateTo = function (schoolPaymentCategoryId)
		{
			var url = "deleteAllocatedPaymentCategory";
			var queryString = "schoolPaymentCategoryId=" + schoolPaymentCategoryId;

			var conf = confirm("Are you sure to delete this field");
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
							$("#refresh").trigger("click");
						},
						error : function(e) {
							alert("error");
						}
					});
				}
		}
</script>
			
<%-- <%@ include file="../common/footer.jsp" %> --%>