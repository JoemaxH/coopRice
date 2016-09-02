<%-- 
    Document   : result
    Created on : Sep 2, 2016, 10:00:09 AM
    Author     : Max
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results</title>
    </head>
    <body>
        hello<br>
        <tr>
            <td><c:out value="${wli.title}"/></td>
            <td><c:out value="${wli.genre}"/></td>
            <td><c:out value="${wli.local}"/></td>
            <td><c:out value="${wli.online}"/></td>
            <td><c:out value="${wli.normalPrice}"/></td>
            <td><c:out value="${wli.salePrice}"/></td>
            <td><c:out value="${wli.savings}"/></td>
            <td><c:out value="${wli.metacriticScore}"/></td>
        </tr>
    </body>
</html>
