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

$.widget('custom.userDetailsAutoComplete', $.ui.autocomplete, {
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
        inner_html = inner_html + '<div class="ui-name">' + item.value.name + ' (' + item.value.username + ')</div>'
        	+ '<div class="ui-email">' + item.value.email + '</div>'
        	+ '<div class="ui-mobile">' + item.value.mobile + '</div>'
        	+'</div></a>';
        //result = $('<li></li>').data('ui-autocomplete-item', item).append('<a class="mcacAnchor">' + t + '<div style="clear: both;"></div></a>').appendTo(ul);
        return $( "<li></li>" )
        .data( "ui-autocomplete-item", item )
        .append(inner_html)
        .appendTo( ul );
    }
});

var initializeUserDetailsAutoComplete = function(){
	var allUserDetailsData;
	$.ajax({
		url : "getUsersDetailsInAutocomplete", // name of controller followed by function
		dataType : "json",
		cache: false,
		async: false,
		success : function(data) {
			allUserDetailsData = $.map( data, function( item ) {
				return {value: item};
			});
		},
		error: function(){
				alert("error occurred in initializeUserDetailsAutoComplete");}
	});
	$(".userDetailsAutoComplete").userDetailsAutoComplete({
		    showHeader: false,
		    showImage: true,
		    imageBaseUrl: jContextPath + '/upload/user_images/',
		    columns: [{name: 'Image', width: '70px', valueField: 'name'},
			        {name: 'Details', width: '100px',valueField: 'fatherName'}
			        ],
			minLength : 0,
			source : function( request, response){
				var re = $.ui.autocomplete.escapeRegex(request.term); 
			    var matcher = new RegExp( "^" + re, "i" );
			    var result = $.grep(allUserDetailsData, function(item){
					return matcher.test(item.value.name); 
				});
				response(result);
			},
			focus : function(event, ui) {                
				return false;
			},
			select : function(event, ui) {
				var userData = ui.item.value;

		    	$(this).val(userData.name);
		    	populateUserDetailsData($(this), userData);					
				return false;
			}
		});

		/* trigger the auto completer on focus */
        $(".userDetailsAutoComplete").on("click", function(e){
        	$(this).userDetailsAutoComplete('search', $(this).val());
        });
        $(".userDetailsAutoComplete").on("input", function(e){
        	clearUserDetailsData($(this));
        });
        
        function populateUserDetailsData(element, studentDetailsData) {
        	var propertyName = findPropertyNameByMatcherClassForElement(element, "UserDetails");
            //alert("data:"+JSON.stringify(hostContactData));
            if(propertyName){
            	elements = $("."+propertyName+"-fullname");
            	if(elements.length > 0)
            		$(elements[0]).val(studentDetailsData.name).change();
            	
            	var elements = $("."+propertyName+"-username");
            	if(elements.length > 0)
            		$(elements[0]).val(studentDetailsData.username).change();
            	
            	var elements = $("."+propertyName+"-email");
            	if(elements.length > 0)
            		$(elements[0]).val(studentDetailsData.email).change();
            	
            	var elements = $("."+propertyName+"-mobile");
            	if(elements.length > 0)
            		$(elements[0]).val(studentDetailsData.mobile).change();
            }
        }
        
        //This is generic function.
        function findPropertyNameByMatcherClassForElement(thisObject, matchClass){	
        	var propertyName = null;
        	var elementMatcherClass = null;
        	var className = thisObject.attr('class');
        	if(className){
        		var classNames = className.split(' ');
        		for(var i = 0; i < classNames.length; i++){
        			if(classNames[i].match("^intelli" + matchClass)){
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
        function clearUserDetailsData(element) {
        	var propertyName = findPropertyNameByMatcherClassForElement(element, "UserDetails");
            //alert("data:"+JSON.stringify(hostContactData));
            if(propertyName){
            	var elements = $("."+propertyName+"-username");
            	if(elements.length > 0)
            		$(elements[0]).val('');
            	
            	var elements = $("."+propertyName+"-email");
            	if(elements.length > 0)
            		$(elements[0]).val('');
            	
            	var elements = $("."+propertyName+"-mobile");
            	if(elements.length > 0)
            		$(elements[0]).val('');
            }
        }
}