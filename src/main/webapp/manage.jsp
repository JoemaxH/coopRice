<%-- 
    Document   : manage
    Created on : Sep 12, 2016, 4:30:55 PM
    Author     : Max Huhman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Change password</h1>
        
            <form id="form1" action="upServlet" method="post" class="cmxform">
                <fieldset>
                    <p>
                        <label for="username">Username </label>
                        <input type="text" name="username" minlength="2" required><br>
                    </p>
                    <p>
                        <label for="password">Old password (required)</label>
                        <input type="password" name="password" required><br>
                    </p>
                    <p>
                        <label for="password">New password (required)</label>
                        <input type="password" name="new_password" required><br>
                    </p>
                    <p>
                        <input class="submit" type="submit" value="Change Password"/>
                    </p>
                </fieldset>
            </form>
        
        <h1>Delete account</h1>
            <form id="form1" action="delServlet" method="post" class="cmxform">
                <fieldset>
                    <p>
                        <label for="username">Username </label>
                        <input type="text" name="username" minlength="2" required><br>
                    </p>
                    <p>
                        <label for="password">Password (required)</label>
                        <input type="password" name="password" required><br>
                    </p>
                    <p>
                        <input class="submit" type="submit" value="Delete Account"/>
                    </p>
                </fieldset>
            </form>
    </body>
</html>
