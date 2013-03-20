<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%-- Setup the base URL --%>
<c:set var="_baseUrl" value="${empty pageContext.request.contextPath || pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath}"/>

<%-- Determine if they're used in, and make the "user" available if so --%>
<sec:authorize access="isAuthenticated()">
	<c:set var="authorized" value="${true}"/>
	<sec:authentication property="principal" var="user" />
</sec:authorize>