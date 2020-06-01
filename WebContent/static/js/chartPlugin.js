/*########################################################	Bar Chart	###########################################################*/
	$.fn.popBarChart = function( options )
 	{
 		// This is the easiest way to have default options.
         var settings = $.extend({
             // These are the defaults.
             event: "click",
             chartLabels: [],
             chartDatasets: []
         }, options );

         var parent = $(this).parent();
		 $(this).text("");
         var chartCanvas = parent.find('.barChart');
		 var chartCanvasParent= chartCanvas.parent();
		 chartCanvas.remove();
		 chartCanvasParent.html('<canvas class="'+chartCanvas.attr('class')+'" width="'+chartCanvas.attr("width")+'" height="'+chartCanvas.attr("height")+'"></canvas>');
    	 var preValue = 0;
    	 $.each(settings.chartDatasets, function(i, item){
    		 $.each(item.data, function(i, item2){
        		 if(preValue < item2)
        			 preValue = item2;
        	 });
    	 });
         if(preValue <= 0)
         {
        	//$("#"+pieChartDivID).children().remove();
    		 //chartCanvas = parent.find('.barChart');
        	 var chartLegendTag = parent.find(".chart-legend");
        	 chartLegendTag.find("li").remove();
    		 chartCanvas.remove();
    		 $(this).text("No Data");
    		 chartCanvasParent.html('<canvas class="'+chartCanvas.attr('class')+'" width="'+chartCanvas.attr("width")+'" height="'+chartCanvas.attr("height")+'"></canvas>');
    		 //console.log($("#"+pieChartDivID).parent().find('pieChart').text());
         }
         else
         {
             //-------------
             //- BAR CHART -
             //-------------
             var barChartCanvas = $(".barChart").get(0).getContext("2d");
             var barChart = new Chart(barChartCanvas, {stacked: true});
             
	         var areaChartData = {
	                 labels: settings.chartLabels,
	                 /*datasets: [
	                   {
	                     label: "Absent",
	                     fillColor: "#f56954",
	                     strokeColor: "#f56954",
	                     pointColor: "#f56954",
	                     pointStrokeColor: "#c1c7d1",
	                     pointHighlightFill: "#fff",
	                     pointHighlightStroke: "rgba(220,220,220,1)",
	                     data: redColorData
	                   },
	                   {
	                     label: "Present",
	                     fillColor: "rgba(60,141,188,0.9)",
	                     strokeColor: "rgba(60,141,188,0.8)",
	                     pointColor: "#3b8bba",
	                     pointStrokeColor: "rgba(60,141,188,1)",
	                     pointHighlightFill: "#fff",
	                     pointHighlightStroke: "rgba(60,141,188,1)",
	                     data: greenColorData
	                   }
	                 ]*/
	                 datasets: settings.chartDatasets
	               };
	         
	         var barChartData = areaChartData;
	         barChartData.datasets[1].fillColor = "#00a65a";
	         barChartData.datasets[1].strokeColor = "#00a65a";
	         barChartData.datasets[1].pointColor = "#00a65a";
	         
	         var barChartOptions = {
	           //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
	           scaleBeginAtZero: true,
	           //Boolean - Whether grid lines are shown across the chart
	           scaleShowGridLines: true,
	           //String - Colour of the grid lines
	           scaleGridLineColor: "rgba(0,0,0,.05)",
	           //Number - Width of the grid lines
	           scaleGridLineWidth: 1,
	           //Boolean - Whether to show horizontal lines (except X axis)
	           scaleShowHorizontalLines: true,
	           //Boolean - Whether to show vertical lines (except Y axis)
	           scaleShowVerticalLines: true,
	           //Boolean - If there is a stroke on each bar
	           barShowStroke: true,
	           //Number - Pixel width of the bar stroke
	           barStrokeWidth: 2,
	           //Number - Spacing between each of the X value sets
	           barValueSpacing: 5,
	           //Number - Spacing between data sets within X values
	           barDatasetSpacing: 1,
	           //String - A legend template
	           //legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>", 
	           //Boolean - whether to make the chart responsive
	           responsive: true,
	           maintainAspectRatio: true
	         };
	
	         barChartOptions.datasetFill = false;
	         barChart.Bar(barChartData, barChartOptions);
	         //legend(document.getElementById("lineLegendBar"), areaChartData);
	         chartLegend(parent, areaChartData); 
	     } 
 	
 	}





     var pupulateBarChart = function(label, greenColorData, redColorData)
     {
    	  //-------------
         //- BAR CHART -
         //-------------
         var barChartCanvas = $("#barChart").get(0).getContext("2d");
         var barChart = new Chart(barChartCanvas, {stacked: true});
         if(greenColorData == "" && redColorData == "")
         {
        	//$("#"+pieChartDivID).children().remove();
    		 var parent = $("#lineLegendBar").parent();
        	 parent.find('#barChart').remove();
        	 parent.html('<div id="lineLegendBar">No Data</div><canvas width="480" style="width: 480px; height: 225px;" id="pieChart" height="225"></canvas>');
    		 //console.log($("#"+pieChartDivID).parent().find('pieChart').text());
         }
         else
         {
	         var areaChartData = {
	                 labels: label,
	                 datasets: [
	                   {
	                     label: "Absent",
	                     fillColor: "#f56954",
	                     strokeColor: "#f56954",
	                     pointColor: "#f56954",
	                     pointStrokeColor: "#c1c7d1",
	                     pointHighlightFill: "#fff",
	                     pointHighlightStroke: "rgba(220,220,220,1)",
	                     data: redColorData
	                   },
	                   {
	                     label: "Present",
	                     fillColor: "rgba(60,141,188,0.9)",
	                     strokeColor: "rgba(60,141,188,0.8)",
	                     pointColor: "#3b8bba",
	                     pointStrokeColor: "rgba(60,141,188,1)",
	                     pointHighlightFill: "#fff",
	                     pointHighlightStroke: "rgba(60,141,188,1)",
	                     data: greenColorData
	                   }
	                 ]
	               };
	         
	         var barChartData = areaChartData;
	         barChartData.datasets[1].fillColor = "#00a65a";
	         barChartData.datasets[1].strokeColor = "#00a65a";
	         barChartData.datasets[1].pointColor = "#00a65a";
	         
	         var barChartOptions = {
	           //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
	           scaleBeginAtZero: true,
	           //Boolean - Whether grid lines are shown across the chart
	           scaleShowGridLines: true,
	           //String - Colour of the grid lines
	           scaleGridLineColor: "rgba(0,0,0,.05)",
	           //Number - Width of the grid lines
	           scaleGridLineWidth: 1,
	           //Boolean - Whether to show horizontal lines (except X axis)
	           scaleShowHorizontalLines: true,
	           //Boolean - Whether to show vertical lines (except Y axis)
	           scaleShowVerticalLines: true,
	           //Boolean - If there is a stroke on each bar
	           barShowStroke: true,
	           //Number - Pixel width of the bar stroke
	           barStrokeWidth: 2,
	           //Number - Spacing between each of the X value sets
	           barValueSpacing: 5,
	           //Number - Spacing between data sets within X values
	           barDatasetSpacing: 1,
	           //String - A legend template
	           legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>", 
	           //Boolean - whether to make the chart responsive
	           responsive: true,
	           maintainAspectRatio: true
	         };
	
	         barChartOptions.datasetFill = false;
	         barChart.Bar(barChartData, barChartOptions);
	         legend(document.getElementById("lineLegendBar"), areaChartData);
	     }
     }
     
     
