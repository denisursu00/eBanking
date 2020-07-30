package banking.web.servlets;

import banking.model.Transaction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class CsvReportServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/csv");
        resp.setHeader("Content-Disposition", "attachment; filename=\"tranzactii.csv\"");
        List<Transaction> transactions = (List<Transaction>) req.getSession().getAttribute("transactions");
        resp.getOutputStream().println("Data, Cont Debitor/Creditor, Suma Debit, Suma Credit, Sold Curent");
        for (Transaction transaction:transactions) {
            String debitSum = "";
            String creditSum = "";
            if (transaction.getDebitSum() == null) {
                debitSum = " ";
                creditSum = transaction.getCreditSum().toString();
            } else {
                creditSum = " ";
                debitSum = transaction.getDebitSum().toString();
            }
            resp.getOutputStream().println(""+
                    transaction.getTransactionDate()+", "+
                    transaction.getAccountId()+", "+
                    debitSum+", "+
                    creditSum+", "+
                    transaction.getCurrentSum());
        }
        resp.getOutputStream().flush();
    }
}
