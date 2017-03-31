<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <body>
    	<h2><spring:message code="user.register" /></h2>
        <c:url value="/create" var="postPath" />
        <form:form modelAttribute="registerForm" action="${postPath}" method="post">
        	<div>
        		<form:label path="username">Username: </form:label>
        		<form:input type="text" path="username" />
        		<form:errors path="username" element="p" cssStyle="color: red;" />
        	</div>
        	<div>
        		<form:label path="password">Password: </form:label>
        		<form:input type="password" path="password" />
        		<form:errors path="password" element="p" cssStyle="color: red;" />
        	</div>
        	<div>
        		<form:label path="repeatPassword">Repeat Password: </form:label>
        		<form:input type="text" path="repeatPassword" />
        		<form:errors path="repeatPassword" element="p" cssStyle="color: red;" />
        	</div>
        	<div>
        		<input type="submit" value="Register!" />
        	</div>
        </form:form >
    </body>
</html>
