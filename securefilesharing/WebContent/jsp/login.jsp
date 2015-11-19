<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html >
	<head>
		<meta charset="UTF-8">
		<title>Sign-Up/Login Form</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
        <script type="text/javascript">
        	function validate() {
        		var message = "${message}";
        		
        	}
        </script>
	</head>

	<body onload="validate()">
		<div id="header">
			<h1>Secure File Sharing</h1>
		</div>
  
		<div class="form">
      
			<ul class="tab-group">
				<li class="tab"><a href="#signup">Sign Up</a></li>
				<li class="tab active"><a href="#login">Log In</a></li>
			</ul>
      
			<div class="tab-content">
				<div id="login">   
					
			  
					<form action="login.view" method="post">
			  
						<div class="field-wrap">
							<label>Email Address<span class="req">*</span></label>
							<input name="email" type="email"required autocomplete="off"/>
						</div>
				  
						<div class="field-wrap">
							<label>Password<span class="req">*</span></label>
							<input name="password" type="password"required autocomplete="off"/>
						</div>
				  
						<p class="forgot"><a href="#">Forgot Password?</a></p>
				  
						<button class="button button-block"/>Log In</button>
			  
					</form>

				</div>
				
				<div id="signup">   
					
			  
					<form action="signup.view" method="post">
			  
						<div class="top-row">
							<div class="field-wrap">
								<label> First Name <span class="req">*</span></label>
								<input name="firstName" type="text" required autocomplete="off" />
							</div>
			
							<div class="field-wrap">
							  <label>
								Last Name<span class="req">*</span>
							  </label>
							  <input name="lastName" type="text"required autocomplete="off"/>
							</div>
						</div>

						<div class="field-wrap">
							<label>Email Address<span class="req">*</span></label>
							<input name="email" type="email"required autocomplete="off"/>
						</div>
			  
						<div class="field-wrap">
							<label>Set A Password<span class="req">*</span></label>
							<input name="password" type="password"required autocomplete="off"/>
						</div>
			  
			  
						<div class="field-wrap">
							<label>Re-enter Password<span class="req">*</span></label>
							<input name="repassword" type="password"required autocomplete="off"/>
						</div>
			  
						<button type="submit" class="button button-block" onclick="validatePassword();"/>Sign Up</button>
						<input name="methodType" type="hidden" value="post"/>
					</form>

				</div>
			
				
			
			</div><!-- tab-content -->
      
		</div> <!-- /form -->
		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

        <script src="${pageContext.request.contextPath}/js/login.js"></script>

    
    
        <div id="footer">
			Copyright © Capstone 8910 
		</div>
	</body>
</html>
    