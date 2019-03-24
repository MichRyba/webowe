/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        String marka = AForm.getMarka();
        String model = AForm.getModel();
        int rocznik = AForm.getRocznik();
        String kolor = AForm.getKolor();
        double cena = AForm.getCena();
        
        Pattern pLetters = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
        Pattern pInt = Pattern.compile("[^0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern pDouble = Pattern.compile("\\\\d+\\\\.\\\\d+", Pattern.CASE_INSENSITIVE);
        Matcher m;
        
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
            
            m = pLetters.matcher(marka);
            if (m.find()) {
                AForm.setError("Wpisz poprawną markę");
                return mapping.findForward(AUTAS);
            } else {

            m = pLetters.matcher(model);
            if (m.find()) {
                AForm.setError("Wpisz poprawny model");
                return mapping.findForward(AUTAS);
            } else {
                
            m = pInt.matcher(Integer.toString(rocznik));
            if (m.find() || rocznik==0) {
                AForm.setError("Wpisz poprawny rok produkcji");
                return mapping.findForward(AUTAS);
            } else {
            m = pLetters.matcher(kolor);    
            if (m.find()) {
                AForm.setError("Wpisz poprawny kolor");
                return mapping.findForward(AUTAS);
            } else {
                
            m = pDouble.matcher(Double.toString(cena));
            if (m.find() || cena==0) {
                AForm.setError("Wpisz poprawną cenę");
                return mapping.findForward(AUTAS);
            } else {    
                
                    try {
                        connection = DriverManager.getConnection(connectionUrl, userId, password);
                        statement = connection.createStatement();
                        statement.execute("INSERT INTO AUTAMODELE (MARKA, MODEL, ROCZNIK, KOLOR, CENA) VALUES ('" + marka + "', '" + model + "', " + rocznik + ", '" + kolor + "', " + cena + ")");
                        connection.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }}}}}
        }
        
        if (request.getParameter("Del") != null) {

            try {
                connection = DriverManager.getConnection(connectionUrl, userId, password);
                statement = connection.createStatement();
                String sql = "DELETE FROM AUTAMODELE WHERE ID = " + request.getParameter("Del");
                statement.execute(sql);

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
