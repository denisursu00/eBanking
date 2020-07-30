package banking.web.servlets;

import banking.model.Account;
import banking.model.Client;
import banking.services.AccountService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SourceAccountsServlet extends HttpServlet {
    private AccountService accountService;

    public SourceAccountsServlet() {
        accountService = new AccountService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        accountsDisplay(req,resp);
    }

    private void accountsDisplay(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = (Client) req.getSession().getAttribute("client");
        List<Account> accounts = accountService.getActiveByClientId(client.getId());
        String json;
        json = new Gson().toJson(accounts);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}
