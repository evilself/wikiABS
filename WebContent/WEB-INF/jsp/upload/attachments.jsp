<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="form-group">
    <label class="pull-left" id="attachmentCount" for="tag">Attachments [${fn:length(attachments)}]</label>					                  
    <c:forEach items="${attachments}" var="att">							            
   	<div class="col-lg-12 col-sm-12 " style="overflow:auto; margin-bottom:5px;">
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