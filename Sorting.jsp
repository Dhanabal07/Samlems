<!DOCTYPE html>
<html>
<head>
	<title>Sorting</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	
</head>
<body>
	<div class="container">
	<%
		String username = (String) session.getAttribute("username");
		if(username==null)
		{
			response.sendRedirect(request.getContextPath()+"/login.html");
		}
	%>
	<div class="header">
		<h2>Sorting Records</h2>
	</div>

	<form id="form" class="form" action="sorting" method="get" >
		<div class="form-control">
			<label for="column_name">Column name</label>
			<select class ='column' id="column_name" name="column_name" >
				<option value="name">Name</option>
				<option value="empid">EmployeeId</option>
				<option value="username">Username</option>
				<option value="mobile">Mobile</option>
				<option value="mail">Mail</option>
				<option value="department">Department</option>
				<option value="company">Company</option>
		    </select>
		</div>

		<div class="form-control-gender" >
			<label for="order"> Sorting order :- </label>
			<input type="radio" name="order" id="order" value="asec" required >Ascending
			<input type="radio" name="order" id="order" value="desc" required>Descending  
			
		</div>



		<div >
			<input class="button" type="submit" name="submit" value="Sort">
		</div>
	</form>
</div>
</body>
</html>