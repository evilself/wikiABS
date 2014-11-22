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

function ajaxLogin(){
    $('#result').html('');
    if(($('#username').val() != "") && ($('#password').val() != "")) {	          
        $.ajax({
        	url:  "/wikiABS/ajaxLogin",
	        type: "POST",
	        data: $("#loginForm").serialize(),
	        success: function(data){
	        	
	        	if (data.indexOf("Success") < 0) {
	        		$('#result').html("Login failed, please try again!").css({"color":"#FFE6E6", "font-size":"1.5em"});
	        		$('#username, #password').val("").css({"border-color":"#FFE6E6"});		        		
	        		
	            } else {		            	
	            	$('#result').html("Login successful!").css({"color":"#CCFF99", "font-size":"1.5em"});
	            	$('#username, #password').css({"border-color":""});
	            	setTimeout(function() { window.location.reload(true); }, 1000);
	            }		                       
	        }		       
	    });	
    } else {	        	
    	 $('#result').html('Please provide both username and password!').css({"color":"#FFE6E6", "font-size":"1.2em"});	        	
    }
}	

$('#loginForm').keypress(function(event){			 
	var keycode = (event.keyCode ? event.keyCode : event.which);
	if(keycode == '13'){
		$('#ajaxSubmitBtn').click();
	}		 
});	

function confirmDel(e) {
 	e.preventDefault();
	swal({
		  title: "Are you sure?",
		  text: "Your will not be able to recover this article and all associated attachments!",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonColor: "#DD6B55",
		  confirmButtonText: "Yes, delete it!"
		},
		function(){    			  
			$('#'+$(e.target).parent()[0].id).submit();
		});    	 	
}

function deleteAttachment(el) {			
	//e.preventDefault();
	swal({
		  title: "Are you sure?",
		  text: "Your will not be able to recover this attachment!",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonColor: "#DD6B55",
		  confirmButtonText: "Yes, delete it!"
		},
		function(){   
			//var formID = $(e.target).parent()[0].id;
			
			$.ajax({
	        	url:  "/wikiABS/upload/delete/" + el,
		        type: "delete",
		        //data: $("#loginForm").serialize(),
		        success: function(data){
		        	
		        	if (data.indexOf("Error") > -1) {
		        		$('#result').html("Oops...Error occured while deleting!").css({"color":"#FFE6E6", "font-size":"1.5em"});
		            } else {  	   			            	
		            	//setTimeout(function() { window.location.reload(true); }, 1000);    			            	
		            	var res = JSON.parse(data);     			            	
		            	$('#'+res.identity).remove();
		            	$('#count').html(res.count);
		            }		                       
		        }		       
		    });	
			
		});
	
}	

function confirmProductDel(e) {
 	e.preventDefault();
	swal({
		  title: "Are you sure?",
		  text: "Your will not be able to recover this product!",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonColor: "#DD6B55",
		  confirmButtonText: "Yes, delete it!"
		},
		function(){     				
		  $('#'+$(e.target).parent()[0].id).submit();
		});
 	
}

function confirmUserDel(e) {
 	e.preventDefault();
	swal({
		  title: "Are you sure?",
		  text: "Your will not be able to recover this user!",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonColor: "#DD6B55",
		  confirmButtonText: "Yes, delete it!"
		},
		function(){     				
		  $('#'+$(e.target).parent()[0].id).submit();
		});    	 	
}