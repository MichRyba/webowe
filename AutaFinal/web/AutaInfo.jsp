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
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
%>
<html>
    <title>Samochody</title>
    <body>
        <html:form action="/AutaInfo">
            <h2 align="center"><font><strong>Samochody</strong></font></h2>
            <h3 align="center"> <html:submit value="Rezerwacje" property="rezerwacje" /></h3>

            <table align="center" cellpadding="5" cellspacing="5" border="1">

                <tr bgcolor="#c5e7e0">
                    <td><b>ID Samochodu</b></td>
                    <td><b>Marka</b></td>
                    <td><b>Model</b></td>
                    <td><b>Rok Produkcji</b></td>
                    <td><b>Kolor</b></td>
                    <td><b>Cena</b></td>
                </tr>

                <%
                    try {

                        connection = DriverManager.getConnection(connectionUrl, userId, password);
                        statement = connection.createStatement();
                        resultSet = statement.executeQuery("SELECT * FROM AUTAMODELE ORDER BY ID");

                        while (resultSet.next()) {
                %>
                <tr bgcolor="#c5e7e0">
                    <td align="right"><%=resultSet.getString("ID")%></td>
                    <td><%=resultSet.getString("MARKA")%></td>
                    <td><%=resultSet.getString("MODEL")%></td>
                    <td><%=resultSet.getString("ROCZNIK")%></td>
                    <td><%=resultSet.getString("KOLOR")%></td>
                    <td><%=resultSet.getString("CENA")%></td>
                </tr>
                <%

                        }

                        connection.close();
                    } catch (Exception e) {

                        e.printStackTrace();

                    }
                %>


                <tr bgcolor="#ffffff">
                    <td><html:text property="id" /></td>
                    <td><html:text property="model" /></td>
                    <td><html:text property="marka" /></td>
                    <td><html:text property="rocznik" /></td>
                    <td><html:text property="kolor" /></td>
                    <td><html:text property="cena" /></td>
                    <td><html:submit value="Dodaj" property="Add" /></td>
                    <td><html:submit value="Usuń" property="Del" /></td>
                </tr>





            </table>
        </html:form>
    </body>
</html>
