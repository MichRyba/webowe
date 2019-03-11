<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>           

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logowanie</title>
    </head>
    <body>
        <h1>
            <html:form action="/login">
                <table border="0">
                    <thead>

                        <tr>
                            <td>Logowanie</td>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td colspan="2">
                            <bean:write name="LoginForm" property="error" filter="false"/>
                            &nbsp;</td>
                    </tr>
                    <tr>
                        <td>Imię:</td>
                        <td><html:text property="name" /></td>
                    </tr>   
                    <tr>
                        <td>Email:</td>
                        <td><html:text property="email" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><html:submit value="Login" /></td>
                    </tr>
                    </tbody>
                </table>

            </html:form>
        </h1>
    </body>
</html>
 