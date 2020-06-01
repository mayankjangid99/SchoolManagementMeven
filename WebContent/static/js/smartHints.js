function initializeHints(fetchType){
	initializeClassSectionHints(fetchType);
}

$.widget('custom.smartAutoPopulate', $.ui.autocomplete, {
    _renderMenu: function(ul, items) {
        var self = this,
            thead;

        if (this.options.showHeader) {
            table = $('<div class="ui-widget-header" style="width:auto"></div>');
            $.each(this.options.columns, function(index, item) {
                table.append('<span style="padding:4px 4px;float:left;font-size: 12px; width:' + item.width + ';">' + item.name + '</span>');
            });
            table.append('<div style="clear: both;"></div>');
            ul.append(table);
        }
        $.each(items, function(index, item) {
            self._renderItem(ul, item);
        });
    },
    _renderItem: function(ul, item) {
        var t = '',
            result = '';
        $.each(this.options.columns, function(index, column) {
			if(item.value[column.valueField]){
	            t += '<span style="padding:0 4px;float:left;font-size: 12px;font-family: arial;width:' + column.width + ';" class="' + column.cssClass + '">' + (item.value[column.valueField]?item.value[column.valueField]:"") + '</span>'
			}
        });

        result = $('<li></li>').data('ui-autocomplete-item', item).append('<a class="mcacAnchor">' + t + '<div style="clear: both;"></div></a>').appendTo(ul);
        return result;
    }
});	

$.widget('custom.studentDetailsAutoComplete', $.ui.autocomplete, {
    _renderMenu: function(ul, items) {
        var self = this,
            thead;

        if (this.options.showHeader) {
            table = $('<div class="ui-widget-header" style="width:auto"></div>');
            $.each(this.options.columns, function(index, item) {
                table.append('<span style="padding:4px 4px;float:left;font-size: 12px; width:' + item.width + ';">' + item.name + '</span>');
            });
            table.append('<div style="clear: both;"></div>');
            ul.append(table);
        }
        $.each(items, function(index, item) {
            self._renderItem(ul, item);
        });
    },
    _renderItem: function(ul, item) {
        var inner_html = '<a><div class="ui-list_item_container">'
        	if(this.options.imageBaseUrl){
        		inner_html = inner_html + '<div class="ui-image"><img src="' + this.options.imageBaseUrl + item.value.image + '" height="54px;" width="50px;"></div>'
        	}
        inner_html = inner_html + '<div class="ui-name">' + item.value.name + '</div>'
        	+ '<div class="ui-fatherName">' + item.value.fatherName + ', ' + item.value.motherName + '</div>'
        	+ '<div class="ui-fatherName">' + item.value.admissionNo + ', ' + item.value.rollNo + '</div>'
        	+'</div></a>';
        //result = $('<li></li>').data('ui-autocomplete-item', item).append('<a class="mcacAnchor">' + t + '<div style="clear: both;"></div></a>').appendTo(ul);
        return $( "<li></li>" )
        .data( "ui-autocomplete-item", item )
        .append(inner_html)
        .appendTo( ul );
    }
});	


//This is generic function.
function findPropertyNameForElement(thisObject){	
	var propertyName = null;
	var className = thisObject.attr('class');
	if(className){
		var classNames = className.split(' ');
		for(var i = 0; i < classNames.length; i++){
			if(classNames[i].match("^intelli")){
				elementClass = classNames[i];
				break;
			}
		}
		var parts = elementClass.split('-');
		if(parts && parts.length > 0){
			propertyName = parts[0]+"-"+parts[1];
		}
	}
    
    return propertyName;
}



/*
* For Country.
*/
var allClassData = null;
var allSectionData = null;
var groupNum=null;
function initializeClassSectionHints(fetchType){
	$.ajax({
		url : "getAllClasses?fetchType=" + fetchType,
		dataType : "json",
		cache: false,
		async: false,
		success : function(data) {
			allClassData = $.map( data.classSectionModels, function( item ) {
				return {value: item};
			});
		},
		error: function(){
				alert("error occurred in initializeClassSectionHints");}
	});
	$(".stdClassHints").smartAutoPopulate({
		    showHeader: true,
		    columns: [{name: 'Class Name', width: '200px', valueField: 'className'},
			        {name: 'Class Code', width: '100px',valueField: 'classCode'}
			        ],
			minLength : 0,
			source : function( request, response){
				var re = $.ui.autocomplete.escapeRegex(request.term); 
			    var matcher = new RegExp( "^" + re, "i" );
			    var result = $.grep(allClassData, function(item){
					return matcher.test(item.value.className); 
				});
				response(result);
			},
			focus : function(event, ui) {                
				return false;
			},
			select : function(event, ui) {
				var classData = ui.item.value;
				$(this).val(classData.countryname);					
				populateCountryNameData($(this), classData);	
				initializeStateHints();
				return false;
			}
		});

		/* trigger the auto completer on focus */
        $(".stdClassHints").on("click", function(e){
        	groupNum=findPropertyNameForElement($(this));
        	//clearSection($(this));
           	$(this).smartAutoPopulate('search', $(this).val());
        });
        
//        remove class code when input in class name
        $(".stdClassHints").on("input", function(e){
        	clearSection($(this));
        	var propName=findPropertyNameForElement($(this));
        	$('.'+propName+'-classCode').val('');
        });
}



