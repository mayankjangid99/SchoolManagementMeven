/*
data-app-proj-value
data-app-proj-url
data-app-proj-role
data-app-proj-method
*/

if (!jQuery) {throw new TypeError("jQuery is required.");};


;(function ($, window, document, undefined) {
	
	$.fn.sendPostReq = function( options )
	{
		// This is the easiest way to have default options.
        var settings = $.extend({
            // These are the defaults.
            event: "click",
            filter: ".sendPost",
            action: ""
        }, options );
        
        var tag = this.prop("tagName");
        if(tag == 'A') {
			this.filter( settings.filter ).each(function() {
	            var link = $( this );
	            link.on(settings.event, function(e){
	                e.preventDefault();
	                var href = link.attr("href");
	                var action = href.split("?")[0];
	                var queryString = href.split("?")[1];
	                //console.log(action+"%"+queryString);
	                var fields = queryString.split("&");
	                
	                var form = $('<form />', {
	                    action: action,
	                    method: "post",
	                    style: 'display: none;'
	                });
	                if (typeof fields !== 'undefined' && fields !== null) {
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
		                    $('<input />', {
		                        type: 'hidden',
		                        name: inputFields[0],
		                        value: inputFields[1]
		                    }).appendTo(form);
		                });
	                }
	                form.appendTo('body').submit();
	                return this;
	            });
	            
	        });
        } else if(tag == 'FORM') {
            /*var href = link.attr("href");
            var action = href.split("?")[0];
            var queryString = href.split("?")[1];*/
            var action = this.attr("action");
            if(settings.action)
            	action = settings.action;
            var queryString = this.serialize();
            //console.log(action+"%"+queryString);
            var fields = queryString.split("&");
            
            var form = $('<form />', {
                action: action,
                method: "post",
                style: 'display: none;'
            });
            $('input, select, textarea').each(function (i, ele) {
            	var tagName = $(this).prop("tagName");
            	var name = $(this).prop("name");
            	var value = $(this).val();
                $('<'+tagName+' />', {
                    type: 'hidden',
                    name: name,
                    value: value
                }).appendTo(form);
            });
            form.appendTo('body').submit();
            return this;
        
        }
        return this;
	};
	
	
	
	$.fn.appProcessRequest = function(eventOptions)
	{
        // These are the defaults.
		var responseContent = '';
		var defaults = {
            role: '',
            url: '',
            data: '',
			complete: null, //Call back functions
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			asyncProcessing:true
		};
		
		var options = $.extend({}, defaults, eventOptions);
		
		if(!options.data){
			options.data = this.serialize();
		}
		if(!options.url){
			var link = this.attr('href');
			var attrLink = this.data('app-proj-url');
			var url = "";
			if(link && link != "#" && attrLink){
				alert("you can not enter link in href and data-app-proj-url");
			} else if((!link || link == "#") && !attrLink){
				alert("Please enter link in href or data-app-proj-url");
			} else if (link && link != "#" && !attrLink){
				url = link;
			} else if ((!link || link == "#") && attrLink){
				url = attrLink;
			}
			var queryString = url.split('?')[1];
			url = url.split('?')[0];
			options.url = url;
			if(!options.data){
				options.data = queryString;
			}
		}
		
        if(!options.role && this.data('app-proj-role')){
        	options.role = this.data('app-proj-role');
        }
        if(options.role == 'response-link') {
        	options.asyncProcessing = false;
        }
        if(!options.role) {
        	options.role = 'ajax-link'
        }

		var appModal = $('.appModal');
		var appAjaxResponse = $('.appAjaxResponse');
		
        $('section.loaderSection').fadeIn(1);
		$.ajax({
			context: document.body,
			data: options.data,
			dataType: 'html',
			type: 'POST',
			//contentType: this.options.contentType,
			url: options.url,
			timeout: 300000,
			async: options.asyncProcessing,
			success: function(response) {
				responseContent = response;
				if($.isFunction(options.complete)){
					options.complete.call(that, response);
				} else {
					var isModalRes = $(responseContent).find('.appProjModalBase').length > 0 ? true : false;
					if(options.role == 'modal-link' || isModalRes){
						$('.modal-backdrop').remove();
						$(appModal).html('');
						$(appModal).html($(responseContent).find('.appProjModalBase'));
						$('#appProjModal').modal('show');
					} else {
						$(appAjaxResponse).html('');
						$(appAjaxResponse).html(responseContent);					
					}
				}
				$('section.loaderSection').delay(500).fadeOut("hide");
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("Error in appProcessRequest");
				$('section.loaderSection').delay(500).fadeOut("hide");
			}				
		});	
		if(options.role == 'response-link'){
			$('section.loaderSection').delay(500).fadeOut("hide");
			return responseContent;
		}
		$('section.loaderSection').delay(500).fadeOut("hide");
        return this;
	}
	
	/*$('[data-toggle="modal"]').on('click', function(event){
		$(this).appProcessRequest({url:'testModal'});
	});*/
	
	
	$(".appModal ").on('click', '#appProjModal .close', function(e){
		$('#appProjModal').modal('hide');
		setTimeout(function(){
			$('.appModal').empty();
		}, 1000);
	});
	
	
	
	/* Process Error and Java Script Messages. */	
	$.processMsg  = {
		name : 'AppProj-JS',		
		version : '1.0.0',
		
		defaults: {
			errors : [],
			warnings : [],
			success : [],
			info: []
		},
		
		init: function(options, element) {
			this.$element = $(element);
			this.options = jQuery.extend({}, this.defaults, options);
			
			this._build();
			return this;
		},
		
		_build: function(){
			this.checkTagsPresent();
			this.showMsg();
		},
		
		checkTagsPresent: function(){
			var self = this.$element;
			var options = this.options;
			var baseCont = null;
			var hasAlert = false;
			
			if(self.closest('.appModal') .length < 1){
				baseCont = $('#content-container');
			} else {
				baseCont = self.closest('.appModal');
			}
			
			if(baseCont == null){
				baseCont = self;
			}
			this.$parentContainer = baseCont;
			
			baseCont.find('.form-control').removeClass("error");
			baseCont.find('.form-control').removeClass("warning");
			
			if($.hasValue(options.errors) && options.errors.length > 0){
				var $errorTag = baseCont.find('.app-proj-error:first');
				if($errorTag.find('.alert-danger').length < 1){
					$errorTag.append('<div class="alert alert-danger alert-dismissable">'+
					'<a class="closeMsg close" data-dismiss="alert" href="#">&times;</a><h4>Error...!</h4></div>');
				}
				if($errorTag.find('.alert-danger ul').length < 1){
					$errorTag.find('.alert-danger').append('<ul></ul>');
				}
				$errorTag.find('.alert ul li').remove();
				$.each(options.errors, function(i, value) {					
					var errorMsg = value.msg;
					
					baseCont.find(value.element).addClass('error');
					if($.hasValue(errorMsg)){
						$errorTag.find('.alert-danger ul').append( '<li>'+errorMsg+'</li>');
					}
				});
				hasAlert=true;
			}
			
			if($.hasValue(options.warnings) && options.warnings.length > 0){
				var $warnTag = baseCont.find('.app-proj-warning:first');
				if($warnTag.find('.alert').length < 1){
					$warnTag.append('<div class="alert alert-warning alert-dismissable">'+
					'<a class="closeMsg close" data-dismiss="alert" href="#">&times;</a><h4>Warning...!</h4></div>');
				}
				if($warnTag.find('.alert ul').length < 1){
					$warnTag.find('.alert').append('<ul></ul>');
				}
				$warnTag.find('.alert ul li').remove();
				$.each(options.warnings, function(i, value) {					
					var warMsg = value.msg;
					
					baseCont.find(value.element).addClass('warning');
					if($.hasValue(warMsg)){
						$warnTag.find('.alert ul').append( '<li>'+warMsg+'</li>');
					}
				});
				hasAlert=true;
			}
			
			if($.hasValue(options.success) && options.success.length > 0){
				var $infoTag = baseCont.find('.app-proj-success:first');
				if($infoTag.find('.alert').length < 1){
					$infoTag.append('<div class="alert alert-success alert-dismissable">'+
					'<a class="closeMsg close" data-dismiss="alert" href="#">&times;</a><h4>Success...!</h4></div>');
				}
				if($infoTag.find('.alert ul').length < 1){
					$infoTag.find('.alert').append('<ul></ul>');
				}
				$infoTag.find('.alert ul li').remove();
				$.each(options.success, function(i, value) {					
					var infoMsg = value.msg;
					
					if($.hasValue(infoMsg)){
						$infoTag.find('.alert ul').append( '<li>'+infoMsg+'</li>');
					}
				});
				hasAlert=true;
			}
			
			if($.hasValue(options.info) && options.info.length > 0){
				var $infoTag = baseCont.find('.app-proj-info:first');
				if($infoTag.find('.alert').length < 1){
					$infoTag.append('<div class="alert alert-info alert-dismissable">'+
					'<a class="closeMsg close" data-dismiss="alert" href="#">&times;</a><h4>Information...!</h4></div>');
				}
				if($infoTag.find('.alert ul').length < 1){
					$infoTag.find('.alert').append('<ul></ul>');
				}
				$infoTag.find('.alert ul li').remove();
				$.each(options.info, function(i, value) {					
					var infoMsg = value.msg;
					
					if($.hasValue(infoMsg)){
						$infoTag.find('.alert ul').append( '<li>'+infoMsg+'</li>');
					}
				});
				hasAlert=true;
			}
			if(hasAlert){
				$("#gotoTop").trigger("click");
			}
		},
		
		showMsg: function(){
			var baseCont = this.$parentContainer;
			
			if($.trim(baseCont.find('.app-proj-error:first .alert-danger ul').text()).length > 0){
				baseCont.find('.app-proj-error:first').removeClass('hidden');			
			} else {
				baseCont.find('.app-proj-error:first').addClass('hidden');
			}
			
			if($.trim(baseCont.find('.app-proj-warning:first .alert ul').text()).length > 0){
				baseCont.find('.app-proj-warning:first').removeClass('hidden');
			} else {
				baseCont.find('.app-proj-warning:first').addClass('hidden');
			}
			
			if($.trim(baseCont.find('.app-proj-success:first .alert ul').text()).length > 0){
				baseCont.find('.app-proj-success:first').removeClass('hidden');
			} else {
				baseCont.find('.app-proj-success:first').addClass('hidden');
			}
			
			if($.trim(baseCont.find('.app-proj-info:first .alert ul').text()).length > 0){
				baseCont.find('.app-proj-info:first').removeClass('hidden');
			} else {
				baseCont.find('.app-proj-info:first').addClass('hidden');
			}
		}
	};
	
	/* Function to check null values */
	$.hasValue = $.fn.hasValue = function (value) {
		var returnVal = true;
		
		if (value == null || value == undefined){
			returnVal = false;
		} else {
			if(typeof value == 'string' && $.trim(value).length < 1){
				returnVal = false;
			}
		}        
		return returnVal;
    };
    
    
    /* Add created plugins */
	//$.plugin('processMsg', $.processMsg);
    $.appProjGrid = function() {
      	 return {
      		 defaultOptions:{
      			gridId:'',
      			gridPager:'',
      			pgTitle:'',
      			btnTitle: 'Button',
      			caption: '',
      			fontSize: '12px',
      			removeColumnIds: [],
      			replaceColumnHeader: [],
      			removeColumnClass: [],
      			printGridData: false
      		 },
      		init: function(options, element) {
   			this.$element = $(element);
   			this.options = jQuery.extend({}, this.defaults, options);
   			
   			this.build();
   			return this;
   		},
      		build:function(){
      			if(this.options.printGridData)
      				this.initPrintGrid();
      		},
      		initPrintGrid : function(){
      			//add div to contains content of grid
      			$('#'+this.options.gridPager).after('<div id="prt-container" class="hidden"></div>');
      			var that = this;
      			var toppager = $('#'+this.options.gridId).jqGrid('getGridParam', "toppager");
      			// print button title.
      			var btnTitle = this.options.btnTitle;

    	   		  // setup print button in the grid bottom navigation bar.
    	   		  $('#'+this.options.gridId).jqGrid('navSeparatorAdd','#'+this.options.gridPager, {sepclass : "ui-separator"});
    	   		  $('#'+this.options.gridId).jqGrid('navButtonAdd','#'+this.options.gridPager, {caption: this.options.caption, title: btnTitle, position: 'last', buttonicon: 'ui-icon-print',
    	   			  onClickButton: function() {

   	 	   	   			// empty the print div container.
   	 	   	   		   $('#prt-container').empty();
   	
   	 	   	   		   // copy and append grid view to print div container.
   	 	   	   		   //$('#prt-container').append('<img src="../img_logo/33logo.png" display="table">');
   	 	   	   		   $('#gview_'+that.options.gridId).clone().appendTo('#prt-container').css({'page-break-after':'auto'});
   	
   	 	   	   		   // remove navigation divs.
   	 	   	   		   $('#prt-container div').remove('.ui-jqgrid-toppager,.ui-jqgrid-titlebar,.ui-userdata,#jqgh_grid_act,#jqgh_grid_cb,.s-ico,.ui-jqgrid-pager');
   	 	   	   		   $('#prt-container div .ui-jqgrid-bdiv table').css('border-collapse', 'collapse').css('border-spacing', '0');
   			 	   	   var columnheader = $('#prt-container div .ui-jqgrid-htable').find('th');
   			 	   	   var gridcellRow = $('#prt-container div .ui-jqgrid-bdiv').find('tr.jqgrow');
   			 	   	   $.each(gridcellRow, function(i, element){
   			 	   		   var gridcell = $(element).find('td');
   			 	   		   $.each(gridcell, function(i, ele){
   					 	   	   $(ele).css('border', '1px solid');
   					 	   });
   			 	   	   });
   			 	   	   $.each(columnheader, function(idx, ele){
   			 	   		   var width = $(ele).css('width');
   			 	   		   $.each(gridcellRow, function(i, element){
   				 	   		   var gridcell = $(element).find('td');
   				 	   		   $(gridcell[idx]).css('width', width);
   				 	   	   });
   			 	   	   });
   			 	   	   //Remove gridcell 
   	 	   	   		   if(that.options.removeColumnClass) {
   	 	   	   			   $.each(that.options.removeColumnClass, function(i,v){
   	 	   	   				   $('#prt-container div').find('.'+v).remove();
   	 	   	   			   });
   	 	   	   		   }
   	 	   	   		   //Remove header 
   			 	   	   if(that.options.removeColumnIds){
   			 	   		   $.each(that.options.removeColumnIds, function(i,v){
   				 	   		   $('#prt-container').find('#'+that.options.gridId+'_'+v).remove();				 	   			   
   			 	   		   });
   			 	   	   }
   			 	   	   //replace header 
   			 	   	   if(that.options.replaceColumnHeader){
   			 	   		   $.each(that.options.replaceColumnHeader, function(i,v){
   				 	   		   $('#prt-container').find('#'+that.options.gridId+'_'+v.exist).text(v.replace);				 	   			   
   			 	   		   });
   			 	   	   }
   	 	   	   				
   	 	   	   		   $('#prt-container div').find('td').css('font-size', that.options.fontSize);
   	 	   	   		   
   	 	   	   		   $('#prt-container div .cbox').remove();
   	 	   	   		   $('#prt-container div input').remove();
   	
   	 	   	   		   // print the contents of the print container.
   	 	   	   		   $('#prt-container').printElement({pageTitle:that.options.pgTitle, /*overrideElementCSS:[{ href:'css/print-grid.css',media:'print'}]*/});
   	 	   	   		   $('#prt-container').empty();
   	   			  } 
   	   		  });
    	   		  
    	   		if(toppager){
   	 	   		// setup print button in the grid top navigation bar.
   	 	   		  $('#'+this.options.gridId).jqGrid('navSeparatorAdd','#'+this.options.gridId+'_toppager_left', {sepclass :'ui-separator'});
   	 	   		  $('#'+this.options.gridId).jqGrid('navButtonAdd','#'+this.options.gridId+'_toppager_left', {caption: this.options.caption, title: btnTitle, position: 'last', buttonicon: 'ui-icon-print', 
   	 	   			  onClickButton: function() {
   	
   		 	   	   			// empty the print div container.
   		 	   	   		   $('#prt-container').empty();
   		
   		 	   	   		   // copy and append grid view to print div container.
   		 	   	   		   //$('#prt-container').append('<img src="../img_logo/33logo.png" display="table">');
   		 	   	   		   $('#gview_'+that.options.gridId).clone().appendTo('#prt-container').css({'page-break-after':'auto'});
   		
   		 	   	   		   // remove navigation divs.
   		 	   	   		   $('#prt-container div').remove('.ui-jqgrid-toppager,.ui-jqgrid-titlebar,.ui-userdata,#jqgh_grid_act,#jqgh_grid_cb,.s-ico,.ui-jqgrid-pager');
   		 	   	   		   $('#prt-container div .ui-jqgrid-bdiv table').css('border-collapse', 'collapse').css('border-spacing', '0');
   				 	   	   var columnheader = $('#prt-container div .ui-jqgrid-htable').find('th');
   				 	   	   var gridcellRow = $('#prt-container div .ui-jqgrid-bdiv').find('tr.jqgrow');
   				 	   	   $.each(gridcellRow, function(i, element){
   				 	   		   var gridcell = $(element).find('td');
   				 	   		   $.each(gridcell, function(i, ele){
   						 	   	   $(ele).css('border', '1px solid');
   						 	   });
   				 	   	   });
   				 	   	   $.each(columnheader, function(idx, ele){
   				 	   		   var width = $(ele).css('width');
   				 	   		   $.each(gridcellRow, function(i, element){
   					 	   		   var gridcell = $(element).find('td');
   					 	   		   $(gridcell[idx]).css('width', width);
   					 	   	   });
   				 	   	   });
   				 	   	   //Remove gridcell 
   				 	   	   if(that.options.removeColumnClass) {
   		 	   	   			   $.each(that.options.removeColumnClass, function(i,v){
   		 	   	   				   $('#prt-container div').find('.'+v).remove();
   		 	   	   			   });
   		 	   	   		   }
   		 	   	   		   //Remove header 
   				 	   	   if(that.options.removeColumnIds){
   				 	   		   $.each(that.options.removeColumnIds, function(i,v){
   					 	   		   $('#prt-container').find('#'+that.options.gridId+'_'+v).remove();				 	   			   
   				 	   		   });
   				 	   	   }
   				 	   	   //replace header 
   				 	   	   if(that.options.replaceColumnHeader){
   				 	   		   $.each(that.options.replaceColumnHeader, function(i,v){
   					 	   		   $('#prt-container').find('#'+that.options.gridId+'_'+v.exist).text(v.replace);				 	   			   
   				 	   		   });
   				 	   	   }
   		 	   	   		   $('#prt-container div').find('td').css('font-size', that.options.fontSize);
   		 	   	   		   $('#prt-container div .cbox').remove();
   		 	   	   		   $('#prt-container div input').remove();
   		 	   	   		   // print the contents of the print container.
   		 	   	   		   $('#prt-container').printElement({pageTitle:that.options.pgTitle, /*overrideElementCSS:[{ href:'css/print-grid.css',media:'print'}]*/});
   		 	   	   		   $('#prt-container').empty();
   	 	   			  } 
   	 	   		  });
    	   		}
      		}
      		
      	 }
      }();
		
})(jQuery, window, document);
