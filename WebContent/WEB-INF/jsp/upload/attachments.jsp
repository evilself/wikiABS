<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="form-group">
    <label class="pull-left" id="attachmentCount" for="tag"><spring:message code="article.attachments"/> [${fn:length(attachments)}]</label>					                  
    <c:forEach items="${attachments}" var="att">							            
   	<div class="col-lg-12 col-sm-12 " style="overflow:auto; margin-bottom:5px;">
   		<div class="col-lg-4 text-right"><label>${att.name}</label>	</div>		                
       	<div class="col-lg-8 col-sm-8">
      		<div class="col-lg-2 col-sm-2 text-right">
           	<a target="_blank" class="btn commonButton btn-info" href="${pageContext.request.contextPath}/upload/display/${att.id}"><spring:message code="attachment.viewbutton"/></a>
      		</div>
      		 <div class="col-lg-2 col-sm-2 text-left">								                    
            <button type="button" class="btn commonButton btn-warning" onclick="deleteAttachment(${att.id});"><spring:message code="attachment.deletebutton"/></button>
        </div>
        <div id="result"></div>									               		
       </div>
    </div>					             
	</c:forEach>							        						              
</div>