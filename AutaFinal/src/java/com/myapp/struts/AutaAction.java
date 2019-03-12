/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author micha
 */
public class AutaAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String AUTAS = "autas";
    private static final String REZERWACJE = "rezerwacje";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        AutaForm AForm = (AutaForm) form;
        int id = AForm.getId();
        String marka = AForm.getMarka();
        String model = AForm.getModel();
        int rocznik = AForm.getRocznik();
        String kolor = AForm.getKolor();
        double cena = AForm.getCena();

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
                statement.execute("INSERT INTO AUTAMODELE (ID, MARKA, MODEL, ROCZNIK, KOLOR, CENA) VALUES (" + id + ", '" + marka + "', '" + model + "', " + rocznik + ", '" + kolor + "', " + cena + ")");
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (request.getParameter("Del") != null) {

            try {
                connection = DriverManager.getConnection(connectionUrl, userId, password);
                statement = connection.createStatement();

                String sql = "DELETE FROM AUTAMODELE WHERE ";

                if (id != 0) {
                    sql = sql + " ID = " + id + " AND ";
                }
                if (!marka.isEmpty()) {
                    sql = sql + " MARKA = '" + marka + "' AND ";
                }
                if (!model.isEmpty()) {
                    sql = sql + " MODEL = '" + model + "' AND ";
                }
                if (rocznik != 0) {
                    sql = sql + " ROCZNIK = " + rocznik + " AND ";
                }
                if (!kolor.isEmpty()) {
                    sql = sql + " KOLOR = '" + kolor + "' AND ";
                }
                if (cena != 0) {
                    sql = sql + " CENA = " + cena + " AND ";
                }
                if (id == 0 && marka.isEmpty() && model.isEmpty() && rocznik == 0 && kolor.isEmpty() && cena == 0) {
                }else{
                    sql = sql.substring(0, sql.length() - 5);
                    statement.execute(sql);
                }
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (request.getParameter("rezerwacje") != null) {
            return mapping.findForward(REZERWACJE);
        }

        return mapping.findForward(AUTAS);
    }
}
