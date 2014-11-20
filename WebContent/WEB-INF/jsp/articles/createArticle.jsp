<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
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
    <!-- Custom CSS for the main page-->
    <link href="<spring:url value="/resources/css/landing-page.css"/>" rel="stylesheet">
    <link href="<spring:url value="/resources/css/bootstrap-combobox.css"/>" rel="stylesheet">
    <!-- SweetAlert-->
    <link href="<spring:url value="/resources/css/sweet-alert.css"/>" rel="stylesheet">
	<link rel="icon" href="/favicon.ico" type="image/x-icon" />

    <!-- Custom Fonts -->
    <link href="<spring:url value="/resources/font-awesome-4.1.0/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
     <style> .invalid { color: red; }</style>
</head>

<body id="articleBody">	
	<div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="background: url(<spring:url value="/resources/img/wall_two.jpg"/>) no-repeat center center;height:300px;">
           
    	</div>
    </div>
	</div>
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <img class="img-responsive navbar-brand" src="<spring:url value="/resources/img/newEagle.jpg"/>" alt=""></img>
                <a class="navbar-brand" target="_blank" href="http://www.americanbanksystems.com">Welcome to American Bank System's knowledge pool!</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="/">Home</a>
                    </li>
                    <li>
                        <a href="/wikiABS/articles">Articles</a>
                    </li> 
                    <c:if test="${admin == 'true'}">                   
					    <li>
	                        <a href="products">Products</a>
	                    </li>
	                    <li>
	                        <a href="users">Users</a>
	                    </li>
					</c:if>                    
                    <c:if test="${loggedUser != null}">
                     <li>
                        <a style="color:purple;cursor:pointer;" id="logoutLink" onclick="confirmLogout(event);" >Logout</a>
                    </li>
                    </c:if>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Header -->
    <div class="intro-header">

        <div class="container">        
	       <div class="row">
                <div class="col-lg-12">               
                    <div class="intro-message" style="padding-top: 10%; padding-bottom: 10%;">      
                    	<h2>Create/Edit Article</h2>              	
                        <sf:form method="post" id="createArticleForm" modelAttribute="article" action="${action}">
				           <div class="col-lg-12 col-sm-12">
				           		<div class="form-group">
				                    <label class="pull-left" for="title">Title</label>
				                    <input class="form-control form-inline pull-right" name="title" id="title" type="text" value="${article.title}"/>
				                </div>
				                <div class="form-group" style="padding:1px;">				                	
					                <label class="pull-left" for="description">Description</label>
					                <label style="margin-top:6px" class=" pull-right">
					                    <a class="btn btn-sm btn-default" style="background-color: #CCFF99" href="${upload}"
										   data-toggle="modal"
										   data-target="#uploadModal">Upload
										 </a>
					                    </label>
				                    <!-- textarea class="form-control" style="height:400px" name="description" class="form-control" id="description" value="${article.description}"/-->
				                	<textarea id="description" name="description" value="${article.description}" style="height:400px" class="form-control">${article.description}</textarea>
				                </div>
				                <div class="form-group pull-left" style="width:58%">
				                    <label class="pull-left" for="tag">Tags</label>
				                    <input class="form-control form-inline pull-right" name="tag" id="tag" type="text" class="form-control" value="${article.tag}"/>
				                </div>
				                <input name="product" style="visibility:hidden;height:1px;width:1px;margin-top:2px;" id="product" type="text" value="${article.product.id}"/> 
				                <div class="text-left form-group pull-right" style="width:38%" > 
						        	<label class="text-left">Associated Product</label> <label id="product-error" class="invalid pull-right" style="display:none;" for="product">product is missing</label>                 	
		                        	<select id="productType" name="productType" class="form-control combobox" >					                	
						                <c:forEach items="#{products}" var="prod">							                						      
									      	<option  name="${prod.id}" value="${prod.id}">${prod.productName}</option>										     			    
									    </c:forEach>
								    </select>
							    </div>
							    <div id="ajaxResponse">
								    <div class="form-group">
					                   <label class="pull-left" id="attachmentCount" for="tag">Attachments [<span id="count">${fn:length(attachments)}</span>]</label>					                  
					                   <c:forEach items="${attachments}" var="att">							            
							            	<div id="${att.id }" class="col-lg-12 col-sm-12 " style="overflow:auto; margin-bottom:5px;">
							            		<div class="col-lg-4 text-right"><label>${att.name}</label>	</div>		                
							                	<div class="col-lg-8 col-sm-8">
							                		<div class="col-lg-2 col-sm-2 text-right">
								                    	<a target="_blank" class="btn btn-info" style="padding-top:1px; padding-bottom: 1px; background-color:#C9C9D5; color:#0066CC; border-color:#C9C9D5" href="/wikiABS/upload/display/${att.id}">View</a>
								               		</div>
								               		 <div class="col-lg-2 col-sm-2 text-left">								                    
									                    <button type="button" class="btn btn-warning" style="padding-top:1px; padding-bottom: 1px; color:#0066CC; border-color:#C9C9D5" onclick="deleteAttachment(${att.id});">Delete</button>
									                </div>
									                <div id="result"></div>									               		
								                </div>
								                </div>					             
							        	</c:forEach>							        						              
					                </div>
							   	</div>	                
				                <a class="btn btn-info pull-right" style="padding-top:1px; padding-bottom: 1px; background-color:#FFE6E6; color:#0066CC; border-color:#C9C9D5" href="/wikiABS/articles/${article.id}">Cancel</a> 				                
				           		<input style="margin-right:5px; padding-top:1px; padding-bottom: 1px; background-color:#C9C9D5; color:#0066CC; border-color:#C9C9D5" class="btn btn-info pull-right" type="submit" value="Save" id="save" />
				           </div>
				        </sf:form>
				        	
                        <!-- h3>What are you looking for?</h3 -->
                        <hr class="intro-divider">                       
                    </div>
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>
    <!-- /.intro-header -->   

    <!-- Footer -->
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <ul class="list-inline">
                        <li>
                            <a href="/">Home</a>
                        </li>                        
                        <li class="footer-menu-divider">&sdot;</li>
                        <li>
                            <a href="articles">Articles</a>
                        </li>
                        <li class="footer-menu-divider">&sdot;</li>
                        <li>
                            <a href="#about">About</a>
                        </li>
                    </ul>
                    <p class="copyright text-muted small">Copyright &copy; American Bank Systems Inc 2014. All Rights Reserved</p>
                </div>
            </div>
        </div>
    </footer>

    <!-- jQuery Version 1.11.0 -->
    <script src="<spring:url value="/resources/js/jquery-1.11.0.js"/> "></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<spring:url value="/resources/js/bootstrap.min.js"/> "></script>
    
    <!-- Bootstrap Core JavaScript -->
    <script src="<spring:url value="/resources/js/bootstrap-combobox.js"/> "></script>
    
    <!-- Bootstrap Core JavaScript -->
    <script src="<spring:url value="/resources/js/sweet-alert.min.js"/> "></script>
    
    <!-- jQuery Validation -->    
    <script src="<spring:url value="/resources/js/jquery.validate.min.js"/> "></script>
    
    <script>
 	// When the browser is ready...
	/*$(function() {  	
		
	    // Setup form validation on the #register-form element
	    $("#createArticleForm").validate({
	    	errorClass: 'invalid',
	        // Specify the validation rules
	        rules: {
	        	title: "required",
	        	description: "required"	,
	        		tag: "required",
	        		product:  "required"
	        },
	        
	        // Specify the validation error messages
	        messages: {
	        	title: "title is missing",
	        	description: "description is missing",
	        	tag: "tag is missing",
	        	product: "product is missing"
	        },
	        
	        submitHandler: function(form) {
	        	//alert($('#product').val());
	        	form.submit();
	        }
	    });
	  });*/
    
    
    	function confirmLogout(e) {
    	 	
    		swal({
    			  title: "Are you sure?",    			  
    			  type: "warning",
    			  showCancelButton: true,
    			  confirmButtonColor: "#DD6B55",
    			  confirmButtonText: "Yes, log me out!"
    			},
    			function(){    			  
    			  window.location="/wikiABS/logout";
    			});
    	 	
    	}
   
    </script>

	<script>
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
	</script>

	 <script>
	      $(document).ready(function(){
	        //$('.combobox').combobox();
	        //alert($("#product").attr("c"));
	        $(".combobox").val($("#product").val());
	        
	      });
	      
	      $('#productType').on('change' , function(){
	       //alert($('#productType').val());
	     	$('#product').val($('#productType').val());
	     	if($('#product').val() != "") $('#product-error').hide();	     	
	      });	     
    </script>

</body>

</html> 
