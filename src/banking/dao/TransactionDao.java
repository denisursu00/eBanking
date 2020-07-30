package banking.dao;

import banking.db.DbConnection;
import banking.model.DepositModel;
import banking.model.ReportModel;
import banking.model.Transaction;
import banking.model.TransferModel;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {
    public void deposit(DepositModel depositModel) {
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            String sql = "" +
                    "INSERT " +
                    "INTO tranzactii(id,data_operatie,id_cont_sursa,sold_final_sursa,id_cont_destinatie,sold_final_destinatie,suma_tranzactie) " +
                    "VALUES(tranzactii_seq.NEXTVAL,CURRENT_DATE,?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            Integer accountId = depositModel.getAccountId();
            BigDecimal accountSum = depositModel.getAccountSum();
            BigDecimal depositSum = depositModel.getDepositSum();

            int parameterIndex = 1;
            stmt.setInt(parameterIndex++,accountId);
            stmt.setBigDecimal(parameterIndex++,accountSum);

            stmt.setInt(parameterIndex++,accountId);
            stmt.setBigDecimal(parameterIndex++,accountSum);

            stmt.setBigDecimal(parameterIndex++,depositSum);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            DbConnection.closeConnection(connection);
        }
    }

    public void transfer(TransferModel transferModel){
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            String sql = "" +
                    "INSERT " +
                    "INTO tranzactii(id,data_operatie,id_cont_sursa,sold_final_sursa,id_cont_destinatie,sold_final_destinatie,suma_tranzactie) " +
                    "VALUES(tranzactii_seq.NEXTVAL,CURRENT_DATE,?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            Integer sourceAccountId = transferModel.getSourceAccountId();
            BigDecimal sourceAccountSum = transferModel.getSourceAccountSum();
            Integer destinationAccountId = transferModel.getDestinationAccountId();
            BigDecimal destinationAccountSum = transferModel.getDestinationAccountSum();
            BigDecimal transferSum = transferModel.getTransferSum();

            int parameterIndex = 1;
            stmt.setInt(parameterIndex++,sourceAccountId);
            stmt.setBigDecimal(parameterIndex++,sourceAccountSum);

            stmt.setInt(parameterIndex++,destinationAccountId);
            stmt.setBigDecimal(parameterIndex++,destinationAccountSum);

            stmt.setBigDecimal(parameterIndex++,transferSum);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            DbConnection.closeConnection(connection);
        }
    }
    public List<Transaction> getByClientId(ReportModel reportModel) {
        Connection connection = null;
        List<Transaction> transactions = new ArrayList<>();
        try {
            connection = DbConnection.getConnection();
            String sql = "" +
                    "SELECT " +
                    "data_operatie," +
                    "id_cont_destinatie AS cont, " +
                    "suma_tranzactie AS suma_debit, " +
                    "NULL AS suma_credit, " +
                    "sold_final_sursa AS sold_curent " +
                    "FROM tranzactii " +
                    "WHERE (id_cont_sursa = ?) AND (id_cont_destinatie<>?) AND (data_operatie BETWEEN ? AND ?) " +
                    "UNION " +
                    "SELECT " +
                    "data_operatie, " +
                    "id_cont_sursa AS cont, " +
                    "NULL AS suma_debit, " +
                    "suma_tranzactie AS suma_credit, " +
                    "sold_final_destinatie AS sold_curent " +
                    "FROM tranzactii " +
                    "WHERE (id_cont_destinatie = ?) AND (data_operatie BETWEEN ? AND ?) " +
                    "ORDER BY data_operatie ASC";
            PreparedStatement stmt = connection.prepareStatement(sql);
            int parameterIndex = 1;
            Integer accountId = reportModel.getAccountId();
            LocalDate startDate = reportModel.getStartDate();
            LocalDate endDate = reportModel.getEndDate();

            stmt.setInt(parameterIndex++,accountId);
            stmt.setInt(parameterIndex++,accountId);
            stmt.setObject(parameterIndex++,startDate);
            stmt.setObject(parameterIndex++,endDate);

            stmt.setInt(parameterIndex++,accountId);
            stmt.setObject(parameterIndex++,startDate);
            stmt.setObject(parameterIndex++,endDate);

            ResultSet result = stmt.executeQuery();
            DateTimeFormatter toFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            while(result.next()) {
                Transaction transaction = new Transaction();

                LocalDate transactionDate = result.getObject("data_operatie",LocalDate.class);

                transaction.setTransactionDate(transactionDate.format(toFormat));
                transaction.setAccountId(result.getInt("cont"));
                transaction.setDebitSum(result.getBigDecimal("suma_debit"));
                transaction.setCreditSum(result.getBigDecimal("suma_credit"));
                transaction.setCurrentSum(result.getBigDecimal("sold_curent"));

                transactions.add(transaction);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            DbConnection.closeConnection(connection);
        }
        return transactions;
    }
}
