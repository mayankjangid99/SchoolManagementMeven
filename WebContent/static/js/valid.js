$(document).ready(function () {
   // Setup form validation on the #register-form element
	
	$('.form').validate({       
        // Specify the validation rules
        rules: {
            className: "required",
            sectionName: "required",
//            date: "required"
        },        
        // Specify the validation error messages
        messages: {
            className: "Please select Class",
			sectionName: "Please select Section",
//			date: "Please select a date"
        }
    });
	
	$('input[type=submit],input[type=button]').on('click', function(e){
		var selectForm = $(this).closest('.formValidate');
		$(selectForm).validate({       
	        // Specify the validation rules
	        rules: {
	            className: "required",
	            sectionName: "required",
	//            date: "required"
	        },        
	        // Specify the validation error messages
	        messages: {
	            className: "Please select Class",
				sectionName: "Please select Section",
	//			date: "Please select a date"
	        }
	    });
	});
	
	// alias required to cRequired with new message
	$.validator.addMethod("classNameRequired", $.validator.methods.required, "Please select Class");
	$.validator.addMethod("classCodeRequired", $.validator.methods.required, "Please select Class Code");
	$.validator.addMethod("sectionNameRequired", $.validator.methods.required, "Please select Section Name");
	$.validator.addMethod("sectionCodeRequired", $.validator.methods.required, "Please select Section Code");
	$.validator.addMethod("typeRequired", $.validator.methods.required, "Type is required.");
	$.validator.addMethod("feeTypeRequired", $.validator.methods.required, "Fee Type is required.");
	$.validator.addMethod("monthRequired", $.validator.methods.required, "Month is required.");
	$.validator.addMethod("resultTypeRequired", $.validator.methods.required, "Result type is required.");
	$.validator.addMethod("admissionNoRequired", $.validator.methods.required, "Please provide a Admission Number.");
	$.validator.addMethod("rollNoRequired", $.validator.methods.required, "Please provide a Roll Number.");
	$.validator.addMethod("dateRequired", $.validator.methods.required, "Please provide a Date.");
	$.validator.addMethod("statusRequired", $.validator.methods.required, "Please select a Status.");
	$.validator.addMethod("fileTypeRequired", $.validator.methods.required, "Please select a File Type.");
	$.validator.addMethod("bulkFileRequired", $.validator.methods.required, "Please select a File.");
	$.validator.addMethod("paymentCategoryNameRequired", $.validator.methods.required, "Please provide a Payment Category Name.");
	$.validator.addMethod("paymentCategoryRequired", $.validator.methods.required, "Please provide a Payment Category.");
	$.validator.addMethod("paymentCategoryCodeRequired", $.validator.methods.required, "Please provide a Payment Category Code.");
	$.validator.addMethod("configCodeRequired", $.validator.methods.required, "Please enter Config Code.");
	$.validator.addMethod("configNameRequired", $.validator.methods.required, "Please enter Config Name.");
	$.validator.addMethod("configValueRequired", $.validator.methods.required, "Please enter Config Value.");
	$.validator.addMethod("configDescriptionRequired", $.validator.methods.required, "Please enter Config Description.");
	$.validator.addMethod("subjectNameRequired", $.validator.methods.required, "Please enter Subject Name.");
	$.validator.addMethod("subjectCodeRequired", $.validator.methods.required, "Please enter Subject Code.");
	$.validator.addMethod("feeCategoryNameRequired", $.validator.methods.required, "Please enter Fee Category Name.");
	$.validator.addMethod("feeCategoryCodeRequired", $.validator.methods.required, "Please enter Fee Category Code.");
	$.validator.addMethod("amountRequired", $.validator.methods.required, "Please enter a Amount.");
	$.validator.addMethod("schoolCodeRequired", $.validator.methods.required, "Please enter School Code.");
	$.validator.addMethod("nameRequired", $.validator.methods.required, "Please enter a Name.");
	$.validator.addMethod("startYearRequired", $.validator.methods.required, "Please enter a Name.");
	$.validator.addMethod("emailRequired", $.validator.methods.required, "Please enter a Email.");
	$.validator.addMethod("addressRequired", $.validator.methods.required, "Please enter a Address.");
	$.validator.addMethod("stateRequired", $.validator.methods.required, "Please enter a State.");
	$.validator.addMethod("postcodeRequired", $.validator.methods.required, "Please enter a Postcode.");
	$.validator.addMethod("phoneRequired", $.validator.methods.required, "Please enter a Phone.");
	$.validator.addMethod("sessionNameRequired", $.validator.methods.required, "Please select a Session Name.");
	$.validator.addMethod("sessionCodeRequired", $.validator.methods.required, "Please select a Session Code.");
	$.validator.addMethod("statusRequired", $.validator.methods.required, "Please enter a Status.");
	$.validator.addMethod("amountPaidRequired", $.validator.methods.required, "Please enter a Paid Amount.");
	$.validator.addMethod("recipientRequired", $.validator.methods.required, "Please enter a Recipient.");
	$.validator.addMethod("ccRecipientRequired", $.validator.methods.required, "Please enter a CC-Recipient.");
	$.validator.addMethod("bccRecipientRequired", $.validator.methods.required, "Please enter a BCC-Recipient.");
	$.validator.addMethod("subjectRequired", $.validator.methods.required, "Please enter a Subject.");
	$.validator.addMethod("messageRequired", $.validator.methods.required, "Please enter a Message.");
	$.validator.addMethod("routeRequired", $.validator.methods.required, "Please enter a Route.");
	$.validator.addMethod("pickUpStopRequired", $.validator.methods.required, "Please enter a Pick Up Stop.");
	$.validator.addMethod("pickUpTimeRequired", $.validator.methods.required, "Please enter a Pick Up Time.");
	$.validator.addMethod("vehicleRequired", $.validator.methods.required, "Please enter a Vehicle.");
	$.validator.addMethod("dropStopRequired", $.validator.methods.required, "Please enter a Drop Stop.");
	$.validator.addMethod("dropTimeRequired", $.validator.methods.required, "Please enter a Drop Time.");
	$.validator.addMethod("transportFeeRequired", $.validator.methods.required, "Please enter a Transport Fee.");
	$.validator.addMethod("receiptNoRequired", $.validator.methods.required, "Please enter a Receipt No.");
	$.validator.addMethod("bankBranchRequired", $.validator.methods.required, "Please enter Bank Branch.");
	$.validator.addMethod("bankNameRequired", $.validator.methods.required, "Please enter Bank Name.");
	$.validator.addMethod("chequeNoRequired", $.validator.methods.required, "Please enter a Cheque No.");
	
	// alias minlength, too
	//$.validator.addMethod("cMinlength", $.validator.methods.minlength, 
	// leverage parameter replacement for minlength, {0} gets replaced with 2
	//$.format("Customer name must have at least {0} characters"));
	// combine them both, including the parameter for minlength
	//$.validator.addClassRules("customer", { cRequired: true, cMinlength: 2 });
	$.validator.addClassRules({
		className: {
			classNameRequired: true
			 //messages: {required: "Please select Classssss"}
		},
		classCode: {
			classCodeRequired: true
			 //messages: {required: "Please select Classssss"}
		},
		sectionName: {
			sectionNameRequired: true
		},
		sectionCode: {
			sectionCodeRequired: true
		},
		type: {
			typeRequired: true
		},
		feeType: {
			feeTypeRequired: true
		},
		month: {
			monthRequired: true
		},
		resultType: {
			resultTypeRequired: true
		},
		admissionNo: {
			admissionNoRequired: true
		},
		rollNo: {
			rollNoRequired: true
		},
		date: {
			dateRequired: true
		},
		status: {
			statusRequired: true
		},
		fileType: {
			fileTypeRequired: true
		},
		bulkFile: {
			bulkFileRequired: true
		},
		paymentCategoryName: {
			paymentCategoryNameRequired: true
		},
		paymentCategory: {
			paymentCategoryRequired: true
		},
		paymentCategoryCode: {
			paymentCategoryCodeRequired: true
		},
		configCode: {
			configCodeRequired: true
		},
		configName: {
			configNameRequired: true
		},
		configValue: {
			configValueRequired: true
		},
		configDescription: {
			configDescriptionRequired: true
		},
		subjectName: {
			subjectNameRequired: true
		},
		subjectCode: {
			subjectCodeRequired: true
		},
		feeCategoryName: {
			feeCategoryNameRequired: true
		},
		feeCategoryCode: {
			feeCategoryCodeRequired: true
		},
		amount: {
			amountRequired: true,
			number: true
		},
		schoolCode: {
			schoolCodeRequired: true
		},
		name: {
			nameRequired: true
		},
		startYear: {
			startYearRequired: true
		},
		email: {
			emailRequired: true,
			email: true
		},
		address: {
			addressRequired: true
		},
		state: {
			stateRequired: true
		},
		postcode: {
			postcodeRequired: true
		},
		phone: {
			phoneRequired: true,
			number: true
		},
		sessionCode: {
			sessionCodeRequired: true
		},
		sessionName: {
			sessionNameRequired: true
		},
		status: {
			statusRequired: true
		},
		amountPaid: {
			amountPaidRequired: true,
			number: true
		},
		recipient: {
			recipientRequired: true
		},
		ccRecipient: {
			ccRecipientRequired: true
		},
		bccRecipient: {
			bccRecipientRequired: true
		},
		subject: {
			subjectRequired: true
		},
		message: {
			messageRequired: true
		},
		route: {
			routeRequired: true
		},
		pickUpStop: {
			pickUpStopRequired: true
		},
		pickUpTime: {
			pickUpTimeRequired: true
		},
		vehicle: {
			vehicleRequired: true
		},
		dropStop: {
			dropStopRequired: true
		},
		dropTime: {
			dropTimeRequired: true
		},
		transportFee: {
			transportFeeRequired: true,
			number: true
		},
		receiptNo: {
			receiptNoRequired: true
		},
		bankBranch: {
			bankBranchRequired: true
		},
		bankName: {
			bankNameRequired: true
		},
		chequeNo: {
			chequeNoRequired: true,
			number: true
		}
    });
	/*$.validator.addClassMessages({
		className: {
			 required: "Please select Class"
			 messages: {required: "Please select Class"}
		},
		sectionName: {
			 required: "Please select Section Name"
			 messages: {required: "Please select Section Name"}
		}
	});*/
	$('form.formValid').each(function(index){
		$(this).validate();
	});
	$(document).on('click', 'input[type=submit]:not(.cancel),input[type=button].formValidBtn, button.formValidBtn', function(e){
		var selectForm = $(this).closest('.formValid');
		var valid = $(selectForm).valid();
		if($(selectForm).hasClass('hasError')){
			$(selectForm).removeClass('hasError');
		}
		if(!valid){
			$(selectForm).addClass('hasError');
		}
	});
	//$('.formValid').validate();
	$('#testForm').submit(function () {
        //var validator = $('#testForm').validate();    
        //var valid = $('#testForm').valid();
        //var invalids = validator.numberOfInvalids();
        
        $('#validatorMessage').html('number of invalids: ' + invalids);        
        
         return false;
    });
	
	$('.searchAttForm1').validate({       
        // Specify the validation rules
        rules: {
            className: "required",
            sectionName: "required",
            status: "required"
        },        
        // Specify the validation error messages
        messages: {
            className: "Please select Class",
			sectionName: "Please select Section",
			status: "Please select a status"
        }
    });
	
	$('#addGlobalConfig').validate({       
        // Specify the validation rules
        rules: {
            configCode: "required",
            configValue: "required",
            configDescription: "required"
        },        
        // Specify the validation error messages
        messages: {
        	configCode: "Please enter Config Code",
        	configValue: "Please enter Config Value",
        	configDescription: "Please enter Config Description"
        }
    });
	
	$('#form2').validate({       
        // Specify the validation rules
        rules: {
            className: "required",
            sectionName: "required",
//            date: "required"
        },        
        // Specify the validation error messages
        messages: {
            className: "Please select Class",
			sectionName: "Please select Section",
//			date: "Please select a date"
        }
    });
	
	
	$('#dbBackup').validate({       
        // Specify the validation rules
        rules: {
            database: "required"
        },        
        // Specify the validation error messages
        messages: {
        	database: "Please select Database"
        }
    });
	
	$('#dbtbBackup').validate({       
        // Specify the validation rules
        rules: {
            database: "required",
            table: "required"
        },        
        // Specify the validation error messages
        messages: {
        	database: "Please select Database",
        	table: "Please select Table"
        }
    });
	
	
	$('#dbtbRestore').validate({       
        // Specify the validation rules
        rules: {
            database: "required",
            databaseFilee: "required"
        },        
        // Specify the validation error messages
        messages: {
        	database: "Please select Database",
        	databaseFilee: "Please select Table"
        }
    });
	
	
	
	
    $('.addStudent').validate({
    
        // Specify the validation rules
        rules: {
        	firstName:
            {
            	required:true
            },
            lastName:
			{
				required:true
			},
			gender: 
            {
            	required:true
            },
            dateOfBirth: 
            {
            	required:true
            },
            fatherFirstName: 
            {
            	required:true
            },
            fatherLastName: 
            {
            	required:true
            },
            fatherMobile: 
            {
            	required:true,
            	number: true,
            	minlength: 10,
            	maxlength: 10
            },
            fatherEmail: 
            {
            	required:true
            },
            fatherOccupation:
            {
            	required:true
            },
            motherOccupation:
            {
            	required:true
            },
            motherName: 
            {
            	required:true
            },
			admissionNo:
			{
				required:true
			},
			rollNo:
			{
				required:true
			},
			status:
			{
				required:true
			},
			datepicker:
			{
				required:true
			},
			age:
			{
				required:true
			},
			category:
			{
				required:true
			},
            studentEmail: 
            {
                required: true,
                email: true
            },
            className: 
            {
            	required:true
            },
            sectionName: 
            {
                required: true
            },
            parentAddress:
            {
                required: true
            },	
            username: "required",
            password: {
				required: true,
				minlength: 5
			},
			repassword: {
				required: true,
				minlength: 5,
				equalTo: "#password"
			},
            agree: "required"
        },
        
        // Specify the validation error messages
        messages: {
        	firstName: "Please enter your first name",
        	lastName: "Please enter your Last name",
        	gender: "Please select gender",
        	dateOfBirth: "Please select Date of birth",
        	fatherFirstName: "Please enter Father First Name",
        	fatherLastName: "Please enter Father Last Name",
        	fatherMobile: "Please enter Father Mobile",
        	fatherMobile: 
            {
            	required: "Please enter Father Mobile",
            	number: "Please enter only number",
            	minlength: "Mobile length should be 10 digit",
            	maxlength: "Mobile length should be max 10 digit"
            },
        	fatherEmail: "Please enter Father Email",
        	fatherOccupation: "Please enter Father Occupation",
        	motherOccupation: "Please enter Mother Occupation",
        	motherName: "Please enter Mother Name",
			admissionNo: "Please provide Adddmision No.",
			sectionName: "Please provide Section",
            className: "Please provide Class",
            studentEmail:
            {
            	required: "Please enter an email address",
            	email: "Please enter a valid email address"
            },
			rollNo: "Please provide Roll No",
			status: "Please select a status",
			category: "Please select a category",
			datepicker: "Please provide Date",
			age: "Please provide Age",
			parentAddress: "Please enter parent address",
        	username: "Select enter an Username",
        	password: {
				required: "Please enter a password",
				minlength: "Password should greter 5 digit"
			},
			repassword: {
				required: "Please enter a confirm password",
				minlength: "Password should greter 5 digit",
				equalTo: "Please enter same password"
			},
            agree: "Please accept our policy"
			
        }
    });
    
    $('.supadmn').validate({       
        // Specify the validation rules
        rules: {
            userRoleName: "required",
            userRoleId: "required",
            pactive: "required",
            username: "required",
            password: {
				required: true,
				minlength: 5
			},
			repassword: {
				required: true,
				minlength: 5,
				equalTo: "#password"
			},
            email: 
            {
                required: true,
                email: true
            },
            firstname: "required",
            lastname: "required",
            mobile: {
				required: true,
            	number: true,
            	minlength: 10,
            	maxlength: 10
			}
        },        
        // Specify the validation error messages
        messages: {
        	userRoleName: "Please enter Role Name",
        	userRoleId: "Please select User Role",
        	pactive: "Select an Active",
        	username: "Select enter an Username",
        	password: {
				required: "Please enter a password",
				minlength: "Password should greter 5 digit"
			},
			repassword: {
				required: "Please enter a confirm password",
				minlength: "Password should greter 5 digit",
				equalTo: "Please enter same password"
			},
            email:
            {
            	required: "Please enter an email address",
            	email: "Please enter a valid email address"
            },
            firstname: "Please enter a firstname",
            lastname: "Please enter a lastname",
            mobile: {
				required: "Please enter a mobile number",
            	number: "Please enter only number",
            	minlength: "Mobile length should be 10 digit",
            	maxlength: "Mobile length should be max 10 digit"
			},
        }
    });
    
    
    $('#updatePassword').validate({       
        // Specify the validation rules
        rules: {
            password: {
				required: true,
				minlength: 5
			},
			repassword: {
				required: true,
				minlength: 5,
				equalTo: "#password"
			}
        },        
        // Specify the validation error messages
        messages: {
        	password: {
				required: "Please enter a password",
				minlength: "Password should greter 5 digit"
			},
			repassword: {
				required: "Please enter a confirm password",
				minlength: "Password should greter 5 digit",
				equalTo: "Please enter same password"
			}
        }
    });
    
    
    

});
    