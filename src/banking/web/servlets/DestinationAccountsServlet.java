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

public class DestinationAccountsServlet extends HttpServlet {
    private AccountService accountService;

    public DestinationAccountsServlet() {
        accountService = new AccountService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        accountsDisplay(req,resp);
    }

    private void accountsDisplay(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json;
        List<Account> accountsDest = accountService.getAllActive();
        json = new Gson().toJson(accountsDest);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}
