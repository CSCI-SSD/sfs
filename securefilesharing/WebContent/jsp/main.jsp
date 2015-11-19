<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html >
  <head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
	
  </head>

  <body>
    <div id="header">
		<h1>Secure File Sharing</h1>
		<div align="right">${login.email }</div>
	</div>

	<div class="menu-form" align="center">
		<table class="tab-menu-group" width="98%">
		<tr>
			
			<td class="tab active" width="14%"><a href="#Upload" >Upload</a></td>
			
			<td class="tab" width="14%"><a href="#profile" >Profile</a></td>
			
			<td class="tab" width="20%"><a href="#signup" >Secure Sahre</a></td>
			
			<td class="tab" width="20%"><a href="#signup">Shared With Me</a></td>
			
			<td class="tab" width="14%"><a href="#signup">Messages</a></td>
			
			<td class="tab" width="14%"><a href="#logout">logout</a></td>
			</tr>
		</table>
	</div>
	
	<div class="form-content " >
	 <div class="tab-content">
	     
		 
		 
		 <div id="Upload">   
          <h1>Upload File</h1>
          <form action="/" method="post">
             <table align="center">
				<tr>
					<td><label>Browse<span class="req">*</span></label> </td>
					<td><input name="fileName" type="file" required autocomplete="off" /></td>
				</tr>
				
				 <tr>
					<td colspan="2">
						<button type="submit" class="button button-block"/>Upload</button>
					</td>
				 </td>
			</table>
          </form>
		  <br><br><br>
        </div>
		 
		 <div id="profile">   
          <h1>Profile</h1>
          <form action="/" method="post">
             <table align="center">
				<tr>
					<td><label>First Name<span class="req">*</span></label> </td>
					<td><input name="firstName" type="text" required autocomplete="off" /></td>
				</tr>
				<tr>
					<td><label>Last Name<span class="req">*</span></label></td>
					<td> <input name="lastName" type="text"required autocomplete="off"/></td>
               
				</tr>
				<tr>
					 <td><label>Email Address<span class="req">*</span></label></td>
					 <td><input name="email" type="email"required autocomplete="off"/></td>
			     </tr>
				 <tr>
					 <td><label>Enter Password<span class="req">*</span></label></td>
					 <td><input name="password" type="password"required autocomplete="off"/></td>
				 </tr>
				 <tr>
					 <td><label>Re-enter Password<span class="req">*</span></label></td>
					 <td><input name="repassword" type="password"required autocomplete="off"/></td>
				 </tr>
				 <tr>
					<td colspan="2">
						<button type="submit" class="button button-block"/>Get Started</button>
					</td>
				 </td>
			</table>
          </form>
		  <br><br><br>
        </div>
		
		
		
		
		 <div id="logout">
			<h1>Confirm Logout</h1>
			  <form action="logout.view" method="post">
				 <table align="center">
					 <tr>
						 <td><button type="submit" class="button button-block" />LogOut</button></td>
					</tr>
				 </table>
			  </form>
			  <br><br><br>
		 </div>
		
	 </div>
	</div>
   
    <div id="footer"> Copyright © Capstone 8910 </div>
	
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    <div id="footer"> Copyright © Capstone 8910 </div>
	
  </body>
</html>