/*#########################################################	Pie Chart	########################################################*/
     
    $.fn.popPieChart = function( options )
 	{
 		// This is the easiest way to have default options.
         var settings = $.extend({
             // These are the defaults.
             event: "click",
             chartPieData: []
         }, options );
         
         var parent = $(this).parent();
		 $(this).text("");
         var chartCanvas = parent.find('.pieChart');
		 var chartCanvasParent= chartCanvas.parent();
		 chartCanvas.remove();
		 chartCanvasParent.html('<canvas class="'+chartCanvas.attr('class')+'" width="'+chartCanvas.attr("width")+'" height="'+chartCanvas.attr("height")+'"></canvas>');
    	 var preValue = 0;
    	 $.each(settings.chartPieData, function(i, item){
    		 if(preValue < item.value)
    			 preValue = item.value;
    	 });
         if(preValue <= 0)
         {
    		 //$("#"+pieChartDivID).children().remove();
    		 //var parent = $(this).parent();
    		 //chartCanvas = parent.find('.pieChart');
        	 var chartLegendTag = parent.find(".chart-legend");
        	 chartLegendTag.find("li").remove();
    		 chartCanvas.remove();
    		 $(this).text("No Data");
    		 chartCanvasParent.html('<canvas class="'+chartCanvas.attr('class')+'" width="'+chartCanvas.attr("width")+'" height="'+chartCanvas.attr("height")+'"></canvas>');
    		 //console.log($("#"+pieChartDivID).parent().find('pieChart').text());        	 
         }
         else
         {
        	//-------------
	         //- PIE CHART -
	         //-------------
	         // Get context with jQuery - using jQuery's .get() method.
	         var pieChartCanvas = $(".pieChart").get(0).getContext("2d");
	         var pieChart = new Chart(pieChartCanvas);
	         /*var PieData = [
	           {
	             value: redColorData,
	             color: "#f56954",
	             highlight: "#f56954",
	             label: "Absent"
	           },
	           {
	             value: greenColorData,
	             color: "#00a65a",
	             highlight: "#00a65a",
	             label: "Present"
	           }
	         ];*/
	         var PieData = settings.chartPieData;
	         var pieOptions = {
	           //Boolean - Whether we should show a stroke on each segment
	           segmentShowStroke: true,
	           //String - The colour of each segment stroke
	           segmentStrokeColor: "#fff",
	           //Number - The width of each segment stroke
	           segmentStrokeWidth: 2,
	           //Number - The percentage of the chart that we cut out of the middle
	           percentageInnerCutout: 2, // This is 0 for Pie charts
	           //Number - Amount of animation steps
	           animationSteps: 300,
	           //String - Animation easing effect
	           animationEasing: "easeOutBounce",
	           //Boolean - Whether we animate the rotation of the Doughnut
	           animateRotate: true,
	           //Boolean - Whether we animate scaling the Doughnut from the centre
	           animateScale: true,
	           //Boolean - whether to make the chart responsive to window resizing
	           responsive: true,
	           // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
	           maintainAspectRatio: false,
	           
	//            String - A legend template
	           legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>"
	         };
	         //Create pie or douhnut chart
	         // You can switch between pie and douhnut using the method below.
	         pieChart.Doughnut(PieData, pieOptions);
	         chartLegend(parent, PieData); 
         }
 	}
     
     
     
     
     
     var pupulatePieChart = function(pieChartDivID, greenColorData, redColorData)
	 {
    	 //$("#"+pieChartDivID).children().remove();
    	 var parent = $("#"+pieChartDivID).parent();
    	 parent.find('.pieChart').remove();
    	 parent.html('<div id="lineLegendPie"></div><canvas class="pieChart" width="530px" height="240px"></canvas>');
    	 if(greenColorData == "" && redColorData == "")
    	 {
    		 //$("#"+pieChartDivID).children().remove();
    		 var parent = $("#"+pieChartDivID).parent();
        	 parent.find('.pieChart').remove();
        	 parent.html('<div id="lineLegendPie">No Data</div><canvas class="pieChart" width="530px" height="240px"></canvas>');
    		 //console.log($("#"+pieChartDivID).parent().find('pieChart').text());
    	 }
    	 else
    	 {
	    	//-------------
	         //- PIE CHART -
	         //-------------
	         // Get context with jQuery - using jQuery's .get() method.
	         var pieChartCanvas = $(".pieChart").get(0).getContext("2d");
	         var pieChart = new Chart(pieChartCanvas);
	         var PieData = [
	           {
	             value: redColorData,
	             color: "#f56954",
	             highlight: "#f56954",
	             label: "Absent"
	           },
	           {
	             value: greenColorData,
	             color: "#00a65a",
	             highlight: "#00a65a",
	             label: "Present"
	           }
	         ];
	         var pieOptions = {
	           //Boolean - Whether we should show a stroke on each segment
	           segmentShowStroke: true,
	           //String - The colour of each segment stroke
	           segmentStrokeColor: "#fff",
	           //Number - The width of each segment stroke
	           segmentStrokeWidth: 2,
	           //Number - The percentage of the chart that we cut out of the middle
	           percentageInnerCutout: 0, // This is 0 for Pie charts
	           //Number - Amount of animation steps
	           animationSteps: 300,
	           //String - Animation easing effect
	           animationEasing: "easeOutBounce",
	           //Boolean - Whether we animate the rotation of the Doughnut
	           animateRotate: true,
	           //Boolean - Whether we animate scaling the Doughnut from the centre
	           animateScale: true,
	           //Boolean - whether to make the chart responsive to window resizing
	           responsive: true,
	           // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
	           maintainAspectRatio: false,
	           
	//            String - A legend template
	           legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>"
	         };
	         //Create pie or douhnut chart
	         // You can switch between pie and douhnut using the method below.
	         pieChart.Doughnut(PieData, pieOptions);
	         legend(document.getElementById(pieChartDivID), PieData);      
    	 }
	 }
     
     
     
     
     
     
     
     
     $.fn.popPlotChart = function( options )
 	 {
 		// This is the easiest way to have default options.
         var settings = $.extend({
             // These are the defaults.
        	 event: "click",
             donutData: []
         }, options );
         
         /*var donutData  = [];
         $.each(settings.chartData, function(i, item){
        	 donutData [i] = {label: settings.chartLabels [i], data: settings.chartData [i], color: settings.chartColor [i]};
         });*/
         /*
          * DONUT CHART
          * -----------
          */

         /*var donutData = [
           {label: "Series2", data: 30, color: "#3c8dbc"},
           {label: "Series3", data: 20, color: "#0073b7"},
           {label: "Series4", data: 50, color: "#00c0ef"}
         ];*/
         $.plot(this, settings.donutData, {
           series: {
             pie: {
               show: true,
               radius: 1,
               innerRadius: 0.5,
               label: {
                 show: true,
                 radius: 2 / 3,
                 formatter: labelFormatter,
                 threshold: 0.1
               }

             }
           },
           legend: {
             show: true
           }
         });
         /*
          * END DONUT CHART
          */
 	}

       /*
        * Custom Label formatter
        * ----------------------
        */
       var labelFormatter = function (label, series) {
         return "<div style='font-size:13px; text-align:center; padding:2px; color: #fff; font-weight: 600;'>"
                 + label
                 + "<br/>"
                 + Math.round(series.percent) + "%</div>";
       }
         
     
     
     
     
     
     
     
