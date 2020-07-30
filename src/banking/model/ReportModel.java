package banking.model;

import java.time.LocalDate;
import java.util.Date;

public class ReportModel {
    Integer accountId;
    LocalDate startDate;
    LocalDate endDate;

    public Integer getAccountId() { return accountId; }
    public void setAccountId(Integer account) { this.accountId = account; }


    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }


    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}
