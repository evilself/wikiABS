<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>

$('#loginModal').on('hidden.bs.modal', function() {
	$(this).removeData('bs.modal')
});


</script>
 
  <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
        <h4 class="modal-title" id="myModalLabel">Login</h4>
        </div>
        <div class="modal-body">
            <c:url var="loginUrl" value="/j_spring_security_check"></c:url>
 	  <form action="${loginUrl}" method="POST">
       <table>
           <tr>
               <td>Username:</td>
               <td><input type='text' name='username' /></td>
           </tr>
           <tr>
               <td>Password:</td>
               <td><input type='password' name='password' /></td>
           </tr>
           <tr>
               <td colspan='2'><input name="submit" type="submit"
                   value="Login" /></td>
           </tr>
       </table>
   </form>
     <button onclick="ajaxLogin()" >Ajax Login</button>		      
        </div>
        <div class="modal-footer">
        	<div id="result"></div>
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>                
      </div>
 

