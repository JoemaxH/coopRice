<%-- 
    Document   : success
    Created on : Sep 1, 2016, 1:14:39 PM
    Author     : Max
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome ${param.username}!<br>
        ${resultMessage}</h1>
        <form action="searchServ" submit>
            <p>
                <label for="game_search">Enter a game title</label>
                <input type="text" name="game_search" required><br>
            </p>
            <p>
                <input class="submit" type="submit" value="Search!"/>
            </p>
        </form>
        <form action="fetchTable" submit>
            <input class="submit" type="submit" value="Update Table"/>
        </form>
        
        <table>
            <tr>
                <td><b>Title</b></td>
                <td><b>Genre</b></td>
                <td><b>Local Players</b></td>
                <td><b>Online Players</b></td>
                <td><b>Normal Price</b></td>
                <td><b>Sale Price</b></td>
                <td><b>Savings</b></td> 
                <td><b>Metacritic Score</b></td>
            </tr>
            <c:forEach items="${wish}" var="wish" >
                <tr>
                    <td><c:out value="${wish.title}"/></td>
                    <td><c:out value="${wish.genre}"/></td>
                    <td><c:out value="${wish.localp}"/></td>
                    <td><c:out value="${wish.onlinep}"/></td>
                    <td><c:out value="${wish.normalPrice}"/></td>
                    <td><c:out value="${wish.salePrice}"/></td>
                    <td><c:out value="${wish.savings}"/></td> 
                    <td><c:out value="${wish.metacriticScore}"/></td>
                </tr>
            </c:forEach>
        </table>
        
    </body>
</html>