function populateCountryNameData(element, classData){
    var propertyName = findPropertyNameForElement(element);
    //alert("data:"+JSON.stringify(hostContactData));
    if(propertyName){
    	var elements = $("."+propertyName+"-className");
    	if(elements.length > 0)
    		$(elements[0]).val(classData.className);
    	
    	var elements = $("."+propertyName+"-classCode");
    	if(elements.length > 0)
    		$(elements[0]).val(classData.classCode);
    	
    	var elements = $("."+propertyName+"-sectionName");
    	if(elements.length > 0)
    		$(elements[0]).addClass('smart-hint');
    }
}


//																State Code initialization

//On Click dailog box of Section if Country Code Exist
/*$(".stdSectionHints:not(.smart-hint)").click(function(){
	var groupN = findPropertyNameForElement($(this));
	var cCode = $('.'+groupNum+'-classCode').val();
	if(cCode !='')
		{
			groupNum = groupN;
			initializeStateHints();
			$(this).smartAutoPopulate('search', $(this).val());
		}
});*/



function initializeStateHints(){
	var cCode=$('.'+groupNum+'-classCode').val();
	//console.log(cCode);
	$.ajax({
		url : "getSections?classCode="+cCode,
		dataType : "json",
		cache: false,
		async: false,
		success : function(data) {
			allSectionData = $.map( data.classSectionModels, function( item ) {
				return {value: item};
			});
		},
		error: function(){
				alert("error occurred in initializeStateHints");}
	});
	$(".stdSectionHints").smartAutoPopulate({
		    showHeader: true,
		    columns: [{name: 'Section Name', width: '200px', valueField: 'sectionName'},
			        {name: 'Section Code', width: '100px',valueField: 'sectionCode'}
			        ],
			minLength : 0,
			source : function( request, response){
				var re = $.ui.autocomplete.escapeRegex(request.term); 
			    var matcher = new RegExp( "^" + re, "i" );
			    var result = $.grep(allSectionData, function(item){
					return matcher.test(item.value.sectionName); 
				});
				response(result);
			},
			focus : function(event, ui) {                
				return false;
			},
			select : function(event, ui) {
				var sectionData = ui.item.value;
				$(this).val(sectionData.sectionName);					
				populateStateNameData($(this), sectionData);					
				return false;
			}
		});

		/* trigger the auto completer on focus */
        $(".stdSectionHints").on("click", function(e){
        	//clearState($(this));
        	var propName=findPropertyNameForElement($(this));
        	var classCode=$('.'+propName+'-classCode').val();
//        	check country code before dailog box
        	if(classCode)			
        	{
        		$(this).smartAutoPopulate('search', $(this).val());
        	}
        });
        
//       remove section code when input in section name
        $(".stdSectionHints").on("input", function(e){
        	var propName=findPropertyNameForElement($(this));
        	$('.'+propName+'-sectionCode').val('');
        	
        	var propIdx = propName.split('-');
        	var studentDetailsEle = $('.intelliDetails-'+propIdx[1]+'-fullName');
        	if(studentDetailsEle.length > 0)
        		clearStudentDetailsAllData(studentDetailsEle);
        });
}


function populateStateNameData(element, sectionData){
    var propertyName = findPropertyNameForElement(element);
    //alert("data:"+JSON.stringify(hostContactData));
    if(propertyName){
    	var elements = $("."+propertyName+"-sectionName");
    	if(elements.length > 0)
    		$(elements[0]).val(sectionData.sectionName);
    	
    	var elements = $("."+propertyName+"-sectionCode");
    	if(elements.length > 0)
    		$(elements[0]).val(sectionData.sectionCode);
    	
    }
}


function clearCountry(curObj)
{
	var propName=findPropertyNameForElement(curObj);
	$('.'+propName+'-className').val("");
	$('.'+propName+'-classCode').val('');
}
function clearSection(curObj)
{
	var propName=findPropertyNameForElement(curObj);
	$('.'+propName+'-sectionName').val('');
	$('.'+propName+'-sectionCode').val('');
	
	var propIdx = propName.split('-');
	var studentDetailsEle = $('.intelliDetails-'+propIdx[1]+'-fullName');
	if(studentDetailsEle.length > 0)
		clearStudentDetailsAllData(studentDetailsEle);
}



