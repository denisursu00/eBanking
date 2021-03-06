package banking.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Date;

public class Transaction {
    String transactionDate;
    Integer accountId;
    BigDecimal debitSum;
    BigDecimal creditSum;
    BigDecimal currentSum;

    public String getTransactionDate() { return transactionDate; }
    public void setTransactionDate(String transactionDate) { this.transactionDate = transactionDate; }


    public Integer getAccountId() { return accountId; }
    public void setAccountId(Integer account) { this.accountId = account; }


    public BigDecimal getDebitSum() { return debitSum; }
    public void setDebitSum(BigDecimal debitSum) { this.debitSum = debitSum; }


    public BigDecimal getCreditSum() { return creditSum; }
    public void setCreditSum(BigDecimal creditSum) { this.creditSum = creditSum; }


    public BigDecimal getCurrentSum() { return currentSum; }
    public void setCurrentSum(BigDecimal currentSum) { this.currentSum = currentSum; }
}
