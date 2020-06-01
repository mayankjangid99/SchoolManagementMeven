<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
input[type="text"]
{
	height:17px;
}
select
{
	height: 23px;
}
td
{
	font-family: cursive;
}
</style>

<script type="text/javascript">
$(window).load(function(){
	  $('.wrapper').removeClass();
});
        function go(){
		var sel = document.getElementById("protection");
        var sv = sel.options[sel.selectedIndex].value;
		//alert(sv);
             if(sv=='yes')
            	 {
            	 	document.getElementById('ownerpwd').disabled=false;
            	 	document.getElementById('userpwd').disabled=false;
            	 	document.getElementById('ownerpwd').required=true;
            	 	document.getElementById('userpwd').required=true;            	 	   	 	
            	 }
             else
            	 {
	            	 document.getElementById('ownerpwd').disabled=true;
	         	 	 document.getElementById('userpwd').disabled=true;
	         	 	 document.getElementById('ownerpwd').required=false;
	         	 	 document.getElementById('userpwd').required=false;
            	 }
          }
        function wo(){
    		var sel = document.getElementById("filetype");
            var sv = sel.options[sel.selectedIndex].value;
    		//alert(sv);
                 if(sv=='pdf')
                	 {
                	 	document.getElementById('protection').disabled=false;
                	 	document.getElementById('protection').required=true;              	 	   	 	
                	 }
                 else
                	 {
    	            	 document.getElementById('protection').disabled=true;
    	         	 	 document.getElementById('protection').required=false;
                	 }
              }

          </script>
</head>
<body><br><br>
<form action="GenerateFile" method="post">
<table align="center" width="40%" cellspacing="15px;" bgcolor="#fdf">
	<tr>
		<td colspan="2" style="text-align: center;color: green;"><h2>Jasper File | xls | csv | xml | docx | html</h2></td>
	</tr>
	<tr>
		<td>Select Drive</td>
		<td><select name="drive" required="required">
				<option value="">-- Select Drive --</option>
				<option value="c:/">C</option>
				<option value="d:/">D</option>
			</select> 
		</td>
	</tr>
	<tr>
		<td>Select File Type</td>
		<td><select name="filetype" id="filetype" required="required" onchange="wo()">
				<option value="">-- Select Type --</option>
				<option value="pdf">PDF</option>
				<option value="xls">Excel</option>
				<option value="csv">CSV</option>
				<option value="xml">XML</option>
				<option value="docx">DOCX</option>
				<option value="html">HTML</option>
			</select> 
		</td>
	</tr>
	<tr>
		<td>Password Protected</td>
		<td><select name="protection" id="protection" onchange="go()" disabled="disabled">
				<option value="">-- Select Protection --</option>
				<option value="yes">Yes</option>
				<option value="no">No</option>
			</select> 
		</td>
	</tr>
	<tr>
		<td>Owner Password</td>
		<td><input type="text" name="ownerpwd" id="ownerpwd" disabled="disabled" />
		</td>
	</tr>
	<tr>
		<td>User Password</td>
		<td><input type="text" name="userpwd" id="userpwd" disabled="disabled" />
		</td>
	</tr>
	<tr>
		<td>File Name</td>
		<td><input type="text" name="filename" required="required" />
		</td>
	</tr>
	<tr>
		<td>Name</td>
		<td><input type="text" name="name" required="required" />
		</td>
	</tr>
	<tr>
		<td>Address</td>
		<td><input type="text" name="address" required="required" />
		</td>
	</tr>
	<tr>
		<td colspan="2" style="text-align: center;"><input type="submit" value=" Generate File " /></td>
	</tr>
</table><br><br>
</form>
<center><a href="graphGraph">Graph Generate >>></a></center>
</body>
</html>