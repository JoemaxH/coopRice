<%-- 
    Document   : success
    Created on : Sep 1, 2016, 1:14:39 PM
    Author     : Max
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome ${param.username}!</h1>
        <form action="searchServ" submit>
            <p>
                <label for="game_search">Enter a game title</label>
                <input type="text" name="game_search" required><br>
            </p>
            <p>
                <input class="submit" type="submit" value="Search!"/>
            </p>
        </form>
    </body>
</html>
