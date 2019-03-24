<%@page import="java.util.Date"%>
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
    Date date = new Date();
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
    <title>Rezerwacje</title>
    <body bgcolor="#fffff0">
        <html:form action="/success">
            <h2 align="center"><font><strong>Rezerwacje</strong></font></h2>
            <h3 align="center"> <html:submit value="Samochody" property="Auta" /></h3>
            <table align="center" cellpadding="5" cellspacing="5" border="0">
                <h3 align="center"><font>Aktualne</font></h3>
                <tr bgcolor="#c5e7e0">
                    <td><b>ID Samochodu</b></td>
                    <td><b>Data Wyporzyczenia</b></td>
                    <td><b>Data Zwrotu</b></td>
                    <td><b>Imię</b></td>
                    <td><b>Nazwisko</b></td>
                    <td><b>Nr telefonu</b></td>
                    <td bgcolor="#fffff0"><b>Usuń</b></td>
                </tr>

                <%
                    try {

                        connection = DriverManager.getConnection(connectionUrl, userId, password);
                        statement = connection.createStatement();
                        resultSet = statement.executeQuery("SELECT * FROM REZERWACJE ORDER BY 'DATA_ODDANIA'");
                        while (resultSet.next()) {
                            if (resultSet.getDate("DATA_ODDANIA").after(date) && resultSet.getBoolean("OCZEKUJACY") == false) {
                %>
                <tr bgcolor="#eeeeee">
                    <td align="right"><%=resultSet.getString("ID_AUTA")%></td>
                    <td><%=resultSet.getString("DATA_WYPOZYCZENIA")%></td>
                    <td><%=resultSet.getString("DATA_ODDANIA")%></td>
                    <td><%=resultSet.getString("IMIE")%></td>
                    <td><%=resultSet.getString("NAZWISKO")%></td>
                    <td><%=resultSet.getString("TEL")%></td>
                    <td bgcolor="#fffff0"><html:submit value='<%=resultSet.getString("ID_REZERWACJI")%>' property="Del" /></td>
                </tr>

                <%
                        }
                    }
                %>
            </table>
            <table  align="center"  cellpadding="5" cellspacing="5" border="0">
                <h3 align="center"><font>Oczekjuące</font></h3>
                <tr bgcolor="#c5e7e0">
                    <td><b>ID Samochodu</b></td>
                    <td><b>Data Wyporzyczenia</b></td>
                    <td><b>Data Zwrotu</b></td>
                    <td><b>Imię</b></td>
                    <td><b>Nazwisko</b></td>
                    <td><b>Nr telefonu</b></td>
                    <td bgcolor="#fffff0"><b>Zatwierdź</b></td>
                    <td bgcolor="#fffff0"><b>Usuń</b></td>
                </tr>
                <%
                    resultSet = statement.executeQuery("SELECT * FROM REZERWACJE ORDER BY 'DATA_ODDANIA'");
                    while (resultSet.next()) {
                        if (resultSet.getDate("DATA_ODDANIA").after(date) && resultSet.getBoolean("OCZEKUJACY") == true) {
                %>

                <tr bgcolor="#eeeeee">
                    <td align="right"><%=resultSet.getString("ID_AUTA")%></td>
                    <td><%=resultSet.getString("DATA_WYPOZYCZENIA")%></td>
                    <td><%=resultSet.getString("DATA_ODDANIA")%></td>
                    <td><%=resultSet.getString("IMIE")%></td>
                    <td><%=resultSet.getString("NAZWISKO")%></td>
                    <td><%=resultSet.getString("TEL")%></td>
                    <td bgcolor="#fffff0"><html:submit value='<%=resultSet.getString("ID_REZERWACJI")%>' property="Zmien" /></td>
                    <td bgcolor="#fffff0"><html:submit value='<%=resultSet.getString("ID_REZERWACJI")%>' property="Del" /></td>
                </tr>

                <%
                        }
                    }
                %>
            </table>
            <table align="center" cellpadding="5" cellspacing="5" border="0">
                <h3 align="center"><font>Zarchiwizowane</font></h3>
                <tr bgcolor="#c5e7e0">
                    <td><b>ID Samochodu</b></td>
                    <td><b>Data Wyporzyczenia</b></td>
                    <td><b>Data Zwrotu</b></td>
                    <td><b>Imię</b></td>
                    <td><b>Nazwisko</b></td>
                    <td><b>Nr telefonu</b></td>
                    <td bgcolor="#fffff0"><b>Usuń</b></td>
                </tr>

                <%
                    resultSet = statement.executeQuery("SELECT * FROM REZERWACJE ORDER BY 'DATA_ODDANIA'");
                    while (resultSet.next()) {
                        if (!resultSet.getDate("DATA_ODDANIA").after(date)) {
                %>

                <tr bgcolor="#eeeeee">
                    <td align="right"><%=resultSet.getString("ID_AUTA")%></td>
                    <td><%=resultSet.getString("DATA_WYPOZYCZENIA")%></td>
                    <td><%=resultSet.getString("DATA_ODDANIA")%></td>
                    <td><%=resultSet.getString("IMIE")%></td>
                    <td><%=resultSet.getString("NAZWISKO")%></td>
                    <td><%=resultSet.getString("TEL")%></td>
                    <td bgcolor="#fffff0"><html:submit value='<%=resultSet.getString("ID_REZERWACJI")%>' property="Del" /></td>
                </tr>



                <%
                            }
                        }

                        connection.close();
                    } catch (Exception e) {

                        e.printStackTrace();

                    }
                %>

            </table>
        </html:form>
    </body>
</html>
