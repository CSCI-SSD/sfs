<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
		<div align="right">${LOGIN_BEAN.lastName }</div>
	</div>

	<div class="menu-form" align="center">
		<table class="tab-menu-group" width="98%">
		<tr>
			
			<td class="tab active" width="14%"><a href="#Upload" >Upload</a></td>
			
			<td class="tab" width="14%"><a href="#profile" >Profile</a></td>
			
			<td class="tab" width="20%"><a href="secureshare.view" >Secure Sahre</a></td>
			
			<td class="tab" width="20%"><a href="#sharedWithMe">Shared With Me</a></td>
			
			<td class="tab" width="14%"><a href="#signup">Messages</a></td>
			
			<td class="tab" width="14%"><a href="#logout">logout</a></td>
			</tr>
		</table>
	</div>
	
	<div class="form-content " >
	 <div class="tab-content">
	     
		 
		 
		 <div id="Upload">   
          <h1>Upload File</h1>
          
          <form action="upload.view" method="post" enctype="multipart/form-data" > 
             <table align="center">
				<tr>
					<td><label>Browse<span class="req">*</span></label> </td>
					<td><input type="file" name="file" required autocomplete="off" /></td>
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
        
        <div id="secureShare">   
        	<br>
             <table align="center"  border="1" width="100%">
				<tr>
					<td class="colHeder"><label>File Name</label> </td>
					<td class="colHeder"><label>Shared With</label></td>
					 <td class="colHeder"><label>Add</label></td>
					<td class="colHeder"><label>Delete user</label></td>
					<td class="colHeder"><label>Delete File</label></td>
				 </tr>
				 <tr>
					<td><label>Staples.pdf</label> </td>
					<td><label>kpuchakaya@unomaha.edu<br></label></td>
					 <td><label>Add</label></td>
					<td><label>Delete</label></td>
					<td><label>Delete</label></td>
				 </tr>
				 <c:forEach var="document" items="${doclist}">
					<c:set var="count" value="${count+1}"/>
					<c:choose>
						<c:when test="${count % 2 == 0}">
							<tr class="even" id="row<c:out value='${count}'/>">
						</c:when>
						<c:otherwise>
							<tr class="odd" id="row<c:out value='${count}'/>">
						</c:otherwise>
					</c:choose>
						<td><span id="typeofcrime${count}"><c:out value="${document.fileName}"/></span></td>
						<td><span id="crimedate${count}"><c:out value="${document.sharedWith}"/></span></td>
						<td>Add</td>
						<td>Delete</td>
						<td>Delete</td>
					</tr>
				</c:forEach>
			</table>
        </div>
		
		  <div id="sharedWithMe">   
        	<br>
             <table align="center"  border="1" width="100%">
				<tr>
					<td class="colHeder"><label>File Name</label> </td>
					<td class="colHeder"><label>Shared By</label></td>
				 </tr>
				 <tr>
					<td><label>Staples.pdf</label> </td>
					<td><label>kpuchakaya@unomaha.edu<br></label></td>
					 
				 </tr>
			</table>
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
	
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    <div id="footer"> Copyright © Capstone 8910 </div>
	
  </body>
</html>
