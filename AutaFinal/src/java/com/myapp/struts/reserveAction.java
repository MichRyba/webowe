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
public class reserveAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        reserveForm rForm = (reserveForm) form;
        String imie = rForm.getImie();
        String nazwisko = rForm.getNazwisko();
        String tel = rForm.getTel();
        String dataW = rForm.getDataW();
        String dataZ = rForm.getDataZ();
        
        Pattern pLetters = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
        Pattern pInt = Pattern.compile("[^0-9 ]");
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

        if (request.getParameter("Reg") != null) {
            
            m = pLetters.matcher(imie);
            if (m.find() || imie.isEmpty()) {
                rForm.setError("Wpisz poprawne imię");
                return mapping.findForward(SUCCESS);
            } else {

            m = pLetters.matcher(nazwisko);
            if (m.find() || nazwisko.isEmpty()) {
                rForm.setError("Wpisz poprawne nazwisko");
                return mapping.findForward(SUCCESS);
            } else {
                
            m = pInt.matcher(tel);
            if (m.find() || tel.isEmpty() || tel.length() != 9) {
                rForm.setError("Wpisz poprawny numer telefonu");
                return mapping.findForward(SUCCESS);
            } else {
                
            
            
               
                
                    try {
                        connection = DriverManager.getConnection(connectionUrl, userId, password);
                        statement = connection.createStatement();
                        statement.execute("INSERT INTO REZERWACJE (ID_AUTA, DATA_WYPOZYCZENIA, DATA_ODDANIA, IMIE, NAZWISKO, TEL, OCZEKUJACY) VALUES (" + request.getParameter("Reg")  +  ", '" + dataW + "', '" + dataZ + "', '" + imie + "', '" + nazwisko + "', '" + tel + "', " + "true)");
                        connection.close();
                        rForm.setError("Rezerwacja powiodała się");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }}}
        }
        
        return mapping.findForward(SUCCESS);
    }
}
