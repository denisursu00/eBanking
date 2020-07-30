package banking.web.forms;

import banking.ValidationException;
import banking.model.ReportModel;
import banking.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ReportForm {
    String accountIdAsString;
    String startDateAsString;
    String endDateAsString;

    public static ReportForm fromRequest(HttpServletRequest req) {
        ReportForm reportForm = new ReportForm();

        reportForm.accountIdAsString = req.getParameter("accountId");
        reportForm.startDateAsString = req.getParameter("startDate");
        reportForm.endDateAsString = req.getParameter("endDate");

        return reportForm;
    }
    public ReportModel toModel() throws ValidationException {
        ReportModel reportModel = new ReportModel();

        Integer accountId = null;
        if(StringUtils.isNotEmpty(accountIdAsString)) {
            accountId = Integer.valueOf(accountIdAsString);
        }
        reportModel.setAccountId(accountId);

        DateTimeFormatter fromFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = null;
        if(StringUtils.isNotEmpty(startDateAsString)) {
            try {
                startDate = LocalDate.parse(startDateAsString,fromFormat);
                if(startDate.compareTo(LocalDate.now())>0) {
                    throw new ValidationException("Data de start incorecta!");
                }
            } catch (DateTimeParseException ex) {
                throw new ValidationException("Data de start incorecta!");
            }
        }
        reportModel.setStartDate(startDate);

        LocalDate endDate = null;
        if(StringUtils.isNotEmpty(endDateAsString)) {
            try {
                endDate = LocalDate.parse(endDateAsString,fromFormat);
                if(endDate.compareTo(LocalDate.now())>0) {
                    throw new ValidationException("Data de sfarsit incorecta!");
                }
            } catch (DateTimeParseException ex) {
                throw new ValidationException("Data de sfarsit incorecta!");
            }
        }
        reportModel.setEndDate(endDate);

        return reportModel;
    }

}