/*var initializeStudentDetailsAutocomplete = function(classCode, sectionCode){
	  $(".studentDetailsAutocomplete").autocomplete({
	    source: "getStudentsDetailsInAutocomplete?classCode="+classCode+"&sectionCode="+sectionCode, // name of controller followed by function
	    select: function (e, i) {
	    	$(this).val(i.item.name);
	    	$(this).parent().parent().parent().find('[name="admissionNo"]').val(i.item.admissionNo);
	    	$(this).parent().parent().parent().find('[name="rollNo"]').val(i.item.rollNo);
	        return false;
	    }
	  }).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
	        var inner_html = '<a><div class="ui-list_item_container">'
	        	+ '<div class="ui-image"><img src="/SchoolManagement/upload/student_images/' + item.image + '" height="54px;" width="50px;"></div>'
	        	+ '<div class="ui-name">' + item.name + '</div>'
	        	+ '<div class="ui-fatherName">' + item.fatherName + ', ' + item.motherName + '</div>'
	        	+ '<div class="ui-fatherName">' + item.admissionNo + ', ' + item.rollNo + '</div>'
	        	+'</div></a>';
	        return $( "<li></li>" )
	            .data( "item.autocomplete", item )
	            .append(inner_html)
	            .appendTo( ul );
	    };
}*/

var initializeStudentDetailsAutoComplete = function(classCode, sectionCode){
	var allStudentDetailsData;
	$.ajax({
		url : "getStudentsDetailsInAutocomplete?classCode="+classCode+"&sectionCode="+sectionCode, // name of controller followed by function
		dataType : "json",
		cache: false,
		async: false,
		success : function(data) {
			allStudentDetailsData = $.map( data, function( item ) {
				return {value: item};
			});
		},
		error: function(){
				alert("error occurred in initializeStudentDetailsAutoComplete");}
	});
	$(".studentDetailsAutoComplete").studentDetailsAutoComplete({
		    showHeader: false,
		    showImage: true,
		    imageBaseUrl: jContextPath + '/upload/student_images/',
		    columns: [{name: 'Section Name', width: '200px', valueField: 'name'},
			        {name: 'Section Code', width: '100px',valueField: 'fatherName'}
			        ],
			minLength : 0,
			source : function( request, response){
				var re = $.ui.autocomplete.escapeRegex(request.term); 
			    var matcher = new RegExp( "^" + re, "i" );
			    var result = $.grep(allStudentDetailsData, function(item){
					return matcher.test(item.value.name); 
				});
				response(result);
			},
			focus : function(event, ui) {                
				return false;
			},
			select : function(event, ui) {
				var studentData = ui.item.value;

		    	$(this).val(studentData.name);
		    	populateStudentDetailsData($(this), studentData);					
				return false;
			}
		});

		/* trigger the auto completer on focus */
        $(".studentDetailsAutoComplete").on("click", function(e){
        	$(this).studentDetailsAutoComplete('search', $(this).val());
        });
        $(".studentDetailsAutoComplete").on("input", function(e){
        	clearStudentDetailsData($(this));
        });
}

//This is generic function.
function findPropertyNameByMatcherClassForElement(thisObject, matchClass){	
	var propertyName = null;
	var elementMatcherClass = null;
	var className = thisObject.attr('class');
	if(className){
		var classNames = className.split(' ');
		for(var i = 0; i < classNames.length; i++){
			if(classNames[i].match("^intelli"+matchClass)){
				elementMatcherClass = classNames[i];
				break;
			}
		}
		var parts = elementMatcherClass.split('-');
		if(parts && parts.length > 0){
			propertyName = parts[0]+"-"+parts[1];
		}
	}
    return propertyName;
}

function populateStudentDetailsData(element, studentDetailsData){
	var propertyName = findPropertyNameByMatcherClassForElement(element, "Details");
    //alert("data:"+JSON.stringify(hostContactData));
    if(propertyName){
    	var elements = $("."+propertyName+"-admissionNo");
    	if(elements.length > 0)
    		$(elements[0]).val(studentDetailsData.admissionNo).change();
    	
    	elements = $("."+propertyName+"-rollNo");
    	if(elements.length > 0)
    		$(elements[0]).val(studentDetailsData.rollNo).change();
    }
}
function clearStudentDetailsData(element)
{
	var propertyName = findPropertyNameByMatcherClassForElement(element, "Details");
    //alert("data:"+JSON.stringify(hostContactData));
    if(propertyName){
    	var elements = $("."+propertyName+"-admissionNo");
    	if(elements.length > 0)
    		$(elements[0]).val('');
    	
    	elements = $("."+propertyName+"-rollNo");
    	if(elements.length > 0)
    		$(elements[0]).val('');
    }
}

function clearStudentDetailsAllData(element)
{
	var propertyName = findPropertyNameByMatcherClassForElement(element, "Details");
    //alert("data:"+JSON.stringify(hostContactData));
    if(propertyName){
    	var elements = $("."+propertyName+"-fullName");
    	if(elements.length > 0)
    		$(elements[0]).val('');
    	
    	elements = $("."+propertyName+"-admissionNo");
    	if(elements.length > 0)
    		$(elements[0]).val('');
    	
    	elements = $("."+propertyName+"-rollNo");
    	if(elements.length > 0)
    		$(elements[0]).val('');
    }
}
