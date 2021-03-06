<%-- 
    Document   : login
    Created on : Sep 1, 2016, 12:47:52 PM
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
        <h1>Sign In or Sign Up!</h1>
        <form id="form1" action="loginServlet" method="post" class="cmxform">
            <fieldset>
                <p>
                    <label for="username">Username (required, min. of 3 chars)</label>
                    <input type="text" name="username" minlength="2" required><br>
                </p>
                <p>
                    <label for="password">Password (required)</label>
                    <input type="password" name="password" required><br>
                </p>
                <p>
                    <input class="submit" type="submit" value="Login!"/>
                </p>
            </fieldset>
        </form>
        <script>
            $("#form1").validate();
        </script>
        or
        <br>
        <form name="form2" action="createAccount" method="post">
            <fieldset>
                <p>
                    <label for="username">Username (required, min. of 3 chars)</label>
                    <input type="text" name="new_username" minlength="3" required><br>
                </p>
                <p>
                    <label for="password">Password (required)</label>
                    <input type="password" name="new_password" required><br>
                </p>
                <p>
                    <label for="confirm_pass">Confirm password (required)</label>
                    <input type="password" name="confirm_pass" required><br>
                </p>
                <p>
                    <input class="submit" type="submit" value="Create a New Account!"/>
                </p>
                
            </fieldset>
        </form>
        <script>
            jQuery.validator.setDefaults({
                
                success: "valid"
            });
            $( "#myform" ).validate({
                rules: {
                    password: "required",
                    password_again: {
                        equalTo: "#password"
                    }
                }
            });
        </script>
        
    </body>
</html>
