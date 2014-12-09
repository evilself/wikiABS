<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Wiki Page/Knowledge base">
    <meta name="author" content="BorisM">
    <title>ABS Wiki</title>
    
    <!-- Bootstrap Core CSS -->
    <link href="<spring:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    
    <!-- Template CSS -->
    <link href="<spring:url value="/resources/css/landing-page.css"/>" rel="stylesheet">
    
    <!-- FAVICON -->
	<link rel="icon" href="/favicon.ico" type="image/x-icon" />
	
	<!-- SweetAlert CSS. This is a superb custom alert popup -->
    <link href="<spring:url value="/resources/css/sweet-alert.css"/>" rel="stylesheet">
    
    <!-- WikiABS custom CSS classes -->
    <link href="<spring:url value="/resources/css/custom.css"/>" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<spring:url value="/resources/font-awesome-4.1.0/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="col-lg-12 col-sm-12">
		<div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
	        <h4 class="modal-title" id="myModalLabel">Upload</h4>
        </div>
        <div class="modal-body">
        	<c:if test="${article.id == null }">
	            <form method="post" id="uploadForm" action="/wikiABS/upload/ajax" enctype="multipart/form-data">
					<div class="form-group col-lg-offset-2">
			        	<label>Select file to upload</label>
			            <input name="file" id="file" type="file" class="form-control" style="width:80%" />		                       
		            </div>		   
			     </form>
		     </c:if>
		     <c:if test="${article.id != null }">
	            <form method="post" id="uploadForm" action="/wikiABS/upload/ajaxUpload/${article.id}" enctype="multipart/form-data">
					<div class="form-group col-lg-offset-2">
			        	<label>Select file to upload</label>
			            <input name="file" id="file" type="file" class="form-control" style="width:80%" />		                       
		            </div>		   
			     </form>
		     </c:if>
		     <!-- button onclick="uploadFormData()" >Upload</button><i>Using FormData Object</i-->
        </div>
        <div class="modal-footer">
        	<div id="result" class="pull-left"></div>        	
            <button type="button" style="margin-right:17%;" class="btn btn-warning pull-right" data-dismiss="modal">Close</button> 
            <button id="ajaxUploadBtn" class="btn btn-default pull-right" style="background-color:#C9C9D5; color:#0066CC; border-color:#C9C9D5; margin-right:1%;" onclick="uploadJqueryForm()" >Upload</button>               
        </div>           
	</div>	     
     
    <!-- jQuery Version 1.11.0 -->
    <script src="<spring:url value="/resources/js/jquery-1.11.0.js"/> "></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/> "></script>
    
    <!-- jQuery Form plugin -->
    <script src="<spring:url value="/resources/js/jquery.form.min.js"/> "></script>
    
	<script>
		$('#uploadForm').on('hidden.bs.modal', function() {
			//$(this).removeData('bs.modal')
		});
	</script>
	
	<script>
		$('#uploadForm').keypress(function(event){			 
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if(keycode == '13'){
				$('#ajaxUploadBtn').click();
			}		 
		});	
		
		
	  //using jquery.form.js
	    function uploadJqueryForm(){
	        $('#result').html('');
	        if ($('#file').val() != "") {
		        $("#uploadForm").ajaxForm({
		        success:function(data) {
		        	  $('#file').val('');
		        	 // swal("Good job!", data+" uploaded!", "success");
		        	 //alert(data);
		        	  $('#ajaxResponse').html(data);
		              $('#result').html('Attachment uploaded!').css({"color":"#CCFF99","font-size":"1.2em"});
		         },
		         dataType:"text"
		        }).submit();     
	        } else {
	        	$('#result').html('Please select a file!').css({"color":"#FFE6E6","font-size":"1.2em"});
	        }
	    }	  
	  
	  
	  //using FormData() object
	    function uploadFormData(){
	        $('#result').html('');
	     
	      var oMyForm = new FormData();
	      oMyForm.append("file", file.files[0]);
	     
	      $.ajax({
	        url: '/wikiABS/upload/ajax',
	        data: oMyForm,
	        dataType: 'text',
	        processData: false,
	        contentType: false,
	        type: 'POST',
	        success: function(data){
	          $('#result').html(data);
	        }
	      });
	    }		
	</script>
</body>
</html>
 

