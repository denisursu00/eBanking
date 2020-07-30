package banking.web.servlets;

import banking.model.Transaction;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PdfReportServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Transaction> transactions = (List<Transaction>) req.getSession().getAttribute("transactions");
        try {
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(transactions);
            Map<String,Object> parameters = new HashMap<String, Object>();
            parameters.put("TransactionParam",itemsJRBean);
            JRDataSource jrDataSource = new JREmptyDataSource();

            String relativePath = "/jasper";
            String absolutePath = getServletContext().getRealPath(relativePath);

            File f = new File(absolutePath,"tranzactii.jasper");

            JasperPrint jasperPrint = JasperFillManager.fillReport(f.getAbsolutePath(),parameters,jrDataSource);

            OutputStream outputStream = resp.getOutputStream();
            resp.setHeader("Content-Disposition", "attachment; filename=\"tranzactii.pdf\"");
            resp.setContentType("application/pdf");

            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            exporter.exportReport();
            resp.getOutputStream().flush();

        } catch (JRException ex) {
            throw new RuntimeException(ex);
        }
    }
}
