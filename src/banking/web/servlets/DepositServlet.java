package banking.web.servlets;

import banking.ValidationException;
import banking.model.DepositModel;
import banking.services.AccountService;
import banking.services.TransactionService;
import banking.web.forms.DepositForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DepositServlet extends HttpServlet {
    private TransactionService transactionService;

    public DepositServlet() { transactionService = new TransactionService(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        goToPage(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        transfer(req,resp);
    }

    private void transfer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DepositForm depositForm = DepositForm.fromRequest(req);
        try {
            DepositModel depositModel = depositForm.toModel();
            transactionService.deposit(depositModel);
            req.setAttribute("message","Tranzactie efectuata cu succes");
        } catch (ValidationException ex) {
            req.setAttribute("message",ex.getMessage());
        }
        goToPage(req,resp);
    }

    private void goToPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/JSP/alimentareCont.jsp").forward(req, resp);
    }
}