/*##############################################################     legend	  ##########################################################*/
     var chartLegend = function (parent, data) {
         var chartLegendTag = parent.find(".chart-legend");
         chartLegendTag.find("li").remove();
         var datas = data.hasOwnProperty('datasets') ? data.datasets : data;
         datas.forEach(function(d, i) {
        	 var bgcolor = d.hasOwnProperty('strokeColor') ? d.strokeColor : d.color;
        	 chartLegendTag.append('<li style="font-size:10px;"><i class="fa fa-circle-o" style="color:' + bgcolor + '; font-size:10px;"></i> ' + d.label + '</li>');
         });
         
     }
     
     
     
     
     var legend = function (parent, data) {
         legend(parent, data, null);
     }

     var legend = function (parent, data, chart, legendTemplate) {
     	legendTemplate = typeof legendTemplate !== 'undefined' ? legendTemplate : "<%=label%>";
         parent.className = 'legend';
         var datas = data.hasOwnProperty('datasets') ? data.datasets : data;
         // remove possible children of the parent
         while(parent.hasChildNodes()) {
             parent.removeChild(parent.lastChild);
         }

         var show = chart ? showTooltip : noop;
         datas.forEach(function(d, i) {

             //span to div: legend appears to all element (color-sample and text-node)
             var title = document.createElement('div');
             title.className = 'title';
             parent.appendChild(title);

             var colorSample = document.createElement('div');
             colorSample.className = 'color-sample';
             colorSample.style.backgroundColor = d.hasOwnProperty('strokeColor') ? d.strokeColor : d.color;
             colorSample.style.borderColor = d.hasOwnProperty('fillColor') ? d.fillColor : d.color;
             title.appendChild(colorSample);
             legendNode=legendTemplate.replace("<%=value%>",d.value);
             legendNode=legendNode.replace("<%=label%>",d.label);
             var text = document.createTextNode(legendNode);
             text.className = 'text-node';
             title.appendChild(text);

             show(chart, title, i);
         });
     }

     //add events to legend that show tool tips on chart
     var showTooltip = function (chart, elem, indexChartSegment){
         var helpers = Chart.helpers;

         var segments = chart.segments;
         //Only chart with segments
         if(typeof segments != 'undefined'){
             helpers.addEvent(elem, 'mouseover', function(){
                 var segment = segments[indexChartSegment];
                 segment.save();
                 segment.fillColor = segment.highlightColor;
                 chart.showTooltip([segment]);
                 segment.restore();
             });

             helpers.addEvent(elem, 'mouseout', function(){
                 chart.draw();
             });
         }
     }
     
     var noop = function() {}
     
     
     
     
     
     
     
