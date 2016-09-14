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
        <form action="fetchManage" submit>
            <input class="submit" type="submit" value="Manage Account"/>
        </form>
        <br>
        <form action="fetchTable" submit>
            <input class="submit" type="submit" value="Update Table"/>
            <select name="col">
                <option value="title">Title</option>
                <option value="genre">Genre</option>
                <option value="localp">Local Players</option>
                <option value="onlinep">Online Players</option>
                <option value="normalPrice">Normal Price</option>
                <option value="salePrice">Sale Price</option>
                <option value="savings">Savings (%)</option>
                <option value="metacriticScore">Metacritic Score</option>
            </select>
            <select name="acdc">
                <option value="ASC">Ascending Order</option>
                <option value="DESC">Descending Order</option>
            </select>
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
                    <form action="delGame" submit>
                        <input type="hidden" name="title" value="${wish.title}">
                        <td><c:out value="${wish.title}"/></td>
                        <td><c:out value="${wish.genre}"/></td>
                        <td><c:out value="${wish.localp}"/></td>
                        <td><c:out value="${wish.onlinep}"/></td>
                        <td><c:out value="${wish.normalPrice}"/></td>
                        <td><c:out value="${wish.salePrice}"/></td>
                        <td><c:out value="${wish.savings}"/></td> 
                        <td><c:out value="${wish.metacriticScore}"/></td>
                        <td><input class="submit" type="submit" value="Delete Item"/></td>
                    </form>
                </tr>
            </c:forEach>
        </table>
        
    </body>
</html>
