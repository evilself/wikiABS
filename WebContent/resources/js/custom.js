// Author: BorisM
// Date: 11.27.2014
// Custom JavaScript library for wikiABS **********************************************************

//Logout JS - depends on SweetAlert.js
function confirmLogout(e) {    	 	
    swal({
	  title: "Are you sure?",    			  
	  type: "warning",
	  showCancelButton: true,
	  confirmButtonColor: "#DD6B55",
	  confirmButtonText: "Yes, log me out!"
	},
	function(){    			  
	  window.location="logout";
	});    	 	
}

// Check for matching passwords JS

var match = false;

function checkPass()
{			
    //Store the password field objects into variables ...
    var pass1 = document.getElementById('rpassword');
    var pass2 = document.getElementById('rnewPassword');
    //Store the Confimation Message Object ...
    var message = document.getElementById('result');
    //Set the colors we will be using ...
    var goodColor = "#CCFF99";
    var badColor = "#FFE6E6";
    //Compare the values in the password field
    //and the confirmation field
    if(pass2.value != "") {
	    if(pass1.value == pass2.value){
	        //The passwords match.
	        //Set the color to the good color and inform
	        //the user that they have entered the correct password
	        pass2.style.backgroundColor = goodColor;
	        message.style.color = goodColor;
	        match=true;
	        message.innerHTML = "Passwords Match!"
	    }else{
	        //The passwords do not match.
	        //Set the color to the bad color and
	        //notify the user.
	        pass2.style.backgroundColor = badColor;
	        message.style.color = badColor;
	        match=false
	        message.innerHTML = "Passwords do not match!"
	    }
    }
}


$('#registerForm').keypress(function(event){			 
	var keycode = (event.keyCode ? event.keyCode : event.which);
	if(keycode == '13'){
		$('#ajaxSubmitBtn').click();
	}		 
});		


function ajaxRegister(){
    $('#result').html('');
    if( ($('#firstName').val() != "") && ($('#lastName').val() != "") && ($('#userName').val() != "") && ($('#password').val() != "") && ($('#newPassword').val() != "")) {
        if(match) {	          
	        $.ajax({
	        	url:  "/wikiABS/ajaxRegister",
		        type: "POST",
		        data: $("#registerForm").serialize(),
		        success: function(data){
		        	
		        	if (data.indexOf("Success") < 0) {
		        		$('#result').html("Register failed, please try again!").css({"color":"#FFE6E6", "font-size":"1.5em"});
		        		$('#username, #password').val("").css({"border-color":"red"});		        		
		        		
		            } else {		            	
		            	$('#result').html("Registered! Please log in with your credentials!").css({"color":"#CCFF99", "font-size":"1.5em"});
		            	$('#username, #password').css({"border-color":""});
		            	setTimeout(function() { window.location.reload(true); }, 1000);
		            }		                       
		        }		       
		    });	
        } else {	        	
        	$('#result').html('Passwords do not match!').css({"color":"#FFE6E6", "font-size":"1.2em"});	        	
        }
    } else {
    	$('#result').html('Please provide all information!').css({"color":"#FFE6E6", "font-size":"1.2em"});	        	
    }
}

//When the browser is ready...
$(function() {
    // Setup form validation on the #register-form element
    $("#loginForm").validate({
    	errorClass: 'invalid',
        // Specify the validation rules
        rules: {
            username: "required",
            password: "required"		            
        },
        
        errorPlacement: function(error, element) {
        	$(error).css({'margin-left':'360px'});        	
            error.insertBefore(element);
        },
        
        // Specify the validation error messages
        messages: {
            username: "Username is missing",
            password: "Password is missing"		            
        },
        
        submitHandler: function(form) {
        	form.submit();
        }
    });
  });		