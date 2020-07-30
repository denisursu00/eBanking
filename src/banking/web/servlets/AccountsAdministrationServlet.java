package banking.web.servlets;

import banking.ValidationException;
import banking.model.Client;
import banking.model.Account;
import banking.model.NewAccountModel;
import banking.services.AccountService;
import banking.web.forms.AccountAdministrationForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AccountsAdministrationServlet extends HttpServlet {

    private AccountService accountService;

    public AccountsAdministrationServlet() { accountService = new AccountService(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        accountsDisplay(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        insertAccount(req,resp);
    }

    private void accountsDisplay(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = (Client) req.getSession().getAttribute("client");
        List<Account> accounts = accountService.getActiveByClientId(client.getId());
        req.setAttribute("accounts",accounts);
        goToPage(req,resp);
    }

    private void insertAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = (Client) req.getSession().getAttribute("client");
        AccountAdministrationForm form = AccountAdministrationForm.fromRequest(req);
        try {
            NewAccountModel newAccountModel = form.toModel();
            newAccountModel.setClientId(client.getId());
            accountService.insert(newAccountModel);
            req.setAttribute("message","Solicitarea transmisa cu succes!");
        } catch (ValidationException ex) {
            req.setAttribute("message", ex.getMessage());
        }
        doGet(req,resp);
    }

    private void goToPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/JSP/administrareCont.jsp").forward(req, resp);
    }
}
