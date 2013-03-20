<%@ include file="/WEB-INF/includes/init.jsp" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tags:template title="Members Area">
        <jsp:body>
                <h1>Members Area</h1>
                <p>You are logged in as <c:out value="${user.name}"/> (<c:out value="${user.email}"/>)</p>
        </jsp:body>
</tags:template>