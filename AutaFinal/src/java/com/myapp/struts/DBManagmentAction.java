package com.myapp.struts;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DBManagmentAction extends org.apache.struts.action.Action {

    private static final String INSERT = "insert";
    private static final String AUTA = "auta";
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        DBManagmentForm DBForm = (DBManagmentForm) form;
        String imie = DBForm.getImie();
        String nazwisko = DBForm.getNazwisko();
        String tel = DBForm.getTel();
        String dataW = DBForm.getDataW();
        String dataZ = DBForm.getDataZ();
        int idAuta = DBForm.getIdAuta();

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

        if (request.getParameter("Add") != null) {

            try {
                connection = DriverManager.getConnection(connectionUrl, userId, password);
                statement = connection.createStatement();
                statement.execute("INSERT INTO REZERWACJE (ID_AUTA, DATA_WYPOZYCZENIA, DATA_ODDANIA, IMIE, NAZWISKO, TEL) VALUES (" + idAuta + ", '" + dataW + "', '" + dataZ + "', '" + imie + "', '" + nazwisko + "', '" + tel + "')");
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            if (request.getParameter("Del") != null) {

                try {
                    connection = DriverManager.getConnection(connectionUrl, userId, password);
                    statement = connection.createStatement();

                    String sql = "DELETE FROM REZERWACJE WHERE ";
                    
                    if (idAuta != 0) {
                        sql = sql + " ID_AUTA = " + idAuta + " AND ";
                    }
                    if (!dataW.isEmpty()) {
                        sql = sql + " DATA_WYPOZYCZENIA = '" + dataW + "' AND ";
                    }
                    if (!dataZ.isEmpty()) {
                        sql = sql + " DATA_ZWROTU = '" + dataZ + "' AND ";
                    }
                    if (!imie.isEmpty()) {
                        sql = sql + " IMIE = '" + imie + "' AND ";
                    }
                    if (!nazwisko.isEmpty()) {
                        sql = sql + " NAZWISKO = '" + nazwisko + "' AND ";
                    }
                    if (!tel.isEmpty()) {
                        sql = sql + " TEL = '" + tel + "' AND ";
                    }
                    if (idAuta == 0 && dataW.isEmpty() && dataZ.isEmpty() && imie.isEmpty() && nazwisko.isEmpty() && tel.isEmpty()) {
                    }else{
                    sql = sql.substring(0, sql.length()-5);
                    statement.execute(sql);
                    }
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (request.getParameter("Auta") != null) {
            return mapping.findForward(AUTA);
        }
        
        return mapping.findForward(INSERT);
    }
}
