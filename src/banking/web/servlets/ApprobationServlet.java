package banking.web.servlets;

import banking.dao.AccountDao;
import banking.model.PendingAccountModel;
import banking.services.AccountService;
import banking.web.forms.ApprobationForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ApprobationServlet extends HttpServlet {
    private AccountService accountService;

    public ApprobationServlet() { accountService = new AccountService(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        displayAccounts(req);
        goToPage(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApprobationForm approbationForm = ApprobationForm.fromRequest(req);
        PendingAccountModel pendingAccountModel = approbationForm.toModel();
        accountService.updatePending(pendingAccountModel);
    }

    private void displayAccounts(HttpServletRequest req) {
        List<PendingAccountModel> accounts = accountService.getPending();
        req.setAttribute("pendingAccounts",accounts);
    }

    private void goToPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/JSP/aprobare.jsp").forward(req, resp);
    }
}
