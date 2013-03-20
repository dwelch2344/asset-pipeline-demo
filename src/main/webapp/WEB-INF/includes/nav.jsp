<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container-fluid">
 			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
    			<span class="icon-bar"></span>
    			<span class="icon-bar"></span>
    			<span class="icon-bar"></span>
 			</a>
 			<a class="brand" href="${_baseUrl}"> ${siteName} </a>
	<div class="nav-collapse collapse">
		<ul class="nav">
			<li><a href="${_baseUrl}/">Home</a></li>
			<li><a href="${_baseUrl}/about">About</a></li>
		</ul>
	</div><!--/.nav-collapse -->

	<div class="btn-group pull-right">
		<%-- Display the appropriate menu if they're logged in. See init.jsp  --%>
		<c:choose>
			<c:when test="${not authorized}">
				<a class="btn dropdown-toggle" data-toggle="dropdown" data-target="dropdown-menu">
					<i class="icon-user"></i> 
					Get Started
					<span class="caret"></span>
				</a>
				
				<ul class="dropdown-menu">
					<li><a href="${_baseUrl}/login">Sign In</a></li>
					<li class="divider"></li>
					<li><a href="${_baseUrl}/register">Register</a></li>
				</ul>
			</c:when>
			<c:otherwise>
				<a class="btn dropdown-toggle" data-toggle="dropdown" data-target="dropdown-menu">
					<i class="icon-user"></i> 
					Welcome <c:out value="${user.name}"/> 
					<span class="caret"></span>
				</a>
				
				<ul class="dropdown-menu">
					<li><a href="${_baseUrl}/secure/account">Your Account</a></li>
					<li class="divider"></li>
					<li><a href="${_baseUrl}/logout">Sign Out</a></li>
					</ul>						
				</c:otherwise>
			</c:choose>
			</div>
       
		</div>
	</div>
</div>