package banking.web.servlets;

import banking.ValidationException;
import banking.model.Client;
import banking.model.TransferModel;
import banking.services.AccountService;
import banking.services.ClientService;
import banking.services.TransactionService;
import banking.web.forms.TransferForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TransferServlet extends HttpServlet {
    private TransactionService transactionService;
    private ClientService clientService;

    public TransferServlet() {
        transactionService = new TransactionService();
        clientService = new ClientService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Client> clients = clientService.getAll();
        req.setAttribute("clients",clients);
        goToPage(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        transfer(req,resp);
    }

    private void transfer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TransferForm transferForm = TransferForm.fromRequest(req);
        try {
            TransferModel transferModel = transferForm.toModel();
            transactionService.transfer(transferModel);
            req.setAttribute("message","Tranzactie efectuata cu succes!");
        } catch (ValidationException ex) {
            req.setAttribute("message",ex.getMessage());
        }

        List<Client> clients = clientService.getAll();
        req.setAttribute("clients",clients);
        goToPage(req,resp);
    }

    private void goToPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/JSP/transfer.jsp").forward(req, resp);
    }
}
