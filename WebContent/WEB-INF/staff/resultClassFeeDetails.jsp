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
			Result Class Fee Details <!-- <small>Student Information</small> -->
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
				<h3 class="box-title">Class Fee Details</h3>
				<div class="col-md-6 pull-right">
					<!-- <a href="addStudentDetails" class="btn btn-primary pull-right">Add Student Details</a> -->
					<span id="clsFeeReport" data-app-proj-method="post" data-app-proj-url="generateClassFeeDetailsReport?classCode=${ClassCode}&sectionCode=${SectionCode}&type=${param.type}&month=${param.month}&resultType=${param.resultType}" class="fa fa-file-pdf-o pull-right classFeeReportt linkCls" style="font-size:32px; color:red; display: none;"></span>
					<a href="searchStudentFeeDetails" class="btn btn-primary pull-right">Search Student Fee</a>
				</div>
			</div>
			
			<div id="jqxWidget" style="font-size: 13px; font-family: Verdana; padding: 2%;">
				<div id="jqxgrid">
					 <script type="text/javascript">
		                var selectionChanged = function (event) {
			                var selectedClass = "${ClassCode}";
			                var selectedSec = "${SectionCode}";
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
								if(values[1] == "attendance")
								{
									url = "resultStudentAttendanceDetails";
									method = "post";
									inputMap = {"classCode":selectedClass,"sectionCode":selectedSec,"admissionNo":values[0],"date":currentDate };
								} 
								else if(values[1] == "feeDetails")
								{
									url = "addStudentFeeDetails";
									method = "post";
									inputMap = {"classCode":selectedClass,"sectionCode":selectedSec,"admissionNo":values[0] };									
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
        	var dataFlag = false;
        	$.ajax({
                url: "fetchClassFeeDetails?${QueryString}",
                dataType: 'json',
                success: function (data) {
                	dataFlag = true;
                	//console.log(JSON.stringify(data)+"?????????dataRess");
                	if(data.length > 0){
                		$('#clsFeeReport').show();
                	}
                	
                	
                	
                	
                	
                	if(dataFlag){
                        var theme = 'ui-redmond';
                        var dataa = [
                        {
                            "ruleid": "1001",
                            "size": "51",
                            "ZScore": "1.21839",
                            "complexity": "8",
                            "constraints": {
                                "constraint": [
                                {
                                    "varName": "BirthWeight",
                                    "Min": "2.500000",
                                    "Max": "2.500000"
                                },
                                {
                                    "varName": "AgeMother",
                                    "Min": "39.000000",
                                    "Max": "39.000000"
                                },
                                {
                                    "varName": "AgeFather",
                                    "Min": "35.000000",
                                    "Max": "39.000000"
                                }
                                ]
                            }
                        },
                        {
                            "ruleid": "1002",
                            "size": "92",
                            "ZScore": "2.60137",
                            "complexity": "2",
                            "constraints": {
                                "constraint": [
                                {
                                    "varName": "AgeFather",
                                    "Min": "32.000000",
                                    "Max": "45.000000"
                                },
                                {
                                    "varName": "Age AD Start (weeks)",
                                    "Min": "14.000000",
                                    "Max": "30.000000"
                                }
                                ]
                            }
                        },
                        {
                            "ruleid": "1003",
                            "size": "62",
                            "ZScore": "2.60137",
                            "complexity": "2",
                            "constraints": {
                                "constraint": [
                                {
                                    "varName": "AgeFather",
                                    "Min": "32.000000",
                                    "Max": "45.000000"
                                },
                                {
                                    "varName": "Age AD Start (weeks)",
                                    "Min": "14.000000",
                                    "Max": "30.000000"
                                }
                                ]
                            }
                        }
                        ];
                        var source =
                        {
                            datafields: [
                                { name: 'sImage', type: 'string' },
                                { name: 'admissionNo', type: 'string' },
                                { name: 'rollNo', type: 'string' },
                                { name: 'className', type: 'string' },
                                { name: 'sectionName', type: 'string' },
                                { name: 'sFirstName', type: 'string' },
                                { name: 'sLastName', type: 'string' },
                                { name: 'fName', type: 'string' },
                                { name: 'mName', type: 'string' },
                                { name: 'gender', type: 'string' }
                            ],
                            datatype: 'json',
                            localdata: data
                        };
                        var adapter = new $.jqx.dataAdapter(source);
                        // create nested grid.
                        var initrowdetails = function (index, parentElement, gridElement, record) {
                            var id = record.uid.toString();
                            var grid = $($(parentElement).children()[0]);
                            var nestedSource =
                              {
                                  datafields: [
									  { name: 'monthName', map: 'monthName', type: 'string' },
									  { name: 'receiptNo', map: 'receiptNo', type: 'string' },
                                      { name: 'feeCategoryName', map: 'feeCategoryName', type: 'string' },
                                      { name: 'totalAmount', map: 'totalAmount', type: 'string' },
                                      { name: 'amountPaid', map: 'amountPaid', type: 'string' },
                                      { name: 'amountDue', map: 'amountDue', type: 'string' }
                                  ],
                                  datatype: 'json',
                                  root: 'jsonListContainer',
                                  localdata: data[index].dataContainer
                              };
                            var nestedAdapter = new $.jqx.dataAdapter(nestedSource);
                            if (grid != null) {
                                grid.jqxGrid({
                                    source: nestedAdapter, 
                                    theme: theme, 
                                    width: 985, 
                                    height: 150, 
                                    rowsheight: 30,
                                    sortable: true,
                                    columns: [
										{ text: "Type", datafield: "monthName", width: 120 },
										{ text: "Receipt No", datafield: "receiptNo", cellsrenderer: cellsrendererFeeTranHistory, width: 180},
                                        { text: "Fee Category Name", datafield: "feeCategoryName" },
                                        { text: "Total Amount", datafield: "totalAmount" },
                                        { text: "Amount Paid", datafield: "amountPaid" },
                                        { text: "Amount Due", datafield: "amountDue", cellsrenderer: cellsrendererAmountDue }
                                   ]
                                });
                            }
                         // nested grid rowclick binding
                            grid.on("rowclick", function (event) {
	                            //alert($('[id^="nestedGrid"]').length);
	                            $('[id^="nestedGrid"]').each(function(){
	                            	$(this).parent().css('z-index', '999');
	                            });
                            	var args = event.args;
                                var rowindex = args.rowindex;
                            });
                        }

                        var cellsrendererAmountDue = function (row, datafield, value) {
                            if(value !== "0.00")
                            {
            					return '<div style="margin-top: 7px; color:red" class="jqx-grid-cell-left-align">'+value+'</div>';
                            }
                            else
                                {
            						return '<div style="margin-top: 7px; color:green" class="jqx-grid-cell-left-align">'+value+'</div>';
                                }
                        };
                        
                        var cellsrendererFeeTranHistory = function (row, datafield, value) {
                        		return '<div onClick = getFeeTranHistory("'+value+'") class="linkCls" style="margin-left: 4px; margin-top: 7px; float: left;">'+value+'</div>';
                        }

                        var cellsrendererImage = function (row, datafield, value) {
                            //return '<img style="margin-left: 15px; margin-top: 5px; border-radius: 50%;" height="40" width="40" src="${pageContext.request.contextPath}/upload/student_images/' + value + '"/>';
                            if(value != "")
                            	return '<img style="margin-left: 15px; margin-top: 5px; border-radius: 50%;" height="35" width="35" src="${pageContext.request.contextPath}/upload/student_images/' + value + '"/>';
                            else
                            	return '<img style="margin-left: 15px; margin-top: 5px; border-radius: 50%;" height="35" width="35" src="${pageContext.request.contextPath}/static/img/profile.png"/>';
                        };

                        var cellsrendererGender = function (row, datafield, value) {
                            if(value=='M')
                            {
            					return '<div style="margin-top: 12px;" class="jqx-grid-cell-left-align">Male</div>';
                            }
                            else if(value='F')
                            {
            					return '<div style="margin-top: 12px;" class="jqx-grid-cell-left-align">Female</div>';
                            }
                            else
                                {
            						return '<div style="margin-top: 12px;" class="jqx-grid-cell-left-align"></div>';
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
                            var buildList = '<div class="form-group"><select id="Select' + row + '" onChange="selectionChanged(event)" class="grid-form-control">';
                            buildList += '<option selected="true" value="">-- Select --</option>';
                            buildList += '<option value="'+admissionNo+'~attendance">Attendance</option>';
                            buildList += '<option value="'+admissionNo+'~feeDetails">Fee Details</option>';
                            buildList += '</select></div>';
                            return buildList;
                        }
                        
                        var cellsrendererReceipt = function (row, column, value) {
                        	var admissionNo = $('#jqxgrid').jqxGrid('getrowdata', row).admissionNo;
                        	var rollNo = $('#jqxgrid').jqxGrid('getrowdata', row).rollNo;
                        	return '<div data-app-proj-method="post" data-app-proj-url = "generateFeeReceipt?admissionNo='+admissionNo+'&rollNo='+rollNo+'&classCode=${ClassCode}&sectionCode=${SectionCode}&type=${param.type}&month=${param.month}" style="margin: 13% 35%;" class="linkCls"><span class="fa fa-file-pdf-o" style="font-size:22px;color:red;"></span></div>';
                        }
                        
                        
                        $("#jqxgrid").jqxGrid(
                        {
                            width: 1060,
                            source: source,
                            pageable: true,
                            autoheight: true,
                            pagesizeoptions: ['10', '20', '50', '100'],
            				rowdetails: true,
                            rowsheight: 40,
                            sortable: true,
                            editmode: "click",
                            theme: 'ui-redmond',
                            initrowdetails: initrowdetails,
                            rowdetailstemplate: { rowdetails: "<div id='nestedGrid' style='margin: 10px;'></div>", rowdetailsheight: 180, rowdetailshidden: true },
                            ready: function () {
                                $("#jqxgrid").jqxGrid('showrowdetails', 0);
                            },
                            columns: [
                                  { text: 'Image', datafield: 'sImage', width: 65, cellsrenderer: cellsrendererImage },
                                  { text: 'Admission No', datafield: 'admissionNo', width: 120 },
                                  { text: 'Roll No', datafield: 'rollNo', width: 75 },
                                  { text: 'Class Name', datafield: 'className', width: 120 },
                                  { text: 'Section Name', datafield: 'sectionName', width: 120 },
                                  { text: 'First Name', datafield: 'sFirstName' },
                                  { text: 'Last Name', datafield: 'sLastName' },
                                  { text: 'Father Name', datafield: 'fName' },
                                  { text: 'Mother Name', datafield: 'mName' },
                                  { text: 'gender', datafield: 'gender', cellsrenderer: cellsrendererGender },
                                  { text: 'Receipt', cellsrenderer: cellsrendererReceipt }
                            ]
                        });
                  }
                	     	
                	
                	
                	
                	
                },
                error: function () {
                    alert("Error with AJAX callback");
                }
            });
        	//console.log(JSON.stringify(dataRes)+"?????????dataRes");
            // prepare the data
            //var theme = getDemoTheme();
      
        });


		var checkConfirm = function(adNo) {
			var agree = confirm('Are you sure to delete this record ?');
			//console.log(agree);
			return agree;
		};

		var navigateTo = function (actionType, admissionNo){
			var url = "";
			if(actionType == "View")
				url = "viewStudentDetails";
			else if(actionType == "Edit")
				url = "editStudentDetails";
			var inputMap = {"admissionNo":admissionNo};
				sendRequest(url, "post", inputMap);
		}
					
		var getFeeTranHistory = function(receiptNo){
			$(document.forms[0]).appProcessRequest({url:'getStudentFeeTransactionHistoryModal?receiptNo='+receiptNo+'&classCode=${ClassCode}&sectionCode=${SectionCode}', role:'modal-link'});
		}
		
		var generateReceipt = function(admissionNo, rollNo){
			var inputMap = {"admissionNo":admissionNo, "rollNo":rollNo, "classCode":"${ClassCode}", "sectionCode":"${SectionCode}"};
			$.ajax({
		        url: "generateFeeReceipt",
		        data: "admissionNo="+admissionNo+"&rollNo="+rollNo+"&classCode=${ClassCode}&sectionCode=${SectionCode}&type=${param.type}&month=${param.month}",
		        //dataType: 'json',
				type: 'POST',
		        success: function (data) {
		        	console.log(data);
		        	//var anch = $('.generate-title').closest('.box-header').find('.triggerAnch');
		        	//console.log("anch= " +$('.triggerAnch').attr('href'));
		        	//$('.triggerAnch').attr('href', '${pageContext.request.contextPath}' + data);
		        	//$('.triggerAnch').attr("target", "_blank");
		        	//$('.triggerAnch').trigger('click');
		        	window.open('${pageContext.request.contextPath}/' + data, "_blank");
		        },
			    error: function () {
			        alert("Error with AJAX callback");
			    }
			});
			//sendRequest("generateFeeReceipt", "get", inputMap);
		}


$('.classFeeReport').on('click', function(){
	var feeCategoryType = $(this).closest('.box-primary').find('.type').val();
	$.ajax({
        url: "generateClassFeeDetailsReport",
        data: "classCode=${ClassCode}&sectionCode=${SectionCode}&type=${param.type}&month=${param.month}&resultType=${param.resultType}",
        //dataType: 'json',
		type: 'POST',
        success: function (data) {
        	console.log(data);
        	//var anch = $('.generate-title').closest('.box-header').find('.triggerAnch');
        	//console.log("anch= " +$('.triggerAnch').attr('href'));
        	//$('.triggerAnch').attr('href', '${pageContext.request.contextPath}' + data);
        	//$('.triggerAnch').attr("target", "_blank");
        	//$('.triggerAnch').trigger('click');
        	window.open('${pageContext.request.contextPath}/' + data, "_blank");
        },
	    error: function () {
	        alert("Error with AJAX callback");
	    }
	});
});
</script>
			
<%-- <%@ include file="../common/footer.jsp" %> --%>