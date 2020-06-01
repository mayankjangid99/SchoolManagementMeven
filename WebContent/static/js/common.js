//Loader
$(window).load(function(){
	  $('section.loaderSection').delay(1000).fadeOut("show");
});

var switchRes = $()

$(document).ready(function() {	
	$("a").sendPostReq();
	
	//Loader on click
	/*$("a, input[type=submit]").click(function(){
		var hrefVal = $(this).attr("href");
		if(hrefVal != "" & hrefVal != "#")
		{
			 //$('section.loaderSection').fadeIn(100);
			//$('section.loaderSection').css("display", "block");
		}
		
	});*/
	
	$('.date-pickerfull').datepicker({format: 'dd-mm-yyyy', todayBtn: 'linked', });
	
	$('.date-picker').datepicker({format: 'dd-mm-yyyy', daysOfWeekDisabled: [0,6], daysOfWeekHighlighted: [0,6], todayBtn: 'linked' });
	
	$('input[placeholder=dd-mm]').datepicker({format: 'dd-mm', daysOfWeekDisabled: [0,6], daysOfWeekHighlighted: [0,6], todayBtn: 'linked' });
	
	$('.monthFormat').datepicker({format: 'dd-mm-yyyy', viewMode: 'months', minViewMode: 'months'});
	
//	Date format for Each date-picker dd-mm-yyyy
	$('.date-picker, .date-pickerfull').each(function() {
		//console.log("date-picker="+$(this).val());
		var dateDatabase = $(this).val().split(' ');
		//console.log(dateDatabase);		
		if (dateDatabase != '' && dateDatabase.length >= 1) {
			var preDate = dateDatabase[0].split('-');
			if(preDate[0].length == 4)
			{
				//console.log(preDate);
				var formatDate = preDate[2] + '-' + preDate[1] + '-' + preDate[0];
				//console.log(formatDate);
				$(this).val(formatDate).datepicker('update').change();
			}
		}
	});
	
	//Replace source
	$("img").each(function() { 
	    $(this).error(function(){
	    	if($(this).hasClass('bgprofile')){
	            $(this).attr('src', '../static/img/profile.png');
	    		//$(this).attr("onerror","this.src='../static/img/profile.png'");
	    	}
	    });
	});
	

	var allCls = '';
	$('a.clearfix').on('click', function(e){
		var skinVal = $(this).attr('data-skin');
		$.ajax({
			   url : "changeUserTheme",
			   data:"theme=" + skinVal,
				//dataType : "json",
				cache: false,
				async: false,
				 /*beforeSend: function() {
					   $("#div_id").html('<img src="./images/ajax-loader.gif"> saving...');
					  },*/
			   success: function(data) {
				   if(data == "success"){
						$('body').removeAttr('class');
						$('body').addClass(skinVal);
						allCls = skinVal;
				   } else{
					   alert("server encountered an internal error. please try again after some time...!!!")
				   }
			   },
			   error: function(e) {
				   alert("error in ajax to chnage theme");
			   }
		}); 
	});
	
	$('a.clearfix').on('mouseover', function(e){
		allCls = $('body').attr('class');
		var skinVal = $(this).attr('data-skin');
		$('body').removeAttr('class');
		$('body').addClass(skinVal);
	});
	
	$('a.clearfix').on('mouseout', function(e){
		$('body').removeAttr('class');
		$('body').addClass(allCls);
	});
	

	$.processMsg.init({errors:[]}, document.forms[0]);
	
});












sendRequest = function (action, method, inputMap) {
    'use strict';
    var form;
    form = $('<form />', {
        action: action,
        method: method,
        style: 'display: none;'
    });
    //console.log(action);
    if (typeof inputMap !== 'undefined' && inputMap !== null) {
        $.each(inputMap, function (name, value) {
            $('<input />', {
                type: 'hidden',
                name: name,
                value: value
            }).appendTo(form);
        });
    }
    form.appendTo('body').submit();
}

$.sendRequest = function (action, method, inputMap) {
    'use strict';
    var form;
    form = $('<form />', {
        action: action,
        method: method,
        style: 'display: none;'
    });
    //console.log(action);
    if (typeof inputMap !== 'undefined' && inputMap !== null) {
        $.each(inputMap, function (name, value) {
            $('<input />', {
                type: 'hidden',
                name: name,
                value: value
            }).appendTo(form);
        });
    }
    form.appendTo('body').submit();
}

$.sendRequestTarget = function (action, method, inputMap) {
    'use strict';
    var form;
    form = $('<form />', {
        action: action,
        method: method,
        target: '_blank',
        style: 'display: none;'
    });
    //console.log(action);
    if (typeof inputMap !== 'undefined' && inputMap !== null) {
        $.each(inputMap, function (name, value) {
            $('<input />', {
                type: 'hidden',
                name: name,
                value: value
            }).appendTo(form);
        });
    }
    form.appendTo('body').submit();
}
/*$.each(input, function (name, value) {
    $('<input />', {
        type: 'hidden',
        name: name,
        value: value
    }).appendTo(form);
});
$("mySelect").append(
	$.map(selectValues, function(v,k) {
	    return $("<option>").val(k).text(v);
	})
);*/

