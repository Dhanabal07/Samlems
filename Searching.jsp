<!DOCTYPE html>
<html>
<head>
	<title>Searching</title>
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
		<h2>Searching Records</h2>
	</div>
	<form id="form" class="form" action="search" method="get" >
		<div class="form-control">
			<label for="column_name">Column name </label>
			<select class ='column'id="column_name" name="column_name">
				<option value="name">Name</option>
				<option value="empid">EmployeeId</option>
				<option value="username">Username</option>
				<option value="mobile">Mobile</option>
				<option value="mail">Mail</option>
				<option value="department">Department</option>
				<option value="company">Company</option>
		    </select>
		</div>

					<div class=" form-control string-search" id="string_type">
						<label for="string">Searching type</label>
						<select class ='column' name="search_option_string" id="string">
							<option value="contains">Contains</option>
							<option value="notContains">Not Contains</option>
							<option value="equal">Equal</option>
							<option value="startswith">Starts With</option>
							<option value="endswith">Ends With</option>
					    </select>
					</div>


					<div class="form-control string-search" id="string_type_values">
						<label for="string_values">Enter the value</label>
						<input type="text" placeholder="Enter your string value" id="string_value" name="string_value" />
					</div>

		<div >
			<input class="button" type="submit" name="submit" value="search">
		</div>
	</form>
</div>
</body>
</html>