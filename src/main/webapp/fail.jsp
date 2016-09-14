<%-- 
    Document   : fail
    Created on : Sep 1, 2016, 1:15:06 PM
    Author     : Max
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Refresh" content="5;url=login.jsp; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Error: <c:out value="${errorMsg}" /></h1>
        Redirecting to login...
    </body>
</html>
