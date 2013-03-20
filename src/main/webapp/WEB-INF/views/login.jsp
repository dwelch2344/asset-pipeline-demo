<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tags:template title="Login">
        <jsp:body>
                <h1>Login Required</h1>
                
                <%-- Show any login errors --%>
                <c:if test="${not empty param.failed}">
				    <div class="alert alert-error">
				    	<h4>Failed Logging In</h4>
				        Your login attempt was not successful, please try again.<br />
				        Reason: <c:out value="${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}"/>
				    </div>
				</c:if>
                
                
                <form action="doLogin" method="POST">
                        <label for="username">User Name:</label>
                        <input id="username" name="user" type="text" />
                        <label for="password">Password:</label>
                        <input id="password" name="password" type="password" />
                        <br/>
                        <input class="btn" type="submit" value="Log In" />
                </form>
        </jsp:body>
</tags:template>