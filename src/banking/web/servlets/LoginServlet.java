package banking.web.servlets;

import banking.ValidationException;
import banking.model.Client;
import banking.model.LoginModel;
import banking.services.ClientService;
import banking.web.forms.LoginForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private ClientService clientService;

    public LoginServlet(){
        clientService = new ClientService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        goToLoginPage(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        login(req,resp);
    }
    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginForm loginForm = LoginForm.fromRequest(req);
        try {
            LoginModel loginModel = loginForm.toModel();
            Client client = clientService.getByUserNameAndPassword(loginModel);
            HttpSession session = req.getSession();
            session.setAttribute("client", client);
            goToMainPage(req,resp);
        } catch(ValidationException ve) {
            req.setAttribute("message", ve.getMessage());
            goToLoginPage(req,resp);
        }
    }
    private void goToLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/JSP/login.jsp").forward(req, resp);
    }
    private void goToMainPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.sendRedirect("main.do");
    }
}
