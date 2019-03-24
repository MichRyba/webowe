<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.Statement" %>
<%@page import="java.sql.Connection" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %> 

<%
    String connectionUrl = "jdbc:derby://localhost:1527/Auta";
    String userId = "DBadmin";
    String password = "123";
    try {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
%>

<html>
    <title>Nazwa Firmy</title>
    <body bgcolor="#fffff0">
        <html:form action="/reserve">
            <h2 align="center"><font><strong>Dostępne samochody</strong></font></h2>

            <table align="center" cellpadding="5" cellspacing="5" border="0">
                <tr bgcolor="#c5e7e0">
                    <td><b>Samochód</b></td>
                    <td><b>Rok Produkcji</b></td>
                    <td><b>Kolor</b></td>
                    <td><b>Cena</b></td>
                    <td><b>Wybierz</b></td>
                </tr>

                <%
                    try {

                        connection = DriverManager.getConnection(connectionUrl, userId, password);
                        statement = connection.createStatement();
                        resultSet = statement.executeQuery("SELECT * FROM AUTAMODELE ORDER BY ID");

                        while (resultSet.next()) {
                %>

                <tr bgcolor="#5ffff5">
                    <td><%=resultSet.getString("MARKA")%> <%=resultSet.getString("MODEL")%></td>
                    <td>Rok: <%=resultSet.getString("ROCZNIK")%></td>
                    <td>Kolor: <%=resultSet.getString("KOLOR")%></td>
                    <td>Cena: <%=resultSet.getString("CENA")%></td>
                    <td><html:submit value='<%=resultSet.getString("ID")%>' property="Reg" /></td>
                </tr>
                <%

                        }

                        connection.close();
                    } catch (Exception e) {

                        e.printStackTrace();

                    }
                %>

            </table>

            <h2 align="center"><font><strong>Formularz</strong></font></h2>

            <table align="center" cellpadding="5" cellspacing="5" border="0">

                <tr>
                    <td colspan="2">
                        <bean:write name="reserveForm" property="error" filter="false"/>
                        &nbsp;</td>
                </tr>
                <tr bgcolor="#c5e7e0">
                    <td><b>Imię</b></td>
                    <td><b>Nazwisko</b></td>
                    <td><b>Nr telefonu</b></td>
                    <td><b>Data wyporzyczenia</b></td>
                    <td><b>Data zwrotu</b></td>
                </tr>

                <tr bgcolor="#ffffff">
                    <td><html:text property="imie" /></td>
                    <td><html:text property="nazwisko" /></td>
                    <td><html:text property="tel" /></td>
                    <td><html:text property="dataW" /></td>
                    <td><html:text property="dataZ" /></td>
                </tr>





            </table>
        </html:form>
    </body>
</html>
