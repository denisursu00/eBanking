package banking.web.servlets;

import banking.model.Account;
import banking.model.Client;
import banking.services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AccountsDisplayServlet extends HttpServlet {
    private AccountService accountService;

    public AccountsDisplayServlet() { accountService = new AccountService(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        accountsDisplay(req,resp);
    }
    private void accountsDisplay(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = (Client) req.getSession().getAttribute("client");
        List<Account> accounts = accountService.getAllByClientId(client.getId());
        req.setAttribute("accounts",accounts);
        goToPage(req,resp);
    }
    private void goToPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/JSP/vizualizareConturi.jsp").forward(req, resp);
    }
}