//     ############################################################# Knob ##########################################################
     var foundItems = 0;
	 $('.knob').each(function(i, items){
		 foundItems = foundItems + 1;
	 });
	 //console.log(found);
	 if(foundItems > 0)
	 {
	     /* END JQUERY KNOB */
	     $('.knob').knob({
	         change : function (value) {
	          //console.log("change : " + value);
	          },
	          release : function (value) {
	          //console.log("release : " + value);
	          },
	          cancel : function () {
	          //console.log("cancel : " + this.value);
	          },
	         draw: function () {
	
	           // "tron" case
	           if (this.$.data('skin') == 'tron') {
	
	             var a = this.angle(this.cv)  // Angle
	                     , sa = this.startAngle          // Previous start angle
	                     , sat = this.startAngle         // Start angle
	                     , ea                            // Previous end angle
	                     , eat = sat + a                 // End angle
	                     , r = true;
	
	             this.g.lineWidth = this.lineWidth;
	
	             this.o.cursor
	                     && (sat = eat - 0.3)
	                     && (eat = eat + 0.3);
	
	             if (this.o.displayPrevious) {
	               ea = this.startAngle + this.angle(this.value);
	               this.o.cursor
	                       && (sa = ea - 0.3)
	                       && (ea = ea + 0.3);
	               this.g.beginPath();
	               this.g.strokeStyle = this.previousColor;
	               this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sa, ea, false);
	               this.g.stroke();
	             }
	
	             this.g.beginPath();
	             this.g.strokeStyle = r ? this.o.fgColor : this.fgColor;
	             this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sat, eat, false);
	             this.g.stroke();
	
	             this.g.lineWidth = 2;
	             this.g.beginPath();
	             this.g.strokeStyle = this.o.fgColor;
	             this.g.arc(this.xy, this.xy, this.radius - this.lineWidth + 1 + this.lineWidth * 2 / 3, 0, 2 * Math.PI, false);
	             this.g.stroke();
	
	             return false;
	           }
	         }
	       });
	       /* END JQUERY KNOB */
	  }
     
     
     
     