//Send post request by Custom Attribute
$(document).on('click', '[data-app-proj-method="get"], [data-app-proj-method="post"]', function(e){
	e.preventDefault();
	var link = $(this).attr('href');
	var attrLink = $(this).data('app-proj-url');
	var url = "";
	if(link && link != "#" && attrLink){
		alert("you can not enter link in href and data-school-link");
	} else if((!link || link == "#") && !attrLink){
		alert("Please enter link in href or data-school-link");
	} else if (link && link != "#" && !attrLink){
		url = link;
	} else if ((!link || link == "#") && attrLink){
		url = attrLink;
	}
	var method = $(this).attr('data-app-proj-method');
	//var url = $(this).attr('data-app-proj-url');
	var action = url.split("?")[0];
    var queryString = url.split("?")[1];
    var inputMap = {};
    if(queryString)
    	inputMap = getJsonFromQueryString(queryString);
    //console.log(inputMap);
	sendRequest(action, method, inputMap);
});


var getJsonFromQueryString = function(queryString){
	var jsonData = {};
    //console.log(action+"%"+queryString);
    var fields = queryString.split("&");
    if (typeof fields !== 'undefined' && fields !== null) {
   	 var fieldIndex = 1;
        $.each(fields, function (name, value) {
        	var inputFields = value.split("=");
        	//alert("length="+inputFields.length);
        	if(inputFields.length > 2)
        	{
            	var temptImput = "";
            	var arrayIndex = 1;
            	for(i = 1; i < inputFields.length; i++)
            	{
            		if(arrayIndex > 1)
            			temptImput = temptImput + "=" + inputFields[i];
            		else
            			temptImput = temptImput + inputFields[i];
            		arrayIndex++;
	                	//console.log(temptImput);
	                	//alert("break");
            	}
            	inputFields[1] = temptImput;
        	}
    		jsonData[inputFields[0]] = inputFields[1];

        	fieldIndex++;
        });
        return jsonData;
    }
}

$(document).on('click', 'a[data-sharepro-role="modal-link"]', function(event){
	event.preventDefault();
	if($(this).hasClass('delConfirm')){
		var conf = confirm('Are you sure to delete this record?');
		if(!conf)
			return false;
	}
	$('#shareProModal').modal('hide');
	$(this).appProcessRequest();
});



$(document).on('click', '.modalSubmit', function(e){
	var form = $(this).closest('form');
	if($(form).data('app-proj-role')){
		e.preventDefault();
		if($(form).data('app-proj-role') == 'modal-form'){
			$('#appProjModal').modal('hide');
			var url = $(form).attr('action');
			var data = $(form).serialize();
			var msg = $(form).appProcessRequest({url: url, data: data, role:'modal-link'});
			$('#appProjModal').find('.modalMessage').text(msg);
		}
	}
	
});


$('a[data-app-proj-role="modal-link"]').on('click', function(event){
	event.preventDefault();
	$(this).appProcessRequest();
});

$('input[type="submit"]').on('click', function(e){
	var $form = $(this).closest('form');
	var $hiddenDiv = $form.find("div.hidden");
	if($('[name="operation"]').length == 0)
		$hiddenDiv.append('<input type="hidden" name="operation" value="saveReturn" />');
	if($(this).hasClass('saveReturn')) {
		$('[name="operation"]').val('saveReturn');
	} else if($(this).hasClass('cancel')) {
		$('[name="operation"]').val('cancel');
		//$form.removeClass('formValid');
	} else {
		$('[name="operation"]').val('save');
	}
	//e.preventDefault();
});

$.processMsg.init({errors:[]}, document.forms[0]);

$('#gotoTop').on('click', function(){
	$('html, body').animate({ scrollTop: 0 }, 'slow');
});

formatDate = function (){
	var date = new Date();
   // 01, 02, 03, ... 29, 30, 31
   var dd = (date.getDate() < 10 ? '0' : '') + date.getDate();
   // 01, 02, 03, ... 10, 11, 12
   var MM = ((date.getMonth() + 1) < 10 ? '0' : '') + (date.getMonth() + 1);
   // 1970, 1971, ... 2015, 2016, ...
   var yyyy = date.getFullYear();
   // create the format you want
   return (dd + "-" + MM + "-" + yyyy);
}

function formatDateToString(date){
   // 01, 02, 03, ... 29, 30, 31
   var dd = (date.getDate() < 10 ? '0' : '') + date.getDate();
   // 01, 02, 03, ... 10, 11, 12
   var MM = ((date.getMonth() + 1) < 10 ? '0' : '') + (date.getMonth() + 1);
   // 1970, 1971, ... 2015, 2016, ...
   var yyyy = date.getFullYear();
   // create the format you want
   return (dd + "-" + MM + "-" + yyyy);
}

