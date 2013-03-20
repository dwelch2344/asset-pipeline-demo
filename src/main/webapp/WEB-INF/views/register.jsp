<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:template title="Register">
        <jsp:attribute name="head">  
			 <style>
			 	.input-error{
			 		color: red;
			 	}
			 </style>
        </jsp:attribute>
        <jsp:body>
                <h1>Register</h1>
                <p> We don't prevent duplicates, don't register with the same email multiple times... </p>
                <form:form commandName="bean" action="register">
                	<form:label path="name">Name:</form:label>
                	<form:input path="name"/>
                	<form:errors path="name" cssClass="input-error" />
                	
                	<form:label path="email">Email:</form:label>
                	<form:input path="email"/>
                	<form:errors path="email" cssClass="input-error" />
                	
                	<form:label path="password">Password:</form:label>
                	<form:password path="password"/>
                	<form:errors path="password" cssClass="input-error" />
                	
                	<br/>
                	<form:button class="btn">Register</form:button>
                </form:form>
        </jsp:body>
</tags:template>