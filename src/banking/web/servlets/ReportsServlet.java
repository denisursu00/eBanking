package banking.web.servlets;

import banking.ValidationException;
import banking.model.Account;
import banking.model.Client;
import banking.model.ReportModel;
import banking.model.Transaction;
import banking.services.AccountService;
import banking.services.TransactionService;
import banking.web.forms.ReportForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReportsServlet extends HttpServlet {
    private AccountService accountService;
    private TransactionService transactionService;

    public ReportsServlet() {
        accountService = new AccountService();
        transactionService = new TransactionService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("transactions");
        displayAccounts(req,resp);
        goToPage(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReportForm reportForm = ReportForm.fromRequest(req);
        try {
            req.getSession().removeAttribute("transactions");
            ReportModel reportModel = reportForm.toModel();
            List<Transaction> transactions = transactionService.getByClientId(reportModel);
            req.setAttribute("transactions",transactions);
            req.getSession().setAttribute("transactions",transactions);
        } catch (ValidationException ex) {
            req.setAttribute("message",ex.getMessage());
        }
        displayAccounts(req,resp);
        goToPage(req,resp);
    }

    private void displayAccounts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = (Client) req.getSession().getAttribute("client");
        List<Account> accounts = accountService.getActiveByClientId(client.getId());
        req.setAttribute("accounts",accounts);
    }

    private void goToPage(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/JSP/rapoarte.jsp").forward(req,resp);
    }
}
