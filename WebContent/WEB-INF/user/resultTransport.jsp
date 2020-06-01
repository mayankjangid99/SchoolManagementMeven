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
			Result Transport<!--  <small>Global Config Information</small> -->
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
				<h3 class="box-title">Transport Details</h3>
				<div class="col-md-6 pull-right">
					<a href="addTransport" class="btn btn-primary pull-right">Add Transport</a>
					<a href="searchTransport" class="btn btn-primary pull-right">Search Transport</a>
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
                	{ name: 'transportId'},
					{ name: 'route'},
                    { name: 'pickUpStop'},
                    { name: 'pickUpTime'},
                    { name: 'dropStop'},
                    { name: 'dropTime'},
                    { name: 'vehicle'},
                    { name: 'type'},
                    { name: 'transportFee'},
                    { name: 'status'}
                ],
                url: "fetchTransport"
            };

			
            
            var cellsrendererEdit = function (row, column, value) {
            	var transportId = $('#jqxgrid').jqxGrid('getrowdata', row).transportId;
            	//alert(name);
                return '<div data-app-proj-url = "editTransport?transportId='+transportId+'" data-app-proj-method="post" class="btn btn-default btn-sm" style="margin: 10% 21%;"><span class="glyphicon glyphicon-pencil"></span></div>';
            };

            var cellsrendererDelete = function (row, column, value) {
            	var transportId = $('#jqxgrid').jqxGrid('getrowdata', row).transportId;
            	//alert(name);
                return '<div onClick=deleteTransport("'+transportId+'") class="btn btn-default btn-sm" style="margin: 10% 20%;"><span class="glyphicon glyphicon-trash"></span></div>';
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
                		  { text: 'transportId', datafield: 'transportId', width: 150, hidden: true },
						  { text: 'Route', datafield: 'route', width: 150 },
                          { text: 'Pick Up Stop', datafield: 'pickUpStop', width: 150},
                          { text: 'Pick Up Time', datafield: 'pickUpTime', width: 100},
                          { text: 'Drop Stop', datafield: 'dropStop', width: 150},
                          { text: 'Drop Time', datafield: 'dropTime', width: 100},
                          { text: 'Vehicle', datafield: 'vehicle', width: 100},
                          { text: 'Type', datafield: 'type', width: 60, cellsrenderer: cellsrendererType},
                          { text: 'Fee', datafield: 'transportFee', width: 55},
                          { text: 'Status', datafield: 'status', width: 60, cellsrenderer: cellsrendererStatus},
                          { text: 'Edit', datafield: 'Edit', width: 60, sortable: false, cellsrenderer: cellsrendererEdit},
                          { text: 'Delete', datafield: 'Delete', width: 60, sortable: false, cellsrenderer: cellsrendererDelete}
                      ]
            });

        });
var navigateTo = function(action, fieldVal)
{
	var url = "";
	var fieldMap = {"transportId" : fieldVal };
	if(action == "Edit")
	{
		url = "editTransport";
	}
	else if(action == "View")
	{
		url = "viewTransport";
	}
	sendRequest(url, "post", fieldMap);	
}

var cellsrendererStatus = function (row, column, value) {
	var transportId = $('#jqxgrid').jqxGrid('getrowdata', row).transportId;
	//alert(name);
	if(value == 'Y')
    {
		return '<div id="'+transportId+'" class="btn btn-default btn-sm" style="margin: 9% 22%; color: #3c8dbc;" onClick=activeDeactive("'+transportId+'") ><input type="hidden" class="hstatus" value='+value+' /><span class="glyphicon glyphicon-ok"></span></div>';
    }
	else if(value == 'N')
    {
		return '<div id="'+transportId+'" class="btn btn-default btn-sm" style="margin: 9% 22%; color: #f26d6d;" onClick=activeDeactive("'+transportId+'") ><input type="hidden" class="hstatus" value='+value+' /><span class="glyphicon glyphicon-remove"></span></div>';
    }
	else
    {
		return '<div id="'+transportId+'" class="btn btn-default btn-sm" style="margin: 9% 22%;"><span class="activeDeactive glyphicon glyphicon-ban-circle"></span></div>';
    }
    
};

function deleteTransport(transportId){
	var conf = confirm("Are you sure to delete this record?");
	if(conf){
		$.sendRequest("deleteTransport", "post", {"transportId":transportId});
	}
}

var activeDeactive = function(transportId) {
	var conf = confirm('Are you sure to change status?');
	if(conf){
		var currentObj = $('div[id~='+transportId+']');
		//alert(currentObj);
		var hstatus = currentObj.children('.hstatus');
		var statusIcon = currentObj.children('.glyphicon');
		var status = hstatus.val();
		//console.log(status);
		$.ajax({
				url : "changeTransportStatus",
				data : "status=" + status +"&transportId="+transportId,
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