<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script>

$('#largeModal').on('hidden.bs.modal', function() {
	$(this).removeData('bs.modal')
});

</script>
 
  <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
            <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
                <form:form method="post" action="upload/save"
        modelAttribute="uploadForm" enctype="multipart/form-data">
 
		    <p>Select files to upload. Press Add button to add more file inputs.</p>
		 
		    <input id="addFile" type="button" value="Add File" />
		    <table id="fileTable">
		        <tr>
		            <td><input name="file" type="file" /></td>
		        </tr>
		        <tr>
		            <!-- td><input name="files[1]" type="file" /></td-->
		        </tr>
		    </table>
		    <br/><input type="submit" value="Upload" />
		</form:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>                
          </div>
 

