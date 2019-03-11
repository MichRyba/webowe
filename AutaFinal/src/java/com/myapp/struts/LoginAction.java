package com.myapp.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author micha
 */
public class LoginAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    private final static String FAILURE = "failure";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        LoginForm formBean = (LoginForm) form;
        String name = formBean.getName();
        String email = formBean.getEmail();

        if (name.equals("phname") || email.equals("ph@email.com")) {
            return mapping.findForward(SUCCESS);
        } else {
            formBean.setError();
            return mapping.findForward(FAILURE);
        }

    }
}
