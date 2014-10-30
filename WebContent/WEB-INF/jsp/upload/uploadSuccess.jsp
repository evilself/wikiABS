<!-- html>
<head>
    <title>Spring MVC Multiple File Upload</title>
</head>
<body>
    <h1>Spring Multiple File Upload example</h1>
    <p>Following files are uploaded successfully.</p>
    <ol>
        
            <li><a target="blank" href="display/${files.id}" >${files.actualFilename}</a></li>
       
    </ol>
</body>
</html-->

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script>

$('#largeModal').on('hidden.bs.modal', function() {
	$(this).removeData('bs.modal')
});

</script>
 
  		<div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
            <h4 class="modal-title" id="myModalLabel">Success</h4>
            </div>
            <div class="modal-body">
                files are uploaded successfully
                <ol>
        
			            <li><a target="blank" href="display/${files.id}" >${files.actualFilename}</a></li>
			       
			    </ol>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>                
          </div>
 

